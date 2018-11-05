package com.sinochem.crude.trade.goods.remote;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 原油产品信息VO
 * 
 * @author liuzuobin
 */
public class CrudeOilInfoVO implements Serializable {

	private static final long serialVersionUID = 6377126707422166990L;

	/**
	 * t_crude_oil.ID自增主键
	 */
	private Long id;

	/**
	 * t_crude_oil.UUID
	 */
	private String uuid;

	/**
	 * t_crude_oil.CRUDE_NAME_E原油英文名
	 */
	private String crudeNameE;

	/**
	 * t_crude_oil.CRUDE_NAME_E_UO原油英文名首字母大写
	 */
	private String crudeNameEUp;	
	
	/**
	 * t_crude_oil.CRUDE_NAME_C中文
	 */
	private String crudeNameC;

	/**
	 * t_crude_oil.ORIGIN_AREA_ID产地区域id
	 */
	private Long originAreaId;

	/**
	 * t_crude_oil.ORIGIN_ID产地id
	 */
	private Long originId;

	/**
	 * t_crude_oil.CATAGORY_ID种类id
	 */
	private Long catagoryId;

	/**
	 * t_crude_oil.TRANSACTION_MODE交易模式(1-现货/2-期货)
	 */
	private String transactionMode;

	/**
	 * t_crude_oil.PRICE_BASE_FLAG是否基准价 1/0，真/假
	 */
	private String priceBaseFlag;

	/**
	 * t_crude_oil.PRICE_MODE价格模式(osp/osp_dis/base_dis，1/2/3，只有现货原油品种有这个属性)
	 */
	private String priceMode;

	/**
	 * t_crude_oil.PRIORITY排序
	 */
	private Integer priority;

	/**
	 * t_crude_oil.TON_BUCKET桶吨比
	 */
	private BigDecimal tonBucket;

	/**
	 * t_crude_catagory.CATAGORY_NAME_E类型英文名
	 */
	private String catagoryNameE;

	/**
	 * t_crude_catagory.CATAGORY_NAME_C类型中文名
	 */
	private String catagoryNameC;

	/**
	 * t_crude_origin.ORIGIN_NAME_E产地英文名
	 */
	private String originNameE;

	/**
	 * t_crude_origin.ORIGIN_NAME_C产地中文名
	 */
	private String originNameC;

	/**
	 * t_crude_origin_area.AREA_NAME_E区域英文名
	 */
	private String areaNameE;

	/**
	 * t_crude_origin_area.AREA_NAME_C区域中文名
	 */
	private String areaNameC;

	/**
	 * t_crude_quality.SIMPLE_DATE品质等级日期
	 */
	private Date simpleDate;
	private String simpleDateAsString;

	/**
	 * t_crude_quality.QUALITY_VERSION品质版本号
	 */
	private String qualityVersion;

	/**
	 * t_crude_quality.APIAPI度
	 */
	private BigDecimal api;

	/**
	 * t_crude_quality.SULPHUR硫含量,单位%wt，质量百分比
	 */
	private BigDecimal sulphur;

	/**
	 * t_crude_quality.ACIDITY酸值,单位mgKOH/g
	 */
	private BigDecimal acidity;

	/**
	 * t_crude_quality.FREEZING_POINT凝点,单位℃
	 */
	private String freezingPoint;

	/**
	 * t_crude_quality.FLASH_POINT闪点,单位℃
	 */
	private String flashPoint;

	/**
	 * t_crude_quality.VISCOSITY黏度（50℃）
	 */
	private String viscosity;

	/**
	 * t_crude_quality.CARBON_RESIDUE残碳,单位%wt，质量百分比
	 */
	private String carbonResidue;

	/**
	 * t_crude_quality.NICKEL镍含量,ppm wt
	 */
	private String nickel;

	/**
	 * t_crude_quality.VANADIUM钒含量,ppm wt
	 */
	private String vanadium;

	/**
	 * t_crude_quality.ALIVE_FLAG数据状态
	 */
	private String aliveFlag;

	/**
	 * t_crude_quality.YIELD>350℃质量收率
	 */
	private String yield;

	/**
	 * t_crude_quality.CREATE_DATE创建日期
	 */
	private Date createDate;
	private String createDateAsString;

	/**
	 * t_crude_quality.CREATE_PERSON创建人
	 */
	private String createPerson;

	/**
	 * t_crude_quality.MODIFY_DATE修改日期
	 */
	private Date modifyDate;
	private String modifyDateAsString;

	/**
	 * t_crude_quality.MODIFY_PERSON修改人
	 */
	private String modifyPerson;

	/**
	 * t_crude_quality.SOURCE数据来源(1:转自接口，2:本系统页面维护)
	 */
	private String source;

	/**
	 * t_crude_quality.INTERFACE_ID中间表对应的id
	 */
	private Long interfaceId;
	
	/**
	 * @author Yichen Zhao
	 * date: 20180104
	 * 备注
	 */
	private String desc;

	public Date getSimpleDate() {
		return simpleDate;
	}

