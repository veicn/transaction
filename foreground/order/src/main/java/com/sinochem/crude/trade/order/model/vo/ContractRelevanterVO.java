package com.sinochem.crude.trade.order.model.vo;

import com.sinochem.crude.trade.order.domain.ContractRelevanter;

/**
 * ContractRelevanter的VO
 * @author Yichen Zhao
 * date: 20180125
 */
public class ContractRelevanterVO {

    /**
     * PK
     */
    private Long id;

    /**
     * 对应的企业id
     */
    private Long eMemberId;

    /**
     * 对应的企业名称
     */
    private String eMemberName;

    /**
     * 采购单信息
     */
    private Long contractId;

    /**
     * 联系人
     */
    private String contacter;

    /**
     * 联系电话
     */
    private String phoneNo;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 传真
     */
    private String fax;

    /**
     * 类型 B buyer S seller T 中间商
     */
    private String type;
    /**
     * 企业地址
     */

    private String address;

    /**
     * 交易员id
     */
    private Long dealerId;

    /**
     * 交易员名称
     */
    private String dealerName;

    /**
     * 转换为ContractRelevanterVO
     */
    public static void convertToContractRelevanterVO(
            ContractRelevanter contractRelevanter,
            ContractRelevanterVO contractRelevanterVO) {
        if (contractRelevanter == null) {
            return;
        }

        contractRelevanterVO.setId(contractRelevanter.getId());
        contractRelevanterVO.seteMemberId(contractRelevanter.getEMemberId());
        contractRelevanterVO.seteMemberName(contractRelevanter.geteMemberName());
        contractRelevanterVO.setContractId(contractRelevanter.getContractId());
        contractRelevanterVO.setContacter(contractRelevanter.getContacter());
        contractRelevanterVO.setPhoneNo(contractRelevanter.getPhoneNo());
        contractRelevanterVO.setEmail(contractRelevanter.getEmail());
        contractRelevanterVO.setFax(contractRelevanter.getFax());
        contractRelevanterVO.setType(contractRelevanter.getType());
        contractRelevanterVO.setAddress(contractRelevanter.getAddress());
        contractRelevanterVO.setDealerId(contractRelevanter.getDealerId());
        contractRelevanterVO.setDealerName(contractRelevanter.getDealerName());
    }

    public static void convertToContractRelevanter(
            ContractRelevanterVO contractRelevanterVO,
            ContractRelevanter contractRelevanter) {
        if (contractRelevanterVO == null) {
            return;
        }

        contractRelevanter.setId(contractRelevanterVO.getId());
        contractRelevanter.setEMemberId(contractRelevanterVO.geteMemberId());
        contractRelevanter.seteMemberName(contractRelevanterVO.geteMemberName());
        contractRelevanter.setContractId(contractRelevanterVO.getContractId());
        contractRelevanter.setContacter(contractRelevanterVO.getContacter());
        contractRelevanter.setPhoneNo(contractRelevanterVO.getPhoneNo());
        contractRelevanter.setEmail(contractRelevanterVO.getEmail());
        contractRelevanter.setFax(contractRelevanterVO.getFax());
        contractRelevanter.setType(contractRelevanterVO.getType());
        contractRelevanter.setAddress(contractRelevanterVO.getAddress());
        contractRelevanter.setDealerId(contractRelevanterVO.getDealerId());
        contractRelevanter.setDealerName(contractRelevanterVO.getDealerName());
    }

    /** getters and setters */
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

    public String geteMemberName() {
        return eMemberName;
    }

    public void seteMemberName(String eMemberName) {
        this.eMemberName = eMemberName;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
