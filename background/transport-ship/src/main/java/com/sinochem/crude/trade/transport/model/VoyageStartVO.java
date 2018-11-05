package com.sinochem.crude.trade.transport.model;

import java.util.List;

import com.sinochem.crude.trade.transport.domain.VoyageStart;
import com.sinochem.crude.trade.transport.model.res.VoyageStartDetail;

public class VoyageStartVO extends VoyageStart {

	private static final long serialVersionUID = 1L;	
	
	/**航次开始*/
	private List<VoyageStartDetail> list;

	/**装卸港维护*/
	private List<AgencyNameVO> listAgency;
	
	
	public List<VoyageStartDetail> getList() {
		return list;
	}

	public void setList(List<VoyageStartDetail> list) {
		this.list = list;
	}

	public List<AgencyNameVO> getListAgency() {
		return listAgency;
	}

	public void setListAgency(List<AgencyNameVO> listAgency) {
		this.listAgency = listAgency;
	}
	
	
	
	
}