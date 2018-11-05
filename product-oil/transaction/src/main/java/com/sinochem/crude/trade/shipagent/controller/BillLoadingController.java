package com.sinochem.crude.trade.shipagent.controller;

import com.alibaba.fastjson.JSONObject;
import com.sinochem.crude.trade.blockchain.domain.*;
import com.sinochem.crude.trade.blockchain.enums.BlockchainEventEnums;
import com.sinochem.crude.trade.blockchain.model.BlockChainFile;
import com.sinochem.crude.trade.blockchain.service.TCommonAttachmentsService;
import com.sinochem.crude.trade.inspector.constant.Constant;
import com.sinochem.crude.trade.shipagent.constant.Constants;
import com.sinochem.crude.trade.shipagent.service.AppointTaskService;
import com.sinochem.crude.trade.shipagent.service.BillLoadingService;
import com.sinochem.crude.trade.shipagent.service.DocumentService;
import com.sinochem.crude.trade.shipagent.utils.KeyGenUtils;
import com.sinochem.crude.trade.shipagent.utils.ResultData;
import com.sinochem.crude.trade.transaction.model.vo.FileInfoVO;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.sinochem.crude.trade.shipagent.utils.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 提单
 *
 * @author songhaiqiang
 * @date 2018/9/7
 */
@Controller
@WithoutAccess
@RequestMapping("/blockchain/shipagent/bl")
public class BillLoadingController {

	@Autowired
	private BillLoadingService billLoadingService;

	@Autowired
	private TCommonAttachmentsService tCommonAttachmentsService;


	/**
	 * 获取提单详情
	 * @param billLoading
	 * @return
	 */
	@RequestMapping(value = "/get.json" , method = {RequestMethod.POST , RequestMethod.GET})
	@ResponseBody
	public ResultData get(@RequestBody TShipagentBillLoading billLoading){
		ResultData resultData = new ResultData();
		try{
			TShipagentBillLoading bl = billLoadingService.get(billLoading.getId());
			resultData.setData(bl);
		}catch (BizException e){
			e.printStackTrace();
			resultData = new ResultData(Result.ERROR , "查询异常");
		}
		return resultData;
	}

	/**
	 * 暂存提单（保存或更新）
	 * @return
	 */
	@RequestMapping(value = "/save.json" , method = {RequestMethod.POST , RequestMethod.GET})
	@ResponseBody
	public ResultData save(@RequestBody TShipagentBillLoading billLoading ){
		ResultData resultData = null;
		try {
			if(billLoading == null){
				resultData = new ResultData(Result.ERROR , "暂存失败") ;
				return resultData;
			}

			resultData = billLoadingService.save(billLoading);
		}catch (Exception e){
			e.printStackTrace();
			resultData = new ResultData(Result.ERROR , "暂存失败");
		}
		return resultData;
	}


	/**
	 * 提交提单（保存或更新，并提交上链）
	 * @param bl
	 * @return
	 */
	@RequestMapping(value = "/commit.json" , method = {RequestMethod.POST , RequestMethod.GET})
	@ResponseBody
	public ResultData commit(@RequestBody TShipagentBillLoading bl){
		ResultData resultData = null;
		try{

			resultData = billLoadingService.commit(bl);
		}catch (Exception e){
			e.printStackTrace();
			resultData = new ResultData(Result.ERROR , "提交失败") ;
		}
		return resultData;
	}

	@RequestMapping(value = "/upload.json" , method = {RequestMethod.POST , RequestMethod.GET})
	@ResponseBody
	public ResultData upload(@RequestBody FileInfoVO fileInfoVO){
		ResultData resultData =  null;
		try {

			resultData = billLoadingService.uploadFile(fileInfoVO);
		}catch (Exception e){
			e.printStackTrace();
			resultData = new ResultData(com.sinochem.it.b2b.common.result.Result.ERROR , "上传失败");
		}
		return resultData;
	}


	/**
	 * 删除附件正本
	 *
	 * 	这里需要上链
	 * @param attachments
	 * @return
	 */
	@RequestMapping(value = "/file/delete.json" , method = {RequestMethod.POST , RequestMethod.GET})
	@ResponseBody
	public ResultData fileDelete(@RequestBody TCommonAttachments attachments){
		ResultData resultData =  new ResultData();
		try{

			resultData = tCommonAttachmentsService.deleteSofOrBl(attachments);
		}catch (Exception e){
			e.printStackTrace();
			resultData = new ResultData(com.sinochem.it.b2b.common.result.Result.ERROR , "删除失败");
		}
		return resultData;
	}
}
