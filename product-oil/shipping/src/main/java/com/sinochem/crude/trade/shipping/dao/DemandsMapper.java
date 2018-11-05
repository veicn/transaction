package com.sinochem.crude.trade.shipping.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.shipping.domain.Demands;
import com.sinochem.crude.trade.shipping.model.query.DemandsQuery;
import com.sinochem.crude.trade.shipping.model.query.WechatDemandsQuery;
//import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface DemandsMapper {

	public int insertRecord(Demands entity);
	
	public int deleteById( @Param("demandsId") Long demandsId);
	
	public int deleteRecords(Demands entity);
	
	public int updateRecordById(Demands entity);
	
	public int updateRecordByUuid(Demands entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public Demands findByPrimaryKey( Long demandsId);	
	
	public Demands findByUuid(String uuid);	
	
	//返回对象的List
	public List<Demands> queryByEntitys(Demands entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 查询租船需求list
	 * @param demandsQuery
	 * @return
	 */
	public List<Demands> queryByEntitysList(DemandsQuery demandsQuery);

	/**
	 * 查询租船需求list(泉炼)
	 * @param demandsQuery
	 * @return
	 */
	public List<Demands> queryByEntitysQuanhuaList(DemandsQuery demandsQuery);

	/**
	 * 租船需求列表查询  微信端API
	 * @param wechatDemandsQuery
	 * @return
	 */
	List<Demands> getDemandsList(DemandsQuery wechatDemandsQuery);

	/**
	 * 修改需求单状态
	 * @param demands
	 */
	public void updateStatusByDemandsId(Demands demands);

}
