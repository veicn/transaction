package com.sinochem.crude.trade.orderexecute.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.dao.OrderContractMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderContract;
import com.sinochem.crude.trade.orderexecute.domain.OrderContractFile; 

public interface OrderContractService {
	
	public abstract OrderContractMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(OrderContract orderContract);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long contractId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(OrderContract  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(OrderContract orderContract) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(OrderContract orderContract);
	
	
	/**
	 * 根据主键-查询对象
	 */
	OrderContract findByPrimaryKey(Long contractId);

	/**
	 * 根据uuid查询对象
	 */
	OrderContract findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<OrderContract> queryByEntitys(OrderContract orderContract);
		
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
	 * 创建合同信息
	 * @param contract
	 * @param contractFiles
	 */
	void createContract(OrderContract contract, List<OrderContractFile> contractFiles)throws Exception;
	
	/**
	 * 更新合同信息
	 * @param contract
	 * @param contractFiles
	 */
	void updateContract(OrderContract contract, List<OrderContractFile> contractFiles);
	
	/**
	 * 删除合同（物理删除）
	 * @param contractUuid
	 */
	void deleteContract(String contractUuid);
	
	/**
	 * 合同接收信息处理
	 * @param orderContract
	 * @param contractFileList
	 */
	void receiveContractProcess(OrderContract orderContract, List<OrderContractFile> contractFileList);
	
	/**
	 * 通过订单编号查找合同
	 * @param orderNo
	 * @return
	 */
	OrderContract findByOrderNo(String orderNo);
	
	/**
	 * 通过订单UUID查找合同
	 * @param orderNo
	 * @return
	 */
	OrderContract findByOrderUuid(String orderUuid);
	
	/**
	 * 通过订单ID查找合同
	 * @param orderNo
	 * @return
	 */
	OrderContract findByOrderId(Long orderId);
	
	/**
	 * 删除合同（逻辑删除）
	 * @param contractUuid
	 * @param customerId
	 * @return
	 */
	void removeContract(String contractUuid, Long customerId);
}
