package com.sinochem.crude.trade.transaction.service.impl;


import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinochem.crude.trade.blockchain.domain.TBlockchainEventRecord;

import com.sinochem.crude.trade.blockchain.domain.TBrokerAppoint;
import com.sinochem.crude.trade.blockchain.domain.TInspectorAppoint;
import com.sinochem.crude.trade.blockchain.domain.TShipagentAppoint;
import com.sinochem.crude.trade.blockchain.enums.BlockchainEventEnums;
import com.sinochem.crude.trade.blockchain.model.BlockChainEntity;
import com.sinochem.crude.trade.blockchain.service.BlockChainService;
import com.sinochem.crude.trade.blockchain.service.TBlockchainEventRecordService;

import com.sinochem.crude.trade.blockchain.service.TTransExtendService;
import com.sinochem.crude.trade.blockchain.utils.BlockChainUtil;
import com.sinochem.crude.trade.broker.service.impl.ForwarderService;
import com.sinochem.crude.trade.common.constant.Mark;
import com.sinochem.crude.trade.inspector.service.TInspectorAppointService;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipagent.service.AppointTaskService;
import com.sinochem.crude.trade.transaction.dao.SaleSheetHistoryMapper;
import com.sinochem.crude.trade.transaction.dao.SaleSheetMapper;
import com.sinochem.crude.trade.transaction.domain.ContractSheetCombine;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.*;
import com.sinochem.crude.trade.transaction.enums.BiddingSheetStatusEnum;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.enums.SaleSheetStatusEnum;
import com.sinochem.crude.trade.transaction.enums.SaleTypeEnum;
import com.sinochem.crude.trade.transaction.helper.IdentificationHelper;
import com.sinochem.crude.trade.transaction.helper.NotificationHelper;
import com.sinochem.crude.trade.transaction.model.query.SaleSheetQuery;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetWXVO;
import com.sinochem.crude.trade.transaction.model.vo.SaleSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.SaleSheetQueryVO;
import com.sinochem.crude.trade.transaction.service.*;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.page.PageUtils;
import com.sinochem.it.b2b.common.result.ResultDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

public class SaleSheetServiceImpl implements SaleSheetService {

    @Autowired
    private SaleSheetMapper saleSheetMapper;

    @Autowired
    private SaleSheetHistoryMapper saleSheetHistoryMapper;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private PricingInfoService pricingInfoService;

    @Autowired
    private TransportInfoService transportInfoService;

    @Autowired
    private BlockChainService blockChainService;

    @Autowired
    private StakeHolderInfoService stakeHolderInfoService;

    @Autowired
    private OtherInfoService otherInfoService;

    @Autowired
    private BiddingSheetService biddingSheetService;

    @Autowired
    private ContractSheetService contractSheetService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private TTransExtendService tTransExtendService;

    @Autowired
    private IdentificationHelper identificationHelper;

    @Autowired
    private NotificationHelper notificationHelper;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private AppointTaskService appointTaskService;
    @Autowired
    private ForwarderService forwarderService;
    @Autowired
    private TInspectorAppointService tInspectorAppointService;


    @Value("${blockchain_gateway}")
    private String url;

    @Autowired
    private TBlockchainEventRecordService tBlockchainEventRecordService;

    @Override
    public Long saveSaleSheet(CurrentUser currentUser, SaleSheet saleSheet) throws BizException {
        return null;
    }

    @Override
    public void deleteSaleSheetById(CurrentUser currentUser, Long id) throws BizException {

    }

    @Override
    public void updateSaleSheet(CurrentUser currentUser, SaleSheet saleSheet) throws BizException {

    }

    @Override
    public void updateSaleSheetSelective(CurrentUser currentUser, SaleSheet saleSheet) throws BizException {

    }

    @Override
    public SaleSheet getSaleSheetById(CurrentUser currentUser, Long id) throws BizException {

        if (id == null) {
            return null;
        }

        SaleSheet saleSheet = saleSheetMapper.selectByPrimaryKey(id);
        return saleSheet;
    }

    @Override
    public SaleSheet getSaleSheetByUuid(CurrentUser currentUser, String uuid) throws BizException {

        if (StringUtil.isEmpty(uuid)) {
            return null;
        }

        SaleSheet saleSheet = saleSheetMapper.selectByUuid(uuid);
        return saleSheet;
    }

