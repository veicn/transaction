package com.sinochem.crude.trade.shipping.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.model.query.ConfirmationSheetQuery;

//@Mapper
public interface ConfirmationSheetMapper {

	public int insertRecord(ConfirmationSheet entity);

	public int deleteById(@Param("confirmationSheetId") Long confirmationSheetId);

	public int deleteRecords(ConfirmationSheet entity);

	public int updateRecordById(ConfirmationSheet entity);

	public int updateRecordByUuid(ConfirmationSheet entity);

	public int updateRecords(Map<String, Object> map);

	public ConfirmationSheet findByPrimaryKey(Long confirmationSheetId);

	public ConfirmationSheet findByUuid(String uuid);

	// 返回对象的List
	public List<ConfirmationSheet> queryByEntitys(ConfirmationSheet entity);

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
	public List<ConfirmationSheet> queryConfirmationSheetList(ConfirmationSheetQuery confirmationSheetQuery);

	/**
	 * 查询列表集合 返回query对象（泉炼）
	 *
	 * @param voyageStartQuery
	 * @return
	 */
	public List<ConfirmationSheet> queryConfirmationQuanhuaSheetList(ConfirmationSheetQuery confirmationSheetQuery);

	/**
	 * 逻辑删除
	 * 
	 * @param voyageStartQuery
	 * @return
	 */
	public Integer deleteLogicConfirmationSheet(ConfirmationSheet confirmationSheet);

	/**
	 *根据关键字查询数据
	 * @return
	 */
	public List<ConfirmationSheet> findByKeywrokds(ConfirmationSheetQuery query);

	public List<ConfirmationSheet> queryByEntitys2(ConfirmationSheet confirmationSheet);

	/**
	 * 更新确认单信息的状态 -武刚鹏-2018年3月20日10:13:45
	 * @param confirmationSheet
	 * @return
	 */
	int updateStatusByconfirmationId(ConfirmationSheet confirmationSheet);

	/**
	 * 根据协议UUID查询确认单信息
	 * @param agreementUuid
	 * @return
	 */
	ConfirmationSheet findByAgreementUuid(String agreementUuid);

	/**
	 * 根据需求UUID查询确认单信息
	 * @param demandsUuid
	 * @return
	 */
	ConfirmationSheet findBydemandsUuid(String demandsUuid);

	/**
	 * 根据协议ID查询确认单信息
		 * 
		 * @param 
		 * @param 
		 * @return
	 */
	public ConfirmationSheet findByAgreementId(@Param("id") Long id);

	int updateFileByOrderId(ConfirmationSheet confirmationSheet);
}
