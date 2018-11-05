package com.sinochem.crude.trade.member.domain;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.JPinyin.ChangeToPinYinJP;

import java.util.Date;

public class BusinessCards {
    /**
     *
     */
    private Long id;

    /**
     *会员id
     */
    private Long memberId;

    /**
     *昵称
     */
    private String nickName;

    /**
     *公司名
     */
    private String orgName;

    /**
     *职称
     */
    private String professionalName;

    /**
     *英文昵称
     */
    private String nickNameEn;

    /**
     * 英文公司名
     */
    private String orgNameEn;

    /**
     *英文职称
     */
    private String professionalNameEn;

    /**
     *电话
     */
    private String phone;

    /**
     *邮箱
     */
    private String email;

    /**
     *删除标识
     */
    private boolean delFlg;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *创建人
     */
    private Long creater;

    /**
     *修改时间
     */
    private Date updateTime;

    /**
     *修改人
     */
    private Long updater;

    //扩暂字段
    /**
     * 昵称首字母
     */
    private String initialsickName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getProfessionalName() {
        return professionalName;
    }

    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName == null ? null : professionalName.trim();
    }

    public String getNickNameEn() {
        return nickNameEn;
    }

    public void setNickNameEn(String nickNameEn) {
        this.nickNameEn = nickNameEn == null ? null : nickNameEn.trim();
    }

    public String getOrgNameEn() {
        return orgNameEn;
    }

    public void setOrgNameEn(String orgNameEn) {
        this.orgNameEn = orgNameEn == null ? null : orgNameEn.trim();
    }

    public String getProfessionalNameEn() {
        return professionalNameEn;
    }

    public void setProfessionalNameEn(String professionalNameEn) {
        this.professionalNameEn = professionalNameEn == null ? null : professionalNameEn.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public boolean getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(boolean delFlg) {
        this.delFlg = delFlg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public String getInitialsickName() {
        if(StringUtil.isEmpty(this.nickName)){
            return "";
        }else {
            return ChangeToPinYinJP.changeToGetShortPinYin(this.nickName.substring(0,1));
        }
    }
}