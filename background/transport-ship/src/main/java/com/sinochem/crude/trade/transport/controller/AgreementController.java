package com.sinochem.crude.trade.transport.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.sinochem.crude.trade.transport.domain.ShipPact;
import com.sinochem.crude.trade.transport.model.AgreementVO;
import com.sinochem.crude.trade.transport.model.res.AgreementRes;
import com.sinochem.crude.trade.transport.query.PactQuery;
import com.sinochem.crude.trade.transport.query.PalletQuery;
import com.sinochem.crude.trade.transport.service.AgreementService;
import com.sinochem.crude.trade.transport.service.IntentionService;
import com.sinochem.crude.trade.transport.service.PalletService;
import com.sinochem.crude.trade.transport.service.ShipPactService;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * @ClassName: AgreementController
 * @Description: 协议信息
 * @author gyy
 */
@Controller
public class AgreementController  {

	@Autowired
	private AgreementService agreementService;
	@Autowired
	private ShipPactService shipPactService;
	@Autowired
	private PalletService palletService;
	@Autowired
	private IntentionService intentionService;
	
	Log log = LogFactory.getLog(AgreementController.class);
	
	
	/**
	 * 生成代理协议（***********作废***********）
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.SAVE_AGREEMENT, method = RequestMethod.POST  )
	public Result saveAgreement(@RequestBody AgreementVO vo,CurrentUser user) {
		Result res = new Result();
		try {
			//验证登录信息
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//校验必填
			if (StringUtils.isNullOrEmpty(vo.getPalletUuid())){
				throw new TransportException(TransportException.TYPE.ITSH016);
			} 
			if (!StringUtils.isNullOrEmpty(vo.getUuid())){
				agreementService.updateAgreement(vo,user);
			} else {
				agreementService.saveAgreement(vo,user);
			}
			
		} catch (BizException e) {
			log.error("saveAgreement error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("saveAgreement error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/**
	 * 生成代理协议
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.AGREEMENT_SAVE, method = RequestMethod.POST  )
	public Result agreementSave(@RequestBody AgreementVO vo,CurrentUser user) {
		Result res = new Result();
		try {
			//验证登录信息
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if ( user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			if (StringUtils.isNullOrEmpty(vo.getShipPlateUuid())){
				throw new TransportException(TransportException.TYPE.ITSH012);
			}
			if (StringUtils.isNullOrEmpty(vo.getType())){
				throw new TransportException(TransportException.TYPE.ITSH550);
			}
			
			if (StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH002);
			}
			agreementService.agreementSave(vo,user);
			
		} catch (BizException e) {
			log.error("agreementSave error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("agreementSave error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/**
	 * 查询代理协议详情
	 * @param vo
	 * @return
	 */
	@RightAccess(2021)
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
				Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER,Constants.ADMIN})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_AHREEMENT_DETAIL, method = RequestMethod.POST  )
	public ResultDatas<Object> findAgreementDetail(@RequestBody AgreementVO vo,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			//校验必填
			if (StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH026);
			} 
			AgreementRes agree = agreementService.findAgreementDetail(vo.getUuid());
			res.setDatas(agree);
		} catch (BizException e) {
			log.error("findAgreementDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("findAgreementDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	/**
	 * 根据合同uuid查询代理协议详情
	 * @param vo
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_SHIPPACT_DETAIL, method = RequestMethod.POST  )
	public ResultDatas<Object> findAgreementDetailByShipPact(@RequestBody AgreementVO vo,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			//校验必填
			if (StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH026);
			} 
			Agreement agree = agreementService.findAgreementDetailByShipPact(vo,user);
			res.setDatas(agree);
		} catch (BizException e) {
			log.error("findAgreementDetailByShipPact error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("findAgreementDetailByShipPact error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	/**
	 * 查询代理协议详情多个
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@RequestMapping(value=URLMapping.FIND_AHREEMENT_DETAIL_MANY, method = RequestMethod.POST  )
	public ResultDatas<Object> findAgreementDetailMany(@RequestBody AgreementVO vo,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			//校验必填
			if (vo.getAgreementUuids() == null || vo.getAgreementUuids().isEmpty()){
				throw new TransportException(TransportException.TYPE.ITSH026);
			} 
			List<AgreementRes> agree = agreementService.findAgreementDetailMany(vo.getAgreementUuids());
			res.setDatas(agree);
		} catch (BizException e) {
			log.error("findAgreementDetailMany error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("findAgreementDetailMany error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	/**
	 * 查询代理协议详情(货盘uuid)
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@RequestMapping(value=URLMapping.FIND_AHREEMENT_DETAIL_BY_UUID, method = RequestMethod.POST  )
	public ResultDatas<Object> findAgreementDetailByPalletUuid( @RequestBody AgreementVO vo,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			//校验必填
			if (StringUtils.isNullOrEmpty(vo.getPalletUuid())){
				throw new TransportException(TransportException.TYPE.ITSH016);
			} 
			AgreementRes agree = agreementService.findAgreementDetailByPalletUuid(vo.getPalletUuid());
			res.setDatas(agree);
		} catch (BizException e) {
			log.error("findAgreementDetailByPalletUuid error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("findAgreementDetailByPalletUuid error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/**
	 * 查询代理协议列表
	 * @param vo
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.QUERY_AHREEMENT_LIST, method = RequestMethod.POST  )
	public ResultDatas<Object> queryAgreementList(@RequestBody AgreementVO vo) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			SimplePageInfo pageInfo = vo.getPageInfo();
			
			Page<Map<String,Object>> page = agreementService.queryAgreementList(BeanConvertUtils.beanToMap(vo),pageInfo);
			List<Agreement> list = BeanConvertUtils.mapToBeanInList(page, Agreement.class);
			res.setDatas(list);
			res.setPageNum(page.getPageNum());
			res.setPageSize(page.getPageSize());
			res.setPageCount(page.getPages());
		} catch (BizException e) {
			log.error("queryAgreementList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("queryAgreementList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	/**
	 * 撤销代理协议
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@RequestMapping(value=URLMapping.REVOKE_AGREEMENT, method = RequestMethod.POST  )
	public Result revokeAgreement(@RequestBody AgreementVO vo,CurrentUser user) {
		Result res = new Result();
		try {

			if (StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH026);
			}
			if (user == null ){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if ( user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			agreementService.revokeAgreement(vo.getUuid(),user);
		} catch (BizException e) {
			log.error("revokeAgreement error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("revokeAgreement error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	/**
	 * 删除代理协议
	 * @param vo
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.DELETE_AGREEMENT, method = RequestMethod.POST  )
	public Result deleteAgreement(@RequestBody AgreementVO vo,CurrentUser user) {
		Result res = new Result();
		try {
			
			if (StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH026);
			}
			if (user == null ){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if ( user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			agreementService.deleteAgreement(vo.getUuid(),user);
		} catch (BizException e) {
			log.error("deleteAgreement error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("deleteAgreement error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/**
	 * 查询代理协议列表
	 * @param vo
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@RequestMapping(value = URLMapping.AGREEMENT_LIST_HTM)
	public void queryAgreementListHtm(CurrentUser user,ModelMap modelMap,  PactQuery query) {
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if ( user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			log.info("##############用户信息："+BeanConvertUtils.beanToMap(user));
			query.setPageSize(10);
			SimplePageInfo pageInfo = query.getPageInfo();
			
			Map<String, Object> maps = new HashMap<>();
			maps.put("agreementCode", query.getCode());
			maps.put("epMemberId", user.getEpMemberId());
			maps.put("status", query.getStatus());
			Page<Map<String,Object>> pages = agreementService.queryAgreementList(maps,pageInfo);
			for (Map<String,Object> map : pages) {
				SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
				map.put("ext1", dateFormater.format(map.get("signDate")));
				String loadPort = (String)map.get("loadPort");
				String unloadPort = (String)map.get("unloadPort");
				String quantity = (String)map.get("quantity");
				String oilType = (String)map.get("oilType");
				String laycan = (String)map.get("laycan");
				if (!StringUtils.isNullOrEmpty(laycan)){
					map.put("laycan",laycan.replaceAll("/", "--"));
				}
				if (!StringUtils.isNullOrEmpty(quantity)){
					map.put("quantity",quantity.substring(0,quantity.length()-1));
				}
				if (!StringUtils.isNullOrEmpty(oilType)){
					map.put("oilType",oilType.substring(0,oilType.length()-1));
				}
				if (!StringUtils.isNullOrEmpty(loadPort)){
					map.put("loadPort",loadPort.substring(0,loadPort.length()-1));
				}
				if (!StringUtils.isNullOrEmpty(unloadPort)){
					map.put("unloadPort",unloadPort.substring(0,unloadPort.length()-1));
				}
			}
			List<Agreement> list = BeanConvertUtils.mapToBeanInList(pages, Agreement.class);
			query.setTotalItem(pages.getTotal());
			modelMap.put("user", user);
			modelMap.put("datas", list);
			modelMap.put("query", query);
		
	}
	/**
	 * 查询未匹配代理协议列表
	 * @param vo
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@RequestMapping(value = URLMapping.AGREEMENT_MATCH_LIST_HTM)
	public void queryAgreementMatchListHtm(CurrentUser user,ModelMap modelMap,  PactQuery query) {
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if ( user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		if (StringUtils.isNullOrEmpty(query.getUuid())){
			throw new TransportException(TransportException.TYPE.ITSH038);
		}
		
		query.setPageSize(10);
		SimplePageInfo pageInfo = query.getPageInfo();
		ShipPact shipPact = shipPactService.findByUuid(query.getUuid());
		if (shipPact == null){
			throw new TransportException(TransportException.TYPE.ITSH039);
		}
		if ( user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		Map<String, Object> maps = new HashMap<>();
		maps.put("agreementCode", query.getCode());
		maps.put("status", "1");
		maps.put("epMemberId", user.getEpMemberId());
		maps.put("shipName", shipPact.getShipName());
		Page<Map<String,Object>> pages = agreementService.queryAgreementList(maps,pageInfo);
		for (Map<String,Object> map : pages) {
			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
			map.put("ext1", dateFormater.format(map.get("signDate")));
		}
		List<Agreement> list = BeanConvertUtils.mapToBeanInList(pages, Agreement.class);
		query.setTotalItem(pages.getTotal());
		modelMap.put("user", user);
		modelMap.put("datas", list);
		modelMap.put("query", query);
		
	}
	/***
	 * 租船信息_有订单列表
	 * 
	 * @param map
	 * @param user
	 * @return 
	 */
	//@RolesAccess({Constants.ADMIN,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.ENTER_MASTER})
	//@RequestMapping(value = URLMapping.ORDER_LIST_HTM)
	public void findOrderPalletList(CurrentUser user, ModelMap modelMap,PalletQuery query) {
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if ( user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		Map<String, Object> maps = new HashMap<>();
		maps.put("orderPact", query.getOrderPact());
		maps.put("oilName", query.getOilName());
		maps.put("traderId", user.getEpMemberId());
		maps.put("status", query.getStatus());
		maps.put("orderNo", "1");
		
		SimplePageInfo pageInfo =new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(10);
		query.setPageSize(10);
		Page<Map<String, Object>> palletList = palletService.findPalletListTrander(maps,pageInfo);
		query.setTotalItem(palletList.getTotal());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Map<String, Object> map2 : palletList) {
			Object obj = map2.get("laycanBeg");
			if (obj != null){
				String format = sdf.format((Date)obj);
				map2.put("laycanBeg", format);
			}
			Object obj2 = map2.get("laycanEnd");
			if (obj2 != null){
				String format1 = sdf.format((Date)obj2);
				map2.put("laycanEnd", format1);
			}
			Object obj3 = map2.get("shipType");
			if(obj3 != null){
				String str = (String)obj3;
				String str1 = str.substring(0, str.length()-1);
				map2.put("shipType", str1);
			}
			Object obj4 = map2.get("shipAge");
			if(obj4 != null){
				String str = (String)obj4;
				String str1 = str.substring(0, str.length()-1);
				map2.put("shipAge", str1);
			}
		}
		
		Map<String, Object> map12 = palletService.getTaskNum(maps);
		
		modelMap.put("task", map12.get("task"));
		modelMap.put("untask", map12.get("untask"));
		
		modelMap.put("user", user);
		modelMap.put("datas", palletList);
		modelMap.put("query", query);
	}
	
	
	/***
	 * 租船信息_无订单列表
	 * 
	 * @param map
	 * @param user
	 * @return
	 */
	//@RolesAccess({Constants.ADMIN,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.ENTER_MASTER})
	//@RequestMapping(value = URLMapping.UNORDER_LIST_HTM)
	public void findNoOrderPalletList(CurrentUser user, ModelMap modelMap,PalletQuery query) {

		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if ( user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		Map<String, Object> maps = new HashMap<>();
		maps.put("loadPort", query.getLoadPort());
		maps.put("unloadPort", query.getUnloadPort());
		maps.put("traderId", user.getEpMemberId());
		maps.put("status", query.getStatus());
		maps.put("orderNo", "0");
		
		SimplePageInfo pageInfo = new  SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(10);
		query.setPageSize(10);
		Page<Map<String, Object>> palletList = palletService.getPalletListTrader(maps,pageInfo);
		query.setTotalItem(palletList.getTotal());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Map<String, Object> map2 : palletList) {
			Object obj = map2.get("laycanBeg");
			if (obj != null){
				String format = sdf.format((Date)obj);
				map2.put("laycanBeg", format);
			}
			Object obj2 = map2.get("laycanEnd");
			if (obj2 != null){
				String format1 = sdf.format((Date)obj2);
				map2.put("laycanEnd", format1);
			}
			Object obj3 = map2.get("shipType");
			if(obj3 != null){
				String str = (String)obj3;
				String str1 = str.substring(0, str.length()-1);
				map2.put("shipType", str1);
			}
			Object obj4 = map2.get("shipAge");
			if(obj4 != null){
				String str = (String)obj4;
				String str1 = str.substring(0, str.length()-1);
				map2.put("shipAge", str1);
			}
		}
		Map<String, Object> map12 = palletService.getTaskNum(maps);
		
		modelMap.put("task", map12.get("task"));
		modelMap.put("untask", map12.get("untask"));
		
		modelMap.put("user", user);
		modelMap.put("datas", palletList);
		modelMap.put("query", query);
	}
	
	/***
	 * 移动端协议列表(代理)
	 * @param PalletVO
	 * @param user
	 * @return 
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.MOBILEQUERY_AGREEMENTLIST_AGENCY, method = RequestMethod.POST )
	public ResultDatas<List<Agreement>> mobileQueryAgreementListHtm( @RequestBody AgreementVO vo,CurrentUser user) {
		ResultDatas<List<Agreement>> res = new ResultDatas<>();
		try {
			SimplePageInfo pageInfo = vo.getPageInfo();
			vo.setEpMemberId(user.getEpMemberId());
			Page<Map<String,Object>> page = agreementService.queryAgreementList(BeanConvertUtils.beanToMap(vo),pageInfo);
			List<Agreement> list = BeanConvertUtils.mapToBeanInList(page, Agreement.class);
			res.setDatas(list);
			res.setPageNum(page.getPageNum());
			res.setPageSize(page.getPageSize());
			res.setPageCount(page.getPages());
		} catch (BizException e) {
			log.error("mobileQueryAgreementListHtm error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("mobileQueryAgreementListHtm error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	/**********************************3/31*************************************************/
	/***
	 * （代理）租船信息_有订单列表
	 * 
	 * @param map
	 * @param user
	 * @return
	 */
	//@RolesAccess({Constants.ADMIN,Constants.SHIP_AGENT,Constants.ENTER_MASTER})
	//@RequestMapping(value = URLMapping.ORDER_LIST_HTM)
	public void findOrderPalletList1(CurrentUser user, ModelMap modelMap,PalletQuery query) {
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if ( user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		Map<String, Object> maps = new HashMap<>();
		maps.put("orderPact", query.getOrderPact());
		maps.put("oilName", query.getOilName());
		maps.put("traderId", user.getEpMemberId());
		maps.put("status", query.getStatus());
		maps.put("traderSel","1");
		maps.put("orderNo", "1");
		SimplePageInfo pageInfo = new  SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(10);
		Page<Map<String, Object>> palletList = palletService.findPalletListTrander(maps,pageInfo);
		query.setTotalItem(Long.valueOf(palletList.getPages()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Map<String, Object> map2 : palletList) {
			Object obj = map2.get("laycanBeg");
			if (obj != null){
				String format = sdf.format((Date)obj);
				map2.put("laycanBeg", format);
			}
			Object obj2 = map2.get("laycanEnd");
			if (obj2 != null){
				String format1 = sdf.format((Date)obj2);
				map2.put("laycanEnd", format1);
			}
			Object obj3 = map2.get("shipType");
			if(obj3 != null){
				String str = (String)obj3;
				String str1 = str.substring(0, str.length()-1);
				map2.put("shipType", str1);
			}
			Map<String, Object> param1 = new HashMap<>();
			param1.put("palletUuid",map2.get("uuid"));
			int count = intentionService.countRecords(param1);
			map2.put("intentionCount", count);
		}
		Map<String, Object> map12 = palletService.getTaskNum(maps);
		modelMap.put("task", map12.get("task"));
		modelMap.put("untask", map12.get("untask"));
		
		modelMap.put("user", user);
		modelMap.put("datas", palletList);
		modelMap.put("query", query);
		modelMap.put("searchUnLoadPort", query.getUnloadPort());
		modelMap.put("searchLoadPort", query.getLoadPort());
		modelMap.put("searchStatus", query.getStatus());
	}
	/***
	 * （代理）租船信息_无订单
	 * 
	 * @param map
	 * @param user
	 * @return
	 */
	//@RolesAccess({Constants.ADMIN,Constants.SHIP_AGENT,Constants.ENTER_MASTER})
	//@RequestMapping(value = URLMapping.UNORDER_LIST_HTM)
	public void findNoOrderPalletList1(CurrentUser user, ModelMap modelMap,PalletQuery query) {

		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if ( user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		Map<String, Object> maps = new HashMap<>();
		maps.put("loadPort", query.getLoadPort());
		maps.put("unloadPort", query.getUnloadPort());
		maps.put("traderId", user.getEpMemberId());
		maps.put("status", query.getStatus());
		maps.put("traderSel","1");
		maps.put("orderNo", "0");
		
		SimplePageInfo pageInfo = new  SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(10);
		Page<Map<String, Object>> palletList = palletService.getPalletListTrader(maps,pageInfo);
		query.setTotalItem(Long.valueOf(palletList.getPages()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Map<String, Object> map2 : palletList) {
			Object obj = map2.get("laycanBeg");
			if (obj != null){
				String format = sdf.format((Date)obj);
				map2.put("laycanBeg", format);
			}
			Object obj2 = map2.get("laycanEnd");
			if (obj2 != null){
				String format1 = sdf.format((Date)obj2);
				map2.put("laycanEnd", format1);
			}
			Object obj3 = map2.get("shipType");
			if(obj3 != null){
				String str = (String)obj3;
				String str1 = str.substring(0, str.length()-1);
				map2.put("shipType", str1);
			}
		}
		Map<String, Object> map12 = palletService.getTaskNum(maps);
		
		modelMap.put("task", map12.get("task"));
		modelMap.put("untask", map12.get("untask"));
		
		modelMap.put("user", user);
		modelMap.put("datas", palletList);
		modelMap.put("query", query);
		modelMap.put("searchOilName", query.getOilName());
		modelMap.put("searchOrderPact", query.getOrderPact());
		modelMap.put("searchStatus", query.getStatus());
	}
	
	
	
	
	//====================== 421=============================//
	
	
	/**
	 * 查询平台协议列表
	 * @param vo
	 * @return
	 */
	@RolesAccess({Constants.ADMIN})
	@RequestMapping(value = URLMapping.QUERY_AGREEMENT_PLATFORM)
	public void queryAgreementPlatform(CurrentUser user,ModelMap modelMap,
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
		Map<String, Object> maps = new HashMap<>();
		maps.put("agreementCode", query.getCode());
		maps.put("status", query.getStatus());
		maps.put("oilType", query.getOilType());
		Page<Map<String,Object>> pages = agreementService.queryAgreementPlatform(maps,pageInfo);
		for (Map<String,Object> map : pages) {
			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
			map.put("ext1", dateFormater.format(map.get("signDate")));
			String loadPort = (String)map.get("loadPort");
			String unloadPort = (String)map.get("unloadPort");
			String quantity = (String)map.get("quantity");
			String oilType = (String)map.get("oilType");
			String laycan = (String)map.get("laycan");
			if (!StringUtils.isNullOrEmpty(laycan)){
				map.put("laycan",laycan.replaceAll("/", "--"));
			}
			if (!StringUtils.isNullOrEmpty(quantity)){
				map.put("quantity",quantity.substring(0,quantity.length()-1));
			}
			if (!StringUtils.isNullOrEmpty(oilType)){
				map.put("oilType",oilType.substring(0,oilType.length()-1));
			}
			if (!StringUtils.isNullOrEmpty(loadPort)){
				map.put("loadPort",loadPort.substring(0,loadPort.length()-1));
			}
			if (!StringUtils.isNullOrEmpty(unloadPort)){
				map.put("unloadPort",unloadPort.substring(0,unloadPort.length()-1));
			}
		}
		List<Agreement> list = BeanConvertUtils.mapToBeanInList(pages, Agreement.class);
		query.setTotalItem(pages.getTotal());
		modelMap.put("user", user);
		modelMap.put("datas", list);
		modelMap.put("query", query);
		modelMap.put("searchCode", query.getCode());
	}
	
	
	
}
