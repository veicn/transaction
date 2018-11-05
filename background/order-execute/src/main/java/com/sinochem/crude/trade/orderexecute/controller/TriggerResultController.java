package com.sinochem.crude.trade.orderexecute.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.domain.OrderPrice;
import com.sinochem.crude.trade.orderexecute.domain.TriggerApply;
import com.sinochem.crude.trade.orderexecute.domain.TriggerContract;
import com.sinochem.crude.trade.orderexecute.domain.TriggerResult;
import com.sinochem.crude.trade.orderexecute.domain.TriggerTransfer;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.service.OrderPriceService;
import com.sinochem.crude.trade.orderexecute.service.OrderStatementService;
import com.sinochem.crude.trade.orderexecute.service.TriggerContractService;
import com.sinochem.crude.trade.orderexecute.service.TriggerResultService;
import com.sinochem.crude.trade.orderexecute.service.TriggerTransferService;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
//@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
public class TriggerResultController  {

	@Autowired
	private TriggerResultService triggerResultService;
	@Autowired
	private TriggerContractService triggerContractService;
	@Autowired
	private OrderPriceService orderPriceService;
	@Autowired
	private TriggerTransferService triggerTransferService;
	@Autowired
	private OrderStatementService statementService;
	
	Log log = LogFactory.getLog(TriggerResultController.class);
	
	/**
	 * 拆单操作
	 * @param user
	 * @param vo
	 * @return
	 */
	@RequestMapping(value=UrlMapping.SPLIT_TRIGGER_ORDER, method=RequestMethod.POST)
	@RolesAccess({ "trade_executor"})
	@ResponseBody
	public ResultDatas<String> saveSplitOrderInfo(CurrentUser user, 
			@RequestParam(value="triggerContractId", required=true) Long triggerContractId,
			@RequestParam(value="lockPrice", required=true) BigDecimal lockPrice,
			@RequestParam(value="lockQuantity", required=true) BigDecimal lockQuantity){
		
		ResultDatas<String> resultData = new ResultDatas<>();
		if (triggerContractId == null) {
			resultData.setFail("缺少参数");
			resultData.setCode("orderexecute.code.00027");
			return resultData;
		} else if (lockPrice == null) {
			resultData.setFail("价格不能为空");
			resultData.setCode("orderexecute.code.00039");
			return resultData;
		} else if (lockQuantity == null) {
			resultData.setFail("数量不能为空");
			resultData.setCode("orderexecute.code.00044");
			return resultData;
		} else if (lockQuantity.compareTo(BigDecimal.ZERO) <= 0) {
			resultData.setFail("数量必须大于0");
			resultData.setCode("orderexecute.code.00049");
			return resultData;
		} else if (lockPrice.compareTo(BigDecimal.ZERO) <= 0) {
			resultData.setFail("价格必须大于0");
			resultData.setCode("orderexecute.code.00040");
			return resultData;
		}
		
		TriggerContract oldTriggerContract = triggerContractService.findByPrimaryKey(triggerContractId);
		if(oldTriggerContract == null) {
			resultData.setFail("合约ID不存在，请检查参数");
			resultData.setCode("orderexecute.code.00058");
			return resultData;
		}
		if(lockQuantity.compareTo(oldTriggerContract.getSurplusQuantity()) > 0) {
			resultData.setFail("拆单量不足，最多可拆量"+oldTriggerContract.getSurplusQuantity());
			resultData.setCode("orderexecute.code.00059");
			return resultData;
		}
		
		Date now = DateTimeUtils.currentDate();
		OrderPrice orderInfo = orderPriceService.findByOrderId(oldTriggerContract.getOrderId());
		TriggerTransfer triggerTransferInfo = triggerTransferService.queryByTriggerContractId(triggerContractId);
		
		//拆单直接点价结果
		TriggerResult triggerResult = new TriggerResult();
		triggerResult.setUuid(KeyGenUtils.newUuid());
		triggerResult.setOrderId(oldTriggerContract.getOrderId());
		triggerResult.setContractId(oldTriggerContract.getId());
		triggerResult.setContractName(oldTriggerContract.getMonth());
		triggerResult.setLockTime(now);
		triggerResult.setLockQuantity(lockQuantity);
		triggerResult.setLockPrice(lockPrice);
		if(triggerTransferInfo != null) {
			triggerResult.setDifferAmount(lockQuantity.multiply(triggerTransferInfo.getDifferPrice()));
			triggerResult.setDifferDesc(triggerTransferInfo.getDifferPrice().stripTrailingZeros().toPlainString());
		}
		triggerResult.setAgio(orderInfo.getAgio());
		triggerResult.setUnitPrice(lockPrice.add(orderInfo.getAgio()));
		triggerResult.setTotalAmount(triggerResult.getUnitPrice().multiply(triggerResult.getLockQuantity()).setScale(3));
		triggerResult.setCreateDate(now);
		triggerResult.setCreateUser(user.getMemberId());
		
		//更新合约信息
		TriggerContract triggerContractUpdate = new TriggerContract();
		triggerContractUpdate.setId(triggerContractId);
		triggerContractUpdate.setSurplusQuantity(oldTriggerContract.getSurplusQuantity().subtract(lockQuantity));
		triggerContractUpdate.setDoneQuantity(oldTriggerContract.getDoneQuantity().add(lockQuantity));
		triggerContractUpdate.setUpdateDate(now);
		triggerContractUpdate.setUpdateUser(user.getMemberId());
		
		triggerResultService.saveSplitOrderInfo(triggerResult, triggerContractUpdate);
		
		return resultData;
	}
	
