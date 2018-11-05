package com.sinochem.crude.trade.listed.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import com.sinochem.crude.trade.listed.domain.*;
import com.sinochem.crude.trade.listed.enums.DemandRelevanterType;
import com.sinochem.crude.trade.listed.enums.TradeChainPublishTimeEnum;
import com.sinochem.crude.trade.listed.enums.TradingChainEnterpriseOperate;
import com.sinochem.crude.trade.listed.enums.TradingChainStatus;
import com.sinochem.crude.trade.listed.model.form.TradeChainForm;
import com.sinochem.crude.trade.listed.model.query.TradeChainQuery;
import com.sinochem.crude.trade.listed.model.vo.*;
import com.sinochem.crude.trade.listed.model.vo.tradingChainVo.EnterpriseNameByTradingChainVO;
import com.sinochem.crude.trade.listed.model.vo.tradingChainVo.TradeChainDemandVO;
import com.sinochem.crude.trade.listed.service.*;
import com.sinochem.crude.trade.listed.model.vo.tradingChainVo.TradingChainDetailsVO;
import com.sinochem.crude.trade.member.constants.MemberConstants;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.RolesAccess;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.eyeieye.melody.util.DateUtil;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.listed.constant.MsgConstant;
import com.sinochem.crude.trade.listed.constant.UrlMapping;
import com.sinochem.crude.trade.listed.helper.DictUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * 贸易链控制层
 * @author jiangxiuqiang
 */
@Controller
public class TradeChainController {

	// 报价单
	@Autowired
	private DemandService demandService;
	//贸易链
	@Autowired
	private TradingChainService TradingChainService;
	//查询买家卖家信息
	@Autowired
	private DemandRelevanterService demandRelevanterService;
	//船务信息
	@Autowired
	private DemandShipService demandShipService;

	@Autowired
	private CrudeOilResourceService crudeOilResourceService;
	@Autowired
	private CrudeOilInfoService crudeOilInfoService;

	@Autowired
	private EnterpriseService enterpriseService;

	@Autowired
	private DictionaryService dictionaryService;

	private final Logger LOGGER = LoggerFactory.getLogger(TradeChainController.class);

	private final String TRADER_CREDENTIAL = "2";

	@RequestMapping(UrlMapping.QUERY_TRADERS)
	public ModelAndView queryTraders() {
		ModelAndView mav = new ModelAndView();
		mav.setView(new MappingJacksonJsonView());

		List<EnterpriseVo> traderList = enterpriseService.selectByCredentials(TRADER_CREDENTIAL);
		mav.addObject("traderList", traderList);

		return mav;
	}

