package com.sinochem.crude.trade.transport.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.transport.domain.LoadPort;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface LoadPortMapper {
	/**根据条件-查询全部*/
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	/**根据条件-查询记录条数*/
	public int countRecords(Map<String,Object> map);
	/**根据条件-批量逻辑删除 (AliveFlag修改为0)*/
	public void deleteRecords(Map<String,Object> map); 
	/**根据条件-批量修改数据*/
	public void updateRecords(Map<String,Object> map);
	/**根据对象-查询对象列表*/
	public List<LoadPort> queryByEntitys(LoadPort entity);
	/**根据主键-查询对象*/
	public LoadPort findByPrimaryKey(  @Param("loadPortId")  Long loadPortId);	
	/**根据UUID-查询对象*/
	public LoadPort findByUuid(String uuid);	
	/**根据主键-修改对象*/
	public void updateRecord(LoadPort entity);
	/**新增*/
	public void insertRecord(LoadPort entity);
	/**根据主键删除数据*/
	public void deleteRecordByKey( @Param("loadPortId") Long loadPortId, @Param("updateUser") Long updateUser);
	//**************************以下方法为开发者补充*********************************/
	
}
