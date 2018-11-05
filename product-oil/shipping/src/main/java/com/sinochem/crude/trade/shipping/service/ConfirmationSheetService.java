package com.sinochem.crude.trade.shipping.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.dao.ConfirmationSheetMapper;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.model.query.ConfirmationSheetQuery;
import com.sinochem.crude.trade.shipping.model.vo.ConfirmationSheetVO;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;

public interface ConfirmationSheetService {

	public abstract ConfirmationSheetMapper getMapper();

	/**
	 * 新增
	 */
	int insertRecord(ConfirmationSheet confirmationSheet);

	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long confirmationSheetId) throws BizException;

	/**
	 * 根据条件-物理删除对象执行delete语句, 慎用！！！
	 */
	public int deleteRecords(ConfirmationSheet record);

	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(ConfirmationSheet confirmationSheet)
			throws BizException;

	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(ConfirmationSheet confirmationSheet)
			throws BizException;

	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);

	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(ConfirmationSheet confirmationSheet);

	/**
	 * 根据主键-查询对象
	 */
	ConfirmationSheet findByPrimaryKey(Long confirmationSheetId);

	/**
	 * 根据uuid查询对象
	 */
	ConfirmationSheet findByUuid(String uuid);

	/**
	 * 根据对象-查询对象列表
	 */
	List<ConfirmationSheet> queryByEntitys(ConfirmationSheet confirmationSheet);

	/**
	 * 根据条件-查询全部
	 */
	List<Map<String, Object>> queryRecords(Map<String, Object> map);

	/**
	 * 根据条件-分页查询
	 */
	Page<Map<String, Object>> queryRecords(Map<String, Object> map,
			SimplePageInfo pageInfo);

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
	List<ConfirmationSheetVO> queryByEntitysList(
			ConfirmationSheetQuery confirmationSheetQuery);

	/**
	 * 逻辑删除
	 * 
	 * @param confirmationSheet
	 * @return
	 */
	Integer deleteLogicConfirmationSheet(ConfirmationSheet confirmationSheet);

	/**
	 * 新增船舶确认单
	 */
	int insertRecordByCurrentUser(ConfirmationSheet confirmationSheet,
			CurrentUser currentUser);

	/**
	 * 获取船舶确认单分页列表
	 * 
	 * @param confiQuery
	 * @param pageInfo
	 * @return
	 * @throws BizException
	 */
	public PageInfoResult getConfirmationSheetList(
			ConfirmationSheetQuery confiQuery, PageInfo pageInfo)
			throws BizException;

	/**
	 * 获取船舶确认单分页列表(泉炼)
	 *
	 * @param confiQuery
	 * @param pageInfo
	 * @return
	 * @throws BizException
	 */
	public PageInfoResult getConfirmationQuanhuaSheetList(
			ConfirmationSheetQuery confiQuery, PageInfo pageInfo) throws BizException;

	/**
	 * 获取船舶确认单分页列表(泉炼) 微信
	 *
	 * @param confiQuery
	 * @return
	 * @throws BizException
	 */
	public List<ConfirmationSheet> getConfirmationQuanhuaSheetListWX(
			ConfirmationSheetQuery confiQuery) throws BizException;
	/**
	 * 根据关键字查询数据
	 */
	List<ConfirmationSheet> findByKeywrokds(ConfirmationSheetQuery query);

	/**
	 * 校验是否租船
	 */
	int checkConfirmationIsExsit(Long orderID);

	/**
	 * 更新确认单的状态为航次开始 -武刚鹏-2018年3月20日10:12:24
	 * 
	 * @param confirmationSheet
	 * @param currentUser
	 * @return
	 */
	Integer updateStatusByconfirmationId(ConfirmationSheet confirmationSheet, CurrentUser currentUser)throws BizException;

	 Integer updateStatusById(
			ConfirmationSheet confirmationSheet, CurrentUser currentUser)throws BizException;

	/**
	 * 确认，协议，需求三张表联查 -武刚鹏-2018年3月24日19:34:24
	 * @param vo
	 * @return
	 */
	ConfirmationSheet findByDemendsAndAgreement(ConfirmationSheetVO vo)throws BizException;

	/**
	 * 根据协议UUid查询确认单
	 * Wh
	 */
	ConfirmationSheet findByAgreementId(Long id);

	/**
	 * 卖家确认 修改确认单 需求单，协议单
	 * @param confirmationSheet
	 * @param currentUser
	 * @return
	 */
	Integer updateStatus(ConfirmationSheet confirmationSheet,
						 CurrentUser currentUser) throws BizException;

}
