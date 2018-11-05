package com.sinochem.crude.trade.transport.model;

import java.util.List;

import com.sinochem.crude.trade.transport.domain.UnloadPort;
import com.sinochem.crude.trade.transport.model.res.UnloadPortDetail;

public class UnloadPortVO extends UnloadPort {

	private static final long serialVersionUID = 1L;	
	
	/**多个卸港信息*/
	private List<UnloadPortDetail> list;

	public List<UnloadPortDetail> getList() {
		return list;
	}

	public void setList(List<UnloadPortDetail> list) {
		this.list = list;
	}
	
	
}