package com.sinochem.crude.trade.orderexecute.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.remote.OrderStatusService;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.commons.constants.OrderStatusEnum;
import com.sinochem.crude.trade.orderexecute.commons.constants.StatementStatusEnum;
import com.sinochem.crude.trade.orderexecute.dao.OrderFeeDissentMapper;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceSystem;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderFeeDissent;
import com.sinochem.crude.trade.orderexecute.domain.OrderPrice;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.InterfaceListVO;
import com.sinochem.crude.trade.orderexecute.model.OrderFeeDissentVO;
import com.sinochem.crude.trade.orderexecute.model.StatementMsgParamVO;
import com.sinochem.crude.trade.orderexecute.query.OrderFeeDissentQuery;
import com.sinochem.crude.trade.orderexecute.service.InterfaceSystemService;
import com.sinochem.crude.trade.orderexecute.service.OrderFeeDissentService;
import com.sinochem.crude.trade.orderexecute.service.OrderMsgRemindingService;
import com.sinochem.crude.trade.orderexecute.service.OrderPriceService;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.crude.trade.orderexecute.service.openapi.OutputService;
import com.sinochem.crude.trade.orderexecute.service.openapi.constants.TypeCodeEnum;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.PhysicalOperationSaveInfoVO;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.PhysicalOperationStatusVO;

@Service
public class OrderFeeDissentServiceImpl implements OrderFeeDissentService {
	@Autowired
	private OrderFeeDissentMapper orderFeeDissentMapper;
	
	@Autowired
	private OutputService outputService;	
	
	@Autowired
	private OrderStatusService orderStatusService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private InterfaceSystemService interfaceSystemService;
	
	@Autowired
	private OrderMsgRemindingService msgRemindingService;
	
	@Autowired
	private OrderPriceService orderPriceService;
	
	@Resource(name="orderExecuteServer")
	private URLBroker orderExecuteServer;
	
	Log log = LogFactory.getLog(OrderStatementServiceImpl.class);
	
