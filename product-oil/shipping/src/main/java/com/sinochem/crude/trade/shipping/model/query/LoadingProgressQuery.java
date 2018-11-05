package com.sinochem.crude.trade.shipping.model.query;

public class LoadingProgressQuery {

	private String uuid;
	/**船舶确认单ID*/
	private Long shipConfirmationSheetId;  
	
	/**船舶装港表ID*/
	private String shipLoadPortId;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getShipConfirmationSheetId() {
		return shipConfirmationSheetId;
	}

	public void setShipConfirmationSheetId(Long shipConfirmationSheetId) {
		this.shipConfirmationSheetId = shipConfirmationSheetId;
	}

	public String getShipLoadPortId() {
		return shipLoadPortId;
	}

	public void setShipLoadPortId(String shipLoadPortId) {
		this.shipLoadPortId = shipLoadPortId;
	}  
	
	
}
