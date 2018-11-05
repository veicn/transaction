package com.sinochem.crude.trade.orderexecute.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderFeeItem;
import com.sinochem.crude.trade.orderexecute.domain.OrderSettlement;
import com.sinochem.crude.trade.orderexecute.domain.OrderStatement;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.OrderSettlementVO;
import com.sinochem.crude.trade.orderexecute.model.OrderStatementVO;
import com.sinochem.crude.trade.orderexecute.query.OrderSettlementQuery;
import com.sinochem.crude.trade.orderexecute.query.OrderStatementQuery;
import com.sinochem.crude.trade.orderexecute.service.OrderFeeItemService;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.crude.trade.orderexecute.service.OrderSettlementService;
import com.sinochem.crude.trade.orderexecute.service.OrderStatementService;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
public class OrderSettlementController  {
	
	@Autowired
	private OrderStatementService orderStatementService;
	
	@Autowired
	private OrderSettlementService orderSettlementService;

	@Autowired	
	private OrderFeeItemService orderFeeItemService;

	@Autowired	
	private OrderService orderService;
	
	
	Log log = LogFactory.getLog(OrderSettlementController.class);
	/**
	 * 卖家预估结算信息
	 * @param vo
	 * @param user
	 * @param modelMap
	 */
	@RequestMapping(value = UrlMapping.S_SETTLEMENT_INFO_PRE)
	public void selectinformation(OrderSettlementVO vo, CurrentUser user,
			ModelMap modelMap) {
		
		Order order = new Order();
		OrderSettlement settlement = new OrderSettlement();
		OrderStatement statement = new OrderStatement();
		if(!"" .equals(vo.getUuid())&& vo.getUuid()!=null){
			order = orderService.findByUuid(vo.getUuid());
		}else if(vo.getOrderSettlementUuid() !=null && !"".equals(vo.getOrderSettlementUuid())){
			settlement = orderSettlementService.findByUuid(vo.getOrderSettlementUuid());
			order = orderService.findByPrimaryKey(settlement.getOrderId());
		}else if(vo.getOrderStatementUuid() !=null && !"".equals(vo.getOrderStatementUuid())){
			statement = orderStatementService.findByUuid(vo.getOrderStatementUuid());
			order = orderService.findByPrimaryKey(statement.getOrderId());
		}else{
			throw new OrderExecException("orderexecute.code.00019","订单ID或对账单ID或结算单ID为空");
		}	
		if(order == null){
			throw new OrderExecException("orderexecute.code.00014","订单不存在");
		}
		Long orderId = order.getId();
		
		//订单相关信息查询
		Map<String, Object> inforData = orderStatementService.selectinformation(orderId);
		
		Map<String,Object> settlementMap = new HashMap<String,Object>();
		settlementMap.put("orderId", orderId);
		settlementMap.put("orderStettlementUuid", vo.getOrderSettlementUuid());
		settlementMap.put("settlementType", Constants.STATEMENT_TYPE_S);				
		//查询结算单
		List<Map<String, Object>> settlementList = orderSettlementService.queryRecords(settlementMap);
		
		Map<String, Object> stateData = new HashMap<String, Object>();
		List<OrderFeeItem> feeDataList = new ArrayList<OrderFeeItem>();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (settlementList != null && settlementList.size() > 0) {
			//结算单信息
			stateData = settlementList.get(0);
			//查询结算单对应费用信息
			paraMap.put("relatId", stateData.get("orderSettlementId"));
			paraMap.put("relatType",Constants.FEE_TYPE_2);
			feeDataList = orderFeeItemService.queryRecords(paraMap);	
		}else{
			//没有结算单给个状态为1，通过对账单生成
			modelMap.put("isSettlementInfo", "1");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", orderId);
			//查询是否已经生成对账单
			map.put("statementType", Constants.STATEMENT_TYPE_S);
			map.put("status",Constants.STATEMENT_STATUS_10);
			map.put("orderStatementUuid", statement.getOrderStatementUuid());
			List<Map<String, Object>> statementList = orderStatementService.queryRecords(map);

			if (statementList != null && statementList.size() > 0) {
				stateData = statementList.get(0);
				//查询对账单对应费用信息
				paraMap.put("relatId", stateData.get("orderStatementId"));
				paraMap.put("relatType",Constants.FEE_TYPE_1);
				feeDataList = orderFeeItemService.queryRecords(paraMap);			
			}else{
				throw new OrderExecException("orderexecute.code.00020","请先生成对账单");
			} 
		}
		stateData.put("uuid", order.getUuid());
		modelMap.put("stateData", stateData);
		modelMap.put("inforData", inforData);
		modelMap.put("feeData", feeDataList);

	}
	
