package com.sinochem.crude.trade.broker.service.impl;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.blockchain.domain.TBrokerAppoint;
import com.sinochem.crude.trade.broker.model.vo.TBrokerAppointQueryVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/6 14:58
 * @Version: [v1.0]
 */

public interface ForwarderService {

    List<TBrokerAppoint> selectList(TBrokerAppoint tBrokerAppoint);

    Page<TBrokerAppoint> getTBrokerAppointPage(TBrokerAppointQueryVO tBrokerAppoint);

    public void insert(TBrokerAppoint tBrokerAppoint);

    public void updateByDealNo(TBrokerAppoint tBrokerAppoint);

    public TBrokerAppoint getTBrokerAppointByDealNo(String dealNo);

    TBrokerAppoint selectByPrimaryKey(Long id);
}
