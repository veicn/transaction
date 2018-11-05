package com.sinochem.crude.trade.shipping.model.vo;

import java.util.Date;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.base.BaseDomainVO;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.shipping.domain.ConfirmationSheet;

public class ConfirmationSheetVO extends BaseDomainVO<ConfirmationSheet> {

	private static final long serialVersionUID = 1L;

	/** UUID */
	private String uuid;

	/**
	 * 租船协议uuid
	 */
	private String agreementUuid;

	/** 买家ID */
	private Long buyerId;

	/** 卖家ID */
	private Long sellerId;

	/** 订单ID */
	private Long orderId;
	
	/**订单Uuid*/
	private String orderUuid;
	
	/** 订单编码 */
	private String orderNumber;

	/** 订单种类 */
	private String tradeTerms;

	/** 租船代理协议ID */
	private Long shipAgreementId;


	/** 需求单UUID*/
	private String demandsUuid;

	/** 租船合同编号 */
	private String charterPartyNumber;

	/** 租船合同日期 */
	private String charterPartyDate;

	/** 托运人 */
	private String consignor;

	/** 租船契约 */
	private String charterParty;

	/** 船舶名称 */
	private String vesselName;

	/** 产品 */
	private String product;

	/** 数量(吨） */
	private String quantity;

	/** 误差范围 */
	private String rangeOfError;

	/** 装货港口 */
	private String portOfLoading;

	/** 卸货港口 */
	private String portOfDischarge;

	/** 受载期开始 */
	private String laycanStrat;

	/** 受载期结束 */
	private String laycanEnd;

	/** 附件地址 */
	private String uploadQ88;

	/** Q88文件名称 */
	private String uploadQ88FileNm;

	/** 附件地址 */
	private String uploadCp;

	/** 合同文件名称 */
	private String uploadCpFileNm;

	/** 卖家是否在线确认标识 */
	private String confirmOnline;

	/** 到货期 */
	private String ddr;

	/** 定价方式CD */
	private String pricingCd;

	/** 定价方式 */
	private String pricingMethod;

	/** 计费种类CD */
	private String revenueTonCd;

	/** 计费种类 */
	private String revenueTon;

	/** 基本运费率 */
	private java.math.BigDecimal basicFreightRate;

	/** 装卸滞纳金 */
	private java.math.BigDecimal demurrageRates;

	/***/
	private Integer laytimeHours;

	/** 许可证日期 */
	private String charterDate;

	/** 备考 */
	private String remark;

	/** 船代ID */
	private Long shippingAgentId;

	/** 船代 */
	private String shippingAgent;

	/** 联系人 */
	private String linkman;

	/** 电话 */
	private String phoneNumber;

	/** 数据创建者ID */
	private Long epMemberId;

	/** 状态 */
	private String status;

	/** 标识 */
	private String imo;

	/** 产品来源 */
	private String productSourceCode;

	/**
	 * 创建开始时间
	 */
	private String createStart;

	/**
	 * 创建结束时间
	 */
	private String createEnd;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 泉炼表示Flag
	 */
	private String quanzhouFlag;

	/**
	 * 文件格式
	 */
	private String fileFormat;

	private Integer type;

	private String confirmationSheetCd;

	public String getQuanzhouFlag() {
		return quanzhouFlag;
	}

