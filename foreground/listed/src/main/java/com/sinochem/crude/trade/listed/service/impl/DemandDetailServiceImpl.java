package com.sinochem.crude.trade.listed.service.impl;

import com.sinochem.crude.trade.listed.dao.DemandDetailMapper;
import com.sinochem.crude.trade.listed.domain.DemandDetail;
import com.sinochem.crude.trade.listed.service.DemandDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sijliu on 15/11/2017.
 */
@Service
public class DemandDetailServiceImpl implements DemandDetailService {

    @Autowired(required = false)
    DemandDetailMapper demandDetailMapper;

    @Override
    public Long saveOrUpdateDetails(DemandDetail record) {
        // 需求id为空的情况下
        if (record.getId() == null) {
            demandDetailMapper.insertSelective(record);
        } else {
            demandDetailMapper.updateByPrimaryKeySelective(record);
        }

        return record.getId();
    }

    @Override
    public List<DemandDetail> getDetailByDemandId(Long demandId) {
        return demandDetailMapper.selectByDemandId(demandId);
    }

    @Override
    public void deleteDemandDetailByDemandId(Long demandId) {
        demandDetailMapper.deleteByDemandId(demandId);
    }
}
