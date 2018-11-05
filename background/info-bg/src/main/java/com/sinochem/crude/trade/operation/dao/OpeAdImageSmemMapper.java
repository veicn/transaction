package com.sinochem.crude.trade.operation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.operation.domain.OpeAdImageSmem;

public interface OpeAdImageSmemMapper {
	public List<OpeAdImageSmem> queryByEntitys(OpeAdImageSmem entity);

	public OpeAdImageSmem findByPrimaryKey(@Param("imageId") String imageId);

	public void updateRecord(OpeAdImageSmem entity);

	public void insertRecord(OpeAdImageSmem entity);

	public void deleteRecordByKey(@Param("imageId") String imageId, @Param("updateUser") String updateUser);

	public List<Map<String, Object>> queryRecords(Map<String, Object> map);

	public int countRecords(Map<String, Object> map);

	public void deleteRecords(Map<String, Object> map);

	public void updateRecords(Map<String, Object> map);

	// ********************************************************/
	public List<String> queryImageDes();

	public List<String> queryTypeCode();
	
	public List<String> queryPageCode(@Param("typeCode") String typeCode);

	public List<OpeAdImageSmem> queryAdimageListByOpeAdImage(Map<String, Object> beanToMap);

	public Page<Map<String, Object>> adImagelList(Map<String, Object> beanToMap);

}
