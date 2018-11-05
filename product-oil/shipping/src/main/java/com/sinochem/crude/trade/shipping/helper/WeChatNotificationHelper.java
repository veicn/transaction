package com.sinochem.crude.trade.shipping.helper;

import com.alibaba.fastjson.JSON;
import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.helper.SystemNotificationHelper;
import com.sinochem.crude.trade.common.utils.MessageHttpUtil;
import com.sinochem.crude.trade.shipping.domain.po.MessageSheet;
import com.sinochem.crude.trade.shipping.domain.po.SendMessage;
import com.sinochem.crude.trade.shipping.enums.NotificationEnums;
import com.sinochem.crude.trade.shipping.service.WechatMessageService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WGP
 * @descriptioncrude-trade-product_oil
 * @date 2018/4/12
 **/
@Component
public class WeChatNotificationHelper {

    private final Logger logger = LoggerFactory.getLogger(NotificationHelper.class);
    private   final String WE_CHAT_URL="/notification.json";
    @Autowired
    private SystemNotificationHelper systemNotificationHelper;

    @Autowired
    private URLBroker wechatServer;

    @Autowired
    private URLBroker shippingServer;

    @Autowired
    private HttpConnectionManager httpConnectionManager;

    @Autowired
    private WechatMessageService wechatMessageService;





    /**
     * 根据枚举类获取模板名
     *
     * @param notificationEnum
     * @return
     */
    private String getTitle(NotificationEnums notificationEnum) {
        return notificationEnum.getEnTitle();
    }


