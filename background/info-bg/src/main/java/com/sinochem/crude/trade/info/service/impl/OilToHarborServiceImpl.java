package com.sinochem.crude.trade.info.service.impl;

import java.util.Map;
import java.io.Closeable;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.info.domain.OilToHarbor;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.OilToHarborMapper;
import com.sinochem.crude.trade.info.service.OilToHarborService;
import com.sinochem.crude.trade.member.user.CurrentUser;

import jodd.util.StringUtil;

@Service
public class OilToHarborServiceImpl implements OilToHarborService {
	@Autowired
	private OilToHarborMapper oilToHarborMapper;
	
	public OilToHarborMapper getMapper(){
		return oilToHarborMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OilToHarbor oiltoharbor){
		oiltoharbor.setUuid(KeyGenUtils.newUuid());
		oiltoharbor.setAliveFlag(Constants.ALIEVE_FLAG);
		oiltoharbor.setCreateDate(DateTimeUtils.currentDate());
		 return oilToHarborMapper.insertRecord(oiltoharbor);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return oilToHarborMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OilToHarbor  record){
    	return oilToHarborMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OilToHarbor oilToHarbor) throws BizException{
		if ( oilToHarbor.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return oilToHarborMapper.updateRecordById(oilToHarbor);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(OilToHarbor oilToHarbor) throws BizException{
		if ( oilToHarbor.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return oilToHarborMapper.updateRecordByUuid(oilToHarbor);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return oilToHarborMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OilToHarbor oilToHarbor){
		return oilToHarborMapper.updateRecords(oilToHarbor.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OilToHarbor findByPrimaryKey(Long id){
		return  oilToHarborMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OilToHarbor findByUuid(String uuid){
		return  oilToHarborMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OilToHarbor> queryByEntitys(OilToHarbor oilToHarbor){
		return  oilToHarborMapper.queryByEntitys(oilToHarbor);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return oilToHarborMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) oilToHarborMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return oilToHarborMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	@Override
	public Result saveOrUpdate(OilToHarbor oilToHarbor, CurrentUser user) {
		Result res=new Result();
		if(oilToHarbor.getId()==null) {
			//新增
			oilToHarbor.setCreateUser(user.getName());
			if(this.insertRecord(oilToHarbor) == 1){
				res.setMessage("新增成功");
			}
			return res;
		}else {
			oilToHarbor.setUpdateUser(user.getName());
			if(this.updateRecordById(oilToHarbor) == 1){
				res.setMessage("修改成功");
			}
			return res;
		}
	}

	@Override
	public ResultDatas<Void> importExcel(InputStream inputStream, CurrentUser user,ResultDatas<Void> res) {
		
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
				HSSFCell ext2 = row.getCell(0);// 品号
				HSSFCell ext3= row.getCell(1);// 数量(吨)
				HSSFCell ext4 = row.getCell(2);// 船名
				HSSFCell ext5 = row.getCell(3);// 船运公司
				HSSFCell ext6 = row.getCell(4);// 来源
				HSSFCell ext7 = row.getCell(5);// 接货商
				HSSFCell ext8 = row.getCell(6);// 贸易方式
				HSSFCell ext9 = row.getCell(7);// 预抵/出时间
				
				OilToHarbor oil = new OilToHarbor();
				oil.setProductionNumber(ext2+"");
				if(ext3 !=null){
					oil.setQuantity(new BigDecimal(ext3+""));
				}else{
					res.setFail("前"+(rowIndex-1)+"行执行成功，第"+(rowIndex)+"行数量格式错误");
					return res;
				}
				oil.setShipName(ext4+"");
				oil.setPierOrCompany(ext5+"");
				oil.setSource(ext6+"");
				oil.setReceiver(ext7+"");
				oil.setTradingWay(ext8+"");
				oil.setEstimatedArrival(ext9+"");
				this.insertRecord(oil);
			}
			 // 操作完毕后，记得要将打开的 HSSFWorkbook 关闭
			 ((Closeable) workbook).close(); 
		 }catch(Exception e){
			 res.setMessage("导入数据为空");
			 res.setStatus(Result.EEROR);
		 }
		return res;
	}
}
