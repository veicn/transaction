package com.sinochem.crude.trade.customs.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/7/13 14:46
 * @Version: [v1.0]
 */
public class ResultDatas extends ResultData {
    private Integer pageNum;

    private Integer pageSize;

    private Integer pageCount;

    private List content;

    private Long total;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public List getContent() {
        return content;
    }

    public void setContent(List content) {
        this.content = content;
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
}
