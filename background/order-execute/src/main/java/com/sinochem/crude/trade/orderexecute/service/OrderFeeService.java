package com.sinochem.crude.trade.orderexecute.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.orderexecute.dao.OrderFeeMapper;
import com.sinochem.crude.trade.orderexecute.domain.OrderFee;
import com.sinochem.crude.trade.orderexecute.model.OrderFeeVO; 

public interface OrderFeeService {
	
	public abstract OrderFeeMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(OrderFee orderFee);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long orderFeeId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(OrderFee  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(OrderFee orderFee) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(OrderFee orderFee);
	
	
	/**
	 * 根据主键-查询对象
	 */
	OrderFee findByPrimaryKey(Long orderFeeId);

	/**
	 * 根据uuid查询对象
	 */
	OrderFee findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<OrderFee> queryByEntitys(OrderFee orderFee);
		
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
	 * 查询费用信息
	 * @param vo
	 * @return 
	 */
	public abstract List<Map<String, Object>> selectInformation(Map<String, Object> map);
	
	public abstract List<Map<String, Object>> selectClassified(Map<String, Object> map);
	
	Map<String, Object> selectId(Map<String, Object> map);
	
	/**
	 * 新增或者更新信息
	 */
	void saveOrderFee(OrderFeeVO vo, CurrentUser user);


	
	
}
