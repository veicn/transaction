package com.sinochem.crude.trade.transport.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import jodd.util.StringUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.eyeieye.melody.web.locale.VisitorLocale;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.aliyun.oss.FileManager;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.commons.ImportUtils;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.dao.AgreementMapper;
import com.sinochem.crude.trade.transport.domain.Appoint;
import com.sinochem.crude.trade.transport.domain.BasicTariff;
import com.sinochem.crude.trade.transport.domain.LoadPort;
import com.sinochem.crude.trade.transport.domain.Transit;
import com.sinochem.crude.trade.transport.domain.UnloadPort;
import com.sinochem.crude.trade.transport.domain.VoyageStart;
import com.sinochem.crude.trade.transport.model.ExportBtVO;
import com.sinochem.crude.trade.transport.model.ExportVO;
import com.sinochem.crude.trade.transport.model.res.LoadPortImport;
import com.sinochem.crude.trade.transport.model.res.ShipPlateImport;
import com.sinochem.crude.trade.transport.model.res.SysShipImport;
import com.sinochem.crude.trade.transport.model.res.UnloadPortImport;
import com.sinochem.crude.trade.transport.service.AppointService;
import com.sinochem.crude.trade.transport.service.CommonService;
import com.sinochem.crude.trade.transport.service.LoadPortService;
import com.sinochem.crude.trade.transport.service.ShipPlateService;
import com.sinochem.crude.trade.transport.service.SysShipService;
import com.sinochem.crude.trade.transport.service.TransitService;
import com.sinochem.crude.trade.transport.service.UnloadPortService;
import com.sinochem.crude.trade.transport.service.VoyageStartService;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;

/**
 * 导出excel
 * @author gyy
 *
 */
@Controller
public class ExportController {
	
	
	private  Log log = LogFactory.getLog(ExportController.class);
	
	private static String mess;
	private static Boolean flag =false;
	private static List<LoadPortImport> loadPorts;
	private static List<UnloadPortImport> unLoadPorts;
	private static List<ShipPlateImport> shipPlates;
	//private static List<SysShipImport> sysShips;
	
	@Autowired
	private  FileManager fileManager;
	
	@Autowired
	private TransitService transitService;
	
	@Autowired
	private VoyageStartService voyageStartService;
	
	@Autowired
	private LoadPortService loadPortService;
	
	@Autowired
	private UnloadPortService unloadPortService;
	
	@Autowired
	private AppointService appointService;
	
	@Autowired
	private AgreementMapper _AgreementMapper;
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	private ShipPlateService shipPlateService;
	
	@Autowired
	private SysShipService sysShipService;
	
	@Autowired
	private CommonService commonService;
	
	@Value("${aliyun.oss.bucket}")
    private  String publicBucket;
	
	@Value("${oss.server.host}")
	private  String host;
	
	/**
	 * 模板导出
	 * @param demo
	 * @return
	 */
	@WithoutAccess
	@RequestMapping(value = "/export.json", method = RequestMethod.POST )
	@ResponseBody
    public  ResultDatas<String> export(@RequestBody ExportVO vo){
		List<String> headList = new ArrayList<>();
		List<List<String>> contentList = new ArrayList<>();
		ImportUtils.template(vo,headList, contentList);
		ResultDatas<String> res = new ResultDatas<>();
    	String storePath = ""; 
    	HSSFWorkbook wkb = null;
        ByteArrayOutputStream out = null;
        InputStream excelStream = null;
		try {
			 // 输出Excel文件
            out = new ByteArrayOutputStream();
            wkb = exports(headList, contentList);
            wkb.write(out);
            excelStream = new ByteArrayInputStream(out.toByteArray());
            storePath ="transport/excel/" ;
            storePath = storePath + KeyGenUtils.newUuid()+".xls";
            fileManager.save(excelStream, publicBucket, storePath);
            res.setDatas("http://"+host+"/"+storePath);
		} catch (BizException e) {
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
    }
	
	/**
	 * 在途导入
	 * @param demo
	 * @return
	 */
	
	@RequestMapping(value = "/import.json", method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<String> imports(HttpServletRequest request,CurrentUser user){
		ResultDatas<String> res = new ResultDatas<>();
		try {
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			 	String savePath = context.getRealPath("/WEB-INF/upload");
	            String tempPath = context.getRealPath("/WEB-INF/temp");
	            File tmpFile = new File(tempPath);
	            if (!tmpFile.exists()) {
	                tmpFile.mkdir();
	            }
	            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(context);
	            if (multipartResolver.isMultipart(request)) {
	                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	                Iterator<String> iter = multiRequest.getFileNames();
	                String[] parameterValues = multiRequest.getParameterValues("uuid");
	                String uuid=parameterValues[0];
	                
	                while (iter.hasNext()) {
	                    MultipartFile file = multiRequest.getFile(iter.next().toString());
	                    if (file != null) {
	                        String fileName = file.getOriginalFilename();

	                        String saveFilename = makeFileName(fileName);
	                        String realSavePath = makePath(saveFilename, savePath);

	                        InputStream in = file.getInputStream();

	                        FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
	                        byte buffer[] = new byte[1024];
	                        int len = 0;
	                        StringBuffer sb = new StringBuffer();
	                        while ((len = in.read(buffer)) > 0) {
	                            sb.append(new String(buffer, 0, len));
	                            out.write(buffer, 0, len);
	                        }
	                        in.close();
	                        out.close();
	                        
	                        String  path=realSavePath + "\\" + saveFilename;
	                        System.out.println("###################路径 ： "+realSavePath + "\\" + saveFilename+"   uuid:"+uuid);
	                        if (StringUtils.isNullOrEmpty(uuid)){
	                        	throw new TransportException(TransportException.TYPE.ITSH038);
	                        }
	                        mess = transitService.imports(path,uuid,user);
	                    }
	                }
	            }
	            flag= true;
		} catch (BizException e) {
			flag= false;
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			flag= false;
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	@SuppressWarnings("deprecation")
	private HSSFWorkbook exports( List<String> headList,List<List<String>> contentList) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个sheet表格
        HSSFSheet sheet = workbook.createSheet("sheet1");
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 35);
        sheet.setDefaultRowHeightInPoints(18);  
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        /*style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);*/
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headList.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headList.get(i));
            cell.setCellValue(text);
        }
        
        //遍历集合数据，产生数据行
        if (contentList != null && !contentList.isEmpty()) {
            int index = 1;
            for (List<String> list : contentList) {
            	if (list != null && !list.isEmpty()){
            		row = sheet.createRow(index);
            		  /**  
            	       * 合并单元格  
            	       *    第一个参数：第一个单元格的行数（从0开始）  
            	       *    第二个参数：第二个单元格的行数（从0开始）  
            	       *    第三个参数：第一个单元格的列数（从0开始）  
            	       *    第四个参数：第二个单元格的列数（从0开始）  
            	       */  
            		/*CellRangeAddress range = new CellRangeAddress(1, 2, 0, 0);   
            		sheet.addMergedRegion(range); */  
            		for (int i = 0; i < list.size(); i++) {
            			row.createCell(i).setCellValue(list.get(i));
					}
            		index++;
            	}
            }
        }
        return workbook;
    }
	
