package com.sinochem.crude.trade.info.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.sinochem.crude.trade.info.dao.PriceIndexMapper;
import com.sinochem.crude.trade.info.domain.PriceIndex;
import com.sinochem.crude.trade.info.query.PriceExcelQuery;
import com.sinochem.crude.trade.info.service.PriceIndexService;
import com.sinochem.crude.trade.member.user.CurrentUser;

@Service
public class PriceIndexServiceImpl implements PriceIndexService {
	@Autowired
	private PriceIndexMapper priceIndexMapper;
	
	public PriceIndexMapper getMapper(){
		return priceIndexMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(PriceIndex priceIndex){
		priceIndex.setAliveFlag(Constants.ALIEVE_FLAG);
		priceIndex.setUuid(KeyGenUtils.newUuid());
		priceIndex.setCreateDate(DateTimeUtils.currentDate());
		return priceIndexMapper.insertRecord(priceIndex);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return priceIndexMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(PriceIndex  record){
    	return priceIndexMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(PriceIndex priceIndex) throws BizException{
		if ( priceIndex.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return priceIndexMapper.updateRecordById(priceIndex);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(PriceIndex priceIndex) throws BizException{
		if ( priceIndex.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return priceIndexMapper.updateRecordByUuid(priceIndex);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return priceIndexMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(PriceIndex priceIndex){
		return priceIndexMapper.updateRecords(priceIndex.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public PriceIndex findByPrimaryKey(Long id){
		return  priceIndexMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public PriceIndex findByUuid(String uuid){
		return  priceIndexMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<PriceIndex> queryByEntitys(PriceIndex priceIndex){
		return  priceIndexMapper.queryByEntitys(priceIndex);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return priceIndexMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) priceIndexMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return priceIndexMapper.countRecords(map);
	}

	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 指数列表
	 */
	@Override
	public Page<Map<String, Object>> queryPriceIndexWithTemp(Map<String, Object> beanToMap, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) priceIndexMapper.queryPriceIndexWithTemp(beanToMap);
	}

	/**
	 * 新增或更新指数
	 */
	@Override
	public boolean saveOrUpdatePriceIndex(PriceIndex target,CurrentUser user) throws BizException {
		
		//置为最新
		/*PriceIndex priceIndex = findLastUpdate(target.getIndexTemplateId());
		if(priceIndex != null) {
			if(target.getIndexDate().after(priceIndex.getIndexDate())) {
				target.setLatestFlag("1");
				priceIndex.setLatestFlag("0");
				this.updateRecordById(priceIndex);
			}else{
				target.setLatestFlag("0");
			}
		}else{
			target.setLatestFlag("1");
		}*/
		String userName = "admin";
		if(user!=null && user.getName()!=null) {
			userName = user.getName();
		}
		if(!StringUtils.isBlank(target.getUuid())) {
			target.setUpdateUser(userName);
			target.setUpdateDate(DateTimeUtils.currentDate());
			return this.updateRecordByUuid(target) == 1;
		}else {
			target.setCreateUser(userName);
			target.setCreateDate(DateTimeUtils.currentDate());
			target.setUpdateUser(userName);
			target.setUpdateDate(DateTimeUtils.currentDate());
			return this.insertRecord(target) == 1;
		}
	}

	/**
	 * 查找模板下最新记录
	 */
	public PriceIndex findLastUpdate(Long tempId) {
		return priceIndexMapper.findLastRecordByTempId(tempId);
	}
	
	/**
	 * 通过模板id查找指数前一天记录
	 * @param beanToMap
	 * @return
	 */
	public PriceIndex findPreRecordByTempId(Map<String, Object> beanToMap) {
		return priceIndexMapper.findPreRecordByTempId(beanToMap);
	}
	
	/**
	 * 根据模板id取最近30条数据
	 */
	@Override
	public List<Map<String, Object>> queryLatest30(Long templateId){
		return priceIndexMapper.queryLatest30(templateId);
	}
	
	/**
	 * 根据模板id取区间的上下限
	 */
	@Override
	public Map<String, Object> queryInterval(Long templateId){
		return priceIndexMapper.queryInterval(templateId);
	}
	
	/**
	 * 获取当天的指数数据
	 */
	@Override
	public List<Map<String, Object>> queryLatest1(Map<String, Object> map){
		return priceIndexMapper.queryLatest1(map);
	}
	
	/**
	 * 获取昨天收盘的指数数据
	 */
	@Override
	public List<Map<String, Object>> queryZS(Map<String, Object> map){
		return priceIndexMapper.queryZS(map);
	}
	
	/**
	 * 获取开始日期到结束日期 期间的有效记录
	 */
	@Override
	public List<Map<String, Object>> queryByStartAndEndDate(PriceExcelQuery query) {
		return priceIndexMapper.queryByStartAndEndDate(query);
	}
}
