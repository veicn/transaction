package com.sinochem.crude.trade.transport.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.SimplePageInfo;

public class TideQuery extends QueryBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String stateSearch;
	String countrySearch;
	String provinceSearch;
	String portSearch;
	String dateSearch;

	@Override
	public Map<String, String> getParameters() {

		Map<String, String> param = new HashMap<String, String>();
		
		param.put("stateSearch", getStateSearch());
		param.put("countrySearch", getCountrySearch());
		param.put("provinceSearch", getProvinceSearch());
		param.put("portSearch", getPortSearch());
		param.put("dateSearch", getDateSearch());
		
		return param;
	}

	public String getStateSearch() {
		return stateSearch;
	}

	public void setStateSearch(String stateSearch) {
		this.stateSearch = stateSearch;
	}

	public String getCountrySearch() {
		return countrySearch;
	}

	public void setCountrySearch(String countrySearch) {
		this.countrySearch = countrySearch;
	}

	public String getProvinceSearch() {
		return provinceSearch;
	}

	public void setProvinceSearch(String provinceSearch) {
		this.provinceSearch = provinceSearch;
	}

	public String getPortSearch() {
		return portSearch;
	}

	public void setPortSearch(String portSearch) {
		this.portSearch = portSearch;
	}

	public String getDateSearch() {
		return dateSearch;
	}

	public void setDateSearch(String dateSearch) {
		this.dateSearch = dateSearch;
	}
	public SimplePageInfo getPageInfo(){
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(super.getCurrentPage());
		pageInfo.setPageSize(super.getPageSize());
		return pageInfo;
	}
}
