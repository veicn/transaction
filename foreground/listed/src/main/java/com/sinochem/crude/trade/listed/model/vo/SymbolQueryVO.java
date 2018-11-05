package com.sinochem.crude.trade.listed.model.vo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 向资讯模块请求资讯合约的VO
 * @author Yichen Zhao
 * date: 20180204
 */
public class SymbolQueryVO implements Serializable {

    private static final long serialVersionUID = 955369954730237456L;

    /**
     * 品类
     */
    private String commodity;

    /**
     * 来源
     */
    private String priceSource;

    /**
     * 市场
     */
    private String market;

    /**
     * 标识，1为取前台显示的，null为取所有
     */
    private String extend2;

    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }

    /** getters and setters */
    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getPriceSource() {
        return priceSource;
    }

    public void setPriceSource(String priceSource) {
        this.priceSource = priceSource;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }
}
