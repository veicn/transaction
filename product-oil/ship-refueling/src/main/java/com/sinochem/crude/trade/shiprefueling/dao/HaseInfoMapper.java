package com.sinochem.crude.trade.shiprefueling.dao;

import com.sinochem.crude.trade.shiprefueling.domain.po.HaseInfo;
import com.sinochem.crude.trade.shiprefueling.model.query.HaseInfoQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
public interface HaseInfoMapper {

	public int insertRecord(HaseInfo entity);
	
	public int deleteById( @Param("infoId") Long infoId);
	
	public int deleteRecords(HaseInfo entity);
	
	public int updateRecordById(HaseInfo entity);
	
	public int updateRecordByUuid(HaseInfo entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public HaseInfo findByPrimaryKey( Long infoId);	
	
	public HaseInfo findByUuid(String uuid);	
	
	//返回对象的List
	public List<HaseInfo> queryByEntitys(HaseInfo entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	public int deleteRecordByUuid(String uuid);
	
	public List<Map<String,Object>> queryHaseInfoRecords(Map<String,Object> map);


	/**
	 * 查询记录 根据创建时间倒序排 武刚鹏 -2018年5月28日17:45:34
	 * @return
	 */
	List<HaseInfo> selectNewHashInfoList();


	List<HaseInfo> queryByEntitysByPage(HaseInfoQuery haseInfoQuery);

}
