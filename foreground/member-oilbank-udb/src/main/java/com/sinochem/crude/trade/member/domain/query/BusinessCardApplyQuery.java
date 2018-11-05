package com.sinochem.crude.trade.member.domain.query;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.JPinyin.ChangeToPinYinJP;
import com.sinochem.crude.trade.member.domain.BusinessCardApply;

/**
 *
 * Created by AlterEgo on 2018/5/4.
 */
public class BusinessCardApplyQuery extends BusinessCardApply {

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

    //扩暂字段
    /**
     * 昵称首字母
     */
    private String initialsickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getProfessionalName() {
        return professionalName;
    }

    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName;
    }

    public String getNickNameEn() {
        return nickNameEn;
    }

    public void setNickNameEn(String nickNameEn) {
        this.nickNameEn = nickNameEn;
    }

    public String getOrgNameEn() {
        return orgNameEn;
    }

    public void setOrgNameEn(String orgNameEn) {
        this.orgNameEn = orgNameEn;
    }

    public String getProfessionalNameEn() {
        return professionalNameEn;
    }

    public void setProfessionalNameEn(String professionalNameEn) {
        this.professionalNameEn = professionalNameEn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInitialsickName() {
        if(StringUtil.isEmpty(this.nickName)){
            return "";
        }else {
            return ChangeToPinYinJP.changeToGetShortPinYin(this.nickName.substring(0,1));
        }
    }
}
