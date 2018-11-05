package com.sinochem.crude.trade.shiprefueling.dao;


import com.sinochem.crude.trade.shiprefueling.domain.po.Chments;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface ChmentsMapper {

	public int insertRecord(Chments entity);
	
	public int deleteById( @Param("attachmentId") Long attachmentId);
	
	public int deleteRecords(Chments entity);
	
	public int updateRecordById(Chments entity);
	
	public int updateRecordByUuid(Chments entity);
	
	public int updateRecords(Map<String,Object> map);

	public int updateAvFlag(Map<String , Object> map);
	
	public Chments findByPrimaryKey( @Param("attachmentId") Long attachmentId);
	
	public Chments findByUuid(String uuid);	
	
	//返回对象的List
	public List<Chments> queryByEntitys(Chments entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	public int deleteRecordByUuid(String uuid);

    List<Chments> findByIdAndType(@Param(value = "id") Long id, @Param(value = "type") String businessType);

    List<Chments> findByOrderId(long orderId);


	List<Chments> selectChmentListByForeignId(Long infoId);

	int batchInsert(List<Chments> chmentsList);
}
