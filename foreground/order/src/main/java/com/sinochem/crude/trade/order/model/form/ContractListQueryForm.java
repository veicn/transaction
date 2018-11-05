package com.sinochem.crude.trade.order.model.form;

/**
 * @Description:查询订单列表
 * @Author : chenyz
 * @Date: 2017/11/17
 */
public class ContractListQueryForm {
    /**
     *  商品名称/订单编号/商品编号
     */
    /*@NotEmpty*/
    private String name;
    /**
     * 业务类型 c  原油  p 成品油
     */
    private String bizType;
    /**
     * 长协和普通
     */
    private String contractType;
    /**
     * 交易类型
     */
    private String dealType;
    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 时间
     */
    private String  dataTime;
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

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
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
