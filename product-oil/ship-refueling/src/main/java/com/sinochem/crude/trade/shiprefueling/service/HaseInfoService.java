package com.sinochem.crude.trade.shiprefueling.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.dao.HaseInfoMapper;
import com.sinochem.crude.trade.shiprefueling.domain.po.HaseInfo;
import com.sinochem.crude.trade.shiprefueling.model.query.HaseInfoQuery;
import com.sinochem.crude.trade.shiprefueling.model.vo.HaseInfoVO;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;
import java.util.Map;

public interface HaseInfoService {
	
	public abstract HaseInfoMapper getMapper();
	
	/**
	 * 新增
	 */
	int insertRecord(HaseInfo haseInfo);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long infoId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(HaseInfo  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(HaseInfo haseInfo) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(HaseInfo haseInfo) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(HaseInfo haseInfo);
	
	
	/**
	 * 根据主键-查询对象
	 */
	HaseInfo findByPrimaryKey(Long infoId);

	/**
	 * 根据uuid查询对象
	 */
	HaseInfo findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<HaseInfo> queryByEntitys(HaseInfo haseInfo);
		
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
	int insertHaseInfoRecord(HaseInfoVO haseInfo/*,CurrentUser user*/) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateHaseInfoRecordByUuid(HaseInfoVO haseInfo) throws BizException;
	
	/**
	 * 删除对象
	 */
	int deleteRecordByUuid(String uuid) throws BizException;
	/**
	 * 列表
	 * @param vo
	 * @param pageInfo
	 * @return
	 */
	public abstract Page<Map<String, Object>> queryHaseInfoList(HaseInfoVO haseInfo,SimplePageInfo pageInfo);

	/**
	 * 查询最新的size 条采购信息-武刚鹏-2018年5月28日17:43:19
	 * @param size
	 * @return
	 */
	List<HaseInfoVO> selectNewHashInfoList(int size);


	PageInfo<HaseInfoVO> queryFrontHaseInfoList(HaseInfoQuery haseInfoVO);


	HaseInfoVO findVoByUuid(String uuid);


	HaseInfoVO findMetaVoByUuid(String uuid);
}
