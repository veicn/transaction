package com.sinochem.crude.trade.listed.model.vo.tradingChainVo;

import com.sinochem.crude.trade.listed.domain.TradingChainDemand;
import com.sinochem.it.b2b.common.utils.DateUtil;

import java.util.Date;

/**
 * 贸易链表与demand中间表Vo
 * made by WangTing
 */
public class TradingChainDemandVO {

    /**
     * 中间表id
     */
    private Long id;

    /**
     * 贸易链表id
     */
    private Long tradingChainId;

    /**
     * demandid
     */
    private Long demandId;

    /**
     * 本条数据的序号(用来表示草约单的顺序)
     */
    private Integer serialNumber;

    /**
     * 中间表的uuid
     */
    private String uuid;

    /**
     * 中间表的创建时间
     */
    private String createTime;

    /**
     * 中间表的修改时间
     */
    private String alterTime;

    /**
     * 中间表的创建人
     */
    private Long userCreated;

    /**
     * 中间表的修改人
     */
    private Long userModified;

    /**
     * 中间表的存活标志
     */
    private Long aliveFlag;

    /**
     * 中间表的版本
     */
    private Long version;

    /**
     * domain转化为vo
     * @param tradingChainDemand
     * @return
     */
    public static TradingChainDemandVO convertToVO(TradingChainDemand tradingChainDemand){
        TradingChainDemandVO tradingChainDemandVo =new TradingChainDemandVO();

        tradingChainDemandVo.setId(tradingChainDemand.getId());
        tradingChainDemandVo.setTradingChainId(tradingChainDemand.getTradingChainId());
        tradingChainDemandVo.setDemandId(tradingChainDemand.getDemandId());
        tradingChainDemandVo.setSerialNumber(tradingChainDemand.getSerialNumber());
        tradingChainDemandVo.setUuid(tradingChainDemand.getUuid());
        tradingChainDemandVo.setCreateTime(DateUtil.formatDate(tradingChainDemand.getCreateTime()));
        tradingChainDemandVo.setAlterTime(DateUtil.formatDate(tradingChainDemand.getAlterTime()));
        tradingChainDemandVo.setUserCreated(tradingChainDemand.getUserCreated());
        tradingChainDemandVo.setUserModified(tradingChainDemand.getUserModified());
        tradingChainDemandVo.setAliveFlag(tradingChainDemand.getAliveFlag());
        tradingChainDemandVo.setVersion(tradingChainDemand.getVersion());
        return tradingChainDemandVo;
    }

    /**
     * vo转化为domain
     * 存活标志默认为1，版本默认为0
     * @param tradingChainDemandVo
     * @return
     */
    public static TradingChainDemand convertToDomain(TradingChainDemandVO tradingChainDemandVo){
        TradingChainDemand tradingChainDemand =new TradingChainDemand();

        tradingChainDemand.setId(tradingChainDemandVo.getId());
        tradingChainDemand.setTradingChainId(tradingChainDemandVo.getTradingChainId());
        tradingChainDemand.setDemandId(tradingChainDemandVo.getDemandId());
        tradingChainDemand.setSerialNumber(tradingChainDemandVo.getSerialNumber());
        tradingChainDemand.setUuid(tradingChainDemandVo.getUuid());
        tradingChainDemand.setCreateTime(DateUtil.getDate(tradingChainDemandVo.getCreateTime()));
        tradingChainDemand.setAlterTime(DateUtil.getDate(tradingChainDemandVo.getAlterTime()));
        tradingChainDemand.setUserCreated(tradingChainDemandVo.getUserCreated());
        tradingChainDemand.setUserModified(tradingChainDemandVo.getUserModified());
        if(tradingChainDemandVo.getAliveFlag() == 0){
            tradingChainDemandVo.setAliveFlag(1L);
        }
        tradingChainDemand.setAliveFlag(tradingChainDemandVo.getAliveFlag());
        tradingChainDemand.setVersion(tradingChainDemandVo.getVersion());

        return tradingChainDemand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTradingChainId() {
        return tradingChainId;
    }

    public void setTradingChainId(Long tradingChainId) {
        this.tradingChainId = tradingChainId;
    }

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public Long getAliveFlag() {
        return aliveFlag;
    }

    public void setAliveFlag(Long aliveFlag) {
        this.aliveFlag = aliveFlag;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
