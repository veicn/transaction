package com.sinochem.crude.trade.member.enums;

/**
 * 
 * @author Leo
 *
 */
public enum MemberRoles {

	/**
	 * 最高权限，可以分配权限,只能用在member模块,这个权限是系统默认的，是不能使用的，写在这里，仅仅是占一个位置
	 */
	ADMIN("A", "超级管理员"),
	/**
	 * 一个企业最高权限，可以分配企业权限，只能用在member模块，是企业注册后默认有的角色，可以用于管理账户
	 */
	E_ADMIN("EA", "企业管理员"),
	/**
	 * 平台对于用户资料的审核角色
	 */
	MEMBER_AUDIT("MA", "会员模块审核"),
	/**
	 * 只能在原油大模块中实现
	 */
	PurchasingAgent("P", "采购经纪人"),
	/**
	 * 只能在原油大模块中实现
	 */
	SalesAgent("S", "销售经纪人"),
	/**
	 * 可以在原油和船务中使用
	 */
	Financer("F", "财务"),
	/**
	 * 只能在原油大模块中实现
	 */
	OrderTaker("OT", "订单执行人"),
	/**
	 * 在船务板块使用
	 */
	ShipTenant("ST", "租船货主"),
	/**
	 * 在船务板块使用
	 */
	ShipAgent("SO", "船代"),
	/**
	 * 在船务板块使用
	 */
	ShipCaptain("SC", "船长");

	MemberRoles(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 权限编码
	 */
	private String code;

	/**
	 * 权限描述
	 */
	private String name;

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
