package com.sinochem.crude.trade.info.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;





import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyun.opensearch.sdk.dependencies.org.apache.commons.lang.StringUtils;
import com.aliyun.opensearch.sdk.generated.search.Config;
import com.aliyun.opensearch.sdk.generated.search.Order;
import com.aliyun.opensearch.sdk.generated.search.SearchFormat;
import com.aliyun.opensearch.sdk.generated.search.SearchParams;
import com.aliyun.opensearch.sdk.generated.search.Sort;
import com.aliyun.opensearch.sdk.generated.search.SortField;
import com.aliyun.opensearch.sdk.generated.search.general.SearchResult;
import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.OpenSearchUtils;
import com.sinochem.crude.trade.common.utils.ValueSetUtils;
import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.domain.Agio;
import com.sinochem.crude.trade.info.domain.BrowsingRecord;
import com.sinochem.crude.trade.info.domain.ChannelSub;
import com.sinochem.crude.trade.info.domain.ShipPoint;
import com.sinochem.crude.trade.info.model.ChannelMVO;
import com.sinochem.crude.trade.info.model.ChannelSubRes;
import com.sinochem.crude.trade.info.model.ChannelSubVO;
import com.sinochem.crude.trade.info.model.CmsChannelMRes;
import com.sinochem.crude.trade.info.model.CrudeAgioQueryVo;
import com.sinochem.crude.trade.info.model.CrudeAgioVo;
import com.sinochem.crude.trade.info.model.CrudeBasePriceVo;
import com.sinochem.crude.trade.info.model.InfoVO;
import com.sinochem.crude.trade.info.query.CrudeAgioQuery;
import com.sinochem.crude.trade.info.query.CrudeOfficialQuery;
import com.sinochem.crude.trade.info.model.NewSymbolPriceVO;
import com.sinochem.crude.trade.info.model.SymbolPriceVO;
import com.sinochem.crude.trade.info.query.FrontInfoVo;
import com.sinochem.crude.trade.info.query.QualityVo;
import com.sinochem.crude.trade.info.result.CrudePriceRest;
import com.sinochem.crude.trade.info.service.AgioService;
import com.sinochem.crude.trade.info.service.BrowsingRecordService;
import com.sinochem.crude.trade.info.service.ChannelMService;
import com.sinochem.crude.trade.info.service.ChannelSubService;
import com.sinochem.crude.trade.info.service.CrudeBasePriceService;
import com.sinochem.crude.trade.info.service.InfoService;
import com.sinochem.crude.trade.info.service.PriceIndexService;
import com.sinochem.crude.trade.info.service.PriceIndexTemplateService;
import com.sinochem.crude.trade.info.service.QualityService;
import com.sinochem.crude.trade.info.service.ShipPointService;
import com.sinochem.crude.trade.info.service.SymbolPriceService;
import com.sinochem.crude.trade.info.util.MapUtil;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import com.sinochem.it.b2b.member.access.AccessDeniedResultBuilder.Result;
import com.sinochem.it.b2b.member.access.ApiSafeAccess;



@Controller
public class FrontInfoController {
	
	@Autowired
	private InfoService infoService;
	
	@Autowired
	private ChannelSubService channelSubService;
	
	@Autowired
	private ChannelMService channelMService;
	
	@Autowired
	private PriceIndexTemplateService priceIndexTemplateService;
	
	@Autowired
	private PriceIndexService priceIndexService;
	
	@Autowired
	private AgioService agioService;
	
	@Autowired
	private ShipPointService shipPointService;
	
	@Autowired
	private CrudeBasePriceService crudeBasePriceService;
	
	@Autowired
	private QualityService qualityService;
	
	@Autowired
	private BrowsingRecordService browsingRecordService;
	
	@Autowired
	private SymbolPriceService symbolPriceService;
	
	@Value("${aliyun.opensearch.appName}")
	private String appName;
	
	private static final Log log = LogFactory.getLog(FrontInfoController.class);
	
