package com.sinochem.crude.trade.shipagent.controller;


import com.alibaba.fastjson.JSON;
import com.sinochem.crude.trade.blockchain.domain.TShipagentSofDetail;
import com.sinochem.crude.trade.shipagent.service.SofDetailService;
import com.sinochem.crude.trade.shipagent.utils.Result;
import com.sinochem.crude.trade.shipagent.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author songhaiqiang
 */
@Controller
@RequestMapping("/blockchain/shipagent/sof/detail")
public class SofDetailController {

	@Autowired
	private SofDetailService sofDetailService;

	@RequestMapping(value = "/create.json")
	public String create(@RequestBody List<TShipagentSofDetail> list){
		ResultData<Integer> resultData = new ResultData<>();
		try{
			if(list == null || list.size() < 1){
				resultData = new ResultData(Result.ERROR , "插入数据不能为空");
				return JSON.toJSONString(resultData);
			}

			resultData = sofDetailService.batchInsert(list);
		}catch (Exception e){
			e.printStackTrace();
			resultData = new ResultData(Result.ERROR , e.getMessage());
		}
		return JSON.toJSONString(resultData);
	}

	/**
	 * 根据ID修改
	 * @param detail
	 * @return
	 */
	@RequestMapping(value = "/update.json")
	public String update(@RequestBody TShipagentSofDetail detail){
		ResultData<Integer> resultData = new ResultData<>();
		try{
			resultData = sofDetailService.updateById(detail);
		}catch (Exception e){
			e.printStackTrace();
			resultData = new ResultData(Result.ERROR , e.getMessage());
		}
		return JSON.toJSONString(resultData);
	}
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete.json")
	public String delete(@RequestBody Long id){
		ResultData<Integer> resultData = new ResultData<>();
		try{
			resultData = sofDetailService.deleteById(id);
		}catch (Exception e){
			e.printStackTrace();
			resultData = new ResultData(Result.ERROR , e.getMessage());
		}
		return JSON.toJSONString(resultData);
	}

}
