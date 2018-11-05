package com.sinochem.crude.trade.shipping.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.dao.AccessoryMapper;
import com.sinochem.crude.trade.shipping.domain.Accessory;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.shipping.exceptions.TransportException;
import com.sinochem.crude.trade.shipping.model.vo.AccessoryVO;

public interface AccessoryService {
	
	public abstract AccessoryMapper getMapper(); 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public abstract List<Accessory> queryByEntitys(Accessory accessory);
	
	/**
	 * 根据主键-查询对象
	 */
	public abstract Accessory findByPrimaryKey(Long accessoryId);
 
 	/**
	 * 根据UUID-查询对象
	 */
	public abstract Accessory findByUuid(String uuid);
 
	/**
	 * 根据主键-修改对象
	 */
	public abstract void updateRecord(Accessory accessory);
	
	/**
	 * 新增
	 */
	public abstract void insertRecord(Accessory accessory);
	
	/**
	 * 根据主键删除数据
	 */
	public abstract void deleteRecordByKey(Long accessoryId);
	
	/**
	 * 根据条件-查询全部
	 */
	public abstract List<Map<String, Object>> queryRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 根据条件-查询记录条数
	 */
	public abstract int countRecords(Map<String, Object> map); 
	
	/**
	 * 根据条件-批量删除
	 */
	public abstract void deleteRecords(Accessory accessory);

	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map);

	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 保存附件
	 * @param vo
	 * @param user
	 */
	public void saveAccessory(Accessory vo,CurrentUser user);
	
	/**
	 * 删除船舶附件信息
	 * @param vo
	 */
	public abstract void delAccessory(AccessoryVO vo) throws TransportException;
	
	/**
	 * 查询附件
	 * @param vo
	 * @return
	 */
	public List<Accessory> findAccessory(AccessoryVO vo) throws TransportException;
}
