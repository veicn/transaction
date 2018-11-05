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
import com.sinochem.crude.trade.orderexecute.model.TriggerApplyVO;
import com.sinochem.crude.trade.orderexecute.dao.TriggerApplyMapper;
import com.sinochem.crude.trade.orderexecute.dao.TriggerContractMapper;
import com.sinochem.crude.trade.orderexecute.dao.TriggerResultMapper;
import com.sinochem.crude.trade.orderexecute.service.TriggerApplyService;

@Service
public class TriggerApplyServiceImpl implements TriggerApplyService {
	@Autowired
	private TriggerApplyMapper triggerApplyMapper;
	@Autowired
	private TriggerContractMapper triggerContractMapper;
	@Autowired
	private TriggerResultMapper triggerResultMapper;
	
	public TriggerApplyMapper getMapper(){
		return triggerApplyMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(TriggerApply triggerapply){
		 return triggerApplyMapper.insertRecord(triggerapply);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return triggerApplyMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(TriggerApply  record){
    	return triggerApplyMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(TriggerApply triggerApply) throws BizException{
		if ( triggerApply.getId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return triggerApplyMapper.updateRecordById(triggerApply);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return triggerApplyMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(TriggerApply triggerApply){
		return triggerApplyMapper.updateRecords(triggerApply.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public TriggerApply findByPrimaryKey(Long id){
		return  triggerApplyMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public TriggerApply findByUuid(String uuid){
		return  triggerApplyMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<TriggerApply> queryByEntitys(TriggerApply triggerApply){
		return  triggerApplyMapper.queryByEntitys(triggerApply);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return triggerApplyMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) triggerApplyMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return triggerApplyMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	@Override
	public List<TriggerApplyVO> findByOrderId(Long orderId) {
		if(orderId == null) {
			return new ArrayList<>();
		}
		
		TriggerApply query = new TriggerApply();
		query.setOrderId(orderId);
		List<TriggerApply> list = this.queryByEntitys(query);
		
		if(list != null && !list.isEmpty()) {
			return BeanConvertUtils.beanToBeanInList(list, TriggerApplyVO.class);
		}
		
		return new ArrayList<>();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int saveTriggerApply(TriggerApply triggerApply, TriggerContract triggerContractUpdate) {
		int num1 = triggerApplyMapper.insertRecord(triggerApply);
		int num2 = triggerContractMapper.updateRecordById(triggerContractUpdate);
		return num1 + num2;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int completeTriggerApply(TriggerApply triggerApplyUpdate, TriggerResult triggerResultInsert,
			TriggerContract triggerContractUpdate) {
		int num1 = triggerApplyMapper.updateRecordById(triggerApplyUpdate);
		if(triggerResultInsert != null) {
			triggerResultMapper.insertRecord(triggerResultInsert);
		}
		if(triggerContractUpdate != null) {
			triggerContractMapper.updateRecordById(triggerContractUpdate);
		}
		
		return num1;
	}
}
