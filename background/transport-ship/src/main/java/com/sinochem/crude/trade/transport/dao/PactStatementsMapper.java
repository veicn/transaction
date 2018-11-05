package com.sinochem.crude.trade.transport.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.transport.domain.PactStatements;
//import org.apache.ibatis.annotations.Mapper;
import com.sinochem.crude.trade.transport.model.PactStatementsVO;

public interface PactStatementsMapper {
	/**根据条件-查询全部*/
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	/**根据条件-查询记录条数*/
	public int countRecords(Map<String,Object> map);
	/**根据条件-批量逻辑删除 (AliveFlag修改为0)*/
	public void deleteRecords(Map<String,Object> map); 
	/**根据条件-批量修改数据*/
	public void updateRecords(Map<String,Object> map);
	/**根据对象-查询对象列表*/
	public List<PactStatements> queryByEntitys(PactStatements entity);
	/**根据主键-查询对象*/
	public PactStatements findByPrimaryKey(  @Param("statementsId")  Long statementsId);	
	/**根据UUID-查询对象*/
	public PactStatements findByUuid(String uuid);	
	/**根据主键-修改对象*/
	public void updateRecord(PactStatements entity);
	/**新增*/
	public void insertRecord(PactStatements entity);
	/**根据主键删除数据*/
	public void deleteRecordByKey( @Param("statementsId") Long statementsId, @Param("updateUser") Long updateUser);
	//**************************以下方法为开发者补充*********************************/
	/**根据油合同号查找数据*/
	public List<PactStatementsVO> findByPactCode(Map<String, Object> map);
	
}
