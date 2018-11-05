package com.sinochem.crude.trade.orderexecute.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.ValueSet;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.commons.constants.ValueSetGroupConstant;
import com.sinochem.crude.trade.orderexecute.commons.utils.ValueSetUtil;
import com.sinochem.crude.trade.orderexecute.domain.OrderShipUnloadinginfo;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.InspectionMemberSaveFormVO;
import com.sinochem.crude.trade.orderexecute.model.OrderShipUnloadinginfoVO;
import com.sinochem.crude.trade.orderexecute.service.OrderShipUnloadinginfoService;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
public class OrderShipUnloadinginfoController  {

	@Autowired
	private OrderShipUnloadinginfoService orderShipUnloadinginfoService;
	
	@Resource(name="orderExecuteServer")
	private URLBroker orderExecuteServer;
	@Resource(name="systemServer")
	private URLBroker systemServer;
	
	Log log = LogFactory.getLog(OrderShipUnloadinginfoController.class);
	/**
	 * 获取卸港值集
	 *
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.QUERY_UNLOADING_TYPE, method = RequestMethod.GET)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public ResultDatas<List<CodeValue>> queryUnloadingType() {
		ResultDatas<List<CodeValue>> res = new ResultDatas<List<CodeValue>>();
		try{
			ValueSet loadingSet = ValueSetUtil.instance(ValueSetGroupConstant.UN_LOAD_PORT);//获取卸港值集
			res.setDatas(loadingSet.getCodeList());
		}catch (OrderExecException e) {
			log.error("saveShipLoading error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("queryLoadingType error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 卸港修改
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.UPDATE_SHIP_UNLOADING)
	@RolesAccess({ "trade_executor"})
	public Result updateShipUnloading(OrderShipUnloadinginfoVO vo, CurrentUser user) {
		Result res = new Result();
		try {
			orderShipUnloadinginfoService.updateShipUnloadingInfo(vo,user);
		} catch (OrderExecException e) {
			log.error("updateShipUnloading error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("updateShipUnloading error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 卸港新增
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.SAVE_SHIP_UNLOADING)
	@RolesAccess({ "trade_executor"})
	public Result saveShipUnloading(OrderShipUnloadinginfoVO vo, CurrentUser user) {
		
		Result res = new Result();
		try {
			orderShipUnloadinginfoService.insertShipUnloading(vo,user);
			
		} catch (OrderExecException e) {
			log.error("saveShipLoading error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("saveShipLoading error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 删除卸货信息
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.DEL_SHIP_UNLOADING)
	@RolesAccess({ "trade_executor"})
	public Result delShipUnloading(OrderShipUnloadinginfoVO vo, CurrentUser user) {
		
		Result res = new Result();
		vo.setAliveFlag(Constants.ALIEVE_FLAG_NO);
		try {
			orderShipUnloadinginfoService.updateRecordById(vo);
		} catch (OrderExecException e) {
			log.error("delShipLoading error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("delShipLoading error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 指定商检信息保存
	 * @param inspectionId
	 * @param contactName
	 * @return
	 */
	@RequestMapping(value=UrlMapping.SAVE_UNLOADING_INSPECTION_MEMBER, method=RequestMethod.POST)
	@ResponseBody
	@RolesAccess({"trade_executor"})
	public Result saveInspectionMember(CurrentUser user,InspectionMemberSaveFormVO formVO){
		Result result = new Result();
		Long id = formVO.getId();
		
		if(id == null) {
			result.setFail("缺少参数");
			result.setCode("orderexecute.code.00027");
			return result;
		}
		//查询是否已维护了商检信息
		OrderShipUnloadinginfo unLoadingInfo = orderShipUnloadinginfoService.findByPrimaryKey(id);
		if(StringUtils.isNotBlank(unLoadingInfo.getInspection())) {
			result.setFail("商检信息已经维护");
			result.setCode("orderexecute.code.00204");
			return result;
		}
		
		OrderShipUnloadinginfo unloadinginfoUpdate = new OrderShipUnloadinginfo();
		unloadinginfoUpdate.setId(formVO.getId());
		unloadinginfoUpdate.setInspection(formVO.getMemberName());
		unloadinginfoUpdate.setInspectionMemberid(formVO.getMemberId());
		unloadinginfoUpdate.setInspectionContact(formVO.getContact());
		unloadinginfoUpdate.setInspectionEmail(formVO.getMail());
		unloadinginfoUpdate.setInspectionTel(formVO.getTel());
		unloadinginfoUpdate.setInspectionTerminal(formVO.getTerminal());
		unloadinginfoUpdate.setUpdateDate(DateTimeUtils.currentDate());
		unloadinginfoUpdate.setUpdateUser(user.getMemberId());
		
		orderShipUnloadinginfoService.updateRecordById(unloadinginfoUpdate);
		
		return result;
	}
	
}
