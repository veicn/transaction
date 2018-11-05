package com.sinochem.crude.trade.shiprefueling.service.impl;

import com.sinochem.crude.trade.shiprefueling.dao.GoryMapper;
import com.sinochem.crude.trade.shiprefueling.domain.po.Gory;
import com.sinochem.crude.trade.shiprefueling.service.GoryService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GoryServiceImpl implements GoryService {
	@Autowired
	private GoryMapper goryMapper;
	
	public GoryMapper getMapper(){
		return goryMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(Gory gory){
		 return goryMapper.insertRecord(gory);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long palletId) throws BizException{
		if (palletId == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return goryMapper.deleteById(palletId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(Gory  record){
    	return goryMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(Gory gory) throws BizException{
		if ( gory.getPalletId() == null  ) {
			throw new BizException("palletId 为空，不能修改 ");
		}
		return goryMapper.updateRecordById(gory);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(Gory gory) throws BizException {
		if ( gory.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return goryMapper.updateRecordByUuid(gory);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return goryMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Gory gory){
		return goryMapper.updateRecords(gory.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public Gory findByPrimaryKey(Long palletId){
		return  goryMapper.findByPrimaryKey(palletId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public Gory findByUuid(String uuid){
		return  goryMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<Gory> queryByEntitys(Gory gory){
		return  goryMapper.queryByEntitys(gory);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return goryMapper.queryRecords(map);
	}
	

	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return goryMapper.countRecords(map);
	}
	
	//**************************以下方法为开发者补充*********************************/
	/**
	 * 根据订单ID查询 品类信息
	 */
	@Override
	public List<Gory> queryGoryListByOrderId(Long orderId) {
		return goryMapper.findByOrderId( orderId);
	}

	@Override
	public int updateAliveFlagByOrderId(Long orderId, String flag) {
		return goryMapper.updateAliveFlagByOrderId(orderId, flag);
	}
}
