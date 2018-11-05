package com.sinochem.crude.trade.customs.model.query;

import com.sinochem.crude.trade.transaction.domain.SimplePageInfo;
import org.codehaus.jackson.annotate.JsonUnwrapped;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 海关首页
 * @author wdh
 * @date 2018/09/25
 */
public class HomePageQuery implements Serializable {

    private String customs_declaration_state;

    private String deal_no;

    public String getDeal_no() {
        return deal_no;
    }

    public void setDeal_no(String deal_no) {
        this.deal_no = deal_no;
    }

    public String getCustoms_declaration_state() {
        return customs_declaration_state;
    }

    public void setCustoms_declaration_state(String customs_declaration_state) {
        this.customs_declaration_state = customs_declaration_state;
    }

    private Integer pageNum = Integer.valueOf(1);
    private Integer pageSize = Integer.valueOf(10);
    private Boolean isCount;

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

    public Boolean getCount() {
        return isCount;
    }

    public void setCount(Boolean count) {
        isCount = count;
    }
//
//    @JsonUnwrapped
//    private SimplePageInfo pageInfo;
//
//    public SimplePageInfo getPageInfo() {
//        return pageInfo;
//    }
//    public void setPageInfo(SimplePageInfo pageInfo) {
//        this.pageInfo = pageInfo;
//    }

    public Map<String, Object> toMap() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("customs_declaration_state",this.customs_declaration_state);
        map.put("deal_no",this.deal_no);
        map.put("pageNum",this.pageNum);
        map.put("pageSize",this.pageSize);
        map.put("isCount",this.isCount);
        return map;
    }

}
