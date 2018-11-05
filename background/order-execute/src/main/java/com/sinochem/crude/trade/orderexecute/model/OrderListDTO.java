package com.sinochem.crude.trade.orderexecute.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 移动端 订单列表数据
 * @author me
 *
 */
public class OrderListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7775737268077650780L;
	
	private String uuid;
	/** 订单编号 */
	private String orderNo;
	/** 商品名称 */
	private String goodsName;
	/** 数量 */
	private String quantity;
	/** 贸易条款 */
	private String tradeTerm;
	/** 装期 */
	private String deliveryDate;
	/** 到货期 */
	private String dischargeDate;
	/** 装船港 */
	private String loadingPort;
	/** 价格 */
	private String price;
	/** 升贴水 */
	private String agio;
	/** 订单状态描述 */
	private String statusDesc;
	/** 交易大类（1原油；2成品油；3化工品） */
	private String tradeCategory;
	/** 买卖类型 */
	private String buySell;
	/** LOGO */
	private String logoUrl;
	/** operate button list */
	private List<String> buttonList;
	
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getTradeTerm() {
		return tradeTerm;
	}
	public void setTradeTerm(String tradeTerm) {
		this.tradeTerm = tradeTerm;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	
	public void setDeliveryDate(Date deliveryDateStart, Date deliveryDateEnd) {
		this.deliveryDate = "";
		if(deliveryDateStart != null) {
			this.deliveryDate += DateFormatUtils.format(deliveryDateStart, "yyyy/MM/dd");
		}
		if(deliveryDateEnd != null) {
			this.deliveryDate += "-";
			this.deliveryDate += DateFormatUtils.format(deliveryDateEnd, "yyyy/MM/dd");
		}
	}
	
	public String getDischargeDate() {
		return dischargeDate;
	}
	
	public void setDischargeDate(Date dischargeDateStart, Date dischargeDateEnd) {
		this.dischargeDate = "";
		if(dischargeDateStart != null) {
			this.dischargeDate += DateFormatUtils.format(dischargeDateStart, "yyyy/MM/dd");
		}
		if(dischargeDateEnd != null) {
			this.dischargeDate += "-";
			this.dischargeDate += DateFormatUtils.format(dischargeDateEnd, "yyyy/MM/dd");
		}
	}
	
	public String getLoadingPort() {
		return loadingPort;
	}
	public void setLoadingPort(String loadingPort) {
		this.loadingPort = loadingPort;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price, String currency, String quantityUnit) {
		if(price != null) {
			this.price = price.toString()+currency+"/"+quantityUnit;
		}
	}
	public String getAgio() {
		return agio;
	}
	public void setAgio(BigDecimal agio, String currency, String quantityUnit) {
		if(agio != null) {
			this.agio = String.valueOf(agio.doubleValue())+currency+"/"+quantityUnit;
		}
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getTradeCategory() {
		return tradeCategory;
	}
	public void setTradeCategory(String tradeCategory) {
		this.tradeCategory = tradeCategory;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public List<String> getButtonList() {
		return buttonList;
	}
	public void setButtonList(List<String> buttonList) {
		this.buttonList = buttonList;
	}
	public String getBuySell() {
		return buySell;
	}
	public void setBuySell(String buySell) {
		this.buySell = buySell;
	}
	
}
