package com.sinochem.crude.trade.orderexecute.controller;

import java.util.List;

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
import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.ValueSet;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.commons.constants.CredentialsConstant;
import com.sinochem.crude.trade.orderexecute.commons.constants.ValueSetGroupConstant;
import com.sinochem.crude.trade.orderexecute.commons.utils.ValueSetUtil;
import com.sinochem.crude.trade.orderexecute.domain.InspectionStaff;
import com.sinochem.crude.trade.orderexecute.domain.OrderShipLoadinginfo;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.InspectionMemberSaveFormVO;
import com.sinochem.crude.trade.orderexecute.model.OrderShipLoadinginfoVO;
import com.sinochem.crude.trade.orderexecute.service.InspectionStaffService;
import com.sinochem.crude.trade.orderexecute.service.OrderShipLoadinginfoService;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
public class OrderShipLoadinginfoController  {

	@Autowired
	private OrderShipLoadinginfoService orderShipLoadinginfoService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private InspectionStaffService inspectionStaffService;
	
	Log log = LogFactory.getLog(OrderShipLoadinginfoController.class);
	/**
	 * 获取装港值集
	 *
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.QUERY_LOADING_TYPE, method = RequestMethod.GET)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public ResultDatas<List<CodeValue>> queryLoadingType() {
		ResultDatas<List<CodeValue>> res = new ResultDatas<List<CodeValue>>();
		try{
			ValueSet loadingSet = ValueSetUtil.instance(ValueSetGroupConstant.LOAD_PORT);//获取装港值集
			res.setDatas(loadingSet.getCodeList());
		} catch (OrderExecException e) {
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
	 * 装港修改
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.UPDATE_SHIP_LOADING)
	@RolesAccess({ "trade_executor"})
	public Result updateShipLoading(OrderShipLoadinginfoVO vo, CurrentUser user) {
		Result res = new Result();

		try {
			orderShipLoadinginfoService.updateShipLoading(vo,user);

		} catch (OrderExecException e) {
			log.error("updateShipLoading error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("updateShipLoading error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 装港新增
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.SAVE_SHIP_LOADING)
	@RolesAccess({ "trade_executor"})
	public Result saveShipLoading(OrderShipLoadinginfoVO vo, CurrentUser user) {
		
		Result res = new Result();
		try {
			orderShipLoadinginfoService.insertShipLoading(vo,user);
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
	 * 删除装货信息
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.DEL_SHIP_LOADING)
	@RolesAccess({ "trade_executor"})
	public Result delShipLoading(OrderShipLoadinginfoVO vo, CurrentUser user) throws OrderExecException{
		
		Result res = new Result();
		vo.setAliveFlag(Constants.ALIEVE_FLAG_NO);
		try {
			orderShipLoadinginfoService.updateRecordById(vo);
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
	 * 商检机构查询
	 * @param model
	 */
	@RequestMapping(value=UrlMapping.INSPECTION_SELECT, method=RequestMethod.GET)
	@RolesAccess({ "trade_executor"})
	public void inspectionUserSelect(@RequestParam("portname") String portname, ModelMap model) {
		List<EnterpriseVo> inspectionList = enterpriseService.selectByCredentials(CredentialsConstant.SJ);
		model.put("inspectionList", inspectionList);
		model.put("portname", portname);
	}
	
	/**
	 * 查询商检结构维护的联系人
	 * @param epMemberId 商检公司ID
	 * @return
	 */
	@RequestMapping(value=UrlMapping.QUERY_CONTACT_BY_INSPECTION, method=RequestMethod.GET)
	@ResponseBody
	@RolesAccess({ "trade_executor"})
	public ResultDatas<List<InspectionStaff>> queryContactByPort(@RequestParam(value="epMemberId", required=true) Long epMemberId) {
		ResultDatas<List<InspectionStaff>> result = new ResultDatas<>();
		
		if(epMemberId != null) {
			InspectionStaff query = new InspectionStaff();
			query.setEpMemberId(epMemberId);
			List<InspectionStaff> list = inspectionStaffService.queryByEntitys(query);
			result.setDatas(list);
		}
		
		return result;
	}
	
	/**
	 * 指定商检信息保存
	 * @param inspectionId
	 * @param contactName
	 * @return
	 */
	@RequestMapping(value=UrlMapping.SAVE_LOADING_INSPECTION_MEMBER, method=RequestMethod.POST)
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
		OrderShipLoadinginfo loadingInfo = orderShipLoadinginfoService.findByPrimaryKey(formVO.getId());
		if(StringUtils.isNotBlank(loadingInfo.getInspection())) {
			result.setFail("商检信息已经维护");
			result.setCode("orderexecute.code.00204");
			return result;
		}
		
		OrderShipLoadinginfo loadinginfoUpdate = new OrderShipLoadinginfo();
		loadinginfoUpdate.setId(formVO.getId());
		loadinginfoUpdate.setInspection(formVO.getMemberName());
		loadinginfoUpdate.setInspectionMemberid(formVO.getMemberId());
		loadinginfoUpdate.setInspectionContact(formVO.getContact());
		loadinginfoUpdate.setInspectionEmail(formVO.getMail());
		loadinginfoUpdate.setInspectionTel(formVO.getTel());
		loadinginfoUpdate.setInspectionTerminal(formVO.getTerminal());
		loadinginfoUpdate.setUpdateDate(DateTimeUtils.currentDate());
		loadinginfoUpdate.setUpdateUser(user.getMemberId());
		
		orderShipLoadinginfoService.updateRecordById(loadinginfoUpdate);
		
		return result;
	}
}
