package com.sinochem.crude.trade.shipagent.controllers;

import com.sinochem.crude.trade.shipagent.common.ThreadContextHolder;
import com.sinochem.crude.trade.shipagent.domain.Result;
import com.sinochem.crude.trade.shipagent.domain.ResultData;
import com.sinochem.crude.trade.shipagent.domain.TShipagentSofDetailTemplate;
import com.sinochem.crude.trade.shipagent.feign.HttpFeignClient;
import com.sinochem.crude.trade.shipagent.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author admin1
 * @date 2018/10/25
 */
@RequestMapping("/sof/detail/template")
@Controller
public class SofDetailTemplateController {


	@Autowired
	private HttpFeignClient httpFeignClient;

	@RequestMapping("/list.json")
	@ResponseBody
	public ResultData list(@RequestBody TShipagentSofDetailTemplate template){
		ResultData result = null;
		try {
			template.setEnterpriseId(ThreadContextHolder.getCurrentUser().getMemberId());
			result =  httpFeignClient.sofDetailTemplateList(template);

		}catch (Exception e){
			e.printStackTrace();
			result = new ResultData(Result.EEROR , Constant.SERVER_BUSY);
		}

		return result;
	}


}
