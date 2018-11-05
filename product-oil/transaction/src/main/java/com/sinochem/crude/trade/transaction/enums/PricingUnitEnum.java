package com.sinochem.crude.trade.transaction.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 计价单位枚举
 * @author Yichen Zhao
 * date: 20180226
 */
public enum PricingUnitEnum {

    MT("1", "MT", "MT"),
    BBL("2", "BBL", "BBL");

    /**
     * 代码
     */
    private String code;

    /**
     * 英文名称
     */
    private String zhName;

    /**
     * 英文名称
     */
    private String enName;

    PricingUnitEnum(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
    }

    /** getters */
    public String getCode() {
        return code;
    }

    public String getZhName() {
        return zhName;
    }

    public String getEnName() {
        return enName;
    }


    /**
     * 方便翻译
     * @return
     * @author whd
     */
    public static Map<String,String> toMap(){
        Map<String,String> map = new HashMap<String,String>();

        map.put("1","MT");
        map.put("2","BBL");

        return map;
    }

}
