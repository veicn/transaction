package com.sinochem.crude.trade.transport.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonUnwrapped;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.domain.Pallet;
import com.sinochem.crude.trade.transport.model.res.PalletPortList;
import com.sinochem.crude.trade.transport.model.res.PorLoadRegiontList;

public class PalletVO extends Pallet {

	private static final long serialVersionUID = 1L;	
	
	/**分页*/
	@JsonUnwrapped
	private SimplePageInfo pageInfo;
	
	/**1询盘，2报盘*/
	private String type;
	private String loadPort;
	private String portName;
	private String unloadPort;
	
	private List<PalletPortList> list;
	private List<PorLoadRegiontList> Regionlist;
	
	
	public String getPortName() {
		return portName;
	}
	public void setPortName(String portName) {
		this.portName = portName;
	}
	public List<PalletPortList> getList() {
		return list;
	}
	public void setList(List<PalletPortList> list) {
		this.list = list;
	}

	public String getLoadPort() {
		return loadPort;
	}
	public void setLoadPort(String loadPort) {
		this.loadPort = loadPort;
	}
	public String getUnloadPort() {
		return unloadPort;
	}
	public void setUnloadPort(String unloadPort) {
		this.unloadPort = unloadPort;
	}
	public List<PorLoadRegiontList> getRegionlist() {
		return Regionlist;
	}

	public void setRegionlist(List<PorLoadRegiontList> regionlist) {
		Regionlist = regionlist;
	}
	
	public SimplePageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(SimplePageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	/**1询盘，2报盘*/
	public String getType() {
		return type;
	}
	
	/**1询盘，2报盘*/
	public void setType(String type) {
		this.type = type;
	}
	
}