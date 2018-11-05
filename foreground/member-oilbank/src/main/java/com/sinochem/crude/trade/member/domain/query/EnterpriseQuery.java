package com.sinochem.crude.trade.member.domain.query;

import com.sinochem.crude.trade.member.domain.Enterprise;

/**
 * 企业查询条件
 * Created by bbt on 2017/12/15.
 */
public class EnterpriseQuery extends Enterprise {

    /**
     * 非此Id
     */
    private Long idNot;

    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 名称不为空
     */
    private String nameNotNull;
    /**
     * 根据名称模糊查询
     */
    private String nameLike;

    private String orderBy;

    private String credentialsCode;

    /**
	 * 详细地址
	 */
	private String addressDetail;

    public Long getIdNot() {
        return idNot;
    }

    public void setIdNot(Long idNot) {
        this.idNot = idNot;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameNotNull() {
        return nameNotNull;
    }

    public void setNameNotNull(String nameNotNull) {
        this.nameNotNull = nameNotNull;
    }

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    
    public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

    public String getCredentialsCode() {
        return credentialsCode;
    }

    public void setCredentialsCode(String credentialsCode) {
        this.credentialsCode = credentialsCode;
    }
}
