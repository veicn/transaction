package com.sinochem.crude.trade.order.domain;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.order.constart.MsgConstart;
import com.sinochem.crude.trade.order.model.valid.CrudeOilValid;
import com.sinochem.crude.trade.order.model.valid.LongTermValid;
import com.sinochem.crude.trade.order.model.valid.ProductOilValid;
import com.sinochem.it.b2b.common.exception.BizException;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 合约
 * 
 * @author Leo
 *
 */
public class Contract {

	public static final String ORDER_STATUS_CONFIG = "crude_oil_import_12_31";
	/**
	 *
	 */
	private Long id;
	/**
	 *
	 */
	private String token;



	private boolean cancelFlag;
	/**
	 * 买方
	 */
	private Long buyer;
	/**
	 * 卖方
	 */
	private Long seller;
	private Date updateTime;
	private Long updater;
	private Long version;
	/**
	 * 付款类型
	 */
	private Long payType;
	/**
	 * 数量
	 */
	private Long num;
	/**
	 * 数量国际化
	 */
	private Long enNum;


	/**
	 * 单价
	 */
	private Integer unitPrice;

	private Long trader;
	/**
	 * 发起方
	 */
	private Long initiator;

	/**
	 * 订单类型，长约，短约，由系统指定，不是页面输入
	 */
	private String contractType;

	/**
	 * 数量偏差
	 */
	private String numFloat;

	/**
	 * 关联合约id
	 */
	private Long referenceId;

	/**
	 * 计价公式
	 */
	private String valuationFormulaJson;

	/**
	 * 计价公式
	 */
	private String valuationFormula;

	/**
	 * 贸易条款
	 */
	private Integer tradeItem;

	/**
	 * 贴水
	 */
	private Long agio;

	/**
	 * 计价基准
	 */
	private String valuationBase;

	/**
	 * 计价期类型
	 */
	private Integer valuationProidType;
	/**
	 * 计价期开始时间
	 */
	private Date valuationProidStart;
	/**
	 * 计价期结束时间
	 */
	private Date valuationProidEnd;

	/**
	 * 付款条款
	 */
	private String payItem;

	private String payItemJSON;

	public String getPayItemJSON() {
		return payItemJSON;
	}

	public void setPayItemJSON(String payItemJSON) {
		this.payItemJSON = payItemJSON;
	}

	/**
	 * 付款时间
	 */
	private Date payTime;

	/**
	 * 执行人
	 */
	private Long executor;

	public Long getEnNum() {
		return enNum;
	}

	public void setEnNum(Long enNum) {
		this.enNum = enNum;
	}

	/**
	 * 订单状态<br/>
	 * <item code="DR" fallback="false" skip="false"/><br/>
	 * <item code="delivery" fallback="false" skip="false"/><br/>
	 * <item code="fund" fallback="true" skip="false"/><br/>
	 * <item code="statement" fallback="false" skip="false"/><br/>
	 */
	private String orderStatus;

	/**
	 * 采购方式
	 */
	private Integer purchaseMode;

	/**
	 * 双签状态 1-订单确认 2-订单修改 3-订单取消
	 */
	private String doubleStatus;

	/**
	 * 双签，两个字符，00，第一个代表买确认，第二个是代表卖确认
	 */
	private String doubleSign = "00";

	private boolean buySign = false;

	private boolean sellerSign = false;

	private ContractShip contractShip;

	/**
	 * 单价
	 */
	private BigDecimal price;

	/**
	 * 单据类型
	 */
	private String type;

	/**
	 * 交易类型，B 买 S 卖
	 */
	private String dealType;

	/**
	 * 业务类型，原油业务还是成品油业务
	 */
	private String bizType;

	/**
	 * 绑定的采购单或需求单
	 */
	private Long biddingId;

	/**
	 * 报价类型：投标、询价
	 */
	private Integer purchaseType;

	/**
	 * 单位
	 */
	private Long unit;

	/**
	 * 比率
	 */
	private Integer rate;

	/**
	 * 计量数量
	 */
	private Long meterNum;

	/**
	 * 计量单位
	 */
	private Long meterUnit;

	/**
	 * uuid
	 */
	private String uuid;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建人
	 */
	private Long creater;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 订单编号
	 */
	private String orderNo;

	/**
	 * 其他条款
	 */
	private String otherItem;

