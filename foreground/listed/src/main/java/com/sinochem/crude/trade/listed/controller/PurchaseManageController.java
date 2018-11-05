package com.sinochem.crude.trade.listed.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.listed.constant.MsgConstant;
import com.sinochem.crude.trade.listed.constant.UrlMapping;
import com.sinochem.crude.trade.listed.domain.*;
import com.sinochem.crude.trade.listed.enums.*;
import com.sinochem.crude.trade.listed.model.vo.*;
import com.sinochem.crude.trade.listed.service.*;
import com.sinochem.crude.trade.member.constants.MemberConstants;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eyeieye.melody.util.DateUtil;
import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.helper.Base64Helper;
import com.sinochem.crude.trade.listed.helper.DictUtils;
import com.sinochem.crude.trade.listed.model.form.DemandForm;
import com.sinochem.crude.trade.listed.model.form.QueryForm;
import com.sinochem.crude.trade.listed.model.query.ResourceQuery;
import com.sinochem.crude.trade.listed.model.result.DemandJoinResult;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.remote.DemandOrderService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.member.access.RolesAccess;


/**
 * 采购管理控制层
 * Created by sijliu on 23/11/2017.
 */
@Controller
public class PurchaseManageController {
    private Logger LOGGER = LoggerFactory.getLogger(PurchaseManageController.class);

    @Autowired
    private URLBroker orderServer;

    @Autowired
    private DemandService demandService;

    @Autowired
    private DemandRelevanterService demandRelevanterService;

    @Autowired
    private CrudeOilResourceService crudeOilResourceService;

    @Autowired
    private DemandShipService demandShipService;

    @Autowired
    private DemandShipBerthService demandShipBerthService;

    @Autowired
    private ResourceManagerService resourceManagerService;

    @Autowired
    private DemandDetailService demandDetailService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired(required = false)
    private DemandOrderService demandOrderService;

    @Autowired
    private CrudeOilInfoService crudeOilInfoService;

    @Autowired
    private DemandBiddingHistoryService demandBiddingHistoryService;

	@Autowired
	private DemandMessageService demandMessageService;

	@Autowired
	private TradingChainService tradingChainService;
    
    /**
     * 我发布的报价列表页面
     * @param user 当前用户
     * @param model
     * @return
     */
	@RolesAccess({MemberConstants.SALES_TRADER})
    @RequestMapping(value = UrlMapping.PURCHASEMANAGER_BIDDINGLIST)
    public String biddingList(@ModelAttribute("queryForm") QueryForm queryForm, PageInfo pageInfo, CurrentUser user, ModelMap model){
        try{
			ResourceQuery resourceQuery = new ResourceQuery();
			resourceQuery.setBizType(Constant.BIZ_TYPE_CRUDEOIL);
			resourceQuery.setType(DemandType.BIDDING.getCode());//报价
			resourceQuery.setDealType(DealType.SELL.getCode());//卖
			// 我的报价单  在能看到自己报价的单子
			resourceQuery.setUserId(user.getEpMemberId());
			resourceQuery.setPubDateStart(queryForm.getPubDateStart());
			resourceQuery.setPubDateEnd(queryForm.getPubDateEnd());
			resourceQuery.setCreateTimeStart(queryForm.getCreateTimeStart());
			resourceQuery.setCreateTimeEnd(queryForm.getCreateTimeEnd());
			resourceQuery.setPayItem(queryForm.getPayItem());
			resourceQuery.setDemandCode(queryForm.getDemandCode());
			PageInfoResult pageInfoResult = demandService.queryDemandBiddingJoinTableByCondition(resourceQuery, pageInfo);
			List<DemandJoinResult> list = pageInfoResult.getList();
			List<BiddingListVO> VOlist = new ArrayList<>();
			for(DemandJoinResult demandJoinResult : list){
				VOlist.add(BiddingListVO.convertToVO(demandJoinResult));
			}
			pageInfoResult.setList(VOlist);
			model.addAttribute("pageInfoList", pageInfoResult);
			Map<Object, String> payItemMap = DictUtils.getPayItemMap();
			model.addAttribute("payItemMap", payItemMap);
			return "/buyerCenter/biddingList";
		}catch (Exception e){
        	LOGGER.error("我发布的报价列表页面失败");
        	LOGGER.error("",e);
			return "/buyerCenter/biddingList";
		}
    }

