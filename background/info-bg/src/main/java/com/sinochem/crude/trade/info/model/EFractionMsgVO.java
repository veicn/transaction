package com.sinochem.crude.trade.info.model;

import com.sinochem.crude.trade.info.domain.EFractionMsg;

public class EFractionMsgVO extends EFractionMsg {

	private static final long serialVersionUID = 1L;	
	
	/** check 校对*/
	private java.math.BigDecimal check;

	public java.math.BigDecimal getCheck() {
		return check;
	}

	public void setCheck(java.math.BigDecimal check) {
		this.check = check;
	}
	
	
}