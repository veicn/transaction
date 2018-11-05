package com.sinochem.crude.trade.transaction.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.transaction.model.query.ContractSheetQuery;

import java.io.Serializable;

public class ContractSheetQueryVO implements Serializable {

    private Integer pageNum = Integer.valueOf(1);

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    private Integer pageSize = Integer.valueOf(10);

    /**
     * 商品分类
     */
    private String productCategoryCode;

    public String getContractNO() {
        return contractNO;
    }

    public void setContractNO(String contractNO) {
        this.contractNO = contractNO;
    }

    /**
     * 合同编号
     */
    private String contractNO;

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    /**
     * 合同日期
     */
    private String contractDate;

    /**
     * 合同日期开始
     */
    private String contractDateStart;

    /**
     * 合同日期结束
     */
    private String contractDateEnd;

    public String getContractDateStart() {
        return contractDateStart;
    }

    public void setContractDateStart(String contractDateStart) {
        this.contractDateStart = contractDateStart;
    }

    public String getContractDateEnd() {
        return contractDateEnd;
    }

    public void setContractDateEnd(String contractDateEnd) {
        this.contractDateEnd = contractDateEnd;
    }

    /**
     * 合约单（订单）状态
     */
    private String contractSheetStatusCode;

    /**
     * 合约单（订单）单据号
     */
    private String serialNumber;

    /**
     * 贸易条款
     */
    private String tradeTermCode;

    /**
     * 创建时间-开始
     */
    private String gmtCreatedStartAsString;

    /**
     * 创建时间-结束
     */
    private String gmtCreatedEndAsString;

    public String getDischargePort() {
        return dischargePort;
    }

    public void setDischargePort(String dischargePort) {
        this.dischargePort = dischargePort;
    }

    /**
     * 卸港港口
     */
    private String dischargePort;

    /**
     * 卖家ID
     */
    private Long salerId;

    private Long epMemberId;

    public Long getEpMemberId() {
        return epMemberId;
    }

    public void setEpMemberId(Long epMemberId) {
        this.epMemberId = epMemberId;
    }

    /**
     * 买家ID
     */
    private Long buyerId;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 模糊查询内容
     */
    private String keywords;


    private String brokerAppointStatus;

    private String inspectorAppointStatus;

    private String shipagentAppointStatus;

    public ContractSheetQuery getContractSheetQuery() {

        ContractSheetQuery contractSheetQuery = new ContractSheetQuery();

        contractSheetQuery.setProductCategoryCode(this.getProductCategoryCode());
        contractSheetQuery.setContractSheetStatusCode(this.getContractSheetStatusCode());
        contractSheetQuery.setSerialNumber(this.getSerialNumber());
        contractSheetQuery.setTradeTermCode(this.getTradeTermCode());
        contractSheetQuery.setSalerId(this.getSalerId());
        contractSheetQuery.setBuyerId(this.getBuyerId());
        contractSheetQuery.setDischargePort(this.dischargePort);
        contractSheetQuery.setContractNO(this.contractNO);
        String gmtCreatedStartAsString = this.getGmtCreatedStartAsString();
        if (!StringUtil.isEmpty(gmtCreatedStartAsString)) {
            contractSheetQuery.setGmtCreatedStart(DateUtil.getDate(gmtCreatedStartAsString));
        }
        if (!StringUtil.isEmpty(this.contractDate)) {
            contractSheetQuery.setContractDate(DateUtil.getDate(this.contractDate));
        }

        String gmtCreatedEndAsString = this.getGmtCreatedEndAsString();
        if (!StringUtil.isEmpty(gmtCreatedEndAsString)) {
            contractSheetQuery.setGmtCreatedEnd(DateUtil.getDate(gmtCreatedEndAsString));
        }
        contractSheetQuery.setContractDateStart(this.contractDateStart);
        contractSheetQuery.setContractDateEnd(this.contractDateEnd);

        return contractSheetQuery;
    }

    /** getters and setters */
    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }

    public String getContractSheetStatusCode() {
        return contractSheetStatusCode;
    }

    public void setContractSheetStatusCode(String contractSheetStatusCode) {
        this.contractSheetStatusCode = contractSheetStatusCode;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTradeTermCode() {
        return tradeTermCode;
    }

    public void setTradeTermCode(String tradeTermCode) {
        this.tradeTermCode = tradeTermCode;
    }

    public String getGmtCreatedStartAsString() {
        return gmtCreatedStartAsString;
    }

    public void setGmtCreatedStartAsString(String gmtCreatedStartAsString) {
        this.gmtCreatedStartAsString = gmtCreatedStartAsString;
    }

    public String getGmtCreatedEndAsString() {
        return gmtCreatedEndAsString;
    }

    public void setGmtCreatedEndAsString(String gmtCreatedEndAsString) {
        this.gmtCreatedEndAsString = gmtCreatedEndAsString;
    }

    public Long getSalerId() {
        return salerId;
    }

    public void setSalerId(Long salerId) {
        this.salerId = salerId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }


    public String getBrokerAppointStatus() {
        return brokerAppointStatus;
    }

    public void setBrokerAppointStatus(String brokerAppointStatus) {
        this.brokerAppointStatus = brokerAppointStatus;
    }

    public String getInspectorAppointStatus() {
        return inspectorAppointStatus;
    }

    public void setInspectorAppointStatus(String inspectorAppointStatus) {
        this.inspectorAppointStatus = inspectorAppointStatus;
    }

    public String getShipagentAppointStatus() {
        return shipagentAppointStatus;
    }

    public void setShipagentAppointStatus(String shipagentAppointStatus) {
        this.shipagentAppointStatus = shipagentAppointStatus;
    }

}
