package com.sinochem.crude.trade.info.dao;

import java.util.Map;

import com.sinochem.crude.trade.info.domain.MemMember;

import java.util.List;

//@Mapper
public interface MemMemberMapper {
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	public int countRecords(Map<String,Object> map);
	public void deleteRecords(Map<String,Object> map); 
	public void updateRecords(Map<String,Object> map);
	//**************************以下方法为开发者补充*********************************/
	public MemMember findMemberByMemberId(String memberId);
	
}