	public void setSimpleDate(Date simpleDate) {
		this.simpleDate = simpleDate;
	}

	public String getQualityVersion() {
		return qualityVersion;
	}

	public void setQualityVersion(String qualityVersion) {
		this.qualityVersion = qualityVersion;
	}

	public BigDecimal getApi() {
		return api;
	}

	public void setApi(BigDecimal api) {
		this.api = api;
	}

	public BigDecimal getSulphur() {
		return sulphur;
	}

	public void setSulphur(BigDecimal sulphur) {
		this.sulphur = sulphur;
	}

	public BigDecimal getAcidity() {
		return acidity;
	}

	public void setAcidity(BigDecimal acidity) {
		this.acidity = acidity;
	}

	public String getFreezingPoint() {
		return freezingPoint;
	}

	public void setFreezingPoint(String freezingPoint) {
		this.freezingPoint = freezingPoint;
	}

	public String getFlashPoint() {
		return flashPoint;
	}

	public void setFlashPoint(String flashPoint) {
		this.flashPoint = flashPoint;
	}

	public String getViscosity() {
		return viscosity;
	}

	public void setViscosity(String viscosity) {
		this.viscosity = viscosity;
	}

	public String getCarbonResidue() {
		return carbonResidue;
	}

	public void setCarbonResidue(String carbonResidue) {
		this.carbonResidue = carbonResidue;
	}

	public String getNickel() {
		return nickel;
	}

	public void setNickel(String nickel) {
		this.nickel = nickel;
	}

	public String getVanadium() {
		return vanadium;
	}

	public void setVanadium(String vanadium) {
		this.vanadium = vanadium;
	}

	public String getYield() {
		return yield;
	}

	public void setYield(String yield) {
		this.yield = yield;
	}

	public String getModifyPerson() {
		return modifyPerson;
	}

	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCrudeNameE() {
		return crudeNameE;
	}

	public void setCrudeNameE(String crudeNameE) {
		this.crudeNameE = crudeNameE;
	}

	public String getCrudeNameEUp() {
		return crudeNameEUp;
	}

	public void setCrudeNameEUp(String crudeNameEUp) {
		this.crudeNameEUp = crudeNameEUp;
	}

	public String getCrudeNameC() {
		return crudeNameC;
	}

	public void setCrudeNameC(String crudeNameC) {
		this.crudeNameC = crudeNameC;
	}

	public Long getOriginAreaId() {
		return originAreaId;
	}

	public void setOriginAreaId(Long originAreaId) {
		this.originAreaId = originAreaId;
	}

	public Long getOriginId() {
		return originId;
	}

	public void setOriginId(Long originId) {
		this.originId = originId;
	}

	public Long getCatagoryId() {
		return catagoryId;
	}

	public void setCatagoryId(Long catagoryId) {
		this.catagoryId = catagoryId;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public String getPriceBaseFlag() {
		return priceBaseFlag;
	}

	public void setPriceBaseFlag(String priceBaseFlag) {
		this.priceBaseFlag = priceBaseFlag;
	}

	public String getPriceMode() {
		return priceMode;
	}

	public void setPriceMode(String priceMode) {
		this.priceMode = priceMode;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public BigDecimal getTonBucket() {
		return tonBucket;
	}

	public void setTonBucket(BigDecimal tonBucket) {
		this.tonBucket = tonBucket;
	}

	public String getAliveFlag() {
		return aliveFlag;
	}

	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getCatagoryNameE() {
		return catagoryNameE;
	}

	public void setCatagoryNameE(String catagoryNameE) {
		this.catagoryNameE = catagoryNameE;
	}

	public String getCatagoryNameC() {
		return catagoryNameC;
	}

	public void setCatagoryNameC(String catagoryNameC) {
		this.catagoryNameC = catagoryNameC;
	}

	public String getOriginNameE() {
		return originNameE;
	}

	public void setOriginNameE(String originNameE) {
		this.originNameE = originNameE;
	}

	public String getOriginNameC() {
		return originNameC;
	}

	public void setOriginNameC(String originNameC) {
		this.originNameC = originNameC;
	}

	public String getAreaNameE() {
		return areaNameE;
	}

	public void setAreaNameE(String areaNameE) {
		this.areaNameE = areaNameE;
	}

	public String getAreaNameC() {
		return areaNameC;
	}

	public void setAreaNameC(String areaNameC) {
		this.areaNameC = areaNameC;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSimpleDateAsString() {
		return simpleDateAsString;
	}

	public void setSimpleDateAsString(String simpleDateAsString) {
		this.simpleDateAsString = simpleDateAsString;
	}

	public String getCreateDateAsString() {
		return createDateAsString;
	}

	public void setCreateDateAsString(String createDateAsString) {
		this.createDateAsString = createDateAsString;
	}

	public String getModifyDateAsString() {
		return modifyDateAsString;
	}

	public void setModifyDateAsString(String modifyDateAsString) {
		this.modifyDateAsString = modifyDateAsString;
	}
}
