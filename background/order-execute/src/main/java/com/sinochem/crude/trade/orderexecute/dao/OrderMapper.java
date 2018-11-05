package com.sinochem.crude.trade.orderexecute.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.orderexecute.domain.InterfaceSystem;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderBO;
import com.sinochem.crude.trade.orderexecute.domain.TransportInfo;
import com.sinochem.crude.trade.orderexecute.model.CountOrderContractVO;
import com.sinochem.crude.trade.orderexecute.model.CountOrderStatusVO;
import com.sinochem.crude.trade.orderexecute.model.InspectionOrderListVO;

//@Mapper
public interface OrderMapper {

	public long insertRecord(Order entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(Order entity);
	
	public int updateRecordById(Order entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public Order findByPrimaryKey( /* @Param("id") */ Long id);	
	
	public Order findByUuid(String uuid);	
	
	//返回对象的List
	public List<Order> queryByEntitys(Order entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	public List<Map<String,Object>> selectFindShipRecords(Long memberId);
	public Map<String,Object> selectFindShipRecordByOrderNo(String orderNo);
	public List<OrderBO> queryOrderList(Map<String,Object> map);
	public List<CountOrderStatusVO> countOrderStatus(Map<String,Object> param);
	public int savePayConfirmFromOut(Order entity);
	public int getStatementCount(String uuid);
	public TransportInfo getTransportInfoDetails(String orderNo);
	public List<Map<String,Object>> getLoadDetails(String orderNo);
	public List<Map<String,Object>> getDispDetails(String orderNo);
	public InterfaceSystem getSysNameBySeller(String orderNo);
	public InterfaceSystem getSysNameByBuyer(String orderNo);
	public int countOrderContract(CountOrderContractVO vo);
	public int updateRecordByOrderNo(Order update);
	public BigDecimal selectTotalAmount(String uuid);
	
	public Order findByTradeId(Long tradeId);	
	public List<InspectionOrderListVO> queryInspectionOrderList(Map<String, Object> prams);
	public List<String> distinctShipForInspection(Map<String, Object> prams);
	public List<String> distinctPortForInspection(Map<String, Object> prams);
}
