package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.info.domain.CrudeBasePrice;
import com.sinochem.crude.trade.info.model.PIMSVo;

//@Mapper
public interface CrudeBasePriceMapper {

	public int insertRecord(CrudeBasePrice entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(CrudeBasePrice entity);
	
	public int updateRecordById(CrudeBasePrice entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public CrudeBasePrice findByPrimaryKey( Long id);	
	
	public CrudeBasePrice findByUuid(String uuid);	
	
	//返回对象的List
	public List<CrudeBasePrice> queryByEntitys(CrudeBasePrice entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * PMIS查询官价
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> queryOfficialPrice(PIMSVo vo);

	public List<String> getDateList(@Param("startDate")String startDate);

	/*public List<Map<String, Object>> getFrontOfficialList(@Param("sb")String sb);*/
	public Page<Map<String, Object>> getFrontOfficialList(Map<String, String> queryMap);

	public List<String> queryOfficalOilList(String pricingDate);

	/*public List<Map<String, Object>> queryOfficalYear(@Param("pricingDate")String pricingDate, @Param("crudeNameE")String crudeNameE);*/

	public List<Map<String, Object>> queryOfficalYear(Map<String, Object> crudeNameE);
}
