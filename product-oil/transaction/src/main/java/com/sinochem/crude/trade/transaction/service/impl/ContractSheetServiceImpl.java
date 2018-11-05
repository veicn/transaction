package com.sinochem.crude.trade.transaction.service.impl;

import java.util.*;

import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.Page;
import com.google.gson.Gson;
import com.sinochem.crude.trade.blockchain.domain.*;
import com.sinochem.crude.trade.blockchain.enums.BlockchainEventEnums;
import com.sinochem.crude.trade.blockchain.model.BlockChainEntity;
import com.sinochem.crude.trade.blockchain.service.TBlockchainEventRecordService;
import com.sinochem.crude.trade.blockchain.utils.BlockChainUtil;
import com.sinochem.crude.trade.broker.service.impl.ForwarderService;
import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.inspector.service.TInspectorAppointService;
import com.sinochem.crude.trade.shipagent.service.AppointTaskService;
import com.sinochem.crude.trade.transaction.domain.BiddingSheetCombine;
import com.sinochem.crude.trade.transaction.domain.SimplePageInfo;
import com.sinochem.crude.trade.transaction.domain.po.*;
import com.sinochem.crude.trade.transaction.enums.ContractSheetStatusEnum;
import com.sinochem.crude.trade.transaction.helper.IdentificationHelper;
import com.sinochem.crude.trade.transaction.service.*;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.constant.Mark;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.dao.ContractSheetHistoryMapper;
import com.sinochem.crude.trade.transaction.dao.ContractSheetMapper;
import com.sinochem.crude.trade.transaction.dao.OtherInfoMapper;
import com.sinochem.crude.trade.transaction.dao.PricingInfoMapper;
import com.sinochem.crude.trade.transaction.dao.ProductInfoMapper;
import com.sinochem.crude.trade.transaction.dao.StakeholderInfoMapper;
import com.sinochem.crude.trade.transaction.dao.TransportInfoMapper;
import com.sinochem.crude.trade.transaction.domain.ContractSheetCombine;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.model.query.BiddingSheetQuery;
import com.sinochem.crude.trade.transaction.model.query.ContractSheetQuery;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetQueryVO;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.page.PageUtils;

import javax.annotation.Resource;

public class ContractSheetServiceImpl implements ContractSheetService {

    @Autowired
    private ContractSheetMapper contractSheetMapper;
    @Autowired
    private PricingInfoMapper pricingInfoMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private StakeholderInfoMapper stakeholderInfoMapper;
    @Autowired
    private TransportInfoMapper transportInfoMapper;
    @Autowired
    private OtherInfoMapper otherInfoMapper;

    @Autowired
    private ContractSheetHistoryMapper contractSheetHistoryMapper;

    @Autowired
    private BiddingSheetService biddingSheetService;

    @Autowired
    private SaleSheetService saleSheetService;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private PricingInfoService pricingInfoService;

    @Autowired
    private TransportInfoService transportInfoService;

    @Autowired
    private StakeHolderInfoService stakeHolderInfoService;

    @Autowired
    private OtherInfoService otherInfoService;

    @Autowired
    private IdentificationHelper identificationHelper;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private AppointTaskService appointTaskService;
    @Autowired
    private ForwarderService forwarderService;
    @Autowired
    private TInspectorAppointService tInspectorAppointService;

    @Autowired
    private TBlockchainEventRecordService tBlockchainEventRecordService;

    @Value("${id.singapore}")
    private Long idSingapore;

    @Value("${id.quanzhou}")
    private Long idQuanzhou;


    @Value("${blockchain_gateway}")
    private String url;

    @Override
    public Long saveContractSheet(CurrentUser currentUser, ContractSheet contractSheet) throws BizException {
        return null;
    }

    @Override
    public void deleteContractSheetById(CurrentUser currentUser, Long id) throws BizException {

    }

