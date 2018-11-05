package com.sinochem.crude.trade.member.enums;

/**
 * 用户类型
 * 0-个人，1-企业
 */
public enum EnumUserType {

    ENTERPRISE(1) , PERSON(0);

    int type;

    EnumUserType(int type){
        this.type = type;
    }

    public boolean is(int type){
        return this.type == type ;
    }

    public boolean is(String type){
        return type != null && is(Integer.valueOf(type));
    }

    public String getType() {
        return Integer.toString(type);
    }
}