	public void setQuanzhouFlag(String quanzhouFlag) {
		this.quanzhouFlag = quanzhouFlag;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	
	public String getAgreementUuid() {
		return agreementUuid;
	}

	public void setAgreementUuid(String agreementUuid) {
		this.agreementUuid = agreementUuid;
	}
	public String getDemandsUuid() {
		return demandsUuid;
	}

	public void setDemandsUuid(String demandsUuid) {
		this.demandsUuid = demandsUuid;
	}
	public String getConfirmationSheetCd() {
		return confirmationSheetCd;
	}

	public void setConfirmationSheetCd(String confirmationSheetCd) {
		this.confirmationSheetCd = confirmationSheetCd;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateStart() {
		return createStart;
	}

	public void setCreateStart(String createStart) {
		this.createStart = createStart;
	}

	public String getCreateEnd() {
		return createEnd;
	}

	public void setCreateEnd(String createEnd) {
		this.createEnd = createEnd;
	}

	public String getProductSourceCode() {
		return productSourceCode;
	}

	public void setProductSourceCode(String productSourceCode) {
		this.productSourceCode = productSourceCode;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getTradeTerms() {
		return tradeTerms;
	}

	public void setTradeTerms(String tradeTerms) {
		this.tradeTerms = tradeTerms;
	}

	public String getRangeOfError() {
		return rangeOfError;
	}

	public void setRangeOfError(String rangeOfError) {
		this.rangeOfError = rangeOfError;
	}

	public String getImo() {
		return imo;
	}

	public void setImo(String imo) {
		this.imo = imo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getOrderUuid() {
		return orderUuid;
	}
	
	public void setOrderUuid(String orderUuid) {
		this.orderUuid = orderUuid;
	}
	
	public Long getShipAgreementId() {
		return shipAgreementId;
	}

	public void setShipAgreementId(Long shipAgreementId) {
		this.shipAgreementId = shipAgreementId;
	}

	public String getCharterPartyNumber() {
		return charterPartyNumber;
	}

	public void setCharterPartyNumber(String charterPartyNumber) {
		this.charterPartyNumber = charterPartyNumber;
	}

	public String getCharterPartyDate() {
		return charterPartyDate;
	}

	public void setCharterPartyDate(String charterPartyDate) {
		this.charterPartyDate = charterPartyDate;
	}

	public String getConsignor() {
		return consignor;
	}

	public void setConsignor(String consignor) {
		this.consignor = consignor;
	}

	public String getCharterParty() {
		return charterParty;
	}

	public void setCharterParty(String charterParty) {
		this.charterParty = charterParty;
	}

	public String getVesselName() {
		return vesselName;
	}

	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPortOfLoading() {
		return portOfLoading;
	}

	public void setPortOfLoading(String portOfLoading) {
		this.portOfLoading = portOfLoading;
	}

	public String getPortOfDischarge() {
		return portOfDischarge;
	}

	public void setPortOfDischarge(String portOfDischarge) {
		this.portOfDischarge = portOfDischarge;
	}

	public String getLaycanStrat() {
		return laycanStrat;
	}

	public void setLaycanStrat(String laycanStrat) {
		this.laycanStrat = laycanStrat;
	}

	public String getLaycanEnd() {
		return laycanEnd;
	}

	public void setLaycanEnd(String laycanEnd) {
		this.laycanEnd = laycanEnd;
	}

	public String getUploadQ88() {
		return uploadQ88;
	}

	public void setUploadQ88(String uploadQ88) {
		this.uploadQ88 = uploadQ88;
	}

	public String getUploadQ88FileNm() {
		return uploadQ88FileNm;
	}

	public void setUploadQ88FileNm(String uploadQ88FileNm) {
		this.uploadQ88FileNm = uploadQ88FileNm;
	}

	public String getUploadCp() {
		return uploadCp;
	}

	public void setUploadCp(String uploadCp) {
		this.uploadCp = uploadCp;
	}

	public String getUploadCpFileNm() {
		return uploadCpFileNm;
	}

	public void setUploadCpFileNm(String uploadCpFileNm) {
		this.uploadCpFileNm = uploadCpFileNm;
	}

	public String getConfirmOnline() {
		return confirmOnline;
	}

	public void setConfirmOnline(String confirmOnline) {
		this.confirmOnline = confirmOnline;
	}

	public String getDdr() {
		return ddr;
	}

	public void setDdr(String ddr) {
		this.ddr = ddr;
	}

	public String getPricingCd() {
		return pricingCd;
	}

	public void setPricingCd(String pricingCd) {
		this.pricingCd = pricingCd;
	}

	public String getPricingMethod() {
		return pricingMethod;
	}

	public void setPricingMethod(String pricingMethod) {
		this.pricingMethod = pricingMethod;
	}

	public String getRevenueTonCd() {
		return revenueTonCd;
	}

	public void setRevenueTonCd(String revenueTonCd) {
		this.revenueTonCd = revenueTonCd;
	}

	public String getRevenueTon() {
		return revenueTon;
	}

	public void setRevenueTon(String revenueTon) {
		this.revenueTon = revenueTon;
	}

	public java.math.BigDecimal getBasicFreightRate() {
		return basicFreightRate;
	}

	public void setBasicFreightRate(java.math.BigDecimal basicFreightRate) {
		this.basicFreightRate = basicFreightRate;
	}

	public java.math.BigDecimal getDemurrageRates() {
		return demurrageRates;
	}

	public void setDemurrageRates(java.math.BigDecimal demurrageRates) {
		this.demurrageRates = demurrageRates;
	}

	public Integer getLaytimeHours() {
		return laytimeHours;
	}

	public void setLaytimeHours(Integer laytimeHours) {
		this.laytimeHours = laytimeHours;
	}

	public String getCharterDate() {
		return charterDate;
	}

	public void setCharterDate(String charterDate) {
		this.charterDate = charterDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getShippingAgentId() {
		return shippingAgentId;
	}

	public void setShippingAgentId(Long shippingAgentId) {
		this.shippingAgentId = shippingAgentId;
	}

	public String getShippingAgent() {
		return shippingAgent;
	}

	public void setShippingAgent(String shippingAgent) {
		this.shippingAgent = shippingAgent;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getEpMemberId() {
		return epMemberId;
	}

	public void setEpMemberId(Long epMemberId) {
		this.epMemberId = epMemberId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	protected void convertToVO(ConfirmationSheet confirmationSheet) {
		if (confirmationSheet.getUuid() != null) {
			this.uuid = confirmationSheet.getUuid();
		}
		if (confirmationSheet.getVesselName() != null) {
			this.vesselName = confirmationSheet.getVesselName();
		}

	}

	/**
	 * 将领域类domain转化为VO
	 */
	public ConfirmationSheetVO domainToVO(ConfirmationSheet confirmationSheet) {
		ConfirmationSheetVO confirmationSheetVO = new ConfirmationSheetVO();

		if (StringUtil.isNotBlank(confirmationSheet.getOrderNumber())) {
			confirmationSheetVO.setOrderNumber(confirmationSheet
					.getOrderNumber());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getProductSourceCode())) {
			confirmationSheetVO.setProductSourceCode(confirmationSheet
					.getProductSourceCode());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getOrderNumber())) {
			confirmationSheetVO.setOrderNumber(confirmationSheet
					.getOrderNumber());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getTradeTerms())) {
			confirmationSheetVO
					.setTradeTerms(confirmationSheet.getTradeTerms());
		}

		if (confirmationSheet.getSellerId() != null) {
			confirmationSheetVO.setSellerId(confirmationSheet.getSellerId());
		}

		if (confirmationSheet.getBuyerId() != null) {
			confirmationSheetVO.setBuyerId(confirmationSheet.getBuyerId());
		}

		if (confirmationSheet.getBasicFreightRate() != null) {
			confirmationSheetVO.setBasicFreightRate(confirmationSheet
					.getBasicFreightRate());
		}

		if (confirmationSheet.getCharterDate() != null) {
			confirmationSheetVO.setCharterDate(DateUtil
					.formatDate(confirmationSheet.getCharterDate()));
		}

		if (StringUtil.isNotBlank(confirmationSheet.getCharterParty())) {
			confirmationSheetVO.setCharterParty(confirmationSheet
					.getCharterParty());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getCharterPartyDate())) {
			confirmationSheetVO.setCharterPartyDate(confirmationSheet
					.getCharterPartyDate());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getCharterPartyNumber())) {
			confirmationSheetVO.setCharterPartyNumber(confirmationSheet
					.getCharterPartyNumber());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getConfirmOnline())) {
			confirmationSheetVO.setConfirmOnline(confirmationSheet
					.getConfirmOnline());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getConsignor())) {
			confirmationSheetVO.setConsignor(confirmationSheet.getConsignor());
		}

		if (confirmationSheet.getDdr() != null) {
			confirmationSheetVO.setDdr(DateUtil.formatDate(confirmationSheet
					.getDdr()));
		}

		if (confirmationSheet.getDemurrageRates() != null) {
			confirmationSheetVO.setDemurrageRates(confirmationSheet
					.getDemurrageRates());
		}

		if (confirmationSheet.getEpMemberId() != null) {
			confirmationSheetVO
					.setEpMemberId(confirmationSheet.getEpMemberId());
		}

		if (confirmationSheet.getLaycanEnd() != null) {
			confirmationSheetVO.setLaycanEnd(DateUtil
					.formatDate(confirmationSheet.getLaycanEnd()));
		}

		if (confirmationSheet.getLaycanStrat() != null) {
			confirmationSheetVO.setLaycanStrat(DateUtil
					.formatDate(confirmationSheet.getLaycanStrat()));
		}

		if (confirmationSheet.getLaytimeHours() != null) {
			confirmationSheetVO.setLaytimeHours(confirmationSheet
					.getLaytimeHours());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getLinkman())) {
			confirmationSheetVO.setLinkman(confirmationSheet.getLinkman());
		}

