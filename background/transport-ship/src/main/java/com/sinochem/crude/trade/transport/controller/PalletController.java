package com.sinochem.crude.trade.transport.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.sinochem.crude.trade.transport.model.res.ForShipOrderInfoRes;
import com.sinochem.crude.trade.transport.service.CommonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eyeieye.melody.web.locale.VisitorLocale;
import com.github.pagehelper.Page;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.remote.FindShipOrderResult;
import com.sinochem.crude.trade.orderexecute.remote.ForShipOrderInfo;
import com.sinochem.crude.trade.orderexecute.remote.IFindShipService;
import com.sinochem.crude.trade.transport.URLMapping;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.domain.Agreement;
import com.sinochem.crude.trade.transport.model.AgreementVO;
import com.sinochem.crude.trade.transport.model.ClauseVO;
import com.sinochem.crude.trade.transport.model.PalletVO;
import com.sinochem.crude.trade.transport.model.ShipPactVO;
import com.sinochem.crude.trade.transport.query.OrderQuery;
import com.sinochem.crude.trade.transport.query.PactQuery;
import com.sinochem.crude.trade.transport.query.PalletQuery;
import com.sinochem.crude.trade.transport.query.Trader;
import com.sinochem.crude.trade.transport.service.AgreementService;
import com.sinochem.crude.trade.transport.service.PalletService;
import com.sinochem.crude.trade.transport.service.ShipPactService;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;

/**
 * @ClassName: PalletController
 * @Description: 租船需求信息
 * @author gyy
 */
@Controller
public class PalletController {
	@Autowired
	private PalletService palletService;
	@Autowired
	private AgreementService agreementService;
	@Autowired
	private IFindShipService iFindShipService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private ShipPactService shipPactService;
	
	@Autowired
	private CommonService commonService;


	Log log = LogFactory.getLog(PalletController.class);

	
	/**
	 * 查询承运商列表(前台接口)
	 * @param vo
	 * @param user
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping(value="/traderNameList.json", method = RequestMethod.POST)
	public ResultDatas<Object> queryWaybillListhaa(Trader trader) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			List<Trader> list = new ArrayList<>();
			Trader trader3 = new Trader();
			trader3.setTraderId(20001);
			trader3.setTraderName("测试用户");
			list.add(trader3);
			try {
				List<EnterpriseVo> list2 = enterpriseService.selectByCredentials("1");
				if (list2 != null && !list2.isEmpty()){
					for (EnterpriseVo enterpriseVo : list2) {
						if (enterpriseVo.getMemberId() != null){
							Trader trader2 = new Trader();
							trader2.setTraderId(enterpriseVo.getMemberId());
							trader2.setTraderName(enterpriseVo.getName());
							list.add(trader2);
							log.info("承运商id++++++==========="+enterpriseVo.getMemberId());
							log.info("承运商名称++++++==========="+enterpriseVo.getFullName());
							log.info("承运商==========="+BeanConvertUtils.beanToMap(enterpriseVo));
						}
					}
				}
			} catch (Exception e) {
				log.error(e);
			}
			res.setDatas(list);
		} catch (BizException e) {
			log.error(" error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error(" error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}*/
	
