package com.sinochem.crude.trade.member.service;

import com.sinochem.crude.trade.member.domain.EnterpriseCrude;
import com.sinochem.it.b2b.common.exception.BizException;


public interface EnterpriseCrudeService {

	/**
	 * 添加企业信息
	 */
	void add(EnterpriseCrude enterprise) throws BizException;
	
	/**
	 * 更新企业信息
	 */
	void update(EnterpriseCrude enterprise) throws BizException;

	void deleteEnterprise(Long id)throws BizException;

	EnterpriseCrude selectByMemberId(Long memberId);

	/**
	 * 根据企业id查询企业信息详情
	 * @param enterpriseId
	 * @return
	 */
	EnterpriseCrude selectByEnterpriseId(Long enterpriseId);

    void saveOrUpdate(EnterpriseCrude enterpriseCrude) throws BizException;
}
