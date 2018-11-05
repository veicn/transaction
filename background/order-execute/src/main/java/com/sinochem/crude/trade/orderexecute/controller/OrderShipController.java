package com.sinochem.crude.trade.orderexecute.controller;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.remote.OrderStatusService;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.commons.constants.OrderStatusEnum;
import com.sinochem.crude.trade.orderexecute.commons.constants.TransportStatusEnum;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderShip;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.crude.trade.orderexecute.service.OrderShipService;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
//@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
public class OrderShipController  {

	@Autowired
	private OrderShipService orderShipService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderStatusService orderStatusService;
	Log log = LogFactory.getLog(OrderShipController.class);
	
	/**
	 * 修改船
	 * @param user
	 * @param contractId
	 * @param quantity
	 * @return
	 */
	@RequestMapping(value=UrlMapping.EDIT_SHIPINFO, method=RequestMethod.POST)
	@RolesAccess({ "trade_executor"})
	@ResponseBody
	public ResultDatas<String> updateShip(CurrentUser user, 
			@RequestParam("name") String name,
			@RequestParam("vef") BigDecimal vef,
			@RequestParam("obq") BigDecimal obq,
			@RequestParam("orderShipId") Long orderShipId,
			@RequestParam("orderId") Long orderId) {
		ResultDatas<String> resultData = new ResultDatas<>();
		if (orderId==null||StringUtils.isBlank(name)||vef == null||obq == null) {
			resultData.setFail("缺少参数");
			resultData.setCode("orderexecute.code.00027");
		} else if(vef.compareTo(BigDecimal.ZERO) < 0 ||obq.compareTo(BigDecimal.ZERO) < 0) {
			resultData.setFail("数必须大于0");
			resultData.setCode("orderexecute.code.00028");
		}
		
		if(resultData.getStatus() != Result.SUCCESS) {
			return resultData;
		}
		
		Order order = orderService.findByPrimaryKey(orderId);
		
		if(OrderStatusEnum.STATUS_8.getCode().equals(order.getStatus())) {
			resultData.setFail("当前订单已取消");
			resultData.setCode("orderexecute.code.00208");
			return resultData;
		}
		if(OrderStatusEnum.STATUS_1_2.getCode().equals(order.getStatus())) {
			resultData.setFail("当前订单已经生成租船需求");
			resultData.setCode("ITSH532");
			return resultData;
		}
	
		try {	
			OrderShip orderShip = orderShipService.findByOrderId(orderId);
			if(orderShip != null){//修改			
				orderShip.setName(name);
				orderShip.setVef(vef);
				orderShip.setObq(obq);
				orderShip.setUpdateDate(DateTimeUtils.currentDate());
				orderShip.setUpdateUser(user.getMemberId());
				
				orderShipService.updateRecordById(orderShip);
				
			}else{//新增
				orderShip = new OrderShip();
				orderShip.setUuid(KeyGenUtils.newUuid());
				orderShip.setOrderId(orderId);
				orderShip.setOrderNo(order.getOrderNo());
				orderShip.setName(name);
				orderShip.setVef(vef);
				orderShip.setObq(obq);
				orderShip.setUpdateDate(DateTimeUtils.currentDate());
				orderShip.setCreateDate(DateTimeUtils.currentDate());
				orderShip.setUpdateUser(user.getMemberId());
				orderShip.setCreateUser(user.getMemberId());
				orderShip.setAliveFlag(Constants.ALIEVE_FLAG_YES);
				orderShip.setLangVer(Constants.LANG_VER);
				orderShipService.insertRecord(orderShip);				
								
				if (OrderStatusEnum.STATUS_1.getCode().equals(
						order.getStatus())) {
	
					// 订单状态更新
					Order changeStatusOrder = new Order();
					changeStatusOrder.setId(order.getId());
					changeStatusOrder.setStatus(OrderStatusEnum.STATUS_2.getCode());
					changeStatusOrder.setUpdateDate(DateTimeUtils.currentDate());
					changeStatusOrder.setUpdateUser(user.getMemberId());
					orderService.updateRecordById(changeStatusOrder);
	
					// 调用状态机
					orderStatusService.setOrderStatus(order.getTradeId(), TransportStatusEnum.ORDER_STATUS_2.getCode(), 
													Integer.parseInt(TransportStatusEnum.ORDER_STATUS_ITEM_2_1.getCode()), "", user.getMemberId());
				}
			}
		} catch (OrderExecException e) {
			log.error("saveShipLoading error", e);
			resultData.setStatus(Constants.EXCEPTION_STATUS);
			resultData.setCode(e.getCode());
			resultData.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("saveShipLoading error", e);
			resultData.setStatus(Constants.EXCEPTION_STATUS);
			resultData.setCode(Constants.EXCEPTION_CODE);
			resultData.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return resultData;
	}
	
}
