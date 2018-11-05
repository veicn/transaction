package com.sinochem.crude.trade.orderexecute.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.constants.ValueSetGroupConstant;
import com.sinochem.crude.trade.orderexecute.commons.utils.ValueSetUtil;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderGoods;
import com.sinochem.crude.trade.orderexecute.domain.OrderShip;
import com.sinochem.crude.trade.orderexecute.domain.OrderShipLoadinginfo;
import com.sinochem.crude.trade.orderexecute.domain.OrderShipUnloadinginfo;
import com.sinochem.crude.trade.orderexecute.model.InspectionContentForDischargeportVO;
import com.sinochem.crude.trade.orderexecute.model.InspectionContentForLoadingportVO;
import com.sinochem.crude.trade.orderexecute.model.InspectionOrderListVO;
import com.sinochem.crude.trade.orderexecute.query.InspectionOrderQuery;
import com.sinochem.crude.trade.orderexecute.service.InspectionService;
import com.sinochem.crude.trade.orderexecute.service.OrderGoodsService;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.crude.trade.orderexecute.service.OrderShipLoadinginfoService;
import com.sinochem.crude.trade.orderexecute.service.OrderShipService;
import com.sinochem.crude.trade.orderexecute.service.OrderShipUnloadinginfoService;
import com.sinochem.it.b2b.member.access.RolesAccess;

@Controller
public class InspectionController {
	@Autowired
	private InspectionService inspectionService;
	@Autowired
	private OrderShipLoadinginfoService loadinginfoService;
	@Autowired
	private OrderShipUnloadinginfoService unloadinginfoService;
	@Autowired
	private OrderShipService orderShipService;
	@Autowired
	private OrderGoodsService orderGoodsService;
	@Autowired
	private OrderService orderService;
	
	/**
	 * 商检订单列表
	 * @param user
	 */
	@RequestMapping(value=UrlMapping.INSPECTION_ORDER_LIST, method=RequestMethod.GET)
	@RolesAccess({"inspection"})
	public void listOrders(CurrentUser user,InspectionOrderQuery query, ModelMap model) {
		Map<String, Object> queryParam = query.getQueryParam();
		queryParam.put("memberId", user.getEpMemberId());
		Page<InspectionOrderListVO> pageResult = inspectionService.queryListForPage(queryParam, query.getPageInfo());
		
		Map<String, Object> selectItemQuery = new HashMap<>();
		selectItemQuery.put("memberId", user.getEpMemberId());
		List<String> distinctShipList = inspectionService.distinctShipForInspection(selectItemQuery);
		List<String> distinctPortList = inspectionService.distinctPortForInspection(selectItemQuery);
		
		QueryBase queryBase = (QueryBase) query;
		queryBase.setTotalItem(pageResult.getTotal());
		
		model.put("param", query.getParameters());
		model.put("query", queryBase);
		model.put("dataList", pageResult.getResult());
		model.put("shipList", distinctShipList);
		model.put("portList", distinctPortList);
	}
	
	/**
	 * 卸港商检详情
	 * @param id 卸港ID
	 * @return
	 */
	@RequestMapping(value= {UrlMapping.GET_INSPECTION_DISCHARGEPORT_DETAIL,UrlMapping.EDIT_INSPECTION_DISCHARGEPORT_DETAIL}, method=RequestMethod.GET)
	@RolesAccess({"inspection","trade_executor"})
	public void getInspectionDischargeportDetail(
			@RequestParam(value="id") Long id, 
			@RequestParam(value="orderNo") String orderNo,
			ModelMap model) {
		OrderShip orderShip = orderShipService.findByOrderNo(orderNo);
		InspectionContentForDischargeportVO data = inspectionService.getInspectionDischargeportDetail(id);
		
		Map<String, Object> countLoadportParams = new HashMap<>();
		countLoadportParams.put("orderNo", orderNo);
		int countLoadport = loadinginfoService.countRecords(countLoadportParams);
		
		OrderShipUnloadinginfo unloadingInfo = unloadinginfoService.findByPrimaryKey(id);
		CodeValue portCode = ValueSetUtil.getByCode(ValueSetGroupConstant.UN_LOAD_PORT, unloadingInfo.getUnloadPort());
		unloadingInfo.setUnloadPort(portCode.getValue());
		
		Order orderInfo = orderService.findByOrderNo(orderNo);
		OrderGoods orderGoods = orderGoodsService.findByOrderId(orderInfo.getId());
		unloadingInfo.setOil(orderGoods.getEnName());
		
		model.put("id", id);
		model.put("orderNo", orderNo);
		model.put("countLoadport", countLoadport);
		model.put("data", data);
		
		model.put("unloadingInfo", unloadingInfo);
		model.put("orderShip", orderShip);
	}
	
