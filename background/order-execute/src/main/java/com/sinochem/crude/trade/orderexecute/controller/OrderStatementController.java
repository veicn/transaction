package com.sinochem.crude.trade.orderexecute.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eyeieye.melody.web.locale.VisitorLocale;
import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.Page;
import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.SequenceUtils;
import com.sinochem.crude.trade.common.utils.StringUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.ValueSet;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.commons.constants.OrderStatusEnum;
import com.sinochem.crude.trade.orderexecute.commons.constants.StatementStatusEnum;
import com.sinochem.crude.trade.orderexecute.commons.constants.ValueSetGroupConstant;
import com.sinochem.crude.trade.orderexecute.commons.utils.ValueSetUtil;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderFeeItem;
import com.sinochem.crude.trade.orderexecute.domain.OrderStatement;
import com.sinochem.crude.trade.orderexecute.domain.TriggerResult;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.OrderStatementVO;
import com.sinochem.crude.trade.orderexecute.model.SelerInfoFinVO;
import com.sinochem.crude.trade.orderexecute.model.SellerListPreVO;
import com.sinochem.crude.trade.orderexecute.model.StatementMsgParamVO;
import com.sinochem.crude.trade.orderexecute.query.OrderStatementQuery;
import com.sinochem.crude.trade.orderexecute.service.OrderFeeItemService;
import com.sinochem.crude.trade.orderexecute.service.OrderMsgRemindingService;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.crude.trade.orderexecute.service.OrderStatementService;
import com.sinochem.crude.trade.orderexecute.service.TriggerResultService;
import com.sinochem.it.b2b.member.access.ApiSafeAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;

@Controller
public class OrderStatementController {

	@Autowired
	private OrderStatementService orderStatementService;

	@Autowired
	private OrderFeeItemService orderFeeItemService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private TriggerResultService triggerResultService;
	
	@Autowired
	private OrderMsgRemindingService msgRemindingService;
	
	@Resource(name="orderExecuteServer")
	private URLBroker orderExecuteServer;
	
	private static final Log log = LogFactory.getLog(OrderStatementController.class);
	
	/**
	 * 拆单列表
	 * @param request
	 * @param user
	 * @param modelMap
	 */
	@RequestMapping(value = UrlMapping.TRIGGER_RESULT_LIST)
	public void queryTriggerResultList(HttpServletRequest request, CurrentUser user, ModelMap modelMap) {
		Long orderId = Long.parseLong(String.valueOf(request.getAttribute("orderId")));
		//查询订单是否已经点价（计价类型为trigger和合约数量与拆单数量相等）
		int count = orderStatementService.queryIsTrigger(orderId);
		modelMap.put("isTrigger", Constants.IS_TRIGGER_0);
		//拆单列表信息
		if(count > 0){
			TriggerResult triggerResult = new TriggerResult();
			triggerResult.setOrderId(orderId);
			List<TriggerResult> triggerResultList = triggerResultService.queryByEntitys(triggerResult);
			if(CollectionUtils.isNotEmpty(triggerResultList)){
				modelMap.put("isTrigger", Constants.IS_TRIGGER_1);
				modelMap.put("triggerResultList", triggerResultList);
			}
		}
		
	}
	
	/**
	 * 临时对账单编辑页面
	 * @param vo
	 * @param user
	 * @param modelMap
	 */
	@RequestMapping(value = UrlMapping.S_STATEMENT_INFO_PRE)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public void sellerInfoPre(OrderStatementVO vo, CurrentUser user,ModelMap modelMap) {
		String isEdit;
		Map<String, Object> map = new HashMap<>();
		Order order;
		if(StringUtils.isNotBlank(vo.getUuid()))
			order = orderService.findByUuid(vo.getUuid());
		else if(StringUtils.isNotBlank(vo.getOrderStatementUuid())){			
			OrderStatement statement = orderStatementService.findByUuid(vo.getOrderStatementUuid());
			order = orderService.findByPrimaryKey(statement.getOrderId());
		}else
			throw new OrderExecException("orderexecute.code.00029","订单ID或对账单ID为空");
			
		if(order == null)
			throw new OrderExecException("orderexecute.code.00014","订单不存在");
		Long orderId = order.getId();
		vo.setOrderId(order.getId());
		vo.setUuid(order.getUuid());
		//订单相关信息查询
		Map<String, Object> inforData = orderStatementService.selectinformation(order.getId());

		//查询是否已经生成对账单
		map.put("orderId", orderId);
		map.put("statementType", Constants.STATEMENT_TYPE_S);
		map.put("orderStatementUuid", vo.getOrderStatementUuid());
		List<Map<String, Object>> statementList = orderStatementService.queryRecords(map);

		Map<String, Object> stateData = new HashMap<>();
		List<OrderFeeItem> feeDataList = new ArrayList<>();
		
		if(CollectionUtils.isNotEmpty(statementList)){
			//最新一条对账单
			stateData = statementList.get(0);
			//校验对账单状态
			if (Constants.STATEMENT_STATUS_01.equals(stateData.get("status")))
				isEdit = "1";//初始状态允许修改
			else if (Constants.STATEMENT_STATUS_20.equals(stateData.get("status")))
				isEdit = "2";//驳回状态允许重新生成
			else
				isEdit = "0";//不允许操作

			if(StringUtils.isEmpty(vo.getIsAgain())){
				//查询对账单对应费用信息
				Map<String, Object> paraMap = new HashMap<>();
				paraMap.put("relatId", stateData.get("orderStatementId"));
				paraMap.put("relatType", Constants.FEE_TYPE_1);
				paraMap.put("statFee", "1");
				
				feeDataList = orderFeeItemService.queryRecords(paraMap);
				
			}else{//修改标记为1 进入修改页面 修改时获取最新费用信息 
				Map<String, Object> feeData =orderStatementService.selectData(vo, user);
				if(feeData != null){
				stateData.put("orderFeeId", feeData.get("orderFeeId"));
				stateData.put("totalFee", feeData.get("totalFee"));
				}
				//查询费用信息
				feeDataList = orderFeeItemService.selectOrderFeeItem(orderId);
				isEdit = "3";//进入修改页面时均可保存
				if("2".equals(vo.getIsAgain()))
					stateData.put("orderStatementId", "");
			}
		} else {
			//新增时 查询订单信息
			stateData = orderStatementService.selectData(vo, user);
			stateData.put("statementType",  Constants.STATEMENT_TYPE_S);
			stateData.put("checkTime", DateTimeUtils.currentDate());
			stateData.put("orderStatementNo", SequenceUtils.nextSequence("PRE"));
			//查询费用信息
			feeDataList = orderFeeItemService.selectOrderFeeItem(orderId);
			isEdit = "3";
		}
		
		//国际化名称转换
		ValueSet i18nValueSet = ValueSetUtil.instance(ValueSetGroupConstant.SUBJECT_LIST);
		for(OrderFeeItem feeData : feeDataList) {
			String subjectCode = feeData.getSubjectCode();
			String subjectName = feeData.getSubjectName();
			feeData.setSubjectName(i18nValueSet.getByCode(subjectCode,subjectName).getValue());
		}
				
		stateData.put("uuid", vo.getUuid());
		stateData.put("orderId", vo.getOrderId());
		modelMap.put("stateData", stateData);
		modelMap.put("inforData", inforData);
		modelMap.put("feeData", feeDataList);
		modelMap.put("isEdit", isEdit);
	}
	
