package com.sinochem.crude.trade.transport.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonUnwrapped;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.transport.domain.Transit;

public class TransitVO extends Transit {

	private static final long serialVersionUID = 1L;	
	
	private List<String> imgList;
	
	@JsonUnwrapped
	private SimplePageInfo pageInfo;

	
	
	public List<String> getImgList() {
		return imgList;
	}

	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}

	public SimplePageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(SimplePageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
}