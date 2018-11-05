package com.sinochem.crude.trade.order.model.result;

/**
 * @Description:订单状态条数统计
 * @Author : chenyz
 * @Date: 2017/11/18
 */
public class OrderStatusCountResult {
    /**
     * 总条数
     */
    private Integer total;
    /**
     * 未支付
     */
    private Integer unpaid;
    /**
     * 待发货
     */
    private Integer unshipments;
    /**
     * 待收货
     */
    private Integer unreceiving;
    /**
     * 待结算
     */
    private Integer unclosing;
    /**
     * 交易成功
     */
    private Integer successCount;
    /**
     * 交易关闭
     */
    private Integer closeCount;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(Integer unpaid) {
        this.unpaid = unpaid;
    }

    public Integer getUnshipments() {
        return unshipments;
    }

    public void setUnshipments(Integer unshipments) {
        this.unshipments = unshipments;
    }

    public Integer getUnreceiving() {
        return unreceiving;
    }

    public void setUnreceiving(Integer unreceiving) {
        this.unreceiving = unreceiving;
    }

    public Integer getUnclosing() {
        return unclosing;
    }

    public void setUnclosing(Integer unclosing) {
        this.unclosing = unclosing;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getCloseCount() {
        return closeCount;
    }

    public void setCloseCount(Integer closeCount) {
        this.closeCount = closeCount;
    }
}
