package com.sinochem.crude.trade.orderexecute.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceContrast;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.InterfaceContrastVO;
import com.sinochem.crude.trade.orderexecute.service.InterfaceContrastService;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
@RolesAccess({"admin"})
public class InterfaceContrastController  {

	@Autowired
	private InterfaceContrastService interfaceContrastService;
	
	Log log = LogFactory.getLog(InterfaceContrastController.class);
	
	/**
	 * 启动页面
	 * @param user
	 * @param modelMap
	 */
	@RequestMapping(value = UrlMapping.INTERFACE_CONTRAST_INIT)
	public void toInterfaceSystem(CurrentUser user, ModelMap modelMap) {
		System.out.println();
	}
	/**
	 * 外部系统数据查询
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.INTERFACE_CONTRAST, method = RequestMethod.POST)
	public ResultDatas<List<Map<String, Object>>> selectListDomesticPrice(@RequestBody InterfaceContrastVO vo, CurrentUser user)throws OrderExecException{
		
		ResultDatas<List<Map<String, Object>>> res = new ResultDatas<List<Map<String, Object>>>();
		try{	
			SimplePageInfo pageInfo = vo.getPageInfo();
			
			Page<Map<String,Object>> page = interfaceContrastService.queryRecords(BeanConvertUtils.beanToMap(vo),pageInfo);
			
			res.setDatas(page);
			res.setPageNum(page.getPageNum());
			res.setPageSize(page.getPageSize());
			res.setPageCount(page.getPages());
			res.setTotal(page.getTotal());
		} catch (OrderExecException e) {
			log.error("selectListDomesticPrice error", e); 
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("selectListDomesticPrice error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
		
	}
	
	/**
	 * 原油价格增加
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.INTERFACE_ADD,method = RequestMethod.POST)
	public Result insertInterfaceContrast(CurrentUser user, @RequestBody InterfaceContrastVO interfaceContrast) throws OrderExecException{
		
		Result res = new Result();
		
		// 增加
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sysName", interfaceContrast.getSysName());
			if(interfaceContrastService.countRecords(map) > 0){
				throw new OrderExecException("orderexecute.code.00006","系统别名已存在");
			}
			interfaceContrast.setCreateUser(user.getEpMemberId());
			interfaceContrast.setUpdateUser(user.getEpMemberId());
			interfaceContrastService.insertRecord(interfaceContrast);
		} catch (OrderExecException e) {
			log.error("insertInterfaceContrast error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("insertInterfaceContrast error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 原油价格修改
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.INTERFACE_UPD,method = RequestMethod.POST)
	public Result updateInterfaceContrast(@RequestBody InterfaceContrastVO vo, CurrentUser user) throws OrderExecException{
		
		Result res = new Result();
		vo.setUpdateDate(DateTimeUtils.currentDate());
		vo.setUpdateUser(user.getMemberId());	
		try {
			interfaceContrastService.updateRecordById(vo);
		} catch (OrderExecException e) {
			log.error("updateInterfaceContrast error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("updateInterfaceContrast error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 原油价格删除
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.INTERFACE_DEL,method = RequestMethod.POST)
	public Result deleteInterfaceContrast(@RequestBody InterfaceContrastVO vo, CurrentUser user) throws OrderExecException{
		
		Result res = new Result();
		
		// 增加
		try {
			if (user == null){
				throw new OrderExecException("orderexecute.code.00007","身份信息为空");
			}
			
			InterfaceContrast interfaceContrast = new InterfaceContrast();
			
			interfaceContrast.setContrastId(vo.getContrastId());
			interfaceContrast.setUpdateDate(DateTimeUtils.currentDate());
			interfaceContrast.setUpdateUser(user.getMemberId());
			interfaceContrast.setAliveFlag(Constants.ALIEVE_FLAG_NO);
			
			interfaceContrastService.updateRecordById(interfaceContrast);
			
		}  catch (OrderExecException e) {
			log.error("deleteInterfaceContrast error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("deleteInterfaceContrast error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
}
