package com.sinochem.crude.trade.transaction.helper;

import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.helper.SystemNotificationHelper;
import com.sinochem.crude.trade.common.utils.MessageHttpUtil;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.dao.ContractSheetMapper;
import com.sinochem.crude.trade.transaction.dao.SaleSheetMapper;
import com.sinochem.crude.trade.transaction.domain.po.BiddingSheet;
import com.sinochem.crude.trade.transaction.domain.po.ContractSheet;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.enums.NotificationEnum;
import com.sinochem.crude.trade.transaction.enums.SaleSheetStatusEnum;
import com.sinochem.crude.trade.transaction.enums.SaleTypeEnum;
import com.sinochem.crude.trade.transaction.model.vo.MemberInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 交易模块系统通知的helper类
 * @author Yichen Zhao
 * date: 20180318
 */
@Component
public class NotificationHelper {

    private final Logger LOGGER = LoggerFactory.getLogger(MessageHelper.class);

    @Autowired
    private SystemNotificationHelper systemNotificationHelper;

    @Autowired
    private URLBroker messageServer;

    @Autowired
    private URLBroker transactionServer;

    @Autowired
    private MemberInfoHelper memberInfoHelper;

    @Autowired
    private SaleSheetMapper saleSheetMapper;

    @Autowired
    private ContractSheetMapper contractSheetMapper;

    //成品油销售资源提醒s
    public void newSaleSheetPublished(SaleSheet saleSheet) {

        //招标
        //发送消息至所有交易员需要member提供接口，这个再说

        //询价
        if (saleSheet != null) {
            if (SaleTypeEnum.DIRECTIONAL.getCode().equals(saleSheet.getSaleTypeCode())) {
                String specifiedEnterpriseIdList = saleSheet.getSpecifiedEnterpriseIdList();

                if (!StringUtil.isEmpty(specifiedEnterpriseIdList)) {
                    String[] enterpriseIds = specifiedEnterpriseIdList.split(",");

                    String saleTypeCode = SaleTypeEnum.DIRECTIONAL.getCode();

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(UrlMapping.PRODUCT_BIDDING).append("/").append(UrlMapping.INDEX);
                    String baseHyperlink = stringBuilder.toString();

                    for (String enterpriseIdAsString : enterpriseIds) {
                        Long enterpriseId = Long.parseLong(enterpriseIdAsString);

                        HashMap<String, Object> model = new HashMap<>();
                        model.put("saleTypeCode", saleTypeCode);

                        String hyperlink = new StringBuilder(baseHyperlink).append(".htm?uuid=").append(saleSheet.getUuid()).toString();
                        model.put("hyperlink", transactionServer.get(hyperlink).toString());

                        this.sendNotification(
                                this.getTitle(NotificationEnum.NEW_SALE_SHEET_PUBLISHED_EN),
                                enterpriseId,
                                NotificationEnum.NEW_SALE_SHEET_PUBLISHED_EN.getTemplateName(),
                                model
                        );
                    }
                }
            }
        }
    }

    //成品油买家报价/投标提醒
    public void newBiddingSheet(BiddingSheet biddingSheet) {

        Long saleSheetId = biddingSheet.getSaleSheetId();
        if (saleSheetId != null) {
            SaleSheet saleSheet = saleSheetMapper.selectByPrimaryKey(saleSheetId);
            //Long enterpriseId = saleSheet.getEnterpriseId();
            Long enterpriseId = saleSheet.getUserCreated();

            HashMap<String, Object> model = new HashMap<>();

            String saleTypeCode = saleSheet.getSaleTypeCode();
            model.put("saleTypeCode", saleTypeCode);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(UrlMapping.MY_PRODUCT_BIDDING_LIST).append("/").append(UrlMapping.INDEX).append(".htm?uuid=" + saleSheet.getUuid());
            String hyperlink = transactionServer.get(stringBuilder.toString()).toString();
            model.put("hyperlink", hyperlink);

            this.sendNotification(
                    this.getTitle(NotificationEnum.NEW_BIDDING_SHEET_EN),
                    enterpriseId,
                    NotificationEnum.NEW_BIDDING_SHEET_EN.getTemplateName(),
                    model
            );
        }
    }

