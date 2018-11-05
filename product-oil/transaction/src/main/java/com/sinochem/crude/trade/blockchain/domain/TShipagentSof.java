package com.sinochem.crude.trade.blockchain.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TShipagentSof {

    public static final String STATUS_SAVED = "1";
    public static final String STATUS_COMMITED = "2";

    /**任务标识*/
    private Long taskId;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    private Long id;

    private String uuid;

    private Long enterpriseId;

    private String vessel;

    private String voyage;

    private String imo;

    private String goodsName;

    private String metricTon;

    private Date sofDate;

    private String port;

    private String contactPerson;

    private String contactNumber;

    private String remark;

    private BigDecimal shipGrossWeight;

    private BigDecimal shipNetWeight;

    private String aliveFlag;

    private String status;

    private Long createUser;

    private Date createDate;

    private Long updateUser;

    private Date updateDate;

    private List<TShipagentSofDetail> detailList;

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
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getVessel() {
        return vessel;
    }

    public void setVessel(String vessel) {
        this.vessel = vessel == null ? null : vessel.trim();
    }

    public String getVoyage() {
        return voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage == null ? null : voyage.trim();
    }

    public String getImo() {
        return imo;
    }

    public void setImo(String imo) {
        this.imo = imo == null ? null : imo.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getMetricTon() {
        return metricTon;
    }

    public void setMetricTon(String metricTon) {
        this.metricTon = metricTon;
    }

    public Date getSofDate() {
        return sofDate;
    }

    public void setSofDate(Date sofDate) {
        this.sofDate = sofDate;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson == null ? null : contactPerson.trim();
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber == null ? null : contactNumber.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getShipGrossWeight() {
        return shipGrossWeight;
    }

    public void setShipGrossWeight(BigDecimal shipGrossWeight) {
        this.shipGrossWeight = shipGrossWeight;
    }

    public BigDecimal getShipNetWeight() {
        return shipNetWeight;
    }

    public void setShipNetWeight(BigDecimal shipNetWeight) {
        this.shipNetWeight = shipNetWeight;
    }

    public String getAliveFlag() {
        return aliveFlag;
    }

    public void setAliveFlag(String aliveFlag) {
        this.aliveFlag = aliveFlag == null ? null : aliveFlag.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<TShipagentSofDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<TShipagentSofDetail> detailList) {
        this.detailList = detailList;
    }
}