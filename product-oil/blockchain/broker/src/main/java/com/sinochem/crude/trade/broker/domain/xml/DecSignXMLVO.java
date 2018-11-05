package com.sinochem.crude.trade.broker.domain.xml;

import javax.xml.bind.annotation.XmlElement;

/**
 * @Author: fengzk
 * @CreateDate: 2018/6/20 14:28
 * @Version: [v1.0]
 */
public class DecSignXMLVO {

    private String ClientSeqNo;
    private String CopCode;
    private String ICCode;
    private String OperType;
    private String OperName;
    private String Sign;
    private String SignDate;
    private String HostId;
    @XmlElement(name="ClientSeqNo")
    public String getClientSeqNo() {
        return ClientSeqNo;
    }

    public void setClientSeqNo(String clientSeqNo) {
        ClientSeqNo = clientSeqNo;
    }
    @XmlElement(name="CopCode")
    public String getCopCode() {
        return CopCode;
    }

    public void setCopCode(String copCode) {
        CopCode = copCode;
    }
    @XmlElement(name="ICCode")
    public String getICCode() {
        return ICCode;
    }

    public void setICCode(String ICCode) {
        this.ICCode = ICCode;
    }
    @XmlElement(name="OperType")
    public String getOperType() {
        return OperType;
    }

    public void setOperType(String operType) {
        OperType = operType;
    }
    @XmlElement(name="OperName")
    public String getOperName() {
        return OperName;
    }

    public void setOperName(String operName) {
        OperName = operName;
    }
    @XmlElement(name="Sign")
    public String getSign() {
        return Sign;
    }

    public void setSign(String sign) {
        Sign = sign;
    }
    @XmlElement(name="SignDate")
    public String getSignDate() {
        return SignDate;
    }

    public void setSignDate(String signDate) {
        SignDate = signDate;
    }
    @XmlElement(name="HostId")
    public String getHostId() {
        return HostId;
    }

    public void setHostId(String hostId) {
        HostId = hostId;
    }
    @XmlElement(name="Certificate")
    public String getCertificate() {
        return Certificate;
    }

    public void setCertificate(String certificate) {
        Certificate = certificate;
    }

    private String Certificate;


}
