package com.sinochem.crude.trade.wechat.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.wechat.domain.UserRelation;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface UserRelationMapper {

	public int insertRecord(UserRelation entity);
	
	public int deleteById(@Param("wechatUserId") Long wechatUserId);
	
	public int deleteRecords(UserRelation entity);
	
	public int updateRecordById(UserRelation entity);
	
	public int updateRecordByUuid(UserRelation entity);
	
	public int updateRecords(Map<String, Object> map);
	
	public UserRelation findByPrimaryKey(Long wechatUserId);
	
	public UserRelation findByUuid(String uuid);	
	
	//返回对象的List
	public List<UserRelation> queryByEntitys(UserRelation entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String, Object> map);
	
	public int countRecords(Map<String, Object> map);
	
	//**************************以下方法为开发者补充*********************************/

	 UserRelation findByOpenid(String openid);
	 
	 int deleteRecordByOpenid(UserRelation userRelation);
}
