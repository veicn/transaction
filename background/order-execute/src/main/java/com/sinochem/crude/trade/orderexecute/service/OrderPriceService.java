package com.sinochem.crude.trade.orderexecute.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.dao.OrderPriceMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderPrice; 

public interface OrderPriceService {
	
	public abstract OrderPriceMapper getMapper(); 
	
	/**
	 * 新增
	 */
	long insertRecord(OrderPrice orderPrice);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long orderPriceId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(OrderPrice  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(OrderPrice orderPrice) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(OrderPrice orderPrice);
	
	
	/**
	 * 根据主键-查询对象
	 */
	OrderPrice findByPrimaryKey(Long orderPriceId);

	/**
	 * 根据uuid查询对象
	 */
	OrderPrice findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<OrderPrice> queryByEntitys(OrderPrice orderPrice);
		
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
	
	OrderPrice findByOrderId(Long orderId);
	/**
	 * 更新付款日期
	 * @param orderPriceId
	 * @param payDate
	 */
	void updatePayDate(Long orderPriceId, Date payDate);
}
