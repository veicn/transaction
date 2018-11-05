package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.VersionBean;

import java.util.List;

public interface VersionBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VersionBean record);

    int insertSelective(VersionBean record);

    VersionBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VersionBean record);

    int updateByPrimaryKey(VersionBean record);

    List<VersionBean> selectAll();

    List<VersionBean> selectByVersionLanguage(String versionLanguage);
}