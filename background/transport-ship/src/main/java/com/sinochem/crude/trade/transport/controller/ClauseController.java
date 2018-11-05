package com.sinochem.crude.trade.transport.controller;

import java.util.HashMap;
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
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.URLMapping;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.model.ClauseVO;
import com.sinochem.crude.trade.transport.query.ClauseQuery;
import com.sinochem.crude.trade.transport.service.ClauseService;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
public class ClauseController  {
	@Autowired
	private ClauseService clauseService;
	Log log = LogFactory.getLog(ClauseController.class);
	/**
	 * 新增报盘信息
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.SAVE_CLAUSE, method = RequestMethod.POST )
	public Result saveClause(@RequestBody ClauseVO vo, CurrentUser user) {
		Result res = new Result();
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			// 船盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getShipPlateUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH012);
			}
			// 货盘uuid校验
			if(StringUtils.isNullOrEmpty(vo.getPalletUuid())){
				throw new TransportException(TransportException.TYPE.ITSH016);
			}
			
			// 新增询盘信息
			clauseService.saveClause(vo,user);
			res.setMessage(Constants.message_007);
			
		} catch (BizException e) {
			log.error("saveClause error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("saveClause error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/**
	 * 查看报盘详细信息（报盘列表）
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_TRADER,Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_CLAUSE_DETAIL,method = RequestMethod.POST )
	public ResultDatas<Object> findClauseDetails(@RequestBody ClauseVO vo,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try{
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			// 报盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH404);
			}
			// 判断用户flag
			if (StringUtils.isNullOrEmpty(vo.getRoleFlag())) {
				throw new TransportException(TransportException.TYPE.ITSH607);
			}
			Map<String, Object> map = clauseService.findClauseDetails(vo,user);
			res.setDatas(map);
		} catch (BizException e) {
			log.error("findClauseDetails error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("findClauseDetails error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	/**
	 * 查看报盘详细信息（需求列表）
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_TRADER,Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.CLAUSE_DETAIL,method = RequestMethod.POST )
	public ResultDatas<Object> clauseDetails(@RequestBody ClauseVO vo,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try{
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			// 货盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getPalletUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH404);
			}
			// 判断用户flag
			if (StringUtils.isNullOrEmpty(vo.getRoleFlag())) {
				throw new TransportException(TransportException.TYPE.ITSH607);
			}
			Map<String, Object> map = clauseService.findClauseDetails1(vo,user);
			res.setDatas(map);
		} catch (BizException e) {
			log.error("clauseDetails error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("clauseDetails error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	/**
	 * 查看报盘信息
	 * @param vo
	 * @return
	 * @exception
	 *//*
	@RolesAccess({Constants.SHIP_TRADER,Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR})
	@ResponseBody
	@RequestMapping(value=URLMapping.CLAUSE_DETAILS,method = RequestMethod.POST )
	public ResultDatas<Object> clauseDetail(@RequestBody ClauseVO vo,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try{
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			Clause map = clauseService.findClauseDetail(vo);
			res.setDatas(map);
		} catch (BizException e) {
			log.error("findIntentionDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("findIntentionDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}*/
	/**
	 * 确认报盘
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.AFFIRM_CLAUSE, method = RequestMethod.POST )
	public Result affirmClause(@RequestBody ClauseVO vo, CurrentUser user) {
		Result res = new Result();
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			// 报盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH404);
			}
			// 修改询盘状态
			clauseService.affirmClause(vo,user);
			res.setMessage(Constants.message_008);
			
		} catch (BizException e) {
			log.error("affirmClause error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		
		} catch (Exception e) {
			log.error("affirmClause error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	

	/**
	 * 洽谈报盘列表
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_TRADER})
	@RequestMapping(value=URLMapping.TALKING_CLAUSE_)
	public void talkingClause(CurrentUser user, ModelMap modelMap, ClauseQuery query) {
		// 验证登录信息
		if (user == null) {
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		if(StringUtils.isNullOrEmpty(query.getShipPlateUuid())){
			throw new TransportException(TransportException.TYPE.ITSH012);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String shipPlateUuid = query.getShipPlateUuid();
		map.put("shipPlateUuid",shipPlateUuid);
		
		//分页信息
		SimplePageInfo pageInfo =new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(10);
		Page<Map<String, Object>> clauseList = clauseService.talkingClause(map, pageInfo);
		query.setTotalItem(Long.valueOf(clauseList.getPages()));
		
		modelMap.put("user", user);
		modelMap.put("datas", clauseList);
		modelMap.put("query", query);
	}
	
	/**
	 * 撤销
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.UPDATE_CLAUSE_STATUS, method = RequestMethod.POST )
	public Result deleteClause(@RequestBody ClauseVO vo, CurrentUser user) {
		Result res = new Result();
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			// 报盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH404);
			}
			// 修改报盘状态
			clauseService.deleteClause(vo, user);
			res.setMessage(Constants.message_009);
		} catch (BizException e) {
			log.error("deleteClause error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		
		} catch (Exception e) {
			log.error("deleteClause error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	/**
	 * 发送报盘初始化
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.TO_CLAUSE_ADD,method = RequestMethod.POST )
	public ResultDatas<Object> toAddClause(@RequestBody ClauseVO vo,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try{
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			// 货盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getPalletUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH016);
			}
			// 船盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getShipPlateUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH012);
			}
			Map<String, Object> map = clauseService.findShipAndShipPlate(vo,user);
			res.setDatas(map);
		} catch (BizException e) {
			log.error("toAddClause error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("toAddClause error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}

}
