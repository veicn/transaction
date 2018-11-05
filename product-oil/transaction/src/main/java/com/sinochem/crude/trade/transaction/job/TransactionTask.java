package com.sinochem.crude.trade.transaction.job;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.transaction.constant.Sinochem;
import com.sinochem.crude.trade.transaction.dao.BiddingSheetMapper;
import com.sinochem.crude.trade.transaction.dao.SaleSheetMapper;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.enums.SaleSheetStatusEnum;
import com.sinochem.crude.trade.transaction.helper.MemberInfoHelper;
import com.sinochem.crude.trade.transaction.helper.NotificationHelper;
import com.sinochem.crude.trade.transaction.model.query.SaleSheetQuery;
import com.sinochem.crude.trade.transaction.service.SaleSheetService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class TransactionTask {

    private final Logger LOGGER = LoggerFactory.getLogger(TransactionTask.class);

    @Autowired
    private SaleSheetMapper saleSheetMapper;

    @Autowired
    private BiddingSheetMapper biddingSheetMapper;

    @Autowired
    private NotificationHelper notificationHelper;

    @Autowired
    private MemberInfoHelper memberInfoHelper;

    @Autowired
    private SaleSheetService saleSheetService;

    @Autowired
    private EnterpriseService enterpriseService;

    private boolean idInitialized = false;

    /**
     * 销售单快截止时成品油报价/投标提醒，在截止前一天提醒
     */
    @Scheduled(cron = "0 0 10 * * ?")
    public void saleSheetApproachDeadline() {

        Calendar today = Calendar.getInstance();
        Calendar tommorow = Calendar.getInstance();
        tommorow.add(Calendar.DAY_OF_MONTH, 1);

        SaleSheetQuery saleSheetQuery = new SaleSheetQuery();
        saleSheetQuery.setDeadlineBefore(tommorow.getTime());
        saleSheetQuery.setDeadlineAfter(today.getTime());

        List<SaleSheet> saleSheetList = saleSheetMapper.getSaleSheetList(saleSheetQuery);
        for (SaleSheet saleSheet : saleSheetList) {
            notificationHelper.saleSheetApproachDeadline(saleSheet);
        }
    }

    /**
     * 成品油截止提醒
     */
    @Scheduled(cron = "0 0 11 * * ?")
    public void saleSheetReachDeadline() {

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        Calendar tommorow = Calendar.getInstance();
        tommorow.set(Calendar.HOUR, 23);
        tommorow.set(Calendar.MINUTE, 59);
        tommorow.set(Calendar.SECOND, 59);

        SaleSheetQuery saleSheetQuery = new SaleSheetQuery();
        saleSheetQuery.setDeadlineAfter(today.getTime());
        saleSheetQuery.setDeadlineBefore(tommorow.getTime());

        List<SaleSheet> saleSheetList = saleSheetMapper.getSaleSheetList(saleSheetQuery);
        for (SaleSheet saleSheet : saleSheetList) {
            notificationHelper.saleSheetReachDeadline(saleSheet);
        }
    }

    /**
     * 招标/询盘逾期未操作提醒
     */
    @Scheduled(cron = "0 0 12 * * ?")
    public void noOperationBeforeBidOpeningDate() {

        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);
        yesterday.set(Calendar.HOUR, 23);
        yesterday.set(Calendar.MINUTE, 59);
        yesterday.set(Calendar.SECOND, 59);

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR, 23);
        today.set(Calendar.MINUTE, 59);
        today.set(Calendar.SECOND, 59);

        SaleSheetQuery saleSheetQuery = new SaleSheetQuery();
        saleSheetQuery.setBidOpeningDateAfter(yesterday.getTime());
        saleSheetQuery.setBidOpeningDateBefore(today.getTime());

        List<SaleSheet> saleSheetList = saleSheetMapper.getSaleSheetList(saleSheetQuery);
        for (SaleSheet saleSheet : saleSheetList) {
            if (!SaleSheetStatusEnum.COMPLETED.getCode().equals(saleSheet.getSaleSheetStatusCode())
                    || !SaleSheetStatusEnum.CANCELLED.getCode().equals(saleSheet.getSaleSheetStatusCode())) {
                notificationHelper.noOperationBeforeBidOpeningDate(saleSheet);
            }
        }
    }

    /**
     * 设置销售单的逾期状态
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void setSaleSheetCancelledStatus() {
        SaleSheetQuery saleSheetQuery = new SaleSheetQuery();
        saleSheetQuery.setDeadlineBefore(new Date());
        List<SaleSheet> cancelledSaleSheetList = saleSheetMapper.getSaleSheetList(saleSheetQuery);
        if(cancelledSaleSheetList.size() > 0){
            for (SaleSheet cancelledSaleSheet : cancelledSaleSheetList) {
                try {
                    //saleSheetService.cancelBiddingSheet(null, cancelledSaleSheet);
                    String saleSheetStatusCode = cancelledSaleSheet.getSaleSheetStatusCode();
                    if(!StringUtil.isEmpty(saleSheetStatusCode)){
                        if(SaleSheetStatusEnum.RELEASED.getCode().equals(saleSheetStatusCode)){
                            saleSheetService.changeSaleSheetStatus(null,cancelledSaleSheet,SaleSheetStatusEnum.EXPIRED.getCode());
                        }
                    }
                } catch (BizException e) {
                    e.printStackTrace();
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 获取中化新和泉化的ID
     */
//    @Scheduled(cron = "0 0/1 * * * ?")
//    public void initializeIds() {
//        if (!idInitialized) {
//            try {
//                EnterpriseVo singapore = enterpriseService.selectByNameLike(Sinochem.SINOCHEM_SINGAPORE, null);
//                Sinochem.SINOCHEM_SINGAPORE_ID = singapore.getMemberId();
//
//                EnterpriseVo quanzhou = enterpriseService.selectByNameLike(Sinochem.SINOCHEM_QUANZHOU, null);
//                Sinochem.SINOCHEM_QUANZHOU_ID = quanzhou.getMemberId();
//
//                idInitialized = true;
//            } catch (Exception e) {
//                e.printStackTrace();
//                LOGGER.error(e.getMessage(), e);
//            }
//        }
//    }
}
