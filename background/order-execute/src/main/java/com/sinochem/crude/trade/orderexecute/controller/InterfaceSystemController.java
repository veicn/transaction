package com.sinochem.crude.trade.orderexecute.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.ValueSetUtils;
import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.commons.constants.LanguageEnum;
import com.sinochem.crude.trade.orderexecute.commons.constants.ValueSetGroupConstant;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceSystem;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.InterfaceSystemVO;
import com.sinochem.crude.trade.orderexecute.service.InterfaceSystemService;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
@RolesAccess({"admin"})
public class InterfaceSystemController  {

	@Autowired
	private InterfaceSystemService interfaceSystemService;
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	Log log = LogFactory.getLog(InterfaceSystemController.class);
	
	/**
	 * 启动页面
	 * @param user
	 * @param modelMap
	 */
	@RequestMapping(value = UrlMapping.INTERFACESYSTEM_INIT)
	public void toInterfaceSystem(CurrentUser user, ModelMap modelMap) {
		System.out.println();
	}
	
	/**
	 * 获取外部系统用户列表
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.INTERFACESYSTEM_LIST, method = RequestMethod.POST)
	public ResultDatas<List<InterfaceSystem>> getInterfaceSystemList(@RequestBody InterfaceSystemVO vo, CurrentUser user) {
		
		ResultDatas<List<InterfaceSystem>> res = new ResultDatas<List<InterfaceSystem>>();
		
		try {
			SimplePageInfo pageInfo = vo.getPageInfo();
			
			Page<Map<String,Object>> page = interfaceSystemService.queryRecords(BeanConvertUtils.beanToMap(vo),pageInfo);
			List<InterfaceSystem> list = BeanConvertUtils.mapToBeanInList(page, InterfaceSystem.class);
			res.setDatas(list);
			res.setPageNum(page.getPageNum());
			res.setPageSize(page.getPageSize());
			res.setPageCount(page.getPages());
			res.setTotal(page.getTotal());
		} catch (OrderExecException e) {
			log.error("getInterfaceSystemList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("getInterfaceSystemList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 获取外部系统名称
	 *
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.QUERY_SYS_NAME, method = RequestMethod.GET)
	public ResultDatas<List<InterfaceSystem>> querySysName() {
		ResultDatas<List<InterfaceSystem>> res = new ResultDatas<List<InterfaceSystem>>();
		try{	
			List<InterfaceSystem> sysNameList = interfaceSystemService.queryByEntitys(new InterfaceSystem());
			res.setDatas(sysNameList);
		} catch (OrderExecException e) {
			log.error("getInterfaceSystemList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		}catch (Exception e ) {
			log.error("getInterfaceSystemList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 获取会员资质
	 *
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.QUERY_MEMBER_TYPE, method = RequestMethod.GET)
	public ResultDatas<Map<String,CodeValue>> queryMemberType() {
		ResultDatas<Map<String,CodeValue>> res = new ResultDatas<Map<String,CodeValue>>();
		try{
			Map<String, CodeValue> valuesByGroup = ValueSetUtils.getValuesByGroup(ValueSetGroupConstant.MEMBER_CREDENTIAL, LanguageEnum.ZH.name().toLowerCase());//会员类型
			res.setDatas(valuesByGroup);
		} catch (OrderExecException e) {
			log.error("getInterfaceSystemList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("getInterfaceSystemList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 通过会员资质查询会员Id和会员名
	 *
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.QUERY_MEMBER_INFO, method = RequestMethod.POST)
	public ResultDatas<List<InterfaceSystem>> queryMemberInfoByType(@RequestParam("memberType") String menberType) {
		ResultDatas<List<InterfaceSystem>> res = new ResultDatas<List<InterfaceSystem>>();
		try {
			if(menberType == null || "".equals(menberType)){
				throw new OrderExecException("orderexecute.code.00010","会员类型不能为空");
			}
			
			//通过会员资质查询会员Id和会员名
			List<EnterpriseVo> list = enterpriseService.selectByCredentials(menberType);
			List<InterfaceSystem> interfaceSystemVOs = new ArrayList<InterfaceSystem>();
			if(list != null && list.size() > 0){
				for (EnterpriseVo enterpriseVo : list) {
					if(enterpriseVo.getMemberId() != null){
						InterfaceSystem interfaceSystem = new InterfaceSystem();
						interfaceSystem.setMemberName(enterpriseVo.getName());//企业名称--会员名称
						interfaceSystem.setMemberId(enterpriseVo.getMemberId());//members表关联ID---会员ID	
						interfaceSystemVOs.add(interfaceSystem);
					}
				}
			}
			
			res.setDatas(interfaceSystemVOs);
		} catch (OrderExecException e) {
			log.error("queryMemberInfoByType error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("queryMemberInfoByType error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 添加外部系统用户
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.INTERFACESYSTEM_ADD, method = RequestMethod.POST)
	public Result addInterfaceSystem(@RequestBody InterfaceSystemVO vo, CurrentUser user) {
		
		Result res = new Result();
		
		try {
			
			if (user == null){
				throw new OrderExecException("orderexecute.code.00007","身份信息为空");
			}
			
			if (StringUtils.isNullOrEmpty(vo.getSysName())) {
				throw new OrderExecException("orderexecute.code.00009","外部系统别名为空");
			}
			
			Map<String, Object> systemMap = new HashMap<String, Object>();
			
			systemMap.put("sysName", vo.getSysName());
			
			if (interfaceSystemService.countRecords(systemMap) > 0) {
				throw new OrderExecException("orderexecute.code.000011","该外部系统已存在");
			}
			
			vo.setLangVer(Constants.LANG_VER);
			vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			vo.setCreateDate(DateTimeUtils.currentDate());
			vo.setCreateUser(user.getMemberId());
			vo.setUpdateDate(DateTimeUtils.currentDate());
			vo.setUpdateUser(user.getMemberId());			
			
			interfaceSystemService.insertRecord(BeanConvertUtils.beanToBean(vo, InterfaceSystem.class));
			
		} catch (OrderExecException e) {
			log.error("addInterfaceSystem error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("addInterfaceSystem error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 修改外部系统用户
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.INTERFACESYSTEM_EDIT, method = RequestMethod.POST)
	public Result editInterfaceSystem(@RequestBody InterfaceSystemVO vo, CurrentUser user) {
		
		Result res = new Result();
		
		try {
			
			if (user == null){
				throw new OrderExecException("orderexecute.code.00007","身份信息为空");
			}
			
			vo.setUpdateDate(DateTimeUtils.currentDate());
			vo.setUpdateUser(user.getMemberId());			
			
			interfaceSystemService.updateRecordById(BeanConvertUtils.beanToBean(vo, InterfaceSystem.class));
			
		} catch (OrderExecException e) {
			log.error("editInterfaceSystem error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("editInterfaceSystem error", e);
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
	@RequestMapping(value = UrlMapping.INTERFACESYSTEM_DEL, method = RequestMethod.POST)
	public Result deleteInterfaceSystem(@RequestBody InterfaceSystemVO vo, CurrentUser user) {
		
		Result res = new Result();
		
		try {
			
			if (user == null){
				throw new OrderExecException("orderexecute.code.00007","身份信息为空");
			}
			
			InterfaceSystem interfaceSystem = new InterfaceSystem();
			
			interfaceSystem.setSysId(vo.getSysId());
			interfaceSystem.setUpdateDate(DateTimeUtils.currentDate());
			interfaceSystem.setUpdateUser(user.getMemberId());
			interfaceSystem.setAliveFlag(Constants.ALIEVE_FLAG_NO);
			
			interfaceSystemService.updateRecordById(interfaceSystem);
			
		} catch (OrderExecException e) {
			log.error("deleteInterfaceSystem error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("deleteInterfaceSystem error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 删除
	 *//*
	@ResponseBody
	@RequestMapping(value="/interface_system/del.json",method = RequestMethod.GET)
	public Result deleteSystem(@RequestParam(value="sysId") Long sysId) throws OrderExecException{
		
		Result result = new Result();
			
		try {
			interfaceSystemService.deleteById(sysId);
		} catch (Exception e) {
			result.setStatus(9);
			result.setMessage("删除失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}*/
}
