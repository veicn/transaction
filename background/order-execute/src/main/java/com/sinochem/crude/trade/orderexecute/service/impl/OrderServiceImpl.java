package com.sinochem.crude.trade.orderexecute.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyeieye.melody.web.locale.VisitorLocale;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.remote.OrderStatusService;
import com.sinochem.crude.trade.orderexecute.commons.constants.FundStatusEnum;
import com.sinochem.crude.trade.orderexecute.commons.constants.OrderStatusEnum;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.ReceivablesVO;
import com.sinochem.crude.trade.orderexecute.dao.OrderDetailViewMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderMapper;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderBO;
import com.sinochem.crude.trade.orderexecute.domain.OrderDetailView;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.CountOrderContractVO;
import com.sinochem.crude.trade.orderexecute.model.CountOrderStatusVO;
import com.sinochem.crude.trade.orderexecute.model.OrderVO;
import com.sinochem.crude.trade.orderexecute.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderStatusService orderStatusService;
	@Autowired
	private OrderDetailViewMapper orderDetailViewMapper;	
	@Autowired
	private EnterpriseService enterpriseService;
	
	Logger log = Logger.getLogger(getClass());
	
	public OrderMapper getMapper(){
		return orderMapper;
	}
	
	/**
	 * 新增
	 */
	@Override
	public long insertRecord(Order order){
		 return orderMapper.insertRecord(order);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(Order  record){
    	return orderMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(Order order) throws BizException{
		if ( order.getId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderMapper.updateRecordById(order);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Order order){
		return orderMapper.updateRecords(order.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public Order findByPrimaryKey(Long id){
		return  orderMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public Order findByUuid(String uuid){
		return  orderMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<Order> queryByEntitys(Order order){
		return  orderMapper.queryByEntitys(order);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	@Override
	public Order findByOrderNo(String orderNo) {
		Order orderQuery = new Order();
		orderQuery.setOrderNo(orderNo);
		List<Order> orderList = orderMapper.queryByEntitys(orderQuery);
		
		if(orderList != null && !orderList.isEmpty()){
			return orderList.get(0);
		}
		
		return null;
	}

	@Override
	public Page<OrderBO> queryOrderList(Map<String, Object> map, Long createUser, SimplePageInfo pageInfo) {
		map.put("createUser", createUser);
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<OrderBO>) orderMapper.queryOrderList(map);
	}
	
	@Override
	public OrderBO queryOrderDetailByUuid(String orderUuid) {
		if(StringUtils.isBlank(orderUuid)) {
			return null;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("uuid", orderUuid);
		List<OrderBO> orderList = orderMapper.queryOrderList(map);
		
		if(orderList != null && !orderList.isEmpty()) {
			
			OrderBO orderInfo = orderList.get(0);
			
			Set<Long> epMemberIds = new HashSet<>();
			epMemberIds.add(orderInfo.getBuyerCustomerId());
			epMemberIds.add(orderInfo.getSellerCustomerId());
			if(orderInfo.getAgentId() != null) {
				epMemberIds.add(orderInfo.getAgentId());
			}
			//查询memberid对应当前语言的公司名称
			Map<Long, String> memId2NameMap = enterpriseService.getEnterpriseName(epMemberIds.toArray(new Long[epMemberIds.size()]), VisitorLocale.getCurrent());
			
			orderInfo.getBuyer().setCustomerName(memId2NameMap.get(orderInfo.getBuyerCustomerId()));
			orderInfo.getSeller().setCustomerName(memId2NameMap.get(orderInfo.getSellerCustomerId()));
			if(orderInfo.getAgent() != null) {
				orderInfo.getAgent().setCustomerName(memId2NameMap.get(orderInfo.getAgentId()));
			}
			
			return orderInfo;
		}
		
		return null;
	}

	/***
	 * 取得确认收款用的订单信息
	 */
	@Override
	public OrderDetailView getOrderInfo(String uuid) {
		return orderDetailViewMapper.findByUuid(uuid);
	}

	/***
	 * 保存确认收款信息
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String savePayConfirm(OrderVO vo, CurrentUser user) {
		Order info = orderMapper.findByPrimaryKey(vo.getId());
		if(info == null) {
			return "没有找到订单信息";
		} else if(info.getReceiveAmount() != null && info.getReceiveAmount().compareTo(BigDecimal.ZERO) != 0) {
			return "收款信息不能重复录入";
		}
		
		int count = orderMapper.getStatementCount(info.getUuid());
		if(count == 0) {
			return "没有生成正式结算单，无法确认收款";
		}

		
		Order entity = new Order();
		entity.setId(vo.getId());
		entity.setReceiveTime(vo.getReceiveTime());
		entity.setReceiveAmount(vo.getReceiveAmount());
		entity.setReceivePerson(user.getMemberId());
		entity.setPayee(vo.getPayee());
		entity.setStatus(OrderStatusEnum.STATUS_7.getCode());
		
		entity.setUpdateDate(DateTimeUtils.currentDate());
		entity.setUpdateUser(user.getMemberId());
		orderMapper.updateRecordById(entity);
		try {
			log.info("savePayConfirm 确认收款状态机开始");
			orderStatusService.setOrderStatus(info.getTradeId(), 
					FundStatusEnum.ORDER_STATUS_4.getCode(), 
					Integer.parseInt(FundStatusEnum.ORDER_STATUS_ITEM_4_9.getCode()), 
					"", 
					user.getMemberId());
			log.info("savePayConfirm 确认收款状态机结束");
		} catch (NumberFormatException e) {
			log.info("savePayConfirm 确认收款状态机出错");
			e.printStackTrace();
			throw new BizException(e.getMessage());
		} catch (com.sinochem.it.b2b.common.exception.BizException e) {
			log.info("savePayConfirm 确认收款状态机出错");
			e.printStackTrace();
			throw new BizException(e.getMessage());
		}
		return null;
	}

	/**
	 * 外部系统-收款信息同步
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String savePayConfirmFromOut(ReceivablesVO vo) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			int count = orderMapper.getStatementCount(vo.getUuid());
			if(count == 0) {
				throw new OrderExecException("orderexecute.code.00085","没有生成正式结算单，无法确认收款");
			}
			
			Order entity = new Order();
			entity.setUuid(vo.getUuid());
			entity.setReceiveTime(sdf.parse(vo.getReceiveTime()));
			entity.setReceiveAmount(new BigDecimal(vo.getReceiveAmount()));
			entity.setReceivePerson(9999L);
			entity.setPayee(vo.getPayee());
			entity.setStatus(OrderStatusEnum.STATUS_7.getCode());
			
			entity.setUpdateDate(DateTimeUtils.currentDate());
			entity.setUpdateUser(9999L);
			
			int ret = orderMapper.savePayConfirmFromOut(entity);
			if(ret == 0) {
				throw new OrderExecException("orderexecute.code.00072","订单信息不存在");

			}

			Order order = this.findByUuid(vo.getUuid());
			
			log.info("savePayConfirm 确认收款状态机开始-外部");
			orderStatusService.setOrderStatus(order.getTradeId(), 
					FundStatusEnum.ORDER_STATUS_4.getCode(), 
					Integer.parseInt(FundStatusEnum.ORDER_STATUS_ITEM_4_9.getCode()), 
					"", 9999L);
			log.info("savePayConfirm 确认收款状态机结束-外部");
		} catch (ParseException e) {
			log.info("savePayConfirm 确认收款状态机出错-外部");
			e.printStackTrace();
			throw new BizException("收款时间不是yyyy-MM-dd HH:mm:ss格式");
		} catch (NumberFormatException e) {
			log.info("savePayConfirm 确认收款状态机出错-外部");
			e.printStackTrace();
			throw new BizException(e.getMessage());
		} catch (com.sinochem.it.b2b.common.exception.BizException e) {
			log.info("savePayConfirm 确认收款状态机出错-外部");
			e.printStackTrace();
			throw new BizException(e.getMessage());
		}

		return null;
	}
	
	@Override
	public Map<String, Integer> countOrderStatus(Map<String, Object> param) {
		param.remove("orderStatus");
		
		Map<String, Integer> countMap = new HashMap<>();
		List<CountOrderStatusVO> countResultList = orderMapper.countOrderStatus(param);
		
		Integer total = 0;
		for(CountOrderStatusVO count : countResultList){
			total += count.getCount();
			countMap.put(count.getStatusCode(), count.getCount());
		}
		
		for(OrderStatusEnum orderStatus : OrderStatusEnum.values()){
			if(countMap.get(orderStatus.getCode()) == null){
				countMap.put(orderStatus.getCode(), 0);
			}
		}
		
		countMap.put("total", total);
		
		return countMap;
	}

	@Override
	public int countOrderContract(CountOrderContractVO vo) {
		return orderMapper.countOrderContract(vo);
	}

	@Override
	public BigDecimal selectTotalAmount(String uuid) {
		return orderMapper.selectTotalAmount(uuid);
	}
}
