package com.sinochem.crude.trade.portal.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 港口VO
 * @author Yichen Zhao
 * date: 20180414
 */
public class ShipPortVO implements Serializable {

    private String code;

    private String zhName;

    private String enName;

    private String subGroup;

    private String value;

    /** getters and setters */
    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(String subGroup) {
        this.subGroup = subGroup;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