	/**
	 * 查询对账信息分页列表 卖家临时结算单详情@param vo @return @exception
	 */
	
	@RequestMapping(value = UrlMapping.S_STATEMENT_INFO_PRE_JSON)
	@ResponseBody
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	@ApiSafeAccess
	public ResultDatas<SelerInfoFinVO> sellerInfoPreJson(OrderStatementVO vo, CurrentUser user, ModelMap modelMap) {
		ResultDatas<SelerInfoFinVO> resultData = new ResultDatas<>();
		try {
			String isEdit;
			Map<String, Object> map = new HashMap<>();
			Order order;
			if(StringUtils.isNotBlank(vo.getUuid()))
				order = orderService.findByUuid(vo.getUuid());
			else if(StringUtils.isNotBlank(vo.getOrderStatementUuid())){
				OrderStatement statement = orderStatementService.findByUuid(vo.getOrderStatementUuid());
				order = orderService.findByPrimaryKey(statement.getOrderId());
			}else
				throw new OrderExecException("orderexecute.code.00029","订单ID或对账单ID为空");
				
			if(order == null)
				throw new OrderExecException("orderexecute.code.00014","订单不存在");
			Long orderId = order.getId();
			vo.setOrderId(order.getId());
			vo.setUuid(order.getUuid());
			//订单相关信息查询
			Map<String, Object> inforData = orderStatementService.selectinformation(order.getId());

			//查询是否已经生成对账单
			map.put("orderId", orderId);
			map.put("statementType", Constants.STATEMENT_TYPE_S);
			map.put("orderStatementUuid", vo.getOrderStatementUuid());
			List<Map<String, Object>> statementList = orderStatementService.queryRecords(map);

			Map<String, Object> stateData = new HashMap<>();
			List<OrderFeeItem> feeDataList = new ArrayList<>();
			
			if(CollectionUtils.isNotEmpty(statementList)){
				//最新一条对账单
				stateData = statementList.get(0);
				//校验对账单状态
				if (Constants.STATEMENT_STATUS_01.equals(stateData.get("status")))
					isEdit = "1";//初始状态允许修改
				else if (Constants.STATEMENT_STATUS_20.equals(stateData.get("status")))
					isEdit = "2";//驳回状态允许重新生成
				else
					isEdit = "0";//不允许操作
				
				if(StringUtils.isEmpty(vo.getIsAgain())){
					//查询对账单对应费用信息
					Map<String, Object> paraMap = new HashMap<>();
					paraMap.put("relatId", stateData.get("orderStatementId"));
					paraMap.put("relatType", Constants.FEE_TYPE_1);
					paraMap.put("statFee", "1");
					feeDataList = orderFeeItemService.queryRecords(paraMap);
					
				}else{//修改标记为1 进入修改页面 修改时获取最新费用信息 
					Map<String, Object> feeData =orderStatementService.selectData(vo, user);
					if(feeData != null){
					stateData.put("orderFeeId", feeData.get("orderFeeId"));
					stateData.put("totalFee", feeData.get("totalFee"));
					}
					//查询费用信息
					feeDataList = orderFeeItemService.selectOrderFeeItem(orderId);
					isEdit = "3";//进入修改页面时均可保存
					if("2".equals(vo.getIsAgain()))
						stateData.put("orderStatementId", "");
				}
			} else {
				//新增时 查询订单信息
				stateData = orderStatementService.selectData(vo, user);
				stateData.put("statementType",  Constants.STATEMENT_TYPE_S);
				stateData.put("checkTime", DateTimeUtils.currentDate());
				stateData.put("orderStatementNo", SequenceUtils.nextSequence("PRE"));
				//查询费用信息
				feeDataList = orderFeeItemService.selectOrderFeeItem(orderId);
				isEdit = "3";
			}
						
			stateData.put("uuid", vo.getUuid());
			stateData.put("orderId", vo.getOrderId());
			modelMap.put("stateData", stateData);
			modelMap.put("inforData", inforData);
			modelMap.put("feeData", feeDataList);
			modelMap.put("isEdit", isEdit);
			
			SelerInfoFinVO selerInfoFinVO = new SelerInfoFinVO();
			selerInfoFinVO.setStateData(stateData);
			selerInfoFinVO.setInforData(inforData);
			selerInfoFinVO.setFeeDataList(feeDataList);
			selerInfoFinVO.setIsEdit(isEdit);
			
			resultData.setDatas(selerInfoFinVO);
		} catch (OrderExecException e) {
			log.error("OrderExecException   ---->  ", e);
			resultData.setFail(e.getMessage());
			resultData.setCode(e.getCode());
		} catch (Exception e) {
			log.error("Exception   ---->  ", e);
			resultData.setFail(e.getMessage());
			resultData.setCode("orderexecute.code.00005");
		}
		
        return resultData;
	}
	
