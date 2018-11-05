package com.sinochem.crude.trade.listed.model.vo;

/**
 * 页面展示数据
 */
public class TradeChainListVo {

    // 日期
    String orderDate;

    // id
    String uuid;

    //编号
    String serialNumber;

    // 供应商
    String seller;

    // 供应商id
    String sellerId;

    //贸易商
    String maoyi;

    //贸易商
    String maoyiId;

    // 购买商
    String buyer;

    // 购买商
    String buyerId;

    // 当前登录人企业id
    String merberId;

    //油种
    String OilType;

    Integer aliveFlag;

    //有效期
    String youxiao;

    public String getOrderDate() {
        return orderDate;
    }

    public String getUuid() {
        return uuid;
    }

    public String getSeller() {
        return seller;
    }

    public String getBuyer() {
        return buyer;
    }

    public String getOilType() {
        return OilType;
    }

    public Integer getAliveFlag() {
        return aliveFlag;
    }

    public String getYouxiao() {
        return youxiao;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public void setOilType(String oilType) {
        OilType = oilType;
    }

    public void setAliveFlag(Integer aliveFlag) {
        this.aliveFlag = aliveFlag;
    }

    public void setYouxiao(String youxiao) {
        this.youxiao = youxiao;
    }

    public String getMaoyi() {
        return maoyi;
    }

    public void setMaoyi(String maoyi) {
        this.maoyi = maoyi;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getMaoyiId() {
        return maoyiId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public String getMerberId() {
        return merberId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public void setMaoyiId(String maoyiId) {
        this.maoyiId = maoyiId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public void setMerberId(String merberId) {
        this.merberId = merberId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
