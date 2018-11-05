package com.sinochem.crude.trade.broker.domain.xml;

import javax.xml.bind.annotation.XmlElement;

/**
 * @Author: fengzk
 * @CreateDate: 2018/6/20 14:25
 * @Version: [v1.0]
 */
public class DecFreeTxtXMLVO {
    private String BonNo;
    private String CusFie;
    private String DecBpNo;
    private String DecNo;
    private String RelId;
    private String RelManNo;
    private String VoyNo;
    @XmlElement(name="BonNo")
    public String getBonNo() {
        return BonNo;
    }

    public void setBonNo(String bonNo) {
        BonNo = bonNo;
    }
    @XmlElement(name="CusFie")
    public String getCusFie() {
        return CusFie;
    }

    public void setCusFie(String cusFie) {
        CusFie = cusFie;
    }
    @XmlElement(name="DecBpNo")
    public String getDecBpNo() {
        return DecBpNo;
    }

    public void setDecBpNo(String decBpNo) {
        DecBpNo = decBpNo;
    }
    @XmlElement(name="DecNo")
    public String getDecNo() {
        return DecNo;
    }

    public void setDecNo(String decNo) {
        DecNo = decNo;
    }
    @XmlElement(name="RelId")
    public String getRelId() {
        return RelId;
    }

    public void setRelId(String relId) {
        RelId = relId;
    }
    @XmlElement(name="RelManNo")
    public String getRelManNo() {
        return RelManNo;
    }

    public void setRelManNo(String relManNo) {
        RelManNo = relManNo;
    }
    @XmlElement(name="VoyNo")
    public String getVoyNo() {
        return VoyNo;
    }

    public void setVoyNo(String voyNo) {
        VoyNo = voyNo;
    }
}
