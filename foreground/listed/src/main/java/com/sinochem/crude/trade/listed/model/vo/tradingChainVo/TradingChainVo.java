package com.sinochem.crude.trade.listed.model.vo.tradingChainVo;


import com.sinochem.crude.trade.listed.domain.TradingChain;
import com.sinochem.it.b2b.common.utils.DateUtil;

import java.util.Date;

/**
 * 贸易链表Vo
 * made by WangTing
 */
public class TradingChainVo  {
    /**
     *贸易链表的id
     */
    private Long id;

    /**
     * 贸易链订单编号
     */
    private String serialNumber;

    /**
     * 报价单id
     */
    private Long demandId;

    /**
     *贸易链的序号
     */
    private String uuid;

    /**
     * 贸易链的执行状态
     */
    private Integer status;

    /**
     * 贸易链的企业参与总人数
     */
    private Integer operatorSum;

    /**
     * 贸易链的创建时间
     */
    private String createTime;

    /**
     * 贸易链的开始时间
     */
    private String startTime;

    /**
     * 贸易链的截止时间
     */
    private String deadline;


    /**
     * 贸易链的完成时间

     */
    private String finishTime;

    /**
     * 参与贸易链企业id合集
     */
    private String operatorsId;

    /**
     * 贸易链的修改时间
     */
    private String alterTime;

    /**
     * 贸易链的创建人
     */
    private Long userCreated;

    /**
     * 贸易链的修改人
     */
    private Long userModified;

    /**
     * 贸易链是否存活
     */
    private Integer aliveFlag;

    /**
     * 贸易链的版本
     */
    private Long version;

    /**
     * domain转化为vo
     * @param tradingChain
     * @return
     */
    public static TradingChainVo convertToVO(TradingChain tradingChain){
        TradingChainVo tradingChainVo =new TradingChainVo();

        tradingChainVo.setId(tradingChain.getId());
        tradingChainVo.setSerialNumber(tradingChain.getSerialNumber());
        tradingChainVo.setDemandId(tradingChain.getDemandId());
        tradingChainVo.setUuid(tradingChain.getUuid());
        tradingChainVo.setStatus(tradingChain.getStatus());
        tradingChainVo.setOperatorSum(tradingChain.getOperatorSum());
        tradingChainVo.setCreateTime(DateUtil.formatDate(tradingChain.getCreateTime()));
        tradingChainVo.setStartTime(DateUtil.formatDate(tradingChain.getStartTime()));
        tradingChainVo.setDeadline(DateUtil.formatDate(tradingChain.getDeadline()));
        tradingChainVo.setFinishTime(DateUtil.formatDate(tradingChain.getFinishTime()));
        tradingChainVo.setOperatorsId(tradingChain.getOperatorsId());
        tradingChainVo.setAlterTime(DateUtil.formatDate(tradingChain.getAlterTime()));
        tradingChainVo.setUserCreated(tradingChain.getUserCreated());
        tradingChainVo.setUserModified(tradingChain.getUserModified());
        tradingChainVo.setAliveFlag(tradingChain.getAliveFlag());
        tradingChainVo.setVersion(tradingChain.getVersion());

        return tradingChainVo;
    }

    /**
     * vo转化为domain
     * 存活标志默认为1，版本默认为0
     * @param tradingChainVo
     * @return
     */
    public static TradingChain convertToDomain(TradingChainVo tradingChainVo){
        TradingChain tradingChain =new TradingChain();

        tradingChain.setId(tradingChainVo.getId());
        tradingChain.setSerialNumber(tradingChainVo.getSerialNumber());
        tradingChain.setDemandId(tradingChainVo.getDemandId());
        tradingChain.setUuid(tradingChainVo.getUuid());
        tradingChain.setStatus(tradingChainVo.getStatus());
        tradingChain.setOperatorSum(tradingChainVo.getOperatorSum());
        tradingChain.setCreateTime(DateUtil.getDate(tradingChainVo.getCreateTime()));
        tradingChain.setStartTime(DateUtil.getDate(tradingChainVo.getStartTime()));
        tradingChain.setDeadline(DateUtil.getDate(tradingChainVo.getDeadline()));
        tradingChain.setFinishTime(DateUtil.getDate(tradingChainVo.getFinishTime()));
        tradingChain.setOperatorsId(tradingChainVo.getOperatorsId());
        tradingChain.setAlterTime(DateUtil.getDate(tradingChainVo.getAlterTime()));
        tradingChain.setUserCreated(tradingChainVo.getUserCreated());
        tradingChain.setUserModified(tradingChainVo.getUserModified());
        if(tradingChainVo.getAliveFlag() == 0){
            tradingChainVo.setAliveFlag(1);
        }
        tradingChain.setAliveFlag(tradingChainVo.getAliveFlag());
        tradingChain.setVersion(tradingChainVo.getVersion());

        return tradingChain;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOperatorSum() {
        return operatorSum;
    }

    public void setOperatorSum(Integer operatorSum) {
        this.operatorSum = operatorSum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getOperatorsId() {
        return operatorsId;
    }

    public void setOperatorsId(String operatorsId) {
        this.operatorsId = operatorsId;
    }

    public String getAlterTime() {
        return alterTime;
    }

    public void setAlterTime(String alterTime) {
        this.alterTime = alterTime;
    }

    public Long getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(Long userCreated) {
        this.userCreated = userCreated;
    }

    public Long getUserModified() {
        return userModified;
    }

    public void setUserModified(Long userModified) {
        this.userModified = userModified;
    }

    public Integer getAliveFlag() {
        return aliveFlag;
    }

    public void setAliveFlag(Integer aliveFlag) {
        this.aliveFlag = aliveFlag;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
