package com.sinochem.crude.trade.transaction.service.impl;


import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.constant.Mark;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.dao.DemandSheetHistoryMapper;
import com.sinochem.crude.trade.transaction.dao.DemandSheetMapper;
import com.sinochem.crude.trade.transaction.dao.SaleSheetHistoryMapper;
import com.sinochem.crude.trade.transaction.dao.SaleSheetMapper;
import com.sinochem.crude.trade.transaction.domain.DemandSheetCombine;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.*;
import com.sinochem.crude.trade.transaction.enums.*;
import com.sinochem.crude.trade.transaction.helper.IdentificationHelper;
import com.sinochem.crude.trade.transaction.helper.NotificationHelper;
import com.sinochem.crude.trade.transaction.model.query.DemandSheetQuery;
import com.sinochem.crude.trade.transaction.model.query.SaleSheetQuery;
import com.sinochem.crude.trade.transaction.model.vo.DemandSheetQueryVO;
import com.sinochem.crude.trade.transaction.model.vo.DemandSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.DemandSheetVO;
import com.sinochem.crude.trade.transaction.model.vo.ProductInfoVO;
import com.sinochem.crude.trade.transaction.model.vo.SaleSheetQueryVO;
import com.sinochem.crude.trade.transaction.service.*;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.page.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Service
public class DemandSheetServiceImpl implements DemandSheetService {

    @Autowired
    private DemandSheetMapper demandSheetMapper;

    @Autowired
    private DemandSheetHistoryMapper demandSheetHistoryMapper;

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
    private BiddingSheetService biddingSheetService;

    @Autowired
    private ContractSheetService contractSheetService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private IdentificationHelper identificationHelper;

    @Autowired
    private NotificationHelper notificationHelper;


