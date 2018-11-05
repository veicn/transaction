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
import com.sinochem.crude.trade.orderexecute.domain.OrderContractFile;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.dao.OrderContractFileMapper;
import com.sinochem.crude.trade.orderexecute.service.OrderContractFileService;

@Service
public class OrderContractFileServiceImpl implements OrderContractFileService {
	@Autowired
	private OrderContractFileMapper orderContractFileMapper;
	
	public OrderContractFileMapper getMapper(){
		return orderContractFileMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderContractFile ordercontractfile){
		 return orderContractFileMapper.insertRecord(ordercontractfile);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long contractFileId) throws BizException{
		if (contractFileId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderContractFileMapper.deleteById(contractFileId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderContractFile  record){
    	return orderContractFileMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderContractFile orderContractFile) throws BizException{
		if ( orderContractFile.getContractFileId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","contractFileId 为空，不能修改","contractFileId");
		}
		return orderContractFileMapper.updateRecordById(orderContractFile);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderContractFileMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderContractFile orderContractFile){
		return orderContractFileMapper.updateRecords(orderContractFile.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderContractFile findByPrimaryKey(Long contractFileId){
		return  orderContractFileMapper.findByPrimaryKey(contractFileId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderContractFile findByUuid(String uuid){
		return  orderContractFileMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderContractFile> queryByEntitys(OrderContractFile orderContractFile){
		return  orderContractFileMapper.queryByEntitys(orderContractFile);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderContractFileMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderContractFileMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderContractFileMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 根据主键+创建者 查询对象
	 */
	@Override
	public OrderContractFile findByPrimaryKeyAndCreater(Long contractFileId, Long userId){
		return orderContractFileMapper.findByPrimaryKeyAndCreater(contractFileId, userId);	
	}
	
}