	/**
	 * 卖家最终结算单编辑
	 * @param vo
	 * @param user
	 * @param modelMap
	 */
	@RequestMapping(value = UrlMapping.S_STATEMENT_INFO_FIN)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public void selerInfoFin(OrderStatementVO vo, CurrentUser user, ModelMap modelMap) {
		String isEdit;
		Map<String, Object> map = new HashMap<>();
		Order order;
		if(StringUtils.isNotBlank(vo.getUuid()))
			order = orderService.findByUuid(vo.getUuid());
		else if(StringUtils.isNotBlank(vo.getOrderStatementUuid())){
			OrderStatement statement = orderStatementService.findByUuid(vo.getOrderStatementUuid());
			order = orderService.findByPrimaryKey(statement.getOrderId());
		}else
			throw new OrderExecException("orderexecute.code.00029","订单ID或对账单ID为空");
			
		if(order == null)
			throw new OrderExecException("orderexecute.code.00014","订单不存在");
		Long orderId = order.getId();
		vo.setOrderId(order.getId());
		vo.setUuid(order.getUuid());
		
		//订单相关信息查询
		Map<String, Object> inforData = orderStatementService.selectinformation(order.getId());

		//查询是否已经生成对账单

		map.put("orderId", orderId);
		map.put("statementType", Constants.STATEMENT_TYPE_I);
		map.put("orderStatementUuid", vo.getOrderStatementUuid());
		List<Map<String, Object>> statementList = orderStatementService.queryRecords(map);
		
		
		Map<String, Object> stateData = new HashMap<>();
		List<OrderFeeItem> feeDataList = new ArrayList<>();

		//费用增加转月费 
		double differAmount = orderStatementService.findDifferAmount(orderId);
		OrderFeeItem transferFee = new OrderFeeItem();
		Locale current = VisitorLocale.getCurrent();
    	if(current != null && StringUtils.equals("en", current.getLanguage())){
    		transferFee.setSubjectName(Constants.SUBJECT_NAME_T_EN);
    	}else{    		
    		transferFee.setSubjectName(Constants.SUBJECT_NAME_T);
    	}
		transferFee.setValuationModel(Constants.VALUATION_MODEL_T);
		transferFee.setQuantity(BigDecimal.valueOf(Constants.QUANTITY_T));
		transferFee.setFeePrice(BigDecimal.valueOf(differAmount));
		transferFee.setTotalFee(BigDecimal.valueOf(differAmount));
		transferFee.setIsAgent(Constants.IS_AGENT_T);
		
		if(CollectionUtils.isNotEmpty(statementList)){
			//最新一条对账单x`x
			stateData = statementList.get(0);
			//校验对账单状态
			if (Constants.STATEMENT_STATUS_01.equals(stateData.get("status")))
				isEdit = "1";//初始状态允许修改
			else if (Constants.STATEMENT_STATUS_20.equals(stateData.get("status")))
				isEdit = "2";//驳回状态允许重新生成
			else
				isEdit = "0";//不允许操作
			
			if(StringUtils.isEmpty(vo.getIsAgain())){
				//查询对账单对应费用信息
				Map<String, Object> paraMap = new HashMap<>();
				paraMap.put("relatId", stateData.get("orderStatementId"));
				paraMap.put("relatType", "1");
				paraMap.put("statFee", "1");
				feeDataList = orderFeeItemService.queryRecords(paraMap);
				
			}else{//修改标记为1 进入修改页面 修改时获取最新费用信息 
				Map<String, Object> feeData =orderStatementService.selectData(vo, user);
				stateData.put("orderFeeId", feeData.get("orderFeeId"));
				stateData.put("totalFee", feeData.get("totalFee"));
				//查询费用信息
				feeDataList = orderFeeItemService.selectOrderFeeItem(orderId);
				isEdit = "3";//进入修改页面时均可保存
				if("2".equals(vo.getIsAgain()))
					stateData.put("orderStatementId", "");
			}
		} else {
			Map<String, Object> mapPre = new HashMap<>();
			mapPre.put("orderId", vo.getOrderId());
			mapPre.put("statementType",  Constants.STATEMENT_TYPE_S);
			mapPre.put("status", Constants.STATEMENT_STATUS_30);
			List<Map<String, Object>> statementPreList = orderStatementService
					.queryRecords(mapPre);
			Map<String, Object> statePreData = new HashMap<>();
			
			if(CollectionUtils.isNotEmpty(statementPreList))
				statePreData = statementPreList.get(0);
			
			//新增时 查询订单信息
			stateData = orderStatementService.selectData(vo, user);
			stateData.put("statementType",  Constants.STATEMENT_TYPE_I);
			stateData.put("checkTime", DateTimeUtils.currentDate());
			stateData.put("settledAmount",statePreData.get("totalAmount"));
			stateData.put("orderStatementNo", SequenceUtils.nextSequence("FIN"));
			//查询费用信息
			feeDataList = orderFeeItemService.selectOrderFeeItem(orderId);
			isEdit = "3";
		}
		//只有计价方式是trigger,合约数量与拆单数量相等时显示转月费
		if(orderStatementService.queryIsTrigger(orderId) > 0){	
			
			feeDataList.add(transferFee);
			
			//查询拆单信息的总拆单数量和平均金额 (对账单中的结算桶和结算金额)
			Map<String, Object> resultMap = orderStatementService.selectDataForTriggerResult(orderId);
			stateData.put("settlementQuantityBbl", resultMap.get("settlementQuantityBbl"));//结算桶
			stateData.put("settlementPrice", resultMap.get("settlementPrice"));//结算价格
			
			//对账单费用合计增加转月费
			BigDecimal totalFee = (BigDecimal)stateData.get("totalFee") ;
			BigDecimal add = totalFee.add(BigDecimal.valueOf(differAmount));
			stateData.put("totalFee", add);
		}
		
		//国际化转换
		ValueSet i18nCodeValue = ValueSetUtil.instance(ValueSetGroupConstant.SUBJECT_LIST);
		for(OrderFeeItem data : feeDataList) {
			String code = data.getSubjectCode();
			String name = data.getSubjectName();
			data.setSubjectName(i18nCodeValue.getByCode(code,name).getValue());
		}
		
		stateData.put("uuid", vo.getUuid());
		stateData.put("orderId", vo.getOrderId());
		modelMap.put("stateData", stateData);
		modelMap.put("inforData", inforData);
		modelMap.put("feeData", feeDataList);
		modelMap.put("isEdit", isEdit);
	}
	
	/**
	 * 查询对账信息分页列表 卖家最终结算单详情@param vo @return @exception
	 */
	
