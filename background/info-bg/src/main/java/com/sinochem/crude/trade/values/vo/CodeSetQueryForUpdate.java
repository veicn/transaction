package com.sinochem.crude.trade.values.vo;

/**
 * Created by GHuang on 2017/2/6.
 */
public class CodeSetQueryForUpdate {

    private String module;
    private String setName;
    private String setCode;
    private String oldModule;
    private String oldSetCode;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSetName() {
        return setName;
    }

    public String getSetCode() {
        return setCode;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getOldModule() {
        return oldModule;
    }

    public void setOldModule(String oldModule) {
        this.oldModule = oldModule;
    }

    public String getOldSetCode() {
        return oldSetCode;
    }

    public void setOldSetCode(String oldSetCode) {
        this.oldSetCode = oldSetCode;
    }
}
