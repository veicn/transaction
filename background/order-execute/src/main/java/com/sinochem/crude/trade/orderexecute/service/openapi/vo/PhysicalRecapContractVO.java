package com.sinochem.crude.trade.orderexecute.service.openapi.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 实货附件VO
 */
public class PhysicalRecapContractVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 订单UUID */
	private String id;
	
	/** 附件列表 */
	private List<ContractDetailVO> files;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ContractDetailVO> getFiles() {
		return files;
	}

	public void setFiles(List<ContractDetailVO> files) {
		this.files = files;
	}
}
