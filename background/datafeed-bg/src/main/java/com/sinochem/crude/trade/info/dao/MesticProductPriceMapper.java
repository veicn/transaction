package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.MesticProductPrice;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface MesticProductPriceMapper {

	public int insertRecord(MesticProductPrice entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(String uuid);
	
	public int updateRecordById(MesticProductPrice entity);
	
	public int updateRecords(MesticProductPrice entity);
	
	public MesticProductPrice findByPrimaryKey( Long id);	
	
	public MesticProductPrice findByUuid(String uuid);	
	
	//返回对象的List
	public List<MesticProductPrice> queryByEntitys(MesticProductPrice entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	// 查询产品code
	public String findByProduceName(String produceName);
	
	// 导出数据
	public List<MesticProductPrice> dataExport();
	
	// 查询主键是否重复
	public List<MesticProductPrice> primateKey(MesticProductPrice entity);

	public List<MesticProductPrice> findTotal(MesticProductPrice mesticproductprice);

}
