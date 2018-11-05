package com.sinochem.crude.trade.listed.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sinochem.it.b2b.member.access.ApiSafeAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.enums.InspectionFeeSharingType;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.constant.MsgConstant;
import com.sinochem.crude.trade.listed.domain.CrudeOil;
import com.sinochem.crude.trade.listed.domain.Demand;
import com.sinochem.crude.trade.listed.domain.DemandRelevanter;
import com.sinochem.crude.trade.listed.domain.DemandShip;
import com.sinochem.crude.trade.listed.helper.DictUtils;
import com.sinochem.crude.trade.listed.model.vo.DemandShipVO;
import com.sinochem.crude.trade.listed.model.vo.deprecate.BiddingDetailVO;
import com.sinochem.crude.trade.listed.model.vo.deprecate.CrudeOilInfoVO;
import com.sinochem.crude.trade.listed.model.vo.deprecate.CrudeOilVO;
import com.sinochem.crude.trade.listed.model.vo.deprecate.DemandRelevanterVO;
import com.sinochem.crude.trade.listed.model.vo.deprecate.DemandVO;
import com.sinochem.crude.trade.listed.model.vo.deprecate.ResourceVO;
import com.sinochem.crude.trade.listed.service.CrudeOilResourceService;
import com.sinochem.crude.trade.listed.service.DemandRelevanterService;
import com.sinochem.crude.trade.listed.service.DemandService;
import com.sinochem.crude.trade.listed.service.DemandShipService;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.common.utils.DateUtil;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;

/**
 * 为移动端提供服务，功能与ResourceManagerController相同
 * @author Yichen Zhao
 * date: 20180103
 */
@Controller
@RequestMapping(value="app")
@ApiSafeAccess
public class AppApiCrudeOilTradeController {

    private final double division = 100.00;

    private final Logger LOGGER = LoggerFactory.getLogger(AppApiCrudeOilTradeController.class);

    @Autowired
    private DemandService demandService;

    @Autowired
    private DemandShipService demandShipService;

    @Autowired
    private DemandRelevanterService demandRelevanterService;

    @Autowired
    private CrudeOilResourceService crudeOilResourceService;

    @Autowired
    private CrudeOilInfoService crudeOilInfoService;

