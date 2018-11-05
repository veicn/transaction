package com.sinochem.crude.trade.member.domain.query;

import com.sinochem.crude.trade.member.domain.Person;

/**
 * 人员信息查询实体
 * Created by bbt on 2017/12/15.
 */
public class PersonQuery extends Person {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 非此id
     */
    private Long idNot;

    /**
     * 根据名称模糊查询
     */
    private String nameLike;

    private String mMobile;

    private String mEmail;

    private Long mid;

    /**
     * 企业名
     */
    private String enterpriseName;

    /**
     * 企业全名
     */
    private String fullName;

    /**
     * 英文名
     */
    private String englishName;

    /**
     * 企业id
     */
    private Long fid;

    public Long getIdNot() {
        return idNot;
    }

    private String roleCode;

    public void setIdNot(Long idNot) {
        this.idNot = idNot;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public String getmMobile() {
        return mMobile;
    }

    public void setmMobile(String mMobile) {
        this.mMobile = mMobile;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}
