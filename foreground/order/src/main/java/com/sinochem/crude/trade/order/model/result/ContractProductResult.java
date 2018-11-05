package com.sinochem.crude.trade.order.model.result;

import java.util.Date;

/**
 * @Description:
 * @Author : chenyz
 * @Date: 2017/11/23
 */
public class ContractProductResult {

    private String resourceId;//资源id

    private String productOilClassify;//分类
    private String productOilKind;//种类

    private String shipmentPort;//装船港

    private String tradeItem;//贸易条款

    private Long num;//数量

    private String numFloat;//溢短装

    private String valuationFormula;//计价公式

    private Long agio;//升贴水

    private String payItem;//付款条款

    private Date shipmentStart;//装货期开始时间
    private Date shipmentEnd;//装货期结束时间

    private Integer valuationProidType;//计价期类行
    private Date valuationProidStart;//计价期开始时间
    private Date  valuationProidEnd;//计价期结束时间


    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getProductOilClassify() {
        return productOilClassify;
    }

    public void setProductOilClassify(String productOilClassify) {
        this.productOilClassify = productOilClassify;
    }

    public String getProductOilKind() {
        return productOilKind;
    }

    public void setProductOilKind(String productOilKind) {
        this.productOilKind = productOilKind;
    }

    public String getShipmentPort() {
        return shipmentPort;
    }

    public void setShipmentPort(String shipmentPort) {
        this.shipmentPort = shipmentPort;
    }

    public String getTradeItem() {
        return tradeItem;
    }

    public void setTradeItem(String tradeItem) {
        this.tradeItem = tradeItem;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getNumFloat() {
        return numFloat;
    }

    public void setNumFloat(String numFloat) {
        this.numFloat = numFloat;
    }

    public String getValuationFormula() {
        return valuationFormula;
    }

    public void setValuationFormula(String valuationFormula) {
        this.valuationFormula = valuationFormula;
    }

    public Long getAgio() {
        return agio;
    }

    public void setAgio(Long agio) {
        this.agio = agio;
    }

    public String getPayItem() {
        return payItem;
    }

    public void setPayItem(String payItem) {
        this.payItem = payItem;
    }

    public Date getShipmentStart() {
        return shipmentStart;
    }

    public void setShipmentStart(Date shipmentStart) {
        this.shipmentStart = shipmentStart;
    }

    public Date getShipmentEnd() {
        return shipmentEnd;
    }

    public void setShipmentEnd(Date shipmentEnd) {
        this.shipmentEnd = shipmentEnd;
    }

    public Integer getValuationProidType() {
        return valuationProidType;
    }

    public void setValuationProidType(Integer valuationProidType) {
        this.valuationProidType = valuationProidType;
    }

    public Date getValuationProidStart() {
        return valuationProidStart;
    }

    public void setValuationProidStart(Date valuationProidStart) {
        this.valuationProidStart = valuationProidStart;
    }

    public Date getValuationProidEnd() {
        return valuationProidEnd;
    }

    public void setValuationProidEnd(Date valuationProidEnd) {
        this.valuationProidEnd = valuationProidEnd;
    }
}
