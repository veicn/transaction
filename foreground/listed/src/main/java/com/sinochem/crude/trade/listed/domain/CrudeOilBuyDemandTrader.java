package com.sinochem.crude.trade.listed.domain;

public class CrudeOilBuyDemandTrader extends DemandRelevanter {

	public CrudeOilBuyDemandTrader() {
		super.setType("CBT");
	}

	@Override
	public String getType() {
		return "CBT";
	}
}