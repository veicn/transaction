package com.sinochem.crude.trade.orderexecute.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.common.utils.ValueSetUtils;
import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.commons.constants.ValueSetGroupConstant;
import com.sinochem.crude.trade.orderexecute.commons.utils.ValueSetUtil;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.InspectionStaffVO;
import com.sinochem.crude.trade.orderexecute.query.InspectionStaffQuery;
import com.sinochem.crude.trade.orderexecute.service.InspectionStaffService;
import com.sinochem.it.b2b.common.utils.VisitorLocale;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
public class InspectionStaffController  {

	@Autowired
	private InspectionStaffService inspectionStaffService;
	
	Log log = LogFactory.getLog(InspectionStaffController.class);
	
	/**
	 * 列表 
	 * @param CodeValue 
	 */
	@RequestMapping(value = UrlMapping.INSPECTION_lIST)
	@RolesAccess({"inspection"})
	public void queryInspectionStaffList(
		CurrentUser user, InspectionStaffQuery query, ModelMap modelMap) { 
	
		Map<String, Object> queryMap = query.getQueryParameter();
		queryMap.put("epMemberId", user.getEpMemberId());
		Page<Map<String, Object>> pageResult = inspectionStaffService.queryRecords(queryMap, query.getPageInfo());
		List<Map<String, Object>> queryCode = pageResult.getResult();
		
		for( Map<String, Object> data : queryCode) {
			String code = (String) data.get("port");
			CodeValue codeValue =ValueSetUtil.getByCode(ValueSetGroupConstant.UN_LOAD_PORT, code);	
			data.put("portName",codeValue.getValue());
		}
		
		QueryBase queryBase = (QueryBase) query;
		queryBase.setTotalItem(pageResult.getTotal());
		
		modelMap.put("datas", pageResult.getResult());
		modelMap.put("query", queryBase);
		
		Map<String, CodeValue> unloadingSet = ValueSetUtils.getValuesByGroup(ValueSetGroupConstant.UN_LOAD_PORT, VisitorLocale.getCurrent().getLanguage());
		modelMap.put("unloadingSet", unloadingSet);
		
	}
	
	/**
	 * 新增
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.INSPECTION_ADD)
	@RolesAccess({ "inspection"})
	public Result saveInspectionStaff(InspectionStaffVO vo, CurrentUser user) throws OrderExecException{
		
		Result res = new Result();
		try {
			vo.setEpMemberId(user.getEpMemberId());
			vo.setUuid(KeyGenUtils.newUuid());
			vo.setUpdateDate(DateTimeUtils.currentDate());
			vo.setCreateDate(DateTimeUtils.currentDate());
			vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			vo.setLangVer(Constants.LANG_VER);
			vo.setUpdateUser(user.getMemberId());
			vo.setCreateUser(user.getMemberId());
			
			inspectionStaffService.insertRecord(vo);
			
		} catch (OrderExecException e) {
			log.error("saveInspectionStaff error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("saveInspectionStaff error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.INSPECTION_EDIT)
	@RolesAccess({ "inspection"})
	public Result updateInspectionStaff(InspectionStaffVO vo, CurrentUser user) throws OrderExecException{
		Result res = new Result();
		try {
			vo.setUpdateDate(DateTimeUtils.currentDate());
			vo.setUpdateUser(user.getMemberId());
			inspectionStaffService.updateRecordById(vo);
		} catch (OrderExecException e) {
			log.error("updateInspectionStaff error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("updateInspectionStaff error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.INSPECTION_DEL)
	@RolesAccess({ "inspection"})
	public Result delInspectionStaff(InspectionStaffVO vo, CurrentUser user) throws OrderExecException{
		
		Result res = new Result();
		vo.setAliveFlag(Constants.ALIEVE_FLAG_NO);
		try {
			inspectionStaffService.updateRecordById(vo);
		} catch (OrderExecException e) {
			log.error("delInspectionStaff error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("delInspectionStaff error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
}
