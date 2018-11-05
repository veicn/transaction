package com.sinochem.crude.trade.shipping.helper;

import com.alibaba.fastjson.JSON;
import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.helper.SystemNotificationHelper;
import com.sinochem.crude.trade.common.utils.MessageHttpUtil;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.constant.ExternalApi;
import com.sinochem.crude.trade.shipping.constant.UrlMapping;
import com.sinochem.crude.trade.shipping.domain.po.MessageSheet;
import com.sinochem.crude.trade.shipping.domain.po.SendMessage;
import com.sinochem.crude.trade.shipping.enums.NotificationEnums;
import com.sinochem.crude.trade.shipping.model.vo.MemberInfoVO;
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
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


@Component
public class NotificationHelper {

    private final Logger logger = LoggerFactory.getLogger(NotificationHelper.class);

    @Autowired
    private SystemNotificationHelper systemNotificationHelper;

    @Autowired
    private URLBroker messageServer;

    @Autowired
    private URLBroker shippingServer;

    @Autowired
    private MemberInfoHelper memberInfoHelper;

    @Autowired
    private HttpConnectionManager httpConnectionManager;

    @Autowired
    private URLBroker wechatServer;


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
        String url = shippingServer.get(UrlMapping.PRODUCT_BACK_DEMANDS + UrlMapping.GET_AGENTDEMANDS_PAGE_LIST).toString();
        model.put("hyperlink", url);
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
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }

        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_ALL_LINES_CAST_OFF_EN),
                memberId,
                NotificationEnums.PRO_ALL_LINES_CAST_OFF_EN.getTemplateName(),
                model
        );
    }

    /**
     * 开始装港信息7个
     *
     * @param sheet{confirmationSheetCd,product,quantity,vesselname,completedLoading} [mycrudeoil.com]Dear Customer: Your shipment (Shipment NO:$!{orderNumber},
     *                                                                                Vessel's name:$!{vesselname}, Product:$!{product}, Quantity:$!{quantity})
     *                                                                                completed loading at $!{completedLoading}, please check details.
     *                                                                                <a href="$!{hyperlink}">Please click the following website to check.</a>
     * @author wugangpeng
     */
    public void sendProCommencedLoading(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("product", sheet.getProduct());
        model.put("vesselname", sheet.getVesselName());
        model.put("quantity", sheet.getQuantity());
        model.put("completedLoading", sheet.getCompletedLoading());
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }

        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_COMMENCED_LOADING_EN),
                memberId,
                NotificationEnums.PRO_COMMENCED_LOADING_EN.getTemplateName(),
                model
        );
    }


    /**
     * 维护装港“商检文件到船”提醒
     * （Cargo documents on board）
     *
     * @param sheet{confirmationSheetCd,vesselname,product,quantity || orderNumber,product,quantity, }
     *                                                              #if($!typeCode == 1)
     *                                                              [mycrudeoil.com]Dear Customer: Your shipment
     *                                                              (Shipment NO:$!{confirmationSheetCd},
     *                                                              Vessel's name:$!{vesselname}, Product:$!{product},
     *                                                              Quantity:$!{quantity}) was on board , please check details.
     *                                                              #else
     *                                                              [mycrudeoil.com]Dear Customer:Your orderId:$!{orderNumber}(Product:$!{product}, Quantity:$!{quantity}) was loaded，please issue bill of lading.
     *                                                              #end
     * @author wugangpeng
     */
    public void sendProCargoDocumentsOnBoard(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        model.put("typeCode", sheet.getTypeCode());
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("vesselname", sheet.getVesselName());
        model.put("orderNumber", sheet.getOrderNumber());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }

        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_CARGO_DOCUMENTS_ON_BOARD_EN),
                memberId,
                NotificationEnums.PRO_CARGO_DOCUMENTS_ON_BOARD_EN.getTemplateName(),
                model
        );
    }

    /**
     * //维护装港靠泊提醒
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
    public void sendProAllFast(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        model.put("typeCode", sheet.getTypeCode());
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("vesselname", sheet.getVesselName());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
        model.put("allFast", sheet.getAllFast());
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_ALL_FASE_EN),
                memberId,
                NotificationEnums.PRO_ALL_FASE_EN.getTemplateName(),
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
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
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
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
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
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
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
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
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
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
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
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
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
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
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
        model.put("typeCode", sheet.getTypeCode());
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
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
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_CONFIRM_SHIP_CONFIRMATION_EN),
                memberId,
                NotificationEnums.PRO_CONFIRM_SHIP_CONFIRMATION_EN.getTemplateName(),
                model
        );
    }

    /**
     * 租船确认单确认提醒
     * 发送给贸易商（生成租船确认单操作员）买家（所有人）船代（所有人） 不需要在线确认
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
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
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
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_PRO_CREATE_SHIP_AGREEMENT_EN),
                memberId,
                NotificationEnums.PRO_PRO_CREATE_SHIP_AGREEMENT_EN.getTemplateName(),
                model
        );
    }

    /**
     * 生成租船确认单提醒
     * 发送给生产厂家（所有人）需要在线确认
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
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
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
        model.put("typeCode", sheet.getTypeCode());
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
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
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
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
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_SHIP_COMPLETED_EN),
                memberId,
                NotificationEnums.PRO_SHIP_COMPLETED_EN.getTemplateName(),
                model
        );
    }

    /**
     * 船代更新船程信息提醒
     *
     * @param sheet {confirmationSheetCd，vesselname，product,quantity}
     */
    public void sendProShippingAgentUpdateShipPlan(MessageSheet sheet) {
        Long memberId = sheet.getMemberId();
        HashMap<String, Object> model = new HashMap<>();
        //少什么参数，在MessageSheet里加上参数
        model.put("confirmationSheetCd", sheet.getConfirmationSheetCd());
        model.put("vesselname", sheet.getVesselName());
        model.put("product", sheet.getProduct());
        model.put("quantity", sheet.getQuantity());
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_SHIPPING_AGENT_UPDATE_SHIP_PLAN_EN),
                memberId,
                NotificationEnums.PRO_SHIPPING_AGENT_UPDATE_SHIP_PLAN_EN.getTemplateName(),
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
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
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
        String url = shippingServer.get(sheet.getHyperlink()).toString();
        if (url != null && url.length() > 0) {
            model.put("hyperlink", url);
        }
        this.sendNotification(
                this.getTitle(NotificationEnums.PRO_UPDATE_PORT_INSPECTION_EN),
                memberId,
                NotificationEnums.PRO_UPDATE_PORT_INSPECTION_EN.getTemplateName(),
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
        try {
            // 发送站内信
            String result = systemNotificationHelper.sendMessage(messageServer.get(MessageHttpUtil.MESSAGE_URL).toString(),
                    MessageHttpUtil.generateMessageParams(tplName, memberId + "", "3", title, model));
            logger.info("发送企业站内信:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发送企业站内信异常" + e);
        }
        this.sendMailMessage(title, memberId, tplName, model);
        this.sendWechatNotification(title, memberId, tplName, model);

    }


    /**
     * 发送邮件信息
     *
     * @param title
     * @param memberId
     * @param tplName
     * @param model
     */
    private void sendMailMessage(String title, Long memberId, String tplName, Map<String, Object> model) {
        MemberInfoVO memberInfoVO = null;
        try {
            memberInfoVO = memberInfoHelper.memberInfo(memberId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取账户信息异常" + e);
        }
        if (memberInfoVO != null) {
            if (StringUtil.isNotEmpty(memberInfoVO.getEmail())) {
                try {
                    // 发送邮件
                    String result = systemNotificationHelper.sendMessage(messageServer.get(MessageHttpUtil.MAIL_TPL_URL).toString(),
                            systemNotificationHelper.generateTplMailParams(tplName, title, memberInfoVO.getEmail(), model));
                    logger.info("发送邮件:" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("发送邮件异常" + e);
                }
            }
        }
    }


    /**
     * @param title    标题
     * @param memberId 发送给谁
     * @param tplName  模板
     * @param model    模板参数
     */
    private void sendWechatNotification(String title, Long memberId, String tplName, Map<String, Object> model) {
        StringBuilder stringBuilder = new StringBuilder();
        String template = wechatMessageService.getTemplateContent(this.convertTemplate(tplName, model.get("typeCode")));
        if (template != null) {
            for (String key : model.keySet()) {
                Object obj = model.get(key);
                template = template.replace("{" + key + "}", obj!=null?obj.toString():"");
            }
            stringBuilder.append(template);
            try {
                String result = this.sendWechatMessage(wechatServer.get(ExternalApi.WE_CHAT_URL).toString(), stringBuilder.toString(), memberId);
                logger.info("发送微信信息:" + result);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("发送微信信息异常" + e);
            }
        }

    }

    /**
     * 把消息模板转换成微信模板的名字
     * @param tplName  消息模板名字
     * @param typeCode  if  else 的类型
     * @return
     */
    private String convertTemplate(String tplName, Object typeCode) {
        if (tplName == null) {
            return null;
        }
        String templateName = null;
        String[] typeCodes = Constants.TYPE_CODESS;
        if (typeCode == null) {
            templateName = tplName.toUpperCase();
        } else {
            if (StringUtil.isNumeric(typeCode.toString())) {
                templateName = (tplName + "_" + typeCodes[Integer.valueOf(typeCode.toString()) - 1]).toUpperCase();
            } else {
                logger.error("typeCode：不符合数值格式");
            }
        }
        return templateName;
    }

    /**
     * 发送微信消息
     *
     * @param url             restFul地址 微信地址
     * @param templateContent 消息内容
     * @param memberId        用户ID
     * @return
     * @throws BizException
     */
    private String sendWechatMessage(String url, String templateContent, Long memberId) {
        String result = "200";
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setContent(templateContent);
            sendMessage.setMemberId(memberId);
            StringEntity se = new StringEntity(JSON.toJSONString(sendMessage), Charset.forName("UTF-8"));
            HttpPost httppost = new HttpPost(url);
            httppost.addHeader(HTTP.CONTENT_TYPE,"application/json;charset=utf-8");
            httppost.addHeader("Accept","application/json");
            httppost.setEntity(se);
            HttpResponse response = httpConnectionManager.getHttpClient().execute(httppost);
            //发送Post,并返回一个HttpResponse对象
            if (response.getStatusLine().getStatusCode() == 200) {//如果状态码为200,就是正常返回
                result = EntityUtils.toString(response.getEntity());
            } else {
                logger.error("http请求异常:" + response.getStatusLine().getStatusCode());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("字符编码不支持", e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("数据读写失败", e);
        } catch (Exception e) {
            logger.error("连接微信restful接口失败", e);
        }
        return result;
    }
}