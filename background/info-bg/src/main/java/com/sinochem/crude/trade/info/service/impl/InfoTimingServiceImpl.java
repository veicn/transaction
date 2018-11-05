package com.sinochem.crude.trade.info.service.impl;

import java.util.HashMap;
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
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.InfoTimingMapper;
import com.sinochem.crude.trade.info.domain.InfoTiming;
import com.sinochem.crude.trade.info.service.InfoTimingService;

@Service
public class InfoTimingServiceImpl implements InfoTimingService {
	@Autowired
	private InfoTimingMapper infoTimingMapper;
	
	public InfoTimingMapper getMapper(){
		return infoTimingMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(InfoTiming infotiming){
		 infotiming.setUuid(KeyGenUtils.newUuid());
		 infotiming.setCreateDate(DateTimeUtils.currentDate());
		 infotiming.setAliveFlag(Constants.ALIEVE_FLAG);
		 return infoTimingMapper.insertRecord(infotiming);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return infoTimingMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(InfoTiming  record){
    	return infoTimingMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(InfoTiming infoTiming) throws BizException{
		if ( infoTiming.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return infoTimingMapper.updateRecordById(infoTiming);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(InfoTiming infoTiming) throws BizException{
		if ( infoTiming.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return infoTimingMapper.updateRecordByUuid(infoTiming);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return infoTimingMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(InfoTiming infoTiming){
		return infoTimingMapper.updateRecords(infoTiming.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public InfoTiming findByPrimaryKey(Long id){
		return  infoTimingMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public InfoTiming findByUuid(String uuid){
		return  infoTimingMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<InfoTiming> queryByEntitys(InfoTiming infoTiming){
		return  infoTimingMapper.queryByEntitys(infoTiming);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return infoTimingMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) infoTimingMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return infoTimingMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 定时发布
	 */
	@Override
	public boolean insertTimingInfo(InfoTiming it) {
		it.setStatus("00"); //待处理
		it.setTimingType("30"); //定时发布
		return this.insertRecord(it) == 1;
	}
	/**
	 * 待处理
	 */
	@Override
	public List<InfoTiming> getTimingList(Long second) {
		Map<String,String> param = new HashMap<>(16);
		param.put("status", "00");
		param.put("timingType", "30");
		param.put("second", second+"");
		return infoTimingMapper.queryTimingList(param);
	}

	/**
	 * 已发布
	 */
	@Override
	public boolean processedInfoTiming(Long id) {
		InfoTiming it = new InfoTiming();
		it.setStatus("10"); //待处理
		it.setId(id);
		return this.updateRecordById(it) == 1;
	}
	
}
