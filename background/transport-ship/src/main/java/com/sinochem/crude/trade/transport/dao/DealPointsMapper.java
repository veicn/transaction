package com.sinochem.crude.trade.transport.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.transport.domain.DealPoints;
//import org.apache.ibatis.annotations.Mapper;
import com.sinochem.crude.trade.transport.model.DealPointsVO;

//@Mapper
public interface DealPointsMapper {
	/**根据条件-查询全部*/
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	/**根据条件-查询记录条数*/
	public int countRecords(Map<String,Object> map);
	/**根据条件-批量逻辑删除 (AliveFlag修改为0)*/
	public void deleteRecords(Map<String,Object> map); 
	/**根据条件-批量修改数据*/
	public void updateRecords(Map<String,Object> map);
	/**根据对象-查询对象列表*/
	public List<DealPoints> queryByEntitys(DealPoints entity);
	/**根据主键-查询对象*/
	public DealPoints findByPrimaryKey(  @Param("dealPointsId")  Long dealPointsId);	
	/**根据UUID-查询对象*/
	public DealPoints findByUuid(String uuid);	
	/**根据主键-修改对象*/
	public void updateRecord(DealPoints entity);
	/**新增*/
	public void insertRecord(DealPoints entity);
	/**根据主键删除数据*/
	public void deleteRecordByKey( @Param("dealPointsId") Long dealPointsId, @Param("updateUser") Long updateUser);
	//**************************以下方法为开发者补充*********************************/
	/**根据uuid删除数据*/
	public void deleteRecordsByUuid(DealPoints dp);
	/**根据uuid修改数据*/
	public void updateRecordByUuid(DealPoints dp);
	//查重
	public DealPoints checkDp(DealPointsVO dp);
	//根据日期，船型，地区查找点数
	public DealPoints findDpByTdr(DealPointsVO dp);
	//查询当天成交点数，按时间倒序返回
	public List<DealPoints> getTodayDealPoints(String todayDate);
	//查询今年的所有成交点数，构造线形图
	public List<DealPoints> getThisYearDealPoints(DealPoints dealPoints);
	
	public List<DealPoints> queryByEntitys2();
	//获取最新成交点数
	public List<DealPoints> getNowDealPoints();
	//获取前一天的
	public DealPoints getDealPointsByRecode(DealPoints dealPoints2);
	
	//参考日期列表
	public List<Date> findAllDates();
	//接口根据日期 或区间返回成交点数
	public List<Map<String, Object>> findDealPointsPageListByRecords(
			Map<String, String> map);
}
