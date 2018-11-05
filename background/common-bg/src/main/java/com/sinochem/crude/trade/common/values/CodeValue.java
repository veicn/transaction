/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.values;

import com.google.common.base.MoreObjects;

/**
 * Created by GHuang on 2016/11/17. Value set item object
 */
public class CodeValue {

    private String id;

    private String group;

    private String groupName;

    private String code;

    private String value;

    private String parentId;

    private String subGroup;

    private Integer sort;
    
    private String langVer;
    
    private String ext1;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(String subGroup) {
        this.subGroup = subGroup;
    }

    public String getLangVer() {
		return langVer;
	}

	public void setLangVer(String langVer) {
		this.langVer = langVer;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	@Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("group", group)
                .add("groupName", groupName)
                .add("code", code)
                .add("value", value)
                .add("parentId", parentId)
                .add("subGroup", subGroup)
                .add("langVer", langVer)
                .add("ext1", ext1)
                .toString();
    }
}
