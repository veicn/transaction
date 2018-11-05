package com.sinochem.crude.trade.transport.query;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.SimplePageInfo;

public class BasicTariffQuery extends QueryBase{

	private static final long serialVersionUID = 1L;
	/** 年份*/
	String year;
	/** 第一装港*/
	String loadPort1;
	/** 第二装港*/
	String loadPort2;
	/** 第三装港*/
	String loadPort3;
	/** 第一卸港*/
	String unloadPort1;
	/** 第二卸港*/
	String unloadPort2;
	/** 第三卸港*/
	String unloadPort3;
	
	/**参考日期*/
	Date referenceDate;
	/**船型*/
	String shipType;
	
	@Override
	public Map<String, String> getParameters() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("year", getYear());
		param.put("loadPort1", getLoadPort1());
		param.put("loadPort2", getLoadPort2());
		param.put("loadPort3", getLoadPort3());
		param.put("unloadPort1", getUnloadPort1());
		param.put("unloadPort2", getUnloadPort2());
		param.put("unloadPort3", getUnloadPort3());
		param.put("shipType", getShipType());
		return param;
	}

	
	public Date getReferenceDate() {
		return referenceDate;
	}

	public void setReferenceDate(Date referenceDate) {
		this.referenceDate = referenceDate;
	}

	public String getShipType() {
		return shipType;
	}

	public void setShipType(String shipType) {
		this.shipType = shipType;
	}

	public String getLoadPort1() {
		if(loadPort1 == null){return "";}
		return loadPort1;
	}

	public void setLoadPort1(String loadPort1) {
		this.loadPort1 = loadPort1;
	}

	public String getLoadPort2() {
		if(loadPort2 == null){return "";}
		return loadPort2;
	}

	public void setLoadPort2(String loadPort2) {
		this.loadPort2 = loadPort2;
	}

	public String getLoadPort3() {
		if(loadPort3 == null){return "";}
		return loadPort3;
	}

	public void setLoadPort3(String loadPort3) {
		this.loadPort3 = loadPort3;
	}

	public String getUnloadPort1() {
		if(unloadPort1 == null){return "";}
		return unloadPort1;
	}

	public void setUnloadPort1(String unloadPort1) {
		this.unloadPort1 = unloadPort1;
	}

	public String getUnloadPort2() {
		if(unloadPort2 == null){return "";}
		return unloadPort2;
	}

	public void setUnloadPort2(String unloadPort2) {
		this.unloadPort2 = unloadPort2;
	}

	public String getUnloadPort3() {
		if(unloadPort3 == null){return "";}
		return unloadPort3;
	}

	public void setUnloadPort3(String unloadPort3) {
		this.unloadPort3 = unloadPort3;
	}

	public String getYear() {
		if(year == null){return "";}
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	public SimplePageInfo getPageInfo(){
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(super.getCurrentPage());
		pageInfo.setPageSize(super.getPageSize());
		return pageInfo;
	}
}