	/**
	 * 信用条款
	 */
	private String authItem;

	/**
	 * 商检机构
	 */
	private String businessCheckOrg;

	/**
	 * 出口配额文件
	 */
	private String exportConfFile;

	/**
	 * 出标时间
	 */
	private Date tenderOutTime;

	/**
	 * 截标时间
	 */
	private Date stopBidTime;

	/**
	 * 允许装卸货时间
	 */
	private String loadAndDischargePermittedTimespan;

	/**
	 * 计量方式
	 */
	private Integer measureMode;

	private ContractBuyer contractBuyer;

	private ContractSupplier contractSupplier;

	private ContractTrader contractTrader;

	/**
	 * 合约种类
	 */
	private String contractKind;

	/**
	 * 商检费分摊类型
	 */
	private Integer inspectionFeeSharingType;
	/**
	 * 商检费分摊类型
	 */
	private String inspectionFeeSharingTypeContent;
	/**
	 * 法律
	 */
	private String law;
	/**
	 * GTC
	 */
	private String gtc;
	/**文件路径*/
    private String filePath;
    /**文件名称*/
    private String originalName;
    /**"0":提交 ;"1":保存*/
    private String isSubmit;
    
    public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getIsSubmit() {
		return isSubmit;
	}

	public void setIsSubmit(String isSubmit) {
		this.isSubmit = isSubmit;
	}
	public String getLaw() {
		return law;
	}

	public void setLaw(String law) {
		this.law = law;
	}

	public String getGtc() {
		return gtc;
	}

	public void setGtc(String gtc) {
		this.gtc = gtc;
	}

	public String getInspectionFeeSharingTypeContent() {
		return inspectionFeeSharingTypeContent;
	}

	public void setInspectionFeeSharingTypeContent(String inspectionFeeSharingTypeContent) {
		this.inspectionFeeSharingTypeContent = inspectionFeeSharingTypeContent;
	}

	/** getters and setters */
	public ContractShip getContractShip() {
		return contractShip;
	}

	public void setContractShip(ContractShip contractShip) {
		this.contractShip = contractShip;
	}

	public ContractBuyer getContractBuyer() {
		return contractBuyer;
	}

	public void setContractBuyer(ContractBuyer contractBuyer) {
		this.contractBuyer = contractBuyer;
		this.setBuyer(contractBuyer.getEMemberId());
	}

	public ContractSupplier getContractSupplier() {
		return contractSupplier;
	}

	public void setContractSupplier(ContractSupplier contractSupplier) {
		this.contractSupplier = contractSupplier;
		this.setSeller(contractSupplier.getEMemberId());
	}

	public ContractTrader getContractTrader() {
		return contractTrader;
	}

