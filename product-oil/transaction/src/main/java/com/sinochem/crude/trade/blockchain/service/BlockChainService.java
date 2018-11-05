package com.sinochem.crude.trade.blockchain.service;


import com.sinochem.crude.trade.blockchain.model.BlockChainEntity;


public interface BlockChainService {

      BlockChainEntity insertToBlockChain(String content);

      BlockChainEntity findFromBlockChain(String id);
      BlockChainEntity insertToBlockChainByUrl(String content,String url);
      BlockChainEntity findFromBlockChainByUrl(String id,String url);
}
