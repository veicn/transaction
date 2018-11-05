package com.sinochem.crude.trade.transaction.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.transaction.domain.po.*;
import com.sinochem.crude.trade.transaction.enums.BiddingSheetStatusEnum;
import com.sinochem.crude.trade.transaction.helper.IdentificationHelper;
import com.sinochem.crude.trade.transaction.helper.NotificationHelper;
import com.sinochem.crude.trade.transaction.model.vo.BiddingSheetCombineVO;
import com.sinochem.crude.trade.transaction.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.constant.Mark;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.dao.BiddingSheetHistoryMapper;
import com.sinochem.crude.trade.transaction.dao.BiddingSheetMapper;
import com.sinochem.crude.trade.transaction.domain.BiddingSheetCombine;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.model.query.BiddingSheetQuery;
import com.sinochem.crude.trade.transaction.model.vo.BiddingSheetQueryVO;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.page.PageUtils;

import javax.annotation.Resource;

public class BiddingSheetServiceImpl implements BiddingSheetService {

    @Autowired
    private BiddingSheetHistoryMapper biddingSheetHistoryMapper;

    @Autowired
    private BiddingSheetMapper biddingSheetMapper;

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
    private SaleSheetService saleSheetService;


    @Autowired
    private DemandSheetService demandSheetService;


    @Autowired
    private IdentificationHelper identificationHelper;

    @Autowired
    private NotificationHelper notificationHelper;

    @Override
    @Deprecated
    public Long saveBiddingSheet(CurrentUser currentUser, BiddingSheet biddingSheet) throws BizException {
        return null;
    }

    @Override
    @Deprecated
    public void deleteBiddingSheetById(CurrentUser currentUser, Long id) throws BizException {

    }

    @Override
    @Deprecated
    public void updateBiddingSheet(CurrentUser currentUser, BiddingSheet biddingSheet) throws BizException {

    }

    @Override
    @Deprecated
    public void updateBiddingSheetSelective(CurrentUser currentUser, BiddingSheet biddingSheet) throws BizException {

    }

    @Override
    public BiddingSheet getBiddingSheetById(CurrentUser currentUser, Long id) throws BizException {

        if (id == null) {
            return null;
        }

        BiddingSheet biddingSheet = biddingSheetMapper.selectByPrimaryKey(id);
        return biddingSheet;
    }

    @Override
    public BiddingSheet getBiddingSheetByUuid(CurrentUser currentUser, String uuid) throws BizException {

        if (StringUtil.isEmpty(uuid)) {
            return null;
        }

        BiddingSheet biddingSheet = biddingSheetMapper.selectByUuid(uuid);
        return biddingSheet;
    }

