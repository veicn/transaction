package com.sinochem.crude.trade.listed.service.impl;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.listed.constant.MsgConstant;
import com.sinochem.crude.trade.listed.dao.DemandShipMapper;
import com.sinochem.crude.trade.listed.domain.DemandShip;
import com.sinochem.crude.trade.listed.service.DemandShipService;

import com.sinochem.it.b2b.common.exception.BizException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sijliu on 15/11/2017.
 */
@Service
public class DemandShipServiceImpl implements DemandShipService {

    @Autowired
    DemandShipMapper demandShipMapper;

    @Override
    public Long saveOrUpdateDemandShip(DemandShip record) {
        if (record.getId() == null) {
            demandShipMapper.insertSelective(record);
        } else {
            demandShipMapper.updateByPrimaryKeySelective(record);
        }
        return record.getId();
    }

    @Override
    public List<DemandShip> getShipByDemandId(Long demandId) throws BizException {

        List<DemandShip> demandShips =  demandShipMapper.selectByDemandId(demandId);
        return demandShips;
    }

    @Override
    public void deleteByDemandId(Long id) {
        this.demandShipMapper.deleteByDemandId(id);
    }
    
    @Override
    public void updateBiddingDemandShip(DemandShip record) {
        demandShipMapper.updateBiddingDemandShip(record);
    }
}
