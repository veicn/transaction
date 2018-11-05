package com.sinochem.crude.trade.shiprefueling.service;

import com.sinochem.crude.trade.shiprefueling.dao.GoryMapper;
import com.sinochem.crude.trade.shiprefueling.domain.po.Gory;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;
import java.util.Map;


public interface GoryService {
	
	public abstract GoryMapper getMapper();
	
	/**
	 * 新增
	 */
	int insertRecord(Gory gory);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long palletId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(Gory  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(Gory gory) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(Gory gory) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Gory gory);
	
	
	/**
	 * 根据主键-查询对象
	 */
	Gory findByPrimaryKey(Long palletId);

	/**
	 * 根据uuid查询对象
	 */
	Gory findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<Gory> queryByEntitys(Gory gory);
		
	/**
	 * 根据条件-查询全部
	 */
	List<Map<String, Object>> queryRecords(Map<String, Object> map);
	

	/**
	 * 根据条件-查询记录条数
	 */
	int countRecords(Map<String, Object> map); 
	
	

	//**************************以下方法为开发者补充*********************************/

	List<Gory> queryGoryListByOrderId(Long orderId);

	int updateAliveFlagByOrderId(Long orderId, String flag);
}
