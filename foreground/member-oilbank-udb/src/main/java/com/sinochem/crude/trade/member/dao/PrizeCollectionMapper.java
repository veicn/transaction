package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.PrizeCollection;

import java.util.List;

public interface PrizeCollectionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PrizeCollection record);

    int insertSelective(PrizeCollection record);

    PrizeCollection selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PrizeCollection record);

    int updateByPrimaryKey(PrizeCollection record);

    List<PrizeCollection> selectAll();

    List<PrizeCollection> selectByContactUser(String contactUser);
}