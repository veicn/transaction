package com.sinochem.crude.trade.orderexecute.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
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
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.common.utils.SequenceUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.commons.constants.OrderStatusEnum;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.FeeVO;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.StatementSheetVO;
import com.sinochem.crude.trade.orderexecute.dao.OrderFeeItemMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderStatementMapper;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderFeeItem;
import com.sinochem.crude.trade.orderexecute.domain.OrderStatement;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.OrderStatementVO;
import com.sinochem.crude.trade.orderexecute.query.OrderStatementQuery;
import com.sinochem.crude.trade.orderexecute.service.OrderStatementService;

@Service
public class OrderStatementServiceImpl implements OrderStatementService {
	@Autowired
	private OrderStatementMapper orderStatementMapper;
	
	@Autowired
	private OrderFeeItemMapper feeItemMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	
	public OrderStatementMapper getMapper(){
		return orderStatementMapper;
	} 
	Log log = LogFactory.getLog(OrderStatementServiceImpl.class);
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderStatement orderstatement){
		 return orderStatementMapper.insertRecord(orderstatement);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long orderStatementId) throws BizException{
		if (orderStatementId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderStatementMapper.deleteById(orderStatementId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderStatement  record){
    	return orderStatementMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderStatement orderStatement) throws BizException{
		if ( orderStatement.getOrderStatementId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","orderStatementId 为空，不能修改","orderStatementId");
		}
		return orderStatementMapper.updateRecordById(orderStatement);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderStatementMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderStatement orderStatement){
		return orderStatementMapper.updateRecords(orderStatement.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderStatement findByPrimaryKey(Long orderStatementId){
		return  orderStatementMapper.findByPrimaryKey(orderStatementId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderStatement findByUuid(String uuid){
		return  orderStatementMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderStatement> queryByEntitys(OrderStatement orderStatement){
		return  orderStatementMapper.queryByEntitys(orderStatement);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderStatementMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderStatementMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderStatementMapper.countRecords(map);
	}

	
	
	//**************************以下方法为开发者补充*********************************/
	@Override
	public List<OrderStatement> queryByOrderId(Long orderId) {
		OrderStatement query = new OrderStatement();
		query.setOrderId(orderId);
		
		return orderStatementMapper.queryByEntitys(query);
	}
	

	
	@Override
	public OrderStatement queryActiveStatementByOrderId(Long orderId) {
		OrderStatement query = new OrderStatement();
		query.setStatus(Constants.STATEMENT_STATUS_10);
		query.setOrderId(orderId);
		List<OrderStatement> statementList = orderStatementMapper.queryByEntitys(query);
		if(statementList == null || statementList.size() == 0){
			query.setStatus(Constants.STATEMENT_STATUS_30);
			statementList = orderStatementMapper.queryByEntitys(query);
		}
		
		OrderStatement data = null;
		if(statementList != null && !statementList.isEmpty()) {
			data = statementList.get(0);
			for (int i = 1;i < statementList.size(); i++) {
				if("2".equals(statementList.get(i).getStatementType())) {
					data = statementList.get(i);
					break;
				}
			}
		}
		
		return data;
	}
	
	@Override
	public int countValidStatementByOrderId(Long orderId) {
		OrderStatement query = new OrderStatement();
		query.setOrderId(orderId);
		query.setStatementType("2");
		
		List<OrderStatement> list = orderStatementMapper.queryByEntitys(query);
		int countNum = 0;
		if(list == null || list.isEmpty()) {
			return countNum;
		}
		
		for(OrderStatement item : list) {
			if(!"20".equals(item.getStatus())) {//非驳回状态
				countNum += 1;
			}
		}
		
		return countNum;
	}

	@Override
	public double calcStatementTotal(Long orderId) {
		return orderStatementMapper.calcStatementTotal(orderId);
	}
	
	/**
	 * 查询订单信息
	 */
	@Override
	public Map<String, Object> selectinformation(Long orderId) {
		
		return orderStatementMapper.selectinformation(orderId);
	}
	
	/**
	 * 根据orderId-查询对象
	 */
	@Override
	public Map<String, Object> selectData(OrderStatementVO vo, CurrentUser user){
		
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("uuid", vo.getUuid());
		
		return  orderStatementMapper.selectData(map);	
	}

	/**
	 * 根据条件分页查询（预估对账管理）
	 */
	@Override
	public List<Map<String, Object>> selectPage(OrderStatementQuery query, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (List<Map<String, Object>>) orderStatementMapper.selectPage(query);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void saveStatement(OrderStatementVO vo, CurrentUser user)  {
		OrderStatement entity= vo;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			entity.setBillDate(sdf.parse(vo.getBillDateDesc()));
			entity.setCheckTime(sdfTime.parse(vo.getCheckTimeDesc()));
			entity.setPaymentDate(sdf.parse(vo.getPaymentDateDesc()));
		} catch (ParseException e) { 
			log.error(e);
		}
		
		if(vo.getOrderStatementId() == null){
			entity.setOrderStatementUuid(KeyGenUtils.newUuid());
			entity.setLangVer(Constants.LANG_VER);
			entity.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			entity.setCreateDate(DateTimeUtils.currentDate());
			entity.setCreateUser(user.getMemberId());
			entity.setStatus(Constants.STATEMENT_STATUS_01);
			entity.setSubmitPerson(user.getMemberId());
			orderStatementMapper.insertRecord(entity);
		}else{
			entity.setUpdateDate(DateTimeUtils.currentDate());
			entity.setUpdateUser(user.getMemberId());
			orderStatementMapper.updateRecordById(entity);
			OrderFeeItem feeItem = new OrderFeeItem();
			feeItem.setRelatId(entity.getOrderStatementId());
			feeItem.setRelatType("1");
			feeItemMapper.deleteRecords(feeItem);
		}
		OrderFeeItem feeItemPara = new OrderFeeItem();
		feeItemPara.setRelatId( vo.getOrderFeeId());
		feeItemPara.setRelatType("0");
		List<OrderFeeItem> itemList=feeItemMapper.queryByEntitys(feeItemPara);
		for(OrderFeeItem item : itemList){
			item.setFeeItemId(null);
			item.setLangVer(Constants.LANG_VER);
			item.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			item.setCreateDate(DateTimeUtils.currentDate());
			item.setCreateUser(user.getMemberId());
			item.setRelatType("1");
			item.setRelatId(entity.getOrderStatementId());
			feeItemMapper.insertRecord(item);
		}
			
	}
	
	/**
	 * 接受外部对账单信息
	 */
	@Override
	public String receiveOrderStatement(StatementSheetVO vo) {	
		if("".equals(vo.getUuid()))
			return "订单ID不能为空。";
		//查询订单
		Order order = orderMapper.findByUuid(vo.getUuid());
		if(order ==null)
			throw new OrderExecException("orderexecute.code.00014","订单不存在");
		if(OrderStatusEnum.STATUS_6.getCode().equals(order.getStatus())||OrderStatusEnum.STATUS_7.getCode().equals(order.getStatus()))
			throw new OrderExecException("orderexecute.code.00032","当前订单不允许此操作");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", order.getId());
		map.put("statementType", vo.getStatementType());
		//查询对账单
		List<Map<String, Object>> statementList = orderStatementMapper.queryRecords(map);
		if(statementList != null && statementList.size() > 0){
			for (Map<String, Object> statementMap : statementList) {
				//当状态不为已驳回时
				if(!Constants.STATEMENT_STATUS_20.equals(statementMap.get("status"))){
					return "对账单状态不对，无法生成对账单";
				}
			}
		}
		
		OrderStatementVO orderStateentVO = BeanConvertUtils.beanToBean(vo, OrderStatementVO.class);
		orderStateentVO.setSettlementPrice(vo.getPrice());
		try {
			Date billDate = DateUtils.parseDate(vo.getBillDate(), new String[]{"yyyy-MM-dd"});
			orderStateentVO.setBillDate(billDate);
		} catch (Exception e) {
			throw new OrderExecException("orderexecute.code.00090","提单日【billDate】格式不正确！"+vo.getBillDate());
		}
		
		orderStateentVO.setOrderId(order.getId());
		orderStateentVO.setOrderNo(order.getOrderNo());
		orderStateentVO.setOrderStatementUuid(KeyGenUtils.newUuid());
		orderStateentVO.setLangVer(Constants.LANG_VER);
		orderStateentVO.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		orderStateentVO.setCreateDate(DateTimeUtils.currentDate());
//		orderStateentVO.setCreateUser(user.getMemberId());
		orderStateentVO.setStatus(Constants.STATEMENT_STATUS_05);
//		orderStateentVO.setSubmitPerson(user.getMemberId());
		orderStateentVO.setBuyerCustomerId(order.getBuyerCustomerId());
		orderStateentVO.setSellerCustomerId(order.getSellerCustomerId());
		orderStateentVO.setOrderStatementNo(SequenceUtils.nextSequence("PRE"));
		orderStateentVO.setCustomerName(order.getBuyerCustomerName());
		
		orderStatementMapper.insertRecord(orderStateentVO);
		
		List<FeeVO> feeList = vo.getFeeList();
		for (FeeVO feeVO : feeList) {
			OrderFeeItem item = BeanConvertUtils.beanToBean(feeVO, OrderFeeItem.class);			
			item.setFeeItemId(null);
			item.setLangVer(Constants.LANG_VER);
			item.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			item.setCreateDate(DateTimeUtils.currentDate());
//			item.setCreateUser(user.getMemberId());
			item.setRelatType(Constants.FEE_TYPE_1);
			item.setRelatId(orderStateentVO.getOrderStatementId());
			
			feeItemMapper.insertRecord(item);
		}

		return null;
	}

	@Override
	public int queryIsTrigger(Long orderId) {
		return orderStatementMapper.queryIsTrigger(orderId);
	}
	
	@Override
	public double findDifferAmount(Long orderId) {
		return orderStatementMapper.findDifferAmount(orderId);
	}
	
	@Override
	public Map<String, Object> selectDataForTriggerResult(Long orderId){
		return  orderStatementMapper.selectDataForTriggerResult(orderId);	
	}

	@Override
	public int updateStatusById(OrderStatementVO vo) {
		return orderStatementMapper.updateStatusById(vo);
	}

	@Override
	public int editStatusById(OrderStatementVO vo) {
		return orderStatementMapper.editStatusById(vo);
	}
}
