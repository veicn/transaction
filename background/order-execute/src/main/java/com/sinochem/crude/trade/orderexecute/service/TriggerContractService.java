package com.sinochem.crude.trade.orderexecute.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.dao.TriggerContractMapper;
import com.sinochem.crude.trade.orderexecute.domain.TriggerContract;
import com.sinochem.crude.trade.orderexecute.model.TriggerContractVO; 

public interface TriggerContractService {
	
	public abstract TriggerContractMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(TriggerContract triggerContract);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(TriggerContract  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(TriggerContract triggerContract) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(TriggerContract triggerContract);
	
	
	/**
	 * 根据主键-查询对象
	 */
	TriggerContract findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	TriggerContract findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<TriggerContract> queryByEntitys(TriggerContract triggerContract);
		
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
	
	List<TriggerContractVO> findByOrderId(Long orderId);
	/**
	 * 查询该订单制定月份下已有的合约
	 * @param orderId
	 * @param month
	 * @return
	 */
	TriggerContract queryExistContract(Long orderId, String month);
	/**
	 * 查询订单第一个合约
	 * @param orderId
	 * @return
	 */
	TriggerContract queryFirstContract(Long orderId);
	
	/**
	 * 重置合约
	 * @param orderId
	 */
	void resetContract(Long orderId);
}
