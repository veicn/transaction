package com.sinochem.crude.trade.order.domain;

/**
 * 买家联系人信息
 * 
 * @author Leo
 *
 */
public class ContractBuyer extends ContractRelevanter {

	public ContractBuyer() {
		super();
	}

	public ContractBuyer(ContractRelevanter relevanter) {
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
		return "B";
	}

	public void setType(String type) {
		super.setType("B");
	}
}
