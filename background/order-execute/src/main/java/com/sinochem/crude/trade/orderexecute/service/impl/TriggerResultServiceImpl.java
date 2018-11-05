package com.sinochem.crude.trade.orderexecute.service.impl;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.orderexecute.domain.TriggerApply;
import com.sinochem.crude.trade.orderexecute.domain.TriggerContract;
import com.sinochem.crude.trade.orderexecute.domain.TriggerResult;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.TriggerResultVO;
import com.sinochem.crude.trade.orderexecute.dao.TriggerApplyMapper;
import com.sinochem.crude.trade.orderexecute.dao.TriggerContractMapper;
import com.sinochem.crude.trade.orderexecute.dao.TriggerResultMapper;
import com.sinochem.crude.trade.orderexecute.service.TriggerResultService;

@Service
public class TriggerResultServiceImpl implements TriggerResultService {
	@Autowired
	private TriggerResultMapper triggerResultMapper;
	@Autowired
	private TriggerContractMapper triggerContractMapper;
	@Autowired
	private TriggerApplyMapper triggerApplyMapper;
	
	public TriggerResultMapper getMapper(){
		return triggerResultMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(TriggerResult triggerresult){
		 return triggerResultMapper.insertRecord(triggerresult);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return triggerResultMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(TriggerResult  record){
    	return triggerResultMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(TriggerResult triggerResult) throws BizException{
		if ( triggerResult.getId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return triggerResultMapper.updateRecordById(triggerResult);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return triggerResultMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(TriggerResult triggerResult){
		return triggerResultMapper.updateRecords(triggerResult.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public TriggerResult findByPrimaryKey(Long id){
		return  triggerResultMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public TriggerResult findByUuid(String uuid){
		return  triggerResultMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<TriggerResult> queryByEntitys(TriggerResult triggerResult){
		return  triggerResultMapper.queryByEntitys(triggerResult);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return triggerResultMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) triggerResultMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return triggerResultMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	@Override
	public List<TriggerResultVO> findByOrderId(Long orderId) {
		if(orderId == null) {
			return new ArrayList<>();
		}
		
		TriggerResult query = new TriggerResult();
		query.setOrderId(orderId);
		List<TriggerResult> list = this.queryByEntitys(query);
		
		if(list != null && !list.isEmpty()) {
			return BeanConvertUtils.beanToBeanInList(list, TriggerResultVO.class);
		}
		
		return new ArrayList<>();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int saveSplitOrderInfo(TriggerResult triggerResult, TriggerContract triggerContractUpdate) {
		int num1 = triggerResultMapper.insertRecord(triggerResult);
		int num2 = triggerContractMapper.updateRecordById(triggerContractUpdate);
		
		return num1 + num2;
	}

	@Override
	public int updateSplitOrder(TriggerResult triggerResultUpdate, TriggerContract triggerContractUpdate, TriggerApply triggerApplyUpdate) {
		int num = 0;
		num += triggerResultMapper.updateRecordById(triggerResultUpdate);
		num += triggerContractMapper.updateRecordById(triggerContractUpdate);
		
		if(triggerApplyUpdate != null) {
			num += triggerApplyMapper.updateRecordById(triggerApplyUpdate);
		}
		
		return num;
	}
}
