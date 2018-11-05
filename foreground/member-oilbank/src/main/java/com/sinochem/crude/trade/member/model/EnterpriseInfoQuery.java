package com.sinochem.crude.trade.member.model;


import com.sinochem.crude.trade.member.domain.Enterprise;

/**
 * @Description:
 * @Author : jasonxu
 * @Date: 09/11/2017
 */
public class EnterpriseInfoQuery extends Enterprise {
    private int maxId;
    private int minId;
    private String orderBy;

    /**
     * 根据名称模糊查询
     */
    private String nameLike;

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    public int getMinId() {
        return minId;
    }

    public void setMinId(int minId) {
        this.minId = minId;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }
}
