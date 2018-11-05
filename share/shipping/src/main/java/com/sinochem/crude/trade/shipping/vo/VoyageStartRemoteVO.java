package com.sinochem.crude.trade.shipping.vo;

import java.io.Serializable;

/**
 * @author WGP
 * @descriptioncrude-trade
 * @date 2018/5/4
 **/
public class VoyageStartRemoteVO implements Serializable {
    /** UUID */
    private String uuid;

    /** 船舶确认单ID */
    private Long shipConfirmationSheetId;

    /** 订单ID */
    private Long orderId;

    /** 文件名称 */
    private String accessoryFileNm;

    /** 附件地址 */
    private String accessory;

    private String di;

    private String diFileNm;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getShipConfirmationSheetId() {
        return shipConfirmationSheetId;
    }

    public void setShipConfirmationSheetId(Long shipConfirmationSheetId) {
        this.shipConfirmationSheetId = shipConfirmationSheetId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getAccessoryFileNm() {
        return accessoryFileNm;
    }

    public void setAccessoryFileNm(String accessoryFileNm) {
        this.accessoryFileNm = accessoryFileNm;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getDi() {
        return di;
    }

    public void setDi(String di) {
        this.di = di;
    }

    public String getDiFileNm() {
        return diFileNm;
    }

    public void setDiFileNm(String diFileNm) {
        this.diFileNm = diFileNm;
    }
}