	/**
	 * 卖家正式结算信息
	 * @param vo
	 * @param user
	 * @param modelMap
	 */
	@RequestMapping(value = UrlMapping.S_SETTLEMENT_INFO_FIN)
	public void selectinformationFin(OrderSettlementVO vo, CurrentUser user,
			ModelMap modelMap) {

		Order order = new Order();
		OrderSettlement settlement = new OrderSettlement();
		OrderStatement statement = new OrderStatement();
		if(!"" .equals(vo.getUuid())&& vo.getUuid()!=null){
			order = orderService.findByUuid(vo.getUuid());
		}else if(vo.getOrderSettlementUuid() !=null && !"".equals(vo.getOrderSettlementUuid())){
			settlement = orderSettlementService.findByUuid(vo.getOrderSettlementUuid());
			order = orderService.findByPrimaryKey(settlement.getOrderId());
		}else if(vo.getOrderStatementUuid() !=null && !"".equals(vo.getOrderStatementUuid())){
			statement = orderStatementService.findByUuid(vo.getOrderStatementUuid());
			order = orderService.findByPrimaryKey(statement.getOrderId());
		}else{
			throw new OrderExecException("orderexecute.code.00019","订单ID或对账单ID或结算单ID为空");
		}	
		if(order == null){
			throw new OrderExecException("orderexecute.code.00014","订单不存在");
		}
		Long orderId = order.getId();
		
		//订单相关信息查询
		Map<String, Object> inforData = orderStatementService.selectinformation(orderId);
		
		Map<String,Object> settlementMap = new HashMap<String,Object>();
		settlementMap.put("orderId", orderId);
		settlementMap.put("orderStettlementUuid", vo.getOrderSettlementUuid());
		settlementMap.put("settlementType", Constants.SETTLEMENT_TYPE_I);				
		//查询结算单
		List<Map<String, Object>> settlementList = orderSettlementService.queryRecords(settlementMap);
		
		Map<String, Object> stateData = new HashMap<String, Object>();
		List<OrderFeeItem> feeDataList = new ArrayList<OrderFeeItem>();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (settlementList != null && settlementList.size() > 0) {
			//结算单信息
			stateData = settlementList.get(0);
			//查询结算单对应费用信息
			paraMap.put("relatId", stateData.get("orderSettlementId"));
			paraMap.put("relatType",Constants.FEE_TYPE_2);
			feeDataList = orderFeeItemService.queryRecords(paraMap);	
		}else{
			//没有结算单给个状态为1，通过对账单生成
			modelMap.put("isSettlementInfo", "1");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", orderId);
			//查询是否已经生成对账单
			map.put("status",Constants.STATEMENT_STATUS_10);
			map.put("statementType", Constants.STATEMENT_TYPE_I);
			map.put("orderStatementUuid", statement.getOrderStatementUuid());
			List<Map<String, Object>> statementList = orderStatementService.queryRecords(map);

			if (statementList != null && statementList.size() > 0) {
				stateData = statementList.get(0);
				//查询对账单对应费用信息
				paraMap.put("relatId", stateData.get("orderStatementId"));
				paraMap.put("relatType",Constants.FEE_TYPE_1);
				feeDataList = orderFeeItemService.queryRecords(paraMap);			
			}else{
				throw new OrderExecException("orderexecute.code.00020","请先生成对账单");
			} 
		}
		stateData.put("uuid", order.getUuid());
		modelMap.put("stateData", stateData);
		modelMap.put("inforData", inforData);
		modelMap.put("feeData", feeDataList);

	}

