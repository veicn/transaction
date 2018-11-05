package com.sinochem.crude.trade.info.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.info.dao.BrowsingRecordMapper;
import com.sinochem.crude.trade.info.domain.BrowsingRecord;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.service.BrowsingRecordService;
import com.sinochem.crude.trade.info.service.InfoService;

@Service
public class BrowsingRecordServiceImpl implements BrowsingRecordService {
	@Autowired
	private BrowsingRecordMapper browsingRecordMapper;
	
	@Autowired
	private InfoService infoService;
	
	public BrowsingRecordMapper getMapper(){
		return browsingRecordMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(BrowsingRecord browsingrecord){
		 return browsingRecordMapper.insertRecord(browsingrecord);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return browsingRecordMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(BrowsingRecord  record){
    	return browsingRecordMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(BrowsingRecord browsingRecord) throws BizException{
		if ( browsingRecord.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return browsingRecordMapper.updateRecordById(browsingRecord);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(BrowsingRecord browsingRecord) throws BizException{
		if ( browsingRecord.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return browsingRecordMapper.updateRecordByUuid(browsingRecord);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return browsingRecordMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(BrowsingRecord browsingRecord){
		return browsingRecordMapper.updateRecords(browsingRecord.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public BrowsingRecord findByPrimaryKey(Long id){
		return  browsingRecordMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public BrowsingRecord findByUuid(String uuid){
		return  browsingRecordMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<BrowsingRecord> queryByEntitys(BrowsingRecord browsingRecord){
		return  browsingRecordMapper.queryByEntitys(browsingRecord);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return browsingRecordMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) browsingRecordMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return browsingRecordMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	@Override
	public int insertBrowsing(BrowsingRecord browsingRecord){
		Long infoId =browsingRecord.getInformationId();
		infoService.updateBrowseCount(infoId);
		return this.insertRecord(browsingRecord);
	}
}
