package com.sinochem.crude.trade.orderexecute.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eyeieye.melody.web.locale.VisitorLocale;
import com.github.pagehelper.Page;
import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.commons.constants.OrderStatusEnum;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderBO;
import com.sinochem.crude.trade.orderexecute.domain.OrderDetailView;
import com.sinochem.crude.trade.orderexecute.domain.OrderGoods;
import com.sinochem.crude.trade.orderexecute.domain.OrderParty;
import com.sinochem.crude.trade.orderexecute.domain.OrderPrice;
import com.sinochem.crude.trade.orderexecute.domain.OrderSettlement;
import com.sinochem.crude.trade.orderexecute.domain.OrderShip;
import com.sinochem.crude.trade.orderexecute.domain.OrderShipLoadinginfo;
import com.sinochem.crude.trade.orderexecute.domain.OrderShipUnloadinginfo;
import com.sinochem.crude.trade.orderexecute.domain.OrderStatement;
import com.sinochem.crude.trade.orderexecute.domain.OrderTransport;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.OrderDetailDTO;
import com.sinochem.crude.trade.orderexecute.model.OrderListDTO;
import com.sinochem.crude.trade.orderexecute.model.OrderVO;
import com.sinochem.crude.trade.orderexecute.query.OrderQuery;
import com.sinochem.crude.trade.orderexecute.service.OrderButtonService;
import com.sinochem.crude.trade.orderexecute.service.OrderGoodsService;
import com.sinochem.crude.trade.orderexecute.service.OrderMsgRemindingService;
import com.sinochem.crude.trade.orderexecute.service.OrderPriceService;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.crude.trade.orderexecute.service.OrderSettlementService;
import com.sinochem.crude.trade.orderexecute.service.OrderShipLoadinginfoService;
import com.sinochem.crude.trade.orderexecute.service.OrderShipService;
import com.sinochem.crude.trade.orderexecute.service.OrderShipUnloadinginfoService;
import com.sinochem.crude.trade.orderexecute.service.OrderStatementService;
import com.sinochem.crude.trade.orderexecute.service.OrderTransportService;
import com.sinochem.crude.trade.orderexecute.service.TriggerApplyService;
import com.sinochem.crude.trade.orderexecute.service.TriggerContractService;
import com.sinochem.crude.trade.orderexecute.service.TriggerResultService;
import com.sinochem.crude.trade.orderexecute.service.TriggerTransferService;
import com.sinochem.crude.trade.orderexecute.service.openapi.constants.MoreOrLessSymbolEnum;
import com.sinochem.crude.trade.transport.remote.IShipService;
import com.sinochem.it.b2b.member.access.ApiSafeAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;

/**
 * 订单管理
 * @author hexinfang
 *
 */
@Controller
public class OrderController  {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderGoodsService goodsService;
	@Autowired
	private OrderPriceService orderPriceService;
	@Autowired
	private OrderTransportService transportService;
	@Autowired
	private OrderButtonService orderButtonService;
	@Autowired
	private OrderShipService orderShipService;
	@Autowired
	private OrderShipLoadinginfoService loadinginfoService;
	@Autowired
	private OrderShipUnloadinginfoService unloadinginfoService;
	@Autowired
	private OrderSettlementService settlementService;
	@Autowired
	private TriggerContractService triggerContractService;
	@Autowired
	private TriggerTransferService triggerTransferService;
	@Autowired
	private TriggerApplyService triggerApplyService;
	@Autowired
	private TriggerResultService triggerResultService;
	@Autowired
	private OrderStatementService statementService;
	@Autowired
	private OrderMsgRemindingService msgRemindingService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private IShipService iShipService;
	
	
	@Value("${doc.server.host}")
	private String docServerHost;
	
	private final Logger log = Logger.getLogger(getClass());
	
	/**
	 * 买家订单列表
	 * @param user
	 * @param query
	 * @param model
	 * @param index
	 */
	@RequestMapping(value = UrlMapping.ORDER_LIST_BUYER)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public void listBuyerOrder(CurrentUser user, OrderQuery query, 
			ModelMap model, HttpServletRequest request){
		Integer index = query.getIndex();
		
		query.setBuyerCustomerId(String.valueOf(user.getEpMemberId()));
		Page<OrderBO> pageResult = listOrderQuery(user.getMemberId(), query, index);
		
		QueryBase queryBase = (QueryBase) query;
		queryBase.setTotalItem(pageResult.getTotal());
		
		model.put("index", index);
		model.put("param", query.getParameters());
		model.put("query", queryBase);
		
		List<OrderBO> orderList = pageResult.getResult();
		
		Set<Long> cusIds = new HashSet<>();
		for(OrderBO order : orderList){
			order.setButtonList(orderButtonService.getButtonList(order, user, false));
			cusIds.add(order.getSellerCustomerId());
		}
		Map<Long, String> cusId2NameMap = enterpriseService.getEnterpriseName(cusIds.toArray(new Long[cusIds.size()]), VisitorLocale.getCurrent());
		for(OrderBO order : orderList) {
			order.setSellerCustomerName(cusId2NameMap.get(order.getSellerCustomerId()));
		}
		
		model.put("dataList", orderList);
		
		Map<String, Integer> countResult = orderService.countOrderStatus(query.getQueryParam());
		model.put("countResult", countResult);
	}
	
