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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.InterfaceOutputVO;
import com.sinochem.crude.trade.orderexecute.service.InterfaceOutputService;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
@RolesAccess({"admin"})
public class InterfaceOutputController  {

	@Autowired
	private InterfaceOutputService interfaceOutputService;
	
	
	Log log = LogFactory.getLog(InterfaceOutputController.class);
	
	/**
	 * 重发
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.INTERFACEOUTPUT_EDIT, method = RequestMethod.POST)
	public Result toInterfaceOutput(CurrentUser user, @RequestParam(value="outputId", required=false) Long outputId) {
		Result result = new Result();	
		try {
			result = interfaceOutputService.updateInterfaceOutput(outputId);
			
		} catch (OrderExecException e) {
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(e.getCode());
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(Constants.EXCEPTION_CODE);
			result.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return result;
	}
	
	/**
	 * 启动页面
	 * @param user
	 * @param modelMap
	 */
	@RequestMapping(value = UrlMapping.INTERFACEOUTPUT_OUTPUT)
	public void toInterfaceOutput(CurrentUser user, ModelMap modelMap) {
		System.out.println();
	}
	
	/**
	 * 获取外部系统用户列表
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.INTERFACEOUTPUT_LIST, method = RequestMethod.POST)
	public ResultDatas<List<Map<String, Object>>> getInterfaceOutputList(@RequestBody InterfaceOutputVO vo, CurrentUser user) {
		
		ResultDatas<List<Map<String, Object>>> res = new ResultDatas<List<Map<String, Object>>>();
		
		try {
			SimplePageInfo pageInfo = vo.getPageInfo();
			Page<Map<String,Object>> page = interfaceOutputService.queryRecords(BeanConvertUtils.beanToMap(vo),pageInfo);
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
