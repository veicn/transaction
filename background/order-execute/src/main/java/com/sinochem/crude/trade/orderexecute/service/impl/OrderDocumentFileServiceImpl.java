package com.sinochem.crude.trade.orderexecute.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.orderexecute.dao.OrderDocumentFileMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderDocumentFile;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.service.OrderDocumentFileService;

@Service
public class OrderDocumentFileServiceImpl implements OrderDocumentFileService {
	@Autowired
	private OrderDocumentFileMapper orderDocumentFileMapper;
	public OrderDocumentFileMapper getMapper(){
		return orderDocumentFileMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderDocumentFile orderdocumentfile){
		 return orderDocumentFileMapper.insertRecord(orderdocumentfile);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(OrderDocumentFile entity) throws BizException{
		return orderDocumentFileMapper.deleteById(entity);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderDocumentFile  record){
    	return orderDocumentFileMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderDocumentFile orderDocumentFile) throws BizException{
		if ( orderDocumentFile.getDocumentFileId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","documentFileId 为空，不能修改","documentFileId");
		}
		return orderDocumentFileMapper.updateRecordById(orderDocumentFile);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderDocumentFileMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderDocumentFile orderDocumentFile){
		return orderDocumentFileMapper.updateRecords(orderDocumentFile.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderDocumentFile findByPrimaryKey(Long documentFileId){
		return  orderDocumentFileMapper.findByPrimaryKey(documentFileId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderDocumentFile findByUuid(String uuid){
		return  orderDocumentFileMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderDocumentFile> queryByEntitys(OrderDocumentFile orderDocumentFile){
		return  orderDocumentFileMapper.queryByEntitys(orderDocumentFile);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderDocumentFileMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderDocumentFileMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderDocumentFileMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryDocumentFile(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderDocumentFileMapper.queryDocumentFile(map);
	}
	
	/**
	 * 取得表单类型下拉列表数据
	 */
	@Override
	public List<Map<String, Object>> getDocumentType(){
		return (List<Map<String, Object>>) orderDocumentFileMapper.getDocumentType();
	}
}
