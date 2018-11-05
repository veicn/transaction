package com.sinochem.crude.trade.info.service;

import java.util.List;
import java.util.Map;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.info.domain.ChannelM;
import com.sinochem.crude.trade.info.domain.ChannelSub;
import com.sinochem.crude.trade.info.domain.Info;

/**
 * @ClassName: AuditService
 * @Description: 资讯审核
 * @author pengfl
 * @date 2017年11月13日 下午7:19:31
 *
 */
public interface AuditService {

	/**
	 * 待审核/已发布列表查询
	 * 
	 * @param query
	 * @return
	 */
	public List<Map<String, Object>> listInfo(Map<String, String> map,SimplePageInfo pageInfo);

	/**
	 * 审核通过
	 * 
	 * @param uuid
	 * @return
	 */
	public boolean auditYes(String uuid) throws BizException;

	/**
	 * 审核驳回
	 * 
	 * @param info
	 * @return
	 */
	public boolean auditNo(Info info) throws BizException;

	/**
	 * 资讯撤销
	 * 
	 * @param uuid
	 * @return
	 */
	public boolean revoke(String uuid) throws BizException;

	/**
	 * 批量审核
	 * 
	 * @return
	 */
	public Map<String, Object> batchAudit(String[] uuids) throws BizException;

	/**
	 * 根据主频道uuid查询主频道信息
	 * 
	 * @param cMUuid
	 * @return
	 */
	public ChannelM CMByUuid(String cMUuid);

	/**
	 * 根据子频道uuid查询子频道信息
	 * 
	 * @param cSubUuid
	 * @return
	 */
	public ChannelSub CSubByUuid(String cSubUuid);
}
