package com.sinochem.crude.trade.order.dao;

import com.sinochem.crude.trade.order.domain.ContractRelevanter;

public interface ContractRelevanterDao {

	public void insert(ContractRelevanter contractRelevanter);

	public void update(Long id, ContractRelevanter contractRelevanter);

}
