package com.sinochem.crude.trade.listed.domain;

public class DemandRelevanter {
	
	/**
	 * PK
	 */
	private Long id;

	/**
	 * 买家  卖家 贸易商id
	 */
	private Long eMemberId;

	/**
	 * 需求id
	 */
	private Long demandId;

	/**
	 * 联系人
	 */
	private String contacter;

	/**
	 * 电话
	 */
	private String phoneNo;

	/**
	 * 邮箱
	 */
	private String famil;

	/**
	 * 传真
	 */
	private String fax;

	/**
	 * 是否公开
	 */
	private Byte share;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 企业名称
	 */
	private String enterpriseName;

	/**
	 * 企业地址
	 */
	private String enterpriseAddress;

	/**
	 * 交易员id
	 */
	private Long dealerId;

	/**
	 * 交易员名称
	 */
	private String dealerName;

	/** getters and setters */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEMemberId() {
		return eMemberId;
	}

	public void setEMemberId(Long eMemberId) {
		this.eMemberId = eMemberId;
	}

	public Long getDemandId() {
		return demandId;
	}

	public void setDemandId(Long demandId) {
		this.demandId = demandId;
	}

	public String getContacter() {
		return contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getFamil() {
		return famil;
	}

	public void setFamil(String famil) {
		this.famil = famil;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Byte getShare() {
		return share;
	}

	public void setShare(Byte share) {
		this.share = share;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getEnterpriseAddress() {
		return enterpriseAddress;
	}

	public void setEnterpriseAddress(String enterpriseAddress) {
		this.enterpriseAddress = enterpriseAddress;
	}

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
}
