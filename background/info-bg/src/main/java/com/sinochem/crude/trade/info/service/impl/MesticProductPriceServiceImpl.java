package com.sinochem.crude.trade.info.service.impl;

import java.io.Closeable;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.info.dao.MesticProductPriceMapper;
import com.sinochem.crude.trade.info.domain.MesticProductPrice;
import com.sinochem.crude.trade.info.model.PIMSVo;
import com.sinochem.crude.trade.info.service.MesticProductPriceService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.values.domain.SysCodeSet;
import com.sinochem.crude.trade.values.service.SysCodeSetService;

@Service
public class MesticProductPriceServiceImpl implements MesticProductPriceService {
	@Autowired
	private MesticProductPriceMapper mesticProductPriceMapper;
	@Autowired
	private SysCodeSetService codeSetService;
	
	public MesticProductPriceMapper getMapper(){
		return mesticProductPriceMapper;
	} 
	
	private static final Log log = LogFactory.getLog(MesticProductPriceServiceImpl.class);
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(MesticProductPrice mesticproductprice){
		
		String uuid = UUID.randomUUID().toString().replace("-", "");
		mesticproductprice.setUuid(uuid);
		mesticproductprice.setCreateDate(DateTimeUtils.currentDate());
		mesticproductprice.setUpdateDate(DateTimeUtils.currentDate());
		//TODO 拿到 创建人ID
		mesticproductprice.setCreateUser("1");
		mesticproductprice.setUpdateUser("1");
		
		return mesticProductPriceMapper.insertRecord(mesticproductprice);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return mesticProductPriceMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(String uuid){
    	return mesticProductPriceMapper.deleteRecords(uuid);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(MesticProductPrice mesticProductPrice) throws BizException{
		if ( mesticProductPrice.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return mesticProductPriceMapper.updateRecordById(mesticProductPrice);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(MesticProductPrice mesticProductPrice){
		return mesticProductPriceMapper.updateRecords(mesticProductPrice);
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public MesticProductPrice findByPrimaryKey(Long id){
		return  mesticProductPriceMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public MesticProductPrice findByUuid(String uuid){
		return  mesticProductPriceMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<MesticProductPrice> queryByEntitys(MesticProductPrice mesticProductPrice){
		return  mesticProductPriceMapper.primateKey(mesticProductPrice);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return mesticProductPriceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) mesticProductPriceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return mesticProductPriceMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 新增了批量导入的方法
	 */
	@SuppressWarnings("resource")
	@Override
	public ResultDatas<Void> senWdImport(InputStream inputStream,CurrentUser user) {
		
		ResultDatas<Void> res = new ResultDatas<>();
		
		try{
			 XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			 inputStream.close();
			 XSSFSheet sheet = workbook.getSheetAt(0);
			 for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				 MesticProductPrice prodectPrice = new MesticProductPrice();
				// XSSFRow 代表一行数据
				XSSFRow row = sheet.getRow(rowIndex);
				if (row == null) {
					res.setMessage("导入失败");
					return res;
				}
				
				XSSFCell ext2 = row.getCell(0);// 产品名称
				XSSFCell ext3 = row.getCell(1);// 最高价格
				XSSFCell ext4 = row.getCell(2);// 最低价格
				XSSFCell ext5 = row.getCell(3);// 价格单位
				XSSFCell ext6 = row.getCell(4);// 价格日期
				XSSFCell ext7 = row.getCell(5);// 区域名称
				XSSFCell ext8 = row.getCell(6);// 价格来源
				XSSFCell ext9 = row.getCell(7);// 价格趋势
				XSSFCell ext10 = row.getCell(8);// 最高价格变动
				XSSFCell ext11 = row.getCell(9);// 最低价格变动
				
				
				SysCodeSet sysCodeSet = new SysCodeSet();
				sysCodeSet.setSetCode("DOMESTIC_PRODUCT");
				sysCodeSet.setItemName(ext2+"");
				// 查询产品编号
				List<SysCodeSet> produceCode = codeSetService.queryByEntitys(sysCodeSet);
				
				if(produceCode.isEmpty()) {
					res.setMessage("产品名称不合法");
					return res;
				}
				// 价格趋势
				String priceTrend = "";
				if("持平".equals(ext9+"")) {
					priceTrend = "0";
				} else if("下跌".equals(ext9+"")) {
					priceTrend = "-1";
				} else if("上涨".equals(ext9+"")){
					priceTrend = "1";
				} else {
					res.setMessage("价格趋势不合法");
					return res;
				}
				
				prodectPrice.setProductCode(produceCode.get(0).getItemCode());
				prodectPrice.setProductName(ext2+"");
				// 转BigDecimal
				BigDecimal bd=new BigDecimal(ext3+"");
				prodectPrice.setHighPrice(bd);
				bd=new BigDecimal(ext4+"");
				prodectPrice.setLowPrice(bd);
				prodectPrice.setPriceUnit(ext5+"");
				prodectPrice.setPriceDate(ext6.getDateCellValue());
				prodectPrice.setAreaName(ext7+"");
				prodectPrice.setPriceSource(ext8+"");
				prodectPrice.setPriceTrend(priceTrend);
				// 转BigDecimal
				bd=new BigDecimal(ext10+"");
				prodectPrice.setHighPriceChange(bd);
				bd=new BigDecimal(ext11+"");
				prodectPrice.setLowPriceChange(bd);
				prodectPrice.setCreateUser("");
				prodectPrice.setCreateDate(DateTimeUtils.currentDate());
				prodectPrice.setUpdateUser("");
				prodectPrice.setUpdateDate(DateTimeUtils.currentDate());
				
				// 查询数据库数据
				List<MesticProductPrice> keyFlag = mesticProductPriceMapper.primateKey(prodectPrice);
				
				if(keyFlag.isEmpty()) {
					// 新建uuid
					prodectPrice.setUuid(KeyGenUtils.newUuid());
					// 新增
					mesticProductPriceMapper.insertRecord(prodectPrice);
				} else {
					// 获取uuid
					prodectPrice.setUuid(keyFlag.get(0).getUuid());
					// 主键重复执行更新
					mesticProductPriceMapper.updateRecords(prodectPrice);
				}
						
			}
			 // 操作完毕后，记得要将打开的 XSSFWorkbook 关闭
			 workbook.close(); 
		 }catch(Exception e){
			 log.error("", e);
			 res.setMessage("导入失败" + e.getMessage());
		 }
		return res;
	}

	
	@Override
	public List<MesticProductPrice> dataExport() {
		
		List<MesticProductPrice> res = null;
		
		res = mesticProductPriceMapper.dataExport();
		
		return res;
		
	}
	
	/**
	 * PMIS查询厂家报价
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> queryVendPrice(PIMSVo vo){
		return mesticProductPriceMapper.queryVendPrice(vo);
	}
	
}
