package com.sinochem.crude.trade.shipping.service.impl;

import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.dao.AgreementMapper;
import com.sinochem.crude.trade.shipping.dao.LoadPortMapper;
import com.sinochem.crude.trade.shipping.domain.Agreement;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;
import com.sinochem.crude.trade.shipping.domain.LoadPort;
import com.sinochem.crude.trade.shipping.domain.po.MessageSheet;
import com.sinochem.crude.trade.shipping.helper.NotificationHelper;
import com.sinochem.crude.trade.shipping.service.DemandsService;
import com.sinochem.crude.trade.shipping.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author WGP
 * @descriptioncrude-trade-product_oil
 * @date 2018/4/13
 **/
@Service
public class MessageServiceImpl implements MessageService {

    private final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Value("${id.quanzhou}")
    private Long producterId;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private DemandsService demandsService;

    @Autowired
    private NotificationHelper notificationHelper;
    @Autowired
    private LoadPortMapper loadPortMapper;

    @Autowired
    private AgreementMapper agreementMapper;


    @Override
    public EnterpriseVo getByMemberId(Long memberId) {
        return enterpriseService.getByMemberId(memberId);
    }



    //拿到卖家，买家，贸易商，二船东的MemberId
    private Long[] getMemberIds(ConfirmationSheet confirmationSheet){
        Long[] memberIds = new Long[]{
                confirmationSheet.getBuyerId(),
                confirmationSheet.getEpMemberId(),
                this.producterId,
                null
        };
        Agreement agreement = agreementMapper.findByPrimaryKey(confirmationSheet.getShipAgreementId());
        if(agreement!=null){
            memberIds[3]=agreement.getEpMemberId();
        }
        return memberIds;
    }

    /**
     * 对应MemberID数组，产生url数组
     * @return
     */
    private String[] getUrls(){
        String[] urls = new String[]{
                Constants.BUYER_CONFIRMATION_URL,
                Constants.SELLER_CHARTER_PARTY_URL,
               Constants.SELLER_CHARTER_PARTY_URL,
                Constants.AGENT_CONFIRMATION_URL
        };
        return urls;
    }

    //发送站内信消息
    @Override
    public void sendSecondMessage(LoadPort domain, ConfirmationSheet confirmationSheet,LoadPort loadPort) {
        MessageSheet messageSheet = new MessageSheet();
//        {typeCode,confirmationSheetCd,vesselname,product,quantity,allFast}
        messageSheet.setProduct(domain.getProduct());
        messageSheet.setQuantity(domain.getQuantity() != null ? domain.getQuantity().toString() : null);
        messageSheet.setConfirmationCD(confirmationSheet.getConfirmationSheetCd());
        messageSheet.setConfirmationSheetCd(confirmationSheet.getConfirmationSheetCd());
        messageSheet.setVesselName(domain.getVesselName());
        messageSheet.setAllFast(DateUtil.formatDateTime(domain.getAllFast()));
        messageSheet.setNor(DateUtil.formatDateTime(domain.getNorTendered()));
        Long[] memberIds = this.getMemberIds(confirmationSheet);

        //如果为添加，并且nor不为空，则发送消息
        if (loadPort == null) {
            //nor发送消息
            if (domain.getNorTendered() != null) {
               this.sendNorMessage(memberIds,messageSheet);
            }
            //添加其他字段发送消息
            if (this.checkSecondFieldNoNor(domain)) {
               this.sendAllFastMessage(memberIds,messageSheet,confirmationSheet);
            }

        } else {
            //如果是更新nor的话
            if (domain.getNorTendered() != null) {
                if (!domain.getNorTendered().equals(loadPort.getNorTendered())) {
                   this.sendNorMessage(memberIds,messageSheet);
                }
            }
            if (this.checkFieldUpdate(domain, loadPort)) {
               this.sendAllFastMessage(memberIds,messageSheet,confirmationSheet);
            }
        }
    }

