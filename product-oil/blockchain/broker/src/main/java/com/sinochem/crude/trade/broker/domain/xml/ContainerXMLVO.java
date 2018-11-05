package com.sinochem.crude.trade.broker.domain.xml;

import javax.xml.bind.annotation.XmlElement;

/**
 * @Author: fengzk
 * @CreateDate: 2018/6/20 14:14
 * @Version: [v1.0]
 */
public class ContainerXMLVO {

    private String ContainerId;
    private String ContainerMd;
    private String ContainerWt;
    @XmlElement(name="ContainerId")
    public String getContainerId() {
        return ContainerId;
    }

    public void setContainerId(String containerId) {
        ContainerId = containerId;
    }
    @XmlElement(name="ContainerMd")
    public String getContainerMd() {
        return ContainerMd;
    }

    public void setContainerMd(String containerMd) {
        ContainerMd = containerMd;
    }
    @XmlElement(name="ContainerWt")
    public String getContainerWt() {
        return ContainerWt;
    }

    public void setContainerWt(String containerWt) {
        ContainerWt = containerWt;
    }
}