	@RequestMapping(value = UrlMapping.S_STATEMENT_INFO_FIN_JSON)
	@ResponseBody
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	@ApiSafeAccess
	public ResultDatas<SelerInfoFinVO> selerInfoFinJson( OrderStatementVO vo, CurrentUser user, ModelMap modelMap) {
		
		ResultDatas<SelerInfoFinVO> resultData = new ResultDatas<>();
		
		try {
			String isEdit;
			Map<String, Object> map = new HashMap<>();
			Order order;
			
			if(StringUtils.isNotBlank(vo.getUuid()))
				order = orderService.findByUuid(vo.getUuid());
			else if(StringUtils.isNotBlank(vo.getOrderStatementUuid())){
				OrderStatement statement = orderStatementService.findByUuid(vo.getOrderStatementUuid());
				order = orderService.findByPrimaryKey(statement.getOrderId());
			}else
				throw new OrderExecException("orderexecute.code.00029","订单ID或对账单ID为空");
				
			if(order == null)
				throw new OrderExecException("orderexecute.code.00014","订单不存在");
			Long orderId = order.getId();
			vo.setOrderId(order.getId());
			vo.setUuid(order.getUuid());
			
			//订单相关信息查询
			Map<String, Object> inforData = orderStatementService.selectinformation(order.getId());

			//查询是否已经生成对账单

			map.put("orderId", orderId);
			map.put("statementType", Constants.STATEMENT_TYPE_I);
			map.put("orderStatementUuid", vo.getOrderStatementUuid());
			List<Map<String, Object>> statementList = orderStatementService.queryRecords(map);
			
			
			Map<String, Object> stateData = new HashMap<>();
			List<OrderFeeItem> feeDataList = new ArrayList<>();

			//费用增加转月费 
			double differAmount = orderStatementService.findDifferAmount(orderId);
			OrderFeeItem transferFee = new OrderFeeItem();
	    	Locale current = VisitorLocale.getCurrent();
	    	if(current != null && StringUtils.equals("en", current.getLanguage())){
	    		transferFee.setSubjectName(Constants.SUBJECT_NAME_T_EN);
	    	}else{    		
	    		transferFee.setSubjectName(Constants.SUBJECT_NAME_T);
	    	}
			transferFee.setValuationModel(Constants.VALUATION_MODEL_T);
			transferFee.setQuantity(BigDecimal.valueOf(Constants.QUANTITY_T));
			transferFee.setFeePrice(BigDecimal.valueOf(differAmount));
			transferFee.setTotalFee(BigDecimal.valueOf(differAmount));
			transferFee.setIsAgent(Constants.IS_AGENT_T);

			if(CollectionUtils.isNotEmpty(statementList)){
				//最新一条对账单x`x
				stateData = statementList.get(0);
				//校验对账单状态
				if (Constants.STATEMENT_STATUS_01.equals(stateData.get("status")))
					isEdit = "1";//初始状态允许修改
				else if (Constants.STATEMENT_STATUS_20.equals(stateData.get("status")))
					isEdit = "2";//驳回状态允许重新生成
				else
					isEdit = "0";//不允许操作
				
				//if(vo.getIsAgain()==null || "".equals(vo.getIsAgain())){
				if(StringUtils.isBlank(vo.getIsAgain())){
					//查询对账单对应费用信息
					Map<String, Object> paraMap = new HashMap<>();
					paraMap.put("relatId", stateData.get("orderStatementId"));
					paraMap.put("relatType", "1");
					paraMap.put("statFee", "1");
					feeDataList = orderFeeItemService.queryRecords(paraMap);
					
				}else{//修改标记为1 进入修改页面 修改时获取最新费用信息 
					Map<String, Object> feeData =orderStatementService.selectData(vo, user);
					stateData.put("orderFeeId", feeData.get("orderFeeId"));
					stateData.put("totalFee", feeData.get("totalFee"));
					//查询费用信息
					feeDataList = orderFeeItemService.selectOrderFeeItem(orderId);
					isEdit = "3";//进入修改页面时均可保存
					if("2".equals(vo.getIsAgain()))
						stateData.put("orderStatementId", "");
				}
			} else {
				Map<String, Object> mapPre = new HashMap<>();
				mapPre.put("orderId", vo.getOrderId());
				mapPre.put("statementType",  Constants.STATEMENT_TYPE_S);
				mapPre.put("status", Constants.STATEMENT_STATUS_30);
				List<Map<String, Object>> statementPreList = orderStatementService.queryRecords(mapPre);
				Map<String, Object> statePreData = new HashMap<>();
				
				if(CollectionUtils.isNotEmpty(statementPreList))
					statePreData = statementPreList.get(0);
				
				//新增时 查询订单信息
				stateData = orderStatementService.selectData(vo, user);
				stateData.put("statementType",  Constants.STATEMENT_TYPE_I);
				stateData.put("checkTime", DateTimeUtils.currentDate());
				stateData.put("settledAmount",statePreData.get("totalAmount"));
				stateData.put("orderStatementNo", SequenceUtils.nextSequence("FIN"));
				//查询费用信息
				feeDataList = orderFeeItemService.selectOrderFeeItem(orderId);
				isEdit = "3";
			}
			//只有计价方式是trigger,合约数量与拆单数量相等时显示转月费
			if(orderStatementService.queryIsTrigger(orderId) > 0){	
				
				feeDataList.add(transferFee);
				
				//查询拆单信息的总拆单数量和平均金额 (对账单中的结算桶和结算金额)
				Map<String, Object> resultMap = orderStatementService.selectDataForTriggerResult(orderId);
				stateData.put("settlementQuantityBbl", resultMap.get("settlementQuantityBbl"));//结算桶
				stateData.put("settlementPrice", resultMap.get("settlementPrice"));//结算价格
				
				//对账单费用合计增加转月费
				BigDecimal totalFee = (BigDecimal)stateData.get("totalFee") ;
				BigDecimal add = totalFee.add(BigDecimal.valueOf(differAmount));
				stateData.put("totalFee", add);
			}
			
			stateData.put("uuid", vo.getUuid());
			stateData.put("orderId", vo.getOrderId());
		    modelMap.put("stateData", stateData);
			modelMap.put("inforData", inforData);
			modelMap.put("feeData", feeDataList);
			modelMap.put("isEdit", isEdit);
			SelerInfoFinVO selerInfoFinVO = new SelerInfoFinVO();
			selerInfoFinVO.setStateData(stateData);
			selerInfoFinVO.setInforData(inforData);
			selerInfoFinVO.setFeeDataList(feeDataList);
			selerInfoFinVO.setIsEdit(isEdit);
			
			resultData.setDatas(selerInfoFinVO);
		} catch (OrderExecException e) {
			log.error("OrderExecException  ---->  ", e);
			resultData.setFail(e.getMessage());
			resultData.setCode(e.getCode());
		} catch (Exception e) {
			log.error("Exception  ---->  ", e);
			resultData.setFail(e.getMessage());
			resultData.setCode("orderexecute.code.00005");
		}

        return resultData;
	}
	
	/**
	 * 新增、保存对账信息 (预估)@param vo @return @exception
	 */
	@RequestMapping(value = UrlMapping.STATEMENT_SAVE_PRE)
	@RolesAccess({ "trade_executor"})
	public Result insertRecord(OrderStatementVO vo, CurrentUser user) {
		Result res = new Result();
		try {
			if(vo.getOrderId() == null)
				throw new OrderExecException("orderexecute.code.00030","订单ID不能为空");
			if(vo.getOrderFeeId() == null)
				throw new OrderExecException("orderexecute.code.00031","未获取到订单费用信息，请先维护费用");
			Order order = orderService.findByPrimaryKey(vo.getOrderId());
			if(order ==null)
				throw new OrderExecException("orderexecute.code.00014","订单不存在");
			if(OrderStatusEnum.STATUS_6.getCode().equals(order.getStatus()) || OrderStatusEnum.STATUS_7.getCode().equals(order.getStatus()))
				throw new OrderExecException("orderexecute.code.00014","当前订单不允许此操作");
			OrderStatement orderStatement = new OrderStatement();
			orderStatement.setOrderId(vo.getOrderId());
			orderStatement.setOrderStatementId(vo.getOrderStatementId());
			orderStatement.setStatementType(vo.getStatementType());
			List<OrderStatement> stateList = orderStatementService.queryByEntitys(orderStatement);
			if (vo.getOrderStatementId() == null) {
				if(CollectionUtils.isNotEmpty(stateList)){
					for (OrderStatement state : stateList) {
						if (!Constants.STATEMENT_STATUS_20.equals(state.getStatus()))
							throw new OrderExecException("orderexecute.code.00033","不能重复生成对账单！");
					}
				}
	
			} else {
				if(CollectionUtils.isNotEmpty(stateList)){
					if (!Constants.STATEMENT_STATUS_01.equals(stateList.get(0).getStatus()))
						throw new OrderExecException("orderexecute.code.00034","当前对账单不允许修改！");
				} else {
					throw new OrderExecException("orderexecute.code.00035","未查询到相应对账单信息！");
				}
			}
			orderStatementService.saveStatement(vo,user);
		} catch (OrderExecException e) {
			log.error("OrderExecException  --->   ", e);
			res.setFail(e.getMessage());
			res.setCode(e.getCode());
		} catch (Exception e) {
			log.error("", e);
			res.setFail(e.getMessage());
			res.setCode("orderexecute.code.00005");
		}
		return res;
	}

