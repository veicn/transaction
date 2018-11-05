package com.sinochem.crude.trade.transport.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.geojson.domain.CurrentData;
import org.geojson.domain.CurrentDataResult;
import org.geojson.domain.CurrentInfo;
import org.geojson.domain.ExpectedData;
import org.geojson.domain.ExpectedDataResult;
import org.geojson.domain.ExpectedInfo;
import org.geojson.domain.HistoryPortEnData;
import org.geojson.domain.HistoryPortEnInfo;
import org.geojson.domain.HistoryPortEnResult;
import org.geojson.domain.HistoryPortInfo;
import org.geojson.domain.HistoryPortResult;
import org.geojson.httpclient.HttpUtils;
import org.geojson.model.CurrentVO;
import org.geojson.model.ExpectedVO;
import org.geojson.model.HistoryPortVO;
import org.geojson.url.DataCenterEarthUrlServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.service.MapPortService;
import com.sinochem.it.b2b.member.access.WithoutAccess;
/**
 * 地图（可视化）相关的导入导出----英文版
 *  
 * @author niuwk
 *
 */
@Controller
public class MapEngLishExcelController {
	
	Log log = LogFactory.getLog(GeoJsonController.class);
	
	@Autowired
	private DataCenterEarthUrlServer dataCenterEarthUrlServer;
	
	@Autowired
	private MapPortService mapPortService;
	
	
	