	/**
	 * 更新拆单数量、价格
	 * @param user
	 * @param id
	 * @param quantity
	 * @param price
	 * @return
	 */
	@RequestMapping(value=UrlMapping.UPDATE_SPLIT_ORDER, method=RequestMethod.POST)
	@RolesAccess({ "trade_executor"})
	public ResultDatas<String> updateSplitOrder(CurrentUser user, 
			@RequestParam("triggerResultId") Long id,
			@RequestParam("quantity") BigDecimal quantity,
			@RequestParam("price") BigDecimal price) {
		ResultDatas<String> resultData = new ResultDatas<>();
		if (id == null) {
			resultData.setFail("缺少参数");
			resultData.setCode("orderexecute.code.00027");
		} else if (quantity == null || quantity.compareTo(BigDecimal.ZERO) <=0) {
			resultData.setFail("数量必须大于0");
			resultData.setCode("orderexecute.code.00049");
		} else if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
			resultData.setFail("价格必须大于0");
			resultData.setCode("orderexecute.code.00046");
		}
		if (resultData.getStatus() != Result.SUCCESS) {
			return resultData;
		}
		
		TriggerResult oldTriggerResult = triggerResultService.findByPrimaryKey(id);
		if(oldTriggerResult == null) {
			resultData.setFail("拆单数据不存在");
			resultData.setCode("orderexecute.code.00060");
			return resultData;
		}
		Date now = DateTimeUtils.currentDate();
		
		int validStatementNum = statementService.countValidStatementByOrderId(oldTriggerResult.getOrderId());
		if(validStatementNum > 0) {//如果有有效对账单
			resultData.setFail("当前已生成对账单，不能进行此操作");
			resultData.setCode("orderexecute.code.00054");
			return resultData;
		}
		
		//>> 更新拆单信息
		TriggerResult triggerResultUpdate = new TriggerResult();
		triggerResultUpdate.setId(oldTriggerResult.getId());
		triggerResultUpdate.setLockPrice(price);
		triggerResultUpdate.setLockQuantity(quantity);
		if(oldTriggerResult.getDifferAmount() != null) {
			//月差
			BigDecimal differPrice = oldTriggerResult.getDifferAmount().divide(oldTriggerResult.getLockQuantity());
			triggerResultUpdate.setDifferAmount(quantity.multiply(differPrice));
		}
		triggerResultUpdate.setUnitPrice(price.add(oldTriggerResult.getAgio()));
		triggerResultUpdate.setTotalAmount(quantity.multiply(triggerResultUpdate.getUnitPrice()));
		triggerResultUpdate.setUpdateDate(now);
		triggerResultUpdate.setUpdateUser(user.getMemberId());
		
		TriggerContract oldTriggerContract = triggerContractService.findByPrimaryKey(oldTriggerResult.getContractId());
		if(oldTriggerContract == null) {
			resultData.setFail("旧数据缺少相关参数，无法完成此操作");
			resultData.setCode("orderexecute.code.00061");
			return resultData;
		}
		BigDecimal quantityBalance = oldTriggerResult.getLockQuantity().subtract(quantity);
		
		//>> 更新合约信息
		TriggerContract triggerContractUpdate = new TriggerContract();
		triggerContractUpdate.setId(oldTriggerResult.getContractId());
		//加上多余的拆单量
		triggerContractUpdate.setSurplusQuantity(oldTriggerContract.getSurplusQuantity().add(quantityBalance));
		if(triggerContractUpdate.getSurplusQuantity().compareTo(BigDecimal.ZERO) < 0) {
			resultData.setFail("最多可修改数量"+(oldTriggerResult.getLockQuantity().add(oldTriggerContract.getSurplusQuantity())));
			resultData.setCode("orderexecute.code.00062");
			return resultData;
		}
		//减去多余的拆单量
		triggerContractUpdate.setDoneQuantity(oldTriggerContract.getDoneQuantity().subtract(quantityBalance));
		triggerContractUpdate.setUpdateDate(now);
		triggerContractUpdate.setUpdateUser(user.getMemberId());
		
		//>> 更新点价申请信息（如果是点价申请的拆单）
		TriggerApply triggerApplyUpdate = null;
		if(oldTriggerResult.getApplyId() != null) {
			triggerApplyUpdate = new TriggerApply();
			triggerApplyUpdate.setId(oldTriggerResult.getApplyId());
			triggerApplyUpdate.setLockTime(now);
			triggerApplyUpdate.setLockQuantity(quantity);
			triggerApplyUpdate.setLockPrice(price);
			triggerApplyUpdate.setDifferAmount(triggerResultUpdate.getDifferAmount());
		}
		
		triggerResultService.updateSplitOrder(triggerResultUpdate, triggerContractUpdate, triggerApplyUpdate);
		
		return resultData;
	}
	
}
