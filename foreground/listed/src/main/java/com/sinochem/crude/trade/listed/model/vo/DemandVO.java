package com.sinochem.crude.trade.listed.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.enums.InspectionFeeSharingType;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.domain.Demand;
import com.sinochem.crude.trade.listed.enums.DemandBiddingType;
import com.sinochem.crude.trade.listed.enums.DemandPublishType;
import com.sinochem.crude.trade.listed.enums.PurchaseType;
import com.sinochem.crude.trade.listed.helper.DictUtils;
import com.sinochem.it.b2b.common.utils.DateUtil;
import com.sinochem.it.b2b.common.utils.VisitorLocale;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;

/**
 * Demand类的VO
 * @author Yichen Zhao
 * date: 20180103
 */
public class DemandVO implements Serializable {

    private static final long serialVersionUID = -2783512481528317059L;

    /**
     * 计价期类型
     */
    private Integer valuationProidType;

    /**
     * 计价期类型内容， 通过字典获得
     */
    private String valuationPeriodTypeContent;

    /**
     * 计价期类型内容，是实体类遗留内容，重构时可删除
     */
    private String valuationProidTypeStr;

    /**
     * 计价期开始时间
     */
    private Date valuationProidStart;

    /**
     * 计价期开始时间（String）
     */
    private String valuationPeriodStartAsString;

    /**
     * 计价期结束时间
     */
    private Date valuationProidEnd;

    /**
     * 计价期结束时间
     */
    private String valuationPeriodEndAsString;

    /**
     * 主键
     */
    private Long id;

    /**
     * 数量
     */
    private Long num;

    /**
     * 数量, 实体遗留字段，重构可考虑删除粗
     */
    private String numStr;

    /**
     * 溢短装
     */
    private String numfloat;

    /**
     * 贸易条款
     */
    private Integer tradeItem;

    /**
     * 贸易条款具体内容，由tradeItem结合字典得来
     */
    private String tradeItemContent;

    /**
     * 贸易条款具体内容，是实体类遗留字段，可在重构时删去
     */
    private String tradeItemStr;

    /**
     * 贴水
     */
    private Long agio;

    /**
     * 贴水，实体遗留字段，重构可考虑删除
     */
    private BigDecimal agioStr;

    /**
     * 计价基准
     */
    private String valuationBase;

    /**
     * 计价基准的具体内容，通过字典获得
     */
    private String valuationBaseContent;

    /**
     * 付款条款
     */
    private String payItem;

    public String getPayItem() {
        return payItem;
    }

    public void setPayItem(String payItem) {
        this.payItem = payItem;
    }

    /**
     * 付款日期Json串
     */
    private String payItemJSON;

    public void setPayItemJSON(String payItemJSON) {
        this.payItemJSON = payItemJSON;
    }

    public String getPayItemJSON() {
        return payItemJSON;
    }

    /**
     * 付款条款具体内容，通过字典获得
     */
    private String payItemContent;

    /**
     * 付款日期
     */
    private String payTime;

    /**
     * 报价类型（投标、询价）
     */
    private Integer purchaseType;
    private String purchaseTypeContent;

    /**
     * 创建人
     */
    private Long creater;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建时间(String)
     */
    private String createTimeAsString;

    /**
     * 更新人
     */
    private Long updater;

    /**
     * 更新时间
     */
    private Date updaterTime;

    /**
     * 更新时间(String)
     */
    private String updateTimeAsString;

    /**
     * 父节点id
     */
    private Long parentId;

    /**
     * 计价公式
     */
    @Length(max=1000)
    private String valuationFormula;

    /**
     * 计价公式，实体类遗留字段，且不知有何作用。重构时可考虑删除
     */
    private String valuationFormulaJson;

    /**
     * 类型（D - 需求 B - 报价）
     */
    private String type;

    /**
     * 交易类型（买、卖）
     */
    private String dealType;

    /**
     * 业务类型
     */
    private String bizType;

    /**
     * 成品油Id，实体遗留字段，意义不明，重构时可酌情删除
     */
    private String productOilId;

    /**
     * 其他条款
     */
    private String otherItem;

    /**
     * 信用条款
     */
    private Integer authItem;

    /**
     * 信用条款内容，通过字典获得
     */
    private String authItemContent;

    /**
     * 信用条款内容，实体类遗留字段，重构时可考虑删除
     */
    private String authItemStr;

    /**
     * 商检机构
     */
    private String businessCheckOrg;

    /**
     * 商检机构内容，通过字典获得
     */
    private String businessCheckOrgContent;

    /**
     * 出口配额文件
     */
    private String exportConfFile;

    /**
     * 备注
     */
    @Length(max=2000)
    private String remark;

    /**
     * 出标时间
     */
    private Date tenderOutTime;

    /**
     * 出标时间（String）
     */
    private String tenderOutTimeAsString;

    /**
     * 截标时间
     */
    private Date stopBidTime;

    /**
     * 截标时间（String）
     */
    private String stopBidTimeAsString;

    /**
     * 响应开始时间
     */
    private Date responseStartTime;

    /**
     * 响应开始时间（String）
     */
    private String responseStartTimeAsString;

