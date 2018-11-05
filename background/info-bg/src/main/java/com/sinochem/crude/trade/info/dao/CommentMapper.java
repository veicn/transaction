package com.sinochem.crude.trade.info.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.info.domain.Comment;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface CommentMapper {

	public int insertRecord(Comment entity);
	
	public int deleteById(@Param("id") Long id);
	
	public int deleteRecords(Comment entity);
	
	public int updateRecordById(Comment entity);
	
	public int updateRecords(Map<String, Object> map);
	
	public Comment findByPrimaryKey(Long id);
	
	public Comment findByUuid(String uuid);	
	
	//返回对象的List
	public List<Comment> queryByEntitys(Comment entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String, Object> map);
	
	public int countRecords(Map<String, Object> map);

	
	//**************************以下方法为开发者补充*********************************/
	
	public Page<Map<String, Object>> queryCommentList(Map<String, Object> beanToMap);

	public int deleteCommentByUuid(String uuid);

	public Page<Map<String, Object>> queryMyCommentInfo(Map<String, Object> map);
	
}
