package com.sinochem.crude.trade.info.model;

import java.util.List;

public class BasePriceVOVO {

	private List<BasePriceVO> vos;
	private String baseDate;

	public List<BasePriceVO> getVos() {
		return vos;
	}

	public void setVos(List<BasePriceVO> vos) {
		this.vos = vos;
	}

	public String getBaseDate() {
		return baseDate;
	}

	public void setBaseDate(String baseDate) {
		this.baseDate = baseDate;
	}
	
	
	
}
