package com.sinochem.crude.trade.inspector.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.blockchain.dao.TBrokerAppointMapper;
import com.sinochem.crude.trade.blockchain.dao.TInspectorAppointMapper;
import com.sinochem.crude.trade.blockchain.domain.TBrokerAppoint;
import com.sinochem.crude.trade.blockchain.domain.TInspectorAppoint;
import com.sinochem.crude.trade.broker.model.vo.TBrokerAppointQueryVO;
import com.sinochem.crude.trade.broker.service.impl.ForwarderService;
import com.sinochem.crude.trade.inspector.service.TInspectorAppointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Authort: fengzk
 * @CreateDate: 2018/9/6 15:21
 * @Version: [v1.0]
 */
@Service
public class TInspectorAppointServiceImpl implements TInspectorAppointService {

    @Autowired
    private TInspectorAppointMapper tInspectorAppointMapper;


    public void insert(TInspectorAppoint InspectorAppoint){

        tInspectorAppointMapper.insert(InspectorAppoint);

    }

    public void updateByDealNo(TInspectorAppoint InspectorAppoint){

        tInspectorAppointMapper.updateByDealNo(InspectorAppoint);

    }

    @Override
    public TInspectorAppoint getTInspectorAppointByDealNo(String dealNo) {
        return tInspectorAppointMapper.selectByDealNo(dealNo);
    }
}
