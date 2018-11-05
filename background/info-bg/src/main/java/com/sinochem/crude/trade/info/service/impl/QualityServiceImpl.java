package com.sinochem.crude.trade.info.service.impl;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoService;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import com.sinochem.crude.trade.info.dao.QualityMapper;
import com.sinochem.crude.trade.info.domain.Quality;
import com.sinochem.crude.trade.info.model.CrudeVO;
import com.sinochem.crude.trade.info.model.PIMSVo;
import com.sinochem.crude.trade.info.query.CrudeQuery;
import com.sinochem.crude.trade.info.service.QualityService;
import com.sinochem.crude.trade.member.user.CurrentUser;

@Service
public class QualityServiceImpl implements QualityService {
	@Autowired
	private QualityMapper qualityMapper;
	/*@Autowired
	CrudeOilInfoService crudeOilInfoService;*/
	
	public QualityMapper getMapper(){
		return qualityMapper;
	} 
	private Log log = LogFactory.getLog(getClass());
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(Quality quality){
		
		 return qualityMapper.insertRecord(quality);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return qualityMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(Quality  record){
    	return qualityMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(Quality quality) throws BizException{
		if ( quality.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return qualityMapper.updateRecordById(quality);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return qualityMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Quality quality){
		return qualityMapper.updateRecords(quality.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public Quality findByPrimaryKey(Long id){
		return  qualityMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public Quality findByUuid(String uuid){
		return  qualityMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<Quality> queryByEntitys(Quality quality){
		return  qualityMapper.queryByEntitys(quality);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return qualityMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) qualityMapper.crudeRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return qualityMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/

	@Override
	public Page<Map<String, Object>> crudeRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) qualityMapper.crudeRecords(map);
	}
	
	/**
	 * 根据条件-逻辑删除
	 */
	@Override
	public int crudeDelete(String uuid){
		
		return qualityMapper.crudeDeleteById(uuid);
	};
	
	/**
	 * 原油新增
	 */
	@Override
	public int crudeInsert(CrudeVO vo){
		
		String uuid = UUID.randomUUID().toString().replace("-", "");
		vo.setUuid(uuid);
		vo.setCreateDate(DateTimeUtils.currentDate());
		vo.setModifyDate(DateTimeUtils.currentDate());
		int crudeOilAdd = qualityMapper.crudeOilAdd(vo);
		/*try {
			CrudeOilInfoVO crudeOilInfoVO=new CrudeOilInfoVO();
			crudeOilInfoVO.setCrudeNameE(vo.getExtend4());
			crudeOilInfoVO.setCrudeNameC(vo.getExtend5());
			crudeOilInfoVO.setCatagoryNameC(vo.getExtend3());
			crudeOilInfoVO.setOriginNameC(vo.getExtend2());
			crudeOilInfoVO.setAreaNameC(vo.getExtend1());
			crudeOilInfoVO.setSimpleDate(vo.getSimpleDate());
			crudeOilInfoVO.setApi(vo.getApi());
			crudeOilInfoVO.setSulphur(vo.getSulphur());
			crudeOilInfoVO.setAcidity(vo.getAcidity());
			crudeOilInfoVO.setFreezingPoint(vo.getFreezingPoint());
			crudeOilInfoVO.setFlashPoint(vo.getFlashPoint());
			crudeOilInfoVO.setViscosity(vo.getViscosity());
			crudeOilInfoVO.setCarbonResidue(vo.getCarbonResidue());
			crudeOilInfoVO.setNickel(vo.getNickel());
			crudeOilInfoVO.setVanadium(vo.getVanadium());
			crudeOilInfoVO.setYield(vo.getYield());
			
			crudeOilInfoService.addCrudeOilInfo(crudeOilInfoVO);
		} catch (Exception e) {
			log.error("对接 插入失败 ");
			log.error("",e);
		}*/
		return crudeOilAdd;
	}
	
	/**
	 * 原油修改
	 */
	@Override
	public int crudeUpdate(CrudeVO vo){
		
		vo.setModifyDate(DateTimeUtils.currentDate());
		vo.setModifyPerson("1");
		return qualityMapper.crudeOilUpdate(vo);
	}

	@Override
	public ResultDatas<Void> crudeOilImport(InputStream inputStream, CurrentUser user) {
		ResultDatas<Void> res = new ResultDatas<Void>();
		
		try{
			 HSSFWorkbook workbook = null;
			 try{
				 workbook = new HSSFWorkbook(inputStream);
			 }catch(Exception e){
				 res.setMessage("导入失败");
			 }
			 
			 inputStream.close();
			 HSSFSheet sheet = workbook.getSheetAt(0);
			 for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				// XSSFRow 代表一行数据
				 HSSFRow row = sheet.getRow(rowIndex);
				if (row == null ) {
					res.setMessage("导入数据为空");
					continue;
				}
				HSSFCell ext1 = row.getCell(0);// 原油英文名
				HSSFCell ext2 = row.getCell(1);// 原油中文名
				HSSFCell ext3 = row.getCell(2);// 区域
				HSSFCell ext4 = row.getCell(3);// 产地
				HSSFCell ext5 = row.getCell(4);// 原油类型
				Date ext6 = row.getCell(5).getDateCellValue();// 品质等级日期
				HSSFCell ext7 = row.getCell(6);// api度
				HSSFCell ext8 = row.getCell(7);// 含硫量
				HSSFCell ext9 = row.getCell(8);// 酸值
				HSSFCell ext10 = row.getCell(9);// 凝点
				HSSFCell ext11 = row.getCell(10);// 闪点
				HSSFCell ext12 = row.getCell(11);// 黏度
				HSSFCell ext13 = row.getCell(12);// 残碳
				HSSFCell ext14 = row.getCell(13);// 镍含量
				HSSFCell ext15 = row.getCell(14);// 钒含量
				HSSFCell ext16 = row.getCell(15);// >350℃质量收率
				
				CrudeVO vo=new CrudeVO();
				Quality quality=new Quality();
				quality.setExtend4(ext1+"");
				quality.setSimpleDate(ext6);
				Long id = null;
				List<Quality> list = this.queryByEntitys(quality);
				if(!CollectionUtils.isEmpty(list)){
					id = list.get(0).getId();
				}
				vo.setExtend4(ext1+"");
				vo.setExtend5(ext2+"");
				vo.setExtend1(ext3+"");
				vo.setExtend2(ext4+"");
				vo.setExtend3(ext5+"");
				vo.setSimpleDate(ext6);
				vo.setApi(new BigDecimal(ext7+""));
				vo.setSulphur(new BigDecimal(ext8+""));
				vo.setAcidity(new BigDecimal(ext9+""));
				vo.setFreezingPoint(ext10+"");
				vo.setFlashPoint(ext11+"");
				vo.setViscosity(ext12+"");
				vo.setCarbonResidue(ext13+"");
				vo.setNickel(ext14+"");
				vo.setVanadium(ext15+"");
				vo.setYield(ext16+"");
				vo.setId(null);
				if(id==null){
					vo.setCreatePerson(user.getName());
					vo.setModifyPerson(user.getName());
					this.crudeInsert(vo);
				}else{
					vo.setId(id);
					this.updateRecordById(vo);
				}
				res.setMessage("导入成功");
			}
			 // 操作完毕后，记得要将打开的 HSSFWorkbook 关闭
			 ((Closeable) workbook).close(); 
		 }catch(Exception e){
			 res.setMessage(e.getMessage());
		 }
		return res;
	}
	public void exportList(HttpServletResponse response){
		List<Map<String, Object>> list = qualityMapper.crudeRecords(null);
		System.out.print(list);
		// 开始导出excel文件
		// 1.创建 一个工作簿 -- excel --默认的名称
		HSSFWorkbook userExcel = new HSSFWorkbook();
		// 获取字体样式
		HSSFFont font = userExcel.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 17);// 设置字体的大小
		font.setColor(IndexedColors.BLUE_GREY.getIndex());// 字体颜色
		font.setItalic(true); // 设置字体为斜体字

		// 样式
		HSSFCellStyle setBorder = userExcel.createCellStyle();// 获取一个样式
		setBorder.setFont(font);
		setBorder.setAlignment(HorizontalAlignment.CENTER); // 居中

		// 第三列样式
		HSSFCellStyle setBorder1 = userExcel.createCellStyle();// 获取一个样式
		setBorder1.setAlignment(HorizontalAlignment.CENTER); // 居中
		setBorder1.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中

		// 创建一个sheet
		HSSFSheet userSheet = userExcel.createSheet("原油品质");

		// 设置列宽，第一个参数代表列id(从0开始),第2个参数代表宽度值 参考 ："2012-08-10"的宽度为2500
		userSheet.setColumnWidth(0, 5000);
		userSheet.setColumnWidth(1, 5000);
		userSheet.setColumnWidth(2, 3766);
		userSheet.setColumnWidth(3, 3766);
		userSheet.setColumnWidth(4, 3766);
		userSheet.setColumnWidth(5, 5000);
		userSheet.setColumnWidth(6, 3766);
		userSheet.setColumnWidth(7, 3766);
		userSheet.setColumnWidth(8, 3766);
		userSheet.setColumnWidth(9, 3766);
		userSheet.setColumnWidth(10, 3766);
		userSheet.setColumnWidth(11, 3766);
		userSheet.setColumnWidth(12, 3766);
		userSheet.setColumnWidth(13, 3766);
		userSheet.setColumnWidth(14, 3766);
		userSheet.setColumnWidth(15, 3766);

		// 设置时间
		HSSFCellStyle DateStyle = userExcel.createCellStyle();
		HSSFDataFormat dataFormat = userExcel.createDataFormat();
		DateStyle.setDataFormat(dataFormat.getFormat("yyyy/mm/dd"));
		DateStyle.setAlignment(HorizontalAlignment.CENTER);
		userSheet.setDefaultColumnStyle(7, DateStyle);
		// 3.通过sheet对象根据下标创建【new】行 row ---创建表头 下标从0 开始
		HSSFRow rowOne = userSheet.createRow(0);
		// 4.通过row对象根据下标创建cell单元格 -- 下标从0开始
		String[] cellArray = new String[] {  "原油英文名", "原油中文名", "区域", "产地" , "原油类型","品质登记日期", "API", "硫含量", "酸值", "凝点", "闪点", "黏度", "残碳", "镍含量", "钒含量", ">350℃质量收率"  };
		for (int i = 0; i < cellArray.length; i++) {
			rowOne.createCell(i).setCellValue(cellArray[i]);
		}
		// 行数的开始位置
		int start = 1;
		if (list != null && list.size() != 0) {
			// 设置眉脚
			HSSFHeader header = (HSSFHeader) userSheet.getHeader();
			header.setLeft("第一页");
			HSSFFooter footer = (HSSFFooter) userSheet.getFooter();
			footer.setRight("总共 " + list.size() + " 条数据 ");
			// 循环 userList 给sheet 的每一行的每一个单元格赋值
			for (int j = 0; j < list.size(); j++) {
				CrudeQuery crudeQuery = (CrudeQuery)list.get(j);
				if (crudeQuery == null) {
					continue;
				}
				// 循环创建行
				HSSFRow row = userSheet.createRow(start);
				// 设置第三列样式
				String date=crudeQuery.getSimpleDate().replace("-", "/");
				row.createCell(0).setCellValue(crudeQuery.getCrudeNameE());
				row.createCell(1).setCellValue(crudeQuery.getCrudeNameC());
				row.createCell(2).setCellValue(crudeQuery.getOriginName());
				row.createCell(3).setCellValue(crudeQuery.getOriginArea());
				row.createCell(4).setCellValue(crudeQuery.getCatagoryName());
				HSSFCell cellData = row.createCell(5);
				cellData.setCellStyle(DateStyle);
				cellData.setCellValue(DateTimeUtils.toDate(date,"yyyy/MM/dd"));
				row.createCell(6).setCellValue(crudeQuery.getApi());
				row.createCell(7).setCellValue(crudeQuery.getSulphur());
				row.createCell(8).setCellValue(crudeQuery.getAcidity());
				row.createCell(9).setCellValue(crudeQuery.getFreezingPoint());
				row.createCell(10).setCellValue(crudeQuery.getFlashPoint());
				row.createCell(11).setCellValue(crudeQuery.getViscosity());
				row.createCell(12).setCellValue(crudeQuery.getCarbonResidue());
				row.createCell(13).setCellValue(crudeQuery.getNickel());
				row.createCell(14).setCellValue(crudeQuery.getVanadium());
				row.createCell(15).setCellValue(crudeQuery.getYield());
				
				
				 
				// 行数的开始位置 自增 +1
				start++;
			}
		} else {
			// 循环 userList 给sheet 的每一行的每一个单元格赋值
			// 循环创建行
			HSSFRow row = userSheet.createRow(start);

			// 设置第三列样式
			Cell cellSex = row.createCell(2);
			cellSex.setCellValue("无");

			// 设置第四列样式
			Cell cellSex3 = row.createCell(3);
			cellSex3.setCellValue("无");

			// 设置第五列样式
			Cell cell2 = row.createCell(4);
			cell2.setCellValue("无");

			row.createCell(5).setCellValue("无");
			row.createCell(6).setCellValue("无");
			row.createCell(7).setCellValue("无");
			row.createCell(8).setCellValue("无");
			// 行数的开始位置 自增 +1
			start++;
		}

		// 创建字节输出流
		ServletOutputStream os = null;
		try {
			response.reset();
			// 不同类型的文件对应不同的MIME类型
			response.setContentType("application/x-msdownload");
			// inline在浏览器中直接显示，不提示用户下载
			// attachment弹出对话框，提示用户进行下载保存本地
			// 默认为inline方式
			String fileName="原油品质";
			response.setHeader("Content-Disposition", "attachment;filename="
					+new String(fileName.getBytes(),"iso-8859-1")+ ".xls");
			// 响应到浏览器的输出流
			os = response.getOutputStream();
			// 写excel工作簿
			userExcel.write(os);
			// 输出到本地磁盘目录
			File f = new File("D:\\原油品质.xls");
			// 创建file文件
			f.createNewFile();
			userExcel.write(f);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					os = null;
				}
			}
			if (userExcel != null) {
				try {
					userExcel.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					userExcel = null;
				}
			}
		}
	}
	
	/**
	 * 外部查询接口
	 * @param vo
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryQuality(PIMSVo vo){
		return qualityMapper.queryQuality(vo);
	}
}
