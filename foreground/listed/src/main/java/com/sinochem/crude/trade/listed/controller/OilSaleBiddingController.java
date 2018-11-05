package com.sinochem.crude.trade.listed.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sinochem.crude.trade.listed.constant.UrlMapping;
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

import com.eyeieye.melody.util.DateUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.constant.MsgConstant;
import com.sinochem.crude.trade.listed.domain.Demand;
import com.sinochem.crude.trade.listed.domain.DemandBiddingHistory;
import com.sinochem.crude.trade.listed.domain.DemandShip;
import com.sinochem.crude.trade.listed.helper.Base64Helper;
import com.sinochem.crude.trade.listed.helper.DictUtils;
import com.sinochem.crude.trade.listed.model.form.CrudeOIlBiddingForm;
import com.sinochem.crude.trade.listed.model.form.CrudeOilForm;
import com.sinochem.crude.trade.listed.model.form.QueryForm;
import com.sinochem.crude.trade.listed.model.query.ResourceQuery;
import com.sinochem.crude.trade.listed.model.result.DemandJoinResult;
import com.sinochem.crude.trade.listed.model.vo.BiddingListVO;
import com.sinochem.crude.trade.listed.model.vo.CrudeOilBiddingVO;
import com.sinochem.crude.trade.listed.model.vo.DemandBiddingHistoryVO;
import com.sinochem.crude.trade.listed.model.vo.DemandRelevanterVO;
import com.sinochem.crude.trade.listed.model.vo.DemandShipVO;
import com.sinochem.crude.trade.listed.model.vo.DemandVO;
import com.sinochem.crude.trade.listed.service.CrudeOilHallService;
import com.sinochem.crude.trade.listed.service.DemandBiddingHistoryService;
import com.sinochem.crude.trade.listed.service.DemandMessageService;
import com.sinochem.crude.trade.listed.service.DemandRelevanterService;
import com.sinochem.crude.trade.listed.service.DemandService;
import com.sinochem.crude.trade.listed.service.DemandShipService;
import com.sinochem.crude.trade.member.constants.MemberConstants;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.member.access.RolesAccess;


/**
 * 报价控制层
 * Created by kangkai on 24/02/2018.
 */
@Controller
public class OilSaleBiddingController {
    private Logger LOGGER = LoggerFactory.getLogger(OilSaleBiddingController.class);

    @Autowired
    private DemandService demandService;

    @Autowired
    private DemandBiddingHistoryService demandBiddingHistoryService;
    
    @Autowired
    private DemandShipService demandShipService;
    
    @Autowired
    private CrudeOilHallService crudeOilHallService;
    
    @Autowired
    private URLBroker appServerBroker;
    
    @Autowired
    private DemandRelevanterService demandRelevanterService;

    @Autowired
    private DemandMessageService demandMessageService;

    @Autowired
    private CrudeOilInfoService crudeOilInfoService;
    
    @Autowired
    private EnterpriseService enterpriseService;
    
    /**
     * 我发布的报价列表页面
     * @param user 当前用户
     * @param model
     * @return
     */
    @RolesAccess({MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.OILSALEBIDDING_BIDDINGLIST)
    public String biddingList(@ModelAttribute("queryForm") QueryForm queryForm, PageInfo pageInfo, CurrentUser user, ModelMap model){
        ResourceQuery resourceQuery = new ResourceQuery();
        // 查询必选项
        resourceQuery.setBizType(Constant.BIZ_TYPE_CRUDEOIL);
        resourceQuery.setType(Constant.DEMAND_TYPE_B);
        resourceQuery.setDealType(Constant.DEAL_TYPE_B);
        
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
        return "/buyerCenter/oilSaleBidding/biddingList";
    }
    
    /**
     * 报价详情页面
     * @param user 当前用户
     * @param model
     * @param demandId 报价单id
     * @return
     */
    @RolesAccess({MemberConstants.SALES_TRADER,MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.OILSALEBIDDING_BIDDINGDETAIL)
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
            
	            // 修改记录
	            List<DemandBiddingHistory> history = demandBiddingHistoryService.getDetailByDemandId(demandId);
	            model.addAttribute("biddingHistory", DemandBiddingHistoryVO.convertListToVo(history));
            }
        } catch (Exception ex) {
            LOGGER.error(ex.toString());
        }