    /**
     * 需求对应的报价列表页面
     * @param user 当前用户
     * @param model
     * @return
     */
	@RolesAccess({MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.PURCHASEMANAGER_DEMANDBIDDINGLIST)
    public String demandBiddingList(CurrentUser user, ModelMap model, Long demandId){
        model.put("user", user);
        model.put("demandId", demandId);
        return "/buyerCenter/demandBiddingList";
    }

    /**
     * 采购需求列表页面
     * @param user 当前用户
     * @param model
     * @return
     */
	@RolesAccess({MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.PURCHASEMANAGER_BUYINGLEADSDEMANDLIST)
    public String buyingLeadsDemandList(@ModelAttribute("queryForm") QueryForm queryForm, PageInfo pageInfo, CurrentUser user, ModelMap model){
       try{
		   model.addAttribute("user", user);
		   ResourceQuery resourceQuery = new ResourceQuery();
		   resourceQuery.setBizType(Constant.BIZ_TYPE_CRUDEOIL);
		   resourceQuery.setType(Constant.DEMAND_TYPE_D);//需求单
		   resourceQuery.setDealType(DealType.BUY.getCode());//买
		   resourceQuery.setUserEnterpriseId(user.getEpMemberId());
		   resourceQuery.setPubDateStart(queryForm.getPubDateStart());
		   resourceQuery.setPubDateEnd(queryForm.getPubDateEnd());
		   resourceQuery.setPayItem(queryForm.getPayItem());
		   resourceQuery.setDemandCode(queryForm.getDemandCode());
		   PageInfoResult pageInfoResult = demandService.queryDemandJoinTableByCondition(resourceQuery, pageInfo);
		   List<DemandJoinResult> list = pageInfoResult.getList();
		   List<DemandListVO> VOlist = new ArrayList<>();
		   for(DemandJoinResult demandJoinResult : list){
			   VOlist.add(DemandListVO.convertToVO(demandJoinResult));
		   }
		   pageInfoResult.setList(VOlist);
		   model.addAttribute("pageInfoList", pageInfoResult);
		   Map<Object, String> payItemMap = DictUtils.getPayItemMap();
		   model.addAttribute("payItemMap", payItemMap);
		   return "/buyerCenter/buyingLeadsDemandList";
	   }catch (Exception e){
       		LOGGER.error("",e);
		   return "/buyerCenter/buyingLeadsDemandList";
	   }
    }

    /**
     * 报价详情页面
     * @param user 当前用户
     * @param model
     * @param demandId 报价单id
     * @return
     */
	@RolesAccess({MemberConstants.SALES_TRADER,MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.PURCHASEMANAGER_BIDDINGDETAIL)
    public String biddingDetail(CurrentUser user, ModelMap model,
                                Long demandId) {
        try {
            Demand demand = demandService.getDemandByKey(demandId);
            if (demand != null) {
                model.addAttribute("demand", DemandVO.convertToVO(demand));
                model.addAttribute("belongToCurrentUser",
                        user != null && user.getEpMemberId().equals(demand.getCreater()));
                model.addAttribute("overdue",
                        demand.getStopBidTime() != null && DateUtil.getToday().getTime().after(demand.getStopBidTime()));

                Demand parentDemand = demandService.getDemandByKey(demand.getParentId());
				model.addAttribute("parentDemandCreater", parentDemand.getCreater());
				model.addAttribute("creater", demand.getCreater());
				model.addAttribute("user", user);
	            // 修改记录
	            List<DemandBiddingHistory> history = demandBiddingHistoryService.getDetailByDemandId(demandId);
	            model.addAttribute("biddingHistory", DemandBiddingHistoryVO.convertListToVo(history));
            }
        } catch (Exception ex) {
			LOGGER.error("报价详情页面失败");
            LOGGER.error("",ex);
        }

        return "/buyerCenter/biddingDetail";
    }

    /**
     * 报价修改页面
     * @param user 当前用户
     * @param demandId 报价单id
     * @return
     */
	@RolesAccess({MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.PURCHASEMANAGER_BUYBIDDINGEDIT)
    public String buyBiddingEdit(CurrentUser user, ModelMap modelMap, Long demandId, String bizType, Integer oilSaleFlag) {
    	DemandPageVO demandForm = new DemandPageVO();
    	Demand demand = null;
    	
    	try {
	        demand = demandService.getDemandByKey(demandId);
	        
	        if (demand != null) {
	        	// 基本信息
	            demandForm.setDemand(DemandVO.convertToVO(demand));
	            
	            if(bizType.equals(Constant.BIZ_TYPE_CRUDEOIL)) {
		            // 油种信息
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
		            demandForm.setCrudeOil(crudeOilListVO);
	            }
	            
	            // 泊位信息
	            List<DemandShipBerth> demandShipBerthList = demandShipBerthService.getShipBerthByDemandId(demandId);
	            List<DemandShipBerthVO> demandShipBerthVOList = DemandShipBerthVO.convertListToVo(demandShipBerthList);
	            demandForm.setDemandShipBerthList(demandShipBerthVOList);
	            
	            // 运输信息
	            List<DemandShip> demandShips = demandShipService.getShipByDemandId(demandId);
	            demandForm.setShip(DemandShipVO.convertDomainToVo(demandShips.get(0)));
	            
	            // 供应商信息
	            DemandRelevanter provider = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeTwo(demandId, DemandRelevanterType.SUPPLIER.getCode());
	            demandForm.setProvider(new DemandRelevanterSupplierVO(provider));
	        
	            // 修改记录
	            List<DemandBiddingHistory> history = demandBiddingHistoryService.getDetailByDemandId(demandId);
	            demandForm.setBiddingHistory(DemandBiddingHistoryVO.convertListToVo(history));
	            
	            // 判断当前报价单是否已结标
	            demandForm.setStopBid(demandService.checkStopBid(demandId));
	        }
		} catch (BizException e) {
			LOGGER.error("报价修改页面失败");
			LOGGER.error("",e);
			modelMap.addAttribute("errorMessage", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0084));
            return "/buyerCenter/biddingEdit/buyBiddingEdit";
		}
        
    	demandForm.setBizType(bizType);
    	modelMap.put("demandForm", demandForm);
    	modelMap.put("oilSaleFlag", oilSaleFlag);
        
    	Map<String,Map<Object, String>> dictMap = new HashMap<String,Map<Object, String>>();
    	DictUtils.initDictMap(dictMap);
    	modelMap.put("dictMap", dictMap);
        
        return "buyerCenter/biddingEdit/buyBiddingEdit";
    }
    
    /**
     * 保存报价信息
     */
	@RolesAccess({MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.PURCHASEMANAGER_BUYBIDDINGSAVE, method = RequestMethod.POST)
    public String buyBiddingSave(@ModelAttribute("demandForm") DemandPageVO demandForm, ModelMap modelMap, CurrentUser user){
    	Demand demand = null;
    	DemandShip ship = null;
    	
        try{
        	// 验证从页面过来的报价数据
        	validBiddingForm(demandForm, user.getEpMemberId());
        	
        	if(demandService.checkStopBid(demandForm.getDemand().getId()) == 1) {
        		throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0085));
        	}
        	
        	// vo to domain
        	demand = DemandVO.convertToDomain(demandForm.getDemand());
        	demand.setNum(null);
        	ship = demandForm.getShip().convertVoToDomain();
        	
        	// 保存修改记录
        	saveHistory(demand, ship, user);
        	
        	// 商品信息
        	demandService.saveOrUpdateDemand(demand);
        	
        	// 运输信息
        	ship.setUpdater(user.getMemberId());
        	demandShipService.updateBiddingDemandShip(ship);

        	//发送报价单修改消息
			demandMessageService.biddingUpdate(demand.getId());

        } catch (BizException e){
			LOGGER.error("保存报价信息异常");
            LOGGER.error("",e);
        	Map<String,Map<Object, String>> dictMap = new HashMap<String,Map<Object, String>>();
        	DictUtils.initDictMap(dictMap);
        	modelMap.put("dictMap", dictMap);
            
        	demandForm.setStopBid(0);
        	modelMap.put("demandForm", demandForm);
        	
        	modelMap.put("errorMessage", e.getMessage());
            modelMap.put("layout", "layout/buyerCenter/default.vm");
            return "buyerCenter/biddingEdit/buyBiddingEdit";
        } catch (Exception e){
			LOGGER.error("保存报价信息异常");
            LOGGER.error("",e);
        	Map<String,Map<Object, String>> dictMap = new HashMap<String,Map<Object, String>>();
        	DictUtils.initDictMap(dictMap);
        	modelMap.put("dictMap", dictMap);
            
        	demandForm.setStopBid(0);
        	modelMap.put("demandForm", demandForm);
        	
        	modelMap.put("errorMessage", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0014));
            modelMap.put("layout", "layout/buyerCenter/default.vm");
            return "buyerCenter/biddingEdit/buyBiddingEdit";
        }

        if(demand.getBizType().equals(Constant.BIZ_TYPE_CRUDEOIL)) {
            return "redirect:/oilSaleBidding/biddingList.htm";
        } else {
        	return "redirect:/resourceManager/myBiddinglist.htm";
        }
    }
    
    /**
     * 报价修改页面
     * @param user 当前用户
     * @param demandId 报价单id
     * @return
     */
	@RolesAccess({MemberConstants.SALES_TRADER})
    @RequestMapping(value = UrlMapping.PURCHASEMANAGER_SELLBIDDINGEDIT)
    public String sellBiddingEdit(CurrentUser user, ModelMap modelMap, Long demandId, String bizType, Integer oilSaleFlag) {
    	DemandPageVO demandForm = new DemandPageVO();
    	Demand demand = null;
    	
    	try {
	        demand = demandService.getDemandByKey(demandId);
	        
	        if (demand != null) {
	        	// 基本信息
	            demandForm.setDemand(DemandVO.convertToVO(demand));
	            
	            if(bizType.equals(Constant.BIZ_TYPE_CRUDEOIL)) {
		            // 油种信息
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
		            demandForm.setCrudeOil(crudeOilListVO);
	            }
	            
	            // 泊位信息
	            List<DemandShipBerth> demandShipBerthList = demandShipBerthService.getShipBerthByDemandId(demandId);
	            List<DemandShipBerthVO> demandShipBerthVOList = DemandShipBerthVO.convertListToVo(demandShipBerthList);
	            demandForm.setDemandShipBerthList(demandShipBerthVOList);
	            
	            // 运输信息
	            List<DemandShip> demandShips = demandShipService.getShipByDemandId(demandId);
	            demandForm.setShip(DemandShipVO.convertDomainToVo(demandShips.get(0)));
	            
	            // 供应商信息
	            DemandRelevanter provider = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeTwo(demandId, DemandRelevanterType.SUPPLIER.getCode());
	            demandForm.setProvider(new DemandRelevanterSupplierVO(provider));
	        
	            // 修改记录
	            List<DemandBiddingHistory> history = demandBiddingHistoryService.getDetailByDemandId(demandId);
	            demandForm.setBiddingHistory(DemandBiddingHistoryVO.convertListToVo(history));
	            
	            // 判断当前报价单是否已结标
	            demandForm.setStopBid(demandService.checkStopBid(demandId));
	        }
		} catch (BizException e) {
			LOGGER.error("报价修改页面失败");
			LOGGER.error("",e);
			modelMap.addAttribute("errorMessage",VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0084));
            return "/buyerCenter/biddingEdit/sellBiddingEdit";
		}
        
    	demandForm.setBizType(bizType);
    	modelMap.put("demandForm", demandForm);
    	modelMap.put("oilSaleFlag", oilSaleFlag);
        
    	Map<String,Map<Object, String>> dictMap = new HashMap<String,Map<Object, String>>();
    	DictUtils.initDictMap(dictMap);
    	modelMap.put("dictMap", dictMap);
        
        return "buyerCenter/biddingEdit/sellBiddingEdit";
    }
    
