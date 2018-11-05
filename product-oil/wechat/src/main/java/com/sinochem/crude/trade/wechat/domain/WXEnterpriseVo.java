package com.sinochem.crude.trade.wechat.domain;

import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;

/**
 * @Author: fengzk
 * @CreateDate: 2018/5/7 11:01
 * @Version: [v1.0]
 */
public class WXEnterpriseVo extends EnterpriseVo {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
}
