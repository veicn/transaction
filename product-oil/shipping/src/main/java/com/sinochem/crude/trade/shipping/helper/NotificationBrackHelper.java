package com.sinochem.crude.trade.shipping.helper;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.helper.SystemNotificationHelper;
import com.sinochem.crude.trade.common.utils.MessageHttpUtil;
import com.sinochem.crude.trade.shipping.domain.po.MessageSheet;
import com.sinochem.crude.trade.shipping.enums.NotificationEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WGP
 * @descriptioncrude-trade-product_oil
 * @date 2018/4/12
 **/
@Component
public class NotificationBrackHelper {

    private final Logger LOGGER = LoggerFactory.getLogger(NotificationHelper.class);

    @Autowired
    private SystemNotificationHelper systemNotificationHelper;

    @Autowired
    private URLBroker messageServer;

    @Autowired
    private URLBroker shippingServer;


    /**
     * 根据枚举类获取模板名
     * @param notificationEnum
     * @return
     */
    private String getTitle(NotificationEnums notificationEnum) {
        return notificationEnum.getEnTitle();
    }


    /**例子
     * 写上功能的注释
     * @param sheet {orderNumber vesselname product quantity} {}内为必要的参数
     */
    public void sendXXX(MessageSheet sheet){
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("vesselname",sheet.getVesselName());
        model.put("product",sheet.getProduct());
        model.put("quantity",sheet.getQuantity());
        model.put("orderNumber",sheet.getOrderNumber());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_SHIP_COMPLETED_EN),
                memberId,
                NotificationEnums.PRO_SHIP_COMPLETED_EN.getTemplateName(),
                model
        );
    }
    
    /**
     * 租船协议确认/中止提醒
     * 发送给 二船东（生成租船协议操作员）
     * 对应Excel第4个 
	 *模板#if($!typeCode == 1)
		[mycrudeoil.com]Dear Customer: Your Charter Party (NO.${confirmationSheetCd},Product:${product},Quantity:${quantity})was confirmed, please check details.
		#else
		[mycrudeoil.com]Dear Customer: We are sorry to inform you that your Charter Party (NO.${confirmationSheetCd},Product:${product},Quantity：${quantity})was terminated, please check details.
		#end 
     * @param sheet {confirmationSheetCd product quantity} {}内为必要的参数
     */
    public void sendProConfirmShipAgreement(MessageSheet sheet){
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("product",sheet.getProduct());
        model.put("quantity",sheet.getQuantity());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_CONFIRM_SHIP_AGREEMENT_EN),
                memberId,
                NotificationEnums.PRO_CONFIRM_SHIP_AGREEMENT_EN.getTemplateName(),
                model
        );
    }
    

    /**
     * 租船确认单确认/中止
     * 发送给贸易商（生成租船确认单操作员）
     * 对应Excel第8个 
     * 模板#if($!typeCode == 1)
		[mycrudeoil.com]Dear Customer: Your Vessel Acceptance (NO.${confirmationSheetCd},Product:${product},Quantity:${quantity})was confirmed, please check details.
		#else
		[mycrudeoil.com]Dear Customer: We are sorry to inform you that your Vessel Acceptance(NO.${confirmationSheetCd},Product:${product},Quantity：${quantity})was terminated, please check details.
		#end
     * @param sheet {confirmationSheetCd product quantity} {}内为必要的参数
     */
    public void sendProConfirmShipConfirmation(MessageSheet sheet){
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("product",sheet.getProduct());
        model.put("quantity",sheet.getQuantity());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_CONFIRM_SHIP_CONFIRMATION_EN),
                memberId,
                NotificationEnums.PRO_CONFIRM_SHIP_CONFIRMATION_EN.getTemplateName(),
                model
        );
    }

    /**
     * 租船确认单确认提醒
     * 发送给贸易商（生成租船确认单操作员）买家（所有人）船代（所有人）
     * 对应Excel第7个 
     * 模板#if($!typeCode == 1)
		[mycrudeoil.com]Dear Customer:  You were appionted as the shipping agent of the shipment (Vessel's name: $!{vesselname},Product:${product},Quantity:${quantity},Loading range:${laycanStrat}——${laycanEnd})，please  check details .
		#else
		[mycrudeoil.com]Dear Customer: The quantity of order{$orderNumber}(Product:${product},Quantity:${quantity}) is confirmed, please send DI.
		#end
     * @param sheet {vesselname product quantity orderNumber laycanStrat laycanEnd} {}内为必要的参数
     */
    public void sendProConfirmationShipConfirm(MessageSheet sheet){
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("vesselname",sheet.getVesselName());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
        model.put("orderNumber",sheet.getOrderNumber());
        model.put("laycanStrat",sheet.getLaycanStrat());
        model.put("laycanEnd",sheet.getLaycanEnd());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_CONFIRMATION_SHIP_CONFIRM_EN),
                memberId,
                NotificationEnums.PRO_CONFIRMATION_SHIP_CONFIRM_EN.getTemplateName(),
                model
        );
    }
    
    /**
     * 二船成提交租船协议提醒
     * 发送给贸易商（租船需求发布人）
     * 对应Excel第3个 
     * 模板[mycrudeoil.com]Dear Customer: $!{buyer} uploaded a Charter Party (NO.:$!{agreementCd},Product:$!{product}, Quantity:$!{quantity}), please check details.
     *  * @param sheet {buyer agreementCd product quantity} {}内为必要的参数
     */
    public void sendProCreateShipAgreement(MessageSheet sheet){
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("buyer", sheet.getBuyer());
        model.put("agreementCd", sheet.getAgreementCd());
        model.put("product",sheet.getProduct());
        model.put("quantity",sheet.getQuantity());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_PRO_CREATE_SHIP_AGREEMENT_EN),
                memberId,
                NotificationEnums.PRO_PRO_CREATE_SHIP_AGREEMENT_EN.getTemplateName(),
                model
        );
    }
    
    /**
     * 生成租船确认单提醒
     * 发送给生产厂家（所有人）
     * 模板Dear Customer:${enterprise}:uploaded a Vessel's name:$!{vesselname}(NO.${confirmationSheetCd},Product:${product},Quantity:${quantity}), please check details.
     * @param sheet {enterprise vesselname confirmationSheetCd product quantity} {}内为必要的参数
     */
    public void sendProCreateShipConfirmation(MessageSheet sheet){
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("enterprise", sheet.getEnterprise());
        model.put("vesselname", sheet.getVesselName());
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("product",sheet.getProduct());
        model.put("quantity",sheet.getQuantity());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_CREATE_SHIP_CONFIRMATION_EN),
                memberId,
                NotificationEnums.PRO_CREATE_SHIP_CONFIRMATION_EN.getTemplateName(),
                model
        );
    }
    
    /**
     * 生成租船确认单提醒(不需要确认)
     * 发送给生产厂家（所有人）买家（所有人）船代（所有人）
     * 对应Excel第5个 
     * 模板#if($!typeCode == 1)
		[mycrudeoil.com]Dear Customer: $!{merchantNm} uploaded a Vessel's name Acceptance(NO.:$!{confirmationCD},Product:$!{product}, Quantity:$!{quantity}), please check details.
		#elseif($!typeCode == 2)
		[mycrudeoil.com]Dear Customer: You were appionted as the shipping agent of the shipment (Vessel's name:$!{vesselname},$!{product}, Quantity:$!{quantity},Loading range:$!{laycan})，please  check details.
		#elseif($!typeCode == 3)
		[mycrudeoil.com]Dear Customer: The quantity of Order:$!{orderNumber}(Product:$!{product}, Quantity:$!{quantity}) was confirmed, please send DI.
		#else
		[mycrudeoil.com]Dear Customer: The DI of Order:$!{orderNumber}(Product:$!{product}, Quantity:$!{quantity}) was confirmed, please prepare documents for CIQ and CHINA CUSTOMS.
		#end 
	 * @param sheet {merchantNm confirmationCD product quantity vesselname laycan orderNumber } {}内为必要的参数
     */
    public void sendProCreateShipConfirmationNoconfirm(MessageSheet sheet){
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("merchantNm", sheet.getMerchantNm());
        model.put("confirmationCD", sheet.getConfirmationCD());
        model.put("product",sheet.getProduct());
        model.put("quantity",sheet.getQuantity());
        model.put("vesselname", sheet.getVesselName());
        model.put("laycan", sheet.getLaycan());
        model.put("orderNumber", sheet.getOrderNumber());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_CREATE_SHIP_CONFIRMATION_NOCONFIRM_EN),
                memberId,
                NotificationEnums.PRO_CREATE_SHIP_CONFIRMATION_NOCONFIRM_EN.getTemplateName(),
                model
        );
    }
    
    /**
     * 维护装港递交NOR提醒
     * 发送给生产厂家（所有人）买家（所有人）二船东（所有人）贸易商（所有人）
     * 对应Excel第12个 
     * 模板Dear Customer: Your shipment (Shipment NO:$!{confirmationSheetCd}, Vessel's name:$!{vesselname}, Product:$!{product},Quantity:$!{quantity}) tendered NOR at $!{nor}, please check details.
     * @param sheet {confirmationSheetCd vesselname product quantity nor} {}内为必要的参数
     */
    public void sendProPresentNor(MessageSheet sheet){
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("vesselname", sheet.getVesselName());
        model.put("product",sheet.getProduct());
        model.put("quantity",sheet.getQuantity());
        model.put("nor", sheet.getNor());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_PRESENT_NOR_EN),
                memberId,
                NotificationEnums.PRO_PRESENT_NOR_EN.getTemplateName(),
                model
        );
    }
    
    /**
     * 贸易商确认航次结束提醒
     * 发送给生产厂家（所有人）买家（所有人）二船东（所有人）贸易商（所有人）
     * 对应Excel第24个 
     * 模板Dear Customer: The yoyage of your shipment (Shipment NO:$!{confirmationSheetCd}, Vessel's name:$!{vesselname}, Product:$!{product}, Quantity:$!{quantity}) was completed, please check details.
     * @param sheet {confirmationSheetCd vesselname product quantity} {}内为必要的参数
     */
    public void sendProShipCompleted(MessageSheet sheet){
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("vesselname", sheet.getVesselName());
        model.put("product",sheet.getProduct());
        model.put("quantity",sheet.getQuantity());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_SHIP_COMPLETED_EN),
                memberId,
                NotificationEnums.PRO_SHIP_COMPLETED_EN.getTemplateName(),
                model
        );
    }
    
    /**
     * 维护装港“更新装货进度”提醒（Loading Progress更新一条发一次）
     * 发送给生产厂家（所有人）买家（所有人）二船东（所有人）贸易商（所有人）
     * 对应Excel第16个 
     * 模板Dear Customer: Your shipment (Shipment NO:$!{confirmationSheetCd}, Vessel's name:$!{vesselname}, Product:$!{product}, Quantity:$!{quantity})  has new loading progress information, please check details.
     * @param sheet {confirmationSheetCd vesselname product quantity} {}内为必要的参数
     */
    public void sendProUpdateLoadingProgress(MessageSheet sheet){
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("vesselname", sheet.getVesselName());
        model.put("product",sheet.getProduct());
        model.put("quantity",sheet.getQuantity());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_UPDATE_LOADING_PROGRESS_EN),
                memberId,
                NotificationEnums.PRO_UPDATE_LOADING_PROGRESS_EN.getTemplateName(),
                model
        );
    }
    
    /**
     * 维护装港“商检联检”（TANKS INSPECTION—>INDEPENDENT INSPECTION）Remarks、Other非必填
     * 发送给生产厂家（所有人）买家（所有人）二船东（所有人）贸易商（所有人）
     * 对应Excel第14个 
     * 模板Dear Customer: Your shipment (Shipment NO:$!{confirmationSheetCd}, Vessel's name:$!{vesselname}, Product:$!{product}, Quantity:$!{quantity})  has new loading progress information, please check details.
     * @param sheet {confirmationSheetCd vesselname product quantity} {}内为必要的参数
     */
    public void sendProUpdatePortInspection(MessageSheet sheet){
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("vesselname", sheet.getVesselName());
        model.put("product",sheet.getProduct());
        model.put("quantity",sheet.getQuantity());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_UPDATE_PORT_INSPECTION_EN),
                memberId,
                NotificationEnums.PRO_UPDATE_PORT_INSPECTION_EN.getTemplateName(),
                model
        );
    }

    
    /**
     *
     * @param title 标题
     * @param memberId 发送给谁
     * @param tplName  模板
     * @param model 模板参数
     */
    private void sendNotification(String title, Long memberId, String tplName, Map<String, Object> model) {
        try {
            // 发送站内信
            String result = systemNotificationHelper.sendMessage(messageServer.get(MessageHttpUtil.MESSAGE_URL).toString(),
                    MessageHttpUtil.generateMessageParams(tplName, memberId + "", "3", title, model));
            LOGGER.info("发送企业站内信:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("发送企业站内信异常" + e);
        }
    }
}
