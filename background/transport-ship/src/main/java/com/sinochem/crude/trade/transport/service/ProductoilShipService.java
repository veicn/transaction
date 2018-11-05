package com.sinochem.crude.trade.transport.service;

import java.util.Map;
import java.util.List;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.domain.ProductoilShip;
import com.sinochem.crude.trade.transport.remote.SysShipVO;
import com.sinochem.crude.trade.transport.dao.ProductoilShipMapper; 

public interface ProductoilShipService {
	
	public abstract ProductoilShipMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<ProductoilShip> queryByEntitys(ProductoilShip productoilship);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract ProductoilShip findByPrimaryKey(Long sysShipId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract ProductoilShip findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(ProductoilShip productoilship);
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public abstract void deleteRecordByKey(Long sysShipId , Long deleteUser);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(ProductoilShip productoilship);
	
	/*
	 * 根据主键删除数据
	 */
	//public abstract void deleteRecordByKey(Long sysShipId);
	
	/**
	 * 根据条件-查询全部
	 */
	public abstract List<Map<String, Object>> queryRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 根据条件-查询记录条数
	 */
	public abstract int countRecords(Map<String, Object> map); 
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public abstract void deleteRecords(Map<String, Object> map);

	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map);


	//**************************以下方法为开发者补充*********************************/
	/**
	 * 查询成品油船舶名称
	 * 
	 * @return
	 */
	public abstract List<Map<String, Object>> findSysShipNameList();

	/**
	 * 成品油查询新增船舶
	 * @param productoilShip
	 * @return
	 * @throws Exception 
	 */
	public abstract void insertProductoilShip(SysShipVO vo) throws Exception;
}
