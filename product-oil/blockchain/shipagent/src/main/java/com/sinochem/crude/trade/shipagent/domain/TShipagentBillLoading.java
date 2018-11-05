package com.sinochem.crude.trade.shipagent.domain;

import java.util.Date;

public class TShipagentBillLoading {



    /**任务ID**/
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

    private String blNo;

    private String consignor;

    private String vessel;

    private String loadingPort;

    private String captain;

    private String unloadingPort;

    private String consignee;

    private String notify;

    private String category;

    private String metricTon;

    private String longTon;

    private String bbls;

    private String ltrs;

    private String ltrsObsTrmp;

    private String remark;

    private String cpDate1;

    private String cpDate2;

    private String cpDate3;

    private String cpDate4;

    private String signNum;

    private String timeZone;

    private String timeDay;

    private String timeMonth;

    private String timeYear;

    private Date singedDate;

    private String masterName;

    private Long createUser;

    private Date createDate;

    private Long updateUser;

    private Date updateDate;

    private String aliveFlag;

    private String status;

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

    public String getBlNo() {
        return blNo;
    }

    public void setBlNo(String blNo) {
        this.blNo = blNo == null ? null : blNo.trim();
    }

    public String getConsignor() {
        return consignor;
    }

    public void setConsignor(String consignor) {
        this.consignor = consignor == null ? null : consignor.trim();
    }

    public String getVessel() {
        return vessel;
    }

    public void setVessel(String vessel) {
        this.vessel = vessel == null ? null : vessel.trim();
    }

    public String getLoadingPort() {
        return loadingPort;
    }

    public void setLoadingPort(String loadingPort) {
        this.loadingPort = loadingPort == null ? null : loadingPort.trim();
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain == null ? null : captain.trim();
    }

    public String getUnloadingPort() {
        return unloadingPort;
    }

    public void setUnloadingPort(String unloadingPort) {
        this.unloadingPort = unloadingPort == null ? null : unloadingPort.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify == null ? null : notify.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCpDate1() {
        return cpDate1;
    }

    public void setCpDate1(String cpDate1) {
        this.cpDate1 = cpDate1 == null ? null : cpDate1.trim();
    }

    public String getCpDate2() {
        return cpDate2;
    }

    public void setCpDate2(String cpDate2) {
        this.cpDate2 = cpDate2 == null ? null : cpDate2.trim();
    }

    public String getCpDate3() {
        return cpDate3;
    }

    public void setCpDate3(String cpDate3) {
        this.cpDate3 = cpDate3 == null ? null : cpDate3.trim();
    }

    public String getCpDate4() {
        return cpDate4;
    }

    public void setCpDate4(String cpDate4) {
        this.cpDate4 = cpDate4 == null ? null : cpDate4.trim();
    }

    public String getSignNum() {
        return signNum;
    }

    public void setSignNum(String signNum) {
        this.signNum = signNum == null ? null : signNum.trim();
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone == null ? null : timeZone.trim();
    }

    public String getTimeDay() {
        return timeDay;
    }

    public void setTimeDay(String timeDay) {
        this.timeDay = timeDay == null ? null : timeDay.trim();
    }

    public String getTimeMonth() {
        return timeMonth;
    }

    public void setTimeMonth(String timeMonth) {
        this.timeMonth = timeMonth == null ? null : timeMonth.trim();
    }

    public String getTimeYear() {
        return timeYear;
    }

    public void setTimeYear(String timeYear) {
        this.timeYear = timeYear == null ? null : timeYear.trim();
    }

    public Date getSingedDate() {
        return singedDate;
    }

    public void setSingedDate(Date singedDate) {
        this.singedDate = singedDate;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName == null ? null : masterName.trim();
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

    public String getMetricTon() {
        return metricTon;
    }

    public void setMetricTon(String metricTon) {
        this.metricTon = metricTon;
    }

    public String getLongTon() {
        return longTon;
    }

    public void setLongTon(String longTon) {
        this.longTon = longTon;
    }

    public String getBbls() {
        return bbls;
    }

    public void setBbls(String bbls) {
        this.bbls = bbls;
    }

    public String getLtrs() {
        return ltrs;
    }

    public void setLtrs(String ltrs) {
        this.ltrs = ltrs;
    }

    public String getLtrsObsTrmp() {
        return ltrsObsTrmp;
    }

    public void setLtrsObsTrmp(String ltrsObsTrmp) {
        this.ltrsObsTrmp = ltrsObsTrmp;
    }
}