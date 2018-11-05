package com.sinochem.crude.trade.orderexecute.model;

import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderGoods;
import com.sinochem.crude.trade.orderexecute.domain.OrderParty;
import com.sinochem.crude.trade.orderexecute.domain.OrderPrice;
import com.sinochem.crude.trade.orderexecute.domain.OrderTransport;

public class OrderCreateVO {
	/** 订单信息 */
	private Order order;
	/** 买家信息 */
	private OrderParty buyerInfo;
	/** 卖家信息 */
	private OrderParty sellerInfo;
	/** 代理商信息 */
	private OrderParty agentInfo;
	/** 报价信息 */
	private OrderPrice offerInfo;
	/** 商品信息 */
	private OrderGoods goodsInfo;
	/** 运输信息 */
	private OrderTransport transportInfo;
	
	public Order getOrder() {
		return order;
	}
	public OrderParty getBuyerInfo() {
		return buyerInfo;
	}
	public OrderParty getSellerInfo() {
		return sellerInfo;
	}
	public OrderParty getAgentInfo() {
		return agentInfo;
	}
	public OrderPrice getOfferInfo() {
		return offerInfo;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public void setBuyerInfo(OrderParty buyerInfo) {
		this.buyerInfo = buyerInfo;
	}
	public void setSellerInfo(OrderParty sellerInfo) {
		this.sellerInfo = sellerInfo;
	}
	public void setAgentInfo(OrderParty agentInfo) {
		this.agentInfo = agentInfo;
	}
	public void setOfferInfo(OrderPrice offerInfo) {
		this.offerInfo = offerInfo;
	}
	public OrderGoods getGoodsInfo() {
		return goodsInfo;
	}
	public OrderTransport getTransportInfo() {
		return transportInfo;
	}
	public void setGoodsInfo(OrderGoods goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
	public void setTransportInfo(OrderTransport transportInfo) {
		this.transportInfo = transportInfo;
	}
	
}
