package com.sinochem.crude.trade.order.domain;

/**
 * 原油采购订单
 * 
 * @author 胡凌
 *
 */
public class CrudeOilBuyContract extends Contract {

	public CrudeOilBuyContract() {
		super();
		super.setDealType("B");
		super.setBizType("CrudeOil");
	}

	/**
	 * 原油-油种
	 */
	private String crudeOilOptions;

	public CrudeOilBuyContract(CrudeOilLongTermContract record) {
		//setId(record.getId());
		//setCrudeOilOptions(record.getCrudeOilOptions());
		//setDealType(record.getDealType());
		//setBizType(record.getBizType());
		//setTraderAgent(record.getTraderAgent());

		//赋值，除了油品等
		super();
		setCrudeOilResource(record.getCrudeOilResource());

		setAgio(record.getAgio());
		setAuthItem(record.getAuthItem());
		setBiddingId(record.getBiddingId());
		setBusinessCheckOrg(record.getBusinessCheckOrg());
		setBuyer(record.getBuyer());
		setBuySign(record.isBuySign());
		setCancelFlag(record.isCancelFlag());
		setContractBuyer(record.getContractBuyer());
		setContractShip(record.getContractShip());
		setContractSupplier(record.getContractSupplier());
		setContractTrader(record.getContractTrader());
		setContractType(record.getContractType());
		setCreater(record.getCreater());
		setCreateTime(record.getCreateTime());
		setDoubleSign(record.getDoubleSign());
		/*setDoubleStatus(record.getOrderStatus());*/
		setExecutor(record.getExecutor());
		setExportConfFile(record.getExportConfFile());
		setInitiator(record.getInitiator());
		setMeterNum(record.getMeterNum());
		setMeterUnit(record.getMeterUnit());
		setNum(record.getNum());
		setNumFloat(record.getNumFloat());
		setOrderNo(record.getOrderNo());
		setOrderStatus(record.getOrderStatus());
		setOtherItem(record.getOtherItem());
		setPayItem(record.getPayItem());
		setPayItemJSON(record.getPayItemJSON());
		setPayTime(record.getPayTime());
		setPayType(record.getPayType());
		setPrice(record.getPrice());
		setPurchaseMode(record.getPurchaseMode());
		setPurchaseType(record.getPurchaseType());
		setRate(record.getRate());
		setReferenceId(record.getReferenceId());
		setRemark(record.getRemark());
		setSeller(record.getSeller());
		setSellerSign(record.isSellerSign());
		setStopBidTime(record.getStopBidTime());
		setTenderOutTime(record.getTenderOutTime());
		setToken(record.getToken());
		setTradeItem(record.getTradeItem());
		setUnit(record.getUnit());
		setUnitPrice(record.getUnitPrice());
		setUpdater(record.getUpdater());
		setUpdateTime(record.getUpdateTime());
		setUuid(record.getUuid());
		setValuationBase(record.getValuationBase());
		setValuationFormula(record.getValuationFormula());
		setValuationProidEnd(record.getValuationProidEnd());
		setValuationProidStart(record.getValuationProidStart());
		setValuationProidType(record.getValuationProidType());
		setVersion(record.getVersion());
		setValuationFormulaJson(record.getValuationFormulaJson());
	}

	private CrudeOilResource crudeOilResource;
	
	public CrudeOilResource getCrudeOilResource() {
		return crudeOilResource;
	}

	public void setCrudeOilResource(CrudeOilResource crudeOilResource) {
		this.crudeOilResource = crudeOilResource;
	}

	public String getCrudeOilOptions() {
		return crudeOilOptions;
	}

	public void setCrudeOilOptions(String crudeOilOptions) {
		this.crudeOilOptions = crudeOilOptions;
	}
}
