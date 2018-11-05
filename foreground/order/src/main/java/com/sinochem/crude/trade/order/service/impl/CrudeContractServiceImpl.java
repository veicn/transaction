package com.sinochem.crude.trade.order.service.impl;


import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.enums.BizType;
import com.sinochem.crude.trade.common.enums.DealType;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.order.constart.Constart;
import com.sinochem.crude.trade.order.constart.MsgConstart;
import com.sinochem.crude.trade.order.dao.ContractMapper;
import com.sinochem.crude.trade.order.dao.ContractRelevanterMapper;
import com.sinochem.crude.trade.order.dao.ContractShipMapper;
import com.sinochem.crude.trade.order.dao.CrudeOilLongTermContractPlanMapper;
import com.sinochem.crude.trade.order.dao.CrudeOilResourceMapper;
import com.sinochem.crude.trade.order.dao.ProductOilResourceMapper;
import com.sinochem.crude.trade.order.domain.Contract;
import com.sinochem.crude.trade.order.domain.ContractRelevanter;
import com.sinochem.crude.trade.order.domain.ContractShip;
import com.sinochem.crude.trade.order.domain.CrudeOilLongTermContractPlan;
import com.sinochem.crude.trade.order.domain.CrudeOilResource;
import com.sinochem.crude.trade.order.domain.ProductOilResource;
import com.sinochem.crude.trade.order.model.form.ContractListQueryForm;
import com.sinochem.crude.trade.order.model.query.ContractQuery;
import com.sinochem.crude.trade.order.model.query.StatisticsQuery;
import com.sinochem.crude.trade.order.model.result.ContractListResult;
import com.sinochem.crude.trade.order.model.result.CrudeStatisticsResult;
import com.sinochem.crude.trade.order.model.result.OrderStatusCountResult;
import com.sinochem.crude.trade.order.model.vo.DealNumStatisticsVO;
import com.sinochem.crude.trade.order.remote.OrderInfoService;
import com.sinochem.crude.trade.order.remote.OrderInfoVO;
import com.sinochem.crude.trade.order.service.CrudeContractService;
import com.sinochem.crude.trade.order.service.OrderMessageService;
import com.sinochem.crude.trade.order.util.DateUtil;
import com.sinochem.crude.trade.order.util.DictUtils;
import com.sinochem.crude.trade.order.util.NetUtil;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.page.PageUtils;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.order.status.service.OrderStatusService;

/**
 * @Description:
 * @Author : jasonxu
 * @Date: 16/11/2017
 */
@Service
public class CrudeContractServiceImpl implements CrudeContractService {
	@Autowired
	private ContractMapper contractMapper;
	@Autowired
	private ContractShipMapper contractShipMapper;
	@Autowired
	private ContractRelevanterMapper contractRelevanterMapper;
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	EnterpriseService enterpriseService;
	@Autowired
	CrudeOilResourceMapper crudeOilResourceMapper;

	@Autowired
	OrderStatusService orderStatusService;
	@Autowired
	ProductOilResourceMapper productOilResourceMapper;

	@Autowired
	CrudeOilLongTermContractPlanMapper crudeOilLongTermContractPlanMapper;

	@Autowired
	private OrderMessageService orderMessageService;

	@Autowired
	URLBroker docServer;

	private Logger logger = LoggerFactory.getLogger(CrudeContractServiceImpl.class);

	/**
	 * 查询订单详情
	 *
	 * @param contractId
	 * @return
	 */
	@Override
	public Contract queryContractInfo(Long contractId) {
		return contractMapper.selectByPrimaryKey(contractId);
	}

	/**
	 * 根据主键id查询订单详情
	 *
	 * @param orderId
	 * @return
	 */
	@Override
	public Contract queryContractInfoByOrderId(String orderId) {
		return contractMapper.selectByOrderNo(orderId);
	}

