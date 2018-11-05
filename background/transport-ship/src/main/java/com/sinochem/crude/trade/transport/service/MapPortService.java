package com.sinochem.crude.trade.transport.service;

import java.util.List;
import java.util.Map;

import com.sinochem.crude.trade.transport.domain.MapPort;

//import com.runyi.ryplat.api.commons.SimplePageInfo;

public interface MapPortService {
	
	public List<MapPort> findMapPorts();
	
	public List<MapPort> findMapForeignPorts(Map<String,Object> map);
}
