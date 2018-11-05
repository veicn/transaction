package com.sinochem.crude.trade.listed.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.constant.MsgConstant;
import com.sinochem.crude.trade.listed.enums.DemandBiddingType;
import com.sinochem.crude.trade.listed.helper.DictUtils;
import com.sinochem.crude.trade.listed.model.result.DemandJoinResult;
import com.sinochem.it.b2b.common.utils.DateUtil;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;

public class BiddingListVO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 单据编号
     */
    private String uuid;
    /**
     * 数量(万桶)
     */
    private String num;
    /**
     * 数量(桶)
     */
    private String numBBL;

    /**
     * 原油油种
     */
    private String crudeOilOptions;
    /**
     * 成品油种类
     */
    private String productOilKind;

    /**
     * 成品油规格
     */
    private String productOilSpecs;
    /**
     * 计价基准
     */
    private String valuationBase;
    /**
     * 贸易条款
     */
    private String tradeItem;
    /**
     * 付款条款
     */
    private String payItem;
    /**
     * 付款条款Json
     */
    private String payItemJSON;

    public String getPayItemJSON() {
        return payItemJSON;
    }

    public void setPayItemJSON(String payItemJSON) {
        this.payItemJSON = payItemJSON;
    }

    /**
     * 计价期类型
     */
    private String valuationProidType;
    /**
     * 采购类型
     */
    private Integer purchaseType;
    /**
     * 装货开始时间
     */
    private String shipmentStartTime;

    /***
     * 装货港
     */
    private String shipmentPort;

     /***
     * 卸货港
     */
     private String dischargePort;

    /**
     * 装货结束时间
     */
    private String shipmentEndTime;

    /**
     * 到货开始时间
     */
    private String dischargeStartTime;

    /**
     * 到货结束时间
     */
    private String dischargeEndTime;

    /**
     * 发布日期
     */
    private String pubDate;
    /**
     * 出标时间
     */
    private String tenderOutTime;

    /**
     * 截标时间
     */
    private String stopBidTime;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 升贴水
     */
    private String agio;

    /**
     * 计价公式
     */
    private String valuationFormula;

    /**
     * 买家
     */
    private Long beMemberId;
    private String bEnterpriseName;

    /**
     * 贸易商
     */
    private Long teMemberId;
    private String tEnterpriseName;

    /**
     * 供应商
     */
    private Long peMemberId;
    private String pEnterpriseName;

    /**
     * 报价类型
     */
    private Integer biddingType;
    private String biddingTypeContent;

    private Integer status;

    /**
     * 创建人
     */
    private Long creater;
    
    /**
     * 是否已结标（1-已结标， 0-未结标）
     */
    private Integer stopBid;
    
    public static BiddingListVO convertToVO(DemandJoinResult demandJoinResult){
        if (demandJoinResult == null) {
            return null;
        }
        Map<Integer, String> productOilKindMap = DictUtils.getProductOilKind();
        Map<Integer, String> productOilSpecsMap = DictUtils.getProductOilSpecs();
        Map<Object, String> tradeItemMap = DictUtils.getTradeItemMap();
        Map<Object, String> payItemMap = DictUtils.getPayItemMap();
        Map<Object, String> valuationPeriodTypeMap = DictUtils.getValuationProidTypeMap();

        BiddingListVO biddingListVO = new BiddingListVO();
        biddingListVO.setId(demandJoinResult.getId());
        biddingListVO.setUuid(demandJoinResult.getUuid());
        // 数量(万桶)
        Long num = demandJoinResult.getNum();
        // 数量判断
        if (num != null) {
            DecimalFormat df = new DecimalFormat("#0");
            double numD = num/10000000.0;
            biddingListVO.setNum(df.format(numD));
            biddingListVO.setNumBBL(df.format(num/1000.0));
        }
        //原油油种
        biddingListVO.setCrudeOilOptions(demandJoinResult.getCrudeOilOptions());
        //成品油油种
        if(demandJoinResult.getProductOilKind() != null){
            biddingListVO.setProductOilKind(productOilKindMap.get(demandJoinResult.getProductOilKind()));
        }
        if(demandJoinResult.getProductOilSpecs() != null){
            biddingListVO.setProductOilSpecs(productOilSpecsMap.get(demandJoinResult.getProductOilSpecs()));
        }
        //采购类型
        biddingListVO.setPurchaseType(demandJoinResult.getPurchaseType());

        //付款日期
        if(demandJoinResult.getPayItem() != null){
            biddingListVO.setPayItem(demandJoinResult.getPayItem());
        }

        //付款日期JSON
        if(demandJoinResult.getPayItemJSON() != null){
            biddingListVO.setPayItemJSON(demandJoinResult.getPayItemJSON());
        }


        //贸易条款
        if(demandJoinResult.getTradeItem() != null){
            biddingListVO.setTradeItem(tradeItemMap.get(demandJoinResult.getTradeItem()));
        }
        //计价基准
        setValuationBase(biddingListVO, demandJoinResult.getValuationBase(), demandJoinResult.getBizType());
        //计价期
        Integer valuationProidType = demandJoinResult.getValuationProidType();
        String valuationProidTypeStr = "";
        if(valuationProidType != null){
            if(valuationProidType == 4){
                valuationProidTypeStr =
                        valuationPeriodTypeMap.get(valuationProidType) +
                                "("
                                + DateUtil.formatDate(demandJoinResult.getValuationProidStart())
                                + VisitorLocale.getByCurrentLanguage(Constant.LISTED_0036)
                                + DateUtil.formatDate(demandJoinResult.getValuationProidEnd())
                                +")";
            }else if(valuationProidType == 5){
                valuationProidTypeStr =
                        valuationPeriodTypeMap.get(valuationProidType) +
                                "("
                                + demandJoinResult.getContractKind()
                                +")";
            } else{
                valuationProidTypeStr = valuationPeriodTypeMap.get(valuationProidType);
            }
        }
        biddingListVO.setValuationProidType(valuationProidTypeStr);
        //计价公式
        biddingListVO.setValuationFormula(demandJoinResult.getValuationFormula());
        //装期
        biddingListVO.setShipmentStartTime(DateUtil.formatDate(demandJoinResult.getShipmentStartTime()));
        biddingListVO.setShipmentEndTime(DateUtil.formatDate(demandJoinResult.getShipmentEndTime()));
        //装货港
        biddingListVO.setShipmentPort(demandJoinResult.getShipmentPort());
        //装货港
        biddingListVO.setDischargePort(demandJoinResult.getDischargePort());
        //到货期
        biddingListVO.setDischargeEndTime(DateUtil.formatDate(demandJoinResult.getDischargeEndTime()));
        biddingListVO.setDischargeStartTime(DateUtil.formatDate(demandJoinResult.getDischargeStartTime()));
        //截止投标时间
        biddingListVO.setStopBidTime(DateUtil.formatDate(demandJoinResult.getStopBidTime()));
        //公布中标时间
        biddingListVO.setTenderOutTime(DateUtil.formatDate(demandJoinResult.getTenderOutTime()));
        //创建时间
        biddingListVO.setCreateTime(DateUtil.formatDateTime(demandJoinResult.getCreateTime()));
        //发布时间
        biddingListVO.setPubDate(DateUtil.formatDateTime(demandJoinResult.getPubDate()));
        //购买商
        biddingListVO.setBeMemberId(demandJoinResult.getBeMemberId());
        biddingListVO.setbEnterpriseName(demandJoinResult.getbEnterpriseName());

        //贸易商
        biddingListVO.setTeMemberId(demandJoinResult.getTeMemberId());
        biddingListVO.settEnterpriseName(demandJoinResult.gettEnterpriseName());

        //供应商
        biddingListVO.setPeMemberId(demandJoinResult.getPeMemberId());
        biddingListVO.setpEnterpriseName(demandJoinResult.getpEnterpriseName());
        //升贴水
        Long agioL = demandJoinResult.getAgio();
        if(agioL != null){
            DecimalFormat df = new DecimalFormat("#0.000");
            double agioD = agioL/1000.000;
            biddingListVO.setAgio(df.format(agioD));
        }
        //状态
        biddingListVO.setStatus(demandJoinResult.getStatus());

        //报价类型
        Integer biddingType = demandJoinResult.getBiddingType();
        if (biddingType != null) {
            biddingListVO.setBiddingType(biddingType);
            biddingListVO.setBiddingTypeContent(DemandBiddingType.getDemandBiddingTypeByCode(biddingType).getName());
        }

        // 创建人
        biddingListVO.setCreater(demandJoinResult.getCreater());
        // 是否已结标
        biddingListVO.setStopBid(demandJoinResult.getStopBid());
        
        return biddingListVO;
    }
    /*类内辅助方法*/
    private static void setValuationBase(BiddingListVO biddingListVO, String valuationBase, String bizType) {
        if(biddingListVO != null && !StringUtil.isEmpty(bizType) && !StringUtil.isEmpty(valuationBase)) {
            if(Constant.BIZ_TYPE_CRUDEOIL.equals(bizType)) {
                Map<Object, String> valuationBaseMap = DictUtils.getValuationBaseMap();
                biddingListVO.setValuationBase(valuationBaseMap.get(valuationBase));
            } else if(Constant.BIZ_TYPE_PRODUCTOIL.equals(bizType)) {
                Map<Object, String> productOilValuationBaseMap = DictUtils.getProductOilValuationBaseMap();
                biddingListVO.setValuationBase(productOilValuationBaseMap.get(valuationBase));
            }
        }
    }

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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
    
    public String getNumBBL() {
		return numBBL;
	}

	public void setNumBBL(String numBBL) {
		this.numBBL = numBBL;
	}

	public String getCrudeOilOptions() {
        return crudeOilOptions;
    }

    public void setCrudeOilOptions(String crudeOilOptions) {
        this.crudeOilOptions = crudeOilOptions;
    }

    public String getProductOilKind() {
        return productOilKind;
    }

    public void setProductOilKind(String productOilKind) {
        this.productOilKind = productOilKind;
    }

    public String getProductOilSpecs() {
        return productOilSpecs;
    }

    public void setProductOilSpecs(String productOilSpecs) {
        this.productOilSpecs = productOilSpecs;
    }

    public String getValuationBase() {
        return valuationBase;
    }

    public void setValuationBase(String valuationBase) {
        this.valuationBase = valuationBase;
    }

    public String getTradeItem() {
        return tradeItem;
    }

    public void setTradeItem(String tradeItem) {
        this.tradeItem = tradeItem;
    }

    public String getPayItem() {
        return payItem;
    }

    public void setPayItem(String payItem) {
        this.payItem = payItem;
    }

    public String getValuationProidType() {
        return valuationProidType;
    }

    public void setValuationProidType(String valuationProidType) {
        this.valuationProidType = valuationProidType;
    }

    public Integer getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(Integer purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getShipmentStartTime() {
        return shipmentStartTime;
    }

    public void setShipmentStartTime(String shipmentStartTime) {
        this.shipmentStartTime = shipmentStartTime;
    }

    public String getShipmentEndTime() {
        return shipmentEndTime;
    }

    public void setShipmentEndTime(String shipmentEndTime) {
        this.shipmentEndTime = shipmentEndTime;
    }

    public String getDischargeStartTime() {
        return dischargeStartTime;
    }

    public void setDischargeStartTime(String dischargeStartTime) {
        this.dischargeStartTime = dischargeStartTime;
    }

    public String getDischargeEndTime() {
        return dischargeEndTime;
    }

    public void setDischargeEndTime(String dischargeEndTime) {
        this.dischargeEndTime = dischargeEndTime;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTenderOutTime() {
        return tenderOutTime;
    }

    public void setTenderOutTime(String tenderOutTime) {
        this.tenderOutTime = tenderOutTime;
    }

    public String getStopBidTime() {
        return stopBidTime;
    }

    public void setStopBidTime(String stopBidTime) {
        this.stopBidTime = stopBidTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getValuationFormula() {
        return valuationFormula;
    }

    public void setValuationFormula(String valuationFormula) {
        this.valuationFormula = valuationFormula;
    }

    public Long getBeMemberId() {
        return beMemberId;
    }

    public void setBeMemberId(Long beMemberId) {
        this.beMemberId = beMemberId;
    }

    public String getbEnterpriseName() {
        return bEnterpriseName;
    }

    public void setbEnterpriseName(String bEnterpriseName) {
        this.bEnterpriseName = bEnterpriseName;
    }

    public Long getTeMemberId() {
        return teMemberId;
    }

    public void setTeMemberId(Long teMemberId) {
        this.teMemberId = teMemberId;
    }

    public String gettEnterpriseName() {
        return tEnterpriseName;
    }

    public void settEnterpriseName(String tEnterpriseName) {
        this.tEnterpriseName = tEnterpriseName;
    }

    public Long getPeMemberId() {
        return peMemberId;
    }

    public void setPeMemberId(Long peMemberId) {
        this.peMemberId = peMemberId;
    }

    public String getpEnterpriseName() {
        return pEnterpriseName;
    }

    public void setpEnterpriseName(String pEnterpriseName) {
        this.pEnterpriseName = pEnterpriseName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAgio() {
        return agio;
    }

    public void setAgio(String agio) {
        this.agio = agio;
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

	public Long getCreater() {
		return creater;
	}
	
	public void setCreater(Long creater) {
		this.creater = creater;
	}

    public String getShipmentPort() {
        return shipmentPort;
    }

    public void setShipmentPort(String shipmentPort) {
        this.shipmentPort = shipmentPort;
    }

    public String getDischargePort() {
        return dischargePort;
    }

    public void setDischargePort(String dischargePort) {
        this.dischargePort = dischargePort;
    }
	public Integer getStopBid() {
		return stopBid;
	}
	public void setStopBid(Integer stopBid) {
		this.stopBid = stopBid;
	}
}
