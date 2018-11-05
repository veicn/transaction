package com.sinochem.crude.trade.broker.domain.xml;

import javax.xml.bind.annotation.XmlElement;

/**
 * @Author: fengzk
 * @CreateDate: 2018/6/20 14:22
 * @Version: [v1.0]
 */
public class LicenseDocuXMLVO {
    private String DocuCode;
    @XmlElement(name="DocuCode")
    public String getDocuCode() {
        return DocuCode;
    }

    public void setDocuCode(String docuCode) {
        DocuCode = docuCode;
    }
    @XmlElement(name="CertCode")
    public String getCertCode() {
        return CertCode;
    }

    public void setCertCode(String certCode) {
        CertCode = certCode;
    }

    private String CertCode;
}
