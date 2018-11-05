package com.sinochem.crude.trade.orderexecute.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.dao.TriggerApplyMapper;
import com.sinochem.crude.trade.orderexecute.domain.TriggerApply;
import com.sinochem.crude.trade.orderexecute.domain.TriggerContract;
import com.sinochem.crude.trade.orderexecute.domain.TriggerResult;
import com.sinochem.crude.trade.orderexecute.model.TriggerApplyVO; 

public interface TriggerApplyService {
	
	public abstract TriggerApplyMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(TriggerApply triggerApply);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(TriggerApply  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(TriggerApply triggerApply) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(TriggerApply triggerApply);
	
	
	/**
	 * 根据主键-查询对象
	 */
	TriggerApply findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	TriggerApply findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<TriggerApply> queryByEntitys(TriggerApply triggerApply);
		
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
	List<TriggerApplyVO> findByOrderId(Long orderId);
	/**
	 * 保存点价申请
	 * @param triggerApply
	 * @param triggerContractUpdate
	 * @return
	 */
	int saveTriggerApply(TriggerApply triggerApply, TriggerContract triggerContractUpdate);
	/**
	 * 完成点价
	 * @param triggerApplyUpdate
	 * @param triggerResultInsert
	 * @param triggerContractUpdate
	 * @return
	 */
	int completeTriggerApply(TriggerApply triggerApplyUpdate, TriggerResult triggerResultInsert, TriggerContract triggerContractUpdate);
}
