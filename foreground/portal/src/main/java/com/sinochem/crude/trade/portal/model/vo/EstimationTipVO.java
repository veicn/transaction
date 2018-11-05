package com.sinochem.crude.trade.portal.model.vo;

import java.io.Serializable;

/**
 * 提示信息VO
 * @author Yichen Zhao
 * date: 20180418
 */
public class EstimationTipVO implements Serializable {

    /**
     * 商检费
     */
    private String commodityInspectionFee;

    /**
     * 货代代理费
     */
    private String forwarderAgencyFee;

    /**
     * 装卸费
     */
    private String handlingCharges;

    /**
     * 油污损害赔偿基金
     */
    private String oilPollutionCompensation;

    /**
     * 代收安保费
     */
    private String proxySecurityCharge;

    /**
     * 代收进油港务费
     */
    private String proxyOilInHarbourCharge;

    /** getters and setters */
    public String getCommodityInspectionFee() {
        return commodityInspectionFee;
    }

    public void setCommodityInspectionFee(String commodityInspectionFee) {
        this.commodityInspectionFee = commodityInspectionFee;
    }

    public String getForwarderAgencyFee() {
        return forwarderAgencyFee;
    }

    public void setForwarderAgencyFee(String forwarderAgencyFee) {
        this.forwarderAgencyFee = forwarderAgencyFee;
    }

    public String getHandlingCharges() {
        return handlingCharges;
    }

    public void setHandlingCharges(String handlingCharges) {
        this.handlingCharges = handlingCharges;
    }

    public String getOilPollutionCompensation() {
        return oilPollutionCompensation;
    }

    public void setOilPollutionCompensation(String oilPollutionCompensation) {
        this.oilPollutionCompensation = oilPollutionCompensation;
    }

    public String getProxySecurityCharge() {
        return proxySecurityCharge;
    }

    public void setProxySecurityCharge(String proxySecurityCharge) {
        this.proxySecurityCharge = proxySecurityCharge;
    }

    public String getProxyOilInHarbourCharge() {
        return proxyOilInHarbourCharge;
    }

    public void setProxyOilInHarbourCharge(String proxyOilInHarbourCharge) {
        this.proxyOilInHarbourCharge = proxyOilInHarbourCharge;
    }
}
