package com.sinochem.crude.trade.listed.model.vo;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.sinochem.crude.trade.listed.domain.DemandRelevanter;

/**
 * 代理商
 */
public class DemandRelevanterAgentVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * PK
	 */
	private Long id;

	/**
	 * 买家 卖家 贸易商id
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
	 * 联系人
	 */
	private String contacter;

	/**
	 * 电话
	 */
	private String phoneNo;

	/**
	 * 邮箱
	 */
	private String famil;

	/**
	 * 传真
	 */
	private String fax;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 企业名称
	 */
	private String enterpriseName;

	/**
	 * 企业地址
	 */
	private String enterpriseAddress;

	/**
	 * 交易员id
	 */
	private Long dealerId;

	/**
	 * 交易员名称
	 */
	private String dealerName;

	public DemandRelevanterAgentVO() {
	}

	/**
	 * 构造函数 将领域模型转换为vo
	 * 
	 * @param demandRelevanter
	 *            需求的领域模型
	 */
	public DemandRelevanterAgentVO(DemandRelevanter demandRelevanter) {
		if(demandRelevanter == null) {
			return;
		}
		this.id = demandRelevanter.getId();
		this.eMemberId = demandRelevanter.getEMemberId();
		this.demandId = demandRelevanter.getDemandId();
		this.share = demandRelevanter.getShare();
		this.contacter = demandRelevanter.getContacter();
		this.phoneNo = demandRelevanter.getPhoneNo();
		this.famil = demandRelevanter.getFamil();
		this.fax = demandRelevanter.getFax();
		this.type = demandRelevanter.getType();
		this.enterpriseName = demandRelevanter.getEnterpriseName();
		this.enterpriseAddress = demandRelevanter.getEnterpriseAddress();
		this.dealerId = demandRelevanter.getDealerId();
		this.dealerName = demandRelevanter.getDealerName();
	}

	/**
	 * 将vo转换为领域模型
	 * 
	 * @return
	 */
	public static DemandRelevanter convertVoToDomain(DemandRelevanterAgentVO vo) {
		DemandRelevanter demandRelevanter = new DemandRelevanter();
		if (vo != null) {
			demandRelevanter.setContacter(vo.getContacter());
			demandRelevanter.setDealerId(vo.getDealerId());
			demandRelevanter.setDealerName(vo.getDealerName());
			demandRelevanter.setDemandId(vo.getDemandId());
			demandRelevanter.setEMemberId(vo.geteMemberId());
			demandRelevanter.setEnterpriseAddress(vo.getEnterpriseAddress());
			demandRelevanter.setEnterpriseName(vo.getEnterpriseName());
			demandRelevanter.setFamil(vo.getFamil());
			demandRelevanter.setFax(vo.getFax());
			demandRelevanter.setId(vo.getId());
			demandRelevanter.setPhoneNo(vo.getPhoneNo());
			demandRelevanter.setShare(vo.getShare());
			demandRelevanter.setType(vo.getType());
		}

		return demandRelevanter;
	}
	
	/**
	 * 将领域模型转换为vo
	 * 
	 * @return
	 */
	public static DemandRelevanterAgentVO convertDomainToVo(DemandRelevanter domain) {
		DemandRelevanterAgentVO vo = new DemandRelevanterAgentVO();
		
		if (domain != null) {
			vo.setId(domain.getId());
			vo.seteMemberId(domain.getEMemberId());
			vo.setDemandId(domain.getDemandId());
			vo.setShare(domain.getShare());
			vo.setContacter(domain.getContacter());
			vo.setPhoneNo(domain.getPhoneNo());
			vo.setFamil(domain.getFamil());
			vo.setFax(domain.getFax());
			vo.setType(domain.getType());
			vo.setEnterpriseName(domain.getEnterpriseName());
			vo.setEnterpriseAddress(domain.getEnterpriseAddress());
			vo.setDealerId(domain.getDealerId());
			vo.setDealerName(domain.getDealerName());
		}
		
		return vo;
	}

	@NotEmpty(message = "不能为空")
	public String getContacter() {
		return contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	@NotEmpty(message = "不能为空")
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@NotEmpty(message = "不能为空")
	@Email
	public String getFamil() {
		return famil;
	}

	public void setFamil(String famil) {
		this.famil = famil;
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

	@NotEmpty(message = "不能为空")
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
