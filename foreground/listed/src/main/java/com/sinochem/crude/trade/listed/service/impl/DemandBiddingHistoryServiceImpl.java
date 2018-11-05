package com.sinochem.crude.trade.listed.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinochem.crude.trade.listed.dao.DemandBiddingHistoryMapper;
import com.sinochem.crude.trade.listed.domain.DemandBiddingHistory;
import com.sinochem.crude.trade.listed.service.DemandBiddingHistoryService;

/**
 * Created by sijliu on 15/11/2017.
 */
@Service
public class DemandBiddingHistoryServiceImpl implements DemandBiddingHistoryService {

    @Autowired
    DemandBiddingHistoryMapper demandBiddingHistoryMapper;
	
	@Override
	public Long insert(DemandBiddingHistory record) {
		return demandBiddingHistoryMapper.insert(record);
	}

	@Override
	public List<DemandBiddingHistory> getDetailByDemandId(Long demandId) {
		return demandBiddingHistoryMapper.selectByDemandId(demandId);
	}
}
