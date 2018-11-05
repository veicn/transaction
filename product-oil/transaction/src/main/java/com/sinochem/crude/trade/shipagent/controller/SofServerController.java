package com.sinochem.crude.trade.shipagent.controller;

import com.sinochem.crude.trade.blockchain.domain.*;
import com.sinochem.crude.trade.blockchain.service.TCommonAttachmentsService;
import com.sinochem.crude.trade.inspector.constant.Constant;
import com.sinochem.crude.trade.shipagent.constant.Constants;
import com.sinochem.crude.trade.shipagent.model.vo.TShipagentSofVo;
import com.sinochem.crude.trade.shipagent.service.AppointTaskService;
import com.sinochem.crude.trade.shipagent.service.BillLoadingService;
import com.sinochem.crude.trade.shipagent.service.DocumentService;
import com.sinochem.crude.trade.shipagent.service.SofService;
import com.sinochem.crude.trade.shipagent.utils.ResultData;
import com.sinochem.crude.trade.transaction.model.vo.FileInfoVO;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author songhaiqiang
 * @date 2018/9/6
 */
@Controller
@RequestMapping("/blockchain/shipagent/sof")
@WithoutAccess
public class SofServerController {

	@Autowired
	private SofService sofService;

	@Autowired
	private BillLoadingService billLoadingService;

	@Autowired
	private TCommonAttachmentsService tCommonAttachmentsService;

	@Value("${aliyun.oss.show.endpoint}")
	private String prefixUrl;

	/**
	 * 查询SOF详情，包含 SOF ， SOF_DETAIL两部分信息
	 * 传输采用JSON格式字符串
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get.json" , method = RequestMethod.POST)
	@ResponseBody
	public ResultData getSofAndDetail(@RequestBody Long id){
		ResultData resultData = new ResultData();
		try{
			TShipagentSofVo sof = sofService.getSofAndDetail(id);
			resultData.setData(sof);
		}catch (BizException e){
			e.printStackTrace();
			resultData = new ResultData(Result.ERROR , "查询异常");
		}
		return resultData;
	}


	/**
	 * 暂存SOF
	 * @return
	 */
	@RequestMapping(value = "/save.json" , method = {RequestMethod.POST , RequestMethod.GET})
	@ResponseBody
	public ResultData save(@RequestBody TShipagentSof sof){
		ResultData resultData = null;
		try {
			if(sof == null){
				resultData = new ResultData(com.sinochem.crude.trade.shipagent.utils.Result.ERROR , "服务端创建失败");
				return resultData;
			}
			sof.setAliveFlag(Constants.ALIVE_FLAG_YES);
			resultData = sofService.save(sof);
		}catch (Exception e){
			e.printStackTrace();
			resultData = new ResultData(Result.ERROR , "暂存失败");
		}
		return resultData;
	}


	/**
	 * 提交SOF
	 * @param sof
	 * @return
	 */
	@RequestMapping(value = "/commit.json" , method = {RequestMethod.POST , RequestMethod.GET})
	@ResponseBody
	public ResultData commit(@RequestBody TShipagentSof sof){
		ResultData resultData = new ResultData();
		try{
			//1、保存当前信息
			if(sof == null){
				new ResultData( com.sinochem.crude.trade.shipagent.utils.Result.ERROR , "服务端创建失败");
				return resultData;
			}
			resultData = sofService.commit(sof);
		}catch (Exception e){
			e.printStackTrace();
			resultData = new ResultData(Result.ERROR , "提交失败");
		}
		return resultData;
	}


	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete.json" , method = {RequestMethod.POST , RequestMethod.GET})
	@ResponseBody
	public ResultData delete(@RequestBody Long id){
		ResultData resultData = null;
		try{

			resultData = sofService.delete(id);
		}catch (Exception e){
			e.printStackTrace();
			resultData = new ResultData(Result.ERROR ,"删除失败");
		}
		return resultData;
	}


	/**
	 * SOF附件上传
	 *  附件上传的同时需要进行上链操作。这里统一在Controller里面封装数据
	 *
	 * @param fileInfoVO
	 * @return
	 */
	@RequestMapping(value = "/upload.json" , method = {RequestMethod.POST , RequestMethod.GET})
	@ResponseBody
	public ResultData upload(@RequestBody FileInfoVO fileInfoVO){
		ResultData resultData =  null;
		try {

			resultData = sofService.uploadFile(fileInfoVO);
		}catch (Exception e){
			e.printStackTrace();
			resultData = new ResultData(Result.ERROR , "上传失败");
		}
		return resultData;
	}

	@RequestMapping(value = "/file/delete.json" , method = {RequestMethod.POST , RequestMethod.GET})
	@ResponseBody
	public ResultData fileDelete(@RequestBody TCommonAttachments attachments){
		ResultData resultData =  null;
		try{

			resultData = tCommonAttachmentsService.deleteSofOrBl(attachments);
		}catch (Exception e){
			e.printStackTrace();
			resultData = new ResultData(Result.ERROR , "删除失败");
		}

		return resultData;
	}

	@RequestMapping(value = "/file/list.json" , method = {RequestMethod.POST , RequestMethod.GET})
	@ResponseBody
	public ResultData fileList(@RequestBody TShipagentDocument document){
		ResultData resultData =  new ResultData();
		List<TCommonAttachments> resultList = new ArrayList<>();
		try{

			TShipagentSof sof = null;
			if(document != null  && document.getSofId() != null){
				sof = sofService.get(document.getSofId());
			}
			if(sof != null && StringUtils.isNotBlank(sof.getUuid())){
				List<TCommonAttachments> attachmentsList = selectByUuid(sof.getUuid());
				//url附加前缀
				attachUrlPrefix(attachmentsList , resultList);
			}

			TShipagentBillLoading billLoading = null;
			if(document != null && document.getBlId() != null){
				billLoading = billLoadingService.get(document.getBlId());
			}
			if(billLoading != null && StringUtils.isNotBlank(billLoading.getUuid())){
				List<TCommonAttachments> attachmentsList = selectByUuid(billLoading.getUuid());
				attachUrlPrefix(attachmentsList , resultList);
			}
			resultData.setData(resultList);
		}catch (Exception e){
			e.printStackTrace();
			resultData = new ResultData(Result.ERROR , "查询异常");
		}
		return resultData;
	}

	/**
	 * url附加前缀
	 * @param attachmentsList
	 * @param resultList
	 */
	private void attachUrlPrefix(List<TCommonAttachments> attachmentsList ,List<TCommonAttachments> resultList){
		if(attachmentsList != null && attachmentsList.size() > 0){
			for(TCommonAttachments atta : attachmentsList){
				String path = atta.getPath();
				atta.setPath(prefixUrl +"/"+ path);
			}
			resultList.addAll(attachmentsList);
		}
	}

	private List<TCommonAttachments> selectByUuid(String uuid){
		TCommonAttachments attachmentsQuery = new TCommonAttachments();
		attachmentsQuery.setAliveFlag(Constant.SAVE_FLAG);
		//sof附件列表
		attachmentsQuery.setBusinessUuid(uuid);
		return tCommonAttachmentsService.selectByParameter(attachmentsQuery);
	}


}
