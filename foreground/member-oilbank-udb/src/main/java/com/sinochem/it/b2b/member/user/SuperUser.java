package com.sinochem.it.b2b.member.user;

public class SuperUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7179436670565902773L;

	public SuperUser(User user) {
		this.user = user;
	}

	private User user;

	public User getUser() {
		return user;
	}

	public void setRoles(String[] roles) {
		user.setRoles(roles);
	}

	public String[] getRoles() {
		return user.getRoles();
	}

	public boolean isPlatformUser() {
		return user.isPlatformUser();
	}

	/**
	 * 是否代理，如果不是代理，就是自己只能操作自己
	 */
	public boolean isProxy() {
		return user.isProxy();
	}

	public Long getMemberId() {
		return user.getMemberId();
	}

	public void setMemberId(Long memberId) {
		user.setMemberId(memberId);
	}

	public Long getpMemberId() {
		return user.getpMemberId();
	}

	public void setpMemberId(Long pMemberId) {
		user.setpMemberId(pMemberId);
	}

	public String getName() {
		return user.getName();
	}

	public void setName(String name) {
		user.setName(name);
	}

	public String getLastToken() {
		return user.getLastToken();
	}

	public void setLastToken(String lastToken) {
		user.setLastToken(lastToken);
	}

}
