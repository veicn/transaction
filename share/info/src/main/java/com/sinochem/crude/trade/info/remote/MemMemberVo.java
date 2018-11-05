package com.sinochem.crude.trade.info.remote;


import java.io.Serializable;

public class MemMemberVo implements Serializable{

    private static final long serialVersionUID = -1460341864817772252L;

    /**主键id*/
    private String memberId;

    /**中文名称*/
    private String memberName;

    /**简称*/
    private String memberNameShort;

    /**登录名*/
    private String loginName;

    /**昵称*/
    private String nickName;

    /**头像地址*/
    private String imgurl;

    /**签名*/
    private String personalNote;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberNameShort() {
        return memberNameShort;
    }

    public void setMemberNameShort(String memberNameShort) {
        this.memberNameShort = memberNameShort;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getPersonalNote() {
        return personalNote;
    }

    public void setPersonalNote(String personalNote) {
        this.personalNote = personalNote;
    }
}
