package com.sinochem.crude.trade.values.vo;

import java.util.List;

/**
 * Created by GHuang on 2017/2/6.
 */
public class CodeSetResult {

    private String id;
    private String module;
    private String setCode;
    private String setName;
    private Boolean isEditable;
    private List<CodeItemResult> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Boolean getEditable() {
        return isEditable;
    }

    public void setEditable(Boolean editable) {
        isEditable = editable;
    }

    public List<CodeItemResult> getItems() {
        return items;
    }

    public void setItems(List<CodeItemResult> items) {
        this.items = items;
    }
}
