package com.sinochem.crude.trade.transport.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eyeieye.melody.web.locale.VisitorLocale;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.dao.PortRegionMapper;
import com.sinochem.crude.trade.transport.domain.PortRegion;
import com.sinochem.crude.trade.transport.service.PortRegionService;

@Service
public class PortRegionServiceImpl implements PortRegionService {
	//private static Log log = LogFactory.getLog(PortRegionServiceImpl.class);
	@Autowired
	private PortRegionMapper _PortRegionMapper;
	
	
	public PortRegionMapper getMapper(){
		return _PortRegionMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<PortRegion> queryByEntitys(PortRegion portregion){
		Locale current = VisitorLocale.getCurrent();
		String string = current.getLanguage();
		//log.info("当前语言=====>"+string);
		if ("en".equals(string)){
			portregion.setLangVer(string);
		} else {
			portregion.setLangVer("zh");
		}
		return  _PortRegionMapper.queryByEntitys(portregion);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public PortRegion findByPrimaryKey(Long portRegionId){
		return  _PortRegionMapper.findByPrimaryKey(portRegionId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public PortRegion findByUuid(String uuid){
		return  _PortRegionMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(PortRegion portregion) {
		 _PortRegionMapper.updateRecord(portregion);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long portRegionId  , Long deleteUser) {
		 _PortRegionMapper.deleteRecordByKey(portRegionId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(PortRegion portregion){
		 _PortRegionMapper.insertRecord(portregion);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long portRegionId){
		 _PortRegionMapper.deleteRecordByKey(portRegionId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		Locale current = VisitorLocale.getCurrent();
		String string = current.getLanguage();
		if ("en".equals(string)){
			map.put("langVer", string);
		} else {
			map.put("langVer", "zh");
		}
		return _PortRegionMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		Locale current = VisitorLocale.getCurrent();
		String string = current.getLanguage();
		if ("en".equals(string)){
			map.put("langVer", string);
		} else {
			map.put("langVer", "zh");
		}
		return (Page<Map<String, Object>>) _PortRegionMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _PortRegionMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_PortRegionMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_PortRegionMapper.updateRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
}
