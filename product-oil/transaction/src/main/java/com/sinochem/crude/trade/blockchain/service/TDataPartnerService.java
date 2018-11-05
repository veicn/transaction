package com.sinochem.crude.trade.blockchain.service;


import com.sinochem.crude.trade.blockchain.domain.TDataPartner;
import com.sinochem.crude.trade.blockchain.model.BlockChainEntity;
import java.util.*;


public interface TDataPartnerService {

    List<TDataPartner> findAllList();
    List<TDataPartner> findListByRole(String role);

    TDataPartner findByQuery(TDataPartner tDataPartner);
}
