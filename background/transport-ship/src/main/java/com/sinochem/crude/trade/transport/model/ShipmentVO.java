package com.sinochem.crude.trade.transport.model;

import java.util.List;

import com.sinochem.crude.trade.transport.domain.Shipment;
import com.sinochem.crude.trade.transport.model.res.ShipmentDetail;

public class ShipmentVO extends Shipment {

	private static final long serialVersionUID = 1L;	
	
	/**装港list*/
	private List<ShipmentDetail> list;

	public List<ShipmentDetail> getList() {
		return list;
	}

	public void setList(List<ShipmentDetail> list) {
		this.list = list;
	}
	
	
}