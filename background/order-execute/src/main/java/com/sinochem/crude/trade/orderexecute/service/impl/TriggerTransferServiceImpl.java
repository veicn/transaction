package com.sinochem.crude.trade.orderexecute.service.impl;

import java.util.ArrayList;
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
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.orderexecute.dao.TriggerContractMapper;
import com.sinochem.crude.trade.orderexecute.dao.TriggerTransferMapper;
import com.sinochem.crude.trade.orderexecute.domain.TriggerContract;
import com.sinochem.crude.trade.orderexecute.domain.TriggerTransfer;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.TriggerTransferVO;
import com.sinochem.crude.trade.orderexecute.service.TriggerTransferService;

@Service
public class TriggerTransferServiceImpl implements TriggerTransferService {
	@Autowired
	private TriggerTransferMapper triggerTransferMapper;
	@Autowired
	private TriggerContractMapper triggerContractMapper;
	
	public TriggerTransferMapper getMapper(){
		return triggerTransferMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(TriggerTransfer triggertransfer){
		 return triggerTransferMapper.insertRecord(triggertransfer);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return triggerTransferMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(TriggerTransfer  record){
    	return triggerTransferMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(TriggerTransfer triggerTransfer) throws BizException{
		if ( triggerTransfer.getId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return triggerTransferMapper.updateRecordById(triggerTransfer);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return triggerTransferMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(TriggerTransfer triggerTransfer){
		return triggerTransferMapper.updateRecords(triggerTransfer.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public TriggerTransfer findByPrimaryKey(Long id){
		return  triggerTransferMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public TriggerTransfer findByUuid(String uuid){
		return  triggerTransferMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<TriggerTransfer> queryByEntitys(TriggerTransfer triggerTransfer){
		return  triggerTransferMapper.queryByEntitys(triggerTransfer);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return triggerTransferMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) triggerTransferMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return triggerTransferMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	@Override
	public List<TriggerTransferVO> findByOrderId(Long orderId) {
		if(orderId == null) {
			return new ArrayList<>();
		}
		
		TriggerTransfer query = new TriggerTransfer();
		query.setOrderId(orderId);
		List<TriggerTransfer> list = this.queryByEntitys(query);
		
		if(list != null && !list.isEmpty()) {
			return BeanConvertUtils.beanToBeanInList(list, TriggerTransferVO.class);
		}
		
		return new ArrayList<>();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int saveTransferInfo(TriggerTransfer triggerTransfer, TriggerContract newTriggerContract, TriggerContract triggerContractUpdate) {
		int num1,num2,num3;
		
		if(newTriggerContract.getId() != null) {
			num2 = triggerContractMapper.updateRecordById(newTriggerContract);
		}else {
			num2 = triggerContractMapper.insertRecord(newTriggerContract);
		}
		
		triggerTransfer.setContractId(newTriggerContract.getId());
		num1 = triggerTransferMapper.insertRecord(triggerTransfer);
		
		//更新旧合约数值
		num3 = triggerContractMapper.updateRecordById(triggerContractUpdate);
		
		return num1+num2+num3;
	}

	@Override
	public int countTransferMonth(Long orderId, String transferMonth) {
		if(orderId == null) {
			return 0;
		}
		
		return triggerTransferMapper.countTransferMonth(orderId, transferMonth);
	}

	@Override
	public TriggerTransfer queryByTriggerContractId(Long contractId) {
		if(contractId == null) {
			return null;
		}
		
		TriggerTransfer query = new TriggerTransfer();
		query.setContractId(contractId);
		
		List<TriggerTransfer> triggerTransferList = triggerTransferMapper.queryByEntitys(query);
		if(triggerTransferList != null && !triggerTransferList.isEmpty()) {
			return triggerTransferList.get(0);
		}
		
		return null;
	}
}