	/**
	 * 查询贸易链列表
	 * @return
	 */
	@RolesAccess({MemberConstants.OBSERVER,MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER,MemberConstants.TRADE_EXECUTOR})
	@RequestMapping(value = UrlMapping.TRADECHAIN_QUERYTRADECHAINLIST)
	public ModelAndView queryTradeChainList(CurrentUser user, TradeChainQuery tradeChainQuery, PageInfo query, String msg){
		ModelAndView model = new ModelAndView();
		try {

			PageInfoResult pageInfoResult = TradingChainService.getTradingChainList(user,tradeChainQuery,query);

			List<TradeChainListVo> tradeChainList = new ArrayList<TradeChainListVo>();
			List<TradingChain> list = pageInfoResult.getList();
			for (TradingChain vo : list ) {
				TradeChainListVo listVo = new TradeChainListVo();
				// 当前登录人id
				listVo.setMerberId(String.valueOf(user.getEpMemberId()));

				Long demandId = vo.getDemandId();

				// 编号
				listVo.setSerialNumber(vo.getSerialNumber());

				// 报价单信息
				Demand demand = demandService.getDemandByKey(demandId);

				// 日期格式化
				Date stopBidTime = demand.getStopBidTime();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(stopBidTime);

				// 贸易链有效期
				listVo.setYouxiao(dateString);

				//贸易链日期
				if(vo.getStartTime() != null ){
					listVo.setOrderDate(formatter.format(vo.getStartTime()));
				}else{
					listVo.setOrderDate(formatter.format(vo.getCreateTime()));
				}

				// id
				listVo.setUuid(vo.getUuid());

				// 状态
				listVo.setAliveFlag(vo.getStatus());

				// 查询油种
				List<CrudeOil> crudeOilList =  crudeOilResourceService.getCrudeListDemandId(demandId);

				for (CrudeOil cor: crudeOilList) {
					if(cor.getOrigin() != null) {
						CrudeOilInfoVO crudeOilInfoVO = crudeOilInfoService.findByENameAndAreaId(cor.getOrigin());
						if (crudeOilInfoVO != null) {
							cor.setAreaString(VisitorLocale.getByCurrentLanguage(new String[][]{new String[]{"zh",crudeOilInfoVO.getOriginNameC()},new String[]{"en",crudeOilInfoVO.getOriginNameE()}}));
						}
					}
				}

				List<CrudeOilVO> crudeOilListVO = CrudeOilVO.convertDomainToVoList(crudeOilList);
				String oil ="";
				// 列表展示油种信息
				for (CrudeOilVO crudeOilVO : crudeOilListVO) {
					listVo.setOilType(crudeOilVO.getName());
				}

				// 查询商家信息
				String operatorsId = vo.getOperatorsId();
				String[] operatorsIds = operatorsId.split(",");

				for (int i = 0; i < operatorsIds.length && operatorsIds != null ; i++){

					if(!operatorsIds[i].equals("null")){

						// 商家merberId
						Long id = Long.valueOf(operatorsIds[i]);

						// 查询商家信息
						EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(id);
//						根据中英文环境获取对应的企业名称
						String[][] enterpriseName = {new String[]{"zh",enterpriseVo.getFullName()}, new String[]{"en",enterpriseVo.getEnglishName()} };
						if(enterpriseVo != null){
							//供应商
							if(i == 0){
								listVo.setBuyer(VisitorLocale.getByCurrentLanguage(enterpriseName));
								listVo.setBuyerId(String.valueOf(enterpriseVo.getMemberId()));
							}

							//购买商
							if(i == 2){
								listVo.setSeller(VisitorLocale.getByCurrentLanguage(enterpriseName));
								listVo.setSellerId(String.valueOf(enterpriseVo.getMemberId()));
							}

							//贸易商
							if(i == 1){
								listVo.setMaoyi(VisitorLocale.getByCurrentLanguage(enterpriseName));
								listVo.setMaoyiId(String.valueOf(enterpriseVo.getMemberId()));
							}
						}
					}
				}

				tradeChainList.add(listVo);
			}

			model.addObject("tradingChainList",pageInfoResult);
			model.addObject("tradeChainList",tradeChainList);

			// 列表按钮操作失败提示
			if(msg != null){
				model.addObject("msg", msg);
			}
		} catch (BizException e) {
			e.printStackTrace();

			LOGGER.error("查询贸易链列表异常!");
			LOGGER.error(e.getMessage(), e);

			model.addObject("tradingChainList", query);

			model.addObject("msg", e.getMessage());
			model.setViewName(UrlMapping.TRADECHAIN_QUERYTRADECHAINLIST);
		} catch (Exception e) {
			e.printStackTrace();

			LOGGER.error("查询贸易链列表异常!");
			LOGGER.error(e.getMessage(), e);

			model.addObject("tradingChainList", query);
			model.addObject("msg", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0190));
			model.setViewName(UrlMapping.TRADECHAIN_QUERYTRADECHAINLIST);
		} finally {
			try {
				List<EnterpriseNameByTradingChainVO> enterpriseNameByTradingChainVOS = TradingChainService.selectEnterpriseNameByTradingChain(user);
				model.addObject("involvedEnterpriseList", enterpriseNameByTradingChainVOS);

				List<DictionaryVO> tradeChainPublishTimeOptionList = TradeChainPublishTimeEnum.getTradeChainPublishTimeOptions();
				model.addObject("tradeChainPublishTimeOptionList", tradeChainPublishTimeOptionList);

			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}


			model.addObject("user", user);
		}

