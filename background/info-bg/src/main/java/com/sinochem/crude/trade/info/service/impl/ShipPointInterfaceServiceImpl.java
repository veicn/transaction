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
import com.sinochem.crude.trade.info.dao.ShipPointInterfaceMapper;
import com.sinochem.crude.trade.info.domain.ExternalInteractive;
import com.sinochem.crude.trade.info.domain.ShipPoint;
import com.sinochem.crude.trade.info.domain.ShipPointInterface;
import com.sinochem.crude.trade.info.service.OilService;
import com.sinochem.crude.trade.info.service.ShipPointInterfaceService;
import com.sinochem.crude.trade.info.service.ShipPointService;
import com.sinochem.it.b2b.common.exception.BizException;

@Service
public class ShipPointInterfaceServiceImpl implements ShipPointInterfaceService {
	@Autowired
	private ShipPointInterfaceMapper shipPointInterfaceMapper;
	
	@Autowired
	private ShipPointService shipPointService;
	
	@Autowired
	private OilService oilService;
	
	private static final Log log = LogFactory.getLog(ShipPointInterfaceServiceImpl.class);
	
	public ShipPointInterfaceMapper getMapper(){
		return shipPointInterfaceMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(ShipPointInterface shippointinterface){
		 return shipPointInterfaceMapper.insertRecord(shippointinterface);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return shipPointInterfaceMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(ShipPointInterface  record){
    	return shipPointInterfaceMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(ShipPointInterface shipPointInterface) throws BizException{
		if ( shipPointInterface.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return shipPointInterfaceMapper.updateRecordById(shipPointInterface);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return shipPointInterfaceMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(ShipPointInterface shipPointInterface){
		return shipPointInterfaceMapper.updateRecords(shipPointInterface.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public ShipPointInterface findByPrimaryKey(Long id){
		return  shipPointInterfaceMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public ShipPointInterface findByUuid(String uuid){
		return  shipPointInterfaceMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<ShipPointInterface> queryByEntitys(ShipPointInterface shipPointInterface){
		return  shipPointInterfaceMapper.queryByEntitys(shipPointInterface);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return shipPointInterfaceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) shipPointInterfaceMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return shipPointInterfaceMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 处理航运点数接口数据
	 * @param ei
	 * @throws Exception
	 */
	public void processShipPoint(ExternalInteractive ei) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = objectMapper.readValue(ei.getBizContent(), Map.class);
		log.info("业务参数map--->" + map);
		
		map.put("releaseDate", DateUtil.strToDate((String)map.get("releaseDate"), "yyyy-MM-dd"));
		ShipPointInterface shipPoint = BeanConvertUtils.mapToBean(map, ShipPointInterface.class);
		shipPoint.setExternalSystem(ei.getSysCode());
		shipPoint.setInterfaceDate(new Date());
		shipPoint.setStatus(Constants.STATUS_00);
		shipPoint.setAliveFlag(Constants.ALIVE_FLAG_1);
		
		ShipPointInterface param = new ShipPointInterface();
		param.setDischargePort(shipPoint.getDischargePort());
		param.setDispatchPort(shipPoint.getDispatchPort());
		param.setReleaseDate(shipPoint.getReleaseDate());
		param.setExternalSystem(shipPoint.getExternalSystem());
		param.setAliveFlag(Constants.ALIVE_FLAG_1);
		if(CollectionUtils.isNotEmpty(this.queryByEntitys(param))){
			shipPoint.setId(this.queryByEntitys(param).get(0).getId());
			this.updateRecordById(shipPoint);
			log.info("更新记录");
		}else{
			shipPoint.setUuid(UUID.randomUUID().toString().replace("-", ""));
			this.insertRecord(shipPoint);
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
		ShipPointInterface spi = this.findByUuid(uuid);
		spi.setStatus(Constants.STATUS_02);
		spi.setProcessDate(new Date());
		spi.setProcessPerson(processPerson);
		this.updateRecordById(spi);
	}
	
	/**
	 * 使用(单个)
	 * @param uuid
	 * @param processPerson
	 * @throws Exception
	 */
	public void useByUuid(String uuid, String processPerson) throws Exception{
		//更新中间表的数据状态
		ShipPointInterface spi = this.findByUuid(uuid);
		spi.setStatus(Constants.STATUS_01);
		spi.setProcessDate(new Date());
		spi.setProcessPerson(processPerson);
		this.updateRecordById(spi);
		
		//插入实际业务表
		ShipPoint param = new ShipPoint();
		param.setSource("1"); //来自接口
		param.setInterfaceId(spi.getId());
		if(CollectionUtils.isNotEmpty(shipPointService.queryByEntitys(param))){
			ShipPoint shipPoint = shipPointService.queryByEntitys(param).get(0);
			shipPoint.setPoint(spi.getPoint());
			shipPoint.setBucketFreight(spi.getBucketFreight());
			shipPoint.setRemarks(spi.getRemarks());
			shipPoint.setShipType(spi.getShipType());
			shipPointService.updateRecordById(shipPoint);
		}else{
			ShipPoint shipPoint = new ShipPoint();
			BeanUtils.copyProperties(spi, shipPoint);
			shipPoint.setCreatePerson(processPerson);
			shipPoint.setSource("1");
			shipPoint.setInterfaceId(spi.getId());
			shipPointService.insertShipPoint(shipPoint);
		}
	}
	
	/**
	 * 全部弃用
	 * @param processPerson
	 * @throws Exception
	 */
	public void abandonAll(String processPerson) throws Exception{
		//
		ShipPointInterface param = new ShipPointInterface();
		param.setStatus(Constants.STATUS_00);
		for(ShipPointInterface spi : this.queryByEntitys(param)){
			spi.setStatus(Constants.STATUS_02);
			spi.setProcessDate(new Date());
			spi.setProcessPerson(processPerson);
			this.updateRecordById(spi);
		}
	}
	
	/**
	 * 全部使用
	 */
	public void useAll(String processPerson) throws Exception{
		ShipPointInterface param = new ShipPointInterface();
		param.setStatus(Constants.STATUS_00);
		for(ShipPointInterface spi : this.queryByEntitys(param)){
			this.useByUuid(spi.getUuid(), processPerson);
		}
	}
	
	
}
