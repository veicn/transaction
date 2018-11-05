package com.sinochem.crude.trade.broker.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinochem.crude.trade.blockchain.dao.TBrokerDeclarationMapper;
import com.sinochem.crude.trade.blockchain.domain.*;
import com.sinochem.crude.trade.blockchain.model.BlockChainEntity;

import com.sinochem.crude.trade.blockchain.service.BlockChainService;
import com.sinochem.crude.trade.blockchain.service.TBlockchainEventRecordService;
import com.sinochem.crude.trade.blockchain.service.TCommonAttachmentsService;
import com.sinochem.crude.trade.blockchain.service.TTransExtendService;
import com.sinochem.crude.trade.blockchain.utils.BlockChainUtil;
import com.sinochem.crude.trade.broker.enums.DeclarationStatusEnum;
import com.sinochem.crude.trade.broker.model.vo.TBrokerDeclarationQueryVO;
import com.sinochem.crude.trade.broker.model.vo.TBrokerVO;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.it.b2b.common.exception.BizException;
import com.zhqkl.api.Records;
import com.zhqkl.api.ResObject;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/7 11:31
 * @Version: [v1.0]
 */
@Service
public class DeclarationServiceImpl implements DeclarationService {
    @Autowired
    private TBrokerDeclarationMapper tBrokerDeclarationMapper;
    @Autowired
    private TBrokeDangerinfoService tBrokeDangerinfoService;
    @Autowired
    private TBrokerBusinessmattersService tBrokerBusinessmattersService;
    @Autowired
    private TBrokerDocumentsService tBrokerDocumentsService;
    @Autowired
    private TBrokerOthermattersService tBrokerOthermattersService;
    @Autowired
    private TBrokerOtherpackinfoService tBrokerOtherpackinfoService;
    @Autowired
    private TBlockchainEventRecordService tBlockchainEventRecordService;
    @Autowired
    private TTransExtendService tTransExtendService;
    @Autowired
    private BlockChainService blockChainService;

    @Autowired
    private TBrokerCipService tBrokerCipService;
    @Autowired
    private TBrokerProductspecificationService tBrokerProductspecificationService;
    @Autowired
    private TCommonAttachmentsService tCommonAttachmentsService;
    @Autowired
    private TBrokerGoodsService tBrokerGoodsService;

    @Value("${blockchain_gateway}")
    private String url;

    @Override
    @Transactional
    public int deleteByUuid(String uuid) {
        tBrokerDocumentsService.deleteByUuid(uuid);
        tBrokerBusinessmattersService.deleteByUuid(uuid);
        tBrokerOthermattersService.deleteByUuid(uuid);
        tBrokerOtherpackinfoService.deleteByUuid(uuid);
        tBrokeDangerinfoService.deleteByUuid(uuid);
        tBrokerCipService.deleteByUuid(uuid);
        tBrokerProductspecificationService.deleteByUuid(uuid);
        tCommonAttachmentsService.deleteByBusinessUuid(uuid);
        tBrokerGoodsService.deleteByUuid(uuid);
        return tBrokerDeclarationMapper.deleteByUuid(uuid);
    }

    @Override
    public TBrokerDeclaration selectByUuid(String uuid) {
        TBrokerDeclaration tBrokerDeclaration = tBrokerDeclarationMapper.selectByUuid(uuid);
        if (tBrokerDeclaration != null) {
            for (DeclarationStatusEnum item : DeclarationStatusEnum.values()
                    ) {
                if (item.getCode().equals(tBrokerDeclaration.getStatus())) {
                    tBrokerDeclaration.setStatus(item.getValue());
                }
            }
        }
        return tBrokerDeclaration;
    }

    @Override
    public Page<TBrokerDeclaration> selectByQuery(TBrokerDeclarationQueryVO tBrokerDeclarationQueryVO) {
        PageHelper.startPage(tBrokerDeclarationQueryVO.getPageNum(), tBrokerDeclarationQueryVO.getPageSize());
        return tBrokerDeclarationMapper.selectByQuery(tBrokerDeclarationQueryVO);
    }

