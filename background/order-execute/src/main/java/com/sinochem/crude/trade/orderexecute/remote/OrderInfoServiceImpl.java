package com.sinochem.crude.trade.orderexecute.remote;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.order.remote.CancelOrderVO;
import com.sinochem.crude.trade.order.remote.OrderInfoService;
import com.sinochem.crude.trade.order.remote.OrderInfoVO;
import com.sinochem.crude.trade.orderexecute.commons.constants.OrderStatusEnum;
import com.sinochem.crude.trade.orderexecute.commons.constants.ValueSetGroupConstant;
import com.sinochem.crude.trade.orderexecute.commons.utils.PaymentTermObjectAnalyse;
import com.sinochem.crude.trade.orderexecute.commons.utils.ValueSetUtil;
import com.sinochem.crude.trade.orderexecute.dao.InterfaceSystemMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderGoodsMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderPartyMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderPriceMapper;
import com.sinochem.crude.trade.orderexecute.dao.OrderTransportMapper;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceSystem;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderExecutePerson;
import com.sinochem.crude.trade.orderexecute.domain.OrderGoods;
import com.sinochem.crude.trade.orderexecute.domain.OrderParty;
import com.sinochem.crude.trade.orderexecute.domain.OrderPrice;
import com.sinochem.crude.trade.orderexecute.domain.OrderTransport;
import com.sinochem.crude.trade.orderexecute.domain.TriggerContract;
import com.sinochem.crude.trade.orderexecute.model.InterfaceListVO;
import com.sinochem.crude.trade.orderexecute.service.OrderExecutePersonService;
import com.sinochem.crude.trade.orderexecute.service.OrderMsgRemindingService;
import com.sinochem.crude.trade.orderexecute.service.TriggerContractService;
import com.sinochem.crude.trade.orderexecute.service.openapi.OutputService;
import com.sinochem.crude.trade.orderexecute.service.openapi.constants.MoreOrLessSymbolEnum;
import com.sinochem.crude.trade.orderexecute.service.openapi.constants.PartyTypeEnum;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.PaymentTerm;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.PhysicalRecapVO;
import com.sinochem.crude.trade.orderexecute.service.openapi.vo.Quantity;
import com.sinochem.crude.trade.transport.remote.IShipService;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderPriceMapper orderPriceMapper;
	@Autowired
	private OrderPartyMapper orderPartyMapper;
	@Autowired
	private OrderTransportMapper transportMapper;
	@Autowired
	private OrderGoodsMapper goodsMapper;
	@Autowired
	private InterfaceSystemMapper interfaceSystemMapper;
	@Autowired
	private OutputService outputService;
	@Autowired
	private OrderExecutePersonService executePersonService;
	@Autowired
	private TriggerContractService triggerContractService;
	@Autowired
	private OrderMsgRemindingService msgRemindingService;
	/*船务取消订单Service*/
	@Autowired
	private IShipService shipService;
	@Resource(name="orderExecuteServer")
	private URLBroker orderExecuteServer;
	@Resource(name="systemServer")
	private URLBroker systemServer;
	
	private final Logger log = Logger.getLogger(getClass());

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void createOrder(OrderInfoVO vo) {
		log.info("开始创建订单：输入参数>> " + JSONObject.fromObject(vo).toString());
		
		Order existOrder = orderMapper.findByUuid(vo.getOrderUuid());
		if(existOrder != null){
			throw new BizException("订单已存在");
		}
		
		//计算付款日期
		Date payDate = calcPayDate(vo);
		vo.setOfferPayDate(payDate);
		
		//值集转换
		convertValueForLocalSys(vo);
		log.info("值集转换后数据>> " + JSONObject.fromObject(vo).toString());
		
		//组装数据
		Order order = buildOrderInfo(vo);
		OrderGoods goodsInfo = buildOrderGoodsInfo(vo);
		OrderPrice offerInfo = buildOfferInfo(vo);
		OrderParty buyerInfo = buildBuyerInfo(vo);
		OrderParty sellerInfo = buildSellerInfo(vo);
		OrderParty agentInfo = buildAgentInfo(vo);
		OrderTransport transportInfo = buildTransportInfo(vo);
		List<OrderExecutePerson> executePersonList = buildExecuterList(vo);
		TriggerContract triggerContract = buildTriggerContractInfo(vo,offerInfo);
		
		//保存本地数据
		log.info("保存订单信息开始...");
		orderMapper.insertRecord(order);
		long orderId = order.getId();
		
		log.info("保存商品信息开始...");
		goodsInfo.setOrderId(orderId);
		goodsMapper.insertRecord(goodsInfo);
		
		log.info("保存报价信息开始...");
		offerInfo.setOrderId(orderId);
		orderPriceMapper.insertRecord(offerInfo);
		
		log.info("保存买家信息开始...");
		buyerInfo.setOrderId(orderId);
		orderPartyMapper.insertRecord(buyerInfo);
		
		log.info("保存卖家信息开始...");
		sellerInfo.setOrderId(orderId);
		orderPartyMapper.insertRecord(sellerInfo);
		
		if(agentInfo != null){
			log.info("保存代理商信息开始...");
			agentInfo.setOrderId(orderId);
			orderPartyMapper.insertRecord(agentInfo);
		}
		
		log.info("保存运输信息开始...");
		transportInfo.setOrderId(orderId);
		transportMapper.insertRecord(transportInfo);
		
		log.info("保存订单消息提醒数据开始...");
		Map<String, Object> msgParams = new HashMap<>();
		msgParams.put("shipName", "");
		msgParams.put("orderNo", order.getOrderNo());
		msgParams.put("orderDetailLink", orderExecuteServer.get("sellerCenter/order/detail.htm")+"?uid="+order.getUuid());
		msgParams.put("loginLink", systemServer.get("login.htm").toString());
		
		//NOR提醒
		msgRemindingService.createNorUpdateReminding(orderId, vo.getSellerDealerId(), vo.getOfferDischargeDateStart(), msgParams);
		//COD提醒
		msgRemindingService.createCodUpdateReminding(orderId, vo.getSellerDealerId(), vo.getOfferDischargeDateStart(), msgParams);
		//提单日提醒
		msgRemindingService.createBillUpdateReminding(orderId, vo.getSellerDealerId(), vo.getOfferDeliveryDateStart(), msgParams);
		//创建对账单提醒
		if(payDate != null) {
			msgRemindingService.createStatementCreateReminding(orderId, vo.getSellerDealerId(), 
					vo.getOfferPaymentTermJson(), payDate, msgParams);
		}
		
		if(!executePersonList.isEmpty()) {
			log.info("保存订单执行人信息开始...");
			for(OrderExecutePerson executer : executePersonList) {
				executer.setOrderId(orderId);
				executePersonService.insertRecord(executer);
			}
		}
		if(triggerContract != null){
			log.info("保存点价信息开始");
			triggerContract.setOrderId(orderId);
			triggerContractService.insertRecord(triggerContract);
		}
		//发送到外部系统
		log.info("准备发往外部系统...");
		sendToSherlockSystem(vo, orderId);
	}
	
	//发送到外部系统
	public void sendToSherlockSystem(OrderInfoVO data, long orderId){
		InterfaceListVO interfaceListVO = new InterfaceListVO();
		
		String jsonData1 = null;
		String jsonData2 = null;
		
		//查询买家注册的外部系统别名
		InterfaceSystem sysDataForBuyer = interfaceSystemMapper.findByMemberId(data.getBuyerCustomerId());
		
		//查询卖家注册的外部系统别名
		InterfaceSystem sysDataForSeller = interfaceSystemMapper.findByMemberId(data.getSellerCustomerId());
		
		//convertValueForLocalSys(data);
		if(sysDataForBuyer != null){
			jsonData1 = buildPriceJsonData(data, PartyTypeEnum.Buy);
		}
		if(sysDataForSeller != null){
			jsonData2 = buildPriceJsonData(data, PartyTypeEnum.Sell);
		}
		//数据校验
		if(jsonData1 != null || jsonData2 != null) {
			dataValidate(data);
		}
		
		//更新订单字段
		Order orderUpdate = new Order();
		orderUpdate.setId(orderId);
		
		if(jsonData1 != null){
			log.info("开始发往买家【"+data.getBuyerCustomerName()+"】外部系统【"+sysDataForBuyer.getSysName()+"】...");
			interfaceListVO.setSysName(sysDataForBuyer.getSysName());
			Result result1 = outputService.physicalRecapInsert(interfaceListVO, jsonData1);
			log.info("发送完成，status:"+result1.getStatus()+", message:"+result1.getMessage());
			
			if(result1.getStatus()==Result.SUCCESS){
				orderUpdate.setBuyerInterfaceStatus("1");
			}else{
				orderUpdate.setBuyerInterfaceStatus("2");
			}
		}else{
			log.info("买家【"+data.getBuyerCustomerName()+"】外部系统不存在！不发送。");
			orderUpdate.setBuyerInterfaceStatus("0");
		}
		
		if(jsonData2 != null){
			log.info("开始发往卖家【"+data.getSellerCustomerName()+"】外部系统【"+sysDataForSeller.getSysName()+"】...");
			interfaceListVO.setSysName(sysDataForSeller.getSysName());
			Result result2 = outputService.physicalRecapInsert(interfaceListVO, jsonData2);
			log.info("发送完成，status:"+result2.getStatus()+", message:"+result2.getMessage());
			
			if(result2.getStatus()==Result.SUCCESS){
				orderUpdate.setSellerInterfaceStatus("1");
			}else{
				orderUpdate.setSellerInterfaceStatus("2");
			}
		}else{
			log.info("卖家【"+data.getSellerCustomerName()+"】外部系统不存在！不发送。");
			orderUpdate.setSellerInterfaceStatus("0");
		}
		
		log.info("接口发送状态更新到订单上...");
		int n = orderMapper.updateRecordById(orderUpdate);
		log.info("更新结束。rsult>>" + n);
	}
	
	//付款日期计算
	private Date calcPayDate(OrderInfoVO vo) {
		PaymentTermObjectAnalyse paymentTermObjectAnalyse = new PaymentTermObjectAnalyse(vo.getOfferPaymentTermJson());
		Date payDate = null;
		if(paymentTermObjectAnalyse.isDate()) {
			payDate = paymentTermObjectAnalyse.getDate();
		}else {
			Date baseDate = null;
			if(paymentTermObjectAnalyse.isBl()) {
				baseDate = vo.getOfferDeliveryDateStart();
			}else if(paymentTermObjectAnalyse.isNor()) {
				baseDate = vo.getOfferDischargeDateStart();
			}else if(paymentTermObjectAnalyse.isCod()) {
				baseDate = vo.getOfferDischargeDateStart();
			}else if(paymentTermObjectAnalyse.isLdr()) {
				baseDate = vo.getOfferDeliveryDateStart();
			}
			
			payDate = paymentTermObjectAnalyse.calcPayDate(baseDate);
		}
		
		return payDate;
	}
	
	//组装订单数据
	private Order buildOrderInfo(OrderInfoVO vo){
		Order order = new Order();
		order.setUuid(vo.getOrderUuid());
		order.setOrderNo(vo.getOrderNo());
		order.setTradeId(vo.getOrderTradeId());
		order.setBuysell(vo.getOrderBuySell());
		order.setBillDate(vo.getOrderBillDate());
		order.setTradeMode(vo.getOrderTradeMode());
		order.setTradeTime(vo.getOrderTradeTime());
		order.setTradeType(vo.getOrderTradeType());
		order.setTradeCategory(vo.getOrderTradeCategory());
		order.setCreditTerm(vo.getOrderCreditTerm());
		order.setOtherTerm(vo.getOrderOtherTerm());
		order.setSurvey(vo.getOrderSurvey());
		order.setCreditModel(vo.getOrderCreditModel());
		order.setQuatoFile(vo.getOrderQuatoFile());
		order.setRemark(vo.getOrderRemark());
		order.setBuyerCustomerId(vo.getBuyerCustomerId());
		order.setBuyerCustomerName(vo.getBuyerCustomerName());
		order.setBuyerPersonId(vo.getBuyerDealerId());
		order.setBuyerPersonName(vo.getBuyerDealerName());
		order.setSellerCustomerId(vo.getSellerCustomerId());
		order.setSellerCustomerName(vo.getSellerCustomerName());
		order.setSellerPersonId(vo.getSellerDealerId());
		order.setSellerPersonName(vo.getSellerDealerName());
		order.setSettlStandard(vo.getMeasureMode());
		order.setLoadAndDischargeTime(vo.getLoadAndDischargePermittedTimespan());
		order.setInspectionFeeSharingType(vo.getInspectionFeeSharingType());
		
		order.setStatus(OrderStatusEnum.STATUS_1.getCode());
		order.setCreateDate(new Date());
		order.setCreateUser(vo.getSellerDealerId());
		order.setCreateUser(vo.getCreateUserId());
		order.setLaw(vo.getLaw());
		order.setGtc(vo.getGtc());
		
		return order;
	}
	
	//组装商品数据
	private OrderGoods buildOrderGoodsInfo(OrderInfoVO vo){
		OrderGoods goods = new OrderGoods();
		goods.setOrderGoodsUuid(KeyGenUtils.newUuid());
		if(StringUtils.isNotBlank(vo.getGoodsInfo().getZhName())){
			goods.setZhName(vo.getGoodsInfo().getZhName());
		}else{
			goods.setZhName(vo.getGoodsInfo().getEnName());
		}
		goods.setEnName(vo.getGoodsInfo().getEnName());
		goods.setQuantity(vo.getGoodsInfo().getQuantity());
		goods.setQuantityUnit(vo.getGoodsInfo().getQuantityUnit());
		goods.setMoreLess(vo.getGoodsInfo().getMoreLess());
		goods.setCountryOrigin(vo.getGoodsInfo().getCountryOrigin());
		goods.setMeasureMode(vo.getGoodsInfo().getMeasureMode());
		goods.setSpec(vo.getGoodsInfo().getSpec());
		
		return goods;
		
	}
	
	//组装报价数据
	private OrderPrice buildOfferInfo(OrderInfoVO vo){
		OrderPrice offerInfo = new OrderPrice();
		offerInfo.setTradeTerm(vo.getOfferTradeTerm());
		offerInfo.setAgio(vo.getOfferPremium());
		offerInfo.setBasePrice(vo.getOfferBasePrice());
		offerInfo.setPricingDesc(vo.getOfferPricingDesc());
		offerInfo.setPriceFormula(vo.getOfferPriceFormula());
		offerInfo.setPriceFormulaJson(vo.getOfferPriceFormulaJson());
		offerInfo.setPaymentTerm(vo.getOfferPaymentTerm());
		offerInfo.setCreateDate(new Date());
		offerInfo.setPriceType(vo.getPricingType());
		offerInfo.setCurrency(vo.getGoodsInfo().getCurrency());
		offerInfo.setPaymentTermJson(vo.getOfferPaymentTermJson());
		offerInfo.setPayDate(vo.getOfferPayDate());
		
		return offerInfo;
	}
	
	//组装运输信息数据
	private OrderTransport buildTransportInfo(OrderInfoVO vo){
		OrderTransport transportInfo = new OrderTransport();
		transportInfo.setShipModel(vo.getOfferShipModel());
		transportInfo.setLoadingPort(vo.getOfferLoadingPort());
		transportInfo.setUploadingPort(vo.getOfferUnloadingPort());
		transportInfo.setDeliveryDateStart(vo.getOfferDeliveryDateStart());
		transportInfo.setDeliveryDateEnd(vo.getOfferDeliveryDateEnd());
		transportInfo.setDischargeDateStart(vo.getOfferDischargeDateStart());
		transportInfo.setDischargeDateEnd(vo.getOfferDischargeDateEnd());
		
		return transportInfo;
	}
	
	//组装买家信息数据
	private OrderParty buildBuyerInfo(OrderInfoVO vo){
		OrderParty buyerInfo = new OrderParty();
		
		buyerInfo.setCustomerId(vo.getBuyerCustomerId());
		buyerInfo.setCustomerName(vo.getBuyerCustomerName());
		buyerInfo.setAddress(vo.getBuyerCustomerAddress());
		buyerInfo.setContactName(vo.getBuyerContactName());
		buyerInfo.setTel(vo.getBuyerContactNumber());
		buyerInfo.setEmail(vo.getBuyerEmail());
		buyerInfo.setFax(vo.getBuyerFax());
		buyerInfo.setPartyType("1");
		
		return buyerInfo;
	}
	
	//组装卖家信息数据
	private OrderParty buildSellerInfo(OrderInfoVO vo){
		OrderParty sellerInfo = new OrderParty();
		
		sellerInfo.setCustomerId(vo.getSellerCustomerId());
		sellerInfo.setCustomerName(vo.getSellerCustomerName());
		sellerInfo.setAddress(vo.getSellerCustomerAddress());
		sellerInfo.setContactName(vo.getSellerContactName());
		sellerInfo.setTel(vo.getSellerContactNumber());
		sellerInfo.setEmail(vo.getSellerEmail());
		sellerInfo.setFax(vo.getSellerFax());
		sellerInfo.setPartyType("2");
		
		return sellerInfo;
	}
	
	//组装代理商信息数据
	private OrderParty buildAgentInfo(OrderInfoVO vo){
		OrderParty agentInfo = null;
		
		if(vo.getAgentCustomerId() != null){
			agentInfo = new OrderParty();
			
			agentInfo.setCustomerId(vo.getAgentCustomerId());
			agentInfo.setCustomerName(vo.getAgentCustomerName());
			agentInfo.setAddress(vo.getAgentCustomerAddress());
			agentInfo.setContactName(vo.getAgentContactName());
			agentInfo.setTel(vo.getAgentContactNumber());
			agentInfo.setEmail(vo.getAgentEmail());
			agentInfo.setFax(vo.getAgentFax());
			agentInfo.setPartyType("3");
		}
		
		return agentInfo;
	}
	
	//组装点价合约信息
	private TriggerContract buildTriggerContractInfo(OrderInfoVO vo,OrderPrice offerInfo){
		if(!"Trigger".equals(vo.getPricingType()))
			return null;
		TriggerContract triggerContract = new TriggerContract();
		triggerContract.setQuantity(vo.getGoodsInfo().getQuantity());
		triggerContract.setUuid(KeyGenUtils.newUuid());
		try {
			
			JSONObject pricingDetailJson = JSONObject.fromObject(vo.getOfferPriceFormulaJson());
			if(pricingDetailJson.get("trigger_pricing_detail") == null )
				throw new BizException("价格公式：点价公式格式不正确");
			JSONObject triggerDetailJson =JSONObject.fromObject(pricingDetailJson.getString("trigger_pricing_detail"));
			if(triggerDetailJson.get("quote") == null )
				throw new BizException("价格公式：点价公式格式不正确");
			JSONObject marketDetailJson =JSONObject.fromObject(triggerDetailJson.getString("quote"));
			if(marketDetailJson.get("commodity") == null)
				throw new BizException("价格公式：品类为空！");
			if(marketDetailJson.get("price_source") == null)
				throw new BizException("价格公式：价格源为空！");
			if(marketDetailJson.get("market") == null)
				throw new BizException("价格公式：市场为空！");
			triggerContract.setCommodity(marketDetailJson.getString("commodity"));
			triggerContract.setMarket(marketDetailJson.getString("market"));
			triggerContract.setPriceSource(marketDetailJson.getString("price_source"));
			triggerContract.setMonth(marketDetailJson.getString("period"));
			triggerContract.setName(marketDetailJson.getString("commodity")+"/"
					+marketDetailJson.getString("price_source")+"/"
					+marketDetailJson.getString("market"));
			triggerContract.setPriceType(marketDetailJson.getString("period"));
			triggerContract.setDoneQuantity(new BigDecimal("0"));
			triggerContract.setSurplusQuantity(vo.getGoodsInfo().getQuantity());
			triggerContract.setTransferQuantity(new BigDecimal("0"));
			if(triggerDetailJson.get("deadline") != null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMM/dd/yyyy",Locale.ENGLISH); 
	        Date d=dateFormat.parse(triggerDetailJson.getString("deadline"));
			triggerContract.setEndTime(d);
			}
			offerInfo.setTransferCount(triggerDetailJson.getInt("monthOuterationTime"));
			offerInfo.setLockQuantityMin(new BigDecimal(triggerDetailJson.getInt("minOperAbleNum")+""));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(e.getMessage());
		}
		
		
		return triggerContract;
	}
	//构建订单执行人列表信息，推送消息人员列表
	private List<OrderExecutePerson> buildExecuterList(OrderInfoVO vo){
		List<OrderExecutePerson> execList = new ArrayList<>();
		if(vo.getBuyerDealerId() != null) {
			OrderExecutePerson executer = new OrderExecutePerson();
			
			executer.setEpMemberId(vo.getBuyerCustomerId());
			executer.setEpMemberName(vo.getBuyerCustomerName());
			executer.setMemberId(vo.getBuyerDealerId());
			executer.setMemberName(vo.getBuyerDealerName());
			executer.setCreateDate(DateTimeUtils.currentDate());
			
			execList.add(executer);
		}
		
		if(vo.getSellerDealerId() != null) {
			OrderExecutePerson executer = new OrderExecutePerson();
			
			executer.setEpMemberId(vo.getSellerCustomerId());
			executer.setEpMemberName(vo.getSellerCustomerName());
			executer.setMemberId(vo.getSellerDealerId());
			executer.setMemberName(vo.getSellerDealerName());
			executer.setCreateDate(DateTimeUtils.currentDate());
			
			execList.add(executer);
		}
		
		return execList;
	}
	
	//构建实货数据JSON格式
	public String buildPriceJsonData(OrderInfoVO data, PartyTypeEnum partyType) throws BizException {
		
		PhysicalRecapVO vo = new PhysicalRecapVO();
		buildPhysicalRecapData(vo, data, partyType);
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"quantityBean"});
		JSONObject jsonRes = JSONObject.fromObject(vo, jsonConfig);
		
		//价格公式String转换成JSON对象
		if(StringUtils.isNotEmpty(vo.getPricing_detail())){
			try {
				JSONObject pricingDetailJson = JSONObject.fromObject(vo.getPricing_detail());
				jsonRes.element("pricing_detail", pricingDetailJson);
			} catch (Exception e) {
				throw new BizException("价格公式JSON格式错误", e);
			}
		}
		
		return "{\"records\":["+jsonRes.toString()+"]}";
	}
	
	//构建实货数据
	public void buildPhysicalRecapData(PhysicalRecapVO vo, OrderInfoVO data, PartyTypeEnum partyType){
		//*-------------------- paymentTerm --------------------
		PaymentTerm paymentTerm = new PaymentTerm();
		paymentTerm.setPayment_type(data.getOfferPaymentTerm());
		
		//*-------------------- quantity --------------------
		Quantity quantity = new Quantity();
		quantity.setMoreOrLess(data.getGoodsInfo().getMoreLess());
		quantity.setMoreOrLessSymbol(MoreOrLessSymbolEnum.PLUS_MINUS);
		quantity.setQuantity(data.getGoodsInfo().getQuantity());
		quantity.setQuantityUnit(data.getGoodsInfo().getQuantityUnit());
		
		//*-------------------- PhysicalRecapVO --------------------
		
		if(partyType == PartyTypeEnum.Buy){
			vo.setBooking(data.getBuyerCustomerName());
			vo.setCounterparty(data.getSellerCustomerName());
			vo.setBuysell(PartyTypeEnum.Buy);
		}else if(partyType == PartyTypeEnum.Sell){
			vo.setBooking(data.getSellerCustomerName());
			vo.setCounterparty(data.getBuyerCustomerName());
			vo.setBuysell(PartyTypeEnum.Sell);
		}
		
		vo.setId(data.getOrderUuid());
		vo.setAlias(data.getOrderNo());
		vo.setBasic_comments(data.getOrderRemark());
		vo.setCommodity(data.getGoodsInfo().getEnName());
		vo.setCredit_term(data.getOrderCreditTerm());
		vo.setDelivery_from(data.getOfferDeliveryDateStart());
		vo.setDelivery_term(data.getOfferTradeTerm());
		vo.setDelivery_to(data.getOfferDeliveryDateEnd());
		vo.setFormula_precision("3");
		vo.setPayment_term(paymentTerm);
		vo.setQuantityBean(quantity);
		vo.setTrade_date(data.getOrderTradeTime());
		vo.setPricing_detail(data.getOfferPriceFormulaJson());
		vo.setPricing_type(data.getPricingType());
		vo.setTradeCategory(data.getOrderTradeCategory());
		
	}
	
	/**
	 * 本地数据值集转换
	 * @param vo
	 */
	private void convertValueForLocalSys(OrderInfoVO vo){
		String value = null;
		//重量单位转换
//		value = ValueSetUtil.getValueZh(ValueSetGroupConstant.QUANTITY_UNIT, vo.getGoodsInfo().getQuantityUnit());
//		vo.getGoodsInfo().setQuantityUnit(value);
		
		//计量方式类型转换
//		value = ValueSetUtil.getValueZh(ValueSetGroupConstant.MEASURE_MODE, vo.getMeasureMode());
//		vo.getGoodsInfo().setMeasureMode(value);
//		vo.setMeasureMode(value);
		
		//计价方式
		value = ValueSetUtil.getValueZh(ValueSetGroupConstant.PRICE_TYPE, vo.getPricingType());
		vo.setPricingType(value);
		
//		//付款条款
//		value = ValueSetUtil.getValueZh(ValueSetGroupConstant.PAYMENT_TERM, vo.getOfferPaymentTerm());
//		vo.setOfferPaymentTerm(value);
		
		//贸易条款
		value = ValueSetUtil.getValueZh(ValueSetGroupConstant.TRADE_TERM, vo.getOfferTradeTerm());
		vo.setOfferTradeTerm(value);
		
		//货币单位
//		value = ValueSetUtil.getValueZh(ValueSetGroupConstant.CURRENCY, vo.getGoodsInfo().getCurrency());
//		vo.getGoodsInfo().setCurrency(value);
		
		//信用条款
		value = ValueSetUtil.getValueZh(ValueSetGroupConstant.CREDIT_TERM, vo.getOrderCreditTerm());
		vo.setOrderCreditTerm(value);
		
		//计价基准
		value = ValueSetUtil.getValueZh(ValueSetGroupConstant.BASE_PRICE, vo.getOfferBasePrice());
		vo.setOfferBasePrice(value);
		
		//商检机构
		value = ValueSetUtil.getValueZh(ValueSetGroupConstant.SURVEY, vo.getOrderSurvey());
		vo.setOrderSurvey(value);
		
		//商检费用分摊方式
		value = ValueSetUtil.getValueZh(ValueSetGroupConstant.INSPECTION_FEE_SHARING_TYPE, vo.getInspectionFeeSharingType());
		vo.setInspectionFeeSharingType(value);
	}
	
	//数据校验
	/**
	 * 
	 * @return
	 */
	private void dataValidate(OrderInfoVO data) {
		
		if(StringUtils.isBlank(data.getGoodsInfo().getEnName()) && StringUtils.isBlank(data.getGoodsInfo().getZhName())) {
			throw new BizException("商品名称不能为空");
		}
		if(StringUtils.isBlank(data.getBuyerCustomerName()) && StringUtils.isBlank(data.getSellerCustomerName())) {
			throw new BizException("买卖家不能为空");
		}
		if(StringUtils.isBlank(data.getOrderCreditTerm())) {
			throw new BizException("信用条款不能为空");
		}
//		if(data.getOfferDeliveryDateStart() == null) {
//			throw new BizException("装货开始时间不能为空");
//		}
//		if(data.getOfferDeliveryDateEnd() == null) {
//			throw new BizException("装货结束时间不能为空");
//		}
		if(StringUtils.isBlank(data.getOfferTradeTerm())) {
			throw new BizException("贸易条款不能为空");
		}
		if(StringUtils.isBlank(data.getOfferPaymentTerm())) {
			throw new BizException("付款条款不能为空");
		}
		if(data.getGoodsInfo().getMoreLess() == null) {
			throw new BizException("溢短装不能为空");
		}
		if(data.getGoodsInfo().getQuantity() == null) {
			throw new BizException("数量不能为空");
		}
		if(StringUtils.isBlank(data.getGoodsInfo().getQuantityUnit())) {
			throw new BizException("数量单位不能为空");
		}
		if(data.getOrderTradeTime() == null) {
			throw new BizException("成交时间不能为空");
		}
		if(StringUtils.isBlank(data.getPricingType())) {
			throw new BizException("计价类型不能为空");
		}
		if(StringUtils.isBlank(data.getOfferPriceFormulaJson())) {
			throw new BizException("计价公式不能为空");
		}
	}
	
	/**
	 * 外部系统数据值集转换
	 * @param vo
	 * @param sysName
	 */
