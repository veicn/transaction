package com.sinochem.crude.trade.transport.model;

import java.util.List;

import com.sinochem.crude.trade.transport.domain.Disburden;
import com.sinochem.crude.trade.transport.model.res.DisburdenDetail;

public class DisburdenVO extends Disburden {

	private static final long serialVersionUID = 1L;	
	
	/**卸港list*/
	private List<DisburdenDetail> list;

	public List<DisburdenDetail> getList() {
		return list;
	}

	public void setList(List<DisburdenDetail> list) {
		this.list = list;
	}
	
}