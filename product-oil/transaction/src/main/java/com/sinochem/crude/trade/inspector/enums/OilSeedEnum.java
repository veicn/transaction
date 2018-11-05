package com.sinochem.crude.trade.inspector.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品分类枚举
 * @author Yichen Zhao
 * date: 20180228
 */
public enum OilSeedEnum {

    GASOLINE("1", "汽油", "Gasoline"),
    GASOIL("2", "柴油", "Gasoil");

    /**
     * 代码
     */
    private String code;

    /**
     * 中文名称
     */
    private String zhName;

    /**
     * 英文名称
     */
    private String enName;

    OilSeedEnum(String code, String zhName, String enName) {
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
     */
    public static Map<String,String> toMap(){
        Map<String,String> map = new HashMap<String,String>();

        map.put("1","汽油");
        map.put("2","柴油");
        map.put("3","航空煤油");
        map.put("4","石脑油");

        return map;
    }

}
