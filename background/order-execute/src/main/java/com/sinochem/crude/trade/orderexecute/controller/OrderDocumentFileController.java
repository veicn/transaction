package com.sinochem.crude.trade.orderexecute.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.github.pagehelper.Page;
import com.sinochem.crude.trade.UrlMapping;
import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.commons.constants.Constants;
import com.sinochem.crude.trade.orderexecute.domain.OrderDocumentFile;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.query.DocumentFileQuery;
import com.sinochem.crude.trade.orderexecute.service.OrderDocumentFileService;
import com.sinochem.it.b2b.member.access.ApiSafeAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
//@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
public class OrderDocumentFileController  {

	@Autowired
	private OrderDocumentFileService orderDocumentFileService;
	@Value("${aliyun.oss.bucket}")
	private String bucket;
	@Autowired
	private OSSClient ossClient;
	
	Log log = LogFactory.getLog(OrderDocumentFileController.class);
	
	/**
	 * 查询单证附件列表
	 * @param vo
	 * @return
	 * @exception
	 */
	@RequestMapping(value = UrlMapping.DOCUMENT_FILE_LIST)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public void getPageList(CurrentUser user, ModelMap modelMap, DocumentFileQuery query) {

		queryDocumentFileList(user,modelMap,query);
	}
	
	/**
	 * 查询单证附件json
	 * @param vo
	 * @return
	 * @exception
	 */
	@ResponseBody
	@RequestMapping(value = UrlMapping.DOCUMENT_FILE_JSON)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	@ApiSafeAccess
	public ResultDatas<List<Map<String, Object>>> getPageListJSON(CurrentUser user, ModelMap modelMap, DocumentFileQuery query) {

		ResultDatas<List<Map<String, Object>>> resuleDatas = new ResultDatas<>();
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> documentFiletList = (List<Map<String, Object>>) queryDocumentFileList(user,modelMap,query).get("dataList");
		
		// 设置OSS URL过期时间为1小时
		Date expiration = new Date(new Date().getTime() + 3600 * 1000);
		for (Map<String, Object> orderDocumentFile : documentFiletList) {
			String filePath = String.valueOf(orderDocumentFile.get("filePath"));
			if(filePath != null){
				//注：由于文件存储方式更改，之前保存的是绝对路径，现在保存相对路径。
				//为了兼容以前的数据，对相对路径进行转换成绝对路径
				if(!filePath.startsWith("http")) {
					String absUrl = ossClient.generatePresignedUrl(bucket, filePath,expiration,HttpMethod.GET).toString();
					orderDocumentFile.put("filePath",absUrl);
				}
			}
		}
		resuleDatas.setDatas(documentFiletList);
		return resuleDatas; 
	}
	
	/**
	 * 合同列表公共方法
	 * @param user
	 * @param modelMap
	 * @param query
	 * @return
	 */
	private ModelMap queryDocumentFileList(CurrentUser user, ModelMap modelMap, DocumentFileQuery query){
		// 查询条件中，加入用户的企业id
		query.setEpMemberId(String.valueOf(user.getEpMemberId()));
		
		Page<Map<String, Object>> pageResult = orderDocumentFileService.queryDocumentFile(query.getQueryParam(), query.getPageInfo());
		
		QueryBase queryBase = (QueryBase) query;
		queryBase.setTotalItem(pageResult.getTotal());
		
		modelMap.put("query", queryBase);
		modelMap.put("param", query.getQueryParam());
		modelMap.put("dataList", pageResult.getResult());
		
		// 取得表单类型下拉列表数据
		modelMap.put("documentType", orderDocumentFileService.getDocumentType());
		
		return modelMap;
		
	}
	
	/**
	 * 逻辑删除
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.DOCUMENT_FILE_DELETE)
	@RolesAccess({"admin", "trade_oper", "trade_executor", "enter_master","sales_trader","buy_trader"})
	public Result delDocumentFile(OrderDocumentFile vo, CurrentUser user) throws OrderExecException{
		log.info("--->" + vo.toString());
		Result res = new Result();
		vo.setAliveFlag(Constants.ALIEVE_FLAG_NO);
		try {
			orderDocumentFileService.updateRecordById(vo);
		} catch (OrderExecException e) {
			log.error("delDocumentFile error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("delDocumentFile error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/***
	 * 删除附件
	 * 
	 * @param vo
	 * @param user
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping(value = UrlMapping.DOCUMENT_FILE_DELETE, method = RequestMethod.POST, produces = "application/json;text/plain;charset=UTF-8")
	public Result deleteFile(OrderDocumentFileVO vo, CurrentUser user) {
		log.info("--->" + vo.toString());
		Result res = new Result();
		
		try {
			OrderDocumentFile entity = new OrderDocumentFile();
			entity.setDocumentFileId(vo.getDocumentFileId());
			entity.setUpdateDate(DateTimeUtils.currentDate());
			entity.setUpdateUser(user.getMemberId());
			
			orderDocumentFileService.deleteById(entity);
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
	}*/
}
