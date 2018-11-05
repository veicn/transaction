package com.sinochem.crude.trade.shipping.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.shipping.dao.DemandsMapper;
import com.sinochem.crude.trade.shipping.domain.Demands;
import com.sinochem.crude.trade.shipping.model.query.DemandsQuery;
import com.sinochem.crude.trade.shipping.model.query.WechatDemandsQuery;
import com.sinochem.it.b2b.common.exception.BizException;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.it.b2b.common.page.PageInfoResult;

public interface DemandsService {
	
	public abstract DemandsMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(Demands demands);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long demandsId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(Demands  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(Demands demands) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(Demands demands) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Demands demands);
	
	
	/**
	 * 根据主键-查询对象
	 */
	Demands findByPrimaryKey(Long demandsId);

	/**
	 * 根据uuid查询对象
	 */
	Demands findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<Demands> queryByEntitys(Demands demands);
		
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
	 * 查询租船需求List
	 * @param demandsQuery
	 * @return
	 */
	List<Demands> queryByEntitysList(
			DemandsQuery demandsQuery);

	List<Demands> queryByEntitysQuanhuaList(DemandsQuery demandsQuery);

	/**
	 * 查询租船需求List-page
	 * @param demandsQuery
	 * @return
	 */
	PageInfoResult<Demands> queryByEntitysList(
			DemandsQuery demandsQuery, PageInfo pageInfo);

	/**
	 * 查询租船需求List-page(泉炼)
	 * @param demandsQuery
	 * @return
	 */
	PageInfoResult<Demands> queryByEntitysQuanhuaList(
			DemandsQuery demandsQuery, PageInfo pageInfo);
	/**
	 * 租船需求列表查询  微信端API
	 * @param wechatDemandsQuery
	 * @return
	 */
	List<Demands> getDemandsList(DemandsQuery wechatDemandsQuery);
	
	/**
	 * 校验是否租船
	 */
	int checkAgreementIsExsit(Long orderID);
}
