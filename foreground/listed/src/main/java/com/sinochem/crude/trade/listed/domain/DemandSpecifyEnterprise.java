package com.sinochem.crude.trade.listed.domain;

import java.util.Date;

/**
 * 指定企业发布需求功能
 * 需求对应的相关企业信息记录
 * Created by sijliu on 17/01/2018.
 */
public class DemandSpecifyEnterprise {

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
