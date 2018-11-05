package com.sinochem.crude.trade.orderexecute.service.impl;

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
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.order.remote.OrderStatusService;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.commons.constants.OrderStatusEnum;
import com.sinochem.crude.trade.orderexecute.commons.constants.TransportStatusEnum;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.ShipInfoVO;
import com.sinochem.crude.trade.orderexecute.dao.OrderShipMapper;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderShip;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.crude.trade.orderexecute.service.OrderShipService;

@Service
public class OrderShipServiceImpl implements OrderShipService {
	@Autowired
	private OrderShipMapper orderShipMapper;
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderStatusService orderStatusService;
	
	public OrderShipMapper getMapper(){
		return orderShipMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderShip ordership){
		 return orderShipMapper.insertRecord(ordership);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long orderShipId) throws BizException{
		if (orderShipId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderShipMapper.deleteById(orderShipId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderShip  record){
    	return orderShipMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderShip orderShip) throws BizException{
		if ( orderShip.getOrderShipId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","orderShipId 为空，不能修改","orderShipId");
		}
		return orderShipMapper.updateRecordById(orderShip);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderShipMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderShip orderShip){
		return orderShipMapper.updateRecords(orderShip.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderShip findByPrimaryKey(Long orderShipId){
		return  orderShipMapper.findByPrimaryKey(orderShipId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderShip findByUuid(String uuid){
		return  orderShipMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderShip> queryByEntitys(OrderShip orderShip){
		return  orderShipMapper.queryByEntitys(orderShip);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderShipMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderShipMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderShipMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 根据orderId-查询对象
	 */
	@Override
	public OrderShip findByOrderId(Long orderId){
		return  orderShipMapper.findByOrderId(orderId);	
	}
	
	@Override
	public OrderShip findByOrderNo(String orderNo){
		OrderShip query = new OrderShip();
		query.setOrderNo(orderNo);
		List<OrderShip> list = orderShipMapper.queryByEntitys(query);
		
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrderShip(ShipInfoVO vo, Order order){
		
		// 同步船舶信息
		OrderShip orderShip = new OrderShip();
		orderShip.setUuid(KeyGenUtils.newUuid());
		orderShip.setOrderId(order.getId());
		orderShip.setOrderNo(order.getOrderNo());
		orderShip.setMmsi(vo.getMmsi());
		orderShip.setName(vo.getName());
		orderShip.setType(vo.getType());
		orderShip.setPinyinSim(vo.getPinyinSim());
		orderShip.setUpdateDate(DateTimeUtils.currentDate());
		orderShip.setUpdateUser(9999L);
		
		OrderShip checkOrderShip = orderShipMapper.findByOrderId(order.getId());
		
		if (checkOrderShip != null) {		
			orderShip.setOrderShipId(checkOrderShip.getOrderShipId());
			orderShipMapper.updateRecordById(orderShip);
		} else {
			orderShip.setLangVer(Constants.LANG_VER);
			orderShip.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			orderShip.setCreateDate(DateTimeUtils.currentDate());
			orderShip.setCreateUser(9999L);
			
			orderShipMapper.insertRecord(orderShip);
		}

		// 订单状态变更
		if (OrderStatusEnum.STATUS_1.getCode().equals(order.getStatus())) {
			Order changeStatusOrder = new Order();
			changeStatusOrder.setId(order.getId());
			changeStatusOrder.setStatus(OrderStatusEnum.STATUS_2.getCode());
			changeStatusOrder.setUpdateDate(DateTimeUtils.currentDate());
			changeStatusOrder.setUpdateUser(9999L);
			orderService.updateRecordById(changeStatusOrder);

			try {
				orderStatusService.setOrderStatus(order.getTradeId(), 
						TransportStatusEnum.ORDER_STATUS_2.getCode(), 
						Integer.parseInt(TransportStatusEnum.ORDER_STATUS_ITEM_2_1.getCode()), 
						"", 9999L);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new BizException(e.getMessage());
			} catch (com.sinochem.it.b2b.common.exception.BizException e) {
				e.printStackTrace();
				throw new BizException(e.getMessage());
			}
		}	
	}
	
//	/**
//	 * 根据条件分页查询（预估结算管理）
//	 */
//	@Override
//	public List<Map<String, Object>> selectAccountsPage(OrderStatementQuery query, SimplePageInfo pageInfo) {
//		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
//		return (List<Map<String, Object>>) orderStatementMapper.selectPage(query);
//	}
}
