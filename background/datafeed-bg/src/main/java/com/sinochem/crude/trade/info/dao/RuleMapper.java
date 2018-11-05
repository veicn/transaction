package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.Rule;

//@Mapper
public interface RuleMapper {

	public int insertRecord(Rule entity);

	public int deleteById(@Param("id") Long id);

	public int deleteRecords(Rule entity);

	public int updateRecordById(Rule entity);

	public int updateRecords(Map<String, Object> map);

	public Rule findByPrimaryKey(Long id);

	public Rule findByUuid(String uuid);

	// 返回对象的List
	public List<Rule> queryByEntitys(Rule entity);

	// 返回Map的List
	public List<Map<String, Object>> queryRecords(Map<String, Object> map);

	public int countRecords(Map<String, Object> map);

	// **************************以下方法为开发者补充*********************************/
	public int updateRecordByUuid(Rule entity);

	public List<Map<String, Object>> listRule(Rule param);
}