    /**
     * 新增租船需求提醒
     *
     * @param sheet{buyer,demandsCd,product,quantity,hyperlink}
     * @author wugangpeng
     */
    public void sendProCreateShipDemands(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        model.put("buyer", sheet.getBuyer());
        model.put("demandsCd", sheet.getOrderNumber());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
        //TODO:后续加上链接
        model.put("hyperlink", sheet.getCompletedLoading());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_CREATE_SHIP_DEMANDS_EN),
                memberId,
                NotificationEnums.PRO_CREATE_SHIP_DEMANDS_EN.getTemplateName(),
                model
        );
    }


    /**
     * //维护装港船舶离泊提醒
     *
     * @param sheet{confirmationSheetCd vesselname product quantity sailed}
     *                                  [mycrudeoil.com]Dear Customer: Your shipment
     *                                  (Shipment NO:$!{confirmationSheetCd}, Vessel's name:$!{vesselname},
     *                                  Product:$!{product}, Quantity:$!{quantity})
     *                                  sailed at $!{sailed}, please check details.
     * @author wugangpeng
     */
    public void sendAllLinesCastOff(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        model.put("vesselname", sheet.getVesselName());
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
        model.put("sailed", sheet.getSailed());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_ALL_LINES_CAST_OFF_EN),
                memberId,
                NotificationEnums.PRO_ALL_LINES_CAST_OFF_EN.getTemplateName(),
                model
        );
    }

    /**
     * 发送装货完毕的消息 对应Excel第17个
     *
     * @param sheet{confirmationSheetCd,product,quantity,vesselname,completedLoading} [mycrudeoil.com]Dear Customer: Your shipment (Shipment NO:$!{orderNumber},
     *                                                                                Vessel's name:$!{vesselname}, Product:$!{product}, Quantity:$!{quantity})
     *                                                                                completed loading at $!{completedLoading}, please check details.
     *                                                                                <a href="$!{hyperlink}">Please click the following website to check.</a>
     * @author wugangpeng
     */
    public void sendProAllFast(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("product", sheet.getProduct());
        model.put("vesselname", sheet.getVesselName());
        model.put("quantity", sheet.getQuantity());
        model.put("completedLoading", sheet.getCompletedLoading());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_ALL_FASE_EN),
                memberId,
                NotificationEnums.PRO_ALL_FASE_EN.getTemplateName(),
                model
        );
    }


    /**
     * 维护装港“商检文件到船”提醒
     * （Cargo documents on board）
     *
     * @param sheet{confirmationSheetCd,vesselname,product,quantity,allFast || orderNumber,product,quantity, }
     *                                                                      #if($!typeCode == 1)
     *                                                                      [mycrudeoil.com]Dear Customer: Your shipment
     *                                                                      (Shipment NO:$!{confirmationSheetCd},
     *                                                                      Vessel's name:$!{vesselname}, Product:$!{product},
     *                                                                      Quantity:$!{quantity}) was on board , please check details.
     *                                                                      #else
     *                                                                      [mycrudeoil.com]Dear Customer:Your orderId:$!{orderNumber}(Product:$!{product}, Quantity:$!{quantity}) was loaded，please issue bill of lading.
     *                                                                      #end
     * @author wugangpeng
     */
    public void sendProCargoDocumentsOnBoard(MessageSheet sheet, String typeCode) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        model.put("typeCode", sheet.getTypeCode());
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("vesselname", sheet.getVesselName());
        model.put("orderNumber", sheet.getOrderNumber());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
        model.put("allFast", sheet.getAllFast());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_CARGO_DOCUMENTS_ON_BOARD_EN),
                memberId,
                NotificationEnums.PRO_CARGO_DOCUMENTS_ON_BOARD_EN.getTemplateName(),
                model
        );
    }

    /**
     * //维护装港开始装货提醒
     *
     * @param sheet {typeCode,confirmationSheetCd,vesselname,product,quantity,allFast}
     *              if($!typeCode == 1)
     *              [mycrudeoil.com]Dear Customer: Your shipment (Shipment NO:$!{confirmationSheetCd},
     *              Vessel's name:$!{vesselname}, Product:$!{product}, Quantity:$!{quantity})
     *              was all fast at:$!{allFast} , please check details.
     *              #else
     *              [mycrudeoil.com]Dear Customer: Your shipment (Shipment NO:$!{confirmationSheetCd},
     *              Vessel's name:$!{vesselname}, Product:$!{product}, Quantity:$!{quantity})
     *              will be loading，please confirm the draft bill of lading.
     *              #end
     */
    public void sendProCommencedLoading(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        model.put("typeCode", sheet.getTypeCode());
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("vesselname", sheet.getVesselName());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
        model.put("allFast", sheet.getAllFast());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_COMMENCED_LOADING_EN),
                memberId,
                NotificationEnums.PRO_COMMENCED_LOADING_EN.getTemplateName(),
                model
        );
    }

    /**
     * //维护装港装货完毕提醒
     *
     * @param sheet{confirmationSheetCd,vesselname,product,quantity,commencedLoading}
     */
    public void sendProCompletedLoading(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("vesselname", sheet.getVesselName());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
        model.put("commencedLoading", sheet.getCommencedLoading());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_COMPLETED_LOADING_EN),
                memberId,
                NotificationEnums.PRO_COMPLETED_LOADING_EN.getTemplateName(),
                model
        );
    }


    /**
     * //二船东更新配载计划提醒
     *
     * @param sheet{orderNumber,product,quantity}
     */
    public void sendProDisponentOwnerUpdateStowagePlan(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>(8);
        model.put("orderNumber", sheet.getOrderNumber());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());

        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_DISPONENT_OWNER_UPDATE_STOWAGE_PLAN_EN),
                memberId,
                NotificationEnums.PRO_DISPONENT_OWNER_UPDATE_STOWAGE_PLAN_EN.getTemplateName(),
                model
        );
    }


    /**
     * 二船东更新在途信息提醒
     *
     * @param sheet{confirmationSheetCd,vesselname,product,quantity}
     */
    public void sendProDisponentOwnerUpdateTransit(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>(8);
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("vesselname", sheet.getVesselName());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());

        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_DISPONENT_OWNER_UPDATE_TRANSIT_EN),
                memberId,
                NotificationEnums.PRO_DISPONENT_OWNER_UPDATE_TRANSIT_EN.getTemplateName(),
                model
        );
    }


    /**
     * 二船东更新卸港信息提醒
     *
     * @param sheet{confirmationSheetCd,vesselname,product,quantity}
     */
    public void sendProDisponentOwnerUpdateUnloading(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>(8);
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("vesselname", sheet.getVesselName());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());

        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_DISPONENT_OWNER_UPDATE_UNLOADING_EN),
                memberId,
                NotificationEnums.PRO_DISPONENT_OWNER_UPDATE_UNLOADING_EN.getTemplateName(),
                model
        );
    }


    /**
     * //贸易商更新在途信息提醒
     *
     * @param sheet{confirmationSheetCd,vesselname,product,quantity}
     */
    public void sendProMerchantUpdateTransit(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>(8);
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("vesselname", sheet.getVesselName());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());

        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_MERCHANT_UPDATE_TRANSIT_EN),
                memberId,
                NotificationEnums.PRO_MERCHANT_UPDATE_TRANSIT_EN.getTemplateName(),
                model
        );
    }


    /**
     * //贸易商更新卸港信息提醒
     *
     * @param sheet{confirmationSheetCd,vesselname,product,quantity}
     */
    public void sendProMerchantUpdateUnloading(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>(8);
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("vesselname", sheet.getVesselName());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());

        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_MERCHANT_UPDATE_UNLOADING_EN),
                memberId,
                NotificationEnums.PRO_MERCHANT_UPDATE_UNLOADING_EN.getTemplateName(),
                model
        );
    }


    /**
     * 对应Excel第9个 二船东更新配载计划
     *
     * @param sheet {orderNumber product quantity}
     */
    public void sendProUpdatePlan(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
        model.put("orderNumber", sheet.getOrderNumber());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_UPDATE_PLAN_EN),
                memberId,
                NotificationEnums.PRO_UPDATE_PLAN_EN.getTemplateName(),
                model
        );
    }


    /**
     * 租船协议确认/中止提醒
     * 发送给 二船东（生成租船协议操作员）
     * 对应Excel第4个
     * 模板#if($!typeCode == 1)
     * [mycrudeoil.com]Dear Customer: Your Charter Party (NO.${confirmationSheetCd},Product:${product},Quantity:${quantity})was confirmed, please check details.
     * #else
     * [mycrudeoil.com]Dear Customer: We are sorry to inform you that your Charter Party (NO.${confirmationSheetCd},Product:${product},Quantity：${quantity})was terminated, please check details.
     * #end
     *
     * @param sheet {confirmationSheetCd product quantity} {}内为必要的参数
     */
    public void sendProConfirmShipAgreement(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
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
     * [mycrudeoil.com]Dear Customer: Your Vessel Acceptance (NO.${confirmationSheetCd},Product:${product},Quantity:${quantity})was confirmed, please check details.
     * #else
     * [mycrudeoil.com]Dear Customer: We are sorry to inform you that your Vessel Acceptance(NO.${confirmationSheetCd},Product:${product},Quantity：${quantity})was terminated, please check details.
     * #end
     *
     * @param sheet {confirmationSheetCd product quantity} {}内为必要的参数
     */
    public void sendProConfirmShipConfirmation(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
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
     * [mycrudeoil.com]Dear Customer:  You were appionted as the shipping agent of the shipment (Vessel's name: $!{vesselname},Product:${product},Quantity:${quantity},Loading range:${laycanStrat}——${laycanEnd})，please  check details .
     * #else
     * [mycrudeoil.com]Dear Customer: The quantity of order{$orderNumber}(Product:${product},Quantity:${quantity}) is confirmed, please send DI.
     * #end
     *
     * @param sheet {vesselname product quantity orderNumber laycanStrat laycanEnd} {}内为必要的参数
     */
    public void sendProConfirmationShipConfirm(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("vesselname", sheet.getVesselName());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
        model.put("orderNumber", sheet.getOrderNumber());
        model.put("laycanStrat", sheet.getLaycanStrat());
        model.put("laycanEnd", sheet.getLaycanEnd());
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
     * * @param sheet {buyer agreementCd product quantity} {}内为必要的参数
     */
    public void sendProCreateShipAgreement(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("buyer", sheet.getBuyer());
        model.put("agreementCd", sheet.getAgreementCd());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
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
     *
     * @param sheet {enterprise vesselname confirmationSheetCd product quantity} {}内为必要的参数
     */
    public void sendProCreateShipConfirmation(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("enterprise", sheet.getEnterprise());
        model.put("vesselname", sheet.getVesselName());
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
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
     * [mycrudeoil.com]Dear Customer: $!{merchantNm} uploaded a Vessel's name Acceptance(NO.:$!{confirmationCD},Product:$!{product}, Quantity:$!{quantity}), please check details.
     * #elseif($!typeCode == 2)
     * [mycrudeoil.com]Dear Customer: You were appionted as the shipping agent of the shipment (Vessel's name:$!{vesselname},$!{product}, Quantity:$!{quantity},Loading range:$!{laycan})，please  check details.
     * #elseif($!typeCode == 3)
     * [mycrudeoil.com]Dear Customer: The quantity of Order:$!{orderNumber}(Product:$!{product}, Quantity:$!{quantity}) was confirmed, please send DI.
     * #else
     * [mycrudeoil.com]Dear Customer: The DI of Order:$!{orderNumber}(Product:$!{product}, Quantity:$!{quantity}) was confirmed, please prepare documents for CIQ and CHINA CUSTOMS.
     * #end
     *
     * @param sheet {merchantNm confirmationCD product quantity vesselname laycan orderNumber } {}内为必要的参数
     */
    public void sendProCreateShipConfirmationNoconfirm(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("merchantNm", sheet.getMerchantNm());
        model.put("confirmationCD", sheet.getConfirmationCD());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
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
     *
     * @param sheet {confirmationSheetCd vesselname product quantity nor} {}内为必要的参数
     */
    public void sendProPresentNor(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("vesselname", sheet.getVesselName());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
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
     *
     * @param sheet {confirmationSheetCd vesselname product quantity} {}内为必要的参数
     */
    public void sendProShipCompleted(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("vesselname", sheet.getVesselName());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
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
     *
     * @param sheet {confirmationSheetCd vesselname product quantity} {}内为必要的参数
     */
    public void sendProUpdateLoadingProgress(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("vesselname", sheet.getVesselName());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
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
     *
     * @param sheet {confirmationSheetCd vesselname product quantity} {}内为必要的参数
     */
    public void sendProUpdatePortInspection(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("vesselname", sheet.getVesselName());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_UPDATE_PORT_INSPECTION_EN),
                memberId,
                NotificationEnums.PRO_UPDATE_PORT_INSPECTION_EN.getTemplateName(),
                model
        );
    }

//    /**
//     * 模板[mycrudeoil.com]Dear Customer: Your shipment (Shipment NO:$!{confirmationSheetCd}, Vessel's name:$!{vesselname}, Product:$!{product}, Quantity:$!{quantity})has new voyage tracking information, please check details.
//     *
//     * @param sheet {confirmationSheetCd vesselname product quantity} {}内为必要的参数
//     */
//    public void sendProUpdateShipVoyage(MessageSheet sheet) {
//        Long memberId = sheet.getMemberId();
//        HashMap<String, Object> model = new HashMap<>();
//        //少什么参数，在MessageSheet里加上参数
//        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
//        model.put("vesselname", sheet.getVesselName());
//        model.put("product", sheet.getProduct());
//        model.put("quantity", sheet.getQuantity());
//        this.sendNotification(
//                this.getTitle(NotificationEnums.PRO_UPDATE_SHIP_VOYAGE),
//                memberId,
//                NotificationEnums.PRO_UPDATE_SHIP_VOYAGE.getTemplateName(),
//                model
//        );
//    }

    /**
     *船代更新船程信息提醒
     * @param sheet {confirmationSheetCd，vesselname，product,quantity}
     */
    public void sendProShippingAgentUpdateShipPlan(MessageSheet sheet){
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("confirmationSheetCd",sheet.getConfirmationSheetCd());
        model.put("vesselname",sheet.getVesselName());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());

        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_SHIPPING_AGENT_UPDATE_SHIP_PLAN_EN),
                memberId,
                NotificationEnums.PRO_SHIPPING_AGENT_UPDATE_SHIP_PLAN_EN.getTemplateName(),
                model
        );
    }
    /**
     * @param title    标题
     * @param memberId 发送给谁
     * @param tplName  模板
     * @param model    模板参数
     */
    private void sendNotification(String title, Long memberId, String tplName, Map<String, Object> model) {


        StringBuilder stringBuilder = new StringBuilder();
        String template = wechatMessageService.getTemplateContent(this.convertTemplate(tplName,model.get("typeCode")));
        for (String key : model.keySet()) {
            template = template.replace("{" + key + "}", model.get(key).toString());
        }
        stringBuilder.append(template);
        try {
            // 发送站内信
            String result = this.sendWechatMessage(wechatServer.get(this.WE_CHAT_URL).toString(), stringBuilder.toString(), memberId);
            logger.info("发送企业站内信:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发送企业站内信异常" + e);
        }
    }

    private String convertTemplate(String tplName,Object typeCode){
        if( tplName == null){
            return null;
        }
        String templateName = null;
        String[] typeCodes =new String[]{"ONE","TWO","THREE","FOUR"};
        if(typeCode==null){
            templateName = tplName.toUpperCase();
        }else{
            if(StringUtil.isNumeric(typeCode.toString())){
                templateName = (tplName + "_" + typeCodes[Integer.valueOf(typeCode.toString())]).toUpperCase();
            }else{
                logger.error("typeCode：不符合数值格式");
            }
        }
        return templateName;
    }

    /**
     *  发送微信消息
     * @param url  restFul地址 微信地址
     * @param templateContent 消息内容
     * @param memberId    用户ID
     * @return
     * @throws BizException
     */
    private String sendWechatMessage(String url, String templateContent, Long memberId) throws BizException {

        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setContent(templateContent);
            sendMessage.setMemberId(memberId);

            StringEntity se = new StringEntity(JSON.toJSONString(sendMessage));
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
            String result = "200";
            //POST的URL
            HttpPost httppost = new HttpPost(url);
            //建立HttpPost对象
            httppost.setEntity(se);
            httppost.addHeader(HTTP.CONTENT_TYPE, "application/json");
            HttpResponse response = httpConnectionManager.getHttpClient().execute(httppost);
            //发送Post,并返回一个HttpResponse对象
            if (response.getStatusLine().getStatusCode() == 200) {//如果状态码为200,就是正常返回
                result = EntityUtils.toString(response.getEntity());
                return result;
            } else {
                throw new BizException("http请求异常:" + response.getStatusLine().getStatusCode());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new BizException("字符编码不支持！");
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizException("数据读写失败！");
        }
    }

}