	@Override
	public PageInfoResult queryContractUnite(CurrentUser user,
											 ContractListQueryForm contractListQueryForm, PageInfo query) throws BizException {
		ContractQuery contractQuery = new ContractQuery();
		contractQuery.setMemberid(user.getEpMemberId());
		contractQuery.setRoleType(contractListQueryForm.getRoleType());

		if (contractListQueryForm != null && !StringUtils.isEmpty(contractListQueryForm.getOrderStatus())
				&& !"0".equals(contractListQueryForm.getOrderStatus())) {
			contractQuery
					.setOrderStatus(contractListQueryForm.getOrderStatus());
		}
		if (contractListQueryForm != null && !StringUtils.isEmpty(contractListQueryForm.getDealType())
				&& !"0".equals(contractListQueryForm.getDealType())) {
			if("1".equals(contractListQueryForm.getDealType())){
				contractQuery.setDealType("B");
			}else{
				contractQuery.setDealType("S");
			}

		}
		if (contractListQueryForm != null && !StringUtils.isEmpty(contractListQueryForm.getBizType())
				&& !"0".equals(contractListQueryForm.getBizType())) {
			contractQuery.setBizType(contractListQueryForm.getBizType());
		}
		if (contractListQueryForm != null && !StringUtils.isEmpty(contractListQueryForm.getContractType())
				&& !"0".equals(contractListQueryForm.getContractType())) {
			contractQuery.setContractType(contractListQueryForm.getContractType());
		}
		if (contractListQueryForm != null && !StringUtils.isEmpty(contractListQueryForm.getOrderByClause())) {
			contractQuery.setOrderByClause(contractListQueryForm.getOrderByClause());
		}else{
			//contractQuery.setOrderByClause("c.double_sign asc,c.create_time desc");
			contractQuery.setOrderByClause("c.create_time desc");
		}
		if(contractListQueryForm != null && !StringUtils.isEmpty(contractListQueryForm.getOrderNo())){
			contractQuery.setOrderNo(contractListQueryForm.getOrderNo());
		}

		String dateTime = contractListQueryForm != null ?contractListQueryForm.getDataTime():"";
		if (!StringUtils.isEmpty(dateTime) && !"0".equals(dateTime)) {
			Date startTime = null;
			if ("1".equals(dateTime)) {
				startTime = DateUtil.getDateTime(3);
			} else if ("2".equals(dateTime)) {
				startTime = DateUtil.getDateTime(6);
			} else if ("3".equals(dateTime)) {
				startTime = DateUtil.getDateTime(12);
			}
			if (dateTime != null) {
				contractQuery.setStartTime(startTime);
				contractQuery.setEndTime(new Date());
			}

		}
		PageUtils.page(query);
		List<ContractListResult> lists = contractMapper.uniteSelectByPrimaryKey(contractQuery);
		
		final CopyOnWriteArrayList<ContractListResult> cowList = new CopyOnWriteArrayList<ContractListResult>(lists);

		if (cowList != null && cowList.size() > 0) {
			for (ContractListResult contractListResult : cowList) {
				
				if(StringUtils.isNotBlank(contractListResult.getDischargePort()) && DictUtils.getUnLoadPortMap().keySet().contains(contractListResult.getDischargePort())){
					String[][] unLoadPortValue = DictUtils.getUnLoadPortValue(contractListResult.getDischargePort());
					String unLoadPort = VisitorLocale.getByCurrentLanguage(unLoadPortValue);
					contractListResult.setDischargePort(unLoadPort);
				}
				
				if("L".equals(contractListResult.getContractType())){	
					Long contractId = contractListResult.getContractId();
					Map map = resouseOil(contractId);
					contractListResult.setCrudeOilOptions((String) map.get("resouseOil"));
					
				}else{
					//获取订单状态
					contractListResult.setOrderStatus(orderStatusService.checkOrderStatus(contractListResult.getContractId()));
				}
				//处理成品油
				if("ProductOil".equals(contractListResult.getBizType())){
					Map<Object,String> classMap = DictUtils.getProductOilClassifyMap();
					Map<Integer,String> kindMap = DictUtils.getProductOilKind();
					Map<Integer,String> specsMap = DictUtils.getProductOilSpecs();

					if(classMap != null){
						contractListResult.setCrudeOilOptionsName(classMap.get(contractListResult.getCrudeOilOptions()));
					}
					if(kindMap != null){
						contractListResult.setProductOilKindName(kindMap.get(contractListResult.getProductOilKind()));
					}
					if(specsMap != null){
						contractListResult.setProductOilSpecsName(specsMap.get(contractListResult.getProductOilSpecs()));
					}
				}
				if(contractListResult.getBuyer() != null){
					Map<Long, String> nameMap = enterpriseService.getEnterpriseName(new Long[]{contractListResult.getBuyer()},VisitorLocale.getCurrent());
					contractListResult.setBuyerName(nameMap.get(contractListResult.getBuyer()));
				}
				if(contractListResult.getSeller() != null){
					Map<Long, String> nameMap = enterpriseService.getEnterpriseName(new Long[]{contractListResult.getSeller()},VisitorLocale.getCurrent());
					contractListResult.setSellerName(nameMap.get(contractListResult.getSeller()));
				}
				//买家订单
				Long epId = null;
				if(contractListResult.getExecutor() == contractListResult.getBuyer()){
					epId = contractListResult.getBuyer();
				}else if(contractListResult.getExecutor() == contractListResult.getSeller()){//卖方
					epId = contractListResult.getSeller();
				}else if(contractListResult.getExecutor() == contractListResult.getTrader()){//中间商
					epId = contractListResult.getTrader();
				}
				if(epId != null){
					EnterpriseVo enterprise = enterpriseService.selectByPrimaryKey(contractListResult.getBuyer(),null);
					contractListResult.setMemberName(enterprise == null ? "":enterprise.getName());
				}
				if("00".equals(contractListResult.getDoubleSign()) && !user.getEpMemberId().equals(contractListResult.getInitiator())){
					cowList.remove(contractListResult);
				}
			}
		}
		return new PageInfoResult<>(cowList);
	}
	
