package com.sinochem.crude.trade.transaction.domain.po;

import com.sinochem.crude.trade.common.base.BasePO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 运输信息，包含需求单和报价单
 * @author Yichen Zhao
 * date: 20180225
 */
public class TransportInfo extends BasePO {

    /**
     * 运输方式（TransportModeEnum）
     */
    private String transportModeCode;

    /**
     * 装港代码
     */
    private String loadingPort;

    /**
     * 允许装货的时间（多长时间内装完）
     */
    private BigDecimal laytime;

    /**
     * 装港开始时间
     */
    private Date laycanStartDate;

    /**
     * 装港结束时间
     */
    private Date laycanEndDate;

    /**
     * 卸港代码
     */
    private String dischargePort;

    /**
     * 装港国家
     */
    private String loadingCountry;

    /**
     * 卸港代码
     */
    private String dischargeCountry;

    /**
     *滞期费率
     */
    private String demurrageRateCode;

    /**
     *滞期费率num
     */
    private BigDecimal demurrageRateNum;

    /** getters and setters */
    public String getTransportModeCode() {
        return transportModeCode;
    }

    public void setTransportModeCode(String transportModeCode) {
        this.transportModeCode = transportModeCode;
    }

    public BigDecimal getLaytime() {
        return laytime;
    }

    public void setLaytime(BigDecimal laytime) {
        this.laytime = laytime;
    }

    public Date getLaycanStartDate() {
        return laycanStartDate;
    }

    public void setLaycanStartDate(Date laycanStartDate) {
        this.laycanStartDate = laycanStartDate;
    }

    public Date getLaycanEndDate() {
        return laycanEndDate;
    }

    public void setLaycanEndDate(Date laycanEndDate) {
        this.laycanEndDate = laycanEndDate;
    }

    public String getLoadingPort() {
        return loadingPort;
    }

    public void setLoadingPort(String loadingPort) {
        this.loadingPort = loadingPort;
    }

    public String getDischargePort() {
        return dischargePort;
    }

    public void setDischargePort(String dischargePort) {
        this.dischargePort = dischargePort;
    }

    public String getLoadingCountry() {
        return loadingCountry;
    }

    public void setLoadingCountry(String loadingCountry) {
        this.loadingCountry = loadingCountry;
    }

    public String getDischargeCountry() {
        return dischargeCountry;
    }

    public void setDischargeCountry(String dischargeCountry) {
        this.dischargeCountry = dischargeCountry;
    }

    public String getDemurrageRateCode() {
        return demurrageRateCode;
    }

    public void setDemurrageRateCode(String demurrageRateCode) {
        this.demurrageRateCode = demurrageRateCode;
    }

    public BigDecimal getDemurrageRateNum() {
        return demurrageRateNum;
    }

    public void setDemurrageRateNum(BigDecimal demurrageRateNum) {
        this.demurrageRateNum = demurrageRateNum;
    }

}
