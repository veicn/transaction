package com.sinochem.crude.trade.blockchain.model;

import com.sinochem.crude.trade.blockchain.domain.TDataPartner;
import com.sinochem.crude.trade.member.user.CurrentUser;

import java.io.Serializable;

public class LoginResult implements Serializable {

    private static final long serialVersionUID = -2715179518013150214L;


    /**
     * 登录用户的信息
     */
    private CurrentUser user;

    /**
     * 登录用户的网站信息
     */
    private TDataPartner tDataPartner;
    /**
     * 登录状态
     */
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public CurrentUser getUser() {
        return user;
    }

    public void setUser(CurrentUser user) {
        this.user = user;
    }

    public TDataPartner gettDataPartner() {
        return tDataPartner;
    }

    public void settDataPartner(TDataPartner tDataPartner) {
        this.tDataPartner = tDataPartner;
    }

}
