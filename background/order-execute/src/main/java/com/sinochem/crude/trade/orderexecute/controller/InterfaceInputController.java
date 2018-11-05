package com.sinochem.crude.trade.orderexecute.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.InterfaceInputVO;
import com.sinochem.crude.trade.orderexecute.service.InterfaceInputService;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
@RolesAccess({"admin"})
public class InterfaceInputController  {

	@Autowired
	private InterfaceInputService interfaceInputService;
	
	Log log = LogFactory.getLog(InterfaceInputController.class);
	
	/**
	 * 启动页面
	 * @param user
	 * @param modelMap
	 */
	@RequestMapping(value = UrlMapping.INTERFACEOUTPUT_INTPUT)
	public void toInterfaceOutput(CurrentUser user, ModelMap modelMap) {
		System.out.println();
	}
	
	/**
	 * 日志记录查询json
	 * @param query 查询条件入参
	 * @param models
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.INTERFACEINPUT_LOGGING_JSON, method = RequestMethod.POST)
	public ResultDatas<List<Map<String, Object>>> getInterfaceOutputList(@RequestBody InterfaceInputVO vo, CurrentUser user) {
		
		ResultDatas<List<Map<String, Object>>> res = new ResultDatas<List<Map<String, Object>>>();
		
		try {
			SimplePageInfo pageInfo = vo.getPageInfo();
			Page<Map<String,Object>> page = interfaceInputService.queryRecords(BeanConvertUtils.beanToMap(vo),pageInfo);
//			List<InterfaceOutput> list = BeanConvertUtils.mapToBeanInList(page, InterfaceOutput.class);
			res.setDatas(page);
			res.setPageNum(page.getPageNum());
			res.setPageSize(page.getPageSize());
			res.setPageCount(page.getPages());
			res.setTotal(page.getTotal());
		} catch (OrderExecException e) {
			log.error("getInterfaceOutputList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("getInterfaceOutputList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	
	
	
}