    @Override
    public void updateContractSheet(CurrentUser currentUser, ContractSheet contractSheet) throws BizException {
        Long pricingInfoId = contractSheet.getPricingInfoId();
        PricingInfo pricingInfo = pricingInfoService.getPricingInfoById(currentUser, pricingInfoId);
        Long productInfoId = contractSheet.getProductInfoId();
        ProductInfo productInfo = productInfoService.getProductInfoById(currentUser, productInfoId);
        Long salerInfoId = contractSheet.getSalerInfoId();
        StakeholderInfo salerInfo = stakeHolderInfoService.getStakeholderInfoById(currentUser, salerInfoId);
        Long buyerInfoId = contractSheet.getBuyerInfoId();
        StakeholderInfo buyerInfo = stakeHolderInfoService.getStakeholderInfoById(currentUser, buyerInfoId);
        Long otherInfoId = contractSheet.getOtherInfoId();
        OtherInfo otherInfo = otherInfoService.getOtherInfoById(currentUser, otherInfoId);
        Long transportInfoId = contractSheet.getTransportInfoId();
        TransportInfo transportInfo = transportInfoService.getTransportInfoById(currentUser, transportInfoId);
        contractSheetMapper.updateByPrimaryKeySelective(contractSheet);
        pricingInfoMapper.updateByPrimaryKey(pricingInfo);
        productInfoMapper.updateByPrimaryKey(productInfo);
        stakeholderInfoMapper.updateByPrimaryKey(buyerInfo);
        stakeholderInfoMapper.updateByPrimaryKey(salerInfo);
        transportInfoMapper.updateByPrimaryKey(transportInfo);
        otherInfoMapper.updateByPrimaryKey(otherInfo);
    }

    @Override
    public void updateContractSheetSelective(CurrentUser currentUser, ContractSheet contractSheet) throws BizException {
    	contractSheetMapper.updateByPrimaryKeySelective(contractSheet);
    	 
    	
    }

    @Override
    public ContractSheet getContractSheetById(CurrentUser currentUser, Long id) throws BizException {

        if (id == null) {
            return null;
        }

        ContractSheet contractSheet = contractSheetMapper.selectByPrimaryKey(id);
        return contractSheet;
    }

    @Override
    public ContractSheet getContractSheetByUuid(CurrentUser currentUser, String uuid) throws BizException {

        if (StringUtil.isEmpty(uuid)) {
            return null;
        }

        ContractSheet contractSheet = contractSheetMapper.selectByUuid(uuid);
        return contractSheet;
    }

    @Override
    public PageInfoResult getContractSheetList(CurrentUser currentUser, ContractSheetQueryVO contractSheetQueryVO,
                                               PageInfo pageInfo) throws BizException {

        ContractSheetQuery contractSheetQuery = new ContractSheetQuery();

        if (contractSheetQueryVO != null) {
            contractSheetQuery = contractSheetQueryVO.getContractSheetQuery();
        }

        if (pageInfo != null) {
            PageUtils.page(pageInfo);
        }
        contractSheetQuery.setSalerId(currentUser.getEpMemberId());

        List<ContractSheet> list = contractSheetMapper.getContractSheetList(contractSheetQuery);
        return new PageInfoResult(list);
    }

    /*
    后台om根据条件查询订单
     */
   @Override
    public  Page<ContractSheet> getOMContractSheetList( ContractSheetQueryVO contractSheetQueryVO,SimplePageInfo simplePageInfo) {
        ContractSheetQuery contractSheetQuery = new ContractSheetQuery();

        if (contractSheetQueryVO != null) {
            contractSheetQuery = contractSheetQueryVO.getContractSheetQuery();
        }
        contractSheetQuery.setSalerId(contractSheetQueryVO.getEpMemberId());

        Page<ContractSheet> contractSheetList;
       PageHelper.startPage(simplePageInfo.getPageNum(), simplePageInfo.getPageSize());
        contractSheetList = contractSheetMapper.getContractSheetPage(contractSheetQuery);


        return contractSheetList;
    }

