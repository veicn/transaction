package com.sinochem.crude.trade.shiprefueling.model.query;

import java.io.Serializable;
import java.util.Date;

public class QueryRSupply implements Serializable {

	/**订单编号*/
	private String orderCode;  

	/**运输方式*/
	private String transportWay;
	/**
	 * 发布时间-开始
	 */
	private Date releasedDateStart;

    /**
     * 发布时间-开始
     */
    private String releasedDateStartString;

    /**
	 * 发布时间-结束
	 */
	private Date releasedDateEnd;

    /**
     * 发布时间-结束
     */
    private String releasedDateEndString;


	/**
	 * 买家id
	 *
	 */
	private Long buyerCompanyId;
	/**
	 * 买家id
	 *
	 */
	private Long sellerCompanyId;

	private String status;

	/*品类*/
	private String oilClassification;
	/**规格*/
	private String oilVarieties;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getTransportWay() {
		return transportWay;
	}

	public void setTransportWay(String transportWay) {
		this.transportWay = transportWay;
	}

	public Date getReleasedDateStart() {
		return releasedDateStart;
	}

	public void setReleasedDateStart(Date releasedDateStart) {
		this.releasedDateStart = releasedDateStart;
	}

	public Date getReleasedDateEnd() {
		return releasedDateEnd;
	}

	public void setReleasedDateEnd(Date releasedDateEnd) {
		this.releasedDateEnd = releasedDateEnd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Long getBuyerCompanyId() {
		return buyerCompanyId;
	}

	public void setBuyerCompanyId(Long buyerCompanyId) {
		this.buyerCompanyId = buyerCompanyId;
	}

	public Long getSellerCompanyId() {
		return sellerCompanyId;
	}

	public void setSellerCompanyId(Long sellerCompanyId) {
		this.sellerCompanyId = sellerCompanyId;
	}


    public String getReleasedDateStartString() {
        return releasedDateStartString;
    }

    public void setReleasedDateStartString(String releasedDateStartString) {
        this.releasedDateStartString = releasedDateStartString;
    }

    public String getReleasedDateEndString() {
        return releasedDateEndString;
    }

    public void setReleasedDateEndString(String releasedDateEndString) {
        this.releasedDateEndString = releasedDateEndString;
    }

}