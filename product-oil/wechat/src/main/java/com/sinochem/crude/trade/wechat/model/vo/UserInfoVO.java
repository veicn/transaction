package com.sinochem.crude.trade.wechat.model.vo;

/**
 * author sunjianbo
 * date 2018/3/17 11:47
 */
public class UserInfoVO {
    private String userName;
    private String password;
    private String openid;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

}
