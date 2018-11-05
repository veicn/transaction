package com.sinochem.crude.trade.order.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.enums.InspectionFeeSharingType;
import com.sinochem.crude.trade.common.enums.PurchaseType;
import com.sinochem.crude.trade.order.domain.*;
import com.sinochem.crude.trade.order.enums.EnumContractType;
import com.sinochem.crude.trade.order.util.DictUtils;
import com.sinochem.it.b2b.common.utils.DateUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Contract类的VO
 * @author Yichen Zhao
 * date: 20180125
 */
public class ContractVO {

    private ContractShipVO contractShip;

    private ContractBuyerVO contractBuyer;

    private ContractSupplierVO contractSupplier;

    private ContractTraderVO contractTrader;

    private Long id;

    private String token;

    private Boolean cancelFlag;

    /**
     * 买方
     */
    private Long buyer;

    /**
     * 卖方
     */
    private Long seller;

    private Date updateTime;
    private String updateTimeAsString;

    private Long updater;

    private Long version;

    /**
     * 付款类型
     * 此处存疑，payType无枚举值
     */
    private Long payType;

    /**
     * 数量
     */
    private Long num;

    /**
     * 单价
     */
    private Integer unitPrice;

    /**
     * 发起方
     */
    private Long initiator;

    /**
     * 贸易商，也就是中间商
     */
    private Long trader;

    /**
     * 代理方向，B代买，S代卖，null就是纯中间商，3方交易
     */
    private String traderAgent;

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
    private String tradeItemContent;

    /**
     * 贴水
     */
    private Long agio;

    /**
     * 计价基准
     */
    private String valuationBase;
    private String valuationBaseContent;

    /**
     * 计价期类型
     */
    private Integer valuationProidType;
    private String valuationPeriodTypeContent;

    /**
     * 计价期开始时间
     */
    private Date valuationProidStart;
    private String valuationPeriodStartAsString;

    /**
     * 计价期结束时间
     */
    private Date valuationProidEnd;
    private String valuationPeriodEndAsString;

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

    private String payItemContent;

    /**
     * 付款时间
     */
    private Date payTime;
    private String payTimeAsString;

    /**
     * 执行人
     */
    private Long executor;

    /**
     * 订单状态<br/>
     * <item code="DR" fallback="false" skip="false"/><br/>
     * <item code="delivery" fallback="false" skip="false"/><br/>
     * <item code="fund" fallback="true" skip="false"/><br/>
     * <item code="statement" fallback="false" skip="false"/><br/>
     */
    private String orderStatus = "0000";

    /**
     * 采购方式
     */
    private Integer purchaseMode;
    private String purchaseModeContent;

    /**
     * 双签，两个字符，00，第一个代表买确认，第二个是代表卖确认
     */
    private String doubleSign = "00";
    
    private String doubleStatus;
    
    public String getDoubleStatus() {
		return doubleStatus;
	}

	public void setDoubleStatus(String doubleStatus) {
		this.doubleStatus = doubleStatus;
	}

	private Boolean buySign;

    private Boolean sellerSign;

    /**
     * 单价
     */
    private BigDecimal price;
    private String priceAsString;

    /**
     * 单据类型
     */
    private String type;
    private String typeContent;

    /**
     * 交易类型，B 买 S 卖
     */
    private String dealType;
    private String dealTypeContent;

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
    private String purchaseTypeContent;

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
    private String createTimeAsString;

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
    private String businessCheckOrgContent;

    /**
     * 出口配额文件
     */
    private String exportConfFile;

    /**
     * 出标时间
     */
    private Date tenderOutTime;
    private String tenderOutTimeAsString;

    /**
     * 截标时间
     */
    private Date stopBidTime;
    private String stopBidTimeAsString;

    /**
     * 允许装卸货时间
     */
    private String loadAndDischargePermittedTimespan;

    /**
     * 计量方式
     */
    private Integer measureMode;
    private String measureModeContent;



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
    /**
     * 前端保存更新接收数量
     */
    private String numStr;
    
    public String getNumStr() {
		return numStr;
	}

	public void setNumStr(String numStr) {
		this.numStr = numStr;
	}

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
    
