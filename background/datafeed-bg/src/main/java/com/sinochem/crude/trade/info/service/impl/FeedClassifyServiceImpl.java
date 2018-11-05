package com.sinochem.crude.trade.info.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.info.domain.FeedClassify;
import com.sinochem.crude.trade.info.dao.FeedClassifyMapper;
import com.sinochem.crude.trade.info.service.FeedClassifyService;

@Service
public class FeedClassifyServiceImpl implements FeedClassifyService {
	@Autowired
	private FeedClassifyMapper feedClassifyMapper;
	
	public FeedClassifyMapper getMapper(){
		return feedClassifyMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(FeedClassify feedclassify){
		 return feedClassifyMapper.insertRecord(feedclassify);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(String id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return feedClassifyMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(FeedClassify  record){
    	return feedClassifyMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(FeedClassify feedClassify) throws BizException{
		if ( feedClassify.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return feedClassifyMapper.updateRecordById(feedClassify);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return feedClassifyMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(FeedClassify feedClassify){
		return feedClassifyMapper.updateRecords(feedClassify.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public FeedClassify findByPrimaryKey(String id){
		return  feedClassifyMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public FeedClassify findByUuid(String uuid){
		return  feedClassifyMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<FeedClassify> queryByEntitys(FeedClassify feedClassify){
		return  feedClassifyMapper.queryByEntitys(feedClassify);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return feedClassifyMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) feedClassifyMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return feedClassifyMapper.countRecords(map);
	}

	@Override
	public FeedClassify getQuotationClass(String id) {
		return feedClassifyMapper.getQuotationClass(id);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
}