    /**
     * 保存报价信息
     */
	@RolesAccess({MemberConstants.SALES_TRADER})
    @RequestMapping(value = UrlMapping.PURCHASEMANAGER_SELLBIDDINGSAVE, method = RequestMethod.POST)
    public String sellBiddingSave(@ModelAttribute("demandForm") DemandPageVO demandForm, ModelMap modelMap, CurrentUser user){
    	Demand demand = null;
    	DemandShip ship = null;
    	
        try {
        	// 验证从页面过来的报价数据
        	validBiddingForm(demandForm, user.getEpMemberId());
        	
        	if(demandService.checkStopBid(demandForm.getDemand().getId()) == 1) {
        		throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0085));
        	}
        	
        	// vo to domain
        	demand = DemandVO.convertToDomain(demandForm.getDemand());
        	demand.setNum(null);
        	ship = demandForm.getShip().convertVoToDomain();
        	
        	// 保存修改记录
        	saveHistory(demand, ship, user);
        	
        	// 商品信息
        	demandService.saveOrUpdateDemand(demand);
        	
        	// 运输信息
        	ship.setUpdater(user.getMemberId());
        	demandShipService.updateBiddingDemandShip(ship);

        	//发送报价单修改消息
			demandMessageService.biddingUpdate(demand.getId());

        } catch (BizException e){
        	LOGGER.error("保存报价信息失败");
            LOGGER.error("",e);
        	Map<String,Map<Object, String>> dictMap = new HashMap<String,Map<Object, String>>();
        	DictUtils.initDictMap(dictMap);
        	modelMap.put("dictMap", dictMap);
            
        	demandForm.setStopBid(0);
        	modelMap.put("demandForm", demandForm);
        	
        	modelMap.put("errorMessage", e.getMessage());
            modelMap.put("layout", "layout/buyerCenter/default.vm");
            return "buyerCenter/biddingEdit/sellBiddingEdit";
        } catch (Exception e) {
        	LOGGER.error("保存报价信息失败");
            LOGGER.error("",e);
        	Map<String,Map<Object, String>> dictMap = new HashMap<String,Map<Object, String>>();
        	DictUtils.initDictMap(dictMap);
        	modelMap.put("dictMap", dictMap);
            
        	demandForm.setStopBid(0);
        	modelMap.put("demandForm", demandForm);
        	
        	modelMap.put("errorMessage", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0014));
            modelMap.put("layout", "layout/buyerCenter/default.vm");
            return "buyerCenter/biddingEdit/sellBiddingEdit";
        }

        if(demand.getBizType().equals(Constant.BIZ_TYPE_CRUDEOIL)) {
            return "redirect:/purchaseManager/biddingList.htm";
        } else {
        	return "redirect:/resourceManager/myBiddinglist.htm";
        }
    }
	
    private void saveHistory(Demand demand, DemandShip ship, CurrentUser user) throws BizException {
    	List<DemandBiddingHistory> historyList = new ArrayList<DemandBiddingHistory>();
    	Demand demandOld = demandService.getDemandByKey(demand.getId());
    	List<DemandShip> demandShips = demandShipService.getShipByDemandId(demand.getId());
    	
    	// 数量（桶）
    	if(!String.valueOf(demandOld.getNum()).equals(String.valueOf(demand.getNum()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("数量（桶）");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0001));
    		h.setValueOld(new BigDecimal(demandOld.getNum()).divide(new BigDecimal(1000)).toString());
    		h.setValueNew(new BigDecimal(demand.getNum()).divide(new BigDecimal(1000)).toString());
    		historyList.add(h);
    	}
    	
    	// 溢短装
    	if(!String.valueOf(demandOld.getNumfloat()).equals(String.valueOf(demand.getNumfloat()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("溢短装");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0002));
    		h.setValueOld(String.valueOf(demandOld.getNumfloat()));
    		h.setValueNew(String.valueOf(demand.getNumfloat()));
    		historyList.add(h);
    	}   	
    	
    	// 贸易条款
    	if(!String.valueOf(demandOld.getTradeItem()).equals(String.valueOf(demand.getTradeItem()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("贸易条款");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0003));
    		h.setValueOld(DictUtils.getTradeItemMap().get(demandOld.getTradeItem()));
    		h.setValueNew(DictUtils.getTradeItemMap().get(demand.getTradeItem()));
    		historyList.add(h);
    	}
    	
    	// 升贴水
    	if(!String.valueOf(demandOld.getAgio()).equals(String.valueOf(demand.getAgio()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("升贴水");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0004));
    		h.setValueOld(new BigDecimal(demandOld.getAgio()).divide(new BigDecimal(1000)).toString());
    		h.setValueNew(new BigDecimal(demand.getAgio()).divide(new BigDecimal(1000)).toString());
    		historyList.add(h);
    	}
    	
    	// 计价基准
    	if(!String.valueOf(demandOld.getValuationBase()).equals(String.valueOf(demand.getValuationBase()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("计价基准");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0005));
    		h.setValueOld(DictUtils.getValuationBaseMap().get(demandOld.getValuationBase()));
    		h.setValueNew(DictUtils.getValuationBaseMap().get(demand.getValuationBase()));
    		historyList.add(h);
    	}
    	
    	// 计价期
    	if(!String.valueOf(demandOld.getValuationProidType()).equals(String.valueOf(demand.getValuationProidType()))
    		|| (demandOld.getValuationProidType() == 4 && !String.valueOf(demandOld.getValuationProidStart()).equals(String.valueOf(demand.getValuationProidStart())))
    		|| (demandOld.getValuationProidType() == 4 && !String.valueOf(demandOld.getValuationProidEnd()).equals(String.valueOf(demand.getValuationProidEnd())))
    		|| (demandOld.getValuationProidType() == 5 && !String.valueOf(demandOld.getContractKind()).equals(String.valueOf(demand.getContractKind())))
    			) {
    		String valueOld = DictUtils.getValuationProidTypeMap().get(demandOld.getValuationProidType());
    		String valueNew = DictUtils.getValuationProidTypeMap().get(demand.getValuationProidType());
    		
    		if(demandOld.getValuationProidType() == 4) {
    			valueOld += " （" 
    					+ DateUtil.convertDateToString("yyyy/MM/dd", demandOld.getValuationProidStart())
    					+ " - "
    					+ DateUtil.convertDateToString("yyyy/MM/dd", demandOld.getValuationProidEnd())
    					+ "）";
    		}
    		if(demandOld.getValuationProidType() == 5) {
    			valueOld += " （" 
    					+ demandOld.getContractKind()
    					+ "）";
    		}
    		
    		if(demand.getValuationProidType() == 4) {
    			valueNew += " （" 
    					+ DateUtil.convertDateToString("yyyy/MM/dd", demand.getValuationProidStart())
    					+ " - "
    					+ DateUtil.convertDateToString("yyyy/MM/dd", demand.getValuationProidEnd())
    					+ "）";
    		}
    		if(demand.getValuationProidType() == 5) {
    			valueNew += " （" 
    					+ demand.getContractKind()
    					+ "）";
    		}
    		
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("计价期");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0006));
    		h.setValueOld(valueOld);
    		h.setValueNew(valueNew);
    		historyList.add(h);
    	}

    	// 计价公式
    	if(!String.valueOf(demandOld.getValuationFormula()).equals(String.valueOf(demand.getValuationFormula()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("计价公式");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0007));
    		h.setValueOld(String.valueOf(demandOld.getValuationFormula()));
    		h.setValueNew(String.valueOf(demand.getValuationFormula()));
    		historyList.add(h);
    	}
    	
    	// 付款日期
    	if(!String.valueOf(demandOld.getPayItem()).equals(String.valueOf(demand.getPayItem()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("付款日期");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0008));
    		h.setValueOld(demandOld.getPayItem());
    		h.setValueNew(demand.getPayItem());
    		historyList.add(h);
    	}
    	
    	// 信用条款
    	if(!String.valueOf(demandOld.getAuthItem()).equals(String.valueOf(demand.getAuthItem()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("信用条款");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0009));
    		h.setValueOld(DictUtils.getCreditItem().get(demandOld.getAuthItem()));
    		h.setValueNew(DictUtils.getCreditItem().get(demand.getAuthItem()));
    		historyList.add(h);
    	}
    	
    	// 结算量标准
    	if(!String.valueOf(demandOld.getMeasureMode()).equals(String.valueOf(demand.getMeasureMode()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("结算量标准");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0010));
    		h.setValueOld(DictUtils.getMeasureModeMap().get(demandOld.getMeasureMode()));
    		h.setValueNew(DictUtils.getMeasureModeMap().get(demand.getMeasureMode()));
    		historyList.add(h);
    	}
    	
    	// 报价有效期
    	if(!String.valueOf(demandOld.getStopBidTime()).equals(String.valueOf(demand.getStopBidTime()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("报价有效期");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0011));
    		h.setValueOld(DateUtil.convertDateToString("yyyy-MM-dd", demandOld.getStopBidTime()));
    		h.setValueNew(DateUtil.convertDateToString("yyyy-MM-dd", demand.getStopBidTime()));
    		historyList.add(h);
    	}
    	
    	// 装卸允许时间
    	if(!String.valueOf(demandOld.getLoadAndDischargePermittedTimespan()).equals(String.valueOf(demand.getLoadAndDischargePermittedTimespan()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("装卸允许时间");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0012));
    		h.setValueOld(String.valueOf(demandOld.getLoadAndDischargePermittedTimespan()));
    		h.setValueNew(String.valueOf(demand.getLoadAndDischargePermittedTimespan()));
    		historyList.add(h);
    	}
    	
    	// 商检费分摊
    	if(!String.valueOf(demandOld.getInspectionFeeSharingType()).equals(String.valueOf(demand.getInspectionFeeSharingType()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("商检费分摊");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0013));
    		h.setValueOld(DictUtils.getInspectionFeeSharingTypeMap().get(demandOld.getInspectionFeeSharingType()));
    		h.setValueNew(DictUtils.getInspectionFeeSharingTypeMap().get(demand.getInspectionFeeSharingType()));
    		historyList.add(h);
    	}
    	
    	// 法律
    	if(!String.valueOf(demandOld.getLaw()).equals(String.valueOf(demand.getLaw()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("法律");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0014));
    		h.setValueOld(String.valueOf(demandOld.getLaw()));
    		h.setValueNew(String.valueOf(demand.getLaw()));
    		historyList.add(h);
    	}
    	
    	// GTC
    	if(!String.valueOf(demandOld.getGtc()).equals(String.valueOf(demand.getGtc()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("GTC");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0015));
    		h.setValueOld(String.valueOf(demandOld.getGtc()));
    		h.setValueNew(String.valueOf(demand.getGtc()));
    		historyList.add(h);
    	}
    	
    	// 价格类型
    	/*if(!String.valueOf(demandOld.getPriceType()).equals(String.valueOf(demand.getPriceType()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		h.setItem("价格类型");
    		h.setValueOld(DictUtils.getPriceTypeMap().get(demandOld.getPriceType()));
    		h.setValueNew(DictUtils.getPriceTypeMap().get(demand.getPriceType()));
    		historyList.add(h);
    	}*/
    	
    	// 备注
    	if(!String.valueOf(demandOld.getRemark()).equals(String.valueOf(demand.getRemark()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("备注");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0016));
    		h.setValueOld(String.valueOf(demandOld.getRemark()));
    		h.setValueNew(String.valueOf(demand.getRemark()));
    		historyList.add(h);
    	}

    	// 装货港
    	if(!String.valueOf(demandShips.get(0).getShipmentPort()).equals(String.valueOf(ship.getShipmentPort()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("装货港");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0017));
    		h.setValueOld(String.valueOf(demandShips.get(0).getShipmentPort()));
    		h.setValueNew(String.valueOf(ship.getShipmentPort()));
    		historyList.add(h);
    	}
    	
    	// 装期
    	if(!String.valueOf(demandShips.get(0).getShipmentStartTime()).equals(String.valueOf(ship.getShipmentStartTime()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("装货期开始");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0018));
    		h.setValueOld(DateUtil.convertDateToString("yyyy-MM-dd", demandShips.get(0).getShipmentStartTime()));
    		h.setValueNew(DateUtil.convertDateToString("yyyy-MM-dd", ship.getShipmentStartTime()));
    		historyList.add(h);
    	}
    	if(!String.valueOf(demandShips.get(0).getShipmentEndTime()).equals(String.valueOf(ship.getShipmentEndTime()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("装货期结束");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0019));
    		h.setValueOld(DateUtil.convertDateToString("yyyy-MM-dd", demandShips.get(0).getShipmentEndTime()));
    		h.setValueNew(DateUtil.convertDateToString("yyyy-MM-dd", ship.getShipmentEndTime()));
    		historyList.add(h);
    	}
    	
    	// 卸货港
    	if(!String.valueOf(demandShips.get(0).getDischargePort()).equals(String.valueOf(ship.getDischargePort()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("卸货港");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0020));
    		h.setValueOld(String.valueOf(demandShips.get(0).getDischargePort()));
    		h.setValueNew(String.valueOf(ship.getDischargePort()));
    		historyList.add(h);
    	}
    	
    	// 卸货期
    	if(!String.valueOf(demandShips.get(0).getDischargeStartTime()).equals(String.valueOf(ship.getDischargeStartTime()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("到货期开始");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0021));
    		h.setValueOld(DateUtil.convertDateToString("yyyy-MM-dd", demandShips.get(0).getDischargeStartTime()));
    		h.setValueNew(DateUtil.convertDateToString("yyyy-MM-dd", ship.getDischargeStartTime()));
    		historyList.add(h);
    	}
    	if(!String.valueOf(demandShips.get(0).getDischargeEndTime()).equals(String.valueOf(ship.getDischargeEndTime()))) {
    		DemandBiddingHistory h = new DemandBiddingHistory();
    		//h.setItem("到货期结束");
    		h.setItem(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0022));
    		h.setValueOld(DateUtil.convertDateToString("yyyy-MM-dd", demandShips.get(0).getDischargeEndTime()));
    		h.setValueNew(DateUtil.convertDateToString("yyyy-MM-dd", ship.getDischargeEndTime()));
    		historyList.add(h);
    	}
    	
    	// 保存
    	for(DemandBiddingHistory h : historyList) {
    		h.setDemandId(demand.getId());
    		h.setUpdater(user.getMemberId());
    		if(StringUtil.isEmpty(h.getValueOld()) || "null".equals(h.getValueOld())) {
    			h.setValueOld("");
    		}
    		if(StringUtil.isEmpty(h.getValueNew()) || "null".equals(h.getValueNew())) {
    			h.setValueNew("");
    		}   		
    		demandBiddingHistoryService.insert(h);
    	}
    }

	/**
	 * 新增采购需求
	 * @param user
	 * @param modelMap
	 * @param demandId
	 * @param isCopy
	 * @return
	 * @throws BizException
	 */
	@RolesAccess({MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.PURCHASEMANAGER_NEWBUYINGLEADS)
    public String newBuyingLeads(CurrentUser user, ModelMap modelMap, Long demandId, String isCopy) throws BizException {
       modelMap.put("user", user);
        if (demandId != null) {
            DemandForm demandForm = new DemandForm();
            DemandVO demandVO = DemandVO.convertToVO(demandService.getDemandByKey(demandId));
            demandForm.setDemand(demandVO);
            
            DemandRelevanter demandRelevanterBuyer = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(demandId, DemandRelevanterType.BUYER.getCode());
            DemandRelevanterBuyerVO demandRelevanterBuyerVO = new DemandRelevanterBuyerVO(demandRelevanterBuyer);
            demandForm.setBuyerRelevanter(demandRelevanterBuyerVO);
            
            DemandRelevanter demandRelevanterAgent = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(demandId,DemandRelevanterType.AGENT.getCode());
    		DemandRelevanterAgentVO demandRelevanterAgentVO = new DemandRelevanterAgentVO(demandRelevanterAgent);
    		demandForm.setAgentRelevanter(demandRelevanterAgentVO);
            
    		List<CrudeOil> curdeOilList = crudeOilResourceService.getCrudeListDemandId(demandId);
    		List<CrudeOilVO> curdeOilVoList = CrudeOilVO.convertDomainToVoList(curdeOilList);
            demandForm.setCrudeOils(curdeOilVoList);
            
            List<DemandShip> ships = demandShipService.getShipByDemandId(demandId);
            demandForm.setDemandDetailList(DemandDetailVO.convertListToVo(demandDetailService.getDetailByDemandId(demandId)));
            if (CollectionUtils.isNotEmpty(ships)) {
                DemandShipVO dmandShipVO = DemandShipVO.convertDomainToVo(ships.get(0));
                demandForm.setDemandShip(dmandShipVO);
            }
            
            List<DemandShipBerth> demandShipBerthList = demandShipBerthService.getShipBerthByDemandId(demandId);
            List<DemandShipBerthVO> demandShipBerthVOList = DemandShipBerthVO.convertListToVo(demandShipBerthList);
            demandForm.setDemandShipBerthList(demandShipBerthVOList);
            
            // 复制功能需要把id清除
            if("1".equals(isCopy)) {
            	demandForm.getDemand().setId(null);
				demandForm.getDemand().setUuid(null);

                if(demandForm.getBuyerRelevanter() != null) {
                	demandForm.getBuyerRelevanter().setId(null);
                }
            	
                if(demandForm.getAgentRelevanter() != null) {
                	demandForm.getAgentRelevanter().setId(null);
                }
                
                if(demandForm.getDemandShip() != null) {
                	demandForm.getDemandShip().setId(null);
                }
                
                if(demandForm.getCrudeOils() != null) {
            		for(int i = 0; i < demandForm.getCrudeOils().size(); i++) {
            			demandForm.getCrudeOils().get(i).setId(null);
            		}
                }

                if(demandForm.getDemandShipBerthList() != null) {
            		for(int i = 0; i < demandForm.getDemandShipBerthList().size(); i++) {
            			demandForm.getDemandShipBerthList().get(i).setId(null);
            		}
                }
                
                demandForm.getBuyerRelevanter().setEnterpriseName(null);
                
            } else {
            	modelMap.put("demandId", demandId);
            }
            
            // 传值
            modelMap.put("demandForm", demandForm);
        }
        EnterpriseVo ev =  enterpriseService.getByMemberId(user.getEpMemberId());
        modelMap.put("enterprise", ev);
        int center =1;
        modelMap.put("personal",center);
        modelMap.put("contractKindMap",DictUtils.getContractKindMap());
        modelMap.put("layout","layout/buyerCenter/default.vm");
        return "crudeoilhallform/sheet";
    }

    /**
     * 采购需求详情页面
     * @param user 当前用户
     * @param modelMap
     * @param demandId 采购需求id
     * @return
     */
	@RolesAccess({MemberConstants.SALES_TRADER,MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.PURCHASEMANAGER_DEMANDDETAIL)
    public String demandDetail(CurrentUser user, ModelMap modelMap, Long demandId) {
        try {
            modelMap.addAttribute("demand", demandService.getDemandByKeyAndCurrentUser(demandId, user.getEpMemberId()));
            modelMap.put("originMap",DictUtils.getCrudeOilOrigin());
        } catch (BizException biz) {
            LOGGER.error("需求查看异常！");
            LOGGER.error("",biz);
        } catch (Exception e) {
            LOGGER.error("后台处理异常！");
			LOGGER.error("",e);
        }
        int center = 1;
        modelMap.put("personal",center);
        modelMap.put("layout","layout/buyerCenter/default.vm");
        return "/crudeoillobby/purchasingDemandDetail";
    }

    /**
     * 买家确认中标
     * @param model
     * @param biddingDemandId
     * @param remindFlag
     * @return
     */
	@RolesAccess({MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.PURCHASEMANAGER_BUY_CONFIRMBIDDINGANDREMIND)
    public String buyConfirmBiddingAndRemind(ModelMap model, Long biddingDemandId,
                                          String remindFlag, Integer biddingType, CurrentUser user){
        // 确认中标
        String orderNo = "";
		String errorMessage = "";

        try {
        	//在报价单确认中标之前需要判断本条报价单是否发起了贸易链
			//查询是否存在贸易链
			List<TradingChain> tradingChainList = tradingChainService.selectByDemandId(biddingDemandId);
			// 判断是否存在贸易链(不存在或者改贸易链的状态是已取消状态可以跳转界面)
//			需要循环查出来的贸易链表 判断是否可以继续发起贸易链
			boolean retryTradingChain = false;
			if(tradingChainList == null || tradingChainList.size() ==0){
				retryTradingChain = true;
			}else{
				for (TradingChain tradingChain :tradingChainList) {
					if(tradingChain.getStatus() != TradingChainStatus.CANCELLED.getCode()){
						retryTradingChain = false;
						break;
					}
					retryTradingChain = true;
				}
			}
			if(!retryTradingChain){
        		throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0189));
			}

            //意向报价
            if (DemandBiddingType.INTENTION_BIDDING.getCode().equals(biddingType)) {
                Long demandId = demandService.confirmIntentionBiddingAndRemind(biddingDemandId, user);
                return "redirect:/purchaseManager/demandBiddingList.htm?demandId=" + demandId;
            }

            //正式报价
            orderNo = demandService.confirmActualBiddingAndRemind(biddingDemandId, remindFlag, user);
            if (StringUtils.isEmpty(orderNo)) {
                LOGGER.error("确认中标异常！");
                errorMessage =  VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0086);
            }
        } catch (BizException e) {
            LOGGER.error("确认中标异常：", e);
			errorMessage =  VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0086); //由于订单执行抛出的都是BizException，包括诸如数据库连接异常等非业务异常，需要记录日志
        } catch (Exception e) {
            LOGGER.error("确认中标异常：", e);
            errorMessage =  VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0086);
        }
        if(StringUtil.isNotBlank(errorMessage)){
            model.put("errorMessage", errorMessage);
            // 操作失败
            return biddingDetail(user, model, biddingDemandId);
        }
        // 确认订单
        try {
            demandOrderService.confirmContract(orderNo, user.getMemberId(), null);
        } catch (BizException e) {
            LOGGER.error("确认订单异常：", e);
            errorMessage =  VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0087);
        } catch (Exception e) {
            LOGGER.error("确认订单异常：", e);
            errorMessage =  VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0087);
        }
        if(StringUtil.isBlank(errorMessage)){
            errorMessage = VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0088);
        }
        errorMessage = Base64Helper.encode(errorMessage.getBytes());
        // 操作成功
        return "redirect:" + orderServer.get("/skip/crudeOilInfoDetail.htm").put("orderNo", orderNo).put("errorMessage", errorMessage);
    }

	/**
	 * 卖家家确认中标
	 * @param model
	 * @param biddingDemandId
	 * @param remindFlag
	 * @return
	 */
	@RolesAccess({MemberConstants.SALES_TRADER})
	@RequestMapping(value = UrlMapping.PURCHASEMANAGER_SALES_CONFIRMBIDDINGANDREMIND)
	public String salesConfirmBiddingAndRemind(ModelMap model, Long biddingDemandId,
										  String remindFlag, Integer biddingType, CurrentUser user){
		// 确认中标
		String orderNo = "";
		String errorMessage = "";
		try {
			//意向报价
			if (DemandBiddingType.INTENTION_BIDDING.getCode().equals(biddingType)) {
				Long demandId = demandService.confirmIntentionBiddingAndRemind(biddingDemandId, user);
				return "redirect:/purchaseManager/demandBiddingList.htm?demandId=" + demandId;
			}

			//正式报价
			orderNo = demandService.confirmActualBiddingAndRemind(biddingDemandId, remindFlag, user);
			if (StringUtils.isEmpty(orderNo)) {
				LOGGER.error("确认中标异常！");
				errorMessage =  VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0086);;
			}
		} catch (BizException e) {
			LOGGER.error("确认中标异常：", e);
			errorMessage =  VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0086); //由于订单执行抛出的都是BizException，包括诸如数据库连接异常等非业务异常，需要记录日志
		} catch (Exception e) {
			LOGGER.error("确认中标异常：", e);
			errorMessage =  VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0086);
		}
		if(StringUtil.isNotBlank(errorMessage)){
			model.put("errorMessage", errorMessage);
			// 操作失败
			return biddingDetail(user, model, biddingDemandId);
		}
		// 确认订单
		try {
			demandOrderService.confirmContract(orderNo, user.getMemberId(), null);
		} catch (BizException e) {
			LOGGER.error("确认订单异常：", e);
			errorMessage =  VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0087);
		} catch (Exception e) {
			LOGGER.error("确认订单异常：", e);
			errorMessage =  VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0087);
		}
		if(StringUtil.isBlank(errorMessage)){
			errorMessage = VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0088);
		}
		errorMessage = Base64Helper.encode(errorMessage.getBytes());
		// 操作成功
		return "redirect:" + orderServer.get("/skip/crudeOilInfoDetail.htm").put("orderNo", orderNo).put("errorMessage", errorMessage);
	}

    /**
     * 报价单对比
     * @param contrastIds 页面传过来的报价单id
     * @return
     */
	@RolesAccess({MemberConstants.SALES_TRADER,MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.PURCHASEMANAGER_BIDDINGCONTRAST)
    public String biddingContrast(String contrastIds, ModelMap model){
        model.addAttribute("list", demandService.getDemandListByIds(contrastIds));
        return "/buyerCenter/demandBiddingContrast";
    }

    /**
     * 批量上架
     * @param ids 待上架资源id串
     * @return
     */
	@RolesAccess({MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.PURCHASEMANAGER_BATCHADDED)
    public Result batchAdded(String ids){
		Result result = new Result();
		try{
			// 批量上架
			return commBatchOpts(ids,"已发布",2);
		}catch (BizException e){
			LOGGER.error("批量上架失败{}",ids);
			LOGGER.error("",e);
			result.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0089));
			return result;
		}
    }

	/**
	 * 上下架
	 * @return
	 */
	private Result commBatchOpts(String ids,String message,Integer status) throws BizException{
		Result result = new Result();
		if(checkResource(ids,3)){
			result.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0067));
			return result;
		}
		//批量查看是否上架
		if(checkResource(ids,status)){
			if(status == 1){
				result.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0091));
			}
			if(status == 2){
				result.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0073));
			}
			return result;
		}
		//批量上架
		resourceManagerService.batchUpdateStatus(ids, status);
		result.setStatus(Result.SUCCESS);
		result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0092));
		return result;
	}

	private boolean checkResource(String ids, Integer status) throws BizException{
		String opts = resourceManagerService.batchUpdateStatused(ids, status);
		if(opts.length() > 0){
			return true;
		}
		return false;
	}

    /**
     * 批量下架
     * @param ids 待下架资源id串
     * @return
     */
	@RolesAccess({MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.PURCHASEMANAGER_BATCHUNDERCHARGED)
    public Result batchUnderCharged(String ids){
		Result result = new Result();
		try{
			// 批量下架
			return commBatchOpts(ids,"已下架",1);
		}catch (BizException e){
			LOGGER.error("批量下架失败{}",ids);
			LOGGER.error("",e);
			result.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0093));
			return result;
		}

    }

    /**
     * 待删除资源id串
     * @param ids
     * @return
     */
	@RolesAccess({MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.PURCHASEMANAGER_BATCHDELETE)
    public String batchDelete(String ids){
        try{
			// 批量删除
			resourceManagerService.batchUpdateStatus(ids, DemandStatus.DEMAND_STATUS_0.getCode());
			// 重定向到当前页面
			return "redirect:/purchaseManager/buyingLeadsDemandList.htm";
		}catch (BizException e){
        	LOGGER.error("待删除资源失败");
			LOGGER.error("待删除资源失败",e);
			return "redirect:/purchaseManager/buyingLeadsDemandList.htm";
		}
    }

    /**
     * 商品比较
     * @param demandIdsStr 比较Demand的ID，以逗号“,"分割
     * @param modelMap
     * @param bizType 比较类型，值为1或者""或者null时是成品油比较，其他情况是个人中心--我的成品油--【销售单对比、报价单对比】
     */
	@RolesAccess({MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER})
    @RequestMapping(value = UrlMapping.PURCHASEMANAGER_DEMANDCOMPARE)
    public String  demandCompare(@RequestParam(value="demands",required=false) String demandIdsStr, 
    							  @RequestParam(value="bizType",required=false) String bizType,
								  ModelMap modelMap, CurrentUser user) {
    	List<Long> demandIds = new ArrayList<Long>();
    	if(StringUtils.isNotBlank(demandIdsStr)){
    		String[] demandIdArr = demandIdsStr.split(",");
    		for(String demandId :demandIdArr){
    			demandIds.add(Long.valueOf(demandId));
    		}
    	}
    	
    	try {
			if(demandIds.size()>0){
				List<Demand> demands = this.demandService.getDemandsByIds(demandIds);
				modelMap.put("demandList", DemandVO.convertDomainToVOList(demands));

				List<DemandShipVO> demandship = new ArrayList<DemandShipVO>();
				List<DemandRelevanterBuyerVO> demandRelevanter = new ArrayList<DemandRelevanterBuyerVO>();
				List<CrudeOilVO> crudeOil = new ArrayList<CrudeOilVO>();

				for(int i = 0; i < demandIds.size(); i++) {
					List<DemandShip> ships = demandShipService.getShipByDemandId(demandIds.get(i));
					if(ships == null || ships.size() == 0) {
						demandship.add(new DemandShipVO());
					} else {
						demandship.add(DemandShipVO.convertDomainToVo(ships.get(0)));
					}

					DemandRelevanter relevanter = null;
					if(bizType.equals(Constant.BIZ_TYPE_CRUDEOIL)) {
						relevanter = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(demandIds.get(i), DemandRelevanterType.SUPPLIER.getCode());
					} else {
						relevanter = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeOne(demandIds.get(i), DemandRelevanterType.BUYER.getCode());
					}

					if(relevanter != null) {
						demandRelevanter.add(new DemandRelevanterBuyerVO(relevanter));
					} else {
						demandRelevanter.add(new DemandRelevanterBuyerVO());
					}

					// 原油需要API度等
					if(bizType.equals(Constant.BIZ_TYPE_CRUDEOIL)) {
						List<CrudeOil> curdeOilList = crudeOilResourceService.getCrudeListDemandId(demandIds.get(i));
						if(curdeOilList != null && curdeOilList.size() > 0) {
							crudeOil.add(CrudeOilVO.convertDomainToVo(curdeOilList.get(0)));
						} else {
							crudeOil.add(new CrudeOilVO());
						}
					}
				}
				modelMap.put("demandship", demandship);
				modelMap.put("demandRelevanter", demandRelevanter);
				modelMap.put("bizType", bizType);
				modelMap.put("crudeOil", crudeOil);
			}
		}catch (BizException e){
			LOGGER.error("商品比较失败");
    		LOGGER.error("",e);
		}

    	modelMap.put("productOilKindMap", DictUtils.getProductOilKind());
    	modelMap.put("productOilSpecsMap", DictUtils.getProductOilSpecs());
        modelMap.put("valuationProidTypeMap", DictUtils.getValuationProidTypeMap());
        modelMap.put("tradeItemMap",DictUtils.getTradeItemMap());
        modelMap.put("payItemMap", DictUtils.getPayItemMap());
        modelMap.put("purchaseModeMap", DictUtils.getPurchaseModes());
        modelMap.put("valuationBaseMap", DictUtils.getValuationBaseMap());
        modelMap.put("authItemBaseMap", DictUtils.getCreditItem());
        modelMap.put("measureModeMap", DictUtils.getMeasureModeMap());

    	if(bizType.equals(Constant.BIZ_TYPE_CRUDEOIL)) {
    		return "buyerCenter/buyerCompare";
    	} else {
    		return "salerCenter/salerCompare";
    	}
    }

	/**
	 * 延期
	 * @param demandId
	 * @param stopBidTime
	 * @return
	 */
	@RolesAccess({MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.PURCHASEMANAGER_POSTPONE)
    @ResponseBody
    public Result postpone(String demandId, String stopBidTime) {
        Result result = null;
        try {
            result = demandService.postpone(demandId, stopBidTime);
        } catch (BizException e) {
            LOGGER.error(e.getMessage());
        } catch (Exception e1) {
            LOGGER.error(e1.getMessage());
            result = new Result();
            result.setCode("ERROR");
            result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0094));
        }

        return result;
    }
	
	/**
     * 后台验证原油报价
     * @param biddingForm
     * @param currentEpmemberId
     * @throws BizException
     */
    private void validBiddingForm(DemandPageVO biddingForm, Long currentEpmemberId) throws Exception {

        DemandVO biddingDemand = biddingForm.getDemand();
        DemandShipVO demandShip = biddingForm.getShip();
     
        // 报价单长度校验
        if(biddingDemand != null) {
    		String num = biddingDemand.getNumStr();					//数量（桶）
    		String numfloat = biddingDemand.getNumfloat();			//溢短装
    		Integer tradeItem = biddingDemand.getTradeItem();		//贸易条款
    		BigDecimal agio = biddingDemand.getAgioStr();			//升贴水
    		String valuationBase = biddingDemand.getValuationBase();				//计价基准
    		Integer valuationProidType = biddingDemand.getValuationProidType();		//计价期
    		String valuationFormula = biddingDemand.getValuationFormula();			//计价公式
    		String valuationFormulaJson = biddingDemand.getValuationFormulaJson();	//计价公式Json
    		String payItem = biddingDemand.getPayItem();							//付款日期
    		String payItemJSON = biddingDemand.getPayItemJSON();					//付款日期
    		Integer authItem = biddingDemand.getAuthItem();							//信用条款
    		Integer measureMode = biddingDemand.getMeasureMode();					// 结算量标准
    		Date stopBidTime = biddingDemand.getStopBidTime();						//报价有效期
    		String loadAndDischargePermittedTimespan = biddingDemand.getLoadAndDischargePermittedTimespan();	//装卸允许时间
    		String law = biddingDemand.getLaw();									//法律
    		String gtc = biddingDemand.getGtc();									//GTC
    		String remark = biddingDemand.getRemark();								//备注
    		
    		if (num != null && num.length() > 12) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0135));
    		}
    		
    		if (numfloat != null && numfloat.length() > 12) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0136));
    		}
    		
    		if (tradeItem != null && tradeItem.toString().length() > 12) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0137));
    		}
    		
    		if (agio != null && agio.toString().length() > 12) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0138));
    		}
    		
    		if (valuationBase != null && valuationBase.length() > 100) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0139));
    		}
    		
    		if (valuationProidType != null && valuationProidType.toString().length() > 11) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0140));
    		}
    		
    		if (valuationFormula != null && valuationFormula.length() > 1000) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0141));
    		}
    		
    		if (valuationFormulaJson != null && valuationFormulaJson.length() > 1000) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0141));
    		}
    		
    		if (payItem != null && payItem.length() > 32) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0142));
    		}
    		
    		if (payItemJSON != null && payItemJSON.length() > 100) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0143));
    		}
    		
    		if (authItem != null && authItem.toString().length() > 12) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0144));
    		}
    		
    		if (measureMode != null && measureMode.toString().length() > 11) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0145));
    		}
    		
    		if (loadAndDischargePermittedTimespan != null && loadAndDischargePermittedTimespan.length() > 255) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0146));
    		}
    		
    		if (law != null && law.length() > 100) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0147));
    		}
    		
    		if (gtc != null && gtc.length() > 100) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0148));
    		}
    		
    		if (remark != null && remark.length() > 2000) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0149));
    		}
    		
    		
    	} else {
    		LOGGER.info("biddingForm.Bidding=============" + "null");
    	}
    	
        // 港口长度校验
    	if(demandShip != null) {
    		String shipmentPort = demandShip.getShipmentPort();				//装货港
    		String dischargePort = demandShip.getDischargePort();			//卸货港
    		Date shipmentStartTime = demandShip.getShipmentStartTime();		//装期开始
    		Date shipmentEndTime = demandShip.getShipmentEndTime();			//装期结束
    		Date dischargeStartTime = demandShip.getDischargeStartTime();	//到货期开始
    		Date dischargeEndTime = demandShip.getDischargeEndTime();		//到货期结束
    		
    		if (shipmentPort != null && shipmentPort.length() > 100) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0150));
    		}
    		
    		if (dischargePort != null && dischargePort.length() > 100) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0151));
    		}
    		
    	} else {
    		LOGGER.info("biddingForm.DemandShip=============" + "null");
    	}
    }
}
