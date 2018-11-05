package com.sinochem.crude.trade.orderexecute.model;

import org.codehaus.jackson.annotate.JsonUnwrapped;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceSystem;

public class InterfaceSystemVO extends InterfaceSystem {

	private static final long serialVersionUID = 1L;	
	
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