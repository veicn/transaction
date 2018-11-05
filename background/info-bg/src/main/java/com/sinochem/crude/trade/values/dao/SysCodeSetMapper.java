package com.sinochem.crude.trade.values.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.values.domain.SysCodeSet;
import com.sinochem.crude.trade.values.domain.SysCodeSetForUpdate;
import com.sinochem.crude.trade.values.domain.SysCodeSetOnlyGroup;

public interface SysCodeSetMapper {
	public List<SysCodeSet> queryByEntitys(SysCodeSet entity);

	public SysCodeSet findByPrimaryKey(@Param("id") String id);

	public void updateRecord(SysCodeSet entity);

	public void insertRecord(SysCodeSet entity);

	public void deleteRecordByKey(@Param("id") String id, @Param("updateUser") String updateUser);

	List<Map<String, Object>> queryRecords(Map<String, Object> map);

	int countRecords(Map<String, Object> map);

	void deleteRecords(Map<String, Object> map);

	void updateRecords(Map<String, Object> map);

	// **************************以下方法为开发者补充*********************************/

	List<SysCodeSet> queryInGroup(SysCodeSet entity);

	List<SysCodeSetOnlyGroup> queryDistinctGroup(SysCodeSet entity);

	String queryEditable(SysCodeSet entity);

	void updateSetCode(SysCodeSetForUpdate entity);

	public List<String>  getByCodeSet(SysCodeSet code);
}
