package com.sinochem.crude.trade.orderexecute.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.constants.TriggerApplyStatusEnum;
import com.sinochem.crude.trade.orderexecute.domain.OrderPrice;
import com.sinochem.crude.trade.orderexecute.domain.TriggerApply;
import com.sinochem.crude.trade.orderexecute.domain.TriggerContract;
import com.sinochem.crude.trade.orderexecute.domain.TriggerResult;
import com.sinochem.crude.trade.orderexecute.domain.TriggerTransfer;
import com.sinochem.crude.trade.orderexecute.model.TriggerApplyVO;
import com.sinochem.crude.trade.orderexecute.service.OrderPriceService;
import com.sinochem.crude.trade.orderexecute.service.TriggerApplyService;
import com.sinochem.crude.trade.orderexecute.service.TriggerContractService;
import com.sinochem.crude.trade.orderexecute.service.TriggerTransferService;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
//@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
public class TriggerApplyController  {

	@Autowired
	private TriggerApplyService triggerApplyService;
	@Autowired
	private TriggerContractService triggerContractService;
	@Autowired
	private OrderPriceService orderPriceService;
	@Autowired
	private TriggerTransferService triggerTransferService;
	
	Log log = LogFactory.getLog(TriggerApplyController.class);
	
	/**
	 * 提交点价申请
	 * @param user
	 * @param vo
	 * @return
	 */
	@RequestMapping(value=UrlMapping.SAVE_TRIGGER_APPLY, method=RequestMethod.POST)
	@RolesAccess({ "trade_executor"})
	public ResultDatas<String> saveApply(CurrentUser user, TriggerApplyVO vo) {
		ResultDatas<String> resultData = new ResultDatas<>();
		if (vo.getContractId() == null) {
			resultData.setFail("缺少参数");
			resultData.setCode("orderexecute.code.00027");
		} else if(vo.getApplyQuantity() == null || vo.getApplyQuantity().compareTo(BigDecimal.ZERO) <= 0) {
			resultData.setFail("点价数量必须大于0");
			resultData.setCode("orderexecute.code.00038");
		} else if(vo.getApplyPrice() == null) {
			resultData.setFail("点价价格不能为空");
			resultData.setCode("orderexecute.code.00039");
		} else if(vo.getApplyPrice().compareTo(BigDecimal.ZERO) <= 0) {
			resultData.setFail("点价价格必须大于0");
			resultData.setCode("orderexecute.code.00040");
		}
		
		if(resultData.getStatus() != Result.SUCCESS) {
			return resultData;
		}
		
		TriggerContract oldTriggerContract = triggerContractService.findByPrimaryKey(vo.getContractId());
		if(oldTriggerContract.getSurplusQuantity().compareTo(vo.getApplyQuantity()) < 0) {
			resultData.setFail("最大只能点价 " + oldTriggerContract.getSurplusQuantity().stripTrailingZeros().toPlainString());
			resultData.setCode("orderexecute.code.00041");
			resultData.setParams(new String[] {oldTriggerContract.getSurplusQuantity().stripTrailingZeros().toPlainString()});
			return resultData;
		}
		
//		OrderPrice orderPriceInfo = orderPriceService.findByOrderId(oldTriggerContract.getOrderId());
//		BigDecimal minLockQuantity = orderPriceInfo.getLockQuantityMin();//点价最小量
//		if(minLockQuantity != null && vo.getApplyQuantity().compareTo(minLockQuantity) < 0) {
//			resultData.setFail("点价数量最低 "+minLockQuantity);
//			return resultData;
//		}
		
		//点价申请记录
		TriggerApply triggerApply = new TriggerApply();
		triggerApply.setUuid(KeyGenUtils.newUuid());
		triggerApply.setOrderId(oldTriggerContract.getOrderId());
		triggerApply.setContractId(vo.getContractId());
		triggerApply.setApplyTime(DateTimeUtils.currentDate());
		triggerApply.setName(oldTriggerContract.getName());
		triggerApply.setMonth(oldTriggerContract.getMonth());
//		triggerApply.setEndTime(oldTriggerContract.getEndTime());
		triggerApply.setApplyQuantity(vo.getApplyQuantity());
		triggerApply.setApplyPrice(vo.getApplyPrice());
		triggerApply.setStatus(TriggerApplyStatusEnum.STATUS_10.getCode());
		triggerApply.setCreateDate(DateTimeUtils.currentDate());
		triggerApply.setCreateUser(user.getMemberId());
		
		//更新合约信息
		TriggerContract triggerContractUpdate = new TriggerContract();
		triggerContractUpdate.setId(vo.getContractId());
		triggerContractUpdate.setDoneQuantity(oldTriggerContract.getDoneQuantity().add(vo.getApplyQuantity()));
		triggerContractUpdate.setSurplusQuantity(oldTriggerContract.getSurplusQuantity().subtract(vo.getApplyQuantity()));
		
		triggerApplyService.saveTriggerApply(triggerApply, triggerContractUpdate);
		
		return resultData;
	}
	
