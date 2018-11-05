package com.sinochem.crude.trade.info.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.info.model.FabulousVO;
import com.sinochem.crude.trade.info.service.FabulousService;
import com.sinochem.crude.trade.member.user.CurrentUser;


@Controller
public class FabulousController  {

	@Autowired
	private FabulousService fabulousService;
	
	public static final Log log = LogFactory.getLog(FabulousController.class);
	
	/**
	 * 踩资讯、评论
	 */
	@ResponseBody
	@RequestMapping(value = URLMapping.THREADINFO_UPDATE, method = RequestMethod.POST)
	public Result tread(@RequestBody FabulousVO vo,CurrentUser user){
		Result result = new Result();
		if(StringUtils.isBlank(vo.getObjId().toString())){
			result.setFail("资讯或频道id不能为空");
		}
		if(StringUtils.isBlank(vo.getObjTpye())){
			result.setFail("资讯或评论类型不能为空");
		}
		if(user != null){
			fabulousService.treadInfo(vo,user);
		}else{
			result.setStatus(result.EEROR);
			result.setMessage("未登录，请先登录");
		}
		return result;
	}
	
	
	/**
	 * 点赞资讯、评论
	 */
	@ResponseBody
	@RequestMapping(value = URLMapping.PRAISEINFO_UPDATE)
	public Result praise(@RequestBody FabulousVO vo,CurrentUser user){
		Result result = new Result();
		if(StringUtils.isBlank(vo.getObjId().toString())){
			result.setFail("资讯或频道id不能为空");
		}
		if(StringUtils.isBlank(vo.getObjTpye())){
			result.setFail("资讯或评论类型不能为空");
		}
		if(user != null){
			fabulousService.praiseInfo(vo,user);
		}else{
			result.setStatus(result.EEROR);
			result.setMessage("未登录，请先登录");
		}
		return result;
	}
}
