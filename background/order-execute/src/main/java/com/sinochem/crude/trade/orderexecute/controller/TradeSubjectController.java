package com.sinochem.crude.trade.orderexecute.controller;

import java.util.HashMap;
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
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.domain.TradeSubject;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.TradeSubjectVO;
import com.sinochem.crude.trade.orderexecute.service.TradeSubjectService;

@Controller
public class TradeSubjectController  {

	@Autowired
	private TradeSubjectService tradeSubjectService;
	
	Log log = LogFactory.getLog(TradeSubjectController.class);
	

	/**
	 * 启动页面
	 * @param user
	 * @param modelMap
	 */
	@RequestMapping(value = UrlMapping.TRADESUBJECT_INIT)
	public void toInterfaceSystem(CurrentUser user, ModelMap modelMap) {
		System.out.println();
	}
	
	/**
	 *贸易费用数据查询
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.TRADESUBJECT_INIT_LIST, method = RequestMethod.POST)
	public ResultDatas<List<Map<String, Object>>> selectListDomesticPrice(@RequestBody TradeSubjectVO vo, CurrentUser user) {
		
		ResultDatas<List<Map<String, Object>>> res = new ResultDatas<List<Map<String, Object>>>();
		try{	
			SimplePageInfo pageInfo = vo.getPageInfo();
			
			Page<Map<String,Object>> page = tradeSubjectService.queryRecords(BeanConvertUtils.beanToMap(vo),pageInfo);
			
			res.setDatas(page);
			res.setPageNum(page.getPageNum());
			res.setPageSize(page.getPageSize());
			res.setPageCount(page.getPages());
			res.setTotal(page.getTotal());
		} catch (OrderExecException e) {
			log.error("selectListDomesticPrice error", e); 
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("selectListDomesticPrice error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
		
	}
	
	/**
	 * 添加贸易费用
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.TRADESUBJECT_ADD, method = RequestMethod.POST)
	public Result addTradeSubject(@RequestBody TradeSubjectVO vo, CurrentUser user) {
		
		Result res = new Result();
		
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("subjectName", vo.getSubjectName());
			map.put("subjectCode", vo.getSubjectCode());
			map.put("productType", vo.getProductType());
			map.put("aliveFlag", vo.getAliveFlag());
			map.put("langVer", vo.getLangVer());
			map.put("version", vo.getVersion());
			
			if (user == null){
				throw new OrderExecException("orderexecute.code.00007","身份信息为空");
			}
			
			vo.setLangVer(Constants.LANG_VER);
			vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			vo.setCreateDate(DateTimeUtils.currentDate());
			vo.setCreateUser(user.getMemberId());
			vo.setUpdateDate(DateTimeUtils.currentDate());
			vo.setUpdateUser(user.getMemberId());			
			
			tradeSubjectService.insertRecord(BeanConvertUtils.beanToBean(vo, TradeSubject.class));
			
		} catch (OrderExecException e) {
			log.error("addTradeSubject error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("addTradeSubject error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	
	/**
	 * 修改贸易费用
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.TRADESUBJECT_EDIT, method = RequestMethod.POST)
	public Result editInterface(@RequestBody TradeSubjectVO vo, CurrentUser user) {
		
		Result res = new Result();
		
		try {
			
			vo.setUpdateDate(DateTimeUtils.currentDate());
			vo.setUpdateUser(user.getMemberId());			
			
			tradeSubjectService.updateRecordById(BeanConvertUtils.beanToBean(vo, TradeSubject.class));
			
		} catch (OrderExecException e) {
			log.error("editInterface error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("editInterface error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 删除贸易费用 逻辑删除
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.TRADESUBJECT_DEL, method = RequestMethod.POST)
	public Result deleteInterface(@RequestBody TradeSubjectVO vo, CurrentUser user) {
		
		Result res = new Result();
		
		try {
			
			if (user == null){
				throw new OrderExecException("orderexecute.code.00007","身份信息为空");
			}
			
			TradeSubject tradeSubject = new TradeSubject();
			
			tradeSubject.setTradeSubjectId(vo.getTradeSubjectId());
			tradeSubject.setUpdateDate(DateTimeUtils.currentDate());
			tradeSubject.setUpdateUser(user.getMemberId());
			tradeSubject.setAliveFlag(Constants.ALIEVE_FLAG_NO);
			
			tradeSubjectService.updateRecordById(tradeSubject);
			
		} catch (OrderExecException e) {
			log.error("deleteInterface error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("deleteInterface error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
}