    /**
     * 响应结束时间
     */
    private Date responseEndTime;

    /**
     * 响应结束时间（String）
     */
    private String responseEndTimeAsString;

    /**
     * 单据编号
     */
    private String uuid;

    /**
     * 原油油种
     */
    private String crudeOilOptions;

    /**
     * 成品油分类
     */
    private Integer productOilClassify;

    /**
     * 成品油分类内容，通过字典获得
     */
    private String productOilClassifyContent;

    /**
     * 成品油种类
     */
    private Integer productOilKind;

    /**
     * 成品油种类内容，通过字典获得
     */
    private String productOilKindContent;

    /**
     * 成品油规格
     */
    private Integer productOilSpecs;

    /**
     * 成品油规格内容，通过字典获得
     */
    private String productOilSpecsContent;

    /**
     * 状态，包括需求状态和报价状态
     */
    private Integer status;

    /**
     * 是否代理
     */
    private Integer isAgent;

    /**
     * 代理商
     */
    private Long agenter;

    /**
     * 出口类型
     */
    private Integer exportType;

    /**
     * 出口类型内容，通过字典获得
     */
    private String exportTypeContent;


    /**
     * 质检报告
     */
    private String qcReport;

    /**
     * 价格方式
     */
    private Integer pricingMode;

    /**
     * 价格方式内容，通过字典获得
     */
    private String pricingModeContent;

    /**
     * 价格计量单位
     */
    private Integer pricingMeasureUnit;

    /**
     * 价格计量单位内容, 通过字典获得
     */
    private String pricingMeasureUnitContent;

    /**
     * 计量方式
     */
    private Integer measureMode;

    /**
     * 计量方式内容，通过字典获得
     */
    private String measureModeContent;

    /**
     * 价格说明
     */
    private String priceDeclare;

    /**
     * 是否密封
     */
    private Integer isSeal;

    /**
     * 计价货币
     */
    private Integer valuationCurrency;

    /**
     * 计价货币内容，通过字典获得
     */
    private String valuationCurrencyContent;

    /**
     * 单价（美元/桶）
     */
    private Integer unitPrice;

    /**
     * 采购方式
     */
    private Integer purchaseMode;

    /**
     * 采购方式内容，通过字典获得
     */
    private String purchaseModeContent;

    /**
     * 发布时间
     */
    private Date pubDate;

    /**
     * 发布时间（String）
     */
    private String pubDateAsString;

    /**
     * 地区
     */
    private Integer region;

    /**
     * 地区内容，通过字典获得
     */
    private String regionContent;

    /**
     * 商品图片ID，多个间","分割
     */
    private String goodsImages;

    /**
     * 是否指定发布（1-是，0-否）
     */
    private Integer specified;

    /**
     * 需求发布类型，1-按油种，2-按性质
     */
    private Integer publishType;
    private String publishTypeContent;

    /**
     * 报价类型，1-意向报价，2-正式报价
     */
    private Integer biddingType;
    private String biddingTypeContent;

    /**
     * 允许装卸货时间
     */
    private String loadAndDischargePermittedTimespan;

    /**
     * 合约种类
     */
    private String contractKind;

    /**
     * 商检费分摊类型
     */
    private Integer inspectionFeeSharingType;
    private String inspectionFeeSharingTypeContent;
    /**
     * 法律
     */
    @Length(max=100)
    private String law;
    /**
     * gtc
     */
    @Length(max=100)
    private String gtc;

    public String getLaw() {
        return law;
    }

    public void setLaw(String law) {
        this.law = law;
    }

    public String getGtc() {
        return gtc;
    }

    public void setGtc(String gtc) {
        this.gtc = gtc;
    }

    /**
     * 价格类型
     */
    private Integer priceType;
    private String priceTypeContent;

    private boolean overdue;
    