	/**
	 * 历史靠港导出
	 * @param ship
	 * @param user
	 * @return
	 */
	@WithoutAccess
	@RequestMapping(value="/api/export/en/current.htm")
	public void  current(CurrentVO current,CurrentUser user,HttpServletRequest request,HttpServletResponse response) {
		
		Workbook wb = new XSSFWorkbook();
		
		CreationHelper createHelper = wb.getCreationHelper();
		
		Sheet sheet = wb.createSheet("Port Arrivals");
		sheet.setColumnWidth(0, 6000); 
		sheet.setColumnWidth(1, 3000); 
		sheet.setColumnWidth(2, 3000); 
		sheet.setColumnWidth(3, 3000); 
		sheet.setColumnWidth(4, 3000); 
		sheet.setColumnWidth(5, 3000); 
		sheet.setColumnWidth(6, 3000); 
		sheet.setColumnWidth(7, 4000);
		sheet.setColumnWidth(8, 6000); 
		sheet.setColumnWidth(9, 6000);
		
		Row row = sheet.createRow(0);
		row.setHeightInPoints(30);
		
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		Cell cell = row.createCell(0);
		cell.setCellValue("Vessel Name");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(1);
		cell.setCellValue("IMO");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(2);
		cell.setCellValue("MMSI");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(3);
		cell.setCellValue("Vessel Type");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(4);
		cell.setCellValue("Max Draught");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(5);
		cell.setCellValue("Arrival  Draught");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(6);
		cell.setCellValue("Departure  Draught");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(7);
		cell.setCellValue("Previous Port");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(8);
		cell.setCellValue("ATD");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(9);
		cell.setCellValue("ATA");
		cell.setCellStyle(cellStyle);
		
		
		ResultDatas<Object> res = new ResultDatas<>();
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		String result =null;
		url+="/sinochem/api/history/port/current";
		String json=JSON.toJSONString(current);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try{
			long begin = System.currentTimeMillis(); 
			result=HttpUtils.sendHttpPost(url, json);
			long end = System.currentTimeMillis() - begin; 
			log.info("url:/api/export/en/current.htm  used :" +end +" milliseconds");
			System.out.println("datacenter result:" +result);
			HistoryPortEnResult historyPortEnResult = JSON.parseObject(result, new TypeReference<HistoryPortEnResult>() {});
			HistoryPortEnData data=historyPortEnResult.getData();
			List<HistoryPortEnInfo> list = data.getData();
			for(int i=1;i<=list.size();i++){
				row = sheet.createRow(i);
				row.setHeightInPoints(20);
				cell = row.createCell(0);
				cell.setCellValue(list.get(i-1).getShipName());
				cell.setCellStyle(cellStyle);
				
				row.createCell(1).setCellValue(list.get(i-1).getImo());
				row.createCell(2).setCellValue(list.get(i-1).getMmsi());
				row.createCell(3).setCellValue(list.get(i-1).getVesselSizeEn());
				row.createCell(4).setCellValue(list.get(i-1).getDraft());
				row.createCell(5).setCellValue(list.get(i-1).getDraughtIn());
				row.createCell(6).setCellValue(list.get(i-1).getDraughtOut());
				row.createCell(7).setCellValue(list.get(i-1).getLastPortEn());
				if(StringUtils.isNotBlank(list.get(i-1).getAtd())){
					Date atd =new Date(Long.parseLong(list.get(i-1).getAtd()));
					row.createCell(8).setCellValue(sdf.format(atd));
				}
				if(StringUtils.isNotBlank(list.get(i-1).getAta())){
					Date ata =new Date(Long.parseLong(list.get(i-1).getAta()));
					row.createCell(9).setCellValue(sdf.format(ata));
				}
			}
		}catch (Exception e ) {
			e.printStackTrace();
		}
		

		
		try {
			
			OutputStream out = null;
			out = response.getOutputStream(); 
			response.setContentType("application/ms-excel;charset=UTF-8"); 
			response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("Port Arrivals List" + ".xlsx", "UTF-8")))); 
			wb.write(out);
		} catch (Exception ex) { 

		}
		
	}
	
	
	/**
	 * 预计到港
	 * @param expected
	 * @param user
	 * @return
	 */
	@WithoutAccess
	@RequestMapping(value="/api/export/en/expected.htm")
	public void  expectedList(ExpectedVO expected,CurrentUser user,HttpServletRequest request,HttpServletResponse response) {
		Workbook wb = new XSSFWorkbook();
		
		CreationHelper createHelper = wb.getCreationHelper();
		
		Sheet sheet = wb.createSheet("Vessels Inbound");
		sheet.setColumnWidth(0, 6000); 
		sheet.setColumnWidth(1, 3000); 
		sheet.setColumnWidth(2, 3000); 
		sheet.setColumnWidth(3, 3000); 
		sheet.setColumnWidth(4, 3000); 
		sheet.setColumnWidth(5, 3000); 
		sheet.setColumnWidth(6, 3000); 
		sheet.setColumnWidth(7, 4000);
		sheet.setColumnWidth(8, 6000); 
		sheet.setColumnWidth(9, 6000);
		
		Row row = sheet.createRow(0);
		row.setHeightInPoints(30);
		
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		
		Cell cell = row.createCell(0);
		cell.setCellValue("Vessel Name");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(1);
		cell.setCellValue("IMO");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(2);
		cell.setCellValue("MMSI");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(3);
		cell.setCellValue("Vessel Type");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(4);
		cell.setCellValue("Max Draught");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(5);
		cell.setCellValue("Arrival  Draught");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(6);
		cell.setCellValue("Previous Port");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(7);
		cell.setCellValue("ETA");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(8);
		cell.setCellValue("Speed");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(9);
		cell.setCellValue("Last Updated");
		cell.setCellStyle(cellStyle);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		ResultDatas<Object> res = new ResultDatas<>();
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		String result =null;
		url+="/sinochem/api/history/port/expected";
		if(expected.getNaviStatus().equals("1,2")){
			expected.setNaviStatus("");
		}
		String json=JSON.toJSONString(expected);
		try{
			long begin = System.currentTimeMillis(); 
			result=HttpUtils.sendHttpPost(url, json);
			long end = System.currentTimeMillis() - begin; 
			log.info("url:/api/export/en/expected.htm  used :" +end +" milliseconds");
			System.out.println("datacenter result:" +result);
			ExpectedDataResult expectedDataResult = JSON.parseObject(result, new TypeReference<ExpectedDataResult>() {});
			ExpectedData data=expectedDataResult.getData();
			List<ExpectedInfo> list = data.getData();
			res.setDatas(data.getData());
			for(int i=1;i<=list.size();i++){
				row = sheet.createRow(i);
				row.setHeightInPoints(20);
				cell = row.createCell(0);
				cell.setCellValue(list.get(i-1).getShipName());
				cell.setCellStyle(cellStyle);
				
				row.createCell(1).setCellValue(list.get(i-1).getImo());
				row.createCell(2).setCellValue(list.get(i-1).getMmsi());
				row.createCell(3).setCellValue(list.get(i-1).getVesselSizeEn());
				row.createCell(4).setCellValue(list.get(i-1).getDraft());
				row.createCell(5).setCellValue(list.get(i-1).getDraught());
				row.createCell(6).setCellValue(list.get(i-1).getLastPortEn());
				
				if(StringUtils.isNotBlank(list.get(i-1).getEta())){
					Date eta =new Date(Long.parseLong(list.get(i-1).getEta()));
					row.createCell(7).setCellValue(sdf.format(eta));
				}
				
				row.createCell(8).setCellValue(list.get(i-1).getSpeed());
				
				if(StringUtils.isNotBlank(list.get(i-1).getReceiveTime())){
					Date greceiveTime =new Date(Long.parseLong(list.get(i-1).getReceiveTime()));
					row.createCell(9).setCellValue(sdf.format(greceiveTime));
				}
			}
 		}catch (Exception e ) {
			e.printStackTrace();
		}
		
		
		try {
			
			OutputStream out = null;
			out = response.getOutputStream(); 
			response.setContentType("application/ms-excel;charset=UTF-8"); 
			response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("Vessels Inbound List" + ".xlsx", "UTF-8")))); 
			wb.write(out);
		} catch (Exception ex) { 

		}
		
	}
	
	
	/**
	 * 当前在港
	 * @param expected
	 * @param user
	 * @return
	 */
	@WithoutAccess
	@RequestMapping(value="/api/export/en/now.htm")
	public void  nowList(CurrentVO expected,CurrentUser user,HttpServletRequest request,HttpServletResponse response) {
		Workbook wb = new XSSFWorkbook();
		
		CreationHelper createHelper = wb.getCreationHelper();
		
		Sheet sheet = wb.createSheet("Vessels In Port");
		sheet.setColumnWidth(0, 6000); 
		sheet.setColumnWidth(1, 3000); 
		sheet.setColumnWidth(2, 3000); 
		sheet.setColumnWidth(3, 3000); 
		sheet.setColumnWidth(4, 3000); 
		sheet.setColumnWidth(5, 3000); 
		sheet.setColumnWidth(6, 3000); 
		sheet.setColumnWidth(7, 6000);
		sheet.setColumnWidth(8, 6000); 
		
		Row row = sheet.createRow(0);
		row.setHeightInPoints(30);
		
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		
		Cell cell = row.createCell(0);
		cell.setCellValue("Vessel Name");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(1);
		cell.setCellValue("IMO");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(2);
		cell.setCellValue("MMSI");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(3);
		cell.setCellValue("Vessel Type");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(4);
		cell.setCellValue("Max Draught");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(5);
		cell.setCellValue("Arrival  Draught");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(6);
		cell.setCellValue("Previous Port");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(7);
		cell.setCellValue("ATA");
		cell.setCellStyle(cellStyle);
		
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		ResultDatas<Object> res = new ResultDatas<>();
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		String result =null;
		url +="/sinochem/api/ship/location";
		String json=JSON.toJSONString(expected);
		try{
			long begin = System.currentTimeMillis(); 
			result=HttpUtils.sendHttpPost(url, json);
			long end = System.currentTimeMillis() - begin; 
			log.info("url://api/export/en/now.htm  used :" +end +" milliseconds");
			System.out.println("datacenter result:" +result);
			CurrentDataResult currentDataResult = JSON.parseObject(result, new TypeReference<CurrentDataResult>() {});
			CurrentData data=currentDataResult.getData();
			List<CurrentInfo>  list = data.getData();
			res.setDatas(data.getData());
			for(int i=1;i<=list.size();i++){
				row = sheet.createRow(i);
				row.setHeightInPoints(20);
				cell = row.createCell(0);
				cell.setCellValue(list.get(i-1).getShipName());
				cell.setCellStyle(cellStyle);
				
				row.createCell(1).setCellValue(list.get(i-1).getImo());
				row.createCell(2).setCellValue(list.get(i-1).getMmsi());
				row.createCell(3).setCellValue(list.get(i-1).getSizeTypeEn());
				row.createCell(4).setCellValue(list.get(i-1).getDraft());
				row.createCell(5).setCellValue(list.get(i-1).getDraught());
				row.createCell(6).setCellValue(list.get(i-1).getFromPortEn());
				
				if(StringUtils.isNotBlank(list.get(i-1).getAta())){
					Date ata =new Date(Long.parseLong(list.get(i-1).getAta()));
					row.createCell(7).setCellValue(sdf.format(ata));
				}
			}
 		}catch (Exception e ) {
			e.printStackTrace();
		}
		
		
		try {
			
			OutputStream out = null;
			out = response.getOutputStream(); 
			response.setContentType("application/ms-excel;charset=UTF-8"); 
			response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("Vessels In Port List" + ".xlsx", "UTF-8")))); 
			wb.write(out);
		} catch (Exception ex) { 

		}
		
	}
	
	
	/**
	 * 历史航线
	 * @param expected
	 * @param user
	 * @return
	 */
	@WithoutAccess
	@RequestMapping(value="/api/export/en/historyport.htm")
	public void  historyport(HistoryPortVO historyPort,CurrentUser user,HttpServletRequest request,HttpServletResponse response) {
		Workbook wb = new XSSFWorkbook();
		
		CreationHelper createHelper = wb.getCreationHelper();
		
		Sheet sheet = wb.createSheet("Historical Route");
		sheet.setColumnWidth(0, 6000); 
		sheet.setColumnWidth(1, 3000); 
		sheet.setColumnWidth(2, 3000); 
		sheet.setColumnWidth(3, 3000); 
		sheet.setColumnWidth(4, 3000); 
		sheet.setColumnWidth(5, 3000); 
		sheet.setColumnWidth(6, 3000); 
		sheet.setColumnWidth(7, 6000);
		sheet.setColumnWidth(8, 6000);
		sheet.setColumnWidth(9, 6000);
		sheet.setColumnWidth(10, 6000);
		sheet.setColumnWidth(11, 6000);
		sheet.setColumnWidth(12, 6000);
		
		Row row = sheet.createRow(0);
		row.setHeightInPoints(30);
		
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		
		Cell cell = row.createCell(0);
		cell.setCellValue("Vessel Name");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(1);
		cell.setCellValue("IMO");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(2);
		cell.setCellValue("MMSI");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(3);
		cell.setCellValue("Vessel Type");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(4);
		cell.setCellValue("Max Draught");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(5);
		cell.setCellValue("Departure Draught(Origin Port)");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(6);
		cell.setCellValue("Arrival Draught( Arrival Port)");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(7);
		cell.setCellValue("Origin Port");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(8);
		cell.setCellValue("Origin Zone");
		cell.setCellStyle(cellStyle);
		
		
		cell = row.createCell(9);
		cell.setCellValue("ATD");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(10);
		cell.setCellValue("Via Ports");
		cell.setCellStyle(cellStyle);
		
		cell = row.createCell(11);
		cell.setCellValue("Destination");
		cell.setCellStyle(cellStyle);
		
		
		cell = row.createCell(12);
		cell.setCellValue("ATA");
		cell.setCellStyle(cellStyle);
		
		
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		ResultDatas<Object> res = new ResultDatas<>();
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		String result =null;
		url+="/sinochem/api/history/port/route";
		String json=JSON.toJSONString(historyPort);
		try{
			long begin = System.currentTimeMillis(); 
			result=HttpUtils.sendHttpPost(url, json);
			long end = System.currentTimeMillis() - begin; 
			log.info("url:/api/export/historyport.htm  used :" +end +" milliseconds");
			System.out.println("datacenter result:" +result);
			HistoryPortResult historyPortResult = JSON.parseObject(result, new TypeReference<HistoryPortResult>() {});
			List<HistoryPortInfo> list=historyPortResult.getData();
			for(int i=1;i<=list.size();i++){
				row = sheet.createRow(i);
				row.setHeightInPoints(20);
				cell = row.createCell(0);
				cell.setCellValue(list.get(i-1).getVesselName());
				cell.setCellStyle(cellStyle);
				
				row.createCell(1).setCellValue(list.get(i-1).getImo());
				row.createCell(2).setCellValue(list.get(i-1).getMmsi());
				row.createCell(3).setCellValue(list.get(i-1).getVesselSizeTypeEN());
				row.createCell(4).setCellValue(list.get(i-1).getDraft());
				row.createCell(5).setCellValue(list.get(i-1).getDraughtOut());
				row.createCell(6).setCellValue(list.get(i-1).getDraughtIn());
				row.createCell(7).setCellValue(list.get(i-1).getFromPortEN());
				row.createCell(8).setCellValue(list.get(i-1).getFromCounrtyEN());
				row.createCell(10).setCellValue(list.get(i-1).getSubPortsEN());
				row.createCell(11).setCellValue(list.get(i-1).getToPortEN());
				if(StringUtils.isNotBlank(list.get(i-1).getAtd())){
					Date atd =new Date(Long.parseLong(list.get(i-1).getAtd()));
					row.createCell(9).setCellValue(sdf.format(atd));
				}
				if(StringUtils.isNotBlank(list.get(i-1).getAta())){
					Date ata =new Date(Long.parseLong(list.get(i-1).getAta()));
					row.createCell(12).setCellValue(sdf.format(ata));
				}
			}
 		}catch (Exception e ) {
			e.printStackTrace();
		}
		
		
		try {
			
			OutputStream out = null;
			out = response.getOutputStream(); 
			response.setContentType("application/ms-excel;charset=UTF-8"); 
			response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("Historical Route List" + ".xlsx", "UTF-8")))); 
			wb.write(out);
		} catch (Exception ex) { 

		}
		
	}
	
	

}
