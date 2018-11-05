package com.sinochem.crude.trade.info.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.info.domain.EFractionMsg;
//import org.apache.ibatis.annotations.Mapper;
import com.sinochem.crude.trade.info.model.EFractionMsgVO;

//@Mapper
public interface EFractionMsgMapper {

	public int insertRecord(EFractionMsg entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(EFractionMsg entity);
	
	public int updateRecordById(EFractionMsg entity);
	
	public int updateRecordByUuid(EFractionMsg entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public EFractionMsg findByPrimaryKey( Long id);	
	
	public EFractionMsg findByUuid(String uuid);	
	
	//返回对象的List
	public List<EFractionMsg> queryByEntitys(EFractionMsg entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	
	//**************************以下方法为开发者补充*********************************/
	
	//馏分信息查询
	public List<Map<String, Object>> queryeFractionMsg(@Param("crudeNameE")String crudeNameE);
	
}
