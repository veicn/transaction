package com.sinochem.crude.trade.goods.remote.impl;

import com.sinochem.crude.trade.goods.dao.TCrudeOriginAreaMapper;
import com.sinochem.crude.trade.goods.remote.CrudeOilOriginAreaResult;
import com.sinochem.crude.trade.goods.remote.CrudeOilOriginAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CrudeOilOriginAreaService的实现类，提供对外接口实现
 * @author Yichen Zhao
 * date: 20180109
 */
@Service
public class CrudeOilOriginAreaServiceImpl implements CrudeOilOriginAreaService {

    @Autowired
    private TCrudeOriginAreaMapper tCrudeOriginAreaMapper;

    @Override
    public List<CrudeOilOriginAreaResult> getAllCrudeOriginAreas() {
        return tCrudeOriginAreaMapper.getAllCrudeOriginAreas();
    }
}
