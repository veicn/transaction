package com.sinochem.crude.trade.listed.model.vo.tradingChainVo;

import com.sinochem.crude.trade.listed.domain.TradingChainMember;
import com.sinochem.it.b2b.common.utils.DateUtil;

import java.util.Date;

/**
 * 贸易链表与member中间表Vo
 * made by WangTing
 */
public class TradingChainMemberVO {

    /**
     *中间表id
     */
    private Long id;

    /**
     * 贸易链表id
     */
    private Long tradingChainId;

    /**
     *member表id
     */
    private Long memberId;

    /**
     * 企业表id
     */
    private Long enterpriseId;

    /**
     * 中间表序号(用来表示贸易商的顺序)
     */
    private Integer serialNumber;

    /**
     *企业参与贸易链的角色
     */
    private String roleType;

    /**
     * 企业的操作状态(确认或者驳回)
     */
    private String operationStatus;

    /**
     * 企业确认时间
     */
    private String acceptTime;

    /**
     * 企业驳回时间
     */
    private String rejectTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String alterTime;

    /**
     * 创建人
     */
    private Long userCreated;

    /**
     * 修改人
     */
    private Long userModified;

    /**
     * 是否存活
     */
    private Long aliveFlag;

    /**
     * 版本
     */
    private Long version;


    /**
     * domain转化为vo
     * @param tradingChainMember
     * @return
     */
    public static TradingChainMemberVO convertToVO(TradingChainMember tradingChainMember){
        TradingChainMemberVO tradingChainMemberVo =new TradingChainMemberVO();

        tradingChainMemberVo.setId(tradingChainMember.getId());
        tradingChainMemberVo.setTradingChainId(tradingChainMember.getTradingChainId());
        tradingChainMemberVo.setMemberId(tradingChainMember.getMemberId());
        tradingChainMemberVo.setEnterpriseId(tradingChainMember.getEnterpriseId());
        tradingChainMemberVo.setSerialNumber(tradingChainMember.getSerialNumber());
        tradingChainMemberVo.setRoleType(tradingChainMember.getRoleType());
        tradingChainMemberVo.setOperationStatus(tradingChainMember.getOperationStatus());
        tradingChainMemberVo.setAcceptTime(DateUtil.formatDate(tradingChainMember.getAcceptTime()));
        tradingChainMemberVo.setRejectTime(DateUtil.formatDate(tradingChainMember.getRejectTime()));
        tradingChainMemberVo.setRemark(tradingChainMember.getRemark());
        tradingChainMemberVo.setUuid(tradingChainMember.getUuid());
        tradingChainMemberVo.setCreateTime(DateUtil.formatDate(tradingChainMember.getCreateTime()));
        tradingChainMemberVo.setAlterTime(DateUtil.formatDate(tradingChainMember.getAlterTime()));
        tradingChainMemberVo.setUserCreated(tradingChainMember.getUserCreated());
        tradingChainMemberVo.setUserModified(tradingChainMember.getUserModified());
        tradingChainMemberVo.setAliveFlag(tradingChainMember.getAliveFlag());
        tradingChainMemberVo.setVersion(tradingChainMember.getVersion());


        return tradingChainMemberVo;
    }

    /**
     * vo转化为domain
     * 存活标志默认为1，版本默认为0
     * @param tradingChainMemberVo
     * @return
     */
    public static TradingChainMember convertToDomain(TradingChainMemberVO tradingChainMemberVo){
        TradingChainMember tradingChainMember =new TradingChainMember();

        tradingChainMember.setId(tradingChainMemberVo.getId());
        tradingChainMember.setTradingChainId(tradingChainMemberVo.getTradingChainId());
        tradingChainMember.setMemberId(tradingChainMemberVo.getMemberId());
        tradingChainMember.setEnterpriseId(tradingChainMemberVo.getEnterpriseId());
        tradingChainMember.setSerialNumber(tradingChainMemberVo.getSerialNumber());
        tradingChainMember.setRoleType(tradingChainMemberVo.getRoleType());
        tradingChainMember.setOperationStatus(tradingChainMemberVo.getOperationStatus());
        tradingChainMember.setAcceptTime(DateUtil.getDate(tradingChainMemberVo.getAcceptTime()));
        tradingChainMember.setRejectTime(DateUtil.getDate(tradingChainMemberVo.getRejectTime()));
        tradingChainMember.setUuid(tradingChainMemberVo.getUuid());
        tradingChainMember.setCreateTime(DateUtil.getDate(tradingChainMemberVo.getCreateTime()));
        tradingChainMember.setAcceptTime(DateUtil.getDate(tradingChainMemberVo.getAcceptTime()));
        tradingChainMember.setRejectTime(DateUtil.getDate(tradingChainMemberVo.getRejectTime()));
        tradingChainMember.setRemark(tradingChainMemberVo.getRemark());
        tradingChainMember.setUuid(tradingChainMemberVo.getUuid());
        tradingChainMember.setCreateTime(DateUtil.getDate(tradingChainMemberVo.getCreateTime()));
        tradingChainMember.setAlterTime(DateUtil.getDate(tradingChainMemberVo.getAlterTime()));
        tradingChainMember.setUserCreated(tradingChainMemberVo.getUserCreated());
        tradingChainMember.setAlterTime(DateUtil.getDate(tradingChainMemberVo.getAlterTime()));
        tradingChainMember.setUserCreated(tradingChainMemberVo.getUserCreated());
        tradingChainMember.setUserModified(tradingChainMemberVo.getUserModified());
        if(tradingChainMemberVo.getAliveFlag() == 0){
            tradingChainMemberVo.setAliveFlag(1L);
        }
        tradingChainMember.setAliveFlag(tradingChainMemberVo.getAliveFlag());
        tradingChainMember.setVersion(tradingChainMemberVo.getVersion());

        return tradingChainMember;
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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getRejectTime() {
        return rejectTime;
    }

    public void setRejectTime(String rejectTime) {
        this.rejectTime = rejectTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
