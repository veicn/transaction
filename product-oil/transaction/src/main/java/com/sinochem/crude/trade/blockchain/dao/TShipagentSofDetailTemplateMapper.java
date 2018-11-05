package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TShipagentSofDetailTemplate;

import java.util.List;

public interface TShipagentSofDetailTemplateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TShipagentSofDetailTemplate record);

    int insertSelective(TShipagentSofDetailTemplate record);

    TShipagentSofDetailTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TShipagentSofDetailTemplate record);

    int updateByPrimaryKey(TShipagentSofDetailTemplate record);

    List<TShipagentSofDetailTemplate> selectByParamter(TShipagentSofDetailTemplate template);
}