package com.sinochem.crude.trade.shipping.vo;

import java.io.Serializable;

/**
 * @author WGP
 * @descriptioncrude-trade
 * @date 2018/5/4
 **/
public class ConfirmationSheetRemoteVO implements Serializable {

    /** UUID */
    private String uuid;

    /**
     * 租船协议uuid
     */
    private String agreementUuid;


    /** 订单编码 */
    private Long orderId;

    /** 订单种类 */
    private String tradeTerms;


    /** 需求单UUID*/
    private String demandsUuid;

    /** 附件地址 */
    private String uploadQ88;

    /** Q88文件名称 */
    private String uploadQ88FileNm;

    /** 附件地址 */
    private String uploadCp;

    /** 合同文件名称 */
    private String uploadCpFileNm;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAgreementUuid() {
        return agreementUuid;
    }

    public void setAgreementUuid(String agreementUuid) {
        this.agreementUuid = agreementUuid;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getTradeTerms() {
        return tradeTerms;
    }

    public void setTradeTerms(String tradeTerms) {
        this.tradeTerms = tradeTerms;
    }

    public String getDemandsUuid() {
        return demandsUuid;
    }

    public void setDemandsUuid(String demandsUuid) {
        this.demandsUuid = demandsUuid;
    }

    public String getUploadQ88() {
        return uploadQ88;
    }

    public void setUploadQ88(String uploadQ88) {
        this.uploadQ88 = uploadQ88;
    }

    public String getUploadQ88FileNm() {
        return uploadQ88FileNm;
    }

    public void setUploadQ88FileNm(String uploadQ88FileNm) {
        this.uploadQ88FileNm = uploadQ88FileNm;
    }

    public String getUploadCp() {
        return uploadCp;
    }

    public void setUploadCp(String uploadCp) {
        this.uploadCp = uploadCp;
    }

    public String getUploadCpFileNm() {
        return uploadCpFileNm;
    }

    public void setUploadCpFileNm(String uploadCpFileNm) {
        this.uploadCpFileNm = uploadCpFileNm;
    }
}