		return model;
	}

	/**
	 * 贸易链驳回
	 */
	@RolesAccess({MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER})
	@RequestMapping(value = UrlMapping.TRADECHAIN_REJECTTRADINGCHAIN)
	public ModelAndView rejectTradingChain(CurrentUser user, String uuid, String reason){
		ModelAndView model = new ModelAndView();
		model.setView(new MappingJacksonJsonView());

		ResultDatas resultDatas = new ResultDatas();
		try {
			if(StringUtil.isNotEmpty(uuid)){

				TradingChainService.rejectTradingChain(user, uuid, reason);
				resultDatas.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0193));
			}

		}catch (BizException e){
			LOGGER.error("贸易链驳回失败", e);
			e.printStackTrace();
			resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0173));

		}catch (Exception e) {
			resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0173));
			LOGGER.error("贸易链驳回失败", e);
			e.printStackTrace();
		}

		model.addAllObjects((Map<String, ?>) resultDatas.toMap());
		return model;
	}

	/**
	 * 贸易链详情
	 */
	@RolesAccess({MemberConstants.OBSERVER,MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER,MemberConstants.TRADE_EXECUTOR})
	@RequestMapping(value = UrlMapping.TRADECHAIN_GETTRADINGCHAINDETAILS)
	public ModelAndView getTradingChainDetails(CurrentUser user, String uuid, String msg){

		ModelAndView model = new ModelAndView();
		try {
			if(uuid != null && StringUtil.isNotEmpty(uuid)){

				// 展示商家信息
				TradeChainListVo listVo = new TradeChainListVo();

				// 调用查询下拉列表方法
				this.publicSelect(model);

				// 当前登录人
				listVo.setMerberId(String.valueOf(user.getEpMemberId()));

				// 查询详情
				TradingChainDetailsVO tradingChainDetailVO = TradingChainService.getTradingChainDetails(user, uuid);
				List<Demand> list = tradingChainDetailVO.getDemandList();

				// 查询商家全名
				List<DemandRelevanter> RelevanterList1 = tradingChainDetailVO.getDemandRelevanterList1();
				for (DemandRelevanter demandRelevanter :RelevanterList1) {
					// 查询商家名称
					EnterpriseVo enterpriseVo1 = enterpriseService.getByMemberId(demandRelevanter.getEMemberId());
					String[][] enterpriseName = {new String[]{"zh",enterpriseVo1.getFullName()}, new String[]{"en",enterpriseVo1.getEnglishName()} };
					demandRelevanter.setEnterpriseName(VisitorLocale.getByCurrentLanguage(enterpriseName));
				}

				// 查询商家全名
				List<DemandRelevanter> RelevanterList2 = tradingChainDetailVO.getDemandRelevanterList2();
				for (DemandRelevanter demandRelevanter :RelevanterList2) {
					// 查询商家名称
					EnterpriseVo enterpriseVo1 = enterpriseService.getByMemberId(demandRelevanter.getEMemberId());
					String[][] enterpriseName = {new String[]{"zh",enterpriseVo1.getFullName()}, new String[]{"en",enterpriseVo1.getEnglishName()} };
					demandRelevanter.setEnterpriseName(VisitorLocale.getByCurrentLanguage(enterpriseName));
				}
				// 详情状态
				listVo.setAliveFlag(tradingChainDetailVO.getTradingChain().getStatus());

				// 报价单id
				Long demandId = tradingChainDetailVO.getTradingChain().getDemandId();

				// 根据报价单id查询数据
				Demand demand = demandService.getDemandByKey(demandId);

				// 查询商家信息
				String operatorsId = tradingChainDetailVO.getTradingChain().getOperatorsId();
				String[] operatorsIds = operatorsId.split(",");

				for (int i = 0; i < operatorsIds.length && operatorsIds != null ; i++){

					if(!operatorsIds[i].equals("null")){

						// 商家merberId
						Long id = Long.valueOf(operatorsIds[i]);

						// 查询商家信息
						EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(id);
//						根据中英文环境获取对应的企业名称
						String[][] enterpriseName = {new String[]{"zh",enterpriseVo.getFullName()}, new String[]{"en",enterpriseVo.getEnglishName()} };

						if(enterpriseVo != null){
							//供应商
							if(i == 0){
								listVo.setBuyer(VisitorLocale.getByCurrentLanguage(enterpriseName));
								listVo.setBuyerId(String.valueOf(enterpriseVo.getMemberId()));

							}

							//购买商
							if(i == 2){
								listVo.setSeller(VisitorLocale.getByCurrentLanguage(enterpriseName));
								listVo.setSellerId(String.valueOf(enterpriseVo.getMemberId()));
							}

							//贸易商
							if(i == 1){
								listVo.setMaoyi(VisitorLocale.getByCurrentLanguage(enterpriseName));
								listVo.setMaoyiId(String.valueOf(enterpriseVo.getMemberId()));
							}
						}
					}
				}
				model.addObject("demand",demand);
				model.addObject("listVo",listVo);
				model.addObject("tradingChainDetailVO",tradingChainDetailVO);
			}

			// 详情页面按钮操作失败提示
			if(msg != null){
				model.addObject("msg",msg);
				return model;
			}
		}catch (BizException e){
			LOGGER.error("",e);

			// 错误信息
			model.addObject("msg", e.getMessage());
			// 返回列表页
			model.setViewName("redirect:/"+"tradeChain/tradeChainList.htm");

		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
			// 错误信息
			model.addObject("msg",VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0175));
			// 返回列表页
			model.setViewName("redirect:/"+"tradeChain/tradeChainList.htm");
		}

		return model;
	}

	/**
	 * 公用下拉列表
	 */
	public void publicSelect(ModelAndView model){
		//商检费分摊
		Map<Object, String> inspectionFeeSharingTypeMap = DictUtils.getInspectionFeeSharingTypeMap();
		model.addObject("inspectionFeeSharingTypeMap", inspectionFeeSharingTypeMap);

		// 贸易条款
		Map<Object, String> tradeItemMap = DictUtils.getTradeItemMap();
		model.addObject("tradeItemMap",tradeItemMap);

		// 采购方式
		Map<Object,String> purchaseModes = DictUtils.getPurchaseModes();
		model.addObject("purchaseModes",purchaseModes);

		//付款日期下拉框
		Map<Object,String> payItemMap = DictUtils.getPayItemMap();
		model.addObject("payItemMap",payItemMap);

		// 信用条款
		Map<Object,String> creditItem = DictUtils.getCreditItem();
		model.addObject("creditItem",creditItem);

		//船型
		Map<Object,String> shipTypes = DictUtils.getShipTypes();
		model.addObject("shipTypes",shipTypes);

		// 计价期类别
		Map<Object,String> valuationProidTypeMap = DictUtils.getValuationProidTypeMap();
		model.addObject("valuationProidTypeMap", valuationProidTypeMap);

		//计价基准
		Map<Object,String> valuationBase = DictUtils.getValuationBaseMap();
		model.addObject("valuationBase",valuationBase);

		//计量方式 提单量、船检量、VEF船检量、船检量-ROB、VEF船检量-ROB、罐收量、罐出量
		Map<Object,String> measureModeMap = DictUtils.getMeasureModeMap();
		model.addObject("measureModeMap",measureModeMap);

		//合约
		Map<Object,String> contractKindMap = DictUtils.getContractKindMap();
		model.addObject("contractKindMap",contractKindMap);
	}

	/**
	 * 贸易链确认
	 */
	@RolesAccess({MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER})
	@RequestMapping(value = UrlMapping.TRADECHAIN_CONFIRMTRADECHAINORDER)
	public ModelAndView confirmTradingChain(CurrentUser user, String uuid, String reason){
		ModelAndView model = new ModelAndView();

		try {
			if(uuid != null && StringUtil.isNotEmpty(uuid)){
				TradingChainService.confirmTradingChain(user, uuid, reason);
			}
			model.setViewName("redirect:/"+"tradeChain/tradeChainList.htm");
		}catch (BizException e){
			LOGGER.error("",e);
			model.addObject("msg", e.getMessage());
			model.addObject("uuid",uuid);
			model.setViewName("redirect:/"+"tradeChain/getTradingChainDetails.htm");
		}catch (Exception e){
			model.addObject("msg", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0174));
			model.addObject("uuid",uuid);
			model.setViewName("redirect:/"+"tradeChain/getTradingChainDetails.htm");
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return model;
	}

	/**
	 * 贸易链列表页面提交/撤回
	 */
	@RolesAccess({MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER})
	@RequestMapping(value = UrlMapping.TRADECHAIN_SUBMITORWITHDRAWTRADINGCHAIN)
	public ModelAndView submitOrWithdrawTradingChain(CurrentUser user,String uuid ,Integer status){
		ModelAndView model = new ModelAndView();
		try {
			if(StringUtil.isNotEmpty(uuid) && uuid != null){
				TradingChainService.alterTradingChainStatus(user, uuid, status);
			}
			model.setViewName("redirect:/"+"tradeChain/tradeChainList.htm");
		}catch (BizException e){
			e.printStackTrace();
			LOGGER.error("",e);

			// 错误信息
			model.addObject("msg",e.getMessage());
			// 返回列表页
			model.setViewName("redirect:/"+"tradeChain/tradeChainList.htm");

		}catch (Exception e){

			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();

			// 错误信息
			model.addObject("msg",VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0176));
			// 返回列表页
			model.setViewName("redirect:/"+"tradeChain/tradeChainList.htm");
		}

		return model;
	}

	/**
	 * 跳转创建贸易链界面
	 * @return
	 */
	@RolesAccess({MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER})
	@RequestMapping(value = UrlMapping.TRADECHAIN_TOTRADECHAININSERT)
	public ModelAndView toTradeChainIInsert(Long demandId,@ModelAttribute(value="tradeChainFrom") TradeChainForm tradeChainFrom, CurrentUser user, String msg){

		ModelAndView model = new ModelAndView();

		// 获取报价单信息
		try {
			if(!tradeChainExists(demandId)) {
				// 调用查询下拉列表方法
				this.publicSelect(model);

				// 查询报价单信息
				Demand demand = demandService.getDemandByKey(demandId);

				//查询代理商信息
				DemandRelevanter demandRelevanter3 = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeTwo(demandId, DemandRelevanterType.SUPPLIER.getCode());
				EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(demandRelevanter3.getEMemberId());
				//	根据中英文环境放入企业的中英文名称
				String[][] enterpriseName = {new String[]{"zh",enterpriseVo.getFullName()}, new String[]{"en",enterpriseVo.getEnglishName()} };
				demandRelevanter3.setEnterpriseName(VisitorLocale.getByCurrentLanguage(enterpriseName));
				DemandRelevanterSupplierVO seller = new DemandRelevanterSupplierVO(demandRelevanter3);
				model.addObject("demandRelevanter3", seller);


				//查询买家信息
				DemandRelevanter demandRelevanter1 = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(demandId, DemandRelevanterType.BUYER.getCode());
				EnterpriseVo enterpriseVo1 = enterpriseService.getByMemberId(demandRelevanter1.getEMemberId());
//				根据中英文环境放入企业的中英文名称
				String[][] enterpriseName2 = {new String[]{"zh",enterpriseVo1.getFullName()}, new String[]{"en",enterpriseVo1.getEnglishName()} };
				demandRelevanter1.setEnterpriseName(VisitorLocale.getByCurrentLanguage(enterpriseName2));
				DemandRelevanterBuyerVO buyer = new DemandRelevanterBuyerVO(demandRelevanter1);
				model.addObject("demandRelevanter1", buyer);

				//获取船务信息
				List<DemandShip> demandShips = demandShipService.getShipByDemandId(demandId);
				if (CollectionUtils.isNotEmpty(demandShips)) {
					DemandShip demandship = demandShips.get(0);
					DemandShipVO demandShipVO = new DemandShipVO();
					demandShipVO.setDischargePort(demandship.getDischargePort());
					demandShipVO.setShipmentPort(demandship.getShipmentPort());
					demandShipVO.setShipmentStartTime(demandship.getShipmentStartTime());
					demandShipVO.setDischargeEndTime(demandship.getDischargeEndTime());
					demandShipVO.setShipmentEndTime(demandship.getShipmentEndTime());
					demandShipVO.setDischargeStartTime(demandship.getDischargeStartTime());
					model.addObject("demandShip", demandShipVO);
				}

				if (demand != null) {
					DemandVO demandVO = DemandVO.convertToVO(demand);

					model.addObject("demand", demandVO);
					Date stopBidTime = demand.getStopBidTime();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String dateString = formatter.format(stopBidTime);
					model.addObject("dateString",dateString);
					model.addObject("belongToCurrentUser", user != null && user.getEpMemberId().equals(demand.getCreater()));
					model.addObject("overdue", demand.getStopBidTime() != null && DateUtil.getToday().getTime().after(demand.getStopBidTime()));
				}
				// 创建贸易链按钮操作失败提示
				if(msg != null){
					model.addObject("msg",msg);
					return model;
				}
			}

		} catch (Exception ex) {

			model.addObject("msg", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0191));
			LOGGER.error(ex.getMessage(), ex);
			model.setViewName("redirect:/"+"purchaseManager/biddingDetail.htm?demandId="+demandId);
		}
		return model;
	}

	/**
	 * 跳转修改贸易链界面
	 */
	@RolesAccess({MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER})
	@RequestMapping(value = UrlMapping.TRADECHAIN_TOTRADECHAINEDIT)
	public ModelAndView toTradeChainIEdit(String uuid, CurrentUser user, String msg){

		ModelAndView model = new ModelAndView();

		try {
			if (StringUtil.isEmpty(uuid)) {
				throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0005));
			}

			TradingChain tradingChain = TradingChainService.selectByUuid(user, uuid);
			if (tradingChain == null) {
				throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0188));
			}
			// 调用查询下拉列表方法
			this.publicSelect(model);

			// 查询报价单信息
			Long demandId = tradingChain.getDemandId();
			Demand demand = demandService.getDemandByKey(demandId);

			// 查询贸易商信息
			TradingChainDetailsVO tradingChainDetailsVO = TradingChainService.getTradingChainDetails(user, uuid);
			List<Demand> list = tradingChainDetailsVO.getDemandList();

			model.addObject("tradeChainDetailsVO", tradingChainDetailsVO);

			DemandRelevanter traderVO2 = tradingChainDetailsVO.getDemandRelevanterList2().get(1);

			//查询贸易商商信息
			DemandRelevanter traderVO1 = tradingChainDetailsVO.getDemandRelevanterList1().get(0);
			EnterpriseVo enterpriseVo2 = enterpriseService.getByMemberId(traderVO1.getEMemberId());

