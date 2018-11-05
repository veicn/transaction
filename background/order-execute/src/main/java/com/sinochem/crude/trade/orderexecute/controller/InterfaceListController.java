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
import com.sinochem.crude.trade.orderexecute.domain.InterfaceList;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.InterfaceListVO;
import com.sinochem.crude.trade.orderexecute.service.InterfaceListService;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
@RolesAccess({"admin"})
public class InterfaceListController  {

	@Autowired
	private InterfaceListService interfaceListService;
	
	Log log = LogFactory.getLog(InterfaceListController.class);
	
	/**
	 * 启动页面
	 * @param user
	 * @param modelMap
	 */
	@RequestMapping(value = UrlMapping.INTERFACELIST_LIST_INIT)
	public void toInterfaceList(CurrentUser user, ModelMap modelMap) {
		System.out.println();
	}
	
	/**
	 * 获取外部接口清单列表
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.INTERFACELIST_LIST, method = RequestMethod.POST)
	public Map<String, Object> getInterfaceList(@RequestBody InterfaceListVO vo, CurrentUser user) {
		
		ResultDatas<List<InterfaceList>> res = new ResultDatas<List<InterfaceList>>();
		
		try {
			SimplePageInfo pageInfo = vo.getPageInfo();
			
			Page<Map<String,Object>> page = interfaceListService.queryRecords(BeanConvertUtils.beanToMap(vo),pageInfo);
			List<InterfaceList> list = BeanConvertUtils.mapToBeanInList(page, InterfaceList.class);
			res.setDatas(list);
			res.setPageNum(page.getPageNum());
			res.setPageSize(page.getPageSize());
			res.setPageCount(page.getPages());
			res.setTotal(page.getTotal());
		} catch (OrderExecException e) {
			log.error("getInterfaceList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("getInterfaceList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		Map<String, Object> aaa = new HashMap<String, Object>();
		
		aaa.putAll(BeanConvertUtils.beanToMap(res));
		
		return aaa;
	}
	
	/**
	 * 根据主键查询外部接口
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.INTERFACELIST_QUERY, method = RequestMethod.POST)
	public ResultDatas<InterfaceList> getInterfaceById(@RequestBody InterfaceListVO vo, CurrentUser user) {
		
		ResultDatas<InterfaceList> res = new ResultDatas<InterfaceList>();
		
		try {
			
			InterfaceList interfaceList = interfaceListService.findByPrimaryKey(vo.getInterfaceId());
			res.setDatas(interfaceList);
			
		} catch (OrderExecException e) {
			log.error("getInterfaceById error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("getInterfaceById error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 添加外部接口
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.INTERFACELIST_ADD, method = RequestMethod.POST)
	public Result addInterface(@RequestBody InterfaceListVO vo, CurrentUser user) {
		
		Result res = new Result();
		
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sysName", vo.getSysName());
			map.put("interfaceCode", vo.getInterfaceCode());
			if(interfaceListService.countRecords(map) > 0){
				throw new OrderExecException("orderexecute.code.00008","系统别名和接口唯一编码已存在");
			}
			
			if (user == null){
				throw new OrderExecException("orderexecute.code.00007","身份信息为空");
			}
			
			vo.setLangVer(Constants.LANG_VER);
			vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			vo.setCreateDate(DateTimeUtils.currentDate());
			vo.setCreateUser(user.getMemberId());
			vo.setUpdateDate(DateTimeUtils.currentDate());
			vo.setUpdateUser(user.getMemberId());			
			
			interfaceListService.insertRecord(BeanConvertUtils.beanToBean(vo, InterfaceList.class));
			
		} catch (OrderExecException e) {
			log.error("addInterface error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("addInterface error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 修改外部接口
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.INTERFACELIST_EDIT, method = RequestMethod.POST)
	public Result editInterface(@RequestBody InterfaceListVO vo, CurrentUser user) {
		
		Result res = new Result();
		
		try {
			
			vo.setUpdateDate(DateTimeUtils.currentDate());
			vo.setUpdateUser(user.getMemberId());			
			
			interfaceListService.updateRecordById(BeanConvertUtils.beanToBean(vo, InterfaceList.class));
			
		} catch (OrderExecException e) {
			log.error("editInterface error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("editInterface error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 修改外部接口
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.INTERFACELIST_DEL, method = RequestMethod.POST)
	public Result deleteInterface(@RequestBody InterfaceListVO vo, CurrentUser user) {
		
		Result res = new Result();
		
		try {
			
			if (user == null){
				throw new OrderExecException("orderexecute.code.00007","身份信息为空");
			}
			
			InterfaceList interfaceList = new InterfaceList();
			
			interfaceList.setInterfaceId(vo.getInterfaceId());
			interfaceList.setUpdateDate(DateTimeUtils.currentDate());
			interfaceList.setUpdateUser(user.getMemberId());
			interfaceList.setAliveFlag(Constants.ALIEVE_FLAG_NO);
			
			interfaceListService.updateRecordById(interfaceList);
			
		} catch (OrderExecException e) {
			log.error("deleteInterface error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("deleteInterface error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
}