	/**
	 * 查询对账信息分页列表  @param vo @return @exception
	 */
	@RequestMapping(value = UrlMapping.S_STATEMENT_LIST_PRE)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public void sellerListPre(CurrentUser user, OrderStatementQuery query, ModelMap modelMap) {
		
		query.setStatementType("1");
		query.setSellerCustomerId(user.getEpMemberId());

		Page<Map<String, Object>> pageResult = orderStatementService.queryRecords(query.getQueryParam(), query.getPageInfo());
		
		QueryBase queryBase = query;
		queryBase.setTotalItem(pageResult.getTotal());
		
		modelMap.put("datas", pageResult.getResult());
		modelMap.put("query", queryBase);

	}
	/**
	 * 查询对账信息分页列表 卖家预估对账单列表@param vo @return @exception
	 */
	
	@RequestMapping(value = UrlMapping.S_STATEMENT_LIST_PRE_JSON)
	@ResponseBody
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	@ApiSafeAccess
	public  ResultDatas<SellerListPreVO> sellerListPreJson(CurrentUser user, OrderStatementQuery query, ModelMap modelMap) {
		query.setStatementType("1");
		query.setSellerCustomerId(user.getEpMemberId());
		Page<Map<String, Object>> pageResult = orderStatementService.queryRecords(query.getQueryParam(), query.getPageInfo());
		pageResult.setPageNum(pageResult.getPageNum());
		pageResult.setTotal(pageResult.getTotal());
		pageResult.setPageSize(pageResult.getPageSize());
		QueryBase queryBase = query;
		queryBase.setTotalItem(pageResult.getTotal());
		ResultDatas<SellerListPreVO> resultData = new ResultDatas<>();
		SellerListPreVO sellerListPreVO = new SellerListPreVO();
		sellerListPreVO.setDatas(pageResult.getResult());
		sellerListPreVO.setQuery(queryBase);

		resultData.setDatas(sellerListPreVO);
		
        return resultData;
	}
	
	/**
	 * 查询对账信息分页列表 @param vo @return @exception
	 */
	@RequestMapping(value = UrlMapping.S_STATEMENT_LIST_FIN)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public void sellerListFin(CurrentUser user, OrderStatementQuery query, ModelMap modelMap) {
		query.setStatementType("2");
		query.setSellerCustomerId(user.getEpMemberId());
		Page<Map<String, Object>> pageResult = orderStatementService.queryRecords(query.getQueryParam(), query.getPageInfo());
		QueryBase queryBase = query;
		queryBase.setTotalItem(pageResult.getTotal());
		
		modelMap.put("datas", pageResult.getResult());
		modelMap.put("query", queryBase);
//		return "sellerCenter/orderStatement/listFin";

	}
	/**
	 * 查询对账信息分页列表 卖家正式对账单列表@param vo @return @exception
	 */
	
	@RequestMapping(value = UrlMapping.S_STATEMENT_LIST_FIN_JSON)
	@ResponseBody
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	@ApiSafeAccess
	public  ResultDatas<SellerListPreVO> sellerListFinJson(CurrentUser user, OrderStatementQuery query, ModelMap modelMap) {
		query.setStatementType("2");
		query.setSellerCustomerId(user.getEpMemberId());
		Page<Map<String, Object>> pageResult = orderStatementService.queryRecords(query.getQueryParam(), query.getPageInfo());
		
		QueryBase queryBase = query;
		queryBase.setTotalItem(pageResult.getTotal());
		
		ResultDatas<SellerListPreVO> resultData = new ResultDatas<>();
		SellerListPreVO sellerListPreVO = new SellerListPreVO();
		sellerListPreVO.setDatas(pageResult.getResult());
		sellerListPreVO.setQuery(queryBase);

		resultData.setDatas(sellerListPreVO);
        return resultData;
	}
	/**
	 * 对账单确认
	 * @param vo 
	 * @return 
	 * @exception
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.STATEMENT_COMFIRM, method = RequestMethod.POST)
	@RolesAccess({ "trade_executor"})
	public Result statementComfirm(@RequestBody OrderStatementVO vo, CurrentUser user) {

		Result res = new Result();
		try {
			if(vo.getOrderStatementId() ==null)
				throw new OrderExecException("orderexecute.code.00022","对账单ID不能为空");

			OrderStatement orderStatement = new OrderStatement();
			orderStatement.setOrderStatementId(vo.getOrderStatementId());
			OrderStatement stateInfo = orderStatementService.findByPrimaryKey(vo.getOrderStatementId());
			if(stateInfo ==null)
				throw new OrderExecException("orderexecute.code.00036","对账单不存在");
			if(!Constants.STATEMENT_STATUS_01.equals(stateInfo.getStatus()))
				throw new OrderExecException("orderexecute.code.00037","当前对账单不允许此操作");

			Order order = orderService.findByPrimaryKey(stateInfo.getOrderId());
			if(order ==null)
				throw new OrderExecException("orderexecute.code.00014","订单不存在");
			if(OrderStatusEnum.STATUS_6.getCode().equals(order.getStatus())||OrderStatusEnum.STATUS_7.getCode().equals(order.getStatus()))
				throw new OrderExecException("orderexecute.code.00032","当前订单不允许此操作");
			
			stateInfo.setStatus(Constants.STATEMENT_STATUS_05);
			stateInfo.setUpdateDate(DateTimeUtils.currentDate());
			stateInfo.setUpdateUser(user.getMemberId());
			stateInfo.setConfirmPerson(user.getMemberId());
			orderStatementService.updateRecordById(stateInfo);
			sendRemindMSG(stateInfo, order);
		} catch (OrderExecException e) {
			log.error("", e);
			res.setFail(e.getMessage());
			res.setCode(e.getCode());
		} catch (Exception e) {
			log.error("", e);
			res.setStatus(Result.EEROR);
			res.setMessage("操作失败");
			res.setCode("orderexecute.code.00042");
		}
		return res;
	}

	private void sendRemindMSG(OrderStatement stateInfo, Order order) {
		try{
			//发送消息提醒
			StatementMsgParamVO templateParams = new StatementMsgParamVO();
			templateParams.setEpMemberName(order.getSellerCustomerName());
			templateParams.setStatementNo(stateInfo.getOrderStatementNo());
			templateParams.setReceiverId(order.getBuyerPersonId());
			templateParams.setCustomerId(order.getBuyerCustomerId());
			templateParams.setOrderId(order.getId());
			String statementDetailLink = "";
			if("2".equals(stateInfo.getStatementType())) {
				statementDetailLink = "buyerCenter/orderStatement/infoFin.htm";
			}else {
				statementDetailLink = "buyerCenter/orderStatement/infoPre.htm";
			}
			templateParams.setStatementDetailLink(orderExecuteServer.get(statementDetailLink)+"?orderStatementUuid="+stateInfo.getOrderStatementUuid());
			
			msgRemindingService.sendStatementProcessMsg(StatementStatusEnum.ORDER_STATUS_ITEM_3_1, templateParams);
		}catch(Exception e){
			log.error("",e);
		}
	}
	
	
	/**
	 * 买家查看对账单详情 
	 * @param vo
	 * @param user
	 * @param modelMap
	 */
	@RequestMapping(value ={ UrlMapping.B_STATEMENT_INFO_PRE})
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public void buyerInfoPre(OrderStatementVO vo, CurrentUser user,ModelMap modelMap) {
		OrderStatement statInfo = new OrderStatement();
		if(StringUtils.isNotBlank(vo.getOrderStatementUuid()))
			statInfo =orderStatementService.findByUuid(vo.getOrderStatementUuid());
		else if(StringUtils.isNotBlank(vo.getUuid())){
			Order findByUuid = orderService.findByUuid(vo.getUuid());
			statInfo.setOrderId(findByUuid.getId());
			statInfo.setStatementType(Constants.STATEMENT_TYPE_S);//预估
			List<OrderStatement> orderStatementList = orderStatementService.queryByEntitys(statInfo);
			
			if(CollectionUtils.isEmpty(orderStatementList))
				throw new OrderExecException("orderexecute.code.00036","对账单不存在");
			else
				statInfo = orderStatementList.get(0);
		}else
			throw new OrderExecException("orderexecute.code.00029","订单ID或对账单ID为空");
		
		if(statInfo ==null)
			throw new OrderExecException("orderexecute.code.00036","对账单不存在");
		if(Constants.STATEMENT_STATUS_01.equals(statInfo.getStatus()))
			throw new OrderExecException("orderexecute.code.00037","当前对账单不允许此操作");
		Map<String, Object> stateData = statInfo.toMap();	
		
		Long orderId = statInfo.getOrderId();
		Map<String, Object> map = new HashMap<>();
		map.put("orderId", statInfo.getOrderId());

		//订单相关信息查询
		Map<String, Object> inforData = orderStatementService.selectinformation(orderId);
		//查询对账单对应费用信息
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("relatId", stateData.get("orderStatementId"));
		paraMap.put("relatType", "1");
		paraMap.put("statFee", "1");
		List<OrderFeeItem> feeDataList = orderFeeItemService.queryRecords(paraMap);
		//国际化名称转换
		ValueSet i18nValueSet = ValueSetUtil.instance(ValueSetGroupConstant.SUBJECT_LIST);
		for(OrderFeeItem feeData : feeDataList) {
			String subjectCode = feeData.getSubjectCode();
			String subjectName = feeData.getSubjectName();
			feeData.setSubjectName(i18nValueSet.getByCode(subjectCode,subjectName).getValue());
		}
		
		modelMap.put("stateData", stateData);
		modelMap.put("inforData", inforData);
		modelMap.put("feeData", feeDataList);
	}
	/**
	 * 查询对账信息分页列表 买家临时结算单详情@param vo @return @exception
	 */
	
