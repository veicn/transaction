package com.sinochem.crude.trade.inspector.domain;

public class TDataPartner {
    private Integer id;

    private String webDomain;

    private Long enterpriseId;

    private String enterpriseName;

    private String logo;

    private String systemTitle;

    private String banner;

    private String bgColor;

    private String loginLogo;

    private String loginBg;

    private String loginDesc;

    private String other;

    private String remark;

    private String blockchainGateway;

    private String partnerRole;

    private String aliveFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWebDomain() {
        return webDomain;
    }

    public void setWebDomain(String webDomain) {
        this.webDomain = webDomain == null ? null : webDomain.trim();
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getSystemTitle() {
        return systemTitle;
    }

    public void setSystemTitle(String systemTitle) {
        this.systemTitle = systemTitle == null ? null : systemTitle.trim();
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner == null ? null : banner.trim();
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor == null ? null : bgColor.trim();
    }

    public String getLoginLogo() {
        return loginLogo;
    }

    public void setLoginLogo(String loginLogo) {
        this.loginLogo = loginLogo == null ? null : loginLogo.trim();
    }

    public String getLoginBg() {
        return loginBg;
    }

    public void setLoginBg(String loginBg) {
        this.loginBg = loginBg == null ? null : loginBg.trim();
    }

    public String getLoginDesc() {
        return loginDesc;
    }

    public void setLoginDesc(String loginDesc) {
        this.loginDesc = loginDesc == null ? null : loginDesc.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getBlockchainGateway() {
        return blockchainGateway;
    }

    public void setBlockchainGateway(String blockchainGateway) {
        this.blockchainGateway = blockchainGateway == null ? null : blockchainGateway.trim();
    }

    public String getPartnerRole() {
        return partnerRole;
    }

    public void setPartnerRole(String partnerRole) {
        this.partnerRole = partnerRole == null ? null : partnerRole.trim();
    }

    public String getAliveFlag() {
        return aliveFlag;
    }

    public void setAliveFlag(String aliveFlag) {
        this.aliveFlag = aliveFlag == null ? null : aliveFlag.trim();
    }
}