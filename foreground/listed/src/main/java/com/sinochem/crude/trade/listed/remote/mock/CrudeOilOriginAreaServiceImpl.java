package com.sinochem.crude.trade.listed.remote.mock;

import com.sinochem.crude.trade.goods.remote.CrudeOilOriginAreaResult;
import com.sinochem.crude.trade.goods.remote.CrudeOilOriginAreaService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yichen Zhao
 * date: 20180109
 */
@Service
public class CrudeOilOriginAreaServiceImpl implements CrudeOilOriginAreaService {

    @Override
    public List<CrudeOilOriginAreaResult> getAllCrudeOriginAreas() {
        return null;
    }
}
