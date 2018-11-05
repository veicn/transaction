package com.sinochem.crude.trade.info.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.info.dao.OilMapper;
import com.sinochem.crude.trade.info.domain.Oil;
import com.sinochem.crude.trade.info.service.OilService;

@Service
public class OilServiceImpl implements OilService {
	@Autowired
	private OilMapper oilMapper;
	
	public OilMapper getMapper(){
		return oilMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(Oil oil){
		 return oilMapper.insertRecord(oil);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return oilMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(Oil  record){
    	return oilMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(Oil oil) throws BizException{
		if ( oil.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return oilMapper.updateRecordById(oil);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return oilMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Oil oil){
		return oilMapper.updateRecords(oil.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public Oil findByPrimaryKey(Long id){
		return  oilMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public Oil findByUuid(String uuid){
		return  oilMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<Oil> queryByEntitys(Oil oil){
		return  oilMapper.queryByEntitys(oil);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return oilMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
//		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) oilMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return oilMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	@Override
	public Page<Map<String, Object>> addQueryRecords(Map<String, Object> map,SimplePageInfo pageInfo){
		
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) oilMapper.addCrudeRecords(map);
	}
	
	/**
	 * 根据中英文名查找oil
	 * @param crudeNameE
	 * @param crudeNameC
	 * @return
	 */
	public Oil findByName(String crudeNameE, String crudeNameC){
		Oil param = new Oil();
		param.setCrudeNameC(crudeNameC);
		param.setCrudeNameE(crudeNameE);
		if(CollectionUtils.isNotEmpty(this.queryByEntitys(param)))
			return this.queryByEntitys(param).get(0);
		else
			return null;
	}
}
