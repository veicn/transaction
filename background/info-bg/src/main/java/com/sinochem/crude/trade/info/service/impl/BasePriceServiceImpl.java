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
import com.sinochem.crude.trade.info.dao.BasePriceMapper;
import com.sinochem.crude.trade.info.domain.BasePrice;
import com.sinochem.crude.trade.info.service.BasePriceService;
import com.sinochem.crude.trade.member.user.CurrentUser;

@Service
public class BasePriceServiceImpl implements BasePriceService {
	@Autowired
	private BasePriceMapper basePriceMapper;
	
	public BasePriceMapper getMapper(){
		return basePriceMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(BasePrice baseprice){
		baseprice.setAliveFlag(Constants.ALIEVE_FLAG);
		baseprice.setUuid(KeyGenUtils.newUuid());
		baseprice.setCreateDate(DateTimeUtils.currentDate());
		return basePriceMapper.insertRecord(baseprice);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return basePriceMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(BasePrice  record){
    	return basePriceMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(BasePrice basePrice) throws BizException{
		if ( basePrice.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return basePriceMapper.updateRecordById(basePrice);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(BasePrice basePrice) throws BizException{
		if ( basePrice.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return basePriceMapper.updateRecordByUuid(basePrice);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return basePriceMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(BasePrice basePrice){
		return basePriceMapper.updateRecords(basePrice.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public BasePrice findByPrimaryKey(Long id){
		return  basePriceMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public BasePrice findByUuid(String uuid){
		return  basePriceMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<BasePrice> queryByEntitys(BasePrice basePrice){
		return  basePriceMapper.queryByEntitys(basePrice);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return basePriceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) basePriceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return basePriceMapper.countRecords(map);
	}

	//**************************以下方法为开发者补充*********************************/
	/**
	 * 基价列表
	 */
	@Override
	public Page<Map<String, Object>> queryLikeRecords(Map<String, Object> beanToMap, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>)basePriceMapper.queryLikeRecords(beanToMap);
	}

	@Override
	public boolean saveOrUpdateBasePrice(BasePrice target, CurrentUser user) throws BizException {
		
		//置为最新
		BasePrice base = findLastUpdate(target.getBaseTemplateId());
		if(base != null) {
			if(target.getBaseDate().before(base.getBaseDate())) {
				base.setLatestFlag("0");
				this.updateRecordById(base);
			}
		}
		target.setCreateUser(user.getName());
		target.setUpdateUser(user.getName());
		if(!StringUtils.isBlank(target.getUuid())) {
			return this.updateRecordByUuid(target) == 1;
		}else {
			return this.insertRecord(target) == 1;
		}
	}
	

	/**
	 * 查找模板下最新记录
	 */
	public BasePrice findLastUpdate(Long tempId) {
		return basePriceMapper.findLastRecordByTempId(tempId);
	}
}
