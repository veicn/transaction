package com.sinochem.crude.trade.shipagent.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinochem.crude.trade.shipagent.common.ThreadContextHolder;
import com.sinochem.crude.trade.shipagent.domain.Result;
import com.sinochem.crude.trade.shipagent.domain.ResultData;
import com.sinochem.crude.trade.shipagent.domain.TShipagentBillLoading;
import com.sinochem.crude.trade.shipagent.domain.TShipagentSof;
import com.sinochem.crude.trade.shipagent.feign.HttpFeignClient;
import com.sinochem.crude.trade.shipagent.utils.Constant;
import com.sinochem.crude.trade.shipagent.utils.KeyGenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 *
 * @author songhaiqiang
 * @date 2018/9/12
 */
@RestController
@RequestMapping("/bl")
public class BlClientController {

	@Autowired
	private HttpFeignClient httpFeignClient;

	/**
	 * 提单暂存（保存）
	 * @param billLoading
	 * @return
	 */
	@PostMapping(value = "/save.json")
	public ResultData save(@RequestBody TShipagentBillLoading billLoading ) {
		ResultData result = null;
		try{
			// 确定新增还是修改
			preSaveOrCommit(billLoading);

			// 接口调用
			result = httpFeignClient.blSave(billLoading);
		}catch (Exception e){
			e.printStackTrace();
			result = new ResultData(Result.EEROR , Constant.SERVER_BUSY);
		}
		return result;
	}

	/**
	 * 提交提单（保存并上链）
	 * @param billLoading
	 * @return
	 */
	@PostMapping(value = "/commit.json")
	public ResultData commit(@RequestBody TShipagentBillLoading billLoading){
		ResultData result = null;
		try{
			// 确定新增还是修改
			preSaveOrCommit(billLoading);

			// 接口调用
			result = httpFeignClient.blCommit(billLoading);
		}catch (Exception e){
			e.printStackTrace();
			result = new ResultData(Result.EEROR , Constant.SERVER_BUSY);
		}
		return result;
	}

	/**
	 * 获取SOF详情
	 * @param billLoading
	 * @return
	 */
	@PostMapping(value = "/get.json")
	public ResultData get(@RequestBody TShipagentBillLoading  billLoading){
		ResultData result = null;
		try{
			billLoading.setEnterpriseId(ThreadContextHolder.getCurrentUser().getEpMemberId());

			// 接口调用
			result = httpFeignClient.blGet(billLoading);
		}catch (Exception e){
			e.printStackTrace();
			result = new ResultData(Result.EEROR , Constant.SERVER_BUSY);
		}
		return result;
	}

	/**
	 * 更新
	 * @param billLoading
	 * @return
	 */
	@PostMapping(value = "/update.json")
	public ResultData update(@RequestBody TShipagentBillLoading billLoading){
		ResultData result = null;
		try{
			billLoading.setEnterpriseId(ThreadContextHolder.getCurrentUser().getEpMemberId());

			// 接口调用
			result = httpFeignClient.blUpdate(billLoading);
		}catch (Exception e){
			e.printStackTrace();
			result = new ResultData(Result.EEROR , Constant.SERVER_BUSY);
		}
		return result;
	}

	private void preSaveOrCommit(TShipagentBillLoading billLoading)throws Exception{
		if(billLoading.getId() == null || billLoading.getId() < 1){
			billLoading.setUuid(KeyGenUtils.newUuid());
			billLoading.setCreateDate(new Date());
			billLoading.setCreateUser(ThreadContextHolder.getCurrentUser().getMemberId());
			billLoading.setEnterpriseId(ThreadContextHolder.getCurrentUser().getEpMemberId());
		}
		billLoading.setUpdateDate(new Date());
		billLoading.setUpdateUser(ThreadContextHolder.getCurrentUser().getMemberId());
	}
}
