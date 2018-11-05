package com.sinochem.crude.trade.operation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.operation.domain.MemMenuSmem;
import com.sinochem.crude.trade.operation.vo.MenuOutPut;

public interface MemMenuSmemMapper {
	public List<MemMenuSmem> queryByEntitys(MemMenuSmem entity);

	public MemMenuSmem findByPrimaryKey(@Param("id") String id);

	public void updateRecord(MemMenuSmem entity);

	public void insertRecord(MemMenuSmem entity);

	public void deleteRecordByKey(@Param("id") String id, @Param("updateUser") String updateUser);

	public List<Map<String, Object>> queryRecords(Map<String, Object> map);

	public int countRecords(Map<String, Object> map);

	public void deleteRecords(Map<String, Object> map);

	public void updateRecords(Map<String, Object> map);

	// **************************以下方法为开发者补充*********************************/
	
	//删除行业推荐
	public void deleteTradeRecommend(Map<String, Object> map);
	
	//删除热门推荐
	public void deleteHotRecommend(Map<String, Object> map);
	
	public void updateRecordsDefine(Map<String, Object> dmap);

	public List<MenuOutPut> queryPersonMenu(@Param("empId") String empId, @Param("info") String info);

	public List<MenuOutPut> queryPublicMenu(@Param("list") List<String> list, @Param("tradeLevel") String tradeLevel);
}
