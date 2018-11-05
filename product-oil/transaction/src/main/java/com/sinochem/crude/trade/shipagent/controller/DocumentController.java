package com.sinochem.crude.trade.shipagent.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.sinochem.crude.trade.blockchain.domain.TShipagentDocument;
import com.sinochem.crude.trade.shipagent.model.query.TShipagentDocumentQuery;
import com.sinochem.crude.trade.shipagent.service.DocumentService;
import com.sinochem.crude.trade.shipagent.utils.Result;
import com.sinochem.crude.trade.shipagent.utils.ResultData;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author admin1
 * @date 2018/9/18
 */
@Controller
@RequestMapping("/blockchain/shipagent/documentation")
@WithoutAccess
public class DocumentController {

	private static final Logger LOG = LoggerFactory.getLogger(DocumentController.class);

	@Autowired
	private DocumentService documentService;

	@RequestMapping("/list.json")
	@ResponseBody
	public ResultData list(@RequestBody TShipagentDocumentQuery documentQuery){
		LOG.info("SHIPAGENT[DocumentController->list]收到参数："+ JSONObject.toJSONString(documentQuery));
		ResultData resultData = new ResultData();
		try {
			Page<TShipagentDocument> page = documentService.list(documentQuery);
			resultData.setData(page.getResult());
			resultData.setPageCount(page.getPages());
			resultData.setPageNum(page.getPageNum());
			resultData.setPageSize(page.getPageSize());
			resultData.setTotal(page.getTotal());
		}catch (Exception e){
			LOG.error(e.getMessage());
			resultData = new ResultData(Result.ERROR , e.getMessage());
		}
		LOG.info("SHIPAGENT[DocumentController->list]返回参数："+JSONObject.toJSONString(resultData));
		return resultData;
	}

	@RequestMapping("/get.json")
	@ResponseBody
	public ResultData get(@RequestBody TShipagentDocument document){
		LOG.info("SHIPAGENT[DocumentController->get]收到参数："+ JSONObject.toJSONString(document));
		ResultData resultData = new ResultData();
		try {
			document = documentService.get(document.getId());
			resultData.setData(document);
		}catch (Exception e){
			LOG.error(e.getMessage());
			resultData = new ResultData(Result.ERROR , e.getMessage());
		}
		LOG.info("SHIPAGENT[DocumentController->get]返回参数："+JSONObject.toJSONString(resultData));
		return resultData;
	}
}
