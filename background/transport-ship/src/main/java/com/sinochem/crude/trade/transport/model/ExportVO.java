package com.sinochem.crude.trade.transport.model;

import java.io.Serializable;
import java.util.List;

public class ExportVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**导出类型（1船在途）（2配载计划）（3装港信息）（4卸港信息）（5船盘信息）（6船舶信息：船东/经纪人）（7船舶信息：平台）*/
	private String type;
	
	private List<String> headList;
	
	private List<List<String>> contentList;

	private String shipPactUuid;
	
	public String getShipPactUuid() {
		return shipPactUuid;
	}

	public void setShipPactUuid(String shipPactUUID) {
		this.shipPactUuid = shipPactUUID;
	}

	/**导出类型（1船在途）（2配载计划）（3装港信息）（4卸港信息）（5船盘信息）（6船舶信息）*/
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getHeadList() {
		return headList;
	}

	public void setHeadList(List<String> headList) {
		this.headList = headList;
	}

	public List<List<String>> getContentList() {
		return contentList;
	}

	public void setContentList(List<List<String>> contentList) {
		this.contentList = contentList;
	}
	
	
	
}
