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
import com.sinochem.crude.trade.orderexecute.domain.InterfaceSystem;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.dao.InterfaceSystemMapper;
import com.sinochem.crude.trade.orderexecute.service.InterfaceSystemService;

@Service
public class InterfaceSystemServiceImpl implements InterfaceSystemService {
	@Autowired
	private InterfaceSystemMapper interfaceSystemMapper;
	
	public InterfaceSystemMapper getMapper(){
		return interfaceSystemMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(InterfaceSystem interfacesystem){
		 return interfaceSystemMapper.insertRecord(interfacesystem);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long sysId) throws BizException{
		if (sysId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return interfaceSystemMapper.deleteById(sysId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(InterfaceSystem  record){
    	return interfaceSystemMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(InterfaceSystem interfaceSystem) throws BizException{
		if ( interfaceSystem.getSysId() == null  ) {
		
			throw new OrderExecException("orderexecute.code.00131","sysId 为空，不能修改","sysId");
		}
		return interfaceSystemMapper.updateRecordById(interfaceSystem);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return interfaceSystemMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(InterfaceSystem interfaceSystem){
		return interfaceSystemMapper.updateRecords(interfaceSystem.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public InterfaceSystem findByPrimaryKey(Long sysId){
		return  interfaceSystemMapper.findByPrimaryKey(sysId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public InterfaceSystem findByUuid(String uuid){
		return  interfaceSystemMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<InterfaceSystem> queryByEntitys(InterfaceSystem interfaceSystem){
		return  interfaceSystemMapper.queryByEntitys(interfaceSystem);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return interfaceSystemMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) interfaceSystemMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return interfaceSystemMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	@Override
	public InterfaceSystem findByMemberId(Long memberId){
		InterfaceSystem query = new InterfaceSystem();
		query.setMemberId(memberId);
		List<InterfaceSystem> list = interfaceSystemMapper.queryByEntitys(query);
		
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		
		return null;
	}
	
}
