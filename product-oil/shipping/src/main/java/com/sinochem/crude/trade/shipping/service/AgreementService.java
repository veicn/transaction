package com.sinochem.crude.trade.shipping.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.dao.AgreementMapper;
import com.sinochem.crude.trade.shipping.domain.Agreement;
import com.sinochem.crude.trade.shipping.domain.Demands;
import com.sinochem.crude.trade.shipping.model.query.AgreementQuery;
import com.sinochem.crude.trade.shipping.model.query.WechatAgreementQuery;
import com.sinochem.crude.trade.shipping.model.vo.AgreementVO;
import com.sinochem.it.b2b.common.exception.BizException;
//import com.runyi.ryplat.api.commons.SimplePageInfo;
import com.sinochem.it.b2b.common.page.PageInfoResult;

public interface AgreementService {
	
	public abstract AgreementMapper getMapper(); 
	
	/**
	 * 新增
	 */
	int insertRecord(Agreement agreement);
	
	/**
	 * 根据主键物理删除, 慎用！！！
	 */
	public int deleteById(Long agreementId) throws BizException;
	
	 /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    public int deleteRecords(Agreement  record);
	
	/**
	 * 根据主键-修改对象
	 */
	int updateRecordById(Agreement agreement) throws BizException;
	
	/**
	 * 根据uuid-修改对象
	 */
	int updateRecordByUuid(Agreement agreement) throws BizException;
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Map<String, Object> map);
	
	/**
	 * 根据条件-批量修改数据(危险慎用)
	 */
	int updateRecords(Agreement agreement);
	
	
	/**
	 * 根据主键-查询对象
	 */
	Agreement findByPrimaryKey(Long agreementId);

	/**
	 * 根据uuid查询对象
	 */
	Agreement findByUuid(String uuid);
 
	
	/**
	 * 根据对象-查询对象列表
	 */
	List<Agreement> queryByEntitys(Agreement agreement);
		
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
	 * 修改代理协议
	 * 
	 * @param vo
	 * @param user
	 * @return 
	 */
	public abstract Integer updateAgreement(AgreementVO vo, CurrentUser user);

	public abstract Integer saveAgreement(AgreementVO vo, CurrentUser user);

	public abstract Integer confirmAgreement(AgreementVO vo, CurrentUser user); 
	
	/**
	 * 微信API 获取协议列表
	 */
	public abstract List<Agreement> getAgreementList(WechatAgreementQuery query);

	/**
	 * 微信API 获取协议列表（贸易商）
	 */
	public abstract List<Agreement> getAgreementListtrader(WechatAgreementQuery query);

	public abstract PageInfoResult queryByEntitysList(AgreementQuery query, PageInfo pageInfo, CurrentUser user);

	public abstract PageInfoResult queryByDemandsEntitysList(AgreementQuery query, PageInfo pageInfo, CurrentUser user);

	public abstract PageInfoResult queryDemandsQuanlianByEntitysList(AgreementQuery query, PageInfo pageInfo, CurrentUser user);

	public abstract List<Agreement> queryDemandsQuanlianByEntitysListWX(AgreementQuery query);
	/**
	 * 根据协议ID更新状态为航次开始 -武刚鹏-2018年3月20日09:51:15
	 * @param agreement
	 * @return
	 */
	Integer updateStatusByAgreementId(Agreement agreement,CurrentUser user)throws BizException;

	public abstract int deleteAgreement(Agreement agreement, CurrentUser user);

}
