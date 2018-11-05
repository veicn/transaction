package com.sinochem.crude.trade.transaction.dao;

import com.sinochem.crude.trade.transaction.domain.po.OtherInfo;

public interface OtherInfoMapper {

    /**
     * 新增对象
     */
    int insert(OtherInfo otherInfo);

    /**
     * 根据主键物理删除, 慎用
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据主键修改对象
     */
    int updateByPrimaryKey(OtherInfo otherInfo);

    /**
     * 根据主键修改对象，更新有值的字段
     */
    int updateByPrimaryKeySelective(OtherInfo otherInfo);

    /**
     * 根据主键查询对象
     */
    OtherInfo selectByPrimaryKey(Long id);

    /**
     * 根据uuid查询对象
     */
    OtherInfo selectByUuid(String uuid);

    //**************************以下方法由开发者补充*********************************/
}