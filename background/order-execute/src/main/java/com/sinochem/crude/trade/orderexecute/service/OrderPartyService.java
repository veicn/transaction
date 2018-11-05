package com.sinochem.crude.trade.orderexecute.service;

import java.util.Map;
import java.util.List;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.domain.OrderParty;
import com.sinochem.crude.trade.orderexecute.dao.OrderPartyMapper; 

public interface OrderPartyService {
	
	public abstract OrderPartyMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(OrderParty orderParty);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long partyId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(OrderParty  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(OrderParty orderParty) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(OrderParty orderParty);
	
	
	/**
	 * 根据主键-查询对象
	 */
	OrderParty findByPrimaryKey(Long partyId);

	/**
	 * 根据uuid查询对象
	 */
	OrderParty findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<OrderParty> queryByEntitys(OrderParty orderParty);
		
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
	 * 查询贸易公司联系人信息
	 * @param orderId 订单ID
	 * @param partyType 所属交易方类型（1：买方，2：卖方，3：代理商）
	 * @return
	 */
	OrderParty findByOrderIdAndPartyType(Long orderId, String partyType);
	/**
	 * 查询贸易公司联系人信息
	 * @param orderId 订单ID
	 * @param 公司（客户）ID
	 * @return
	 */
	OrderParty findByOrderIdAndCustomerId(Long orderId, Long customerId);
}