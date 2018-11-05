package com.sinochem.crude.trade.transport.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.transport.domain.SysShip;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface SysShipMapper {
	/**根据条件-查询全部*/
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	/**根据条件-查询记录条数*/
	public int countRecords(Map<String,Object> map);
	/**根据条件-批量逻辑删除 (AliveFlag修改为0)*/
	public void deleteRecords(Map<String,Object> map); 
	/**根据条件-批量修改数据*/
	public void updateRecords(Map<String,Object> map);
	/**根据对象-查询对象列表*/
	public List<SysShip> queryByEntitys(SysShip entity);
	/**根据主键-查询对象*/
	public SysShip findByPrimaryKey(  @Param("sysShipId")  Long sysShipId);	
	/**根据UUID-查询对象*/
	public SysShip findByUuid(String uuid);	
	/**根据主键-修改对象*/
	public void updateRecord(SysShip entity);
	/**新增*/
	public void insertRecord(SysShip entity);
	/**根据主键删除数据*/
	public void deleteRecordByKey( @Param("sysShipId") Long sysShipId, @Param("updateUser") Long updateUser);
	//**************************以下方法为开发者补充*********************************/
	/**根据Imo、船名、船型查船舶信息(前台接口)*/
	public List<Map<String,Object>> findSysShipByImoName(SysShip entity);
	public List<Map<String,Object>> findSysShipAccessoryByImoName(SysShip entity);
	/**根据uuid、查询船舶状态*/
	public List<Map<String,Object>> findSysShipStatus(SysShip entity);
	/**根据uuid-查询对象详细信息*/
	public Map<String, Object> findByUUIDForward(@Param("UUID") String UUID);
	/**根据条件-查询状态为‘待审核’和‘有效’的船舶*/
	public List<Map<String,Object>> findShipRecords(Map<String,Object> map);
	/**根据IMO、MMSI、船名、查船舶信息(可视化地图接口)*/
	public List<Map<String,Object>> findSysShipByImoOrMmsiOrName(Map<String,Object> map);
}
