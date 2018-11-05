package com.sinochem.crude.trade.member.model.udbvo;

import com.sinochem.crude.trade.member.domain.Person;

/**
 * Created by Summer on 2018-07-27.
 */
public class PersonUdbVo {


    private String accountUid;

    private String realName;

    private String countryCode;

    private String provinceCode;

    private String cityCode;

    private String areaCode;

    private String addressDetail;

    private String zipCode;

    private Long birth;

    private String gender;

    private String cardType;

    private String cardNo;
    private String cardImg;


    private String userQq;

    private String memo;

    private String extraInfo;

    private String headUrl;

    private String nickName;

    private String webSite;

    public String getCardImg() {
        return cardImg;
    }

    public void setCardImg(String careImg) {
        this.cardImg = careImg;
    }

    public String getAccountUid() {
        return accountUid;
    }

    public void setAccountUid(String accountUid) {
        this.accountUid = accountUid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Long getBirth() {
        return birth;
    }

    public void setBirth(Long birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }


    public void transferPersonVoToPersonUdbVo(Person person) {
        this.setAddressDetail(person.getAddressDetail());
        this.setAreaCode(person.getAreaCode());
        this.setBirth(person.getBirth() == null ? null : person.getBirth().getTime());
        this.setCardNo(person.getCardNo());
        this.setCardType(person.getCardType());
        this.setCityCode(person.getCityCode());
        this.setCountryCode(person.getCountryCode());
        this.setGender(person.getGender());
        this.setHeadUrl(person.getHeadImg());
        this.setMemo(person.getMemo());
        this.setProvinceCode(person.getProvinceCode());
        this.setRealName(person.getName());
        this.setZipCode(person.getZipCode());
        this.setCardImg(person.getCardImg());
    }
}
