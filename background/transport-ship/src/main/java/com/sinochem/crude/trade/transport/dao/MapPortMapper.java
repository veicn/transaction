package com.sinochem.crude.trade.transport.dao;

import java.util.List;
import java.util.Map;

//import org.apache.ibatis.annotations.Mapper;
import com.sinochem.crude.trade.transport.domain.MapPort;

//@Mapper
public interface MapPortMapper {
	
	public List<MapPort> findMapPorts();
	
	public List<MapPort> findMapForeignPorts(Map<String,Object> map);
	
}
