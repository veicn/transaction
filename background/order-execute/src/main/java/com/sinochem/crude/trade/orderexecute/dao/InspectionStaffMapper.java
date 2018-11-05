package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.orderexecute.domain.InspectionStaff;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface InspectionStaffMapper {

	public int insertRecord(InspectionStaff entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(InspectionStaff entity);
	
	public int updateRecordById(InspectionStaff entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public InspectionStaff findByPrimaryKey( /* @Param("id") */ Long id);	
	
	public InspectionStaff findByUuid(String uuid);	
	
	//返回对象的List
	public List<InspectionStaff> queryByEntitys(InspectionStaff entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
