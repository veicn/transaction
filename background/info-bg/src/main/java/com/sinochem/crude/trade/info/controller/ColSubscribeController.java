package com.sinochem.crude.trade.info.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.info.domain.ColSubscribe;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.model.ColumnVO;
import com.sinochem.crude.trade.info.model.SubscribeInfoVO;
import com.sinochem.crude.trade.info.query.SubscribeQuery;
import com.sinochem.crude.trade.info.service.ColColumnService;
import com.sinochem.crude.trade.info.service.ColSubscribeService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.member.access.WithoutAccess;


@Controller
public class ColSubscribeController {
	@Autowired
	private ColSubscribeService colSubscribeService;
	
	@Autowired
	private ColColumnService colColumnService;
	
	Log log = LogFactory.getLog(CollectionController.class);
	
	/**
	 * 我订阅的专栏
	 * @param query
	 * @param user
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value = URLMapping.SUBSCRIBE_COLUMN_LIST, method = RequestMethod.POST)
	public ResultDatas<List<ColumnVO>> mySubscribeQuery(@RequestBody SubscribeQuery query, CurrentUser user){
		ResultDatas<List<ColumnVO>> result = new ResultDatas<>();
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
			
			ColSubscribe subscribe = new ColSubscribe();
			if(StringUtils.isBlank(query.getMemberId())){
				if(user != null){
					subscribe.setSubscribeUser(ObjectUtils.toString(user.getMemberId()));
				}else{
					result.setFail("会员id为必填项！");
					return result;
				}
			}else{
				subscribe.setSubscribeUser(query.getMemberId());
			}
			
			Page<Map<String, Object>> collList = colSubscribeService.mySubscribeQuery(BeanConvertUtils.beanToMap(subscribe), pageInfo);
			List<ColumnVO> mapToBeanInList = new ArrayList<>();
			for (Map<String, Object> map : collList) {
				ColumnVO columnVO = BeanConvertUtils.mapToBean(map, ColumnVO.class);
				String id = columnVO.getId();
				List<Info> queryInfo = colSubscribeService.queryNewInfo(id);
				columnVO.setInfoList(queryInfo);
				mapToBeanInList.add(columnVO);
			}
			
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
			result.setPageNum(collList.getPageNum());
			result.setPageSize(collList.getPageSize());
			result.setPageCount(collList.getPages());
			result.setTotal(collList.getTotal());
			result.setDatas(mapToBeanInList);
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.EEROR);
			result.setMessage("失败");
		}
		return result;
	}
	
	/**
	 * 我订阅的文章
	 * @param query
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = URLMapping.SUBSCRIBE_ARTICLE_LIST, method = RequestMethod.POST)
	public ResultDatas<List<SubscribeInfoVO>> mySubscribeInfoQuery(@RequestBody SubscribeQuery query, CurrentUser user){
		ResultDatas<List<SubscribeInfoVO>> result = new ResultDatas<>();
		try {
			if(user == null){
				result.setStatus(Result.EEROR);
				result.setCode("Invalid Access");
				result.setMessage("请先登录");
				return result;
			}
			
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
			
			SubscribeInfoVO infoVO = new SubscribeInfoVO();
			if(StringUtils.isNotEmpty(query.getMemberId())){
				infoVO.setSubscribeUser(query.getMemberId());
			}else{
				infoVO.setSubscribeUser(ObjectUtils.toString(user.getMemberId()));
			}
			
			Page<Map<String, Object>> collList = colSubscribeService.mySubscribeInfoQuery(BeanConvertUtils.beanToMap(infoVO), pageInfo);
			
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
			result.setPageNum(collList.getPageNum());
			result.setPageSize(collList.getPageSize());
			result.setPageCount(collList.getPages());
			result.setTotal(collList.getTotal());
			result.setDatas(BeanConvertUtils.mapToBeanInList(collList, SubscribeInfoVO.class));
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.EEROR);
			result.setMessage("失败");
		}
		return result;
	}
	/**
	 * 订阅
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/subscribe/subscribeColumn.json",method = RequestMethod.POST)
	public ResultDatas<Map<String,Object>> subscribeColumn(@RequestBody SubscribeQuery query,CurrentUser user){
		ResultDatas<Map<String,Object>> result=new ResultDatas<>();
		try {
			if(StringUtils.isNotEmpty(query.getColumnId())){
				if(user!=null){
					ColSubscribe sub=new ColSubscribe();
					sub.setColumnId(query.getColumnId());
					sub.setSubscribeUser(user.getMemberId()+"");
					if(CollectionUtils.isNotEmpty(colSubscribeService.queryByEntitys(sub))){
						result.setStatus(Result.SUCCESS);
						result.setMessage("已订阅");
						return result;
					}
					sub.setSubscribeDate(DateTimeUtils.currentDate());
					sub.setSubscribeUserName(user.getName());
					sub.setSubscribeType("1");
					sub.setLangVer("zh");
					colSubscribeService.insertRecord(sub,user);
					colColumnService.updateColumnById(query.getColumnId(),"1");
					result.setStatus(Result.SUCCESS);
					result.setMessage("订阅成功");
				}else{
					result.setStatus(Result.EEROR);
					result.setCode("Invalid Access");
					result.setMessage("请登录");
				}
			}else{
				result.setStatus(Result.EEROR);
				result.setMessage("专栏id不能为null");
			}
			return result;
		} catch (Exception e) {
			result.setFail("订阅失败"+e);
			return result;
		}
		
	}
	/**
	 * 取消订阅
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/subscribe/removeSubscribe.json",method = RequestMethod.POST)
	public ResultDatas<Map<String,Object>> removeSubscribe(@RequestBody SubscribeQuery query,CurrentUser user){
		ResultDatas<Map<String,Object>> result=new ResultDatas<>();
		try {
			if(StringUtils.isNotEmpty(query.getColumnId())){
				if(user!=null){
					ColSubscribe sub=new ColSubscribe();
					sub.setColumnId(query.getColumnId());
					sub.setSubscribeUser(user.getMemberId()+"");
					colSubscribeService.updateByColumnId(sub);
					colColumnService.updateColumnById(query.getColumnId(),"2");
					result.setStatus(Result.SUCCESS);
					result.setMessage("取消订阅成功");
				}else{
					result.setStatus(Result.EEROR);
					result.setCode("Invalid Access");
					result.setMessage("请登录");
				}
			}else{
				result.setStatus(Result.EEROR);
				result.setMessage("专栏id不能为null");
			}
			return result;
		} catch (Exception e) {
			result.setFail("取消订阅失败"+e);
			return result;
		}
		
	}
	
}
