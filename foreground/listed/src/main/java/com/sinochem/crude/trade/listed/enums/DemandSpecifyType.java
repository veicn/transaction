package com.sinochem.crude.trade.listed.enums;

/**
 * 表示一个需求单是否为定向需求单
 * @author Yichen Zhao
 * date: 20180117
 */
public enum DemandSpecifyType {

    /**
     * 定向
     */
    SPECIFIED(1),

    /**
     * 非定向
     */
    NOT_SPECIFIED(0);

    Integer code;

    DemandSpecifyType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
