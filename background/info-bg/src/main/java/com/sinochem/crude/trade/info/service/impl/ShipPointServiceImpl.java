package com.sinochem.crude.trade.info.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.dao.ShipPointMapper;
import com.sinochem.crude.trade.info.domain.ShipPoint;
import com.sinochem.crude.trade.info.service.ShipPointService;
import com.sinochem.it.b2b.common.exception.BizException;

@Service
public class ShipPointServiceImpl implements ShipPointService {
	@Autowired
	private ShipPointMapper shipPointMapper;
	
	public ShipPointMapper getMapper(){
		return shipPointMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(ShipPoint shippoint){
		 return shipPointMapper.insertRecord(shippoint);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long id) throws BizException{
		if (id == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return shipPointMapper.deleteById(id);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(ShipPoint  record){
    	return shipPointMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(ShipPoint shipPoint) throws BizException{
		if ( shipPoint.getId() == null  ) {
			throw new BizException("id 为空，不能修改 ");
		}
		return shipPointMapper.updateRecordById(shipPoint);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return shipPointMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(ShipPoint shipPoint){
		return shipPointMapper.updateRecords(shipPoint.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public ShipPoint findByPrimaryKey(Long id){
		return  shipPointMapper.findByPrimaryKey(id);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public ShipPoint findByUuid(String uuid){
		return  shipPointMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<ShipPoint> queryByEntitys(ShipPoint shipPoint){
		return  shipPointMapper.queryByEntitys(shipPoint);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return shipPointMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) shipPointMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return shipPointMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 获取最新的一条记录
	 * @return
	 */
	public ShipPoint getLatest(String diliveryRegion){
		ShipPoint param = new ShipPoint();
		param.setLatestFlag("1");
		param.setAliveFlag(Constants.ALIVE_FLAG_1);
		param.setDiliveryRegion(diliveryRegion);
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
	public int insertShipPoint(ShipPoint shipPoint) throws Exception{
		//设置插入初始值
		shipPoint.setId(null);
		shipPoint.setUuid(UUID.randomUUID().toString().replace("-", ""));
		shipPoint.setCreateDate(new Date());
		shipPoint.setAliveFlag("1");
		
		ShipPoint latest = this.getLatest(shipPoint.getDiliveryRegion());
		
		if(latest==null){
			shipPoint.setLatestFlag("1");
			return this.insertRecord(shipPoint);
		}else{
			if(latest.getReleaseDate().before(shipPoint.getReleaseDate())){
				latest.setLatestFlag("0");
				this.updateRecordById(latest);
				
				shipPoint.setLatestFlag("1");
				return this.insertRecord(shipPoint);
			}else{
				shipPoint.setLatestFlag("0");
				return this.insertRecord(shipPoint);
			}
		}
	}
	
	public List<Map<String,Object>> queryDilveryRegion(Date endDate){
		if(endDate==null)
			return shipPointMapper.queryDilveryRegion(new Date());
		return shipPointMapper.queryDilveryRegion(endDate);
	}
	
	public List<Map<String,Object>> queryPointYear(Map<String,Object> map){
		if(map.get("endDate")==null)
			map.put("endDate", new Date());
		return shipPointMapper.queryPointYear(map);
	}
	
	public List<Map<String,Object>> queryEndDate(String paramDateStr){
		return shipPointMapper.queryEndDate(paramDateStr);
	}
}
