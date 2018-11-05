package com.sinochem.crude.trade.inspector.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 委托商检状态：1已确认、2已完成',
 * @author wdh
 * @date 20180917
 */
public enum AppointEnum {
//    STAY_ENTRUST("1","待委托", ""),
    HANG_IN_THE_AIR("1","已确认", ""),
    COMPLETED_FLAG ("2","已完成", "");

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

    private AppointEnum(String code, String zhName, String enName) {
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
        map.put("1","已确认");
        map.put("2","已完成");
        return map;
    }


}
