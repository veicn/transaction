package com.sinochem.crude.trade.info.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.opensearch.sdk.dependencies.org.apache.commons.lang.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.AgioMapper;
import com.sinochem.crude.trade.info.domain.Agio;
import com.sinochem.crude.trade.info.model.CrudeAgioVo;
import com.sinochem.crude.trade.info.model.PIMSVo;
import com.sinochem.crude.trade.info.result.CrudePriceDetailRest;
import com.sinochem.crude.trade.info.result.CrudePriceRest;
import com.sinochem.crude.trade.info.service.AgioService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.AccessDeniedResultBuilder.Result;

@Service
public class AgioServiceImpl implements AgioService {
	@Autowired
	private AgioMapper agioMapper;
	
	public AgioMapper getMapper(){
		return agioMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(Agio agio){
		 return agioMapper.insertRecord(agio);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return agioMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(Agio  record){
    	return agioMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(Agio agio) throws BizException{
		if ( agio.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return agioMapper.updateRecordById(agio);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return agioMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Agio agio){
		return agioMapper.updateRecords(agio.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public Agio findByPrimaryKey(Long id){
		return  agioMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public Agio findByUuid(String uuid){
		return  agioMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<Agio> queryByEntitys(Agio agio){
		return  agioMapper.queryByEntitys(agio);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return agioMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) agioMapper.queryRecords2(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return agioMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 获取最新的一条记录
	 * @return
	 */
	public Agio getLatest(Long crudeId){
		Agio param = new Agio();
		param.setCrudeId(crudeId);
		param.setLatestFlag("1");
		param.setAliveFlag(Constants.ALIVE_FLAG_1);
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
	public int insertAgio(Agio agio) throws Exception{
		//设置插入初始值
		agio.setId(null);
		agio.setUuid(UUID.randomUUID().toString().replace("-", ""));
		agio.setCreateDate(new Date());
		agio.setAliveFlag("1");
		
		Agio latest = this.getLatest(agio.getCrudeId());

		if(latest==null){
			agio.setLatestFlag("1");
			return this.insertRecord(agio);
		}else{
			if(latest.getPricingDate().before(agio.getPricingDate())){
				latest.setLatestFlag("0");
				this.updateRecordById(latest);
				
				agio.setLatestFlag("1");
				return this.insertRecord(agio);
			}else{
				agio.setLatestFlag("0");
				return this.insertRecord(agio);
			}
		}
	}
	
	/**
	 * PMIS查询贴水
	 */
	@Override
	public List<Map<String, Object>> queryAgio(PIMSVo vo) {
		return agioMapper.queryAgio(vo);
	}

/*	@Override
	public List<Map<String, Object>> queryPointYear(Map<String, Object> m) {
		return agioMapper.queryPointYear(m);
	}
*/
	
	/**
	 * 现货贴水油种列表查询
	 * 
	 */
	@Override
	public List<String> queryOilList(String pricingDate) {
		return agioMapper.queryOilList(pricingDate);
	}
	
	/**
	 * 现货贴水走势查询
	 * 
	 */
	@Override
	public List<CrudePriceRest> queryPointYear(CrudeAgioVo vo) {
		com.sinochem.crude.trade.common.result.Result res = new com.sinochem.crude.trade.common.result.Result();
		List<CrudePriceRest> result = new ArrayList<>();
		
		try {
			
			List<String> crudeNameEArray = vo.getCrudeNameArray();
			for (String crudeNameE : crudeNameEArray) {
				CrudePriceRest crudePriceRest = new CrudePriceRest();
				
				Map<String, Object> paramMap = new HashMap<>();
				paramMap.put("pricingDate", vo.getPricingDate());
				paramMap.put("crudeNameE", crudeNameE);
				List<Map<String, Object>> crudePriceMapList = agioMapper.queryPointYear(paramMap);
				
				crudePriceRest.setCrudeNameE(crudeNameE);
				crudePriceRest.setDataList(BeanConvertUtils.mapToBeanInList(crudePriceMapList, CrudePriceDetailRest.class));
				
				result.add(crudePriceRest);
				
			}
			
		} catch (Exception e) {
			/*log*/
			res.setStatus(Result.ERROR);
			res.setMessage(e.getMessage());
		}
		
		return result;
	}

}
