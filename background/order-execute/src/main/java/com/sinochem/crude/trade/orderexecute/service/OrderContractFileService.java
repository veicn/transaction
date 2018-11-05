package com.sinochem.crude.trade.orderexecute.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.dao.OrderContractFileMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderContractFile; 

public interface OrderContractFileService {
	
	public abstract OrderContractFileMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(OrderContractFile orderContractFile);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long contractFileId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(OrderContractFile  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(OrderContractFile orderContractFile) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(OrderContractFile orderContractFile);
	
	
	/**
	 * 根据主键-查询对象
	 */
	OrderContractFile findByPrimaryKey(Long contractFileId);

	/**
	 * 根据uuid查询对象
	 */
	OrderContractFile findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<OrderContractFile> queryByEntitys(OrderContractFile orderContractFile);
		
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
	/**
	 * 根据主键+创建者 查询对象
	 */
	public OrderContractFile findByPrimaryKeyAndCreater(Long contractFileId, Long userId);
	
}
