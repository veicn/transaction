package com.sinochem.crude.trade.operation.vo;

import java.io.Serializable;
import java.util.List;

public class OpeAdImageRes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 1:默认图 */
	private String type;

	/** 广告页面位置 */
	private String adPageSet;

	private List<OpeAdImage> list;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAdPageSet() {
		return adPageSet;
	}

	public void setAdPageSet(String adPageSet) {
		this.adPageSet = adPageSet;
	}

	public List<OpeAdImage> getList() {
		return list;
	}

	public void setList(List<OpeAdImage> list) {
		this.list = list;
	}

}
