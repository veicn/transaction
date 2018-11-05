package com.sinochem.crude.trade.info.controller;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aliyun.opensearch.sdk.dependencies.org.apache.commons.lang.StringUtils;
import com.eyeieye.melody.util.DateUtil;
import com.github.pagehelper.Page;
import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.info.domain.SymbolPrice;
import com.sinochem.crude.trade.info.model.SymbolPriceVO;
import com.sinochem.crude.trade.info.query.SymbolPriceQuery;
import com.sinochem.crude.trade.info.service.SymbolPriceService;
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
public class SymbolPriceController  {

	@Autowired
	private SymbolPriceService symbolPriceService;
	
	public static final Log log = LogFactory.getLog(SymbolPriceController.class);
	
	/**
	 * 合约列表
	 */
	@RightAccess(1100)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = URLMapping.SYMBOLPRICEINDEX_LIST)
	public void list(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain, ModelMap model, SymbolPriceQuery query) {
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		if(StringUtils.isBlank(query.getTradeDate()))
			query.setTradeDate(DateUtil.convertDateToString("yyyy-MM-dd", new Date()));
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(query.getPageSize());
		Page<Map<String, Object>> pageList = symbolPriceService.querySymbolPriceIndex(BeanConvertUtils.beanToMap(query),
				pageInfo);
		//去除id
		/*for(Map<String, Object> item : pageList.getResult()) {
			item.remove("id");
			String smeiValue=item.get("smeiValue") == null ? null : df1.format((BigDecimal)item.get("smeiValue"));  
			String riseFall = item.get("riseFall") == null ? null : df2.format((BigDecimal)item.get("riseFall"));
			String riseFallRate = item.get("riseFallRate") == null ? null : df3.format(((BigDecimal)item.get("riseFallRate")).divide(new BigDecimal("100")));
			item.put("smeiValue", smeiValue);
			item.put("riseFall", riseFall); 
			item.put("riseFallRate", riseFallRate);
		}*/
		query.setTotalItem(pageList.getTotal());
		model.put("symbolPrice", pageList.getResult());
		model.put("query", query);
	}
	/**
	 * 新增或更新合约
	 * @return
	 */
	@RightAccess(1104)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value=URLMapping.SYMBOLPRICEINDEX_SAVEORUPDATE,method=RequestMethod.POST)
	public Result saveOrUpdateSymbolPrice(@RequestBody SymbolPriceVO vos,CurrentUser user) {
		Result res = new Result();
		res.setMessage("保存合约成功");
		if(vos != null) {
			if(!CollectionUtils.isEmpty(vos.getVos())) {
				for(SymbolPrice vo : vos.getVos()) {
					
					SymbolPrice target = new SymbolPrice();
					BeanUtils.copyProperties(vo, target);
					target.setTradeDate(DateTimeUtils.toDate(vos.getTradeDate()));
					try {
						symbolPriceService.saveOrUpdateSymbolPrice(target,user);
					}catch (BizException e) {
						log.error("保存合约失败",e);
						res.setFail("保存合约失败");
					}
				}
			}
		}
		return res;
	}
	/**
	 * 批量重置/删除合约
	 * @return
	 */
	@RightAccess(1105)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value=URLMapping.SYMBOLPRICEINDEX_DELETE,method=RequestMethod.POST)
	public Result deleteSymbolPrice(@RequestBody List<String> uuids,CurrentUser user){
		Result res = new Result();
		res.setMessage("重置成功");
		if(!CollectionUtils.isEmpty(uuids)) {
			for(String uuid : uuids) {
				SymbolPrice price = new SymbolPrice();
				price.setAliveFlag("0");
				price.setUuid(uuid);
				price.setUpdateUser(user.getName());
				try {
					symbolPriceService.updateRecordByUuid(price);
				}catch (BizException e) {
					log.error("批量重置删除合约",e);
					res.setFail("批量重置删除合约失败");
				}
			}
		}
		return res;
		
	}
	/**
	 * 外部查询接口
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/query/symbolPrice.json",method=RequestMethod.POST)
	public Map<String, Object> getSymbolPrice(@RequestBody SymbolPriceVO vo){
		MapUtil result=new MapUtil();
		try {
			checkParam(vo);
			return result.setOk("查询成功", symbolPriceService.getSymbolPrice(vo));
		} catch (Exception e) {
			log.error("", e);
			result.setNo("查询失败," + e.getMessage());
		}
		return null;
		
	}
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/query/appSymbolPrice.json",method=RequestMethod.POST)
	public ResultDatas<List<Map<String,Object>>> getAppSymbolPrice(@RequestBody SymbolPriceVO vo){
		ResultDatas<List<Map<String,Object>>> result = new ResultDatas<>();
		try {
			checkParam(vo);
			result.setDatas(symbolPriceService.getSymbolPrice(vo));
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
	void checkParam(SymbolPriceVO vo) throws Exception{
		if(StringUtils.isBlank(vo.getTradeDateStart()))
			throw new Exception("价格起始日不能为空！");
		if(StringUtils.isBlank(vo.getTradeDateEnd()))
			throw new Exception("价格结束日不能为空！");
	}
	/**
     * 导出excel 文件
     * @param response
     */
	@RightAccess(1102)
	@RolesAccess({"admin","info_oper"})
    @RequestMapping(value = "/symbol/exportList.htm")
    public void exportList(HttpServletResponse response,SymbolPriceVO vo){
    	if(StringUtils.isBlank(vo.getTradeDate())){
    		vo.setTradeDate(DateUtil.convertDateToString("yyyy-MM-dd", new Date()));
    	}
    	symbolPriceService.exportList(response,vo);
 	}
    /**
	 * 批量导入
	 * @param request
	 * @return
	 */
    @RightAccess(1101)
    @RolesAccess({"admin","info_oper"})
	@RequestMapping(value = "/symbol/importExcel.json", method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<Void> crudeOilImport(CurrentUser user, HttpServletRequest request) {
		ResultDatas<Void> res = new ResultDatas<Void>();
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multiRequest.getFile("file");
		try {
			String fileName = file.getOriginalFilename();
			if(!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")){
				res.setMessage("文件格式不正确，请选择excel");
				res.setStatus(Result.EEROR);
				return res;
			}

			InputStream inputStream = file.getInputStream();
			res.setMessage("上传成功");
			res = symbolPriceService.crudeOilImport(inputStream,user);
			
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			log.error(e);
		}
		
		return res;
	}
}
