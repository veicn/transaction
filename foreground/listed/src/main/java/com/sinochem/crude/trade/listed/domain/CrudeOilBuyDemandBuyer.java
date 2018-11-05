package com.sinochem.crude.trade.listed.domain;

public class CrudeOilBuyDemandBuyer extends DemandRelevanter {

	public CrudeOilBuyDemandBuyer() {
		super.setType("CBB");
	}

	@Override
	public String getType() {
		return "CBB";
	}
}
