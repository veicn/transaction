package com.sinochem.crude.trade.orderexecute.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.orderexecute.domain.OrderMsgReminding;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface OrderMsgRemindingMapper {

public int insertRecord(OrderMsgReminding entity);
	
	public int deleteById( @Param("orderId") Long orderId ,  @Param("msgCode") String msgCode);
	public OrderMsgReminding findByPrimaryKey( @Param("orderId") Long orderId ,  @Param("msgCode") String msgCode);	
	
	public void createMsg(OrderMsgReminding data);
	public int removeMsg(@Param("orderId")Long orderId, @Param("msgCode")String msgCode);
	public int updateSchedule(@Param("orderId")Long orderId, @Param("msgCode")String msgCode, @Param("schedule") Date schedule);
	public int finishExecMsg(@Param("orderId")Long orderId, @Param("msgCode")String msgCode);
	public List<OrderMsgReminding> queryScheduleMsgList();
}
