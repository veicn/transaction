package com.sinochem.crude.trade.transaction.dao;

import com.sinochem.crude.trade.transaction.domain.po.DemandSheet;

public interface DemandSheetHistoryMapper {

    /**
     * 新增对象
     */
    int insert(DemandSheet demandSheet);

}