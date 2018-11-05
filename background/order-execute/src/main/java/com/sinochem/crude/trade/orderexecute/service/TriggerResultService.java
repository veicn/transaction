package com.sinochem.crude.trade.orderexecute.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.dao.TriggerResultMapper;
import com.sinochem.crude.trade.orderexecute.domain.TriggerApply;
import com.sinochem.crude.trade.orderexecute.domain.TriggerContract;
import com.sinochem.crude.trade.orderexecute.domain.TriggerResult;
import com.sinochem.crude.trade.orderexecute.model.TriggerResultVO; 

public interface TriggerResultService {
	
	public abstract TriggerResultMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(TriggerResult triggerResult);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(TriggerResult  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(TriggerResult triggerResult) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(TriggerResult triggerResult);
	
	
	/**
	 * 根据主键-查询对象
	 */
	TriggerResult findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	TriggerResult findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<TriggerResult> queryByEntitys(TriggerResult triggerResult);
		
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
	
	List<TriggerResultVO> findByOrderId(Long orderId);
	/**
	 * 保存拆单相关信息
	 * @param triggerResult
	 * @param triggerContractUpdate
	 * @return
	 */
	int saveSplitOrderInfo(TriggerResult triggerResult, TriggerContract triggerContractUpdate);
	
	/**
	 * 更新拆单信息
	 * @param triggerResultUpdate
	 * @param triggerContractUpdate
	 * @param triggerApplyUpdate
	 * @return
	 */
	int updateSplitOrder(TriggerResult triggerResultUpdate, TriggerContract triggerContractUpdate, TriggerApply triggerApplyUpdate);
}