	/**
	 * 新增结算信息 @param vo @return @exception
	 */
	@RequestMapping(value = UrlMapping.SETTLEMENT_SAVE_PRE)
	public Result insertRecord(OrderStatementVO vo, CurrentUser user) {
		Result res = new Result();
		try {
			if(vo.getUuid() == null){
				throw new OrderExecException("orderexecute.code.00021","订单UUID不能为空");
			}
			if(vo.getOrderStatementId() ==null)
				throw new OrderExecException("orderexecute.code.00022","对账单ID不能为空 ");
			Order order = orderService.findByUuid(vo.getUuid());
			if(order == null)
				throw new OrderExecException("orderexecute.code.00023","无效订单");
			
			OrderStatement statement = orderStatementService.findByPrimaryKey(vo.getOrderStatementId());
			if(statement == null)
				throw new OrderExecException("orderexecute.code.00024","未找到相应对账单");
			if(!Constants.STATEMENT_STATUS_10.equals(statement.getStatus()))
				throw new OrderExecException("orderexecute.code.00025","当前对账单不允许创建结算单");
				
			//生成结算单
			orderSettlementService.saveSettlement(vo,user);
			
		} catch (OrderExecException e) {
			res.setStatus(Result.EEROR);
			res.setMessage(e.getMessage());
			res.setCode(e.getCode());
		} catch (Exception e) {
			log.error(e);
			res.setStatus(Result.EEROR);
			res.setMessage("结算单生成失败");
			res.setCode("orderexecute.code.00026");
		}
		return res;
	}
	
	/**
	 * 查询卖家预估结算信息分页列表 @param vo @return @exception
	 */
	@RequestMapping(value = UrlMapping.S_SETTLEMENT_LIST_PRE)
	public void selectPageSellerPre(
			CurrentUser user, OrderSettlementQuery query, ModelMap modelMap) {
		
		query.setSettlementType(Constants.STATEMENT_TYPE_S);
		query.setSellerCustomerId(user.getEpMemberId());
		Page<Map<String, Object>> pageResult = orderSettlementService.queryRecords(query.getQueryParam(), query.getPageInfo());
		
		QueryBase queryBase = (QueryBase) query;
		queryBase.setTotalItem(pageResult.getTotal());
		
		modelMap.put("datas", pageResult.getResult());
		modelMap.put("query", queryBase);

	}
	/**
	 * 查询卖家正式结算信息分页列表 @param vo @return @exception
	 */
	@RequestMapping(value = UrlMapping.S_SETTLEMENT_LIST_FIN)
	public void selectPageSellerFin(
			CurrentUser user, OrderSettlementQuery query, ModelMap modelMap) {
		
		query.setSettlementType(Constants.STATEMENT_TYPE_I);
		query.setSellerCustomerId(user.getEpMemberId());
		Page<Map<String, Object>> pageResult = orderSettlementService.queryRecords(query.getQueryParam(), query.getPageInfo());
		
		QueryBase queryBase = (QueryBase) query;
		queryBase.setTotalItem(pageResult.getTotal());
		
		modelMap.put("datas", pageResult.getResult());
		modelMap.put("query", queryBase);

	}
	
	
	/**
	 * 买家预估结算信息
	 * @param vo
	 * @param user
	 * @param modelMap
	 */
	@RequestMapping(value = UrlMapping.B_SETTLEMENT_INFO_PRE)
	public void selectinformationBuyer(OrderSettlementVO vo, CurrentUser user,
			ModelMap modelMap) {
		
		Order order = new Order();
		OrderSettlement settlement = new OrderSettlement();
		OrderStatement statement = new OrderStatement();
		if(!"" .equals(vo.getUuid())&& vo.getUuid()!=null){
			order = orderService.findByUuid(vo.getUuid());
		}else if(vo.getOrderSettlementUuid() !=null && !"".equals(vo.getOrderSettlementUuid())){
			settlement = orderSettlementService.findByUuid(vo.getOrderSettlementUuid());
			order = orderService.findByPrimaryKey(settlement.getOrderId());
		}else if(vo.getOrderStatementUuid() !=null && !"".equals(vo.getOrderStatementUuid())){
			statement = orderStatementService.findByUuid(vo.getOrderStatementUuid());
			order = orderService.findByPrimaryKey(statement.getOrderId());
		}else{
			throw new OrderExecException("orderexecute.code.00019","订单ID或对账单ID或结算单ID为空");
		}	
		if(order == null){
			throw new OrderExecException("orderexecute.code.00014","订单不存在");
		}
		Long orderId = order.getId();
		
		//订单相关信息查询
		Map<String, Object> inforData = orderStatementService.selectinformation(orderId);
		
		Map<String,Object> settlementMap = new HashMap<String,Object>();
		settlementMap.put("orderId", orderId);
		settlementMap.put("orderStettlementUuid", vo.getOrderSettlementUuid());
		settlementMap.put("settlementType", Constants.STATEMENT_TYPE_S);				
		//查询结算单
		List<Map<String, Object>> settlementList = orderSettlementService.queryRecords(settlementMap);
		
		Map<String, Object> stateData = new HashMap<String, Object>();
		List<OrderFeeItem> feeDataList = new ArrayList<OrderFeeItem>();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (settlementList != null && settlementList.size() > 0) {
			//结算单信息
			stateData = settlementList.get(0);
			//查询结算单对应费用信息
			paraMap.put("relatId", stateData.get("orderSettlementId"));
			paraMap.put("relatType",Constants.FEE_TYPE_2);
			feeDataList = orderFeeItemService.queryRecords(paraMap);	
		}else{
			//没有结算单给个状态为1，通过对账单生成
			modelMap.put("isSettlementInfo", "1");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", orderId);
			//查询是否已经生成对账单
			map.put("statementType", Constants.STATEMENT_TYPE_S);
			map.put("orderStatementUuid", statement.getOrderStatementUuid());
			List<Map<String, Object>> statementList = orderStatementService.queryRecords(map);

			if (statementList != null && statementList.size() > 0) {
				stateData = statementList.get(0);
				//查询对账单对应费用信息
				paraMap.put("relatId", stateData.get("orderStatementId"));
				paraMap.put("relatType",Constants.FEE_TYPE_1);
				feeDataList = orderFeeItemService.queryRecords(paraMap);			
			}else{
				throw new OrderExecException("orderexecute.code.00020","请先生成对账单");
			} 
		}
		modelMap.put("stateData", stateData);
		modelMap.put("inforData", inforData);
		modelMap.put("feeData", feeDataList);

	}
	
