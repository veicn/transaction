package com.sinochem.crude.trade.shiprefueling.model.vo;

import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.domain.po.Chments;
import org.codehaus.jackson.annotate.JsonUnwrapped;
import org.springframework.beans.BeanUtils;


public class ChmentsVO extends Chments {

	private static final long serialVersionUID = 1L;	
	
	@JsonUnwrapped
	private SimplePageInfo pageInfo;
	
	public SimplePageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(SimplePageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public ChmentsVO toVO(Chments chments){
		if(chments == null){
			return null;
		}
		BeanUtils.copyProperties(chments , this);
		return this;
	}
}
