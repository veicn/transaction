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
import com.sinochem.crude.trade.orderexecute.domain.InterfaceInput;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.dao.InterfaceInputMapper;
import com.sinochem.crude.trade.orderexecute.service.InterfaceInputService;

@Service
public class InterfaceInputServiceImpl implements InterfaceInputService {
	@Autowired
	private InterfaceInputMapper interfaceInputMapper;
	
	public InterfaceInputMapper getMapper(){
		return interfaceInputMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(InterfaceInput interfaceinput){
		 return interfaceInputMapper.insertRecord(interfaceinput);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long inputId) throws BizException{
		if (inputId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改  ","id");
		}
		return interfaceInputMapper.deleteById(inputId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(InterfaceInput  record){
    	return interfaceInputMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(InterfaceInput interfaceInput) throws BizException{
		if ( interfaceInput.getInputId() == null  ) {
			
			throw new OrderExecException("orderexecute.code.00131","inputId 为空，不能修改","inputId");
		}
		return interfaceInputMapper.updateRecordById(interfaceInput);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return interfaceInputMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(InterfaceInput interfaceInput){
		return interfaceInputMapper.updateRecords(interfaceInput.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public InterfaceInput findByPrimaryKey(Long inputId){
		return  interfaceInputMapper.findByPrimaryKey(inputId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public InterfaceInput findByUuid(String uuid){
		return  interfaceInputMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<InterfaceInput> queryByEntitys(InterfaceInput interfaceInput){
		return  interfaceInputMapper.queryByEntitys(interfaceInput);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return interfaceInputMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) interfaceInputMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return interfaceInputMapper.countRecords(map);
	}

	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<InterfaceInput> queryLogging(Map<String, Object> map, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<InterfaceInput>) interfaceInputMapper.queryLogging(map);
	}
	
	
	
	//**************************以下方法为开发者补充*********************************/
	
}