	/**
	 * 买家正式结算信息
	 * @param vo
	 * @param user
	 * @param modelMap
	 */
	@RequestMapping(value = UrlMapping.B_SETTLEMENT_INFO_FIN)
	public void selectinformationBuyerFin(OrderSettlementVO vo, CurrentUser user,
			ModelMap modelMap) {
		Order order = new Order();
		OrderSettlement settlement = new OrderSettlement();
		OrderStatement statement = new OrderStatement();
		if(!"" .equals(vo.getUuid())&& vo.getUuid()!=null){
			order = orderService.findByUuid(vo.getUuid());
		}else if(vo.getOrderSettlementUuid() !=null && !"".equals(vo.getOrderSettlementUuid())){
			settlement = orderSettlementService.findByUuid(vo.getOrderSettlementUuid());
			order = orderService.findByPrimaryKey(settlement.getOrderId());
		}else if(vo.getOrderStatementUuid() !=null && !"".equals(vo.getOrderStatementUuid())){
			statement = orderStatementService.findByUuid(vo.getOrderStatementUuid());
			order = orderService.findByPrimaryKey(statement.getOrderId());
		}else{
			throw new OrderExecException("orderexecute.code.00019","订单ID或对账单ID或结算单ID为空");
		}	
		if(order == null){
			throw new OrderExecException("orderexecute.code.00014","订单不存在");
		}
		Long orderId = order.getId();
		
		//订单相关信息查询
		Map<String, Object> inforData = orderStatementService.selectinformation(orderId);
		
		Map<String,Object> settlementMap = new HashMap<String,Object>();
		settlementMap.put("orderId", orderId);
		settlementMap.put("orderStettlementUuid", vo.getOrderSettlementUuid());
		settlementMap.put("settlementType", Constants.STATEMENT_TYPE_I);				
		//查询结算单
		List<Map<String, Object>> settlementList = orderSettlementService.queryRecords(settlementMap);
		
		Map<String, Object> stateData = new HashMap<String, Object>();
		List<OrderFeeItem> feeDataList = new ArrayList<OrderFeeItem>();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (settlementList != null && settlementList.size() > 0) {
			//结算单信息
			stateData = settlementList.get(0);
			//查询结算单对应费用信息
			paraMap.put("relatId", stateData.get("orderSettlementId"));
			paraMap.put("relatType",Constants.FEE_TYPE_2);
			feeDataList = orderFeeItemService.queryRecords(paraMap);	
		}else{
			//没有结算单给个状态为1，通过对账单生成
			modelMap.put("isSettlementInfo", "1");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", orderId);
			//查询是否已经生成对账单
			map.put("statementType", Constants.STATEMENT_TYPE_S);
			map.put("orderStatementUuid", statement.getOrderStatementUuid());
			List<Map<String, Object>> statementList = orderStatementService.queryRecords(map);

			if (statementList != null && statementList.size() > 0) {
				stateData = statementList.get(0);
				//查询对账单对应费用信息
				paraMap.put("relatId", stateData.get("orderStatementId"));
				paraMap.put("relatType",Constants.FEE_TYPE_1);
				feeDataList = orderFeeItemService.queryRecords(paraMap);			
			}else{
				throw new OrderExecException("orderexecute.code.00020","请先生成对账单");
			} 
		}
		modelMap.put("stateData", stateData);
		modelMap.put("inforData", inforData);
		modelMap.put("feeData", feeDataList);

	}
	
