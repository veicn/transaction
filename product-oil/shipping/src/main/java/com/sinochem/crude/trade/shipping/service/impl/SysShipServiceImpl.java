package com.sinochem.crude.trade.shipping.service.impl;

import java.util.List;
import java.util.Map;

import com.sinochem.crude.trade.transport.remote.IShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.dao.SysShipMapper;
import com.sinochem.crude.trade.shipping.domain.Accessory;
import com.sinochem.crude.trade.shipping.domain.SysShip;
import com.sinochem.crude.trade.shipping.exceptions.TransportException;
import com.sinochem.crude.trade.shipping.model.query.SysShipQuery;
import com.sinochem.crude.trade.shipping.model.vo.SysShipVO;
import com.sinochem.crude.trade.shipping.service.AccessoryService;
import com.sinochem.crude.trade.shipping.service.SimplePageInfo;
import com.sinochem.crude.trade.shipping.service.SysShipService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.page.PageUtils;



@Service
public class SysShipServiceImpl implements SysShipService {
	@Autowired
	private SysShipMapper sysShipMapper;
	
	@Autowired
	private AccessoryService accessoryService;

	@Autowired
	private IShipService iShipService;
	
	public SysShipMapper getMapper(){
		return sysShipMapper;
	} 
	
	/**
	 * 新增
	 */
	@Override
	public int insertRecord(SysShip sysship){
		 return sysShipMapper.insertRecord(sysship);
	}
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	@Override
	public int deleteById(Long sysShipId) throws BizException{
		if (sysShipId == null) {
			throw new BizException("id 为空，不能修改 ");
		}
		return sysShipMapper.deleteById(sysShipId);
	}
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(SysShip  record){
    	return sysShipMapper.deleteRecords(record);
    }
	
	/**
	 * 根据主键-修改对象
	 */
	@Override
	public int updateRecordById(SysShip sysShip) throws BizException{
		if ( sysShip.getSysShipId() == null  ) {
			throw new BizException("sysShipId 为空，不能修改 ");
		}
		return sysShipMapper.updateRecordById(sysShip);
	}
	
