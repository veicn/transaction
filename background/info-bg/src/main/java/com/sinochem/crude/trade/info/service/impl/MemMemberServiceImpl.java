package com.sinochem.crude.trade.info.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.info.dao.MemMemberMapper;
import com.sinochem.crude.trade.info.dao.immt.MemMemberImmtMapper;
import com.sinochem.crude.trade.info.domain.MemMember;
import com.sinochem.crude.trade.info.service.MemMemberService;
import com.sinochem.crude.trade.member.user.CurrentUser;

@Service
public class MemMemberServiceImpl implements MemMemberService {
	@Autowired
	private MemMemberMapper memMemberMapper;
	
	@Autowired
	private MemMemberImmtMapper memMemberImmtMapper;
	
	public MemMemberMapper getMapper(){
		return memMemberMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<MemMember> queryByEntitys(MemMember memmember){
		return memMemberImmtMapper.queryByEntitys(memmember);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public MemMember findByPrimaryKey(String id){
		return memMemberImmtMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(MemMember memmember) throws Exception {
		if ( memmember.getId() == null  ) {
			throw new Exception("id 为空，不能修改 ");
		}
		memMemberImmtMapper.updateRecord(memmember);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(String id  , String deleteUser) throws Exception {
		if (  id == null ) {
			throw new Exception("id 为空，不能删除 ");
		}
		memMemberImmtMapper.deleteRecordByKey(id , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(MemMember memmember){
		memMemberImmtMapper.insertRecord(memmember);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(String id) throws Exception {
		if (  id == null ) {
			throw new Exception("id 为空，不能删除 ");
		}
		memMemberImmtMapper.deleteRecordByKey(id);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return memMemberMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) memMemberMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return memMemberMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map) throws Exception {
		memMemberMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) throws Exception {
		memMemberMapper.updateRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/*查询登陆用户信息是否存在*/
	@Override
	public MemMember findMemberByMemberId(String memberId) {
		MemMember findMemberByMemberId = memMemberMapper.findMemberByMemberId(memberId);
		return findMemberByMemberId;
	}
}
