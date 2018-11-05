package com.sinochem.crude.trade.shipping.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.shipping.domain.TransitUnloading;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface TransitUnloadingMapper {

	public int insertRecord(TransitUnloading entity);
	
	public int deleteById( @Param("transitUnloadingId") Long transitUnloadingId);
	
	public int deleteRecords(TransitUnloading entity);
	
	public int updateRecordById(TransitUnloading entity);
	
	public int updateRecordByUuid(TransitUnloading entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public TransitUnloading findByPrimaryKey( Long transitUnloadingId);	
	
	public TransitUnloading findByUuid(String uuid);	
	
	//返回对象的List
	public List<TransitUnloading> queryByEntitys(TransitUnloading entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	
	//**************************以下方法为开发者补充*********************************/

	/**
	 * 根据确认单ID查询卸港在途信息-武刚鹏-2018年3月19日19:34:46
	 * @param confirmationSheetId
	 * @return
	 */
	public List<TransitUnloading> findByConfirmationSheetId(Long confirmationSheetId);

	/**
	 * 根据uuid删除船舶在途信息 -武刚鹏-2018年3月19日19:33:45
	 * @param uuid
	 * @return
	 */
	int deleteRecordsByUuid(String uuid);
}
