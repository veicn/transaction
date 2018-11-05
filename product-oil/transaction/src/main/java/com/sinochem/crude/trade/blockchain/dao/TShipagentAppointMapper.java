package com.sinochem.crude.trade.blockchain.dao;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.blockchain.domain.TBrokerAppoint;
import com.sinochem.crude.trade.blockchain.domain.TShipagentAppoint;
import com.sinochem.crude.trade.broker.model.vo.TBrokerAppointQueryVO;

import java.util.List;

public interface TShipagentAppointMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TShipagentAppoint record);

    int insertSelective(TShipagentAppoint record);

    TShipagentAppoint selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TShipagentAppoint record);

    int updateByPrimaryKey(TShipagentAppoint record);


    /**
     * 任务列表查询
     * @return
     */
    Page<TShipagentAppoint> getTBrokerAppointPage(TBrokerAppointQueryVO tBrokerAppoint);

    int updateByDealNo(TShipagentAppoint record);

    TShipagentAppoint selectByDealNo(String dealNo);

    List<TShipagentAppoint> selectByCondition(TShipagentAppoint queryAppoint);

}