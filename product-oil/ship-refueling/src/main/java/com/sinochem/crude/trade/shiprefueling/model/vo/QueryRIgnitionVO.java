package com.sinochem.crude.trade.shiprefueling.model.vo;

import com.sinochem.crude.trade.shiprefueling.model.query.QueryRIgnition;
import com.sinochem.crude.trade.common.utils.DateUtil;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class QueryRIgnitionVO implements Serializable {

	/**订单编号*/
	private String orderCode;  

	/**运输方式*/
	private String transportWay;



    /**提货方式*/
    private String deliveryWay;

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


    private String infoType;

    public String getSysTag() {
        return sysTag;
    }

    public void setSysTag(String sysTag) {
        this.sysTag = sysTag;
    }

    private String sysTag;

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

	public QueryRIgnition getQueryRIgnition(){
		QueryRIgnition queryRIgnition = new QueryRIgnition();
		if(!com.eyeieye.melody.util.StringUtil.isEmpty(this.oilClassification)){
			queryRIgnition.setOilClassification(this.oilClassification);
		}
		if(!com.eyeieye.melody.util.StringUtil.isEmpty(this.oilVarieties)){
			queryRIgnition.setOilVarieties(this.oilVarieties);
		}
		if(!com.eyeieye.melody.util.StringUtil.isEmpty(this.orderCode)){
			queryRIgnition.setOrderCode(this.orderCode);
		}
		if(!com.eyeieye.melody.util.StringUtil.isEmpty(this.status)){
			queryRIgnition.setStatus(this.status);
		}
		if(!com.eyeieye.melody.util.StringUtil.isEmpty(this.transportWay)){
			queryRIgnition.setTransportWay(this.transportWay);
		}
        if(!com.eyeieye.melody.util.StringUtil.isEmpty(this.deliveryWay)){
            queryRIgnition.setDeliveryWay(this.deliveryWay);
        }
		if(this.buyerCompanyId != null){
			queryRIgnition.setBuyerCompanyId(this.buyerCompanyId);
		}
		if(this.sellerCompanyId != null){
			queryRIgnition.setSellerCompanyId(this.sellerCompanyId);
		}
        if(!com.eyeieye.melody.util.StringUtil.isEmpty(this.infoType)){
            queryRIgnition.setInfoType(this.infoType);
        }
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(!com.eyeieye.melody.util.StringUtil.isEmpty(this.orderTimeStart)){

				queryRIgnition.setReleasedDateStart(sdf.parse(this.orderTimeStart));
                queryRIgnition.setReleasedDateStartString(this.orderTimeStart);
			}
			if(!com.eyeieye.melody.util.StringUtil.isEmpty(this.orderTimeEnd)){
				queryRIgnition.setReleasedDateEnd(sdf.parse(this.orderTimeEnd));
                queryRIgnition.setReleasedDateEndString(this.orderTimeEnd);
			}
		}catch(ParseException p){

		}
		return queryRIgnition;
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
    public String getDeliveryWay() {
        return deliveryWay;
    }

    public void setDeliveryWay(String deliveryWay) {
        this.deliveryWay = deliveryWay;
    }
}