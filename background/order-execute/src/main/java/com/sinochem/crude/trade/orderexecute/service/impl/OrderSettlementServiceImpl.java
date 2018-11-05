package com.sinochem.crude.trade.orderexecute.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.remote.OrderStatusService;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.commons.constants.OrderStatusEnum;
import com.sinochem.crude.trade.orderexecute.commons.constants.StatementStatusEnum;
import com.sinochem.crude.trade.orderexecute.dao.OrderFeeItemMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderSettlementMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderStatementMapper;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceSystem;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderFeeItem;
import com.sinochem.crude.trade.orderexecute.domain.OrderSettlement;
import com.sinochem.crude.trade.orderexecute.domain.OrderStatement;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.InterfaceListVO;
import com.sinochem.crude.trade.orderexecute.model.OrderSettlementVO;
import com.sinochem.crude.trade.orderexecute.model.OrderStatementVO;
import com.sinochem.crude.trade.orderexecute.query.OrderStatementQuery;
import com.sinochem.crude.trade.orderexecute.service.InterfaceSystemService;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.crude.trade.orderexecute.service.OrderSettlementService;
import com.sinochem.crude.trade.orderexecute.service.openapi.OutputService;
import com.sinochem.crude.trade.orderexecute.service.openapi.constants.TypeCodeEnum;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.PhysicalOperationSaveInfoVO;

@Service
public class OrderSettlementServiceImpl implements OrderSettlementService {
	@Autowired
	private OrderSettlementMapper orderSettlementMapper;
	@Autowired
	private OrderStatementMapper orderStatementMapper;
	@Autowired
	private OrderStatusService orderStatusService;
	@Autowired
	private OrderFeeItemMapper feeItemMapper;
	@Autowired
	private OrderService orderService;
	@Autowired	
	private OutputService outputService;
	@Autowired	
	private InterfaceSystemService interfaceSystemService;
	Log log = LogFactory.getLog(OrderStatementServiceImpl.class);
	
