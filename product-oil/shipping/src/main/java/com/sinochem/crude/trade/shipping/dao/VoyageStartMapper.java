package com.sinochem.crude.trade.shipping.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.shipping.domain.VoyageStart;
import com.sinochem.crude.trade.shipping.model.query.VoyageStartQuery;
import com.sinochem.crude.trade.shipping.model.vo.VoyageStartVO;

//@Mapper
public interface VoyageStartMapper {

	public int insertRecord(VoyageStart entity);

	public int deleteById(@Param("voyageStartId") Long voyageStartId);

	public int deleteRecords(VoyageStart entity);

	public int updateRecordById(VoyageStart entity);

	public int updateRecordByUuid(VoyageStart entity);

	public int updateRecords(Map<String, Object> map);

	public VoyageStart findByPrimaryKey(Long voyageStartId);

	public VoyageStart findByUuid(String uuid);

	// 返回对象的List
	public List<VoyageStart> queryByEntitys(VoyageStart entity);

	// 返回Map的List
	public List<Map<String, Object>> queryRecords(Map<String, Object> map);

	public int countRecords(Map<String, Object> map);

	// **************************以下方法为开发者补充*********************************/
	/**
	 * 查询列表集合 返回query对象
	 * 
	 * @param voyageStartQuery
	 * @return
	 */
	public List<VoyageStartVO> queryByEntitysList(VoyageStartQuery voyageStartQuery);

	/**
	 * 逻辑删除
	 * 
	 * @param voyageStartQuery
	 * @return
	 */
	public Integer VoyageLogicDelete(VoyageStartQuery voyageStartQuery);
	

	/**
	 * 查询配载计划
	 * @param confirmationSheetId
	 * @return
	 */
	public VoyageStart findByShipConfirmationSheetId(Long confirmationSheetId);


	int updateFileByOrderId(VoyageStart voyageStart);

	int updateDiFileByOrderId(VoyageStart voyageStart);

}
