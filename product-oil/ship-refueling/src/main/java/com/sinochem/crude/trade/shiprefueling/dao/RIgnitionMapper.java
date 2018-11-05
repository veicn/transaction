package com.sinochem.crude.trade.shiprefueling.dao;

import com.sinochem.crude.trade.shiprefueling.domain.po.RIgnition;
import com.sinochem.crude.trade.shiprefueling.model.query.QueryRIgnition;
import com.sinochem.crude.trade.shiprefueling.model.vo.QueryRIgnitionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RIgnitionMapper {

	public int insertRecord(RIgnition entity);
	
	public int deleteById( @Param("orderId") Long orderId);
	
	public int deleteRecords(RIgnition entity);
	
	public int updateRecordById(RIgnition entity);
	
	public int updateRecordByUuid(RIgnition entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public RIgnition findByPrimaryKey( Long orderId);	
	
	public RIgnition findByUuid(String uuid);	
	
	//返回对象的List
	public List<RIgnition> queryByEntitys(RIgnition entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 多条件查询船燃订单信息
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryIgnitionRecords(Map<String,Object> map);

	/**
	 * 买家统计卖家的订单数
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> querySellCompanyOrder(Map<String,Object> map);

	/**
	 * 卖家统计买家的订单数
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> queryBuyCompanyOrder(Map<String,Object> map);

	int updateRecordsAliveFlag(@Param("orderId") Long orderId, @Param("aliveFlag") String aliveFlag);

	//返回对象的List
	List<RIgnition> queryByQueryRIgnition(QueryRIgnition queryRIgnition);

}
