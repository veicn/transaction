package com.sinochem.crude.trade.info.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.info.domain.Symbol;
import com.sinochem.crude.trade.info.model.SymbolVO;
import com.sinochem.crude.trade.info.query.SymbolIndexQuery;
import com.sinochem.crude.trade.info.result.CrudePriceRest;
import com.sinochem.crude.trade.info.service.SymbolService;
import com.sinochem.crude.trade.info.util.MapUtil;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.member.access.ApiSafeAccess;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;

/**
 * admin - 系统管理员
 * info_oper - 内容维护人员
 */
@Controller
public class SymbolController  {

	@Autowired
	private SymbolService symbolService;
	
	public static final Log log = LogFactory.getLog(SymbolController.class);
	
	
	//返回结果
		class Ret extends HashMap<String,Object>{
			public Ret() {
				this.put("status", "0");
				this.put("message", "");
				this.put("dataList", null);
			}
			public Ret setNo(String message) {
				this.put("status", "0");
				this.put("message", message);
				this.put("dataList", null);
				return this;
			}
			public Ret setOk(String message,Object dataList) {
				this.put("status", "1");
				this.put("message", message);
				this.put("dataList", dataList);
				return this;
			}
			public Ret appSetOk(String message,Object dataList) {
				this.put("status", "1");
				this.put("message", message);
				this.put("datas", dataList);
				return this;
			}
			public Ret appSetNo(String message) {
				this.put("status", "0");
				this.put("message", message);
				this.put("datas", null);
				return this;
			}
		}
	/**
	 * 合约列表
	 */
	@RightAccess(1097)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = URLMapping.SYMBOLINDEX_LIST)
	public void list(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain, ModelMap model, SymbolIndexQuery query) {
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(query.getPageSize());
		Page<Map<String, Object>> pageList = symbolService.queryRecords(BeanConvertUtils.beanToMap(query),
				pageInfo);
		query.setTotalItem(pageList.getTotal());
		model.put("symbolDates", pageList.getResult());
		model.put("query", query);
	}
	/**
	 * 新增或更新合约
	 * @return
	 */
	@RightAccess(1098)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value=URLMapping.SYMBOLINDEX_SAVEORUPDARESYMBOLINDEX,method=RequestMethod.POST)
	public Result saveOrUpdateSymbol(@RequestBody SymbolVO vo,CurrentUser user) {
		Result res = new Result();
		res.setMessage("合约新增成功");
		if(StringUtils.isBlank(vo.getSymbolName())) {
			res.setFail("合约名称不为空");
			return res;
		}
		try {
			Symbol symbol = new Symbol();
			BeanUtils.copyProperties(vo, symbol);
			Integer sym=symbolService.querySymbol(symbol);
			if(sym == 1){
				res.setFail(symbol.getSymbol() +"合约代码重复");
				return res;
			}
			return symbolService.saveOrUpdateSymbol(symbol,user);
		}catch (BizException e) {
			log.error("新增合约失败", e);
			res.setFail(e.getMessage());
		}
		return res;
	}
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/query/symbol.json",method=RequestMethod.POST)
	public Map<String, Object> getSymbol(@RequestBody SymbolVO vo){
		Ret result = new Ret();
		try {
			//checkParam(vo);
			return result.setOk("查询成功", symbolService.getSymbol(vo));
		} catch (Exception e) {
			log.error("", e);
			result.setNo("查询失败," + e.getMessage());
		}
		return result;
		
	}
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/query/appSymbol.json",method=RequestMethod.POST)
	public ResultDatas<List<Map<String, Object>>> getAppSymbol(@RequestBody SymbolVO vo){
		ResultDatas<List<Map<String, Object>>> result = new ResultDatas<>();
		try {
			//checkParam(vo);
			vo.setExtend2("1");
			result.setStatus(Result.SUCCESS);
			result.setDatas(symbolService.getSymbol(vo));
			return result;
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.EEROR);
			result.setMessage(e.getMessage());
		}
		return result;
		
	}
	
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/query/commodity.json",method=RequestMethod.GET)
	public Map<String, Object> getCommodityList(@RequestParam(value = "priceSource", required = false) String priceSource,
			@RequestParam(value = "market", required = false) String market
			,@RequestParam(value = "extend2", required = false) String extend2){
		MapUtil result = new MapUtil();
		try {
			Map<String, Object> map = new HashMap<>();
			if(StringUtils.isNotBlank(priceSource))
				map.put("priceSource", priceSource);
			if(StringUtils.isNotBlank(market))
				map.put("market", market);
			if(StringUtils.isNotBlank(extend2))
				map.put("extend2", extend2);
			return result.setOk("查询成功", symbolService.getCommodityList(map));
		} catch (Exception e) {
			log.error("", e);
			result.setNo("查询失败," + e.getMessage());
		}
		return result;
	}
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/query/appCommodity.json",method=RequestMethod.GET)
	public ResultDatas<List<String>> getAppCommodityList(@RequestParam(value = "priceSource", required = false) String priceSource,
			@RequestParam(value = "market", required = false) String market
			,@RequestParam(value = "extend2", required = false) String extend2){
		ResultDatas<List<String>> result = new ResultDatas<>();
		try {
			Map<String, Object> map = new HashMap<>();	
			if(StringUtils.isNotBlank(priceSource))
				map.put("priceSource", priceSource);
			if(StringUtils.isNotBlank(market))
				map.put("market", market);
			if(StringUtils.isNotBlank(extend2))
				map.put("extend2", extend2);
			result.setDatas(symbolService.getCommodityList(map));
			result.setStatus(Result.SUCCESS);
			return result;
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.EEROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/query/priceSource.json",method=RequestMethod.GET)
	public Map<String, Object> getPriceSource(@RequestParam(value = "commodity", required = false) String commodity,
			@RequestParam(value = "market", required = false) String market
			,@RequestParam(value = "extend2", required = false) String extend2){
		MapUtil result = new MapUtil();
		try {
			Map<String, Object> map = new HashMap<>();
			if(StringUtils.isNotBlank(commodity))
				map.put("commodity", commodity);
			if(StringUtils.isNotBlank(market))
				map.put("market", market);
			if(StringUtils.isNotBlank(extend2))
				map.put("extend2", extend2);
			return result.setOk("查询成功", symbolService.getPriceSourceList(map));
		} catch (Exception e) {
			log.error("", e);
			result.setNo("查询失败," + e.getMessage());
		}
		return result;
		
	}
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/query/appPriceSource.json",method=RequestMethod.GET)
	public ResultDatas<List<String>> getAppPriceSource(@RequestParam(value = "commodity", required = false) String commodity,
			@RequestParam(value = "market", required = false) String market
			,@RequestParam(value = "extend2", required = false) String extend2){
		ResultDatas<List<String>> result = new ResultDatas<>();
		try {
			Map<String, Object> map = new HashMap<>();
			if(StringUtils.isNotBlank(commodity))
				map.put("commodity", commodity);
			if(StringUtils.isNotBlank(market))
				map.put("market", market);
			if(StringUtils.isNotBlank(extend2))
				map.put("extend2", extend2);
			result.setDatas(symbolService.getPriceSourceList(map));
			result.setStatus(Result.SUCCESS);
			return result;
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.EEROR);
			result.setMessage(e.getMessage());
		}
		return result;
		
	}
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/query/market.json",method=RequestMethod.GET)
	public Map<String, Object> getMarket(@RequestParam(value = "commodity", required = false) String commodity,
			@RequestParam(value = "priceSource", required = false) String priceSource
			,@RequestParam(value = "extend2", required = false) String extend2){
		MapUtil result = new MapUtil();
		try {
			Map<String, Object> map = new HashMap<>();
			if(StringUtils.isNotBlank(commodity))
				map.put("commodity", commodity);
			if(StringUtils.isNotBlank(priceSource))
				map.put("priceSource", priceSource);
			if(StringUtils.isNotBlank(extend2))
				map.put("extend2", extend2);
			return result.setOk("查询成功", symbolService.getMarketList(map));
		} catch (Exception e) {
			log.error("", e);
			result.setNo("查询失败," + e.getMessage());
		}
		return result;
		
	}
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/query/appMarket.json",method=RequestMethod.GET)
	public ResultDatas<List<String>> getAppMarket(@RequestParam(value = "commodity", required = false) String commodity,
			@RequestParam(value = "priceSource", required = false) String priceSource
			,@RequestParam(value = "extend2", required = false) String extend2){
		ResultDatas<List<String>> result = new ResultDatas<>();
		try {
			Map<String, Object> map = new HashMap<>();
			if(StringUtils.isNotBlank(commodity))
				map.put("commodity", commodity);
			if(StringUtils.isNotBlank(priceSource))
				map.put("priceSource", priceSource);
			if(StringUtils.isNotBlank(extend2))
				map.put("extend2", extend2);
			result.setDatas(symbolService.getMarketList(map));
			result.setStatus(Result.SUCCESS);
			return result;
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.EEROR);
			result.setMessage(e.getMessage());
		}
		return result;
		
	}
	
	/**
	 * 校验参数
	 * @param vo
	 * @throws Exception
	 */
	void checkParam(SymbolVO vo) throws Exception{
		if(StringUtils.isBlank(vo.getCommodity()))
			throw new Exception("商品标的不能为空！");
		if(StringUtils.isBlank(vo.getMarket()))
			throw new Exception("市场不能为空！");
		if(StringUtils.isBlank(vo.getPriceSource()))
			throw new Exception("价格来源不能为空！");
	}
}