	/**
	 * 撤销点价申请
	 * @param user
	 * @param id
	 * @return
	 */
	@RequestMapping(value=UrlMapping.CANCEL_TRIGGER_APPLY, method=RequestMethod.POST)
	@RolesAccess({ "trade_executor"})
	public ResultDatas<String> cancelApply(CurrentUser user, 
			@RequestParam("applyId") Long id) {
		ResultDatas<String> resultData = new ResultDatas<>();
		if(id == null) {
			resultData.setFail("操作失败");
			resultData.setCode("orderexecute.code.00042");
			return resultData;
		}
		
		TriggerApply oldTriggerApply = triggerApplyService.findByPrimaryKey(id);
		if(oldTriggerApply.getStatus().equals(TriggerApplyStatusEnum.STATUS_10.getCode())) {
//			Date now = DateTimeUtils.currentDate();
//			
//			if(oldTriggerApply.getEndTime().before(now)) {//已过期
//				resultData.setFail("申请已过期");
//				return resultData;
//			}
		}else {
			resultData.setFail("当前状态不允许此操作");
			resultData.setFail("orderexecute.code.00043");
			return resultData;
		}
		
		//更新点价申请信息
		TriggerApply triggerApplyUpdate = new TriggerApply();
		triggerApplyUpdate.setId(id);
		triggerApplyUpdate.setStatus(TriggerApplyStatusEnum.STATUS_40.getCode());
		triggerApplyUpdate.setUpdateDate(DateTimeUtils.currentDate());
		triggerApplyUpdate.setUpdateUser(user.getMemberId());
		
		//更新合约信息
		TriggerContract oldTriggerContract = triggerContractService.findByPrimaryKey(oldTriggerApply.getContractId());
		
		TriggerContract triggerContractUpdate = new TriggerContract();
		triggerContractUpdate.setId(oldTriggerApply.getContractId());
		triggerContractUpdate.setSurplusQuantity(oldTriggerContract.getSurplusQuantity().add(oldTriggerApply.getApplyQuantity()));
		triggerContractUpdate.setDoneQuantity(oldTriggerContract.getDoneQuantity().subtract(oldTriggerApply.getApplyQuantity()));
		
		triggerApplyService.completeTriggerApply(triggerApplyUpdate, null, triggerContractUpdate);
		
		return resultData;
	}
	
