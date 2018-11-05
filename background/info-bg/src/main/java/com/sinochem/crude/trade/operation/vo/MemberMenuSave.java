package com.sinochem.crude.trade.operation.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberMenuSave implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String empId;
    
    private String menuId;

    private Integer sort;

    private String info;
    
    private List<String> menuIds;
    
    private String isPersonal;
    
    private String userInfo;
    
    private String id;
    
    private String menuDes;
    
   	private String deviceId;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getMenuDes() {
		return menuDes;
	}

	public void setMenuDes(String menuDes) {
		this.menuDes = menuDes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getIsPersonal() {
		return isPersonal;
	}

	public void setIsPersonal(String isPersonal) {
		this.isPersonal = isPersonal;
	}

	public List<String> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<String> menuIds) {
		this.menuIds = menuIds;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    

	public Map<String, Object> toMap(){
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("empId", this.empId);
		map.put("menuId", this.menuId);
		map.put("sort", this.sort);
		map.put("info", this.info);
		map.put("menuIds", this.menuIds);
		map.put("isPersonal", this.isPersonal);
		map.put("userInfo", this.userInfo);
		return map;
	}
}
