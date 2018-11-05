package com.sinochem.crude.trade.shipagent.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netflix.client.http.HttpRequest;
import com.sinochem.crude.trade.shipagent.common.ThreadContextHolder;
import com.sinochem.crude.trade.shipagent.domain.ResultData;
import com.sinochem.crude.trade.shipagent.domain.TShipagentSof;
import com.sinochem.crude.trade.shipagent.domain.TShipagentUser;
import com.sinochem.crude.trade.shipagent.domain.vo.TShipagentAppointQueryVO;
import com.sinochem.crude.trade.shipagent.feign.HttpFeignClient;
import com.sinochem.crude.trade.shipagent.utils.Constant;
import com.sinochem.crude.trade.shipagent.utils.Result;
import com.sinochem.crude.trade.shipagent.utils.ResultDatas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 船代任务控制器
 * @author songhaiqiang
 *
 */
@Controller
@RequestMapping(value = "/task")
public class TaskClientController {

	Logger logger = LoggerFactory.getLogger(TaskClientController.class);

	@Autowired
	private HttpFeignClient httpFeignClient;

	Gson GSON = new GsonBuilder().serializeNulls().create();

	/**
	 * 船代任务列表
	 *
	 * @return
	 */
	@RequestMapping(value = "/list.json" , method = RequestMethod.POST)
	@ResponseBody
	public ResultData list(@RequestBody TShipagentAppointQueryVO shipagentAppointQueryVO){
		ResultData result = null;
		try {
			shipagentAppointQueryVO.setEnterpriseId(ThreadContextHolder.getCurrentUser().getEpMemberId());
			System.out.println("============输入参数=============="+GSON.toJson(shipagentAppointQueryVO));
			// 接口掉用
			result =  httpFeignClient.taskList(shipagentAppointQueryVO);
			System.out.println("============返回参数=============="+GSON.toJson(result));
		}catch (Exception e){
			e.printStackTrace();
			result = new ResultData(Result.EEROR , Constant.SERVER_BUSY);
		}
		return result;
	}

	/**
	 * 船代任务详情
	 * @return
	 */
	@RequestMapping(value = "/get.json" , method = RequestMethod.POST)
	@ResponseBody
	public ResultData get(@RequestBody Map<String , String> dealMap){
		ResultData result = new ResultData();
		try {

			// 接口掉用
			ResultDatas resultDatas = httpFeignClient.taskGet(dealMap.get("dealUuid"));
			result.setStatus(resultDatas.getStatus());
			result.setMessage(resultDatas.getMessage());
			result.setData(resultDatas.getDatas());
		}catch (Exception e){
			e.printStackTrace();
			result = new ResultData(Result.EEROR , Constant.SERVER_BUSY);
		}
		return result;
	}


	@RequestMapping(value = "/getSimple.json" , method = RequestMethod.POST)
	@ResponseBody
	public ResultData getSimple(@RequestBody TShipagentSof sof){
		ResultData result = null;
		try {

			// 接口掉用
			result =   httpFeignClient.taskGetSimple(sof.getTaskId());
		}catch (Exception e){
			e.printStackTrace();
			result = new ResultData(Result.EEROR , Constant.SERVER_BUSY);
		}
		return result;
	}
}
