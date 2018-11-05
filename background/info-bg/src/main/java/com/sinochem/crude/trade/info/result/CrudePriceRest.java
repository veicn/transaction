package com.sinochem.crude.trade.info.result;

import java.io.Serializable;
import java.util.List;

public class CrudePriceRest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String crudeNameE;
	
	private Boolean isExy;
	
	private List<CrudePriceDetailRest> dataList;
	
	public String getCrudeNameE() {
		return crudeNameE;
	}

	public void setCrudeNameE(String crudeNameE) {
		this.crudeNameE = crudeNameE;
	}
	
	public Boolean getIsExy() {
		return isExy;
	}

	public void setIsExy(Boolean isExy) {
		this.isExy = isExy;
	}

	public List<CrudePriceDetailRest> getDataList() {
		return dataList;
	}

	public void setDataList(List<CrudePriceDetailRest> dataList) {
		this.dataList = dataList;
	}
}
