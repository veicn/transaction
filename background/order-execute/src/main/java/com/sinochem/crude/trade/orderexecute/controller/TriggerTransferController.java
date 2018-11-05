package com.sinochem.crude.trade.orderexecute.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.constants.TriggerContractStatusEnum;
import com.sinochem.crude.trade.orderexecute.domain.OrderPrice;
import com.sinochem.crude.trade.orderexecute.domain.TriggerContract;
import com.sinochem.crude.trade.orderexecute.domain.TriggerTransfer;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.TriggerTransferVO;
import com.sinochem.crude.trade.orderexecute.service.OrderPriceService;
import com.sinochem.crude.trade.orderexecute.service.TriggerContractService;
import com.sinochem.crude.trade.orderexecute.service.TriggerTransferService;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
//@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
public class TriggerTransferController  {

	@Autowired
	private TriggerTransferService triggerTransferService;
	@Autowired
	private TriggerContractService triggerContractService;
	@Autowired
	private OrderPriceService orderPriceService;
	
	Log log = LogFactory.getLog(TriggerTransferController.class);
	
	/**
	 * 点价转月新增预览
	 * @param triggerContractId
	 * @param modelMap
	 */
	@RequestMapping(value=UrlMapping.TRIGGER_TRANSFER_ADD_INIT, method=RequestMethod.GET)
	public void addBomp(@RequestParam("triggerContractId") Long triggerContractId, ModelMap modelMap) {
		if(triggerContractId == null) {
			return;
		}
		
		TriggerContract triggerContract = triggerContractService.findByPrimaryKey(triggerContractId);
		OrderPrice orderPriceInfo = orderPriceService.findByOrderId(triggerContract.getOrderId());
		
		modelMap.put("triggerContractInfo", triggerContract);
		modelMap.put("transferCountMax", orderPriceInfo.getTransferCount());
	}
	