//			根据中英文环境放入企业的中英文名称
			String[][] enterpriseName2 = {new String[]{"zh",enterpriseVo2.getFullName()}, new String[]{"en",enterpriseVo2.getEnglishName()} };
			traderVO1.setEnterpriseName(VisitorLocale.getByCurrentLanguage(enterpriseName2));
			DemandRelevanterSupplierVO agent = new DemandRelevanterSupplierVO(traderVO1);
			model.addObject("agent", agent);

			// 贸易商
			model.addObject("traderVO1", traderVO1);
			model.addObject("traderVO2", traderVO2);

			// 草约单ID
			model.addObject("demandId1", tradingChainDetailsVO.getDemandList().get(0).getId());
			model.addObject("demandId2", tradingChainDetailsVO.getDemandList().get(1).getId());

			// 运输ID
			model.addObject("demandShipId1", tradingChainDetailsVO.getDemandShipList().get(0).getId());
			model.addObject("demandShipId2", tradingChainDetailsVO.getDemandShipList().get(1).getId());

			//查询代理商信息
			DemandRelevanter demandRelevanter3 = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeTwo(demandId, DemandRelevanterType.SUPPLIER.getCode());
			// 查询商家名称
			EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(demandRelevanter3.getEMemberId());
//			根据中英文环境放入企业的中英文名称
			String[][] enterpriseName = {new String[]{"zh",enterpriseVo.getFullName()}, new String[]{"en",enterpriseVo.getEnglishName()} };
			demandRelevanter3.setEnterpriseName(VisitorLocale.getByCurrentLanguage(enterpriseName));
			DemandRelevanterSupplierVO seller = new DemandRelevanterSupplierVO(demandRelevanter3);
			model.addObject("demandRelevanter3", seller);

			//查询买家信息
			DemandRelevanter demandRelevanter1 = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(demandId, DemandRelevanterType.BUYER.getCode());
			// 查询商家名称
			EnterpriseVo enterpriseVo1 = enterpriseService.getByMemberId(demandRelevanter3.getEMemberId());
