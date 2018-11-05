package com.sinochem.crude.trade.orderexecute.controller;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderGoods;
import com.sinochem.crude.trade.orderexecute.domain.OrderInspection;
import com.sinochem.crude.trade.orderexecute.model.OrderInspectionVO;
import com.sinochem.crude.trade.orderexecute.service.OrderGoodsService;
import com.sinochem.crude.trade.orderexecute.service.OrderInspectionService;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * 报检委托书模板功能
 * @author me
 *
 */
@Controller
public class OrderInspectionController {

	@Autowired
	private OrderInspectionService orderInspectionService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderGoodsService orderGoodsService;
	
	Log log = LogFactory.getLog(OrderInspectionController.class);
	
	private static final String DATE_FORMAT = "yyyy年MM月dd日";
	
	/**
	 * 初始化报检委托书信息
	 * @param user
	 * @param orderNo
	 * @param model
	 */
	@RequestMapping(value=UrlMapping.INIT_INSPECTION_TEMPLATE,method=RequestMethod.GET)
	@RolesAccess({"trade_executor"})
	public void init(CurrentUser user, @RequestParam("orderNo") String orderNo, ModelMap model) {		
		OrderInspection entity = orderInspectionService.findByOrderNo(orderNo);
		if(entity == null) {
			entity = new OrderInspection();
		}
		
		Order order = orderService.findByOrderNo(orderNo);
		/*if(StringUtils.isBlank(entity.getConsignor()))
			entity.setConsignor(order.getBuyerCustomerName());*/
		
		OrderGoods orderGoods = orderGoodsService.findByOrderId(order.getId());
		if(StringUtils.isBlank(entity.getCommodityName()))
			entity.setCommodityName(orderGoods.getZhName());
		if(entity.getNightstool()==null)
			entity.setNightstool(orderGoods.getQuantity());
		
		EnterpriseVo memerInfo = enterpriseService.getByMemberId(user.getEpMemberId());
		if(StringUtils.isBlank(entity.getOrganizationCode()))
			entity.setOrganizationCode(memerInfo.getOrganizationCode());
		
		model.put("data", entity);
		model.put("orderNo", orderNo);
	}
	
	/**
	 * 保存报检委托书信息
	 * @param form
	 * @return
	 */
	@RequestMapping(value=UrlMapping.SAVE_INSPECTION_TEMPLATE,method=RequestMethod.POST)
	@RolesAccess({"trade_executor"})
	public Result save(CurrentUser user, OrderInspectionVO form) {
		Result result = new Result();
		if(StringUtils.isBlank(form.getOrderNo())){
			result.setFail("缺少参数");
			result.setCode("orderexecute.code.00027");
			return result;
		}
		
		OrderInspection entity = form;
		try {
			if(StringUtils.isNotBlank(form.getValidPeriodStr())) {
				Date date = DateTimeUtils.toDate(form.getValidPeriodStr(), DATE_FORMAT);
				entity.setValidPeriod(date);
			}
			if(StringUtils.isNotBlank(form.getEntrustDateStr())) {
				Date date = DateTimeUtils.toDate(form.getEntrustDateStr(), DATE_FORMAT);
				entity.setEntrustDate(date);
			}
			if(StringUtils.isNotBlank(form.getEntrustedDateStr())) {
				Date date = DateTimeUtils.toDate(form.getEntrustedDateStr(), DATE_FORMAT);
				entity.setEntrustedDate(date);
			}
		} catch (Exception e) {
			result.setFail("日期格式错误");
			result.setCode("orderexecute.code.00088");
			return result;
		}
		
		Order orderInfo = orderService.findByOrderNo(form.getOrderNo());
		entity.setOrderId(orderInfo.getId());
		entity.setUuid(KeyGenUtils.newUuid());
		
		int num = orderInspectionService.insertOrUpdateRecord(entity,user);
		if(num <= 0) {
			result.setFail("保存失败");
			result.setCode("orderexecute.code.00012");
		}
		
		return result;
	}
	
}
