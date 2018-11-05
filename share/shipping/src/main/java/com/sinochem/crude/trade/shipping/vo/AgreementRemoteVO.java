package com.sinochem.crude.trade.shipping.vo;

import java.io.Serializable;

/**
 * @author WGP
 * @description  协议单Q88文件dubbo实体
 * @date 2018/5/4
 **/
public class AgreementRemoteVO implements Serializable {

    /**主键_ID*/
    private Long agreementId;

    /**UUID*/
    private String uuid;

    /**UUID*/
    private String demandsUuid;

    /**租船需求表ID*/
    private Long demandsId;

    /**租船代理协议编码*/
    private String agreementCd;

    /**订单编码*/
    private Long orderId;
    /**附件地址*/
    private String uploadQ88;

    /**Q88文件名称*/
    private String uploadQ88FileNm;

    /**附件地址*/
    private String uploadCp;

    public Long getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Long agreementId) {
        this.agreementId = agreementId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDemandsUuid() {
        return demandsUuid;
    }

    public void setDemandsUuid(String demandsUuid) {
        this.demandsUuid = demandsUuid;
    }

    public Long getDemandsId() {
        return demandsId;
    }

    public void setDemandsId(Long demandsId) {
        this.demandsId = demandsId;
    }

    public String getAgreementCd() {
        return agreementCd;
    }

    public void setAgreementCd(String agreementCd) {
        this.agreementCd = agreementCd;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    /**合同文件名称*/
    private String uploadCpFileNm;
}
