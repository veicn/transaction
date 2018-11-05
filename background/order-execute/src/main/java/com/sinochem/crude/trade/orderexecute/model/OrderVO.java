package com.sinochem.crude.trade.orderexecute.model;

import java.util.List;

import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.orderexecute.commons.constants.ValueSetGroupConstant;
import com.sinochem.crude.trade.orderexecute.commons.utils.ValueSetUtil;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderGoods;
import com.sinochem.crude.trade.orderexecute.domain.OrderParty;
import com.sinochem.crude.trade.orderexecute.domain.OrderPrice;
import com.sinochem.crude.trade.orderexecute.domain.OrderShip;
import com.sinochem.crude.trade.orderexecute.domain.OrderShipLoadinginfo;
import com.sinochem.crude.trade.orderexecute.domain.OrderShipUnloadinginfo;
import com.sinochem.crude.trade.orderexecute.domain.OrderTransport;

public class OrderVO extends Order {

	private static final long serialVersionUID = 1L;
	
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
	/** 装港信息 */
	private List<OrderShipLoadinginfo> loadinginfoList;
	/** 卸港信息 */
	private List<OrderShipUnloadinginfo> unloadinginfoList;
	/** 船务信息 */
	private OrderShip orderShipInfo;
	/** 合约信息 */
	private List<TriggerContractVO> triggerContractList;
	/** 转月信息 */
	private List<TriggerTransferVO> triggerTransferList;
	/** 点价信息 */
	private List<TriggerApplyVO> triggerApplyList;
	/** 拆单信息 */
	private List<TriggerResultVO> triggerResultList;
	
	public OrderParty getBuyer() {
		return buyer;
	}
	public void setBuyer(OrderParty buyer) {
		this.buyer = buyer;
	}
	public OrderParty getSeller() {
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
		return goodsInfo;
	}
	public void setGoodsInfo(OrderGoods goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
	public OrderPrice getPriceInfo() {
		return priceInfo;
	}
	public void setPriceInfo(OrderPrice priceInfo) {
		this.priceInfo = priceInfo;
	}
	public OrderTransport getTransportInfo() {
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
	public OrderShip getOrderShipInfo() {
		return orderShipInfo;
	}
	public void setOrderShipInfo(OrderShip orderShipInfo) {
		this.orderShipInfo = orderShipInfo;
	}
	public List<TriggerContractVO> getTriggerContractList() {
		return triggerContractList;
	}
	public void setTriggerContractList(List<TriggerContractVO> triggerContractList) {
		this.triggerContractList = triggerContractList;
	}
	public List<TriggerTransferVO> getTriggerTransferList() {
		return triggerTransferList;
	}
	public void setTriggerTransferList(List<TriggerTransferVO> triggerTransferList) {
		this.triggerTransferList = triggerTransferList;
	}
	public List<TriggerApplyVO> getTriggerApplyList() {
		return triggerApplyList;
	}
	public void setTriggerApplyList(List<TriggerApplyVO> triggerApplyList) {
		this.triggerApplyList = triggerApplyList;
	}
	public List<TriggerResultVO> getTriggerResultList() {
		return triggerResultList;
	}
	public void setTriggerResultList(List<TriggerResultVO> triggerResultList) {
		this.triggerResultList = triggerResultList;
	}
	/** --------------- 代码转换 --------------- **/
	/** 订单状态描述 */
	public String getStatusDesc() {
		CodeValue code = ValueSetUtil.getByCode(ValueSetGroupConstant.ORDER_STATUS, super.getStatus());
		return code.getValue();
	}
	/** 价格方式 */
	public String getPriceTypeDesc() {
		if(priceInfo != null){
			CodeValue code = ValueSetUtil.getByCode(ValueSetGroupConstant.PRICE_TYPE, priceInfo.getPriceType());
			return code.getValue();
		}
		return null;
	}
}