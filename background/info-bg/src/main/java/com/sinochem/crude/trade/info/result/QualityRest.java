package com.sinochem.crude.trade.info.result;

import java.io.Serializable;
import java.math.BigDecimal;

public class QualityRest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String crudeNameC;
	
	private String crudeNameE;
	
	private String area;
	
	private String origin;
	
	private String catagory;
	
	/**品质等级日期*/
	private String simpleDate;
	
	/**品质版本号*/
	private String qualityVersion;
	
	/**API度*/
	private BigDecimal api;
	
	/**硫含量,单位%wt，质量百分比*/
	private BigDecimal sulphur;
	
	/**酸值,单位mgKOH/g*/
	private BigDecimal acidity;
	
	/**凝点,单位℃*/
	private String freezingPoint;
	
	/**闪点,单位℃*/
	private String flashPoint;
	
	/**黏度（50℃）*/
	private String viscosity;
	
	/**残碳,单位%wt，质量百分比*/
	private String carbonResidue;
	
	/**镍含量,ppm wt*/
	private String nickel;
	
	/**钒含量,ppm wt*/
	private String vanadium;
	
	/**>350℃质量收率*/
	private String yield;

	public String getCrudeNameC() {
		return crudeNameC;
	}

	public void setCrudeNameC(String crudeNameC) {
		this.crudeNameC = crudeNameC;
	}

	public String getCrudeNameE() {
		return crudeNameE;
	}

	public void setCrudeNameE(String crudeNameE) {
		this.crudeNameE = crudeNameE;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public String getSimpleDate() {
		return simpleDate;
	}

	public void setSimpleDate(String simpleDate) {
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

}
