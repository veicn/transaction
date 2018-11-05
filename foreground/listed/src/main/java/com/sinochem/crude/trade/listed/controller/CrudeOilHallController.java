package com.sinochem.crude.trade.listed.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.sinochem.crude.trade.listed.constant.UrlMapping;
import com.sinochem.crude.trade.listed.service.*;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BasicClientCookie2;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.util.DateUtil;
import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import com.sinochem.crude.trade.goods.remote.CrudeOilOriginAreaService;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.constant.MsgConstant;
import com.sinochem.crude.trade.listed.domain.CrudeOil;
import com.sinochem.crude.trade.listed.domain.Demand;
import com.sinochem.crude.trade.listed.domain.DemandRelevanter;
import com.sinochem.crude.trade.listed.domain.DemandShip;
import com.sinochem.crude.trade.listed.domain.DemandSpecifyEnterprise;
import com.sinochem.crude.trade.listed.enums.CrudeOilDemandOrderProptertyType;
import com.sinochem.crude.trade.listed.enums.CrudeOilDemandSearchOptionType;
import com.sinochem.crude.trade.listed.enums.CrudeOilPublishRangeType;
import com.sinochem.crude.trade.listed.enums.CrudeOilQuantityType;
import com.sinochem.crude.trade.listed.enums.DealType;
import com.sinochem.crude.trade.listed.enums.DemandPublishType;
import com.sinochem.crude.trade.listed.enums.DemandRelevanterType;
import com.sinochem.crude.trade.listed.enums.DemandStatus;
import com.sinochem.crude.trade.listed.enums.PurchaseType;
import com.sinochem.crude.trade.listed.helper.Base64Helper;
import com.sinochem.crude.trade.listed.model.form.CrudeOIlBiddingForm;
import com.sinochem.crude.trade.listed.model.form.CrudeOilForm;
import com.sinochem.crude.trade.listed.model.form.DemandForm;
import com.sinochem.crude.trade.listed.model.result.DemandJoinResult;
import com.sinochem.crude.trade.listed.model.vo.CrudeOilBiddingVO;
import com.sinochem.crude.trade.listed.model.vo.CrudeOilDemandQueryVO;
import com.sinochem.crude.trade.listed.model.vo.CrudeOilDemandSearchOptionsVO;
import com.sinochem.crude.trade.listed.model.vo.DemandDetailVO;
import com.sinochem.crude.trade.listed.model.vo.DemandJoinVO;
import com.sinochem.crude.trade.listed.model.vo.DemandRelevanterBuyerVO;
import com.sinochem.crude.trade.listed.model.vo.DemandRelevanterVO;
import com.sinochem.crude.trade.listed.model.vo.DemandShipVO;
import com.sinochem.crude.trade.listed.model.vo.DemandSpecifyEnterpriseVO;
import com.sinochem.crude.trade.listed.model.vo.DemandVO;
import com.sinochem.crude.trade.listed.model.vo.KeyValueVO;
import com.sinochem.crude.trade.member.constants.MemberConstants;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * 原油大厅控制层
 */
@Controller
public class CrudeOilHallController {

    private Logger LOGGER = LoggerFactory.getLogger(CrudeOilHallController.class);

    @Autowired
    private DemandService demandService;

    @Autowired
    private DemandRelevanterService demandRelevanterService;

    @Autowired
    private CrudeOilHallService crudeOilHallService;

    @Autowired
    private CrudeOilResourceService crudeOilResourceService;

    @Autowired
    private CrudeOilInfoService crudeOilInfoService;

    @Autowired
    private CrudeOilOriginAreaService crudeOilOriginAreaService;

    @Autowired
    private URLBroker appServerBroker;

    @Autowired
    private URLBroker memberServer;

    @Autowired
    private HttpConnectionManager httpConnectionManager;

    @Autowired
    private DemandMessageService demandMessageService;

    @Autowired
    private URLBroker listedServer;

    @Autowired
    private MessageService messageService;

    @Autowired
    private DemandShipService demandShipService;

    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * 原油大厅
     * @param user
     * @param model
     * @param order
     */
    @RolesAccess({MemberConstants.OBSERVER,MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER,MemberConstants.TRADE_EXECUTOR,MemberConstants.INSPECTION, MemberConstants.SHIP_EXECUTOR,MemberConstants.SHIP_TRADER,MemberConstants.OPTIMIZATION})
    @RequestMapping(value = UrlMapping.CRUDEOILLOBBY_CRUDEOILSUPMLIST)
    public void crudeoilsupmlist(CurrentUser user, ModelMap model, String order) {
        model.put("user", user);
        model.put("today", new Date());
        model.addAttribute("order",order);
    }

