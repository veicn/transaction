package com.sinochem.crude.trade.order.model.query;

import java.util.Date;

/**
 * @Description:
 * @Author : chenyz
 * @Date: 2017/11/18
 */
public class ContractQuery {
    /**
     * 企业id
     */
    private Long memberid;
    /**
     *  商品名称/订单编号/商品编号
     */
    private String name;
    /**
     * 操作人
     */
    private Long creater;
    /**
     * 业务类型
     */
    private String bizType;
    /**
     * 交易类型
     */
    private String dealType;
    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 开始时间结束时间
     */
    private Date startTime;
    private Date endTime;
    /**
     * 长约还是短约 D 短约 L 长约
     */
    private String contractType;

    /**
     * 排序字段
     */
    private String orderByClause;

    private String orderNo;

    /**
     * 订单角色 1：买家；2：卖家；
     */
    private Integer roleType;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}
}