	/**
	 * 卖家订单列表
	 * @param user
	 * @param query
	 * @param model
	 * @param index
	 */
	@RequestMapping(value = UrlMapping.ORDER_LIST_SELLER)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public void listSellerOrder(CurrentUser user, OrderQuery query, ModelMap model,
			@RequestParam(value="index", required=false, defaultValue="0") Integer index){
		query.setSellerCustomerId(String.valueOf(user.getEpMemberId()));
		Page<OrderBO> pageResult = listOrderQuery(user.getMemberId(), query, index);
		
		QueryBase queryBase = (QueryBase) query;
		queryBase.setTotalItem(pageResult.getTotal());
		
		model.put("index", index);
		model.put("param", query.getParameters());
		model.put("query", queryBase);
		
		List<OrderBO> orderList = pageResult.getResult();
		Set<Long> cusIds = new HashSet<>();
		for(OrderBO order : orderList){
			order.setButtonList(orderButtonService.getButtonList(order, user, true));
			cusIds.add(order.getBuyerCustomerId());
		}
		
		Map<Long, String> cusId2NameMap = enterpriseService.getEnterpriseName(cusIds.toArray(new Long[cusIds.size()]), VisitorLocale.getCurrent());
		for(OrderBO order : orderList) {
			order.setBuyerCustomerName(cusId2NameMap.get(order.getBuyerCustomerId()));
		}
		
		model.put("dataList", orderList);
		
		Map<String, Integer> countResult = orderService.countOrderStatus(query.getQueryParam());
		model.put("countResult", countResult);
	}
	
	/**
	 * 【API接口】 查询订单列表
	 * @param query  查询条件
	 * @param u      查询类型（1：作为买家，2：作为卖家）
	 * @param index  订单状态分类标识
	 * @return
	 */
	@RequestMapping(value = UrlMapping.ORDER_LIST_JSON, method = RequestMethod.GET)
	@ResponseBody
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	@ApiSafeAccess
	public ResultDatas<List<OrderListDTO>> listOrder(
			CurrentUser user,
			OrderQuery query,
			@RequestParam(value="u", required=false, defaultValue="1") String u,
			@RequestParam(value="index", required=false, defaultValue="0") Integer index) {
		
		Long userId = user.getMemberId();
		Long userCompanyId = user.getEpMemberId();
		if("1".equals(u)) {
			query.setBuyerCustomerId(userCompanyId.toString());
		} else if("2".equals(u)) {
			query.setSellerCustomerId(userCompanyId.toString());
		} else if("0".equals(u)) {
			query.setAllCustomerId(userCompanyId.toString());
		} else {//defult
			query.setBuyerCustomerId(userCompanyId.toString());
		}
		
		Page<OrderBO> pageResult = listOrderQuery(userId, query, index);
		List<OrderListDTO> resultData = new ArrayList<>();
		for(OrderBO orderBO : pageResult.getResult()) {
			OrderListDTO orderDTO = new OrderListDTO();
			
			orderDTO.setUuid(orderBO.getUuid());
			orderDTO.setOrderNo(orderBO.getOrderNo());
			orderDTO.setGoodsName(orderBO.getGoodsInfo().getZhName());
			//以万为单位
			BigDecimal quantity = orderBO.getGoodsInfo().getQuantity();
			if(quantity != null) {
				orderDTO.setQuantity(quantity.divide(new BigDecimal("10000")).toBigInteger().toString());
			}
			orderDTO.setTradeTerm(orderBO.getPriceInfo().getTradeTerm());
			orderDTO.setDeliveryDate(orderBO.getTransportInfo().getDeliveryDateStart(), orderBO.getTransportInfo().getDeliveryDateEnd());			
			//运输信息
			OrderTransport transportInfo = transportService.findByOrderId(orderService.findByUuid(orderBO.getUuid()).getId());
			if(transportInfo != null) {
				orderDTO.setDischargeDate(transportInfo.getDischargeDateStart(), transportInfo.getDischargeDateEnd());		
			}
			orderDTO.setLoadingPort(orderBO.getTransportInfo().getLoadingPort());
			orderDTO.setPrice(orderBO.getPriceInfo().getPrice(), orderBO.getPriceInfo().getCurrency(), orderBO.getGoodsInfo().getQuantityUnit());
			orderDTO.setAgio(orderBO.getPriceInfo().getAgio(), orderBO.getPriceInfo().getCurrency(), orderBO.getGoodsInfo().getQuantityUnit());
			orderDTO.setTradeCategory(orderBO.getTradeCategory());
			orderDTO.setStatusDesc(orderBO.getStatusDesc());
			
			if(userCompanyId.equals(orderBO.getBuyerCustomerId())) {//买家
				orderDTO.setBuySell("buy");
			}else if(userCompanyId.equals(orderBO.getSellerCustomerId())) {//卖家
				orderDTO.setBuySell("sell");
			}else if(userCompanyId.equals(orderBO.getAgentId())) {//代理商
				orderDTO.setBuySell("agent");
			}
			
			List<String> btnList = new ArrayList<>();
			if("1".equals(u)) {//买家列表
				orderDTO.setLogoUrl("http://"+docServerHost+"/img/GSLOGO/"+orderBO.getSellerCustomerId()+".img");//对家公司LOGO
				btnList = orderButtonService.getButtonList(orderBO, user, false);//操作按钮
				
			} else {//卖家列表
				orderDTO.setLogoUrl("http://"+docServerHost+"/img/GSLOGO/"+orderBO.getBuyerCustomerId()+".img");//对家公司LOGO
				btnList = orderButtonService.getButtonList(orderBO, user, true);//操作按钮
			}
			
			//我要租船
			if(OrderStatusEnum.STATUS_1.getCode().equals(orderBO.getStatus())) {
				btnList.add("wyzc");
			}
			
			orderDTO.setButtonList(btnList);
			
			resultData.add(orderDTO);
		}
		
		ResultDatas<List<OrderListDTO>> result = new ResultDatas<>();
		result.setDatas(resultData);
		result.setPageNum(pageResult.getPageNum());
		result.setPageSize(pageResult.getPageSize());
		result.setTotal(pageResult.getTotal());
		result.setPageCount(pageResult.getPages());
		
		return result;
	}
	
