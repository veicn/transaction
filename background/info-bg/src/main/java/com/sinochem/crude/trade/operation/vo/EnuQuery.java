package com.sinochem.crude.trade.operation.vo;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;

public class EnuQuery  extends QueryBase{

	private static final long serialVersionUID = 1L;
	private String menuDes;//菜单描述
	private String menuType;//菜单类型
	private String menuUrl;//菜单代码
	
	
	@Override
	public Map<String, String> getParameters() {
		Map<String,String> map = new HashMap<>();
		map.put("menuDes", this.getMenuDes());
		map.put("menuType", this.getMenuType());
		map.put("menuUrl", this.getMenuUrl());
		return map;
	}


	public String getMenuDes() {
		return menuDes;
	}


	public void setMenuDes(String menuDes) {
		this.menuDes = menuDes;
	}


	public String getMenuType() {
		return menuType;
	}


	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}


	public String getMenuUrl() {
		return menuUrl;
	}


	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	
}