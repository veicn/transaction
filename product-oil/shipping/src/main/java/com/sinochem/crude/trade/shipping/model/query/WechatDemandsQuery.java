package com.sinochem.crude.trade.shipping.model.query;

/**
 * author sunjianbo
 * date 2018/3/12 14:11
 * 租船需求列表查询
 */
public class WechatDemandsQuery {
    /**
     * 关键字
     */
    private String keywords;
    /**
     * 公司id
     */
    private Long epMemberId;

    /**
     * 类型
     */
    private Integer type;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Long getEpMemberId() {
        return epMemberId;
    }

    public void setEpMemberId(Long epMemberId) {
        this.epMemberId = epMemberId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
