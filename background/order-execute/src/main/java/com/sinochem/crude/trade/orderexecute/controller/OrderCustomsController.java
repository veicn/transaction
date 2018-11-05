package com.sinochem.crude.trade.orderexecute.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.constants.ValueSetGroupConstant;
import com.sinochem.crude.trade.orderexecute.commons.utils.PdfTemplateUtil;
import com.sinochem.crude.trade.orderexecute.commons.utils.ValueSetUtil;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderCustoms;
import com.sinochem.crude.trade.orderexecute.domain.OrderGoods;
import com.sinochem.crude.trade.orderexecute.domain.OrderInspection;
import com.sinochem.crude.trade.orderexecute.model.OrderCustomsVO;
import com.sinochem.crude.trade.orderexecute.service.OrderCustomsService;
import com.sinochem.crude.trade.orderexecute.service.OrderGoodsService;
import com.sinochem.crude.trade.orderexecute.service.OrderInspectionService;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * 报关委托书模板功能
 * @author me
 *
 */
@Controller
public class OrderCustomsController {

	@Autowired
	private OrderCustomsService orderCustomsService;
	@Autowired
	private OrderInspectionService orderInspectionService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderGoodsService orderGoodsService;
	
	Log log = LogFactory.getLog(OrderCustomsController.class);
	
	private static final String DATE_FORMAT = "yyyy年MM月dd日";
	
	/**
	 * 初始化报关委托书信息
	 * @param user
	 * @param orderNo
	 * @param model
	 */
	@RequestMapping(value=UrlMapping.INIT_DECLARE_TEMPLATE,method=RequestMethod.GET)
	@RolesAccess({"trade_executor"})
	public void init(CurrentUser user, @RequestParam("orderNo") String orderNo, ModelMap model) {
		OrderCustoms entity = orderCustomsService.findByOrderNo(orderNo);
		if(entity == null) {
			entity = new OrderCustoms();
			
			Order order = orderService.findByOrderNo(orderNo);
			entity.setConsignor(order.getBuyerCustomerName());
			
			OrderGoods orderGoods = orderGoodsService.findByOrderId(order.getId());
			entity.setGoodsName(orderGoods.getZhName());
			entity.setNightstool(orderGoods.getQuantity());
			
			if(StringUtils.isNotBlank(orderGoods.getCountryOrigin())) {
				String zhName = ValueSetUtil.getValueZh(ValueSetGroupConstant.OIL_ORGIN, orderGoods.getCountryOrigin());
				entity.setOrigin(zhName);
			}
		}
		
		model.put("data", entity);
		model.put("orderNo", orderNo);
	}
	
	/**
	 * 保存报关委托书信息
	 * @param form
	 * @return
	 */
	@RequestMapping(value=UrlMapping.SAVE_DECLARE_TEMPLATE,method=RequestMethod.POST)
	@RolesAccess({"trade_executor"})
	public Result save(CurrentUser user, OrderCustomsVO form) {
		Result result = new Result();
		if(StringUtils.isBlank(form.getOrderNo())){
			result.setFail("缺少参数");
			result.setCode("orderexecute.code.00027");
			return result;
		}
		
		OrderCustoms entity = form;
		try {
			if(StringUtils.isNotBlank(form.getValidPeriodStr())) {
				Date date = DateTimeUtils.toDate(form.getValidPeriodStr(), DATE_FORMAT);
				entity.setValidPeriod(date);
			}
			if(StringUtils.isNotBlank(form.getEntrustDateStr())) {
				Date date = DateTimeUtils.toDate(form.getEntrustDateStr(), DATE_FORMAT);
				entity.setEntrustDate(date);
			}
			if(StringUtils.isNotBlank(form.getImportDateStr())) {
				Date date = DateTimeUtils.toDate(form.getImportDateStr(), DATE_FORMAT);
				entity.setImportDate(date);
			}
			if(StringUtils.isNotBlank(form.getStampDateStr())) {
				Date date = DateTimeUtils.toDate(form.getStampDateStr(), DATE_FORMAT);
				entity.setStampDate(date);
			}
		} catch (Exception e) {
			result.setFail("日期格式错误");
			result.setCode("orderexecute.code.00088");
			return result;
		}
		
		Order orderInfo = orderService.findByOrderNo(form.getOrderNo());
		entity.setOrderId(orderInfo.getId());
		entity.setUuid(KeyGenUtils.newUuid());
		
		int num = orderCustomsService.insertOrUpdateRecord(entity,user);
		
		if(num <= 0) {
			result.setFail("保存失败");
			result.setCode("orderexecute.code.00012");
		}
		
		return result;
	}
	
