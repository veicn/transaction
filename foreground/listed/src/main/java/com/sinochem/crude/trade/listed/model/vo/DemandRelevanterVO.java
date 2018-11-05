package com.sinochem.crude.trade.listed.model.vo;

import com.sinochem.crude.trade.listed.domain.DemandRelevanter;
import com.sinochem.it.b2b.common.exception.BizException;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

/**
 * DemandRelevanter类（代理商）的VO(APP接口专用)
 * @author Yichen Zhao
 * date：20180103
 */
public class DemandRelevanterVO implements Serializable {

    private static final long serialVersionUID = -4184862022960970568L;

    /**
     * PK
     */
    private Long id;

    /**
     * 买家  卖家 贸易商id
     */
    private Long eMemberId;

    /**
     * 需求id
     */
    private Long demandId;

    /**
     * 是否公开
     */
    private Byte share;

    /**
     * 类型
     */
    private String type;

    /**
     * 交易员id
     */
    private Long dealerId;

    /**
     * 交易员名称
     */
    private String dealerName;

    /**
     * 企业名称
     */
    @Length(max=100)
    private String enterpriseName;

    /**
     * 企业地址
     */
    @Length(max=128)
    private String enterpriseAddress;

    /**
     * 联系人
     */
    @Length(max=100)
    private String contacter;

    /**
     * 邮箱
     */
    @Length(max=64)
    private String famil; //实体类里面写的是famil

    /**
     * 电话
     */
    @Length(max=20)
    private String phoneNo;

    /**
     * 传真
     */
    @Length(max=20)
    private String fax;

    public DemandRelevanterVO (){}
    /**
     * 构造函数
     * 将领域模型转换为vo
     * @param demandRelevanter 需求的领域模型
     */
    public DemandRelevanterVO (DemandRelevanter demandRelevanter) {
        if (demandRelevanter != null) {
            this.id = demandRelevanter.getId();
            this.eMemberId = demandRelevanter.getEMemberId();
            this.demandId = demandRelevanter.getDemandId();
            this.share = demandRelevanter.getShare();
            this.type = demandRelevanter.getType();
            this.dealerId = demandRelevanter.getDealerId();
            this.dealerName = demandRelevanter.getDealerName();
            this.enterpriseName = demandRelevanter.getEnterpriseName();
            this.enterpriseAddress = demandRelevanter.getEnterpriseAddress();
            this.contacter = demandRelevanter.getContacter();
            this.phoneNo = demandRelevanter.getPhoneNo();
            this.famil = demandRelevanter.getFamil();
            this.fax = demandRelevanter.getFax();
        }

    }

    /**
     * 将vo转换为领域模型
     * @return
     */
    public DemandRelevanter convertVoToDomain() {
        DemandRelevanter demandRelevanter = new DemandRelevanter();
        demandRelevanter.setId(this.getId());
        demandRelevanter.setDemandId(this.getDemandId());
        demandRelevanter.setEMemberId(this.geteMemberId());
        demandRelevanter.setType(this.getType());
        demandRelevanter.setShare(this.getShare());
        demandRelevanter.setDealerId(this.getDealerId());
        demandRelevanter.setDealerName(this.getDealerName());
        demandRelevanter.setEnterpriseName(this.getEnterpriseName());
        demandRelevanter.setEnterpriseAddress(this.getEnterpriseAddress());
        demandRelevanter.setContacter(this.getContacter());
        demandRelevanter.setPhoneNo(this.getPhoneNo());
        demandRelevanter.setFamil(this.getFamil());
        demandRelevanter.setFax(this.getFax());
        return demandRelevanter;
    }

    /*getters and setters*/
    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseAddress() {
        return enterpriseAddress;
    }

    public void setEnterpriseAddress(String enterpriseAddress) {
        this.enterpriseAddress = enterpriseAddress;
    }

    public String getContacter() {
        return contacter;
    }

    public String getFamil() {
        return famil;
    }

    public void setFamil(String famil) {
        this.famil = famil;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long geteMemberId() {
        return eMemberId;
    }

    public void seteMemberId(Long eMemberId) {
        this.eMemberId = eMemberId;
    }

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    public Byte getShare() {
        return share;
    }

    public void setShare(Byte share) {
        this.share = share;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }
}