	/**
	 * 卸货港中英文转换
	 * @return
	 */
	private String dischargePortSetValue(String dischargePort){
		HttpConnectionHelper httpConnectionHelper = ContextLoader.getCurrentWebApplicationContext().getBean(HttpConnectionHelper.class);
		URLBroker shipServer = (URLBroker) ContextLoader.getCurrentWebApplicationContext().getBean("shipServer");
		try {
			String url = shipServer.get("/sysShip/queryValueSet.json").toString();
			JSONObject requestJson = new JSONObject();
			requestJson.put("valueSetType", "4");
			
			JSONObject responseJson;
			responseJson = httpConnectionHelper.httpPost(requestJson, url);
			Integer status = responseJson.getInteger("status");
			if (status == null || Result.ERROR == status.intValue()) {
				throw new RuntimeException("响应失败");
			}
			
			JSONArray dataJsonArray = responseJson.getJSONArray("datas");
			List<Map<String,Object>> mapListJson = (List)dataJsonArray;
			for (Map<String, Object> map : mapListJson) {
				//if(dischargePort.equals(map.get("value"))){
					String code =(String)map.get("code");
					//TODO
				//}
			}
			
			if (dataJsonArray == null) {
				throw new RuntimeException("数据获取失败");
			}
		} catch (Exception e) {
			logger.error(e + "");
		}
		return null;
	}

	/**
	 * 统计合约定点状态
	 *
	 * @return
	 */
	@Override
	public OrderStatusCountResult getCountOrderStatus() {
		return contractMapper.countOrderStatus();
	}

	/**
	 * 根据UUID查询订单详情
	 *
	 * @param uuid
	 * @return
	 */
	@Override
	public Contract queryContractInfoByUUID(String uuid) {
		return contractMapper.selectByUUID(uuid);
	}

