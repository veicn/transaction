package com.sinochem.crude.trade.member.domain;

import com.sinochem.crude.trade.member.model.udbvo.PersonUdbVo;

import java.util.Date;

/**
 * @author Administrator
 *
 */
public class Person {
  
	/**
	 * 用户id
	 */
	private Long id;

	/**
	 * 用户编号，uuid
	 */
	private String code;

	/**
	 * 用户名称
	 */
	private String name;

	/**
	 * 备注
	 */
	private String memo;

	/**
	 * 证件类型
	 */
	private String cardType;

	/**
	 * 证件号码
	 */
	private String cardNo;

	/**
	 * 证件图片ID
	 * （证件类型：SFZ/HZ）
	 */
	private String cardImg;

	/**
	 * 人员头像图片ID
	 * （证件类型：RYTX）
	 */
	private String headImg;

	/**
	 * 人员邮箱地址
	 */
	private String email;
	/**
	 * 人员邮箱地址
	 */
	private String mobile;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 创建用户
	 */
	private String createUser;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 更新用户
	 */
	private String updateUser;
	
	/**
	 * members表关联ID
	 */
	private Long memberId;
	//详细地址
	private String addressDetail;
	//性别
	private String gender;
	//生日
	private Date birth;
	//国家编码
	private String countryCode;
	//省编码
	private String provinceCode;
	//城市编码
	private String cityCode;
	//地区编码
	private String areaCode;
	//邮编
	private String zipCode;


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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getCardImg() {
		return cardImg;
	}

	public void setCardImg(String cardImg) {
		this.cardImg = cardImg;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}


	public void transferPersonUdbVoToPersonVo(PersonUdbVo personUdbVo){
		this.setAddressDetail(personUdbVo.getAddressDetail());
		this.setAreaCode(personUdbVo.getAreaCode());
		this.setBirth(personUdbVo.getBirth()==null?null:new Date(personUdbVo.getBirth()));
		this.setCardNo(personUdbVo.getCardNo());
		this.setCardType(personUdbVo.getCardType());
		this.setCityCode(personUdbVo.getCityCode());
		this.setCountryCode(personUdbVo.getCountryCode());
		this.setGender(personUdbVo.getGender());
		this.setHeadImg(personUdbVo.getHeadUrl());
		this.setMemo(personUdbVo.getMemo());
		this.setProvinceCode(personUdbVo.getProvinceCode());
		this.setName(personUdbVo.getRealName());
		this.setZipCode(personUdbVo.getZipCode());
	}
}