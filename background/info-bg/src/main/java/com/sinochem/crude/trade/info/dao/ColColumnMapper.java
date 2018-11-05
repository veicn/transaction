package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.info.domain.ColColumn;
import com.sinochem.crude.trade.info.domain.ColSubscribe;
import com.sinochem.crude.trade.info.model.ColumnDetailVO;

//@Mapper
public interface ColColumnMapper {
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	public int countRecords(Map<String,Object> map);
	public void deleteRecords(Map<String,Object> map); 
	public void updateRecords(Map<String,Object> map);
	//**************************以下方法为开发者补充*********************************/
	public Page<Map<String, Object>> queryColumnList(Map<String, Object> beanToMap);
	public ColumnDetailVO findById(Map<String, Object> map);
	public void updateColumnSubscribeCount(Map<String, Object> beanToMap);
	public ColSubscribe findSubscribeById(String id);
	public ColColumn findColumnByInfoId(Long id);
	
}
