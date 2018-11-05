package com.sinochem.crude.trade.shiprefueling.model.vo;

import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.domain.po.IryQuotation;
import org.codehaus.jackson.annotate.JsonUnwrapped;


public class IryQuotationVO extends IryQuotation {

	private static final long serialVersionUID = 1L;
	//数据状态
	public static final String ALIVE_FLAG_NORMAL = "1";
	public static final String ALIVE_FLAG_INVALID = "0";


	public static final String STATUS_NOT_CONNECTED = "1";
	public static final String STATUS_CONNECTED = "2";
	public static final String STATUS_DONE = "3";


	@JsonUnwrapped
	private SimplePageInfo pageInfo;
	
	private String issueTimeStart;//发布起始时间
	private String issueTimeEnd;//发布结束时间
	    
	
	public SimplePageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(SimplePageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public String getIssueTimeStart() {
		return issueTimeStart;
	}
	public void setIssueTimeStart(String issueTimeStart) {
		this.issueTimeStart = issueTimeStart;
	}
	public String getIssueTimeEnd() {
		return issueTimeEnd;
	}
	public void setIssueTimeEnd(String issueTimeEnd) {
		this.issueTimeEnd = issueTimeEnd;
	}
	
}