	@RequestMapping(value = UrlMapping.B_STATEMENT_INFO_PRE_JSON)
	@ResponseBody
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	@ApiSafeAccess
	public ResultDatas<SelerInfoFinVO> buyerInfoPreJson(
			OrderStatementVO vo, CurrentUser user, ModelMap modelMap) {
		ResultDatas<SelerInfoFinVO> resultData = new ResultDatas<>();
		
		try {
			OrderStatement statInfo = new OrderStatement();
			if(StringUtils.isNotBlank(vo.getOrderStatementUuid()))
				statInfo =orderStatementService.findByUuid(vo.getOrderStatementUuid());
			else if(StringUtils.isNotBlank(vo.getUuid())){
				Order findByUuid = orderService.findByUuid(vo.getUuid());
				statInfo.setOrderId(findByUuid.getId());
				statInfo.setStatementType(Constants.STATEMENT_TYPE_S);//预估
				 List<OrderStatement> orderStatementList = orderStatementService.queryByEntitys(statInfo);
				 if(CollectionUtils.isEmpty(orderStatementList))
					 throw new OrderExecException("orderexecute.code.00036","对账单不存在");
				 else
					 statInfo = orderStatementList.get(0);
			}else
				throw new OrderExecException("orderexecute.code.00029","订单ID或对账单ID为空");
			
			if(statInfo ==null)
				throw new OrderExecException("orderexecute.code.00036","对账单不存在");
			if(Constants.STATEMENT_STATUS_01.equals(statInfo.getStatus()))
				throw new OrderExecException("orderexecute.code.00037","当前对账单不允许此操作");
			Map<String, Object> stateData = statInfo.toMap();	
			
			Long orderId = statInfo.getOrderId();
			Map<String, Object> map = new HashMap<>();
			map.put("orderId", statInfo.getOrderId());

			//订单相关信息查询
			Map<String, Object> inforData = orderStatementService.selectinformation(orderId);
			//查询对账单对应费用信息
			Map<String, Object> paraMap = new HashMap<>();
			paraMap.put("relatId", stateData.get("orderStatementId"));
			paraMap.put("relatType", "1");
			paraMap.put("statFee", "1");
			List<OrderFeeItem> feeDataList = orderFeeItemService.queryRecords(paraMap);
			
			SelerInfoFinVO selerInfoFinVO = new SelerInfoFinVO();
			selerInfoFinVO.setStateData(stateData);
			selerInfoFinVO.setInforData(inforData);
			selerInfoFinVO.setFeeDataList(feeDataList);

			resultData.setDatas(selerInfoFinVO);
		} catch (OrderExecException e) {
			log.error("", e);
			resultData.setFail(e.getMessage());
			resultData.setCode(e.getCode());
		} catch (Exception e) {
			log.error("", e);
			resultData.setFail(e.getMessage());
			resultData.setCode("orderexecute.code.00005");
		}
		
        return resultData;
	}
	