	public void setContractTrader(ContractTrader contractTrader) {
		this.contractTrader = contractTrader;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public Long getBuyer() {
		return buyer;
	}

	public void setBuyer(Long buyer) {
		this.buyer = buyer;
	}

	public Long getSeller() {
		return seller;
	}

	public void setSeller(Long seller) {
		this.seller = seller;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * 版本增加，每次加1
	 */
	public void addVersion() {
		this.version = null == version ? 1 : (version.longValue() + 1);
	}

	public Long getPayType() {
		return payType;
	}

	public void setPayType(Long payType) {
		this.payType = payType;
	}

	public boolean isBuySign() {
		return buySign;
	}

	public boolean isSellerSign() {
		return sellerSign;
	}

	/**
	 * 如果是确认前取消，就是关闭
	 */
	public boolean isClosed() {
		return this.isCancelFlag() && !isConfirm();
	}

	/**
	 * 如果是确认后取消，就是取消
	 */
	public boolean isCancel() {
		return this.isCancelFlag() && isConfirm();
	}

	public String getDoubleStatus() {
		return doubleStatus;
	}

	public void setDoubleStatus(String doubleStatus) {
		this.doubleStatus = doubleStatus;
	}

	public String getDoubleSign() {
		return doubleSign;
	}

	/**
	 * update by sijliu
	 * @param doubleSign
	 */
	public void setDoubleSign(String doubleSign) {
		doubleSign = StringUtil.isEmpty(doubleSign) ? "00" : doubleSign;

		this.doubleSign = doubleSign;
		this.buySign = doubleSign.charAt(0) == '1';
		this.sellerSign = doubleSign.charAt(1) == '1';
	}

	/**
	 * 重设双签值
	 */
	public void resetDoubleSign(){
		this.doubleSign = this.buySign ? "1" : "0";
		this.doubleSign += this.sellerSign ? "1" : "0";
	}

	@NotEmpty(groups = {CrudeOilValid.class, ProductOilValid.class})
	public String getNumFloat() {
		return numFloat;
	}

	public void setNumFloat(String numFloat) {
		this.numFloat = numFloat;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	@NotEmpty(groups = {CrudeOilValid.class, ProductOilValid.class})
	public String getValuationFormula() {
		return valuationFormula;
	}

	public void setValuationFormula(String valuationFormula) {
		this.valuationFormula = valuationFormula;
	}

	@NotNull(message = "不能为空", groups = {CrudeOilValid.class, ProductOilValid.class, LongTermValid.class})
	public Integer getTradeItem() {
		return tradeItem;
	}

	public void setTradeItem(Integer tradeItem) {
		this.tradeItem = tradeItem;
	}

	public Long getAgio() {
		return agio;
	}

	public void setAgio(Long agio) {
		this.agio = agio;
	}

	@NotEmpty(groups = {CrudeOilValid.class, ProductOilValid.class})
	public String getValuationBase() {
		return valuationBase;
	}

	public void setValuationBase(String valuationBase) {
		this.valuationBase = valuationBase;
	}

	@NotNull(message = "不能为空", groups = {CrudeOilValid.class, ProductOilValid.class})
	public Integer getValuationProidType() {
		return valuationProidType;
	}

	public void setValuationProidType(Integer valuationProidType) {
		this.valuationProidType = valuationProidType;
	}

	public Date getValuationProidStart() {
		return valuationProidStart;
	}

	public void setValuationProidStart(Date valuationProidStart) {
		this.valuationProidStart = valuationProidStart;
	}

	public Date getValuationProidEnd() {
		return valuationProidEnd;
	}

	public void setValuationProidEnd(Date valuationProidEnd) {
		this.valuationProidEnd = valuationProidEnd;
	}

	@NotNull(message = "不能为空", groups = {CrudeOilValid.class, ProductOilValid.class, LongTermValid.class})
	public String getPayItem() {
		return payItem;
	}

	public void setPayItem(String payItem) {
		this.payItem = payItem;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@NotNull(message = "不能为空", groups = {CrudeOilValid.class, ProductOilValid.class})
	public Integer getPurchaseMode() {
		return purchaseMode;
	}

	public void setPurchaseMode(Integer purchaseMode) {
		this.purchaseMode = purchaseMode;
	}

	public Long getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}

	public Long getInitiator() {
		return initiator;
	}

	/**
	 * 确认操作--通过设置操作人 来重新定义双签字段
	 * @param oper
	 */
	public void confirm(Long oper,String oldDoubleSign) {
		oldDoubleSign = StringUtil.isEmpty(oldDoubleSign) ? "00" : oldDoubleSign;
		//买家确认
		if (oper.equals(this.getBuyer())) {
			this.doubleSign = "1" + oldDoubleSign.charAt(1);
		}
		//卖家确认
		if (oper.equals(this.getSeller())) {
			this.doubleSign = oldDoubleSign.charAt(0) + "1";
		}
	}
	/**
	 * 修改操作--通过设置操作人 来重新定义双签字段
	 * @param oper
	 */
	public void update(Long oper) {
		this.setToken(String.valueOf(System.currentTimeMillis()));
		//买家修改
		if (oper.equals(this.getBuyer())) {
			this.sellerSign = false;
			this.buySign = true;
		}
		//卖家修改
		if (oper.equals(this.getSeller())) {
			this.sellerSign = true;
			this.buySign = false;
		}
		resetDoubleSign();
	}

	public void setInitiator(Long initiator) {
		this.initiator = initiator;
	}

	public void check(Long oper) throws BizException {
		//1、买方、卖方不能为空
		if (this.getBuyer() == null || this.getSeller() == null) {
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0038));
		}
		//2、三方不能出现重复
		if (this.getSeller().equals(this.getBuyer())) {
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0039));
		}
		//3、必须有一方是操作人
		if (!oper.equals(this.getBuyer())
				&& !oper.equals(this.getSeller())) {
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0040));
		}
	}

	public boolean isConfirm() {
		return "11".equals(doubleSign);
	}

	public boolean isConfirm(Long oper,String doubleSign) {
		doubleSign = StringUtil.isEmpty(doubleSign) ? "00" : doubleSign;
		//买家确认
		if (oper.equals(this.getBuyer()) && "10".equals(doubleSign)) {
			return true;
		}
		//卖家确认
		if (oper.equals(this.getSeller())  && "01".equals(doubleSign)) {
			return true;
		}
		return false;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public Long getBiddingId() {
		return biddingId;
	}

	public void setBiddingId(Long biddingId) {
		this.biddingId = biddingId;
	}

	public Long getExecutor() {
		return executor;
	}

	public void setExecutor(Long executor) {
		this.executor = executor;
	}

	public Integer getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(Integer purchaseType) {
		this.purchaseType = purchaseType;
	}

	public Long getUnit() {
		return unit;
	}

	public void setUnit(Long unit) {
		this.unit = unit;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Long getMeterNum() {
		return meterNum;
	}

	public void setMeterNum(Long meterNum) {
		this.meterNum = meterNum;
	}

	public Long getMeterUnit() {
		return meterUnit;
	}

	public void setMeterUnit(Long meterUnit) {
		this.meterUnit = meterUnit;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getCreater() {
		return creater;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOtherItem() {
		return otherItem;
	}

	public void setOtherItem(String otherItem) {
		this.otherItem = otherItem;
	}

	public String getAuthItem() {
		return authItem;
	}

	public void setAuthItem(String authItem) {
		this.authItem = authItem;
	}

	public String getBusinessCheckOrg() {
		return businessCheckOrg;
	}

	public void setBusinessCheckOrg(String businessCheckOrg) {
		this.businessCheckOrg = businessCheckOrg;
	}

	public String getExportConfFile() {
		return exportConfFile;
	}

	public void setExportConfFile(String exportConfFile) {
		this.exportConfFile = exportConfFile;
	}

	public Date getTenderOutTime() {
		return tenderOutTime;
	}

	public void setTenderOutTime(Date tenderOutTime) {
		this.tenderOutTime = tenderOutTime;
	}

	public Date getStopBidTime() {
		return stopBidTime;
	}

	public void setStopBidTime(Date stopBidTime) {
		this.stopBidTime = stopBidTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdater() {
		return updater;
	}

	public void setUpdater(Long updater) {
		this.updater = updater;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setBuySign(boolean buySign) {
		this.buySign = buySign;
	}

	public void setSellerSign(boolean sellerSign) {
		this.sellerSign = sellerSign;
	}

	public String getValuationFormulaJson() {
		return valuationFormulaJson;
	}

	public void setValuationFormulaJson(String valuationFormulaJson) {
		this.valuationFormulaJson = valuationFormulaJson;
	}

	public void setPurchaseMode(int purchaseMode) {
		this.purchaseMode = purchaseMode;
	}

	@NotEmpty(groups = {CrudeOilValid.class, ProductOilValid.class})
	public String getNumStr() {
		return this.num == null ? "" : BigDecimal.valueOf(this.num).divide(new BigDecimal(1000)).toString();
	}

	@NotEmpty(groups = {CrudeOilValid.class, ProductOilValid.class})
	public String getAgioStr() {
		return this.agio == null ? "" : BigDecimal.valueOf(this.agio).divide(new BigDecimal(1000)).toString();
	}

	public String getLoadAndDischargePermittedTimespan() {
		return loadAndDischargePermittedTimespan;
	}

	public void setLoadAndDischargePermittedTimespan(String loadAndDischargePermittedTimespan) {
		this.loadAndDischargePermittedTimespan = loadAndDischargePermittedTimespan;
	}

	public Integer getMeasureMode() {
		return measureMode;
	}

	public void setMeasureMode(Integer measureMode) {
		this.measureMode = measureMode;
	}

	public String getContractKind() {
		return contractKind;
	}

	public void setContractKind(String contractKind) {
		this.contractKind = contractKind;
	}

	public Integer getInspectionFeeSharingType() {
		return inspectionFeeSharingType;
	}

	public void setInspectionFeeSharingType(Integer inspectionFeeSharingType) {
		this.inspectionFeeSharingType = inspectionFeeSharingType;
	}

	public Long getTrader() {
		return trader;
	}

	public void setTrader(Long trader) {
		this.trader = trader;
	}
}
