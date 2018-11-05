package com.sinochem.crude.trade.operation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.operation.domain.OpeAdImageSet;

public interface OpeAdImageSetMapper {
	public List<OpeAdImageSet> queryByEntitys(OpeAdImageSet entity);

	public OpeAdImageSet findByPrimaryKey(@Param("adSetId") String adSetId);

	public void updateRecord(OpeAdImageSet entity);

	public void insertRecord(OpeAdImageSet entity);

	public void deleteRecordByKey(@Param("adSetId") String adSetId, @Param("updateUser") String updateUser);

	public List<Map<String, Object>> queryRecords(Map<String, Object> map);

	public int countRecords(Map<String, Object> map);

	public void deleteRecords(Map<String, Object> map);

	public void updateRecords(Map<String, Object> map);
	// **************************以下方法为开发者补充*********************************/

}