	/**
	 * 【API接口】 查询订单详情
	 * 
	 * @param query
	 * @param index
	 * @return
	 */
	@RequestMapping(value = UrlMapping.ORDER_DETAIL_JSON, method = RequestMethod.GET)
	@ResponseBody
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	@ApiSafeAccess
	public ResultDatas<OrderDetailDTO> getOrderDetail(
			@RequestParam(value="ouid") String orderUuid) {
		
		ResultDatas<OrderDetailDTO> result = new ResultDatas<>();
		OrderDetailDTO data = new OrderDetailDTO();
		
		OrderBO orderBO = orderService.queryOrderDetailByUuid(orderUuid);
		if(orderBO == null) {
			result.setFail("订单不存在");
			result.setCode("orderexecute.code.00014");
			return result;
		}
		
		Long orderId = orderBO.getId();
		//商品信息
		OrderGoods goodsInfo = goodsService.findByOrderId(orderId);
		orderBO.setGoodsInfo(goodsInfo);
		//价格信息
		OrderPrice priceInfo = orderPriceService.findByOrderId(orderId);
		orderBO.setPriceInfo(priceInfo);
		//运输信息
		OrderTransport transportInfo = transportService.findByOrderId(orderId);
		orderBO.setTransportInfo(transportInfo);
		
		//商品信息
		//OrderGoods goodsInfo = orderBO.getGoodsInfo()!=null? orderBO.getGoodsInfo() : new OrderGoods();
		//价格信息
		//OrderPrice priceInfo = orderBO.getPriceInfo()!=null? orderBO.getPriceInfo() : new OrderPrice();
		//运输信息
		//OrderTransport transportInfo = orderBO.getTransportInfo()!=null? orderBO.getTransportInfo() : new OrderTransport();
		//结算信息
		//OrderSettlement settlement = settlementService.queryActiveSettlementByOrderId(orderId);
		//对账信息
		OrderStatement statement = statementService.queryActiveStatementByOrderId(orderId);
		data.setUuid(orderUuid);
		/**成交方式*/
		data.setTradeMode("现货");
		/**成交单ID*/
		data.setTradeId(orderBO.getTradeId());
		/**交易大类（1原油；2成品油；3化工品）*/
		data.setTradeCategory(orderBO.getTradeCategory());
		/** 订单状态描述 */
		OrderStatusEnum orderStatusEnum = OrderStatusEnum.getByCode(orderBO.getStatus());
		if(orderStatusEnum != null) {
			data.setOrderStatusDesc(orderStatusEnum.getValue());
		}else {
			data.setOrderStatusDesc(orderBO.getStatus());
		}
		/** 订单编号 */
		data.setOrderNo(orderBO.getOrderNo());
		/** 创建时间 */
		data.setCreateDate(DateFormatUtils.format(orderBO.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
		/** 商品名称，油种 */
		data.setGoodsName(goodsInfo.getZhName());
		/** 规格 */
		data.setSpec(goodsInfo.getSpec());
		/** 数量 */
		if(goodsInfo.getQuantity() != null) {
			data.setQuantity(goodsInfo.getQuantity().divide(new BigDecimal(1), 3, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString());
		}
		/** 溢短装 */
		if(goodsInfo.getMoreLess() != null) {
			data.setMoreLess(MoreOrLessSymbolEnum.PLUS_MINUS.getSymbol()+goodsInfo.getMoreLess().stripTrailingZeros().toPlainString()+"%");
		}
		/** 贸易条款 */
		data.setTradeTerm(priceInfo.getTradeTerm());
		/** 计价公式 */
		data.setPriceFormula(priceInfo.getPriceFormula());
		/** 付款条款 */
		data.setPaymentTerm(priceInfo.getPaymentTerm());
		data.setPaymentDate(priceInfo.getPaymentTerm());
		/** 信用条款 */
		data.setCreditTerm(orderBO.getCreditTerm());
		/** 升贴水 */
		if(priceInfo.getAgio() != null) {
			String agioDesc = priceInfo.getAgio().stripTrailingZeros().toPlainString();
			data.setAgio(agioDesc);
		}
		/** 计价方式 */
		data.setPriceType(priceInfo.getPriceType());
		/** 计价基准 */
		data.setBasicPrice(priceInfo.getBasePrice());
		/** 计价货币 */
		data.setCurrency(priceInfo.getCurrency());
		/** 定价计量单位 */
		data.setQuantityUnit(goodsInfo.getQuantityUnit());
		/** 价格说明 */
		data.setPriceDesc(priceInfo.getPriceDesc());
		/** 计价期 */
		data.setPricingDesc(priceInfo.getPricingDesc());
		/** 装货港 */
		data.setLoadingPort(orderBO.getTransportInfo().getLoadingPort());
		/** 卸货港 */
		data.setUploadingPort(transportInfo.getUploadingPort());
		/** 装期 */
		String deliveryDate = "";
		if(orderBO.getTransportInfo().getDeliveryDateStart() != null) {
			deliveryDate += DateFormatUtils.format(orderBO.getTransportInfo().getDeliveryDateStart(), "yyyy/MM/dd");
		}
		if(orderBO.getTransportInfo().getDeliveryDateEnd() != null) {
			deliveryDate += "-";
			deliveryDate += DateFormatUtils.format(orderBO.getTransportInfo().getDeliveryDateEnd(), "yyyy/MM/dd");
		}
		data.setDeliveryDate(deliveryDate);
		/** 到货期 */
		String dischargeDate = "";
		if(orderBO.getTransportInfo().getDischargeDateStart() != null) {
			dischargeDate += DateFormatUtils.format(orderBO.getTransportInfo().getDischargeDateStart(), "yyyy/MM/dd");
		}
		if(orderBO.getTransportInfo().getDischargeDateEnd() != null) {
			dischargeDate += "-";
			dischargeDate += DateFormatUtils.format(orderBO.getTransportInfo().getDischargeDateEnd(), "yyyy/MM/dd");
		}
		data.setDischargeDate(dischargeDate);
		
		if(statement != null) {
			/** 提单日 */
			if(statement.getBillDate() != null) {
				data.setBillDate(DateFormatUtils.format(statement.getBillDate(), "yyyy-MM-dd"));
			}
			/** 付款日 */
			/*if(statement.getPaymentDate() != null) {
				data.setPaymentDate(DateFormatUtils.format(statement.getPaymentDate(), "yyyy-MM-dd"));
			}*/
			/** 提单量（净吨） */
			if(statement.getBillWeightT() != null) {
				data.setBillWeightT(statement.getBillWeightT().stripTrailingZeros().toPlainString());
			}
			/** 提单量（净桶） */
			if(statement.getBillWeightBbl() != null) {
				data.setBillWeightBbl(statement.getBillWeightBbl().divide(new BigDecimal(1), 3, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString());
			}
			/** 结算价格 */
			if(statement.getSettlementPrice() != null) {
				data.setSettlementPrice(statement.getSettlementPrice().stripTrailingZeros().toPlainString());
			}
			/** 结算量（桶） */
			if(statement.getSettlementQuantityBbl() != null) {
				data.setSettlementQuantityBbl(statement.getSettlementQuantityBbl().divide(new BigDecimal(1), 3, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString());
			}
			/** 基准价格 */
			if(statement.getBasePrice() != null) {
				data.setBasePrice(statement.getBasePrice().stripTrailingZeros().toPlainString());
			}
			/** 费用合计 */
			if(statement.getTotalFee() != null) {
				data.setTotalFee(statement.getTotalFee().stripTrailingZeros().toPlainString());
			}
			/** 已结算金额 */
			if(statement.getSettledAmount() != null) {
				//double settlementTotal = settlementService.calcSettlementTotal(orderId);
				
				double statementTotal = statementService.calcStatementTotal(orderId);
				data.setSettledAmount(BigDecimal.valueOf(statementTotal).stripTrailingZeros().toPlainString());
			}
		}
		
		/** 备注 */
		data.setRemark(orderBO.getRemark());
		
		OrderParty buyer = orderBO.getBuyer();
		/** 买家公司名称 */
		data.setBuyerCustomerName(buyer.getCustomerName());
		/** 买家公司地址 */
		data.setBuyerAddress(buyer.getAddress());
		/** 买家联系人 */
		data.setBuyerContactName(buyer.getContactName());
		/** 买家联系电话 */
		data.setBuyerTel(buyer.getTel());
		/** 买家邮箱 */
		data.setBuyerEmail(buyer.getEmail());
		/** 买家传真 */
		data.setBuyerFax(buyer.getFax());
		
		OrderParty seller = orderBO.getSeller();
		/** 卖家公司名称 */
		data.setSellerCustomerName(seller.getCustomerName());
		/** 卖家公司地址 */
		data.setSellerAddress(seller.getAddress());
		/** 卖家联系人 */
		data.setSellerContactName(seller.getContactName());
		/** 卖家联系电话 */
		data.setSellerTel(seller.getTel());
		/** 卖家邮箱 */
		data.setSellerEmail(seller.getEmail());
		/** 卖家传真 */
		data.setSellerFax(seller.getFax());
		
		/** 其它条款 */
		data.setOtherTerm(orderBO.getOtherTerm());
		/** 信用条款 */
		data.setCreditTerm(orderBO.getCreditTerm());
		/** 商检机构 */
		data.setSurvey(orderBO.getSurvey());
		/** 出口配额文件 */
		data.setQuatoFile(orderBO.getQuatoFile());
		
		result.setDatas(data);
		
		return result;
	}
	
	public Page<OrderBO> listOrderQuery(Long createUser, OrderQuery query, Integer index){
		
		String orderStatus = null;
		switch (index) {
		case 1:
			orderStatus = OrderStatusEnum.STATUS_1.getCode();
			break;
		case 2:
			orderStatus = OrderStatusEnum.STATUS_2.getCode();
			break;
		case 3:
			orderStatus = OrderStatusEnum.STATUS_3.getCode();
			break;
		case 4:
			orderStatus = OrderStatusEnum.STATUS_4.getCode();
			break;
		case 5:
			orderStatus = OrderStatusEnum.STATUS_5.getCode();
			break;
		case 6:
			orderStatus = OrderStatusEnum.STATUS_6.getCode();
			break;
		case 7:
			orderStatus = OrderStatusEnum.STATUS_7.getCode();
			break;
		case 8:
			orderStatus = OrderStatusEnum.STATUS_8.getCode();
			break;
		default:
			break;
		}
		query.setOrderStatus(orderStatus);
		Page<OrderBO> pageResult = orderService.queryOrderList(query.getQueryParam(), createUser, query.getPageInfo());
		
		return pageResult;
	}
	
	/**
	 * 买家订单详情
	 * @param user
	 * @param uid
	 * @param model
	 */
	@RequestMapping(value = UrlMapping.ORDER_DETAIL_BUYER, method = RequestMethod.GET)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public void getBuyerOrderDetail(CurrentUser user, @RequestParam("uid") String uid, ModelMap model){
		OrderBO orderBO  = orderService.queryOrderDetailByUuid(uid);
		if(orderBO == null){
			throw new OrderExecException("404");
		}
		
		Long orderId = orderBO.getId();
		OrderVO orderVO = BeanConvertUtils.beanToBean(orderBO.getOrderInfo(), OrderVO.class);
		orderVO.setBuyer(orderBO.getBuyer());
		orderVO.setSeller(orderBO.getSeller());
		orderVO.setAgent(orderBO.getAgent());
		orderVO.setGoodsInfo(orderBO.getGoodsInfo());
		orderVO.setPriceInfo(orderBO.getPriceInfo());
		orderVO.setTransportInfo(orderBO.getTransportInfo());
		orderVO.setLoadinginfoList(orderBO.getLoadinginfoList());
		orderVO.setUnloadinginfoList(orderBO.getUnloadinginfoList());
		//船信息
		OrderShip orderShip = orderShipService.findByOrderId(orderId);
		orderVO.setOrderShipInfo(orderShip);
		
		if(orderBO.getPriceInfo() != null && "Trigger".equals(orderBO.getPriceInfo().getPriceType())) {
			//点价信息
			orderVO.setTriggerApplyList(triggerApplyService.findByOrderId(orderId));
			orderVO.setTriggerContractList(triggerContractService.findByOrderId(orderId));
			orderVO.setTriggerResultList(triggerResultService.findByOrderId(orderId));
			orderVO.setTriggerTransferList(triggerTransferService.findByOrderId(orderId));
		}
		
		//装船信息
		List<OrderShipLoadinginfo> loadingList = loadinginfoService.findByOrderNo(orderBO.getOrderNo());
		orderVO.setLoadinginfoList(loadingList);
		List<OrderShipUnloadinginfo> unloadingList = unloadinginfoService.findByOrderNo(orderBO.getOrderNo());
		orderVO.setUnloadinginfoList(unloadingList);
		
		//查询当前结算单
		OrderSettlement currentSettlement = settlementService.queryActiveSettlementByOrderId(orderId);
		//结算金额合计
		double settlementTotal = settlementService.calcSettlementTotal(orderId);
		
		model.put("settlementTotal", settlementTotal);
		model.put("settlementInfo", currentSettlement);
		
		//查询当前已确认的对账单
		OrderStatement statementInfo = statementService.queryActiveStatementByOrderId(orderId);
		//对账结算金额合计
		double statementTotal = statementService.calcStatementTotal(orderId);
		
		model.put("statementTotal", statementTotal);
		model.put("statementInfo", statementInfo);
		model.put("order", orderVO);
	}
	/**
	 * 卖家订单详情
	 * @param user
	 * @param uid
	 * @param model
	 */
	@RequestMapping(value = UrlMapping.ORDER_DETAIL_SELLER, method = RequestMethod.GET)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public void getSellerOrderDetail(CurrentUser user, @RequestParam("uid") String uid, ModelMap model, HttpServletRequest req){
		getBuyerOrderDetail(user, uid, model);
	}
	
	/***
	 * 取得确认收款用的订单信息
	 */
	@RequestMapping(value = UrlMapping.ORDER_PAYCONFIRM_DETAIL)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public void orderPayConfirm(CurrentUser user, ModelMap modelMap, String uuid, String formPage) {
		log.info("--->" + uuid);
		//结算总金额（预估+正式）
		BigDecimal totalAmountSum = orderService.selectTotalAmount(uuid);
		
		OrderDetailView orderInfo = orderService.getOrderInfo(uuid);
		
		modelMap.put("receiveData", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		modelMap.put("totalAmountSum", totalAmountSum);
		modelMap.put("orderInfo", orderInfo);
		modelMap.put("formPage", formPage);
	}
	
	/***
	 * 保存确认收款信息
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.ORDER_PAYCONFIRM_SAVE, method = RequestMethod.POST)
	@RolesAccess({ "trade_executor"})
	public Result savePayConfirm(@RequestBody OrderVO vo, CurrentUser user) {
		log.info("--->" + vo.toString());
		Result res = new Result();
		try {
			// 验证登录信息
			if (user == null) {
				//throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			String msg = orderService.savePayConfirm(vo, user);
			if(msg != null) {
				log.error("saveDissentContent error" + msg);
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setCode(Constants.EXCEPTION_CODE);
				res.setMessage(msg);	
			}else {
				//关闭确认收款消息提醒
				msgRemindingService.closeCheckReceiptReminding(vo.getId());
			}
		} catch (OrderExecException e) {
			log.error("saveReplyContent error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("saveReplyContent error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 订单操作按钮片段
	 * @param user
	 * @param orderUuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = UrlMapping.ORDER_CONTAIN_OPERATE_BTNS, method = RequestMethod.GET)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public String getOrderOperateButtons(
			CurrentUser user,
			@RequestParam(value="ouid") String orderUuid,
			ModelMap model) {
		
		Order order = orderService.findByUuid(orderUuid);
		if(order == null){
			return "contain/order/operateButtons";
		}
		
		boolean isSeller = false;
		if(user.getEpMemberId().equals(order.getBuyerCustomerId())){
			isSeller = false;
		}else if(user.getEpMemberId().equals(order.getSellerCustomerId())){
			isSeller = true;
		}
		List<String> buttons = orderButtonService.getButtonList(order, user, isSeller);
		
		if(buttons.contains("wlgz")){
			OrderShip orderShip = orderShipService.findByOrderId(order.getId());
			if(orderShip != null){
				model.put("agentUuid", orderShip.getAgentUuid());
			}
		}
		
		model.put("buttonList", buttons);
		model.put("contractId", order.getContractId());
		model.put("uuid", order.getUuid());
		model.put("orderNo", order.getOrderNo());
		model.put("tradeCategory", order.getTradeCategory());
		model.put("isSeller", isSeller);
		
		return "contain/order/operateButtons";
	}
	
	/**
	 * 商品详情 页面片段
	 * @param user
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = UrlMapping.ORDER_CONTAIN_GOODSDETAIL, method = RequestMethod.GET)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public String getOrderGoodsDetail(
			CurrentUser user,
			@RequestParam(value="oid") Long orderId,
			ModelMap model) {
		
		Order order = orderService.findByPrimaryKey(orderId);
		if(order == null) {
			return "contain/order/goodsDetail";
		}
		
		OrderVO orderVO = BeanConvertUtils.beanToBean(order, OrderVO.class);
		
		OrderGoods goodsInfo = goodsService.findByOrderId(orderId);
		orderVO.setGoodsInfo(goodsInfo);
		
		OrderPrice priceInfo = orderPriceService.findByOrderId(orderId);
		orderVO.setPriceInfo(priceInfo);
		
		OrderTransport transportInfo = transportService.findByOrderId(orderId);
		orderVO.setTransportInfo(transportInfo);
		
		orderVO.setGoodsInfo(goodsInfo);
		orderVO.setPriceInfo(priceInfo);
		orderVO.setTransportInfo(transportInfo);
		
		model.put("order", orderVO);
		
		return "contain/order/goodsDetail";
	}
	
	/**
	 * 损益状况信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value = UrlMapping.ORDER_INCOME_STATEMENT, method = RequestMethod.GET)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public void getOrderIncomeStatement(
			CurrentUser user, @RequestParam("orderNo") String orderNo,
			ModelMap modelMap) {
		//装港
		String loading = "";
		//卸港
		String unloading = "";
		//船名
		String shipName = "";
		//品种
		String goodsName = "";
		//密度
		String density = "";
		//船舶经验系数（VEF）
		BigDecimal vef = BigDecimal.ZERO;
		//提单日期
		String billDate = "";
		//卸货日期
		String unloadDate = "";
		
		//提单量
		BigDecimal billQuantity_MT = BigDecimal.ZERO;//毛吨
		BigDecimal billQuantity_MBBL = BigDecimal.ZERO;//毛桶
		//OBQ
		BigDecimal obq_MT = BigDecimal.ZERO;//毛吨
		BigDecimal obq_MBBL = BigDecimal.ZERO;//毛桶
		//装港船量
		BigDecimal loading_MT = BigDecimal.ZERO;//毛吨
		BigDecimal loading_MBBL = BigDecimal.ZERO;//毛桶
		//卸港船量
		BigDecimal unloading_MT = BigDecimal.ZERO;//毛吨
		BigDecimal unloading_MBBL = BigDecimal.ZERO;//毛桶
		//卸港岸罐量
		BigDecimal transfer_MT = BigDecimal.ZERO;//毛吨
		BigDecimal transfer_MBBL = BigDecimal.ZERO;//毛桶
		
		//装港信息
		List<OrderShipLoadinginfo> loadinginfoList = loadinginfoService.findByOrderNo(orderNo);
		//卸港信息
		List<OrderShipUnloadinginfo> unloadinginfoList = unloadinginfoService.findByOrderNo(orderNo);
		//船舶信息
		OrderShip shipInfo = orderShipService.findByOrderNo(orderNo);
		//商品信息
		OrderGoods goodsInfo = null;
		if(shipInfo != null) {
			goodsInfo = goodsService.findByOrderId(shipInfo.getOrderId());
			if(goodsInfo == null) {
				goodsInfo = new OrderGoods();
			}
		}else {
			shipInfo = new OrderShip();
			goodsInfo = new OrderGoods();
		}
		
		shipName = shipInfo.getName();
		goodsName = goodsInfo.getZhName();
		density = shipInfo.getDensity();
		vef = shipInfo.getVef()!=null?shipInfo.getVef():vef;
		
		if(loadinginfoList != null && !loadinginfoList.isEmpty()) {
			for(OrderShipLoadinginfo loadinginfo : loadinginfoList) {
				loading += "，" + loadinginfo.getLoadPort();
				if(loadinginfo.getBlDate() != null) {
					billDate += "，" + DateTimeUtils.toDateString(loadinginfo.getBlDate());
				}
				
				billQuantity_MT = billQuantity_MT.add(loadinginfo.getBlHairTonnage()
						==null?BigDecimal.ZERO:loadinginfo.getBlHairTonnage());
				billQuantity_MBBL = billQuantity_MBBL.add(loadinginfo.getBlHairBarrel()
						==null?BigDecimal.ZERO:loadinginfo.getBlHairBarrel());
				loading_MT = loading_MT.add(loadinginfo.getShHairTonnage()
						==null?BigDecimal.ZERO:loadinginfo.getShHairTonnage());
				loading_MBBL = loading_MBBL.add(loadinginfo.getShHairBarrel()
						==null?BigDecimal.ZERO:loadinginfo.getShHairBarrel());
			}
			
			loading = loading.substring(1);
			if(StringUtils.isNotBlank(billDate)) {
				billDate = billDate.substring(1);
			}
		}
		
		if(unloadinginfoList != null && !unloadinginfoList.isEmpty()) {
			for(OrderShipUnloadinginfo unloadinginfo : unloadinginfoList) {
				unloading += "，" + unloadinginfo.getUnloadPort();
				if(unloadinginfo.getAcStart()!=null && unloadinginfo.getAcFinish()!=null) {
					unloadDate += "，" + DateTimeUtils.toDateTimeString(unloadinginfo.getAcStart()) +"至"+ DateTimeUtils.toDateTimeString(unloadinginfo.getAcFinish());
				}
				
				unloading_MT = unloading_MT.add(unloadinginfo.getShipHairTon()
						==null?BigDecimal.ZERO:unloadinginfo.getShipHairTon());
				unloading_MBBL = unloading_MBBL.add(unloadinginfo.getShipHairBar()
						==null?BigDecimal.ZERO:unloadinginfo.getShipHairBar());
				transfer_MT = transfer_MT.add(unloadinginfo.getPotHairTon()
						==null?BigDecimal.ZERO:unloadinginfo.getPotHairTon());
				transfer_MBBL = transfer_MBBL.add(unloadinginfo.getPotHairBar()
						==null?BigDecimal.ZERO:unloadinginfo.getPotHairBar());
			}
			unloading = unloading.substring(1);
			if(StringUtils.isNotBlank(unloadDate)) {
				unloadDate = unloadDate.substring(1);
			}
		}
		String[][] count1 = {
			{decimalToString(billQuantity_MBBL),decimalToString(billQuantity_MT)},
			{decimalToString(obq_MBBL),decimalToString(obq_MT)},
			{decimalToString(loading_MBBL),decimalToString(loading_MT)},
			{incomeSurplus(loading_MBBL,obq_MBBL),incomeSurplus(loading_MT,obq_MT)},
			{incomeSurplus(loading_MBBL.subtract(obq_MBBL),billQuantity_MBBL),incomeSurplus(loading_MT.subtract(obq_MT),billQuantity_MT)},
			{incomePersent(loading_MBBL.subtract(obq_MBBL),billQuantity_MBBL),incomePersent(loading_MT.subtract(obq_MT),billQuantity_MT)}
		};
		String[][] count2 = {
			{decimalToString(loading_MBBL),decimalToString(loading_MT)},
			{decimalToString(unloading_MBBL),decimalToString(unloading_MT)},
			{incomeSurplus(unloading_MBBL,loading_MBBL),incomeSurplus(unloading_MT,loading_MT)},
			{incomePersent(unloading_MBBL,loading_MBBL),incomePersent(unloading_MT,loading_MT)}
		};
		String[][] count3 = {
			{decimalToString(billQuantity_MBBL),decimalToString(billQuantity_MT)},
			{decimalToString(unloading_MBBL),decimalToString(unloading_MT)},
			{incomeSurplus(unloading_MBBL,billQuantity_MBBL),incomeSurplus(unloading_MT,billQuantity_MT)},
			{incomePersent(unloading_MBBL,billQuantity_MBBL),incomePersent(unloading_MT,billQuantity_MT)}
		};
		String[][] count4 = {
			{decimalToString(unloading_MBBL),decimalToString(unloading_MT)},
			{decimalToString(unloading_MBBL.multiply(vef)),decimalToString(unloading_MT.multiply(vef))},
			{decimalToString(transfer_MBBL),decimalToString(transfer_MT)},
			{incomeSurplus(transfer_MBBL,unloading_MBBL.multiply(vef)),incomeSurplus(transfer_MT,unloading_MT.multiply(vef))},
			{incomePersent(transfer_MBBL,unloading_MBBL.multiply(vef)),incomePersent(transfer_MT,unloading_MT.multiply(vef))}
		};
		String[][] count5 = {
			{decimalToString(billQuantity_MBBL),decimalToString(billQuantity_MT)},
			{decimalToString(transfer_MBBL),decimalToString(transfer_MT)},
			{incomeSurplus(transfer_MBBL,billQuantity_MBBL),incomeSurplus(transfer_MT,billQuantity_MT)},
			{incomePersent(transfer_MBBL,billQuantity_MBBL),incomePersent(transfer_MT,billQuantity_MT)}
		};
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("loading", loading);
		dataMap.put("unloading", unloading);
		dataMap.put("shipName", shipName);
		dataMap.put("goodsName", goodsName);
		dataMap.put("density", density);
		dataMap.put("vef", vef);
		dataMap.put("billDate", billDate);
		dataMap.put("unloadDate", unloadDate);
		
		modelMap.put("count1", count1);
		modelMap.put("count2", count2);
		modelMap.put("count3", count3);
		modelMap.put("count4", count4);
		modelMap.put("count5", count5);
		modelMap.put("data", dataMap);
	}
	
	/**
	 * 根据船名查询imo
	 * @param num
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=UrlMapping.ORDER_SHIP_IMO, method = RequestMethod.GET)
	public ResultDatas<String> findImoByShipName(@RequestParam String ShipName){
		ResultDatas<String> result = new ResultDatas<>();
		String imo = "";
		try {
			imo = iShipService.findImoByShipName(ShipName);
		} catch (Exception e) {
			log.error("", e);
		}//9241102
		result.setDatas(imo);
		return result;
	}	
	
	/**
	 * 进入录入合同号页面
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.ORDER_EDIT_CONTRACT_NO, method = RequestMethod.GET)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public ResultDatas<Order> editContractNo(@RequestParam String uuid){
		ResultDatas<Order> result = new ResultDatas<>();
		try {
			Order order = orderService.findByUuid(uuid);
			result.setDatas(order);
		} catch (Exception e) {
			log.error("", e);
		}
		return result;
	}	
	
	/**
	 * 保存合同号(买卖双方)
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.ORDER_SAVE_CONTRACT_NO, method = RequestMethod.POST)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public ResultDatas<String> saveContractNo(OrderVO vo){
		ResultDatas<String> result = new ResultDatas<>();
		try {
			String uuid = vo.getUuid();
			Order order = orderService.findByUuid(uuid);
			order.setSellerContractNo(vo.getSellerContractNo());
			order.setBuyerContractNo(vo.getBuyerContractNo());
			orderService.updateRecordById(order);
		} catch (Exception e) {
			log.error("", e);
		}
		result.setDatas("");
		return result;
	}	
	
	private String decimalToString(BigDecimal num) {
		return DecimalFormat.getInstance().format(num);
	}
	private String incomeSurplus(BigDecimal num1, BigDecimal num2) {
		return decimalToString(num1.subtract(num2));
	}
	private String incomePersent(BigDecimal num1, BigDecimal num2) {
		if(num1.multiply(num2).compareTo(BigDecimal.ZERO) == 0) {//其中一个为0
			return "-";
		}
		
		return decimalToString(num1.subtract(num2).multiply(new BigDecimal("100")).divide(num2,3, RoundingMode.HALF_UP));
	}
	
}
