package com.sinochem.crude.trade.orderexecute.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.domain.OrderDocumentList;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.OrderDocumentListVO;
import com.sinochem.crude.trade.orderexecute.service.OrderDocumentListService;

@Controller
public class OrderDocumentListController  {

	@Autowired
	private OrderDocumentListService orderDocumentListService;
	
	private static final Log log = LogFactory.getLog(OrderDocumentListController.class);

	//private final static String APPLICATION_JSON_UTF8_VALUE = MediaType.APPLICATION_JSON + ";charset=UTF-8";
	
	
	/**
	 * 启动页面
	 * @param user
	 * @param modelMap
	 */
	@RequestMapping(value = UrlMapping.DOCUMENT_INIT)
	public void toInterfaceSystem(CurrentUser user, ModelMap modelMap) {
		System.out.println();
	}
	
	
	/**
	 *单证清单list
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.DOCUMENT_INIT_LIST, method = RequestMethod.POST)
	public ResultDatas<List<Map<String, Object>>> selectListDomesticPrice(@RequestBody OrderDocumentListVO vo, CurrentUser user) {
		
		ResultDatas<List<Map<String, Object>>> res = new ResultDatas<List<Map<String, Object>>>();
		try{	
			SimplePageInfo pageInfo = vo.getPageInfo();
			
			Page<Map<String,Object>> page = orderDocumentListService.queryRecords(BeanConvertUtils.beanToMap(vo),pageInfo);
			
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
	 * 单证清单list更多查询
	 * @param uuid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.DOCUMENT_LIST_BUYID, method = RequestMethod.POST)
	public ResultDatas<List<OrderDocumentList>> queryByEntitys(@RequestBody OrderDocumentListVO vo, CurrentUser user,Long documentId) {
		ResultDatas<List<OrderDocumentList>> result = new ResultDatas<>();

		OrderDocumentList  orderDocumentList= new OrderDocumentList();  
		orderDocumentList.setDocumentType(vo.getDocumentType());
		List<OrderDocumentList> list = orderDocumentListService.queryByEntitys(orderDocumentList);
		result.setDatas(list);
		return result;
	}

	/**
	 * 添加单证清单
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.DOCUMENT_LIST_ADD, method = RequestMethod.POST)
	public Result addOrderDocumentList(@RequestBody OrderDocumentListVO vo, CurrentUser user) {
		
		Result res = new Result();
		
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("tradeCategory", vo.getTradeCategory());
			map.put("documentTypeDesc", vo.getDocumentTypeDesc());
			map.put("documentType", vo.getDocumentType());
			map.put("fileName", vo.getFileName());
			map.put("fileCode", vo.getFileCode());
			map.put("fromUser", vo.getFromUser());
			map.put("toUser", vo.getToUser());
			
			if (user == null){
				throw new OrderExecException("orderexecute.code.00007","身份信息为空");
			}
			
			vo.setLangVer(Constants.LANG_VER);
			vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			vo.setCreateDate(DateTimeUtils.currentDate());
			vo.setCreateUser(user.getMemberId());
			vo.setUpdateDate(DateTimeUtils.currentDate());
			vo.setUpdateUser(user.getMemberId());			
			
			orderDocumentListService.insertRecord(BeanConvertUtils.beanToBean(vo, OrderDocumentList.class));
			
		} catch (OrderExecException e) {
			log.error("addOrderDocumentList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("addOrderDocumentList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	
	/**
	 * 新增值集
	 * @param vo
	 * @param user
	 * @return
	 */
	@RequestMapping(value =UrlMapping.DOCUMENT_LIST_SET_ADD)
	@ResponseBody
	public ResultDatas<Void> addCodeSet(@RequestBody OrderDocumentListVO vo,CurrentUser user) {
		ResultDatas<Void> res = new ResultDatas<>();
		try {
			String tradeCategory = vo.getTradeCategory();
			String documentType = vo.getDocumentType();
			String documentTypeDesc = vo.getDocumentTypeDesc();
			if (Strings.isNullOrEmpty(tradeCategory) || Strings.isNullOrEmpty(documentType) || Strings.isNullOrEmpty(documentTypeDesc)){
				res.setFail("明细组不存在");
				res.setCode("orderexecute.code.00016");
				return res;
			}
			if ("1".equals(vo.getAliveFlag())) {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("fileCode", vo.getFileCode());
				map.put("fromUser", vo.getFromUser());
				map.put("toUser", vo.getToUser());
				int count = orderDocumentListService.countRecords(map);
				
				if (count != 0) {
					res.setFail("该明细已存在");
					res.setCode("orderexecute.code.00017");
					return res;
				}
			} else {
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("fileCode", vo.getFileCode());
				map.put("fromUser", vo.getFromUser());
				map.put("toUser", vo.getToUser());
				
				int count = orderDocumentListService.countRecords(map);
				
				if (count != 0) {
					res.setFail("该明细已存在");
					res.setCode("orderexecute.code.00017");
					return res;
				}
			}
			
			vo.setLangVer(Constants.LANG_VER);
			vo.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			vo.setCreateDate(DateTimeUtils.currentDate());
			vo.setCreateUser(user.getMemberId());
			vo.setUpdateDate(DateTimeUtils.currentDate());
			vo.setUpdateUser(user.getMemberId());			
			orderDocumentListService.insertRecord(BeanConvertUtils.beanToBean(vo, OrderDocumentList.class));
		} catch (OrderExecException e) {
			log.error("addOrderDocumentList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("addOrderDocumentList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}

		return res;
	}
	
	
	

	
	
	/**
	 * 删除单证清单子集   逻辑删除
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.DOCUMENT_LIST_SET_DEL, method = RequestMethod.POST)
	
	public ResultDatas<Void> delCodeSet(@RequestBody OrderDocumentListVO vo,CurrentUser user) {	
		ResultDatas<Void> res = new ResultDatas<>();
		try {
			
			String tradeCategory = vo.getTradeCategory();
			String documentTypeDesc = vo.getDocumentTypeDesc();
			if (StringUtils.isNotBlank(tradeCategory) || StringUtils.isNotBlank(documentTypeDesc)) {
				
				OrderDocumentListVO orderDocumentList = new OrderDocumentListVO();
				
				orderDocumentList.setDocumentId(vo.getDocumentId());
				orderDocumentList.setUpdateDate(DateTimeUtils.currentDate());
				orderDocumentList.setUpdateUser(user.getMemberId());
				orderDocumentList.setAliveFlag(Constants.ALIEVE_FLAG_NO);
				orderDocumentListService.updateRecordById(orderDocumentList);	
			 
				}
			} catch (OrderExecException e) {
				log.error("OrderDocumentList error", e);
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setCode(e.getCode());
				res.setMessage(e.getMessage());
			} catch (Exception e ) {
				log.error("OrderDocumentList error", e);
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setCode(Constants.EXCEPTION_CODE);
				res.setMessage(Constants.EXCEPTION_MESSAGE);
			}
		
		
			return res;
		}
	
	
	
	/**
	 * 删除单证清单   逻辑删除
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.DOCUMENT_LIST_DEL, method = RequestMethod.POST)
	
	public ResultDatas<Void>  delDocumentList(@RequestBody OrderDocumentListVO vo,CurrentUser user) {	
		ResultDatas<Void> resultDTO = new ResultDatas<>();
			OrderDocumentListVO query = new OrderDocumentListVO();
			Long documentId = vo.getDocumentId();
			String tradeCategory = vo.getTradeCategory();
			String documentType = vo.getDocumentType();
			String documentTypeDesc = vo.getDocumentTypeDesc();
			if (Strings.isNullOrEmpty(tradeCategory) || Strings.isNullOrEmpty(documentType) || Strings.isNullOrEmpty(documentTypeDesc)){
				resultDTO.setFail("明细组不存在");
				resultDTO.setCode("orderexecute.code.00017");
				return resultDTO;
			}
			query.setDocumentId(documentId);
			query.setTradeCategory(tradeCategory);
			query.setDocumentType(documentType);
			query.setDocumentTypeDesc(documentTypeDesc);
			
			Map<String, Object> map = Maps.newHashMap();
			map.put("tradeCategory", tradeCategory);
			map.put("documentType", documentType);
			map.put("documentTypeDesc", documentTypeDesc);
			
			orderDocumentListService.updateDeleteRecords(map);
			

		return resultDTO;
	}

	/**
	 * 修改单证清单   
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.DOCUMENT_LIST_EDIT, method = RequestMethod.POST)
	
	public ResultDatas<Void>  editDocumentList(@RequestBody OrderDocumentListVO vo,CurrentUser user) {	
		ResultDatas<Void> resultDTO = new ResultDatas<>();
			
			OrderDocumentListVO query = new OrderDocumentListVO();
			Long documentId = vo.getDocumentId();
			String tradeCategory = vo.getTradeCategory();
			String documentType = vo.getDocumentType();
			String documentTypeDesc = vo.getDocumentTypeDesc();
			
			String oldTradeCategory = vo.getOldTradeCategory();
			String oldDocumentType = vo.getOldDocumentType();
			String oldDocumentTypeDesc = vo.getOldDocumentTypeDesc();
			
			if (Strings.isNullOrEmpty(tradeCategory) || Strings.isNullOrEmpty(documentType) || Strings.isNullOrEmpty(documentTypeDesc)){
				resultDTO.setFail("明细组不存在");
				resultDTO.setCode("orderexecute.code.00017");
				return resultDTO;
			}
			query.setDocumentId(documentId);
			query.setTradeCategory(tradeCategory);
			query.setDocumentType(documentType);
			query.setDocumentTypeDesc(documentTypeDesc);
			
			query.setOldTradeCategory(vo.getOldTradeCategory());
			query.setOldDocumentType(vo.getOldDocumentType());
			query.setOldDocumentTypeDesc(vo.getOldDocumentTypeDesc());
			
			query.setTradeCategory(vo.getTradeCategory());
			query.setDocumentType(vo.getDocumentType());
			query.setDocumentTypeDesc(vo.getDocumentTypeDesc());
			
			Map<String, Object> map = Maps.newHashMap();
			map.put("tradeCategory", tradeCategory);
			map.put("documentType", documentType);
			map.put("documentTypeDesc", documentTypeDesc);
			
			map.put("oldTradeCategory", oldTradeCategory);
			map.put("oldDocumentType", oldDocumentType);
			map.put("oldDocumentTypeDesc", oldDocumentTypeDesc);
			
			orderDocumentListService.updateRecords(map);
			

		return resultDTO;
	}
	/**
	 * 修改单证清单子集
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.DOCUMENT_LIST_SET_EDIT, method = RequestMethod.POST)
	public Result OrderDocumentList(@RequestBody OrderDocumentListVO vo, CurrentUser user) {
		
		ResultDatas<Void> res = new ResultDatas<>();
		
		try {
			vo.setUpdateDate(DateTimeUtils.currentDate());
			vo.setUpdateUser(user.getMemberId());			
			
			orderDocumentListService.updateRecordById(BeanConvertUtils.beanToBean(vo, OrderDocumentList.class));
			
		} catch (OrderExecException e) {
			log.error("OrderDocumentList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("OrderDocumentList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	

}
