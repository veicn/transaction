package com.sinochem.crude.trade.listed.service;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.listed.model.vo.CrudeOilDemandQueryVO;
import com.sinochem.crude.trade.listed.model.vo.ReleaseCrudeOilVO;
import com.sinochem.it.b2b.common.page.PageInfoResult;

import java.util.List;

/**
 * 原油大厅发布需求
 */
public interface ReleaseCrudeOilFormService {
    /**
     * @deprecated
     * 原油大厅发布信息查询
     * @param pageInfo
     * @return
     */
    PageInfoResult<ReleaseCrudeOilVO> getReleaseCrudeOilFormList(PageInfo pageInfo, CrudeOilDemandQueryVO crudeOilDemandQueryVO);
}
