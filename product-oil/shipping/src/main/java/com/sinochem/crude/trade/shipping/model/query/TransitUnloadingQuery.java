package com.sinochem.crude.trade.shipping.model.query;

import java.io.Serializable;

public class TransitUnloadingQuery implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 日期 */
	private java.util.Date datetime;

	/** 日期 */
	public void setDatetime(java.util.Date datetime) {
		this.datetime = datetime;
	}

	/** 日期 */
	public java.util.Date getDatetime() {
		return this.datetime;
	}

}