	/**
	 * 装港商检详情
	 * @param id 卸港ID
	 * @param loadportId 装港ID
	 * @param orderNo 订单号
	 * @param model
	 */
	@RequestMapping(value= {UrlMapping.GET_INSPECTION_LOADPORT_DETAIL,UrlMapping.EDIT_INSPECTION_LOADPORT_DETAIL}, method=RequestMethod.GET)
	@RolesAccess({"inspection","trade_executor"})
	public void getInspectionLoadportDetail(
			@RequestParam(value="id") Long id, 
			@RequestParam(value="loadportId",required=false) Long loadportId, 
			@RequestParam(value="orderNo") String orderNo, 
			ModelMap model) {
		
		List<OrderShipLoadinginfo> loadingportList = loadinginfoService.findByOrderNo(orderNo);
		OrderShipLoadinginfo loadinginfo;
		if(loadportId == null) {
			loadinginfo = loadingportList.get(0);
			loadportId = loadinginfo.getId();
		}else {
			loadinginfo = loadinginfoService.findByPrimaryKey(loadportId);
		}
		
		Order orderInfo = orderService.findByOrderNo(orderNo);
		OrderGoods orderGoods = orderGoodsService.findByOrderId(orderInfo.getId());
		loadinginfo.setOil(orderGoods.getEnName());
		
		OrderShip orderShip = orderShipService.findByOrderNo(orderNo);
		InspectionContentForLoadingportVO data = inspectionService.getInspectionLoadportDetail(loadportId);
		
		model.put("id", id);
		model.put("orderNo", orderNo);
		model.put("loadportId", loadportId);
		model.put("orderShip", orderShip);
		model.put("loadinginfo", loadinginfo);
		
		model.put("loadportList", loadingportList);
		model.put("data", data);
		
	}
	
	/**
	 * 保存装港商检详情信息
	 * @param user
	 * @param loadportId 装港ID
	 * @param contentVO
	 * @return
	 */
	@RequestMapping(value=UrlMapping.SAVE_INSPECTION_LOADPORT_DETAIL, method=RequestMethod.POST)
	@ResponseBody
	@RolesAccess({"inspection"})
	public Result saveInspectionLoadportDetail(CurrentUser user, Long loadportId, InspectionContentForLoadingportVO contentVO) {
		Result result = new Result();
		
		if(loadportId == null) {
			result.setFail("缺少参数");
			result.setCode("orderexecute.code.00027");
			return result;
		}
		
		int num = inspectionService.saveInspectionLoadportDetail(loadportId, contentVO);
		if(num <= 0) {
			result.setFail("保存失败");
			result.setCode("orderexecute.code.00012");
		}
		
		return result;
	}
	
	/**
	 * 保存卸港商检详情信息
	 * @param user
	 * @param id 卸港信息ID
	 * @param contentVO
	 * @return
	 */
	@RequestMapping(value=UrlMapping.SAVE_INSPECTION_DISCHARGEPORT_DETAIL, method=RequestMethod.POST)
	@ResponseBody
	@RolesAccess({"inspection"})
	public Result saveInspectionDischargeportDetail(CurrentUser user, Long id, InspectionContentForDischargeportVO contentVO) {
		Result result = new Result();
		
		if(id == null) {
			result.setFail("缺少参数");
			result.setCode("orderexecute.code.00027");
			return result;
		}
		
		int num = inspectionService.saveInspectionDischargeportDetail(id, contentVO);
		if(num <= 0) {
			result.setFail("保存失败");
			result.setCode("orderexecute.code.00012");
		}
		
		return result;
	}

}
