package com.sinochem.crude.trade.info.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.info.domain.InfoAttachment;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface InfoAttachmentMapper {

	public int insertRecord(InfoAttachment entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(InfoAttachment entity);
	
	public int updateRecordById(InfoAttachment entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public InfoAttachment findByPrimaryKey( Long id);	
	
	public InfoAttachment findByUuid(String uuid);	
	
	//返回对象的List
	public List<InfoAttachment> queryByEntitys(InfoAttachment entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 删除附件
	 */
	public int deleteInfoAttachment(InfoAttachment item);
	
}
