package com.sinochem.crude.trade.values.vo;

import java.io.Serializable;

public class CodeSetVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 值集类别代码 */
	private String setCode;

	/** 模块代码 */
	private String modelCode;

	public String getSetCode() {
		return setCode;
	}

	public void setSetCode(String setCode) {
		this.setCode = setCode;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
}