	@Override
	public void pushOrderInfo(Long contractId, Long oper) throws BizException {
		logger.info("check order contractId:"+contractId+" oper:"+oper);
		Contract contract = contractMapper.selectByPrimaryKey(contractId);
		if(contract != null) {
			// 1  初始化order-bill
			try {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("service", "createTask");
				jsonObject.put("memberId", oper);
				jsonObject.put("objectId", contract.getId());

				if (BizType.CRUDE_OIL.getCode().equals(contract.getBizType())) {
					jsonObject.put("bizTypeId", 1);
				} else if (BizType.PRODUCT_OIL.getCode().equals(contract.getBizType())) {
					jsonObject.put("bizTypeId", 2);
				}
				String str = NetUtil.post(docServer.get("/api/service.json").toString(), jsonObject);
				JSONObject retJson = JSONObject.parseObject(str);
				if (!"200".equals(retJson.getString("status"))) {
					logger.error("order-bill执行失败：" + retJson);
					throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0022));
				}
			} catch (IOException e) {
				logger.error("order-bill 执行失败：" + e);
				throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0023));
			}
			// 2.数据组装
			OrderInfoVO orderInfoVO = new OrderInfoVO();
			/**----------------------- 订单信息 -----------------------*/
			orderInfoVO.setInspectionFeeSharingType(contract.getInspectionFeeSharingType() + ""); //商检费分摊
			orderInfoVO.setLaw(contract.getLaw()); // 法律
			orderInfoVO.setGtc(contract.getGtc()); //GTC
			orderInfoVO.setCreateUserId(oper);//当前用户id
			if (!StringUtils.isEmpty(contract.getNumFloat()))//溢短装
				orderInfoVO.getGoodsInfo().setMoreLess(new BigDecimal(contract.getNumFloat()));
			if (contract.getNum() != null) {
				orderInfoVO.getGoodsInfo().setQuantity(BigDecimal.valueOf(contract.getNum()).divide(new BigDecimal(1000)));//必填
			}
			if (contract.getUnit() == null){
				orderInfoVO.getGoodsInfo().setQuantityUnit("1");
			}else {
				orderInfoVO.getGoodsInfo().setQuantityUnit(contract.getUnit().toString());//必填
			}
			orderInfoVO.setOrderUuid(contract.getUuid());
			orderInfoVO.setOrderNo(contract.getOrderNo());
			orderInfoVO.setOrderTradeId(contract.getId());//交易流水号
			if (contract.getDealType() != null && "B".equals(contract.getDealType())) {
				orderInfoVO.setOrderBuySell("1");
			}
			if (contract.getDealType() != null && "S".equals(contract.getDealType())) {
				orderInfoVO.setOrderBuySell("2");
			}
			//TODO zj 临时处理方法
			if(StringUtil.isNotBlank(contract.getValuationFormulaJson())
					&& contract.getValuationFormulaJson().indexOf("fixed_pricing_detail") > 0){
				orderInfoVO.setPricingType("04");//fixed
			}
			if(StringUtil.isNotBlank(contract.getValuationFormulaJson())
					&& contract.getValuationFormulaJson().indexOf("average_pricing_detail") > 0){
				orderInfoVO.setPricingType("01");//average
			}
			if(StringUtil.isNotBlank(contract.getValuationFormulaJson())
					&& contract.getValuationFormulaJson().indexOf("event_pricing_detail") > 0){
				orderInfoVO.setPricingType("03");//event
			}
			if(StringUtil.isNotBlank(contract.getValuationFormulaJson())
					&& contract.getValuationFormulaJson().indexOf("trigger_pricing_detail") > 0){
				orderInfoVO.setPricingType("05");//trigger
			}
			if(StringUtil.isNotBlank(contract.getValuationFormulaJson())
					&& contract.getValuationFormulaJson().indexOf("complex_pricing_detail") > 0){
				orderInfoVO.setPricingType("02");//complex
			}
			if (contract.getPurchaseMode() != null ) {
				orderInfoVO.setOrderTradeMode(contract.getPurchaseMode().toString());
			}
			orderInfoVO.setOrderTradeTime(new Date());//成交时间
			if ("CrudeOil".equals(contract.getBizType())) {
				orderInfoVO.setOrderTradeCategory("1");
			} else if ("ProductOil".equals(contract.getBizType())) {
				orderInfoVO.setOrderTradeCategory("2");
			} else {
				orderInfoVO.setOrderTradeCategory("3");
			}
			if(contract.getPurchaseType() != null)
			    orderInfoVO.setOrderTradeType(contract.getPurchaseType().toString());
			orderInfoVO.setOrderCreditTerm(contract.getAuthItem());
			orderInfoVO.setOrderOtherTerm(contract.getOtherItem());
			orderInfoVO.setOrderSurvey(contract.getBusinessCheckOrg());
			orderInfoVO.setOrderCreditModel(contract.getAuthItem());
			orderInfoVO.setOrderQuatoFile(contract.getExportConfFile());
			orderInfoVO.setOrderRemark(contract.getRemark());
			orderInfoVO.setMeasureMode(String.valueOf(contract.getMeasureMode()));
			orderInfoVO.setLoadAndDischargePermittedTimespan(contract.getLoadAndDischargePermittedTimespan());
			// 计价公式相关数据
			orderInfoVO.setOfferPriceFormula(contract.getValuationFormula());
			orderInfoVO.setOfferPriceFormulaJson(contract.getValuationFormulaJson());

			/**----------------------- 报价信息 -----------------------*/
			orderInfoVO.setOfferTradeTerm(convertNumber(contract.getTradeItem()));
			if (contract.getAgio() != null)
				orderInfoVO.setOfferPremium(BigDecimal.valueOf(contract.getAgio()).divide(new BigDecimal(1000)));
			orderInfoVO.setOfferBasePrice(contract.getValuationBase());
			//判断是否存在计价期//只有Period类型有计价期
			if(contract.getValuationProidType() != null){
				if(contract.getValuationProidType().intValue() == 4){
					String type = DictUtils.getValue("valuationProidTypeMap", contract.getValuationProidType().toString()).toString();
					String start = "";
					String end = "";
					if(contract.getValuationProidStart() != null) {
						start = DateUtil.convertStr(contract.getValuationProidStart());
					}
					if(contract.getValuationProidEnd() != null) {
						end = DateUtil.convertStr(contract.getValuationProidEnd());
					}
					orderInfoVO.setOfferPricingDesc(type + "(" + start + "-" + end + ")");
				}else if(contract.getValuationProidType().intValue() == 5){
					String type = DictUtils.getValue("valuationProidTypeMap", contract.getValuationProidType().toString()).toString();
					orderInfoVO.setOfferPricingDesc(type + "(" + contract.getContractKind()  + ")");
				}else{
					String type = DictUtils.getValue("valuationProidTypeMap", contract.getValuationProidType().toString()).toString();
					orderInfoVO.setOfferPricingDesc(type);
				}
			}
			orderInfoVO.setOfferPriceFormula(contract.getValuationFormula());
			orderInfoVO.setOfferPaymentTerm(contract.getPayItem());
			orderInfoVO.setOfferPaymentTermJson(contract.getPayItemJSON());
			orderInfoVO.setOfferPayDate(contract.getPayTime());


			ContractRelevanter buyerParm = new ContractRelevanter();
			buyerParm.setType("B");
			buyerParm.setContractId(contractId);
			ContractRelevanter buyer = contractRelevanterMapper.selectByCondition(buyerParm);

			if (buyer != null) {
				/**----------------------- 买家信息 -----------------------*/
				orderInfoVO.setBuyerCustomerId(buyer.getEMemberId());
				orderInfoVO.setBuyerCustomerName(buyer.geteMemberName());
				orderInfoVO.setBuyerCustomerAddress(buyer.getAddress());
				orderInfoVO.setBuyerContactName(buyer.getContacter());
				orderInfoVO.setBuyerContactNumber(buyer.getPhoneNo());
				orderInfoVO.setBuyerEmail(buyer.getEmail());
				orderInfoVO.setBuyerFax(buyer.getFax());

				// 设置交易员信息  by sijliu  2017-12-27
				orderInfoVO.setBuyerDealerId(buyer.getDealerId());
				orderInfoVO.setBuyerDealerName(buyer.getDealerName());

			}
			ContractRelevanter sellerParm = new ContractRelevanter();
			sellerParm.setType("S");
			sellerParm.setContractId(contractId);
			ContractRelevanter seller = contractRelevanterMapper.selectByCondition(sellerParm);

			if (seller != null) {
				/**----------------------- 卖家信息 -----------------------*/
				orderInfoVO.setSellerCustomerId(seller.getEMemberId());
				orderInfoVO.setSellerCustomerName(seller.geteMemberName());
				orderInfoVO.setSellerCustomerAddress(seller.getAddress());
				orderInfoVO.setSellerContactName(seller.getContacter());
				orderInfoVO.setSellerContactNumber(seller.getPhoneNo());
				orderInfoVO.setSellerEmail(seller.getEmail());
				orderInfoVO.setSellerFax(seller.getFax());

				// 设置交易员信息  by sijliu  2017-12-27
				orderInfoVO.setSellerDealerId(seller.getDealerId());
				orderInfoVO.setSellerDealerName(seller.getDealerName());
			}

			ContractRelevanter traderParm = new ContractRelevanter();
			traderParm.setType("T");
			traderParm.setContractId(contractId);
			ContractRelevanter trader = contractRelevanterMapper.selectByCondition(traderParm);

			if (trader != null) {
				/**----------------------- 代理商信息 -----------------------*/
				orderInfoVO.setAgentCustomerId(trader.getEMemberId());
				orderInfoVO.setAgentCustomerName(trader.geteMemberName());
				orderInfoVO.setAgentCustomerAddress(trader.getAddress());
				orderInfoVO.setAgentContactName(trader.getContacter());
				orderInfoVO.setAgentContactNumber(trader.getPhoneNo());
				orderInfoVO.setAgentEmail(trader.getEmail());
				orderInfoVO.setAgentFax(trader.getFax());

				// 设置交易员信息  by sijliu  2017-12-27
				orderInfoVO.setAgentDealerId(trader.getDealerId());
				orderInfoVO.setAgentDealerName(trader.getDealerName());
			}

			ContractShip contractShip = contractShipMapper.queryByContractId(contractId);
			if (contractShip != null) {
				/**----------------------- 船务信息 -----------------------*/
				orderInfoVO.setOfferShipModel(convertNumber(contractShip.getTransportModes()));
				orderInfoVO.setOfferLoadingPort(contractShip.getShipmentPort());
				orderInfoVO.setOfferUnloadingPort(contractShip.getDischargePort());
				orderInfoVO.setOfferDeliveryDateStart(contractShip.getShipmentStartTime());
				orderInfoVO.setOfferDeliveryDateEnd(contractShip.getShipmentEndTime());
				orderInfoVO.setOfferDischargeDateStart(contractShip.getDischargeStartTime());
				orderInfoVO.setOfferDischargeDateEnd(contractShip.getDischargeEndTime());
			}
			CrudeOilResource crudeOilResource = crudeOilResourceMapper.selectByPrimaryKey(contractId);
			if (crudeOilResource != null) {
				orderInfoVO.getGoodsInfo().setZhName(crudeOilResource.getCnName());
				orderInfoVO.getGoodsInfo().setCountryOrigin(crudeOilResource.getOrigin() == null ? "" : crudeOilResource.getOrigin().toString());
				orderInfoVO.getGoodsInfo().setEnName(crudeOilResource.getName());
				if(crudeOilResource.getPropertyId() != null)
					orderInfoVO.getGoodsInfo().setResourceId(crudeOilResource.getPropertyId().intValue());
			}
			ProductOilResource productOilResource = productOilResourceMapper.selectByPrimaryKey(contractId);
			if (productOilResource != null) {
				orderInfoVO.getGoodsInfo().setZhName(productOilResource.getCnName());
				orderInfoVO.getGoodsInfo().setCountryOrigin(productOilResource.getOrigin() == null ? "" : productOilResource.getOrigin().toString());
				if(productOilResource.getName() != null){
					orderInfoVO.getGoodsInfo().setEnName(productOilResource.getName());
				}else{
                    Map<Integer,String> KindMap = DictUtils.getProductOilKind();
                    if(KindMap != null && productOilResource.getProductOilKind() != null){
                        orderInfoVO.getGoodsInfo().setEnName(KindMap.get(productOilResource.getProductOilKind()));
                    }
                    Map<Integer,String> specsMap = DictUtils.getProductOilSpecs();
                    if(specsMap != null && productOilResource.getProductOilSpecs() != null){
                        orderInfoVO.getGoodsInfo().setSpec(specsMap.get(productOilResource.getProductOilSpecs()));
                    }
				}

				if(productOilResource.getPropertyId() != null)
					orderInfoVO.getGoodsInfo().setResourceId(productOilResource.getPropertyId().intValue());
				orderInfoVO.getGoodsInfo().setMeasureMode("1");
			}

			logger.info("推送信息：" + JSONObject.toJSONString(orderInfoVO));
