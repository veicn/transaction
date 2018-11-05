package com.sinochem.crude.trade.order.remote;

import java.io.Serializable;

/**
 * Created by sijliu on 08/12/2017.
 */
public class UserVo  implements Serializable {

    /**
     * 登录用户的用户编号
     */
    private Long memberId;
    /**
     * 登录用户的企业id
     */
    private Long epMemberId;
    /**
     * 登录用户的显示名称
     */
    private String name;

    /**
     * 已经经过审批的模块
     */
    private int[] sys;

    /**
     * 用户的权限
     */
    private String[] roles;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getEpMemberId() {
        return epMemberId;
    }

    public void setEpMemberId(Long epMemberId) {
        this.epMemberId = epMemberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getSys() {
        return sys;
    }

    public void setSys(int[] sys) {
        this.sys = sys;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
