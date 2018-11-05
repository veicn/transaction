package com.sinochem.crude.trade.blockchain.service;

import com.sinochem.crude.trade.blockchain.domain.TTransExtend;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;

public interface TTransExtendService {



    int saveOrUpdateTTransExtend(TTransExtend tTransExtend, CurrentUser currentUser) throws BizException;

    int saveBlockChain(TTransExtend tTransExtend,CurrentUser currentUser) throws BizException;

    TTransExtend getTTransExtendByDealNo(CurrentUser currentUser, String dealNo) throws BizException;

    TTransExtend selectByPurchaseContractNo(String purchaseContractNo);

    List<TTransExtend> selectAllList();
}
