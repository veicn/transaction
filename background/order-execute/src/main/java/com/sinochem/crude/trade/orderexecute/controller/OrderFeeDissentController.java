package com.sinochem.crude.trade.orderexecute.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.OrderFeeDissentVO;
import com.sinochem.crude.trade.orderexecute.query.OrderFeeDissentQuery;
import com.sinochem.crude.trade.orderexecute.service.OrderFeeDissentService;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
public class OrderFeeDissentController  {

	@Autowired
	private OrderFeeDissentService orderFeeDissentService;
	
	Log log = LogFactory.getLog(OrderFeeDissentController.class);
	
	/**
	 * 取得异议留言
	 * @param vo
	 * @return
	 * @exception
	 */
	@RequestMapping(value = UrlMapping.FEE_DISSENT_REPLY)
	public void orderFeeDissentReply(HttpServletRequest request, CurrentUser user, ModelMap modelMap, OrderFeeDissentQuery query) {
		// 保存参数
		query.setOrderId(Long.parseLong(String.valueOf(request.getAttribute("orderId"))));
		if(request.getAttribute("canReply") != null) {
			query.setCanReply(Integer.parseInt(String.valueOf(request.getAttribute("canReply"))));
		}
		if(request.getAttribute("dissentType") != null) {
			query.setDissentType(String.valueOf(request.getAttribute("dissentType")));
		}

		modelMap.put("query", query);
		// 取得异议留言
		modelMap.put("dissentList", orderFeeDissentService.getDissentList(query));
	}		
	
	/***
	 * 保存留言
	 * 
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.FEE_DISSENT_REPLY_SAVE, method = RequestMethod.POST)
	public ResultDatas<String> saveReplyContent(@RequestBody OrderFeeDissentVO vo, CurrentUser user) {
		log.info("--->" + vo.toString());
		ResultDatas<String> res = new ResultDatas<String>();
		try {
			// 验证登录信息
			if (user == null) {
				//throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			String updateDate = orderFeeDissentService.saveReplyContent(vo, user);
			res.setDatas(updateDate);
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
	 * 订单确认
	 * @param vo
	 * @return
	 * @exception
	 */
	@RequestMapping(value = UrlMapping.FEE_DISSENT_DISSENT)
	public void orderFeeDissent(HttpServletRequest request, CurrentUser user, ModelMap modelMap, OrderFeeDissentQuery query) {
		// 保存参数
		if(request.getAttribute("dissentType") != null) {
			query.setDissentType(String.valueOf(request.getAttribute("dissentType")));
		}
		query.setOrderId(Long.parseLong(String.valueOf(request.getAttribute("orderId"))));
		modelMap.put("query", query);
	}		
	
	/***
	 * 保存结算单确认信息
	 * 
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.FEE_DISSENT_DISSENT_SAVE, method = RequestMethod.POST)
	public Result saveDissentContent(@RequestBody OrderFeeDissentVO vo, CurrentUser user) {
		log.info("--->" + vo.toString());
		Result res = new Result();
		try {
			// 验证登录信息
			if (user == null) {
				//throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			String msg = orderFeeDissentService.saveDissentContent(vo, user);
			if(msg != null) {
				log.error("saveDissentContent error" + msg);
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setCode(Constants.EXCEPTION_CODE);
				res.setMessage(msg);
			}
		} catch (OrderExecException e) {
			log.error("saveDissentContent error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("saveDissentContent error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
}
