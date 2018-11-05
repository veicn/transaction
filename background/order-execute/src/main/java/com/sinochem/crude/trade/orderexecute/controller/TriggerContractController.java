package com.sinochem.crude.trade.orderexecute.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderGoods;
import com.sinochem.crude.trade.orderexecute.domain.TriggerContract;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.service.OrderGoodsService;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.crude.trade.orderexecute.service.OrderStatementService;
import com.sinochem.crude.trade.orderexecute.service.TriggerContractService;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
//@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
public class TriggerContractController  {

	@Autowired
	private TriggerContractService triggerContractService;
	@Autowired
	private OrderGoodsService goodsService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderStatementService statementService;
	
	Log log = LogFactory.getLog(TriggerContractController.class);
	
	/**
	 * 修改合约数量
	 * @param user
	 * @param contractId
	 * @param quantity
	 * @return
	 */
	@RequestMapping(value=UrlMapping.UPDATE_TRIGGERCONTRACT_QT, method=RequestMethod.POST)
	@RolesAccess({ "trade_executor"})
	@ResponseBody
	public ResultDatas<String> updateContractQuantity(CurrentUser user, 
			@RequestParam("contractId") Long contractId,
			@RequestParam("quantity") BigDecimal quantity,
			@RequestParam("quantityStatus") String quantityStatus){
		TriggerContract oldTriggerContract = triggerContractService.findByPrimaryKey(contractId);
		
		ResultDatas<String> resultData = new ResultDatas<>();
		if (contractId == null) {
			resultData.setFail("缺少参数");
			resultData.setCode("orderexecute.code.00027");
		} else if (quantity == null) {
			resultData.setFail("请填写合约数量");
			resultData.setCode("orderexecute.code.00051");
		} else if(quantity.compareTo(BigDecimal.ZERO) <= 0) {
			resultData.setFail("合约数量必须大于0");
			resultData.setCode("orderexecute.code.00052");
		}else if("1".equals(oldTriggerContract.getQuantityStatus())) {
			resultData.setFail("合约数量为最终结算时不可修改");
			resultData.setCode("orderexecute.code.00053");
		}
		
		if(resultData.getStatus() != Result.SUCCESS) {
			return resultData;
		}
		
		int validStatementNum = statementService.countValidStatementByOrderId(oldTriggerContract.getOrderId());
		if(validStatementNum > 0) {//如果有有效对账单
			resultData.setFail("当前已生成对账单，不能进行此操作");
			resultData.setCode("orderexecute.code.00054");
			return resultData;
		}
		
		//是否是第一个合约
		TriggerContract firstContract = triggerContractService.queryFirstContract(oldTriggerContract.getOrderId());
		if(firstContract == null || !firstContract.getId().equals(contractId)) {
			resultData.setFail("操作非法");
			resultData.setCode("orderexecute.code.00055");
			return resultData;
		}
		
		BigDecimal minQuantity = oldTriggerContract.getQuantity().subtract(oldTriggerContract.getSurplusQuantity());
		if(quantity.compareTo(minQuantity) < 0) {
			resultData.setFail("数量不能小于"+minQuantity.stripTrailingZeros().toPlainString());
			resultData.setCode("orderexecute.code.00056");
			return resultData;
		}
		
		OrderGoods goodsInfo = goodsService.findByOrderId(oldTriggerContract.getOrderId());
		//溢短装（默认 +- %）
		BigDecimal moreLess = goodsInfo.getMoreLess();
		//订单量
		BigDecimal orderQt = goodsInfo.getQuantity();
		
		BigDecimal tempQt = orderQt.multiply(moreLess.divide(BigDecimal.valueOf(100,0)));
		//最大量
		BigDecimal maxQt = orderQt.add(tempQt);
		//最小量
		BigDecimal minQt = orderQt.subtract(tempQt);
		
		if(quantity.compareTo(maxQt) > 0 || quantity.compareTo(minQt) < 0) {
			NumberFormat formater = DecimalFormat.getInstance();
			resultData.setFail("合约数量不能超出溢短装值【"+formater.format(minQt)+"~"+formater.format(maxQt)+"】");
			resultData.setCode("orderexecute.code.00057");
			return resultData;
		}
		
		TriggerContract triggerContractUpdate = new TriggerContract();
		triggerContractUpdate.setId(contractId);
		triggerContractUpdate.setQuantity(quantity);
		triggerContractUpdate.setQuantityStatus(quantityStatus);
		triggerContractUpdate.setSurplusQuantity(quantity.subtract(minQuantity));
		triggerContractUpdate.setUpdateDate(DateTimeUtils.currentDate());
		triggerContractUpdate.setUpdateUser(user.getMemberId());
		
		triggerContractService.updateRecordById(triggerContractUpdate);
		
		return resultData;
	}
	
	/**
	 * 合约重置
	 * @param user
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value=UrlMapping.RESET_TRIGGERCONTRACT, method=RequestMethod.POST)
	@RolesAccess({ "trade_executor"})
	@ResponseBody
	public ResultDatas<String> resetContract(CurrentUser user, 
			@RequestParam("orderId") Long orderId) {
		ResultDatas<String> resultData = new ResultDatas<>();
		
		try {
			Order orderInfo = orderService.findByPrimaryKey(orderId);
			if(orderInfo == null || !orderInfo.getSellerCustomerId().equals(user.getEpMemberId())) {
				throw new OrderExecException("orderexecute.code.00055","操作非法");
			}
			
			int validStatementNum = statementService.countValidStatementByOrderId(orderId);
			if(validStatementNum > 0) {//如果有有效对账单
				throw new OrderExecException("orderexecute.code.00054","当前已生成对账单，不能进行此操作");
			}
			
			triggerContractService.resetContract(orderId);
		} catch (OrderExecException e) {
			resultData.setCode(e.getCode());
			resultData.setFail(e.getMessage());
		} catch (Exception e) {
			resultData.setCode("orderexecute.code.00005");
			resultData.setFail(e.getMessage());
		}
		
		return resultData;
	}
}
