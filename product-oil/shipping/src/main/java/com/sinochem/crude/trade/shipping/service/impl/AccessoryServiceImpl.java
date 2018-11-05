package com.sinochem.crude.trade.shipping.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.dao.AccessoryMapper;
import com.sinochem.crude.trade.shipping.dao.SysShipMapper;
import com.sinochem.crude.trade.shipping.domain.Accessory;
import com.sinochem.crude.trade.shipping.domain.SysShip;
import com.sinochem.crude.trade.shipping.exceptions.TransportException;
import com.sinochem.crude.trade.shipping.model.vo.AccessoryVO;
import com.sinochem.crude.trade.shipping.service.AccessoryService;
import com.sinochem.crude.trade.shipping.service.SimplePageInfo;
//import com.runyi.ryplat.api.commons.SimplePageInfo;

@Service
public class AccessoryServiceImpl implements AccessoryService {
	@Autowired
	private AccessoryMapper _AccessoryMapper;
	@Autowired
	private SysShipMapper _SysShipMapper;
	
	
	public AccessoryMapper getMapper(){
		return _AccessoryMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<Accessory> queryByEntitys(Accessory accessory){
		return  _AccessoryMapper.queryByEntitys(accessory);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public Accessory findByPrimaryKey(Long accessoryId){
		return  _AccessoryMapper.findByPrimaryKey(accessoryId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public Accessory findByUuid(String uuid){
		return  _AccessoryMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(Accessory accessory) {
		_AccessoryMapper.updateRecordById(accessory);
	}
	
	/**
	 * 根据主键-删除对象
	 */
	public void deleteRecordByKey(Long accessoryId) {
		_AccessoryMapper.deleteById(accessoryId);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(Accessory accessory){
		 _AccessoryMapper.insertRecord(accessory);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _AccessoryMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _AccessoryMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _AccessoryMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量删除
	 */
	public void deleteRecords(Accessory accessory){
		_AccessoryMapper.deleteRecords(accessory);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_AccessoryMapper.updateRecords(map);
	}

	
	//**************************以下方法为开发者补充*********************************/

	/**
	 * 保存附件
	 */
	@Override
	public void saveAccessory(Accessory vo, CurrentUser user) {
		//校验
		vo.setUuid(UUID.randomUUID().toString());
		vo.setCreateDate(DateUtil.getCurrentDate());
		vo.setUpdateDate(DateUtil.getCurrentDate());

		vo.setAliveFlag(Constants.SAVE_FLAG);
		vo.setUpdateUser(user.getEpMemberId());
		vo.setCreateUser(user.getEpMemberId());
		
		// 添加附件信息
		this.insertRecord(vo);
	}
	
	/**
	 * 删除船舶附件信息
	 * @throws TransportException 
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delAccessory(AccessoryVO vo) throws TransportException {

		// 取得船舶id
		Long id = vo.getSysShipId();
		
		// 附件uuid、船舶id校验
		if (id == null) {
			throw new TransportException(TransportException.TYPE.ITSH218);
		}
		
		//根据附件uuid或船舶id删除附件信息
		Accessory param = new Accessory();
		param.setSysShipId(id);
		this.deleteRecords(param);
	}
	
	/**
	 * 查询附件
	 * @throws TransportException 
	 */
	@Override
	public List<Accessory> findAccessory(AccessoryVO vo) throws TransportException {
		String sysShipUuid = vo.getUuid();

		//根据船舶uuid查询船舶信息
		SysShip sysShip = _SysShipMapper.findByUuid(sysShipUuid);
		if (sysShip == null){
			throw new TransportException(TransportException.TYPE.ITSH201);
		}
		
		Long sysShipId = sysShip.getSysShipId();
		Accessory voAc = new Accessory();
		voAc.setSysShipId(sysShipId);
		// 查询附件信息列表
		List<Accessory> accessoryList = (List<Accessory>)this.queryByEntitys(voAc);
		
		return accessoryList;
	}
}