	/**
	 * 买家查看对账单详情  正式
	 * @param vo
	 * @param user
	 * @param modelMap
	 */
	@RequestMapping(value ={ UrlMapping.B_STATEMENT_INFO_FIN})
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public void buyerInfoFin(OrderStatementVO vo, CurrentUser user,ModelMap modelMap) {
		OrderStatement statInfo = new OrderStatement();
		if(vo.getOrderStatementUuid()!=null && !"".equals(vo.getOrderStatementUuid()))
		 statInfo =orderStatementService.findByUuid(vo.getOrderStatementUuid());
		else if(StringUtils.isNotBlank(vo.getUuid())){
			Order findByUuid = orderService.findByUuid(vo.getUuid());
			statInfo.setOrderId(findByUuid.getId());
			statInfo.setStatementType(Constants.STATEMENT_TYPE_I);//正式
			List<OrderStatement> orderStatementList = orderStatementService.queryByEntitys(statInfo);
			if(CollectionUtils.isEmpty(orderStatementList))
				throw new OrderExecException("orderexecute.code.00036","对账单不存在");
			 else
				 statInfo = orderStatementList.get(0);
		}else
			throw new OrderExecException("orderexecute.code.00029","订单ID或对账单ID为空");
		
		if(statInfo ==null)
			throw new OrderExecException("orderexecute.code.00036","对账单不存在");
		if(Constants.STATEMENT_STATUS_01.equals(statInfo.getStatus()))
			throw new OrderExecException("orderexecute.code.00037","当前对账单不允许此操作");
		Map<String, Object> stateData = statInfo.toMap();	
		
		Long orderId = statInfo.getOrderId();
		Map<String, Object> map = new HashMap<>();
		map.put("orderId", statInfo.getOrderId());

		//订单相关信息查询
		Map<String, Object> inforData = orderStatementService.selectinformation(orderId);
		//查询对账单对应费用信息
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("relatId", stateData.get("orderStatementId"));
		paraMap.put("relatType", "1");
		paraMap.put("statFee", "1");
		List<OrderFeeItem> feeDataList = orderFeeItemService.queryRecords(paraMap);
		
		//费用增加转月费 
		double differAmount = orderStatementService.findDifferAmount(orderId);
		OrderFeeItem transferFee = new OrderFeeItem();
		transferFee.setSubjectName(Constants.SUBJECT_NAME_T);
		transferFee.setValuationModel(Constants.VALUATION_MODEL_T);
		transferFee.setQuantity(BigDecimal.valueOf(Constants.QUANTITY_T));
		transferFee.setFeePrice(BigDecimal.valueOf(differAmount));
		transferFee.setTotalFee(BigDecimal.valueOf(differAmount));
		transferFee.setIsAgent(Constants.IS_AGENT_T);

		//只有计价方式是trigger,合约数量与拆单数量相等时显示转月费
		if(orderStatementService.queryIsTrigger(orderId) > 0){	
			
			feeDataList.add(transferFee);
			
			//查询拆单信息的总拆单数量和平均金额 
			Map<String, Object> resultMap = orderStatementService.selectDataForTriggerResult(orderId);
			stateData.put("settlementQuantityBbl", resultMap.get("settlementQuantityBbl"));//结算桶
			stateData.put("settlementPrice", resultMap.get("settlementPrice"));//结算价格
			
			//对账单费用合计增加转月费
			BigDecimal totalFee = (BigDecimal)stateData.get("totalFee") ;
			BigDecimal add = totalFee.add(BigDecimal.valueOf(differAmount));
			stateData.put("totalFee", add);
		}
		
		//国际化转换
		ValueSet i18nCodeValue = ValueSetUtil.instance(ValueSetGroupConstant.SUBJECT_LIST);
		for(OrderFeeItem data : feeDataList) {
			String code = data.getSubjectCode();
			String name = data.getSubjectName();
			data.setSubjectName(i18nCodeValue.getByCode(code,name).getValue());
		}
		
		modelMap.put("stateData", stateData);
		modelMap.put("inforData", inforData);
		modelMap.put("feeData", feeDataList);
	}
	
	/**
	 * 查询对账信息分页列表 买家正式对账单详情@param vo @return @exception
	 */
	
	@RequestMapping(value = UrlMapping.B_STATEMENT_INFO_FIN_JSON)
	@ResponseBody
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	@ApiSafeAccess
	public ResultDatas<SelerInfoFinVO> buyerInfoFinJson(OrderStatementVO vo, CurrentUser user,OrderStatementQuery query, ModelMap modelMap) {
		ResultDatas<SelerInfoFinVO> resultData = new ResultDatas<>();
		try {
			OrderStatement statInfo = new OrderStatement();
			if(StringUtils.isNotBlank(vo.getOrderStatementUuid()))
				statInfo =orderStatementService.findByUuid(vo.getOrderStatementUuid());
			//else if(vo.getUuid() != null && !"".equals(vo.getUuid()) ){
			else if(StringUtils.isNotBlank(vo.getUuid())){
				Order findByUuid = orderService.findByUuid(vo.getUuid());
				statInfo.setOrderId(findByUuid.getId());
				statInfo.setStatementType(Constants.STATEMENT_TYPE_I);//正式
				 List<OrderStatement> orderStatementList = orderStatementService.queryByEntitys(statInfo);
				 if(CollectionUtils.isEmpty(orderStatementList))
						throw new OrderExecException("orderexecute.code.00036","对账单不存在");
				 else
					 statInfo = orderStatementList.get(0);
			}else
				throw new OrderExecException("orderexecute.code.00029","订单ID或对账单ID为空");
			
			if(statInfo ==null)
				throw new OrderExecException("orderexecute.code.00036","对账单不存在");
			if(Constants.STATEMENT_STATUS_01.equals(statInfo.getStatus()))
				throw new OrderExecException("orderexecute.code.00037","当前对账单不允许此操作");
			Map<String, Object> stateData = statInfo.toMap();	
			
			Long orderId = statInfo.getOrderId();
			Map<String, Object> map = new HashMap<>();
			map.put("orderId", statInfo.getOrderId());

			//订单相关信息查询
			Map<String, Object> inforData = orderStatementService
					.selectinformation(orderId);
			//查询对账单对应费用信息
			Map<String, Object> paraMap = new HashMap<>();
			paraMap.put("relatId", stateData.get("orderStatementId"));
			paraMap.put("relatType", "1");
			paraMap.put("statFee", "1");
			List<OrderFeeItem> feeDataList = orderFeeItemService.queryRecords(paraMap);
			
			//费用增加转月费 
			double differAmount = orderStatementService.findDifferAmount(orderId);
			OrderFeeItem transferFee = new OrderFeeItem();
			Locale current = VisitorLocale.getCurrent();
	    	if(current != null && StringUtils.equals("en", current.getLanguage())){
	    		transferFee.setSubjectName(Constants.SUBJECT_NAME_T_EN);
	    	}else{    		
	    		transferFee.setSubjectName(Constants.SUBJECT_NAME_T);
	    	}
			transferFee.setValuationModel(Constants.VALUATION_MODEL_T);
			transferFee.setQuantity(BigDecimal.valueOf(Constants.QUANTITY_T));
			transferFee.setFeePrice(BigDecimal.valueOf(differAmount));
			transferFee.setTotalFee(BigDecimal.valueOf(differAmount));
			transferFee.setIsAgent(Constants.IS_AGENT_T);

			//只有计价方式是trigger,合约数量与拆单数量相等时显示转月费
			if(orderStatementService.queryIsTrigger(orderId) > 0){	
				
				feeDataList.add(transferFee);
				
				//查询拆单信息的总拆单数量和平均金额 
				Map<String, Object> resultMap = orderStatementService.selectDataForTriggerResult(orderId);
				stateData.put("settlementQuantityBbl", resultMap.get("settlementQuantityBbl"));//结算桶
				stateData.put("settlementPrice", resultMap.get("settlementPrice"));//结算价格
				
				//对账单费用合计增加转月费
				BigDecimal totalFee = (BigDecimal)stateData.get("totalFee") ;
				BigDecimal add = totalFee.add(BigDecimal.valueOf(differAmount));
				stateData.put("totalFee", add);
			}
			
			
			SelerInfoFinVO selerInfoFinVO = new SelerInfoFinVO();
			selerInfoFinVO.setStateData(stateData);
			selerInfoFinVO.setInforData(inforData);
			selerInfoFinVO.setFeeDataList(feeDataList);
			
			resultData.setDatas(selerInfoFinVO);
		} catch (OrderExecException e) {
			log.error("",e);
			resultData.setFail(e.getMessage());
			resultData.setCode(e.getCode());
		} catch (Exception e) {
			log.error("",e);
			resultData.setFail(e.getMessage());
			resultData.setCode("orderexecute.code.00005");
		}
		
        return resultData;
	}
	
