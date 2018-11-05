package com.sinochem.crude.trade.shipagent.controllers;

import com.sinochem.crude.trade.shipagent.common.ThreadContextHolder;
import com.sinochem.crude.trade.shipagent.domain.ResultData;
import com.sinochem.crude.trade.shipagent.domain.TCommonAttachments;
import com.sinochem.crude.trade.shipagent.domain.TShipagentDocument;
import com.sinochem.crude.trade.shipagent.domain.vo.BillCoreUploadVo;
import com.sinochem.crude.trade.shipagent.domain.vo.FileInfoVO;
import com.sinochem.crude.trade.shipagent.feign.HttpFeignClient;
import com.sinochem.crude.trade.shipagent.utils.Constant;
import com.sinochem.crude.trade.shipagent.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 *
 * @author admin1
 * @date 2018/9/28
 */
@RequestMapping("/file")
@Controller
public class FileUploadController {

	@Autowired
	private HttpFeignClient httpFeignClient;

	@PostMapping("/upload.json")
	@ResponseBody
	public ResultData upload(@RequestBody FileInfoVO fileInfoVO){
		ResultData result = null;
		try{
			fileInfoVO.setCreateUser(ThreadContextHolder.getCurrentUser().getMemberId());
			final String type = fileInfoVO.getType();
			switch (type){
				case FileInfoVO.FILE_TYPE_BL:{
					result = httpFeignClient.blUpload(fileInfoVO);
					break;
				}
				case FileInfoVO.FILE_TYPE_SOF:{
					result = httpFeignClient.sofUpload(fileInfoVO);
					break;
				}
				default:{
					result = new ResultData(Result.EEROR , "type不能为空");
					break;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			result = new ResultData(Result.EEROR , Constant.SERVER_BUSY);
		}
		return result;
	}

	@PostMapping("/delete.json")
	@ResponseBody
	public ResultData delete(@RequestBody TCommonAttachments attachments){
		ResultData result = null;
		final String type = attachments.getFileType();
		attachments.setFileType(null);
		switch (type){
			case FileInfoVO.FILE_TYPE_BL:{
				result = httpFeignClient.blFileDelete(attachments);
				break;
			}
			case FileInfoVO.FILE_TYPE_SOF:{
				result = httpFeignClient.sofFileDelete(attachments);
				break;
			}
			default: {
				result = new ResultData(Result.EEROR , "type不能为空");
			}
		}
		return result;
	}

	@PostMapping("/list.json")
	@ResponseBody
	public ResultData list(@RequestBody TShipagentDocument document){
		ResultData result = null;
		if(document.getSofId() == null && document.getBlId()==null){
			result.setStatus(Result.EEROR);
			result.setMessage("附件查询条件不能为空");
		}

		try{

			// 接口调用
			result = httpFeignClient.fileList(document);
		}catch (Exception e){
			e.printStackTrace();
			result = new ResultData(Result.EEROR , Constant.SERVER_BUSY);
		}
		return result;
	}
}
