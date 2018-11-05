package com.sinochem.crude.trade.info.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eyeieye.melody.util.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.FuturesPriceInterfaceMapper;
import com.sinochem.crude.trade.info.domain.ExternalInteractive;
import com.sinochem.crude.trade.info.domain.FuturesPrice;
import com.sinochem.crude.trade.info.domain.FuturesPriceInterface;
import com.sinochem.crude.trade.info.domain.Oil;
import com.sinochem.crude.trade.info.service.FuturesPriceInterfaceService;
import com.sinochem.crude.trade.info.service.FuturesPriceService;
import com.sinochem.crude.trade.info.service.OilService;
import com.sinochem.it.b2b.common.exception.BizException;

@Service
public class FuturesPriceInterfaceServiceImpl implements FuturesPriceInterfaceService {
	@Autowired
	private FuturesPriceInterfaceMapper futuresPriceInterfaceMapper;
	
	@Autowired
	private FuturesPriceService futuresPriceService;
	
	@Autowired
	private OilService oilService;
	
	private static final Log log = LogFactory.getLog(FuturesPriceInterfaceServiceImpl.class);
	
	public FuturesPriceInterfaceMapper getMapper(){
		return futuresPriceInterfaceMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(FuturesPriceInterface futurespriceinterface){
		 return futuresPriceInterfaceMapper.insertRecord(futurespriceinterface);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return futuresPriceInterfaceMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(FuturesPriceInterface  record){
    	return futuresPriceInterfaceMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(FuturesPriceInterface futuresPriceInterface) throws BizException{
		if ( futuresPriceInterface.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return futuresPriceInterfaceMapper.updateRecordById(futuresPriceInterface);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return futuresPriceInterfaceMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(FuturesPriceInterface futuresPriceInterface){
		return futuresPriceInterfaceMapper.updateRecords(futuresPriceInterface.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public FuturesPriceInterface findByPrimaryKey(Long id){
		return  futuresPriceInterfaceMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public FuturesPriceInterface findByUuid(String uuid){
		return  futuresPriceInterfaceMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<FuturesPriceInterface> queryByEntitys(FuturesPriceInterface futuresPriceInterface){
		return  futuresPriceInterfaceMapper.queryByEntitys(futuresPriceInterface);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return futuresPriceInterfaceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) futuresPriceInterfaceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return futuresPriceInterfaceMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 处理原油期货价格接口数据
	 * @param ei
	 * @throws Exception
	 */
	public void processFuturesPrice(ExternalInteractive ei) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = objectMapper.readValue(ei.getBizContent(), Map.class);
		log.info("业务参数map--->" + map);
		
		map.put("pricingDate", DateUtil.strToDate((String)map.get("pricingDate"), "yyyy-MM-dd"));
		FuturesPriceInterface futuresPrice = BeanConvertUtils.mapToBean(map, FuturesPriceInterface.class);
		futuresPrice.setExternalSystem(ei.getSysCode());
		futuresPrice.setInterfaceDate(new Date());
		futuresPrice.setStatus(Constants.STATUS_00);
		futuresPrice.setAliveFlag(Constants.ALIVE_FLAG_1);
		
		//根据逻辑主键判断数据是否已存在，存在则更新，不存在则新增
		FuturesPriceInterface param = new FuturesPriceInterface();
		param.setCrudeNameC(futuresPrice.getCrudeNameC());
		param.setCrudeNameE(futuresPrice.getCrudeNameE());
		param.setPricingDate(futuresPrice.getPricingDate());
		param.setExternalSystem(futuresPrice.getExternalSystem());
		param.setAliveFlag(Constants.ALIVE_FLAG_1);
		if(CollectionUtils.isNotEmpty(this.queryByEntitys(param))){
			futuresPrice.setId(this.queryByEntitys(param).get(0).getId());
			this.updateRecordById(futuresPrice);
			log.info("更新记录");
		}else{
			futuresPrice.setUuid(UUID.randomUUID().toString().replace("-", ""));
			this.insertRecord(futuresPrice);
			log.info("新增记录");
		}
	}
	
	/**
	 * 弃用(单个)
	 * @param uuid
	 * @param processPerson
	 * @throws Exception
	 */
	public void abandonByUuid(String uuid, String processPerson) throws Exception{
		FuturesPriceInterface fpi = this.findByUuid(uuid);
		fpi.setStatus(Constants.STATUS_02);
		fpi.setProcessDate(new Date());
		fpi.setProcessPerson(processPerson);
		this.updateRecordById(fpi);
	}
	
	/**
	 * 使用(单个)
	 * @param uuid
	 * @param processPerson
	 * @throws Exception
	 */
	public void useByUuid(String uuid, String processPerson) throws Exception{
		//更新中间表的数据状态
		FuturesPriceInterface futuresPriceInterface = this.findByUuid(uuid);
		futuresPriceInterface.setStatus(Constants.STATUS_01);
		futuresPriceInterface.setProcessDate(new Date());
		futuresPriceInterface.setProcessPerson(processPerson);
		this.updateRecordById(futuresPriceInterface);
		
		//插入实际业务表
		FuturesPrice param = new FuturesPrice();
		param.setSource("1"); //来自接口
		param.setInterfaceId(futuresPriceInterface.getId());
		if(CollectionUtils.isNotEmpty(futuresPriceService.queryByEntitys(param))){
			FuturesPrice futuresPrice = futuresPriceService.queryByEntitys(param).get(0);
			futuresPrice.setPricing(futuresPriceInterface.getPricing());
			futuresPrice.setNote(futuresPriceInterface.getNote());
			futuresPriceService.updateRecordById(futuresPrice);
		}else{
			FuturesPrice futuresPrice = new FuturesPrice();
			BeanUtils.copyProperties(futuresPriceInterface, futuresPrice);
			Oil oil = oilService.findByName(futuresPriceInterface.getCrudeNameE(), futuresPriceInterface.getCrudeNameC());
			if(oil==null)
				throw new Exception("未找到对应的原油");
			futuresPrice.setCrudeId(oil.getId());
			futuresPrice.setCreatePerson(processPerson);
			futuresPrice.setSource("1");
			futuresPrice.setInterfaceId(futuresPriceInterface.getId());
			futuresPriceService.insertFuturesPrice(futuresPrice);
		}
	}
	
	/**
	 * 全部弃用
	 * @param processPerson
	 * @throws Exception
	 */
	public void abandonAll(String processPerson) throws Exception{
		//
		FuturesPriceInterface param = new FuturesPriceInterface();
		param.setStatus(Constants.STATUS_00);
		for(FuturesPriceInterface fpi : this.queryByEntitys(param)){
			fpi.setStatus(Constants.STATUS_02);
			fpi.setProcessDate(new Date());
			fpi.setProcessPerson(processPerson);
			this.updateRecordById(fpi);
		}
	}
	
	/**
	 * 全部使用
	 */
	public void useAll(String processPerson) throws Exception{
		FuturesPriceInterface param = new FuturesPriceInterface();
		param.setStatus(Constants.STATUS_00);
		for(FuturesPriceInterface fpi : this.queryByEntitys(param)){
			this.useByUuid(fpi.getUuid(), processPerson);
		}
	}
}
