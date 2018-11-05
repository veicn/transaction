package com.sinochem.crude.trade.operation.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MemMenuSmem implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 序号 */
	private String id;

	/** 菜单id */
	private String menuId;

	/** 会员id */
	private String empId;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/** 排序 */
	private Integer sort;

	/** 菜单类型 */
	private String menuType;

	/** 菜单描述 */
	private String menuDes;

	/** 菜单图标地址 */
	private String menuIcon;

	/** 菜单指向地址 */
	private String menuUrl;

	/** 菜单参数 */
	private String menuArgu;

	/** 市场编号 */
	private String marketId;

	/** 语言类型（ZH：中文，EN：英文） */
	private String langVer;

	/** 当前有效状态 */
	private String aliveFlag;

	/** 创建者 */
	private String createUser;

	/** 创建时间 */
	private java.util.Date createDate;

	/** 修改者 */
	private String updateUser;

	/** 修改时间 */
	private java.util.Date updateDate;

	/** 序号 */
	public void setId(String id) {
		this.id = id;
	}

	/** 序号 */
	public String getId() {
		return this.id;
	}

	/** 菜单id */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/** 菜单id */
	public String getMenuId() {
		return this.menuId;
	}

	/** 排序 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/** 排序 */
	public Integer getSort() {
		return this.sort;
	}

	/** 菜单类型 */
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	/** 菜单类型 */
	public String getMenuType() {
		return this.menuType;
	}

	/** 菜单描述 */
	public void setMenuDes(String menuDes) {
		this.menuDes = menuDes;
	}

	/** 菜单描述 */
	public String getMenuDes() {
		return this.menuDes;
	}

	/** 菜单图标地址 */
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	/** 菜单图标地址 */
	public String getMenuIcon() {
		return this.menuIcon;
	}

	/** 菜单指向地址 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	/** 菜单指向地址 */
	public String getMenuUrl() {
		return this.menuUrl;
	}

	/** 菜单参数 */
	public void setMenuArgu(String menuArgu) {
		this.menuArgu = menuArgu;
	}

	/** 菜单参数 */
	public String getMenuArgu() {
		return this.menuArgu;
	}

	/** 市场编号 */
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	/** 市场编号 */
	public String getMarketId() {
		return this.marketId;
	}

	/** 语言类型（ZH：中文，EN：英文） */
	public void setLangVer(String langVer) {
		this.langVer = langVer;
	}

	/** 语言类型（ZH：中文，EN：英文） */
	public String getLangVer() {
		return this.langVer;
	}

	/** 当前有效状态 */
	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}

	/** 当前有效状态 */
	public String getAliveFlag() {
		return this.aliveFlag;
	}

	/** 创建者 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/** 创建者 */
	public String getCreateUser() {
		return this.createUser;
	}

	/** 创建时间 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	/** 创建时间 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	/** 修改者 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/** 修改者 */
	public String getUpdateUser() {
		return this.updateUser;
	}

	/** 修改时间 */
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	/** 修改时间 */
	public java.util.Date getUpdateDate() {
		return this.updateDate;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", this.id);
		map.put("menuId", this.menuId);
		map.put("empId", this.empId);
		map.put("sort", this.sort);
		map.put("menuType", this.menuType);
		map.put("menuDes", this.menuDes);
		map.put("menuIcon", this.menuIcon);
		map.put("menuUrl", this.menuUrl);
		map.put("menuArgu", this.menuArgu);
		map.put("marketId", this.marketId);
		map.put("langVer", this.langVer);
		map.put("aliveFlag", this.aliveFlag);
		map.put("createUser", this.createUser);
		map.put("createDate", this.createDate);
		map.put("updateUser", this.updateUser);
		map.put("updateDate", this.updateDate);
		return map;
	}
}