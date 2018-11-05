package org.geojson.domain;

import java.io.Serializable;
import java.util.List;

public class HistoryData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1504081559930905926L;
	private int recordTotal;
	private int pageIndex;
	private int pageSize;
	private int pageTotal;
	
	private List<HistoryInfo> data;

	public int getRecordTotal() {
		return recordTotal;
	}

	public void setRecordTotal(int recordTotal) {
		this.recordTotal = recordTotal;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public List<HistoryInfo> getData() {
		return data;
	}

	public void setData(List<HistoryInfo> data) {
		this.data = data;
	}
	
	
}
