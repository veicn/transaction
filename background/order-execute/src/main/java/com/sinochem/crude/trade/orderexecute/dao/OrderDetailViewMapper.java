package com.sinochem.crude.trade.orderexecute.dao;

import java.util.List;
import java.util.Map;

import com.sinochem.crude.trade.orderexecute.domain.OrderDetailView;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderDetailViewMapper {
	
	public OrderDetailView findByPrimaryKey( /* @Param("id") */ Long id);	
	
	public OrderDetailView findByUuid(String uuid);	
	
	//返回对象的List
	public List<OrderDetailView> queryByEntitys(OrderDetailView entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
