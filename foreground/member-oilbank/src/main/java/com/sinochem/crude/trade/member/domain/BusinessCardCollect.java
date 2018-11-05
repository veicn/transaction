package com.sinochem.crude.trade.member.domain;

import java.util.Date;

/**
 * 名片收藏表
 */
public class BusinessCardCollect {
    private Long id;

    /**
     *会员id
     */
    private Long memberId;

    /**
     *收藏名片会员id
     */
    private Long collectMemberId;

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

    public Long getCollectMemberId() {
        return collectMemberId;
    }

    public void setCollectMemberId(Long collectMemberId) {
        this.collectMemberId = collectMemberId;
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