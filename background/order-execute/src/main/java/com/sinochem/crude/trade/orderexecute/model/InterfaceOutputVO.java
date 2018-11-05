package com.sinochem.crude.trade.orderexecute.model;

import org.codehaus.jackson.annotate.JsonUnwrapped;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceOutput;

public class InterfaceOutputVO extends InterfaceOutput {

	private static final long serialVersionUID = 1L;	
	
	/**系统描述*/
	private String sysDec; 
	/**接口说明*/
	private String interfaceDesc; 
	
	public String getSysDec() {
		return sysDec;
	}

	public void setSysDec(String sysDec) {
		this.sysDec = sysDec;
	}

	public String getInterfaceDesc() {
		return interfaceDesc;
	}

	public void setInterfaceDesc(String interfaceDesc) {
		this.interfaceDesc = interfaceDesc;
	}

	/**分页对象*/
	@JsonUnwrapped
	private SimplePageInfo pageInfo;

	public SimplePageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(SimplePageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
}