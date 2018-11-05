package com.sinochem.crude.trade.orderexecute.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sinochem.crude.trade.orderexecute.service.OrderFeeItemService;


@Controller
public class OrderFeeItemController  {

	@Autowired
	private OrderFeeItemService orderFeeItemService;
	
	Log log = LogFactory.getLog(OrderFeeItemController.class);

	/**
	 * 新增费用信息
	 * @param vo
	 * @return
	 * @exception
	 *//*
	@ResponseBody
	@RequestMapping(value="/om/saveOrderFeeItem.json", method = RequestMethod.POST, produces="application/json;text/plain;charset=UTF-8")
	public Result saveOrderFeeItem(@RequestBody OrderFeeItemVO vo,CurrentUser user) {

		Result res = new Result();
		
		try {
			// 验证登录信息
						if (user == null) {
							//throw new TransportException(TransportException.TYPE.ITSH001);
						}
						
			// 新增费用信息
		orderFeeItemService.saveOrderFeeItem(vo, BeanConvertUtils.beanToBean(vo, user));
		
	} catch (OrderExecException e) {
		log.error("getDocumentFileList error", e);
		res.setStatus(Constants.EXCEPTION_STATUS);
		res.setCode(e.getCode());
		res.setMessage(e.getMessage());
	} catch (Exception e) {
		log.error("getDocumentFileList error", e);
		res.setStatus(Constants.EXCEPTION_STATUS);
		res.setCode(Constants.EXCEPTION_CODE);
		res.setMessage(Constants.EXCEPTION_MESSAGE);
	}
		
		return res;
	}
*/
	}

