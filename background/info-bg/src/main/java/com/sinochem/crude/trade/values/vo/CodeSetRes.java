package com.sinochem.crude.trade.values.vo;

import java.io.Serializable;

public class CodeSetRes implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 代码集代码 */
	private String itemCode;

	/** 代码集名称 */
	private String itemName;

	/** 值集类别代码 */
	private String codeSet;

	/** 值集id */
	private String id;

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

	public String getCodeSet() {
		return codeSet;
	}

	public void setCodeSet(String codeSet) {
		this.codeSet = codeSet;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
