package com.sinochem.crude.trade.inspector.service;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.blockchain.domain.TBrokerAppoint;
import com.sinochem.crude.trade.blockchain.domain.TInspectorAppoint;
import com.sinochem.crude.trade.broker.model.vo.TBrokerAppointQueryVO;

import java.util.List;

public interface TInspectorAppointService {

    public void insert(TInspectorAppoint  tInspectorAppoint);

    public void updateByDealNo(TInspectorAppoint  tInspectorAppoint);

    public TInspectorAppoint getTInspectorAppointByDealNo(String dealNo);
}
