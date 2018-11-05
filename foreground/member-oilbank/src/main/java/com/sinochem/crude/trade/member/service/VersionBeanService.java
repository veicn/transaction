package com.sinochem.crude.trade.member.service;

import com.sinochem.crude.trade.member.domain.VersionBean;
import com.sinochem.crude.trade.member.model.VersionBeanVO;

import java.util.List;


/**
 * @made by WangTing
 * 用以实现app版本控制的service
 *
 */

public interface VersionBeanService {

    /**
     * 根据主键id查询某个版本
     */
    public VersionBeanVO selectByPrimaryKey(Long id);

    /**
     * 添加版本
     */
    public void add(VersionBean versionBean) ;

    /**
     * 修改版本信息
     */
    public void update(VersionBean versionBean);

    /**
     * 根据主键id删除某个版本
     */
    public void delete(Long id);

    /**
     * 查询所有版本信息列表
     */
    public List<VersionBeanVO> selectAll();


    public List<VersionBeanVO> selectByVersionLanguage(String versionLanguage);
}
