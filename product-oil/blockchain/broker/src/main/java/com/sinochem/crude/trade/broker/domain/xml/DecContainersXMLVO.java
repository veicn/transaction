package com.sinochem.crude.trade.broker.domain.xml;

import javax.xml.bind.annotation.XmlElement;

/**
 * @Author: fengzk
 * @CreateDate: 2018/6/20 14:13
 * @Version: [v1.0]
 */
public class DecContainersXMLVO {

private ContainerXMLVO Container;
    @XmlElement(name="Container")
    public ContainerXMLVO getContainer() {
        return Container;
    }

    public void setContainer(ContainerXMLVO container) {
        Container = container;
    }
}
