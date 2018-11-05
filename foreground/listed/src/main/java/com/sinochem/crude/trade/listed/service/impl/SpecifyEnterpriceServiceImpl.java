package com.sinochem.crude.trade.listed.service.impl;

import com.sinochem.crude.trade.listed.dao.DemandSpecifyEnterpriseMapper;
import com.sinochem.crude.trade.listed.domain.DemandSpecifyEnterprise;
import com.sinochem.crude.trade.listed.service.SpecifyEnterpriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sijliu on 18/01/2018.
 */
@Service
@Transactional
public class SpecifyEnterpriceServiceImpl implements SpecifyEnterpriceService {

    @Autowired
    DemandSpecifyEnterpriseMapper specifyEnterpriseMapper;

    @Override
    public List<DemandSpecifyEnterprise> getDemandSpecifyEnterpriseListByDemandId(Long demandId) {
        return specifyEnterpriseMapper.selectObjByDemandId(demandId);
    }
}
