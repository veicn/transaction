package com.sinochem.crude.trade.info.query;

public class ColumnAuditQuery {

	private String status; // 状态 00:取消申请 01:审核不通过 09待提交(草稿) 10:申请中 20:审核通过
	private String columnClassifyName; // 专栏分类名
	private Integer pageNum;
    private Integer pageSize;
    private Integer pageCount;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getColumnClassifyName() {
		return columnClassifyName;
	}
	public void setColumnClassifyName(String columnClassifyName) {
		this.columnClassifyName = columnClassifyName;
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
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}




}
