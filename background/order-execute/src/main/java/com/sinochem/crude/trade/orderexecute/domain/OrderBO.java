package com.sinochem.crude.trade.orderexecute.domain;

import java.io.Serializable;
import java.util.List;

import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.orderexecute.commons.constants.ValueSetGroupConstant;
import com.sinochem.crude.trade.orderexecute.commons.utils.ValueSetUtil;

public class OrderBO extends Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4262666969869967764L;
	
	/** 买家信息 */
	private OrderParty buyer;
	/** 卖家信息 */
	private OrderParty seller;
	/** 代理商信息 */
	private OrderParty agent;
	/** 商品信息 */
	private OrderGoods goodsInfo;
	/** 价格信息 */
	private OrderPrice priceInfo;
	/** 运输信息 */
	private OrderTransport transportInfo;
	/** 船舶信息 */
	private OrderShip shipInfo;
	/** 装港信息 */
	private List<OrderShipLoadinginfo> loadinginfoList;
	/** 卸港信息 */
	private List<OrderShipUnloadinginfo> unloadinginfoList;

	/**
	 * 按钮集
	 */
	private List<String> buttonList;
	
	public OrderParty getBuyer() {
		if(buyer == null){
			return new OrderParty();
		}
		return buyer;
	}
	public void setBuyer(OrderParty buyer) {
		this.buyer = buyer;
	}
	public OrderParty getSeller() {
		if(seller == null){
			return new OrderParty();
		}
		return seller;
	}
	public void setSeller(OrderParty seller) {
		this.seller = seller;
	}
	public OrderParty getAgent() {
		return agent;
	}
	public void setAgent(OrderParty agent) {
		this.agent = agent;
	}
	public OrderGoods getGoodsInfo() {
		if(goodsInfo == null){
			return new OrderGoods();
		}
		return goodsInfo;
	}
	public void setGoodsInfo(OrderGoods goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
	public OrderPrice getPriceInfo() {
		if(priceInfo == null){
			return new OrderPrice();
		}
		return priceInfo;
	}
	public void setPriceInfo(OrderPrice priceInfo) {
		this.priceInfo = priceInfo;
	}
	public OrderTransport getTransportInfo() {
		if(transportInfo == null){
			return new OrderTransport();
		}
		return transportInfo;
	}
	public void setTransportInfo(OrderTransport transportInfo) {
		this.transportInfo = transportInfo;
	}
	public List<OrderShipLoadinginfo> getLoadinginfoList() {
		return loadinginfoList;
	}
	public void setLoadinginfoList(List<OrderShipLoadinginfo> loadinginfoList) {
		this.loadinginfoList = loadinginfoList;
	}
	public List<OrderShipUnloadinginfo> getUnloadinginfoList() {
		return unloadinginfoList;
	}
	public void setUnloadinginfoList(List<OrderShipUnloadinginfo> unloadinginfoList) {
		this.unloadinginfoList = unloadinginfoList;
	}
	public List<String> getButtonList() {
		return buttonList;
	}
	public void setButtonList(List<String> buttonList) {
		this.buttonList = buttonList;
	}
	
	public OrderShip getShipInfo() {
		return shipInfo;
	}
	public void setShipInfo(OrderShip shipInfo) {
		this.shipInfo = shipInfo;
	}
	public Order getOrderInfo() {
		return (Order)this;
	}
	
	/** --------------- 代码转换 --------------- **/
	/** 订单状态描述 */
	public String getStatusDesc() {
		CodeValue code = ValueSetUtil.getByCode(ValueSetGroupConstant.ORDER_STATUS, super.getStatus());
		return code.getValue();
	}
	
}
