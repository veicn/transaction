package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.BusinessCardCollect;

import java.util.List;

public interface BusinessCardCollectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessCardCollect record);

    int insertSelective(BusinessCardCollect record);

    BusinessCardCollect selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessCardCollect record);

    int updateByPrimaryKey(BusinessCardCollect record);

    /**
     * 根据实体查询
     * @param entity
     * @return
     */
    List<BusinessCardCollect> selectByEntity(BusinessCardCollect entity);
}