package com.sinochem.crude.trade.orderexecute.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.ShipInfoVO;
import com.sinochem.crude.trade.orderexecute.dao.OrderShipMapper;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderShip; 

public interface OrderShipService {
	
	public abstract OrderShipMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(OrderShip orderShip);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long orderShipId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(OrderShip  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(OrderShip orderShip) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(OrderShip orderShip);
	
	
	/**
	 * 根据主键-查询对象
	 */
	OrderShip findByPrimaryKey(Long orderShipId);

	/**
	 * 根据uuid查询对象
	 */
	OrderShip findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<OrderShip> queryByEntitys(OrderShip orderShip);
		
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
	
	OrderShip findByOrderId(Long orderId); 
	/**
	 * 根据订单编号-查询全部
	 */
	OrderShip findByOrderNo(String orderNo);
	/**
	 * 外部系统-同步船舶信息
	 */
	void saveOrderShip(ShipInfoVO vo, Order order);
}
