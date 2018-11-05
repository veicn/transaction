package com.sinochem.crude.trade.info.controller;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.aliyun.opensearch.sdk.dependencies.org.apache.commons.lang.StringUtils;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.sinochem.crude.trade.common.datafeed.DataFeedUtils;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.HttpUtils;
import com.sinochem.crude.trade.commons.exceptions.OperationException;
import com.sinochem.crude.trade.info.model.TicksVo;
import com.sinochem.crude.trade.info.query.DataFeedQuery;
import com.sinochem.crude.trade.info.util.MapUtil;
import com.sinochem.crude.trade.values.domain.SysCodeSet;
import com.sinochem.crude.trade.values.service.SysCodeSetService;
import com.sinochem.crude.trade.values.vo.CodeItemResult;
import com.sinochem.it.b2b.member.access.WithoutAccess;

@Controller
public class DataFeedController {
	
	public static final Log log = LogFactory.getLog(SymbolPriceController.class);
	@Autowired
	private SysCodeSetService codeSetService;
	@Value("${dataFeed.ticks.url}")  
	private String ticksUrl;  
	@Value("${dataFeed.newsids.url}")  
	private String newsidsUrl;  
	@Value("${dataFeed.newsc.url}")  
	private String newscUrl;
	/**
	 * 行情的历史数据（日K线）
	 * @return
	 * @throws Exception 
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/sinochem/ticks.json",method=RequestMethod.POST)
	public String ticks(@RequestBody TicksVo vo) throws Exception{
		checkParam(vo,1);
		StringBuilder url=new StringBuilder(ticksUrl);
		url.append(vo.getId());
		url.append("&sp=");
		url.append(vo.getSp());
		url.append("&ep=");
		url.append(vo.getEp());
		
		String sendGet = HttpUtils.sendGet(url.toString(), "UTF-8");
		return sendGet;
		
	}
	/**
	 * 根据type获取id集合
	 * @return
	 * @throws Exception 
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/sinochem/newsids.json",method=RequestMethod.POST)
	public String newsids(TicksVo vo) throws Exception{
		checkParam(vo,2);
		StringBuilder url=new StringBuilder(newsidsUrl);
		url.append(vo.getKey());
		String sendGet = HttpUtils.sendGet(url.toString(), null);
		return sendGet;
		
	}
	/**
	 * 根据id获取资讯内容
	 * @return
	 * @throws Exception 
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/sinochem/newsc.json",method=RequestMethod.POST)
	public MapUtil newsc(TicksVo vo) throws Exception{
		checkParam(vo,3);
		MapUtil result=new MapUtil();
		StringBuilder url=new StringBuilder(newscUrl);
		url.append(vo.getId());
		String sendGet = HttpUtils.newscGet(url.toString(), null);
		String replace = sendGet.replace("&", "&amp;").replace("\\", "\\\\").replace("\'", "&#39;").replace("\"", "&quot;");
		JSONObject jsonObject = XML.toJSONObject(replace).getJSONObject("newsc");
		String t = jsonObject.get("t").toString();
		String title = t.substring(1, t.length());
		Map<String,Object> map=new HashMap<>();
		map.put("date", jsonObject.get("date"));
		map.put("time", jsonObject.get("time"));
		map.put("title", title);
		map.put("c", jsonObject.get("c"));
		return result.setOk("查询成功",map);
		
	}
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/sinochem/appNewsc.json",method=RequestMethod.POST)
	public ResultDatas<Map<String,Object>> appNewsc(TicksVo vo) throws Exception{
		checkParam(vo,3);
		ResultDatas<Map<String,Object>> result = new ResultDatas<>();
		StringBuilder url=new StringBuilder(newscUrl);
		url.append(vo.getId());
		String sendGet = HttpUtils.newscGet(url.toString(), null);
		String replace = sendGet.replace("&", "&amp;").replace("\\", "\\\\").replace("\'", "&#39;").replace("\"", "&quot;");
		JSONObject jsonObject = XML.toJSONObject(replace).getJSONObject("newsc");
		String t = jsonObject.get("t").toString();
		String title = t.substring(1, t.length());
		Map<String,Object> map=new HashMap<>();
		map.put("date", jsonObject.get("date"));
		map.put("time", jsonObject.get("time"));
		map.put("title", title);
		map.put("c", jsonObject.get("c"));
		result.setDatas(map);
		result.setStatus(Result.SUCCESS);
		return result;
		
	}
	/**
	 * 校验参数
	 * @param vo
	 * @throws Exception
	 */
	void checkParam(TicksVo vo,Integer type) throws Exception{
		if(type==1){
			if(StringUtils.isBlank(vo.getId()))
				throw new Exception("id不能为空！");
			if(StringUtils.isBlank(vo.getSp()))
				throw new Exception("起始日期不能为空！");
			if(StringUtils.isBlank(vo.getEp()))
				throw new Exception("结束日期不能为空！");
		}else if(type == 2){
			if(StringUtils.isBlank(vo.getKey()))
				throw new Exception("资讯分类标识不能为空！");
		}else if(type == 3){
			if(StringUtils.isBlank(vo.getId()))
				throw new Exception("id不能为空！");
		}
	}
	@WithoutAccess
	@RequestMapping(value = "/sinochem/queryDataList.json")
	@ResponseBody
	public ResultDatas<List<Map<String,String>>> queryDataList(DataFeedQuery query) {

		ResultDatas<List<Map<String,String>>> result = new ResultDatas<>();
		if ((query.getId()==null || query.getId() =="") && (query.getSetCodes()==null)) {
			result.setFail("");
			return result;
		}
		
		List<Map<String,String>> dataList=new ArrayList<Map<String,String>>();
		SysCodeSet queryEntity = new SysCodeSet();
		try {
			if(!StringUtils.isEmpty(query.getId())){
				dataList.add(DataFeedUtils.getItem(query.getId()));
			}else{
				for (String setCode : query.getSetCodes()) {
					queryEntity.setSetCode(setCode);
					
					List<SysCodeSet> items;
					try {
						items = codeSetService.queryCodeItems(queryEntity);
					} catch (Exception e) {
						throw new OperationException(OperationException.TYPE.OPBA009, e);
					}
					
					List<CodeItemResult> ids = Lists.transform(items,
							new Function<SysCodeSet, CodeItemResult>() {
						@Override
						public CodeItemResult apply(SysCodeSet sysCodeSet) {
							return entityToItem(sysCodeSet);
						}
					});
					for (int i = 0; i < ids.size(); i++) {
						dataList.add(DataFeedUtils.getItem(ids.get(i).getItemCode()));
					}
				}
			}
			result.setDatas(dataList);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	private CodeItemResult entityToItem(SysCodeSet entity) {

		CodeItemResult itemResult = new CodeItemResult();
		String isEditStr = entity.getIsEdit();
		boolean isEdit;
		isEdit = "1".equals(isEditStr);
		itemResult.setEditable(isEdit);
		itemResult.setItemCode(entity.getItemCode());
		itemResult.setItemName(entity.getItemName());
		itemResult.setItemSort(entity.getItemSort());
		itemResult.setId(entity.getId());
		itemResult.setModule(entity.getModelCode());

		return itemResult;
	}
}
