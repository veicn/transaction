package com.sinochem.crude.trade.order.domain;

/**
 * 贸易商联系人
 * 
 * @author Leo
 *
 */
public class ContractTrader extends ContractRelevanter {

	public ContractTrader() {
		super();
	}

	public ContractTrader(ContractRelevanter relevanter) {
		this.setId(relevanter.getId());
		this.setAddress(relevanter.getAddress());
		this.setContacter(relevanter.getContacter());
		this.setContractId(relevanter.getContractId());
		this.setEMemberId(relevanter.getEMemberId());
		this.seteMemberName(relevanter.geteMemberName());
		this.setFax(relevanter.getFax());
		this.setEmail(relevanter.getEmail());
		this.setPhoneNo(relevanter.getPhoneNo());
		this.setType(relevanter.getType());
		this.setDealerId(relevanter.getDealerId());
		this.setDealerName(relevanter.getDealerName());
	}

	public String getType() {
		return "T";
	}

	public void setType(String type) {
		super.setType("T");
	}
}
