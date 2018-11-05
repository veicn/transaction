package com.sinochem.crude.trade.shipagent.domain.vo;

import com.sinochem.crude.trade.shipagent.domain.TShipagentDocument;

/**
 *
 * @author admin1
 * @date 2018/9/18
 */
public class TShipagentDocumentQuery extends TShipagentDocument {

	private Integer pageNum = 1;

	private Integer pageSize = 10;

	/** 单证状态*/
	private String status;
	/** 时间*/
	private String queryDate;
	/**外贸合同号*/
	private String purchaseContractNo;


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getPurchaseContractNo() {
		return purchaseContractNo;
	}

	public void setPurchaseContractNo(String purchaseContractNo) {
		this.purchaseContractNo = purchaseContractNo;
	}
}
