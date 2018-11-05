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
import com.sinochem.crude.trade.orderexecute.domain.FeeSubject;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.dao.FeeSubjectMapper;
import com.sinochem.crude.trade.orderexecute.service.FeeSubjectService;

@Service
public class FeeSubjectServiceImpl implements FeeSubjectService {
	@Autowired
	private FeeSubjectMapper feeSubjectMapper;
	
	public FeeSubjectMapper getMapper(){
		return feeSubjectMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(FeeSubject feesubject){
		 return feeSubjectMapper.insertRecord(feesubject);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long feeSubjectId) throws BizException{
		if (feeSubjectId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改 ","id");
		}
		return feeSubjectMapper.deleteById(feeSubjectId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(FeeSubject  record){
    	return feeSubjectMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(FeeSubject feeSubject) throws BizException{
		if ( feeSubject.getFeeSubjectId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","feeSubjectId 为空，不能修改 ","feeSubjectId");
		}
		return feeSubjectMapper.updateRecordById(feeSubject);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return feeSubjectMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(FeeSubject feeSubject){
		return feeSubjectMapper.updateRecords(feeSubject.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public FeeSubject findByPrimaryKey(Long feeSubjectId){
		return  feeSubjectMapper.findByPrimaryKey(feeSubjectId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public FeeSubject findByUuid(String uuid){
		return  feeSubjectMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<FeeSubject> queryByEntitys(FeeSubject feeSubject){
		return  feeSubjectMapper.queryByEntitys(feeSubject);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return feeSubjectMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) feeSubjectMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return feeSubjectMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
}
