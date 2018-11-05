package com.sinochem.crude.trade.info.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.info.domain.MesticProductPrice;
import com.sinochem.crude.trade.info.query.DomesticPriceQuery;
import com.sinochem.crude.trade.info.service.MesticProductPriceService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;

/**
 * admin - 系统管理员
 * info_oper - 内容维护人员
 */
@Controller
public class MesticProductPriceController  {

	@Autowired
	private MesticProductPriceService mesticProductPriceService;
	
	public static final Log log = LogFactory.getLog(MesticProductPriceController.class);
	
	/**
	 * 原油价格查询
	 */
	@RightAccess(1090)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = "/om/domesticPrice/domesticPrice.htm")
	public void selectListDomesticPrice(ModelMap model, DomesticPriceQuery query,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain)throws BizException{
		
		Result result = new Result();
		
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}	
		// 查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("selProductName", query.getSelProductName());

		// 分页条件
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageSize(query.getPageSize());
		pageInfo.setPageNum(query.getCurrentPage());
		try {
			// 查询
			Page<Map<String, Object>> datas = mesticProductPriceService.queryRecords(map, pageInfo);
			query.setTotalItem(datas.getTotal());
			model.put("mestics", datas.getResult());
			model.put("query", query);
			
		} catch (Exception e) {
			result.setStatus(4);
			result.setMessage("查询失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 原油价格增加
	 */
	@RightAccess(1091)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value="/mesticProduct/mesticProductAdd.json",method = RequestMethod.POST)
	public Result insertMesticProductPrice(@RequestBody MesticProductPrice mesticproductprice,CurrentUser user) throws BizException{
		
		Result result = new Result();
		
		// 增加
			try {
				
				List<MesticProductPrice> keyFlag = mesticProductPriceService.queryByEntitys(mesticproductprice);
				if(keyFlag.size() == 0) {
					mesticProductPriceService.insertRecord(mesticproductprice);
				} else {
					result.setStatus(9);
					result.setMessage("已经登陆过了");
				}
				
			} catch (Exception e) {
				result.setStatus(9);
				result.setMessage("新增失败");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return result;
	}
	
	/**
	 * 原油价格修改
	 */
	@RightAccess(1095)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value="/mesticProduct/mesticProductUpdate.json",method = RequestMethod.POST)
	public Result updateMesticProductPrice(@RequestBody MesticProductPrice mesticproductprice) throws BizException{
		
		Result result = new Result();
		
		// 增加
		try {
			mesticProductPriceService.updateRecords(mesticproductprice);
		} catch (Exception e) {
			result.setStatus(9);
			result.setMessage("编辑失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 原油价格删除
	 */
	@RightAccess(1096)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value="/mesticProduct/mesticProductDelete.json",method = RequestMethod.GET)
	public Result deleteCrudeOil(@RequestParam(value="uuid") String uuid) throws BizException{
		
		Result result = new Result();
		
		try {
			mesticProductPriceService.deleteRecords(uuid);	
		} catch (Exception e) {
			result.setStatus(9);
			result.setMessage("删除失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 批量导入
	 * @param request
	 * @return
	 */
	@RightAccess(1092)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = "/mesticProduct/mesticProductDaoru.json", method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<Void> senWdImport(CurrentUser user, HttpServletRequest request) {
		ResultDatas<Void> res = new ResultDatas<Void>();
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multiRequest.getFile("file");
		try {
			String fileName = file.getOriginalFilename();
			if(!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")){
				res.setFail("导入失败,文件格式不正确(xls或xlsx)");
				return res;
			}
			InputStream inputStream = file.getInputStream();
			res = mesticProductPriceService.senWdImport(inputStream,user);
		} catch (Exception e) {
			res.setFail(e.getMessage());
			log.error(e);
		}
		
		return res;
	}
	
	/**
	 * 第三方流水导出 组装excel
	 * @param fundPaymentOrderIn
	 * @param isThird
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RightAccess(1093)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = "/mesticProduct/mesticProductExport.json")
	@ResponseBody
	public XSSFWorkbook exportLocal(HttpServletResponse response) {
		// 声明一个工作薄
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 生成一个表格
		XSSFSheet sheet = null;
			sheet = workbook.createSheet("原油价格");
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 30);
		// 生成一个样式
		XSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setAlignment(HorizontalAlignment.CENTER);
		// 生成一个字体
		XSSFFont font = workbook.createFont();
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		
		
		
		// 把字体应用到当前的样式
		style.setFont(font);
		// 产生表格标题行
		XSSFRow row1 = sheet.createRow(0);
		XSSFCell cell0 = row1.createCell(0);
		XSSFCell cell1 = row1.createCell(1);
		XSSFCell cell2 = row1.createCell(2);
		XSSFCell cell3 = row1.createCell(3);
		XSSFCell cell4 = row1.createCell(4);
		XSSFCell cell5 = row1.createCell(5);
		XSSFCell cell6 = row1.createCell(6);
		XSSFCell cell7 = row1.createCell(7);
		XSSFCell cell8 = row1.createCell(8);
		XSSFCell cell9 = row1.createCell(9);
		
		cell0.setCellStyle(style);
		cell1.setCellStyle(style);
		cell2.setCellStyle(style);
		cell3.setCellStyle(style);
		cell4.setCellStyle(style);
		cell5.setCellStyle(style);
		cell6.setCellStyle(style);
		cell7.setCellStyle(style);
		cell8.setCellStyle(style);
		cell9.setCellStyle(style);
		
		cell0.setCellValue("产品名称");
		cell1.setCellValue("最低价格");
		cell2.setCellValue("最高价格");
		cell3.setCellValue("价格单位");
		cell4.setCellValue("价格日期");
		cell5.setCellValue("区域名称");
		cell6.setCellValue("价格来源");
		cell7.setCellValue("价格趋势");
		cell8.setCellValue("最低价格变动");
		cell9.setCellValue("最高价格变动");
		
		// 日期格式
		XSSFCellStyle cellStyle1 = workbook.createCellStyle();
		cellStyle1.setDataFormat(workbook.createDataFormat().getFormat("yyyy-MM-dd"));
		List<MesticProductPrice> dataList = null;
		dataList = mesticProductPriceService.dataExport();
//		if(dataList.isEmpty()){
//			return workbook;
//		}
		for (int i = 0; i <= dataList.size() - 1; i++) {
			XSSFRow row = sheet.createRow(i + 1);
			XSSFCell cellData = row.createCell(4);
			cellData.setCellStyle(cellStyle1);
			row.createCell(0).setCellValue(dataList.get(i).getProductName());
			row.createCell(1).setCellValue(dataList.get(i).getLowPrice()+"");
			row.createCell(2).setCellValue(dataList.get(i).getHighPrice()+"");
			row.createCell(3).setCellValue(dataList.get(i).getPriceUnit());
			cellData.setCellValue(dataList.get(i).getPriceDate());
			row.createCell(5).setCellValue(dataList.get(i).getAreaName());
			row.createCell(6).setCellValue(dataList.get(i).getPriceSource());
			if("0".equals(dataList.get(i).getPriceTrend())) {
				row.createCell(7).setCellValue("持平");
			} else if("1".equals(dataList.get(i).getPriceTrend())) {
				row.createCell(7).setCellValue("上涨");
			} else if("-1".equals(dataList.get(i).getPriceTrend())){
				row.createCell(7).setCellValue("下跌");
			}
			if(dataList.get(i).getLowPriceChange() == null) {
				row.createCell(8).setCellValue("");
			} else {
				row.createCell(8).setCellValue(dataList.get(i).getLowPriceChange()+"");
			}
			if(dataList.get(i).getHighPriceChange() == null) {
				row.createCell(9).setCellValue("");
			} else {
				row.createCell(9).setCellValue(dataList.get(i).getHighPriceChange()+"");
			}
			
		}
		
		try {
			
			long l = DateTimeUtils.currentDate().getTime();
			OutputStream out = response.getOutputStream();
			response.reset();// 清空输出流   
	        response.setHeader("Content-disposition", "attachment; filename="+new String("厂家报价".getBytes(),"iso-8859-1") + ".xls");// 设定输出文件头   
	        response.setContentType("application/msexcel");// 定义输出类型 
	        workbook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/front/domesticPriceList.json", method = RequestMethod.POST)
	public ResultDatas<List<Map<String, Object>>>  getFrontOfficialList(@RequestBody Map<String, Object> map){
		ResultDatas<List<Map<String, Object>>> result = new ResultDatas<>();
		try {
			SimplePageInfo pageInfo = new SimplePageInfo();
			if(map.get("pageNum")!=null)
				pageInfo.setPageNum((Integer)map.get("pageNum"));
			if(map.get("pageSize")!=null)
				pageInfo.setPageSize((Integer)map.get("pageSize"));
			
			Page<Map<String, Object>> page = mesticProductPriceService.queryRecords(map, pageInfo);
			
			result.setDatas(page.getResult());
			result.setPageNum(pageInfo.getPageNum());
			result.setPageSize(pageInfo.getPageSize());
			result.setTotal(page.getTotal());
			result.setStatus(0);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(999);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
}
