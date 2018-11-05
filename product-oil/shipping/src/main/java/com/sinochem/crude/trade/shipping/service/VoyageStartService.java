package com.sinochem.crude.trade.shipping.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.dao.VoyageStartMapper;
import com.sinochem.crude.trade.shipping.domain.VoyageStart;
import com.sinochem.crude.trade.shipping.model.query.VoyageStartQuery;
import com.sinochem.crude.trade.shipping.model.vo.VoyageStartVO;
import com.sinochem.it.b2b.common.exception.BizException;

public interface VoyageStartService {

	/**
	 * 新增
	 */
	int insertRecord(VoyageStart voyageStart);

	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long voyageStartId) throws BizException;

	/**
	 * 根据条件-物理删除对象执行delete语句, 慎用！！！
	 */
	public int deleteRecords(VoyageStart record);

	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(VoyageStart voyageStart) throws BizException;

	/**
	 * 根据uuid-修改对象
	 */
	Integer updateRecordByUuid(VoyageStart voyageStart,CurrentUser currentUser) throws BizException;

	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);

	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(VoyageStart voyageStart);

	/**
	 * 根据主键-查询对象
	 */
	VoyageStart findByPrimaryKey(Long voyageStartId);

	/**
	 * 根据uuid查询对象
	 */
	VoyageStart findByUuid(String uuid);

	/**
	 * 根据对象-查询对象列表
	 */
	List<VoyageStart> queryByEntitys(VoyageStart voyageStart);

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

	// **************************以下方法为开发者补充*********************************/

	/**
	 * 查询数据集合
	 * 
	 * @param voyageStartQuery
	 * @return
	 */
	List<VoyageStartVO> queryByEntitysList(VoyageStartQuery voyageStartQuery);

	Integer VoyageLogicDelete(VoyageStartQuery voyageStartQuery);


	//region 添加一条航行计划 -武刚鹏-2018年3月26日10:27:27

	/**
	 * 添加一条航行计划 -武刚鹏-2018年3月26日10:27:27
	 * @param voyageStart
	 * @param currentUser
	 * @return Integer
	 * @throws BizException
	 */
	Integer insertRecordByCurrentUser(VoyageStart voyageStart, CurrentUser currentUser)throws BizException;
	//endregion

	//region 根据确认单ID查询航行开始信息 -武刚鹏-2018年3月26日10:28:22
	/**
	 * 根据确认单ID查询航行开始信息
	 * @param confirmationSheetId
	 * @return VoyageStart 航行实体
	 */
	 VoyageStart findByShipConfirmationSheetId(Long confirmationSheetId);
	//endregion



}