    /**
     * 资源信息,展示单个成品油的销售需求信息
     * @param demandId
     * @return
     */
    @RequestMapping(value = "resourceDetail")
    @ResponseBody
    public ResultDatas<ResourceVO> resourceDetail(Long demandId) {
        ResultDatas resultDatas = new ResultDatas();

        if( demandId == null ) {
            resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0035));
            return resultDatas;
        }

        Map<Integer, String> productOilKindMap = DictUtils.getProductOilKind();
        Map<Integer, String> productOilSpecsMap = DictUtils.getProductOilSpecs();
        Map<Object, String> tradeItemMap = DictUtils.getTradeItemMap();
        Map<Object, String> payItemMap = DictUtils.getPayItemMap();
        Map<Object, String> productOilClassifyMap = DictUtils.getProductOilClassifyMap();
        Map<Object, String> valuationCurrencyMap = DictUtils.getValuationCurrencyMap();
        Map<Object, String> pricingMeasureUnitMap = DictUtils.getPricingMeasureUnitMap();
        Map<Object, String> measureModeMap = DictUtils.getMeasureModeMap();
        Map<Object, String> valuationPeriodTypeMap = DictUtils.getValuationProidTypeMap(); //Period单词拼错
        Map<Object, String> productOilValuationBaseMap = DictUtils.getProductOilValuationBaseMap();
        Map<Object, String> creditItemMap = DictUtils.getCreditItem();
        Map<Object, String> businessCheckOrgMap = DictUtils.getBusinessCheckOrg();
        Map<Object, String> purchaseModesMap = DictUtils.getPurchaseModes();

        /*构建DemandVO*/
        Demand demand = null;
        try {
            demand = demandService.getDemandByKey(demandId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            resultDatas.setFail(e.getMessage());
            return resultDatas;
        }

        if( demand == null ) {
            resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0036));
            return resultDatas;
        }

        DemandVO demandVO = new DemandVO();

        demandVO.setId(demandId);
        demandVO.setUuid(demand.getUuid());
        demandVO.setStatus(demand.getStatus());
        demandVO.setPurchaseType(demand.getPurchaseType());
        demandVO.setPublishType(demand.getPublishType());
        demandVO.setCreater(demand.getCreater());
        demandVO.setLoadAndDischargePermittedTimespan(demand.getLoadAndDischargePermittedTimespan());
        
        String numFloat = demand.getNumfloat();
        if(!StringUtil.isEmpty(numFloat)) {
            demandVO.setNumfloat("+/- " + numFloat + " %");
        }

        Integer productOilClassify = demand.getProductOilClassify();
        demandVO.setProductOilClassify(productOilClassifyMap.get(productOilClassify));
        
        Integer productOilKind = demand.getProductOilKind();
        demandVO.setProductOilKind(productOilKindMap.get(productOilKind));

        Integer productOilSpecs = demand.getProductOilSpecs();
        demandVO.setProductOilSpecs(productOilSpecsMap.get(productOilSpecs));

        Integer tradeItem = demand.getTradeItem();
        demandVO.setTradeItem(tradeItemMap.get(tradeItem));

        demandVO.setPurchaseMode(purchaseModesMap.get(demand.getPurchaseMode()));

        demandVO.setNum(demand.getNum().toString());
        demandVO.setNumStr(demand.getNumStr());

        String payItem = demand.getPayItem();
        demandVO.setPayItem(payItem);

        String payItemJSON = demand.getPayItemJSON();
        demandVO.setPayItemJSON(demand.getPayItemJSON());

        Date stopBidTime = demand.getStopBidTime();
        demandVO.setStopBidTime(DateUtil.formatDate(stopBidTime));

        Date tenderOutTime = demand.getTenderOutTime();
        demandVO.setTenderOutTime(DateUtil.formatDate(tenderOutTime));

        Integer valuationCurrency = demand.getValuationCurrency();
        demandVO.setValuationCurrency(valuationCurrencyMap.get(valuationCurrency));

        setValuationBase(demandVO, demand.getValuationBase(), demand.getBizType());
        
        Integer pricingMeasureUnit = demand.getPricingMeasureUnit();
        demandVO.setPricingMeasureUnit(pricingMeasureUnitMap.get(pricingMeasureUnit));

        Integer valuationPeriodType = demand.getValuationProidType();
        String valuationPeriodTypeStr = "";
		if (valuationPeriodType != null) {
			if (valuationPeriodType == 4) {
				valuationPeriodTypeStr =
					valuationPeriodTypeMap.get(valuationPeriodType) +
					"("
					+ DateUtil.formatDate(demand.getValuationProidStart())
					+ VisitorLocale.getByCurrentLanguage(Constant.LISTED_0036)
					+ DateUtil.formatDate(demand.getValuationProidEnd())
					+")";
			} else {
				valuationPeriodTypeStr = valuationPeriodTypeMap.get(valuationPeriodType);
			}
		}	
		demandVO.setValuationPeriodType(valuationPeriodTypeStr);

        Date valuationPeriodStart = demand.getValuationProidStart();
        demandVO.setValuationPeriodStart(DateUtil.format(valuationPeriodStart, "yyyy-MM-dd"));

        Date valuationPeriodEnd = demand.getValuationProidEnd();
        demandVO.setValuationPeriodEnd(DateUtil.format(valuationPeriodEnd, "yyyy-MM-dd"));

        Integer measureMode = demand.getMeasureMode();
        demandVO.setMeasureMode(measureModeMap.get(measureMode));

        demandVO.setRemark(demand.getRemark());
        
        String crudeOilOptions = demand.getCrudeOilOptions();
        if(!StringUtil.isEmpty(crudeOilOptions)) {
        	if(crudeOilOptions.endsWith(",")) {
        		crudeOilOptions = crudeOilOptions.substring(0, crudeOilOptions.length() - 1);
        	}
        	
        	demandVO.setCrudeOilOptions(crudeOilOptions);
        }

        try {
            //CrudeOilInfoVO[] crudeOilsInfoVO = getCrudeOilsInfoVO(demandId);
            //demandVO.setCrudeOilsInfoVO(crudeOilsInfoVO);
            List<CrudeOil> curdeOilList = crudeOilResourceService.getCrudeListDemandId(demandId);
    		List<CrudeOilVO> curdeOilVoList = CrudeOilVO.convertDomainToVoList(curdeOilList);
    		demandVO.setCrudeOils(curdeOilVoList);
            
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            resultDatas.setFail(e.getMessage());
            return resultDatas;
        }

        try {
            setBuyerVO(demandVO, demandId);
            setAgentVO(demandVO, demandId);
            setSupplierVO(demandVO, demandId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            resultDatas.setFail(e.getMessage());
            return resultDatas;
        }

        demandVO.setOtherItem(demand.getOtherItem());

        Integer authItem = demand.getAuthItem();
        demandVO.setAuthItem(creditItemMap.get(authItem));

        String businessCheckOrg = demand.getBusinessCheckOrg();
        demandVO.setBusinessCheckOrg(businessCheckOrgMap.get(businessCheckOrg));

        demandVO.setExportConfFile(demand.getExportConfFile());

        Date pubDate = demand.getPubDate();
        if (pubDate != null) {
            demandVO.setPubDate(DateUtil.formatDate(pubDate));
        }

        /*构建DemandShipVO的Array*/
        DemandShipVO[] demandShipsVO;
        try {
            demandShipsVO = getDemandShipsVO(demandId);
        } catch(Exception e) {
            LOGGER.error(e.getMessage());
            resultDatas.setFail(e.getMessage());
            return resultDatas;
        }

        /*构建ResourceVO*/
        ResourceVO resourceVO = new ResourceVO();

        resourceVO.setDemandVO(demandVO);
        resourceVO.setDemandShipsVO(demandShipsVO);

        /*返回*/
        resultDatas.setDatas(resourceVO);
        return resultDatas;
    }

    /**
     * 报价详情,展示单个成品油或原油的投标详情信息
     * @param demandId
     * @return
     */
    @RequestMapping(value = "crudeOilBiddingDetail")
    @ResponseBody
    public ResultDatas<BiddingDetailVO> biddingDetail(Long demandId, String bidding)  {
        ResultDatas resultDatas = new ResultDatas();

        if( demandId == null ) {
            resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0035));
            return resultDatas;
        }

        Map<Integer, String> productOilKindMap = DictUtils.getProductOilKind();
        Map<Integer, String> productOilSpecsMap = DictUtils.getProductOilSpecs();
        Map<Object, String> tradeItemMap = DictUtils.getTradeItemMap();
        Map<Object, String> payItemMap = DictUtils.getPayItemMap();
        Map<Object, String> productOilClassifyMap = DictUtils.getProductOilClassifyMap();
        Map<Object, String> valuationCurrencyMap = DictUtils.getValuationCurrencyMap();
        Map<Object, String> pricingMeasureUnitMap = DictUtils.getPricingMeasureUnitMap();
        Map<Object, String> measureModeMap = DictUtils.getMeasureModeMap();
        Map<Object, String> valuationPeriodTypeMap = DictUtils.getValuationProidTypeMap(); //Period单词拼错
        Map<Object, String> productOilValuationBaseMap = DictUtils.getProductOilValuationBaseMap();
        Map<Object, String> creditItemMap = DictUtils.getCreditItem();
        Map<Object, String> businessCheckOrgMap = DictUtils.getBusinessCheckOrg();
        Map<Object, String> purchaseModesMap = DictUtils.getPurchaseModes();

        /*构建DemandVO*/
        Demand demand = null;
        try {
            demand = demandService.getDemandByKey(demandId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            resultDatas.setFail(e.getMessage());
            return resultDatas;
        }

        if( demand == null ) {
            resultDatas.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.LISTED0036));
            return resultDatas;
        }

        DemandVO demandVO = new DemandVO();

        demandVO.setId(demandId);
        demandVO.setUuid(demand.getUuid());
        demandVO.setStatus(demand.getStatus());
        String numFloat = demand.getNumfloat();
        if(!StringUtil.isEmpty(numFloat)) {
            demandVO.setNumfloat("+/- " + numFloat + " %");
        }
        demandVO.setPurchaseType(demand.getPurchaseType());
        demandVO.setPublishType(demand.getPublishType());

        Integer productOilKind = demand.getProductOilKind();
        demandVO.setProductOilKind(productOilKindMap.get(productOilKind));

        Integer productOilClassify = demand.getProductOilClassify();
        demandVO.setProductOilClassify(productOilClassifyMap.get(productOilClassify));

        Integer productOilSpecs = demand.getProductOilSpecs();
        demandVO.setProductOilSpecs(productOilSpecsMap.get(productOilSpecs));

        demandVO.setPurchaseMode(purchaseModesMap.get(demand.getPurchaseMode()));

        Integer tradeItem = demand.getTradeItem();
        demandVO.setTradeItem(tradeItemMap.get(tradeItem));

        demandVO.setNum(demand.getNum().toString());
        demandVO.setNumStr(demand.getNumStr());

        String payItem = demand.getPayItem();
        demandVO.setPayItem(payItem);

        String payItemJSON = demand.getPayItemJSON();
