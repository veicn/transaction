package com.sinochem.crude.trade.member.domain;

import java.util.Date;

public class BusinessCardApply {
    private Long id;

    /**
     *会员id
     */
    private Long memberId;

    /**
     *申请会员名片id
     */
    private Long applyMemberId;

    /**
     *申请状态 （0：申请中 ，1：同意，2：忽略）
     */
    private String applyStatus;

    /**
     *备注
     */
    private String remark;

    /**
     *删除标识
     */
    private boolean delFlg;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *创建人
     */
    private Long creater;

    /**
     *修改时间
     */
    private Date updateTime;

    /**
     *修改人
     */
    private Long updater;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getApplyMemberId() {
        return applyMemberId;
    }

    public void setApplyMemberId(Long applyMemberId) {
        this.applyMemberId = applyMemberId;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus == null ? null : applyStatus.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public boolean getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(boolean delFlg) {
        this.delFlg = delFlg;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }
}