        return "/buyerCenter/oilSaleBidding/biddingDetail";
    }
    
    /**
     * 需求、采购信息对比回显 PathVariable
     *
     * @param demandId
     * @param modelMap
     */
    @RolesAccess({MemberConstants.SALES_TRADER,MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.OILSALEBIDDING_PRICEDETAIL_COMPARE)
    public String queryDemandCompare(@RequestParam("demandId") Long demandId, ModelMap modelMap){
        try{
            //通过demandId 查询
            if (demandId != null) {
                Demand demand =  demandService.getDemandByKey(demandId);
                DemandVO demandVO = DemandVO.convertToVO(demand);

                modelMap.put("demand", demandVO);
                modelMap.put("demandShips", getShipByDemandId(demandId));
                if (null != demand.getParentId()) {//查询需求单
                    modelMap.put("demandParent", DemandVO.convertToVO(demandService.getDemandByKey(demand.getParentId())));
                    modelMap.put("demandParentShips", getShipByDemandId(demand.getParentId()));
                }
            }
            Map<Object,String> valuationProidTypeMap = DictUtils.getValuationProidTypeMap();
            modelMap.put("valuationProidTypeMap",valuationProidTypeMap);
            Map<Object,String> tradeItemMap = DictUtils.getTradeItemMap();
            modelMap.put("tradeItemMap",tradeItemMap);
            Map<Object,String> payItemMap =DictUtils.getPayItemMap();
            modelMap.put("payItemMap",payItemMap);
            Map<Object,String> purchaseModeMap = DictUtils.getPurchaseModes();
            modelMap.put("purchaseModeMap",purchaseModeMap);
            modelMap.put("valuationBaseMap", DictUtils.getValuationBaseMap());
            modelMap.put("authItemBaseMap", DictUtils.getCreditItem());
            modelMap.put("measureModeMap", DictUtils.getMeasureModeMap());
            return "/buyerCenter/oilSaleBidding/pricedetail_compare";
        }catch (Exception e){
            LOGGER.error("需求、采购信息对比回显异常");
            LOGGER.error("",e);
            return "/buyerCenter/oilSaleBidding/pricedetail_compare";
        }
    }
    
    /**
     * 根据demandId获取船务信息
     * @param demandId
     */
    private DemandShipVO getShipByDemandId(Long demandId){
         try {
             if(demandId == null) {
                 return null;
             }
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
                 return demandShipVO;
             }
         }catch (BizException e){
             LOGGER.error("根据demandId获取船务信息失败");
             LOGGER.error("",e);
         }catch (Exception e){
             LOGGER.error("根据demandId获取船务信息异常");
             LOGGER.error("",e);
         }
         return null;
    }
    
    /**
     * 需求对应的报价列表页面
     * @param user 当前用户
     * @param model
     * @return
     */
    @RolesAccess({MemberConstants.SALES_TRADER})
    @RequestMapping(value = UrlMapping.OILSALEBIDDING_DEMANDBIDDINGDETAIL)
    public String demandBiddingDetail(CurrentUser user, ModelMap model, Long demandId){
        model.put("user", user);
        model.put("demandId", demandId);
        return "/buyerCenter/oilSaleBidding/demandBiddingDetail";
    }
    
    /**
     * 需求的报价列表
     * @param user
     * @param model
     * @param demandId
     */
    @RolesAccess({MemberConstants.SALES_TRADER})
    @RequestMapping(value = UrlMapping.OILSALEBIDDING_DEMANDBIDDINGLIST)
    public String demandBiddingList(CurrentUser user, ModelMap model, Long demandId){
        try{
            ResourceQuery resourceQuery = new ResourceQuery();
            resourceQuery.setParentId(demandId);
            PageInfoResult pageInfoResult = demandService.queryDemandBiddingJoinTableByCondition(resourceQuery, null);
            List<DemandJoinResult> list = pageInfoResult.getList();
            List<BiddingListVO> VOlist = new ArrayList<>();
            for(DemandJoinResult demandJoinResult : list){
                VOlist.add(BiddingListVO.convertToVO(demandJoinResult));
            }
            pageInfoResult.setList(VOlist);
            model.addAttribute("pageInfoList", pageInfoResult);

            return "/buyerCenter/oilSaleBidding/demandBiddingList";
        }catch (Exception e){
            LOGGER.error("");
            LOGGER.error("",e);
        }
        return "/buyerCenter/oilSaleBidding/demandBiddingList";
    }
    
    /**
     * 供应商报价下单页demand信息回显
     * @param crudeOilBiddingVO
     * @param modelMap
     * @param user
     * @throws BizException
     */
    @RolesAccess({MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER})
    @RequestMapping(value = UrlMapping.OILSALEBIDDING_CRUDEOILBIDDING, method = RequestMethod.GET)
    public String saveProcurementDemand(CrudeOilBiddingVO crudeOilBiddingVO,Long demandAgain, ModelMap modelMap, CurrentUser user){
         /*demandAgain 是 再次报价 -页面回显功能 时添加
            2018年2月28日17:34:06
          */
        try {
            modelMap.put("user", user);
            if (crudeOilBiddingVO != null) {
                Long demandId = crudeOilBiddingVO.getDemandId();
                if (demandId != null) {
                   	// 基本信息
                    Demand demand = demandService.getDemandByKeyAndCurrentUser(demandId, user.getEpMemberId());

                	// 运输信息
                    DemandShipVO demandShip = getShipByDemandId(demandId);
                    
                    Date stopBidTime = demand.getStopBidTime();
                    Date today = DateUtil.getToday().getTime();
                    if (stopBidTime != null) {
                        if (today.compareTo(stopBidTime) > 0) {
                            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0037));
                        }
                    }

                    // 返回数据
                    CrudeOIlBiddingForm biddingForm = new CrudeOIlBiddingForm();
                    
                    // 报价信息
                    DemandVO bidding = null;
                    DemandShipVO biddingShip = null;
                    if(demandAgain != null) {
                    	// 基本信息
                    	Demand d = demandService.getDemandByKeyAndCurrentUser(demandAgain, user.getEpMemberId());
                    	bidding = DemandVO.convertToVO(d);
                    	
                    	// 运输信息
                    	biddingShip = getShipByDemandId(demandAgain);
                    	biddingForm.setDemandShip(biddingShip);
                    } else {
                    	// 基本信息
                    	bidding = DemandVO.convertToVO(demand);

                    	// 运输信息
                    	biddingForm.setDemandShip(demandShip);
                    }
                    bidding.setStopBidTime(null);
                    bidding.setRemark(null);
                    biddingForm.setBidding(bidding);
                    
                    // 供应商信息
                    DemandRelevanterVO demandRelevanter = new DemandRelevanterVO();
                    if(user!=null){
                        EnterpriseVo ev =  enterpriseService.getByMemberId(user.getEpMemberId());
                        demandRelevanter.setEnterpriseName(ev.getFullName());
                    }
                    biddingForm.setDemandRelevanter(demandRelevanter);
                    
                    // 销售标志
                    biddingForm.setOilSaleFlag(1);
                    
                    // 返回画面
                    modelMap.put("biddingForm", biddingForm);
                    modelMap.put("demandId", demandId);
                    modelMap.put("demand", demand);
                    modelMap.put("demandShips", demandShip);
                    
                    String errorMessage = crudeOilBiddingVO.getErrorMsg();
                    if (StringUtils.isNotBlank(errorMessage)) {
                        errorMessage = new String(Base64Helper.decode(errorMessage));
                        String type = "报价";
                        if (demand.getPurchaseType() != null && 2 != demand.getPurchaseType().intValue()) {
                            type = "投标";
                        }
                        if(errorMessage.equalsIgnoreCase("creator")) {
                            errorMessage = VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0064);
                        }
                        if(errorMessage.equalsIgnoreCase("proxy")) {
                            errorMessage = VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0065);
                        }
                        modelMap.put("errorMessage", errorMessage);
                    }
                }
            }
        } catch (BizException e) {
            LOGGER.error("供应商报价下单页demand信息回显 失败");
            LOGGER.error("",e);
            modelMap.put("errorMsg",e.getMessage());
        } catch (Exception ex) {
            LOGGER.error("供应商报价下单页demand信息回显异常");
            LOGGER.error("",ex);
            modelMap.put("errorMessage", ex.getMessage());
        }
        return "/crudeoillobby/oilSaleBidding/oilSaleBidding";
    }

    /**
     * 查询demand船信息
     * @param demandId
     * @param modelMap
     * @return
     * @throws BizException
     */
    @RolesAccess({MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER})
    @RequestMapping(value = UrlMapping.OILSALEBIDDING_PRICELISTTWO)
    public String queryDemandShip(Long demandId, ModelMap modelMap) throws BizException {
        /*if(demandId!=null){
            Demand demand = demandService.getDemandByKey(demandId);
            DemandVO demandVO = DemandVO.convertToVO(demand);
            modelMap.put("demand", demand);
            modelMap.put("demandShips", getShipByDemandId(demandId));
        }*/

        Map<Object, String> inspectionFeeSharingTypeMap = DictUtils.getInspectionFeeSharingTypeMap();
        modelMap.put("inspectionFeeSharingTypeMap", inspectionFeeSharingTypeMap);
        Map<Object, String> tradeItemMap = DictUtils.getTradeItemMap();
        modelMap.put("tradeItemMap",tradeItemMap);
        Map<Object,String> purchaseModes = DictUtils.getPurchaseModes();
        modelMap.put("purchaseModes",purchaseModes);
        Map<Object,String> payItemMap = DictUtils.getPayItemMap();
        modelMap.put("payItemMap",payItemMap);
        Map<Object,String> creditItem = DictUtils.getCreditItem();
        modelMap.put("creditItem",creditItem);
        Map<Object,String> shipTypes = DictUtils.getShipTypes();
        modelMap.put("shipTypes",shipTypes);
        modelMap.put("valuationProidTypeMap", DictUtils.getValuationProidTypeMap());
        Map<Object,String> valuationBase = DictUtils.getValuationBaseMap();
        modelMap.put("valuationBase",valuationBase);
        Map<Object,String> measureModeMap = DictUtils.getMeasureModeMap();
        modelMap.put("measureModeMap",measureModeMap);
        Map<Object,String> contractKindMap = DictUtils.getContractKindMap();
        modelMap.put("contractKindMap",contractKindMap);
        
        return "/crudeoillobby/oilSaleBidding/pricelistTwo";
    }
    
    /**
     * 立即报价
     * @param currentUser
     * @param biddingForm
     * @param modelMap
     * @return
     */
    @RolesAccess({MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.OILSALEBIDDING_QUOTETYPE)
    public String updateType(CurrentUser currentUser, @ModelAttribute(value="biddingForm") CrudeOIlBiddingForm biddingForm, ModelMap modelMap){
        Long demandId = null;
        CrudeOilForm crudeOilForm = null;

        try {
        	// demandId
        	demandId = biddingForm.getBidding().getParentId();
        	
        	// 回显用油种数据
    		CrudeOilInfoVO crudeOilInfoVO = crudeOilInfoService.findCrudeOilInfoById(biddingForm.getCrudeOil().getPropertyId());
    		crudeOilForm = CrudeOilForm.convertVoToForm(crudeOilInfoVO);
    		crudeOilForm.setDesc(biddingForm.getCrudeOil().getDesc());
        	
        	//LOGGER.info("logBiddingForm start");
        	//logBiddingForm(currentUser, biddingForm);
        	
            // 验证从页面过来的报价数据
        	//LOGGER.info("validBiddingForm start");
            validBiddingForm(biddingForm, currentUser.getEpMemberId());
            // 保存报价数据
            
        	//LOGGER.info("saveQuote strat");
            biddingForm.setOilSaleFlag(1);
            Demand biddingDemand = crudeOilHallService.saveQuote(currentUser, biddingForm);
            
            // 向对家发送消息
        	//LOGGER.info("biddingRelease strat");
            demandMessageService.biddingRelease(biddingDemand.getId());

            //LOGGER.info("updateType success");
            return "redirect:/oilSaleBidding/biddingList.htm";
        } catch (BizException e) {
        	e.printStackTrace();
        	
            // 返回画面
        	biddingForm.setCrudeOil(crudeOilForm);
            modelMap.put("biddingForm", biddingForm);
            modelMap.put("demandId", demandId);
            modelMap.put("errorMessage", e.getMessage());
        	
            try {
				modelMap.put("demand", DemandVO.convertToVO(demandService.getDemandByKeyAndCurrentUser(demandId, currentUser.getEpMemberId())));
				modelMap.put("demandShips", getShipByDemandId(demandId));
            } catch (BizException e1) {
				e1.printStackTrace();
			}
            
        	LOGGER.error("投标失败{}",demandId);
        	return "/crudeoillobby/oilSaleBidding/oilSaleBidding";        
        } catch (Exception e) {
        	e.printStackTrace();
        	
            // 返回画面
        	biddingForm.setCrudeOil(crudeOilForm);
            modelMap.put("biddingForm", biddingForm);
            modelMap.put("demandId", demandId);
            modelMap.put("errorMessage", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0014));
        	
            try {
				modelMap.put("demand", DemandVO.convertToVO(demandService.getDemandByKeyAndCurrentUser(demandId, currentUser.getEpMemberId())));
				modelMap.put("demandShips", getShipByDemandId(demandId));
            } catch (BizException e1) {
				e1.printStackTrace();
			}
            
        	LOGGER.error("投标失败{}",demandId);
        	return "/crudeoillobby/oilSaleBidding/oilSaleBidding";
        }
    }
    
    /**
     * 后台验证原油报价
     * @param biddingForm
     * @param currentEpmemberId
     * @throws BizException
     */
    private void validBiddingForm(CrudeOIlBiddingForm biddingForm, Long currentEpmemberId) throws Exception {

        DemandVO biddingDemand = biddingForm.getBidding();
        CrudeOilForm crudeOil = biddingForm.getCrudeOil();
        DemandShipVO demandShip = biddingForm.getDemandShip();
        DemandRelevanterVO demandRelevanter = biddingForm.getDemandRelevanter();
        
        // 油种信息非空判断
        if (null == crudeOil) {
        	throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0076));
        }
        // 报价单长度校验
        if (biddingDemand != null) {
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
    	if (demandShip != null) {
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
    	
    	// 企业信息长度校验
    	if (demandRelevanter != null) {
    		String enterpriseName = demandRelevanter.getEnterpriseName();		//企业名称
    		String enterpriseAddress = demandRelevanter.getEnterpriseAddress();	//企业地址
    		String contacter = demandRelevanter.getContacter();					//联系人
    		String phoneNo = demandRelevanter.getPhoneNo();						//联系电话
    		String famil = demandRelevanter.getFamil();							//邮箱
    		String fax = demandRelevanter.getFax();								//传真
    		
    		if (enterpriseName != null && enterpriseName.length() > 100) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0152));
    		}
    		
    		if (enterpriseAddress != null && enterpriseAddress.length() > 128) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0153));
    		}
    		
    		if (contacter != null && contacter.length() > 100) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0154));
    		}
    		
    		if (phoneNo != null && phoneNo.length() > 20) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0155));
    		}
    		
    		if (famil != null && famil.length() > 64) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0156));
    		}
    		
    		if (fax != null && fax.length() > 20) {
    			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0157));
    		}
    		
    	} else {
    		LOGGER.info("biddingForm.DemandRelevanter=============" + "null");
    	}
        
        if (biddingDemand != null) {
            Date stopBidTime = biddingDemand.getStopBidTime();

            if (stopBidTime != null) {
                if (DateUtil.getToday().getTime().after(stopBidTime)) {
                    throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0066));
                }
            }
        }

        Demand demands = demandService.getDemandByKey(biddingForm.getBidding().getParentId());
        // 判断当前用户是否是创建需求的用户
        if (currentEpmemberId.equals(demands.getCreater())) throw new BizException(VisitorLocale.getByCurrentLanguage(Constant.LISTED_0037));

        //DemandRelevanter demandRelevanter1 = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeTwo(
        //        demands.getId(), DemandRelevanterType.AGENT.getCode());
        // 判断当前用户是否已经报价
        //if (demandRelevanter1!=null && currentEpmemberId.equals(demandRelevanter1.getEMemberId())) throw new BizException("proxy");
    }
}
