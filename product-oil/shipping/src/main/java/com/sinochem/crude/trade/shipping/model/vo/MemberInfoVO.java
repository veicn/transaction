package com.sinochem.crude.trade.shipping.model.vo;

/**
 * @author WGP
 * @descriptioncrude-trade-product_oil
 * @date 2018/4/23
 **/
public class MemberInfoVO {
    /**
     * 用户ID
     */
    private Long memberId;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 邮箱地址
     */
    private String email;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
