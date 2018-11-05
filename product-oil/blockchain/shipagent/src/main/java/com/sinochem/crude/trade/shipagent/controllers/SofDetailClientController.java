package com.sinochem.crude.trade.shipagent.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinochem.crude.trade.shipagent.domain.ResultData;
import com.sinochem.crude.trade.shipagent.feign.HttpFeignClient;
import com.sinochem.crude.trade.shipagent.utils.Constant;
import com.sinochem.crude.trade.shipagent.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author admin1
 * @date 2018/9/12
 */
@RequestMapping("/sof/detail")
@Controller
public class SofDetailClientController {

	@Autowired
	HttpFeignClient httpFeignClient;

	/**
	 * 删除SOF详情
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete.json" , produces = "application/json")
	public ResultData update(@RequestBody Long id){
		ResultData result = null;
		try {

			// 接口掉用
			result = httpFeignClient.sofDetailDelete(id);
		}catch (Exception e){
			e.printStackTrace();
			result = new ResultData(Result.EEROR , Constant.SERVER_BUSY);
		}
		return result;
	}
}
