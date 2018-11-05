package com.sinochem.crude.trade.orderexecute.controller.openapi.vo;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 *
 */
public class ContractInfoAttaVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 合同附件名称
	 */
	private String contractFileName;
	
	/**
	 * 附件地址
	 */
	private String filePath;
	
	/**
	 * 上传时间
	 */
	private String uploadTime;
	
	/**
	 * 原文件名
	 */
	private String originalName;

	public String getContractFileName() {
		return contractFileName;
	}

	public void setContractFileName(String contractFileName) {
		this.contractFileName = contractFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	
}