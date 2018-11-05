package com.sinochem.crude.trade.shipping.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.shipping.domain.LoadingProgress;
//import org.apache.ibatis.annotations.Mapper;
import com.sinochem.crude.trade.shipping.model.query.LoadingProgressQuery;

//@Mapper
public interface LoadingProgressMapper {

	public int insertRecord(LoadingProgress entity);
	
	public int deleteById( @Param("shipLoadingProgressId") Long shipLoadingProgressId);
	
	public int deleteRecords(LoadingProgress entity);
	
	public int updateRecordById(LoadingProgress entity);
	
	public int updateRecordByUuid(LoadingProgress entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public LoadingProgress findByPrimaryKey( Long shipLoadingProgressId);	
	
	public LoadingProgress findByUuid(String uuid);	
	
	//返回对象的List
	public List<LoadingProgress> queryByEntitys(LoadingProgress entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	//**************************以下方法为开发者补充*********************************/
	/**
	 * 根据装货款主键 查询进度表
	 * @param loadingProgressQuery
	 * @return
	 */
	public List<LoadingProgress> queryByEntitysList(LoadingProgressQuery loadingProgressQuery);

	public int deleteLoadPortId(Long shipLoadPortId);

	public int loadPortList(List<LoadingProgress> progreeList);

	//船舶装港进度
	public LoadingProgress findByConfirmationSheetId(Long confirmationSheetId);
	
	//船舶装港进度
	public List<LoadingProgress> findListByConfirmationSheetId(Long confirmationSheetId);


	int findCountByShipLoadportId(Long shipLoadportId);

}
