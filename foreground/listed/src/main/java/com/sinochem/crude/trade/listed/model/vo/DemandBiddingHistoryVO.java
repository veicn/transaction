package com.sinochem.crude.trade.listed.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eyeieye.melody.util.DateUtil;
import com.sinochem.crude.trade.listed.domain.DemandBiddingHistory;

/**
 * 报价单修改记录
 * 
 * @author kangkai
 *
 */
public class DemandBiddingHistoryVO implements Serializable {
	private static final long serialVersionUID = -4184862022960970568L;

    /**
     * 主键
     */
	private Long id;

    /**
     * demand表主键
     */
	private Long demandId;

    /**
     * 变更时间
     */
	private String updateTime;

    /**
     * 变更人
     */
	private Long updater;

    /**
     * 变更字段
     */
	private String item;

    /**
     * 变更前值
     */
	private String valueOld;

    /**
     * 变更后值
     */
	private String valueNew;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDemandId() {
		return demandId;
	}

	public void setDemandId(Long demandId) {
		this.demandId = demandId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdater() {
		return updater;
	}

	public void setUpdater(Long updater) {
		this.updater = updater;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getValueOld() {
		return valueOld;
	}

	public void setValueOld(String valueOld) {
		this.valueOld = valueOld;
	}

	public String getValueNew() {
		return valueNew;
	}

	public void setValueNew(String valueNew) {
		this.valueNew = valueNew;
	}

    /**
     * 构造函数
     * 将领域模型转换为vo
     * @param DemandBiddingHistory 需求的领域模型
     */
    public static DemandBiddingHistoryVO convertDomainToVo(DemandBiddingHistory domain) {
    	DemandBiddingHistoryVO vo = new DemandBiddingHistoryVO();
    	
    	vo.setId(domain.getId());
    	vo.setDemandId(domain.getDemandId());
    	vo.setItem(domain.getItem());
    	vo.setUpdater(domain.getUpdater());
    	vo.setUpdateTime(DateUtil.convertDateToString("yyyy-MM-dd HH:mm:ss", domain.getUpdateTime()));
    	vo.setValueNew(domain.getValueNew());
    	vo.setValueOld(domain.getValueOld());
    	
    	return vo;
    }

    /**
     * 将vo转换为领域模型
     * @return
     */
    public static List<DemandBiddingHistoryVO> convertListToVo(List<DemandBiddingHistory> domainList) {
    	if(domainList == null) {
    		return null;
    	}
    	
    	List<DemandBiddingHistoryVO> voList = new ArrayList<DemandBiddingHistoryVO>();
    	for(int i = 0; i < domainList.size(); i++) {
    		voList.add(convertDomainToVo(domainList.get(i)));
    	}
    	
    	return voList;
    }
}