    /**
     * 发送靠泊信息
     * @param memberIds
     * @param messageSheet
     * @param confirmationSheet
     */
    private void sendAllFastMessage(Long[] memberIds, MessageSheet messageSheet,ConfirmationSheet confirmationSheet) {
        try {
            String[] urls = this.getUrls();
            int index = 0;
            for (Long memberId : memberIds) {
                if(memberId!=null){
                    messageSheet.setTypeCode(Constants.TYPE_CODE_ONE);
                    messageSheet.setMemberId(memberId);
                    messageSheet.setHyperlink(urls[index]);
                    notificationHelper.sendProAllFast(messageSheet);
                }
                index ++;
            }
            messageSheet.setMemberId(confirmationSheet.getShippingAgentId());
            messageSheet.setTypeCode(Constants.TYPE_CODE_TWO);
            if(messageSheet.getMemberId()!=null){
                messageSheet.setHyperlink(Constants.AGENT_CONFIRMATION_URL);
                notificationHelper.sendProAllFast(messageSheet);
            }
        } catch (Exception e) {
            logger.error("靠泊信息发送失败", e);
        }
    }

    /**
     * 发送第三步 装港进程信息
     * @param confirmationSheet
     */
    @Override
    public void sendThreeProcessMessage(ConfirmationSheet confirmationSheet) {
        MessageSheet messageSheet = new MessageSheet();
        messageSheet.setProduct(confirmationSheet.getProduct());
        messageSheet.setQuantity(confirmationSheet.getQuantity() != null ? confirmationSheet.getQuantity().toString() : null);
        messageSheet.setConfirmationCD(confirmationSheet.getConfirmationSheetCd());
        messageSheet.setVesselName(confirmationSheet.getVesselName());
        Long[] memberIds = this.getMemberIds(confirmationSheet);
        try{
            String[] urls = this.getUrls();
            int index = 0;
            for (Long memberId:memberIds) {
                if(memberId!=null){
                    messageSheet.setMemberId(memberId);
                    messageSheet.setHyperlink(urls[index]);
                    notificationHelper.sendProUpdateLoadingProgress(messageSheet);
                }
                index ++;
            }
        }catch (Exception e){
            logger.error("更新装港进度信息发送消息失败",e);
        }
    }

    /**
     * 装港第四步 发送信息
     * @param domain
     * @param confirmationSheet
     * @param loadPort
     */
    @Override
    public void sendFourMessage( LoadPort domain, ConfirmationSheet confirmationSheet,LoadPort loadPort) {
        MessageSheet messageSheet = new MessageSheet();
//        confirmationSheetCd,vesselname,product,quantity,allFast  || orderNumber,product,quantity, }
        messageSheet.setProduct(domain.getProduct());
        messageSheet.setQuantity(domain.getQuantity() != null ? domain.getQuantity().toString() : null);
        messageSheet.setConfirmationSheetCd(confirmationSheet.getConfirmationSheetCd());
        messageSheet.setVesselName(domain.getVesselName());
        messageSheet.setOrderNumber(confirmationSheet.getOrderNumber());

        Long[] memberIds = this.getMemberIds(confirmationSheet);
        if(loadPort==null){
            //检测商检文件存在，发送消息
            if(this.checkFourCargoDocumentsOnBoardNotNull(domain)){
                this.sendCargoDocumentsOnBoardMessage(memberIds,messageSheet,confirmationSheet);
            }
            //检测其他信息离泊信息存在，发送消息
            if(this.checkAllLinesCastOffNotNull(domain)){
               this.sendAllLinesCastOffMessage(memberIds,messageSheet,confirmationSheet);
            }
        }else {
            //检测商检文件更新，发送消息
            if(this.checkFourCargoDocumentsOnBoardUpdated(domain,loadPort)){
                this.sendCargoDocumentsOnBoardMessage(memberIds,messageSheet,confirmationSheet);
            }
            //检测离泊信息更新，发送消息
            if(this.checkAllLinesCastOffUpdated(domain,loadPort)){
               this.sendAllLinesCastOffMessage(memberIds,messageSheet,confirmationSheet);
            }
        }

    }

    /**
     * 发送离港信息
     * @param memberIds memberId数组
     * @param messageSheet 消息实体
     * @param confirmationSheet 确认单实体
     */
    private void sendAllLinesCastOffMessage(Long[] memberIds, MessageSheet messageSheet, ConfirmationSheet confirmationSheet) {
        try{
            String[] urls = this.getUrls();
            int index=0;
            for (Long memberId:memberIds) {
                if(memberId!=null){
                    messageSheet.setMemberId(memberId);
                    messageSheet.setHyperlink(urls[index]);
                    notificationHelper.sendAllLinesCastOff(messageSheet);
                }
                index ++;
            }
        }catch (Exception e){
            logger.error("船舶离港发送消息失败",e);
        }
    }

