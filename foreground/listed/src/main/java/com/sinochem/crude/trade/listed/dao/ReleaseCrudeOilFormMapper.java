package com.sinochem.crude.trade.listed.dao;

import com.sinochem.crude.trade.listed.model.query.CrudeOilDemandQuery;
import com.sinochem.crude.trade.listed.model.vo.ReleaseCrudeOilVO;

import java.util.List;

@Deprecated
public interface ReleaseCrudeOilFormMapper {
    /**
     * @param dataQuery
     * @return
     */
    List<ReleaseCrudeOilVO> getReleaseCrudeOilFormList(CrudeOilDemandQuery dataQuery);
}
