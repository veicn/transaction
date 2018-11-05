package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TInspectorPresentation;

import java.util.List;
import java.util.Map;

public interface TInspectorPresentationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TInspectorPresentation record);

    int insertSelective(TInspectorPresentation record);

    TInspectorPresentation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TInspectorPresentation record);

    int updateByPrimaryKey(TInspectorPresentation record);

    //**************************以下方法为开发者补充*********************************/

    public List<Map<String,Object>> queryReportInfoList(Map<String,Object> map);

    public Map<String,Object> queryTransactionInfoInfoList(String uuid);

    List<String> selectBillNo(Map<String,Object> map);
}