//	private void convertValueForRemoteSys(OrderInfoVO vo, String sysName){
//		
//	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void cancelOrder(CancelOrderVO vo) {
		if(vo.getTradeId() == null)
			throw new BizException("订单ID不能为空");
		long tradeId = vo.getTradeId();
		Order findByTradeId = orderMapper.findByTradeId(tradeId);
		if(findByTradeId == null)
			throw new BizException("订单不存在");
		/*
		 * 操作类型
		 * 1：申请
		 * 2：同意
		 * 3：驳回
		 * 4：撤回
		 */
		findByTradeId.setCloseStatus(String.valueOf(vo.getCancelType()));
		findByTradeId.setUpdateUser(vo.getUserId());
		findByTradeId.setUpdateDate(new Date());
		switch (vo.getCancelType()) {
		case 1:
			
			break;
		case 2:
			findByTradeId.setStatus(OrderStatusEnum.STATUS_8.getCode());
			//TODO 待增加船务接口
			//确认取消订单时，调用船务取消接口
			shipService.cancelOrder(findByTradeId.getOrderNo(), vo.getUserId());
			break;
		case 3:
			
			break;
		case 4:
			
			break;
		default:
			throw new BizException("撤销指令有误");
		}
		orderMapper.updateRecordById(findByTradeId);
	}
}
