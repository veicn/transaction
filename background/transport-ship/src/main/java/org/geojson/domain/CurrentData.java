package org.geojson.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.tools.ant.taskdefs.Javadoc.Html;

public class CurrentData implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1174979862036036276L;
	
	private int recordTotal;
	private int pageIndex;
	private int pageSize;
	private int pageTotal;
	
	
	private List<CurrentInfo> data;
	
	private HtmlInfo html;


	public HtmlInfo getHtml() {
		return html;
	}


	public void setHtml(HtmlInfo html) {
		this.html = html;
	}


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


	public List<CurrentInfo> getData() {
		return data;
	}


	public void setData(List<CurrentInfo> data) {
		this.data = data;
	}
	
	
	
	
}