    /**
     * 原油买家
     */
    @RolesAccess({MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.CRUDEOILLOBBY_PURCHASINGDEMAND)
    public void purchasingDemand() {

    }


    /**
     * 采购需求详情
     * @param modelMap
     * @param demandId
     */
    @RolesAccess({MemberConstants.OBSERVER,MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER,MemberConstants.TRADE_EXECUTOR,MemberConstants.INSPECTION})
    @RequestMapping(value = UrlMapping.CRUDEOILLOBBY_PURCHASINGDEMANDDETAIL)
    public void queryDemand(CurrentUser user, ModelMap modelMap, @RequestParam("demandId") Long demandId) {
        try {
            if (demandId != null) {
                Demand demand = demandService.getDemandByKeyAndCurrentUser(demandId, user.getEpMemberId());
                DemandVO demandVO = DemandVO.convertToVO(demand);

                modelMap.put("demand", demandVO);

                DemandRelevanter buyer = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeTwo(demandId, DemandRelevanterType.BUYER.getCode());
                EnterpriseVo ev = null;
                if (buyer != null) {
                    ev = enterpriseService.getByMemberId(buyer.getEMemberId());
                }
                modelMap.put("ev", ev);
            }
        } catch (BizException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(ex.toString());
        }
    }

    /**
     * 供应商报价下单页demand信息回显
     * @param crudeOilBiddingVO
     * @param modelMap
     * @param user
     */
    @RolesAccess({MemberConstants.SALES_TRADER})
    @RequestMapping(value = UrlMapping.CRUDEOILLOBBY_CRUDEOILBIDDING, method = RequestMethod.GET)
    public String saveProcurementDemand(CrudeOilBiddingVO crudeOilBiddingVO, Long demandAgain, ModelMap modelMap, CurrentUser user){
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
                    
                    // 返回画面
                    modelMap.put("biddingForm", biddingForm);
                    modelMap.put("demandId", demandId);
                    modelMap.put("demand", demand);
                    modelMap.put("demandShips", demandShip);
                    
                    String errorMessage = crudeOilBiddingVO.getErrorMsg();
                    if (StringUtils.isNotBlank(errorMessage)) {
                        errorMessage = new String(Base64Helper.decode(errorMessage));
                        if(errorMessage.equalsIgnoreCase("creator")) {
                            errorMessage = VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0038);
                        }
                        if(errorMessage.equalsIgnoreCase("proxy")) {
                            errorMessage = VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0039);
                        }
                        modelMap.put("errorMessage", errorMessage);
                    }
                }
            }
        } catch (BizException e) {
            LOGGER.error("供应商报价下单页demand信息回显失败");
            LOGGER.error("",e);
        } catch (Exception ex) {
            LOGGER.error("供应商报价下单页demand信息回显失败");
            LOGGER.error("",ex);
            modelMap.put("errorMessage", ex.getMessage());
        }
        
        return "crudeoillobby/crudeOilBidding";
    }

    /**
     * 根据demandId获取船务信息
     * @param demandId
     */
    private DemandShipVO getShipByDemandId(Long demandId) throws BizException {
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
        return null;
    }
    
    /**
     * 保存需求单  保存或发布（status:1保存 2发布）
     * @param
     * @param currentUser
     * @return
     */
    @RolesAccess({MemberConstants.BUY_TRADER})
    @RequestMapping(value = UrlMapping.CRUDEOILLOBBY_SAVEDEMAND, method = RequestMethod.POST)
    public String saveProcurementDemand(@ModelAttribute("demandForm") @Valid DemandForm demandForm, BindingResult bindingResult, ModelMap modelMap, CurrentUser currentUser ) {
        demandForm.setSpecifyEnterpriseList();

        try {
            validForm(demandForm, bindingResult);
            if (bindingResult.hasErrors()) {
                modelMap.put("errors", bindingResult.getAllErrors());
                if(StringUtils.isNotBlank(demandForm.getPageType())
                        && StringUtils.equalsIgnoreCase(Constant.MALL, demandForm.getPageType())){
                	modelMap.put("demandForm.crudeOilFormList", demandForm.getCrudeOilFormList());
                	modelMap.put("demandForm", demandForm);
                	return "crudeoillobby/purchasingDemand";
                }else{
                	modelMap.put("demandForm.crudeOilFormList", demandForm.getCrudeOilFormList());
                    modelMap.put("layout","layout/buyerCenter/default.vm");
                    return "crudeoilhallform/sheet";
                }
            }

            List list = new ArrayList();
            list.add(DemandRelevanterBuyerVO.convertVoToDomain(demandForm.getBuyerRelevanter()));


            DemandShip demandShip = new DemandShip();
            if (demandForm.getDemandShip() != null) {
            	demandShip = demandForm.getDemandShip().convertVoToDomain();
            }
            // 采购需求指定发布  by sijiliu 2018-01-18
            List<DemandSpecifyEnterprise> specifyEnterpriseList = CollectionUtils.isNotEmpty(demandForm.getSpecifyEnterpriseList()) ?
                    DemandSpecifyEnterpriseVO.convertListToDomain(demandForm.getSpecifyEnterpriseList()) : null;
            
            Demand demand = crudeOilHallService.saveProcurementDemand(DemandVO.convertToDomain(demandForm.getDemand()),
                    null, //demandShipBerth被删去
                    list,
                    demandForm.getIdForm(),
                    demandForm.getCrudeOilFormList(),
                    DemandDetailVO.convertListToDomain(demandForm.getDemandDetailList()),
                    demandShip,
                    currentUser,
                    specifyEnterpriseList);
            modelMap.put("idForm", demandForm.getIdForm());

            // 状态为发布时
            if (DemandStatus.DEMAND_STATUS_2.getCode() == demandForm.getDemand().getStatus()) {
                demandMessageService.demandDirectionalRelease(demand.getId());
            }

            return "redirect:/purchaseManager/buyingLeadsDemandList.htm";
        } catch(BizException e) {
            modelMap.put("errorMessage", e.getMessage());
            e.printStackTrace();
            if(StringUtils.isNotBlank(demandForm.getPageType())
                    && StringUtils.equalsIgnoreCase(Constant.MALL, demandForm.getPageType())){
            	modelMap.put("demandForm.crudeOilFormList", demandForm.getCrudeOilFormList());
            	modelMap.put("demandForm", demandForm);
            	return "crudeoillobby/purchasingDemand";
            }else{
            	modelMap.put("demandForm.crudeOilFormList", demandForm.getCrudeOilFormList());
                modelMap.put("layout","layout/buyerCenter/default.vm");
                return "crudeoilhallform/sheet";
            }
        } catch (Exception ex){
            modelMap.put("errorMessage", VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0040));
            ex.printStackTrace();
            LOGGER.error(ex.toString());
            if(StringUtils.isNotBlank(demandForm.getPageType())
                    && StringUtils.equalsIgnoreCase(Constant.MALL, demandForm.getPageType())){
            	modelMap.put("demandForm.crudeOilFormList", demandForm.getCrudeOilFormList());
            	modelMap.put("demandForm", demandForm);
            	return "crudeoillobby/purchasingDemand";
            }else{
            	modelMap.put("demandForm.crudeOilFormList", demandForm.getCrudeOilFormList());
                modelMap.put("layout","layout/buyerCenter/default.vm");
                return "crudeoilhallform/sheet";
            }
        }
    }

    /**
     * 立即报价
     * @param currentUser
     * @param biddingForm
     * @param modelMap
     * @return
     * @throws BizException
     */
    @RolesAccess({MemberConstants.SALES_TRADER})
    @RequestMapping(value = UrlMapping.CRUDEOILLOBBY_QUOTETYPE)
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
            Demand biddingDemand = crudeOilHallService.saveQuote(currentUser, biddingForm);
            
            // 向对家发送消息
        	//LOGGER.info("biddingRelease strat");
            demandMessageService.biddingRelease(biddingDemand.getId());

            //LOGGER.info("updateType success");
            return "redirect:/purchaseManager/biddingList.htm";
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
        	return "/crudeoillobby/crudeOilBidding";        
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
        	return "/crudeoillobby/crudeOilBidding";
        }
    }

    private void logBiddingForm(CurrentUser currentUser, CrudeOIlBiddingForm biddingForm) {
    	LOGGER.info("user.memberId===========" + currentUser.getMemberId());
    	LOGGER.info("user.epMemberId=========" + currentUser.getEpMemberId());
    	LOGGER.info("user.name===============" + currentUser.getName());

    	if(biddingForm.getCrudeOil() != null) {
    		LOGGER.info("biddingForm.CrudeOil.id=============" + biddingForm.getCrudeOil().getId());
    		LOGGER.info("biddingForm.CrudeOil.PropertyId=============" + biddingForm.getCrudeOil().getPropertyId());
    	} else {
    		LOGGER.info("biddingForm.CrudeOil=============" + "null");
    	}

    	if(biddingForm.getBidding() != null) {
    		LOGGER.info("biddingForm.Bidding.Id=============" + biddingForm.getBidding().getId());
    		LOGGER.info("biddingForm.Bidding.Num=============" + biddingForm.getBidding().getNum());
    		LOGGER.info("biddingForm.Bidding.Numfloat=============" + biddingForm.getBidding().getNumfloat());
    		LOGGER.info("biddingForm.Bidding.TradeItem=============" + biddingForm.getBidding().getTradeItem());
    		LOGGER.info("biddingForm.Bidding.Agio=============" + biddingForm.getBidding().getAgio());
    		LOGGER.info("biddingForm.Bidding.ValuationBase=============" + biddingForm.getBidding().getValuationBase());
    		LOGGER.info("biddingForm.Bidding.ValuationProidType=============" + biddingForm.getBidding().getValuationProidType());
    		LOGGER.info("biddingForm.Bidding.ValuationFormula=============" + biddingForm.getBidding().getValuationFormula());
    		LOGGER.info("biddingForm.Bidding.ValuationFormulaJson=============" + biddingForm.getBidding().getValuationFormulaJson());
    		LOGGER.info("biddingForm.Bidding.PayItem=============" + biddingForm.getBidding().getPayItem());
    		LOGGER.info("biddingForm.Bidding.PayItemJSON=============" + biddingForm.getBidding().getPayItemJSON());
    		LOGGER.info("biddingForm.Bidding.AuthItem=============" + biddingForm.getBidding().getAuthItem());
    		LOGGER.info("biddingForm.Bidding.MeasureMode=============" + biddingForm.getBidding().getMeasureMode());
    		LOGGER.info("biddingForm.Bidding.StopBidTime=============" + biddingForm.getBidding().getStopBidTime());
    		LOGGER.info("biddingForm.Bidding.LoadAndDischargePermittedTimespan=============" + biddingForm.getBidding().getLoadAndDischargePermittedTimespan());
    		LOGGER.info("biddingForm.Bidding.InspectionFeeSharingType=============" + biddingForm.getBidding().getInspectionFeeSharingType());
    		LOGGER.info("biddingForm.Bidding.Law=============" + biddingForm.getBidding().getLaw());
    		LOGGER.info("biddingForm.Bidding.Gtc=============" + biddingForm.getBidding().getGtc());
    		LOGGER.info("biddingForm.Bidding.Remark=============" + biddingForm.getBidding().getRemark());
    	} else {
    		LOGGER.info("biddingForm.Bidding=============" + "null");
    	}

    	if(biddingForm.getDemandShip() != null) {
    		LOGGER.info("biddingForm.DemandShip.ShipmentPort=============" + biddingForm.getDemandShip().getShipmentPort());
    		LOGGER.info("biddingForm.DemandShip.DischargePort=============" + biddingForm.getDemandShip().getDischargePort());
    		LOGGER.info("biddingForm.DemandShip.ShipmentStartTime=============" + biddingForm.getDemandShip().getShipmentStartTime());
    		LOGGER.info("biddingForm.DemandShip.ShipmentEndTime=============" + biddingForm.getDemandShip().getShipmentEndTime());
    		LOGGER.info("biddingForm.DemandShip.DischargeStartTime=============" + biddingForm.getDemandShip().getDischargeStartTime());
    		LOGGER.info("biddingForm.DemandShip.DischargeEndTime=============" + biddingForm.getDemandShip().getDischargeEndTime());
    	} else {
    		LOGGER.info("biddingForm.DemandShip=============" + "null");
    	}

    	if(biddingForm.getDemandRelevanter() != null) {
    		LOGGER.info("biddingForm.DemandRelevanter.id=============" + biddingForm.getDemandRelevanter().getId());
    		LOGGER.info("biddingForm.DemandRelevanter.EnterpriseName=============" + biddingForm.getDemandRelevanter().getEnterpriseName());
    		LOGGER.info("biddingForm.DemandRelevanter.EnterpriseAddress=============" + biddingForm.getDemandRelevanter().getEnterpriseAddress());
    		LOGGER.info("biddingForm.DemandRelevanter.Contacter=============" + biddingForm.getDemandRelevanter().getContacter());
    		LOGGER.info("biddingForm.DemandRelevanter.PhoneNo=============" + biddingForm.getDemandRelevanter().getPhoneNo());
    		LOGGER.info("biddingForm.DemandRelevanter.Famil=============" + biddingForm.getDemandRelevanter().getFamil());
    		LOGGER.info("biddingForm.DemandRelevanter.Fax=============" + biddingForm.getDemandRelevanter().getFax());

    	} else {
    		LOGGER.info("biddingForm.DemandRelevanter=============" + "null");
    	}
    }
    
    /**
     * 原油大厅 发布需求分页查询
     *
     * @param modelMap
     * @param pageInfo
     */
    @RolesAccess({MemberConstants.OBSERVER,MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER,MemberConstants.TRADE_EXECUTOR,MemberConstants.INSPECTION})
    @RequestMapping(value = UrlMapping.CRUDEOILLOBBY_INDEX)
    public void queryCrudeOilDemands(ModelMap modelMap, PageInfo pageInfo,
                                     CrudeOilDemandQueryVO crudeOilDemandQueryVO, CurrentUser user,
                                     HttpServletRequest request) {
        try {
            if (crudeOilDemandQueryVO.getPageSize() != null) {
                pageInfo.setPageSize(crudeOilDemandQueryVO.getPageSize());
            } else {
                pageInfo.setPageSize(Constant.DEFAULT_PAGE_SIZE);
            }

            crudeOilDemandQueryVO.setEpMemberId(user.getEpMemberId());

            List<CrudeOilDemandSearchOptionsVO> crudeOilDemandSearchOptions = getSearchOptions(request);
            modelMap.addAttribute("searchOptionsList", crudeOilDemandSearchOptions);
            if(StringUtil.isEmpty(crudeOilDemandQueryVO.getOrderByKeys())){
                crudeOilDemandQueryVO.setOrderByKeys(CrudeOilDemandOrderProptertyType.NONE.getCode());
            }
            modelMap.addAttribute("crudeOilDemandQuery", crudeOilDemandQueryVO);
            crudeOilDemandQueryVO.setType(Constant.DEMAND_TYPE_D);
            crudeOilDemandQueryVO.setDealType(DealType.BUY.getCode());
            PageInfoResult pageInfoResult =  demandService.queryCrudeOilDemandByCondition(crudeOilDemandQueryVO, pageInfo);
            List<DemandJoinResult> demandJoinResultList = pageInfoResult.getList();

            List<DemandJoinVO> demandJoinVOList = new ArrayList<>();
            for (DemandJoinResult demandJoinResult : demandJoinResultList) {
                DemandJoinVO demandJoinVO = DemandJoinVO.convertToVO(demandJoinResult);
                decorateDemandJoinVO(demandJoinVO, demandJoinResult);
                demandJoinVOList.add(demandJoinVO);
            }
            pageInfoResult.setList(demandJoinVOList);

            //查询所有发布信息条数
            Long total = pageInfoResult.getTotal();
            modelMap.put("pageInfoResult", pageInfoResult);
            modelMap.put("total",total);
            modelMap.put("user",user);

        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(ex.toString());
        }
    }

    /**
     * 保存后数据回显
     *
     * @param demandId
     * @param modelMap
     */
    @RequestMapping(value = UrlMapping.CRUDEOILLOBBY_PURCHASINGDEMANDEDIT)
    public void deliveryDemandId(@RequestParam("demandId") Long demandId, ModelMap modelMap) {
        try {
            modelMap.put("demandId", demandId);
            List<CrudeOil> crudeOilList = crudeOilResourceService.getCrudeListDemandId(demandId);
            modelMap.put("crudeOilResourceList", crudeOilList);
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(ex.toString());
        }
    }

    /**
     * 查询原油产品信息
     *
     * @param crudeName
     * @return
     */
    @RequestMapping(value = UrlMapping.CRUDEOILLOBBY_QUERYCRUDEOILINFOS)
    public List<CrudeOilInfoVO> queryCrudeOilInfos(String crudeName) {
        try {
            List<CrudeOilInfoVO> vos = crudeOilInfoService.findCrudeOilInfos(crudeName, null);
            return vos;
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(ex.toString());

            return null;
        }
    }

    /**
     * 原油大厅查询所有发布信息总条数
     *
     * @param modelMap
     * @throws BizException
     */
    @RequestMapping(value = UrlMapping.CRUDEOILLOBBY_ELECT)
    public void getDemandCount(ModelMap modelMap) {
        try {
            Long demandCount = demandService.getDemandCount();
            modelMap.put("demandCount", demandCount);
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(ex.toString());
        }
    }

    /**
     * 对比
     */
    @RolesAccess({MemberConstants.OBSERVER,MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER,MemberConstants.TRADE_EXECUTOR,MemberConstants.INSPECTION})
    @RequestMapping(value = UrlMapping.CRUDEOILLOBBY_CONTRAST, method = RequestMethod.GET)
    public void queryCompare(ModelMap modelMap, CurrentUser user, String demands, String dealType) {
        try {
            if (demands != null) {
                String[] ids = demands.split(",");
                List<Long> idList = new ArrayList<Long>();
                for (String c : ids) {
                    Long id = Long.parseLong(c);
                    idList.add(id);
                }
                List<DemandJoinResult> demandJoinResultList = demandService.getCrudeOilCompareByIds(idList);
                List<DemandJoinVO> demandJoinVOList = new ArrayList<>();

                //用于多语言翻译
                List<Long> epMemberIdList = new ArrayList<>();

                for (DemandJoinResult demandJoinResult : demandJoinResultList) {
                    DemandJoinVO demandJoinVO = DemandJoinVO.convertToVO(demandJoinResult);
                    decorateDemandJoinVO(demandJoinVO, demandJoinResult);
                    
            		// 企业信息
            		DemandRelevanter dr = null;
            		// 购买商信息
            		if(demandJoinVO.getDealType().equals(Constant.DEAL_TYPE_B)) {
            			dr = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeTwo(Long.parseLong(demandJoinVO.getId()), DemandRelevanterType.BUYER.getCode());
                        if(dr != null) {
                            epMemberIdList.add(dr.getEMemberId());
                        }
            		}
            		// 供应商信息
            		if(demandJoinVO.getDealType().equals(Constant.DEAL_TYPE_S)) {
            			dr = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeTwo(Long.parseLong(demandJoinVO.getId()), DemandRelevanterType.SUPPLIER.getCode());
                        if(dr != null) {
                            epMemberIdList.add(dr.getEMemberId());
                        }
            		}

                    demandJoinVOList.add(demandJoinVO);
                }

                /* 企业名称翻译 */
                Map<Long, String> enterpriseNameMap = enterpriseService.getEnterpriseName((Long[]) epMemberIdList.toArray(), VisitorLocale.getCurrent());

                for (DemandJoinVO demandJoinVO : demandJoinVOList) {
                    Long bEpMemberId = demandJoinVO.getbEpMemberId();
                    String bEnpterpriseName = enterpriseNameMap.get(bEpMemberId);
                    demandJoinVO.setbEnterpriseName(bEnpterpriseName);

                    Long pEpMemberId = demandJoinVO.getpEpMemberId();
                    String pEnterpriseName = enterpriseNameMap.get(pEpMemberId);
                    demandJoinVO.setpEnterpriseName(pEnterpriseName);
                }

                modelMap.put("demandList", demandJoinVOList);
                modelMap.put("user", user);
                modelMap.put("dealType", dealType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(ex.toString());
        }
    }

    private List<CrudeOilDemandSearchOptionsVO> getSearchOptions(HttpServletRequest request) {
        try {
            List<CrudeOilDemandSearchOptionsVO> crudeOilDemandSearchOptionsVOList = new ArrayList<>();

            // 交易方式
            CrudeOilDemandSearchOptionsVO purchaseTypeOptions = new CrudeOilDemandSearchOptionsVO(
                    CrudeOilDemandSearchOptionType.PURCHASE_TYPE,
                    PurchaseType.convertToVOList()
            );
            crudeOilDemandSearchOptionsVOList.add(purchaseTypeOptions);

            // 添加 采购类型
            CrudeOilDemandSearchOptionsVO publishTypeOptions = new CrudeOilDemandSearchOptionsVO(
                    CrudeOilDemandSearchOptionType.PUBLISH_TYPE,
                    DemandPublishType.convertToVOList()
            );
            crudeOilDemandSearchOptionsVOList.add(publishTypeOptions);

            // 添加 发布范围 筛选条件
            CrudeOilDemandSearchOptionsVO publishRangeOptions = new CrudeOilDemandSearchOptionsVO(
                    CrudeOilDemandSearchOptionType.PUBLISH_RANGE,
                    CrudeOilPublishRangeType.convertToVOList()
            );
            crudeOilDemandSearchOptionsVOList.add(publishRangeOptions);
            
            // 数量
            CrudeOilDemandSearchOptionsVO quantityOptions = new CrudeOilDemandSearchOptionsVO(
                    CrudeOilDemandSearchOptionType.QUANTITY,
                    CrudeOilQuantityType.convertToVOList()
            );
            crudeOilDemandSearchOptionsVOList.add(quantityOptions);
            
            // 添加 发布企业
            CloseableHttpResponse httpResponse = null;
            String requestUrl = memberServer.get("/centercontain/enterpriseList.json").toString();
            HttpGet httpGet = new HttpGet(requestUrl);

            String responseString;

            CloseableHttpClient closeableHttpClient = httpConnectionManager.getHttpClient();
            HttpClientContext httpContext = HttpClientContext.create();
            CookieStore cookieStore = new BasicCookieStore();
            for (Cookie cookie : request.getCookies()) {
                BasicClientCookie clientCookie = new BasicClientCookie2(cookie.getName(), cookie.getValue());

                clientCookie.setDomain(memberServer.getConfig().getHost());
                clientCookie.setPath("/");
                clientCookie.setVersion(cookie.getVersion());

                cookieStore.addCookie(clientCookie);
            }

            httpContext.setCookieStore(cookieStore);

            try {
                httpResponse = closeableHttpClient.execute(httpGet, httpContext);
                HttpEntity httpEntity = httpResponse.getEntity();

                if (httpEntity == null) {
                    throw new Exception(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0041));
                }

                responseString = EntityUtils.toString(httpEntity, "UTF-8");
                EntityUtils.consume(httpEntity);
            } catch (Exception ex) {
                ex.printStackTrace();
                LOGGER.error(ex.toString());
                return null;
            } finally {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            }

            if (StringUtil.isEmpty(responseString)) {
                throw new Exception(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0041));
            }

            JSONObject responseJson = JSONObject.parseObject(responseString);
            JSONArray dataJsonArray = responseJson.getJSONArray("datas");

            List<KeyValueVO> keyValueVOList = new ArrayList<>();
            for (int i = 0; i < dataJsonArray.size(); i++) {
                JSONObject dataJson = dataJsonArray.getJSONObject(i);

                String epMemberId = dataJson.getString("memberId");
                String enterpriseName = dataJson.getString("name");

                KeyValueVO keyValueVO = new KeyValueVO();
                keyValueVO.setKey(epMemberId);
                keyValueVO.setValue(enterpriseName);

                keyValueVOList.add(keyValueVO);
            }

            CrudeOilDemandSearchOptionsVO enterpriseOptions = new CrudeOilDemandSearchOptionsVO(
                    CrudeOilDemandSearchOptionType.ENTERPRISE,
                    keyValueVOList
            );
            crudeOilDemandSearchOptionsVOList.add(enterpriseOptions);

            return crudeOilDemandSearchOptionsVOList;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.toString());
            return null;
        }
    }

    private void validForm(DemandForm demandForm, BindingResult bindingResult) throws BizException {
        DemandRelevanterBuyerVO buyerRelevanter = demandForm.getBuyerRelevanter();
        
        if (buyerRelevanter != null) {
//            String phoneNo = buyerRelevanter.getPhoneNo();
//            if (StringUtils.isNotBlank(phoneNo) && !Pattern.matches("^((0\\d{2,3}-\\d{7,8})|(1\\d{10}))$", phoneNo))
//                bindingResult.rejectValue("buyerRelevanter.phoneNo", null, VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0042));
            String email = buyerRelevanter.getFamil();
            if (StringUtils.isNotBlank(email) && !Pattern.matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$", email))
                bindingResult.rejectValue("buyerRelevanter.famil", null, VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0043));
        }
        
        int  i = 0;
        for(CrudeOilForm dd : demandForm.getCrudeOilFormList()){
            if("".equals(dd.getName())){
                bindingResult.rejectValue("crudeOilFormList["+i+"].name", null, VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0044));
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0045).replaceAll("XXX",(i+1) + ""));
            }
            i++;
        }

        if(null == demandForm.getCrudeOilFormList() || demandForm.getCrudeOilFormList().size()==0)
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0046));
        if(demandForm.getDemand().getValuationProidType()==4)
            if(demandForm.getDemand().getValuationProidEnd()==null || demandForm.getDemand().getValuationProidStart()==null)
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0047));
        if(demandForm.getDemandShip().getDischargeStartTime()!=null && demandForm.getDemandShip().getDischargeEndTime() !=null){
            if(demandForm.getDemandShip().getDischargeEndTime().getTime() < demandForm.getDemandShip().getDischargeStartTime().getTime()){
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0048));
            }
        }
        if(demandForm.getDemandShip().getShipmentStartTime()!=null && demandForm.getDemandShip().getShipmentEndTime() !=null){
            if(demandForm.getDemandShip().getShipmentStartTime().getTime() > demandForm.getDemandShip().getShipmentEndTime().getTime()){
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0049));
            }
        }

        if(demandForm.getDemand().getSpecified() != null && demandForm.getDemand().getSpecified() == 1) {
            if(StringUtil.isEmpty(demandForm.getEpMemberIds()))
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0050));
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
        if(null == crudeOil) {
        	throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0076));
        }
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
    	
    	// 企业信息长度校验
    	if(demandRelevanter != null) {
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
                    throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0051));
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

    private void decorateDemandJoinVO(DemandJoinVO demandJoinVO, DemandJoinResult demandJoinResult) throws ParseException {
        Long num = demandJoinResult.getNum();
        if (num != null) {
            DecimalFormat df = new DecimalFormat("#0");
            double numConvert = num / (Constant.DEMAND_NUM_AMPLIFY_SCALE * Constant.TEN_THOUSAND);
            double numBBL = num / (Constant.DEMAND_NUM_AMPLIFY_SCALE);
            demandJoinVO.setNum(df.format(numConvert));
            demandJoinVO.setNumBBL(df.format(numBBL));
        }

        String crudeOilOptions = demandJoinResult.getCrudeOilOptions();
        if (!StringUtil.isEmpty(crudeOilOptions) && crudeOilOptions.endsWith(",")) {
            demandJoinVO.setCrudeOilOptions(crudeOilOptions.substring(0, crudeOilOptions.length() - 1));
        }

        Date stopBidTime = demandJoinResult.getStopBidTime();

        if(stopBidTime != null){
            Date today = DateUtil.getToday().getTime();
            demandJoinVO.setOverdue(today.after(stopBidTime));
        }else{
            demandJoinVO.setOverdue(false);
        }
    }
    
    /**
     * 销售资源维护_原油大厅的发布销售
     * @param
     * @return
     * @throws BizException
     */
    @RolesAccess({MemberConstants.SALES_TRADER})
    @RequestMapping(value = UrlMapping.CRUDEOILLOBBY_SELLINGDEMAND)
    public String sellingDemand() {
    	return "crudeoillobby/sellingDemand";
    }
    
    /**
     * 销售资源维护_原油大厅的发布销售分页查询
     *
     * @param modelMap
     * @param pageInfo
     */
    @RolesAccess({MemberConstants.OBSERVER,MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER,MemberConstants.TRADE_EXECUTOR,MemberConstants.INSPECTION})
    @RequestMapping(value = UrlMapping.CRUDEOILLOBBY_INDEXSELL)
    public String queryCrudeSellDemands(ModelMap modelMap, PageInfo pageInfo,
                                     CrudeOilDemandQueryVO crudeOilDemandQueryVO, CurrentUser user,
                                     HttpServletRequest request) {
        try {
            if (crudeOilDemandQueryVO.getPageSize() != null) {
                pageInfo.setPageSize(crudeOilDemandQueryVO.getPageSize());
            } else {
                pageInfo.setPageSize(Constant.DEFAULT_PAGE_SIZE);
            }

            crudeOilDemandQueryVO.setEpMemberId(user.getEpMemberId());

            List<CrudeOilDemandSearchOptionsVO> crudeOilDemandSearchOptions = getSearchOptions(request);
            modelMap.addAttribute("searchOptionsList", crudeOilDemandSearchOptions);
            if(StringUtil.isEmpty(crudeOilDemandQueryVO.getOrderByKeys())){
                crudeOilDemandQueryVO.setOrderByKeys(CrudeOilDemandOrderProptertyType.NONE.getCode());
            }
            modelMap.addAttribute("crudeOilDemandQuery", crudeOilDemandQueryVO);
            crudeOilDemandQueryVO.setType(Constant.DEMAND_TYPE_D);
            crudeOilDemandQueryVO.setDealType(DealType.SELL.getCode());
            PageInfoResult pageInfoResult =  demandService.queryCrudeOilDemandByCondition(crudeOilDemandQueryVO, pageInfo);
            List<DemandJoinResult> demandJoinResultList = pageInfoResult.getList();

            List<DemandJoinVO> demandJoinVOList = new ArrayList<>();
            for (DemandJoinResult demandJoinResult : demandJoinResultList) {
                DemandJoinVO demandJoinVO = DemandJoinVO.convertToVO(demandJoinResult);
                decorateDemandJoinVO(demandJoinVO, demandJoinResult);
                demandJoinVOList.add(demandJoinVO);
            }
            pageInfoResult.setList(demandJoinVOList);

            //查询所有发布信息条数
            Long total = pageInfoResult.getTotal();
            modelMap.put("pageInfoResult", pageInfoResult);
            modelMap.put("total",total);
            modelMap.put("user",user);

        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(ex.toString());
        }
        
        return "crudeoillobby/indexSell";
    }
    
    /**
     * 销售资源维护_原油大厅的销售资源详情
     * @param modelMap
     * @param demandId
     */
    @RolesAccess({MemberConstants.OBSERVER,MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER,MemberConstants.TRADE_EXECUTOR,MemberConstants.INSPECTION})
    @RequestMapping(value = UrlMapping.CRUDEOILLOBBY_SELLINGDEMANDDETAIL)
    public void querySellingDemand(CurrentUser user, ModelMap modelMap, @RequestParam("demandId") Long demandId) {
        try {
            if (demandId != null) {
                Demand demand = demandService.getDemandByKeyAndCurrentUser(demandId, user.getEpMemberId());
                DemandVO demandVO = DemandVO.convertToVO(demand);

                modelMap.put("demand", demandVO);

                DemandRelevanter buyer = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeTwo(demandId, DemandRelevanterType.SUPPLIER.getCode());
                EnterpriseVo ev = null;
                if (buyer != null) {
                    ev = enterpriseService.getByMemberId(buyer.getEMemberId());
                }
                modelMap.put("ev", ev);
            }
        } catch (BizException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(ex.toString());
        }
    }

    /**
     * 交易工具
     */
    @RolesAccess({MemberConstants.OBSERVER,MemberConstants.BUY_TRADER,MemberConstants.SALES_TRADER,MemberConstants.TRADE_EXECUTOR,MemberConstants.INSPECTION})
    @RequestMapping(value = UrlMapping.CRUDEOILLOBBY_INSTRUMENTOFEXCHANGE)
    public void instrumentOfExchange(){

    }
}



