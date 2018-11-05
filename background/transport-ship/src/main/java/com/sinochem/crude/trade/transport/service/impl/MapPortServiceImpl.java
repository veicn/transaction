package com.sinochem.crude.trade.transport.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinochem.crude.trade.transport.dao.MapPortMapper;
import com.sinochem.crude.trade.transport.domain.MapPort;
import com.sinochem.crude.trade.transport.service.MapPortService;

@Service
public class MapPortServiceImpl implements MapPortService {

	@Autowired
	private MapPortMapper _MapPortMapper;
	
	@Override
	public List<MapPort> findMapPorts() {
		return _MapPortMapper.findMapPorts();
	}

	@Override
	public List<MapPort> findMapForeignPorts(Map<String,Object> map) {
		return _MapPortMapper.findMapForeignPorts(map);
	}

}