    /**
     * 提供从实体类转换成VO的方法
     * @param demand
     * @return
     */
    public static DemandVO convertToVO(Demand demand) {
        if (demand == null) {
            return null;
        }

        Map<Integer, String> productOilKindMap = DictUtils.getProductOilKind();
        Map<Integer, String> productOilSpecsMap = DictUtils.getProductOilSpecs();
        Map<Object, String> tradeItemMap = DictUtils.getTradeItemMap();
        Map<Object, String> payItemMap = DictUtils.getPayItemMap();
        Map<Object, String> productOilClassifyMap = DictUtils.getProductOilClassifyMap();
        Map<Object, String> valuationCurrencyMap = DictUtils.getValuationCurrencyMap();
        Map<Object, String> pricingMeasureUnitMap = DictUtils.getPricingMeasureUnitMap();
        Map<Object, String> creditItemMap = DictUtils.getCreditItem();
        Map<Object, String> businessCheckOrgMap = DictUtils.getBusinessCheckOrg();
        Map<Object, String> purchaseModesMap = DictUtils.getPurchaseModes();
        Map<Object, String> exportTypeMap = DictUtils.getExportType();
        Map<Object, String> pricingModeMap = DictUtils.getPricingModeMap();
        Map<Object, String> measureModeMap = DictUtils.getMeasureModeMap();
        Map<Object, String> regionProductOilMap = DictUtils.getRegionProductOil();
        Map<Object, String> valuationPeriodTypeMap = DictUtils.getValuationProidTypeMap();
        Map<Object, String> priceTypeMap = DictUtils.getPriceTypeMap();

        DemandVO demandVO = new DemandVO();

        /*可考虑删除的属性*/
        demandVO.setTradeItemStr(demand.getTradeItemStr());
        demandVO.setValuationProidTypeStr(demand.getValuationProidTypeStr());
        demandVO.setValuationFormulaJson(demand.getValuationFormulaJson());
        demandVO.setProductOilId(demand.getProductOilId());
        demandVO.setAuthItemStr(demand.getAuthItemStr());
        demandVO.setNumStr(demand.getNumStr());
        demandVO.setAgioStr(demand.getAgioStr());
        /**/

        demandVO.setId(demand.getId());
        demandVO.setNum(demand.getNum());
        demandVO.setNumfloat(demand.getNumfloat());

        String payItemJson = demand.getPayItemJSON();
        demandVO.setPayItemJSON(payItemJson);
        Integer tradeItem = demand.getTradeItem();
        demandVO.setTradeItem(tradeItem);
        if (tradeItem != null) {
            demandVO.setTradeItemContent(tradeItemMap.get(tradeItem));
        }

        demandVO.setAgio(demand.getAgio());

        String valuationBase = demand.getValuationBase();
        String bizType = demand.getBizType();
        demandVO.setValuationBase(valuationBase);
        demandVO.setBizType(bizType);
        setValuationBase(demandVO, valuationBase, bizType);

        String payItem = demand.getPayItem();
        demandVO.setPayItem(payItem);
        if (payItem != null) {
            demandVO.setPayItemContent(payItem);
        }

        demandVO.setPayTime(demand.getPayTime());

        Integer purchaseType = demand.getPurchaseType();
        if (purchaseType != null) {
            demandVO.setPurchaseType(purchaseType);
            demandVO.setPurchaseTypeContent(PurchaseType.getPurchaseTypeByCode(purchaseType).getName());
        }

        demandVO.setCreater(demand.getCreater());

        Date createTime = demand.getCreateTime();
        demandVO.setCreateTime(createTime);
        if (createTime != null) {
            demandVO.setCreateTimeAsString(DateUtil.formatDate(createTime));
        }

        demandVO.setUpdater(demand.getUpdater());

        Date updateTime = demand.getUpdaterTime();
        demandVO.setUpdaterTime(updateTime);
        if (updateTime != null) {
            demandVO.setUpdateTimeAsString(DateUtil.formatDate(updateTime));
        }

        demandVO.setParentId(demand.getParentId());
        demandVO.setValuationFormula(demand.getValuationFormula());
        demandVO.setType(demand.getType());
        demandVO.setDealType(demand.getDealType());
        demandVO.setBizType(demand.getBizType());
        demandVO.setOtherItem(demand.getOtherItem());

        Integer authItem = demand.getAuthItem();
        demandVO.setAuthItem(authItem);
        if (authItem != null) {
            demandVO.setAuthItemContent(creditItemMap.get(authItem));
        }

        String businessCheckOrg = demand.getBusinessCheckOrg();
        demandVO.setBusinessCheckOrg(businessCheckOrg);
        if (!StringUtil.isEmpty(businessCheckOrg)) {
            demandVO.setBusinessCheckOrgContent(businessCheckOrgMap.get(businessCheckOrg));
        }

        demandVO.setExportConfFile(demand.getExportConfFile());
        demandVO.setRemark(demand.getRemark());

        Date tenderOutTime = demand.getTenderOutTime();
        demandVO.setTenderOutTime(tenderOutTime);
        if (tenderOutTime != null) {
            demandVO.setTenderOutTimeAsString(DateUtil.formatDate(tenderOutTime));
        }

        Date stopBidTime = demand.getStopBidTime();
        demandVO.setStopBidTime(stopBidTime);
        if (stopBidTime != null) {
            demandVO.setStopBidTimeAsString(DateUtil.formatDate(stopBidTime));
        }

        Date responseStartTime = demand.getResponseStartTime();
        demandVO.setResponseStartTime(responseStartTime);
        if (responseStartTime != null) {
            demandVO.setResponseStartTimeAsString(DateUtil.formatDate(responseStartTime));
        }

        Date responseEndTime = demand.getResponseEndTime();
        demandVO.setResponseEndTime(responseEndTime);
        if (responseEndTime != null) {
            demandVO.setResponseEndTimeAsString(DateUtil.formatDate(responseEndTime));
        }

        demandVO.setUuid(demand.getUuid());

        String crudeOilOptions = demand.getCrudeOilOptions();
        if (!StringUtil.isEmpty(crudeOilOptions)) {
            int index = crudeOilOptions.lastIndexOf(",");
            if (index != -1) {
                crudeOilOptions = crudeOilOptions.substring(0, index);
            }

            demandVO.setCrudeOilOptions(crudeOilOptions);
        }

        Integer productOilClassify = demand.getProductOilClassify();
        demandVO.setProductOilClassify(productOilClassify);
        if (productOilClassify != null) {
            demandVO.setProductOilClassifyContent(productOilClassifyMap.get(productOilClassify));
        }

        Integer productOilKind = demand.getProductOilKind();
        demandVO.setProductOilKind(productOilKind);
        if (productOilKind != null) {
            demandVO.setProductOilKindContent(productOilKindMap.get(productOilKind));
        }

        Integer productOilSpecs = demand.getProductOilSpecs();
        demandVO.setProductOilSpecs(productOilSpecs);
        if (productOilSpecs != null) {
            demandVO.setProductOilSpecsContent(productOilSpecsMap.get(productOilSpecs));
        }

        demandVO.setStatus(demand.getStatus());
        demandVO.setIsAgent(demand.getIsAgent());
        demandVO.setAgenter(demand.getAgenter());

        Integer exportType = demand.getExportType();
        demandVO.setExportType(exportType);
        if (exportType != null) {
            demandVO.setExportTypeContent(exportTypeMap.get(exportType));
        }

        demandVO.setQcReport(demand.getQcReport());

        Integer pricingMode = demand.getPricingMode();
        demandVO.setPricingMode(pricingMode);
        if (pricingMode != null) {
            demandVO.setPricingModeContent(pricingModeMap.get(pricingMode));
        }

        Integer pricingMeasureUnit = demand.getPricingMeasureUnit();
        demandVO.setPricingMeasureUnit(pricingMeasureUnit);
        if (pricingMeasureUnit != null) {
            demandVO.setPricingMeasureUnitContent(pricingMeasureUnitMap.get(pricingMeasureUnit));
        }

        Integer measureMode = demand.getMeasureMode();
        demandVO.setMeasureMode(demand.getMeasureMode());
        if (measureMode != null) {
            demandVO.setMeasureModeContent(measureModeMap.get(measureMode));
        }

        demandVO.setPriceDeclare(demand.getPriceDeclare());
        demandVO.setIsSeal(demand.getIsSeal());

        Integer valuationCurrency = demand.getValuationCurrency();
        demandVO.setValuationCurrency(valuationCurrency);
        if (valuationCurrency != null) {
            demandVO.setValuationCurrencyContent(valuationCurrencyMap.get(valuationCurrency));
        }

        Integer valuationProidType = demand.getValuationProidType();
        demandVO.setValuationProidType(valuationProidType);
        if (valuationProidType != null) {
            demandVO.setValuationPeriodTypeContent(valuationPeriodTypeMap.get(valuationProidType));
        }

        Date valuationProidStart = demand.getValuationProidStart();
        demandVO.setValuationProidStart(valuationProidStart);
        if (valuationProidStart != null) {
            demandVO.setValuationPeriodStartAsString(DateUtil.formatDate(valuationProidStart));
        }

        Date valuationProidEnd = demand.getValuationProidEnd();
        demandVO.setValuationProidEnd(valuationProidEnd);
        if (valuationProidEnd != null) {
            demandVO.setValuationPeriodEndAsString(DateUtil.formatDate(valuationProidEnd));
        }

        demandVO.setUnitPrice(demand.getUnitPrice());

        Integer purchaseMode = demand.getPurchaseMode();
        demandVO.setPurchaseMode(purchaseMode);
        if (purchaseMode != null) {
            demandVO.setPurchaseModeContent(purchaseModesMap.get(purchaseMode));
        }

        Date pubDate = demand.getPubDate();
        demandVO.setPubDate(pubDate);
        if (pubDate != null) {
            demandVO.setPubDateAsString(DateUtil.formatDate(pubDate));
        }

        Integer region = demand.getRegion();
        demandVO.setRegion(region);
        if (region != null) {
            demandVO.setRegionContent(regionProductOilMap.get(region));
        }

        demandVO.setGoodsImages(demand.getGoodsImages());

        demandVO.setSpecified(demand.getSpecified());

        Integer publishType = demand.getPublishType();
        demandVO.setPublishType(publishType);
        if (publishType != null) {
            demandVO.setPublishTypeContent(DemandPublishType.getDemandPublishTypeByCode(publishType).getName());
        }

        Integer biddingType = demand.getBiddingType();
        demandVO.setBiddingType(biddingType);
        if (biddingType != null) {
            demandVO.setBiddingTypeContent(DemandBiddingType.getDemandBiddingTypeByCode(biddingType).getName());
        }

        demandVO.setLoadAndDischargePermittedTimespan(demand.getLoadAndDischargePermittedTimespan());
        demandVO.setContractKind(demand.getContractKind());

        Integer inspectionFeeSharingType = demand.getInspectionFeeSharingType();
        if (inspectionFeeSharingType != null) {
            demandVO.setInspectionFeeSharingType(inspectionFeeSharingType);

            InspectionFeeSharingType type = InspectionFeeSharingType.getInspectionFeeSharingTypeByCode(inspectionFeeSharingType);
            if (type != null) {
                demandVO.setInspectionFeeSharingTypeContent(type.getName());
            }
        }

        demandVO.setLaw(demand.getLaw());
        demandVO.setGtc(demand.getGtc());

        // 价格类型
        Integer priceType = demand.getPriceType();
        if(priceType != null) {
        	demandVO.setPriceType(demand.getPriceType());
        	demandVO.setPriceTypeContent(priceTypeMap.get(demand.getPriceType()));
        }

        if(stopBidTime != null){
            try {
                Date today = com.eyeieye.melody.util.DateUtil.getToday().getTime();
                demandVO.setOverdue(today.after(stopBidTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else{
            demandVO.setOverdue(false);
        }
        
        return demandVO;
    }
    
    
    public static List<DemandVO> convertDomainToVOList(List<Demand> demands) {
    	List<DemandVO> vos = new ArrayList<>();
    	for(Demand item : demands) {
    		vos.add(DemandVO.convertToVO(item));
    	}
		return vos;
	}

    /**
     * 提供从VO转换成实体类的方法
     * @param demandVO
     * @return
     */
    public static Demand convertToDomain(DemandVO demandVO) {
        if (demandVO == null) {
            return null;
        }

        Demand demand = new Demand();

        demand.setId(demandVO.getId());
        demand.setNumStr(demandVO.getNumStr());
        demand.setNum(demandVO.getNum());
        demand.setNumfloat(demandVO.getNumfloat());
        demand.setTradeItem(demandVO.getTradeItem());
        demand.setTradeItemStr(demandVO.getTradeItemStr());
        demand.setAgioStr(demandVO.getAgioStr());
        demand.setAgio(demandVO.getAgio());
        demand.setValuationBase(demandVO.getValuationBase());
        demand.setValuationProidType(demandVO.getValuationProidType());
        demand.setValuationProidTypeStr(demandVO.getValuationProidTypeStr());
        demand.setPayItem(demandVO.getPayItem());
        demand.setPayItemJSON(demandVO.getPayItemJSON());
        demand.setPayTime(demandVO.getPayTime());
        demand.setPurchaseType(demandVO.getPurchaseType());
        demand.setCreater(demandVO.getCreater());
        demand.setCreateTime(demandVO.getCreateTime());
        demand.setUpdater(demandVO.getUpdater());
        demand.setUpdaterTime(demandVO.getUpdaterTime());
        demand.setParentId(demandVO.getParentId());
        demand.setValuationFormulaJson(demandVO.getValuationFormulaJson());
        demand.setValuationFormula(demandVO.getValuationFormula());
        demand.setType(demandVO.getType());
        demand.setDealType(demandVO.getDealType());
        demand.setProductOilId(demandVO.getProductOilId());
        demand.setOtherItem(demandVO.getOtherItem());
        demand.setAuthItem(demandVO.getAuthItem());
        demand.setAuthItemStr(demandVO.getAuthItemStr());
        demand.setBusinessCheckOrg(demandVO.getBusinessCheckOrg());
        demand.setExportConfFile(demandVO.getExportConfFile());
        demand.setRemark(demandVO.getRemark());
        demand.setTenderOutTime(demandVO.getTenderOutTime());
        demand.setStopBidTime(demandVO.getStopBidTime());
        demand.setResponseStartTime(demandVO.getResponseStartTime());
        demand.setResponseEndTime(demandVO.getResponseEndTime());
        demand.setUuid(demandVO.getUuid());
        demand.setCrudeOilOptions(demandVO.getCrudeOilOptions());
        demand.setProductOilClassify(demandVO.getProductOilClassify());
        demand.setProductOilKind(demandVO.getProductOilKind());
        demand.setProductOilSpecs(demandVO.getProductOilSpecs());
        demand.setStatus(demandVO.getStatus());
        demand.setIsAgent(demandVO.getIsAgent());
        demand.setAgenter(demandVO.getAgenter());
        demand.setExportType(demandVO.getExportType());
        demand.setQcReport(demandVO.getQcReport());
        demand.setPricingMeasureUnit(demandVO.getPricingMeasureUnit());
        demand.setPricingMode(demandVO.getPricingMode());
        demand.setPriceDeclare(demandVO.getPriceDeclare());
        demand.setIsSeal(demandVO.getIsSeal());
        demand.setValuationCurrency(demandVO.getValuationCurrency());
        demand.setValuationProidStart(demandVO.getValuationProidStart());
        demand.setValuationProidEnd(demandVO.getValuationProidEnd());
        demand.setUnitPrice(demandVO.getUnitPrice());
        demand.setPurchaseMode(demandVO.getPurchaseMode());
        demand.setPubDate(demandVO.getPubDate());
        demand.setRegion(demandVO.getRegion());
        demand.setGoodsImages(demandVO.getGoodsImages());
        demand.setMeasureMode(demandVO.getMeasureMode());
        demand.setSpecified(demandVO.getSpecified());
        demand.setPublishType(demandVO.getPublishType());
        demand.setBiddingType(demandVO.getBiddingType());
        demand.setLoadAndDischargePermittedTimespan(demandVO.getLoadAndDischargePermittedTimespan());
        demand.setContractKind(demandVO.getContractKind());
        demand.setBizType(demandVO.getBizType());
        demand.setInspectionFeeSharingType(demandVO.getInspectionFeeSharingType());
        demand.setLaw(demandVO.getLaw());
        demand.setGtc(demandVO.getGtc());
        demand.setPriceType(demandVO.getPriceType());
        
        return demand;
    }

    /*类内辅助方法*/
    private static void setValuationBase(DemandVO demandVO, String valuationBase, String bizType) {
        if(demandVO != null && !StringUtil.isEmpty(bizType) && !StringUtil.isEmpty(valuationBase)) {
            Map<Object, String> valuationBaseMap = DictUtils.getValuationBaseMap();
            demandVO.setValuationBaseContent(valuationBaseMap.get(Integer.parseInt(valuationBase)));
        }
    }

    /*getters and setters*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(Integer purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getOtherItem() {
        return otherItem;
    }

    public void setOtherItem(String otherItem) {
        this.otherItem = otherItem;
    }

    public Integer getAuthItem() {
        return authItem;
    }

    public void setAuthItem(Integer authItem) {
        this.authItem = authItem;
    }

    public String getBusinessCheckOrg() {
        return businessCheckOrg;
    }

    public void setBusinessCheckOrg(String businessCheckOrg) {
        this.businessCheckOrg = businessCheckOrg;
    }

    public String getExportConfFile() {
        return exportConfFile;
    }

    public void setExportConfFile(String exportConfFile) {
        this.exportConfFile = exportConfFile;
    }

    public Integer getProductOilClassify() {
        return productOilClassify;
    }

    public void setProductOilClassify(Integer productOilClassify) {
        this.productOilClassify = productOilClassify;
    }

    public Integer getProductOilSpecs() {
        return productOilSpecs;
    }

    public void setProductOilSpecs(Integer productOilSpecs) {
        this.productOilSpecs = productOilSpecs;
    }

    public Integer getTradeItem() {
        return tradeItem;
    }

    public void setTradeItem(Integer tradeItem) {
        this.tradeItem = tradeItem;
    }



    public Date getStopBidTime() {
        return stopBidTime;
    }

    public void setStopBidTime(Date stopBidTime) {
        this.stopBidTime = stopBidTime;
    }

    public Date getTenderOutTime() {
        return tenderOutTime;
    }

    public void setTenderOutTime(Date tenderOutTime) {
        this.tenderOutTime = tenderOutTime;
    }

    public Integer getValuationCurrency() {
        return valuationCurrency;
    }

    public void setValuationCurrency(Integer valuationCurrency) {
        this.valuationCurrency = valuationCurrency;
    }

    public String getValuationBase() {
        return valuationBase;
    }

    public void setValuationBase(String valuationBase) {
        this.valuationBase = valuationBase;
    }

    public Integer getPricingMeasureUnit() {
        return pricingMeasureUnit;
    }

    public void setPricingMeasureUnit(Integer pricingMeasureUnit) {
        this.pricingMeasureUnit = pricingMeasureUnit;
    }

    public Integer getMeasureMode() {
        return measureMode;
    }

    public void setMeasureMode(Integer measureMode) {
        this.measureMode = measureMode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getAgio() {
        return agio;
    }

    public void setAgio(Long agio) {
        this.agio = agio;
    }

    public String getValuationFormula() {
        return valuationFormula;
    }

    public void setValuationFormula(String valuationFormula) {
        this.valuationFormula = valuationFormula;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Integer getProductOilKind() {
        return productOilKind;
    }

    public void setProductOilKind(Integer productOilKind) {
        this.productOilKind = productOilKind;
    }

	public String getCrudeOilOptions() {
		return crudeOilOptions;
	}

	public void setCrudeOilOptions(String crudeOilOptions) {
        //油种名称转换逻辑
        crudeOilOptions =
                crudeOilOptions
                        .replace("API度", VisitorLocale.getByCurrentLanguage(Constant.LISTED_0038))
                        .replace("含硫量",VisitorLocale.getByCurrentLanguage(Constant.LISTED_0035));
        this.crudeOilOptions = crudeOilOptions;
	}
    @NotEmpty(message = "溢短装不能为空！")
	public String getNumfloat() {
		return numfloat;
	}

	public void setNumfloat(String numfloat) {
		this.numfloat = numfloat;
	}

    public Integer getPurchaseMode() {
        return purchaseMode;
    }

    public void setPurchaseMode(Integer purchaseMode) {
        this.purchaseMode = purchaseMode;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getProductOilId() {
        return productOilId;
    }

    public void setProductOilId(String productOilId) {
        this.productOilId = productOilId;
    }

    public Date getResponseStartTime() {
        return responseStartTime;
    }

    public void setResponseStartTime(Date responseStartTime) {
        this.responseStartTime = responseStartTime;
    }

    public Date getResponseEndTime() {
        return responseEndTime;
    }

    public void setResponseEndTime(Date responseEndTime) {
        this.responseEndTime = responseEndTime;
    }

    public Integer getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(Integer isAgent) {
        this.isAgent = isAgent;
    }

    public Long getAgenter() {
        return agenter;
    }

    public void setAgenter(Long agenter) {
        this.agenter = agenter;
    }

    public Integer getExportType() {
        return exportType;
    }

    public void setExportType(Integer exportType) {
        this.exportType = exportType;
    }

    public String getQcReport() {
        return qcReport;
    }

    public void setQcReport(String qcReport) {
        this.qcReport = qcReport;
    }

    public Integer getPricingMode() {
        return pricingMode;
    }

    public void setPricingMode(Integer pricingMode) {
        this.pricingMode = pricingMode;
    }

    public String getPriceDeclare() {
        return priceDeclare;
    }

    public void setPriceDeclare(String priceDeclare) {
        this.priceDeclare = priceDeclare;
    }

    public Integer getIsSeal() {
        return isSeal;
    }

    public void setIsSeal(Integer isSeal) {
        this.isSeal = isSeal;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public String getGoodsImages() {
        return goodsImages;
    }

    public void setGoodsImages(String goodsImages) {
        this.goodsImages = goodsImages;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getTradeItemContent() {
        return tradeItemContent;
    }

    public void setTradeItemContent(String tradeItemContent) {
        this.tradeItemContent = tradeItemContent;
    }

    public String getValuationBaseContent() {
        return valuationBaseContent;
    }

    public void setValuationBaseContent(String valuationBaseContent) {
        this.valuationBaseContent = valuationBaseContent;
    }

    public String getPayItemContent() {
        return payItemContent;
    }

    public void setPayItemContent(String payItemContent) {
        this.payItemContent = payItemContent;
    }

    public String getCreateTimeAsString() {
        return createTimeAsString;
    }

    public void setCreateTimeAsString(String createTimeAsString) {
        this.createTimeAsString = createTimeAsString;
    }

    public String getUpdateTimeAsString() {
        return updateTimeAsString;
    }

    public void setUpdateTimeAsString(String updateTimeAsString) {
        this.updateTimeAsString = updateTimeAsString;
    }

    public Date getUpdaterTime() {
        return updaterTime;
    }

    public void setUpdaterTime(Date updaterTime) {
        this.updaterTime = updaterTime;
    }

    public String getAuthItemContent() {
        return authItemContent;
    }

    public void setAuthItemContent(String authItemContent) {
        this.authItemContent = authItemContent;
    }

    public String getBusinessCheckOrgContent() {
        return businessCheckOrgContent;
    }

    public void setBusinessCheckOrgContent(String businessCheckOrgContent) {
        this.businessCheckOrgContent = businessCheckOrgContent;
    }

    public String getTenderOutTimeAsString() {
        return tenderOutTimeAsString;
    }

    public void setTenderOutTimeAsString(String tenderOutTimeAsString) {
        this.tenderOutTimeAsString = tenderOutTimeAsString;
    }

    public String getStopBidTimeAsString() {
        return stopBidTimeAsString;
    }

    public void setStopBidTimeAsString(String stopBidTimeAsString) {
        this.stopBidTimeAsString = stopBidTimeAsString;
    }

    public String getResponseStartTimeAsString() {
        return responseStartTimeAsString;
    }

    public void setResponseStartTimeAsString(String responseStartTimeAsString) {
        this.responseStartTimeAsString = responseStartTimeAsString;
    }

    public String getResponseEndTimeAsString() {
        return responseEndTimeAsString;
    }

    public void setResponseEndTimeAsString(String responseEndTimeAsString) {
        this.responseEndTimeAsString = responseEndTimeAsString;
    }

    public String getProductOilClassifyContent() {
        return productOilClassifyContent;
    }

    public void setProductOilClassifyContent(String productOilClassifyContent) {
        this.productOilClassifyContent = productOilClassifyContent;
    }

    public String getProductOilKindContent() {
        return productOilKindContent;
    }

    public void setProductOilKindContent(String productOilKindContent) {
        this.productOilKindContent = productOilKindContent;
    }

    public String getProductOilSpecsContent() {
        return productOilSpecsContent;
    }

    public void setProductOilSpecsContent(String productOilSpecsContent) {
        this.productOilSpecsContent = productOilSpecsContent;
    }

    public String getExportTypeContent() {
        return exportTypeContent;
    }

    public void setExportTypeContent(String exportTypeContent) {
        this.exportTypeContent = exportTypeContent;
    }

    public String getPricingModeContent() {
        return pricingModeContent;
    }

    public void setPricingModeContent(String pricingModeContent) {
        this.pricingModeContent = pricingModeContent;
    }

    public String getPricingMeasureUnitContent() {
        return pricingMeasureUnitContent;
    }

    public void setPricingMeasureUnitContent(String pricingMeasureUnitContent) {
        this.pricingMeasureUnitContent = pricingMeasureUnitContent;
    }

    public String getMeasureModeContent() {
        return measureModeContent;
    }

    public void setMeasureModeContent(String measureModeContent) {
        this.measureModeContent = measureModeContent;
    }

    public String getValuationCurrencyContent() {
        return valuationCurrencyContent;
    }

    public void setValuationCurrencyContent(String valuationCurrencyContent) {
        this.valuationCurrencyContent = valuationCurrencyContent;
    }

    public Integer getValuationProidType() {
        return valuationProidType;
    }

    public void setValuationProidType(Integer valuationProidType) {
        this.valuationProidType = valuationProidType;
    }

    public String getValuationPeriodTypeContent() {
        return valuationPeriodTypeContent;
    }

    public void setValuationPeriodTypeContent(String valuationPeriodTypeContent) {
        this.valuationPeriodTypeContent = valuationPeriodTypeContent;
    }

    public Date getValuationProidStart() {
        return valuationProidStart;
    }

    public void setValuationProidStart(Date valuationProidStart) {
        this.valuationProidStart = valuationProidStart;
    }

    public String getValuationPeriodStartAsString() {
        return valuationPeriodStartAsString;
    }

    public void setValuationPeriodStartAsString(String valuationPeriodStartAsString) {
        this.valuationPeriodStartAsString = valuationPeriodStartAsString;
    }

    public Date getValuationProidEnd() {
        return valuationProidEnd;
    }

    public void setValuationProidEnd(Date valuationProidEnd) {
        this.valuationProidEnd = valuationProidEnd;
    }

    public String getValuationPeriodEndAsString() {
        return valuationPeriodEndAsString;
    }

    public void setValuationPeriodEndAsString(String valuationPeriodEndAsString) {
        this.valuationPeriodEndAsString = valuationPeriodEndAsString;
    }

    public String getPurchaseModeContent() {
        return purchaseModeContent;
    }

    public void setPurchaseModeContent(String purchaseModeContent) {
        this.purchaseModeContent = purchaseModeContent;
    }

    public String getPubDateAsString() {
        return pubDateAsString;
    }

    public void setPubDateAsString(String pubDateAsString) {
        this.pubDateAsString = pubDateAsString;
    }

    public String getRegionContent() {
        return regionContent;
    }

    public void setRegionContent(String regionContent) {
        this.regionContent = regionContent;
    }

    public String getValuationProidTypeStr() {
        return valuationProidTypeStr;
    }

    public void setValuationProidTypeStr(String valuationProidTypeStr) {
        this.valuationProidTypeStr = valuationProidTypeStr;
    }

    public String getTradeItemStr() {
        return tradeItemStr;
    }

    public void setTradeItemStr(String tradeItemStr) {
        this.tradeItemStr = tradeItemStr;
    }

    public String getValuationFormulaJson() {
        return valuationFormulaJson;
    }

    public void setValuationFormulaJson(String valuationFormulaJson) {
        this.valuationFormulaJson = valuationFormulaJson;
    }

    public String getAuthItemStr() {
        return authItemStr;
    }

    public void setAuthItemStr(String authItemStr) {
        this.authItemStr = authItemStr;
    }
    @NotEmpty(message = "数量不能为空！")
    public String getNumStr() {
        return numStr;
    }

    public void setNumStr(String numStr) {
        this.numStr = numStr;
    }

    public BigDecimal getAgioStr() {
        return agioStr;
    }

    public void setAgioStr(BigDecimal agioStr) {
        this.agioStr = agioStr;
    }

    public Integer getSpecified() {
        return specified;
    }

    public void setSpecified(Integer specified) {
        this.specified = specified;
    }

    public Integer getPublishType() {
        return publishType;
    }

    public void setPublishType(Integer publishType) {
        this.publishType = publishType;
    }

    public String getPublishTypeContent() {
        return publishTypeContent;
    }

    public void setPublishTypeContent(String publishTypeContent) {
        this.publishTypeContent = publishTypeContent;
    }

    public Integer getBiddingType() {
        return biddingType;
    }

    public void setBiddingType(Integer biddingType) {
        this.biddingType = biddingType;
    }

    public String getBiddingTypeContent() {
        return biddingTypeContent;
    }

    public void setBiddingTypeContent(String biddingTypeContent) {
        this.biddingTypeContent = biddingTypeContent;
    }

    public String getLoadAndDischargePermittedTimespan() {
        return loadAndDischargePermittedTimespan;
    }

    public void setLoadAndDischargePermittedTimespan(String loadAndDischargePermittedTimespan) {
        this.loadAndDischargePermittedTimespan = loadAndDischargePermittedTimespan;
    }

    public String getPurchaseTypeContent() {
        return purchaseTypeContent;
    }

    public void setPurchaseTypeContent(String purchaseTypeContent) {
        this.purchaseTypeContent = purchaseTypeContent;
    }

    public String getContractKind() {
        return contractKind;
    }

    public void setContractKind(String contractKind) {
        this.contractKind = contractKind;
    }

    public Integer getInspectionFeeSharingType() {
        return inspectionFeeSharingType;
    }

    public void setInspectionFeeSharingType(Integer inspectionFeeSharingType) {
        this.inspectionFeeSharingType = inspectionFeeSharingType;
    }

    public String getInspectionFeeSharingTypeContent() {
        return inspectionFeeSharingTypeContent;
    }

    public void setInspectionFeeSharingTypeContent(String inspectionFeeSharingTypeContent) {
        this.inspectionFeeSharingTypeContent = inspectionFeeSharingTypeContent;
    }


	public Integer getPriceType() {
		return priceType;
	}


	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}


	public String getPriceTypeContent() {
		return priceTypeContent;
	}


	public void setPriceTypeContent(String priceTypeContent) {
		this.priceTypeContent = priceTypeContent;
	}

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }
}

