package com.sinochem.crude.trade.values.vo;

/**
 * Created by GHuang on 2017/2/6.
 */
public class CodeSetQuery {

    private String module;
    private String setName;
    private String setCode;
    private Integer pageNum;
    private Integer pageSize;
    private Integer pageCount;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSetName() {
        return setName;
    }

    public String getSetCode() {
        return setCode;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode;
    }

    public void setSetName(String setName) {
        this.setName = setName;
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