	public OrderSettlementMapper getMapper(){
		return orderSettlementMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderSettlement ordersettlement){
		 return orderSettlementMapper.insertRecord(ordersettlement);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long orderSettlementId) throws BizException{
		if (orderSettlementId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderSettlementMapper.deleteById(orderSettlementId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderSettlement  record){
    	return orderSettlementMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderSettlement orderSettlement) throws BizException{
		if ( orderSettlement.getOrderSettlementId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","orderSettlementId 为空，不能修改","orderSettlementId");
		}
		return orderSettlementMapper.updateRecordById(orderSettlement);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderSettlementMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderSettlement orderSettlement){
		return orderSettlementMapper.updateRecords(orderSettlement.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderSettlement findByPrimaryKey(Long orderSettlementId){
		return  orderSettlementMapper.findByPrimaryKey(orderSettlementId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderSettlement findByUuid(String uuid){
		return  orderSettlementMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderSettlement> queryByEntitys(OrderSettlement orderSettlement){
		return  orderSettlementMapper.queryByEntitys(orderSettlement);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderSettlementMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderSettlementMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderSettlementMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	
	/**
	 * 根据条件分页查询（预估结算管理）
	 */
	@Override
	public List<Map<String, Object>> selectAccountsPage(OrderStatementQuery query, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (List<Map<String, Object>>) orderSettlementMapper.selectAccountsPage(query);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Result saveSettlement(OrderStatementVO vo, CurrentUser user) throws ParseException {
		Result result = new Result();
		try {
			OrderStatement orderStatement = orderStatementMapper.findByPrimaryKey(vo.getOrderStatementId());
			
			OrderSettlementVO entity = BeanConvertUtils.beanToBean(orderStatement, OrderSettlementVO.class);
			entity.setSettlementType(orderStatement.getStatementType());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				entity.setBillDate(sdf.parse(vo.getBillDateDesc()));
			} catch (ParseException e) { log.error(e);}
			try {
				entity.setCheckTime(sdfTime.parse(vo.getCheckTimeDesc()));
			} catch (ParseException e) { log.error(e);}
			try {
				entity.setPaymentDate(sdf.parse(vo.getPaymentDateDesc()));
			} catch (ParseException e) { log.error(e);}
			
			entity.setOrderSettlementUuid(KeyGenUtils.newUuid());
			
			if(Constants.SETTLEMENT_TYPE_S.equals(entity.getSettlementType())){
				entity.setOrderSettlementNo(vo.getOrderNo() + Constants.SETTLEMENT_PRE);
			}else{
				entity.setOrderSettlementNo(vo.getOrderNo() + Constants.SETTLEMENT_FIN);
				log.info("saveSettlement 确认结算状态机开始");
				//正式结算单状态机（已结算）
				orderStatusService.setOrderStatus(orderService.findByPrimaryKey(entity.getOrderId()).getTradeId(), 
						StatementStatusEnum.ORDER_STATUS_3.getCode(), 
						Integer.parseInt(StatementStatusEnum.ORDER_STATUS_ITEM_3_9.getCode()), 
						"", 
						user.getMemberId());
				log.info("saveSettlement 确认结算状态机结束");
				//正式 更改订单状态
				Order order = new Order();
				order.setId(entity.getOrderId());
				order.setStatus(OrderStatusEnum.STATUS_6.getCode());
				orderService.updateRecordById(order);
			}
			
			entity.setStatus(Constants.STATEMENT_STATUS_30);
		
			orderSettlementMapper.insertRecord(entity);
			
			//修改对账单的状态为已结算
			vo.setStatus(Constants.STATEMENT_STATUS_30);
			orderStatementMapper.updateRecordById(vo);
			
			OrderFeeItem feeItemPara = new OrderFeeItem();
			feeItemPara.setRelatId(vo.getOrderStatementId());
			feeItemPara.setRelatType(Constants.FEE_TYPE_1);
			List<OrderFeeItem> itemList = feeItemMapper.queryByEntitys(feeItemPara);
			for(OrderFeeItem item : itemList){
				item.setFeeItemId(null);
				item.setLangVer(Constants.LANG_VER);
				item.setAliveFlag(Constants.ALIEVE_FLAG_YES);
				item.setCreateDate(DateTimeUtils.currentDate());
				item.setCreateUser(user.getMemberId());
				item.setRelatType(Constants.FEE_TYPE_2);
				item.setRelatId(entity.getOrderSettlementId());
				feeItemMapper.insertRecord(item);
			}
			
			if(orderStatement.getDataJson() != null && !"".equals(orderStatement.getDataJson())){
				Order order = orderService.findByPrimaryKey(vo.getOrderId());
				PhysicalOperationSaveInfoVO physicalOperationSaveInfoVO = new PhysicalOperationSaveInfoVO();
				physicalOperationSaveInfoVO.setId(order.getUuid());
				if(Constants.STATEMENT_TYPE_S.equals(orderStatement.getStatementType())){
					physicalOperationSaveInfoVO.setType_code(TypeCodeEnum.EST);//临时
				}else{
					physicalOperationSaveInfoVO.setType_code(TypeCodeEnum.FINAL);//最终
				}	
				physicalOperationSaveInfoVO.setStatus(0);
				physicalOperationSaveInfoVO.setValues(orderStatement.getDataJson());

				List<InterfaceSystem> list = new ArrayList<InterfaceSystem>();
				
				if(order.getBuyerCustomerId() != null && !"".equals(order.getBuyerCustomerId())){
					InterfaceSystem interfaceSystemVO = interfaceSystemService.findByMemberId(order.getBuyerCustomerId());
					InterfaceListVO interfaceListVO = new InterfaceListVO();
					interfaceListVO.setSysName(interfaceSystemVO.getSysName());
					outputService.physicalOperationSaveInfo(interfaceListVO, physicalOperationSaveInfoVO);
				}
				if(order.getSellerCustomerId() != null && !"".equals(order.getSellerCustomerId())){
					InterfaceSystem interfaceSystemVO = interfaceSystemService.findByMemberId(order.getSellerCustomerId());
					InterfaceListVO interfaceListVO = new InterfaceListVO();
					interfaceListVO.setSysName(interfaceSystemVO.getSysName());
					outputService.physicalOperationSaveInfo(interfaceListVO, physicalOperationSaveInfoVO);
				}
				
				
			}
			return result;
		} catch (BizException e) {
			log.info("saveSettlement 确认结算状态机出错");
			log.error("saveSettlement error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
			throw new BizException(e.getMessage());
		} catch (Exception e) {
			log.info("saveSettlement 确认结算状态机出错");
			log.error("synchronizeReceivables error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(Constants.EXCEPTION_CODE);
			result.setMessage(Constants.EXCEPTION_MESSAGE);
			throw new BizException(e.getMessage());
		}
		
	}

	
	/**
	 * 查询订单信息
	 */
	@Override
	public Map<String, Object> selectinformation(Long orderId) {
		return orderSettlementMapper.selectinformation(orderId);
	}

	@Override
	public List<OrderSettlement> queryByOrderId(Long orderId) {
		OrderSettlement query = new OrderSettlement();
		query.setOrderId(orderId);
		return orderSettlementMapper.queryByEntitys(query);
	}

	@Override
	public OrderSettlement queryActiveSettlementByOrderId(Long orderId) {
		List<OrderSettlement> settlementList = queryByOrderId(orderId);
		
		OrderSettlement data = null;
		if(settlementList != null && !settlementList.isEmpty()) {
			data = settlementList.get(0);
			for (int i = 1;i < settlementList.size(); i++) {
				if("2".equals(settlementList.get(i).getSettlementType())) {
					data = settlementList.get(i);
					break;
				}
			}
		}
		
		return data;
	}

	@Override
	public double calcSettlementTotal(Long orderId) {
		return orderSettlementMapper.calcSettlementTotal(orderId);
	}

}
