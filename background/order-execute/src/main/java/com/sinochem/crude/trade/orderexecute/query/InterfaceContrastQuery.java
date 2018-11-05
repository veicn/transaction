package com.sinochem.crude.trade.orderexecute.query;

import java.util.HashMap;
import java.util.Map;

import com.sinochem.crude.trade.orderexecute.commons.QueryBaseExt;

public class InterfaceContrastQuery extends QueryBaseExt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private Long contrastId;
	
	private String sysName;
	
	private String paraType;
	
	private String paraCode;

	private String paraDesc;
	
	private String otherCode;
	
	private String langVer;
	
	private String selSysName;
	
	private String selParaCode;
	
	private String selOtherCode;
	
	
	
	
	
	@Override
	public Map<String, String> getParameters() {
		Map<String, String> demo = new HashMap<String, String>();
		
		return demo;
	}



	

	public String getSelSysName() {
		return selSysName;
	}





	public void setSelSysName(String selSysName) {
		this.selSysName = selSysName;
	}





	public String getSelParaCode() {
		return selParaCode;
	}





	public void setSelParaCode(String selParaCode) {
		this.selParaCode = selParaCode;
	}





	public String getSelOtherCode() {
		return selOtherCode;
	}





	public void setSelOtherCode(String selOtherCode) {
		this.selOtherCode = selOtherCode;
	}





	public Long getContrastId() {
		return contrastId;
	}





	public void setContrastId(Long contrastId) {
		this.contrastId = contrastId;
	}





	public String getSysName() {
		return sysName;
	}





	public void setSysName(String sysName) {
		this.sysName = sysName;
	}





	public String getParaType() {
		return paraType;
	}





	public void setParaType(String paraType) {
		this.paraType = paraType;
	}





	public String getParaCode() {
		return paraCode;
	}





	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
	}





	public String getParaDesc() {
		return paraDesc;
	}





	public void setParaDesc(String paraDesc) {
		this.paraDesc = paraDesc;
	}





	public String getOtherCode() {
		return otherCode;
	}





	public void setOtherCode(String otherCode) {
		this.otherCode = otherCode;
	}





	public String getLangVer() {
		return langVer;
	}





	public void setLangVer(String langVer) {
		this.langVer = langVer;
	}
	
	
	
	
	
	
}
