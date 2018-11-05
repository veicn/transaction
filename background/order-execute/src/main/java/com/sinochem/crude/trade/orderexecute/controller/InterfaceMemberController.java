package com.sinochem.crude.trade.orderexecute.controller;

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
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceMember;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.InterfaceMemberVO;
import com.sinochem.crude.trade.orderexecute.service.InterfaceMemberService;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
@RolesAccess({"admin"})
public class InterfaceMemberController  {

	@Autowired
	private InterfaceMemberService interfaceMemberService;
	
	Log log = LogFactory.getLog(InterfaceMemberController.class);
	
	/**
	 * 启动页面
	 * @param user
	 * @param modelMap
	 */
	@RequestMapping(value = UrlMapping.INTERFACEMEMBER_LIST_INIT)
	public void toInterfaceList(CurrentUser user, ModelMap modelMap) {
		System.out.println();
	}
	
	/**
	 * 获取外部系统用户列表
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.INTERFACEMEMBER_LIST, method = RequestMethod.POST)
	public ResultDatas<List<InterfaceMember>> getInterfaceMemberList(@RequestBody InterfaceMemberVO vo, CurrentUser user) {
		
		ResultDatas<List<InterfaceMember>> res = new ResultDatas<List<InterfaceMember>>();
		
		try {
			SimplePageInfo pageInfo = vo.getPageInfo();
			
			Page<Map<String,Object>> page = interfaceMemberService.queryRecords(BeanConvertUtils.beanToMap(vo),pageInfo);
			List<InterfaceMember> list = BeanConvertUtils.mapToBeanInList(page, InterfaceMember.class);
			res.setDatas(list);
			res.setPageNum(page.getPageNum());
			res.setPageSize(page.getPageSize());
			res.setPageCount(page.getPages());
			res.setTotal(page.getTotal());
		} catch (OrderExecException e) {
			log.error("getInterfaceMemberList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("getInterfaceMemberList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 根据主键查询外部系统用户信息
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.INTERFACEMEMBER_QUERY, method = RequestMethod.POST)
	public ResultDatas<InterfaceMember> getInterfaceMemberById(@RequestBody InterfaceMemberVO vo, CurrentUser user) {
		
		ResultDatas<InterfaceMember> res = new ResultDatas<InterfaceMember>();
		
		try {
			
			InterfaceMember interfaceMember = interfaceMemberService.findByPrimaryKey(vo.getInterfaceMemberId());
			res.setDatas(interfaceMember);
			
		} catch (OrderExecException e) {
			log.error("getInterfaceMemberById error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("getInterfaceMemberById error", e);
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
	@RequestMapping(value = UrlMapping.INTERFACEMEMBER_ADD, method = RequestMethod.POST)
	public Result addInterfaceMember(@RequestBody InterfaceMemberVO vo, CurrentUser user) {
		
		Result res = new Result();
		
		try {
			
			if (user == null){
				throw new OrderExecException("orderexecute.code.00007","身份信息为空");
			}
			
			if (StringUtils.isNullOrEmpty(vo.getSysName())) {
				throw new OrderExecException("orderexecute.code.00009","外部系统别名为空");
			}
			
			vo.setLangVer(Constants.LANG_VER);
			vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			vo.setCreateDate(DateTimeUtils.currentDate());
			vo.setCreateUser(user.getMemberId());
			vo.setUpdateDate(DateTimeUtils.currentDate());
			vo.setUpdateUser(user.getMemberId());			
			
			interfaceMemberService.insertRecord(BeanConvertUtils.beanToBean(vo, InterfaceMember.class));
			
		} catch (OrderExecException e) {
			log.error("addInterfaceMember error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("addInterfaceMember error", e);
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
	@RequestMapping(value = UrlMapping.INTERFACEMEMBER_EDIT, method = RequestMethod.POST)
	public Result editInterfaceMember(@RequestBody InterfaceMemberVO vo, CurrentUser user) {
		
		Result res = new Result();
		
		try {
			
			if (user == null){
				throw new OrderExecException("orderexecute.code.00007","身份信息为空");
			}
			
			vo.setUpdateDate(DateTimeUtils.currentDate());
			vo.setUpdateUser(user.getMemberId());			
			
			interfaceMemberService.updateRecordById(BeanConvertUtils.beanToBean(vo, InterfaceMember.class));
			
		} catch (OrderExecException e) {
			log.error("editInterfaceMember error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("editInterfaceMember error", e);
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
	@RequestMapping(value = UrlMapping.INTERFACEMEMBER_DEL, method = RequestMethod.POST)
	public Result deleteInterfaceMember(@RequestBody InterfaceMemberVO vo, CurrentUser user) {
		
		Result res = new Result();
		
		try {
			
			if (user == null){
				throw new OrderExecException("orderexecute.code.00007","身份信息为空");
			}
			
			InterfaceMember interfaceMember = new InterfaceMember();
			
			interfaceMember.setInterfaceMemberId(vo.getInterfaceMemberId());
			interfaceMember.setUpdateDate(DateTimeUtils.currentDate());
			interfaceMember.setUpdateUser(user.getMemberId());
			interfaceMember.setAliveFlag(Constants.ALIEVE_FLAG_NO);
			
			interfaceMemberService.updateRecordById(interfaceMember);
			
		} catch (OrderExecException e) {
			log.error("deleteInterfaceMember error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("deleteInterfaceMember error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
}
