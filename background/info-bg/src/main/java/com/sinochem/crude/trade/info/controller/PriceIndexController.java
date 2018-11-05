package com.sinochem.crude.trade.info.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
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

import com.eyeieye.melody.util.DateUtil;
import com.github.pagehelper.Page;
import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.info.domain.PriceIndex;
import com.sinochem.crude.trade.info.model.PriceIndexVO;
import com.sinochem.crude.trade.info.model.PriceIndexVOVO;
import com.sinochem.crude.trade.info.query.PriceExcelQuery;
import com.sinochem.crude.trade.info.query.PriceIndexQuery;
import com.sinochem.crude.trade.info.service.PriceIndexService;
import com.sinochem.crude.trade.info.service.PriceIndexTemplateService;
import com.sinochem.crude.trade.info.util.ExcelCheckUtil;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.xiaoleilu.hutool.collection.CollUtil;
import com.xiaoleilu.hutool.poi.excel.ExcelReader;
import com.xiaoleilu.hutool.poi.excel.ExcelUtil;
import com.xiaoleilu.hutool.poi.excel.ExcelWriter;
import com.xiaoleilu.hutool.util.ClassUtil;

/**
 * 指数
 * 
 * admin - 系统管理员
 * info_oper - 内容维护人员
 */
@Controller
public class PriceIndexController {

	@Autowired
	private PriceIndexService priceIndexService;
	@Autowired
	private PriceIndexTemplateService priceIndexTemplateService;

	public static final Log log = LogFactory.getLog(PriceIndexController.class);
	private DecimalFormat df1 = new DecimalFormat("0.000");    //0.000   百分比格式，后面不足3位的用0补齐
	private DecimalFormat df2 = new DecimalFormat("0.0000");    //0.0000   百分比格式，后面不足4位的用0补齐
	private DecimalFormat df3 = new DecimalFormat("0.0000%");    //00.0000%   百分比格式，后面不足4位的用0补齐

