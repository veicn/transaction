package com.sinochem.crude.trade.info.dao.immt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinochem.crude.trade.info.domain.MemMember;


//@Mapper
public interface MemMemberImmtMapper {
	//**本文件内为系统生成文件，不允许人工修改， 如果被覆盖后果自负**/
	//public List<MemMember> queryRecords(Map<String,Object> map,RowBounds rowBounds);
	//public void deleteRecord(MemMember entity);
	//public void deleteRecordByKey( @Param("id") String id);
	//public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	public List<MemMember> queryByEntitys(MemMember entity);
	public MemMember findByPrimaryKey( @Param("id") String id);	
	//public int countRecords(Map<String,Object> map);
	public void updateRecord(MemMember entity);
	public void insertRecord(MemMember entity);
	//public void deleteRecord(MemMember entity); 
	public void deleteRecordByKey( @Param("id") String id, @Param("updateUser") String updateUser);
}
