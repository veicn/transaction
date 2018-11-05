package com.sinochem.crude.trade.transport.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.transport.domain.Pallet;

//@Mapper
public interface PalletMapper {
	/**根据条件-查询全部*/
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	/**根据条件-查询记录条数*/
	public int countRecords(Map<String,Object> map);
	/**根据条件-批量逻辑删除 (AliveFlag修改为0)*/
	public void deleteRecords(Map<String,Object> map); 
	/**根据条件-批量修改数据*/
	public void updateRecords(Map<String,Object> map);
	/**根据对象-查询对象列表*/
	public List<Pallet> queryByEntitys(Pallet entity);
	/**根据主键-查询对象*/
	public Pallet findByPrimaryKey(  @Param("palletId")  Long palletId);	
	/**根据UUID-查询对象*/
	public Pallet findByUuid(String uuid);	
	/**根据主键-修改对象*/
	public void updateRecord(Pallet entity);
	/**新增*/
	public void insertRecord(Pallet entity);
	/**根据主键删除数据*/
	public void deleteRecordByKey( @Param("palletId") Long palletId, @Param("updateUser") Long updateUser);
	//**************************以下方法为开发者补充*********************************/
	/**根据条件-无订单列表*/
	public List<Map<String,Object>> queryPallet(Map<String,Object> map);
	/**根据条件-有订单列表*/
	public List<Map<String,Object>> findPallet(Map<String,Object> map);
	/**根据PalletUUID-查询对象*/
	public Map<String,Object> findPalletByUuid(String uuid);	
	/**根据主键-修改货盘*/
	public void updatePallet(Pallet entity);
	
	/**根据UUID-查询对象*/
	public Map<String, Object> findMapByUuid(String uuid);
	
	//无订单total
	public int countRecords2(Map<String,Object> map);
	//有订单total
	public int countRecords3(Map<String,Object> map);
	
	//有订单租船未产生代理协议任务数量
	public int getTaskNum(Map<String, Object> map);
	//无订单租船未产生代理协议任务数量
	public int getUntaskNum(Map<String, Object> map);
	//货盘滚动查询
	public List<Pallet> findPalletList(Pallet vo);
	//货盘滚动查看更多查询
	public Page<Map<String, Object>> findMorePalletList(Map<String, Object> map);
	//om需求列表
	public List<Map<String,Object>> queryPalletOm(Map<String,Object> map);
	
}
