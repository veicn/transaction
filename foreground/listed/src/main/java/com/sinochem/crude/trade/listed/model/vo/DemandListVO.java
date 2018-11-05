package com.sinochem.crude.trade.listed.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.helper.DictUtils;
import com.sinochem.crude.trade.listed.model.result.DemandJoinResult;
import com.sinochem.it.b2b.common.utils.DateUtil;
import com.sinochem.it.b2b.common.utils.VisitorLocale;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author wangning
 *
 */
public class DemandListVO {
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
     * 贸易条款
     */
    private String tradeItem;
    /**
     * 付款条款
     */
    private String payItem;
    /**
     * 采购类型
     */
    private Integer purchaseType;
    /**
     * 装货开始时间
     */
    private String shipmentStartTime;

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
     * 状态
     */
    private String status;

    /**
     * 装货港
     */
    private String shipmentPort;

    /**
     * 到货港
     */
    private String dischargePort;

    /**
     * 计价基准
     */
    private String valuationBase;

    /**
     * 是否过期
     */
    private boolean overdue;

    /**
     * 需求发布类型，1-按油种，2-按性质
     */
    private Integer publishType;

    public static DemandListVO convertToVO(DemandJoinResult demandJoinResult){
        if (demandJoinResult == null) {
            return null;
        }
        Map<Integer, String> productOilKindMap = DictUtils.getProductOilKind();
        Map<Integer, String> productOilSpecsMap = DictUtils.getProductOilSpecs();
        Map<Object, String> tradeItemMap = DictUtils.getTradeItemMap();
        Map<Object, String> payItemMap = DictUtils.getPayItemMap();

        DemandListVO demandListVO = new DemandListVO();
        demandListVO.setId(demandJoinResult.getId());
        demandListVO.setUuid(demandJoinResult.getUuid());
        // 数量(万桶)
        Long num = demandJoinResult.getNum();
        // 数量判断
        if (num != null) {
            DecimalFormat df = new DecimalFormat("#0");
            double numD = num/10000000.0;
            demandListVO.setNum(df.format(numD));
            demandListVO.setNumBBL(df.format(num/1000.0));
        }
        //原油油种
        demandListVO.setCrudeOilOptions(demandJoinResult.getCrudeOilOptions());
        //成品油油种
        if(demandJoinResult.getProductOilKind() != null){
            demandListVO.setProductOilKind(productOilKindMap.get(demandJoinResult.getProductOilKind()));
        }
        if(demandJoinResult.getProductOilSpecs() != null){
            demandListVO.setProductOilSpecs(productOilSpecsMap.get(demandJoinResult.getProductOilSpecs()));
        }
        //采购类型
        demandListVO.setPurchaseType(demandJoinResult.getPurchaseType());
        //付款日期
        if(demandJoinResult.getPayItem() != null){
            demandListVO.setPayItem(demandJoinResult.getPayItem());
        }
        //贸易条款
        if(demandJoinResult.getTradeItem() != null){
            demandListVO.setTradeItem(tradeItemMap.get(demandJoinResult.getTradeItem()));
        }
        //计价基准
        setValuationBase(demandListVO, demandJoinResult.getValuationBase(), demandJoinResult.getBizType());
        //装货港
        demandListVO.setShipmentPort(demandJoinResult.getShipmentPort());

        //卸货港
        Map dischargePortMap = DictUtils.getUnLoadPortMap();
        String dischargePort = demandJoinResult.getDischargePort();
        String[][] dischargePortDictionary = (String[][]) dischargePortMap.get(dischargePort);

        if (dischargePortDictionary != null) {
            demandListVO.setDischargePort(VisitorLocale.getByCurrentLanguage(dischargePortDictionary));
        } else {
            demandListVO.setDischargePort(dischargePort);
        }

        //装期
        demandListVO.setShipmentStartTime(DateUtil.formatDate(demandJoinResult.getShipmentStartTime()));
        demandListVO.setShipmentEndTime(DateUtil.formatDate(demandJoinResult.getShipmentEndTime()));

        //到货期
        demandListVO.setDischargeEndTime(DateUtil.formatDate(demandJoinResult.getDischargeEndTime()));
        demandListVO.setDischargeStartTime(DateUtil.formatDate(demandJoinResult.getDischargeStartTime()));
        //截止投标时间
        demandListVO.setStopBidTime(DateUtil.formatDate(demandJoinResult.getStopBidTime()));
        //公布中标时间
        demandListVO.setTenderOutTime(DateUtil.formatDate(demandJoinResult.getTenderOutTime()));
        //创建时间
        demandListVO.setCreateTime(DateUtil.formatDateTime(demandJoinResult.getCreateTime()));
        //发布时间
        demandListVO.setPubDate(DateUtil.formatDateTime(demandJoinResult.getPubDate()));
        //状态
        demandListVO.setStatus(demandJoinResult.getStatus().toString());
        //需求发布类型，1-按油种，2-按性质
        demandListVO.setPublishType(demandJoinResult.getPublishType());
        //是否过期 true-已逾期
        if(demandJoinResult.getStopBidTime() != null
                && DateUtil.formatDate(new Date()).compareTo(DateUtil.formatDate(demandJoinResult.getStopBidTime())) > 0){
            demandListVO.setOverdue(true);
        }else{
            demandListVO.setOverdue(false);
        }
        return demandListVO;
    }
    private static void setValuationBase(DemandListVO demandListVO, String valuationBase, String bizType) {
        if(demandListVO != null && !StringUtil.isEmpty(bizType) && !StringUtil.isEmpty(valuationBase)) {
            if(Constant.BIZ_TYPE_CRUDEOIL.equals(bizType)) {
                Map<Object, String> valuationBaseMap = DictUtils.getValuationBaseMap();
                demandListVO.setValuationBase(valuationBaseMap.get(valuationBase));
            } else if(Constant.BIZ_TYPE_PRODUCTOIL.equals(bizType)) {
                Map<Object, String> productOilValuationBaseMap = DictUtils.getProductOilValuationBaseMap();
                demandListVO.setValuationBase(productOilValuationBaseMap.get(valuationBase));
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
        //油种名称转换逻辑
        crudeOilOptions =
                crudeOilOptions
                        .replace("API度", VisitorLocale.getByCurrentLanguage(Constant.LISTED_0038))
                        .replace("含硫量",VisitorLocale.getByCurrentLanguage(Constant.LISTED_0035));
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
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Integer getPublishType() {
		return publishType;
	}

	public void setPublishType(Integer publishType) {
		this.publishType = publishType;
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

    public String getValuationBase() {
        return valuationBase;
    }

    public void setValuationBase(String valuationBase) {
        this.valuationBase = valuationBase;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }
}
