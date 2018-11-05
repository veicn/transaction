package com.sinochem.crude.trade.transport.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonUnwrapped;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.domain.Waybill;

public class WaybillVO extends Waybill {

	private static final long serialVersionUID = 1L;	
	
	/**分页对象*/
	@JsonUnwrapped
	private SimplePageInfo pageInfo;

	/**协议uuid*/
	private List<String> agreementUuids;
	

	public List<String> getAgreementUuids() {
		return agreementUuids;
	}

	public void setAgreementUuids(List<String> agreementUuids) {
		this.agreementUuids = agreementUuids;
	}

	public SimplePageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(SimplePageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
}