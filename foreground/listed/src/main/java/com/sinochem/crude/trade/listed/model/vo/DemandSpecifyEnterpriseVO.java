package com.sinochem.crude.trade.listed.model.vo;


import com.sinochem.crude.trade.listed.domain.DemandSpecifyEnterprise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 需求关联指定企业信息vo
 * Created by sijliu on 17/01/2018.
 */
public class DemandSpecifyEnterpriseVO {
    private Long id;
    /**
     * 需求id
     */
    private Long demandId;
    /**
     * 企业id
     */
    private Long epMemberId;
    /**
     * 企业名称
     */
    private String enterpriseName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private Long creater;

    public DemandSpecifyEnterpriseVO () {}

    public DemandSpecifyEnterpriseVO (DemandSpecifyEnterprise specify) {
        this.id = specify.getId();
        this.demandId = specify.getDemandId();
        this.epMemberId = specify.getEpMemberId();
        this.enterpriseName = specify.getEnterpriseName();
        this.createTime = specify.getCreateTime();
        this.creater = specify.getCreater();
    }

    /**
     * 转vo为domain
     * @return
     */
    public DemandSpecifyEnterprise convertVoToDomain() {
        DemandSpecifyEnterprise specify = new DemandSpecifyEnterprise();
        specify.setId(this.id);
        specify.setDemandId(this.demandId);
        specify.setEpMemberId(this.epMemberId);
        specify.setEnterpriseName(this.enterpriseName);
        specify.setCreateTime(this.createTime);
        specify.setCreater(this.creater);
        return specify;
    }

    /**
     * 转domainlist为volist
     * @param domainList
     * @return
     */
    public static List<DemandSpecifyEnterpriseVO> convertListToVo(List<DemandSpecifyEnterprise> domainList) {
        if(domainList == null) {
            return null;
        }

        List<DemandSpecifyEnterpriseVO> voList = new ArrayList<DemandSpecifyEnterpriseVO>();
        for(int i = 0; i < domainList.size(); i++) {
            voList.add(new DemandSpecifyEnterpriseVO(domainList.get(i)));
        }

        return voList;
    }

    /**
     * 转volist为domainlist
     * @param voList
     * @return
     */
    public static List<DemandSpecifyEnterprise> convertListToDomain(List<DemandSpecifyEnterpriseVO> voList) {
        if (voList == null)
            return null;
        List<DemandSpecifyEnterprise> domainList = new ArrayList<>();
        for (int i=0; i<voList.size(); i++) {
            domainList.add(voList.get(i).convertVoToDomain());
        }
        return domainList;
    }

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

    public Long getEpMemberId() {
        return epMemberId;
    }

    public void setEpMemberId(Long epMemberId) {
        this.epMemberId = epMemberId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }
}
