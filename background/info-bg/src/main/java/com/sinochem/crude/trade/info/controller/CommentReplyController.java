package com.sinochem.crude.trade.info.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.StringUtils;
import com.sinochem.crude.trade.info.model.CommentReplyVO;
import com.sinochem.crude.trade.info.model.CommentVO;
import com.sinochem.crude.trade.info.service.CommentReplyService;
import com.sinochem.crude.trade.member.user.CurrentUser;


@Controller
public class CommentReplyController  {

	@Autowired
	private CommentReplyService commentReplyService;
	
	public static final Log log = LogFactory.getLog(CommentReplyController.class);
	
	/**
	 * 新增回复
	 * */
	@ResponseBody
	@RequestMapping(value="/info/comment/reply.json",method=RequestMethod.POST)
	public Result reply(@RequestBody CommentReplyVO vo,CurrentUser user){
		Result res = new Result();
		if(vo.getCommentId()==null){
			throw new BizException("评论ID为空，无法新增");
		}
		if(StringUtils.isEmpty(vo.getReplyContent())){
			throw new BizException("回复内容为空，无法新增");
		}
		if(StringUtils.isEmpty(vo.getInfoUuid())){
			throw new BizException("资讯id为空，无法新增");
		}
		if(user!=null){
			try {
				int count=commentReplyService.saveReplyAndUpdateReplayCount(vo,user);
				res.setMessage("回复成功");
				res.setCode("");
				res.setStatus(Result.SUCCESS);
				return res;
			} catch (Exception e) {
				log.error(e);
				res.setStatus(Result.EEROR);
				res.setMessage("回复失败");
				return res;
			}
		}else{
			res.setStatus(Result.EEROR);
			res.setCode("Invalid Access");
			res.setMessage("请先登录");
			return res;
		}
	}
	/**
	 * 删除回复
	 * */
	@ResponseBody
	@RequestMapping(value="/comment/reply/del.json",method = RequestMethod.POST)
	public Result deleteReply(@RequestBody CommentReplyVO vo,CurrentUser user){
		Result res=new Result();
		if(StringUtils.isEmpty(vo.getUuid()) ){
			throw new BizException("UUID为空，无法删除");
		}
		try {
			if(commentReplyService.delReplyAndUpdateReplayCount(vo,user) == 2){
				res.setMessage("删除回复成功");
				res.setCode("");
				res.setStatus(Result.SUCCESS);
				return res;
			}else{
				log.error(vo.getUuid() +"--对应回复不存在 ");
				res.setStatus(Result.EEROR);
				res.setMessage("删除回复失败");
				return res;
			}
		} catch (Exception e) {
			log.error("删除回复失败", e);
			res.setStatus(Result.EEROR);
			res.setMessage(e.getMessage());
			return res;
		}
	}
	
}
