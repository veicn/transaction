package com.sinochem.crude.trade.values.vo;

/**
 * Created by GHuang on 2017/2/6.
 */
public class CodeItemResult {

    private String id;
    private String module;
    private String itemCode;
    private String itemName;
    private Integer itemSort;
    private Boolean isEditable;
    private String langVer;
    private String ext1;
    

    public String getLangVer() {
		return langVer;
	}

	public void setLangVer(String langVer) {
		this.langVer = langVer;
	}

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

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemSort() {
        return itemSort;
    }

    public void setItemSort(Integer itemSort) {
        this.itemSort = itemSort;
    }

    public Boolean getEditable() {
        return isEditable;
    }

    public void setEditable(Boolean editable) {
        isEditable = editable;
    }

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
}