    /**
     * 发送商检文件消息
     * @param memberIds
     * @param messageSheet
     * @param confirmationSheet
     */
    private void sendCargoDocumentsOnBoardMessage(Long[] memberIds, MessageSheet messageSheet,ConfirmationSheet confirmationSheet ) {
        try{
            String[] urls = this.getUrls();
            int index  = 0;
            for (Long memberId:memberIds) {
                if(memberId!=null){
                    messageSheet.setMemberId(memberId);
                    messageSheet.setHyperlink(urls[index]);
                    messageSheet.setTypeCode(Constants.TYPE_CODE_ONE);
                    notificationHelper.sendProCargoDocumentsOnBoard(messageSheet);
                }
                index++;
            }
                messageSheet.setMemberId(confirmationSheet.getShippingAgentId());
                messageSheet.setHyperlink(Constants.AGENT_CONFIRMATION_URL);
                messageSheet.setTypeCode(Constants.TYPE_CODE_TWO);
                if(messageSheet.getMemberId()!=null){
                    notificationHelper.sendProCargoDocumentsOnBoard(messageSheet);
                }
        }catch (Exception e){
            logger.error("更新商检文件信息发送消息失败",e);
        }
    }

    /**
     * 发送NOR  Tendered 更新时的消息
     * @param memberIds
     * @param messageSheet
     */
    private void sendNorMessage(Long[] memberIds,MessageSheet messageSheet){
        try {
            String[] urls = this.getUrls();
            int index = 0;
            for (Long memberId : memberIds) {
                if(memberId!=null){
                    messageSheet.setHyperlink(urls[index]);
                    messageSheet.setMemberId(memberId);
                    notificationHelper.sendProPresentNor(messageSheet);
                }
                index ++;
            }
        } catch (Exception e) {
            logger.error("递交nor发送消息失败", e);
        }
    }

    /**
     *    检测第二步 除了nor tendered ，其余字段有一个不为空
     * @param domain
     * @return
     */
    private Boolean checkSecondFieldNoNor(LoadPort domain){
        if(domain.getAnchorAweigh() != null || domain.getPobDatetimeOne() != null
                || domain.getPobBerth() != null || domain.getFirstLineDatetime() != null
                || domain.getFirstLineBerth() != null || domain.getFirstLineEtb() != null
                || domain.getAllFast() != null){
            return true;
        }
        return false;
    }

