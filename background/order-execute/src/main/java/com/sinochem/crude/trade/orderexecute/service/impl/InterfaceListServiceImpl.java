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
import com.sinochem.crude.trade.orderexecute.domain.InterfaceList;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.dao.InterfaceListMapper;
import com.sinochem.crude.trade.orderexecute.service.InterfaceListService;

@Service
public class InterfaceListServiceImpl implements InterfaceListService {
	@Autowired
	private InterfaceListMapper interfaceListMapper;
	
	public InterfaceListMapper getMapper(){
		return interfaceListMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(InterfaceList interfacelist){
		 return interfaceListMapper.insertRecord(interfacelist);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long interfaceId) throws BizException{
		if (interfaceId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改 ","id");
		}
		return interfaceListMapper.deleteById(interfaceId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(InterfaceList  record){
    	return interfaceListMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(InterfaceList interfaceList) throws BizException{
		if ( interfaceList.getInterfaceId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改 ","id");
		}
		return interfaceListMapper.updateRecordById(interfaceList);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return interfaceListMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(InterfaceList interfaceList){
		return interfaceListMapper.updateRecords(interfaceList.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public InterfaceList findByPrimaryKey(Long interfaceId){
		return  interfaceListMapper.findByPrimaryKey(interfaceId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public InterfaceList findByUuid(String uuid){
		return  interfaceListMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<InterfaceList> queryByEntitys(InterfaceList interfaceList){
		return  interfaceListMapper.queryByEntitys(interfaceList);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return interfaceListMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) interfaceListMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return interfaceListMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
}
