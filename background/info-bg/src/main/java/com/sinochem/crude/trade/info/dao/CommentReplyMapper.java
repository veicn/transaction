package com.sinochem.crude.trade.info.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.info.domain.CommentReply;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface CommentReplyMapper {

	public int insertRecord(CommentReply entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(CommentReply entity);
	
	public int updateRecordById(CommentReply entity);
	
	public int updateRecordByUuid(CommentReply entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public CommentReply findByPrimaryKey( Long id);	
	
	public CommentReply findByUuid(String uuid);	
	
	//返回对象的List
	public List<CommentReply> queryByEntitys(CommentReply entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	public List<Map<String, Object>> queryByCommentId(Map<String, Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