    @Override
    public void deleteSaleSheetByIdLogical(CurrentUser currentUser, String uuid) throws BizException {
        BizException bizException = new BizException();

        if (currentUser == null || StringUtil.isEmpty(uuid)) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode()); //使用ExceptionEnum来枚举异常的code
            throw bizException;
        }

        SaleSheet saleSheet = saleSheetMapper.selectByUuid(uuid);
        if (saleSheet == null) {
            bizException.setCode(ExceptionEnum.NO_DATA.getCode());
            throw bizException;
        }

        SaleSheet saleSheetForUpdate = new SaleSheet();
        saleSheetForUpdate.setId(saleSheet.getId());
        saleSheetForUpdate.setSaleSheetStatusCode(SaleSheetStatusEnum.CANCELLED.getCode());
        saleSheetForUpdate.setCancelledDate(new Date());
        saleSheetForUpdate.setAliveFlag(Mark.DELETED);
        saleSheetMapper.updateByPrimaryKeySelective(saleSheetForUpdate);

        productInfoService.deleteProductInfoByIdLogical(currentUser, saleSheet.getProductInfoId());
        pricingInfoService.deletePricingInfoByIdLogical(currentUser, saleSheet.getPricingInfoId());
        transportInfoService.deleteTransportInfoByIdLogical(currentUser, saleSheet.getTransportInfoId());
        stakeHolderInfoService.deleteStakeholderInfoByIdLogical(currentUser, saleSheet.getStakeholderInfoId());
        otherInfoService.deleteOtherInfoByIdLogical(currentUser, saleSheet.getOtherInfoId());
    }


    @Override
    public Long postSaleSheet(CurrentUser currentUser, SaleSheetCombine saleSheetCombine) throws BizException {
        BizException bizException = new BizException();

        if (currentUser == null || saleSheetCombine == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        SaleSheet saleSheet = saleSheetCombine.getSaleSheet();
        if (saleSheet == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        String saleSheetStatusCode = saleSheet.getSaleSheetStatusCode();
        if (StringUtil.isEmpty(saleSheetStatusCode)) {
            //bizException.setCode(ExceptionEnum.SALE_SHEET_STATUS_ERROR.getCode());
            throw bizException;
        }

        if (!SaleSheetStatusEnum.SAVED.getCode().equals(saleSheetStatusCode)
                && !SaleSheetStatusEnum.RELEASED.getCode().equals(saleSheetStatusCode)) {
            //bizException.setCode(ExceptionEnum.SALE_SHEET_STATUS_ERROR.getCode());
            throw bizException;
        }

        ProductInfo productInfo = saleSheetCombine.getProductInfo();
        productInfo.setSaleTypeCode(saleSheet.getSaleTypeCode());
        PricingInfo pricingInfo = saleSheetCombine.getPricingInfo();
        TransportInfo transportInfo = saleSheetCombine.getTransportInfo();

        StakeholderInfo salerInfo = saleSheetCombine.getSalerInfo();
        if (salerInfo == null) {
            salerInfo = new StakeholderInfo();
        }
        salerInfo.setEnterpriseId(currentUser.getEpMemberId());

        OtherInfo otherInfo = saleSheetCombine.getOtherInfo();

        Long productInfoId = productInfoService.saveProductInfo(currentUser, productInfo);
        Long pricingInfoId = pricingInfoService.savePricingInfo(currentUser, pricingInfo);
        Long transportInfoId = transportInfoService.saveTransportInfo(currentUser, transportInfo);
        Long salerInfoId = stakeHolderInfoService.saveStakeholderInfo(currentUser, salerInfo);
        Long otherInfoId = otherInfoService.saveOtherInfo(currentUser, otherInfo);

        saleSheet.setProductInfoId(productInfoId);
        saleSheet.setPricingInfoId(pricingInfoId);
        saleSheet.setTransportInfoId(transportInfoId);
        saleSheet.setStakeholderInfoId(salerInfoId);
        saleSheet.setOtherInfoId(otherInfoId);

        saleSheet.setGmtCreated(new Date());
        saleSheet.setUuid(identificationHelper.generateUuid());
        saleSheet.setUserCreated(currentUser.getMemberId());
        saleSheet.setEnterpriseId(currentUser.getEpMemberId());
        saleSheet.setSerialNumber(identificationHelper.generateSerialNumber());

        if (SaleSheetStatusEnum.SAVED.getCode().equals(saleSheetStatusCode)) {
            saleSheet.setSavedDate(new Date()); //保存
        } else if (SaleSheetStatusEnum.RELEASED.getCode().equals(saleSheetStatusCode)){
            saleSheet.setReleasedDate(new Date()); //发布
        }

        saleSheetMapper.insert(saleSheet);

        if (SaleSheetStatusEnum.RELEASED.getCode().equals(saleSheetStatusCode)) {
            String saleTypeCode = saleSheet.getSaleTypeCode();

            if (!StringUtil.isEmpty(saleTypeCode)) {
                if (SaleTypeEnum.DIRECTIONAL.getCode().equals(saleTypeCode)) {
                    notificationHelper.newSaleSheetPublished(saleSheet);
                }
            }
        }

        return saleSheet.getId();
    }

    @Override
    public void updateSaleSheetCombineSaveHistory(CurrentUser currentUser, SaleSheetCombine saleSheetCombine) throws BizException {
        BizException bizException = new BizException();

        if (saleSheetCombine == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        SaleSheet saleSheet = saleSheetCombine.getSaleSheet();
        if (saleSheet == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        Long saleSheetId = saleSheet.getId();
        String saleSheetUuid = saleSheet.getUuid();
        SaleSheet existedSaleSheet;

        if (saleSheetId != null) {
            existedSaleSheet = saleSheetMapper.selectByPrimaryKey(saleSheetId);
        } else if (!StringUtil.isEmpty(saleSheetUuid)) {
            existedSaleSheet = saleSheetMapper.selectByUuid(saleSheetUuid);
        } else {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        if (existedSaleSheet == null) {
            bizException.setCode(ExceptionEnum.NO_DATA.getCode());
            throw bizException;
        }

        /*
        if (!existedSaleSheet.getUserCreated().equals(currentUser.getMemberId())) {
            bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
            throw bizException;
        }
        */

        saleSheet.setId(existedSaleSheet.getId());

        /*将原有信息存入历史表*/
        this.saveSaleSheetHistory(currentUser, existedSaleSheet.getId());

        /*更新全部信息*/
        ProductInfo productInfo = saleSheetCombine.getProductInfo();
        PricingInfo pricingInfo = saleSheetCombine.getPricingInfo();
        TransportInfo transportInfo = saleSheetCombine.getTransportInfo();
        StakeholderInfo salerInfo = saleSheetCombine.getSalerInfo();
        OtherInfo otherInfo = saleSheetCombine.getOtherInfo();

        Long productInfoId = productInfoService.saveProductInfo(currentUser, productInfo);
        Long pricingInfoId = pricingInfoService.savePricingInfo(currentUser, pricingInfo);
        Long transportInfoId = transportInfoService.saveTransportInfo(currentUser, transportInfo);
        Long stakeholderInfoId = stakeHolderInfoService.saveStakeholderInfo(currentUser, salerInfo);
        Long otherInfoId = otherInfoService.saveOtherInfo(currentUser, otherInfo);

        saleSheet.setProductInfoId(productInfoId);
        saleSheet.setPricingInfoId(pricingInfoId);
        saleSheet.setTransportInfoId(transportInfoId);
        saleSheet.setStakeholderInfoId(stakeholderInfoId);
        saleSheet.setOtherInfoId(otherInfoId);

        saleSheet.setGmtModified(new Date());
        saleSheet.setUserModified(saleSheet.getUserCreated());

        saleSheetMapper.updateByPrimaryKeySelective(saleSheet);
    }

    @Override
    public PageInfoResult<SaleSheet> getSaleSheetList(CurrentUser currentUser, SaleSheetQueryVO saleSheetQueryVO, PageInfo pageInfo) throws BizException {
        BizException bizException = new BizException();

        if (currentUser == null || saleSheetQueryVO == null ) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        SaleSheetQuery saleSheetQuery = new SaleSheetQuery();
        if(saleSheetQueryVO != null){
            saleSheetQuery = saleSheetQueryVO.getSaleSheetQuery();
        }

        if (pageInfo != null) {
            PageUtils.page(pageInfo);
        }

        List<SaleSheet> saleSheetList = saleSheetMapper.getSaleSheetList(saleSheetQuery);
        return new PageInfoResult<SaleSheet>(saleSheetList);
    }

    @Override
    public PageInfoResult getVisibleSaleSheetList(CurrentUser currentUser, SaleSheetQueryVO saleSheetQueryVO, PageInfo pageInfo) throws BizException {
        BizException bizException = new BizException();

        if (currentUser == null || saleSheetQueryVO == null ) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        SaleSheetQuery saleSheetQuery = new SaleSheetQuery();
        if(saleSheetQueryVO != null){
            saleSheetQuery = saleSheetQueryVO.getSaleSheetQuery();
        }

        saleSheetQuery.setQueryEnterpriseId(currentUser.getEpMemberId());

        if (pageInfo != null) {
            PageUtils.page(pageInfo);
        }

        List<SaleSheet> saleSheetList = saleSheetMapper.getVisibleSaleSheetList(saleSheetQuery);
        return new PageInfoResult<SaleSheet>(saleSheetList);
    }

    @Override
    public void changeSaleSheetStatus(CurrentUser currentUser, SaleSheet saleSheet, String targetStatusCode) throws BizException {
        BizException bizException = new BizException();

        if (saleSheet == null || targetStatusCode == null ) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        if(SaleSheetStatusEnum.CANCELLED.getCode().equals(targetStatusCode)){
            saleSheet.setCancelledDate(new Date());
            saleSheet.setSaleSheetStatusCode(SaleSheetStatusEnum.CANCELLED.getCode());
        } else if(SaleSheetStatusEnum.COMPLETED.getCode().equals(targetStatusCode)){
            saleSheet.setCompletedDate(new Date());
            saleSheet.setSaleSheetStatusCode(SaleSheetStatusEnum.COMPLETED.getCode());
        } else if(SaleSheetStatusEnum.SAVED.getCode().equals(targetStatusCode)){
            saleSheet.setSavedDate(new Date());
            saleSheet.setSaleSheetStatusCode(SaleSheetStatusEnum.SAVED.getCode());
        } else if(SaleSheetStatusEnum.RELEASED.getCode().equals(targetStatusCode)){
            saleSheet.setReleasedDate(new Date());
            saleSheet.setSaleSheetStatusCode(SaleSheetStatusEnum.RELEASED.getCode());
        }

        saleSheet.setSaleSheetStatusCode(targetStatusCode);
        // saleSheet 转换为 saleSheetCombine
        ProductInfo productInfo = productInfoService.getProductInfoById(currentUser,saleSheet.getProductInfoId());
        PricingInfo pricingInfo = pricingInfoService.getPricingInfoById(currentUser,saleSheet.getPricingInfoId());
        TransportInfo transportInfo = transportInfoService.getTransportInfoById(currentUser,saleSheet.getTransportInfoId());
        StakeholderInfo stakeholderInfo = stakeHolderInfoService.getStakeholderInfoById(currentUser,saleSheet.getStakeholderInfoId());
        OtherInfo otherInfo = otherInfoService.getOtherInfoById(currentUser,saleSheet.getOtherInfoId());
        SaleSheetCombine saleSheetCombine = new SaleSheetCombine();
        saleSheetCombine.setOtherInfo(otherInfo);
        saleSheetCombine.setPricingInfo(pricingInfo);
        saleSheetCombine.setProductInfo(productInfo);
        saleSheetCombine.setSaleSheet(saleSheet);
        saleSheetCombine.setTransportInfo(transportInfo);
        saleSheetCombine.setSalerInfo(stakeholderInfo);
        this.updateSaleSheetCombineSaveHistory(currentUser,saleSheetCombine);
    }

    @Override
    public void confirmBidding(CurrentUser currentUser, SaleSheet salesSheet, BiddingSheet biddingSheet) throws BizException {
        BizException bizException = new BizException();

        if (currentUser == null || salesSheet == null || biddingSheet == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        Long saleSheetId = salesSheet.getId();

        if(saleSheetId != null){
            List<BiddingSheet> bidList = biddingSheetService.selectBySaleSheetId(saleSheetId);
            for (BiddingSheet bidSheet: bidList) {
                //修改该需求单下其他报价单状态改为未中标
                if(!bidSheet.getId().equals(biddingSheet.getId()) ){
                    biddingSheetService.changeBiddingSheetStatus(currentUser,bidSheet, BiddingSheetStatusEnum.LOST.getCode());
                    notificationHelper.biddingSheetLost(bidSheet);
                }
            }
            //生成合约单
            ContractSheet contractSheet  = contractSheetService.generateContractSheet(currentUser,salesSheet,biddingSheet);
            //修改需求单状态改为已完成
            this.changeSaleSheetStatus(currentUser,salesSheet, SaleSheetStatusEnum.COMPLETED.getCode());
            //修改报价单状态改为已中标
            biddingSheetService.changeBiddingSheetStatus(currentUser,biddingSheet,BiddingSheetStatusEnum.WON.getCode());

            //if(contractSheet.getSalerId()==100008253) {
               /* //交易扩展表
                TTransExtend tTransExtend = new TTransExtend();
                tTransExtend.setDealNo(contractSheet.getSerialNumber());
                tTransExtend.setBuyerEnterpriseId(contractSheet.getBuyerId());
                EnterpriseVo buyerVo = enterpriseService.getByMemberId(contractSheet.getBuyerId());
                tTransExtend.setBuyerEnterpriseName(buyerVo.getFullName());
                tTransExtend.setSellerEnterpriseId(contractSheet.getSalerId());
                EnterpriseVo sellerVo = enterpriseService.getByMemberId(contractSheet.getSalerId());
                tTransExtend.setSellerEnterpriseName(sellerVo.getFullName());
                tTransExtend.setAliveFlag("1");
                tTransExtend.setCreateDate(new Date());
                tTransExtend.setCreateUser(currentUser.getEpMemberId());
                tTransExtend.setUpdateDate(new Date());
                tTransExtend.setUpdateUser(currentUser.getEpMemberId());

                //交易扩展表
                tTransExtendService.saveTTransExtend(tTransExtend);

                */

                //broker委托表
                TBrokerAppoint tBrokerAppoint=new TBrokerAppoint();
                tBrokerAppoint.setAliveFlag("1");
                //tBrokerAppoint.setAppointEnterpriseId( Long.valueOf(appointEnterpriseId).longValue());
                //tBrokerAppoint.setAppointEnterpriseName(appointEnterpriseName);
                tBrokerAppoint.setCreateDate(new Date());
                tBrokerAppoint.setCreateUser(currentUser.getEpMemberId());
                tBrokerAppoint.setDealNo(contractSheet.getSerialNumber());
                tBrokerAppoint.setDealUuid(contractSheet.getUuid());
                //tBrokerAppoint.setEnterpriseId(currentUser.getEpMemberId());
                //tBrokerAppoint.setEnterpriseName(currentUser.getName());
                tBrokerAppoint.setStatus("1");
                forwarderService.insert(tBrokerAppoint);

                //shipagent委托表
                TShipagentAppoint tShipagentAppoint=new TShipagentAppoint();
                tShipagentAppoint.setAliveFlag("1");
                //tShipagentAppoint.setAppointEnterpriseId( Long.valueOf(appointEnterpriseId).longValue());
                //tShipagentAppoint.setAppointEnterpriseName(appointEnterpriseName);
                tShipagentAppoint.setCreateDate(new Date());
                tShipagentAppoint.setCreateUser(currentUser.getEpMemberId());
                tShipagentAppoint.setDealNo(contractSheet.getSerialNumber());
                tShipagentAppoint.setDealUuid(contractSheet.getUuid());
                //tShipagentAppoint.setEnterpriseId(currentUser.getEpMemberId());
                //tShipagentAppoint.setEnterpriseName(currentUser.getName());
                tShipagentAppoint.setStatus("1");
                appointTaskService.insert(tShipagentAppoint);

                //inspector委托表
                TInspectorAppoint tInspectorAppoint=new TInspectorAppoint();
                tInspectorAppoint.setAliveFlag("1");
                //tInspectorAppoint.setAppointEnterpriseId( Long.valueOf(appointEnterpriseId).longValue());
                //tInspectorAppoint.setAppointEnterpriseName(appointEnterpriseName);
                tInspectorAppoint.setCreateDate(new Date());
                tInspectorAppoint.setCreateUser(currentUser.getEpMemberId());
                tInspectorAppoint.setDealNo(contractSheet.getSerialNumber());
                tInspectorAppoint.setDealUuid(contractSheet.getUuid());
                //tInspectorAppoint.setEnterpriseId(currentUser.getEpMemberId());
                //tInspectorAppoint.setEnterpriseName(currentUser.getName());
                tInspectorAppoint.setStatus("1");
                tInspectorAppointService.insert(tInspectorAppoint);

                //交易达成-上链：油种、数量、装期、贸易条款
                /*SaleSheetCombine saleSheetCombine = this.getSaleSheetCombine(currentUser,salesSheet);
                SaleSheetCombineVO saleSheetCombineVO = new SaleSheetCombineVO(saleSheetCombine);
                String productCategory =saleSheetCombineVO.getProductInfoVO().getProductCategoryVO().getEnName()+" "+saleSheetCombineVO.getProductInfoVO().getProductSpecificationVO().getEnName();
                String quantity=saleSheetCombineVO.getProductInfoVO().getQuantityAsString()+saleSheetCombineVO.getProductInfoVO().getQuantityUnitVO().getEnName();
                String laycan=saleSheetCombineVO.getTransportInfoVO().getLaycanStartDateAsString() +"-"+saleSheetCombineVO.getTransportInfoVO().getLaycanEndDateAsString();
                String incoterms=saleSheetCombineVO.getProductInfoVO().getTradeTermVO().getEnName();
                Map map=new HashMap<String,String>();
                map.put("productCategory","productCategory");
                map.put("quantity","quantity");
                map.put("laycan","laycan");
                map.put("incoterms","incoterms");
                map.put("quantity","quantity");
                map.put("dealNo", contractSheet.getSerialNumber());*/

            ContractSheetWXVO contdata = this.getContractSheetListByuuid(contractSheet.getUuid());
            EnterpriseVo enterpriseAgent = enterpriseService.getByMemberId(100008254L);
            contdata.setAgentName(enterpriseAgent.getName());
            contdata.setAgentContactAddress(enterpriseAgent.getContactAddress());
            contdata.setAgentContactName(enterpriseAgent.getContactName());
            contdata.setAgentEmail(enterpriseAgent.getEmail());
            contdata.setAgentContactPhone(enterpriseAgent.getContactPhone());
            contdata.setAgentFax("");

                Gson gson = new GsonBuilder().serializeNulls().create();
                //BlockChainEntity entity= BlockChainUtil.insertToBlockChain(gson.toJson(map),url);
                BlockChainEntity entity= BlockChainUtil.insertToBlockChain(gson.toJson(contdata),url);
                if (null==entity||!entity.getCode().equals("1")) {
                    bizException.setCode(ExceptionEnum.BLOCKCHAIN_SAVE_ERROR.getCode());
                    throw bizException;
                }
                TBlockchainEventRecord tBlockchainEventRecord=new TBlockchainEventRecord();
                tBlockchainEventRecord.setDealNo(contractSheet.getSerialNumber());
                tBlockchainEventRecord.setBusiId(contractSheet.getId());
                tBlockchainEventRecord.setPostData(gson.toJson(contdata));
                int len = gson.toJson(contdata).length();
                tBlockchainEventRecord.setEventCode(BlockchainEventEnums.E4007.getCode());
                tBlockchainEventRecord.setDescription(BlockchainEventEnums.E4007.getZhName());
                tBlockchainEventRecord.setBlockchainId(entity.getId());
                tBlockchainEventRecord.setBlockchainTxid(entity.getTxId());
                tBlockchainEventRecord.setCreateDate(new Date());
                tBlockchainEventRecord.setCreateUser(currentUser.getEpMemberId());

                tBlockchainEventRecordService.saveTBlockchainEventRecord(tBlockchainEventRecord);

            //}



            notificationHelper.biddingSheetWon(biddingSheet);
        }
    }

    @Override
    public void saveSaleSheetHistory(CurrentUser currentUser, Long saleSheetId) throws BizException {
        BizException bizException = new BizException();

        /*if (currentUser == null || saleSheetId == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }*/
        SaleSheet saleSheet = this.getSaleSheetById(currentUser,saleSheetId);
        if (saleSheet == null) {
            bizException.setCode(ExceptionEnum.NO_DATA.getCode());
            throw bizException;
        }

        saleSheet.setUuid(UUID.randomUUID().toString());
        saleSheetHistoryMapper.insert(saleSheet);
    }

    @Override
    public SaleSheetCombine getSaleSheetCombine(CurrentUser currentUser, SaleSheet saleSheet) throws BizException {
        BizException bizException = new BizException();

        /*if (currentUser == null || saleSheet == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }*/

        /**
        if (!currentUser.getMemberId().equals(saleSheet.getUserCreated())) {
            bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
            throw bizException;
        }
         */

        SaleSheetCombine saleSheetCombine = new SaleSheetCombine();
        saleSheetCombine.setSaleSheet(saleSheet);

        ProductInfo productInfo = productInfoService.getProductInfoById(currentUser, saleSheet.getProductInfoId());
        saleSheetCombine.setProductInfo(productInfo);

        PricingInfo pricingInfo = pricingInfoService.getPricingInfoById(currentUser, saleSheet.getPricingInfoId());
        saleSheetCombine.setPricingInfo(pricingInfo);

        TransportInfo transportInfo = transportInfoService.getTransportInfoById(currentUser, saleSheet.getTransportInfoId());
        saleSheetCombine.setTransportInfo(transportInfo);

        StakeholderInfo stakeholderInfo = stakeHolderInfoService.getStakeholderInfoById(currentUser, saleSheet.getStakeholderInfoId());
        saleSheetCombine.setSalerInfo(stakeholderInfo);

        OtherInfo otherInfo = otherInfoService.getOtherInfoById(currentUser, saleSheet.getOtherInfoId());
        saleSheetCombine.setOtherInfo(otherInfo);

        return saleSheetCombine;
    }

    @Override
    public void cancelBiddingSheet(CurrentUser currentUser, SaleSheet saleSheet) throws BizException {


        this.changeSaleSheetStatus(currentUser, saleSheet, SaleSheetStatusEnum.CANCELLED.getCode());
        List<BiddingSheet> biddingSheetList = biddingSheetService.selectBySaleSheetId(saleSheet.getId());
        for (BiddingSheet biddingSheet : biddingSheetList) {
            if("1".equals(biddingSheet.getBiddingSheetStatusCode())){
                notificationHelper.biddingSheetLost(biddingSheet);
            }
            biddingSheetService.changeBiddingSheetStatus(currentUser, biddingSheet, BiddingSheetStatusEnum.LOST.getCode());
        }
    }

    @Override
    public void productShelfOrOffShelf(CurrentUser currentUser, String uuid , String code) throws BizException {
        BizException bizException = new BizException();
        if (currentUser == null || StringUtil.isEmpty(uuid) || StringUtil.isEmpty(code)) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        SaleSheet saleSheet = this.getSaleSheetByUuid(currentUser, uuid);

        //如果是下架与此销售单对应的报价单改为作废
        if(code.equals(SaleSheetStatusEnum.CANCELLED.getCode()) || code.equals(SaleSheetStatusEnum.SAVED.getCode())){
            List<BiddingSheet> biddingSheets = biddingSheetService.selectBySaleSheetId(saleSheet.getId());
            if(!biddingSheets.isEmpty()){
                for(BiddingSheet biddingSheet:biddingSheets) {
                    biddingSheetService.changeBiddingSheetStatus(currentUser,biddingSheet,BiddingSheetStatusEnum.CANCELLED.getCode());
                }
            }
        }
        this.changeSaleSheetStatus(currentUser,saleSheet,code);
    }


    public ResultDatas<ContractSheetWXVO> getContractSheetListByeuuid(String uuid) throws BizException {
        ResultDatas<ContractSheetWXVO> resultDatas = new ResultDatas<>();
        ContractSheetWXVO wxvo = null;
            ContractSheet contractSheet = contractSheetService.getContractSheetByUuid(null, uuid);//.getContractSheetListByeEpmemberid(queryvo.getBuyerId(), queryvo.getKeywords());
            ContractSheetCombine contractSheetCombine = contractSheetService.getContractSheetCombine(null, contractSheet);
            ContractSheetCombineVO contractSheetCombineVO = new ContractSheetCombineVO(contractSheetCombine);
            if (contractSheetCombineVO != null) {
                wxvo = new ContractSheetWXVO();
                wxvo.setUuid(contractSheet.getUuid());
                wxvo.setSerialNumber(contractSheetCombineVO.getContractSheetVO().getSerialNumber());
                wxvo.setContractSheetStatus(contractSheetCombineVO.getContractSheetVO().getContractSheetStatusVO().getZhName());
                if (contractSheetCombineVO.getProductInfoVO() != null) {
                    wxvo.setProductCategory(contractSheetCombineVO.getProductInfoVO().getProductCategoryVO().getZhName());
                    wxvo.setProductSpecification(contractSheetCombineVO.getProductInfoVO().getProductSpecificationVO().getZhName());
                    String quantity = contractSheetCombineVO.getProductInfoVO().getQuantityAsString();
                    if (contractSheetCombineVO.getProductInfoVO().getQuantityUnitVO() != null) {
                        quantity += contractSheetCombineVO.getProductInfoVO().getQuantityUnitVO().getZhName();
                    }
                    wxvo.setQuantity(quantity);
                    if (contractSheetCombineVO.getProductInfoVO().getTradeTermVO() != null) {
                        wxvo.setTradeTerm(contractSheetCombineVO.getProductInfoVO().getTradeTermVO().getZhName());
                    }
                    String tolerance = "+/-" + contractSheetCombineVO.getProductInfoVO().getTolerance() + "%at OT";
                    wxvo.setTolerance(tolerance);
                    wxvo.setQualityStandard(contractSheetCombineVO.getProductInfoVO().getQualityStandard());

                }
                if (contractSheetCombineVO.getPricingInfoVO() != null) {
                    if (contractSheetCombineVO.getPricingInfoVO().getPricingPeriod() != null) {
                        wxvo.setPricingPeriod(contractSheetCombineVO.getPricingInfoVO().getPricingPeriod());
                    }
                    wxvo.setPricingUnit(contractSheetCombineVO.getPricingInfoVO().getPricingUnitVO().getZhName());
                    String pdstr = contractSheetCombineVO.getPricingInfoVO().getPremiumsAndDiscountsAsString();
                    pdstr += contractSheetCombineVO.getPricingInfoVO().getCurrencyVO().getZhName() + "/" + contractSheetCombineVO.getPricingInfoVO().getPricingUnitVO().getEnName();
                    wxvo.setPremiumsAndDiscounts(pdstr);
                    if (contractSheetCombineVO.getPricingInfoVO().getPricingBenchmarkVO() != null) {
                        wxvo.setPricingBenchmark(contractSheetCombineVO.getPricingInfoVO().getPricingBenchmarkVO().getZhName());
                    }
                    if (contractSheetCombineVO.getPricingInfoVO().getPricingBenchmarkVO2() != null) {
                        wxvo.setPricingBenchmark2(contractSheetCombineVO.getPricingInfoVO().getPricingBenchmarkVO2().getZhName());
                        wxvo.setPricingFormula("(Benchmark+Benchmark2)/2+ Premium/Discount Currency/Unit");
                    } else {
                        wxvo.setPricingFormula("Benchmark+ Premium/Discount Currency/Unit");
                    }
                    wxvo.setQuantityDter(contractSheetCombineVO.getPricingInfoVO().getSettlementQuantity());

                    wxvo.setPaymentTerm(contractSheetCombineVO.getPricingInfoVO().getPaymentTerm());
                    wxvo.setLaw(contractSheetCombineVO.getPricingInfoVO().getLaw());
                    if (contractSheetCombineVO.getPricingInfoVO().getInspectionVO() != null) {
                        wxvo.setInspection(contractSheetCombineVO.getPricingInfoVO().getInspectionVO().getZhName());
                    }
                }
                String tolerance = "+/-" + contractSheetCombineVO.getProductInfoVO().getTolerance() + "%at OT";
                wxvo.setTolerance(tolerance);
                wxvo.setTradeTermCode(contractSheetCombineVO.getProductInfoVO().getTradeTermVO().getZhName());
                if (contractSheetCombineVO.getOtherInfoVO() != null) {
                    wxvo.setOtherTerm(contractSheetCombineVO.getOtherInfoVO().getOtherTerm());
                }
                if (contractSheetCombineVO.getTransportInfoVO() != null) {
                    wxvo.setLaycanEndDateAsString(contractSheetCombineVO.getTransportInfoVO().getLaycanEndDateAsString());
                    wxvo.setLaycanStartDateAsString(contractSheetCombineVO.getTransportInfoVO().getLaycanStartDateAsString());
                    wxvo.setLaycanDateAsString(com.sinochem.crude.trade.transaction.utils.DateUtil.ENDateCH(wxvo.getLaycanStartDateAsString()) + " - " + com.sinochem.crude.trade.transaction.utils.DateUtil.ENDateCH(wxvo.getLaycanEndDateAsString()));

                    if (contractSheetCombineVO.getTransportInfoVO().getTransportModeVO() != null) {
                        wxvo.setTransportMode(contractSheetCombineVO.getTransportInfoVO().getTransportModeVO().getZhName());
                    }
                    if (contractSheetCombineVO.getTransportInfoVO().getDischargePort() != null) {
                        wxvo.setDischargeCountry(contractSheetCombineVO.getTransportInfoVO().getDischargeCountry());
                        wxvo.setDischargePort(contractSheetCombineVO.getTransportInfoVO().getDischargePort());
                    } else {
                        wxvo.setDischargeCountry("");
                        wxvo.setDischargePort("");

                    }
                    if (contractSheetCombineVO.getTransportInfoVO().getLoadingPort() != null) {
                        wxvo.setLoadCountry(contractSheetCombineVO.getTransportInfoVO().getLoadingCountry());
                        wxvo.setLoadPort(contractSheetCombineVO.getTransportInfoVO().getLoadingPort());
                    } else {
                        wxvo.setLoadCountry("");
                        wxvo.setLoadPort("");
                    }
                    wxvo.setDemurrageRateNum(contractSheetCombineVO.getTransportInfoVO().getDemurrageRateNumAsString());
                    wxvo.setLaytimeAsString(contractSheetCombineVO.getTransportInfoVO().getLaytimeAsString());


                }
                if (contractSheetCombineVO.getBuyerInfoVO() != null) {
                    wxvo.setBuyerId(String.valueOf(contractSheetCombineVO.getBuyerInfoVO().getEnterpriseId()));
                    wxvo.setBuyerEmail(contractSheetCombineVO.getBuyerInfoVO().getEmail());
                    wxvo.setBuyerFax(contractSheetCombineVO.getBuyerInfoVO().getFax());
                    wxvo.setBuyerName(contractSheetCombineVO.getBuyerInfoVO().getEnterpriseName());
                    wxvo.setBuyerAddress(contractSheetCombineVO.getBuyerInfoVO().getAddress());
                    wxvo.setBuyerTelephone(contractSheetCombineVO.getBuyerInfoVO().getTelephone());
                    wxvo.setBuyerTraderName(contractSheetCombineVO.getBuyerInfoVO().getTraderName());
                }
                if (contractSheetCombineVO.getSalerInfoVO() != null) {
                    wxvo.setSalerId(String.valueOf(contractSheetCombineVO.getSalerInfoVO().getEnterpriseId()));
                    wxvo.setSalerEmail(contractSheetCombineVO.getSalerInfoVO().getEmail());
                    wxvo.setSalerFax(contractSheetCombineVO.getSalerInfoVO().getFax());
                    wxvo.setSalerName(contractSheetCombineVO.getSalerInfoVO().getEnterpriseName());
                    wxvo.setSalerAddress(contractSheetCombineVO.getSalerInfoVO().getAddress());
                    wxvo.setSalerTelephone(contractSheetCombineVO.getSalerInfoVO().getTelephone());
                    wxvo.setSalerTraderName(contractSheetCombineVO.getSalerInfoVO().getTraderName());
                }
                resultDatas.setDatas(wxvo);
            }

        return resultDatas;
    }

    public ContractSheetWXVO getContractSheetListByuuid(String uuid) throws BizException {
        ResultDatas<ContractSheetWXVO> resultDatas = new ResultDatas<>();
        ContractSheetWXVO wxvo = null;
        ContractSheet contractSheet = contractSheetService.getContractSheetByUuid(null, uuid);//.getContractSheetListByeEpmemberid(queryvo.getBuyerId(), queryvo.getKeywords());
        ContractSheetCombine contractSheetCombine = contractSheetService.getContractSheetCombine(null, contractSheet);
        ContractSheetCombineVO contractSheetCombineVO = new ContractSheetCombineVO(contractSheetCombine);
        if (contractSheetCombineVO != null) {
            wxvo = new ContractSheetWXVO();
            wxvo.setUuid(contractSheet.getUuid());
            wxvo.setSerialNumber(contractSheetCombineVO.getContractSheetVO().getSerialNumber());
            wxvo.setContractSheetStatus(contractSheetCombineVO.getContractSheetVO().getContractSheetStatusVO().getZhName());
            if (contractSheetCombineVO.getProductInfoVO() != null) {
                wxvo.setProductCategory(contractSheetCombineVO.getProductInfoVO().getProductCategoryVO().getZhName());
                wxvo.setProductSpecification(contractSheetCombineVO.getProductInfoVO().getProductSpecificationVO().getZhName());
                String quantity = contractSheetCombineVO.getProductInfoVO().getQuantityAsString();
                if (contractSheetCombineVO.getProductInfoVO().getQuantityUnitVO() != null) {
                    quantity += contractSheetCombineVO.getProductInfoVO().getQuantityUnitVO().getZhName();
                }
                wxvo.setQuantity(quantity);
                if (contractSheetCombineVO.getProductInfoVO().getTradeTermVO() != null) {
                    wxvo.setTradeTerm(contractSheetCombineVO.getProductInfoVO().getTradeTermVO().getZhName());
                }
                String tolerance = "+/-" + contractSheetCombineVO.getProductInfoVO().getTolerance() + "%at OT";
                wxvo.setTolerance(tolerance);
                wxvo.setQualityStandard(contractSheetCombineVO.getProductInfoVO().getQualityStandard());

            }
            if (contractSheetCombineVO.getPricingInfoVO() != null) {
                if (contractSheetCombineVO.getPricingInfoVO().getPricingPeriod() != null) {
                    wxvo.setPricingPeriod(contractSheetCombineVO.getPricingInfoVO().getPricingPeriod());
                }
                wxvo.setPricingUnit(contractSheetCombineVO.getPricingInfoVO().getPricingUnitVO().getZhName());
                String pdstr = contractSheetCombineVO.getPricingInfoVO().getPremiumsAndDiscountsAsString();
                pdstr += contractSheetCombineVO.getPricingInfoVO().getCurrencyVO().getZhName() + "/" + contractSheetCombineVO.getPricingInfoVO().getPricingUnitVO().getEnName();
                wxvo.setPremiumsAndDiscounts(pdstr);
                if (contractSheetCombineVO.getPricingInfoVO().getPricingBenchmarkVO() != null) {
                    wxvo.setPricingBenchmark(contractSheetCombineVO.getPricingInfoVO().getPricingBenchmarkVO().getZhName());
                }
                if (contractSheetCombineVO.getPricingInfoVO().getPricingBenchmarkVO2() != null) {
                    wxvo.setPricingBenchmark2(contractSheetCombineVO.getPricingInfoVO().getPricingBenchmarkVO2().getZhName());
                    wxvo.setPricingFormula("(Benchmark+Benchmark2)/2+ Premium/Discount Currency/Unit");
                } else {
                    wxvo.setPricingFormula("Benchmark+ Premium/Discount Currency/Unit");
                }
                wxvo.setQuantityDter(contractSheetCombineVO.getPricingInfoVO().getSettlementQuantity());

                wxvo.setPaymentTerm(contractSheetCombineVO.getPricingInfoVO().getPaymentTerm());
                wxvo.setLaw(contractSheetCombineVO.getPricingInfoVO().getLaw());
                if (contractSheetCombineVO.getPricingInfoVO().getInspectionVO() != null) {
                    wxvo.setInspection(contractSheetCombineVO.getPricingInfoVO().getInspectionVO().getZhName());
                }
            }
            String tolerance = "+/-" + contractSheetCombineVO.getProductInfoVO().getTolerance() + "%at OT";
            wxvo.setTolerance(tolerance);
            wxvo.setTradeTermCode(contractSheetCombineVO.getProductInfoVO().getTradeTermVO().getZhName());
            if (contractSheetCombineVO.getOtherInfoVO() != null) {
                wxvo.setOtherTerm(contractSheetCombineVO.getOtherInfoVO().getOtherTerm());
            }
            if (contractSheetCombineVO.getTransportInfoVO() != null) {
                wxvo.setLaycanEndDateAsString(contractSheetCombineVO.getTransportInfoVO().getLaycanEndDateAsString());
                wxvo.setLaycanStartDateAsString(contractSheetCombineVO.getTransportInfoVO().getLaycanStartDateAsString());
                wxvo.setLaycanDateAsString(com.sinochem.crude.trade.transaction.utils.DateUtil.ENDateCH(wxvo.getLaycanStartDateAsString()) + " - " + com.sinochem.crude.trade.transaction.utils.DateUtil.ENDateCH(wxvo.getLaycanEndDateAsString()));

                if (contractSheetCombineVO.getTransportInfoVO().getTransportModeVO() != null) {
                    wxvo.setTransportMode(contractSheetCombineVO.getTransportInfoVO().getTransportModeVO().getZhName());
                }
                if (contractSheetCombineVO.getTransportInfoVO().getDischargePort() != null) {
                    wxvo.setDischargeCountry(contractSheetCombineVO.getTransportInfoVO().getDischargeCountry());
                    wxvo.setDischargePort(contractSheetCombineVO.getTransportInfoVO().getDischargePort());
                } else {
                    wxvo.setDischargeCountry("");
                    wxvo.setDischargePort("");

                }
                if (contractSheetCombineVO.getTransportInfoVO().getLoadingPort() != null) {
                    wxvo.setLoadCountry(contractSheetCombineVO.getTransportInfoVO().getLoadingCountry());
                    wxvo.setLoadPort(contractSheetCombineVO.getTransportInfoVO().getLoadingPort());
                } else {
                    wxvo.setLoadCountry("");
                    wxvo.setLoadPort("");
                }
                wxvo.setDemurrageRateNum(contractSheetCombineVO.getTransportInfoVO().getDemurrageRateNumAsString());
                wxvo.setLaytimeAsString(contractSheetCombineVO.getTransportInfoVO().getLaytimeAsString());


            }
            if (contractSheetCombineVO.getBuyerInfoVO() != null) {
                if(null!=contractSheetCombineVO.getBuyerInfoVO().getEnterpriseId())
                    wxvo.setBuyerId(String.valueOf(contractSheetCombineVO.getBuyerInfoVO().getEnterpriseId()));
                wxvo.setBuyerEmail(contractSheetCombineVO.getBuyerInfoVO().getEmail());
                wxvo.setBuyerFax(contractSheetCombineVO.getBuyerInfoVO().getFax());
                wxvo.setBuyerName(contractSheetCombineVO.getBuyerInfoVO().getEnterpriseName());
                wxvo.setBuyerAddress(contractSheetCombineVO.getBuyerInfoVO().getAddress());
                wxvo.setBuyerTelephone(contractSheetCombineVO.getBuyerInfoVO().getTelephone());
                wxvo.setBuyerTraderName(contractSheetCombineVO.getBuyerInfoVO().getTraderName());
            }
            if (contractSheetCombineVO.getSalerInfoVO() != null) {
                if(null!=contractSheetCombineVO.getSalerInfoVO().getEnterpriseId())
                    wxvo.setSalerId(String.valueOf(contractSheetCombineVO.getSalerInfoVO().getEnterpriseId()));
                wxvo.setSalerEmail(contractSheetCombineVO.getSalerInfoVO().getEmail());
                wxvo.setSalerFax(contractSheetCombineVO.getSalerInfoVO().getFax());
                wxvo.setSalerName(contractSheetCombineVO.getSalerInfoVO().getEnterpriseName());
                wxvo.setSalerAddress(contractSheetCombineVO.getSalerInfoVO().getAddress());
                wxvo.setSalerTelephone(contractSheetCombineVO.getSalerInfoVO().getTelephone());
                wxvo.setSalerTraderName(contractSheetCombineVO.getSalerInfoVO().getTraderName());
            }

        }

        return wxvo;
    }
}
