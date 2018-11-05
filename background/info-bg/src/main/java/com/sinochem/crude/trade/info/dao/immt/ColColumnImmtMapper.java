package com.sinochem.crude.trade.info.dao.immt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.ColColumn;

//@Mapper
public interface ColColumnImmtMapper {
	//**本文件内为系统生成文件，不允许人工修改， 如果被覆盖后果自负**/
	//public List<ColColumn> queryRecords(Map<String,Object> map,RowBounds rowBounds);
	//public void deleteRecord(ColColumn entity);
	//public void deleteRecordByKey( @Param("id") String id);
	//public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	public List<ColColumn> queryByEntitys(ColColumn entity);
	public ColColumn findByPrimaryKey( @Param("id") String id);	
	//public int countRecords(Map<String,Object> map);
	public void updateRecord(ColColumn entity);
	public void insertRecord(ColColumn entity);
	//public void deleteRecord(ColColumn entity); 
	public void deleteRecordByKey( @Param("id") String id, @Param("updateUser") String updateUser);
}
