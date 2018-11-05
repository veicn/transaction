package com.sinochem.crude.trade.transport.model;

import java.io.Serializable;
import java.util.List;

public class ExportBtVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	
	private List<String> headList;
	
	private List<List<String>> contentList;

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