    @Override
    public void deleteBiddingSheetByIdLogical(CurrentUser currentUser, Long id) throws BizException {
        BizException bizException = new BizException();
        if (currentUser == null || id == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        BiddingSheet biddingSheet = biddingSheetMapper.selectByPrimaryKey(id);
        if (biddingSheet == null) {
            bizException.setCode(ExceptionEnum.NO_DATA.getCode());
            throw bizException;
        }

        BiddingSheet biddingSheetForUpdate = new BiddingSheet();
        biddingSheetForUpdate.setId(id);
        biddingSheetForUpdate.setAliveFlag(Mark.DELETED);
        biddingSheetMapper.updateByPrimaryKeySelective(biddingSheetForUpdate);

        productInfoService.deleteProductInfoByIdLogical(currentUser, biddingSheet.getProductInfoId());
        pricingInfoService.deletePricingInfoByIdLogical(currentUser, biddingSheet.getPricingInfoId());
        transportInfoService.deleteTransportInfoByIdLogical(currentUser, biddingSheet.getTransportInfoId());
        stakeHolderInfoService.deleteStakeholderInfoByIdLogical(currentUser, biddingSheet.getStakeholderInfoId());
        otherInfoService.deleteOtherInfoByIdLogical(currentUser, biddingSheet.getOtherInfoId());
    }

    @Override
    public void updateBiddingSheetCombineSaveHistory(CurrentUser currentUser, BiddingSheetCombine biddingSheetCombine) throws BizException {
        BizException bizException = new BizException();
        if ( biddingSheetCombine == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        BiddingSheet biddingSheet = biddingSheetCombine.getBiddingSheet();
        if (biddingSheet == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        Long biddingSheetId = biddingSheet.getId();
        if (biddingSheetId == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        BiddingSheet existedBiddingSheet = biddingSheetMapper.selectByPrimaryKey(biddingSheetId);
        if (existedBiddingSheet == null) {
            bizException.setCode(ExceptionEnum.NO_DATA.getCode());
            throw bizException;
        }

           /*将原有信息存入历史表*/
        this.saveBiddingSheetHistory(currentUser, existedBiddingSheet.getId());
		   /*更新全部信息*/
        ProductInfo productInfo = biddingSheetCombine.getProductInfo();
        PricingInfo pricingInfo = biddingSheetCombine.getPricingInfo();
        TransportInfo transportInfo = biddingSheetCombine.getTransportInfo();
        StakeholderInfo salerInfo;
        if(null!=biddingSheetCombine.getBiddingSheet().getBiddingFlag()&&"1".equals(biddingSheetCombine.getBiddingSheet().getBiddingFlag()))
            salerInfo = biddingSheetCombine.getBuyerInfo();
        else
            salerInfo = biddingSheetCombine.getSalerInfo();
        OtherInfo otherInfo = biddingSheetCombine.getOtherInfo();

        Long productInfoId = productInfoService.saveProductInfo(currentUser, productInfo);
        Long pricingInfoId = pricingInfoService.savePricingInfo(currentUser, pricingInfo);
        Long transportInfoId = transportInfoService.saveTransportInfo(currentUser, transportInfo);
        Long stakeholderInfoId = stakeHolderInfoService.saveStakeholderInfo(currentUser, salerInfo);
        Long otherInfoId = otherInfoService.saveOtherInfo(currentUser, otherInfo);

        biddingSheet.setProductInfoId(productInfoId);
        biddingSheet.setPricingInfoId(pricingInfoId);
        biddingSheet.setTransportInfoId(transportInfoId);
        if(null!=biddingSheetCombine.getBiddingSheet().getBiddingFlag()&&"1".equals(biddingSheetCombine.getBiddingSheet().getBiddingFlag()))
            biddingSheet.setStakeholderInfoId(stakeholderInfoId);
        else
            biddingSheet.setSalerInfoId(stakeholderInfoId);
        biddingSheet.setOtherInfoId(otherInfoId);

        biddingSheet.setGmtModified(new Date());
        biddingSheet.setUserModified(biddingSheet.getUserCreated());

        biddingSheetMapper.updateByPrimaryKey(biddingSheet);
    }

    @Override
    public PageInfoResult getBiddingSheetList(CurrentUser currentUser, BiddingSheetQueryVO biddingSheetQueryVO,
                                              PageInfo pageInfo) throws BizException {

        PageUtils.page(pageInfo);
        BiddingSheetQuery biddingSheetQuery = new BiddingSheetQuery();

        //saleSheetQueryVO  转 saleSheetQuery 方法转换
        if(biddingSheetQueryVO != null){

            biddingSheetQuery = biddingSheetQueryVO.getBiddingSheetQuery();
        }

        //查询时，按照所属企业进行查询，即假如一个公司有两个业务员都发了投标单，这两个业务员是能看到互相发的单的
        biddingSheetQuery.setEnterpriseId(currentUser.getEpMemberId());

        List<BiddingSheet> biddingSheetList = biddingSheetMapper.getBiddingSheetList(biddingSheetQuery);
        return new PageInfoResult(biddingSheetList);
    }

    @Override
    public Long postBiddingSheet(CurrentUser currentUser, BiddingSheetCombine biddingSheetCombine) throws BizException {
        BizException bizException = new BizException();
        if (currentUser == null || biddingSheetCombine == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        SaleSheet saleSheet = saleSheetService.getSaleSheetById(currentUser, biddingSheetCombine.getBiddingSheet().getSaleSheetId());
        if (saleSheet == null) {
            bizException.setCode(ExceptionEnum.NO_DATA.getCode());
            throw bizException;
        }

        ProductInfo productInfo = biddingSheetCombine.getProductInfo();
        if (productInfo != null) {
            productInfo.setSaleTypeCode(saleSheet.getSaleTypeCode());
        }
        PricingInfo pricingInfo = biddingSheetCombine.getPricingInfo();
        TransportInfo transportInfo = biddingSheetCombine.getTransportInfo();

        StakeholderInfo buyerInfo = biddingSheetCombine.getBuyerInfo();
        if (buyerInfo == null) {
            buyerInfo = new StakeholderInfo();
        }
        buyerInfo.setEnterpriseId(currentUser.getEpMemberId());

        OtherInfo otherInfo = biddingSheetCombine.getOtherInfo();

/*        Long productInfoId = productInfoService.saveAdditionalProductInfo(currentUser, saleSheet.getProductInfoId(), productInfo);
        Long pricingInfoId = pricingInfoService.saveAdditionalPricingInfo(currentUser, saleSheet.getPricingInfoId(), pricingInfo);
        Long transportInfoId = transportInfoService.saveAdditionalTransportInfo(currentUser, saleSheet.getTransportInfoId(), transportInfo);
        Long salerInfoId = stakeHolderInfoService.saveStakeholderInfo(currentUser, buyerInfo);
        Long otherInfoId = otherInfoService.saveAdditionalOtherInfo(currentUser, saleSheet.getOtherInfoId(), otherInfo);*/

        Long productInfoId = productInfoService.saveAdditionalProductInfo(currentUser, null, productInfo);
        Long pricingInfoId = pricingInfoService.saveAdditionalPricingInfo(currentUser, null, pricingInfo);
        Long transportInfoId = transportInfoService.saveAdditionalTransportInfo(currentUser, null, transportInfo);
        Long salerInfoId = stakeHolderInfoService.saveStakeholderInfo(currentUser, buyerInfo);
        Long otherInfoId = otherInfoService.saveAdditionalOtherInfo(currentUser, null, otherInfo);


        BiddingSheet biddingSheet = biddingSheetCombine.getBiddingSheet();
        biddingSheet.setSaleSheetUuid(saleSheet.getUuid());
        biddingSheet.setProductInfoId(productInfoId);
        biddingSheet.setPricingInfoId(pricingInfoId);
        biddingSheet.setTransportInfoId(transportInfoId);
        biddingSheet.setStakeholderInfoId(salerInfoId);
        biddingSheet.setOtherInfoId(otherInfoId);

        biddingSheet.setGmtCreated(new Date());
        biddingSheet.setReleasedDate(new Date());
        biddingSheet.setUserCreated(currentUser.getMemberId());
        biddingSheet.setEnterpriseId(currentUser.getEpMemberId());
        biddingSheet.setUuid(identificationHelper.generateUuid());
        biddingSheet.setSerialNumber(identificationHelper.generateSerialNumber());
        biddingSheet.setBiddingFlag("1");
        biddingSheetMapper.insert(biddingSheet);

        notificationHelper.newBiddingSheet(biddingSheet);

        return biddingSheet.getId();
    }


    @Override
    public Long postBiddingDemandSheet(CurrentUser currentUser, BiddingSheetCombine biddingSheetCombine) throws BizException {
        BizException bizException = new BizException();
        if (currentUser == null || biddingSheetCombine == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        DemandSheet demandSheet = demandSheetService.getDemandSheetById(currentUser, biddingSheetCombine.getBiddingSheet().getDemandSheetId());
        if (demandSheet == null) {
            bizException.setCode(ExceptionEnum.NO_DATA.getCode());
            throw bizException;
        }
        ProductInfo productInfo = biddingSheetCombine.getProductInfo();
        if (productInfo != null) {
            productInfo.setDemandTypeCode(demandSheet.getDemandTypeCode());
        }
        PricingInfo pricingInfo = biddingSheetCombine.getPricingInfo();
        TransportInfo transportInfo = biddingSheetCombine.getTransportInfo();

        StakeholderInfo sellerInfo = biddingSheetCombine.getSalerInfo();
        if (sellerInfo == null) {
            sellerInfo = new StakeholderInfo();
        }
        sellerInfo.setEnterpriseId(currentUser.getEpMemberId());

        OtherInfo otherInfo = biddingSheetCombine.getOtherInfo();

        Long productInfoId = productInfoService.saveAdditionalProductInfo(currentUser, null, productInfo);
        Long pricingInfoId = pricingInfoService.saveAdditionalPricingInfo(currentUser, null, pricingInfo);
        Long transportInfoId = transportInfoService.saveAdditionalTransportInfo(currentUser, null, transportInfo);
        Long salerInfoId = stakeHolderInfoService.saveStakeholderInfo(currentUser, sellerInfo);
        Long otherInfoId = otherInfoService.saveAdditionalOtherInfo(currentUser, null, otherInfo);

        BiddingSheet biddingSheet = biddingSheetCombine.getBiddingSheet();
        biddingSheet.setDemandSheetUuid(demandSheet.getUuid());
        biddingSheet.setProductInfoId(productInfoId);
        biddingSheet.setPricingInfoId(pricingInfoId);
        biddingSheet.setTransportInfoId(transportInfoId);
        biddingSheet.setSalerInfoId(salerInfoId);
        biddingSheet.setOtherInfoId(otherInfoId);

        biddingSheet.setGmtCreated(new Date());
        biddingSheet.setReleasedDate(new Date());
        biddingSheet.setUserCreated(currentUser.getMemberId());
        biddingSheet.setEnterpriseId(currentUser.getEpMemberId());
        biddingSheet.setUuid(identificationHelper.generateUuid());
        biddingSheet.setSerialNumber(identificationHelper.generateSerialNumber());
        biddingSheet.setBiddingFlag("2");
        biddingSheetMapper.insert(biddingSheet);

        notificationHelper.newBiddingSheet(biddingSheet);

        return biddingSheet.getId();
    }

    @Override
    public void changeBiddingSheetStatus(CurrentUser currentUser, BiddingSheet biddingSheet, String targetStatusCode)
            throws BizException {
        BizException bizException = new BizException();
        if (biddingSheet == null || targetStatusCode == null || StringUtil.isEmpty(targetStatusCode)) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        BiddingSheetCombine biddingSheetCombine = new BiddingSheetCombine();
        if (BiddingSheetStatusEnum.LOST.getCode().equals(targetStatusCode)) {
            biddingSheet.setLostDate(new Date());
            biddingSheet.setBiddingSheetStatusCode(BiddingSheetStatusEnum.LOST.getCode());
        }
        if (BiddingSheetStatusEnum.CANCELLED.getCode().equals(targetStatusCode)) {
            biddingSheet.setCancelledDate(new Date());
            biddingSheet.setBiddingSheetStatusCode(BiddingSheetStatusEnum.CANCELLED.getCode());
        }
        if (BiddingSheetStatusEnum.WON.getCode().equals(targetStatusCode)) {
            biddingSheet.setWonDate(new Date());
            biddingSheet.setBiddingSheetStatusCode(BiddingSheetStatusEnum.WON.getCode());
        }
        ProductInfo productInfo = productInfoService.getProductInfoById(currentUser, biddingSheet.getProductInfoId());
        PricingInfo pricingInfo = pricingInfoService.getPricingInfoById(currentUser, biddingSheet.getPricingInfoId());
        TransportInfo transportInfo = transportInfoService.getTransportInfoById(currentUser, biddingSheet.getTransportInfoId());
        StakeholderInfo stakeholderInfo = stakeHolderInfoService.getStakeholderInfoById(currentUser, biddingSheet.getStakeholderInfoId());
        StakeholderInfo stakeholderInfo2 = stakeHolderInfoService.getStakeholderInfoById(currentUser, biddingSheet.getSalerInfoId());
        OtherInfo otherInfo = otherInfoService.getOtherInfoById(currentUser, biddingSheet.getOtherInfoId());

        biddingSheetCombine.setOtherInfo(otherInfo);
        biddingSheetCombine.setPricingInfo(pricingInfo);
        biddingSheetCombine.setProductInfo(productInfo);
        //biddingSheetCombine.setBiddingSheet(biddingSheet);
        biddingSheetCombine.setTransportInfo(transportInfo);
        biddingSheetCombine.setBuyerInfo(stakeholderInfo);
        biddingSheetCombine.setSalerInfo(stakeholderInfo2);
        biddingSheetCombine.setBiddingSheet(biddingSheet);
        this.updateBiddingSheetCombineSaveHistory(currentUser, biddingSheetCombine);
    }

    @Override
    public void saveBiddingSheetHistory(CurrentUser currentUser, Long biddingSheetId) throws BizException {
        BizException bizException = new BizException();
        if (biddingSheetId == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        BiddingSheet biddingSheet = biddingSheetMapper.selectByPrimaryKey(biddingSheetId);
        biddingSheet.setGmtCreated(new Date());
        biddingSheet.setUuid(UUID.randomUUID().toString());
        biddingSheetHistoryMapper.insert(biddingSheet);
    }

    @Override
    public List<BiddingSheet> selectBySaleSheetId(Long saleSheetId) {
        List<BiddingSheet> biddingSheets = biddingSheetMapper.selectBySaleSheetId(saleSheetId);
        return biddingSheets;
    }

    @Override
    public List<BiddingSheet> selectByDemandSheetId(Long demandSheetId) {
        List<BiddingSheet> biddingSheets = biddingSheetMapper.selectByDemandSheetId(demandSheetId);
        return biddingSheets;
    }

    @Override
    public List<BiddingSheet> selectHistoryBySaleSheetId(CurrentUser currentUser, BiddingSheetQuery biddingSheetQuery) {
        List<BiddingSheet> biddingSheets = biddingSheetMapper.selectHistoryBySaleSheetId(biddingSheetQuery);
        return biddingSheets;
    }

    @Override
    public List<BiddingSheet> selectHistoryByDemandSheetId(CurrentUser currentUser, BiddingSheetQuery biddingSheetQuery) {
        List<BiddingSheet> biddingSheets = biddingSheetMapper.selectHistoryByDemandSheetId(biddingSheetQuery);
        return biddingSheets;
    }

    @Override
    public List<BiddingSheet> selectBySaleSheetIdAndCurrentUser(CurrentUser currentUser, Long saleSheetId) {
        BiddingSheetQuery biddingSheetQuery = new BiddingSheetQuery();
        if(saleSheetId != null){
            biddingSheetQuery.setSaleSheetId(saleSheetId);
        }
        if(currentUser != null && currentUser.getEpMemberId() != null){
            biddingSheetQuery.setEnterpriseId(currentUser.getEpMemberId());
        }
        List<BiddingSheet> biddingSheets = biddingSheetMapper.selectBySaleSheetIdAndCurrentUser(biddingSheetQuery);
        return biddingSheets;
    }

    @Override
    public List<BiddingSheet> selectBySaleSheetIdForMe(CurrentUser currentUser, Long saleSheetId) {
        BiddingSheetQuery biddingSheetQuery = new BiddingSheetQuery();
        if(saleSheetId != null){
            biddingSheetQuery.setSaleSheetId(saleSheetId);
        }
        if(currentUser != null && currentUser.getEpMemberId() != null){
            biddingSheetQuery.setEnterpriseId(currentUser.getEpMemberId());
        }
        List<BiddingSheet> biddingSheets = biddingSheetMapper.selectBySaleSheetIdForMe(biddingSheetQuery);
        return biddingSheets;
    }

    @Override
    public BiddingSheetCombine getBiddingSheetCombine(CurrentUser currentUser, BiddingSheet biddingSheet) throws BizException {
        BizException bizException = new BizException();
        if (biddingSheet == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        BiddingSheetCombine biddingSheetCombine = new BiddingSheetCombine();
        biddingSheetCombine.setBiddingSheet(biddingSheet);

        ProductInfo productInfo = productInfoService.getProductInfoById(currentUser, biddingSheet.getProductInfoId());
        biddingSheetCombine.setProductInfo(productInfo);

        PricingInfo pricingInfo = pricingInfoService.getPricingInfoById(currentUser, biddingSheet.getPricingInfoId());
        biddingSheetCombine.setPricingInfo(pricingInfo);

        TransportInfo transportInfo = transportInfoService.getTransportInfoById(currentUser, biddingSheet.getTransportInfoId());
        biddingSheetCombine.setTransportInfo(transportInfo);

        StakeholderInfo stakeholderInfo = stakeHolderInfoService.getStakeholderInfoById(currentUser, biddingSheet.getStakeholderInfoId());
        biddingSheetCombine.setBuyerInfo(stakeholderInfo);

        StakeholderInfo salerInfo = stakeHolderInfoService.getStakeholderInfoById(currentUser, biddingSheet.getSalerInfoId());
        biddingSheetCombine.setSalerInfo(salerInfo);

        OtherInfo otherInfo = otherInfoService.getOtherInfoById(currentUser, biddingSheet.getOtherInfoId());
        biddingSheetCombine.setOtherInfo(otherInfo);

        return biddingSheetCombine;
    }

    @Override
    public List<BiddingSheet> selectHistoryBidding(CurrentUser currentUser, BiddingSheetQuery biddingSheetQuery) throws BizException{
        BizException bizException = new BizException();
        if (biddingSheetQuery == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }
        List<BiddingSheet> biddingSheets = biddingSheetMapper.selectHistoryBidding(biddingSheetQuery);
        return biddingSheets;
    }

    /**
     * 根据采购单主键ID查询报价单列表
     * @param demendId
     * @return
     */
    @Override
    public List<BiddingSheet> selectByDemendId(Long demendId) {
        List<BiddingSheet> biddingSheets = biddingSheetMapper.selectByDemandId(demendId);
        return biddingSheets;
    }


    /**
     * 根据报价单uuid查询报价单的组合信息 武刚鹏
     * @param biddingUuid
     * @return
     */
    @Override
    public BiddingSheetCombineVO findBiddingSheetCombineVO(CurrentUser user,String biddingUuid)throws BizException {
        BiddingSheet biddingSheet = biddingSheetMapper.selectByUuid(biddingUuid);
        BiddingSheetCombine bscb = this.getBiddingSheetCombine(user,biddingSheet);
        BiddingSheetCombineVO bscbVO = new BiddingSheetCombineVO(bscb);
        return bscbVO;
    }

    /**
     * 查询某个报价公司对某个采购单的报价历史
     * @param currentUser
     * @param biddingSheetQuery
     * @return
     * @throws BizException
     */
    @Override
    public List<BiddingSheet> selectHistoryDemandBidding(CurrentUser currentUser, BiddingSheetQuery biddingSheetQuery) throws BizException {
        return biddingSheetMapper.selectHistoryDemandBidding(biddingSheetQuery);
    }

    /**
     *查询当前用户的报价单
     * @param currentUser
     * @param uuid
     * @return
     * @throws BizException
     */
    @Override
    public BiddingSheet getBiddingSheetByUser(CurrentUser currentUser, String uuid) throws BizException {

        BiddingSheetQuery biddingSheetQuery = new BiddingSheetQuery();
        if(uuid != null){
            biddingSheetQuery.setSaleSheetUuid(uuid);
        }
        if(currentUser != null && currentUser.getEpMemberId() != null){
            biddingSheetQuery.setEnterpriseId(currentUser.getEpMemberId());
        }
        BiddingSheet biddingSheet = biddingSheetMapper.getBiddingSheetByUser(biddingSheetQuery);
        return biddingSheet;
    }



    @Override
    public List<BiddingSheet> selectByDemandSheetIdForMe(CurrentUser currentUser, Long saleSheetId) {
        BiddingSheetQuery biddingSheetQuery = new BiddingSheetQuery();
        if(saleSheetId != null){
            biddingSheetQuery.setDemandSheetId(saleSheetId);
        }
        if(currentUser != null && currentUser.getEpMemberId() != null){
            biddingSheetQuery.setEnterpriseId(currentUser.getEpMemberId());
        }
        List<BiddingSheet> biddingSheets = biddingSheetMapper.selectByDemandSheetIdForMe(biddingSheetQuery);
        return biddingSheets;
    }


    /**
     *查询当前用户的采购报价单
     * @param currentUser
     * @param uuid
     * @return
     * @throws BizException
     */
    @Override
    public BiddingSheet getBiddingDemandSheetByUser(CurrentUser currentUser, String uuid) throws BizException {

        BiddingSheetQuery biddingSheetQuery = new BiddingSheetQuery();
        if(uuid != null){
            biddingSheetQuery.setDemandSheetUuid(uuid);
        }
        if(currentUser != null && currentUser.getEpMemberId() != null){
            biddingSheetQuery.setEnterpriseId(currentUser.getEpMemberId());
        }
        BiddingSheet biddingSheet = biddingSheetMapper.getBiddingDemandSheetByUser(biddingSheetQuery);
        return biddingSheet;
    }
}
