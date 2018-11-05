package com.sinochem.crude.trade.operation.vo;

import java.io.Serializable;

public class MenuRank implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String menuId;
    
    private Integer sort;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}


    
}