    private void setuuid(TBrokerVO tBrokerVO)
    {
        if(tBrokerVO.gettBrokerDocuments()!=null) {
            for (TBrokerDocuments item : tBrokerVO.gettBrokerDocuments()
                    ) {
                item.setDeclarationuuid(tBrokerVO.getUuid());
            }
        }
        if(tBrokerVO.gettBrokerBusinessmatters()!=null) {
            for (TBrokerBusinessmatters item : tBrokerVO.gettBrokerBusinessmatters()
                    ) {
                item.setDeclarationuuid(tBrokerVO.getUuid());
            }
        }
        if(tBrokerVO.gettBrokerOthermatters()!=null) {
            for (TBrokerOthermatters item : tBrokerVO.gettBrokerOthermatters()
                    ) {
                item.setDeclarationuuid(tBrokerVO.getUuid());
            }
        }
        if(tBrokerVO.gettBrokerOtherpackinfo()!=null) {
            for (TBrokerOtherpackinfo item : tBrokerVO.gettBrokerOtherpackinfo()
                    ) {
                item.setDeclarationuuid(tBrokerVO.getUuid());
            }
        }
        if(tBrokerVO.gettBrokerCips()!=null) {
            for (TBrokerCip item : tBrokerVO.gettBrokerCips()) {
                item.setDeclarationuuid(tBrokerVO.getUuid());
            }
        }
        if(tBrokerVO.gettBrokerDangerinfo()!=null) {
            tBrokerVO.gettBrokerDangerinfo().setDeclarationuuid(tBrokerVO.getUuid());
        }
        if(tBrokerVO.gettBrokerProductspecification()!=null) {
            for (TBrokerProductspecification item : tBrokerVO.gettBrokerProductspecification()) {
                item.setDeclarationuuid(tBrokerVO.getUuid());
            }

        }
        if(tBrokerVO.gettBrokerVoucher()!=null){
            for (TCommonAttachments item : tBrokerVO.gettBrokerVoucher()) {
                item.setBusinessUuid(tBrokerVO.getUuid());
                //设置时间
                item.setCreateDate(new Date());
                item.setUpdateDate(new Date());
            }
        }
        if(tBrokerVO.gettBrokerGoods()!=null){
            for (TBrokerGoods item : tBrokerVO.gettBrokerGoods()) {
                item.setDeclarationuuid(tBrokerVO.getUuid());
            }
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(TBrokerVO tBrokerVO) throws BizException {
//        if(StringHelper.isNullOrEmptyString(tBrokerVO.getUuid())) {
            String uuid = UUID.randomUUID().toString();
            tBrokerVO.setUuid(uuid);
            setuuid(tBrokerVO);
//        }

        tBrokerVO.setCreatetime(new Date());

        tBrokerDocumentsService.insertList(tBrokerVO.gettBrokerDocuments());
        tBrokerBusinessmattersService.insertList(tBrokerVO.gettBrokerBusinessmatters());
        tBrokerOthermattersService.insertList(tBrokerVO.gettBrokerOthermatters());
        tBrokerOtherpackinfoService.insertList(tBrokerVO.gettBrokerOtherpackinfo());

        tBrokeDangerinfoService.insertSelective(tBrokerVO.gettBrokerDangerinfo());
        tBrokerProductspecificationService.insertList(tBrokerVO.gettBrokerProductspecification());
        tBrokerCipService.insertList(tBrokerVO.gettBrokerCips());
        tCommonAttachmentsService.insertList(tBrokerVO.gettBrokerVoucher());
        tBrokerGoodsService.insertList(tBrokerVO.gettBrokerGoods());

        int num= tBrokerDeclarationMapper.insertSelective(tBrokerVO);

//        //上链
        String content =gson.toJson(tBrokerVO);
        BlockChainEntity entity= blockChainService.insertToBlockChain(content);
        BizException bizException=new BizException();
        if (null==entity||!entity.getCode().equals("1")) {
            bizException.setCode(ExceptionEnum.BLOCKCHAIN_SAVE_ERROR.getCode());
            throw new BizException();
        }
        TTransExtend tTransExtend= tTransExtendService.selectByPurchaseContractNo(tBrokerVO.getPurchaseContractNo());
        TBlockchainEventRecord tBlockchainEventRecord=new TBlockchainEventRecord();
        tBlockchainEventRecord.setCreateUser(tBrokerVO.getEnterpriseId());
        if(tTransExtend!=null) {
            tBlockchainEventRecord.setDealNo(tTransExtend.getDealNo());
        }
        tBlockchainEventRecord.setBusiId(Long.valueOf(tBrokerVO.getId()));
        if(content.length()>1000){
            tBlockchainEventRecord.setPostData(content.substring(0,1000));
        }else {
            tBlockchainEventRecord.setPostData(content);
        }
        tBlockchainEventRecord.setEventCode("2004");
        tBlockchainEventRecord.setDescription("提交报关单信息");
        tBlockchainEventRecord.setBlockchainId(entity.getId());
        tBlockchainEventRecord.setBlockchainTxid(entity.getTxId());
        tBlockchainEventRecord.setCreateDate(new Date());
        tBlockchainEventRecordService.saveTBlockchainEventRecord(tBlockchainEventRecord);
        return num;
    }

    Gson gson = new Gson();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByUuidSelective(TBrokerVO tBrokerVO) throws BizException {
        setuuid(tBrokerVO);
        tBrokerDocumentsService.updateByUuid(tBrokerVO);
        tBrokerBusinessmattersService.updateByUuid(tBrokerVO);
        tBrokerOthermattersService.updateByUuid(tBrokerVO);
        tBrokerOtherpackinfoService.updateByUuid(tBrokerVO);
        tBrokeDangerinfoService.updateByUuid(tBrokerVO);
        tBrokerCipService.updateByUuid(tBrokerVO);
        tBrokerProductspecificationService.updateByUuid(tBrokerVO);
        tBrokerGoodsService.updateByUuid(tBrokerVO);
        tCommonAttachmentsService.updateByUuid(tBrokerVO);

        int num = tBrokerDeclarationMapper.updateByUuidSelective(tBrokerVO);

        String content = gson.toJson(tBrokerVO);
        BlockChainEntity entity= blockChainService.insertToBlockChain(content);
        BizException bizException=new BizException();
        if (null==entity||!entity.getCode().equals("1")) {
            bizException.setCode(ExceptionEnum.BLOCKCHAIN_SAVE_ERROR.getCode());
            throw new BizException();
        }
        TTransExtend tTransExtend= tTransExtendService.selectByPurchaseContractNo(tBrokerVO.getPurchaseContractNo());
        TBlockchainEventRecord tBlockchainEventRecord=new TBlockchainEventRecord();
        tBlockchainEventRecord.setCreateUser(tBrokerVO.getEnterpriseId());
        if(tTransExtend!=null) {
            tBlockchainEventRecord.setDealNo(tTransExtend.getDealNo());
        }

      tBlockchainEventRecord.setBusiId(Long.valueOf(tBrokerVO.getId()==null?0:tBrokerVO.getId()));
        if(content.length()>1000){
            tBlockchainEventRecord.setPostData(content.substring(0,1000));
        }else {
            tBlockchainEventRecord.setPostData(content);
        }
        tBlockchainEventRecord.setEventCode("2003");
        tBlockchainEventRecord.setDescription("更新报关单信息");
        tBlockchainEventRecord.setBlockchainId(entity.getId());
        tBlockchainEventRecord.setBlockchainTxid(entity.getTxId());
        tBlockchainEventRecord.setCreateDate(new Date());
        tBlockchainEventRecordService.saveTBlockchainEventRecord(tBlockchainEventRecord);
        return num;
    }
}