    /**
     * 转换为ContractVO
     * @param contract
     * @return
     */
    public static void convertToContractVO(
            Contract contract,
            ContractVO contractVO) {
        if (contract == null) {
            return;
        }

        Map<Integer, String> tradeItemMap;
        Map<Object, String> valuationBaseMap;
        Map<Object, String> valuationPeriodTypeMap;
        Map<Object, String> purchaseModeMap;
        Map<Object, String> businessCheckOrgMap;
        Map<Object, String> measureModeMap;

        contractVO.setId(contract.getId());
        contractVO.setToken(contract.getToken());
        contractVO.setCancelFlag(contract.isCancelFlag());
        contractVO.setBuyer(contract.getBuyer());
        contractVO.setSeller(contract.getSeller());
        contractVO.setDoubleStatus(contract.getDoubleStatus());
        contractVO.setNumStr(contract.getNumStr());
        
        Date updateTime = contract.getUpdateTime();
        if (updateTime != null) {
            contractVO.setUpdateTime(updateTime);
            contractVO.setUpdateTimeAsString(DateUtil.formatDate(updateTime));
        }

        contractVO.setUpdater(contract.getUpdater());
        contractVO.setVersion(contract.getVersion());
        contractVO.setPayType(contract.getPayType());
        contractVO.setNum(contract.getNum());
        contractVO.setUnitPrice(contract.getUnitPrice());
        contractVO.setInitiator(contract.getInitiator());
        contractVO.setContractType(contract.getContractType());
        contractVO.setNumFloat(contract.getNumFloat());
        contractVO.setReferenceId(contract.getReferenceId());
        contractVO.setValuationFormula(contract.getValuationFormula());
        contractVO.setValuationFormulaJson(contract.getValuationFormulaJson());

        Integer tradeItem = contract.getTradeItem();
        if (tradeItem != null) {
            contractVO.setTradeItem(tradeItem);
            tradeItemMap = DictUtils.getTradeItemMap();
            contractVO.setTradeItemContent(tradeItemMap.get(tradeItem));
        }

        contractVO.setAgio(contract.getAgio());

        String valuationBase = contract.getValuationBase();
        if (!StringUtil.isEmpty(valuationBase)) {
            contractVO.setValuationBase(valuationBase);
            valuationBaseMap = DictUtils.getValuationBaseMap();
            contractVO.setValuationBaseContent(valuationBaseMap.get(valuationBase));
        }

        Integer valuationPeriodType = contract.getValuationProidType();
        if (valuationPeriodType != null) {
            contractVO.setValuationProidType(valuationPeriodType);
            valuationPeriodTypeMap = DictUtils.getValuationProidTypeMap();
            contractVO.setValuationPeriodTypeContent(valuationPeriodTypeMap.get(valuationPeriodType));
        }

        Date valuationPeriodStart = contract.getValuationProidStart();
        if (valuationPeriodStart != null) {
            contractVO.setValuationProidStart(valuationPeriodStart);
            contractVO.setValuationPeriodStartAsString(DateUtil.formatDate(valuationPeriodStart));
        }

        Date valuationPeriodEnd = contract.getValuationProidEnd();
        if (valuationPeriodEnd != null) {
            contractVO.setValuationProidEnd(valuationPeriodEnd);
            contractVO.setValuationPeriodEndAsString(DateUtil.formatDate(valuationPeriodEnd));
        }

        String payItem = contract.getPayItem();
        if (payItem != null) {
            contractVO.setPayItem(payItem);
            contractVO.setPayItemContent(payItem);
        }
        String payItemJSON = contract.getPayItemJSON();
        if(payItemJSON != null){
            contractVO.setPayItemJSON(payItemJSON);
        }

        Date payTime = contract.getPayTime();
        if (payTime != null) {
            contractVO.setPayTime(payTime);
            contractVO.setPayTimeAsString(DateUtil.formatDate(payTime));
        }

        contractVO.setExecutor(contract.getExecutor());
        contractVO.setOrderStatus(contract.getOrderStatus());

        Integer purchaseMode = contract.getPurchaseMode();
        if (purchaseMode != null) {
            contractVO.setPurchaseMode(purchaseMode);
            purchaseModeMap = DictUtils.getPurchaseModes();
            contractVO.setPurchaseModeContent(purchaseModeMap.get(purchaseMode));
        }

        contractVO.setDoubleSign(contract.getDoubleSign());
        contractVO.setBuySign(contract.isBuySign());
        contractVO.setSellerSign(contract.isSellerSign());

        ContractShip contractShip = contract.getContractShip();
        if (contractShip != null) {
            ContractShipVO contractShipVO = new ContractShipVO();
            ContractShipVO.convertToContractShipVO(contractShip, contractShipVO);
            contractVO.setContractShip(contractShipVO);
        }

        BigDecimal price = contract.getPrice();
        if (price != null) {
            contractVO.setPrice(price);
            contractVO.setPriceAsString(price.toString());
        }

        String type = contract.getType();
        if (!StringUtil.isEmpty(type)) {
            contractVO.setType(type);
            contractVO.setTypeContent(EnumContractType.getContractTypeByCode(type).getDesc());
        }

        contractVO.setDealType(contract.getDealType());
        contractVO.setBizType(contract.getBizType());
        contractVO.setBiddingId(contract.getBiddingId());

        Integer purchaseType = contract.getPurchaseType();
        if (purchaseType != null) {
            contractVO.setPurchaseType(purchaseType);
            contractVO.setPurchaseTypeContent(PurchaseType.getPurchaseTypeByCode(purchaseType).getName());
        }

        contractVO.setUnit(contract.getUnit());
        contractVO.setRate(contract.getRate());
        contractVO.setMeterNum(contract.getMeterNum());
        contractVO.setMeterUnit(contract.getMeterUnit());
        contractVO.setUuid(contract.getUuid());
        contractVO.setRemark(contract.getRemark());
        contractVO.setCreater(contract.getCreater());

        Date createTime = contract.getCreateTime();
        if (createTime != null) {
            contractVO.setCreateTime(createTime);
            contractVO.setCreateTimeAsString(DateUtil.formatDateTime(createTime));
        }

        contractVO.setOrderNo(contract.getOrderNo());
        contractVO.setOtherItem(contract.getOtherItem());
        contractVO.setAuthItem(contract.getAuthItem());

        String businessCheckOrg = contract.getBusinessCheckOrg();
        if (!StringUtil.isEmpty(businessCheckOrg)) {
            contractVO.setBusinessCheckOrg(businessCheckOrg);
            businessCheckOrgMap = DictUtils.getBusinessCheckOrg();
            contractVO.setBusinessCheckOrgContent(businessCheckOrgMap.get(businessCheckOrg));
        }

        contractVO.setExportConfFile(contract.getExportConfFile());

        Date tenderOutTime = contract.getTenderOutTime();
        if (tenderOutTime != null) {
            contractVO.setTenderOutTime(tenderOutTime);
            contractVO.setTenderOutTimeAsString(DateUtil.formatDate(tenderOutTime));
        }

        Date stopBidTime = contract.getStopBidTime();
        if (stopBidTime != null) {
            contractVO.setStopBidTime(stopBidTime);
            contractVO.setStopBidTimeAsString(DateUtil.formatDate(stopBidTime));
        }

        contractVO.setLoadAndDischargePermittedTimespan(contract.getLoadAndDischargePermittedTimespan());

        Integer measureMode = contract.getMeasureMode();
        if (measureMode != null) {
            contractVO.setMeasureMode(measureMode);
            measureModeMap = DictUtils.getMeasureModeMap();
            contractVO.setMeasureModeContent(measureModeMap.get(measureMode));
        }

        ContractBuyer contractBuyer = contract.getContractBuyer();
        if (contractBuyer != null) {
            ContractBuyerVO contractBuyerVO = new ContractBuyerVO();
            ContractBuyerVO.convertToContractRelevanterVO(contractBuyer, contractBuyerVO);
            contractVO.setContractBuyer(contractBuyerVO);
        }

        ContractTrader contractTrader = contract.getContractTrader();
        if (contractTrader != null) {
            ContractTraderVO contractTraderVO = new ContractTraderVO();
            ContractTraderVO.convertToContractRelevanterVO(contractTrader, contractTraderVO);
            contractVO.setContractTrader(contractTraderVO);
        }

        ContractSupplier contractSupplier = contract.getContractSupplier();
        if (contractSupplier != null) {
            ContractSupplierVO contractSupplierVO = new ContractSupplierVO();
            ContractSupplierVO.convertToContractRelevanterVO(contractSupplier, contractSupplierVO);
            contractVO.setContractSupplier(contractSupplierVO);
        }

        Integer inspectionFeeSharingType = contract.getInspectionFeeSharingType();
        if (inspectionFeeSharingType != null) {
        	contractVO.setInspectionFeeSharingType(inspectionFeeSharingType);

            InspectionFeeSharingType type1 = InspectionFeeSharingType.getInspectionFeeSharingTypeByCode(inspectionFeeSharingType);
            if (type1 != null) {
            	contractVO.setInspectionFeeSharingTypeContent(type1.getName());
            }
        }

        contractVO.setLaw(contract.getLaw());
        contractVO.setGtc(contract.getGtc());
        contractVO.setFilePath(contract.getFilePath());
        contractVO.setOriginalName(contract.getOriginalName());
        contractVO.setIsSubmit(contract.getIsSubmit());
        contractVO.setContractKind(contract.getContractKind());
        
    }

