package com.sinochem.crude.trade.info.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.redisson.api.Node.InfoSection;
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
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.model.CollectionInfoVO;
import com.sinochem.crude.trade.info.query.CollectionInfoQuery;
import com.sinochem.crude.trade.info.service.CollectionService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.member.access.WithoutAccess;


@Controller
public class CollectionController  {

	@Autowired
	private CollectionService collectionService;
	
	Log log = LogFactory.getLog(CollectionController.class);

	/**
	 * 资讯收藏
	 */
	@ResponseBody
	@RequestMapping(value = URLMapping.COLLECTIONINFO_UPDATE)
	public Result collectInfo(@RequestBody Map<String,Object> map,CurrentUser user){
		boolean flag = map.containsKey("infoUUId");
		Result result = new Result();
		result.setMessage("资讯收藏成功");
		if(flag){
			String infoUUId = String.valueOf(map.get("infoUUId"));
			if(StringUtils.isNotBlank(infoUUId)){
				String collectUserId = "";
				return collectionService.collectInfo(infoUUId,collectUserId,user);
			}else{
				log.error("资讯主键不能为空");
				result.setFail("资讯收藏失败");
			}
		}else{
			log.error("资讯主键不存在");
			result.setFail("资讯收藏失败");
		}
		
		return result;
	}
	
	/**
	 * 我的收藏-列表
	 * @param user
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value = URLMapping.COLLECTION_INFO_LIST,method = RequestMethod.POST)
	public ResultDatas<List<CollectionInfoVO>> myInfoList(@RequestBody CollectionInfoQuery query,CurrentUser user){
		ResultDatas<List<CollectionInfoVO>> result  = new ResultDatas<>();
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
			
			CollectionInfoVO collectionInfoVO = new CollectionInfoVO();
			collectionInfoVO.setTitle(query.getTitle());
			if(StringUtils.isBlank(query.getMemberId())){
				if(user != null){
					collectionInfoVO.setCollectionUserId(user.getMemberId());
				}else{
					result.setFail("会员id为必填项！");
					return result;
				}
			}else{
				collectionInfoVO.setCollectionUserId(Long.valueOf(query.getMemberId()));
			}
			
			Page<Map<String, Object>> collList = collectionService.queryCollectionInfo(BeanConvertUtils.beanToMap(collectionInfoVO), pageInfo);
			
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
			result.setPageNum(collList.getPageNum());
			result.setPageSize(collList.getPageSize());
			result.setPageCount(collList.getPages());
			result.setTotal(collList.getTotal());
			result.setDatas(BeanConvertUtils.mapToBeanInList(collList, CollectionInfoVO.class));
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.EEROR);
			result.setMessage("失败");
		}
		return result;
	}
	
	
}