	/**
	 * 指数列表
	 */
	@RightAccess(1074)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = URLMapping.PRICEINDEX_LIST)
	public void list(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain, ModelMap model, PriceIndexQuery query) {
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		if(StringUtils.isBlank(query.getIndexDate()))
			query.setIndexDate(DateUtil.convertDateToString("yyyy-MM-dd", new Date()));
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(query.getPageSize());
		Page<Map<String, Object>> pageList = priceIndexService.queryPriceIndexWithTemp(BeanConvertUtils.beanToMap(query),
				pageInfo);
		//去除id
		for(Map<String, Object> item : pageList.getResult()) {
			item.remove("id");
			String smeiValue=item.get("smeiValue") == null ? null : df1.format((BigDecimal)item.get("smeiValue"));  
			String riseFall = item.get("riseFall") == null ? null : df2.format((BigDecimal)item.get("riseFall"));
			String riseFallRate = item.get("riseFallRate") == null ? null : df3.format(((BigDecimal)item.get("riseFallRate")));
			item.put("smeiValue", smeiValue);
			item.put("riseFall", riseFall); 
			item.put("riseFallRate", riseFallRate);
		}
		query.setTotalItem(pageList.getTotal());
		model.put("priceIndexs", pageList.getResult());
		model.put("query", query);
	}
	
	/**
	 * 新增或更新指数
	 * @return
	 */
	@RightAccess(1079)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value=URLMapping.PRICEINDEX_SAVEORUPDAREPRICEINDEX,method=RequestMethod.POST)
	public Result saveOrUpdatePriceIndex(@RequestBody PriceIndexVOVO vos,CurrentUser user) {
		Result res = new Result();
		res.setMessage("保存指数成功");
		
		try {
			if(vos==null) {
				res.setFail("数据为空");
				return res;
			}
			if(CollectionUtils.isEmpty(vos.getVos())) {
				return res;
			}
			
			for(PriceIndexVO vo : vos.getVos()) {
				PriceIndex target = new PriceIndex();
				BeanUtils.copyProperties(vo, target);
				target.setIndexDate(DateTimeUtils.toDate(vos.getIndexDate()));
				if(target.getRiseFall() == null || target.getSmeiValue() == null) {
					continue;
				}
				target.setIndexTemplateId(priceIndexTemplateService.findByUuid(vo.getTempUuid()).getId());
				target.setRiseFallRate(target.getRiseFallRate().divide(new BigDecimal("100")));
				priceIndexService.saveOrUpdatePriceIndex(target,user);
			}
		} catch (BizException e) {
			log.error("保存指数失败",e);
			res.setFail("保存指数失败");
		}
		
		return res;
	}
	
	/**
	 * 批量重置删除指数
	 * @param uuids
	 * @return
	 */
	@RightAccess(1080)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value=URLMapping.PRICEINDEX_DELETEPRICEINDEXLIST,method=RequestMethod.POST)
	public Result deletePriceIndexList(@RequestBody List<String> uuids) {
		Result res = new Result();
		res.setMessage("重置成功");
		if(!CollectionUtils.isEmpty(uuids)) {
			for(String uuid : uuids) {
				PriceIndex index = priceIndexService.findByUuid(uuid);
				index.setAliveFlag("0");
				try {
					priceIndexService.updateRecordByUuid(index);
				}catch (BizException e) {
					log.error("批量重置删除指数",e);
					res.setFail("批量重置删除指数失败");
				}
			}
		}
		return res;
	}
	
	/**
	 * 导入指数-数据(Excel 文件)  
	 * @return
	 */
	@RightAccess(1076)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value="/price/importExcel.json")
	public Result importExcel(HttpServletRequest request,CurrentUser user) {
		Result result = new Result();
		result.setMessage("导入数据成功");
		MultipartHttpServletRequest  multiRequest = (MultipartHttpServletRequest)request;
		ExcelReader reader = null;
		MultipartFile file = multiRequest.getFile("file");
		try {
			reader = ExcelUtil.getReader(file.getInputStream());
			if(reader == null) {
				result.setFail("数据为空 请检查excel");
				return result;
			}
			List<Map<String,Object>> list = reader.readAll();
			Map<String,Long> map = new HashMap<>();
			List<Map<String,Object>> tempList = priceIndexTemplateService.findAll();
			for(Map<String,Object> item : tempList) {
				String tempName = (String)item.get("indexName");
				Long id = priceIndexTemplateService.findByName(tempName);
				if(id==null)
					continue;
				map.put(tempName, id);
			}
			
			int count = 2;
			for(Map<String,Object> item : list) {
				String tempName = (String)item.get("指数名称");
				Map<String,Object> beanMap = checkExcelRow(item,result,count);
				if(result.getStatus() == 9999) {
					return result;
				}
				PriceIndex target = new PriceIndex();
				target.setIndexTemplateId(map.get(tempName));
				target.setIndexDate((Date)beanMap.get("indexDate"));
				List<PriceIndex> args = priceIndexService.queryByEntitys(target);
				if(!CollectionUtils.isEmpty(args)) {
					//重复数据则为 更新
					target.setUuid(args.get(0).getUuid());
				}
				target.setSmeiValue((BigDecimal)beanMap.get("smeiValue"));
				target.setRiseFall((BigDecimal)beanMap.get("riseFall"));
				target.setRiseFallRate((BigDecimal)beanMap.get("riseFallRate"));
				target.setIndexDate((Date)beanMap.get("indexDate"));
				target.setOpen(new BigDecimal(String.valueOf(beanMap.get("open"))));
				target.setHigh(new BigDecimal(String.valueOf(beanMap.get("height"))));
				target.setLow(new BigDecimal(String.valueOf(beanMap.get("low"))));
				target.setPreClose(new BigDecimal(String.valueOf(beanMap.get("preClose"))));
				target.setPreRiseFall(new BigDecimal(String.valueOf(beanMap.get("preRiseFall"))));
				target.setPreRiseFallRate(new BigDecimal(String.valueOf(beanMap.get("preRiseFallRate"))));
				target.setRemark((String)beanMap.get("remark"));
				priceIndexService.saveOrUpdatePriceIndex(target,user);
				++count;
			}
			
		} catch (IOException e) {
			log.error("指数excel 文件导入失败 ");
			result.setFail("指数excel 文件导入失败");
		}finally {
			if(reader != null) {
				reader.close();
			}
		}
		return result;
	}
	
	private Map<String,Object> checkExcelRow(Map<String,Object> item,Result result, int count) {
		Map<String,Object> map = new HashMap<>();
		if(!ExcelCheckUtil.isNotEmptyNumeric(String.valueOf(item.get("指数值")))) {
			result.setFail("第" + (count -1) + "行之前数据导入成功，" + "第"+(count)+"行：指数名称为空 之后导入失败");
		}
		if(!ExcelCheckUtil.isNotEmptyNumeric(String.valueOf(item.get("涨跌*")))) {
			result.setFail("第" + (count -1) + "行之前数据导入成功，" + "第"+(count)+"行：涨跌*为空 之后导入失败");
		}
		if(!ExcelCheckUtil.isNotEmptyNumeric(String.valueOf(item.get("涨跌幅")))) {
			result.setFail("第" + (count -1) + "行之前数据导入成功，" + "第"+(count)+"行：涨跌幅为空或格式不正确 之后导入失败");
		}
		if(!ExcelCheckUtil.isNotEmptyDate(String.valueOf(item.get("指数日期")))) {
			result.setFail("第" + (count -1) + "行之前数据导入成功，" + "第"+(count)+"行：指数日期为空或格式不正确 之后导入失败");
		}
		map.put("smeiValue", new BigDecimal(String.valueOf(item.get("指数值"))));
		map.put("riseFall", new BigDecimal(String.valueOf(item.get("涨跌*"))));
		map.put("riseFallRate", new BigDecimal(String.valueOf(item.get("涨跌幅"))));
		map.put("indexDate", DateTimeUtils.toDate(String.valueOf(item.get("指数日期")), "yyyy-MM-dd"));
		map.put("open", !ExcelCheckUtil.isNotEmptyStr(item.get("开盘")) ? "0" : String.valueOf(item.get("开盘")));
		map.put("height", !ExcelCheckUtil.isNotEmptyStr(item.get("最高"))  ? "0" : String.valueOf(item.get("最高")));
		map.put("low", !ExcelCheckUtil.isNotEmptyStr(item.get("最低")) ? "0" : String.valueOf(item.get("最低")));
		map.put("preClose", !ExcelCheckUtil.isNotEmptyStr(item.get("昨收")) ? "0" : String.valueOf(item.get("昨收")));
		map.put("preRiseFall", !ExcelCheckUtil.isNotEmptyStr(item.get("昨收涨跌")) ? "0" : String.valueOf(item.get("昨收涨跌")));
		map.put("preRiseFallRate", !ExcelCheckUtil.isNotEmptyStr(item.get("昨收涨跌率"))  ? "0" : String.valueOf(item.get("昨收涨跌率")));
		map.put("remark", (String)item.get("备注"));
		return map;
		
	}
	
	/**
	 * 导出指数-数据(Excel 文件)  
	 * @return
	 */
	@RightAccess(1077)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value="/om/price/exportExcel.htm")
	public void exportExcel(HttpServletResponse response, PriceExcelQuery query) {
		ExcelWriter writer = ExcelUtil.getWriter(ClassUtil.getClassPath() + "/excel/priceIndex.xls");
		if(StringUtils.isBlank(query.getEndTime()) || StringUtils.isBlank(query.getStartTime())) {
			return;
		}
		List<Map<String,Object>> list = priceIndexService.queryByStartAndEndDate(query);
		List<List<String>> rows = new ArrayList<>();
		List<String> headRow =CollUtil.newArrayList("指数名称", "指数值", "指数日期", "涨跌*", "涨跌幅", "开盘", "最高", "最低", "昨收", "昨收涨跌", "昨收涨跌率", "备注");
		for(Map<String,Object> item : list) {
			
			String tempName = item.get("INDEX_NAME") == null || item.get("INDEX_NAME") == "" ? null : item.get("INDEX_NAME").toString() ;
			String smeiValue = item.get("SMEI_VALUE") == null || item.get("SMEI_VALUE") == "" ? null : item.get("SMEI_VALUE").toString() ;
			String riseFall = item.get("RISE_FALL") == null || item.get("RISE_FALL") == "" ? null : item.get("RISE_FALL").toString() ;
			String riseFallRate = item.get("RISE_FALL_RATE") == null || item.get("RISE_FALL_RATE") == "" ? null : item.get("RISE_FALL_RATE").toString() ;
			String indexDate = item.get("INDEX_DATE") == null || item.get("INDEX_DATE") == "" ? null : ((Date)item.get("INDEX_DATE")).toString();
			String open = item.get("OPEN") == null || item.get("OPEN") == "" ? "0" : item.get("OPEN").toString() ;
			String height = item.get("HIGH") == null || item.get("HIGH") == "" ? "0" : item.get("HIGH").toString() ;
			String low = item.get("LOW") == null || item.get("LOW") == "" ? "0" : item.get("LOW").toString() ;
			String preClose = item.get("PRE_CLOSE") == null || item.get("PRE_CLOSE") == "" ? "0" : item.get("PRE_CLOSE").toString() ;
			String preRiseFall = item.get("PRE_RISE_FALL") == null || item.get("PRE_RISE_FALL") == "" ? "0" : item.get("PRE_RISE_FALL").toString() ;
			String preRiseFallRate = item.get("PRE_RISE_FALL_RATE") == null || item.get("PRE_RISE_FALL_RATE") == "" ? "0" : item.get("PRE_RISE_FALL_RATE").toString() ;
			rows.add(CollUtil.newArrayList(
					tempName, 
					smeiValue, 
					indexDate, 
					riseFall,
					riseFallRate,
					open,
					height,
					low,
					preClose,
					preRiseFall,
					preRiseFallRate,
					(String)item.get("REMARK")));
		}
		String fileName = query.getStartTime() + "到" + query.getEndTime() + "时间内容所有有效指数数据";
		//response.reset();// 清空输出流   
		// 不同类型的文件对应不同的MIME类型
		response.setContentType("application/x-msdownload");
		// inline在浏览器中直接显示，不提示用户下载
		// attachment弹出对话框，提示用户进行下载保存本地
		// 默认为inline方式
		try {
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes(),"iso-8859-1") + ".xls");
		} catch (UnsupportedEncodingException e1) {
		}
		response.setCharacterEncoding("utf-8");
		// 设置这些样式
		writer.getHeadCellStyle().setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		writer.getHeadCellStyle().setFillPattern(FillPatternType.SOLID_FOREGROUND);
		writer.getHeadCellStyle().setBorderBottom(BorderStyle.THIN);
		writer.getHeadCellStyle().setBorderLeft(BorderStyle.THIN);
		writer.getHeadCellStyle().setBorderRight(BorderStyle.THIN);
		writer.getHeadCellStyle().setBorderTop(BorderStyle.THIN);
		writer.getHeadCellStyle().setAlignment(HorizontalAlignment.CENTER);
		// 生成一个字体
		Font font = writer.getWorkbook().createFont();
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		writer.writeHeadRow(headRow);
		writer.write(rows);
		ServletOutputStream os = null;
		try {
			os = response.getOutputStream();
			writer.flush(os);
		} catch (IOException e) {
			log.error("导出指数-数据(Excel 文件) 失败");
		}finally{
			try {
				if(os != null) {
					os.close();
				}
				writer.close();
			} catch (IOException e) {
				log.error("导出指数-数据(Excel 文件) 失败");
			}
			
		}
	}
	
	
}
