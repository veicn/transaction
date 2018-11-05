package com.sinochem.crude.trade.goods.remote;

import java.util.List;

/**
 * 提供原油产地区域（注意不是产地）相关服务
 * @author Yichen Zhao
 * date: 20180109
 */
public interface CrudeOilOriginAreaService {
    public List<CrudeOilOriginAreaResult> getAllCrudeOriginAreas();
}

