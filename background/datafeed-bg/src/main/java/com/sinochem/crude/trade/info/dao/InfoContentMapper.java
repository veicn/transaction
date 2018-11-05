package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.InfoContent;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface InfoContentMapper {

	public int insertRecord(InfoContent entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(InfoContent entity);
	
	public int updateRecordById(InfoContent entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public InfoContent findByPrimaryKey( Long id);	
	
	public InfoContent findByUuid(String uuid);	
	
	//返回对象的List
	public List<InfoContent> queryByEntitys(InfoContent entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 查找公告
	 */
	public InfoContent findByInfoId(Long infoId);
	
	/**
	 * 更新infocontent表中标题和内容
	 * @return
	 */
	public int updateInfoContentTexAndTextHtml(InfoContent infoContent);
	
	/**
	 * 根据资讯ID删除资讯内容
	 * @param infoId
	 * @return
	 */
	public int deleteByInfoContent(InfoContent infoContent);
}
