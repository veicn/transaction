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
import com.sinochem.crude.trade.info.dao.AgioInterfaceMapper;
import com.sinochem.crude.trade.info.domain.Agio;
import com.sinochem.crude.trade.info.domain.AgioInterface;
import com.sinochem.crude.trade.info.domain.ExternalInteractive;
import com.sinochem.crude.trade.info.domain.Oil;
import com.sinochem.crude.trade.info.service.AgioInterfaceService;
import com.sinochem.crude.trade.info.service.AgioService;
import com.sinochem.crude.trade.info.service.OilService;
import com.sinochem.it.b2b.common.exception.BizException;

@Service
public class AgioInterfaceServiceImpl implements AgioInterfaceService {
	@Autowired
	private AgioInterfaceMapper agioInterfaceMapper;
	
	@Autowired
	private AgioService agioService;
	
	@Autowired 
	private OilService oilService;
	
	private static final Log log = LogFactory.getLog(AgioInterfaceServiceImpl.class);
	
	public AgioInterfaceMapper getMapper(){
		return agioInterfaceMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(AgioInterface agiointerface){
		 return agioInterfaceMapper.insertRecord(agiointerface);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return agioInterfaceMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(AgioInterface  record){
    	return agioInterfaceMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(AgioInterface agioInterface) throws BizException{
		if ( agioInterface.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return agioInterfaceMapper.updateRecordById(agioInterface);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return agioInterfaceMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(AgioInterface agioInterface){
		return agioInterfaceMapper.updateRecords(agioInterface.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public AgioInterface findByPrimaryKey(Long id){
		return  agioInterfaceMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public AgioInterface findByUuid(String uuid){
		return  agioInterfaceMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<AgioInterface> queryByEntitys(AgioInterface agioInterface){
		return  agioInterfaceMapper.queryByEntitys(agioInterface);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return agioInterfaceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) agioInterfaceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return agioInterfaceMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 处理实货贴水接口数据
	 * @param ei
	 * @throws Exception
	 */
	public void processAigo(ExternalInteractive ei) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = objectMapper.readValue(ei.getBizContent(), Map.class);
		log.info("业务参数map--->" + map);
		
		map.put("pricingDate", DateUtil.strToDate((String)map.get("pricingDate"), "yyyy-MM-dd"));
		AgioInterface agioInterface = BeanConvertUtils.mapToBean(map, AgioInterface.class);
		agioInterface.setChange1((String)map.get("change"));
		agioInterface.setExternalSystem(ei.getSysCode());
		agioInterface.setInterfaceDate(new Date());
		agioInterface.setStatus(Constants.STATUS_00);
		agioInterface.setAliveFlag(Constants.ALIVE_FLAG_1);
				
		//根据逻辑主键判断数据是否已存在，存在则更新，不存在则新增
		AgioInterface param = new AgioInterface();
		param.setCrudeNameC(agioInterface.getCrudeNameC());
		param.setCrudeNameE(agioInterface.getCrudeNameE());
		param.setAliveFlag(Constants.ALIVE_FLAG_1);
		param.setPricingDate(agioInterface.getPricingDate());
		param.setExternalSystem(agioInterface.getExternalSystem());
		if(CollectionUtils.isNotEmpty(this.queryByEntitys(param))){
			agioInterface.setId(this.queryByEntitys(param).get(0).getId());
			this.updateRecordById(agioInterface);
			log.info("更新记录");
		}else{
			agioInterface.setUuid(UUID.randomUUID().toString().replace("-", ""));
			this.insertRecord(agioInterface);
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
		AgioInterface aigo = this.findByUuid(uuid);
		aigo.setStatus(Constants.STATUS_02);
		aigo.setProcessDate(new Date());
		aigo.setProcessPerson(processPerson);
		this.updateRecordById(aigo);
	}
	
	/**
	 * 使用(单个)
	 * @param uuid
	 * @param processPerson
	 * @throws Exception
	 */
	public void useByUuid(String uuid, String processPerson) throws Exception{
		//更新中间表的数据状态
		AgioInterface agioInterface = this.findByUuid(uuid);
		agioInterface.setStatus(Constants.STATUS_01);
		agioInterface.setProcessDate(new Date());
		agioInterface.setProcessPerson(processPerson);
		this.updateRecordById(agioInterface);
		
		//插入实际业务表
		Agio param = new Agio();
		param.setSource("1"); //来自接口
		param.setInterfaceId(agioInterface.getId());
		if(CollectionUtils.isNotEmpty(agioService.queryByEntitys(param))){
			Agio agio = agioService.queryByEntitys(param).get(0);
			agio.setPricing(agioInterface.getPricing());
			agio.setLoadingMonth(agioInterface.getLoadingMonth());
			agio.setTransportClause(agioInterface.getTransportClause());
			agio.setChange1(agioInterface.getChange1());
			agioService.updateRecordById(agio);
		}else{
			Agio agio = new Agio();
			BeanUtils.copyProperties(agioInterface, agio);
			Oil oil = oilService.findByName(agioInterface.getCrudeNameE(), agioInterface.getCrudeNameC());
			if(oil==null)
				throw new Exception("未找到对应的原油");
			agio.setCrudeId(oil.getId());
			agio.setCreatePerson(processPerson);
			agio.setSource("1");
			agio.setInterfaceId(agioInterface.getId());
			agioService.insertAgio(agio);
		}
	}
	
	/**
	 * 全部弃用
	 * @param processPerson
	 * @throws Exception
	 */
	public void abandonAll(String processPerson) throws Exception{
		//
		AgioInterface param = new AgioInterface();
		param.setStatus(Constants.STATUS_00);
		for(AgioInterface ai : this.queryByEntitys(param)){
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
		AgioInterface param = new AgioInterface();
		param.setStatus(Constants.STATUS_00);
		for(AgioInterface ai : this.queryByEntitys(param)){
			this.useByUuid(ai.getUuid(), processPerson);
		}
	}
}
