package com.sinochem.crude.trade.orderexecute.controller.openapi.vo;

import java.util.List;

/**
 * 单证信息VO
 * @author Administrator
 *
 */
public class DocInfoVO extends InputVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 合同号
	 */
	private String contractNo;
	
	/**
	 * 单证类型
	 */
	private String documentType;
	
	/**
	 * 附件清单
	 */
	private List<DocInfoAttaVO> fileList;
	
	/**
	 * 订单ID
	 */
	private String uuid;

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public List<DocInfoAttaVO> getFileList() {
		return fileList;
	}

	public void setFileList(List<DocInfoAttaVO> fileList) {
		this.fileList = fileList;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}