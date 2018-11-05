package com.sinochem.crude.trade.info.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.info.domain.Comment;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.model.CollectionInfoVO;
import com.sinochem.crude.trade.info.model.CommentInfoVO;
import com.sinochem.crude.trade.info.model.CommentVO;
import com.sinochem.crude.trade.info.query.CommentInfoQuery;
import com.sinochem.crude.trade.info.query.CommentQuery;
import com.sinochem.crude.trade.info.service.CommentReplyService;
import com.sinochem.crude.trade.info.service.CommentService;
import com.sinochem.crude.trade.info.service.InfoService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.member.access.WithoutAccess;


@Controller
public class CommentController  {

	@Autowired
	private CommentService commentService;
	@Autowired
	private CommentReplyService commentReplyService;
	@Autowired
	private InfoService infoService;
	private
	
	Log log = LogFactory.getLog(CommentController.class);

	/**
	 * 新增评论
	 */
	@ResponseBody
	@RequestMapping(value=URLMapping.INFO_COMMENT_ADD, method = RequestMethod.POST)
	public Result addComment(@RequestBody CommentVO vo,CurrentUser user){
		Result result = new Result();
		result.setMessage("评论成功");

		if(vo.getInformationId() == null){
			throw new BizException("资讯ID为空，无法新增");
		}
		if(StringUtils.isBlank(vo.getCommentContent())){
			throw new BizException("评论内容为空");
		}
		if(user!=null){
			if(commentService.commentInfoAndUpdateInfoCount(vo,user) == 2){
				result.setStatus(Result.SUCCESS);
				return result;
			}
		}else{
			result.setStatus(Result.EEROR);
			result.setCode("Invalid Access");
			result.setMessage("请先登录");
			return result;
		}
		
		result.setFail("评论失败");
		return result;
	}
	/**
	 * 获取资讯评论列表
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.INFO_COMMENT_LIST, method = RequestMethod.POST)
	public ResultDatas<List<Map<String, Object>>> commentList(
			@RequestBody CommentQuery commentQuery
			,CurrentUser user){
		if(StringUtils.isEmpty(commentQuery.getInfoUuid())){
			throw new BizException("资讯UUID为空");
		}
		ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
		try {
			Comment comment=new Comment();
			SimplePageInfo pageInfo = new SimplePageInfo();
			pageInfo.setPageNum(commentQuery.getPageNum());
			pageInfo.setPageSize(commentQuery.getPageSize());
			Info info = infoService.findByUuid(commentQuery.getInfoUuid());
			if(info !=null){
				comment.setInformationId(info.getId());
				Page<Map<String, Object>> pageList =commentService.queryCommentList(BeanConvertUtils.beanToMap(comment),pageInfo);
				for (int i = 0; i < pageList.getResult().size(); i++) {
					Long id=(Long) pageList.getResult().get(i).get("commentId");
					Map<String,Object> map=new HashMap<>();
					map.put("commentId", id);
					List<Map<String, Object>> replyList = commentReplyService.queryByCommentId(map);
					pageList.getResult().get(i).put("replys", replyList);
				}
				res.setDatas(pageList);
				res.setStatus(Result.SUCCESS);
				res.setMessage("成功");
				res.setPageNum(pageList.getPageNum());
				res.setPageSize(pageList.getPageSize());
				res.setPageCount(pageList.getPages());
				res.setTotal(pageList.getTotal());
				return res;
			}else{
				log.error(commentQuery.getInfoUuid()+"--对应资讯不存在 ");
				res.setStatus(Result.EEROR);
				res.setMessage("查询失败");
				return res;
			}
		} catch (Exception e) {
			log.error("获取资讯评论列表失败", e);
			res.setStatus(Result.EEROR);
			res.setMessage(e.getMessage());
			return res;
		}
	}
	/**
	 * 删除评论
	 * */
	@ResponseBody
	@RequestMapping(value=URLMapping.INFO_COMMENT_DEL,method = RequestMethod.POST)
	public Result deleteComment(@RequestBody CommentVO vo,CurrentUser user){
		Result res=new Result();
		if(StringUtils.isEmpty(vo.getUuid())){
			throw new BizException("UUID为空，无法删除");
		}
		try {
			if(commentService.delCommentInfoAndUpdateInfoCount(vo,user) == 2){
				res.setMessage("成功");
				res.setCode("");
				res.setStatus(Result.SUCCESS);
				return res;
			}else{
				log.error(vo.getUuid() +"--对应评论不存在 ");
				res.setStatus(Result.EEROR);
				res.setMessage("删除评论失败");
				return res;
			}
		} catch (Exception e) {
			log.error("删除评论失败", e);
			res.setStatus(Result.EEROR);
			res.setMessage(e.getMessage());
			return res;
		}
	}
	
	
	/**
	 * 我的评论列表
	 * @param query
	 * @param user
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value = URLMapping.COMMENT_ARTICLE_LIST, method = RequestMethod.POST)
	public ResultDatas<List<CommentInfoVO>> myCommentInfo(@RequestBody CommentInfoQuery query, CurrentUser user){
		ResultDatas<List<CommentInfoVO>> result = new ResultDatas<>();
		try {
			
			SimplePageInfo pageInfo = new SimplePageInfo();
			if(query.getPageNum() == null){
				pageInfo.setPageNum(1);
			}else{
				pageInfo.setPageNum(query.getPageNum());
			}
			if(query.getPageSize() == null){
				pageInfo.setPageSize(10);
			}else{
				pageInfo.setPageSize(query.getPageSize());
			}
			
			CommentInfoVO commentInfoVO = new CommentInfoVO();
			if(StringUtils.isBlank(query.getMemberId())){
				if(user != null){
					commentInfoVO.setCommentUserId(user.getMemberId());
				}else{
					result.setFail("会员id为必填项！");
					return result;
				}
			}else{
				commentInfoVO.setCommentUserId(Long.valueOf(query.getMemberId()));
			}
			
			Page<Map<String, Object>> collList = commentService.queryMyCommentInfo(BeanConvertUtils.beanToMap(commentInfoVO), pageInfo);
			
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
			result.setPageNum(collList.getPageNum());
			result.setPageSize(collList.getPageSize());
			result.setPageCount(collList.getPages());
			result.setTotal(collList.getTotal());
			result.setDatas(BeanConvertUtils.mapToBeanInList(collList, CommentInfoVO.class));
		} catch (Exception e) {
			log.error(" ", e);
			result.setStatus(Result.EEROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
}
