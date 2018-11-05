package com.sinochem.crude.trade.info.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.CrudeBasePriceMapper;
import com.sinochem.crude.trade.info.domain.CrudeBasePrice;
import com.sinochem.crude.trade.info.model.CrudeBasePriceVo;
import com.sinochem.crude.trade.info.model.PIMSVo;
import com.sinochem.crude.trade.info.query.CrudeOfficialQuery;
import com.sinochem.crude.trade.info.result.CrudePriceDetailRest;
import com.sinochem.crude.trade.info.result.CrudePriceRest;
import com.sinochem.crude.trade.info.service.CrudeBasePriceService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.AccessDeniedResultBuilder.Result;

@Service
public class CrudeBasePriceServiceImpl implements CrudeBasePriceService {
	@Autowired
	private CrudeBasePriceMapper crudeBasePriceMapper;
	
	public CrudeBasePriceMapper getMapper(){
		return crudeBasePriceMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(CrudeBasePrice baseprice){
		 return crudeBasePriceMapper.insertRecord(baseprice);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return crudeBasePriceMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(CrudeBasePrice  record){
    	return crudeBasePriceMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(CrudeBasePrice basePrice) throws BizException{
		if ( basePrice.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return crudeBasePriceMapper.updateRecordById(basePrice);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return crudeBasePriceMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(CrudeBasePrice basePrice){
		return crudeBasePriceMapper.updateRecords(basePrice.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public CrudeBasePrice findByPrimaryKey(Long id){
		return  crudeBasePriceMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public CrudeBasePrice findByUuid(String uuid){
		return  crudeBasePriceMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<CrudeBasePrice> queryByEntitys(CrudeBasePrice basePrice){
		return  crudeBasePriceMapper.queryByEntitys(basePrice);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return crudeBasePriceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) crudeBasePriceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return crudeBasePriceMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 获取最新的一条记录
	 * @return
	 */
	public CrudeBasePrice getLatest(Long crudeId){
		CrudeBasePrice param = new CrudeBasePrice();
		param.setLatestFlag("1");
		param.setAliveFlag(Constants.ALIVE_FLAG_1);
		param.setCrudeId(crudeId);
		if(CollectionUtils.isNotEmpty(this.queryByEntitys(param)))
			return this.queryByEntitys(param).get(0);
		else 
			return null;
	}
	
	/**
	 * 新增数据，处理最新标志字段
	 * @param agio
	 * @return
	 * @throws Exception
	 */
	public int insertBasePrice(CrudeBasePrice basePrice) throws Exception{
		//设置插入初始值
		basePrice.setId(null);
		basePrice.setUuid(UUID.randomUUID().toString().replace("-", ""));
		basePrice.setCreateDate(new Date());
		basePrice.setAliveFlag("1");
		
		CrudeBasePrice latest = this.getLatest(basePrice.getCrudeId());
		
		if(latest==null){
			basePrice.setLatestFlag("1");
			return this.insertRecord(basePrice);
		}else{
			if(latest.getPricingDate().before(basePrice.getPricingDate())){
				latest.setLatestFlag("0");
				this.updateRecordById(latest);
				
				basePrice.setLatestFlag("1");
				return this.insertRecord(basePrice);
			}else{
				basePrice.setLatestFlag("0");
				return this.insertRecord(basePrice);
			}
		}
	}
	/**
	 * PMIS查询官价
	 */
	@Override
	public List<Map<String, Object>> queryOfficialPrice(PIMSVo vo) {
		return crudeBasePriceMapper.queryOfficialPrice(vo);
	}
	
	/**
	 * 
	 * 最新官价查询
	 * @param dateList
	 * @param pageInfo
	 * @param query
	 * @return
	 */
	@Override
	public Page<Map<String, Object>> getFrontOfficialList(List<String> dateList , SimplePageInfo pageInfo, CrudeOfficialQuery query  ) {
		StringBuilder sb=new StringBuilder();
		Map<String, String> queryMap = new HashMap<>();
		
		/*拼接日期sql*/
		for (int i = 0; i < dateList.size(); i++) {
			sb.append(",MIN(CASE DATE_FORMAT(t.PRICING_DATE,'%Y%m') WHEN '");
			sb.append(dateList.get(i));
			sb.append("' THEN t.PRICING ELSE NULL END) AS ");
			sb.append("col");
			sb.append(i+3);
		}
		
		/*条件查询*/
		queryMap.put("crudeNameE", query.getCrudeNameE());
		queryMap.put("startDate", query.getStartDate());
		queryMap.put("sb", sb.toString());
		/*分页*/
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		Page<Map<String, Object>> FrontOfficialList= crudeBasePriceMapper.getFrontOfficialList(queryMap);
		
		return FrontOfficialList;
	}
	@Override
	public List<String> getDateList(String startDate) {
		return crudeBasePriceMapper.getDateList(startDate);
	}

	/**
	 * 最新官价油种列表查询
	 * 
	 */
	@Override
	public List<String> queryOfficalOilList(String pricingDate) {
		return crudeBasePriceMapper.queryOfficalOilList(pricingDate);
	}

	/**
	 * 最新官价油种走势查询
	 * 
	 */
	@Override
	public List<CrudePriceRest> queryOfficalYear(CrudeBasePriceVo vo) {
		com.sinochem.crude.trade.common.result.Result res = new com.sinochem.crude.trade.common.result.Result();
		List<CrudePriceRest> result = new ArrayList<>();
		
		try {
			Boolean isExy = false;
			List<String> crudeNameEArray = vo.getCrudeNameArray();
			for (String crudeNameE : crudeNameEArray) {
				CrudePriceRest crudePriceRest = new CrudePriceRest();
				crudePriceRest.setCrudeNameE(crudeNameE);
				
				Map<String, Object> paramMap = new HashMap<>();
				paramMap.put("pricingDate", vo.getPricingDate());
				paramMap.put("crudeNameE", crudeNameE);
				List<Map<String, Object>> crudePriceMapList = crudeBasePriceMapper.queryOfficalYear(paramMap);
				if(CollectionUtils.isEmpty(crudePriceMapList)){
					crudePriceRest.setIsExy(false);
					result.add(crudePriceRest);
					continue;
				}
				List<CrudePriceDetailRest> mapToBeanInList = BeanConvertUtils.mapToBeanInList(crudePriceMapList, CrudePriceDetailRest.class);
				if(this.getMax(mapToBeanInList) < 10){
					isExy = true;
				}else{
					isExy = false;
				}
				crudePriceRest.setIsExy(isExy);
				crudePriceRest.setDataList(mapToBeanInList);
				
				result.add(crudePriceRest);
			}
		} catch (Exception e) {
			res.setStatus(Result.ERROR);
			res.setMessage(e.getMessage());
		}
		
		return result;
		
	}
	
	
	Double getMax(List<CrudePriceDetailRest> vo) {
		List<Double> pricingList = new ArrayList<>();
		for (CrudePriceDetailRest crudePriceDetailRest : vo) {
			pricingList.add(crudePriceDetailRest.getPricing());
		}
		
		return Collections.max(pricingList);
	}
}
