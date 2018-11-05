package com.sinochem.crude.trade.listed.model.form;

import javax.validation.Valid;

import com.sinochem.crude.trade.listed.model.vo.DemandRelevanterBuyerVO;
import com.sinochem.crude.trade.listed.model.vo.DemandShipVO;
import com.sinochem.crude.trade.listed.model.vo.DemandVO;

public class TenderForm {
	@Valid
	private DemandVO demand = new DemandVO();
	@Valid
    private DemandShipVO ship = new DemandShipVO();
	@Valid
    private DemandRelevanterBuyerVO buyer = new DemandRelevanterBuyerVO();
    
    
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
    
    
    
}
