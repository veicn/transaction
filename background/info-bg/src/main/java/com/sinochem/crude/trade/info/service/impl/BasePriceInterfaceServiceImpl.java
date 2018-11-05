package com.sinochem.crude.trade.info.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.eyeieye.melody.util.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.BasePriceInterfaceMapper;
import com.sinochem.crude.trade.info.domain.BasePriceInterface;
import com.sinochem.crude.trade.info.domain.CrudeBasePrice;
import com.sinochem.crude.trade.info.domain.ExternalInteractive;
import com.sinochem.crude.trade.info.domain.Oil;
import com.sinochem.crude.trade.info.service.BasePriceInterfaceService;
import com.sinochem.crude.trade.info.service.CrudeBasePriceService;
import com.sinochem.crude.trade.info.service.OilService;
import com.sinochem.it.b2b.common.exception.BizException;

@Service
public class BasePriceInterfaceServiceImpl implements BasePriceInterfaceService {
	@Autowired
	private BasePriceInterfaceMapper basePriceInterfaceMapper;
	
	@Autowired
	private CrudeBasePriceService basePriceService;
	
	@Autowired
	private OilService oilService;
	
	private static final Log log = LogFactory.getLog(BasePriceInterfaceServiceImpl.class);
	
	public BasePriceInterfaceMapper getMapper(){
		return basePriceInterfaceMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(BasePriceInterface basepriceinterface){
		 return basePriceInterfaceMapper.insertRecord(basepriceinterface);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return basePriceInterfaceMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(BasePriceInterface  record){
    	return basePriceInterfaceMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(BasePriceInterface basePriceInterface) throws BizException{
		if ( basePriceInterface.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return basePriceInterfaceMapper.updateRecordById(basePriceInterface);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return basePriceInterfaceMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(BasePriceInterface basePriceInterface){
		return basePriceInterfaceMapper.updateRecords(basePriceInterface.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public BasePriceInterface findByPrimaryKey(Long id){
		return  basePriceInterfaceMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public BasePriceInterface findByUuid(String uuid){
		return  basePriceInterfaceMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<BasePriceInterface> queryByEntitys(BasePriceInterface basePriceInterface){
		return  basePriceInterfaceMapper.queryByEntitys(basePriceInterface);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return basePriceInterfaceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) basePriceInterfaceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return basePriceInterfaceMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 处理原油基准价接口数据
	 * @param ei
	 * @throws Exception
	 */
	public void processBasePrice(ExternalInteractive ei) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = objectMapper.readValue(ei.getBizContent(), Map.class);
		log.info("业务参数map--->" + map);
		
		map.put("pricingDate", DateUtil.strToDate((String)map.get("pricingDate"), "yyyy-MM-dd"));
		BasePriceInterface basePriceInterface = BeanConvertUtils.mapToBean(map, BasePriceInterface.class);
		basePriceInterface.setExternalSystem(ei.getSysCode());
		basePriceInterface.setInterfaceDate(new Date());
		basePriceInterface.setStatus(Constants.STATUS_00);
		basePriceInterface.setAliveFlag(Constants.ALIVE_FLAG_1);
		
		//根据逻辑主键判断数据是否已存在，存在则更新，不存在则新增
		BasePriceInterface param = new BasePriceInterface();
		param.setCrudeNameC(basePriceInterface.getCrudeNameC());
		param.setCrudeNameE(basePriceInterface.getCrudeNameE());
		param.setAliveFlag(Constants.ALIVE_FLAG_1);
		param.setPricingDate(basePriceInterface.getPricingDate());
		param.setExternalSystem(basePriceInterface.getExternalSystem());
		if(CollectionUtils.isNotEmpty(this.queryByEntitys(param))){
			basePriceInterface.setId(this.queryByEntitys(param).get(0).getId());
			this.updateRecordById(basePriceInterface);
			log.info("更新记录");
		}else{
			basePriceInterface.setUuid(UUID.randomUUID().toString().replace("-", ""));
			this.insertRecord(basePriceInterface);
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
		BasePriceInterface basePrice = this.findByUuid(uuid);
		basePrice.setStatus(Constants.STATUS_02);
		basePrice.setProcessDate(new Date());
		basePrice.setProcessPerson(processPerson);
		this.updateRecordById(basePrice);
	}
	
	/**
	 * 使用(单个)
	 * @param uuid
	 * @param processPerson
	 * @throws Exception
	 */
	public void useByUuid(String uuid, String processPerson) throws Exception{
		//更新中间表的数据状态
		BasePriceInterface basePriceInterface = this.findByUuid(uuid);
		basePriceInterface.setStatus(Constants.STATUS_01);
		basePriceInterface.setProcessDate(new Date());
		basePriceInterface.setProcessPerson(processPerson);
		this.updateRecordById(basePriceInterface);
		
		//插入实际业务表
		CrudeBasePrice param = new CrudeBasePrice();
		param.setSource("1"); //来自接口
		param.setInterfaceId(basePriceInterface.getId());
		if(CollectionUtils.isNotEmpty(basePriceService.queryByEntitys(param))){
			CrudeBasePrice basePrice = basePriceService.queryByEntitys(param).get(0);
			basePrice.setPricing(basePriceInterface.getPricing());
			basePrice.setNote(basePriceInterface.getNote());
			basePrice.setRemark(basePriceInterface.getRemark());
			basePriceService.updateRecordById(basePrice);
		}else{
			CrudeBasePrice basePrice = new CrudeBasePrice();
			BeanUtils.copyProperties(basePriceInterface, basePrice);
			Oil oil = oilService.findByName(basePriceInterface.getCrudeNameE(), basePriceInterface.getCrudeNameC());
			if(oil==null)
				throw new Exception("未找到对应的原油");
			basePrice.setCrudeId(oil.getId());
			basePrice.setCreatePerson(processPerson);
			basePrice.setSource("1");
			basePrice.setInterfaceId(basePriceInterface.getId());
			basePriceService.insertBasePrice(basePrice);
		}
	}
	
	/**
	 * 全部弃用
	 * @param processPerson
	 * @throws Exception
	 */
	public void abandonAll(String processPerson) throws Exception{
		//
		BasePriceInterface param = new BasePriceInterface();
		param.setStatus(Constants.STATUS_00);
		for(BasePriceInterface ai : this.queryByEntitys(param)){
			ai.setStatus(Constants.STATUS_02);
			ai.setProcessDate(new Date());
			ai.setProcessPerson(processPerson);
			this.updateRecordById(ai);
		}
	}
	
	/**
	 * 全部使用
	 */
	public void useAll(String processPerson) throws Exception{
		BasePriceInterface param = new BasePriceInterface();
		param.setStatus(Constants.STATUS_00);
		for(BasePriceInterface ai : this.queryByEntitys(param)){
			this.useByUuid(ai.getUuid(), processPerson);
		}
	}
}
