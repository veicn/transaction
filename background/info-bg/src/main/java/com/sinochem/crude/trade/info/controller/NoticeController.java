package com.sinochem.crude.trade.info.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.info.model.NewSymbolPriceVO;
import com.sinochem.crude.trade.info.model.NoticeVO;
import com.sinochem.crude.trade.info.query.CommentQuery;
import com.sinochem.crude.trade.info.query.NoticeQuery;
import com.sinochem.crude.trade.info.service.InfoService;
import com.sinochem.crude.trade.info.service.NoticeService;
import com.sinochem.crude.trade.info.util.MapUtil;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;

/**
 * admin - 系统管理员
 * info_oper - 内容维护人员
 */
@Controller
public class NoticeController  {

	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private InfoService infoService;
	
	Log log = LogFactory.getLog(NoticeController.class);
	
	/**
	 * 公告列表
	 */
	@RightAccess(1001)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = "/om/notice/notice.htm")
	public void list(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain,
			ModelMap model, NoticeQuery query) throws BizException{
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		query.setStatus("30");
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(query.getPageSize());
		Map<String, Object> param = new HashMap<>();
		param.putAll(BeanConvertUtils.beanToMap(query));
		param.put("noticeFlag", "1");
		Page<Map<String, Object>> pageList = infoService.queryRecords(param, pageInfo);
		query.setTotalItem(pageList.getTotal());
		model.put("notices", pageList.getResult());
		model.put("query", query);
	}
	
	/**
	 * 历史公告列表
	 */
	@RightAccess(1006)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = "/om/notice/historyNotice.htm")
	public void historyList(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain,
			ModelMap model, NoticeQuery query) throws BizException{
		query.setStatus("00");
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(query.getPageSize());
		Map<String, Object> param = new HashMap<>();
		param.putAll(BeanConvertUtils.beanToMap(query));
		param.put("noticeFlag", "1");
		Page<Map<String, Object>> pageList = infoService.queryRecords(param, pageInfo);
		query.setTotalItem(pageList.getTotal());
		model.put("historyNotices", pageList.getResult());
		model.put("query", query);
	}
	
	/**
	 * 跳转到新增公告页面
	 */
	@RightAccess(1002)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value="/om/notice/addlist.htm")
	public void toAdd(){
		//仅仅跳转页面
	}
	
	/**
	 * 跳转到新增公告页面
	 */
	@RightAccess(1008)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value="/om/notice/edit.htm")
	public void toEdit(@RequestParam String uuid,ModelMap model){
		model.put("notice", noticeService.findNoticeDetailByUUId(uuid));
	}
	
	/**
	 * 新增公告
	 */
	@ResponseBody
	@RequestMapping(value="/notice/saveNotice.json",method=RequestMethod.POST)
	public Result saveNotice(@RequestBody NoticeVO vo,CurrentUser user) throws BizException{
		Result res = new Result();
		if(StringUtils.isBlank(vo.getTitle())){
			res.setFail("标题不为空");
			return res;
		}
		if(StringUtils.isBlank(vo.getContent())){
			res.setFail("内容不为空");
			return res;
		}
		return noticeService.saveOrUpdateNotice(vo,user);
	}
	
	/**
	 * 查询公告详情
	 */
	@RightAccess(1004)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value="/om/notice/noticeDetail.htm")
	public void findNoticeDetail(@RequestParam String uuid,ModelMap model) throws BizException{
		if(StringUtils.isBlank(uuid)){
			throw new BizException("uuid不为空");
		}
		model.put("notice", noticeService.findNoticeDetailByUUId(uuid));
	}
	
	/**
	 * 撤销公告
	 */
	@RightAccess(1005)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value="/notice/revokeNotice.json",method=RequestMethod.GET)
	public Result revokeNotice(@RequestParam String uuid,CurrentUser user) throws BizException{
		if(StringUtils.isBlank(uuid)){
			throw new BizException("uuid不为空");
		}
		return noticeService.revokeNotice(uuid,user);
	}
	
	/**
	 * 历史公告发布
	 */
	@RightAccess(1009)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value="/notice/pushNotice.json",method=RequestMethod.GET)
	public Result pushNotice(@RequestParam String uuid,CurrentUser user){
		if(StringUtils.isBlank(uuid)){
			throw new BizException("uuid不为空");
		}
		return noticeService.pushNotice(uuid,user);
	}
	
	/**
	 * 删除历史公告
	 */
	@RightAccess(1010)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value="/notice/deleteNotice.json",method=RequestMethod.GET)
	public Result deleteNotice(@RequestParam String uuid,CurrentUser user) throws BizException{
		if(StringUtils.isBlank(uuid)){
			throw new BizException("uuid不为空");
		}
		return noticeService.deleteNotice(uuid,user);
	}
	/**
	 * 外部查询公告列表接口
	 * */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value = "/notice/queryNotice.json",method=RequestMethod.POST)
	public ResultDatas<List<Map<String, Object>>> queryNotice(
			@RequestBody CommentQuery commentQuery
			, NoticeQuery query) throws BizException{
		ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
		query.setStatus("30");
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(commentQuery.getPageNum());
		pageInfo.setPageSize(commentQuery.getPageSize());
		Map<String, Object> param = new HashMap<>();
		param.putAll(BeanConvertUtils.beanToMap(query));
		param.put("noticeFlag", "1");
		Page<Map<String, Object>> pageList = infoService.queryRecords(param, pageInfo);
		res.setDatas(pageList);
		res.setStatus(Result.SUCCESS);
		res.setMessage("成功");
		res.setPageNum(pageList.getPageNum());
		res.setPageSize(pageList.getPageSize());
		res.setPageCount(pageList.getPages());
		res.setTotal(pageList.getTotal());
		return res;
	}
	/**
	 * 查询公告详情
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/notice/queryNoticeDetail.json",method=RequestMethod.POST)
	public Map<String, Object> queryNoticeDetail(@RequestBody NoticeQuery query) throws BizException{
		if(StringUtils.isBlank(query.getUuid())){
			throw new BizException("uuid不能为空");
		}
		MapUtil result=new MapUtil();
		return result.setOk("查询成功", noticeService.findNoticeDetailByUUId(query.getUuid()));
		
	}
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/notice/appQueryNoticeDetail.json",method=RequestMethod.POST)
	public ResultDatas<NoticeVO> appQueryNoticeDetail(@RequestBody NoticeQuery query) throws BizException{
		if(StringUtils.isBlank(query.getUuid())){
			throw new BizException("uuid不能为空");
		}
		ResultDatas<NoticeVO> result = new ResultDatas<>();
		result.setDatas( noticeService.findNoticeDetailByUUId(query.getUuid()));
		result.setStatus(Result.SUCCESS);
		return result;
		
	}
}
