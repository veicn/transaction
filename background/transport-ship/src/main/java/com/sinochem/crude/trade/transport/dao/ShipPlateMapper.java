package com.sinochem.crude.trade.transport.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.transport.domain.ShipPlate;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface ShipPlateMapper {
	/**根据条件-查询全部*/
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	/**根据条件-查询记录条数*/
	public int countRecords(Map<String,Object> map);
	/**根据条件-批量逻辑删除 (AliveFlag修改为0)*/
	public void deleteRecords(Map<String,Object> map); 
	/**根据条件-批量修改数据*/
	public void updateRecords(Map<String,Object> map);
	/**根据对象-查询对象列表*/
	public List<ShipPlate> queryByEntitys(ShipPlate entity);
	/**根据主键-查询对象*/
	public ShipPlate findByPrimaryKey(  @Param("shipPlateId")  Long shipPlateId);	
	/**根据UUID-查询对象*/
	public ShipPlate findByUuid(String uuid);	
	/**根据主键-修改对象*/
	public void updateRecord(ShipPlate entity);
	/**新增*/
	public void insertRecord(ShipPlate entity);
	/**根据主键删除数据*/
	public void deleteRecordByKey( @Param("shipPlateId") Long shipPlateId, @Param("updateUser") Long updateUser);
	//**************************以下方法为开发者补充*********************************/
	/**根据对象-查询船盘列表(前台)*/
	public List<Map<String,Object>> queryByEntitysForground(ShipPlate entity);
	/**根据uuid-批量修改数据*/
	public void updateRecordsFn(Map<String,Object> map);
	/**根据主键查询对象详细*/
	public List<Map<String,Object>> queryByPrimaryKeyForground(@Param("shipPlateId") Long shipPlateId);
	/**查询全部船盘信息列表(前台接口)*/
	public List<Map<String,Object>> queryShipPlatePageList(ShipPlate entity);
	
	/**查询无代理推荐船盘*/
	public List<Map<String,Object>> recommendShipPlate(Map<String,Object> map);
	/**更多船盘-查询全部*/
	public List<Map<String,Object>> queryMoreRecords(Map<String,Object> map);
	/**根据UUID-查询船盘Map*/
	public Map<String, Object> findMapByUuid(String uuid);
	/**根据条件-查询全部（om平台）*/
	public List<Map<String,Object>> queryRecordsOM(Map<String,Object> map);
	/**根据条件-分页查询（船东/经纪人，转租船东，查询，包括平台辅助导入）*/
	public List<Map<String,Object>> queryRecordsAll(Map<String,Object> map);
	/**根据条件-查询记录条数（船东/经纪人，转租船东，查询，包括平台辅助导入）*/
	public int countRecordsAll(Map<String,Object> map);
}
