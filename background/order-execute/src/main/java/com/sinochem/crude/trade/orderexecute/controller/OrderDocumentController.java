package com.sinochem.crude.trade.orderexecute.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.ValueSet;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.commons.constants.ValueSetGroupConstant;
import com.sinochem.crude.trade.orderexecute.commons.utils.ValueSetUtil;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderDetailView;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.model.OrderDocumentVO;
import com.sinochem.crude.trade.orderexecute.query.DocumentQuery;
import com.sinochem.crude.trade.orderexecute.service.OrderDocumentService;
import com.sinochem.crude.trade.orderexecute.service.OrderMessagePushService;
import com.sinochem.crude.trade.orderexecute.service.OrderService;
import com.sinochem.it.b2b.member.access.ApiSafeAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
//@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
public class OrderDocumentController  {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDocumentService orderDocumentService;
	
	@Autowired
	private OrderMessagePushService orderMessagePushService;
	
	@Value("${aliyun.oss.bucket}")
	private String bucket;
	
	@Resource(name="orderExecuteServer")
	private URLBroker orderHost;
	Log log = LogFactory.getLog(OrderDocumentController.class);
	
	
	/***
	 * 所有单证的列表
	 * 
	 * @param vo
	 * @param user
	 * @return
	 */
	@RequestMapping(value = UrlMapping.DOCUMENT_EDIT_NEW)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public void documentEditNew(CurrentUser user, ModelMap modelMap, DocumentQuery query) {
		log.info("--->" + query.toString());
		Order order = orderService.findByUuid(query.getUuid());
		query.setTradeCategory(order.getTradeCategory());
		
		Map<String,Object> mainData = orderDocumentService.getMainData(query);
		List<Map<String,Object>> subData = orderDocumentService.queryDocumentFileList(query);
		
		ValueSet i18nValueSet = ValueSetUtil.instance(ValueSetGroupConstant.DOCUMENT_LIST);
		for (Map<String, Object> map : subData) {
			String filePath = (String) map.get("filePath");
			if(filePath != null){
				if(!filePath.contains("http")){
					//下载  $orderExecuteServer.get('/common/doc/download.htm')?path=$!{subData.filePath}&fileName=$!{subData.originalName}
					map.put("downloadUrl", orderHost.get("/common/doc/download.htm")+"?path=" + map.get("filePath") + "&fileName=" + map.get("originalName"));
					//预览  $orderExecuteServer.get('/common/doc/file.htm')?path=$!{subData.filePath}&fileName=$!{subData.originalName} 
					map.put("lookUrl",  orderHost.get("/common/doc/file.htm")+"?path=" + map.get("filePath") + "&fileName=" + map.get("originalName"));
				}else{
					map.put("fileFormat","xxx");
					map.put("downloadUrl", map.get("filePath"));
					map.put("lookUrl", 	map.get("filePath"));
				}
			}
			
			//获取国际化名称
			String fileCode = String.valueOf(map.get("fileCode"));
			String fileName = String.valueOf(map.get("fileName"));
			map.put("fileName", i18nValueSet.getByCode(fileCode,fileName).getValue());
		}
		List<Map<String,Object>> subDataCheck = orderDocumentService.getDocumentFileList(query);
		modelMap.put("mainData", mainData);
		modelMap.put("subDatas", subData);
		modelMap.put("subDataCheck", subDataCheck);
		modelMap.put("query", query);
	}
	
	/***
	 * 不含自定义单证的列表
	 * 
	 * @param vo
	 * @param user
	 * @return
	 */
	@RequestMapping(value = UrlMapping.DOCUMENT_CHECK)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public void documentEdit(CurrentUser user, ModelMap modelMap, DocumentQuery query) {
		log.info("--->" + query.toString());

		Map<String,Object> mainData = orderDocumentService.getMainData(query);
		List<Map<String,Object>> documentType = orderDocumentService.getDocumentType(query);
		List<Map<String,Object>> subDataCheck = orderDocumentService.getDocumentFileList(query);
		//国际化名称转换
		ValueSet i18nValueSet = ValueSetUtil.instance(ValueSetGroupConstant.DOCUMENT_LIST);
		
		for(Map<String,Object> data : subDataCheck) {
			String code = String.valueOf(data.get("fileCode"));
			String name = String.valueOf(data.get("fileName"));
			data.put("fileName", i18nValueSet.getByCode(code,name).getValue());
		}
		
		modelMap.put("mainData", mainData);
		modelMap.put("subDataCheck", subDataCheck);
		modelMap.put("documentType", documentType);
		modelMap.put("query", query);
	}
	
