package com.sinochem.crude.trade.blockchain.dao;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.blockchain.domain.TBrokerAppoint;
import com.sinochem.crude.trade.broker.model.vo.TBrokerAppointQueryVO;

import java.util.List;

public interface TBrokerAppointMapper {

    int deleteByPrimaryKey(Long id);


    int insert(TBrokerAppoint record);

    int insertSelective(TBrokerAppoint record);


    TBrokerAppoint selectByPrimaryKey(Long id);

    List<TBrokerAppoint> selectList(TBrokerAppoint tBrokerAppoint);

    Page<TBrokerAppoint> getTBrokerAppointPage(TBrokerAppointQueryVO tBrokerAppoint);

    int updateByPrimaryKeySelective(TBrokerAppoint record);


    int updateByPrimaryKey(TBrokerAppoint record);

    int updateByDealNo(TBrokerAppoint record);

    TBrokerAppoint selectByDealNo(String dealNo);
}