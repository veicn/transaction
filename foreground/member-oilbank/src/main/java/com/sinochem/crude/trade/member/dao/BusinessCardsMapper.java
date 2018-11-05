package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.BusinessCards;
import com.sinochem.crude.trade.member.domain.query.BusinessCardQuery;

import java.util.List;

public interface BusinessCardsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessCards record);

    int insertSelective(BusinessCards record);

    BusinessCards selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessCards record);

    int updateByPrimaryKey(BusinessCards record);

    /**
     * 根据名片查询实体
     * @param entity
     * @return
     */
    List<BusinessCards> selectByEntity(BusinessCards entity);

    /**
     * 名片搜索
     * @param queryParam
     * @param queryParam.memberId ，传入该参数，只会搜索改会员id收藏（保存）的名片
     * @return
     */
    List<BusinessCards> getBusinessCardByQuery(BusinessCardQuery queryParam);
}