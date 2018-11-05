package com.sinochem.crude.trade.listed.enums;

/**
 * 升序，降序的枚举
 * @author Yichen Zhao
 * date: 20180109
 */
public enum DataQueryOrderType {

    DESC(0, "desc"),
    ASC(1, "asc");

    Integer code;
    String name;

    DataQueryOrderType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
