package com.sinochem.crude.trade.transport.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.transport.domain.PalletPort;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface PalletPortMapper {
	/**根据条件-查询全部*/
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	/**根据条件-查询记录条数*/
	public int countRecords(Map<String,Object> map);
	/**根据条件-批量逻辑删除 (AliveFlag修改为0)*/
	public void deleteRecords(Map<String,Object> map); 
	/**根据条件-批量修改数据*/
	public void updateRecords(Map<String,Object> map);
	/**根据对象-查询对象列表*/
	public List<PalletPort> queryByEntitys(PalletPort entity);
	/**根据主键-查询对象*/
	public PalletPort findByPrimaryKey(  @Param("palletPortId")  Long palletPortId);	
	/**根据UUID-查询对象*/
	public PalletPort findByUuid(String uuid);	
	/**根据主键-修改对象*/
	public void updateRecord(PalletPort entity);
	/**新增*/
	public void insertRecord(PalletPort entity);
	/**根据主键删除数据*/
	public void deleteRecordByKey( @Param("palletPortId") Long palletPortId, @Param("updateUser") Long updateUser);
	//**************************以下方法为开发者补充*********************************/
	
	/**根据主键-修改对象*/
	public void update(PalletPort entity);
}