//			throw new BizException("订单创建失败测试数据");
			try {
				// 3  推送数据给订单执行
				orderInfoService.createOrder(orderInfoVO);
				// 4  初始化状态机
				if(orderStatusService.checkOrderStatus(contractId) == -1){
					orderStatusService.initOrderStatus(contractId, Contract.ORDER_STATUS_CONFIG, oper);
				}
			}catch(BizException e){
				logger.error("订单创建失败：" , e);
				throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0024));
			}catch (Exception e){
				logger.error("订单创建失败：" , e);
				throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0024));
			}
		}else{
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0025));
		}
	}

	@Override
	public List<DealNumStatisticsVO> statisticsOilDealDatas(StatisticsQuery statisticsQuery) {
		Calendar nowdate = Calendar.getInstance();
		int year = nowdate.get(Calendar.YEAR);
		int month = nowdate.get(Calendar.MARCH);

		int arrlength = 0;
		int paramYear = Integer.valueOf(statisticsQuery.getYear());
		if (year == paramYear)
			arrlength = month + 1;
		else if (year < paramYear)
			arrlength = 0;
		else
			arrlength = 12;

		List<DealNumStatisticsVO> list = new ArrayList<>();
		if (0 < arrlength) {

			statisticsQuery.setDealType(DealType.BUY.getCode());
			List<CrudeStatisticsResult> buyStatisticsList = contractMapper.statisticeDealNumData(statisticsQuery);
			// 格式化列表数据
			List<CrudeStatisticsResult> buylist = formatStatisticsListData(buyStatisticsList, arrlength, statisticsQuery.getYear());

			statisticsQuery.setDealType(DealType.SELL.getCode());
			List<CrudeStatisticsResult> sellStatisticsList = contractMapper.statisticeDealNumData(statisticsQuery);
			// 格式化列表数据
			List<CrudeStatisticsResult> sellist = formatStatisticsListData(sellStatisticsList, arrlength, statisticsQuery.getYear());

			DealNumStatisticsVO buy = new DealNumStatisticsVO();
			buy.setLegendName(VisitorLocale.getByCurrentLanguage(Constart.ORDERTYPE02));
			buy.setList(buylist);
			list.add(buy);
			DealNumStatisticsVO sell = new DealNumStatisticsVO();
			sell.setLegendName(VisitorLocale.getByCurrentLanguage(Constart.ORDERTYPE01));
			sell.setList(sellist);
			list.add(sell);
		}

		return list;
	}

	private static String convertNumber(Integer num){
		return num == null ? "" : String.format("%02d", num);
	}

	private Map<Object,Object> resouseOil(Long contractId){
		Map map = new HashMap();
		List<CrudeOilLongTermContractPlan> list = crudeOilLongTermContractPlanMapper.selectByContractId(contractId);
		ArrayList<String> al = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		for (CrudeOilLongTermContractPlan crudeOilLongTermContractPlan:list) {
			Long oilId = crudeOilLongTermContractPlan.getCrudeOilId();
			CrudeOilResource crudeOilResource = crudeOilResourceMapper.selectById(oilId);
			if(crudeOilResource != null ){
				set.add(crudeOilLongTermContractPlan.getDate());
				al.add(crudeOilResource.getName());
				
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Date now = new Date();
		int endTime = CollectionUtils.isNotEmpty(set)?Collections.max(set):Integer.valueOf(sdf.format(now));
		int startTime = CollectionUtils.isNotEmpty(set)?Collections.min(set):Integer.valueOf(sdf.format(now));
		//String resouseOil = StringUtils.join(al,",");
		if(al != null && al.size() > 0){
			String resouseOil = al.get(0);
			map.put("resouseOil",resouseOil);
		}	
		
		map.put("endTime",endTime);
		map.put("startTime",startTime);
		return map;
	}

	/**
	 * 将数据库中查出的数据 格式化 为 按年月顺序的列表
	 * @param list 数据
	 * @param arrlength 格式化后数据长度
	 * @param querYear 年份
	 */
	private List<CrudeStatisticsResult> formatStatisticsListData(List<CrudeStatisticsResult> list, int arrlength, String querYear) {
		List<CrudeStatisticsResult> newList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(list)) {

			for (int i=1; i<=arrlength; i++) {
				String ym = querYear + "-" + (i<10?('0'+String.valueOf(i)):String.valueOf(i));
				boolean flag = true;
				for (CrudeStatisticsResult obj : list) {
					if (ym.equals(obj.getYm())) {
						newList.add(obj);
						flag = false;
						break;
					}
				}
				if (flag)
					newList.add(new CrudeStatisticsResult(0.0, ym));
			}
		} else {
			for (int i=1; i<=arrlength; i++) {
				String ym = querYear + "-" + (i<10?('0'+String.valueOf(i)):String.valueOf(i));
				newList.add(new CrudeStatisticsResult(0.0, ym));
			}
		}
		return newList;
	}

	@Override
	public void updateOrderStatusById(String orderStatus, Long id) {
		contractMapper.updateOrderStatusById(orderStatus, id);
	}
}
