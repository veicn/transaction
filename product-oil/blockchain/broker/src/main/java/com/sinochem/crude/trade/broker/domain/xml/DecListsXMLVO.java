package com.sinochem.crude.trade.broker.domain.xml;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/6/20 11:27
 * @Version: [v1.0]
 */
public class DecListsXMLVO {

    @XmlElement(name="DecList")
    public  List<DecListXMLVO> getDecLists() {
        return DecList;
    }

    public void setDecLists( List<DecListXMLVO> decList) {
        DecList = decList;
    }

    private List<DecListXMLVO> DecList;
}
