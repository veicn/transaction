package com.sinochem.crude.trade.listed.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于实时转计价功能的油种类型枚举
 * @author Yichen Zhao
 * date: 20180202
 */
public enum OilModelTypeEnum {
    DUBAI("DUBAI"),
    WTI("WTI"),
    DTD_BRENT("DTD BRENT"),
    ICE_BRENT("ICE BRENT");

    String code;

    OilModelTypeEnum(String code) {
        this.code = code;
    }

    public static List<String> convertToList() {
        List<String> list = new ArrayList<>();

        for (OilModelTypeEnum oilModelTypeEnum : OilModelTypeEnum.values()) {
            list.add(oilModelTypeEnum.getCode());
        }

        return list;
    }

    /** getters and setters */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