	/**
	 * 下载委托书
	 * @param orderNo 订单编号
	 * @param type 类型，（1：报关，2：报检）
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value=UrlMapping.DOWNLOAD_TEMPLATE,method=RequestMethod.GET)
	@RolesAccess({"trade_executor"})
	public void download(
			@RequestParam("orderNo") String orderNo, 
			@RequestParam("type") String type, 
			HttpSession session,
			HttpServletResponse response) throws IOException {
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "attachment;Filename="
				+ KeyGenUtils.newUuid()+".pdf");
		
		Map<String, Object> data;
		String filePath = "";
		if("1".equals(type)) {
			filePath = "/template/declare_template.pdf";
			OrderCustoms entity = orderCustomsService.findByOrderNo(orderNo);
			entity.setGoodsName(entity.getGoodsName()+" 原油");
			data = entity.toMap();
			if(entity.getValidPeriod() != null) {
				data.put("validPeriod_year", DateTimeUtils.toDateString(entity.getValidPeriod(), "yyyy"));
				data.put("validPeriod_month", DateTimeUtils.toDateString(entity.getValidPeriod(), "MM"));
				data.put("validPeriod_day", DateTimeUtils.toDateString(entity.getValidPeriod(), "dd"));
			}
			if(entity.getEntrustDate() != null) {
				data.put("entrustDate_year", DateTimeUtils.toDateString(entity.getEntrustDate(), "yyyy"));
				data.put("entrustDate_month", DateTimeUtils.toDateString(entity.getEntrustDate(), "MM"));
				data.put("entrustDate_day", DateTimeUtils.toDateString(entity.getEntrustDate(), "dd"));
			}
			if(entity.getImportDate() != null) {
				data.put("importDate_year", DateTimeUtils.toDateString(entity.getImportDate(), "yyyy"));
				data.put("importDate_month", DateTimeUtils.toDateString(entity.getImportDate(), "MM"));
				data.put("importDate_day", DateTimeUtils.toDateString(entity.getImportDate(), "dd"));
			}
			if(entity.getStampDate() != null) {
				data.put("stampDate_year", DateTimeUtils.toDateString(entity.getStampDate(), "yyyy"));
				data.put("stampDate_month", DateTimeUtils.toDateString(entity.getStampDate(), "MM"));
				data.put("stampDate_day", DateTimeUtils.toDateString(entity.getStampDate(), "dd"));
			}
			
		}else if("2".equals(type)) {
			filePath = "/template/inspection_template.pdf";
			OrderInspection entity = orderInspectionService.findByOrderNo(orderNo);
			entity.setCommodityName(entity.getCommodityName()+" 原油");
			data = entity.toMap();
			if(entity.getValidPeriod() != null) {
				data.put("validPeriod_year", DateTimeUtils.toDateString(entity.getValidPeriod(), "yyyy"));
				data.put("validPeriod_month", DateTimeUtils.toDateString(entity.getValidPeriod(), "MM"));
				data.put("validPeriod_day", DateTimeUtils.toDateString(entity.getValidPeriod(), "dd"));
			}
			if(entity.getEntrustDate() != null) {
				data.put("entrustDate_year", DateTimeUtils.toDateString(entity.getEntrustDate(), "yyyy"));
				data.put("entrustDate_month", DateTimeUtils.toDateString(entity.getEntrustDate(), "MM"));
				data.put("entrustDate_day", DateTimeUtils.toDateString(entity.getEntrustDate(), "dd"));
			}
			if(entity.getEntrustedDate() != null) {
				data.put("entrustedDate_year", DateTimeUtils.toDateString(entity.getEntrustedDate(), "yyyy"));
				data.put("entrustedDate_month", DateTimeUtils.toDateString(entity.getEntrustedDate(), "MM"));
				data.put("entrustedDate_day", DateTimeUtils.toDateString(entity.getEntrustedDate(), "dd"));
			}
		}else {
			return;
		}
		
		String pdfTemplatePath = getClass().getResource(filePath).getPath();
		ServletOutputStream os = response.getOutputStream();
		
		PdfTemplateUtil.exportPdf(pdfTemplatePath, data, os);
	}
	
}
