package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TShipagentSofDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TShipagentSofDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TShipagentSofDetail record);

    int insertSelective(TShipagentSofDetail record);

    TShipagentSofDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TShipagentSofDetail record);

    int updateByPrimaryKey(TShipagentSofDetail record);

    /**
     * 根据sofId 查询详情信息
     * @param sofId
     * @return
     */
    public List<TShipagentSofDetail> selectBySof(Long sofId);

    /**
     * 批量插入
     * @param list
     * @return
     */
    public int bachInsert(List<TShipagentSofDetail> list);


    /***
     * 批量更新
     * @param detailList
     * @return
     */
    public int bachUpdate(@Param("detailList")List<TShipagentSofDetail> detailList);
}