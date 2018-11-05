package com.sinochem.crude.trade.member.service.impl;

import com.sinochem.crude.trade.member.dao.VersionBeanMapper;
import com.sinochem.crude.trade.member.domain.VersionBean;
import com.sinochem.crude.trade.member.model.VersionBeanVO;
import com.sinochem.crude.trade.member.service.VersionBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Made By WangTing
 * 本类是用来实现app版本控制工具的service的实现类
 * 已经实现的功能有:
 *          查询所有版本信息
 *          添加版本信息
 *          修改版本信息
 *          根据id删除版本信息
 */

@Service
public class VersionBeanServiceImpl implements VersionBeanService{

    @Autowired
    private VersionBeanMapper versionBeanMapper;


    /**
     * 查询所有版本信息
     * @return
     */
    @Override
    public List<VersionBeanVO> selectAll() {
        List<VersionBeanVO>  list = new ArrayList<>();
        List<VersionBean> versionBeans = versionBeanMapper.selectAll();
        for (VersionBean versionBean:versionBeans) {
            VersionBeanVO versionBeanVO = new VersionBeanVO(versionBean);
            list.add(versionBeanVO);
        }
        return list;

    }

    @Override
    public List<VersionBeanVO> selectByVersionLanguage(String versionLanguage) {
        List<VersionBeanVO>  list = new ArrayList<>();
        List<VersionBean> versionBeans = versionBeanMapper.selectByVersionLanguage(versionLanguage);
        for (VersionBean versionBean:versionBeans) {
            VersionBeanVO versionBeanVO = new VersionBeanVO(versionBean);
            list.add(versionBeanVO);
        }
        return list;
    }

    @Override
    public VersionBeanVO selectByPrimaryKey(Long id) {
        VersionBean versionBean = versionBeanMapper.selectByPrimaryKey(id);
        VersionBeanVO versionBeanVO = new VersionBeanVO(versionBean);
        return versionBeanVO;
    }

    /**
     * 添加版本信息
     * @param versionBean
     */
    @Override
    public void add(VersionBean versionBean) {
        versionBeanMapper.insertSelective(versionBean);

    }

    /**
     * 修改版本信息
     * @param versionBean
     */
    @Override
    public void update(VersionBean versionBean) {

        versionBeanMapper.updateByPrimaryKeySelective(versionBean);

    }

    /**
     * 通过id删除版本信息
     * @param id
     */
    @Override
    public void delete(Long id) {

        versionBeanMapper.deleteByPrimaryKey(id);

    }


}
