package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TBrokerAppoint;
import com.sinochem.crude.trade.blockchain.domain.TInspectorAppoint;

import java.util.List;
import java.util.Map;

public interface TInspectorAppointMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TInspectorAppoint record);

    int insertSelective(TInspectorAppoint record);

    TInspectorAppoint selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TInspectorAppoint record);

    int updateByPrimaryKey(TInspectorAppoint record);

    //**************************以下方法为开发者补充*********************************/

    public List<Map<String,Object>> queryTaskInfoList(Map<String,Object> map);

    int updateByDealNo(TInspectorAppoint record);

    TInspectorAppoint selectByDealNo(String dealNo);
}