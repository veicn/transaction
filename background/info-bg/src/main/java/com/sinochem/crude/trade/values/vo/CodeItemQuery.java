package com.sinochem.crude.trade.values.vo;

/**
 * Created by GHuang on 2017/2/8.
 */
public class CodeItemQuery {

    private String setCode;
    private Integer pageNum;
    private Integer pageSize;

    public String getSetCode() {
        return setCode;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode;
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
}
