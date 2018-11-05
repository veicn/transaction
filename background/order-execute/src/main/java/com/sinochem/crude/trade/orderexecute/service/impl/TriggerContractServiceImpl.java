package com.sinochem.crude.trade.orderexecute.service.impl;

import java.math.BigDecimal;
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
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.orderexecute.dao.OrderGoodsMapper;
import com.sinochem.crude.trade.orderexecute.dao.TriggerApplyMapper;
import com.sinochem.crude.trade.orderexecute.dao.TriggerContractMapper;
import com.sinochem.crude.trade.orderexecute.dao.TriggerResultMapper;
import com.sinochem.crude.trade.orderexecute.dao.TriggerTransferMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderGoods;
import com.sinochem.crude.trade.orderexecute.domain.TriggerApply;
import com.sinochem.crude.trade.orderexecute.domain.TriggerContract;
import com.sinochem.crude.trade.orderexecute.domain.TriggerResult;
import com.sinochem.crude.trade.orderexecute.domain.TriggerTransfer;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.TriggerContractVO;
import com.sinochem.crude.trade.orderexecute.service.TriggerContractService;

@Service
public class TriggerContractServiceImpl implements TriggerContractService {
	@Autowired
	private TriggerContractMapper triggerContractMapper;
	@Autowired
	private TriggerApplyMapper triggerApplyMapper;
	@Autowired
	private TriggerTransferMapper triggerTransferMapper;
	@Autowired
	private TriggerResultMapper triggerResultMapper;
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	
	public TriggerContractMapper getMapper(){
		return triggerContractMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(TriggerContract triggercontract){
		 return triggerContractMapper.insertRecord(triggercontract);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return triggerContractMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(TriggerContract  record){
    	return triggerContractMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(TriggerContract triggerContract) throws BizException{
		if ( triggerContract.getId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return triggerContractMapper.updateRecordById(triggerContract);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return triggerContractMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(TriggerContract triggerContract){
		return triggerContractMapper.updateRecords(triggerContract.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public TriggerContract findByPrimaryKey(Long id){
		return  triggerContractMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public TriggerContract findByUuid(String uuid){
		return  triggerContractMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<TriggerContract> queryByEntitys(TriggerContract triggerContract){
		return  triggerContractMapper.queryByEntitys(triggerContract);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return triggerContractMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) triggerContractMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return triggerContractMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	@Override
	public List<TriggerContractVO> findByOrderId(Long orderId) {
		if(orderId == null) {
			return new ArrayList<>();
		}
		
		TriggerContract query = new TriggerContract();
		query.setOrderId(orderId);
		List<TriggerContract> list = this.queryByEntitys(query);
		
		if(list != null && !list.isEmpty()) {
			return BeanConvertUtils.beanToBeanInList(list, TriggerContractVO.class);
		}
		
		return new ArrayList<>();
	}
	
	@Override
	public TriggerContract queryExistContract(Long orderId, String month) {
		TriggerContract query = new TriggerContract();
		query.setOrderId(orderId);
		query.setMonth(month);
		
		List<TriggerContract> triggerContractList = triggerContractMapper.queryByEntitys(query);
		if(triggerContractList != null && !triggerContractList.isEmpty()) {
			return triggerContractList.get(0);
		}
		
		return null;
	}

	@Override
	public TriggerContract queryFirstContract(Long orderId) {
		TriggerContract query = new TriggerContract();
		query.setOrderId(orderId);
		List<TriggerContract> list = triggerContractMapper.queryByEntitys(query);
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void resetContract(Long orderId) {
		OrderGoods goodsQuery = new OrderGoods();
		goodsQuery.setOrderId(orderId);
		List<OrderGoods> goodsInfoList = orderGoodsMapper.queryByEntitys(goodsQuery);
		OrderGoods goodsInfo;
		if(goodsInfoList != null && !goodsInfoList.isEmpty()){
			goodsInfo = goodsInfoList.get(0);
		}else {
			return;
		}
		BigDecimal quantity = goodsInfo.getQuantity();
		
		TriggerContract contractQuery = new TriggerContract();
		contractQuery.setOrderId(orderId);
		List<TriggerContract> oldContractList = triggerContractMapper.queryByEntitys(contractQuery);
		
		for(int i = 1; i < oldContractList.size(); i++) {
			triggerContractMapper.deleteById(oldContractList.get(i).getId());
		}
		
		TriggerContract firstContract = oldContractList.get(0);
		firstContract.setQuantity(quantity);
		firstContract.setDoneQuantity(BigDecimal.ZERO);
		firstContract.setSurplusQuantity(quantity);
		firstContract.setTransferQuantity(BigDecimal.ZERO);
		firstContract.setUpdateDate(DateTimeUtils.currentDate());
		
		triggerContractMapper.updateRecordById(firstContract);
		
		TriggerApply applyDelete = new TriggerApply();
		applyDelete.setOrderId(orderId);
		triggerApplyMapper.deleteRecords(applyDelete);
		
		TriggerTransfer transferDelete = new TriggerTransfer(); 
		transferDelete.setOrderId(orderId);
		triggerTransferMapper.deleteRecords(transferDelete);
		
		TriggerResult resultDelete = new TriggerResult();
		resultDelete.setOrderId(orderId);
		triggerResultMapper.deleteRecords(resultDelete);
		
	}
}
