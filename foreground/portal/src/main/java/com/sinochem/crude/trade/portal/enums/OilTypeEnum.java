package com.sinochem.crude.trade.portal.enums;

import java.util.HashMap;
import java.util.List;

/**
 * 油种枚举
 * @author Yichen Zhao
 * date: 20180411
 */
public enum OilTypeEnum {

    OMAN("Oman", "阿曼原油", "Oman");

    /**
     * 代码
     */
    private String code;

    /**
     * 中文名
     */
    private String zhName;

    /**
     * 英文名
     */
    private String enName;

    OilTypeEnum(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
    }

    /** getters */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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
}
