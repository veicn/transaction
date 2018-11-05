package com.sinochem.crude.trade.order.domain;



/**
 * 合约条款
 */
public class ContractItem  {
	/**
	 * PKid
	 */
	private Long id;
	/**
	 * 合约id
	 */
	private Long contractId;
	/**
	 * 类型 1 原油  2 成品油
	 */
	private Integer type;
	/**
	 * 资源id，如果是原油，原油表里面取，如果是成品油，就从成品油里面取
	 */
	private Long resourceId;
	/**
	 * 数量
	 */
	private Integer num;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}
