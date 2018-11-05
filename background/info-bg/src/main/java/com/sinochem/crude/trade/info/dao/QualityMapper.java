package com.sinochem.crude.trade.info.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.info.domain.ChannelM;
import com.sinochem.crude.trade.info.domain.Quality;
import com.sinochem.crude.trade.info.model.CrudeVO;
import com.sinochem.crude.trade.info.model.PIMSVo;
//import org.apache.ibatis.annotations.Mapper;
import com.sinochem.crude.trade.info.query.CrudeQuery;

//@Mapper
public interface QualityMapper {

	public int insertRecord(Quality entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(Quality entity);
	
	public int updateRecordById(Quality entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public Quality findByPrimaryKey( Long id);	
	
	public Quality findByUuid(String uuid);	
	
	//返回对象的List
	public List<Quality> queryByEntitys(Quality entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 查询列表主频道
	 */
	public List<Map<String,Object>> crudeRecords(Map<String,Object> map);
	
	/**
	 * 原油删除
	 */	
	public int crudeDeleteById(String uuid);
	
	/**
	 * 原油新增
	 */
	public int crudeOilAdd(CrudeVO vo);
	
	/**
	 * 原油修改
	 */
	public int crudeOilUpdate(CrudeVO vo);
	
	/**
	 * 外部接口
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> queryQuality(PIMSVo vo);
}
