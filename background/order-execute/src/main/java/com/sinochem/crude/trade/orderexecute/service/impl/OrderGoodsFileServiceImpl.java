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
import com.sinochem.crude.trade.orderexecute.domain.OrderGoodsFile;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.dao.OrderGoodsFileMapper;
import com.sinochem.crude.trade.orderexecute.service.OrderGoodsFileService;

@Service
public class OrderGoodsFileServiceImpl implements OrderGoodsFileService {
	@Autowired
	private OrderGoodsFileMapper orderGoodsFileMapper;
	
	public OrderGoodsFileMapper getMapper(){
		return orderGoodsFileMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(OrderGoodsFile ordergoodsfile){
		 return orderGoodsFileMapper.insertRecord(ordergoodsfile);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long goodsFileId) throws BizException{
		if (goodsFileId == null) {
		
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return orderGoodsFileMapper.deleteById(goodsFileId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(OrderGoodsFile  record){
    	return orderGoodsFileMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(OrderGoodsFile orderGoodsFile) throws BizException{
		if ( orderGoodsFile.getGoodsFileId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","goodsFileId 为空，不能修改","goodsFileId");
		}
		return orderGoodsFileMapper.updateRecordById(orderGoodsFile);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return orderGoodsFileMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(OrderGoodsFile orderGoodsFile){
		return orderGoodsFileMapper.updateRecords(orderGoodsFile.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public OrderGoodsFile findByPrimaryKey(Long goodsFileId){
		return  orderGoodsFileMapper.findByPrimaryKey(goodsFileId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public OrderGoodsFile findByUuid(String uuid){
		return  orderGoodsFileMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<OrderGoodsFile> queryByEntitys(OrderGoodsFile orderGoodsFile){
		return  orderGoodsFileMapper.queryByEntitys(orderGoodsFile);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return orderGoodsFileMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) orderGoodsFileMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return orderGoodsFileMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
}
