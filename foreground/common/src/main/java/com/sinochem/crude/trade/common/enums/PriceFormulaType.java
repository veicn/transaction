package com.sinochem.crude.trade.common.enums;

/**
 * 价格公式类型
 * @author zuliu
 *
 */
public enum PriceFormulaType {

	AVERAGE(1,"Average"),//平均价
	COMPLEX(2,"Complex"),
	EVENT(3,"Event"),//事件计价
	FIXED(4,"Fixed"),//固定价
	Trigger(5,"Trigger")//点价
	;
	
	/**
	 * 编号
	 */
	int code;
	
	/**
	 * 名称
	 */
	String formulaType;
	
	private PriceFormulaType(int code, String formulaType) {
		this.code = code;
		this.formulaType = formulaType;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getFormulaType() {
		return formulaType;
	}

	public void setFormulaType(String formulaType) {
		this.formulaType = formulaType;
	}

	public static PriceFormulaType getFormulaTypeByCode(int code) {
		for(PriceFormulaType formulaType : values()){
			if(formulaType.getCode() == code)
				return formulaType;
		}
		return null;
	}
}
