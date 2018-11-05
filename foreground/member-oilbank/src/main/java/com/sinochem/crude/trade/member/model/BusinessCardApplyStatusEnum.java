package com.sinochem.crude.trade.member.model;

/**
 * 发送名片申请状态
 * Created by AlterEgo on 2018/5/4.
 */
public enum BusinessCardApplyStatusEnum {
    APPLYING("申请中", 0),
    AGREE("同意申请", 1),
    IGNORE("忽略申请", 2);

    private String name ;
    private int code ;

    BusinessCardApplyStatusEnum( String name , int code ){
        this.name = name ;
        this.code = code ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
