package com.sinochem.crude.trade.transport.model;

import org.codehaus.jackson.annotate.JsonUnwrapped;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.domain.ShipPact;

public class ShipPactVO extends ShipPact {

	private static final long serialVersionUID = 1L;

	@JsonUnwrapped
	private SimplePageInfo pageInfo;

	/** 代理协议uuid */
	private String agreementUuid;
	
	/**值集类型（1经纪人2装港商检3卸港商检4卸港5装港6卸港船代7卸港船监8船东9船型10油种11装港船代12大洲13船龄14单位换算类型15基础运价年份16船位17港口100自定义code）*/
	private String valueSetType;
	/**多个用;拼接--->4;5;*/
	private String valueSetTypes;

	/** 订单编号 */
	private String orderCode;

	/** 自定义code */
	private String subGroup;

	/** 订单编号 */
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}


	public String getSubGroup() {
		return subGroup;
	}

	public void setSubGroup(String subGroup) {
		this.subGroup = subGroup;
	}

	public String getValueSetTypes() {
		return valueSetTypes;
	}

	public void setValueSetTypes(String valueSetTypes) {
		this.valueSetTypes = valueSetTypes;
	}

	/** 值集类型（1经纪人2装港商检3卸港商检4卸港5装港6卸港船代7卸港船监8船东9船型10油种11装港船代） */
	public String getValueSetType() {
		return valueSetType;
	}

	/** 值集类型（1经纪人2装港商检3卸港商检4卸港5装港6卸港船代7卸港船监8船东9船型10油种11装港船代） */
	public void setValueSetType(String valueSetType) {
		this.valueSetType = valueSetType;
	}

	public String getAgreementUuid() {
		return agreementUuid;
	}

	public void setAgreementUuid(String agreementUuid) {
		this.agreementUuid = agreementUuid;
	}

	public SimplePageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(SimplePageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

}