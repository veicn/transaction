package com.sinochem.crude.trade.orderexecute.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceMember;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.dao.InterfaceMemberMapper;
import com.sinochem.crude.trade.orderexecute.service.InterfaceMemberService;

@Service
public class InterfaceMemberServiceImpl implements InterfaceMemberService {
	@Autowired
	private InterfaceMemberMapper interfaceMemberMapper;
	
	public InterfaceMemberMapper getMapper(){
		return interfaceMemberMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(InterfaceMember interfacemember){
		 return interfaceMemberMapper.insertRecord(interfacemember);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long interfaceMemberId) throws BizException{
		if (interfaceMemberId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改 ","id");
		}
		return interfaceMemberMapper.deleteById(interfaceMemberId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(InterfaceMember  record){
    	return interfaceMemberMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(InterfaceMember interfaceMember) throws BizException{
		if ( interfaceMember.getInterfaceMemberId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","interfaceMemberId 为空，不能修改  ","interfaceMemberId");
		}
		return interfaceMemberMapper.updateRecordById(interfaceMember);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return interfaceMemberMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(InterfaceMember interfaceMember){
		return interfaceMemberMapper.updateRecords(interfaceMember.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public InterfaceMember findByPrimaryKey(Long interfaceMemberId){
		return  interfaceMemberMapper.findByPrimaryKey(interfaceMemberId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public InterfaceMember findByUuid(String uuid){
		return  interfaceMemberMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<InterfaceMember> queryByEntitys(InterfaceMember interfaceMember){
		return  interfaceMemberMapper.queryByEntitys(interfaceMember);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return interfaceMemberMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) interfaceMemberMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return interfaceMemberMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
}
