package com.sinochem.crude.trade.transport.model;

public class MemberInfoVO {
    /**
     * 用户ID
     */
    private Long memberId;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 用户名
     */
    private String username;

    /**
     * 英文名
     */
    private String engLishName;
    
    
    public String getEngLishName() {
		return engLishName;
	}

	public void setEngLishName(String engLishName) {
		this.engLishName = engLishName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