    //销售单快截止时成品油报价/投标提醒
    public void saleSheetApproachDeadline(SaleSheet saleSheet) {

        //Long enterpriseId = saleSheet.getEnterpriseId();
        Long enterpriseId = saleSheet.getUserCreated();

        HashMap<String, Object> model = new HashMap<>();

        String saleTypeCode = saleSheet.getSaleTypeCode();
        model.put("saleTypeCode", saleTypeCode);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(UrlMapping.MY_PRODUCT_DETAIL).append("/").append(UrlMapping.INDEX).append(".htm?uuid=" + saleSheet.getUuid());
        String hyperlink = transactionServer.get(stringBuilder.toString()).toString();
        model.put("hyperlink", hyperlink);

        this.sendNotification(
                this.getTitle(NotificationEnum.SALE_SHEET_APPROACH_DEADLINE_EN),
                enterpriseId,
                NotificationEnum.SALE_SHEET_APPROACH_DEADLINE_EN.getTemplateName(),
                model
        );
    }

    //成品油截止提醒
    public void saleSheetReachDeadline(SaleSheet saleSheet) {

        //Long enterpriseId = saleSheet.getEnterpriseId();
        Long enterpriseId = saleSheet.getUserCreated();

        HashMap<String, Object> model = new HashMap<>();

        String saleTypeCode = saleSheet.getSaleTypeCode();
        model.put("saleTypeCode", saleTypeCode);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(UrlMapping.MY_PRODUCT_DETAIL).append("/").append(UrlMapping.INDEX).append(".htm?uuid=" + saleSheet.getUuid());
        String hyperlink = transactionServer.get(stringBuilder.toString()).toString();
        model.put("hyperlink", hyperlink);

        this.sendNotification(
                this.getTitle(NotificationEnum.SALE_SHEET_REACH_DEADLINE_EN),
                enterpriseId,
                NotificationEnum.SALE_SHEET_REACH_DEADLINE_EN.getTemplateName(),
                model
        );
    }

    //招标/询盘逾期未操作提醒
    public void noOperationBeforeBidOpeningDate(SaleSheet saleSheet) {

        //Long enterpriseId = saleSheet.getEnterpriseId();
        Long enterpriseId = saleSheet.getUserCreated();

        HashMap<String, Object> model = new HashMap<>();

        String saleTypeCode = saleSheet.getSaleTypeCode();
        model.put("saleTypeCode", saleTypeCode);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(UrlMapping.MY_PRODUCT_DETAIL).append("/").append(UrlMapping.INDEX).append(".htm?uuid=" + saleSheet.getUuid());
        String hyperlink = transactionServer.get(stringBuilder.toString()).toString();
        model.put("hyperlink", hyperlink);

        this.sendNotification(
                this.getTitle(NotificationEnum.NO_OPERATION_BEFORE_BID_OPENING_DATE_EN),
                enterpriseId,
                NotificationEnum.NO_OPERATION_BEFORE_BID_OPENING_DATE_EN.getTemplateName(),
                model
        );
    }

    //成品油中标/成交提醒
    public void biddingSheetWon(BiddingSheet wonBiddingSheet) {

        Long saleSheetId = wonBiddingSheet.getSaleSheetId();

        if (saleSheetId != null) {
            SaleSheet saleSheet = saleSheetMapper.selectByPrimaryKey(saleSheetId);
            ContractSheet contractSheet = contractSheetMapper.getContractBySaleSheetId(saleSheetId);
            //Long enterpriseId = wonBiddingSheet.getEnterpriseId();
            Long enterpriseId = wonBiddingSheet.getUserCreated();

            HashMap<String, Object> model = new HashMap<>();

            String saleTypeCode = saleSheet.getSaleTypeCode();
            model.put("saleTypeCode", saleTypeCode);

            //给买家发
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(UrlMapping.MY_CONTRACT_DETAIL)
                    .append("/")
                    .append(UrlMapping.INDEX)
                    .append(".htm?uuid=")
                    .append(contractSheet.getUuid());
            String hyperlink = transactionServer.get(stringBuilder.toString()).toString();
            model.put("hyperlink", hyperlink);

            this.sendNotification(
                    this.getTitle(NotificationEnum.BIDDING_SHEET_WON_EN),
                    enterpriseId,
                    NotificationEnum.BIDDING_SHEET_WON_EN.getTemplateName(),
                    model
            );
            //给卖家发
            StringBuilder stringBuilderTwo = new StringBuilder();
            stringBuilderTwo.append(UrlMapping.MY_CONTRACT_DETAIL)
                    .append("/")
                    .append(UrlMapping.INDEX)
                    .append(".htm?uuid=")
                    .append(contractSheet.getUuid());
            String hyperlinkTwo = transactionServer.get(stringBuilderTwo.toString()).toString();
            model.put("hyperlink", hyperlinkTwo);

            this.sendNotification(
                    this.getTitle(NotificationEnum.BIDDING_SHEET_WON_SALER_EN),
                    saleSheet.getUserCreated(),
                    NotificationEnum.BIDDING_SHEET_WON_SALER_EN.getTemplateName(),
                    model
            );
        }
    }

