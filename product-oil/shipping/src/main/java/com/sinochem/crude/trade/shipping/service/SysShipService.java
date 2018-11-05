package com.sinochem.crude.trade.shipping.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.dao.SysShipMapper;
import com.sinochem.crude.trade.shipping.domain.SysShip;
import com.sinochem.crude.trade.shipping.model.query.SysShipQuery;
import com.sinochem.crude.trade.shipping.model.vo.SysShipVO;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;

public interface SysShipService {
	
	public abstract SysShipMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(SysShip sysShip);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long sysShipId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(SysShip  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(SysShip sysShip) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(SysShip sysShip) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(SysShip sysShip);	
	
	/**
	 * 根据主键-查询对象
	 */
	SysShip findByPrimaryKey(Long sysShipId);

	/**
	 * 根据uuid查询对象
	 */
	SysShip findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<SysShip> queryByEntitys(SysShip sysShip) throws Exception;
		
	/**
	 * 根据条件-查询全部
	 */
	List<Map<String, Object>> queryRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-分页查询
	 */
	Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo);
	
	/**
	 * 根据条件-查询记录条数
	 */
	int countRecords(Map<String, Object> map); 
	
	

	//**************************以下方法为开发者补充*********************************/
	/**
	 * 根据条件-查询记录条数
	 */
	PageInfoResult<SysShip> queryByParamList(SysShipQuery sysShipQuery, PageInfo pageInfo);
	
	/**
	 * 新增平台船舶信息
	 * @param vo
	 */
	public abstract Long saveSysShip(SysShipVO vo, CurrentUser user);
	
	/**
	 * 修改平台船舶信息
	 * @param vo
	 */
	public abstract void updateSysShip(SysShipVO vo, CurrentUser user) throws Exception;

	/**
	 * 删除平台船舶信息
	 * @param vo
	 */
	public abstract void delSysShip(SysShipVO vo) throws Exception ;
	
	/**
	 * 查询平台船舶信息详细
	 * @param vo
	 */
	public abstract SysShip findSysShipDetail(SysShipVO vo) throws Exception;
	
	/**
	 * 查询平台船舶信息列表
	 * @param vo
	 */
	public abstract List<SysShip> findSysShipList(SysShipVO vo) throws Exception;
	
	/**
	 * 查询所有的船舶
	 * @return
	 */
	public List<SysShip> findAllList();


	/**
	 * 根据vo的Long id主要查询imo
	 * @param shipVo
	 * @return
	 */
	SysShipVO findShipVoByShipIdRemote(SysShipVO shipVo);

	/**
	 * 调用原油dubbo服务，把船舶信息插入原油船舶列表，为航程追踪作准备
	 * @param sysShip
	 * @return
	 */
	void insertShipRemote(SysShip ship);
}
