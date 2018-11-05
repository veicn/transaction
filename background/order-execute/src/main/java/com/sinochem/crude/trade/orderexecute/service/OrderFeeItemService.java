package com.sinochem.crude.trade.orderexecute.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.dao.OrderFeeItemMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderFeeItem; 

public interface OrderFeeItemService {
	
	public abstract OrderFeeItemMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(OrderFeeItem orderFeeItem);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long feeItemId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(OrderFeeItem  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(OrderFeeItem orderFeeItem) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(OrderFeeItem orderFeeItem);
	
	
	/**
	 * 根据主键-查询对象
	 */
	OrderFeeItem findByPrimaryKey(Long feeItemId);

	/**
	 * 根据uuid查询对象
	 */
	OrderFeeItem findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<OrderFeeItem> queryByEntitys(OrderFeeItem orderFeeItem);
		
	/**
	 * 根据条件-查询全部
	 */
	List<OrderFeeItem> queryRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-分页查询
	 */
	Page<OrderFeeItem> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 根据条件-查询记录条数
	 */
	int countRecords(Map<String, Object> map); 
	
	

	//**************************以下方法为开发者补充*********************************/
	
//	/**
//	 * 新增费用信息
//	 * @param vo
//	 */
//	public abstract void saveOrderFeeItem(OrderFeeItemVO vo,CurrentUser user);
	
	public abstract List<OrderFeeItem> selectOrderFeeItem(Long orderId);
	
}