   private String makeFileName(String filename) {
        return UUID.randomUUID().toString() + "_" + filename;
    }

   private String makePath(String filename, String savePath) {
        int hashcode = filename.hashCode();
        int dir1 = hashcode & 0xf;
        int dir2 = (hashcode & 0xf0) >> 4;
        String dir = savePath + "\\" + dir1 + "\\" + dir2;
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }

	/**
	 * 上传结果
	 * @param request
	 * @return
	 */
    @RightAccess(2007)
    @WithoutAccess
	@RequestMapping(value = "/import/returnFlag.json", method = RequestMethod.POST )
	@ResponseBody
	public ResultDatas<String> returnFlag(HttpServletRequest request) {
		ResultDatas<String> res = new ResultDatas<>();
		try {
			if (!flag){
				throw new TransportException(TransportException.TYPE.ITSH552);
			}
			res.setDatas(mess);
		} catch (Exception e) {
			log.error("returnFlag error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
		
	/**
	 * 在途数据导出
	 * @param demo
	 * @return
	 */
	@WithoutAccess
	@RequestMapping(value = "/exportTrasitData.json", method = RequestMethod.POST )
	@ResponseBody
    public  ResultDatas<String> exportTransit(@RequestBody ExportVO vo){
		List<String> headList = new ArrayList<>();
		List<List<String>> contentList = new ArrayList<>();
		if (StringUtils.isNullOrEmpty(vo.getType())){
			throw new TransportException(TransportException.TYPE.ITSH550);
		}
		if ("1".equals(vo.getType())){
			//标题
			ImportUtils.transitHeadline(headList);
			//内容
			Transit transit = new Transit();
			transit.setShipPactUuid(vo.getShipPactUuid());
			List<Transit> transitList= transitService.queryByEntitys(transit);
			if(transitList==null||transitList.isEmpty()){
				throw new TransportException(TransportException.TYPE.ITSH009);
			}
			for (Transit transit1 : transitList) {
				List<String> list2 = new ArrayList<>();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				list2.add(dateFormat.format(transit1.getDateNow()));
				list2.add(transit1.getTimeNow());
				list2.add(transit1.getPosition());
				list2.add(String.valueOf(transit1.getSpeedH()));
				list2.add(String.valueOf(transit1.getSpeedAll()));
				list2.add(String.valueOf(transit1.getRpm()));
				list2.add(transit1.getSea());
				list2.add(transit1.getUnloadEta());
				list2.add(transit1.getWater());
				list2.add(transit1.getSulfide());
				list2.add(transit1.getExt1());
				contentList.add(list2);
			}
		} else {
			 headList = vo.getHeadList();
			 contentList = vo.getContentList();
		}
		ResultDatas<String> res = new ResultDatas<>();
    	String storePath = ""; 
    	HSSFWorkbook wkb = null;
        ByteArrayOutputStream out = null;
        InputStream excelStream = null;
		try {
			 // 输出Excel文件
            out = new ByteArrayOutputStream();
            wkb = exports(headList, contentList);
            wkb.write(out);
            excelStream = new ByteArrayInputStream(out.toByteArray());
            storePath ="transport/excel/" ;
            storePath = storePath + KeyGenUtils.newUuid()+".xls";
            fileManager.save(excelStream, publicBucket, storePath);
            res.setDatas("http://"+host+"/"+storePath);
		} catch (BizException e) {
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
    }
	/**
	 * 基础运价数据导出
	 * @param demo
	 * @return
	 */
	@RightAccess(2040)
	@WithoutAccess
	@RequestMapping(value = "/exportBt.json", method = RequestMethod.POST )
	@ResponseBody
    public  ResultDatas<String> exportBt(@RequestBody ExportBtVO vo){
		List<String> headList = new ArrayList<>();
		List<List<String>> contentList = new ArrayList<>();
		if (StringUtils.isNullOrEmpty(vo.getType())){
			throw new TransportException(TransportException.TYPE.ITSH550);
		}
		if ("1".equals(vo.getType())){
			//标题
			headList.add("LOAD_PORT1（装港1）");
			headList.add("LOAD_PORT2（装港2）");
			headList.add("LOAD_PORT3（装港3）");
			headList.add("UNLOAD_PORT1（卸港1）");
			headList.add("UNLOAD_PORT2（卸港2）");
			headList.add("UNLOAD_PORT3（卸港3）");
			headList.add("YEAR（年份）");
			headList.add("FLAT RATE（基础运价）");
			headList.add("MILEAGE（里程数）");
			headList.add("REMARK（注意事项）");
			headList.add("ROUTE（路线选择）");
			headList.add("REGION（地区）");
			//内容
			List<String> list2 = new ArrayList<>();
			list2.add("AKRINIRK");
			list2.add("UNPORSRBLE");
			list2.add("KALEMIERT");
			list2.add("日照港");
			list2.add("青岛港");
			list2.add("大连港");
			list2.add("2018");
			list2.add("21.3");
			list2.add("1234");
			list2.add("");
			list2.add("");
			list2.add("");
			contentList.add(list2);
			
		}
		ResultDatas<String> res = new ResultDatas<>();
    	String storePath = ""; 
    	HSSFWorkbook wkb = null;
        ByteArrayOutputStream out = null;
        InputStream excelStream = null;
		try {
			 // 输出Excel文件
            out = new ByteArrayOutputStream();
            wkb = exports(headList, contentList);
            wkb.write(out);
            excelStream = new ByteArrayInputStream(out.toByteArray());
            storePath ="transport/excel/" ;
            storePath = storePath + KeyGenUtils.newUuid()+".xls";
            fileManager.save(excelStream, publicBucket, storePath);
            res.setDatas("http://"+host+"/"+storePath);
		} catch (BizException e) {
			log.error("exportBT error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("exportBT error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
    }
	/**
	 * 基础运价导入
	 * @param demo
	 * @return
	 */
	@RightAccess(2039)
	@RequestMapping(value = "/importBt.json", method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<String> importsBt(HttpServletRequest request,CurrentUser user){
		ResultDatas<String> res = new ResultDatas<>();
		try {
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
			MultipartFile file = req.getFile("file");
			//String filename = file.getOriginalFilename();
			InputStream inputStream = file.getInputStream();
			Workbook wb = new HSSFWorkbook(inputStream);
			Sheet sheet = wb.getSheetAt(0);
			List<BasicTariff> list = new ArrayList<>();
			for (Row row : sheet) {
				if(row.getRowNum()>=1){
					try {
						BasicTariff bt = new BasicTariff();
						bt.setUuid(KeyGenUtils.newUuid());
						bt.setLangVer(Constants.LANG_VER);
						bt.setAliveFlag(Constants.ALIEVE_FLAG_YES);
						bt.setCreateDate(DateTimeUtils.currentDate());
						bt.setCreateUser(user.getMemberId());
						bt.setVersion("1");
						for (int i = 0; i < row.getLastCellNum(); i++) {
							if(row.getCell(i)!=null){
								row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);													
							}
						}
						if(row.getCell(0) != null){
							bt.setLoadPort1(row.getCell(0).getStringCellValue());
						}
						if(row.getCell(1) != null){
							bt.setLoadPort2(row.getCell(1).getStringCellValue());					
						}
						if(row.getCell(2) != null){
							bt.setLoadPort3(row.getCell(2).getStringCellValue());
						}
						if(row.getCell(3) != null){
							bt.setUnloadPort1(row.getCell(3).getStringCellValue());
						}
						if(row.getCell(4) != null){
							bt.setUnloadPort2(row.getCell(4).getStringCellValue());
						}
						if(row.getCell(5) != null){
							bt.setUnloadPort3(row.getCell(5).getStringCellValue());
						}
						if(row.getCell(6) != null){
							bt.setYear(Integer.parseInt(row.getCell(6).getStringCellValue()));
						}
						if(row.getCell(7) != null){
							String cell7 = row.getCell(7).getStringCellValue();
							if(!StringUtils.isNullOrEmpty(cell7)){
								BigDecimal bigDecimal1 = new BigDecimal(cell7);
								bt.setPrice(bigDecimal1);
							}
						}
						if(row.getCell(8) != null){
							String cell8 = row.getCell(8).getStringCellValue();
							if(!StringUtils.isNullOrEmpty(cell8)){
								BigDecimal bigDecimal2 = new BigDecimal(cell8);
								bt.setMileage(bigDecimal2);
							}
						}
						if(row.getCell(9) != null){
							bt.setRemark(row.getCell(9).getStringCellValue());
						}
						if(row.getCell(10) != null){
							bt.setRemark2(row.getCell(10).getStringCellValue());
						}
						if(row.getCell(11) != null){
							bt.setRegion(row.getCell(11).getStringCellValue());
						}
						list.add(bt);
					} catch (Exception e) {
						log.error("exportBT error", e);
						BasicTariff bt = new BasicTariff();
						list.add(bt);
					}
				}
			}
			
			mess = transitService.importsBt(list, user);
	        flag= true;
		} catch (BizException e) {
			flag= false;
			log.error("exportBT error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			flag= false;
			log.error("exportBT error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 航次开始导入
	 * @param demo
	 * @return
	 */
	@RequestMapping(value = "/importVoyageStart.json", method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<String> importsVoyageStart(HttpServletRequest request,CurrentUser user){
		ResultDatas<String> res = new ResultDatas<>();
		try {
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			 	String savePath = context.getRealPath("/WEB-INF/upload");
	            String tempPath = context.getRealPath("/WEB-INF/temp");
	            File tmpFile = new File(tempPath);
	            if (!tmpFile.exists()) {
	                tmpFile.mkdir();
	            }
	            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(context);
	            if (multipartResolver.isMultipart(request)) {
	                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	                Iterator<String> iter = multiRequest.getFileNames();
	                String[] parameterValues = multiRequest.getParameterValues("uuid");
	                String uuid=parameterValues[0];
	                
	                while (iter.hasNext()) {
	                    MultipartFile file = multiRequest.getFile(iter.next().toString());
	                    if (file != null) {
	                        String fileName = file.getOriginalFilename();

	                        String saveFilename = makeFileName(fileName);
	                        String realSavePath = makePath(saveFilename, savePath);

	                        InputStream in = file.getInputStream();

	                        FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
	                        byte buffer[] = new byte[1024];
	                        int len = 0;
	                        StringBuffer sb = new StringBuffer();
	                        while ((len = in.read(buffer)) > 0) {
	                            sb.append(new String(buffer, 0, len));
	                            out.write(buffer, 0, len);
	                        }
	                        in.close();
	                        out.close();
	                        
	                        String  path=realSavePath + "\\" + saveFilename;
	                        System.out.println("###################路径 ： "+realSavePath + "\\" + saveFilename+"   uuid:"+uuid);
	                        if (StringUtils.isNullOrEmpty(uuid)){
	                        	throw new TransportException(TransportException.TYPE.ITSH038);
	                        }
	                        mess = voyageStartService.imports(path,uuid,user);
	                    }
	                }
	            }
	            flag= true;
		} catch (BizException e) {
			flag= false;
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			flag= false;
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 航次开始数据导出
	 * @param vo
	 * @return
	 */
	@WithoutAccess
	@RequestMapping(value = "/exportVoyageStartData.json", method = RequestMethod.POST )
	@ResponseBody
    public  ResultDatas<String> exportVoyageStartData(@RequestBody ExportVO vo){
		List<String> headList = new ArrayList<>();
		List<List<String>> contentList = new ArrayList<>();
		if (StringUtils.isNullOrEmpty(vo.getType())){
			throw new TransportException(TransportException.TYPE.ITSH550);
		}
		if ("2".equals(vo.getType())){
			//标题
			ImportUtils.voyageStartHeadline(headList);
			//内容
			VoyageStart voyagestart = new VoyageStart();
			voyagestart.setShipPactUuid(vo.getShipPactUuid());
			List<VoyageStart> voyagestartList= voyageStartService.queryByEntitys(voyagestart);
			if(voyagestartList==null||voyagestartList.isEmpty()){
				throw new TransportException(TransportException.TYPE.ITSH007);
			}
			for (VoyageStart voyagestart1 : voyagestartList) {
				List<String> list2 = new ArrayList<>();
				list2.add(voyagestart1.getOilType());
				list2.add(String.valueOf(voyagestart1.getQuantityCask()));
				list2.add(String.valueOf(voyagestart1.getQuantity()));	
				list2.add(String.valueOf(voyagestart1.getApi()));
				list2.add(String.valueOf(voyagestart1.getLoadTemp()));
				list2.add(String.valueOf(voyagestart1.getLoadDraft()));
				list2.add(String.valueOf(voyagestart1.getUnloadDraft()));
				contentList.add(list2);
			}
		} else {
			 headList = vo.getHeadList();
			 contentList = vo.getContentList();
		}
		ResultDatas<String> res = new ResultDatas<>();
    	String storePath = ""; 
    	HSSFWorkbook wkb = null;
        ByteArrayOutputStream out = null;
        InputStream excelStream = null;
		try {
			 // 输出Excel文件
            out = new ByteArrayOutputStream();
            wkb = exports(headList, contentList);
            wkb.write(out);
            excelStream = new ByteArrayInputStream(out.toByteArray());
            storePath ="transport/excel/" ;
            storePath = storePath + KeyGenUtils.newUuid()+".xls";
            fileManager.save(excelStream, publicBucket, storePath);
            res.setDatas("http://"+host+"/"+storePath);
		} catch (BizException e) {
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
    }
	
	/**
	 * 装港数据导出
	 * @param vo
	 * @return
	 */
	@WithoutAccess
	@RequestMapping(value = "/exportLoadPortData.json", method = RequestMethod.POST )
	@ResponseBody
    public  ResultDatas<String> exportLoadPortData(@RequestBody ExportVO vo){
		List<String> headList = new ArrayList<>();
		List<List<String>> contentList = new ArrayList<>();
		ResultDatas<String> res = new ResultDatas<>();
		try {
			if (StringUtils.isNullOrEmpty(vo.getType())){
				throw new TransportException(TransportException.TYPE.ITSH004);
			}
			if ("3".equals(vo.getType())){
				//标题
				ImportUtils.loadPortDataHeadline(headList);
				//内容
				LoadPort loadPort = new LoadPort();
				loadPort.setShipPactUuid(vo.getShipPactUuid());
				List<LoadPort> loadPortList= loadPortService.queryByEntitys(loadPort);
				if(loadPortList==null||loadPortList.isEmpty()){
					throw new TransportException(TransportException.TYPE.ITSH048);
				}
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				for (LoadPort loadPort1 : loadPortList) {
					String[] AgreementCodes = loadPort1.getAgreementCode().split(";");
					String[] AgreementUuids = loadPort1.getAgreementUuid().split(";");
					for (int i=0;i<AgreementCodes.length;i++) {
						List<String> list2 = new ArrayList<>();
						String loadPortCode = loadPort1.getLoadPortCode();
						if(StringUtil.isNotBlank(loadPortCode)){
							String loadPortName = commonService.findNameByCodeAndLang("1",loadPortCode);
							list2.add(loadPortName);
						}else{
							list2.add(loadPort1.getLoadPort());
						}
						String oilTypeCode = loadPort1.getOilTypeCode();
						if(StringUtil.isNotBlank(oilTypeCode)){
							String oilTypeName = commonService.findNameByCodeAndLang("3",oilTypeCode);
							list2.add(oilTypeName);
						}else{
							list2.add(loadPort1.getOilType());
						}
						list2.add(dateFormat.format(loadPort1.getEta()));
						list2.add(dateFormat.format(loadPort1.getExBerth()));
						list2.add(dateFormat.format(loadPort1.getNorDate()));
						list2.add(dateFormat.format(loadPort1.getWaterDate()));
						list2.add(dateFormat.format(loadPort1.getAtripDate()));
						list2.add(dateFormat.format(loadPort1.getBerthDate()));
						list2.add(dateFormat.format(loadPort1.getAcStart()));
						list2.add(dateFormat.format(loadPort1.getAcFinish()));
						list2.add(dateFormat.format(loadPort1.getRemTubeDate()));
						list2.add(dateFormat.format(loadPort1.getExLeave()));
						list2.add(dateFormat.format(loadPort1.getAcLeave()));
						list2.add(loadPort1.getInspection());
						list2.add(loadPort1.getAgency());
						list2.add(AgreementCodes[i]);
						list2.add(AgreementUuids[i]);
						contentList.add(list2);
					}
				}
			} else {
				 headList = vo.getHeadList();
				 contentList = vo.getContentList();
			}
			
	    	String storePath = ""; 
	    	HSSFWorkbook wkb = null;
	        ByteArrayOutputStream out = null;
	        InputStream excelStream = null;
			
				 // 输出Excel文件
	            out = new ByteArrayOutputStream();
	            wkb = exports(headList, contentList);
	            wkb.write(out);
	            excelStream = new ByteArrayInputStream(out.toByteArray());
	            storePath ="transport/excel/" ;
	            storePath = storePath + KeyGenUtils.newUuid()+".xls";
	            fileManager.save(excelStream, publicBucket, storePath);
	            res.setDatas("http://"+host+"/"+storePath);
		} catch (BizException e) {
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
    }
	
	/**
	 * 装港模板导出
	 * @param vo
	 * @return
	 */
	@WithoutAccess
	@RequestMapping(value = "/exportLoadPortTemple.json", method = RequestMethod.POST )
	@ResponseBody
    public  ResultDatas<String> exportLoadPortTemple(@RequestBody ExportVO vo){
		List<String> headList = new ArrayList<>();
		List<List<String>> contentList = new ArrayList<>();
		if("3".equals(vo.getType())){
			ImportUtils.loadPortHeadline(headList);
			ImportUtils.loadPortTemplate(contentList);
		}else{
			throw new TransportException(TransportException.TYPE.ITSH007);
		}
		ResultDatas<String> res = new ResultDatas<>();
    	String storePath = ""; 
    	HSSFWorkbook wkb = null;
        ByteArrayOutputStream out = null;
        InputStream excelStream = null;
		try {
			 // 输出Excel文件
            out = new ByteArrayOutputStream();
            wkb = exports(headList, contentList);
            wkb.write(out);
            excelStream = new ByteArrayInputStream(out.toByteArray());
            storePath ="transport/excel/" ;
            storePath = storePath + KeyGenUtils.newUuid()+".xls";
            fileManager.save(excelStream, publicBucket, storePath);
            res.setDatas("http://"+host+"/"+storePath);
		} catch (BizException e) {
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 装港信息导入
	 * @param demo
	 * @return
	 */
	@RequestMapping(value = "/importLoadPortData.json", method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<List<LoadPortImport>> importLoadPortData(HttpServletRequest request,CurrentUser user){
		ResultDatas<List<LoadPortImport>> res = new ResultDatas<>();
		try {
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
		 	String savePath = context.getRealPath("/WEB-INF/upload");
	        String tempPath = context.getRealPath("/WEB-INF/temp");
	        File tmpFile = new File(tempPath);
	        if (!tmpFile.exists()) {
	            tmpFile.mkdir();
	        }
	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(context);
	        if (multipartResolver.isMultipart(request)) {
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	            Iterator<String> iter = multiRequest.getFileNames();
	            String[] parameterValues = multiRequest.getParameterValues("uuid");
	            String uuid=parameterValues[0];
	            
	            while (iter.hasNext()) {
	                MultipartFile file = multiRequest.getFile(iter.next().toString());
	                if (file != null) {
	                    String fileName = file.getOriginalFilename();
	
	                    String saveFilename = makeFileName(fileName);
	                    String realSavePath = makePath(saveFilename, savePath);
	
	                    InputStream in = file.getInputStream();
	
	                    FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
	                    byte buffer[] = new byte[1024];
	                    int len = 0;
	                    StringBuffer sb = new StringBuffer();
	                    while ((len = in.read(buffer)) > 0) {
	                        sb.append(new String(buffer, 0, len));
	                        out.write(buffer, 0, len);
	                    }
	                    in.close();
	                    out.close();
	                    String  path=realSavePath + "\\" + saveFilename;
	                    System.out.println("###################路径 ： "+realSavePath + "\\" + saveFilename+"   uuid:"+uuid);
	                    if (StringUtils.isNullOrEmpty(uuid)){
	                    	throw new TransportException(TransportException.TYPE.ITSH038);
	                    }
	                    loadPorts = ImportUtils.getloadPort(path);
	                    mess= path;
	                }
	            }
	        }
	        res.setDatas(loadPorts);
	        flag= true;
		} catch (BizException e) {
			flag= false;
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			flag= false;
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 卸港数据导出
	 * @param vo
	 * @return
	 */
	@WithoutAccess
	@RequestMapping(value = "/exportUnloadPortData.json", method = RequestMethod.POST )
	@ResponseBody
    public  ResultDatas<String> exportUnloadPortData(@RequestBody ExportVO vo){
		List<String> headList = new ArrayList<>();
		List<List<String>> contentList = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		ResultDatas<String> res = new ResultDatas<>();
		try {
			if (StringUtils.isNullOrEmpty(vo.getType())){
				throw new TransportException(TransportException.TYPE.ITSH011);
			}
			if ("4".equals(vo.getType())){
				//标题
				ImportUtils.unloadPortDataHeadline(headList);
				//内容
				UnloadPort unloadPort = new UnloadPort();
				unloadPort.setShipPactUuid(vo.getShipPactUuid());
				List<UnloadPort> unloadPortlist= unloadPortService.queryByEntitys(unloadPort);
				if(unloadPortlist==null||unloadPortlist.isEmpty()){
					throw new TransportException(TransportException.TYPE.ITSH011);
				}
				for (UnloadPort unloadPort2 : unloadPortlist) {
					Appoint appoint = new Appoint();
					appoint.setUnloadPortUuid(unloadPort2.getUuid());
					List<Appoint> appointlist = appointService.queryByEntitys(appoint);
					if(appointlist==null||appointlist.isEmpty()){
						throw new TransportException(TransportException.TYPE.ITSH011);
					}
					for (Appoint appoint2 : appointlist) {
						List<String> unloadPort3 = new ArrayList<>();
						String unloadPortCode = unloadPort2.getUnloadPortCode();
						if(StringUtil.isNotBlank(unloadPortCode)){
							String unloadPortName = commonService.findNameByCodeAndLang("2",unloadPortCode);
							unloadPort3.add(unloadPortName);
						}else{
							unloadPort3.add(unloadPort2.getUnloadPort());
						}
						String oilTypeCode = unloadPort2.getOilTypeCode();
						if(StringUtil.isNotBlank(oilTypeCode)){
							String oilTypeName = commonService.findNameByCodeAndLang("3",oilTypeCode);
							unloadPort3.add(oilTypeName);
						}else{
							unloadPort3.add(unloadPort2.getOilType());
						}
						unloadPort3.add(dateFormat.format(unloadPort2.getEta()));
						unloadPort3.add(dateFormat.format(unloadPort2.getExBerth()));
						unloadPort3.add(dateFormat.format(unloadPort2.getNorDate()));
						
						unloadPort3.add(dateFormat.format(unloadPort2.getWaterDate()));
						unloadPort3.add(dateFormat.format(unloadPort2.getAtripDate()));
						unloadPort3.add(dateFormat.format(unloadPort2.getBerthDate()));
						unloadPort3.add(dateFormat.format(unloadPort2.getAcStart()));
						
						unloadPort3.add(dateFormat.format(unloadPort2.getAcFinish()));
						unloadPort3.add(dateFormat.format(unloadPort2.getRemTubeDate()));
						unloadPort3.add(dateFormat.format(unloadPort2.getExLeave()));
						unloadPort3.add(dateFormat.format(unloadPort2.getAcLeave()));
						
						String agreementCode= _AgreementMapper.findByUuid(appoint2.getAgreementUuid()).getAgreementCode();
						unloadPort3.add(agreementCode);
						unloadPort3.add(appoint2.getAgreementUuid());
						unloadPort3.add(appoint2.getInspection());
						unloadPort3.add(appoint2.getInspectionTel());
						unloadPort3.add(appoint2.getAgency());
						unloadPort3.add(appoint2.getAgencyTel());
						unloadPort3.add(appoint2.getMonitor());
						unloadPort3.add(appoint2.getMonitorTel());
						contentList.add(unloadPort3);
					}
				}
			} else {
				 headList = vo.getHeadList();
				 contentList = vo.getContentList();
			}
			
	    	String storePath = ""; 
	    	HSSFWorkbook wkb = null;
	        ByteArrayOutputStream out = null;
	        InputStream excelStream = null;
		
			 // 输出Excel文件
	        out = new ByteArrayOutputStream();
	        wkb = exports(headList, contentList);
	        wkb.write(out);
	        excelStream = new ByteArrayInputStream(out.toByteArray());
	        storePath ="transport/excel/" ;
	        storePath = storePath + KeyGenUtils.newUuid()+".xls";
	        fileManager.save(excelStream, publicBucket, storePath);
	        res.setDatas("http://"+host+"/"+storePath);
		} catch (BizException e) {
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
    }
	
	/**
	 * 卸港模板导出
	 * @param vo
	 * @return
	 */
	@WithoutAccess
	@RequestMapping(value = "/exportUnloadPortTemple.json", method = RequestMethod.POST )
	@ResponseBody
    public  ResultDatas<String> exportUnloadPortTemple(@RequestBody ExportVO vo){
		List<String> headList = new ArrayList<>();
		List<List<String>> contentList = new ArrayList<>();
		if("4".equals(vo.getType())){
			ImportUtils.unloadPortHeadline(headList);
			ImportUtils.unloadPortTemplate(contentList);
		}else{
			throw new TransportException(TransportException.TYPE.ITSH007);
		}
		ResultDatas<String> res = new ResultDatas<>();
    	String storePath = ""; 
    	HSSFWorkbook wkb = null;
        ByteArrayOutputStream out = null;
        InputStream excelStream = null;
		try {
			 // 输出Excel文件
            out = new ByteArrayOutputStream();
            wkb = exports(headList, contentList);
            wkb.write(out);
            excelStream = new ByteArrayInputStream(out.toByteArray());
            storePath ="transport/excel/" ;
            storePath = storePath + KeyGenUtils.newUuid()+".xls";
            fileManager.save(excelStream, publicBucket, storePath);
            res.setDatas("http://"+host+"/"+storePath);
		} catch (BizException e) {
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}

	/**
	 * 卸港信息导入
	 * @param demo
	 * @return
	 */
	@RolesAccess({Constants.ADMIN,Constants.SHIP_AGENT,Constants.ENTER_MASTER})
	@ResponseBody
	@RequestMapping(value = "/importUnloadPortData.json", method = RequestMethod.POST)
	public ResultDatas<List<UnloadPortImport>> importUnloadPortData(HttpServletRequest request,CurrentUser user){
		ResultDatas<List<UnloadPortImport>> res = new ResultDatas<>();
		List<UnloadPortImport>  unloadPorts = new ArrayList<UnloadPortImport>();
		try {
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
		 	String savePath = context.getRealPath("/WEB-INF/upload");
            String tempPath = context.getRealPath("/WEB-INF/temp");
            File tmpFile = new File(tempPath);
            if (!tmpFile.exists()) {
                tmpFile.mkdir();
            }
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(context);
            if (multipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iter = multiRequest.getFileNames();
                String[] parameterValues = multiRequest.getParameterValues("uuid");
                String uuid=parameterValues[0];
                
                while (iter.hasNext()) {
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    if (file != null) {
                        String fileName = file.getOriginalFilename();

                        String saveFilename = makeFileName(fileName);
                        String realSavePath = makePath(saveFilename, savePath);

                        InputStream in = file.getInputStream();

                        FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
                        byte buffer[] = new byte[1024];
                        int len = 0;
                        StringBuffer sb = new StringBuffer();
                        while ((len = in.read(buffer)) > 0) {
                            sb.append(new String(buffer, 0, len));
                            out.write(buffer, 0, len);
                        }
                        in.close();
                        out.close();
                        
                        String  path=realSavePath + "\\" + saveFilename;
                        System.out.println("###################路径 ： "+realSavePath + "\\" + saveFilename+"   uuid:"+uuid);
                        if (StringUtils.isNullOrEmpty(uuid)){
                        	throw new TransportException(TransportException.TYPE.ITSH038);
                        }
                       unLoadPorts = ImportUtils.getUnLoadPort(path);
                    }
                }
            }
            res.setDatas(unloadPorts);
            flag= true;
		} catch (BizException e) {
			flag= false;
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			flag= false;
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	/**
	 * 装港解析结果
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/import/returnLoadPortDatas.json", method = RequestMethod.POST )
	@ResponseBody
	public ResultDatas<List<LoadPortImport>> returnLoadPortDatas(HttpServletRequest request) {
		ResultDatas<List<LoadPortImport>> res = new ResultDatas<>();
		try {
			if (!flag){
				throw new TransportException(TransportException.TYPE.ITSH552);
			}
			res.setDatas(loadPorts);
		} catch (Exception e) {
			log.error("returnFlag error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	/**
	 * 卸港解析结果
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/import/returnUnLoadPortDatas.json", method = RequestMethod.POST )
	@ResponseBody
	public ResultDatas<List<UnloadPortImport>> returnUnLoadPortDatas(HttpServletRequest request) {
		ResultDatas<List<UnloadPortImport>> res = new ResultDatas<>();
		try {
			if (!flag){
				throw new TransportException(TransportException.TYPE.ITSH552);
			}
			res.setDatas(unLoadPorts);
		} catch (Exception e) {
			log.error("returnFlag error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	
	/**
	 * 船盘模板导出
	 * @param vo
	 * @return
	 */
	@RightAccess(2013)
	@WithoutAccess
	@RequestMapping(value = "/exportShipPlateTemple.json", method = RequestMethod.POST )
	@ResponseBody
    public  ResultDatas<String> exportShipPlateTemple(@RequestBody ExportVO vo){
		ResultDatas<String> res = new ResultDatas<>();
		List<String> headList = new ArrayList<>();
		List<List<String>> contentList = new ArrayList<>();
		
		if("5".equals(vo.getType())){
			ImportUtils.shipPlateHeadlineTemplae(headList);
			ImportUtils.shipPlateContentTemplate(contentList);
		}else{
			throw new TransportException(TransportException.TYPE.ITSH414);
		}
		
    	String storePath = ""; 
    	HSSFWorkbook wkb = null;
        ByteArrayOutputStream out = null;
        InputStream excelStream = null;
        
		try {
			//输出Excel文件
            out = new ByteArrayOutputStream();
            wkb = exports(headList, contentList);
            wkb.write(out);
            excelStream = new ByteArrayInputStream(out.toByteArray());
            storePath ="transport/excel/" ;
            storePath = storePath + KeyGenUtils.newUuid()+".xls";
            fileManager.save(excelStream, publicBucket, storePath);
            res.setDatas("http://"+host+"/"+storePath);
		} catch (BizException e) {
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	
	/**
	 * 船盘信息导入（中心）
	 * @param demo
	 * @return
	 */
	@RequestMapping(value = "/importShipPlateData.json", method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<List<ShipPlateImport>> importShipPlateData(HttpServletRequest request,CurrentUser user){
		
		ResultDatas<List<ShipPlateImport>> res = new ResultDatas<>();
		
		try {
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
		 	String savePath = context.getRealPath("/WEB-INF/upload");
	        String tempPath = context.getRealPath("/WEB-INF/temp");
	        File tmpFile = new File(tempPath);
	        if (!tmpFile.exists()) {
	            tmpFile.mkdir();
	        }
	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(context);
	        if (multipartResolver.isMultipart(request)) {
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	            Iterator<String> iter = multiRequest.getFileNames();
	            
	            while (iter.hasNext()) {
	                MultipartFile file = multiRequest.getFile(iter.next().toString());
	                if (file != null) {
	                    String fileName = file.getOriginalFilename();
	
	                    String saveFilename = makeFileName(fileName);
	                    String realSavePath = makePath(saveFilename, savePath);
	
	                    InputStream in = file.getInputStream();
	
	                    FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
	                    byte buffer[] = new byte[1024];
	                    int len = 0;
	                    StringBuffer sb = new StringBuffer();
	                    while ((len = in.read(buffer)) > 0) {
	                        sb.append(new String(buffer, 0, len));
	                        out.write(buffer, 0, len);
	                    }
	                    in.close();
	                    out.close();
	                    String  path=realSavePath + "\\" + saveFilename;
	                    System.out.println("###################路径 ： "+realSavePath + "\\" + saveFilename);
	                    
	                    //保存船盘信息
	                    mess = shipPlateService.imports(path,user);	//回显提示消息
	                }
	            }
	        }
	        res.setDatas(shipPlates);
	        flag= true;
		} catch (BizException e) {
			flag= false;
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			flag= false;
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	
	
	/**
	 * 船盘信息导入（om平台）
	 * @param demo
	 * @return
	 */
	@RightAccess(2015)
	@RequestMapping(value = "/importShipPlateDataOM.json", method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<List<ShipPlateImport>> importShipPlateDataOM(HttpServletRequest request,CurrentUser user){
		
		ResultDatas<List<ShipPlateImport>> res = new ResultDatas<>();
		
		Long epMemberId=null;
		String epMemberIdStr = request.getParameter("epMemberId");
		if(!StringUtils.isNullOrEmpty(epMemberIdStr)){
			epMemberId = new Long(epMemberIdStr);
		}
		
		try {
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			String savePath = context.getRealPath("/WEB-INF/upload");
			String tempPath = context.getRealPath("/WEB-INF/temp");
			File tmpFile = new File(tempPath);
			if (!tmpFile.exists()) {
				tmpFile.mkdir();
			}
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(context);
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator<String> iter = multiRequest.getFileNames();
				
				while (iter.hasNext()) {
					MultipartFile file = multiRequest.getFile(iter.next().toString());
					if (file != null) {
						String fileName = file.getOriginalFilename();
						
						String saveFilename = makeFileName(fileName);
						String realSavePath = makePath(saveFilename, savePath);
						
						InputStream in = file.getInputStream();
						
						FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
						byte buffer[] = new byte[1024];
						int len = 0;
						StringBuffer sb = new StringBuffer();
						while ((len = in.read(buffer)) > 0) {
							sb.append(new String(buffer, 0, len));
							out.write(buffer, 0, len);
						}
						in.close();
						out.close();
						String  path=realSavePath + "\\" + saveFilename;
						System.out.println("###################路径 ： "+realSavePath + "\\" + saveFilename);
						
						//保存船盘信息
						mess = shipPlateService.importsOM(path,user,epMemberId);	//回显提示消息
					}
				}
			}
			res.setDatas(shipPlates);
			flag= true;
		} catch (BizException e) {
			flag= false;
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			flag= false;
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	
	/**
	 * 船舶模板导出
	 * @param vo
	 * @return
	 */
	@RightAccess(2005)
	@WithoutAccess
	@RequestMapping(value = "/exportSysShipTemple.json", method = RequestMethod.POST )
	@ResponseBody
    public  ResultDatas<String> exportSysShipTemple(@RequestBody ExportVO vo){	
		ResultDatas<String> res = new ResultDatas<>();
		List<String> headList = new ArrayList<>();
		List<List<String>> contentList = new ArrayList<>();
		
		if("6".equals(vo.getType()) || "7".equals(vo.getType())){		//（6船舶信息：船东/经纪人）（7船舶信息：平台）
			ImportUtils.sysShipHeadlineTemplae(headList);
			ImportUtils.sysShipContentTemplate(contentList);
		}else{
			throw new TransportException(TransportException.TYPE.ITSH414);
		}
		
    	String storePath = ""; 
    	HSSFWorkbook wkb = null;
        ByteArrayOutputStream out = null;
        InputStream excelStream = null;
        
		try {
			//输出Excel文件
            out = new ByteArrayOutputStream();
            wkb = exports(headList, contentList);
            wkb.write(out);
            excelStream = new ByteArrayInputStream(out.toByteArray());
            storePath ="transport/excel/" ;
            storePath = storePath + KeyGenUtils.newUuid()+".xls";
            fileManager.save(excelStream, publicBucket, storePath);
            res.setDatas("http://"+host+"/"+storePath);
		} catch (BizException e) {
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	
	/**
	 * 船舶信息批量导入
	 * @param demo
	 * @return
	 */
	@RightAccess(2006)
	@RequestMapping(value = "/importSysShipData.json", method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<List<SysShipImport>> importSysShipData(HttpServletRequest request,CurrentUser user){
		
		ResultDatas<List<SysShipImport>> res = new ResultDatas<>();
		
		String type=request.getParameter("type");	//（6船舶信息：船东/经纪人）（7船舶信息：平台）
		
		try {
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			String savePath = context.getRealPath("/WEB-INF/upload");
			String tempPath = context.getRealPath("/WEB-INF/temp");
			File tmpFile = new File(tempPath);
			if (!tmpFile.exists()) {
				tmpFile.mkdir();
			}
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(context);
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator<String> iter = multiRequest.getFileNames();
				
				while (iter.hasNext()) {
					MultipartFile file = multiRequest.getFile(iter.next().toString());
					if (file != null) {
						String fileName = file.getOriginalFilename();
						
						String saveFilename = makeFileName(fileName);
						String realSavePath = makePath(saveFilename, savePath);
						
						InputStream in = file.getInputStream();
						
						FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
						byte buffer[] = new byte[1024];
						int len = 0;
						StringBuffer sb = new StringBuffer();
						while ((len = in.read(buffer)) > 0) {
							sb.append(new String(buffer, 0, len));
							out.write(buffer, 0, len);
						}
						in.close();
						out.close();
						String  path=realSavePath + "\\" + saveFilename;
						System.out.println("###################路径 ： "+realSavePath + "\\" + saveFilename);
						
						//保存船舶信息
						mess = sysShipService.imports(path,user,type);	//回显提示消息
					}
				}
			}
			//res.setDatas(sysShips);
			flag= true;
		} catch (BizException e) {
			flag= false;
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			flag= false;
			log.error("export error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
}