	/**
	 * 保存转月信息
	 * @param user
	 * @param vo
	 * @return
	 */
	@RequestMapping(value=UrlMapping.SAVE_TRIGGER_TRANSFER, method=RequestMethod.POST)
	@RolesAccess({ "trade_executor"})
	@ResponseBody
	public ResultDatas<String> save(CurrentUser user, TriggerTransferVO vo) {
		ResultDatas<String> resultData = new ResultDatas<>();
		Date transferEndDate = null;
		if (vo.getContractId() == null) {
			resultData.setFail("缺少参数");
			resultData.setCode("orderexecute.code.00027");
		} else if (StringUtils.isEmpty(vo.getMonth())) {
			resultData.setFail("合约月份不能为空");
			resultData.setCode("orderexecute.code.00063");
		} else if (StringUtils.isEmpty(vo.getTransferEndDateStr())) {
			resultData.setFail("转月截止时间不能为空");
			resultData.setCode("orderexecute.code.00063");
		} else if (vo.getDifferPrice() == null) {
			resultData.setFail("月差不能为空");
			resultData.setCode("orderexecute.code.00064");
		} else if (vo.getTransferQuantity() == null || vo.getTransferQuantity().compareTo(BigDecimal.ZERO)<=0) {
			resultData.setFail("转月数量必须大于0");
			resultData.setCode("orderexecute.code.00065");
		} else {
			try {
				transferEndDate = DateTimeUtils.toDate(vo.getTransferEndDateStr(), "yyyy-MM-dd");
				if(DateTimeUtils.currentDate().after(transferEndDate)) {
					resultData.setFail("截止时间必须大于当前时间");
					resultData.setCode("orderexecute.code.00066");
				}
			} catch (Exception e) {
				resultData.setFail("转月截止时间格式不正确");
				resultData.setCode("orderexecute.code.00067");
			}
		}
		
		if(resultData.getStatus() != Result.SUCCESS) {
			return resultData;
		}
		
		synchronized (this) {
			TriggerContract oldTriggerContract = triggerContractService.findByPrimaryKey(vo.getContractId());
			if(oldTriggerContract == null) {
				resultData.setFail("参数错误：当前合约不存在");
				resultData.setCode("orderexecute.code.00068");
				return resultData;
			}
			
			//转月次数控制（相同月份次数），同月份不限制次数
			int transferCount = triggerTransferService.countTransferMonth(oldTriggerContract.getOrderId(), vo.getMonth());
			OrderPrice orderPriceInfo = orderPriceService.findByOrderId(oldTriggerContract.getOrderId());
			if(orderPriceInfo.getTransferCount() != null && orderPriceInfo.getTransferCount().compareTo(transferCount) <= 0) {
				resultData.setFail("转月次数已达上限（"+orderPriceInfo.getTransferCount()+"次）");
				resultData.setCode("orderexecute.code.00069");
				return resultData;
			}
			
			if(vo.getMonth().equals(oldTriggerContract.getMonth())) {//转月的合约月份不能跟自身相同
				resultData.setFail("转月合约月份不能和自身相同");
				resultData.setCode("orderexecute.code.00070");
				return resultData;
			}
			if(oldTriggerContract.getSurplusQuantity().compareTo(vo.getTransferQuantity()) < 0) {
				resultData.setFail("可转数量不足，最多"+oldTriggerContract.getSurplusQuantity().stripTrailingZeros().toPlainString());
				resultData.setCode("orderexecute.code.00071");
				return resultData;
			}
			
			Date now = DateTimeUtils.currentDate();
			//转月信息
			TriggerTransfer triggerTransfer = new TriggerTransfer();
			triggerTransfer.setUuid(KeyGenUtils.newUuid());
			triggerTransfer.setOrderId(oldTriggerContract.getOrderId());
			triggerTransfer.setOldMonth(oldTriggerContract.getMonth());
			triggerTransfer.setMonth(vo.getMonth());
			triggerTransfer.setName(oldTriggerContract.getName());
			triggerTransfer.setTransferTime(now);
			triggerTransfer.setTransferQuantity(vo.getTransferQuantity());
			triggerTransfer.setDifferPrice(vo.getDifferPrice());
			triggerTransfer.setDifferAmount(vo.getDifferPrice().multiply(vo.getTransferQuantity()).setScale(3));
			triggerTransfer.setCreateDate(now);
			triggerTransfer.setCreateUser(user.getMemberId());
			
			//转月后的新合约
			TriggerContract newTriggerContract = new TriggerContract();
			newTriggerContract.setUuid(KeyGenUtils.newUuid());
			newTriggerContract.setOrderId(oldTriggerContract.getOrderId());
			newTriggerContract.setName(oldTriggerContract.getName());
			newTriggerContract.setMonth(vo.getMonth());
			newTriggerContract.setPriceType(oldTriggerContract.getPriceType());
			newTriggerContract.setCommodity(oldTriggerContract.getCommodity());
			newTriggerContract.setMarket(oldTriggerContract.getMarket());
			newTriggerContract.setPriceSource(oldTriggerContract.getPriceSource());
			newTriggerContract.setQuantity(vo.getTransferQuantity());
			newTriggerContract.setBeginTime(now);
			newTriggerContract.setEndTime(transferEndDate);
			newTriggerContract.setTransferQuantity(BigDecimal.ZERO);
			newTriggerContract.setDoneQuantity(BigDecimal.ZERO);
			newTriggerContract.setSurplusQuantity(vo.getTransferQuantity());
			newTriggerContract.setStatus(TriggerContractStatusEnum.STATUS_00.getCode());
			newTriggerContract.setCreateDate(now);
			newTriggerContract.setCreateUser(user.getMemberId());
			
			//更新旧合约数值
			TriggerContract triggerContractUpdate = new TriggerContract();
			triggerContractUpdate.setId(oldTriggerContract.getId());
			triggerContractUpdate.setSurplusQuantity(oldTriggerContract.getSurplusQuantity().subtract(vo.getTransferQuantity()));
			triggerContractUpdate.setTransferQuantity(oldTriggerContract.getTransferQuantity().add(vo.getTransferQuantity()));
			triggerContractUpdate.setCreateDate(now);
			triggerContractUpdate.setCreateUser(user.getMemberId());
			
			triggerTransferService.saveTransferInfo(triggerTransfer, newTriggerContract, triggerContractUpdate);
		}
		
		return resultData;
	}
	
}
