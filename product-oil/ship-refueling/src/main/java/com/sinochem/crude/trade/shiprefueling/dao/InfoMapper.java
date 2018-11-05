package com.sinochem.crude.trade.shiprefueling.dao;

import com.sinochem.crude.trade.shiprefueling.domain.po.Info;
import com.sinochem.crude.trade.shiprefueling.model.query.InfoQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InfoMapper {

	public int insertRecord(Info entity);

	public int deleteById( @Param("infoId") Long infoId);

	public int deleteRecords(Info entity);

	public int updateRecordById(Info entity);

	public int updateRecordByUuid(Info entity);

	public int updateRecords(Map<String,Object> map);

	public Info findByPrimaryKey( Long infoId);

	public Info findByUuid(String uuid);

	//返回对象的List
	public List<Info> queryByEntitys(Info entity);

	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);

	public int countRecords(Map<String,Object> map);


	//**************************以下方法为开发者补充*********************************/
	List<Map<String,Object>> queryByUserId(InfoQuery InfoQuery);

	/**
	 * 根据uuid修改状态
	 * @param info
	 * @return
	 */
	int updateByUuidStatus(Info info);

	/**
	 * 根据uuid删除销售订单
	 * @param uuid
	 * @return
	 */
	int deleteByuuid(String uuid);

	/**
	 * 前台——查询最新的销售信息
	 * @return
	 */
	List<Info> selectNewSellInfoList();

	/**
	 * 根据uuid逻辑删除信息
	 * @param uuid
	 * @return
	 */
	int deleteRecordByUuid(String uuid);

	/**
	 * 查询销售列表
	 * 查询信息类型为船燃贸易，船燃贸易(内贸)，船燃贸易(保税)中各自类型得三个不同企业的最新一条信息
	 * @return
	 * @
	 */
	List<Map<String,Object>> queryList();
}
