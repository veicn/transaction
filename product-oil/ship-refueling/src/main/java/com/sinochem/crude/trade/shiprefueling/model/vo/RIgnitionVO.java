package com.sinochem.crude.trade.shiprefueling.model.vo;


import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.domain.po.RIgnition;
import org.codehaus.jackson.annotate.JsonUnwrapped;


public class RIgnitionVO extends RIgnition {

    private static final long serialVersionUID = 1L;

    @JsonUnwrapped
    private SimplePageInfo pageInfo;

    //订单时间
    private String orderTimeStart;

    //订单时间
    private String orderTimeEnd;

    //油种
    private String oilVarieties;

    //品类
    private String oilClassification;


    //infoType
    private String infoType;

    // 内部系统查询标识
    private String sysTag;

    public SimplePageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(SimplePageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getOrderTimeStart() {
        return orderTimeStart;
    }

    public void setOrderTimeStart(String orderTimeStart) {
        this.orderTimeStart = orderTimeStart;
    }

    public String getOrderTimeEnd() {
        return orderTimeEnd;
    }

    public void setOrderTimeEnd(String orderTimeEnd) {
        this.orderTimeEnd = orderTimeEnd;
    }

    public String getOilVarieties() {
        return oilVarieties;
    }

    public void setOilVarieties(String oilVarieties) {
        this.oilVarieties = oilVarieties;
    }

    public String getOilClassification() {
        return oilClassification;
    }

    public void setOilClassification(String oilClassification) {
        this.oilClassification = oilClassification;
    }

    public String getSysTag() {
        return sysTag;
    }

    public void setSysTag(String sysTag) {
        this.sysTag = sysTag;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }
}