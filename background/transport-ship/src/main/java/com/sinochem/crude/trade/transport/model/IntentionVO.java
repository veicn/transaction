package com.sinochem.crude.trade.transport.model;

import org.codehaus.jackson.annotate.JsonUnwrapped;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.domain.Intention;

public class IntentionVO extends Intention {

	private static final long serialVersionUID = 1L;	
	
	@JsonUnwrapped
	private SimplePageInfo pageInfo;

	public SimplePageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(SimplePageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	

}