	/**
	 * 根据uuid-修改对象
	 */
	@Override
	public int updateRecordByUuid(SysShip sysShip) throws BizException{
		if ( sysShip.getUuid() == null) {
			throw new BizException("uuid为空，不能修改 ");
		}
		return sysShipMapper.updateRecordByUuid(sysShip);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(Map<String, Object> map) {
		return sysShipMapper.updateRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	@Override
	public int updateRecords(SysShip sysShip){
		return sysShipMapper.updateRecords(sysShip.toMap());
	}
	
	
	/**
	 * 根据主键-查询对象
	 */
	@Override
	public SysShip findByPrimaryKey(Long sysShipId){
		return  sysShipMapper.findByPrimaryKey(sysShipId);	
	}
	
	/**
	 * 根据uuid查询对象
	 */
	@Override
	public SysShip findByUuid(String uuid){
		return  sysShipMapper.findByUuid(uuid);	
	}
	
	/**
	 * 根据对象-查询对象列表
	 */
	@Override
	public List<SysShip> queryByEntitys(SysShip sysShip){
		return  sysShipMapper.queryByEntitys(sysShip);
	}
	
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return sysShipMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	@Override
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) sysShipMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	@Override
	public int countRecords(Map<String, Object> map){
		return sysShipMapper.countRecords(map);
	}


	//**************************以下方法为开发者补充*********************************/
	/**
	 * 根据条件-查询全部
	 */
	@Override
	public PageInfoResult<SysShip> queryByParamList(SysShipQuery sysShipQuery, PageInfo pageInfo) {
		PageUtils.page(pageInfo);
		List<SysShip> rls = sysShipMapper.queryByParamList(sysShipQuery);
		return new PageInfoResult(rls);
	}
	
	/**
	 * 添加平台船舶信息
	 */
	@Override
	public Long saveSysShip(SysShipVO vo, CurrentUser user) {
		// 校验
		vo.setAliveFlag(Constants.SAVE_FLAG);
		vo.setUpdateUser(user.getMemberId());
		vo.setCreateUser(user.getMemberId());
		SysShip domain = vo.getDomain();
		domain.setCreateDate(DateUtil.getCurrentDate());
		domain.setUpdateDate(DateUtil.getCurrentDate());
		
		// 添加船舶信息
		this.insertRecord(domain);
		
		// 取得新增船务ID
		Long sysShipId = domain.getSysShipId();

		// 插入附件
		if (vo.getFileInfoList() != null) {
			for(int i = 0; i < vo.getFileInfoList().size(); i++) {
				Accessory accessory = vo.getFileInfoList().get(i);
				accessory.setSysShipId(sysShipId);
				accessoryService.saveAccessory(accessory, user);
			}
		}
		
		return sysShipId;
	}

	@Override
	public void updateSysShip(SysShipVO vo, CurrentUser user) throws Exception {
		// 取得uuid
		String uuid = vo.getUuid();
		
		// 船舶uuid校验
		if (StringUtils.isNullOrEmpty(uuid)) {
			throw new TransportException(TransportException.TYPE.ITSH200);
		}
		
		//根据船舶uuid查询船舶信息
		SysShip sysShip = this.findByUuid(uuid);
		
		if (sysShip == null) {
			throw new TransportException(TransportException.TYPE.ITSH201);
		}
		
		Long sysShipId = sysShip.getSysShipId();
		vo.setSysShipId(sysShipId);
		vo.setUpdateUser(user.getMemberId());		
		SysShip domain = vo.getDomain();
		domain.setCreateDate(DateUtil.getCurrentDate());
		domain.setUpdateDate(DateUtil.getCurrentDate());		// 修改船舶信息
		this.updateRecordById(domain);
		
		// 插入附件
		if (vo.getFileInfoList() != null) {
			for (int i = 0; i < vo.getFileInfoList().size(); i++) {
				Accessory accessory = vo.getFileInfoList().get(i);
				accessory.setSysShipId(sysShipId);;
				accessoryService.saveAccessory(accessory, user);
			}
		}
	}

	/**
	 * 删除平台船舶信息
	 */
	@Override
	public void delSysShip(SysShipVO vo) throws Exception {
		// 取得uuid
		String uuid = vo.getUuid();
		
		// 船舶uuid校验
		if (StringUtils.isNullOrEmpty(uuid)) {
			throw new TransportException(TransportException.TYPE.ITSH200);
		}
		
		//根据船舶uuid查询船舶信息
		SysShip sysShip = this.findByUuid(uuid);
		
		if (sysShip == null) {
			throw new TransportException(TransportException.TYPE.ITSH201);
		}
		
		// 取得船舶主键
		Long sysShipId = sysShip.getSysShipId();
		
		// 删除船舶信息
		this.deleteById(sysShipId);
		
		// 删除平台船舶附件
		Accessory accessory = new Accessory();
		accessory.setSysShipId(sysShipId);
		accessoryService.deleteRecords(accessory);
		
	}

	/**
	 * 查询平台船舶信息详细
	 */
	@Override
	public SysShip findSysShipDetail(SysShipVO vo) throws Exception {
		// 取得uuid
		String uuid = vo.getUuid();
		
		// 船舶uuid校验
		if (StringUtils.isNullOrEmpty(uuid)) {
			throw new TransportException(TransportException.TYPE.ITSH200);
		}
		
		//根据船舶uuid查询船舶信息
		SysShip sysShip = this.findByUuid(uuid);
		
		if (sysShip == null) {
			throw new TransportException(TransportException.TYPE.ITSH201);
		}
		
		// 取得船舶主键
		Long sysShipId = sysShip.getSysShipId();
		
		// 查询船舶信息
		SysShip sysShipDetail = this.findByPrimaryKey(sysShipId);
		
		return sysShipDetail;
	}
	
	/**
	 * 查询平台船舶名称下拉列表
	 */
	@Override
	public List<SysShip> findSysShipList(SysShipVO vo) throws Exception {
		SysShip domain = vo.getDomain();
		
		// 查询船舶信息列表
		List<SysShip> sysShipList = (List<SysShip>)this.queryByEntitys(domain);
		
		return sysShipList;
	}

	/**
	 * 查询所有的船舶信息（调用原有的dubbo接口）
	 * @return
	 */
	@Override
	public List<SysShip> findAllList() {
		List<SysShip> sysShipList = sysShipMapper.findAllList();
		return sysShipList;
	}

	/**
	 * 	根据vo的Long id主要查询imo 武刚鹏-2018年4月20日16:44:09
	 * @param shipVO
	 * @return
	 */
	@Override
	public SysShipVO findShipVoByShipIdRemote(SysShipVO shipVO){
		SysShip sysShip = sysShipMapper.findByPrimaryKey(shipVO.getSysShipId());
		if(sysShip != null){
			shipVO.convertToVO(sysShip);
			return shipVO;
		}
		return null;

	}

	/**
	 * 插入船舶到原油列表，准备航程追踪
	 * @param ship
	 */
    @Override
    public void insertShipRemote(SysShip ship) {
		List<SysShip> sysShipList = sysShipMapper.queryByEntitys(ship);
		SysShip sysShip = null;
		if(sysShipList!=null && sysShipList.size()>0){
			 sysShip  = sysShipList.get(0);
		}
		if(sysShip!=null){
			com.sinochem.crude.trade.transport.remote.SysShipVO sysShipRemoteVO = new com.sinochem.crude.trade.transport.remote.SysShipVO();
			sysShipRemoteVO.setAccessory(sysShip.getAccessory());
			sysShipRemoteVO.setImo(sysShip.getImo());
			sysShipRemoteVO.setUuid(sysShip.getUuid());
			sysShipRemoteVO.setDraft(sysShip.getDraft());
			sysShipRemoteVO.setHull(sysShip.getHullType());
			sysShipRemoteVO.setName(sysShip.getVesselName());
			sysShipRemoteVO.setType(sysShip.getVesselType());
			sysShipRemoteVO.setCompleteDate(sysShip.getBuilt());
			iShipService.insertProductoilShip(sysShipRemoteVO);
		}
    }
}