    /**
     * 装港时第三步发送消息
     * @param domain
     * @param confirmationSheet
     * @param loadPort
     */
    @Override
    public void sendThreeMessage(LoadPort domain, ConfirmationSheet confirmationSheet,LoadPort loadPort) {
        MessageSheet messageSheet = new MessageSheet();
        messageSheet.setProduct(domain.getProduct());
        messageSheet.setQuantity(domain.getQuantity() != null ? domain.getQuantity().toString() : null);
        messageSheet.setConfirmationSheetCd(confirmationSheet.getConfirmationSheetCd());
        messageSheet.setVesselName(domain.getVesselName());
        messageSheet.setCommencedLoading(confirmationSheet.getPortOfLoading());
        Long[] memberIds = this.getMemberIds(confirmationSheet);
        String[] urls = this.getUrls();
        if(loadPort == null){
            //检测商检联检存在
            if(this.checkThreeInspectionNotNull(domain)){
                try{

                    int index = 0;
                    for (Long memberId:memberIds) {
                        if(memberId!=null){
                            messageSheet.setMemberId(memberId);
                            messageSheet.setHyperlink(urls[index]);
                            notificationHelper.sendProUpdatePortInspection(messageSheet);
                        }
                        index++;
                    }
                }catch (Exception e){
                    logger.error("添加商检联检信息发送消息失败",e);
                }
            }
            //检测开始装港信息存在
            if(this.checkThreeCommencedNotNull(domain)){
                try{
                    int index = 0;
                    for (Long memberId:memberIds) {
                        if(memberId!=null){
                            messageSheet.setMemberId(memberId);
                            messageSheet.setHyperlink(urls[index]);
                            notificationHelper.sendProCommencedLoading(messageSheet);
                        }
                        index++;
                    }

                }catch (Exception e){
                    logger.error("添加开始装港信息发送消息失败",e);
                }
            }
            //装港完成时，发送消息。
            if(this.checkCompletedLoadingNotNull(domain)){
                try{
                    int index = 0;
                    for (Long memberId:memberIds) {
                        if(memberId!=null){
                            messageSheet.setMemberId(memberId);
                            messageSheet.setHyperlink(urls[index]);
                            notificationHelper.sendProCompletedLoading(messageSheet);
                        }
                        index++;
                    }
                }catch (Exception e){
                    logger.error("更新完成装港信息发送消息失败",e);
                }
            }

        }else{
            //商检联检信息更新了，发送消息
            if(this.checkThreeInspectionUpdate(domain,loadPort)){
                try{
                    int index = 0;
                    for (Long memberId:memberIds) {
                        if(memberId!=null){
                            messageSheet.setMemberId(memberId);
                            messageSheet.setHyperlink(urls[index]);
                            notificationHelper.sendProUpdatePortInspection(messageSheet);
                        }
                        index++;
                    }
                }catch (Exception e){
                    logger.error("更新商检联检信息发送消息失败",e);
                }

            }
            //开始装港信息更新，发送消息
            if(this.checkThreeCommencedUpdated(domain,loadPort)){
                try{
                    int index =0;
                    for (Long memberId:memberIds) {
                        if(memberId!=null){
                            messageSheet.setMemberId(memberId);
                            messageSheet.setHyperlink(urls[index]);
                            notificationHelper.sendProCommencedLoading(messageSheet);
                        }
                        index ++;
                    }
                }catch (Exception e){
                    logger.error("更新开始装港信息发送消息失败",e);
                }
            }
            //装港完成信息更新，发送消息
            if(this.checkCompletedLoadingUpdated(domain,loadPort)){
                try{
                    int index = 0;
                    for (Long memberId:memberIds) {
                        if(memberId!=null){
                            messageSheet.setMemberId(memberId);
                            messageSheet.setHyperlink(urls[index]);
                            notificationHelper.sendProCompletedLoading(messageSheet);
                        }
                        index++;
                    }
                }catch (Exception e){
                    logger.error("更新完成装港信息发送消息失败",e);
                }
            }
        }
    }




