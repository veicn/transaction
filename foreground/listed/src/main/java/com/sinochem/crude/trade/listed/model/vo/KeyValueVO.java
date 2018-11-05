package com.sinochem.crude.trade.listed.model.vo;

import java.io.Serializable;

/**
 * 用于封装键值对的VO
 * @author Yichen Zhao
 * date: 20180111
 */
public class KeyValueVO implements Serializable {

    private static final long serialVersionUID = -8638615274821248929L;

    private String key;
    private String value;

    public KeyValueVO() {}

    public KeyValueVO(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
