package com.sinochem.crude.trade.info.model;

import java.io.Serializable;
import java.util.List;

public class PriceIndexVOVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PriceIndexVO> vos;
	private String indexDate;
	
	
	public List<PriceIndexVO> getVos() {
		return vos;
	}
	public void setVos(List<PriceIndexVO> vos) {
		this.vos = vos;
	}
	public String getIndexDate() {
		return indexDate;
	}
	public void setIndexDate(String indexDate) {
		this.indexDate = indexDate;
	}
	
	
}
