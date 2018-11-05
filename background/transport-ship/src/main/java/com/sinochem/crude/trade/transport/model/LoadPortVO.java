package com.sinochem.crude.trade.transport.model;

import java.util.List;

import com.sinochem.crude.trade.transport.domain.LoadPort;
import com.sinochem.crude.trade.transport.model.res.LoadPortDetail;

public class LoadPortVO extends LoadPort {

	private static final long serialVersionUID = 1L;	
	
	/***/
	private List<LoadPortDetail> list;

	public List<LoadPortDetail> getList() {
		return list;
	}

	public void setList(List<LoadPortDetail> list) {
		this.list = list;
	}
	
	
}