package com.sinochem.crude.trade.shipping.model.vo;

import java.util.List;

public class TransitLoadingRequseVo {

	private List<TransitLoadingVO> volist;

	private String conUuid;
	
	private Integer tableMaxFlag;
	
	

	public Integer getTableMaxFlag() {
		return tableMaxFlag;
	}

	public void setTableMaxFlag(Integer tableMaxFlag) {
		this.tableMaxFlag = tableMaxFlag;
	}

	public String getConUuid() {
		return conUuid;
	}

	public void setConUuid(String conUuid) {
		this.conUuid = conUuid;
	}

	public List<TransitLoadingVO> getVolist() {
		return volist;
	}

	public void setVolist(List<TransitLoadingVO> volist) {
		this.volist = volist;
	}

	
	
	
}
