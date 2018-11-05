package com.sinochem.crude.trade.blockchain.service.impl;

import com.google.gson.Gson;
import com.sinochem.crude.trade.blockchain.dao.TTransExtendMapper;
import com.sinochem.crude.trade.blockchain.domain.TBlockchainEventRecord;
import com.sinochem.crude.trade.blockchain.domain.TDataPartner;
import com.sinochem.crude.trade.blockchain.domain.TTransExtend;

import com.sinochem.crude.trade.blockchain.enums.BlockchainEventEnums;
import com.sinochem.crude.trade.blockchain.model.BlockChainEntity;
import com.sinochem.crude.trade.blockchain.service.BlockChainService;
import com.sinochem.crude.trade.blockchain.service.TBlockchainEventRecordService;

import com.sinochem.crude.trade.blockchain.utils.BlockChainUtil;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.domain.po.ContractSheet;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.service.ContractSheetService;
import com.sinochem.crude.trade.blockchain.service.TTransExtendService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TTransExtendServiceImpl implements TTransExtendService {
    @Autowired
    TTransExtendMapper tTransExtendMapper;

    @Autowired
    EnterpriseService enterpriseService;

    @Autowired
    BlockChainService blockChainService;

    @Autowired
    ContractSheetService contractSheetService;

    @Autowired
    private TBlockchainEventRecordService tBlockchainEventRecordService;

    @Value("${blockchain_gateway}")
    private String url;

    @Transactional
    @Override
    public int saveOrUpdateTTransExtend(TTransExtend tTransExtend, CurrentUser currentUser) throws BizException {

        ContractSheet contractSheet = contractSheetService.selectBySerialNumber(currentUser,tTransExtend.getDealNo());

        ModelAndView mav = new ModelAndView();
        int res;

        if(null!=tTransExtend.getId()){ //更新
            this.saveBlockChain(tTransExtend,currentUser);//上链

            tTransExtend.setUpdateDate(new Date());
            tTransExtend.setUpdateUser(currentUser.getEpMemberId());
            res = tTransExtendMapper.updateBySelective(tTransExtend);


        }else{ //创建


            tTransExtend.setDealNo(contractSheet.getSerialNumber());
            tTransExtend.setBuyerEnterpriseId(contractSheet.getBuyerId());
            EnterpriseVo buyerVo = enterpriseService.getByMemberId(contractSheet.getBuyerId());
            tTransExtend.setBuyerEnterpriseName(buyerVo.getFullName());
            tTransExtend.setSellerEnterpriseId(contractSheet.getSalerId());
            EnterpriseVo sellerVo = enterpriseService.getByMemberId(contractSheet.getSalerId());
            tTransExtend.setSellerEnterpriseName(sellerVo.getFullName());
            tTransExtend.setAliveFlag("1");
            tTransExtend.setUpdateDate(new Date());
            tTransExtend.setUpdateUser(currentUser.getEpMemberId());
            tTransExtend.setCreateDate(new Date());
            tTransExtend.setCreateUser(currentUser.getEpMemberId());
            res = tTransExtendMapper.insert(tTransExtend);
            this.saveBlockChain(tTransExtend, currentUser);//上链


        }

        return res;

    }


    @Override
    public int saveBlockChain(TTransExtend tTransExtend,CurrentUser currentUser) throws BizException {
        BizException bizException=new BizException();
        //创建合同号-上链
        Map map=new HashMap<String,String>();
        map.put("purchaseContractNo",tTransExtend.getPurchaseContractNo());
        map.put("agencyAgreementNo",tTransExtend.getAgencyAgreementNo());
        map.put("dealNo", tTransExtend.getDealNo());
        Gson gson=new Gson();
        String cnt = gson.toJson(map);
        //url="123.123.123.123";
        BlockChainEntity entity= BlockChainUtil.insertToBlockChain(cnt,url);
        if (null==entity||null==entity.getCode()||!entity.getCode().equals("1")) {
            bizException.setCode(ExceptionEnum.BLOCKCHAIN_SAVE_ERROR.getCode());
            throw bizException;
        }
        TBlockchainEventRecord tBlockchainEventRecord=new TBlockchainEventRecord();
        tBlockchainEventRecord.setDealNo(tTransExtend.getDealNo());
        tBlockchainEventRecord.setBusiId(tTransExtend.getId());
        tBlockchainEventRecord.setPostData(gson.toJson(map));
        if(null!=tTransExtend.getId()) {
            //更新合同号
            tBlockchainEventRecord.setEventCode(BlockchainEventEnums.E4005.getCode());
            tBlockchainEventRecord.setDescription(BlockchainEventEnums.E4005.getZhName());
        }else {
            //创建合同号
            tBlockchainEventRecord.setEventCode(BlockchainEventEnums.E4006.getCode());
            tBlockchainEventRecord.setDescription(BlockchainEventEnums.E4006.getZhName());
        }

        tBlockchainEventRecord.setBlockchainId(entity.getId());
        tBlockchainEventRecord.setBlockchainTxid(entity.getTxId());
        tBlockchainEventRecord.setCreateDate(new Date());
        tBlockchainEventRecord.setCreateUser(currentUser.getEpMemberId());

        return tBlockchainEventRecordService.saveTBlockchainEventRecord(tBlockchainEventRecord);
    }

    @Override
    public TTransExtend getTTransExtendByDealNo(CurrentUser currentUser, String dealNo) throws BizException {
        return tTransExtendMapper.selectByDealNo(dealNo);
    }

    @Override
    public TTransExtend selectByPurchaseContractNo(String purchaseContractNo) {
        return tTransExtendMapper.selectByPurchaseContractNo(purchaseContractNo);
    }

    @Override
    public List<TTransExtend> selectAllList(){

        return tTransExtendMapper.selectAllList();
    }
}
