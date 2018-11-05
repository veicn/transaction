package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.InfoTiming;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface InfoTimingMapper {

	public int insertRecord(InfoTiming entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(InfoTiming entity);
	
	public int updateRecordById(InfoTiming entity);
	
	public int updateRecordByUuid(InfoTiming entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public InfoTiming findByPrimaryKey( Long id);	
	
	public InfoTiming findByUuid(String uuid);	
	
	//返回对象的List
	public List<InfoTiming> queryByEntitys(InfoTiming entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 获取定时列表
	 * @param param
	 * @return
	 */
	public List<InfoTiming> queryTimingList(Map<String, String> param);
	
	
}
