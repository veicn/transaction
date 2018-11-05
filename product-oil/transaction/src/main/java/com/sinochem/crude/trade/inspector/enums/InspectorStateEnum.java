package com.sinochem.crude.trade.inspector.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 商检状态
 * @author wdh
 * @date 2018/9/10
 */
public enum InspectorStateEnum {

    TEMPORARY_STORAGE_FLAG("0","暂存", ""),
    SUBMITTED_FLAG("1","已提交", "");

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

    private InspectorStateEnum(String code, String zhName, String enName) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
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
        map.put("0","暂存");
        map.put("1","已提交");
        return map;
    }

}
