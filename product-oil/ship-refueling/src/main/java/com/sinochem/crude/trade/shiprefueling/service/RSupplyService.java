package com.sinochem.crude.trade.shiprefueling.service;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.controller.common.ResultDatas;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.dao.RSupplyMapper;
import com.sinochem.crude.trade.shiprefueling.domain.RSupplyCombine;
import com.sinochem.crude.trade.shiprefueling.domain.po.RIgnition;
import com.sinochem.crude.trade.shiprefueling.domain.po.RSupply;
import com.sinochem.crude.trade.shiprefueling.model.vo.QueryRIgnitionVO;
import com.sinochem.crude.trade.shiprefueling.model.vo.QueryRSupplyVO;
import com.sinochem.it.b2b.common.exception.BizException;

import java.text.ParseException;
import java.util.List;
import java.util.Map;



public interface RSupplyService {
	
	public abstract RSupplyMapper getMapper();
	
	/**
	 * 新增
	 */
	int insertRecord(RSupply rSupply);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long orderId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(RSupply  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(RSupply rSupply) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(RSupply rSupply) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(RSupply rSupply);
	
	
	/**
	 * 根据主键-查询对象
	 */
	RSupply findByPrimaryKey(Long orderId);

	/**
	 * 根据uuid查询对象
	 */
	RSupply findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<RSupply> queryByEntitys(RSupply rSupply);
		
	/**
	 * 根据条件-查询全部
	 */
	List<Map<String, Object>> queryRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-查询记录条数
	 */
	int countRecords(Map<String, Object> map);



    //**************************以下方法为开发者补充*********************************/
    ResultDatas<List<Map<String, Object>>> querySupplyRecords(Map<String,Object> map, SimplePageInfo pageInfo) throws ParseException;

    ResultDatas<List<Map<String,Object>>> queryBuySupplyGroupByCompany(Map<String,Object> map, SimplePageInfo pageInfo);
    ResultDatas<List<Map<String,Object>>> querySellSupplyGroupByCompany(Map<String,Object> map, SimplePageInfo pageInfo);

	List<Map<String,Object>> queryOrderListtByCompany(Map<String,Object> map);

	/**
	 * 买家导入
	 */
	String buyImportRSupply(List<RSupplyCombine> list, CurrentUser user);

	/**
	 * 卖家导入
	 */
	String sellerImportRSupply(List<RSupplyCombine> list, CurrentUser user);
	/**
	 * om导入
	 */
	String omImportRSupply(List<RSupplyCombine> list, CurrentUser user);

    int updateRecordsAliveFlag(Long orderId, String s);

	/**
	 * 导出下游订单查询结果
	 * @param queryRSupplyVO
	 * @return
	 */
	List<RSupply> queryByQueryRSupply(QueryRSupplyVO queryRSupplyVO);
}
