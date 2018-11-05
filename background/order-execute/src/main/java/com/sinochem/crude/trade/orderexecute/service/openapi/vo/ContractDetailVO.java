package com.sinochem.crude.trade.orderexecute.service.openapi.vo;

import java.io.Serializable;

public class ContractDetailVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 附件名称 */
	private String file_name;
	
	/** 附件路径 */
	private String file_path;

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
}
