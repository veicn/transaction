package com.sinochem.crude.trade.broker.controller;

//import com.sinochem.it.b2b.common.result.ResultDatas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinochem.crude.trade.blockchain.domain.*;
import com.sinochem.crude.trade.blockchain.model.BlockChainEntity;
import com.sinochem.crude.trade.blockchain.utils.BlockChainUtil;
import com.sinochem.crude.trade.blockchain.utils.TestUtil;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;
import com.sinochem.crude.trade.transaction.domain.po.OtherInfo;
import com.sinochem.crude.trade.transaction.service.ContractExtendService;
import com.sinochem.crude.trade.transaction.service.OtherInfoService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blockchain/broker/declaration")
public class BrokerController {

    @Autowired
    private ContractExtendService contractExtendService;
//    @Autowired
//    private BlockChainUtil blockChainUtil;
//    @Autowired
//    private TestUtil testUtil;

    @WithoutAccess
    @RequestMapping(value = "/testApi.json")
    @ResponseBody
    public String testApi() {
//        Gson gson= new GsonBuilder().serializeNulls().create();
//        TBrokerVO vo=new TBrokerVO(); t_contract_extend
//        vo.settBrokerDangerinfo(new TBrokerDangerinfo());
//        vo.settBrokerDeclaration(new TBrokerDeclaration());
//        vo.settBrokerDocuments(new TBrokerDocuments());
//        vo.settBrokerOthermatters(new TBrokerOthermatters());
//        vo.settBrokerOtherpackinfo(new TBrokerOtherpackinfo());

//        return gson.toJson(vo);
//        contractExtendService.deleteByPrimaryKey(7l);
//        int a=1/0;
//        String content="{'deal_no':'180123049438','trans_amt':'12800.12','trans_num':'188'}";
//        BlockChainEntity entity=blockChainUtil.insertToBlockChain1(content);

//        BlockChainEntity entity2=BlockChainUtil.findFromBlockChain(entity.getId());
//        String content2=entity2.getContent();
//        String id=entity2.getId();
//        System.out.print(content2);
//        String hh = testUtil.gethello();
        return null;
    }

}
