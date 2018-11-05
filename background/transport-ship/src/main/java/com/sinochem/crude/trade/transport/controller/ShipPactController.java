package com.sinochem.crude.trade.transport.controller;

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
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.URLMapping;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.domain.Agreement;
import com.sinochem.crude.trade.transport.domain.Intention;
import com.sinochem.crude.trade.transport.domain.ShipPact;
import com.sinochem.crude.trade.transport.model.ShipPactVO;
import com.sinochem.crude.trade.transport.query.PactQuery;
import com.sinochem.crude.trade.transport.service.AgreementService;
import com.sinochem.crude.trade.transport.service.IntentionService;
import com.sinochem.crude.trade.transport.service.ShipPactService;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;

/**
 * @ClassName: ShipPactController
 * @Description: 船合同信息
 * @author gyy
 */
@Controller
public class ShipPactController {
	@Autowired
	private ShipPactService shipPactService;
	@Autowired
	private AgreementService agreementService;
	
	
	@Autowired
	private IntentionService intentionService;
	
	Log log = LogFactory.getLog(ShipPactController.class);

	
	/**
	 * 生成船合同
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.SAVE_SHIPPACT, method = RequestMethod.POST  )
	public Result saveShipPact(@RequestBody ShipPactVO vo,CurrentUser user) {
		Result res = new Result();
		try {
			//验证登录信息
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if ( user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			if (StringUtils.isNullOrEmpty(vo.getUuid())){
				//校验必填
				if (StringUtils.isNullOrEmpty(vo.getShipPlateUuid())){
					throw new TransportException(TransportException.TYPE.ITSH012);
				} 
				Intention intention = intentionService.findByUuid(vo.getShipIntentionUuid());
				if(intention!=null){
					vo.setShipIntentionId(intention.getIntentionId());
				}
				shipPactService.saveShipPact(vo,user);
			} else {
				//修改
				shipPactService.updateShipPact(vo,user);
			}
			
		} catch (BizException e) {
			log.error("saveShipPact error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("saveShipPact error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	
	/**
	 * 查询船合同详情
	 * @param vo
	 * @param user
	 * @return
	 */
	@RightAccess(2020)
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.DEPA_PORT,Constants.DEPA_PORT,Constants.ADMIN})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_SHIPPACT_DEATIL, method = RequestMethod.POST )
	public ResultDatas<Object> findShipPactDetail(@RequestBody ShipPactVO vo) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			Map<String,Object> map = new HashMap<>();
			//校验必填
			if (StringUtils.isNullOrEmpty(vo.getUuid())){
					throw new TransportException(TransportException.TYPE.ITSH038);
			} 
			ShipPact pact = shipPactService.findShipPactDetail(vo);
			String wsRes = pact.getWsRes();
			List<Map<String,Object>> list = new ArrayList<>();
			if (!StringUtils.isNullOrEmpty(wsRes)){
				String[] str = wsRes.split(";");
				for (String string : str) {
					Map<String,Object> maps = new HashMap<>();
					String ws ="";
					String wsR ="";
					String time1 ="";
					String time2 ="";
					if (!StringUtils.isNullOrEmpty(string)){
						String[] split = string.split("/");
						
						if (split.length >= 1){
							if (split.length == 1){
								ws=pact.getWs()+"";
								wsR=split[0];
							} else {
								ws=split[0];
							}
						}
						if (split.length >= 2){
							wsR=split[1];
						}
						if (split.length >= 3){
							time1=split[2];
						}
						if (split.length >= 4){
							time2=split[3];
						}
					}
					maps.put("ws", ws);
					maps.put("wsRes", wsR);
					maps.put("time1", time1);
					maps.put("time2", time2);
					list.add(maps);
				}
			}
			String wsRes2 = pact.getWsRes();
			if (!StringUtils.isNullOrEmpty(wsRes2)){
				String end = wsRes2.substring(wsRes2.length()-1,wsRes2.length());
				if (";".equals(end)){
					wsRes2=wsRes2.substring(0,wsRes2.length()-1);
				}
				pact.setWsRes(wsRes2);
			}
			map.put("shipPact", pact);
			map.put("list", list);
			res.setDatas(map);
		} catch (BizException e) {
			log.error("findShipPactDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("findShipPactDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	
	/**
	 * 查询船合同详情(询盘列表里查看或者船盘列表里查看)
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_SHIPPACT_DEATIL_INTENTION_OR_PLATE, method = RequestMethod.POST )
	public ResultDatas<Object> findShipPactDetailIntention(@RequestBody ShipPactVO vo) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			Map<String,Object> map = new HashMap<>();
			//校验必填
			if (StringUtils.isNullOrEmpty(vo.getShipIntentionUuid())&&StringUtils.isNullOrEmpty(vo.getShipPlateUuid())){
					throw new TransportException(TransportException.TYPE.ITSH038);
			} 
			ShipPact pact = shipPactService.queryIntentionOrPlateUuid(vo);
			String wsRes = pact.getWsRes();
			List<Map<String,Object>> list = new ArrayList<>();
			if (!StringUtils.isNullOrEmpty(wsRes)){
				String[] str = wsRes.split(";");
				for (String string : str) {
					Map<String,Object> maps = new HashMap<>();
					String ws ="";
					String wsR ="";
					String time1 ="";
					String time2 ="";
					if (!StringUtils.isNullOrEmpty(string)){
						String[] split = string.split("/");
						
						if (split.length >= 1){
							if (split.length == 1){
								ws=pact.getWs()+"";
								wsR=split[0];
							} else {
								ws=split[0];
							}
						}
						if (split.length >= 2){
							wsR=split[1];
						}
						if (split.length >= 3){
							time1=split[2];
						}
						if (split.length >= 4){
							time2=split[3];
						}
					}
					maps.put("ws", ws);
					maps.put("wsRes", wsR);
					maps.put("time1", time1);
					maps.put("time2", time2);
					list.add(maps);
				}
			}
			String wsRes2 = pact.getWsRes();
			if (!StringUtils.isNullOrEmpty(wsRes2)){
				String end = wsRes2.substring(wsRes2.length()-1,wsRes2.length());
				if (";".equals(end)){
					wsRes2=wsRes2.substring(0,wsRes2.length()-1);
				}
				pact.setWsRes(wsRes2);
			}
			map.put("shipPact", pact);
			map.put("list", list);
			res.setDatas(map);
		} catch (BizException e) {
			log.error("findShipPactDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("findShipPactDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/**
	 * 查询船合同列表
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.QUERY_SHIPPACT_LIST, method = RequestMethod.POST  )
	public ResultDatas<Object> queryShipPactList(@RequestBody ShipPactVO vo) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			SimplePageInfo pageInfo = vo.getPageInfo();
			Page<Map<String,Object>> page = shipPactService.queryShipPactList(BeanConvertUtils.beanToMap(vo),pageInfo);
			List<ShipPact> list = BeanConvertUtils.mapToBeanInList(page, ShipPact.class);
			res.setDatas(list);
			res.setPageNum(page.getPageNum());
			res.setPageCount(page.getPages());
			res.setPageSize(page.getPageSize());
		} catch (BizException e) {
			log.error("queryShipPactList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("queryShipPactList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	
	/**
	 * 查询船合同和协议信息
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.QUERY_AGREEMENT_AND_PACT, method = RequestMethod.POST  )
	public ResultDatas<Object> queryAgreementAndPact(@RequestBody ShipPactVO vo) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			if ( StringUtils.isNullOrEmpty(vo.getAgreementUuid())){
				throw new TransportException(TransportException.TYPE.ITSH026);
			} else if ( StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH038);
			}
			
			Map<String,Object> map = shipPactService.queryAgreementAndPact(vo);
			
			res.setDatas(map);
		} catch (BizException e) {
			log.error("queryAgreementAndPact error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("queryAgreementAndPact error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	
	/**
	 * 航次结束
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR})
	@ResponseBody
	@RequestMapping(value=URLMapping.FINISH_SHIPPACT, method = RequestMethod.POST  )
	public Result finishShipPact(@RequestBody ShipPactVO vo,CurrentUser user) {
		Result res = new Result();
		try {
			if ( user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if ( user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			if ( StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH038);
			}
			
			shipPactService.finishShipPact(vo.getUuid(),user);
			
		} catch (BizException e) {
			log.error("finishShipPact error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("finishShipPact error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	
	/**
	 * 校验航次结束
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR})
	@ResponseBody
	@RequestMapping(value=URLMapping.CHECK_SHIPPACT_FINISH, method = RequestMethod.POST  )
	public ResultDatas<Object> checkShipPactFinish(@RequestBody ShipPactVO vo,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			if ( user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if ( user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			if ( StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH038);
			}
			
			Map<String,Object> map = shipPactService.checkShipPactFinish(vo.getUuid());
			res.setDatas(map);
		} catch (BizException e) {
			log.error("checkShipPactFinish error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("checkShipPactFinish error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	
	/**
	 * 查询值集后台
	 * @param vo
	 * @return
	 */
	@RightAccess(2003)
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.QUERY_VALUE_SET, method = RequestMethod.POST )
	public ResultDatas<Object> queryValueSet(@RequestBody ShipPactVO vo) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			if (StringUtils.isNullOrEmpty(vo.getValueSetType()) && StringUtils.isNullOrEmpty(vo.getValueSetTypes())){
				throw new TransportException(TransportException.TYPE.ITSH089);
			}
			List<Map<String,Object>> map = shipPactService.queryValueSet(vo);
			res.setDatas(map);
		} catch (BizException e) {
			log.error("queryValueSet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("queryValueSet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	/**
	 * 查询值集后台（基础运价使用）
	 * @param vo
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.QUERY_VALUE_SET_EN, method = RequestMethod.POST )
	public ResultDatas<Object> queryValueSetEn(@RequestBody ShipPactVO vo) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			if (StringUtils.isNullOrEmpty(vo.getValueSetType()) && StringUtils.isNullOrEmpty(vo.getValueSetTypes())){
				throw new TransportException(TransportException.TYPE.ITSH089);
			}
			List<Map<String,Object>> map = shipPactService.queryValueSetEn(vo);
			res.setDatas(map);
		} catch (BizException e) {
			log.error("queryValueSet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("queryValueSet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/**
	 * 查询值集前台
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value=URLMapping.QUERY_VALUE_SET_ELE, method = RequestMethod.POST )
	public ResultDatas<Object> queryValueSetEle(@RequestBody ShipPactVO vo) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			if (StringUtils.isNullOrEmpty(vo.getValueSetType()) && StringUtils.isNullOrEmpty(vo.getValueSetTypes())){
				throw new TransportException(TransportException.TYPE.ITSH089);
			}
			List<Map<String,Object>> map = shipPactService.queryValueSet(vo);
			res.setDatas(map);
		} catch (BizException e) {
			log.error("queryValueSetEle error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("queryValueSetEle error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/**
	 * 查询船合同列表
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@RequestMapping(value=URLMapping.SHIPPACT_LIST_HTM)
	public void queryShipPactList(CurrentUser user,ModelMap modelMap, PactQuery query) {
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			log.info("#####当前登录用户信息########:"+BeanConvertUtils.beanToMap(user));
			if ( user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			String[] roles = user.getRoles();
			if (roles != null){
				for (String str : roles) {
					log.info("#####当前登录用户权限信息########:"+str);
				}
			}
			query.setPageSize(10);
			SimplePageInfo pageInfo = query.getPageInfo();
			Map<String,Object> map = new HashMap<>();
			if ( user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			map.put("epMemberId", user.getEpMemberId());
			map.put("pactCode", query.getCode());
			map.put("status", query.getStatus());
			Page<Map<String,Object>> pages = shipPactService.queryShipPactList(map,pageInfo);
			
			query.setTotalItem(pages.getTotal());
			modelMap.put("user", user);
			modelMap.put("datas", pages);
			modelMap.put("query", query);
	}
	
	/**
	 * 查询船合同列表（转租船东）
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@RequestMapping(value=URLMapping.SHIPPACT_LIST_HTM_NEW)
	public void queryShipPactListNew(CurrentUser user,ModelMap modelMap, PactQuery query) {
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		log.info("#####当前登录用户信息########:"+BeanConvertUtils.beanToMap(user));
		if ( user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		String[] roles = user.getRoles();
		if (roles != null){
			for (String str : roles) {
				log.info("#####当前登录用户权限信息########:"+str);
			}
		}
		query.setPageSize(10);
		SimplePageInfo pageInfo = query.getPageInfo();
		Map<String,Object> map = new HashMap<>();
		if ( user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		map.put("epMemberId", user.getEpMemberId());
		map.put("pactCode", query.getCode());
		map.put("status", query.getStatus());
		map.put("type", "2");
		Page<Map<String,Object>> pages = shipPactService.queryShipPactList(map,pageInfo);
		
		query.setTotalItem(pages.getTotal());
		modelMap.put("user", user);
		modelMap.put("datas", pages);
		modelMap.put("query", query);
	}
	
	/**
	 * 查询船合同列表（船东）
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER})
	@RequestMapping(value=URLMapping.SHIPPACT_LIST_HTM_NEW_OWNER)
	public void queryShipPactListNewOwner(CurrentUser user,ModelMap modelMap, PactQuery query) {
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		log.info("#####当前登录用户信息########:"+BeanConvertUtils.beanToMap(user));
		if ( user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		String[] roles = user.getRoles();
		if (roles != null){
			for (String str : roles) {
				log.info("#####当前登录用户权限信息########:"+str);
			}
		}
		query.setPageSize(10);
		SimplePageInfo pageInfo = query.getPageInfo();
		Map<String,Object> map = new HashMap<>();
		if ( user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		map.put("epMemberId", user.getEpMemberId());
		map.put("pactCode", query.getCode());
		map.put("status", query.getStatus());
		map.put("type", "1");
		Page<Map<String,Object>> pages = shipPactService.queryShipPactList(map,pageInfo);
		
		query.setTotalItem(pages.getTotal());
		modelMap.put("user", user);
		modelMap.put("datas", pages);
		modelMap.put("query", query);
	}
	/**
	 * 查询船合同列表（船代）
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.ARRIV_PORT,Constants.DEPA_PORT})
	@RequestMapping(value=URLMapping.SHIPPACT_LIST_HTM_AGENCY)
	public void queryShipPactListAgency(CurrentUser user,ModelMap modelMap, PactQuery query) {
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		log.info("#####当前登录用户信息########:"+BeanConvertUtils.beanToMap(user));
		if ( user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		String[] roles = user.getRoles();
		if (roles != null){
			for (String str : roles) {
				log.info("#####当前登录用户权限信息########:"+str);
			}
		}
		query.setPageSize(10);
		SimplePageInfo pageInfo = query.getPageInfo();
		Map<String,Object> map = new HashMap<>();
		if ( user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		map.put("epMemberId", user.getEpMemberId());
		map.put("pactCode", query.getCode());
		map.put("status", query.getStatus());
		Page<Map<String,Object>> pages = shipPactService.queryShipPactListAgency(map,pageInfo);
		
		query.setTotalItem(pages.getTotal());
		modelMap.put("user", user);
		modelMap.put("datas", pages);
		modelMap.put("query", query);
	}
	
	/**
	 * 查询船合同列表 （/）
	 * @param vo
	 * @param user
	 * @return
	 */
	/*@RolesAccess({Constants.ADMIN,Constants.SHIP_OWNER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@RequestMapping(value=URLMapping.SHIPPACT_LIST_HTM_NEW)
	public void queryShipPactListNew(CurrentUser user,ModelMap modelMap, PactQuery query) {
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			log.info("#####当前登录用户信息########:"+BeanConvertUtils.beanToMap(user));
			if ( user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			String[] roles = user.getRoles();
			if (roles != null){
				for (String str : roles) {
					log.info("#####当前登录用户权限信息########:"+str);
				}
			}
			query.setPageSize(10);
			SimplePageInfo pageInfo = query.getPageInfo();
			pageInfo.setPageSize(10);
			Map<String,Object> map = new HashMap<>();
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			map.put("epMemberId", user.getEpMemberId());
			map.put("agreementCode", query.getCode());
			map.put("pageSize", pageInfo.getPageSize());
			map.put("type", "2");
			int fromIndex = (pageInfo.getPageNum() - 1) * pageInfo.getPageSize();
			map.put("fromIndex", fromIndex);
			Page<ShipPact>  list = shipPactService.queryShipPactLists(map,pageInfo);
//			int i = shipPactService.queryShipPactListCount(map);
//			String total =String.valueOf(i);
			query.setTotalItem(list.getTotal());
			modelMap.put("user", user);
			modelMap.put("datas", list);
			modelMap.put("query", query);
	}*/
	/**
	 * 查询船合同列表 （新版船东）
	 * @param vo
	 * @param user
	 * @return
	 */
	/*@RolesAccess({Constants.ADMIN,Constants.SHIP_OWNER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@RequestMapping(value=URLMapping.SHIPPACT_LIST_HTM_NEW_OWNER)
	public void queryShipPactListNewOwner(CurrentUser user,ModelMap modelMap, PactQuery query) {
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		log.info("#####当前登录用户信息########:"+BeanConvertUtils.beanToMap(user));
		if ( user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		String[] roles = user.getRoles();
		if (roles != null){
			for (String str : roles) {
				log.info("#####当前登录用户权限信息########:"+str);
			}
		}
		query.setPageSize(10);
		SimplePageInfo pageInfo = query.getPageInfo();
		pageInfo.setPageSize(10);
		Map<String,Object> map = new HashMap<>();
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		map.put("epMemberId", user.getEpMemberId());
		map.put("agreementCode", query.getCode());
		map.put("pageSize", pageInfo.getPageSize());
		map.put("type", "1");
		int fromIndex = (pageInfo.getPageNum() - 1) * pageInfo.getPageSize();
		map.put("fromIndex", fromIndex);
		Page<ShipPact>  list = shipPactService.queryShipPactLists(map,pageInfo);
		query.setTotalItem(list.getTotal());
		modelMap.put("user", user);
		modelMap.put("datas", list);
		modelMap.put("query", query);
	}*/
	
	
	/**
	 * 查询船合同列表(船东/经纪人)
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER})
	@RequestMapping(value=URLMapping.SHIP_OWNER_SHIPPACT_LIST_HTM)
	public void queryShipOwnerShipPactList(CurrentUser user,ModelMap modelMap, PactQuery query) {
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			log.info("#####当前登录用户信息########:"+BeanConvertUtils.beanToMap(user));
			if ( user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			String[] roles = user.getRoles();
			if (roles != null){
				for (String str : roles) {
					log.info("#####当前登录用户权限信息########:"+str);
				}
			}
			query.setPageSize(10);
			SimplePageInfo pageInfo = query.getPageInfo();
			Map<String,Object> map = new HashMap<>();
			if ( user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			map.put("member", user.getEpMemberId());
			map.put("pactCode", query.getCode());
			map.put("status", query.getStatus());
			Page<Map<String,Object>> pages = shipPactService.queryShipOwnerShipPactList(map,pageInfo);
			
			query.setTotalItem(pages.getTotal());
			modelMap.put("user", user);
			modelMap.put("datas", pages);
			modelMap.put("query", query);
	}
	
	
	/**
	 * 查询船合同列表（代理）
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@RequestMapping(value=URLMapping.SHIP_AGENT_SHIPPACT_LIST_HTM)
	public void queryShipAgentShipPactList(CurrentUser user,ModelMap modelMap, PactQuery query) {
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			log.info("#####当前登录用户信息########:"+BeanConvertUtils.beanToMap(user));
			if ( user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			String[] roles = user.getRoles();
			if (roles != null){
				for (String str : roles) {
					log.info("#####当前登录用户权限信息########:"+str);
				}
			}
			query.setPageSize(10);
			SimplePageInfo pageInfo = query.getPageInfo();
			Map<String,Object> map = new HashMap<>();
			if ( user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			map.put("epMemberId", user.getEpMemberId());
			map.put("pactCode", query.getCode());
			map.put("status", query.getStatus());
			Page<Map<String,Object>> pages = shipPactService.queryShipAgentShipPactList(map,pageInfo);
			
			query.setTotalItem(pages.getTotal());
			modelMap.put("user", user);
			modelMap.put("datas", pages);
			modelMap.put("query", query);
	}
	
	
	/**
	 * 查询船合同列表（货主）
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@RequestMapping(value=URLMapping.CARGO_OWNER_SHIPPACT_LIST_HTM)
	public void queryCargoOwnerShipPactList(CurrentUser user,ModelMap modelMap, PactQuery query) {
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			log.info("#####当前登录用户信息########:"+BeanConvertUtils.beanToMap(user));
			if ( user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			String[] roles = user.getRoles();
			if (roles != null){
				for (String str : roles) {
					log.info("#####当前登录用户权限信息########:"+str);
				}
			}
			query.setPageSize(10);
			SimplePageInfo pageInfo = query.getPageInfo();
			Map<String,Object> map = new HashMap<>();
			if ( user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			map.put("epMemberId", user.getEpMemberId());
			map.put("pactCode", query.getCode());
			map.put("status", query.getStatus());
			Page<Map<String,Object>> pages = shipPactService.queryShipAgentShipPactList(map,pageInfo);
			
			query.setTotalItem(pages.getTotal());
			modelMap.put("user", user);
			modelMap.put("datas", pages);
			modelMap.put("query", query);
	}
	
	
	/**
	 * 查询物流跟踪
	 * @param vo
	 * @return
	 */
	@WithoutAccess
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.QUERY_TRACK, method = RequestMethod.POST )
	public ResultDatas<Object> queryTrack(@RequestBody ShipPactVO vo,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			if (StringUtils.isNullOrEmpty(vo.getAgreementUuid()) && StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH526);
			}
			/*if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}*/
			Map<String,Object> map = shipPactService.queryTrack(vo,user);
			res.setDatas(map);
		} catch (BizException e) {
			log.error("queryTrack error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("queryTrack error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	
	/**
	 * 移动端查询物流跟踪
	 * @param vo
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.MOBILEQUERY_TRACK,method = RequestMethod.POST)
	public ResultDatas<Object> mobileQueryTrack(@RequestBody ShipPactVO vo) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			if (StringUtils.isNullOrEmpty(vo.getOrderCode())){
				throw new TransportException(TransportException.TYPE.ITSH106);
			}
			Agreement agreement = new Agreement();
			agreement.setOrderCode(vo.getOrderCode());
			List<Agreement> agreements = agreementService.queryByEntitys(agreement);
			if(agreements==null||agreements.size()==0){
				throw new TransportException(TransportException.TYPE.ITSH027);
			}
			vo.setAgreementUuid(agreements.get(0).getUuid());
			Map<String,Object> map = shipPactService.queryTrack(vo,null);
			res.setDatas(map);
		} catch (BizException e) {
			log.error("mobileQueryTrack error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("mobileQueryTrack error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/**
	 * 移动端查询船合同分页列表(代理）
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.MOBILEQUERY_SHIPPACT_LIST, method = RequestMethod.POST  )
	public ResultDatas<Object> queryAgencyShipPactPageList(@RequestBody ShipPactVO vo, CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			SimplePageInfo pageInfo = vo.getPageInfo();
			vo.setEpMemberId(user.getEpMemberId());
			Page<Map<String,Object>> page = shipPactService.queryShipPactList(BeanConvertUtils.beanToMap(vo),pageInfo);
			//List<ShipPact> list = BeanConvertUtils.mapToBeanInList(page, ShipPact.class);
			res.setDatas(page);
			res.setPageNum(page.getPageNum());
			res.setPageCount(page.getPages());
			res.setPageSize(page.getPageSize());
		} catch (BizException e) {
			log.error("queryShipPactList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("queryShipPactList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	

	//***************************************************************************************
	/**
	 * 匹配完成
	 * @param uuid
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.SHIP_OWNER,Constants.SHIP_BROKER})
	@ResponseBody
	@RequestMapping(value=URLMapping.SHIPPACT_AGREENMENT_MAPPING_OVER, method = RequestMethod.POST  )
	public Result cancelWaybill(@RequestBody ShipPactVO vo,CurrentUser user) {
		Result res = new Result();
		//验证登录信息
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH039);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		if (StringUtils.isNullOrEmpty(vo.getUuid())){
			throw new TransportException(TransportException.TYPE.ITSH038);
		}
		Map<String,Object> map = new HashMap<>();
		map.put("status", "2");
		map.put("updateUser", user.getMemberId());
		map.put("uuid", vo.getUuid());
		try {
			shipPactService.updateShipPactStatus(vo,user);
		} catch (Exception e ) {
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	
	/**
	 * 根据 shipPlateUuid，查询船合同信息
	 * @param vo
	 * @param user
	 * @return
	 */
/*	@RolesAccess({Constants.ADMIN,Constants.SHIP_AGENT,Constants.ENTER_MASTER})
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.QUERY_BY_SHIPPLATEUUID, method = RequestMethod.POST  )
	public ResultDatas<Object> queryByShipPlateUuid(@RequestBody ShipPactVO vo) {
		
		ResultDatas<Object> res = new ResultDatas<>();
		
		try {
			//船盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getShipPlateUuid())){
				throw new TransportException(TransportException.TYPE.ITSH230);
			}
			
			List<ShipPact> list = shipPactService.queryByEntitys(BeanConvertUtils.beanToBean(vo, ShipPact.class));
			if(list==null || list.size()==0){
				throw new TransportException(TransportException.TYPE.ITSH039);
			}
			ShipPact shipPact = list.get(0);
			
			Map<String, Object> map=new HashMap<String,Object>();
			map.put("shipPact", shipPact);
			res.setDatas(map);
			
		} catch (BizException e) {
			log.error("queryByShipPlateUuid error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("queryByShipPlateUuid error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	
	*//**
	 * 根据 intentionUuid，查询船合同信息
	 * @param vo
	 * @param user
	 * @return
	 *//*
	@RolesAccess({Constants.ADMIN,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.ENTER_MASTER})
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.QUERY_BY_INTENTION_UUID, method = RequestMethod.POST  )
	public ResultDatas<Object> queryByIntentionUuid(@RequestBody ShipPactVO vo) {
		
		ResultDatas<Object> res = new ResultDatas<>();
		
		try {
			//询盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getShipIntentionUuid())){
				throw new TransportException(TransportException.TYPE.ITSH404);
			}
			
			List<ShipPact> list = shipPactService.queryByIntentionUuid(vo);
			if(list==null || list.size()==0){
				throw new TransportException(TransportException.TYPE.ITSH039);
			}
			
			ShipPact shipPact = list.get(0);
			Map<String, Object> map=new HashMap<String,Object>();
			map.put("shipPact", shipPact);
			res.setDatas(map);
			
		} catch (BizException e) {
			log.error("queryByIntentionUuid error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("queryByIntentionUuid error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}*/
	
	
	//===================== 421 ==============================//
	
	
	/**
	 * 查询船合同列表（平台跟踪）
	 * @param vo
	 * @param user
	 * @return
	 */
	@RightAccess(2019)
	@RolesAccess({Constants.ADMIN})
	@RequestMapping(value=URLMapping.QUERY_SHIPPACTP_LATFORM)
	public void queryShipPactPlatform(CurrentUser user,ModelMap modelMap,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain,PactQuery query) {
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		query.setPageSize(20);
		SimplePageInfo pageInfo = query.getPageInfo();
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		pageInfo.setPageNum(query.getCurrentPage());
		Map<String,Object> map = new HashMap<>();
		map.put("shipName", query.getShipName());
		map.put("pactCode", query.getPactCode());
		map.put("status", query.getStatus());
		map.put("agreementCode", query.getAgreementCode());
		/*map.put("ageBeg", query.getAgeBeg());
		map.put("ageEnd", query.getAgeEnd());*/
		map.put("publishName", query.getPublishName());
		map.put("carrierName", query.getCarrierName());
		map.put("laycanBeg", query.getLaycanBeg());
		map.put("laycanEnd", query.getLaycanEnd());
		map.put("shipOwner", query.getShipOwner());
		map.put("loadPort", query.getLoadPort());
		map.put("unloadPort", query.getUnloadPort());
		map.put("createBeg", query.getCreateBeg());
		map.put("createEnd", query.getCreateEnd());
		map.put("oilType", query.getOilType());
		
		Page<Map<String,Object>> pages = shipPactService.queryShipPactPlatform(map,pageInfo);
		
		query.setTotalItem(pages.getTotal());
		modelMap.put("user", user);
		modelMap.put("datas", pages);
		modelMap.put("query", query);
	}
	
}