    public String getInspectionFeeSharingTypeContent() {
		return inspectionFeeSharingTypeContent;
	}

	public void setInspectionFeeSharingTypeContent(String inspectionFeeSharingTypeContent) {
		this.inspectionFeeSharingTypeContent = inspectionFeeSharingTypeContent;
	}

	/**
     * 转换为Contract
     * @param contractVO
     * @return
     */
    public static void convertToContract(
            ContractVO contractVO,
            Contract contract) {
        if (contractVO == null) {
            return;
        }

        contract.setId(contractVO.getId());
        contract.setToken(contractVO.getToken());

        Boolean cancelFlag = contractVO.getCancelFlag();
        if (cancelFlag != null) {
            contract.setCancelFlag(contractVO.getCancelFlag());
        }

        contract.setBuyer(contractVO.getBuyer());
        contract.setSeller(contractVO.getSeller());
        contract.setUpdater(contractVO.getUpdater());
        contract.setUpdateTime(contractVO.getUpdateTime());
        contract.setVersion(contractVO.getVersion());
        contract.setPayType(contractVO.getPayType());
        contract.setNum(contractVO.getNum());
        contract.setUnitPrice(contractVO.getUnitPrice());
        contract.setInitiator(contractVO.getInitiator());
        contract.setContractType(contractVO.getContractType());
        contract.setNumFloat(contractVO.getNumFloat());
        contract.setReferenceId(contractVO.getReferenceId());
        contract.setValuationFormulaJson(contractVO.getValuationFormulaJson());
        contract.setValuationFormula(contractVO.getValuationFormula());
        contract.setTradeItem(contractVO.getTradeItem());
        contract.setAgio(contractVO.getAgio());
        contract.setValuationBase(contractVO.getValuationBase());
        contract.setValuationProidType(contractVO.getValuationProidType());
        contract.setValuationProidStart(contractVO.getValuationProidStart());
        contract.setValuationProidEnd(contractVO.getValuationProidEnd());
        contract.setPayItem(contractVO.getPayItem());
        contract.setPayTime(contractVO.getPayTime());
        contract.setExecutor(contractVO.getExecutor());
        contract.setOrderStatus(contractVO.getOrderStatus());
        contract.setPurchaseMode(contractVO.getPurchaseMode());
        contract.setDoubleSign(contractVO.getDoubleSign());

        Boolean buySign = contractVO.getBuySign();
        if (buySign != null) {
            contract.setBuySign(buySign.booleanValue());
        }

        Boolean sellerSign = contractVO.getSellerSign();
        if (sellerSign != null) {
            contract.setSellerSign(sellerSign.booleanValue());
        }

        ContractShipVO contractShipVO = contractVO.getContractShip();
        if (contractShipVO != null) {
            ContractShip contractShip = new ContractShip();
            ContractShipVO.convertToContractShip(contractShipVO, contractShip);
            contract.setContractShip(contractShip);
        }

        contract.setPrice(contractVO.getPrice());
        contract.setType(contractVO.getType());
        contract.setDealType(contractVO.getDealType());
        contract.setBizType(contractVO.getBizType());
        contract.setBiddingId(contractVO.getBiddingId());
        contract.setPurchaseType(contractVO.getPurchaseType());
        contract.setUnit(contractVO.getUnit());
        contract.setRate(contractVO.getRate());
        contract.setMeterNum(contractVO.getMeterNum());
        contract.setMeterUnit(contractVO.getMeterUnit());
        contract.setUuid(contractVO.getUuid());
        contract.setRemark(contractVO.getRemark());
        contract.setCreater(contractVO.getCreater());
        contract.setCreateTime(contractVO.getCreateTime());
        contract.setOrderNo(contractVO.getOrderNo());
        contract.setOtherItem(contractVO.getOtherItem());
        contract.setAuthItem(contractVO.getAuthItem());
        contract.setBusinessCheckOrg(contractVO.getBusinessCheckOrg());
        contract.setExportConfFile(contractVO.getExportConfFile());
        contract.setTenderOutTime(contractVO.getTenderOutTime());
        contract.setStopBidTime(contractVO.getStopBidTime());
        contract.setLoadAndDischargePermittedTimespan(contractVO.getLoadAndDischargePermittedTimespan());
        contract.setMeasureMode(contractVO.getMeasureMode());

        ContractBuyerVO contractBuyerVO = contractVO.getContractBuyer();
        if (contractBuyerVO != null) {
            ContractBuyer contractBuyer = new ContractBuyer();
            ContractBuyerVO.convertToContractRelevanter(contractBuyerVO, contractBuyer);
            contract.setContractBuyer(contractBuyer);
        }

        ContractTraderVO contractTraderVO = contractVO.getContractTrader();
        if (contractTraderVO != null) {
            ContractTrader contractTrader = new ContractTrader();
            ContractTraderVO.convertToContractRelevanter(contractTraderVO, contractTrader);
            contract.setContractTrader(contractTrader);
        }

        ContractSupplierVO contractSupplierVO = contractVO.getContractSupplier();
        if (contractSupplierVO != null) {
            ContractSupplier contractSupplier = new ContractSupplier();
            ContractSupplierVO.convertToContractRelevanter(contractSupplierVO, contractSupplier);
            contract.setContractSupplier(contractSupplier);
        }

        contract.setContractKind(contractVO.getContractKind());
        contract.setInspectionFeeSharingType(contractVO.getInspectionFeeSharingType());
        contract.setLaw(contractVO.getLaw());
        contract.setGtc(contractVO.getGtc());
    }

