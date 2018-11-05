package com.sinochem.crude.trade.shiprefueling.model.vo;

import com.sinochem.crude.trade.shiprefueling.model.query.QueryRIgnition;
import com.sinochem.crude.trade.shiprefueling.model.query.QueryRSupply;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class QueryRSupplyVO implements Serializable {

	/**订单编号*/
	private String orderCode;  

	/**运输方式*/
	private String transportWay;
	/**
	 * 发布时间-开始
	 */
	private String orderTimeStart;
	/**
	 * 发布时间-结束
	 */
	private String orderTimeEnd;
    /**
     * 状态
	 */
	private String status;

	/*品类*/
	private String oilClassification;

	/**规格*/
	private String oilVarieties;

	/**
	 * 买家id
	 *
	 */
	private Long buyerCompanyId;
	/**
	 * 卖家id
	 *
	 */
	private Long sellerCompanyId;

	public QueryRSupply getQueryRSupply(){
		QueryRSupply queryRSupply = new QueryRSupply();
		if(!com.eyeieye.melody.util.StringUtil.isEmpty(this.oilClassification)){
			queryRSupply.setOilClassification(this.oilClassification);
		}
		if(!com.eyeieye.melody.util.StringUtil.isEmpty(this.oilVarieties)){
			queryRSupply.setOilVarieties(this.oilVarieties);
		}
		if(!com.eyeieye.melody.util.StringUtil.isEmpty(this.orderCode)){
			queryRSupply.setOrderCode(this.orderCode);
		}
		if(!com.eyeieye.melody.util.StringUtil.isEmpty(this.status)){
			queryRSupply.setStatus(this.status);
		}
		if(!com.eyeieye.melody.util.StringUtil.isEmpty(this.transportWay)){
			queryRSupply.setTransportWay(this.transportWay);
		}
		if(this.buyerCompanyId != null){
			queryRSupply.setBuyerCompanyId(this.buyerCompanyId);
		}
		if(this.sellerCompanyId != null){
			queryRSupply.setSellerCompanyId(this.sellerCompanyId);
		}
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(!com.eyeieye.melody.util.StringUtil.isEmpty(this.orderTimeStart)){

				queryRSupply.setReleasedDateStart(sdf.parse(this.orderTimeStart));
                queryRSupply.setReleasedDateStartString(this.orderTimeStart);
			}
			if(!com.eyeieye.melody.util.StringUtil.isEmpty(this.orderTimeEnd)){
				queryRSupply.setReleasedDateEnd(sdf.parse(this.orderTimeEnd));
                queryRSupply.setReleasedDateEndString(this.orderTimeEnd);
			}
		}catch(ParseException p){

		}
		return queryRSupply;
	}

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
}