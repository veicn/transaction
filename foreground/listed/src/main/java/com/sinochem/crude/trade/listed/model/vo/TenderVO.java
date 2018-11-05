package com.sinochem.crude.trade.listed.model.vo;

public class TenderVO {
	private DemandVO demand;
    private DemandShipVO ship;
    private DemandRelevanterBuyerVO buyer;
    private Long demendId;
    private String shipBerthIds;
	public DemandVO getDemand() {
		return demand;
	}
	public void setDemand(DemandVO demand) {
		this.demand = demand;
	}
	public DemandShipVO getShip() {
		return ship;
	}
	public void setShip(DemandShipVO ship) {
		this.ship = ship;
	}
	public DemandRelevanterBuyerVO getBuyer() {
		return buyer;
	}
	public void setBuyer(DemandRelevanterBuyerVO buyer) {
		this.buyer = buyer;
	}
	public Long getDemendId() {
		return demendId;
	}
	public void setDemendId(Long demendId) {
		this.demendId = demendId;
	}
	public String getShipBerthIds() {
		return shipBerthIds;
	}
	public void setShipBerthIds(String shipBerthIds) {
		this.shipBerthIds = shipBerthIds;
	}
    
}
