package com.sinochem.crude.trade.orderexecute.service;

import java.util.Map;
import java.util.List;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.domain.TradeSubject;
import com.sinochem.crude.trade.orderexecute.dao.TradeSubjectMapper; 

public interface TradeSubjectService {
	
	public abstract TradeSubjectMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(TradeSubject tradeSubject);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long tradeSubjectId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(TradeSubject  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(TradeSubject tradeSubject) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(TradeSubject tradeSubject);
	
	
	/**
	 * 根据主键-查询对象
	 */
	TradeSubject findByPrimaryKey(Long tradeSubjectId);

	/**
	 * 根据uuid查询对象
	 */
	TradeSubject findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<TradeSubject> queryByEntitys(TradeSubject tradeSubject);
		
	/**
	 * 根据条件-查询全部
	 */
	List<Map<String, Object>> queryRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-分页查询
	 */
	Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 根据条件-查询记录条数
	 */
	int countRecords(Map<String, Object> map); 
	
	

	//**************************以下方法为开发者补充*********************************/
	
}
