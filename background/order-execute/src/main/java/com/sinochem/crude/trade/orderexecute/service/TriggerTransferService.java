package com.sinochem.crude.trade.orderexecute.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.dao.TriggerTransferMapper;
import com.sinochem.crude.trade.orderexecute.domain.TriggerContract;
import com.sinochem.crude.trade.orderexecute.domain.TriggerTransfer;
import com.sinochem.crude.trade.orderexecute.model.TriggerTransferVO; 

public interface TriggerTransferService {
	
	public abstract TriggerTransferMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(TriggerTransfer triggerTransfer);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(TriggerTransfer  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(TriggerTransfer triggerTransfer) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(TriggerTransfer triggerTransfer);
	
	
	/**
	 * 根据主键-查询对象
	 */
	TriggerTransfer findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	TriggerTransfer findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<TriggerTransfer> queryByEntitys(TriggerTransfer triggerTransfer);
		
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
	
	List<TriggerTransferVO> findByOrderId(Long orderId);
	/**
	 * 保存转月信息
	 * @param triggerTransfer
	 * @param newTriggerContract
	 * @param triggerContractUpdate
	 * @return
	 */
	int saveTransferInfo(TriggerTransfer triggerTransfer, TriggerContract newTriggerContract, TriggerContract triggerContractUpdate);
	
	/**
	 * 查询订单已转月次数
	 * @param orderId 订单ID
	 * @param transferMonth 转月的合约月份
	 * @return
	 */
	int countTransferMonth(Long orderId, String transferMonth);
	/**
	 * 查询指定合约转月信息
	 * @param contractId
	 * @return
	 */
	TriggerTransfer queryByTriggerContractId(Long contractId);
	
}
