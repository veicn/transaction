package com.sinochem.crude.trade.order.domain;

import com.sinochem.crude.trade.order.model.valid.CrudeOilValid;
import com.sinochem.crude.trade.order.model.valid.LongTermValid;
import com.sinochem.crude.trade.order.model.valid.ProductOilValid;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * 交易相关人
 * 
 * @author Leo
 *
 */
public class ContractRelevanter {

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

	@NotEmpty(groups = {CrudeOilValid.class, ProductOilValid.class, LongTermValid.class})
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getEMemberId() {
		return eMemberId;
	}

	public void setEMemberId(Long eMemberId) {
		this.eMemberId = eMemberId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	@NotEmpty(groups = {CrudeOilValid.class, ProductOilValid.class, LongTermValid.class})
	public String getContacter() {
		return contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@NotEmpty(groups = {CrudeOilValid.class, ProductOilValid.class})
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@NotEmpty(groups = {CrudeOilValid.class, ProductOilValid.class})
	@Email(groups = {CrudeOilValid.class, ProductOilValid.class, LongTermValid.class})
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty(groups = {CrudeOilValid.class, ProductOilValid.class})
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@NotEmpty(groups = {CrudeOilValid.class, ProductOilValid.class})
	public String geteMemberName() {
		return eMemberName;
	}

	public void seteMemberName(String eMemberName) {
		this.eMemberName = eMemberName;
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
