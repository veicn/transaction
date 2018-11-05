package com.sinochem.crude.trade.shiprefueling.model.query;

import com.eyeieye.melody.util.StringUtil;
import com.google.common.collect.Lists;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.domain.po.HaseInfo;
import com.sinochem.crude.trade.shiprefueling.domain.po.Info;
import org.codehaus.jackson.annotate.JsonUnwrapped;

import java.util.Arrays;
import java.util.List;

/**
 * Created by z1761 on 2018/5/26.
 */
public class InfoQuery extends Info {

    /**UUID*/
    private String uuid;

    /**油品分类*/
    private String oilClassification;

    /**品种*/
    private String oilVarieties;
    /**交货方式(1:送供 2:自提)*/
    private String deliveryWay;

    /**港口*/
    private String port;

    /**运输方式*/
    private String transportWay;

    /**
     * 发布日期开始
     */
    private String releaseBeginDate;

    /**
     * 发布日期结束
     */
    private String releaseEndDate;

    /**会员公司id*/
    private Long epMemberId;

    /**会员公司名称*/
    private String epMemberName;

    /**是否有效(1有效0无效)*/
    private String aliveFlag;

    private String infoType;
    /**信息类型*/
    private List<String> infoTypeList;

    /**状态*/
    private String status;
    /**状态列表*/
    private List<String> statusList;

    @JsonUnwrapped
    private SimplePageInfo pageInfo;

    /**
     *  是否个人中心
     *  0:否
     *  1:是
     * */
    private String personal;

    public SimplePageInfo getPageInfo() {
        return pageInfo;
    }
    public void setPageInfo(SimplePageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
    public String getAliveFlag() {
        return aliveFlag;
    }

    public void setAliveFlag(String aliveFlag) {
        this.aliveFlag = aliveFlag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        if(StringUtil.isNotBlank(status)){
            String[] statusArray = status.split(",");
            setStatusList(Arrays.asList(statusArray));
        }

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOilClassification() {
        return oilClassification;
    }

    public void setOilClassification(String oilClassification) {
        this.oilClassification = oilClassification;
    }

    public String getOilVarieties() {
        return oilVarieties;
    }

    public void setOilVarieties(String oilVarieties) {
        this.oilVarieties = oilVarieties;
    }

    public String getDeliveryWay() {
        return deliveryWay;
    }

    public void setDeliveryWay(String deliveryWay) {
        this.deliveryWay = deliveryWay;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getTransportWay() {
        return transportWay;
    }

    public void setTransportWay(String transportWay) {
        this.transportWay = transportWay;
    }

    public String getReleaseBeginDate() {
        return releaseBeginDate;
    }

    public void setReleaseBeginDate(String releaseBeginDate) {
        this.releaseBeginDate = releaseBeginDate;
    }

    public String getReleaseEndDate() {
        return releaseEndDate;
    }

    public void setReleaseEndDate(String releaseEndDate) {
        this.releaseEndDate = releaseEndDate;
    }

    public Long getEpMemberId() {
        return epMemberId;
    }

    public void setEpMemberId(Long epMemberId) {
        this.epMemberId = epMemberId;
    }

    public String getEpMemberName() {
        return epMemberName;
    }

    public void setEpMemberName(String epMemberName) {
        this.epMemberName = epMemberName;
    }

    public List<String> getInfoTypeList() {
        return infoTypeList;
    }

    public void setInfoTypeList(List<String> infoTypeList) {
        this.infoTypeList = infoTypeList;
    }

    @Override
    public String getInfoType() {
        return infoType;
    }

    @Override
    public void setInfoType(String infoType) {
        this.infoType = infoType;
        if(StringUtil.isNotBlank(infoType)){
            String[] infoTypeArray = infoType.split(",");
            setInfoTypeList(Arrays.asList(infoTypeArray));
        }
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }
}
