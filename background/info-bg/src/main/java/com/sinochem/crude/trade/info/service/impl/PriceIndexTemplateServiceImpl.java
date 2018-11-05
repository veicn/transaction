package com.sinochem.crude.trade.info.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
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
import com.sinochem.crude.trade.info.dao.PriceIndexTemplateMapper;
import com.sinochem.crude.trade.info.domain.PriceIndexTemplate;
import com.sinochem.crude.trade.info.service.PriceIndexTemplateService;
import com.sinochem.crude.trade.member.user.CurrentUser;

@Service
public class PriceIndexTemplateServiceImpl implements PriceIndexTemplateService {
	@Autowired
	private PriceIndexTemplateMapper priceIndexTemplateMapper;
	
	public PriceIndexTemplateMapper getMapper(){
		return priceIndexTemplateMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(PriceIndexTemplate priceindextemplate){
			priceindextemplate.setUuid(KeyGenUtils.newUuid());
			priceindextemplate.setAliveFlag(Constants.ALIEVE_FLAG);
			priceindextemplate.setCreateDate(DateTimeUtils.currentDate());
			return priceIndexTemplateMapper.insertRecord(priceindextemplate);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return priceIndexTemplateMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(PriceIndexTemplate  record){
    	return priceIndexTemplateMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(PriceIndexTemplate priceIndexTemplate) throws BizException{
		if ( priceIndexTemplate.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return priceIndexTemplateMapper.updateRecordById(priceIndexTemplate);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(PriceIndexTemplate priceIndexTemplate) throws BizException{
		if ( priceIndexTemplate.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return priceIndexTemplateMapper.updateRecordByUuid(priceIndexTemplate);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return priceIndexTemplateMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(PriceIndexTemplate priceIndexTemplate){
		return priceIndexTemplateMapper.updateRecords(priceIndexTemplate.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public PriceIndexTemplate findByPrimaryKey(Long id){
		return  priceIndexTemplateMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public PriceIndexTemplate findByUuid(String uuid){
		return  priceIndexTemplateMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<PriceIndexTemplate> queryByEntitys(PriceIndexTemplate priceIndexTemplate){
		return  priceIndexTemplateMapper.queryByEntitys(priceIndexTemplate);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return priceIndexTemplateMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) priceIndexTemplateMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return priceIndexTemplateMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 新增或更新指数模板
	 */
	@Override
	public Result saveOrUpdatePriceIndexTemp(PriceIndexTemplate priceIndexTemp,CurrentUser user) throws BizException {
		Result res = new Result();
		res.setFail("新增指数模板失败");
		PriceIndexTemplate exist = checkEixstPriceIndexTemp(priceIndexTemp);
		if(StringUtils.isBlank(priceIndexTemp.getUuid())) {
			if(exist != null) {
				//已存在
				res.setFail("新增模板代码已存在");
				return res;
			}
			//新增
			priceIndexTemp.setCreateUser(user.getName());
			if(this.insertRecord(priceIndexTemp) == 1) {
				res.setMessage("新增指数模板成功");
				return res;
			}
			return res;
		}else {
			
			if(exist != null && !exist.getUuid().equals(priceIndexTemp.getUuid())){
				res.setFail("新增模板代码已存在");
				return res;
			}
			if(this.updateRecordByUuid(priceIndexTemp) == 1) {
				res.setStatus(Result.SUCCESS);
				res.setMessage("指数模板更新成功");
				return res;
			}
			return res;
		}
	}
	//检查是否有重复指数模板代码
	public PriceIndexTemplate checkEixstPriceIndexTemp(PriceIndexTemplate ptemp) {
		return priceIndexTemplateMapper.queryByIndexCode(ptemp.getIndexCode());
	}
	/**
	 * 根据指数名称模糊查询分页列表
	 */
	@Override
	public Page<Map<String, Object>> queryLikeNameRecords(Map<String, Object> beanToMap, SimplePageInfo pageInfo) {
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>)priceIndexTemplateMapper.queryLikeRecords(beanToMap);
	}
	/**
	 * 设置模板指数状态
	 */
	@Override
	public boolean setPriceIndexTempStatus(String uuid) {
		PriceIndexTemplate priceIndexTemp =  this.findByUuid(uuid);
		if("1".equals(priceIndexTemp.getStatus())) {
			priceIndexTemp.setStatus("0");
		}else {
			priceIndexTemp.setStatus("1");
		}
		return this.updateRecordByUuid(priceIndexTemp) == 1;
	}

	@Override
	public PriceIndexTemplate findBycrudeNameC(String crudeNameC) {
		PriceIndexTemplate ptl = new PriceIndexTemplate();
		ptl.setIndexName(crudeNameC);
		List<PriceIndexTemplate> list = this.queryByEntitys(ptl);
		if(!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 根据日期查ID
	 */
	@Override
	public Long findByName(String tempName) {
		return priceIndexTemplateMapper.findByName(tempName);
	}

	/**
	 * 查询所有
	 */
	@Override
	public List<Map<String, Object>> findAll() {
		return priceIndexTemplateMapper.queryRecords(null);
	}

	/**
	 * 根据指数模板查模板
	 */
	@Override
	public PriceIndexTemplate findByIndexCode(String indexCode) {
		return priceIndexTemplateMapper.queryByIndexCode(indexCode);
	}

}