//			根据中英文环境放入企业的中英文名称
			String[][] enterpriseName1 = {new String[]{"zh",enterpriseVo1.getFullName()}, new String[]{"en",enterpriseVo1.getEnglishName()} };
			demandRelevanter1.setEnterpriseName(VisitorLocale.getByCurrentLanguage(enterpriseName1));
			DemandRelevanterBuyerVO buyer = new DemandRelevanterBuyerVO(demandRelevanter1);
			model.addObject("demandRelevanter1", buyer);

			//获取船务信息
			List<DemandShip> demandShips = demandShipService.getShipByDemandId(demandId);
			if (CollectionUtils.isNotEmpty(demandShips)) {
				DemandShip demandship = demandShips.get(0);
				DemandShipVO demandShipVO = new DemandShipVO();
				demandShipVO.setDischargePort(demandship.getDischargePort());
				
				if(StringUtils.isNotBlank(demandship.getDischargePort()) && DictUtils.getUnLoadPortMap().keySet().contains(demandship.getDischargePort())){
					String[][] unLoadPortValue = DictUtils.getUnLoadPortValue(demandship.getDischargePort());
					String unLoadPort = VisitorLocale.getByCurrentLanguage(unLoadPortValue);
					demandShipVO.setDischargePort(unLoadPort);
				}
				demandShipVO.setShipmentPort(demandship.getShipmentPort());
				demandShipVO.setShipmentStartTime(demandship.getShipmentStartTime());
				demandShipVO.setDischargeEndTime(demandship.getDischargeEndTime());
				demandShipVO.setShipmentEndTime(demandship.getShipmentEndTime());
				demandShipVO.setDischargeStartTime(demandship.getDischargeStartTime());
				model.addObject("demandShip", demandShipVO);
			}

			if (demand != null) {
				model.addObject("demand", DemandVO.convertToVO(demand));
				Date stopBidTime = demand.getStopBidTime();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(stopBidTime);
				model.addObject("dateString",dateString);
				model.addObject("belongToCurrentUser", user != null && user.getEpMemberId().equals(demand.getCreater()));
				model.addObject("overdue", demand.getStopBidTime() != null && DateUtil.getToday().getTime().after(demand.getStopBidTime()));
			}

			model.addObject("uuid", tradingChain.getUuid());
			// 修改贸易链按钮操作失败提示
			if(msg != null){
				model.addObject("msg",msg);
				return model;
			}

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			ex.printStackTrace();

			// 错误信息
			model.addObject("msg",VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0176));
			// 返回列表页
			model.setViewName("redirect:/"+"tradeChain/tradeChainList.htm");
		}

		return model;
	}

	/**
	 * 创建贸易链
	 * @return
	 */
	@RolesAccess({MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER})
	@RequestMapping(value = UrlMapping.TRADECHAIN_INSERTTRADECHAIN)
	public ModelAndView insertTradeChain(ModelMap modelMap, TradeChainForm tradeChainFrom, CurrentUser user){
		ModelAndView model = new ModelAndView();
		try {
			// 表单校验长度
			Long currentEpmemberId = user.getEpMemberId();
//			this.validTradeChainForm(tradeChainFrom, currentEpmemberId);
			String status = tradeChainFrom.getStatus();
			TradingChain tradingChain = new TradingChain();

			// 提交
			if(status.equals("1")) {
				tradingChain = TradingChainService.saveTradingChain(user, tradeChainFrom);
				String uuid = tradingChain.getUuid();
				Integer sta = TradingChainStatus.PUBLISHED.getCode();
				TradingChainService.alterTradingChainStatus(user, uuid, sta);
			}

			// 保存
			if(status.equals("2")) {
				TradingChainService.saveTradingChain(user, tradeChainFrom);
			}

			model.setViewName("redirect:/tradeChain/tradeChainList.htm");
		}catch (BizException e){

			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
			model.addObject("demandId",tradeChainFrom.getBiddingId());

			// 错误信息
			model.addObject("msg",e.getMessage());
			// 返回列表页
			model.setViewName("redirect:/tradeChain/toTradeChainInsert.htm");

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
			model.addObject("demandId",tradeChainFrom.getBiddingId());
			// 错误信息
			model.addObject("msg",VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0177));
			// 返回列表页
			model.setViewName("redirect:/tradeChain/toTradeChainInsert.htm");
		}
		return model;
	}

	/**
	 * 修改贸易链
	 * @return
	 */
	@RolesAccess({MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER})
	@RequestMapping(value = UrlMapping.TRADECHAIN_UPDATETRADECHAIN)
	public ModelAndView updateTradeChain(ModelMap modelMap, TradeChainForm tradeChainFrom, CurrentUser user){
		ModelAndView model = new ModelAndView();
		try {
			// 表单校验长度
			Long currentEpmemberId = user.getEpMemberId();
			this.validTradeChainForm(tradeChainFrom, currentEpmemberId);

			String uuid = tradeChainFrom.getUuid();
			if (StringUtil.isEmpty(uuid)) {
				throw new BizException();
			}

			/*List<TradeChainDemandVO> demandList = tradeChainFrom.getBiddingList();
			for (TradeChainDemandVO tradeChainDemandVO : demandList) {
				DemandVO demand = tradeChainDemandVO.getBidding();
				// 数量桶
				Long num = demand.getNum();
				// 升贴水
				Long agio =	demand.getAgio();
				if (num != null) {
					demand.setNum(num * 1000);
				}
				if(agio != null){
					demand.setAgio(agio * 1000);
				}
			}*/

			TradingChainService.updateTradingChain(user, tradeChainFrom,uuid);
			model.setViewName("redirect:/tradeChain/tradeChainList.htm");
		} catch (BizException e){
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
			// 错误信息
			model.addObject("msg", e.getMessage());
			model.addObject("uuid",tradeChainFrom.getBiddingId());
			// 返回列表页
			model.setViewName("redirect:/"+"tradeChain/toTradeChainEdit.htm");

		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			// 错误信息
			model.addObject("msg", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0178));
			model.addObject("uuid",tradeChainFrom.getBiddingId());
			// 返回列表页
			model.setViewName("redirect:/"+"tradeChain/toTradeChainEdit.htm");

			e.printStackTrace();
		}
		return model;
	}

	/**
	 * 后台验证,贸易链表单校验长度和必填
	 * @param tradeChainFrom
	 * @param currentEpmemberId
	 * @throws BizException
	 */
	private void validTradeChainForm(TradeChainForm tradeChainFrom, Long currentEpmemberId) throws Exception {

		List<TradeChainDemandVO> list = tradeChainFrom.getBiddingList();
		for (TradeChainDemandVO biddingDemand :list) {
			// 报价单长度校验和必填项
			if(biddingDemand != null) {
				String nums = biddingDemand.getBidding().getNumStr();					//数量（桶）
				String numfloat = biddingDemand.getBidding().getNumfloat();			//溢短装
				Integer tradeItem = biddingDemand.getBidding().getTradeItem();		//贸易条款
				BigDecimal agio = biddingDemand.getBidding().getAgioStr();			//升贴水
				String valuationBase = biddingDemand.getBidding().getValuationBase();				//计价基准
				String valuationFormula = biddingDemand.getBidding().getValuationFormula();			//计价公式
				String valuationFormulaJson = biddingDemand.getBidding().getValuationFormulaJson();	//计价公式Json
				String payItem = biddingDemand.getBidding().getPayItem();							//付款日期
				String payItemJSON = biddingDemand.getBidding().getPayItemJSON();					//付款日期
				Integer authItem = biddingDemand.getBidding().getAuthItem();							//信用条款

				if (nums != null && nums.length() > 12) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0135));
				}

				if (nums == null && nums.equals("")) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0158));
				}

				if (numfloat != null && numfloat.length() > 12) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0136));
				}

				if (numfloat == null && numfloat.equals("")) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0159));
				}

				if (tradeItem != null && tradeItem.toString().length() > 12) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0137));
				}

				if (tradeItem == null && tradeItem.equals("")) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0160));
				}

				if (agio != null && agio.toString().length() > 12) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0138));
				}
				if (agio == null && agio.equals("")) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0161));
				}

				if (valuationBase != null && valuationBase.length() > 100) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0139));
				}

				if (valuationBase == null && valuationBase.equals("")) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0162));
				}

				if (valuationFormula != null && valuationFormula.length() > 1000) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0141));
				}

				if (valuationFormula == null && valuationFormula.equals("")) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0164));
				}

				if (valuationFormulaJson != null && valuationFormulaJson.length() > 1000) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0141));
				}

				if (valuationFormulaJson == null && valuationFormulaJson.equals("")) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0164));
				}

				if (payItem != null && payItem.length() > 32) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0142));
				}

				if (payItem == null && payItem.equals("")) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0165));
				}

				if (payItemJSON != null && payItemJSON.length() > 100) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0143));
				}

				if (payItemJSON == null && payItemJSON.equals("")) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0166));
				}

				if (authItem != null && authItem.toString().length() > 12) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0144));
				}

				if (authItem == null && authItem.equals("")) {
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0167));
				}
			} else {
				LOGGER.info("biddingForm.Bidding=============" + "null");
			}
		}
	}

	@RequestMapping(UrlMapping.TRADECHAIN_TRADETRAINALREADYEXISTS)
	public ModelAndView tradeChainAlreadyExists(Long demandId) {
		ModelAndView mav = new ModelAndView();
		mav.setView(new MappingJacksonJsonView());

		ResultDatas resultDatas = new ResultDatas();
		try {
			resultDatas.setDatas(tradeChainExists(demandId));
		} catch (Exception e) {
			resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0192));
		}

		mav.addAllObjects((Map<String, ?>) resultDatas.toMap());
		return mav;
	}

	private boolean tradeChainExists(Long demandId) throws BizException {

		List<TradingChain> tradingChainList = TradingChainService.selectByDemandId(demandId);

		boolean tradeChainExists = false;
		if (tradingChainList != null && tradingChainList.size() != 0){
			for (TradingChain tradingChain : tradingChainList) {
				if (!TradingChainStatus.CANCELLED.getCode().equals(tradingChain.getStatus())){
					tradeChainExists = true;
					break;
				}
			}
		}

		return tradeChainExists;
	}
}
