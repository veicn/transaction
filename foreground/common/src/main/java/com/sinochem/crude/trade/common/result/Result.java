package com.sinochem.crude.trade.common.result;

import java.io.Serializable;

public class Result implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int SUCCESS = 0;

	public static final int EEROR = 9999;

	private int status;
	
	private String code;

	private String message;
	
	private Integer pageNum;
	
	private Integer pageSize;
	
	private Integer pageCount;
	
	private Long total;

	public Result() {
		this.status = SUCCESS;
		message = "成功";
	}

	public Result(String message) {
		this.status = SUCCESS;
		this.message = message;
	}

	public Result(int status) {
		this.status = status;
	}

	public Result(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public void setFail(String message, int status) {
		this.message = message;
		this.status = status;
	}

	public void setFail(String message) {
		this.setFail(message, EEROR);
	}

}