    //成品油未中标/成交提醒
    public void biddingSheetLost(BiddingSheet lostBiddingSheet) {

        Long saleSheetId = lostBiddingSheet.getSaleSheetId();

        if (saleSheetId != null) {
            SaleSheet saleSheet = saleSheetMapper.selectByPrimaryKey(saleSheetId);
            //Long enterpriseId = lostBiddingSheet.getEnterpriseId();
            Long enterpriseId = lostBiddingSheet.getUserCreated();

            HashMap<String, Object> model = new HashMap<>();

            String saleTypeCode = saleSheet.getSaleTypeCode();
            String saleSheetStatusCode = saleSheet.getSaleSheetStatusCode();
            model.put("saleTypeCode", saleTypeCode);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(UrlMapping.MY_BIDDING_DETAIL)
                    .append("/")
                    .append(UrlMapping.INDEX)
                    .append(".htm?uuid=")
                    .append(lostBiddingSheet.getUuid());
            String hyperlink = transactionServer.get(stringBuilder.toString()).toString();
            model.put("hyperlink", hyperlink);

            if (SaleSheetStatusEnum.CANCELLED.getCode().equals(saleSheetStatusCode)) {
                this.sendNotification(
                        this.getTitle(NotificationEnum.SALE_SHEET_CANCELLED_EN),
                        enterpriseId,
                        NotificationEnum.SALE_SHEET_CANCELLED_EN.getTemplateName(),
                        model
                );
            } else if (SaleSheetStatusEnum.COMPLETED.getCode().equals(saleSheetStatusCode)) {
                this.sendNotification(
                        this.getTitle(NotificationEnum.BIDDING_SHEET_LOST_EN),
                        enterpriseId,
                        NotificationEnum.BIDDING_SHEET_LOST_EN.getTemplateName(),
                        model
                );
            }
        }
    }

    /** 类内辅助方法 */
    //获取提示的标题，暂时默认为英文，之后需要根据Locale进行调整
    private String getTitle(NotificationEnum notificationEnum) {
        return notificationEnum.getEnTitle();
    }

    //发送短信，邮件和站内信
    private void sendNotification(String title, Long memberId, String tplName, Map<String, Object> model) {
        try{
            // 发送站内信
            String result = systemNotificationHelper.sendMessage(messageServer.get(MessageHttpUtil.MESSAGE_URL).toString(),
                    MessageHttpUtil.generateMessageParams(tplName, memberId+"", "3", title, model));
            LOGGER.info("发送企业站内信:" + result);
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("发送企业站内信异常" + e);
        }


        /*try{
            // 发APP请求
            String result = systemNotificationHelper.sendMessage(messageServer.get(MessageHttpUtil.APP_URL).toString(),
                    systemNotificationHelper.generateAPPParams(tplName, memberId+"", model));
            LOGGER.info("推送APP消息:" + result);
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("推送APP消息异常" + e);
        }*/
        MemberInfoVO memberInfoVO = null;
        try{
            memberInfoVO = memberInfoHelper.memberInfo(memberId);
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("获取账户信息异常" + e);
        }
        if(memberInfoVO != null){
            if(StringUtil.isNotEmpty(memberInfoVO.getEmail())){
                try{
                    // 发送邮件
                    String result = systemNotificationHelper.sendMessage(messageServer.get(MessageHttpUtil.MAIL_TPL_URL).toString(),
                            systemNotificationHelper.generateTplMailParams(tplName, title, memberInfoVO.getEmail(), model));
                    LOGGER.info("发送邮件:" + result);
                }catch (Exception e){
                    e.printStackTrace();
                    LOGGER.error("发送邮件异常" + e);
                }
            }
           /* if(StringUtil.isNotEmpty(memberInfoVO.getMobile())){
                try{
                    // 发送短信
                    String result = systemNotificationHelper.sendMessage(messageServer.get(MessageHttpUtil.SMS_URL).toString(),
                            systemNotificationHelper.generateSmsParams(tplName, memberInfoVO.getMobile(), model));
                    LOGGER.info("发送短信:" + result);
                }catch (Exception e){
                    e.printStackTrace();
                    LOGGER.error("发送短信异常" + e);
                }
            }*/
        }else{
            LOGGER.error("未获取到账户信息!");
        }
    }
}
