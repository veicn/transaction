package com.sinochem.crude.trade.member.dao;


import com.sinochem.crude.trade.member.domain.EnterpriseCrude;

public interface EnterpriseCrudeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EnterpriseCrude enterprise);

    int insertSelective(EnterpriseCrude record);

    EnterpriseCrude selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EnterpriseCrude record);

    int updateByPrimaryKey(EnterpriseCrude record);

	//企业信息详情查询
	EnterpriseCrude selectByMemberId(Long memberId);

    /**
     * 根据企业id查询企业信息详情
     * @param enterpriseId
     * @return
     */
    EnterpriseCrude selectByEnterpriseId(Long enterpriseId);

}