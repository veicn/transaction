package com.sinochem.crude.trade.shipping.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.shipping.domain.SysShip;
import com.sinochem.crude.trade.shipping.model.query.SysShipQuery;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface SysShipMapper {

	public int insertRecord(SysShip entity);
	
	public int deleteById( @Param("sysShipId") Long sysShipId);
	
	public int deleteRecords(SysShip entity);
	
	public int updateRecordById(SysShip entity);
	
	public int updateRecordByUuid(SysShip entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public SysShip findByPrimaryKey( Long sysShipId);	
	
	public SysShip findByUuid(String uuid);	
	
	//返回对象的List
	public List<SysShip> queryByEntitys(SysShip entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 查询列表集合 返回query对象
	 * @param voyageStartQuery
	 * @return
	 */
	public List<SysShip> queryByParamList(SysShipQuery sysShipQuery);
	
	/**根据主键删除数据 (AliveFlag修改为0)*/
	public void deleteRecordByKey( @Param("sysShipId") Long sysShipId, @Param("updateUser") Long updateUser);
	
	/**
	 * 查询所有的船舶list
	 * @return
	 */
	public List<SysShip> findAllList();
	
}
