package com.sinochem.crude.trade.shipping.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.dao.LoadPortMapper;
import com.sinochem.crude.trade.shipping.domain.LoadPort;
import com.sinochem.crude.trade.shipping.domain.LoadingProgress;
import com.sinochem.crude.trade.shipping.model.vo.LoadPortVO;
import com.sinochem.crude.trade.shipping.model.vo.LoadingProgressVO;
import com.sinochem.it.b2b.common.exception.BizException;
//import com.runyi.ryplat.api.commons.SimplePageInfo;

public interface LoadPortService {
	
	public abstract LoadPortMapper getMapper();

	/**
	 * 根据确认单ID查询数据
	 * @param map
	 * @return
	 */
	 LoadPort queryConfirmationSheetId(String csheetid);


	/**
	 * 新增
	 */
	int insertRecord(LoadPort loadPort);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long shipLoadPortId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(LoadPort  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(LoadPort loadPort) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(LoadPort loadPort) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(LoadPort loadPort);
	
	
	/**
	 * 根据主键-查询对象
	 */
	LoadPort findByPrimaryKey(Long shipLoadPortId);

	/**
	 * 根据uuid查询对象
	 */
	LoadPort findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<LoadPort> queryByEntitys(LoadPort loadPort);
		
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
	Integer firstStepSave(LoadPort domain, CurrentUser currentUser, List<LoadingProgress> progreeList) throws BizException;

	/**
	 * 查询装港信息
	 * @param confirmationSheetId
	 * @return
	 */
	 LoadPort findByShipConfirmationSheetId(Long confirmationSheetId);

	 /**
	  * 根据uuid 查询
	  * @param comUuid
	  * @return
	  */
	LoadPort confirmationfindbyuuid(String comUuid);



}