    /** getters and setters */
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

    public Boolean getCancelFlag() {
        return cancelFlag;
    }

    public void setCancelFlag(Boolean cancelFlag) {
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getPayType() {
        return payType;
    }

    public void setPayType(Long payType) {
        this.payType = payType;
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

    public Long getInitiator() {
        return initiator;
    }

    public void setInitiator(Long initiator) {
        this.initiator = initiator;
    }

    public Long getTrader() {
        return trader;
    }

    public void setTrader(Long trader) {
        this.trader = trader;
    }

    public String getTraderAgent() {
        return traderAgent;
    }

    public void setTraderAgent(String traderAgent) {
        this.traderAgent = traderAgent;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getNumFloat() {
        return numFloat;
    }

    public void setNumFloat(String numFloat) {
        this.numFloat = numFloat;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public String getValuationFormulaJson() {
        return valuationFormulaJson;
    }

    public void setValuationFormulaJson(String valuationFormulaJson) {
        this.valuationFormulaJson = valuationFormulaJson;
    }

    public String getValuationFormula() {
        return valuationFormula;
    }

    public void setValuationFormula(String valuationFormula) {
        this.valuationFormula = valuationFormula;
    }

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

    public String getValuationBase() {
        return valuationBase;
    }

    public void setValuationBase(String valuationBase) {
        this.valuationBase = valuationBase;
    }

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

    public String getValuationPeriodStartAsString() {
        return valuationPeriodStartAsString;
    }

    public void setValuationPeriodStartAsString(String valuationPeriodStartAsString) {
        this.valuationPeriodStartAsString = valuationPeriodStartAsString;
    }

    public Date getValuationProidEnd() {
        return valuationProidEnd;
    }

    public void setValuationProidEnd(Date valuationProidEnd) {
        this.valuationProidEnd = valuationProidEnd;
    }

    public String getValuationPeriodEndAsString() {
        return valuationPeriodEndAsString;
    }

    public void setValuationPeriodEndAsString(String valuationPeriodEndAsString) {
        this.valuationPeriodEndAsString = valuationPeriodEndAsString;
    }

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

    public String getPayTimeAsString() {
        return payTimeAsString;
    }

    public void setPayTimeAsString(String payTimeAsString) {
        this.payTimeAsString = payTimeAsString;
    }

    public Long getExecutor() {
        return executor;
    }

    public void setExecutor(Long executor) {
        this.executor = executor;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPurchaseMode() {
        return purchaseMode;
    }

    public void setPurchaseMode(Integer purchaseMode) {
        this.purchaseMode = purchaseMode;
    }

    public String getDoubleSign() {
        return doubleSign;
    }

    public void setDoubleSign(String doubleSign) {
        this.doubleSign = doubleSign;
    }

    public Boolean getBuySign() {
        return buySign;
    }

    public void setBuySign(Boolean buySign) {
        this.buySign = buySign;
    }

    public Boolean getSellerSign() {
        return sellerSign;
    }

    public void setSellerSign(Boolean sellerSign) {
        this.sellerSign = sellerSign;
    }

    public ContractShipVO getContractShip() {
        return contractShip;
    }

    public void setContractShip(ContractShipVO contractShipVO) {
        this.contractShip = contractShipVO;
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

    public ContractBuyerVO getContractBuyer() {
        return contractBuyer;
    }

    public void setContractBuyer(ContractBuyerVO contractBuyer) {
        this.contractBuyer = contractBuyer;
    }

    public ContractSupplierVO getContractSupplier() {
        return contractSupplier;
    }

    public void setContractSupplier(ContractSupplierVO contractSupplier) {
        this.contractSupplier = contractSupplier;
    }

    public ContractTraderVO getContractTrader() {
        return contractTrader;
    }

    public void setContractTrader(ContractTraderVO contractTrader) {
        this.contractTrader = contractTrader;
    }

    public String getContractKind() {
        return contractKind;
    }

    public void setContractKind(String contractKind) {
        this.contractKind = contractKind;
    }

    public String getUpdateTimeAsString() {
        return updateTimeAsString;
    }

    public void setUpdateTimeAsString(String updateTimeAsString) {
        this.updateTimeAsString = updateTimeAsString;
    }

    public String getTradeItemContent() {
        return tradeItemContent;
    }

    public void setTradeItemContent(String tradeItemContent) {
        this.tradeItemContent = tradeItemContent;
    }

    public String getValuationBaseContent() {
        return valuationBaseContent;
    }

    public void setValuationBaseContent(String valuationBaseContent) {
        this.valuationBaseContent = valuationBaseContent;
    }

    public String getValuationPeriodTypeContent() {
        return valuationPeriodTypeContent;
    }

    public void setValuationPeriodTypeContent(String valuationPeriodTypeContent) {
        this.valuationPeriodTypeContent = valuationPeriodTypeContent;
    }

    public String getPayItemContent() {
        return payItemContent;
    }

    public void setPayItemContent(String payItemContent) {
        this.payItemContent = payItemContent;
    }

    public String getPurchaseModeContent() {
        return purchaseModeContent;
    }

    public void setPurchaseModeContent(String purchaseModeContent) {
        this.purchaseModeContent = purchaseModeContent;
    }

    public String getDealTypeContent() {
        return dealTypeContent;
    }

    public void setDealTypeContent(String dealTypeContent) {
        this.dealTypeContent = dealTypeContent;
    }

    public String getPriceAsString() {
        return priceAsString;
    }

    public void setPriceAsString(String priceAsString) {
        this.priceAsString = priceAsString;
    }

    public String getTypeContent() {
        return typeContent;
    }

    public void setTypeContent(String typeContent) {
        this.typeContent = typeContent;
    }

    public String getPurchaseTypeContent() {
        return purchaseTypeContent;
    }

    public void setPurchaseTypeContent(String purchaseTypeContent) {
        this.purchaseTypeContent = purchaseTypeContent;
    }

    public String getCreateTimeAsString() {
        return createTimeAsString;
    }

    public void setCreateTimeAsString(String createTimeAsString) {
        this.createTimeAsString = createTimeAsString;
    }

    public String getBusinessCheckOrgContent() {
        return businessCheckOrgContent;
    }

    public void setBusinessCheckOrgContent(String businessCheckOrgContent) {
        this.businessCheckOrgContent = businessCheckOrgContent;
    }

    public String getTenderOutTimeAsString() {
        return tenderOutTimeAsString;
    }

    public void setTenderOutTimeAsString(String tenderOutTimeAsString) {
        this.tenderOutTimeAsString = tenderOutTimeAsString;
    }

    public String getStopBidTimeAsString() {
        return stopBidTimeAsString;
    }

    public void setStopBidTimeAsString(String stopBidTimeAsString) {
        this.stopBidTimeAsString = stopBidTimeAsString;
    }

    public String getMeasureModeContent() {
        return measureModeContent;
    }

    public void setMeasureModeContent(String measureModeContent) {
        this.measureModeContent = measureModeContent;
    }

    public Integer getInspectionFeeSharingType() {
        return inspectionFeeSharingType;
    }

    public void setInspectionFeeSharingType(Integer inspectionFeeSharingType) {
        this.inspectionFeeSharingType = inspectionFeeSharingType;
    }
}

