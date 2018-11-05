package com.sinochem.crude.trade.orderexecute.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.orderexecute.commons.QueryBaseExt;

public class InterfaceInputQuery  extends QueryBaseExt{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**输入参数*/
	private String inputPara;  
	/**输出参数*/
	private String outputPara;  

	
	@Override
	public Map<String, String> getParameters() {
		Map<String, String> demo = new HashMap<String, String>();
		return demo;
	}
	
	public Map<String, Object> InterfaceInputQuery() {
		Map<String, Object> params = new HashMap<>();
		params.put("inputPara", this.inputPara);
		params.put("outputPara", this.outputPara);
		return params;
	}
	
	public SimplePageInfo getPageInfo() {
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(super.getCurrentPage());
		pageInfo.setPageSize(super.getPageSize());
		
		return pageInfo;
	}

	public String getInputPara() {
		return inputPara;
	}

	public void setInputPara(String inputPara) {
		this.inputPara = inputPara;
	}

	public String getOutputPara() {
		return outputPara;
	}

	public void setOutputPara(String outputPara) {
		this.outputPara = outputPara;
	}

	

	


}
