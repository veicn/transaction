package com.sinochem.crude.trade.info.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.info.domain.Share;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface ShareMapper {

	public int insertRecord(Share entity);
	
	public int deleteById(@Param("id") Long id);
	
	public int deleteRecords(Share entity);
	
	public int updateRecordById(Share entity);
	
	public int updateRecords(Map<String, Object> map);
	
	public Share findByPrimaryKey(Long id);
	
	public Share findByUuid(String uuid);	
	
	//返回对象的List
	public List<Share> queryByEntitys(Share entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String, Object> map);
	
	public int countRecords(Map<String, Object> map);
	
	//**************************以下方法为开发者补充*********************************/

	/**
	 * 分享资讯列表
	 */
	public List<Share> queryShareList(Share share);
}