    /*
    微信专用--模糊查询（产品名称）
     */
    @Override
    public List<ContractSheet> getContractSheetListByeEpmemberid(long epMemeberid,String keywords) {
        ContractSheetQuery contractSheetQuery = new ContractSheetQuery();
        contractSheetQuery.setSalerId(epMemeberid);
        List<ContractSheet> list =new ArrayList<>();
        //模糊查询字段
       if(!StringHelper.isNullOrEmptyString(keywords)) {
           Set<String> productkey = new HashSet<String>();
           for (String key : dictionaryService.productCategoryMap().keySet()) {
               if (dictionaryService.productCategoryMap().get(key).getEnName().toLowerCase().contains(keywords.toLowerCase())) {
                   productkey.add(key);
               }
           }
           for (String key : productkey) {
               contractSheetQuery.setProductCategoryCode(key);
//               List<ContractSheet> plist = contractSheetMapper.getContractSheetListwx(contractSheetQuery);
               List<ContractSheet> plist=null;
               if (idQuanzhou.equals(epMemeberid)) {
                   contractSheetQuery.setEnterpriseIdSingapore(idSingapore);
                   plist = contractSheetMapper.getQuanzhouContractSheetListwx(contractSheetQuery);
               } else {
                   plist = contractSheetMapper.getContractSheetList(contractSheetQuery);
               }
               list.addAll(plist);
           }
       }
       else
       {
          // list = contractSheetMapper.getContractSheetList(contractSheetQuery);


           if (idQuanzhou.equals(epMemeberid)) {
               contractSheetQuery.setEnterpriseIdSingapore(idSingapore);
               list = contractSheetMapper.getQuanzhouContractSheetListwx(contractSheetQuery);
           } else {
               list = contractSheetMapper.getContractSheetList(contractSheetQuery);
           }
       }

        return  list;
    }

    @Override
    public void updateContractSheetCombineSaveHistory(CurrentUser currentUser, ContractSheetCombine contractSheetCombine) throws BizException {

    }

