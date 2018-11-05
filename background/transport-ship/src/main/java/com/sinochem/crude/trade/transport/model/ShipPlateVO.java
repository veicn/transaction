package com.sinochem.crude.trade.transport.model;

import org.codehaus.jackson.annotate.JsonUnwrapped;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.domain.ShipPlate;

public class ShipPlateVO extends ShipPlate {

	private static final long serialVersionUID = 1L;

	/** 操作类型（1:发布，2:保存，3:确认发布，4:下架） */
	private String saveType;

	/** 操作类型（1:发布，2:保存，3:确认发布，4:下架） */
	public String getSaveType() {
		return saveType;
	}

	/** 多个船盘uuid */
	private String uuidStr;

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}

	@JsonUnwrapped
	private SimplePageInfo pageInfo;

	public SimplePageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(SimplePageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getUuidStr() {
		return uuidStr;
	}

	public void setUuidStr(String uuidStr) {
		this.uuidStr = uuidStr;
	}

}