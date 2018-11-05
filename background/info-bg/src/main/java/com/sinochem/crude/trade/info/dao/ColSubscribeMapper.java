package com.sinochem.crude.trade.info.dao;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.info.domain.ColSubscribe;
import com.sinochem.crude.trade.info.model.ColSubscribeVO;

//@Mapper
public interface ColSubscribeMapper {
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	public int countRecords(Map<String,Object> map);
	public void deleteRecords(Map<String,Object> map); 
	public void updateRecords(Map<String,Object> map);
	//**************************以下方法为开发者补充*********************************/
	public Page<Map<String, Object>> mySubscribeQuery(Map<String, Object> map);
	
	public Page<Map<String, Object>> mySubscribeInfoQuery(Map<String, Object> map);
	public void updateByColumnId(ColSubscribe sub);
	public List<ColSubscribe> findByColId(String id);
	public List<ColSubscribe> findSubscribeByColumnId(ColSubscribeVO colSubscribeVO);
	
}
