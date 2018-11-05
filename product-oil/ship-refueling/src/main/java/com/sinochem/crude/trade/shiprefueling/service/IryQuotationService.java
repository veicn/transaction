package com.sinochem.crude.trade.shiprefueling.service;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.dao.IryQuotationMapper;
import com.sinochem.crude.trade.shiprefueling.domain.po.IryQuotation;
import com.sinochem.crude.trade.shiprefueling.model.vo.IryQuotationVO;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;
import java.util.Map;


public interface IryQuotationService {
	
	public abstract IryQuotationMapper getMapper();
	
	/**
	 * 新增
	 */
	int insertRecord(IryQuotation iryQuotation);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long inquiryQuotationId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(IryQuotation  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(IryQuotation iryQuotation) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(IryQuotation iryQuotation) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(IryQuotation iryQuotation);
	
	
	/**
	 * 根据主键-查询对象
	 */
	IryQuotation findByPrimaryKey(Long inquiryQuotationId);

	/**
	 * 根据uuid查询对象
	 */
	IryQuotation findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<IryQuotation> queryByEntitys(IryQuotation iryQuotation);
		
	/**
	 * 根据条件-查询全部
	 */
	List<Map<String, Object>> queryRecords(Map<String, Object> map);
	

	/**
	 * 根据条件-查询记录条数
	 */
	int countRecords(Map<String, Object> map); 
	
	

	//**************************以下方法为开发者补充*********************************/
	
	/**
	 * 新增
	 */
	int insertIryQuotationRecord(IryQuotationVO vo) throws BizException;
	
	/**
	 * 列表
	 * @param vo
	 * @param pageInfo
	 * @return
	 */
	public abstract Page<Map<String, Object>> queryIryQuotationList(Map<String, Object> map,SimplePageInfo pageInfo);
	/**
	 * 列表
	 * @param vo
	 * @param pageInfo
	 * @return
	 */
	public abstract Page<Map<String, Object>> queryListByNeedUuid(Map<String, Object> map,SimplePageInfo pageInfo);

    public void updateRecordByNeedUUid(String uuid ,  String type );
}