		if (confirmationSheet.getOrderId() != null) {
			confirmationSheetVO.setOrderId(confirmationSheet.getOrderId());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getPhoneNumber())) {
			confirmationSheetVO.setPhoneNumber(confirmationSheet
					.getPhoneNumber());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getPortOfDischarge())) {
			confirmationSheetVO.setPortOfDischarge(confirmationSheet
					.getPortOfDischarge());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getPortOfLoading())) {
			confirmationSheetVO.setPortOfLoading(confirmationSheet
					.getPortOfLoading());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getPricingCd())) {
			confirmationSheetVO.setPricingCd(confirmationSheet.getPricingCd());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getPricingMethod())) {
			confirmationSheetVO.setPricingMethod(confirmationSheet
					.getPricingMethod());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getProduct())) {
			confirmationSheetVO.setProduct(confirmationSheet.getProduct());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getQuantity())) {
			confirmationSheetVO.setQuantity(confirmationSheet.getQuantity());// 设置quantity
		}

		if (StringUtil.isNotBlank(confirmationSheet.getRangeOfError())) {
			confirmationSheetVO.setRangeOfError(confirmationSheet
					.getRangeOfError());// 设置rangeOfError
		}

		if (StringUtil.isNotBlank(confirmationSheet.getRemark())) {
			confirmationSheetVO.setRemark(confirmationSheet.getRemark());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getRevenueTon())) {
			confirmationSheetVO
					.setRevenueTon(confirmationSheet.getRevenueTon());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getRevenueTonCd())) {
			confirmationSheetVO.setRevenueTonCd(confirmationSheet
					.getRevenueTonCd());
		}

		if (confirmationSheet.getShipAgreementId() != null) {
			confirmationSheetVO.setShipAgreementId(confirmationSheet
					.getShipAgreementId());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getShippingAgent())) {
			confirmationSheetVO.setShippingAgent(confirmationSheet
					.getShippingAgent());
		}

		if (confirmationSheet.getShippingAgentId() != null) {
			confirmationSheetVO.setShippingAgentId(confirmationSheet
					.getShippingAgentId());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getUploadCp())) {
			confirmationSheetVO.setUploadCp(confirmationSheet.getUploadCp());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getUploadCpFileNm())) {
			confirmationSheetVO.setUploadCpFileNm(confirmationSheet
					.getUploadCpFileNm());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getUploadQ88())) {
			confirmationSheetVO.setUploadQ88(confirmationSheet.getUploadQ88());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getUploadQ88FileNm())) {
			confirmationSheetVO.setUploadQ88FileNm(confirmationSheet
					.getUploadQ88FileNm());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getUuid())) {
			confirmationSheetVO.setUuid(confirmationSheet.getUuid());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getVesselName())) {
			confirmationSheetVO
					.setVesselName(confirmationSheet.getVesselName());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getStatus())) {
			confirmationSheetVO.setStatus(confirmationSheet.getStatus());
		}

		if (StringUtil.isNotBlank(confirmationSheet.getImo())) {
			confirmationSheetVO.setImo(confirmationSheet.getImo());
		}

		if (confirmationSheet.getCreateDate() != null) {
			Date createDate = confirmationSheet.getCreateDate();
			String formatDate = DateUtil.formatDate(createDate);
			confirmationSheetVO.setCreateTime(formatDate);
		}

		if (StringUtil.isNotBlank(confirmationSheet.getConfirmationSheetCd())) {
			confirmationSheetVO.setConfirmationSheetCd(confirmationSheet
					.getConfirmationSheetCd());
		}

		return confirmationSheetVO;
	}

	/**
	 * 将VO转换为领域类domain
	 */
	public ConfirmationSheet voToDomain() {
		ConfirmationSheet confirmationSheet = new ConfirmationSheet();

		if (StringUtil.isNotBlank(this.tradeTerms)) {
			confirmationSheet.setTradeTerms(this.tradeTerms);
		}

		if (StringUtil.isNotBlank(this.orderNumber)) {
			confirmationSheet.setOrderNumber(this.orderNumber);
		}

		if (this.sellerId != null) {
			confirmationSheet.setSellerId(this.sellerId);
		}

		if (StringUtil.isNotBlank(this.productSourceCode)) {
			confirmationSheet.setProductSourceCode(this.productSourceCode);
		}

		if (this.buyerId != null) {
			confirmationSheet.setBuyerId(this.buyerId);
		}

		if (this.basicFreightRate != null) {
			confirmationSheet.setBasicFreightRate(this.basicFreightRate);
		}

		if (StringUtil.isNotBlank(this.charterDate)) {
			confirmationSheet
					.setCharterDate(DateUtil.getDate(this.charterDate));
		}

		if (StringUtil.isNotBlank(this.charterParty)) {
			confirmationSheet.setCharterParty(this.charterParty);
		}

		if (StringUtil.isNotBlank(this.charterPartyDate)) {
			confirmationSheet.setCharterPartyDate(this.charterPartyDate);
		}

		if (StringUtil.isNotBlank(this.charterPartyNumber)) {
			confirmationSheet.setCharterPartyNumber(this.charterPartyNumber);
		}

		if (StringUtil.isNotBlank(this.confirmOnline)) {
			confirmationSheet.setConfirmOnline(this.confirmOnline);
		}

		if (StringUtil.isNotBlank(this.consignor)) {
			confirmationSheet.setConsignor(this.consignor);
		}

		if (StringUtil.isNotBlank(this.ddr)) {
			confirmationSheet.setDdr(DateUtil.getDate(this.ddr));
		}

		if (this.demurrageRates != null) {
			confirmationSheet.setDemurrageRates(this.demurrageRates);
		}

		if (StringUtil.isNotBlank(this.laycanStrat)) {
			confirmationSheet
					.setLaycanStrat(DateUtil.getDate(this.laycanStrat));
		}

		if (StringUtil.isNotBlank(this.laycanEnd)) {
			confirmationSheet.setLaycanEnd(DateUtil.getDate(this.laycanEnd));
		}

		if (this.laytimeHours != null) {
			confirmationSheet.setLaytimeHours(this.laytimeHours);
		}

		if (StringUtil.isNotBlank(this.linkman)) {
			confirmationSheet.setLinkman(this.linkman);
		}
		
		if (this.orderId != null) {
			confirmationSheet.setOrderId(this.orderId);
		}

		if (StringUtil.isNotBlank(this.phoneNumber)) {
			confirmationSheet.setPhoneNumber(this.phoneNumber);
		}

		if (StringUtil.isNotBlank(this.portOfDischarge)) {
			confirmationSheet.setPortOfDischarge(this.portOfDischarge);
		}

		if (StringUtil.isNotBlank(this.portOfLoading)) {
			confirmationSheet.setPortOfLoading(this.portOfLoading);
		}

		if (StringUtil.isNotBlank(this.pricingCd)) {
			confirmationSheet.setPricingCd(this.pricingCd);
		}

		if (StringUtil.isNotBlank(this.pricingMethod)) {
			confirmationSheet.setPricingMethod(this.pricingMethod);
		}

		if (StringUtil.isNotBlank(this.product)) {
			confirmationSheet.setProduct(this.product);
		}

		if (StringUtil.isNotBlank(this.quantity)) {
			confirmationSheet.setQuantity(this.quantity);
		}

		if (StringUtil.isNotBlank(this.rangeOfError)) {
			confirmationSheet.setRangeOfError(this.rangeOfError);
		}

		if (StringUtil.isNotBlank(this.remark)) {
			confirmationSheet.setRemark(this.remark);
		}

		if (StringUtil.isNotBlank(this.revenueTon)) {
			confirmationSheet.setRevenueTon(this.revenueTon);
		}

		if (StringUtil.isNotBlank(this.revenueTonCd)) {
			confirmationSheet.setRevenueTonCd(this.revenueTonCd);
		}

		if (StringUtil.isNotBlank(this.shippingAgent)) {
			confirmationSheet.setShippingAgent(this.shippingAgent);
		}

		if (this.shipAgreementId != null) {
			confirmationSheet.setShipAgreementId(this.shipAgreementId);
		}

		if (this.shippingAgentId != null) {
			confirmationSheet.setShippingAgentId(this.shippingAgentId);
		}

		if (this.shippingAgent != null) {
			confirmationSheet.setShippingAgent(this.shippingAgent);
		}

		if (StringUtil.isNotBlank(this.uploadCp)) {
			confirmationSheet.setUploadCp(this.uploadCp);
		}

		if (StringUtil.isNotBlank(this.uploadCpFileNm)) {
			confirmationSheet.setUploadCpFileNm(this.uploadCpFileNm);
		}

		if (StringUtil.isNotBlank(this.uploadQ88)) {
			confirmationSheet.setUploadQ88(this.uploadQ88);
		}

		if (StringUtil.isNotBlank(this.uploadQ88FileNm)) {
			confirmationSheet.setUploadQ88FileNm(this.uploadQ88FileNm);
		}

		if (StringUtil.isNotBlank(this.uuid)) {
			confirmationSheet.setUuid(this.uuid);
		}

		if (StringUtil.isNotBlank(this.vesselName)) {
			confirmationSheet.setVesselName(this.vesselName);
		}

		if (StringUtil.isNotBlank(this.status)) {
			confirmationSheet.setStatus(this.status);
		}

		if (StringUtil.isNotBlank(this.imo)) {
			confirmationSheet.setImo(this.imo);
		}

		if (StringUtil.isNotBlank(this.createTime)) {
			confirmationSheet.setCreateDate(DateUtil.getDate(this.createTime));
		}

		return confirmationSheet;
	}

	@Override
	protected ConfirmationSheet convertToDomain() {
		ConfirmationSheet confirmationSheet = new ConfirmationSheet();

		return confirmationSheet;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}