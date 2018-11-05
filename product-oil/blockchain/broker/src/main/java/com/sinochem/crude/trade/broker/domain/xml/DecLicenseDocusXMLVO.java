package com.sinochem.crude.trade.broker.domain.xml;

import javax.xml.bind.annotation.XmlElement;

/**
 * @Author: fengzk
 * @CreateDate: 2018/6/20 14:21
 * @Version: [v1.0]
 */
public class DecLicenseDocusXMLVO {
    private LicenseDocuXMLVO LicenseDocu;
    @XmlElement(name="LicenseDocu")
    public LicenseDocuXMLVO getLicenseDocu() {
        return LicenseDocu;
    }

    public void setLicenseDocu(LicenseDocuXMLVO licenseDocu) {
        LicenseDocu = licenseDocu;
    }
}
