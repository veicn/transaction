package com.sinochem.crude.trade.member.enums;

/**
 * 类型,0=境内,1=境外
 */
public enum EnumEpType {
    EP_TYPE_0(0, "境内"),
    EP_TYPE_1(1, "境外");

    Integer code;
    String name;

    EnumEpType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static EnumEpType getPurchaseTypeByCode(Integer code) {
        for (EnumEpType enumEpType : EnumEpType.values()) {
            if (enumEpType.getCode().equals(code)) {
                return enumEpType;
            }
        }
        return null;
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