	/**
	 * web资讯首页
	 * @param vo
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/front/zx_index.json", method = RequestMethod.POST)
	public ResultDatas<List<Map<String, Object>>> getFrontInfoIndex(@RequestBody FrontInfoVo vo){
		ResultDatas<List<Map<String, Object>>> result = new ResultDatas<>();
		try {
			List<Map<String, Object>> resultList = new ArrayList<>();
			
			//只取资讯栏目下的数据
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("channelDesc", Constants.channel_m_desc_enum.channel_m_desc_ZX.getChannel_m_desc_key());
			List<Map<String, Object>> channelList = channelSubService.queryRecords(paramMap);
			
			for(Map<String, Object> c: channelList){
				FrontInfoVo param = new FrontInfoVo();
				//param.setChannelName((String)c.get("channelName"));
				param.setChannelCode((String)c.get("channelCode"));
				param.setChannelMDesc(Constants.channel_m_desc_enum.channel_m_desc_ZX.getChannel_m_desc_key());
				//首页取前5条即可
				param.setPageNum(1);
				param.setPageSize(10);
				Map<String, Object> map = this.getInfoList(param);
				map.putAll(c);
				resultList.add(map);
			}
			result.setDatas(resultList);
		} catch (Exception e) {
			log.error("", e);
		}
		
		return result;
	}
	
	/**
	 * web资讯首页搜索
	 * @param vo
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/front/zx_indexQuery.json", method = RequestMethod.POST)
	public ResultDatas<List<Map<String, Object>>> getFrontInfoIndexQuery(@RequestBody FrontInfoVo vo){
		ResultDatas<List<Map<String, Object>>> result = new ResultDatas<>();
		try {
			if(StringUtils.isEmpty(vo.getChannelMDesc())){
				result.setFail("频道类型为必传");
				return result;
			}
			List<Map<String, Object>> resultList = new ArrayList<>();
			
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("channelDesc", vo.getChannelMDesc());
			List<Map<String, Object>> channelList = channelSubService.queryRecords(paramMap);
			
			for(Map<String, Object> c: channelList){
				FrontInfoVo param = new FrontInfoVo();
				param.setChannelName((String)c.get("channelName"));
				param.setChannelMDesc(vo.getChannelMDesc());
				param.setSearchText(vo.getSearchText());
				param.setChannelMCode(vo.getChannelMCode());
				param.setChannelMName(vo.getChannelMName());
				param.setStick(vo.getStick());
				param.setExtend3(vo.getExtend3());
				
				param.setPageNum(vo.getPageNum());
				param.setPageSize(vo.getPageSize());
				Map<String, Object> map = this.getInfoList(param);
				map.putAll(c);
				resultList.add(map);
			}
			result.setDatas(resultList);
		} catch (Exception e) {
			log.error("", e);
		}
		
		return result;
	}
	
	
	/**
	 * 前台-资讯列表
	 * @param uuid
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/front/infoList.json", method = RequestMethod.POST)
	public ResultDatas<Map<String, Object>> getFrontInfoList(@RequestBody FrontInfoVo vo){
		ResultDatas<Map<String, Object>> result = new ResultDatas<>();
		Map<String, Object> map = new HashMap<>();
		try {
			//如果收到的是SSBK,查询类别为石油百科的(app定制)
			if(Constants.channel_m_desc_enum.channel_m_desc_SSBK.getChannel_m_desc_key().equals(vo.getChannelMCode())){
				vo.setChannelMDesc(vo.getChannelMCode());
				vo.setChannelMCode(null);
			}else{
				vo.setChannelMDesc(Constants.channel_m_desc_enum.channel_m_desc_ZX.getChannel_m_desc_key());
			}
				
			map = this.getInfoList(vo);
			//openSearch的查询结果pageInfo信息转换
			result.setPageCount((Integer)map.get("pageCount"));
			map.remove("pageCount");
			result.setPageNum((Integer)map.get("pageNum"));
			map.remove("pageNum");
			result.setPageSize((Integer)map.get("pageSize"));
			map.remove("pageSize");
			result.setTotal((Long.valueOf(map.get("total").toString())));
			map.remove("total");
			result.setDatas(map);
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	/**
	 * 前台-资讯列表通过code查询
	 * @param uuid
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/front/infoListByCode.json", method = RequestMethod.POST)
	public ResultDatas<Map<String, Object>> getFrontInfoListByCode(@RequestBody FrontInfoVo vo){
		ResultDatas<Map<String, Object>> result = new ResultDatas<>();
		Map<String, Object> map = new HashMap<>();
		try {
			map = this.getInfoList(vo);
			//openSearch的查询结果pageInfo信息转换
			result.setPageCount((Integer)map.get("pageCount"));
			map.remove("pageCount");
			result.setPageNum((Integer)map.get("pageNum"));
			map.remove("pageNum");
			result.setPageSize((Integer)map.get("pageSize"));
			map.remove("pageSize");
			result.setTotal((Long.valueOf(map.get("total").toString())));
			map.remove("total");
			result.setDatas(map);
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	/**
	 * 资讯列表--搜索引擎
	 * @param uuid
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.INFOLISTOS_QUERY, method = RequestMethod.POST)
	public ResultDatas<Map<String, Object>> getInfoListOS(@RequestBody FrontInfoVo vo){
		ResultDatas<Map<String, Object>> result = new ResultDatas<>();
		Map<String, Object> map = new HashMap<>();
		try {
			
			if(StringUtils.isBlank(vo.getChannelMDesc())){
				result.setFail("频道类型为必传");
				return result;
			}
			
			map = this.getInfoList(vo);
			//openSearch的查询结果pageInfo信息转换
			result.setPageCount((Integer)map.get("pageCount"));
			map.remove("pageCount");
			result.setPageNum((Integer)map.get("pageNum"));
			map.remove("pageNum");
			result.setPageSize((Integer)map.get("pageSize"));
			map.remove("pageSize");
			result.setTotal((Long.valueOf(map.get("total").toString())));
			map.remove("total");
			result.setDatas(map);
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * 
	 * 船务
	 * 
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.TRANSPORT_QUERY, method = RequestMethod.POST)
	public ResultDatas<Map<String, Object>> transportInfoList(@RequestBody FrontInfoVo vo){
		ResultDatas<Map<String, Object>> result = new ResultDatas<>();
		Map<String,Object> map = new HashMap<>();
		try {
			//如果收到的是SSBK,查询类别为石油百科的(app定制)
			if(Constants.channel_m_desc_enum.channel_m_desc_SSBK.getChannel_m_desc_key().equals(vo.getChannelMCode())){
				vo.setChannelMDesc(vo.getChannelMCode());
				vo.setChannelMCode(null);
			}else{
				vo.setChannelMDesc(Constants.channel_m_desc_enum.channel_m_desc_ZX.getChannel_m_desc_key());
			}
				
			map = this.getInfoList(vo);
			//openSearch的查询结果pageInfo信息转换
			result.setPageCount((Integer)map.get("pageCount"));
			map.remove("pageCount");
			result.setPageNum((Integer)map.get("pageNum"));
			map.remove("pageNum");
			result.setPageSize((Integer)map.get("pageSize"));
			map.remove("pageSize");
			result.setTotal((Long.valueOf(map.get("total").toString())));
			map.remove("total");
			result.setDatas(map);
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 前台-资讯详情
	 * @param uuid
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/front/infoDetail.json")
	public ResultDatas<Map<String, Object>> getFrontInfoDetail(@RequestParam String infoUuid, CurrentUser user){
		ResultDatas<Map<String, Object>> result = new ResultDatas<>();
		//user = null;
		try {
			//资讯本身
			Map<String, Object> param = new HashMap<>();
			param.put("infoUuid", infoUuid);
			Map<String, Object> map = infoService.getFrontList(param).get(0);	
			
			if(StringUtils.equals("金凯讯", (String) map.get("origin"))){
				map.put("textHtml", "<p style='text-indent:32px;line-height:150%'>"+map.get("textHtml")+"</p>");
			}
			
			//判断是否公开
			Long channelId = Long.valueOf(map.get("channelId").toString());
			String openFlag = channelSubService.findByPrimaryKey(channelId).getExtend1();
			if(!"1".equals(openFlag) && user==null){
				result.setStatus(Result.ERROR);
				result.setCode("Invalid Access");
				result.setMessage("请先登录");
				return result;
			}
			
			//上一篇
			if(infoService.getPreInfo(map)!=null)
				map.putAll(infoService.getPreInfo(map));
			
			//下一篇
			if(infoService.getNextInfo(map)!=null)
				map.putAll(infoService.getNextInfo(map));

			//统计浏览量
			BrowsingRecord record = new BrowsingRecord();
			record.setUuid(UUID.randomUUID().toString().replace("-", ""));
			record.setInformationId((Long)map.get("id"));
			map.remove("id");
			record.setBrowsingUserId(user==null || user.getMemberId()==null?"游客":user.getMemberId().toString());
			record.setCreateUser(user==null || user.getMemberId()==null?"游客":user.getMemberId().toString());
			record.setCreateDate(new Date());
			browsingRecordService.insertBrowsing(record);
			
			//app端获取相关资讯
			FrontInfoVo vo = new FrontInfoVo();
			vo.setChannelName(map.get("channelName").toString());
			//vo.setChannelMDesc(Constants.channel_m_desc_enum.channel_m_desc_ZX.getChannel_m_desc_key());
			String tagList = (String)map.get("tagList");
			if(StringUtils.isEmpty(tagList)){
				map.put("tagList", "");
			}
			if(StringUtils.isNotBlank(tagList)){
				vo.setSearchText(tagList.replace(",", ""));
			}
			vo.setPageNum(1);
			vo.setPageSize(5);
			List<Map<String, Object>> relatedList = (List<Map<String, Object>>)this.getInfoList(vo).get("infoList");
			for(int i=0; i<relatedList.size();i++){
				if(infoUuid.equals(relatedList.get(i).get("uuid"))){
					relatedList.remove(i);
					break;
				}
			}
			if(CollectionUtils.isEmpty(relatedList)){
				vo.setSearchText("");
				relatedList = (List<Map<String, Object>>)this.getInfoList(vo).get("infoList");
			}
			map.put("relatedList", relatedList);
			
			//对*月*日航运市场情况进行特殊处理
			this.processShipPoint(map);
			
			result.setDatas(map);
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	/**
	 * 外部-资讯详情
	 * @param vo
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/front/infoDetailByTitle.json",method=RequestMethod.POST)
	public ResultDatas<Map<String, Object>> getFrontInfoDetailByTitle(@RequestBody InfoVO vo, CurrentUser user){
		ResultDatas<Map<String, Object>> result = new ResultDatas<>();
		//user = null;
		try {
			if(StringUtil.isBlank(vo.getTitle())){
				result.setStatus(Result.ERROR);
				result.setMessage("title为空");
				return result;
			}
			Map<String, Object> map = infoService.getFrontList(BeanConvertUtils.beanToMap(vo)).get(0);	
			if(StringUtils.equals("金凯讯", (String) map.get("origin"))){
				map.put("textHtml", "<p style='text-indent:32px;line-height:150%'>"+map.get("textHtml")+"</p>");
			}
			
			result.setDatas(map);
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	private void processShipPoint(Map<String, Object> map) {
		try {
			String title = (String)map.get("title");
			if(title.endsWith("航运市场情况") || title.startsWith("Market Report")){
				String releaseDate = (String) map.get("releaseDate");
				//获取离发布日期最近的航运点数发布日期
				Date endDate = (Date)shipPointService.queryEndDate(releaseDate).get(0).get("endDate");
				ShipPoint shipPoint = new ShipPoint();
				shipPoint.setReleaseDate(endDate);
				List<ShipPoint> tableList = shipPointService.queryByEntitys(shipPoint);
				map.put("tableList", tableList);
				
				List<Map<String, Object>> list = shipPointService.queryDilveryRegion(endDate);
				for(Map<String, Object> m : list){
					m.put("endDate", endDate);
					List<Map<String, Object>> dataList = shipPointService.queryPointYear(m);
					m.put("dataList", dataList);
				}
				map.put("echartList", list);
			}
		} catch (Exception e) {
			log.error("获取航运点数详情报错", e);
		}
	}
	
	/**
	 * 前台-指数(实时报价)
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/front/indecPrice.json")
	public ResultDatas<List<Map<String, Object>>> getFrontIndexPrice(){
		ResultDatas<List<Map<String, Object>>> result = new ResultDatas<>();
		try {
			//找到所有的指数模板
			Map<String, Object> map = new HashMap<>();
			map.put("status", "1");
			List<Map<String, Object>> list = priceIndexTemplateService.queryRecords(map);
			
			//对每一个模板，取最新的30条数据
			for(Map<String, Object> m : list){
				Long templateId = (Long) m.get("id"); 
				//获取该模板的指数最大值和最小值
				//log.info("获取区间--->" + priceIndexService.queryInterval(templateId));
				Map<String, Object> temp = priceIndexService.queryInterval(templateId);
				if(temp!=null)
					m.putAll(temp);
				
				List<Map<String, Object>> priceList = priceIndexService.queryLatest30(templateId);
				if(CollectionUtils.isEmpty(priceList)){
					list.remove(m);
					break;
				}
				m.put("priceList", priceList);
			}
			result.setDatas(list);
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取资讯频道列表App---频道栏
	 * @return
	 */
	@WithoutAccess
	@RequestMapping(value = URLMapping.CMS_APPCHANNELMLIST)
	@ResponseBody
	public ResultDatas<List<CmsChannelMRes>> appChannelMList() {
		ResultDatas<List<CmsChannelMRes>> res = new ResultDatas<>();

		try {
			List<CmsChannelMRes> channelList = new ArrayList<>();
			Map<String, CodeValue> list = ValueSetUtils.getValuesByGroup(Constants.CHANNEL_LIST);
			if (list != null && list.size() > 0) {
				for (Map.Entry<String, CodeValue> e : list.entrySet()) {
					CodeValue value = e.getValue();
					if (value.getGroup().equals(Constants.CHANNEL_LIST)) {
						CmsChannelMRes cmsChannel = new CmsChannelMRes();
						cmsChannel.setChannelMCode(value.getCode());
						cmsChannel.setChannelMName(value.getValue());
						cmsChannel.setChannelMDesc(value.getExt1());
						channelList.add(cmsChannel);
					}
				}
			}
			res.setStatus(Result.SUCCESS);
			res.setMessage("成功");
			res.setDatas(channelList);
		} catch (Exception e) {
			log.error("", e);
			res.setStatus(Result.ERROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
	/**
	 * 获取资讯主频道列表---频道栏
	 * @return
	 */
	@WithoutAccess
	@RequestMapping(value = URLMapping.CMS_CHANNELMLIST,method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<List<CmsChannelMRes>> channelMList(@RequestBody ChannelMVO vo) {
		ResultDatas<List<CmsChannelMRes>> res = new ResultDatas<>();
		
		try {
			if(StringUtils.isEmpty(vo.getChannelMDesc())){
				res.setFail("频道类型为必传");
				return res;
			}
			
			res.setStatus(Result.SUCCESS);
			res.setMessage("成功");
			res.setDatas(BeanConvertUtils.mapToBeanInList(channelMService.queryRecords(BeanConvertUtils.beanToMap(vo)), CmsChannelMRes.class));
		} catch (Exception e) {
			log.error("获取资讯主频道列表异常", e);
			res.setStatus(Result.ERROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
	/**
	 * 获取资讯子频道列表---频道栏
	 * @return
	 */
	@WithoutAccess
	@RequestMapping(value = URLMapping.CMS_CHANNELSUBLIST,method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<List<ChannelSubRes>> channelSubList(@RequestBody ChannelSubVO vo) {
		ResultDatas<List<ChannelSubRes>> res = new ResultDatas<>();
		
		try {
			if(StringUtils.isEmpty(vo.getChannelMDesc())){
				res.setFail("频道类型为必传");
				return res;
			}
			
			SimplePageInfo pageInfo = new SimplePageInfo();
			pageInfo.setPageNum(1);
			pageInfo.setPageSize(0);
			
			res.setStatus(Result.SUCCESS);
			res.setMessage("成功");
			res.setDatas(BeanConvertUtils.mapToBeanInList(channelSubService.queryChannelSubList(BeanConvertUtils.beanToMap(vo), pageInfo), ChannelSubRes.class));
		} catch (Exception e) {
			log.error("获取资讯主频道列表异常", e);
			res.setStatus(Result.ERROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
	@WithoutAccess
	@RequestMapping(value = "/front/appStickList.json")
	@ResponseBody
	public ResultDatas<Map<String, Object>> appStickList() {
		ResultDatas<Map<String, Object>> result = new ResultDatas<>();
		Map<String, Object> map = new HashMap<>();
		try {
			FrontInfoVo vo = new FrontInfoVo();
			vo.setStick("1");
			vo.setChannelMDesc(Constants.channel_m_desc_enum.channel_m_desc_ZX.getChannel_m_desc_key());
			map = this.getInfoList(vo);
			//openSearch的查询结果pageInfo信息转换
			result.setPageCount((Integer)map.get("pageCount"));
			map.remove("pageCount");
			result.setPageNum((Integer)map.get("pageNum"));
			map.remove("pageNum");
			result.setPageSize((Integer)map.get("pageSize"));
			map.remove("pageSize");
			result.setTotal((Long.valueOf(map.get("total").toString())));
			map.remove("total");
			result.setDatas(map);
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	/**
	 * 获取当天的指数(实时报价)
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/front/indecPriceT.json")
	public ResultDatas<List<Map<String, Object>>> getFrontIndexPriceT(){
		ResultDatas<List<Map<String, Object>>> result = new ResultDatas<>();
		try {
			//找到RMB的指数模板
			Map<String, Object> map = new HashMap<>();
			List<Map<String, Object>> list = priceIndexService.queryLatest1(map);
			result.setDatas(list);
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取当天美元的指数(实时报价)
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value = URLMapping.DOLLARQUOTE_QUERY)
	public ResultDatas<List<Map<String, Object>>> getFrontIndexPriceRMB(){
		ResultDatas<List<Map<String, Object>>> result = new ResultDatas<>();
		try {
			//找到所有的指数模板
			Map<String, Object> map = new HashMap<>();
			List<Map<String, Object>> list = priceIndexService.queryLatest1(map);
			List<Map<String, Object>> rmbList = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map2 : list) {
				if(StringUtils.equals("Dollar", (String) map2.get("indexCode"))){
					map2.remove("indexCode");
					map2.remove("indexName");
					map2.remove("riseFall");
					map2.remove("riseFallRate");
					map2.remove("riseFallRateD");
					rmbList.add(map2);
				}
			}
			result.setDatas(rmbList);
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取昨天的收盘价
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/front/indecPriceZS.json")
	public ResultDatas<List<Map<String, Object>>> getFrontIndexPriceZS(){
		ResultDatas<List<Map<String, Object>>> result = new ResultDatas<>();
		try {
			Map<String, Object> map = new HashMap<>();
			List<Map<String, Object>> list = priceIndexService.queryZS(map);
			result.setDatas(list);
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取当天的指数(实时报价)-排序
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/front/indecPriceTB.json")
	public ResultDatas<List<Map<String, Object>>> getFrontIndexPriceTB(@RequestParam String orderBy){
		ResultDatas<List<Map<String, Object>>> result = new ResultDatas<>();
		try {
			Map<String, Object> map = new HashMap<>();
			switch(orderBy){
			case "riseFallAsc":
				map.put("orderBy", "rise_Fall Asc");
				break;
			case "riseFallDesc":
				map.put("orderBy", "rise_Fall Desc");
				break;
			case "riseFallRateAsc":
				map.put("orderBy", "rise_Fall_Rate Asc");
				break;
			case "riseFallRateDesc":
				map.put("orderBy", "rise_Fall_Rate Desc");
				break;
			case "smeiValueAsc":
				map.put("orderBy", "smei_Value Asc");
				break;
			case "smeiValueDesc":
				map.put("orderBy", "smei_Value Desc");
				break;
			default:;
			}
			//找到所有的指数模板
			List<Map<String, Object>> list = priceIndexService.queryLatest1(map);
			result.setDatas(list);
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * 获取当天的贴水查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value=URLMapping.AGIOLISTQUERY_QUERY, method = RequestMethod.POST)
	public ResultDatas<List<Agio>> getFrontAgioT(@RequestBody CrudeAgioQuery query){
		ResultDatas<List<Agio>> result = new ResultDatas<>();
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
			
			/*查询条件*/
			CrudeAgioQueryVo agioQueryVo = new CrudeAgioQueryVo();
			agioQueryVo.setCrudeNameE(query.getCrudeNameE());
			agioQueryVo.setStartDate(query.getStartDate());
			agioQueryVo.setEndDate(query.getEndDate());
			agioQueryVo.setLatestFlag("1");
			
			Map<String, Object> beanToMap = BeanConvertUtils.beanToMap(agioQueryVo);
			Page<Map<String, Object>> queryAgio = agioService.queryRecords(beanToMap, pageInfo);
			
			result.setPageNum(queryAgio.getPageNum());
			result.setPageSize(queryAgio.getPageSize());
			result.setPageCount(queryAgio.getPages());
			result.setTotal(queryAgio.getTotal());
			result.setDatas(BeanConvertUtils.mapToBeanInList(queryAgio, Agio.class));
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取最近官价
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/front/officialT.json")
	public ResultDatas<List<Map<String, Object>>> getFrontOfficialT(){
		ResultDatas<List<Map<String, Object>>> result = new ResultDatas<>();
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("latestFlag", "1");
			List<Map<String, Object>> dataList = crudeBasePriceService.queryRecords(param);
			result.setDatas(dataList);
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取官价列表
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping(value="/front/officialList.json")
	public ResultDatas<List<Map<String, Object>>> getFrontOfficialList(){
		ResultDatas<List<Map<String, Object>>> result = new ResultDatas<>();
		try {
			List<Map<String, Object>> dataList = new ArrayList<>();
			Map<String, Object> titleLine = new HashMap<>();
			titleLine.put("col1", "油种");
			titleLine.put("col2", "基准价");
			titleLine.put("col3", "201704");
			titleLine.put("col4", "201705");
			titleLine.put("col5", "201706");
			titleLine.put("col6", "201707");
			titleLine.put("col7", "201708");
			titleLine.put("col8", "201709");
			titleLine.put("col9", "2017010");
			titleLine.put("col10", "201711");
			titleLine.put("col11", "201712");
			titleLine.put("col12", "201801");
			dataList.add(titleLine);
			//String[] titile = {"油种", "基准价", "201704", "201705", "201706", "201707", "201708", "201709","201710", "201711", "201712", "201801"};
			for(int i=0; i<26; i++){
				Map<String, Object> dataLine = new HashMap<>();
				dataLine.put("col1", "KUWAIT" + "-" + i);
				dataLine.put("col2", "OMAN/DUBAI");
				dataLine.put("col3", new BigDecimal("0.11").multiply(BigDecimal.valueOf(i)));
				dataLine.put("col4", new BigDecimal("0.12").multiply(BigDecimal.valueOf(i)));
				dataLine.put("col5", new BigDecimal("0.13").multiply(BigDecimal.valueOf(i)));
				dataLine.put("col6", new BigDecimal("0.14").multiply(BigDecimal.valueOf(i)));
				dataLine.put("col7", new BigDecimal("0.15").multiply(BigDecimal.valueOf(i)));
				dataLine.put("col8", new BigDecimal("0.16").multiply(BigDecimal.valueOf(i)));
				dataLine.put("col9", new BigDecimal("0.17").multiply(BigDecimal.valueOf(i)));
				dataLine.put("col10", new BigDecimal("0.177").multiply(BigDecimal.valueOf(i)));
				dataLine.put("col11", new BigDecimal("0.178").multiply(BigDecimal.valueOf(i)));
				dataLine.put("col12", new BigDecimal("0.179").multiply(BigDecimal.valueOf(i)));
				dataList.add(dataLine);
			}
			result.setStatus(0);
			result.setDatas(dataList);
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(999);
			result.setMessage(e.getMessage());
		}
		return result;
	}*/
	
	/**
	 * 获取近一年航运点数形成折线图用
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/front/shipPointY.json")
	public ResultDatas<List<Map<String, Object>>> getShipPointY(){
		ResultDatas<List<Map<String, Object>>> result = new ResultDatas<>();
		try {
			List<Map<String, Object>> list = shipPointService.queryDilveryRegion(null);
			for(Map<String, Object> m : list){
				List<Map<String, Object>> dataList = shipPointService.queryPointYear(m);
				m.put("dataList", dataList);
			}
			result.setDatas(list);
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 原油品质查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/front/qualityList.json", method = RequestMethod.POST)
	public ResultDatas<List<Map<String, Object>>> getQualityList(@RequestBody QualityVo vo){
		ResultDatas<List<Map<String, Object>>> result = new ResultDatas<>();
		try {
			Map param = BeanConvertUtils.beanToMap(vo);
			SimplePageInfo pageInfo = vo;
			Page<Map<String, Object>> page = qualityService.crudeRecords(param, pageInfo);
			result.setTotal(page.getTotal());
			result.setPageSize(vo.getPageSize());
			result.setPageNum(vo.getPageNum());
			result.setDatas(page.getResult());
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * web端获取石油百科的分类信息
	 * @return
	 */
	@WithoutAccess
	@RequestMapping(value="/front/ssbkChannelList.json")
	@ResponseBody
	public ResultDatas<List<Map<String, Object>>> getSsbkChannelList() {
		ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("channelMDesc", Constants.channel_m_desc_enum.channel_m_desc_SSBK.getChannel_m_desc_key());
			List<Map<String, Object>> list = channelMService.queryRecords(param);
			for(Map<String, Object> m : list) {
				Long channelMId = (Long)m.get("id");
				ChannelSub subParam = new ChannelSub();
				subParam.setChannelMId(channelMId);
				List<ChannelSub> subList = channelSubService.queryByEntitys(subParam);
				for(ChannelSub sub : subList)
					sub.setId(null);
				m.put("subList", subList);
				m.remove("id");
			}
			res.setStatus(Result.SUCCESS);
			res.setMessage("成功");
			res.setDatas(list);
		} catch (Exception e) {
			log.error("", e);
			res.setStatus(Result.ERROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
	/**
	 * web端获取石油百科的列表
	 * @return
	 */
	@WithoutAccess
	@RequestMapping(value="/front/ssbkList.json", method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<Map<String, Object>> getSsbkList(@RequestBody FrontInfoVo vo){
		ResultDatas<Map<String, Object>> result = new ResultDatas<>();
		Map<String, Object> map = new HashMap<>();
		try {
			vo.setChannelMDesc(Constants.channel_m_desc_enum.channel_m_desc_SSBK.getChannel_m_desc_key());
			map = this.getInfoList(vo);
			//openSearch的查询结果pageInfo信息转换
			result.setPageCount((Integer)map.get("pageCount"));
			map.remove("pageCount");
			result.setPageNum((Integer)map.get("pageNum"));
			map.remove("pageNum");
			result.setPageSize((Integer)map.get("pageSize"));
			map.remove("pageSize");
			result.setTotal((Long.valueOf(map.get("total").toString())));
			map.remove("total");
			result.setDatas(map);
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * web端获取石油百科的详情
	 * @return
	 */
	@WithoutAccess
	@RequestMapping(value="/front/ssbkDetail.json")
	@ResponseBody
	public ResultDatas<Map<String, Object>> getSsbkDetail(@RequestParam String infoUuid, CurrentUser user) {
		ResultDatas<Map<String, Object>> result = new ResultDatas<>();
		//user = null;
		try {
			//资讯本身
			Map<String, Object> param = new HashMap<>();
			param.put("infoUuid", infoUuid);
			Map<String, Object> map = infoService.getFrontList(param).get(0);			
			
			//判断是否公开
			Long channelId = Long.valueOf(map.get("channelId").toString());
			String openFlag = channelSubService.findByPrimaryKey(channelId).getExtend1();
			if(!"1".equals(openFlag) && user==null){
				result.setStatus(Result.ERROR);
				result.setCode("Invalid Access");
				result.setMessage("请先登录");
				return result;
			}
			
			//上一篇
			if(infoService.getPreInfo(map)!=null)
				map.putAll(infoService.getPreInfo(map));
			
			//下一篇
			if(infoService.getNextInfo(map)!=null)
				map.putAll(infoService.getNextInfo(map));
			
			//统计浏览量
			BrowsingRecord record = new BrowsingRecord();
			record.setUuid(UUID.randomUUID().toString().replace("-", ""));
			record.setInformationId((Long)map.get("id"));
			map.remove("id");
			record.setBrowsingUserId(user==null || user.getMemberId()==null?"游客":user.getMemberId().toString());
			record.setCreateUser(user==null || user.getMemberId()==null?"游客":user.getMemberId().toString());
			record.setCreateDate(new Date());
			browsingRecordService.insertBrowsing(record);
			
			result.setDatas(map);
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
		
	private Map<String, Object> getInfoList(FrontInfoVo vo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		//定义Config对象，用于设定config子句参数，分页或数据返回格式，指定应用名等等
		Config config = new Config(Lists.newArrayList(appName));
		
		if(vo.getPageNum()==null)
			vo.setPageNum(1);
		if(vo.getPageSize()==null)
			vo.setPageSize(10);
		
		config.setStart((vo.getPageNum()-1)*vo.getPageSize());
		config.setHits(vo.getPageSize());	
		
		//设置返回格式为FULLJSON，目前支持返回 XML，JSON，FULLJSON 等格式
		config.setSearchFormat(SearchFormat.FULLJSON);
		config.setFetchFields(Lists.newArrayList("uuid", "title", "browse_count", "author", "release_date", "general", "tex", "extend1", "origin","display_mode","extend1_2"));
		
		SearchParams searchParams = new SearchParams(config);
		
		//设置查询条件
		StringBuilder query = new StringBuilder();
		query.append("alive_flag:'1' AND status:'30'");
		//类别
		if(StringUtils.isNotBlank(vo.getChannelMDesc()))
			query.append(" AND channel_m_desc:'").append(vo.getChannelMDesc()).append("'");
		//置顶标志
		if(StringUtils.isNotBlank(vo.getStick()))
			query.append(" AND stick:'").append(vo.getStick()).append("'");
		//子频道名称
		if(StringUtils.isNotBlank(vo.getChannelName()))
			query.append(" AND channel_name:'").append(vo.getChannelName()).append("'");
		//子频道编码
		if(StringUtils.isNotBlank(vo.getChannelCode())) {
			query.append(" AND channel_code:'").append(vo.getChannelCode()).append("'");
		}
		//主频道名称
		if(StringUtils.isNotBlank(vo.getChannelMName()))
			query.append(" AND channel_m_name:'").append(vo.getChannelMName()).append("'");
		//主频道编码
		if(StringUtils.isNotBlank(vo.getChannelMCode()))
			query.append(" AND channel_m_code:'").append(vo.getChannelMCode()).append("'");
		//资讯发布频率: day(每日一份) month(每月一份) week(每周一份) halfMonth(半月一份) untime(不定时)
		if(StringUtils.isNotBlank(vo.getExtend3()))
			query.append(" AND extend3:'").append(vo.getExtend3()).append("'");
		//资讯标题
		if(StringUtils.isNotBlank(vo.getTitle()))
			query.append(" AND title:'").append(vo.getTitle()).append("'");
		//搜索框的输入
		if(StringUtils.isNotBlank(vo.getSearchText()))
			query.append(" AND default:'").append(vo.getSearchText()).append("'");
		//log.info("query--->" + query.toString());
		searchParams.setQuery(query.toString());
		
		// 设置sort条件
		Sort sorter = new Sort();
		sorter.addToSortFields(new SortField("stick", Order.DECREASE)); //设置stick字段降序
		sorter.addToSortFields(new SortField("release_date", Order.DECREASE));
		searchParams.setSort(sorter);
			
		// 搜索
		SearchResult searchResult = OpenSearchUtils.search(searchParams);
		//log.info("原始返回--->  "  + searchResult.getResult());
		ObjectMapper mapper = new ObjectMapper();
		Map map1 = mapper.readValue(searchResult.getResult(), Map.class);
		Map map2 = (Map)(map1.get("result"));
		Integer total = (Integer)map2.get("total");
		log.info("total--->  " + total);
		List<Map> list = (List<Map>)map2.get("items");
		
		List<Map<String, Object>> infoList = new ArrayList<>();
		for(int i=0; i<list.size(); i++){
			infoList.add((Map<String, Object>)list.get(i).get("fields"));
		}
		map.put("pageNum", vo.getPageNum());
		map.put("pageSize", vo.getPageSize());
		map.put("total", total);
		int pageCount = total%vo.getPageSize()==0?total/vo.getPageSize():total/vo.getPageSize()+1;
		map.put("pageCount", pageCount);
		
		map.put("infoList", infoList);
		return map;
	}
	
	
	/**
	 * http的post请求
	 * @param url
	 * @param json
	 * @return
	 */
	/*public static JSONObject doPost(String url,JSONObject json){
        
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }*/
	
	/**
	 * 
	 * 最新官价查询
	 * @param query
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.OFFICALLISTQUERY_QUERY, method = RequestMethod.POST)
	public ResultDatas<List<Map<String, Object>>>  getFrontOfficialList(@RequestBody CrudeOfficialQuery query){
		ResultDatas<List<Map<String, Object>>> result = new ResultDatas<>();
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
			if(StringUtils.isEmpty(query.getStartDate())){
				result.setFail("日期不能为空，格式为yyyy");
				return result;
			}
			
			/*拼接标头*/
			Page<Map<String, Object>> officialListQuery = new Page<Map<String, Object>>();
			/*List<Map<String, Object>> dataList = new ArrayList<>();*/
			Map<String, Object> titleLine = new HashMap<>();
			titleLine.put("col1", "油种");
			titleLine.put("col2", "基准价");
			/*List<String> dateList = crudeBasePriceService.getDateList(query.getStartDate());*/
			List<String> dateList = new ArrayList<>();
			for (int i = 1; i < 13; i++) {
				StringBuilder sb = new StringBuilder();
				if(i<10){
					sb.append(query.getStartDate());
					sb.append(0);
					sb.append(i);
					dateList.add(sb.toString());
				}else{
					sb.append(query.getStartDate());
					sb.append(i);
					dateList.add(sb.toString());
				}
			}
			for(int i=0; i<dateList.size(); i++){
				titleLine.put("col" + (i+3), dateList.get(i));
			}
			/*dataList.add(titleLine);*/
			officialListQuery.add(titleLine);
			Page<Map<String, Object>> frontOfficialList = crudeBasePriceService.getFrontOfficialList(dateList,pageInfo,query);
			officialListQuery.addAll(frontOfficialList);
			
			result.setPageNum(frontOfficialList.getPageNum());
			result.setPageSize(frontOfficialList.getPageSize());
			result.setPageCount(frontOfficialList.getPages());
			result.setTotal(frontOfficialList.getTotal());
			result.setDatas(officialListQuery);
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 现货贴水走势油种
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.OILLIST_QUERY)
	public ResultDatas<List<String>> getOilList(@RequestBody CrudeAgioVo vo){
		ResultDatas<List<String>> result = new ResultDatas<>();
		if(StringUtils.isEmpty(vo.getPricingDate())){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			String newDate = sdf.format(new Date());
			vo.setPricingDate(newDate);
		}
		try {
			//获取油种列表
			List<String> dataList = agioService.queryOilList(vo.getPricingDate());
			result.setDatas(dataList);
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 获取近一个月贴水形成折线图用
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.AGIOPOINT_QUERY, method = RequestMethod.POST)
	public ResultDatas<List<CrudePriceRest>> getAgioPointY(@RequestBody CrudeAgioVo vo){
		ResultDatas<List<CrudePriceRest>> result = new ResultDatas<>();
		
		if(StringUtils.isEmpty(vo.getPricingDate())){
			result.setFail("日期不能为空，格式为yyyy-MM");
			return result;
		}
		if(CollectionUtils.isEmpty(vo.getCrudeNameArray()) || vo.getCrudeNameArray().size() > 3){
			result.setFail("名称不能为空，且最多选择3种油种，至少选择一个");
			return result;
		}
		
		try {
			List<CrudePriceRest> datas = agioService.queryPointYear(vo);
			result.setDatas(datas);
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
	

	/**
	 * 官价走势/索引油种
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.OFFICALLIST_QUERY)
	public ResultDatas<List<String>> getOfficalOilList(@RequestBody CrudeBasePriceVo vo){
		ResultDatas<List<String>> result = new ResultDatas<>();
		if(StringUtils.isEmpty(vo.getPricingDate())){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String newDate = sdf.format(new Date());
			vo.setPricingDate(newDate);
		}
	    try {
			//获取油种列表
			List<String> oilList = crudeBasePriceService.queryOfficalOilList(vo.getPricingDate());
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
			result.setDatas(oilList);
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取近一年官价走势/索引形成折线图用
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.OFFICALPOINT_QUERY, method = RequestMethod.POST)
	public ResultDatas<List<CrudePriceRest>> getOfficalPointY(@RequestBody CrudeBasePriceVo vo){
		ResultDatas<List<CrudePriceRest>> result = new ResultDatas<>();

		if(StringUtils.isEmpty(vo.getPricingDate())){
			result.setFail("日期不能为空，格式为yyyy-MM");
			return result;
		}
		if(CollectionUtils.isEmpty(vo.getCrudeNameArray()) || vo.getCrudeNameArray().size() > 3){
			result.setMessage("名称不能为空，且最多为3种油种，至少选择一个");
			return result;
		}
		try {
			List<CrudePriceRest> datas = crudeBasePriceService.queryOfficalYear(vo);
			result.setDatas(datas);
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 根据合约代码和名称查询最新合约价格
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/query/newSymbolPrice.json",method=RequestMethod.POST)
	public Map<String, Object> getNewSymbolPrice(@RequestBody SymbolPriceVO vo){
		MapUtil result=new MapUtil();
		try {
			if(StringUtils.isBlank(vo.getSymbol()))
				throw new Exception("合约代码不能为空！");
			if(StringUtils.isBlank(vo.getSymbolName()))
				throw new Exception("合约名称不能为空！");
			Map<String, Object> map = symbolPriceService.getNewSymbolPrice(vo);
			return result.setOk("查询成功", BeanConvertUtils.mapToBean(map, NewSymbolPriceVO.class));
		} catch (Exception e) {
			log.error("", e);
			result.setNo("查询失败," + e.getMessage());
		}
		return null;
		
	}
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/query/appNewSymbolPrice.json",method=RequestMethod.POST)
	public ResultDatas<NewSymbolPriceVO> getAppNewSymbolPrice(@RequestBody SymbolPriceVO vo){
		ResultDatas<NewSymbolPriceVO> result = new ResultDatas<>();
		try {
			if(StringUtils.isBlank(vo.getSymbol()))
				throw new Exception("合约代码不能为空！");
			if(StringUtils.isBlank(vo.getSymbolName()))
				throw new Exception("合约名称不能为空！");
			Map<String, Object> map = symbolPriceService.getNewSymbolPrice(vo);
			result.setDatas(BeanConvertUtils.mapToBean(map, NewSymbolPriceVO.class));
			result.setStatus(Result.SUCCESS);
			return result;
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.ERROR);
			result.setMessage(e.getMessage());
		}
		return result;
		
	}
}
