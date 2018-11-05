package com.sinochem.crude.trade.shipagent.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinochem.crude.trade.shipagent.common.ThreadContextHolder;
import com.sinochem.crude.trade.shipagent.domain.*;
import com.sinochem.crude.trade.shipagent.domain.vo.FileInfoVO;
import com.sinochem.crude.trade.shipagent.domain.vo.TShipagentSofVo;
import com.sinochem.crude.trade.shipagent.feign.HttpFeignClient;
//import com.sinochem.crude.trade.shipagent.utils.ExcelUtil;
import com.sinochem.crude.trade.shipagent.utils.Constant;
import com.sinochem.crude.trade.shipagent.utils.ExportFileNameUtil;
import com.sinochem.crude.trade.shipagent.utils.KeyGenUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author songhaqiang
 * @date 2018/9/11
 */
@RestController
@RequestMapping("/sof")
public class SofClientController {

	@Autowired
	HttpFeignClient httpFeignClient;

	/**
	 * SOF暂存（保存）
	 * @param sof
	 * @return
	 */
	@PostMapping(value = "/save.json")
	public ResultData save(@RequestBody TShipagentSof sof) {
		ResultData result = null;
		try{
			// 确定新增还是修改
			preSaveOrCommit(sof);

			// 接口调用
			result = httpFeignClient.sofSave(sof);
		}catch (Exception e){
			e.printStackTrace();
			result = new ResultData(Result.EEROR , Constant.SERVER_BUSY);
		}

		return result;
	}

	/**
	 * 提交SOF（保存并上链）
	 * @param sof
	 * @return
	 */
	@PostMapping(value = "/commit.json")
	public ResultData commit(@RequestBody TShipagentSof sof){
		ResultData result = null;
		try{
			// 确定新增还是修改
			preSaveOrCommit(sof);

			// 接口调用
			result = httpFeignClient.sofCommit(sof);
		}catch (Exception e){
			e.printStackTrace();
			result = new ResultData(Result.EEROR , Constant.SERVER_BUSY);
		}
		return result;
	}

	/**
	 * 获取SOF详情
	 * @param sof
	 * @return
	 */
	@PostMapping(value = "/get.json")
	public ResultData get(@RequestBody TShipagentSof sof){
		ResultData result = null;
		try{
			sof.setEnterpriseId(ThreadContextHolder.getCurrentUser().getEpMemberId());

			// 接口调用
			result = httpFeignClient.sofGet(sof.getId());
		}catch (Exception e){
			e.printStackTrace();
			result = new ResultData(Result.EEROR , Constant.SERVER_BUSY);
		}
		return result;
	}

	/**
	 * 修改SOF详情
	 * @param sof
	 * @return
	 */
	@PostMapping(value = "/update.json")
	public ResultData update(@RequestBody TShipagentSof sof){
		ResultData result = null;
		try{
			sof.setUpdateUser(ThreadContextHolder.getCurrentUser().getMemberId());
			sof.setUpdateDate(new Date());

			// 接口调用
			result = httpFeignClient.sofUpdate(sof);
		}catch (Exception e){
			e.printStackTrace();
			result = new ResultData(Result.EEROR , Constant.SERVER_BUSY);
		}
		return result;
	}


	@RequestMapping("/excel/get.json")
	public String downloadExcel(@RequestBody TShipagentSof sof){
		ResultData result = null;
		try{
			sof.setUpdateUser(ThreadContextHolder.getCurrentUser().getMemberId());
			sof.setUpdateDate(new Date());

			// 接口调用
			result = httpFeignClient.sofUpdate(sof);

			if(result != null  && result.getData() != null){

				//
				TShipagentSofVo vo = (TShipagentSofVo) result.getData();



			}
		}catch (Exception e){
			e.printStackTrace();
			result = new ResultData(Result.EEROR , Constant.SERVER_BUSY);
		}
		return "";
	}