	/**
	 * 点价申请处理
	 * @param user
	 * @param id
	 * @param lockQuantity
	 * @param lockPrice
	 * @return
	 */
	@RequestMapping(value=UrlMapping.TRIGGER_APPLY_PROCESS, method=RequestMethod.POST)
	@RolesAccess({ "trade_executor"})
	public ResultDatas<String> applyProcess(CurrentUser user, 
			@RequestParam("applyId") Long id,
			@RequestParam("lockQuantity") BigDecimal lockQuantity,
			@RequestParam("lockPrice") BigDecimal lockPrice){
		
		ResultDatas<String> resultData = new ResultDatas<>();
		if(id == null) {
			resultData.setFail("缺少参数");
			resultData.setCode("orderexecute.code.00027");
			return resultData;
		}else if(lockQuantity == null) {
			resultData.setFail("数量不能为空");
			resultData.setCode("orderexecute.code.00044");
			return resultData;
		}else if(lockPrice == null) {
			resultData.setFail("价格不能为空");
			resultData.setCode("orderexecute.code.00045");
			return resultData;
		}else if(lockPrice.compareTo(BigDecimal.ZERO) <= 0) {
			resultData.setFail("价格必须大于0");
			resultData.setCode("orderexecute.code.00046");
			return resultData;
		}
		
		TriggerApply oldTriggerApply = triggerApplyService.findByPrimaryKey(id);
		if (oldTriggerApply == null) {
			resultData.setFail("点价申请不存在");
			resultData.setCode("orderexecute.code.00047");
		} else if (!TriggerApplyStatusEnum.STATUS_10.getCode().equals(oldTriggerApply.getStatus())) {
			resultData.setFail("当前状态不允许此操作，请刷新页面");
			resultData.setCode("orderexecute.code.00048");
		} else if (lockQuantity.compareTo(BigDecimal.ZERO) <= 0) {
			resultData.setFail("数量必须大于0");
			resultData.setCode("orderexecute.code.00049");
		} else if(lockQuantity.compareTo(oldTriggerApply.getApplyQuantity()) > 0) {
			resultData.setFail("超过最大申请量"+oldTriggerApply.getApplyQuantity().stripTrailingZeros().toPlainString());
			resultData.setCode("orderexecute.code.00050");
		}
		
		if(resultData.getStatus() != Result.SUCCESS) {
			return resultData;
		}
		
		OrderPrice orderPriceInfo = orderPriceService.findByOrderId(oldTriggerApply.getOrderId());
		TriggerContract oldTriggerContract = triggerContractService.findByPrimaryKey(oldTriggerApply.getContractId());
		TriggerTransfer triggerTransferInfo = triggerTransferService.queryByTriggerContractId(oldTriggerApply.getContractId());
		
		Date now = DateTimeUtils.currentDate();
		//更新点价信息
		TriggerApply triggerApplyUpdate = new TriggerApply();
		triggerApplyUpdate.setId(id);
		triggerApplyUpdate.setLockTime(now);
		triggerApplyUpdate.setLockQuantity(lockQuantity);
		triggerApplyUpdate.setLockPrice(lockPrice);
		if(triggerTransferInfo != null) {
			BigDecimal differPrice = lockQuantity.multiply(triggerTransferInfo.getDifferPrice());
			triggerApplyUpdate.setDifferAmount(differPrice);
			triggerApplyUpdate.setDifferDesc(triggerTransferInfo.getDifferPrice().stripTrailingZeros().toPlainString());
		}
		triggerApplyUpdate.setStatus(TriggerApplyStatusEnum.STATUS_30.getCode());
		triggerApplyUpdate.setUpdateDate(now);
		triggerApplyUpdate.setUpdateUser(user.getMemberId());
		
		//添加点价（拆单）结果
		TriggerResult triggerResultInsert = new TriggerResult();
		triggerResultInsert.setUuid(KeyGenUtils.newUuid());
		triggerResultInsert.setOrderId(oldTriggerApply.getOrderId());
		triggerResultInsert.setApplyId(oldTriggerApply.getId());
		triggerResultInsert.setContractId(oldTriggerContract.getId());
		triggerResultInsert.setContractName(oldTriggerContract.getMonth());
		triggerResultInsert.setLockTime(now);
		triggerResultInsert.setLockQuantity(triggerApplyUpdate.getLockQuantity());
		triggerResultInsert.setLockPrice(triggerApplyUpdate.getLockPrice());
		triggerResultInsert.setDifferAmount(triggerApplyUpdate.getDifferAmount());
		triggerResultInsert.setDifferDesc(triggerApplyUpdate.getDifferDesc());
		triggerResultInsert.setAgio(orderPriceInfo.getAgio());
		triggerResultInsert.setUnitPrice(triggerApplyUpdate.getLockPrice().add(orderPriceInfo.getAgio()));
		triggerResultInsert.setTotalAmount(triggerResultInsert.getUnitPrice().multiply(triggerApplyUpdate.getLockQuantity()));
		triggerResultInsert.setCreateDate(now);
		triggerResultInsert.setCreateUser(user.getMemberId());
		
		//更新合约信息
		TriggerContract triggerContractUpdate = null;
		
		BigDecimal quantityBalance = oldTriggerApply.getApplyQuantity().subtract(lockQuantity);
		//申请量大于提交量 或 申请量小于提交量
		if(quantityBalance.compareTo(BigDecimal.ZERO) > 0 || quantityBalance.compareTo(BigDecimal.ZERO) < 0) {
			triggerContractUpdate = new TriggerContract();
			triggerContractUpdate.setId(oldTriggerApply.getContractId());
			//加上多余的申请量
			triggerContractUpdate.setSurplusQuantity(oldTriggerContract.getSurplusQuantity().add(quantityBalance));
			//减去多余的已申请量
			triggerContractUpdate.setDoneQuantity(oldTriggerContract.getDoneQuantity().subtract(quantityBalance));
			
			triggerContractUpdate.setUpdateDate(now);
			triggerContractUpdate.setUpdateUser(user.getMemberId());
		}
		
		triggerApplyService.completeTriggerApply(triggerApplyUpdate, triggerResultInsert, triggerContractUpdate);
		
		return resultData;
	}
	
	/**
	 * 点价-未成功
	 * @param user
	 * @param id
	 * @return
	 */
	@RequestMapping(value=UrlMapping.TRIGGER_APPLY_NODEAL, method=RequestMethod.POST)
	@RolesAccess({ "trade_executor"})
	public ResultDatas<String> applyNoDeal(CurrentUser user, 
			@RequestParam("applyId") Long id){
		ResultDatas<String> resultData = new ResultDatas<>();
		if(id == null) {
			resultData.setFail("缺少参数");
			resultData.setCode("orderexecute.code.00027");
			return resultData;
		}
		
		//更新点价申请信息
		TriggerApply triggerApplyUpdate = new TriggerApply();
		triggerApplyUpdate.setId(id);
		triggerApplyUpdate.setStatus(TriggerApplyStatusEnum.STATUS_20.getCode());
		triggerApplyUpdate.setUpdateDate(DateTimeUtils.currentDate());
		triggerApplyUpdate.setUpdateUser(user.getMemberId());
		
		//更新合约信息
		TriggerApply oldTriggerApply = triggerApplyService.findByPrimaryKey(id);
		TriggerContract oldTriggerContract = triggerContractService.findByPrimaryKey(oldTriggerApply.getContractId());
		
		TriggerContract triggerContractUpdate = new TriggerContract();
		triggerContractUpdate.setId(oldTriggerApply.getContractId());
		triggerContractUpdate.setSurplusQuantity(oldTriggerContract.getSurplusQuantity().add(oldTriggerApply.getApplyQuantity()));
		triggerContractUpdate.setDoneQuantity(oldTriggerContract.getDoneQuantity().subtract(oldTriggerApply.getApplyQuantity()));
		
		triggerApplyService.completeTriggerApply(triggerApplyUpdate, null, triggerContractUpdate);
		
		return resultData;
	}
}