	public OrderFeeDissentMapper getMapper(){
		return orderFeeDissentMapper;
	} 
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderFeeDissent orderfeedissent){
		 return orderFeeDissentMapper.insertRecord(orderfeedissent);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long feeDissentId) throws BizException{
		if (feeDissentId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderFeeDissentMapper.deleteById(feeDissentId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderFeeDissent  record){
    	return orderFeeDissentMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderFeeDissent orderFeeDissent) throws BizException{
		if ( orderFeeDissent.getFeeDissentId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","feeDissentId 为空，不能修改","feeDissentId");
		}
		return orderFeeDissentMapper.updateRecordById(orderFeeDissent);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderFeeDissentMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderFeeDissent orderFeeDissent){
		return orderFeeDissentMapper.updateRecords(orderFeeDissent.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderFeeDissent findByPrimaryKey(Long feeDissentId){
		return  orderFeeDissentMapper.findByPrimaryKey(feeDissentId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderFeeDissent findByUuid(String uuid){
		return  orderFeeDissentMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderFeeDissent> queryByEntitys(OrderFeeDissent orderFeeDissent){
		return  orderFeeDissentMapper.queryByEntitys(orderFeeDissent);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderFeeDissentMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderFeeDissentMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderFeeDissentMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 取得异议留言
	 */
	@Override
	public List<Map<String, Object>> getDissentList(OrderFeeDissentQuery query){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", query.getOrderId());
		map.put("dissentType", query.getDissentType());
		
		return orderFeeDissentMapper.queryRecords(map);
	}

	/**
	 * 保存留言
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String saveReplyContent(OrderFeeDissentVO vo, CurrentUser user) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = DateTimeUtils.currentDate();
		
		OrderFeeDissent entity = new OrderFeeDissent();
		entity.setFeeDissentId(vo.getFeeDissentId());
		entity.setIsReply("1");
		entity.setReplyContent(vo.getReplyContent());
		entity.setUpdateDate(date);
		entity.setUpdateUser(user.getMemberId());
		
		orderFeeDissentMapper.updateRecordById(entity);
		
		return sdf.format(date);
	}
	
	/**
	 * 保存确认信息
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String saveDissentContent(OrderFeeDissentVO vo, CurrentUser user) {
		Map<String, Object> map = new HashMap<String, Object>();
		int isPass = vo.getIsPass();
		Map<String,Object> info = orderFeeDissentMapper.getStatementInfo(vo.getOrderStatementId());
		if(info == null) {
			return "没有找到对账单信息";
		} else if(!"05".equals(String.valueOf(info.get("status")))) {
			return "对账单不是待确认状态";
		}
		
		Long orderId = (Long) info.get("orderId");
		if(orderId == null) {
			return "orderId不能为空";
		} 
		
		Order order = orderService.findByPrimaryKey(orderId);
		String statementType = String.valueOf(info.get("statementType"));
		
		if(isPass == 1) {
			map.put("status", "10"); //10已确认
			
			if(Constants.STATEMENT_TYPE_I.equals(statementType)){
				//对账确认后调用状态机（已结算）
				log.info("saveDissentContent 确认结算状态机开始");
				try {
					orderStatusService.setOrderStatus(order.getTradeId(), 
							StatementStatusEnum.ORDER_STATUS_3.getCode(), 
							Integer.parseInt(StatementStatusEnum.ORDER_STATUS_ITEM_3_9.getCode()), 
							"", 
							user.getMemberId());
				} catch (Exception e) {
					log.info("saveDissentContent 确认结算状态机出错");
					return "saveDissentContent 确认结算状态机出错";
				}
				log.info("saveDissentContent 确认结算状态机结束");
				//正式 更改订单状态
				order.setStatus(OrderStatusEnum.STATUS_6.getCode());
				orderService.updateRecordById(order);
			}
			
			String dataJson = String.valueOf(info.get("dataJson"));
			if(StringUtils.isNotBlank(dataJson)&& !"null".equals(dataJson)){
				
				PhysicalOperationSaveInfoVO physicalOperationSaveInfoVO = new PhysicalOperationSaveInfoVO();
				physicalOperationSaveInfoVO.setId(order.getUuid());
				if(Constants.STATEMENT_TYPE_S.equals(statementType)){
					physicalOperationSaveInfoVO.setType_code(TypeCodeEnum.EST);//临时
				}else{
					physicalOperationSaveInfoVO.setType_code(TypeCodeEnum.FINAL);//最终
				}	
				physicalOperationSaveInfoVO.setStatus(0);
				physicalOperationSaveInfoVO.setValues(dataJson);

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
		} else {
			map.put("status", "20"); //20已驳回
			
			OrderFeeDissent entity = new OrderFeeDissent();
			entity.setOrderId(vo.getOrderId());
			entity.setDissentType(vo.getDissentType());
			entity.setBillId(vo.getOrderStatementId());
			entity.setDissentContent(vo.getDissentContent());
			entity.setDissentTime(DateTimeUtils.currentDate());
			entity.setDissentPerson(user.getMemberId());
			
			entity.setLangVer(Constants.LANG_VER);
			entity.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			entity.setCreateDate(DateTimeUtils.currentDate());
			entity.setCreateUser(user.getMemberId());
			entity.setUpdateDate(DateTimeUtils.currentDate());
			entity.setUpdateUser(user.getMemberId());
			
			orderFeeDissentMapper.insertRecord(entity);
			
			// 驳回时调用外部接口
			if(info.get("sysName") != null) {
				InterfaceListVO ifl = new InterfaceListVO();
				ifl.setBookingId(String.valueOf(info.get("sysName")));
				
				PhysicalOperationStatusVO pos = new PhysicalOperationStatusVO();
				pos.setId(String.valueOf(info.get("uuid")));
				if("1".equals(statementType)) {
					pos.setType_code(TypeCodeEnum.EST);
				} else {
					pos.setType_code(TypeCodeEnum.FINAL);
				}
				pos.setStatus(1);
				
				outputService.physicalOperationStatusUpdate(ifl, pos);
			}
		}
		
		// 修改对账单状态
		map.put("orderStatementId", vo.getOrderStatementId());
		map.put("confirmPerson", user.getMemberId());
		map.put("updateDate", DateTimeUtils.currentDate());
		map.put("updateUser", user.getMemberId());
		orderFeeDissentMapper.updateOrderStatementStatus(map);
		
		//保存消息提醒数据
		String orderStatementNo = String.valueOf(info.get("orderStatementNo"));
		String status = (String) map.get("status");
		StatementStatusEnum statusEnum = null;
		if("10".equals(status)) {//确认
			OrderPrice orderPrice = orderPriceService.findByOrderId(orderId);
			Date payDate = orderPrice.getPayDate();
			Map<String, Object> msgParams = new HashMap<>();
			
			msgParams.put("statementNo", orderStatementNo);
			msgParams.put("orderNo", order.getOrderNo());
			
			if(Constants.STATEMENT_TYPE_S.equals(statementType)) {//预估对账单
				msgParams.put("statementDetailLink", orderExecuteServer.get("/sellerCenter/orderStatement/infoPre.htm")+"?uuid="+order.getUuid());
				msgRemindingService.createUploadPaymentVoucherReminding(orderId, order.getBuyerPersonId(), payDate,false, msgParams);
			}else {//正式对账单
				msgParams.put("statementDetailLink", orderExecuteServer.get("/sellerCenter/orderStatement/infoFin.htm")+"?uuid="+order.getUuid());
				msgRemindingService.createUploadPaymentVoucherReminding(orderId, order.getBuyerPersonId(), payDate,true, msgParams);
				msgRemindingService.createCheckReceiptReminding(orderId, order.getSellerPersonId(), payDate, msgParams);
			}
			
			statusEnum = StatementStatusEnum.ORDER_STATUS_ITEM_3_2;
		}else if("20".equals(status)) {//驳回
			statusEnum = StatementStatusEnum.ORDER_STATUS_ITEM_3_3;
		}
		try{
			//发送消息通知
			StatementMsgParamVO templateParams = new StatementMsgParamVO();
			templateParams.setEpMemberName(order.getBuyerCustomerName());
			templateParams.setStatementNo(orderStatementNo);
			templateParams.setReceiverId(order.getSellerPersonId());
			templateParams.setCustomerId(order.getSellerCustomerId());
			templateParams.setOrderId(order.getId());
			
			String statementDetailLink = "";
			if("2".equals(statementType)) {
				statementDetailLink = "sellerCenter/orderStatement/infoFin.htm";
			}else {
				statementDetailLink = "sellerCenter/orderStatement/infoPre.htm";
			}
			templateParams.setStatementDetailLink(orderExecuteServer.get(statementDetailLink)+"?orderStatementUuid="+info.get("statementUuid"));
			
			msgRemindingService.sendStatementProcessMsg(statusEnum, templateParams);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
}