	/**
	 * 查询承运商列表
	 * @param vo
	 * @param user
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.TRADERNAMELISTFORBACK, method = RequestMethod.POST  )
	public ResultDatas<Object> queryWaybillListForBack(@RequestBody Trader trader) {
		ResultDatas<Object> res = new ResultDatas<>();
		if (StringUtils.isNullOrEmpty(trader.getType()) && (trader.getTypeList() == null || trader.getTypeList().isEmpty()) ){
			trader.setType("7");
		}
		try {
			//获取当前语言环境
			Locale current = VisitorLocale.getCurrent();
			String lang = current.getLanguage();
			
			List<Map<String,Object>> list = new ArrayList<>();
			try {
				//多种资质查询
				if (trader.getTypeList() != null && !trader.getTypeList().isEmpty()){
					for (String type : trader.getTypeList()) {
						List<EnterpriseVo> list2 = enterpriseService.selectByCredentials(type);
						if (list2 != null && !list2.isEmpty()){
							for (EnterpriseVo enterpriseVo : list2) {
								if (enterpriseVo.getMemberId() != null){
									Map<String,Object> map = new HashMap<>();
									
									String nameStr="";
									if ("en".equals(lang)){
										nameStr = enterpriseVo.getEnglishName();
									} else {
										nameStr = enterpriseVo.getFullName();
									}
									
									map.put("traderName",nameStr);
									map.put("traderId",enterpriseVo.getMemberId());
									map.put("type",type);
									list.add(map);
								}
							}
						}
					}
				} else {
					//一种资质查询
					List<EnterpriseVo> list2 = enterpriseService.selectByCredentials(trader.getType());
					if (list2 != null && !list2.isEmpty()){
						for (EnterpriseVo enterpriseVo : list2) {
							if (enterpriseVo.getMemberId() != null){
								Map<String,Object> map = new HashMap<>();
								
								String nameStr="";
								if ("en".equals(lang)){
									nameStr = enterpriseVo.getEnglishName();
								} else {
									nameStr = enterpriseVo.getFullName();
								}
								
								map.put("traderName",nameStr);
								map.put("traderId",enterpriseVo.getMemberId());
								map.put("type",trader.getType());
								list.add(map);
								/*log.info("根据"+trader.getType()+"资质查询会员==========="+BeanConvertUtils.beanToMap(enterpriseVo));*/
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("根据资质查询会员接口异常",e);
			}
			res.setDatas(list);
		} catch (BizException e) {
			log.error("根据资质查询会员接口异常", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("根据资质查询会员接口异常", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/**
	 * 查询货主协议列表
	 * @param vo
	 * @return
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@RequestMapping(value = URLMapping.PALLET_AGREEMENT_LIST_HTM)
	public void queryAgreementListHtm(CurrentUser user,ModelMap modelMap,  PactQuery query) {

		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		query.setPageSize(10);
		SimplePageInfo pageInfo = query.getPageInfo();
		Map<String, Object> maps = new HashMap<>();
		maps.put("agreementCode", query.getCode());
		maps.put("member",user.getEpMemberId());
		maps.put("status", query.getStatus());
		maps.put("oilType", query.getOilType());
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
		modelMap.put("searchCode", query.getCode());
	}
	
	
	/***
	 * 租船信息_新增
	 * 
	 * @param vo
	 * @param user
	 * @return
	 */
	/*@RolesAccess({Constants.ADMIN,Constants.TRADE_EXECUTOR,Constants.ENTER_MASTER})
	@ResponseBody
	//@RequestMapping(value = URLMapping.PALLET_SAVE, method = RequestMethod.POST )
	public Result savePallet(@RequestBody PalletVO vo, CurrentUser user) {
		Result res = new Result();
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			palletService.savePallet(vo, user);
		} catch (BizException e) {
			log.error("savePallet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("savePallet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}*/
	/**
	 * 校验是否已租船
	 * @param vo
	 * @return
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.CHECKA_GREEMENT, method = RequestMethod.POST  )
	public Result checkAgreement(@RequestBody AgreementVO vo,CurrentUser user) {
		Result res = new Result();
		try {
			
			if (StringUtils.isNullOrEmpty(vo.getOrderCode())){
				throw new TransportException(TransportException.TYPE.ITSH106);
			}
			if (user == null ){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			palletService.checkAgreementIsExsit(vo.getOrderCode());
		} catch (BizException e) {
			log.error("checkAgreement error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("checkAgreement error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/***
	 * 租船信息_取消申请
	 * 
	 * @param vo
	 * @param user
	 * @return
	 */
	/*@RolesAccess({Constants.ADMIN,Constants.TRADE_EXECUTOR,Constants.ENTER_MASTER})
	@ResponseBody
	//@RequestMapping(value = URLMapping.PALLET_UPDATE_STATUS, method = RequestMethod.POST )
	public Result updateStatusOld(@RequestBody PalletVO vo, CurrentUser user) {
		log.info("--->" + vo.toString());
		Result res = new Result();
		try {
			palletService.updateStatus(vo, user);
		} catch (BizException e) {
			log.error("deleteStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("deleteStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}*/

	/***
	 * 租船信息_无订单列表
	 * 
	 * @param map
	 * @param user
	 * @return
	 */
	/*@RolesAccess({Constants.ADMIN,Constants.TRADE_EXECUTOR,Constants.ENTER_MASTER})
	//@RequestMapping(value = URLMapping.PALLET_SELECT)
	public void slecteAll(CurrentUser user, ModelMap modelMap, PalletQuery query) {
		//后台数据统一10条/页
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loadPort", query.getLoadPort());
		param.put("unloadPort", query.getUnloadPort());
		param.put("epMemberId", user.getEpMemberId());
		param.put("status", query.getStatus());
		param.put("orderNo","0");
		// 总记录数设定
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimplePageInfo pageInfo =new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(10);
		query.setPageSize(10);
		Page<Map<String, Object>> palletList = palletService.getPalletListTrader(param,pageInfo);
		query.setTotalItem(palletList.getTotal());
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
		
		Map<String, Object> map12 = palletService.getTaskNum(param);
		
		modelMap.put("task", map12.get("task"));
		modelMap.put("untask", map12.get("untask"));
		modelMap.put("user", user);
		modelMap.put("datas", palletList);
		modelMap.put("query", query);
		modelMap.put("searchUnLoadPort", query.getUnloadPort());
		modelMap.put("searchLoadPort", query.getLoadPort());
		modelMap.put("searchStatus", query.getStatus());
	}*/

	/***
	 * 租船信息_有订单列表
	 * 
	 * @param map
	 * @param user
	 * @return
	 */
	/*@RolesAccess({Constants.ADMIN,Constants.TRADE_EXECUTOR,Constants.ENTER_MASTER})
	//@RequestMapping(value = URLMapping.PALLET_QUEREY)
	public void findAll(CurrentUser user, ModelMap modelMap,PalletQuery query) {
		//后台数据统一10条/页
		query.setPageSize(10);
		try{
			//检查用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			query.setEpMemberId(user.getEpMemberId());
		}catch (BizException e) {
			log.error("用户信息 error", e);
			throw new TransportException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			log.error("用户信息错误", e);
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		Map<String, Object> param = new HashMap<String, Object>();
		//param.put("orderPact", query.getOrderPact());
		param.put("oilName", query.getOilName());
		param.put("epMemberId", 100000085L);
		param.put("status", query.getStatus());
		param.put("orderNo","1");
		
		// 总记录数
		//int total = palletService.countRecords3(param);
		// 总记录数设定
		//query.setTotalItem(Long.valueOf(total));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimplePageInfo pageInfo =new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(10);
		query.setPageSize(10);
		Page<Map<String, Object>> palletList = palletService.findPalletListTrander(param, pageInfo);
		query.setTotalItem(palletList.getTotal());
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
		Map<String, Object> map12 = palletService.getTaskNum(param);
		
		modelMap.put("task", map12.get("task"));
		modelMap.put("untask", map12.get("untask"));
		
		modelMap.put("user", user);
		modelMap.put("datas", palletList);
		modelMap.put("query", query);
		modelMap.put("searchOilName", query.getOilName());
		//modelMap.put("searchOrderPact", query.getOrderPact());
		modelMap.put("searchStatus", query.getStatus());
	}*/

	/***
	 * 租船信息_详情
	 * 
	 * @param vo
	 * @return
	 */
	@RightAccess(2018)
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.ADMIN,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value = URLMapping.PALLET_SELECT_UUID, method = RequestMethod.POST )
	public ResultDatas<Map<String, Object>> palletDeatil(@RequestBody PalletVO vo) {
		ResultDatas<Map<String, Object>> res = new ResultDatas<>();
		try {
			// uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH016);
			}
			// 获取对象
			Map<String, Object> map = palletService.palletDeatil(vo);
			res.setDatas(map);
		} catch (BizException e) {
			log.error("palletDeatil error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {		
			log.error("palletDeatil error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	/***
	 * 回显租船协议页面数据
	 * 
	 * @param vo
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value = URLMapping.PALLET_SELECT_BY_UUID, method = RequestMethod.POST)
	public ResultDatas<Map<String, Object>> palletDeatilByUuid(@RequestBody PalletVO vo) {
		ResultDatas<Map<String, Object>> res = new ResultDatas<>();
		try {
			// uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH016);
			}
			// 获取对象
			Map<String, Object> map = palletService.palletDeatilByUuid(vo);
			res.setDatas(map);
		} catch (BizException e) {
			log.error("selectPollet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {		
			log.error("selectPollet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}

	/****
	 * 租船信息_删除
	 * 
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value = URLMapping.PALLET_DELETE, method = RequestMethod.POST)
	public Result delPallet(@RequestBody PalletVO vo, CurrentUser user) {
		log.info("--->" + vo.toString());
		Result res = new Result();
		try {
			palletService.delPallet(vo, user);
		} catch (BizException e) {
			log.error("delPallet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("delPallet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}

	/****
	 * 租船信息_修改
	 * 
	 * @param vo
	 * @param user
	 * @return
	 */
	/*@RolesAccess({Constants.ADMIN,Constants.TRADE_EXECUTOR,Constants.ENTER_MASTER})
	@ResponseBody
	//@RequestMapping(value = URLMapping.PALLET_UPDATE, method = RequestMethod.POST)
	public Result updatePalletDiscard(@RequestBody PalletVO vo, CurrentUser user) {
		Result res = new Result();
		try {
			// 租船uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH016);
			}
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			palletService.updatePallet(vo, user);
		} catch (BizException e) {
			log.error("updatePallet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());

		} catch (Exception e) {
			log.error("updatePallet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}

		return res;
	}*/
	
	
	/**
	 * 查询我要租船订单信息翻页列表
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@RequestMapping(value=URLMapping.PALLET_ORDER_PAGE_LIST)
	public void getOrderPageList(CurrentUser user, ModelMap modelMap, OrderQuery query) {
		//后台数据统一10条/页
		query.setPageSize(10);
		Integer pageNum = query.getCurrentPage();
		Integer pageSize = query.getPageSize();
		FindShipOrderResult result = iFindShipService.sendOrderInfo(pageNum, pageSize,user.getEpMemberId());//100000129
				
		// 总记录数
		Long total = result.getTotal();

		// 总记录数设定
		query.setTotalItem(total);
				
		// 订单数据
        List<ForShipOrderInfoRes> list2 = new ArrayList<>();
		List<ForShipOrderInfo> list = result.getOrderInfoList();
		if (list != null && !list.isEmpty()){
			for(ForShipOrderInfo info : list){
                ForShipOrderInfoRes  res = new ForShipOrderInfoRes();
                BeanUtils.copyProperties(info,res);
                String oil =  info.getName();
                String loadPort =  info.getLoadPort();
                String unport =  info.getUnport();
                res.setLoadPortName( commonService.findNameByCodeAndLang("1",loadPort));
                res.setUnportName( commonService.findNameByCodeAndLang("2",unport));
                res.setNameOil( commonService.findNameByCodeAndLang("3",oil));
                list2.add(res);
            }
		}
		modelMap.put("user", user);
		modelMap.put("datas", list2);
		modelMap.put("query", query);
	}
	/**
	 * 订单租船页面
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@RequestMapping(value=URLMapping.PALLET_EDIT)
	public void palletEdit(CurrentUser user, ModelMap modelMap, OrderQuery query) {
		modelMap.put("user", user);
		modelMap.put("query", query);
	}
	
	/**
	 * 根据船名模糊查询船合同信息或代理协议信息
	 * @param vo
	 * @param user
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.QUERY_TRACK_UUID, method = RequestMethod.POST )
	public ResultDatas<List<Map<String,Object>>> queryUuid(@RequestBody ShipPactVO vo, CurrentUser user) {
		ResultDatas<List<Map<String,Object>>> res = new ResultDatas<>();
		List<Map<String, Object>> list = new ArrayList<>(); 
		Map<String, Object> map = new HashMap<>();
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		map.put("epMemberId", user.getEpMemberId());
		try {
			if(Arrays.asList(user.getRoles()).contains(Constants.TRADE_EXECUTOR)){
				List<Map<String, Object>> palletList = palletService.queryRecords(map);
				if(palletList==null||palletList.isEmpty()){
					throw new TransportException(TransportException.TYPE.ITSH017);
				}
				for (Map<String, Object> pallet : palletList) {
					Map<String, Object> map3 = new HashMap<>();
					Agreement agreement = new Agreement();
					agreement.setPalletUuid(String.valueOf(pallet.get("uuid")));
					agreement.setShipName(vo.getShipName());
					List<Agreement> agreements = agreementService.queryByEntitys(agreement);
					if(agreements!=null&&!agreements.isEmpty()){
						map3.put("agreementUuid",agreements.get(0).getUuid());
						map3.put("agreementCode",agreements.get(0).getAgreementCode());
						map3.put("type",1);
						list.add(map3);
					}
				}
			}
			if(Arrays.asList(user.getRoles()).contains(Constants.SHIP_AGENT)){
				map.put("shipName",vo.getShipName());
				List<Map<String, Object>> shipPactList =shipPactService.queryRecords(map);
				if(shipPactList!=null&&!shipPactList.isEmpty()){
					for (Map<String, Object> shipPact : shipPactList) {
						Map<String, Object> map3 = new HashMap<>();
						map3.put("shipPactUuid",shipPact.get("uuid"));
						map3.put("pactCode", shipPact.get("pactCode"));
						map3.put("type",2);
						list.add(map3);
					}
				}
			}
			res.setDatas(list);
		} catch (BizException e) {
			log.error("queryValueSet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("queryValueSet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	
	//**************appJSON列表数据接口*************************
	/**
	 * 有订单列表数据
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value = URLMapping.PALLET_QUEREY_APP,method = RequestMethod.POST )
	public ResultDatas<List<Map<String, Object>>> findAllApp(@RequestBody PalletVO vo,CurrentUser user) {
		ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
		try{
			//检查用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
				vo.setEpMemberId(user.getEpMemberId());
		}catch (BizException e) {
			log.error("用户信息 error", e);
			throw new TransportException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			log.error("用户信息错误", e);
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		vo.setOrderNo("0");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Page<Map<String, Object>> palletList = palletService.findPalletListTrander(BeanConvertUtils.beanToMap(vo),vo.getPageInfo());
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
		res.setDatas(palletList);
		res.setPageNum(palletList.getPageNum());
		res.setPageCount(palletList.getPages());
		res.setPageSize(palletList.getPageSize());
		
		return res;
	}

	/**
	 * 无订单列表数据
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value = URLMapping.PALLET_SELECT_APP,method = RequestMethod.POST )
	public ResultDatas<List<Map<String, Object>>> selectAllApp(@RequestBody PalletVO vo,CurrentUser user) {
		ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		vo.setEpMemberId(user.getEpMemberId());
		vo.setOrderNo("0");
		Page<Map<String, Object>> palletList = palletService.getPalletListTrader(BeanConvertUtils.beanToMap(vo),vo.getPageInfo());
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
		
		res.setDatas(palletList);
		res.setPageNum(palletList.getPageNum());
		res.setPageCount(palletList.getPages());
		res.setPageSize(palletList.getPageSize());
		
		return res;
	}
	/**
	 * 货主协议列表数据
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value = URLMapping.PALLET_AGREEMENT_APP, method = RequestMethod.POST )
	public ResultDatas<List<Agreement>> queryAgreementListJson(@RequestBody AgreementVO vo,CurrentUser user) {
			ResultDatas<List<Agreement>> res = new ResultDatas<>();
			SimplePageInfo pageInfo = vo.getPageInfo();
			Page<Map<String,Object>> pages = agreementService.queryAgreementList(BeanConvertUtils.beanToMap(vo),pageInfo);
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
			
			res.setDatas(list);
			res.setPageNum(pages.getPageNum());
			res.setPageCount(pages.getPages());
			res.setPageSize(pages.getPageSize());
			return res;
	}
	
	/***
	 * app租船需求信息__有订单翻页列表(代理)
	 * 
	 * @param PalletVO
	 * @param user
	 * @return 
	 */
	@RolesAccess({Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.MOBILEQUERY_PALLETLIST_AGENCY2, method = RequestMethod.POST )
	public ResultDatas<Map<String,List<Map<String, Object>>>> queryAgencyPalletList(@RequestBody PalletVO vo, CurrentUser user) {
		ResultDatas<Map<String,List<Map<String, Object>>>> res = new ResultDatas<>();
		Map<String,List<Map<String, Object>>> datas =new HashMap<>();
		List<Map<String, Object>> tasks = new ArrayList<>();
		try{
			//检查用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
				vo.setTraderId(user.getEpMemberId());
		}catch (BizException e) {
			log.error("用户信息 error", e);
			throw new TransportException(e.getCode(), e.getMessage());
		} catch (Exception e) {
			log.error("用户信息错误", e);
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		vo.setOrderNo("1");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Page<Map<String, Object>> palletList = palletService.findPalletListTrander(BeanConvertUtils.beanToMap(vo),vo.getPageInfo());
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
		Map<String, Object> map12 = palletService.getTaskNum(BeanConvertUtils.beanToMap(vo));
		datas.put("tasks",tasks);
		datas.put("list", palletList);
		tasks.add(map12);
		res.setDatas(datas);
		res.setPageNum(palletList.getPageNum());
		res.setPageCount(palletList.getPages());
		res.setPageSize(palletList.getPageSize());
		return res;
	}
	
	/***
	 * app租船需求信息__无订单翻页列表(代理)
	 * 
	 * @param PalletVO
	 * @param user
	 * @return 
	 */
	@RolesAccess({Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.MOBILEQUERY_PALLETLIST_AGENCY1, method = RequestMethod.POST )
	public ResultDatas<Map<String,List<Map<String, Object>>>> queryAgencyPalletPageList(@RequestBody PalletVO vo, CurrentUser user) {
		ResultDatas<Map<String,List<Map<String, Object>>>> res = new ResultDatas<>();
		Map<String,List<Map<String, Object>>> datas =new HashMap<>();
		List<Map<String, Object>> tasks = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		vo.setTraderId(user.getEpMemberId());
		vo.setOrderNo("0");
		Page<Map<String, Object>> palletList = palletService.getPalletListTrader(BeanConvertUtils.beanToMap(vo),vo.getPageInfo());
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
		Map<String, Object> map12 = palletService.getTaskNum(BeanConvertUtils.beanToMap(vo));
		datas.put("tasks",tasks);
		datas.put("list", palletList);
		tasks.add(map12);
		res.setDatas(datas);
		res.setPageNum(palletList.getPageNum());
		res.setPageCount(palletList.getPages());
		res.setPageSize(palletList.getPageSize());
		return res;
	}
	
/************************************************3/31***************************************************/
	/***
	 * 租船需求_无订单列表_货主
	 * @param map
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@RequestMapping(value = URLMapping.PALLET_SELECT)
	public void palletSelect(CurrentUser user, ModelMap modelMap, PalletQuery query) {
		//验证登陆
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if ( user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		
		//查询条件
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loadPort", query.getLoadPort());
		param.put("unloadPort", query.getUnloadPort());
		param.put("epMemberId", user.getEpMemberId());
		param.put("status", query.getStatus());
		//无订单 
		param.put("orderNo","0");
		//翻页
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		query.setPageSize(10);
		Page<Map<String, Object>> palletList = palletService.getPalletListTrader(param,query.getPageInfo());
		query.setTotalItem(Long.valueOf(palletList.getTotal()));
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
		modelMap.put("user", user);
		modelMap.put("datas", palletList);
		modelMap.put("query", query);
		modelMap.put("searchUnLoadPort", query.getUnloadPort());
		modelMap.put("searchLoadPort", query.getLoadPort());
		modelMap.put("searchStatus", query.getStatus());
	}
	
	/***
	 * 租船需求_有订单列表_货主
	 * @param map
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@RequestMapping(value = URLMapping.PALLET_QUEREY)
	public void slecteAll12(CurrentUser user, ModelMap modelMap, PalletQuery query) {
		//登录验证
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if ( user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		//条件
		Map<String, Object> param = new HashMap<String, Object>();
		//param.put("orderPact", query.getOrderPact());
		param.put("oilName", query.getOilName());
		param.put("epMemberId", user.getEpMemberId());
		param.put("status", query.getStatus());
		//有订单
		param.put("orderNo","1");
		// 总记录数设定
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//翻页
		query.setPageSize(10);
		Page<Map<String, Object>> palletList = palletService.findPalletListTrander(param,query.getPageInfo());
		query.setTotalItem(Long.valueOf(palletList.getTotal()));
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
		modelMap.put("user", user);
		modelMap.put("datas", palletList);
		modelMap.put("query", query);
		modelMap.put("searchOilName", query.getOilName());
		//modelMap.put("searchOrderPact", query.getOrderPact());
		modelMap.put("searchStatus", query.getStatus());
	}
	
	/***
	 * 租船需求_无订单列表_转租船东
	 * 
	 * @param map
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_TRADER})
	@RequestMapping(value = URLMapping.PALLET_SELECT_OWNER)
	public void palletListAgency(CurrentUser user, ModelMap modelMap, PalletQuery query) {
		//登录验证
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if ( user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		//条件
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loadPort", query.getLoadPort());
		param.put("unloadPort", query.getUnloadPort());
		param.put("traderId", user.getEpMemberId());
		param.put("status", query.getStatus());
		//指定转租船东
		param.put("traderSel","1");
		//无订单
		param.put("orderNo","0");
		// 分页
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		query.setPageSize(10);
		Page<Map<String, Object>> palletList = palletService.getPalletListTrader(param,query.getPageInfo());
		query.setTotalItem(Long.valueOf(palletList.getTotal()));
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
		//待处理的需求数
		Map<String, Object> taskNum = palletService.getTaskNum(param);
		
		modelMap.put("taskNum",taskNum);
		modelMap.put("user", user);
		modelMap.put("datas", palletList);
		modelMap.put("query", query);
		modelMap.put("searchUnLoadPort", query.getUnloadPort());
		modelMap.put("searchLoadPort", query.getLoadPort());
		modelMap.put("searchStatus", query.getStatus());
	}
	/***
	 * 租船需求_有订单列表_转租船东
	 * 
	 * @param map
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_TRADER})
	@RequestMapping(value = URLMapping.PALLET_QUEREY_OWNER)
	public void slecteAll14(CurrentUser user, ModelMap modelMap, PalletQuery query) {
		//登录验证
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if ( user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		//条件
		Map<String, Object> param = new HashMap<>();
		param.put("oilName", query.getOilName());
		param.put("traderId", user.getEpMemberId());
		param.put("status", query.getStatus());
		//无订单
		param.put("orderNo","1");
		//指定转租船东
		param.put("traderSel","1");
		
		// 总记录数设定
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		query.setPageSize(10);
		Page<Map<String, Object>> palletList = palletService.findPalletListTrander(param,query.getPageInfo());
		query.setTotalItem(Long.valueOf(palletList.getTotal()));
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
		//待处理的需求数
		Map<String, Object> taskNum = palletService.getTaskNum(param);
		modelMap.put("taskNum", taskNum);
		modelMap.put("user", user);
		modelMap.put("datas", palletList);
		modelMap.put("query", query);
		modelMap.put("searchOilName", query.getOilName());
		//modelMap.put("searchOrderPact", query.getOrderPact());
		modelMap.put("searchStatus", query.getStatus());
	}
	
	/***
	 * 租船信息_新增
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value = URLMapping.PALLET_SAVE, method = RequestMethod.POST )
	public Result savePallet1(@RequestBody PalletVO vo, CurrentUser user) {
		Result res = new Result();
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			palletService.savePallet(vo,user);
			res.setMessage(vo.getUuid());
		} catch (BizException e) {
			log.error("savePallet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("savePallet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	/****
	 * 租船信息_修改
	 * 
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value = URLMapping.PALLET_UPDATE, method = RequestMethod.POST)
	public Result updatePallet(@RequestBody PalletVO vo, CurrentUser user) {
		Result res = new Result();
		try {
			// 租船uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH016);
			}
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			palletService.updatePallet(vo, user);
		} catch (BizException e) {
			log.error("updatePallet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());

		} catch (Exception e) {
			log.error("updatePallet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	/***
	 * 租船信息_关闭
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value = URLMapping.PALLET_UPDATE_STATUS, method = RequestMethod.POST )
	public Result updateStatus(@RequestBody PalletVO vo, CurrentUser user) {
		Result res = new Result();
		try {
			palletService.updateStatus(vo, user);
		} catch (BizException e) {
			log.error("deleteStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("deleteStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	/***
	 * 指定转租船东
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value = URLMapping.PALLET_APPOINT, method = RequestMethod.POST )
	public Result appointTradertrader(@RequestBody PalletVO vo, CurrentUser user) {
		Result res = new Result();
		try {
			palletService.appointTradertrader(vo, user);
		} catch (BizException e) {
			log.error("deleteStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("deleteStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/**
	 * 查询货盘信息列表(前台接口，货盘滚动信息)
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_PALLET_LIST, method = RequestMethod.POST )
	public ResultDatas<List<Map<String,Object>>> findPalletList(@RequestBody PalletVO vo, CurrentUser user) {

		ResultDatas<List<Map<String,Object>>> res = new ResultDatas<>();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			// 查询货盘信息列表
			List<Map<String,Object>> list= palletService.findPalletList(vo);
			
			// 设定返回数据
			res.setDatas(list);
			res.setTotal(Long.valueOf(list.size()));
		} catch (BizException e) {
			log.error("findPalletList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("findPalletList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 查询货盘信息列表(前台接口，货盘滚动查看更多信息)
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_MORE_PALLET_LIST, method = RequestMethod.POST )
	public ResultDatas<List<Map<String,Object>>> findMorePalletList(@RequestBody PalletVO vo, CurrentUser user) {
		ResultDatas<List<Map<String,Object>>> res = new ResultDatas<>();
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			SimplePageInfo pageInfo = vo.getPageInfo();
			Map<String,Object> map = new HashMap<>();
			map.put("portName", vo.getPortName());
			Page<Map<String,Object>> page= palletService.findMorePalletList(map,pageInfo);
			// 设定返回数据
			res.setDatas(page);
			res.setPageCount(page.getPages());
			res.setPageNum(page.getPageNum());
			res.setPageSize(page.getPageSize());
			res.setTotal(page.getTotal());
		} catch (BizException e) {
			log.error("findMorePalletList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("findMorePalletList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	
	/**
	 * 查询货盘详情(前台接口)
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_PALLET_DETAIL, method = RequestMethod.POST )
	public ResultDatas<Map<String,Object>> findPalletDetail(@RequestBody PalletVO vo, CurrentUser user) {
		ResultDatas<Map<String,Object>> res = new ResultDatas<>();
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			if (StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH016);
			}
			Map<String,Object> map= palletService.findPalletDetail(vo);
			// 设定返回数据
			res.setDatas(map);
		} catch (BizException e) {
			log.error("findPalletDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("findPalletDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	/**
	 * 租船确认详情
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.BUY_TRADER,Constants.SALES_TRADER,Constants.TRADE_EXECUTOR,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.CONFIRMSDETAILS, method = RequestMethod.POST )
	public ResultDatas<Map<String,Object>> confirmsDetails(@RequestBody ClauseVO vo, CurrentUser user) {
		ResultDatas<Map<String,Object>> res = new ResultDatas<>();
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			if (StringUtils.isNullOrEmpty(vo.getPalletUuid())){
				throw new TransportException(TransportException.TYPE.ITSH016);
			}
			Map<String,Object> map= palletService.confirmsDetails(vo);
			// 设定返回数据
			res.setDatas(map);
		} catch (BizException e) {
			log.error("findPalletDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("findPalletDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/***
	 * om租船需求列表
	 */
	@RightAccess(2017)
	@RolesAccess({Constants.ADMIN})
	@RequestMapping(value = URLMapping.PALLETLIST_OM)
	public void queryPallets(CurrentUser user, ModelMap modelMap , @RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain,PalletQuery query) {
		//登录验证
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if ( user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		//条件
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("epMemberName", query.getPublisher());
		param.put("oilName", query.getOilName());
		param.put("shipType", query.getShipType());
		param.put("loadPort", query.getLoadPort());
		
		param.put("unloadPort", query.getUnloadPort());
		param.put("shipAgeBeg", query.getShipAgeBeg());
		param.put("shipAgeEnd", query.getShipAgeEnd());
		param.put("publishTimeBeg", query.getPublishTimeBeg());
		
		param.put("publishTimeEnd", query.getPublishTimeEnd());
		param.put("layCanBeg", query.getLayCanBeg());
		param.put("layCanEnd", query.getLayCanEnd());
		param.put("status", query.getStatus());
		
		param.put("palletCode", query.getPalletCode());
		param.put("orderNo", query.getOrderNo());
		
		// 总记录数设定
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		query.setPageSize(20);
		Page<Map<String, Object>> palletList = palletService.queryPallets(param,query.getPageInfo());
		query.setTotalItem(palletList.getTotal());
		
		modelMap.put("user", user);
		modelMap.put("datas", palletList);
		modelMap.put("query", query);
	}
}
