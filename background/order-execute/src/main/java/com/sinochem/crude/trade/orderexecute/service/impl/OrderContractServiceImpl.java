package com.sinochem.crude.trade.orderexecute.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.order.remote.OrderStatusService;
import com.sinochem.crude.trade.orderexecute.commons.constants.ContractStatusEnum;
import com.sinochem.crude.trade.orderexecute.dao.OrderContractFileMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderContractMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderMapper;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderContract;
import com.sinochem.crude.trade.orderexecute.domain.OrderContractFile;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.service.OrderContractService;
import com.sinochem.crude.trade.orderexecute.service.OrderService;

@Service
public class OrderContractServiceImpl implements OrderContractService {
	@Autowired
	private OrderContractMapper orderContractMapper;
	@Autowired
	private OrderContractFileMapper contractFileMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderStatusService orderStatusService;
	@Autowired
	private OrderService orderService;
	
	public OrderContractMapper getMapper(){
		return orderContractMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderContract ordercontract){
		 return orderContractMapper.insertRecord(ordercontract);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long contractId) throws BizException{
		if (contractId == null) {
			
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderContractMapper.deleteById(contractId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderContract  record){
    	return orderContractMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderContract orderContract) throws BizException{
		if ( orderContract.getContractId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","contractId 为空，不能修改","contractId");
		}
		return orderContractMapper.updateRecordById(orderContract);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderContractMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderContract orderContract){
		return orderContractMapper.updateRecords(orderContract.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderContract findByPrimaryKey(Long contractId){
		return  orderContractMapper.findByPrimaryKey(contractId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderContract findByUuid(String uuid){
		return  orderContractMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderContract> queryByEntitys(OrderContract orderContract){
		return  orderContractMapper.queryByEntitys(orderContract);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderContractMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		return (Page<Map<String, Object>>) orderContractMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderContractMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public synchronized void createContract(OrderContract contract, List<OrderContractFile> contractFiles) throws Exception {
		//重复校验，是否已有合同，目前一个订单只有一个合同
		
		OrderContract contractQuery = new OrderContract();
		contractQuery.setOrderId(contract.getOrderId());
		List<OrderContract> contractExistList = orderContractMapper.queryByEntitys(contractQuery);
		if(contractExistList.size() > 0){
			throw new OrderExecException("orderexecute.code.00076","合同已存在");
		}
		
		orderContractMapper.insertRecord(contract);
		
		Long contractId = contract.getContractId();
		
		for(OrderContractFile contractFile : contractFiles){
			contractFile.setContractId(contractId);
			contractFileMapper.insertRecord(contractFile);
		}

		Order order = orderService.findByPrimaryKey(contract.getOrderId());
		order.setContractId(contractId);
		order.setContractNo(contract.getContractNo());
		orderMapper.updateRecordById(order);

		orderStatusService.setOrderStatus(order.getTradeId(), 
				ContractStatusEnum.ORDER_STATUS_1.getCode(), 
				Integer.parseInt(ContractStatusEnum.ORDER_STATUS_ITEM_1_9.getCode()), 
				"", 
				contract.getUpdateUser());
			
			
			
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void updateContract(OrderContract contract, List<OrderContractFile> contractFiles) {
		orderContractMapper.updateRecordById(contract);
		
		OrderContractFile contractFileDelete = new OrderContractFile();
		contractFileDelete.setContractId(contract.getContractId());
		contractFileMapper.deleteRecords(contractFileDelete);
		
		for(OrderContractFile contractFile : contractFiles){
			contractFile.setContractId(contract.getContractId());
			contractFileMapper.insertRecord(contractFile);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void receiveContractProcess(OrderContract orderContract, List<OrderContractFile> contractFileList) {
		orderContract.setContractUuid(KeyGenUtils.newUuid());  
		orderContractMapper.insertRecord(orderContract);
		
		for(OrderContractFile contractFile : contractFileList){
			contractFile.setContractId(orderContract.getContractId());
			contractFileMapper.insertRecord(contractFile);
		}
		
		Order updateOrder = new Order();
		updateOrder.setId(orderContract.getOrderId());
		updateOrder.setContractId(orderContract.getContractId());
		updateOrder.setContractNo(orderContract.getContractNo());
		orderMapper.updateRecordById(updateOrder);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteContract(String contractUuid) {
		OrderContract contract = orderContractMapper.findByUuid(contractUuid);
		if(contract == null){
			return;
		}
		
		orderContractMapper.deleteById(contract.getContractId());
		
		Order updateOrder = new Order();
		updateOrder.setId(contract.getOrderId());
		updateOrder.setContractId(null);
		updateOrder.setContractNo(null);
		orderMapper.updateRecordById(updateOrder);
		
	}

	@Override
	public OrderContract findByOrderNo(String orderNo) {
		OrderContract contractQuery = new OrderContract();
		contractQuery.setOrderNo(orderNo);
		
		List<OrderContract> contractList = orderContractMapper.queryByEntitys(contractQuery);
		if(contractList != null && !contractList.isEmpty()){
			contractList.get(0);
		}
		
		return null;
	}

	@Override
	public OrderContract findByOrderUuid(String orderUuid) {
		
		List<OrderContract> contractList = orderContractMapper.findByOrderUuid(orderUuid);
		if(contractList != null && !contractList.isEmpty()){
			return contractList.get(0);
		}
		
		return null;
	}
	
	@Override
	public OrderContract findByOrderId(Long orderId) {
		OrderContract query = new OrderContract();
		query.setOrderId(orderId);
		List<OrderContract> contractList = orderContractMapper.queryByEntitys(query);
		if(contractList != null && !contractList.isEmpty()){
			return contractList.get(0);
		}
		
		return null;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void removeContract(String contractUuid, Long customerId) {
		OrderContract contract = orderContractMapper.findByUuid(contractUuid);
		if(contract == null){
			return;
		}
		
		orderContractMapper.removeRecordByIdOrUuid(null, contractUuid, customerId);
		
		Order updateOrder = new Order();
		updateOrder.setId(contract.getOrderId());
		updateOrder.setContractId(null);
		updateOrder.setContractNo(null);
		orderMapper.updateRecordById(updateOrder);
		
	}
}
