package com.sinochem.crude.trade.orderexecute.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.ReceivablesVO;
import com.sinochem.crude.trade.orderexecute.dao.OrderMapper;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderBO;
import com.sinochem.crude.trade.orderexecute.domain.OrderDetailView;
import com.sinochem.crude.trade.orderexecute.model.CountOrderContractVO;
import com.sinochem.crude.trade.orderexecute.model.OrderVO;

public interface OrderService {
	
	public abstract OrderMapper getMapper(); 
	
	/**
	 * 新增
	 */
	long insertRecord(Order order);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(Order  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(Order order) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Order order);
	
	
	/**
	 * 根据主键-查询对象
	 */
	Order findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	Order findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<Order> queryByEntitys(Order order);
		
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
	
	Order findByOrderNo(String orderNo);
	
	/**
	 * 查询订单列表
	 * @param map 
	 * @param createUser
	 * @param pageInfo
	 * @return
	 */
	Page<OrderBO> queryOrderList(Map<String, Object> map, Long createUser, SimplePageInfo pageInfo);
	
	/**
	 * 查询订单详情
	 * @param orderUuid
	 * @return
	 */
	public OrderBO queryOrderDetailByUuid(String orderUuid);
	
	/***
	 * 取得确认收款用的订单信息
	 */
	OrderDetailView getOrderInfo(String uuid);
	
	/**
	 * 保存确认收款信息
	 */
	String savePayConfirm(OrderVO vo, CurrentUser user);
	
	/**
	 * 外部系统-收款信息同步
	 */
	String savePayConfirmFromOut(ReceivablesVO vo);	
	
	/**
	 * 订单状态统计
	 * @return map<key(订单状态Code,total:全部), value(统计数量)> 
	 */
	Map<String, Integer> countOrderStatus(Map<String, Object> param);
	
	/**
	 * 查询订单合同数量
	 * @param vo
	 * @return
	 */
	int countOrderContract(CountOrderContractVO vo);
	/**
	 * 查询总金额
	 * @param uuid
	 * @return
	 */
	BigDecimal selectTotalAmount(String uuid);
}
