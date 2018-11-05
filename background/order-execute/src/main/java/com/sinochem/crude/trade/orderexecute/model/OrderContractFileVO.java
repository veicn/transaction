package com.sinochem.crude.trade.orderexecute.model;

import org.springframework.web.multipart.MultipartFile;

import com.sinochem.crude.trade.orderexecute.domain.OrderContractFile;

public class OrderContractFileVO extends OrderContractFile {

	private static final long serialVersionUID = 1L;
	
	/** 合同附件文件 */
	private MultipartFile file;
	
	/** 文件地址 */
	private String ossPath;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getOssPath() {
		return ossPath;
	}

	public void setOssPath(String ossPath) {
		this.ossPath = ossPath;
	}

}