    @Override
    public Long postDemandSheet(CurrentUser currentUser, DemandSheetCombine demandSheetCombine) throws BizException {
        BizException bizException = new BizException();

        if (currentUser == null || demandSheetCombine == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        DemandSheet demandSheet = demandSheetCombine.getDemandSheet();
        if (demandSheet == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        String demandSheetStatusCode = demandSheet.getDemandSheetStatusCode();
        if (StringUtil.isEmpty(demandSheetStatusCode)) {
            //bizException.setCode(ExceptionEnum.SALE_SHEET_STATUS_ERROR.getCode());
            throw bizException;
        }

        if (!SaleSheetStatusEnum.SAVED.getCode().equals(demandSheetStatusCode)
                && !SaleSheetStatusEnum.RELEASED.getCode().equals(demandSheetStatusCode)) {
            //bizException.setCode(ExceptionEnum.SALE_SHEET_STATUS_ERROR.getCode());
            throw bizException;
        }

        ProductInfo productInfo = demandSheetCombine.getProductInfo();
        productInfo.setDemandTypeCode(demandSheet.getDemandTypeCode());
        PricingInfo pricingInfo = demandSheetCombine.getPricingInfo();
        TransportInfo transportInfo = demandSheetCombine.getTransportInfo();

        StakeholderInfo buyerInfo = demandSheetCombine.getBuyerInfo();
        if (buyerInfo == null) {
            buyerInfo = new StakeholderInfo();
        }
        buyerInfo.setEnterpriseId(currentUser.getEpMemberId());

        OtherInfo otherInfo = demandSheetCombine.getOtherInfo();

        Long productInfoId = productInfoService.saveProductInfo(currentUser, productInfo);
        Long pricingInfoId = pricingInfoService.savePricingInfo(currentUser, pricingInfo);
        Long transportInfoId = transportInfoService.saveTransportInfo(currentUser, transportInfo);
        Long buyerInfoId = stakeHolderInfoService.saveStakeholderInfo(currentUser, buyerInfo);
        Long otherInfoId = otherInfoService.saveOtherInfo(currentUser, otherInfo);

        demandSheet.setProductInfoId(productInfoId);
        demandSheet.setPricingInfoId(pricingInfoId);
        demandSheet.setTransportInfoId(transportInfoId);
        demandSheet.setStakeholderInfoId(buyerInfoId);
        demandSheet.setOtherInfoId(otherInfoId);

        demandSheet.setGmtCreated(new Date());
        demandSheet.setUuid(identificationHelper.generateUuid());
        demandSheet.setUserCreated(currentUser.getMemberId());
        demandSheet.setEnterpriseId(currentUser.getEpMemberId());
        demandSheet.setSerialNumber(identificationHelper.generateSerialNumber());

        if (DemandSheetStatusEnum.SAVED.getCode().equals(demandSheetStatusCode)) {
            demandSheet.setSavedDate(new Date()); //保存
        } else if (DemandSheetStatusEnum.RELEASED.getCode().equals(demandSheetStatusCode)){
            demandSheet.setReleasedDate(new Date()); //发布
        }

        demandSheetMapper.insert(demandSheet);

        /*if (SaleSheetStatusEnum.RELEASED.getCode().equals(demandSheetStatusCode)) {
            String demandTypeCode = demandSheet.getDemandTypeCode();

            if (!StringUtil.isEmpty(demandTypeCode)) {
                if (SaleTypeEnum.DIRECTIONAL.getCode().equals(demandTypeCode)) {
                    notificationHelper.newDemsndSheetPublished(demandSheet);
                }
            }
        }*/
        return demandSheet.getId();
    }
    @Override
    public void changeDemandSheetStatus(CurrentUser currentUser, DemandSheet demandSheet, String targetStatusCode) throws BizException {
        BizException bizException = new BizException();

        if (demandSheet == null || targetStatusCode == null ) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        if(SaleSheetStatusEnum.CANCELLED.getCode().equals(targetStatusCode)){
            demandSheet.setCancelledDate(new Date());
            demandSheet.setDemandSheetStatusCode(SaleSheetStatusEnum.CANCELLED.getCode());
        } else if(SaleSheetStatusEnum.COMPLETED.getCode().equals(targetStatusCode)){
            demandSheet.setCompletedDate(new Date());
            demandSheet.setDemandSheetStatusCode(SaleSheetStatusEnum.COMPLETED.getCode());
        } else if(SaleSheetStatusEnum.SAVED.getCode().equals(targetStatusCode)){
            demandSheet.setSavedDate(new Date());
            demandSheet.setDemandSheetStatusCode(SaleSheetStatusEnum.SAVED.getCode());
        } else if(SaleSheetStatusEnum.RELEASED.getCode().equals(targetStatusCode)){
            demandSheet.setReleasedDate(new Date());
            demandSheet.setDemandSheetStatusCode(SaleSheetStatusEnum.RELEASED.getCode());
        }

        demandSheet.setDemandSheetStatusCode(targetStatusCode);
        // demandSheet 转换为 demandSheetCombine
        ProductInfo productInfo = productInfoService.getProductInfoById(currentUser,demandSheet.getProductInfoId());
        PricingInfo pricingInfo = pricingInfoService.getPricingInfoById(currentUser,demandSheet.getPricingInfoId());
        TransportInfo transportInfo = transportInfoService.getTransportInfoById(currentUser,demandSheet.getTransportInfoId());
        StakeholderInfo stakeholderInfo = stakeHolderInfoService.getStakeholderInfoById(currentUser,demandSheet.getStakeholderInfoId());
        OtherInfo otherInfo = otherInfoService.getOtherInfoById(currentUser,demandSheet.getOtherInfoId());
        DemandSheetCombine demandSheetCombine = new DemandSheetCombine();
        demandSheetCombine.setOtherInfo(otherInfo);
        demandSheetCombine.setPricingInfo(pricingInfo);
        demandSheetCombine.setProductInfo(productInfo);
        demandSheetCombine.setDemandSheet(demandSheet);
        demandSheetCombine.setTransportInfo(transportInfo);
        demandSheetCombine.setBuyerInfo(stakeholderInfo);
        this.updateDemandSheetCombineSaveHistory(currentUser,demandSheetCombine);
    }
    @Override
    public void saveDemandSheetHistory(CurrentUser currentUser, Long demandSheetId) throws BizException {
        BizException bizException = new BizException();

        /*if (currentUser == null || saleSheetId == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }*/
        DemandSheet demandSheet = this.getDemandSheetById(currentUser,demandSheetId);
        if (demandSheet == null) {
            bizException.setCode(ExceptionEnum.NO_DATA.getCode());
            throw bizException;
        }

        demandSheet.setUuid(UUID.randomUUID().toString());
        demandSheetHistoryMapper.insert(demandSheet);
    }

    @Override
    public DemandSheetCombine getDemandSheetCombine(CurrentUser currentUser, DemandSheet demandSheet) throws BizException {
        BizException bizException = new BizException();


        DemandSheetCombine demandSheetCombine = new DemandSheetCombine();
        demandSheetCombine.setDemandSheet(demandSheet);

        ProductInfo productInfo = productInfoService.getProductInfoById(currentUser, demandSheet.getProductInfoId());
        demandSheetCombine.setProductInfo(productInfo);

        PricingInfo pricingInfo = pricingInfoService.getPricingInfoById(currentUser, demandSheet.getPricingInfoId());
        demandSheetCombine.setPricingInfo(pricingInfo);

        TransportInfo transportInfo = transportInfoService.getTransportInfoById(currentUser, demandSheet.getTransportInfoId());
        demandSheetCombine.setTransportInfo(transportInfo);

        StakeholderInfo stakeholderInfo = stakeHolderInfoService.getStakeholderInfoById(currentUser, demandSheet.getStakeholderInfoId());
        demandSheetCombine.setBuyerInfo(stakeholderInfo);

        OtherInfo otherInfo = otherInfoService.getOtherInfoById(currentUser, demandSheet.getOtherInfoId());
        demandSheetCombine.setOtherInfo(otherInfo);

        return demandSheetCombine;
    }


    @Override
    public DemandSheet getDemandSheetById(CurrentUser currentUser, Long id) throws BizException {

        if (id == null) {
            return null;
        }

        DemandSheet demandSheet = demandSheetMapper.selectByPrimaryKey(id);
        return demandSheet;
    }

    @Override
    public DemandSheet getDemandSheetByUuid(CurrentUser currentUser, String uuid) throws BizException {

        if (StringUtil.isEmpty(uuid)) {
            return null;
        }

        DemandSheet demandSheet = demandSheetMapper.selectByUuid(uuid);
        return demandSheet;
    }

    @Override
    public void updateDemandSheetCombineSaveHistory(CurrentUser currentUser, DemandSheetCombine demandSheetCombine) throws BizException {
        BizException bizException = new BizException();

        if (demandSheetCombine == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        DemandSheet demandSheet = demandSheetCombine.getDemandSheet();
        if (demandSheet == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        Long demandSheetId = demandSheet.getId();
        String demandSheetUuid = demandSheet.getUuid();
        DemandSheet existedDemandSheet;

        if (demandSheetId != null) {
            existedDemandSheet = demandSheetMapper.selectByPrimaryKey(demandSheetId);
        } else if (!StringUtil.isEmpty(demandSheetUuid)) {
            existedDemandSheet = demandSheetMapper.selectByUuid(demandSheetUuid);
        } else {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        if (existedDemandSheet == null) {
            bizException.setCode(ExceptionEnum.NO_DATA.getCode());
            throw bizException;
        }

        demandSheet.setId(existedDemandSheet.getId());

        /*将原有信息存入历史表*/
        this.saveDemandSheetHistory(currentUser, existedDemandSheet.getId());

        /*更新全部信息*/
        ProductInfo productInfo = demandSheetCombine.getProductInfo();
        PricingInfo pricingInfo = demandSheetCombine.getPricingInfo();
        TransportInfo transportInfo = demandSheetCombine.getTransportInfo();
        StakeholderInfo buyerInfo = demandSheetCombine.getBuyerInfo();
        OtherInfo otherInfo = demandSheetCombine.getOtherInfo();

        Long productInfoId = productInfoService.saveProductInfo(currentUser, productInfo);
        Long pricingInfoId = pricingInfoService.savePricingInfo(currentUser, pricingInfo);
        Long transportInfoId = transportInfoService.saveTransportInfo(currentUser, transportInfo);
        Long stakeholderInfoId = stakeHolderInfoService.saveStakeholderInfo(currentUser, buyerInfo);
        Long otherInfoId = otherInfoService.saveOtherInfo(currentUser, otherInfo);

        demandSheet.setProductInfoId(productInfoId);
        demandSheet.setPricingInfoId(pricingInfoId);
        demandSheet.setTransportInfoId(transportInfoId);
        demandSheet.setStakeholderInfoId(stakeholderInfoId);
        demandSheet.setOtherInfoId(otherInfoId);

        demandSheet.setGmtModified(new Date());
        demandSheet.setUserModified(demandSheet.getUserCreated());

        demandSheetMapper.updateByPrimaryKeySelective(demandSheet);
    }

    @Override
    public PageInfoResult getDemandSheetList(CurrentUser currentUser, DemandSheetQueryVO demandSheetQueryVO, PageInfo pageInfo) throws BizException {
        BizException bizException = new BizException();
        if (currentUser == null || demandSheetQueryVO == null ) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        DemandSheetQuery demandSheetQuery = new DemandSheetQuery();
        if(demandSheetQueryVO != null){
            demandSheetQuery = demandSheetQueryVO.getDemandSheetQuery();
        }

        if (pageInfo != null) {
            PageUtils.page(pageInfo);
        }
        /*String serialNumber = demandSheetQuery.getSerialNumber();
        if(serialNumber != null && !StringUtil.isEmpty(serialNumber)){
            demandSheetQuery.setSerialNumber("%"+serialNumber+"%");
        }*/

        List<DemandSheet> demandSheetList = demandSheetMapper.getDemandSheetList(demandSheetQuery);
        return new PageInfoResult<DemandSheet>(demandSheetList);
    }

    @Override
    public void demandShelfOrOffShelf(CurrentUser currentUser, String uuid , String code) throws BizException {
        BizException bizException = new BizException();
        if (currentUser == null || StringUtil.isEmpty(uuid) || StringUtil.isEmpty(code)) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        DemandSheet demandSheet = this.getDemandSheetByUuid(currentUser, uuid);

        //如果是下架与此销售单对应的报价单改为作废
        if(code.equals(DemandSheetStatusEnum.CANCELLED.getCode()) || code.equals(DemandSheetStatusEnum.SAVED.getCode())){
            List<BiddingSheet> biddingSheets = biddingSheetService.selectByDemandSheetId(demandSheet.getId());
            if(!biddingSheets.isEmpty()){
                for(BiddingSheet biddingSheet:biddingSheets) {
                    biddingSheetService.changeBiddingSheetStatus(currentUser,biddingSheet,BiddingSheetStatusEnum.CANCELLED.getCode());
                }
            }
        }
        this.changeDemandSheetStatus(currentUser,demandSheet,code);
    }

    @Override
    public void deleteDemandSheetByIdLogical(CurrentUser currentUser, String uuid) throws BizException {
        BizException bizException = new BizException();

        if (currentUser == null || StringUtil.isEmpty(uuid)) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode()); //使用ExceptionEnum来枚举异常的code
            throw bizException;
        }

        DemandSheet demandSheet = demandSheetMapper.selectByUuid(uuid);
        if (demandSheet == null) {
            bizException.setCode(ExceptionEnum.NO_DATA.getCode());
            throw bizException;
        }

        DemandSheet demandSheetForUpdate = new DemandSheet();
        demandSheetForUpdate.setId(demandSheet.getId());
        demandSheetForUpdate.setDemandSheetStatusCode(DemandSheetStatusEnum.CANCELLED.getCode());
        demandSheetForUpdate.setCancelledDate(new Date());
        demandSheetForUpdate.setAliveFlag(Mark.DELETED);
        demandSheetMapper.updateByPrimaryKeySelective(demandSheetForUpdate);

        productInfoService.deleteProductInfoByIdLogical(currentUser, demandSheet.getProductInfoId());
        pricingInfoService.deletePricingInfoByIdLogical(currentUser, demandSheet.getPricingInfoId());
        transportInfoService.deleteTransportInfoByIdLogical(currentUser, demandSheet.getTransportInfoId());
        stakeHolderInfoService.deleteStakeholderInfoByIdLogical(currentUser, demandSheet.getStakeholderInfoId());
        otherInfoService.deleteOtherInfoByIdLogical(currentUser, demandSheet.getOtherInfoId());
    }


    /**
     * 取消采购单的发布 -武刚鹏 -2018年5月21日14:43:30
     * @param user
     * @param demandUuid
     * @throws BizException
     */
    @Override
    public void cancelDemand(CurrentUser user, String demandUuid)throws BizException {
        if (user == null || StringUtil.isEmpty(demandUuid) || StringUtil.isEmpty(demandUuid)) {
            BizException bizException = new BizException();
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        //1、修改采购单的状态为关闭
        DemandSheet demandSheet = demandSheetMapper.selectByUuid(demandUuid);
        this.changeDemandSheetStatus(user,demandSheet,DemandSheetStatusEnum.CANCELLED.getCode());
        //2、查找采购单下的报价单列表
        List<BiddingSheet> biddingSheetList = biddingSheetService.selectByDemendId(demandSheet.getId());
        for (BiddingSheet bidding:biddingSheetList) {
            biddingSheetService.changeBiddingSheetStatus(user,bidding,BiddingSheetStatusEnum.CANCELLED.getCode());
        }
    }


    /**获取采购单基本详情
     * @param uuid
     * @return
     */
    @Override
    public DemandSheetCombineVO findDemandDetails(CurrentUser user, String uuid) throws BizException {
        DemandSheet demandSheet = demandSheetMapper.selectByUuid(uuid);
        DemandSheetCombine dsc = this.getDemandSheetCombine(user, demandSheet);
        return new DemandSheetCombineVO(dsc);
    }


    /**
     * 接受报价 武刚鹏 -2018年5月21日13:40:40
     * @param user
     * @param biddingUuid
     * @return
     * @throws BizException
     */
    @Override
    public Boolean acceptBidding(CurrentUser user,String biddingUuid)throws BizException {

//        更新报价单的状态 为 won/loss
        BiddingSheet biddingSheet =  biddingSheetService.getBiddingSheetByUuid(user,biddingUuid);
        biddingSheet.setBiddingSheetStatusCode(BiddingSheetStatusEnum.WON.getCode());
        //更新报价表的状态
        biddingSheetService.changeBiddingSheetStatus(user,biddingSheet, BiddingSheetStatusEnum.WON.getCode());

        List<BiddingSheet> biddingList = biddingSheetService.selectByDemendId(biddingSheet.getDemandSheetId());
        for (BiddingSheet bidding:biddingList) {
            if(!biddingUuid.equals(bidding.getUuid())){
                biddingSheetService.changeBiddingSheetStatus(user,bidding,BiddingSheetStatusEnum.LOST.getCode());
            }
        }
        //更新采购单的状态为 completed
        DemandSheet demandSheet = demandSheetMapper.selectByPrimaryKey(biddingSheet.getDemandSheetId());
        String completedCode = DemandSheetStatusEnum.COMPLETED.getCode();
        demandSheet.setDemandSheetStatusCode(completedCode);
        this.changeDemandSheetStatus(user,demandSheet,completedCode);
        //生成合约单
        contractSheetService.generateContractSheet(user,demandSheet,biddingSheet);
        return true;
    }


    @Override
    public PageInfoResult getVisibleDemandSheetList(CurrentUser currentUser, DemandSheetQueryVO demandSheetQueryVO, PageInfo pageInfo) throws BizException {
        BizException bizException = new BizException();

        if (currentUser == null || demandSheetQueryVO == null ) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        DemandSheetQuery demandSheetQuery = new DemandSheetQuery();
        if(demandSheetQueryVO != null){
            demandSheetQuery = demandSheetQueryVO.getDemandSheetQuery();
        }

        demandSheetQuery.setQueryEnterpriseId(currentUser.getEpMemberId());

        if (pageInfo != null) {
            PageUtils.page(pageInfo);
        }

        List<DemandSheet> demandSheetList = demandSheetMapper.getVisibleDemandSheetList(demandSheetQuery);
        return new PageInfoResult<DemandSheet>(demandSheetList);
    }

}