    @Override
    public void deleteContractSheetByIdLogical(CurrentUser currentUser, Long id) throws BizException {
        BizException bizException = new BizException();
        if (id == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        ContractSheet contractSheet = contractSheetMapper.selectByPrimaryKey(id);
        if (contractSheet == null) {
            bizException.setCode(ExceptionEnum.NO_DATA.getCode());
            throw bizException;
        }

        ContractSheet contractSheetForUpdate = new ContractSheet();
        contractSheetForUpdate.setId(id);
        contractSheetForUpdate.setAliveFlag(Mark.DELETED);
        contractSheetMapper.updateByPrimaryKeySelective(contractSheetForUpdate);

        productInfoService.deleteProductInfoByIdLogical(currentUser, contractSheet.getProductInfoId());
        pricingInfoService.deletePricingInfoByIdLogical(currentUser, contractSheet.getPricingInfoId());
        transportInfoService.deleteTransportInfoByIdLogical(currentUser, contractSheet.getTransportInfoId());
        stakeHolderInfoService.deleteStakeholderInfoByIdLogical(currentUser, contractSheet.getBuyerInfoId());
        stakeHolderInfoService.deleteStakeholderInfoByIdLogical(currentUser, contractSheet.getSalerInfoId());
        otherInfoService.deleteOtherInfoByIdLogical(currentUser, contractSheet.getOtherInfoId());
    }

    @Override
    public ContractSheet generateContractSheet(CurrentUser currentUser, SaleSheet saleSheet, BiddingSheet biddingSheet)
            throws BizException {
        BizException bizException = new BizException();
        if (saleSheet == null || biddingSheet == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        ContractSheet contractSheet = new ContractSheet();

        //插入的数据全部复制一份再进行插入
        Long productInfoId = biddingSheet.getProductInfoId();
        if (productInfoId != null) {
            ProductInfo productInfo = productInfoService.getProductInfoById(currentUser, productInfoId);
            Long copiedProductInfoId = productInfoService.saveProductInfo(currentUser, productInfo);
            contractSheet.setProductInfoId(copiedProductInfoId);
        }

        Long pricingInfoId = biddingSheet.getPricingInfoId();
        if (pricingInfoId != null) {
            PricingInfo pricingInfo = pricingInfoService.getPricingInfoById(currentUser, pricingInfoId);
            Long copiedPricingInfoId = pricingInfoService.savePricingInfo(currentUser, pricingInfo);
            contractSheet.setPricingInfoId(copiedPricingInfoId);
        }

        Long transportInfoId = biddingSheet.getTransportInfoId();
        if (transportInfoId != null) {
            TransportInfo transportInfo = transportInfoService.getTransportInfoById(currentUser, transportInfoId);
            Long copiedTransportInfoId = transportInfoService.saveTransportInfo(currentUser, transportInfo);
            contractSheet.setTransportInfoId(copiedTransportInfoId);
        }

        Long otherInfoId = biddingSheet.getOtherInfoId();
        if (otherInfoId != null) {
            OtherInfo otherInfo = otherInfoService.getOtherInfoById(currentUser, otherInfoId);
            Long copiedOtherInfoId = otherInfoService.saveOtherInfo(currentUser, otherInfo);
            contractSheet.setOtherInfoId(copiedOtherInfoId);
        }

        Long salerInfoId = saleSheet.getStakeholderInfoId();
        if (salerInfoId != null) {
            StakeholderInfo salerInfo = stakeHolderInfoService.getStakeholderInfoById(currentUser, salerInfoId);
            Long copiedSalerInfoId = stakeHolderInfoService.saveStakeholderInfo(currentUser, salerInfo);
            contractSheet.setSalerInfoId(copiedSalerInfoId);
            contractSheet.setSalerId(saleSheet.getEnterpriseId());
        }

        Long buyerInfoId = biddingSheet.getStakeholderInfoId();
        if (buyerInfoId != null) {
            StakeholderInfo buyerInfo = stakeHolderInfoService.getStakeholderInfoById(currentUser, buyerInfoId);
            Long copiedBuyerInfoId = stakeHolderInfoService.saveStakeholderInfo(currentUser, buyerInfo);
            contractSheet.setBuyerInfoId(copiedBuyerInfoId);
            contractSheet.setBuyerId(biddingSheet.getEnterpriseId());
        }

        contractSheet.setBiddingSheetId(biddingSheet.getId());
        contractSheet.setSaleSheetId(saleSheet.getId());
        contractSheet.setContractSheetStatusCode(ContractSheetStatusEnum.CONFIRMED.getCode());
        contractSheet.setConfirmedDate(new Date());
        String serialNumber=identificationHelper.generateSerialNumber();
        contractSheet.setSerialNumber(serialNumber);
        contractSheet.setUuid(identificationHelper.generateUuid());
        contractSheet.setGmtCreated(new Date());
        contractSheet.setUserCreated(currentUser.getMemberId());

        contractSheetMapper.insert(contractSheet);
        return contractSheet;
    }

    /**
     * 就先这么写吧
     */
    @Override
    public void changeContractSheetStatus(CurrentUser currentUser, ContractSheet contractSheet, String targetStatusCode)
            throws BizException {
        BizException bizException = new BizException();
        if (contractSheet == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        ContractSheet contractSheetForUpdate = new ContractSheet();
        contractSheetForUpdate.setId(contractSheet.getId());

        contractSheetForUpdate.setContractSheetStatusCode(targetStatusCode);
        contractSheetForUpdate.setCompletedDate(new Date());
        contractSheetForUpdate.setGmtModified(new Date());

        contractSheetMapper.updateByPrimaryKeySelective(contractSheetForUpdate);
    }

    @Override
    public void saveContractSheetHistory(CurrentUser currentUser, Long contractSheetId) throws BizException {
        BizException bizException = new BizException();
        if (contractSheetId == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        ContractSheet contractSheet = contractSheetMapper.selectByPrimaryKey(contractSheetId);
        contractSheet.setUuid(UUID.randomUUID().toString());
        contractSheetHistoryMapper.insert(contractSheet);
    }

    @Override
    public ContractSheetCombine getContractSheetCombine(CurrentUser currentUser, ContractSheet contractSheet) throws BizException {

        BizException bizException = new BizException();

        if (contractSheet == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        ContractSheetCombine contractSheetCombine = new ContractSheetCombine();
        contractSheetCombine.setContractSheet(contractSheet);

        BiddingSheet biddingSheet = biddingSheetService.getBiddingSheetById(currentUser, contractSheet.getBiddingSheetId());
        contractSheetCombine.setBiddingSheet(biddingSheet);

        SaleSheet saleSheet = saleSheetService.getSaleSheetById(currentUser, contractSheet.getSaleSheetId());
        contractSheetCombine.setSaleSheet(saleSheet);

        ProductInfo productInfo = productInfoService.getProductInfoById(currentUser, contractSheet.getProductInfoId());
        contractSheetCombine.setProductInfo(productInfo);

        PricingInfo pricingInfo = pricingInfoService.getPricingInfoById(currentUser, contractSheet.getPricingInfoId());
        contractSheetCombine.setPricingInfo(pricingInfo);

        TransportInfo transportInfo = transportInfoService.getTransportInfoById(currentUser, contractSheet.getTransportInfoId());
        contractSheetCombine.setTransportInfo(transportInfo);

        OtherInfo otherInfo = otherInfoService.getOtherInfoById(currentUser, contractSheet.getOtherInfoId());
        contractSheetCombine.setOtherInfo(otherInfo);

        StakeholderInfo saleInfo = stakeHolderInfoService.getStakeholderInfoById(currentUser, contractSheet.getSalerInfoId());
        contractSheetCombine.setSalerInfo(saleInfo);

        StakeholderInfo buyerInfo = stakeHolderInfoService.getStakeholderInfoById(currentUser, contractSheet.getBuyerInfoId());
        contractSheetCombine.setBuyerInfo(buyerInfo);

        //委托
        TBrokerAppoint tBrokerAppoint = forwarderService.getTBrokerAppointByDealNo(contractSheet.getSerialNumber());
        TInspectorAppoint tInspectorAppoint=tInspectorAppointService.getTInspectorAppointByDealNo(contractSheet.getSerialNumber());
        TShipagentAppoint tShipagentAppoint = appointTaskService.getTShipagentAppointByDealNo(contractSheet.getSerialNumber());
        if(null!=tBrokerAppoint)
            contractSheetCombine.settBrokerAppoint(tBrokerAppoint);
        if(null!=tInspectorAppoint)
            contractSheetCombine.settInspectorAppoint(tInspectorAppoint);
        if(null!=tShipagentAppoint)
            contractSheetCombine.settShipagentAppoint(tShipagentAppoint);

        return contractSheetCombine;
    }

    @Override
    public void generateContractSheet(CurrentUser currentUser, DemandSheet demandSheet, BiddingSheet biddingSheet) throws BizException {
        BizException bizException = new BizException();
        if (demandSheet == null || biddingSheet == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        ContractSheet contractSheet = new ContractSheet();

        //插入的数据全部复制一份再进行插入
        Long productInfoId = biddingSheet.getProductInfoId();
        if (productInfoId != null) {
            ProductInfo productInfo = productInfoService.getProductInfoById(currentUser, productInfoId);
            Long copiedProductInfoId = productInfoService.saveProductInfo(currentUser, productInfo);
            contractSheet.setProductInfoId(copiedProductInfoId);
        }

        Long pricingInfoId = biddingSheet.getPricingInfoId();
        if (pricingInfoId != null) {
            PricingInfo pricingInfo = pricingInfoService.getPricingInfoById(currentUser, pricingInfoId);
            Long copiedPricingInfoId = pricingInfoService.savePricingInfo(currentUser, pricingInfo);
            contractSheet.setPricingInfoId(copiedPricingInfoId);
        }

        Long transportInfoId = biddingSheet.getTransportInfoId();
        if (transportInfoId != null) {
            TransportInfo transportInfo = transportInfoService.getTransportInfoById(currentUser, transportInfoId);
            Long copiedTransportInfoId = transportInfoService.saveTransportInfo(currentUser, transportInfo);
            contractSheet.setTransportInfoId(copiedTransportInfoId);
        }

        Long otherInfoId = biddingSheet.getOtherInfoId();
        if (otherInfoId != null) {
            OtherInfo otherInfo = otherInfoService.getOtherInfoById(currentUser, otherInfoId);
            Long copiedOtherInfoId = otherInfoService.saveOtherInfo(currentUser, otherInfo);
            contractSheet.setOtherInfoId(copiedOtherInfoId);
        }

        Long salerInfoId = biddingSheet.getSalerInfoId();
        if (salerInfoId != null) {
            StakeholderInfo salerInfo = stakeHolderInfoService.getStakeholderInfoById(currentUser, salerInfoId);
            Long copiedSalerInfoId = stakeHolderInfoService.saveStakeholderInfo(currentUser, salerInfo);
            contractSheet.setSalerInfoId(copiedSalerInfoId);
            contractSheet.setSalerId(biddingSheet.getEnterpriseId());
        }

        Long buyerInfoId = demandSheet.getStakeholderInfoId();
        if (buyerInfoId != null) {
            StakeholderInfo buyerInfo = stakeHolderInfoService.getStakeholderInfoById(currentUser, buyerInfoId);
            Long copiedBuyerInfoId = stakeHolderInfoService.saveStakeholderInfo(currentUser, buyerInfo);
            contractSheet.setBuyerInfoId(copiedBuyerInfoId);
            contractSheet.setBuyerId(demandSheet.getEnterpriseId());
        }

        contractSheet.setBiddingSheetId(biddingSheet.getId());
        contractSheet.setSaleSheetId(demandSheet.getId());
        contractSheet.setContractSheetStatusCode(ContractSheetStatusEnum.CONFIRMED.getCode());
        contractSheet.setConfirmedDate(new Date());
        contractSheet.setSerialNumber(identificationHelper.generateSerialNumber());
        contractSheet.setUuid(identificationHelper.generateUuid());
        contractSheet.setGmtCreated(new Date());
        contractSheet.setUserCreated(currentUser.getMemberId());

        contractSheetMapper.insert(contractSheet);
    }




    @Override
    public ContractSheet selectBySerialNumber(CurrentUser currentUser, String serialNumber) throws BizException {

        if (StringUtil.isEmpty(serialNumber)) {
            return null;
        }

        ContractSheet contractSheet = contractSheetMapper.selectBySerialNumber(serialNumber);
        return contractSheet;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void contractAppiont(CurrentUser currentUser, String contractuuid, String appointType, String appointEnterpriseId, String appointEnterpriseName) throws BizException {

        ContractSheet contractSheet = this.getContractSheetByUuid(currentUser, contractuuid);



        if(appointType.equals("1")){//报关
            TBrokerAppoint tba = forwarderService.getTBrokerAppointByDealNo(contractSheet.getSerialNumber());
            TBrokerAppoint tBrokerAppoint=new TBrokerAppoint();
            //tBrokerAppoint.setAliveFlag("1");
            tBrokerAppoint.setEnterpriseId( Long.valueOf(appointEnterpriseId).longValue());
            tBrokerAppoint.setEnterpriseName(appointEnterpriseName);
            tBrokerAppoint.setUpdateDate(new Date());
            tBrokerAppoint.setUpdateUser(currentUser.getEpMemberId());
            tBrokerAppoint.setDealNo(contractSheet.getSerialNumber());
            //tBrokerAppoint.setDealUuid(contractuuid);
            tBrokerAppoint.setAppointEnterpriseId(currentUser.getEpMemberId());
            tBrokerAppoint.setAppointEnterpriseName(currentUser.getName());
            tBrokerAppoint.setStatus("1");
            forwarderService.updateByDealNo(tBrokerAppoint);

            Map<String,String> blockchainMap=new HashMap();
            blockchainMap.put("dealNo", contractSheet.getSerialNumber());
            blockchainMap.put("appointEnterpriseId",appointEnterpriseId);
            blockchainMap.put("appointEnterpriseName",appointEnterpriseName);
            Gson gson=new Gson();
            String cnt = gson.toJson(blockchainMap);
            Map<String,String> eventRecordMap=new HashMap();
            eventRecordMap.put("dealNo", contractSheet.getSerialNumber());
            eventRecordMap.put("busiId",String.valueOf(tba.getId()));
            eventRecordMap.put("postData",cnt);
            eventRecordMap.put("appopointType",appointType);
            eventRecordMap.put("createUser",String.valueOf(currentUser.getEpMemberId()));
            this.saveBlockChain(blockchainMap,eventRecordMap);


        }else if(appointType.equals("2")){//船代
            TShipagentAppoint tsa = appointTaskService.getTShipagentAppointByDealNo(contractSheet.getSerialNumber());

            TShipagentAppoint tShipagentAppoint=new TShipagentAppoint();
            //tShipagentAppoint.setAliveFlag("1");
            tShipagentAppoint.setEnterpriseId( Long.valueOf(appointEnterpriseId).longValue());
            tShipagentAppoint.setEnterpriseName(appointEnterpriseName);
            tShipagentAppoint.setUpdateDate(new Date());
            tShipagentAppoint.setUpdateUser(currentUser.getEpMemberId());
            tShipagentAppoint.setDealNo(contractSheet.getSerialNumber());
            //tShipagentAppoint.setDealUuid(contractuuid);
            tShipagentAppoint.setAppointEnterpriseId(currentUser.getEpMemberId());
            tShipagentAppoint.setAppointEnterpriseName(currentUser.getName());
            tShipagentAppoint.setStatus("1");
            appointTaskService.updateByDealNo(tShipagentAppoint);

            Map<String,String> blockchainMap=new HashMap();
            blockchainMap.put("dealNo", contractSheet.getSerialNumber());
            blockchainMap.put("appointEnterpriseId",appointEnterpriseId);
            blockchainMap.put("appointEnterpriseName",appointEnterpriseName);
            Gson gson=new Gson();
            String cnt = gson.toJson(blockchainMap);
            Map<String,String> eventRecordMap=new HashMap();
            eventRecordMap.put("dealNo", contractSheet.getSerialNumber());
            eventRecordMap.put("busiId",String.valueOf(tsa.getId()));
            eventRecordMap.put("postData",cnt);
            eventRecordMap.put("appopointType",appointType);
            eventRecordMap.put("createUser",String.valueOf(currentUser.getEpMemberId()));
            this.saveBlockChain(blockchainMap,eventRecordMap);

        }else if(appointType.equals("3")){//商检
            TInspectorAppoint tia=tInspectorAppointService.getTInspectorAppointByDealNo(contractSheet.getSerialNumber());

            TInspectorAppoint tInspectorAppoint=new TInspectorAppoint();
            //tInspectorAppoint.setAliveFlag("1");
            tInspectorAppoint.setEnterpriseId( Long.valueOf(appointEnterpriseId).longValue());
            tInspectorAppoint.setEnterpriseName(appointEnterpriseName);
            tInspectorAppoint.setUpdateDate(new Date());
            tInspectorAppoint.setUpdateUser(currentUser.getEpMemberId());
            tInspectorAppoint.setDealNo(contractSheet.getSerialNumber());
            //tInspectorAppoint.setDealUuid(contractuuid);
            tInspectorAppoint.setAppointEnterpriseId(currentUser.getEpMemberId());
            tInspectorAppoint.setAppointEnterpriseName(currentUser.getName());
            tInspectorAppoint.setStatus("1");
            tInspectorAppointService.updateByDealNo(tInspectorAppoint);


            Map<String,String> blockchainMap=new HashMap();
            blockchainMap.put("dealNo", contractSheet.getSerialNumber());
            blockchainMap.put("appointEnterpriseId",appointEnterpriseId);
            blockchainMap.put("appointEnterpriseName",appointEnterpriseName);
            Gson gson=new Gson();
            String cnt = gson.toJson(blockchainMap);
            Map<String,String> eventRecordMap=new HashMap();
            eventRecordMap.put("dealNo", contractSheet.getSerialNumber());
            eventRecordMap.put("busiId",String.valueOf(tia.getId()));
            eventRecordMap.put("postData",cnt);
            eventRecordMap.put("appopointType",appointType);
            eventRecordMap.put("createUser",String.valueOf(currentUser.getEpMemberId()));
            this.saveBlockChain(blockchainMap,eventRecordMap);
        }
    }




    public int saveBlockChain(Map<String,String> blockchainMap, Map<String,String> eventRecordMap) throws BizException {
        BizException bizException=new BizException();
        //委托-上链
        Map map=new HashMap<String,String>();
        map.put("dealNo",blockchainMap.get("dealNo"));
        map.put("appointEnterpriseId",blockchainMap.get("appointEnterpriseId"));
        map.put("appointEnterpriseName",blockchainMap.get("appointEnterpriseName"));
        BlockChainEntity entity= BlockChainUtil.insertToBlockChain(eventRecordMap.get("postData"),url);

        if (null==entity||null==entity.getCode()||!entity.getCode().equals("1")) {
            bizException.setCode(ExceptionEnum.BLOCKCHAIN_SAVE_ERROR.getCode());
            throw bizException;
        }
        TBlockchainEventRecord tBlockchainEventRecord=new TBlockchainEventRecord();
        tBlockchainEventRecord.setDealNo(eventRecordMap.get("dealNo"));
        tBlockchainEventRecord.setBusiId(Long.parseLong(eventRecordMap.get("busiId")));
        tBlockchainEventRecord.setPostData(eventRecordMap.get("postData"));
        if(eventRecordMap.get("appopointType").equals("1")) {
            tBlockchainEventRecord.setEventCode(BlockchainEventEnums.E5001.getCode());
            tBlockchainEventRecord.setDescription(BlockchainEventEnums.E5001.getZhName());
        }else if(eventRecordMap.get("appopointType").equals("2")) {
            tBlockchainEventRecord.setEventCode(BlockchainEventEnums.E5003.getCode());
            tBlockchainEventRecord.setDescription(BlockchainEventEnums.E5003.getZhName());
        }else if(eventRecordMap.get("appopointType").equals("3")) {
            tBlockchainEventRecord.setEventCode(BlockchainEventEnums.E5002.getCode());
            tBlockchainEventRecord.setDescription(BlockchainEventEnums.E5002.getZhName());
        }
        tBlockchainEventRecord.setBlockchainId(entity.getId());
        tBlockchainEventRecord.setBlockchainTxid(entity.getTxId());
        tBlockchainEventRecord.setCreateDate(new Date());
        tBlockchainEventRecord.setCreateUser(Long.parseLong(eventRecordMap.get("createUser")));

        return tBlockchainEventRecordService.saveTBlockchainEventRecord(tBlockchainEventRecord);
    }

}
