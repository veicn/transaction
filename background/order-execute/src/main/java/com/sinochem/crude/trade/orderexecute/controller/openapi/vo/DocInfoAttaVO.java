package com.sinochem.crude.trade.orderexecute.controller.openapi.vo;

import java.io.Serializable;

/**
 * 单证信息附件VO
 * @author Administrator
 *
 */
public class DocInfoAttaVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 附件代码
	 */
	private String fileCode;
	
	/**
	 * 附件名称
	 */
	private String fileName;
	
	/**
	 * 附件地址
	 */
	private String filePath;
	
	/**
	 * 上传时间
	 */
	private String uploadTime;

	/**
	 * 文件原名
	 */
	private String originalName;
	
	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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