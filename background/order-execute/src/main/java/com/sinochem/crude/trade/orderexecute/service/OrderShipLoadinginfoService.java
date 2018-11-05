package com.sinochem.crude.trade.orderexecute.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.controller.openapi.vo.PortLoadingInfoVO;
import com.sinochem.crude.trade.orderexecute.dao.OrderShipLoadinginfoMapper;
import com.sinochem.crude.trade.orderexecute.domain.Order;
import com.sinochem.crude.trade.orderexecute.domain.OrderShipLoadinginfo;
import com.sinochem.crude.trade.orderexecute.model.OrderShipLoadinginfoVO; 

public interface OrderShipLoadinginfoService {
	
	public abstract OrderShipLoadinginfoMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(OrderShipLoadinginfo orderShipLoadinginfo);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long id) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(OrderShipLoadinginfo  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(OrderShipLoadinginfo orderShipLoadinginfo) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(OrderShipLoadinginfo orderShipLoadinginfo);
	
	
	/**
	 * 根据主键-查询对象
	 */
	OrderShipLoadinginfo findByPrimaryKey(Long id);

	/**
	 * 根据uuid查询对象
	 */
	OrderShipLoadinginfo findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<OrderShipLoadinginfo> queryByEntitys(OrderShipLoadinginfo orderShipLoadinginfo);
		
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
	
	List<OrderShipLoadinginfo> findByOrderNo(String orderNo);

	/**
	 * 外部系统-同步装港信息
	 */
	void saveLoadingInfo(PortLoadingInfoVO vo, Order order);
	/**
	 * 新增装货信息
	 * @param vo
	 */
	void insertShipLoading(OrderShipLoadinginfoVO vo, CurrentUser user);

	void updateShipLoading(OrderShipLoadinginfoVO vo, CurrentUser user);
	
}
