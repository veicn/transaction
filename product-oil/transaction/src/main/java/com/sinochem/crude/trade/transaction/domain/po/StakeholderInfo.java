package com.sinochem.crude.trade.transaction.domain.po;

import com.sinochem.crude.trade.common.base.BasePO;

/**
 * 干系企业信息，关联SaleSheet或BiddingSheet，分别代表卖家和买家
 * @author Yichen Zhao
 * date: 20180226
 */
public class StakeholderInfo extends BasePO {

    /**
     * 干系企业的公司ID
     */
    private Long enterpriseId;

    /**
     * 干系企业名称通过member模块带出，需要请求接口防止数据不一致
     */

    /**
     * 干系企业地址
     */
    private String address;

    /**
     * 交易员
     */
    private String traderName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 传真
     */
    private String fax;

    /** getters and setters */
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
