package com.sinochem.crude.trade.orderexecute.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.PortDischargeInfoVO;
import com.sinochem.crude.trade.orderexecute.dao.OrderShipUnloadinginfoMapper;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderShipUnloadinginfo;
import com.sinochem.crude.trade.orderexecute.model.OrderShipUnloadinginfoVO; 

public interface OrderShipUnloadinginfoService {
	
	public abstract OrderShipUnloadinginfoMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(OrderShipUnloadinginfo orderShipUnloadinginfo);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(OrderShipUnloadinginfo  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(OrderShipUnloadinginfo orderShipUnloadinginfo) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(OrderShipUnloadinginfo orderShipUnloadinginfo);
	
	
	/**
	 * 根据主键-查询对象
	 */
	OrderShipUnloadinginfo findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	OrderShipUnloadinginfo findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<OrderShipUnloadinginfo> queryByEntitys(OrderShipUnloadinginfo orderShipUnloadinginfo);
		
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
	
	List<OrderShipUnloadinginfo> findByOrderNo(String orderNo);
	
	/**
	 * 外部系统-同步卸港信息
	 */
	void saveUnloadingInfo(PortDischargeInfoVO vo, Order order);
	/**
	 * 新增卸货信息
	 * @param vo
	 */
	void insertShipUnloading(OrderShipUnloadinginfoVO vo,CurrentUser user);
	/**
	 * 修改卸货信息
	 * @param vo
	 * @param user
	 * @return
	 */
	void updateShipUnloadingInfo(OrderShipUnloadinginfoVO vo,CurrentUser user);
}
