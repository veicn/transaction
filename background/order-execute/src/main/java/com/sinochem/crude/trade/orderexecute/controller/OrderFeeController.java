package com.sinochem.crude.trade.orderexecute.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.ValueSet;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.commons.constants.ValueSetGroupConstant;
import com.sinochem.crude.trade.orderexecute.commons.utils.ValueSetUtil;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderDetailView;
import com.sinochem.crude.trade.orderexecute.domain.OrderFee;
import com.sinochem.crude.trade.orderexecute.domain.OrderFeeItem;
import com.sinochem.crude.trade.orderexecute.domain.OrderShip;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.OrderFeeVO;
import com.sinochem.crude.trade.orderexecute.query.OrderFeeQuery;
import com.sinochem.crude.trade.orderexecute.service.OrderDetailViewService;
import com.sinochem.crude.trade.orderexecute.service.OrderFeeItemService;
import com.sinochem.crude.trade.orderexecute.service.OrderFeeService;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.crude.trade.orderexecute.service.OrderShipService;
import com.sinochem.crude.trade.orderexecute.service.TradeSubjectService;
import com.sinochem.it.b2b.member.access.RolesAccess;

@Controller
public class OrderFeeController {

	@Autowired
	private OrderFeeService orderFeeService;
	@Autowired
	private OrderDetailViewService orderDetailViewService;
	@Autowired
	private OrderFeeItemService orderFeeItemService;
	@Autowired
	private TradeSubjectService tradeSubjectService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderShipService orderShipService;
	
	private static final Log log = LogFactory.getLog(OrderFeeController.class);

	/**
	 * 查询费用信息
	 * 
	 * @param vo
	 * @return
	 * @exception
	 */
	@RequestMapping(value = UrlMapping.FEE_INFO)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public String selectOrderFeeItem(OrderFeeQuery feeQuery, ModelMap modelMap ) {
		
		OrderDetailView orderDetail = orderDetailViewService.findByUuid(feeQuery.getUuid());
		
		if(orderDetail == null )
			throw new OrderExecException("404");
		// 查询主表订单ID
		OrderFee orderFee = new OrderFee();
		orderFee.setOrderId(orderDetail.getOrderId());
		orderFee.setFeeType("0");
		
		//查询该订单是否已有生效的费用信息
		List<OrderFee> feeList = orderFeeService.queryByEntitys(orderFee);
		if(CollectionUtils.isNotEmpty(feeList)){
			orderFee = feeList.get(0);
			Map<String, Object>  paraFeeItem = new HashMap<>(); 
			paraFeeItem.put("relatId", orderFee.getOrderFeeId());
			paraFeeItem.put("relatType","0");
			List<OrderFeeItem> orderFeeItem = orderFeeItemService.queryRecords(paraFeeItem);
			
			//国际化转换
			ValueSet i18nValueSet = ValueSetUtil.instance(ValueSetGroupConstant.SUBJECT_LIST);
			for(OrderFeeItem data : orderFeeItem) {
				String code = data.getSubjectCode();
				String name = data.getSubjectName();
				
				data.setSubjectName(i18nValueSet.getByCode(code, name).getValue());
			}
			
			modelMap.put("orderFee", orderFee);
			modelMap.put("orderFeeItem", orderFeeItem);
			modelMap.put("quantity", orderDetail.getQuantity());
			modelMap.put("isNew","0");
			
		} else {
			Map<String, Object>  paraFeeItem = new HashMap<>(); 
			paraFeeItem.put("productType", orderDetail.getTradeCategory());
			List<Map<String, Object>> orderFeeItem = tradeSubjectService.queryRecords(paraFeeItem);
			for(Map<String, Object> editOrderFeeItem : orderFeeItem){
				editOrderFeeItem.put("quantity", orderDetail.getQuantity());
				editOrderFeeItem.put("isAgent", '1');
			}
			orderFee.setOil(orderDetail.getTradeCategoryDesc());
			orderFee.setOrderNo(orderDetail.getOrderNo());
			orderFee.setOrderId(orderDetail.getOrderId());
			orderFee.setAgentNo(orderDetail.getContractNo());
			orderFee.setBreed(orderDetail.getEnName());
			orderFee.setBuyerCustomerName(orderDetail.getBuyerCustomerName());
			OrderShip orderShip = orderShipService.findByOrderNo(orderFee.getOrderNo());
			if(orderShip != null){
				orderFee.setShipName(orderShip.getName());
			}
			orderFee.setQuantity(orderDetail.getQuantity());
			orderFee.setSpec(orderDetail.getSpec());
			
			//国际化转换
			ValueSet i18nValueSet = ValueSetUtil.instance(ValueSetGroupConstant.SUBJECT_LIST);
			for(Map<String, Object> data : orderFeeItem) {
				String code = String.valueOf(data.get("subjectCode"));
				String name = String.valueOf(data.get("subjectName"));
				
				data.put("subjectName", i18nValueSet.getByCode(code, name).getValue());
			}
			
			modelMap.put("orderFee", orderFee);
			modelMap.put("orderFeeItem", orderFeeItem);
			modelMap.put("quantity", orderDetail.getQuantity());
			modelMap.put("isNew","1");
		}

		return "sellerCenter/orderFee/orderFeeInfo";
	}

	/***
	 * 新增或者更新费用信息
	 * 
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.FEE_SAVE, method = RequestMethod.POST)
	@RolesAccess({ "trade_executor"})
	public Result saveOrderFee( OrderFeeVO vo, CurrentUser user) {
		log.info("--->" + vo.toString());
		Long orderId = vo.getOrderId();
		Result res = new Result();
		try {
			if(vo.getTotalFee() == null){
				vo.setTotalFee(BigDecimal.ZERO);
			}
			int totalFeePrecision = vo.getTotalFee().precision();
			if(totalFeePrecision > 16){
				throw new OrderExecException("orderexecute.code.00199","请确认您输入的是有效金额");
			}
			Order order = orderService.findByPrimaryKey(orderId);
			if(order == null )
				throw new OrderExecException("orderexecute.code.00018","未查询到有效订单！");
			// 查询主表订单ID
			OrderFee orderFee = new OrderFee();
			orderFee.setOrderId(orderId);
			orderFee.setFeeType("0");
			
			//查询该订单是否已有生效的费用信息
			List<OrderFee> feeList = orderFeeService.queryByEntitys(orderFee);
			if(CollectionUtils.isNotEmpty(feeList)){
				vo.setOrderFeeId(feeList.get(0).getOrderFeeId());
			}
			orderFeeService.saveOrderFee(vo,user);
		} catch (OrderExecException e) {
			log.error("savePallet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("savePallet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}

}
