package com.sinochem.crude.trade.transport.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.transport.domain.UnitRate;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface UnitRateMapper {
	/**根据条件-查询全部*/
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	/**根据条件-查询记录条数*/
	public int countRecords(Map<String,Object> map);
	/**根据条件-批量逻辑删除 (AliveFlag修改为0)*/
	public void deleteRecords(Map<String,Object> map); 
	/**根据条件-批量修改数据*/
	public void updateRecords(Map<String,Object> map);
	/**根据对象-查询对象列表*/
	public List<UnitRate> queryByEntitys(UnitRate entity);
	/**根据主键-查询对象*/
	public UnitRate findByPrimaryKey(  @Param("unitRateId")  Long unitRateId);	
	/**根据UUID-查询对象*/
	public UnitRate findByUuid(String uuid);	
	/**根据主键-修改对象*/
	public void updateRecord(UnitRate entity);
	/**新增*/
	public void insertRecord(UnitRate entity);
	/**根据主键删除数据*/
	public void deleteRecordByKey( @Param("unitRateId") Long unitRateId, @Param("updateUser") Long updateUser);
	//**************************以下方法为开发者补充*********************************/
	/**根据UUID-逻辑删除对象*/
	public void deleteRecordsByUuid(Map<String, Object> map);
	/**根据UUID-修改对象*/
	public void updateRecordByUuid(UnitRate unitRate);
	
	/**跟据type，onename，twoname查找unitrate*/
	public UnitRate entityRecord(UnitRate unitRate);
	
	//通过名字查对象
	public int getUnitRateByName(Map<String,Object> map);
	
}