    /**
     * 除了第二步检测nor字段 检测更新时是否发生了变化
     * @param domain
     * @param loadPort
     * @return
     */
    private Boolean checkFieldUpdate(LoadPort domain, LoadPort loadPort) {
        if (domain.getAnchorAweigh() != null) {
            if (!domain.getAnchorAweigh().equals(loadPort.getAnchorAweigh())) {
                return true;
            }
        }
        if (domain.getPobDatetimeOne() != null) {
            if (!domain.getPobDatetimeOne().equals(loadPort.getPobDatetimeOne())) {
                return true;
            }
        }
        if (domain.getPobBerth() != null) {
            if (!domain.getPobBerth().equals(loadPort.getPobBerth())) {
                return true;
            }
        }
        if (domain.getFirstLineDatetime() != null) {
            if (!domain.getFirstLineDatetime().equals(loadPort.getFirstLineDatetime())) {
                return true;
            }
        }
        if (domain.getFirstLineBerth() != null) {
            if (!domain.getFirstLineBerth().equals(loadPort.getFirstLineBerth())) {
                return true;
            }
        }
        if (domain.getFirstLineEtb() != null) {
            if (!domain.getFirstLineEtb().equals(loadPort.getFirstLineEtb())) {
                return true;
            }
        }
        if (domain.getAllFast() != null) {
            if (!domain.getAllFast().equals(loadPort.getAllFast())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 检测添加时商检联检不为空
     * @param loadPort
     * @return
     */
    private Boolean checkThreeInspectionNotNull(LoadPort loadPort){
        if(loadPort.getIndependentInspectionObq()!=null || loadPort.getTanksInspectionDatetime()!=null
                ||loadPort.getIndependentInspection()!=null || loadPort.getShoreArmsConnecting()!=null
                || loadPort.getIndependentInspectionDatetime()!=null){
            return true;
        }
        return false;
    }

    /**
     * 添加时检测开始装港不为空
     * @param loadPort
     * @return
     */
    private Boolean checkThreeCommencedNotNull(LoadPort loadPort){
        if(loadPort.getCommencedLoadingDatetime()!=null || loadPort.getEtc()!=null){
            return true;
        }
        return false;
    }


    /**
     * 第三步商检字段是否更新了
     * @param domain
     * @param loadPort
     * @return
     */
    private Boolean checkThreeInspectionUpdate(LoadPort domain, LoadPort loadPort) {
        if (domain.getTanksInspectionDatetime() != null) {
            if (!domain.getTanksInspectionDatetime().equals(loadPort.getTanksInspectionDatetime())) {
                return true;
            }
        }
        if (domain.getIndependentInspection() != null) {
            if (!domain.getIndependentInspection().equals(loadPort.getIndependentInspection())) {
                return true;
            }
        }
        if (domain.getIndependentInspectionDatetime() != null) {
            if (!domain.getIndependentInspectionDatetime().equals(loadPort.getIndependentInspectionDatetime())) {
                return true;
            }
        }
        if (domain.getIndependentInspectionObq() != null) {
            if (!domain.getIndependentInspectionObq().equals(loadPort.getIndependentInspectionObq())) {
                return true;
            }
        }
        if (domain.getShoreArmsConnecting() != null) {
            if (!domain.getShoreArmsConnecting().equals(loadPort.getShoreArmsConnecting())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测开始装货的字段是否更新了
     * @param domain
     * @param loadPort
     * @return
     */
    private Boolean checkThreeCommencedUpdated(LoadPort domain, LoadPort loadPort){
        if (domain.getCommencedLoadingDatetime() != null) {
            if (!domain.getCommencedLoadingDatetime().equals(loadPort.getCommencedLoadingDatetime())) {
                return true;
            }
        }
        if (domain.getEtc() != null) {
            if (!domain.getEtc().equals(loadPort.getEtc())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测装港完成存在
     * @return
     */
    private Boolean checkCompletedLoadingNotNull(LoadPort loadPort){
        if(loadPort.getCompletedLoadingDatetime()!=null){
            return true;
        }
        return false;
    }

    /**
     * 检测装港完成更新
     * @return
     */
    private Boolean checkCompletedLoadingUpdated(LoadPort domain,LoadPort loadPort){
        if(domain.getCompletedLoadingDatetime()!=null){
            if(domain.getCompletedLoadingDatetime().equals(loadPort.getCompletedLoadingDatetime())){
                return true;
            }
        }
        return false;
    }


    /**
     * 添加 检查 商检文件不为空
     * @param loadPort
     * @return
     */
    private Boolean checkFourCargoDocumentsOnBoardNotNull(LoadPort loadPort){
        if(loadPort.getBlMetricTons()!=null || loadPort.getBlLongTons()!=null
                ||loadPort.getLtr()!=null || loadPort.getBbls()!=null){
            return true;
        }
        return false;
    }

    /**
     * 检测商检文件是否更新
     * @param domain
     * @param loadPort
     * @return
     */
    private Boolean checkFourCargoDocumentsOnBoardUpdated(LoadPort domain,LoadPort loadPort){

        if (domain.getBlMetricTons() != null) {
            if (!domain.getBlMetricTons().equals(loadPort.getBlMetricTons())) {
                return true;
            }
        }
        if (domain.getBlLongTons() != null) {
            if (!domain.getBlLongTons().equals(loadPort.getBlMetricTons())) {
                return true;
            }
        }
        if (domain.getLtr() != null) {
            if (!domain.getLtr().equals(loadPort.getLtr())) {
                return true;
            }
        }
        if (domain.getBbls() != null) {
            if (!domain.getBbls().equals(loadPort.getBbls())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测离港信息不为空
     * @param loadPort
     * @return
     */
    private Boolean checkAllLinesCastOffNotNull(LoadPort loadPort){
        if(loadPort.getLeaveDatetime()!=null || loadPort.getNextPort()!=null){
            return true;
        }
        return false;
    }

    /**
     * 检测离港信息更新
     * @param domain
     * @param loadPort
     * @return
     */
    private Boolean checkAllLinesCastOffUpdated(LoadPort domain,LoadPort loadPort){
        if (domain.getLeaveDatetime() != null) {
            if (!domain.getLeaveDatetime().equals(loadPort.getLeaveDatetime())) {
                return true;
            }
        }
        if (domain.getNextPort() != null) {
            if (!domain.getNextPort().equals(loadPort.getNextPort())) {
                return true;
            }
        }
        return false;
    }
}