	private void createExcel(TShipagentSofVo vo){
		// 创建一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建一个Excel的Sheet
		HSSFSheet sheet = wb.createSheet("SOF信息");

		// 定义样式
		//表头样工
		HSSFCellStyle cellStyleCenter = ExportFileNameUtil.initColumnHeadStyle(wb);
		//单元格样式
		HSSFCellStyle cellStyleRight = ExportFileNameUtil.initColumnCenterstyle(wb);
		HSSFCellStyle cellStyleLeft = ExportFileNameUtil.initColumnCenterstyle(wb);
		//右对齐
		cellStyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		//左对齐
		cellStyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		// 设置列宽
		sheet.setColumnWidth(0, 9880);
		sheet.setColumnWidth(1, 8630);
		sheet.setColumnWidth(2, 10500);
		sheet.setColumnWidth(3, 10500);
		sheet.setColumnWidth(4, 13380);
		sheet.setColumnWidth(5, 8750);
		sheet.setColumnWidth(6, 11000);
		sheet.setColumnWidth(7, 15880);
		sheet.setColumnWidth(8, 3880);
		try {
			HSSFRow row = null;
			HSSFCell cell = null;

		//-------------------------------初始化边框的表头------------------

			for(int i = 0; i < 39; i++){
				row = sheet.createRow(i);
				for (int j = 0 ; j <= 9 ; j++){
					cell = row.createCell(j);
					cell.setCellStyle(cellStyleCenter);

				}

			}

			//-------------------------------填充数据-------------------------
			//头信息
			cell = sheet.getRow(0).getCell(0);
			cell.setCellValue(new HSSFRichTextString(vo.getFullName()));
			cell = sheet.getRow(1).getCell(0);
			cell.setCellValue(new HSSFRichTextString(vo.getEnglishName()));
			cell = sheet.getRow(2).getCell(0);
			cell.setCellValue(new HSSFRichTextString("装货时间事实记录"));
			cell = sheet.getRow(3).getCell(0);
			cell.setCellValue(new HSSFRichTextString("LAYTIME STATEMENT OF FACTS"));
			//设置样式( 开始行，结束行，开始列，结束列)
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
			sheet.addMergedRegion(new CellRangeAddress(1, 0, 0, 8));
			sheet.addMergedRegion(new CellRangeAddress(2, 0, 0, 8));
			sheet.addMergedRegion(new CellRangeAddress(3, 0, 0, 8));

			// 第五行
			//  船名
			cell = sheet.getRow(4).getCell(0);
			cell.setCellValue(new HSSFRichTextString("船名"));
			cell = sheet.getRow(4).getCell(1);
			cell.setCellValue(new HSSFRichTextString(vo.getVessel()));

			//航次
			cell = sheet.getRow(4).getCell(3);
			cell.setCellValue(new HSSFRichTextString("航次"));
			cell = sheet.getRow(4).getCell(1);
			cell.setCellValue(new HSSFRichTextString(vo.getVoyage()));

			//日期
			cell = sheet.getRow(4).getCell(6);
			cell.setCellValue(new HSSFRichTextString("日期"));
			cell = sheet.getRow(4).getCell(7);
			cell.setCellValue(new HSSFRichTextString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vo.getSofDate())));
			// 第五行样式
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
			sheet.addMergedRegion(new CellRangeAddress(1, 0, 0, 8));
			sheet.addMergedRegion(new CellRangeAddress(2, 0, 0, 8));
			sheet.addMergedRegion(new CellRangeAddress(3, 0, 0, 8));



			//第六行
			//  M/V
			cell = sheet.getRow(5).getCell(0);
			cell.setCellValue(new HSSFRichTextString("M/N"));

			//  VOY.NO.
			cell = sheet.getRow(5).getCell(3);
			cell.setCellValue(new HSSFRichTextString("VOY.NO."));

			//DATE
			cell = sheet.getRow(5).getCell(6);
			cell.setCellValue(new HSSFRichTextString("DATE"));

			// 第7行
			//  M/V
			cell = sheet.getRow(5).getCell(0);
			cell.setCellValue(new HSSFRichTextString("装货名称"));

			//  VOY.NO.
			cell = sheet.getRow(5).getCell(3);
			cell.setCellValue(new HSSFRichTextString("吨数"));

			//
			cell = sheet.getRow(5).getCell(5);
			cell.setCellValue(new HSSFRichTextString("港口"));

			//-------------------------------------
			//  M/V
			cell = sheet.getRow(6).getCell(0);
			cell.setCellValue(new HSSFRichTextString("LOADING"));

			//  VOY.NO.
			cell = sheet.getRow(6).getCell(3);
			cell.setCellValue(new HSSFRichTextString("MT"));

			//DATE
			cell = sheet.getRow(6).getCell(5);
			cell.setCellValue(new HSSFRichTextString("Loading Port"));

			//sof明细
			//日   期	星   期	开始时间	结束时间	说明
			cell = sheet.getRow(7).getCell(0);
			cell.setCellValue(new HSSFRichTextString("日   期"));

			cell = sheet.getRow(7).getCell(1);
			cell.setCellValue(new HSSFRichTextString("星   期"));

			cell = sheet.getRow(7).getCell(2);
			cell.setCellValue(new HSSFRichTextString("开始时间"));

			cell = sheet.getRow(7).getCell(3);
			cell.setCellValue(new HSSFRichTextString("结束时间"));

			cell = sheet.getRow(7).getCell(4);
			cell.setCellValue(new HSSFRichTextString("说明"));


			//

			sheet.addMergedRegion(new CellRangeAddress(2, 2, 3, 4));
			sheet.addMergedRegion(new CellRangeAddress(3, 4, 4, 4));
			sheet.addMergedRegion(new CellRangeAddress(2, 4, 5, 5));



		}catch (Exception e){
			e.printStackTrace();
		}




	}



	private void preSaveOrCommit(TShipagentSof sof)throws Exception{
		if(sof.getId() == null ){
			sof.setUuid(KeyGenUtils.newUuid());
			sof.setCreateUser(ThreadContextHolder.getCurrentUser().getMemberId());
			sof.setCreateDate(new Date());
			sof.setStatus(TShipagentSof.STATUS_SAVED);
			sof.setEnterpriseId(ThreadContextHolder.getCurrentUser().getEpMemberId());
		}
		sof.setUpdateDate(new Date());
		sof.setUpdateUser(ThreadContextHolder.getCurrentUser().getMemberId());
	}
}