//        demandVO.setPayItemJSON(demand.getPayItemJSON());

        Date stopBidTime = demand.getStopBidTime();
        demandVO.setStopBidTime(DateUtil.formatDate(stopBidTime));

        Date tenderOutTime = demand.getTenderOutTime();
        demandVO.setTenderOutTime(DateUtil.formatDate(tenderOutTime));

        Integer valuationCurrency = demand.getValuationCurrency();
        demandVO.setValuationCurrency(valuationCurrencyMap.get(valuationCurrency));

        setValuationBase(demandVO, demand.getValuationBase(), demand.getBizType());

        Integer pricingMeasureUnit = demand.getPricingMeasureUnit();
        demandVO.setPricingMeasureUnit(pricingMeasureUnitMap.get(pricingMeasureUnit));

        Integer valuationPeriodType = demand.getValuationProidType();
        String valuationPeriodTypeStr = "";
		if (valuationPeriodType != null) {
			if (valuationPeriodType == 4) {
				valuationPeriodTypeStr =
					valuationPeriodTypeMap.get(valuationPeriodType) +
					"("
					+ DateUtil.formatDate(demand.getValuationProidStart())
					+ VisitorLocale.getByCurrentLanguage(Constant.LISTED_0036)
					+ DateUtil.formatDate(demand.getValuationProidEnd())
					+")";
			} else {
				valuationPeriodTypeStr = valuationPeriodTypeMap.get(valuationPeriodType);
			}
		}
		demandVO.setValuationPeriodType(valuationPeriodTypeStr);

        Date valuationPeriodStart = demand.getValuationProidStart();
        demandVO.setValuationPeriodStart(DateUtil.format(valuationPeriodStart, "yyyy-MM-dd"));

        Date valuationPeriodEnd = demand.getValuationProidEnd();
        demandVO.setValuationPeriodEnd(DateUtil.format(valuationPeriodEnd, "yyyy-MM-dd"));
        
        Integer measureMode = demand.getMeasureMode();
        demandVO.setMeasureMode(measureModeMap.get(measureMode));
        
        demandVO.setLoadAndDischargePermittedTimespan(demand.getLoadAndDischargePermittedTimespan());
        
        String crudeOilOptions = demand.getCrudeOilOptions();
        if(!StringUtil.isEmpty(crudeOilOptions)) {
        	if(crudeOilOptions.endsWith(",")) {
        		crudeOilOptions = crudeOilOptions.substring(0, crudeOilOptions.length() - 1);
        	}
        	
        	demandVO.setCrudeOilOptions(crudeOilOptions);
        }

        try {
            CrudeOilInfoVO[] crudeOilsInfoVO = getCrudeOilsInfoVO(demandId);
            demandVO.setCrudeOilsInfoVO(crudeOilsInfoVO);
//          List<CrudeOil> curdeOilList = crudeOilResourceService.getCrudeListDemandId(demandId);
//    		List<CrudeOilVO> curdeOilVoList = CrudeOilVO.convertDomainToVoList(curdeOilList);
//    		demandVO.setCrudeOils(curdeOilVoList);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            resultDatas.setFail(e.getMessage());
            return resultDatas;
        }

        demandVO.setParentId(demand.getParentId());

        Long agio = demand.getAgio();
        if (agio != null) {
            demandVO.setAgio(BigDecimal.valueOf(agio).divide(new BigDecimal(1000)).setScale(3).toString());
        }

        demandVO.setValuationFormula(demand.getValuationFormula());
        demandVO.setRemark(demand.getRemark());

        try {
            setBuyerVO(demandVO, demandId);
            setAgentVO(demandVO, demandId);
            setSupplierVO(demandVO, demandId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            resultDatas.setFail(e.getMessage());
            return resultDatas;
        }

        demandVO.setOtherItem(demand.getOtherItem());

        Integer authItem = demand.getAuthItem();
        demandVO.setAuthItem(creditItemMap.get(authItem));

        String businessCheckOrg = demand.getBusinessCheckOrg();
        demandVO.setBusinessCheckOrg(businessCheckOrgMap.get(businessCheckOrg));

        demandVO.setExportConfFile(demand.getExportConfFile());
        demandVO.setParentId(demand.getParentId());

        Date pubDate = demand.getPubDate();
        if (pubDate != null) {
            demandVO.setPubDate(DateUtil.formatDate(pubDate));
        }

        // 商检费分摊
        Integer inspectionFeeSharingType = demand.getInspectionFeeSharingType();
        if (inspectionFeeSharingType != null) {
            InspectionFeeSharingType type = InspectionFeeSharingType.getInspectionFeeSharingTypeByCode(inspectionFeeSharingType);
            if (type != null) {
                demandVO.setInspectionFeeSharingType(type.getName());
            }
        }

        // 法律
        demandVO.setLaw(demand.getLaw());
        
        // GTC
        demandVO.setGtc(demand.getGtc());
        
        /*构建DemandShipVO的Array*/
        DemandShipVO[] demandShipsVO;
        try {
            demandShipsVO = getDemandShipsVO(demandId);
        } catch(Exception e) {
            LOGGER.error(e.getMessage());
            resultDatas.setFail(e.getMessage());
            return resultDatas;
        }

        /*构建BiddingDetaiilVO*/
        BiddingDetailVO biddingDetailVO = new BiddingDetailVO();

        biddingDetailVO.setBidding(bidding);
        biddingDetailVO.setDemandVO(demandVO);
        biddingDetailVO.setDemandShipsVO(demandShipsVO);

        /*返回*/
        resultDatas.setDatas(biddingDetailVO);
        return resultDatas;
    }

    /*类内辅助方法*/
    private void setValuationBase(DemandVO demandVO, String valuationBase, String bizType) {
    	if(!StringUtil.isEmpty(bizType) && !StringUtil.isEmpty(valuationBase)) {
    		if("CrudeOil".equals(bizType)) {
    			Map<Object, String> valuationBaseMap = DictUtils.getValuationBaseMap();
    			demandVO.setValuationBase(valuationBaseMap.get(valuationBase));
    		} else if("ProductOil".equals(bizType)) {
    			Map<Object, String> productOilValuationBaseMap = DictUtils.getProductOilValuationBaseMap();
    			demandVO.setValuationBase(productOilValuationBaseMap.get(valuationBase));
    		}
    	}
    }
    
    /*构建DemandShipVO的Array*/
    private DemandShipVO[] getDemandShipsVO(Long demandId) throws Exception {
        List<DemandShip> demandShips = demandShipService.getShipByDemandId(demandId);
        int demandShipCount = demandShips.size();
        DemandShipVO[] demandShipsVO = new DemandShipVO[demandShipCount];

        for( int i = 0; i < demandShipCount; i++ ) {
            DemandShip demandShip = demandShips.get(i);
            DemandShipVO demandShipVO = new DemandShipVO();

            demandShipVO.setShipmentPort(demandShip.getShipmentPort());
            Date shipmentStartTime = demandShip.getShipmentStartTime();
            Date shipmentEndTime = demandShip.getShipmentEndTime();
            
            String shipmentPeriod = "";
            if (shipmentStartTime != null && shipmentEndTime != null) {
            	shipmentPeriod = DateUtil.formatDate(shipmentStartTime) + VisitorLocale.getByCurrentLanguage(Constant.LISTED_0036) + DateUtil.formatDate(shipmentEndTime);
            } else {
            	shipmentPeriod = "";
            }
            demandShipVO.setShipmentPeriod(shipmentPeriod);
            
            demandShipVO.setDischargePort(demandShip.getDischargePort());
            Date dischargeStartTime = demandShip.getDischargeStartTime();
            Date dischargeEndTime = demandShip.getDischargeEndTime();
            String dischargePeriod = "";
            
            if (dischargeStartTime != null && dischargeEndTime != null) {
            	dischargePeriod = DateUtil.formatDate(dischargeStartTime) + VisitorLocale.getByCurrentLanguage(Constant.LISTED_0036) + DateUtil.formatDate(dischargeEndTime);
            } else {
            	dischargePeriod = "";
            }
            
            demandShipVO.setDischargePeriod(dischargePeriod);

            demandShipsVO[i] = demandShipVO;
        }

        return demandShipsVO;
    }

    private CrudeOilInfoVO[] getCrudeOilsInfoVO(Long demandId) throws Exception {
        List<CrudeOil> crudeOils = crudeOilResourceService.getCrudeListDemandId(demandId);
        int crudeOilResourceCount = crudeOils.size();
        CrudeOilInfoVO[] crudeOilsInfoVO = new CrudeOilInfoVO[crudeOilResourceCount];
        		
        com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO temp = new com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO(); //这是service机制的原因，目前仅能发现该方法能够将原有产地的代号和名称进行对应
        for( int i = 0; i < crudeOilResourceCount; i++ ) {
        	CrudeOil crudeOil = crudeOils.get(i);
        	CrudeOilInfoVO crudeOilInfoVO = new CrudeOilInfoVO();
        	
            if( crudeOil.getOrigin() != null ) {
            	temp = crudeOilInfoService.findByENameAndAreaId(crudeOil.getOrigin());
            	crudeOilInfoVO.setOriginNameC(temp.getOriginNameC());
            }

            crudeOilInfoVO.setCrudeNameC(crudeOil.getName());

            Long indicator1Min = crudeOil.getIndicator1Min();
            if (indicator1Min != null) {
                crudeOilInfoVO.setApi(String.valueOf(indicator1Min / division));
            }

            Long indicator2Min = crudeOil.getIndicator2Min();
            if (indicator2Min != null) {
                crudeOilInfoVO.setSulphur(String.valueOf(indicator2Min / division));
            }

            Long indicator3Min = crudeOil.getIndicator3Min();
            if (indicator3Min != null) {
                crudeOilInfoVO.setAcidity(String.valueOf(indicator3Min / division));
            }

            Long indicator4Min = crudeOil.getIndicator4Min();
            if (indicator4Min != null) {
                crudeOilInfoVO.setCarbonResidue(String.valueOf(indicator4Min / division));
            }

            Long indicator5Min = crudeOil.getIndicator5Min();
            if (indicator5Min != null) {
                crudeOilInfoVO.setNickel(String.valueOf(indicator5Min / division));
            }

            Long indicator6Min = crudeOil.getIndicator6Min();
            if (indicator6Min != null) {
                crudeOilInfoVO.setVanadium(String.valueOf(indicator6Min / division));
            }

            Long indicator7Min = crudeOil.getIndicator7Min();
            if (indicator7Min != null) {
                crudeOilInfoVO.setYield(String.valueOf(indicator7Min / division));
            }

            crudeOilInfoVO.setDesc(crudeOil.getDesc());
            
            crudeOilsInfoVO[i] = crudeOilInfoVO;
        }

        return crudeOilsInfoVO;
    }

    private void setBuyerVO(DemandVO demandVO, Long demandId) throws Exception {
        DemandRelevanterVO buyerVO = new DemandRelevanterVO(); //购买商信息
        DemandRelevanter buyer = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeTwo(demandId,"CBB");
        if( buyer != null ) {
            fillDemandRelevanterVO(buyerVO, buyer);
            demandVO.setBuyer(buyerVO);
        }
    }

    private void setAgentVO(DemandVO demandVO, Long demandId) throws Exception {
        DemandRelevanterVO agentVO = new DemandRelevanterVO(); //代理商信息
        DemandRelevanter agent = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeTwo(demandId,"CBT");
        if( agent != null ) {
            fillDemandRelevanterVO(agentVO, agent);
            demandVO.setAgent(agentVO);
        }
    }

    private void setSupplierVO(DemandVO demandVO, Long demandId) throws Exception {
        DemandRelevanterVO supplierVO = new DemandRelevanterVO(); //供应商信息
        DemandRelevanter supplier = demandRelevanterService.getDemandRelevanterByDemandIdAndTypeTwo(demandId,"P");
        if( supplier != null ) {
            fillDemandRelevanterVO(supplierVO, supplier);
            demandVO.setSupplier(supplierVO);
        }
    }

    private void fillDemandRelevanterVO(DemandRelevanterVO relevanterVO, DemandRelevanter relevanter) {
        relevanterVO.setEnterpriseName(relevanter.getEnterpriseName());
        relevanterVO.setEnterpriseAddress(relevanter.getEnterpriseAddress());
        relevanterVO.setContacter(relevanter.getContacter());
        relevanterVO.setEmail(relevanter.getFamil());
        relevanterVO.setPhoneNo(relevanter.getPhoneNo());
        relevanterVO.setFax(relevanter.getFax());
    }
}
