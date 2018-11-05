package com.sinochem.crude.trade.shipping.controller;

import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.constant.UrlMapping;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.exceptions.TransportException;
import com.sinochem.crude.trade.shipping.model.query.LoadingProgressQuery;
import com.sinochem.crude.trade.shipping.service.ConfirmationSheetService;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.domain.LoadingProgress;
import com.sinochem.crude.trade.shipping.model.vo.LoadingProgressVO;
import com.sinochem.crude.trade.shipping.service.LoadingProgressService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value = UrlMapping.LOAD_PORT_PROGRESS)
public class LoadingProgressController  {

	@Autowired
	private LoadingProgressService loadingProgressService;

	@Autowired
	private ConfirmationSheetService confirmationSheetService;
	public static final Log log = LogFactory.getLog(LoadingProgressController.class);
	
	/**
	 * 新增或修改
	 */
	@RequestMapping
	public void LoadingProgressSave(LoadingProgressVO vo, CurrentUser currentUser,String confuuid){

		if (null != vo) {
			LoadingProgress domain = vo.getDomain();
			try {
				Integer code = loadingProgressService.insertRecordOrUpdate(domain,currentUser,confuuid);
			} catch (BizException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 新增或修改  微信api
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="saveLoadingProgress.json", method = RequestMethod.POST)
	public Result saveLoadingProgress(@RequestBody LoadingProgressVO vo){
		Result result = new Result();
		if (null != vo) {
			ConfirmationSheet confirmationSheet = confirmationSheetService.findByUuid(vo.getConfirmUuid());
			if(confirmationSheet==null){
				result.setFail("保存数据不能为空");
			}
			LoadingProgress domain = vo.getDomain();
			domain.setShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
			Integer code = loadingProgressService.saveOrUpdateLoadingProgress(domain,null);
			result.setStatus(code);
		}
		else
		{
			result.setFail("保存数据不能为空");
		}
		return result;
	}

	/**
	 * 进度列表查询  微信API
	 * @param confirmUUid
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value = "queryLoadingProgerss.json")
	public ResultDatas<List<LoadingProgressVO>> queryLoadingProgerss(String confirmUUid) {
		ResultDatas<List<LoadingProgressVO>> res = new ResultDatas<>();
		LoadingProgressVO vo = new LoadingProgressVO();
		try {
			// uuid校验
			if (confirmUUid==null) {
				throw new TransportException(TransportException.TYPE.ITSH102);
			}
			ConfirmationSheet confirmationSheet = confirmationSheetService.findByUuid(confirmUUid);
			LoadingProgressQuery loadingProgressQuery = new LoadingProgressQuery();
			loadingProgressQuery.setShipConfirmationSheetId(confirmationSheet.getConfirmationSheetId());
			List<LoadingProgress> loadingProgressList = loadingProgressService.findByConfirmationSheetId(loadingProgressQuery);
			List<LoadingProgressVO> loadingProgressVOList = vo.toListVo(loadingProgressList);
			res.setDatas(loadingProgressVOList);
		} catch (BizException e) {
			log.error("selectPollet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("selectPollet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 逻辑删除装港进度  微信
	 * @param progressUuid
	 */
	@WithoutAccess
	@RequestMapping(value = "deleteLoadingProgerss.json")
	public Result deleteLoadingProgress(String progressUuid){
		Result result = new Result();
		LoadingProgressVO loadingProgressVO = new LoadingProgressVO();
		loadingProgressVO.setUuid(progressUuid);
		LoadingProgress domain = loadingProgressVO.getDomain();
		Integer deleteRecords = loadingProgressService.deleteRecords(domain);
		if(deleteRecords==0){
			result.setFail("删除失败");
		}
		return result;
	}

}