	/**
	 * 查询买家预估结算信息分页列表 @param vo @return @exception
	 */
	@RequestMapping(value = UrlMapping.B_SETTLEMENT_LIST_PRE)
	public void selectPageBuyerPre(
			CurrentUser user, OrderSettlementQuery query, ModelMap modelMap) {
		
		query.setSettlementType(Constants.STATEMENT_TYPE_S);
		query.setBuyerCustomerId(user.getEpMemberId());
		Page<Map<String, Object>> pageResult = orderSettlementService.queryRecords(query.getQueryParam(), query.getPageInfo());
		
		QueryBase queryBase = (QueryBase) query;
		queryBase.setTotalItem(pageResult.getTotal());
		
		modelMap.put("datas", pageResult.getResult());
		modelMap.put("query", queryBase);

	}
	/**
	 * 查询买家正式结算信息分页列表 @param vo @return @exception
	 */
	@RequestMapping(value = UrlMapping.B_SETTLEMENT_LIST_FIN)
	public void selectPageBuyerFin(
			CurrentUser user, OrderSettlementQuery query, ModelMap modelMap) {
		
		query.setSettlementType(Constants.STATEMENT_TYPE_I);
		query.setBuyerCustomerId(user.getEpMemberId());
		Page<Map<String, Object>> pageResult = orderSettlementService.queryRecords(query.getQueryParam(), query.getPageInfo());
		
		QueryBase queryBase = (QueryBase) query;
		queryBase.setTotalItem(pageResult.getTotal());
		
		modelMap.put("datas", pageResult.getResult());
		modelMap.put("query", queryBase);

	}
	

	
	/**
	 * 查询对账信息分页列表 
	 * @param vo 
	 * @return 
	 */
	@RequestMapping(value = UrlMapping.SETTLEMENT_LIST)
	public void selectAccountsPage(CurrentUser user, OrderStatementQuery query, ModelMap modelMap,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "30") int limit) {

		// 查询条件中，加入用户的企业id
		query.setEpMemberId(user.getEpMemberId());

		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(page);
		pageInfo.setPageSize(limit);

		modelMap.put("datas", orderSettlementService.selectAccountsPage(query, pageInfo));
		modelMap.put("user", user);
		modelMap.put("query", query);

	}
}
