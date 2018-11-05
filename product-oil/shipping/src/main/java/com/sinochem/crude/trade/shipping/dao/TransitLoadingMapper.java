package com.sinochem.crude.trade.shipping.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.shipping.domain.TransitLoading;
//import org.apache.ibatis.annotations.Mapper;
import com.sinochem.crude.trade.shipping.model.query.TransitLoadingQuery;
import com.sinochem.it.b2b.common.exception.BizException;

//@Mapper
public interface TransitLoadingMapper {

	public int insertRecord(TransitLoading entity);
	
	public int deleteById( @Param("transitLoadingId") Long transitLoadingId);
	
	public int deleteRecords(TransitLoading entity);
	
	public int updateRecordById(TransitLoading entity);
	
	public int updateRecordByUuid(TransitLoading entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public TransitLoading findByPrimaryKey( Long transitLoadingId);	
	
	public TransitLoading findByUuid(String uuid);	
	
	//返回对象的List
	public List<TransitLoading> queryByEntitys(TransitLoading entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	
	//**************************以下方法为开发者补充*********************************/
	
	public List<TransitLoading> queryRecordsList(TransitLoadingQuery domain);

	public Integer transitLocigDelete(TransitLoading domain) throws BizException;

	/**
	 * 新增装港前在途中
	 * @param domain
	 * @return
	 */
	public Integer insertTransit(TransitLoading domain);

	//查看航程信息
	public List<TransitLoading> findByShipConfirmationSheetId(Long confirmationSheetId);

	/**
	 * 删除到港前在途中信息
	 * @param confirmationSheetId
	 */
	public void deleteByConfiId(Long confirmationSheetId);

	/**
	 * 根据确认单ID查询在途信息的条数
	 * @param confirmationSheetId
	 * @return
	 */
	int findCountByconfirmationSheetId(Long confirmationSheetId);
	
}
