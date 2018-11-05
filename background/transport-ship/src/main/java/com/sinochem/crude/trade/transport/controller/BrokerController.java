package com.sinochem.crude.trade.transport.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.URLMapping;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.domain.Broker;
import com.sinochem.crude.trade.transport.model.BrokerVO;
import com.sinochem.crude.trade.transport.service.BrokerService;


@Controller
public class BrokerController  {
	@Autowired
	private BrokerService brokerService;

	Log log = LogFactory.getLog(BrokerController.class);

	/**
	 * 查询经纪人信息列表
	 * @param vo
	 * @return
	 * @exception
	 */
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_BROKER_LIST, method = RequestMethod.POST )
	public ResultDatas<List<Broker>> findBrokerList( @RequestBody BrokerVO vo, CurrentUser user) {

		ResultDatas<List<Broker>> res = new ResultDatas<>();
		
		try {
			//验证登录信息
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 查询经纪人信息列表
			List<Broker> brokerList= brokerService.findBrokerList(BeanConvertUtils.beanToBean(vo, Broker.class));
			
			//JSONArray jsonarray = JSONArray.fromObject(brokerList);
			//String brokerStr = jsonarray.toString();
			
			// 设定返回数据
			res.setDatas(brokerList);
			
		} catch (BizException e) {
			log.error("findBrokerList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
			
		} catch (Exception e ) {
			log.error("findBrokerList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
}
