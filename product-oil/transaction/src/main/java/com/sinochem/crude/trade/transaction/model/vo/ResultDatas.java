package com.sinochem.crude.trade.transaction.model.vo;

import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import org.apache.poi.ss.formula.functions.T;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/7/13 14:46
 * @Version: [v1.0]
 */
public class ResultDatas extends com.sinochem.it.b2b.common.result.ResultDatas {

    private Integer pageNum;

    private Integer pageSize;

    private Integer pageCount;

    private List content;

    private Long total;

    public List getContent() {
        return content;
    }

    public void setContent(List content) {
        this.content = content;
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
    //wdh_add_枚举_20180927
    private HashMap<String, DictionaryVO> ProductCategoryEnum;

    public HashMap<String, DictionaryVO> getProductCategoryEnum() {
        return ProductCategoryEnum;
    }

    public void setProductCategoryEnum(HashMap<String, DictionaryVO> productCategoryEnum) {
        ProductCategoryEnum = productCategoryEnum;
    }

    public HashMap<String, DictionaryVO> getAppointEnum() {
        return AppointEnum;
    }

    public void setAppointEnum(HashMap<String, DictionaryVO> appointEnum) {
        AppointEnum = appointEnum;
    }

    private HashMap<String, DictionaryVO> AppointEnum;

}
