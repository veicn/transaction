package com.sinochem.crude.trade.info.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.info.dao.ExternalInteractiveMapper;
import com.sinochem.crude.trade.info.domain.ExternalInteractive;
import com.sinochem.crude.trade.info.service.ExternalInteractiveService;
import com.sinochem.it.b2b.common.exception.BizException;

@Service
public class ExternalInteractiveServiceImpl implements ExternalInteractiveService {
	
	@Autowired
	private ExternalInteractiveMapper externalInteractiveMapper;
	
	private static final Log log = LogFactory.getLog(ExternalInteractiveServiceImpl.class);
	
	public ExternalInteractiveMapper getMapper(){
		return externalInteractiveMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(ExternalInteractive externalinteractive){
		 return externalInteractiveMapper.insertRecord(externalinteractive);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return externalInteractiveMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(ExternalInteractive  record){
    	return externalInteractiveMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(ExternalInteractive externalInteractive) throws BizException{
		if ( externalInteractive.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return externalInteractiveMapper.updateRecordById(externalInteractive);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return externalInteractiveMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(ExternalInteractive externalInteractive){
		return externalInteractiveMapper.updateRecords(externalInteractive.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public ExternalInteractive findByPrimaryKey(Long id){
		return  externalInteractiveMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public ExternalInteractive findByUuid(String uuid){
		return  externalInteractiveMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<ExternalInteractive> queryByEntitys(ExternalInteractive externalInteractive){
		return  externalInteractiveMapper.queryByEntitys(externalInteractive);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return externalInteractiveMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) externalInteractiveMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return externalInteractiveMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	
}
