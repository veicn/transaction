package com.sinochem.crude.trade.listed.domain;

/**
 * 原油采购单的详情
 * 
 * @author Leo
 *
 */
public class DemandDetail {

	/**
	 * PK
	 */
	private Long id;
	
	private String head;
	
	private String subHead;
	
	private String content;
	
	
	

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getSubHead() {
		return subHead;
	}

	public void setSubHead(String subHead) {
		this.subHead = subHead;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 采购单信息
	 */
	private Long demandId;

	/**
	 * 富文本编辑器内的内容，有待商榷，在需求单的展现页面这里要做成widget，不能直接和挂单页面接起来
	 */
	private String desc;

	public Long getDemandId() {
		return demandId;
	}

	public void setDemandId(Long demandId) {
		this.demandId = demandId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
