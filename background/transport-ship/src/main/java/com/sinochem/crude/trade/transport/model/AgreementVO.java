package com.sinochem.crude.trade.transport.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonUnwrapped;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.domain.Agreement;

public class AgreementVO extends Agreement {

	private static final long serialVersionUID = 1L;	
	
	/**分页对象*/
	@JsonUnwrapped
	private SimplePageInfo pageInfo;

	// 船盘uuid
	private String shipPlateUuid;
	
	private String type;
	/**
	 * 协议uuids
	 */
	private List<String> agreementUuids;
	
	public List<String> getAgreementUuids() {
		return agreementUuids;
	}

	public void setAgreementUuids(List<String> agreementUuids) {
		this.agreementUuids = agreementUuids;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SimplePageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(SimplePageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getShipPlateUuid() {
		return shipPlateUuid;
	}

	public void setShipPlateUuid(String shipPlateUuid) {
		this.shipPlateUuid = shipPlateUuid;
	}
	
	
}