package com.sinochem.crude.trade.operation.vo;

import java.io.Serializable;
import java.util.List;

public class AppMenuOut implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//菜单类型
	private String menuType;
	
	//行业推荐id
	private String tradeId;
	
	//行业推荐名称
	private String tradeName;
	
	//菜单属性
	private List<MenuOutPut> menuOutPut;

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public List<MenuOutPut> getMenuOutPut() {
		return menuOutPut;
	}

	public void setMenuOutPut(List<MenuOutPut> menuOutPut) {
		this.menuOutPut = menuOutPut;
	}
	

}