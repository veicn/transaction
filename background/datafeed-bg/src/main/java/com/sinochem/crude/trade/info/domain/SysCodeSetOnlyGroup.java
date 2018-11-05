package com.sinochem.crude.trade.info.domain;

/**
 * Created by GHuang on 2017/2/8.
 */
public class SysCodeSetOnlyGroup {

    private String module;
    private String setCode;
    private String setName;
    private String isEditable;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSetCode() {
        return setCode;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getIsEditable() {
        return isEditable;
    }

    public void setIsEditable(String isEditable) {
        this.isEditable = isEditable;
    }
}
