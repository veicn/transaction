package com.sinochem.crude.trade.orderexecute.controller.openapi.vo;

import java.util.List;

/**
 * 
 * @author Down
 *
 */
public class ContractVO extends InputVO {

	private static final long serialVersionUID = 1L;

	/** 合同信息列表 */
	private List<ContractInfoVO> contractList;

	public List<ContractInfoVO> getContractList() {
		return contractList;
	}

	public void setContractList(List<ContractInfoVO> contractList) {
		this.contractList = contractList;
	}
}