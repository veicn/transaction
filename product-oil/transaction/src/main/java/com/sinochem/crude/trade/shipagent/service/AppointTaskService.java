package com.sinochem.crude.trade.shipagent.service;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.blockchain.domain.TBrokerAppoint;
import com.sinochem.crude.trade.blockchain.domain.TCommonAttachments;
import com.sinochem.crude.trade.blockchain.domain.TShipagentAppoint;
import com.sinochem.crude.trade.broker.model.vo.TBrokerAppointQueryVO;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;
import java.util.Map;

/**
 * Created by admin1 on 2018/9/14.
 */
public interface AppointTaskService {


    public Page<TShipagentAppoint> getTBrokerAppointPage(TBrokerAppointQueryVO tBrokerAppoint);

    public TShipagentAppoint selectByPrimaryKey(Long id);

    public void insert(TShipagentAppoint tShipagentAppoint);

    public void updateByDealNo(TShipagentAppoint tShipagentAppoint);

    public TShipagentAppoint getTShipagentAppointByDealNo(String dealNo);

    public List<TShipagentAppoint> selectByCondition(TShipagentAppoint queryAppoint);

	public void updateStatusIfFileNotExist(TCommonAttachments attachments)throws BizException;
}