	/**
	 * (买家)查询对账信息分页列表 @param vo @return @exception
	 */
	@RequestMapping(value = UrlMapping.B_STATEMENT_LIST_PRE)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public void buyerListPre(CurrentUser user, OrderStatementQuery query, ModelMap modelMap) {
		query.setStatementType(Constants.STATEMENT_TYPE_S);
		query.setBuyerCustomerId(user.getEpMemberId());
		query.setNoStatus(Constants.STATEMENT_STATUS_01);
		Page<Map<String, Object>> pageResult = orderStatementService.queryRecords(query.getQueryParam(), query.getPageInfo());
		
		QueryBase queryBase = query;
		queryBase.setTotalItem(pageResult.getTotal());
		
		modelMap.put("datas", pageResult.getResult());
		modelMap.put("query", queryBase);

	}
	
	/**
	 * app端买家的临时结算单列表接口
	 * @param user
	 * @param query
	 * @param modelMap
	 * @return
	 */
	
	@RequestMapping(value = UrlMapping.B_STATEMENT_LIST_PRE_JSON)
	@ResponseBody
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	@ApiSafeAccess
	public  ResultDatas<SellerListPreVO> buyerListPreJson(CurrentUser user, OrderStatementQuery query, ModelMap modelMap) {
		query.setStatementType(Constants.STATEMENT_TYPE_S);
		query.setBuyerCustomerId(user.getEpMemberId());
		query.setNoStatus(Constants.STATEMENT_STATUS_01);
		Page<Map<String, Object>> pageResult = orderStatementService.queryRecords(query.getQueryParam(), query.getPageInfo());
		
		QueryBase queryBase = query;
		queryBase.setTotalItem(pageResult.getTotal());
		Map<String, Object> params = query.getQueryParam();
		params.put("query", queryBase);
		
	
		ResultDatas<SellerListPreVO> resultData = new ResultDatas<>();
		SellerListPreVO sellerListPreVO = new SellerListPreVO();
		sellerListPreVO.setDatas(pageResult.getResult());
		sellerListPreVO.setQuery(queryBase);

		resultData.setDatas(sellerListPreVO);
		
        return resultData;
	}
	
	/**
	 * 买家的最终结算单列表
	 * @param user
	 * @param query
	 * @param modelMap
	 */
	@RequestMapping(value = UrlMapping.B_STATEMENT_LIST_FIN)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public void buyerListFin(CurrentUser user, OrderStatementQuery query, ModelMap modelMap) {
		query.setStatementType(Constants.STATEMENT_TYPE_I);
		query.setBuyerCustomerId(user.getEpMemberId());
		query.setNoStatus(Constants.STATEMENT_STATUS_01);
		Page<Map<String, Object>> pageResult = orderStatementService.queryRecords(query.getQueryParam(), query.getPageInfo());
		
		QueryBase queryBase = query;
		queryBase.setTotalItem(pageResult.getTotal());
		
		modelMap.put("datas", pageResult.getResult());
		modelMap.put("query", queryBase);

	}
	
	/**
	 * app端的最终结算单列表接口
	 * @param user
	 * @param query
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = UrlMapping.B_STATEMENT_LIST_FIN_JSON)
	@ResponseBody
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	@ApiSafeAccess
	public  ResultDatas<SellerListPreVO> buyerListFinJson(
			CurrentUser user, OrderStatementQuery query, ModelMap modelMap) {
		query.setStatementType(Constants.STATEMENT_TYPE_I);
		query.setBuyerCustomerId(user.getEpMemberId());
		query.setNoStatus(Constants.STATEMENT_STATUS_01);
		Page<Map<String, Object>> pageResult = orderStatementService.queryRecords(query.getQueryParam(), query.getPageInfo());
		
		QueryBase queryBase = query;
		queryBase.setTotalItem(pageResult.getTotal());
		
		Map<String, Object> params = query.getQueryParam();
		params.put("query", queryBase);

		ResultDatas<SellerListPreVO> resultData = new ResultDatas<>();
		SellerListPreVO sellerListPreVO = new SellerListPreVO();
		sellerListPreVO.setDatas(pageResult.getResult());
		sellerListPreVO.setQuery(queryBase);

		resultData.setDatas(sellerListPreVO);
		
        return resultData;
	}
	/**
	 * 上传付款凭证
	 * @param vo 
	 * @return 
	 * @exception
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.STATEMENT_STATUS, method = RequestMethod.POST)
	@RolesAccess({ "trade_executor"})
	public Result statementEditStatus(OrderStatementVO vo,CurrentUser user) {

		Result res = new Result();
		try {
			orderStatementService.updateStatusById(vo);
		} catch (BizException e) {
			log.error("", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		try {
			//关闭付款凭证上传消息提醒
			OrderStatement orderStatement = orderStatementService.findByPrimaryKey(vo.getOrderStatementId());
			if("2".equals(orderStatement.getStatementType())) {
				msgRemindingService.closeUploadPaymentVoucherReminding(orderStatement.getOrderId(), true);
			}else {
				msgRemindingService.closeUploadPaymentVoucherReminding(orderStatement.getOrderId(), false);
			}
			
			//发送通知
			Order orderInfo = orderService.findByPrimaryKey(orderStatement.getOrderId());
			
			StatementMsgParamVO templateParams = new StatementMsgParamVO();
			templateParams.setEpMemberName(orderInfo.getBuyerCustomerName());
			templateParams.setStatementNo(orderStatement.getOrderStatementNo());
			templateParams.setReceiverId(orderInfo.getSellerPersonId());
			templateParams.setCustomerId(orderInfo.getSellerCustomerId());
			templateParams.setOrderId(orderInfo.getId());
			
			
			String statementDetailLink = "";
			if("2".equals(orderStatement.getStatementType())) {
				statementDetailLink = "sellerCenter/orderStatement/infoFin.htm";
			}else {
				statementDetailLink = "sellerCenter/orderStatement/infoPre.htm";
			}
			templateParams.setStatementDetailLink(orderExecuteServer.get(statementDetailLink)+"?orderStatementUuid="+orderStatement.getOrderStatementUuid());
			
			msgRemindingService.sendStatementProcessMsg(
					StatementStatusEnum.ORDER_STATUS_ITEM_3_9, templateParams);
		} catch (Exception e) {
			log.error("", e);
		}
		
		return res;
	}
	
	/**
	 * 撤销(卖家在待确认状态时可以撤销)
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.S_STATEMENT_EDIT_STATUS, method = RequestMethod.POST)
	public Result editStatusById(OrderStatementVO vo,CurrentUser user) {
		Result res = new Result();
		try {
			orderStatementService.editStatusById(vo);
		} catch (BizException e) {
			log.error("", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
		}
	}

