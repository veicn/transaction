package com.sinochem.crude.trade.orderexecute.model;

import com.sinochem.crude.trade.orderexecute.domain.TriggerTransfer;

public class TriggerTransferVO extends TriggerTransfer {

	private static final long serialVersionUID = 1L;	
	
	/** 转月截止时间 */
	private String transferEndDateStr;

	public String getTransferEndDateStr() {
		return transferEndDateStr;
	}

	public void setTransferEndDateStr(String transferEndDateStr) {
		this.transferEndDateStr = transferEndDateStr;
	}
}