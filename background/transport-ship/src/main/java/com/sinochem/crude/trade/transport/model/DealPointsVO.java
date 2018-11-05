package com.sinochem.crude.trade.transport.model;

import java.util.Date;

import com.sinochem.crude.trade.transport.domain.DealPoints;

public class DealPointsVO extends DealPoints {

	private static final long serialVersionUID = 1L;	
	
	private Date dateStart;
	
	private Date dateEnd;

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDataEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	
}