	/**
	 * 查询单证附件
	 * @param vo
	 * @return
	 * @exception
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.DOCUMENT_DETAIL)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	@ApiSafeAccess
	public ResultDatas<Map<String, Object>> getDocumentDetail(
			@RequestParam("uuid") String uuid,
			@RequestParam(value="documentType", required=false) String documentType
			) {
		ResultDatas<Map<String, Object>> res = new ResultDatas<Map<String, Object>>();
		
		try {
			DocumentQuery query = new DocumentQuery();
			query.setUuid(uuid);
			query.setDocumentType(documentType);
			
			Map<String, Object> map = orderDocumentService.getDocumentDetail(query);
			if(map != null) {
				query.setTradeCategory(String.valueOf(map.get("tradeCategory")));
				List<Map<String,Object>> fileList = orderDocumentService.queryDocumentFileList(query);
				
				ValueSet i18nValueSet = ValueSetUtil.instance(ValueSetGroupConstant.DOCUMENT_LIST);
				for (Map<String, Object> orderDocumentFile : fileList) {
					String filePath = String.valueOf(orderDocumentFile.get("filePath"));
					if(filePath != null){
						if(!filePath.contains("http")){
							orderDocumentFile.put("filePath", orderHost.get("/common/doc/download.htm")+"?path=" + orderDocumentFile.get("filePath") + "&fileName=" + orderDocumentFile.get("originalName"));
							orderDocumentFile.put("lookUrl",  orderHost.get("/common/doc/file.htm")+"?path=" + orderDocumentFile.get("filePath") + "&fileName=" + orderDocumentFile.get("originalName"));
						}
					}
					
					//国际化名称转换
					String code = String.valueOf(orderDocumentFile.get("fileCode"));
					String name = String.valueOf(orderDocumentFile.get("fileName"));
					orderDocumentFile.put("fileName", i18nValueSet.getByCode(code, name).getValue());
					
				}
				map.put("fileList", fileList);
				res.setDatas(map);
			} else {
				new OrderExecException("orderexecute.code.00015","没有找到单证信息");
			}
			
		} catch (OrderExecException e) {
			log.error("getDocumentDetailJSON error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("getDocumentDetailJSON error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/***
	 * 取得附件代码列表
	 * 
	 * @param vo
	 * @param user
	 * @return
	 */
	@RequestMapping(value = UrlMapping.DOCUMENT_LIST)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public String documentList(CurrentUser user, ModelMap modelMap, 
			@RequestParam(value = "uuid", required = true) String uuid) {
		log.info("--->" + uuid);
		OrderDetailView orderInfo = orderService.getOrderInfo(uuid);
		
		DocumentQuery query = new DocumentQuery();
		query.setUuid(uuid);

		if(orderInfo != null) {
			query.setTradeCategory(orderInfo.getTradeCategory());
		}
		
		List<Map<String,Object>> contractFileList = orderDocumentService.getContractFileList(uuid);
		List<Map<String,Object>> documentFileList = orderDocumentService.queryDocumentFileList(query);
		List<Map<String,Object>> documentType = orderDocumentService.getDocumentType(query);

		if("1".equals(query.getTradeCategory())) {
			modelMap.put("maxDocumentType", "T10");
		} else {
			modelMap.put("maxDocumentType", "T20");
		}
		
		if(documentType != null) {
			for(int i = 0; i < documentType.size(); i++) {
				if(documentType.get(i).get("orderDocumentId") != null) {
					modelMap.put("maxDocumentType", documentType.get(i).get("documentType"));
				}
			}
		}
		
		modelMap.put("documentFileList", documentFileList);
		modelMap.put("contractFileList", contractFileList);
		modelMap.put("documentType", documentType);
		modelMap.put("query", query);
		
		if("1".equals(query.getTradeCategory())) {
			return "buyerCenter/orderDocument/documentImportList";
		} else {
			return "buyerCenter/orderDocument/documentExportList";
		}
	}
	
	/***
	 * 新增或者更新单证信息
	 * 
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.DOCUMENT_SAVE, method = RequestMethod.POST)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public Result saveDocument(@RequestBody OrderDocumentVO vo, CurrentUser user) {
		log.info("--->" + vo.toString());
		Result res = new Result();
		try {
			orderDocumentService.saveDocument(vo, user);
			
			// 有附件时需要发送通知
			if(vo.getFileInfoList() != null && vo.getFileInfoList().size() > 0) {
				orderMessagePushService.messagePush(2, vo.getUuid(), user.getMemberId(), vo.getDocumentType(), null);
			}
		} catch (OrderExecException e) {
			log.error("saveDocument error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("saveDocument error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
}
