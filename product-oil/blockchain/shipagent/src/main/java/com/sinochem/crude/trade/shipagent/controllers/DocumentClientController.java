package com.sinochem.crude.trade.shipagent.controllers;

import com.sinochem.crude.trade.shipagent.common.ThreadContextHolder;
import com.sinochem.crude.trade.shipagent.domain.Result;
import com.sinochem.crude.trade.shipagent.domain.ResultData;
import com.sinochem.crude.trade.shipagent.domain.TShipagentDocument;
import com.sinochem.crude.trade.shipagent.domain.vo.TShipagentDocumentQuery;
import com.sinochem.crude.trade.shipagent.feign.HttpFeignClient;
import com.sinochem.crude.trade.shipagent.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin1
 * @date 2018/9/19
 */
@RequestMapping("/documentation")
@RestController
public class DocumentClientController {

	@Autowired
	private HttpFeignClient httpFeignClient;

	/**
	 * 单证列表
	 * @param documentQuery
	 * @return
	 */
	@RequestMapping("/list.json")
	public ResultData list(@RequestBody TShipagentDocumentQuery documentQuery){
		ResultData result = null;
		try {
			documentQuery.setEnterpriseId(ThreadContextHolder.getCurrentUser().getEpMemberId());

			// 接口调用
			result = httpFeignClient.documentList(documentQuery);
		}catch (Exception e){
			e.printStackTrace();
			result = new ResultData(Result.EEROR , Constant.SERVER_BUSY);
		}
		return result;
	}

	/**
	 * 单证详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/get.json")
	public Result get(@RequestBody Long id){
		ResultData result = null;
		try {

			// 接口调用
			result = httpFeignClient.documentGet(id);
		}catch (Exception e){
			e.printStackTrace();
			result = new ResultData(Result.EEROR , Constant.SERVER_BUSY);
		}
		return result;
	}
}
