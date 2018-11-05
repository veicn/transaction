package com.sinochem.crude.trade.orderexecute.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.orderexecute.dao.InterfaceContrastMapper;
import com.sinochem.crude.trade.orderexecute.domain.InterfaceContrast;
import com.sinochem.crude.trade.orderexecute.exception.OrderExecException;
import com.sinochem.crude.trade.orderexecute.service.InterfaceContrastService;

@Service
public class InterfaceContrastServiceImpl implements InterfaceContrastService {
	@Autowired
	private InterfaceContrastMapper interfaceContrastMapper;
	
	public InterfaceContrastMapper getMapper(){
		return interfaceContrastMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(InterfaceContrast interfacecontrast){
		
		
		interfacecontrast.setCreateDate(DateTimeUtils.currentDate());
		interfacecontrast.setUpdateDate(DateTimeUtils.currentDate());

		
		 return interfaceContrastMapper.insertRecord(interfacecontrast);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long contrastId) throws BizException{
		if (contrastId == null) {
			throw new OrderExecException("orderexecute.code.00131","id 为空，不能修改 ","id");
		}
		return interfaceContrastMapper.deleteById(contrastId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(InterfaceContrast  record){
    	return interfaceContrastMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(InterfaceContrast interfaceContrast) throws BizException{
		if ( interfaceContrast.getContrastId() == null  ) {
			throw new OrderExecException("orderexecute.code.00131","contrastId 为空，不能修改 ","contrastId");
		}
		return interfaceContrastMapper.updateRecordById(interfaceContrast);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(InterfaceContrast interfaceContrast){
		return interfaceContrastMapper.updateRecords(interfaceContrast);
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public InterfaceContrast findByPrimaryKey(Long contrastId){
		return  interfaceContrastMapper.findByPrimaryKey(contrastId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public InterfaceContrast findByUuid(String uuid){
		return  interfaceContrastMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<InterfaceContrast> queryByEntitys(InterfaceContrast interfaceContrast){
		return  interfaceContrastMapper.queryByEntitys(interfaceContrast);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return interfaceContrastMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) interfaceContrastMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return interfaceContrastMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecordsByid(Long id){
    	return interfaceContrastMapper.deleteRecordsByid(id);
    }

	@Override
	public String queryOtherCode(String sysName, String paraType, String paraCode) {
		InterfaceContrast query = new InterfaceContrast();
		query.setSysName(sysName);
		query.setParaType(paraType);
		query.setParaCode(paraCode);
		
		List<InterfaceContrast> contrastList = interfaceContrastMapper.queryByEntitys(query);
		if(contrastList != null && !contrastList.isEmpty()){
			return contrastList.get(0).getOtherCode();
		}
		
		return paraCode;
	}
	
	
}
