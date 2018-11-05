package com.sinochem.crude.trade.member.remote.vo;

import java.io.Serializable;

/**
 * @Description:
 * @Author : chenyz
 * @Date: 2017/11/22
 */
public class PersonVo implements Serializable {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 证件类型
     */
    private String cardType;

    /**
     * 证件号码
     */
    private String cardNo;
    /**
     * 人员邮箱地址
     */
    private String email;
    /**
     * 人员邮箱地址
     */
    private String mobile;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
