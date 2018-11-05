package com.sinochem.crude.trade.broker.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinochem.crude.trade.blockchain.dao.TBrokerAppointMapper;
import com.sinochem.crude.trade.blockchain.domain.TBrokerAppoint;
import com.sinochem.crude.trade.broker.model.vo.TBrokerAppointQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/6 15:21
 * @Version: [v1.0]
 */
@Service
public class ForwarderServiceImpl implements ForwarderService {

    @Autowired
    private TBrokerAppointMapper tBrokerAppointMapper;

    @Override
    public List<TBrokerAppoint> selectList(TBrokerAppoint tBrokerAppoint) {
        return tBrokerAppointMapper.selectList(tBrokerAppoint);
    }

    @Override
    public Page<TBrokerAppoint> getTBrokerAppointPage(TBrokerAppointQueryVO tBrokerAppoint) {
//        SimplePageInfo simplePageInfo=new SimplePageInfo();
        PageHelper.startPage(tBrokerAppoint.getPageNum(), tBrokerAppoint.getPageSize());
        Page<TBrokerAppoint> contractSheetList = tBrokerAppointMapper.getTBrokerAppointPage(tBrokerAppoint);

        return contractSheetList;
    }


    public void insert(TBrokerAppoint tBrokerAppoint){

        tBrokerAppointMapper.insert(tBrokerAppoint);

    }

    public void updateByDealNo(TBrokerAppoint tBrokerAppoint){

        tBrokerAppointMapper.updateByDealNo(tBrokerAppoint);

    }

    @Override
    public TBrokerAppoint getTBrokerAppointByDealNo(String dealNo) {
        return tBrokerAppointMapper.selectByDealNo(dealNo);
    }

    @Override
    public TBrokerAppoint selectByPrimaryKey(Long id) {
        return tBrokerAppointMapper.selectByPrimaryKey(id);
    }
}
