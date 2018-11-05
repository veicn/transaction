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
import com.sinochem.crude.trade.orderexecute.domain.TradeSubject;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.dao.TradeSubjectMapper;
import com.sinochem.crude.trade.orderexecute.service.TradeSubjectService;

@Service
public class TradeSubjectServiceImpl implements TradeSubjectService {
	@Autowired
	private TradeSubjectMapper tradeSubjectMapper;
	
	public TradeSubjectMapper getMapper(){
		return tradeSubjectMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(TradeSubject tradesubject){
		 return tradeSubjectMapper.insertRecord(tradesubject);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long tradeSubjectId) throws BizException{
		if (tradeSubjectId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改","id");
		}
		return tradeSubjectMapper.deleteById(tradeSubjectId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(TradeSubject  record){
    	return tradeSubjectMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(TradeSubject tradeSubject) throws BizException{
		if ( tradeSubject.getTradeSubjectId() == null  ) {
			throw new BizException("tradeSubjectId 为空，不能修改 ");
		}
		return tradeSubjectMapper.updateRecordById(tradeSubject);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return tradeSubjectMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(TradeSubject tradeSubject){
		return tradeSubjectMapper.updateRecords(tradeSubject.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public TradeSubject findByPrimaryKey(Long tradeSubjectId){
		return  tradeSubjectMapper.findByPrimaryKey(tradeSubjectId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public TradeSubject findByUuid(String uuid){
		return  tradeSubjectMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<TradeSubject> queryByEntitys(TradeSubject tradeSubject){
		return  tradeSubjectMapper.queryByEntitys(tradeSubject);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return tradeSubjectMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) tradeSubjectMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return tradeSubjectMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
}
