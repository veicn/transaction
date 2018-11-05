package com.sinochem.crude.trade.info.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.info.dao.ExternalInteractiveMapper;
import com.sinochem.crude.trade.info.domain.ExternalInteractive;
import com.sinochem.it.b2b.common.exception.BizException; 

public interface ExternalInteractiveService {
	
	public abstract ExternalInteractiveMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(ExternalInteractive externalInteractive);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(ExternalInteractive  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(ExternalInteractive externalInteractive) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(ExternalInteractive externalInteractive);
	
	
	/**
	 * 根据主键-查询对象
	 */
	ExternalInteractive findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	ExternalInteractive findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<ExternalInteractive> queryByEntitys(ExternalInteractive externalInteractive);
		
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
