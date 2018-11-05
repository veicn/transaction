package com.sinochem.crude.trade.listed.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sinochem.crude.trade.listed.domain.DemandDetail;
import com.sinochem.crude.trade.listed.domain.DemandShipBerth;
import com.sinochem.crude.trade.listed.helper.DictUtils;

public class DemandDetailVO implements Serializable {
	private static final long serialVersionUID = -4184862022960970568L;
	
	/**
	 * PK
	 */
	private Long id;
	
	private String head;
	
	private String subHead;
	
	private String content;
	
	/**
     * 将vo转换为领域模型
     * @return
     */
    public DemandDetail convertVoToDomain() {
    	DemandDetail domain = new DemandDetail();
        domain.setId(this.getId());
        domain.setHead(this.getHead());
        domain.setSubHead(this.getSubHead());
        domain.setContent(this.getContent());

        return domain;
    }
    
    /**
     * 构造函数
     * 将领域模型转换为vo
     * @param demandDetail 需求的领域模型
     */
    public static DemandDetailVO convertDomainToVo(DemandDetail domain) {
    	DemandDetailVO vo = new DemandDetailVO();
    	
    	vo.setId(domain.getId());
    	vo.setHead(domain.getHead());
    	vo.setSubHead(domain.getSubHead());
    	vo.setContent(domain.getContent());
    	
    	return vo;
    }

    /**
     * 将vo转换为领域模型
     * @return
     */
    public static List<DemandDetailVO> convertListToVo(List<DemandDetail> domainList) {
    	if(domainList == null) {
    		return null;
    	}
    	
    	List<DemandDetailVO> voList = new ArrayList<DemandDetailVO>();
    	for(int i = 0; i < domainList.size(); i++) {
    		voList.add(convertDomainToVo(domainList.get(i)));
    	}
    	
    	return voList;
    }
    
    /**
     * 将vo转换为领域模型
     * @return
     */
    public static List<DemandDetail> convertListToDomain(List<DemandDetailVO> voList) {
    	if(voList == null) {
    		return null;
    	}
    	
    	List<DemandDetail> domainList = new ArrayList<DemandDetail>();
    	for(int i = 0; i < voList.size(); i++) {
    		domainList.add(voList.get(i).convertVoToDomain());
    	}
    	
    	return domainList;
    }

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
