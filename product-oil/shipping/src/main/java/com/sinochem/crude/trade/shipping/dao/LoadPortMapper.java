package com.sinochem.crude.trade.shipping.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.shipping.domain.LoadPort;
//import org.apache.ibatis.annotations.Mapper;
import com.sinochem.it.b2b.common.exception.BizException;

//@Mapper
public interface LoadPortMapper {

	public int insertRecord(LoadPort entity);
	
	public int deleteById( @Param("shipLoadPortId") Long shipLoadPortId);
	
	public int deleteRecords(LoadPort entity);
	
	public int updateRecordById(LoadPort entity);
	
	public int updateRecordByUuid(LoadPort entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public LoadPort findByPrimaryKey( Long shipLoadPortId);	
	
	public LoadPort findByUuid(String uuid);	
	
	//返回对象的List
	public List<LoadPort> queryByEntitys(LoadPort entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);

	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 第一步
	 * @param domain
	 * @return
	 * @throws BizException
	 */
	public int firstStepSave(LoadPort domain) throws BizException;

	/**
	 * 第二步
	 * @param domain
	 * @return
	 */
	public int theSecondStepSave(LoadPort domain) throws BizException;

	/**
	 * 第三步
	 * @param domain
	 * @return
	 * @throws BizException
	 */
	public int stepFourSave(LoadPort domain) throws BizException;

	/**
	 * 第四步
	 * @param domain
	 * @return
	 * @throws BizException
	 */
	public int theThirdStepSave(LoadPort domain) throws BizException;

	/**
	 * 根据确认单ID查询数据
	 * @param map
	 * @return
	 */
	public LoadPort queryConfirmationSheetId(HashMap<String, Object> map);

	/**
	 * 新增装货港
	 * @param loadport
	 */
	public void insertRecordObject(LoadPort loadport);

	/**
	 * 根据uuid 查询本表信息 上面的uuid查询已修改
	 * @param uuid
	 * @return
	 */
	public LoadPort findLoadUuid(String uuid);

	/**
	 * 根据确认单主键 查询本表关联信息
	 * @param conSellerId
	 * @return
	 */
	public LoadPort ConsellerIdqueryLoadPost(String conSellerId);
	
	/**
	 * 查询装港信息
	 * @param confirmationSheetId
	 * @return
	 */
	public LoadPort findByShipConfirmationSheetId(Long confirmationSheetId);

	/**
	 * 根据uuid 查询本表信息
	 * @param uuid
	 * @return
	 */
	public LoadPort confirmationfindbyuuid(String uuid);
	
}
