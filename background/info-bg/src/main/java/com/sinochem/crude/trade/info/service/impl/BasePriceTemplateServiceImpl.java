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
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.BasePriceTemplateMapper;
import com.sinochem.crude.trade.info.domain.BasePriceTemplate;
import com.sinochem.crude.trade.info.service.BasePriceTemplateService;
@Service
public class BasePriceTemplateServiceImpl implements BasePriceTemplateService {
	@Autowired
	private BasePriceTemplateMapper basePriceTemplateMapper;
	
	public BasePriceTemplateMapper getMapper(){
		return basePriceTemplateMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(BasePriceTemplate basepricetemplate){
		 basepricetemplate.setAliveFlag(Constants.ALIEVE_FLAG);
		 basepricetemplate.setCreateDate(DateTimeUtils.currentDate());
		 basepricetemplate.setUuid(KeyGenUtils.newUuid());
		 basepricetemplate.setStatus("1");
		 return basePriceTemplateMapper.insertRecord(basepricetemplate);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return basePriceTemplateMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(BasePriceTemplate  record){
    	return basePriceTemplateMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(BasePriceTemplate basePriceTemplate) throws BizException{
		if ( basePriceTemplate.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return basePriceTemplateMapper.updateRecordById(basePriceTemplate);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(BasePriceTemplate basePriceTemplate) throws BizException{
		if ( basePriceTemplate.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		basePriceTemplate.setUpdateDate(DateTimeUtils.currentDate());
		return basePriceTemplateMapper.updateRecordByUuid(basePriceTemplate);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return basePriceTemplateMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(BasePriceTemplate basePriceTemplate){
		return basePriceTemplateMapper.updateRecords(basePriceTemplate.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public BasePriceTemplate findByPrimaryKey(Long id){
		return  basePriceTemplateMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public BasePriceTemplate findByUuid(String uuid){
		return  basePriceTemplateMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<BasePriceTemplate> queryByEntitys(BasePriceTemplate basePriceTemplate){
		return  basePriceTemplateMapper.queryByEntitys(basePriceTemplate);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return basePriceTemplateMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) basePriceTemplateMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return basePriceTemplateMapper.countRecords(map);
	}

	//**************************以下方法为开发者补充*********************************/
	/**
	 * 基价模板列表
	 */
	@Override
	public Page<Map<String, Object>> queryLikeNameRecords(Map<String, Object> beanToMap, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>)basePriceTemplateMapper.queryLikeNameRecords(beanToMap);
	}
	
	/**
	 * 新增或编辑基价模板
	 */
	@Override
	public Result saveOrUpdateBasePriceIndexTemp(BasePriceTemplate basepriceTemp) throws BizException {
		BasePriceTemplate exist = this.findByBaseCode(basepriceTemp.getBaseCode());
		Result result = new Result();
		if(StringUtils.isBlank(basepriceTemp.getUuid())) {
			//新增
			if(exist != null) {
				result.setFail("基价模板代码已存在");
				return result;
			}
			if(this.insertRecord(basepriceTemp) == 1) {
				result.setMessage("基价模板新增成功");
				return result;
			}
			
			result.setFail("新增基价模板失败");
			return result;
		}else {
			if(exist != null && !exist.getUuid().equals(basepriceTemp.getUuid())) {
				result.setFail("基价模板已存在");
				return result;
			}
			
			if(this.updateRecordByUuid(basepriceTemp) == 1) {
				result.setMessage("基价模板更新成功");
				return result;
			}
			
			result.setFail("更新基价模板失败");
			return result;
		}
	}
	/**
	 * 设置基价模板状态
	 */
	@Override
	public boolean setBasePriceTempStatus(String uuid) {
		BasePriceTemplate basePriceTemp =  this.findByUuid(uuid);
		if("1".equals(basePriceTemp.getStatus())) {
			basePriceTemp.setStatus("0");
		}else {
			basePriceTemp.setStatus("1");
		}
		return basePriceTemplateMapper.updateBasePriceTempStatus(basePriceTemp) == 1;
	}

	/**
	 * 根据模板编码查找模板
	 */
	@Override
	public BasePriceTemplate findByBaseCode(String baseCode) {
		return basePriceTemplateMapper.queryBaseCode(baseCode) ;
	}
	
}
