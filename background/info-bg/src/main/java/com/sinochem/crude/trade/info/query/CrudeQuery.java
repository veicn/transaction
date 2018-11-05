package com.sinochem.crude.trade.info.query;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.QueryBase;
import com.sinochem.crude.trade.common.SimplePageInfo;

public class CrudeQuery extends QueryBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String uuid;
	private String oilName;
	private String oilPlace;
	private String oilArea;
	private String oilApi;
	private String oilSulphur;
	private String oilAcidity;
	private String dateStart;
	private String dateEnd;
	private String api;
	private String sulphur;
	private String acidity;
	private String freezingPoint;
	private String flashPoint;
	private String viscosity;
	private String carbonResidue;
	private String nickel;
	private String vanadium;
	private String yield;
	private String originName;
	private String originArea;
	private String catagoryName;
	private String simpleDate;
	private String crudeNameE;
	private String crudeNameC;
	private String crudeId;
	private BigDecimal oilAcidityMin;//酸值最小值
	private BigDecimal oilAcidityMax;//酸值最大值
	private BigDecimal oilSulphurMin;//含硫量最小值
	private BigDecimal oilSulphurMax;//含硫量最大值
	private BigDecimal oilApiMin;//api最小值
	private BigDecimal oilApiMax;//api最大值
	
	@Override
	public Map<String, String> getParameters() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", getId());
		map.put("uuid", getUuid());
		map.put("oilName", getOilName());
		map.put("oilPlace", getOilPlace());
		map.put("oilArea", getOilArea());
		map.put("dateStart", getDateStart());
		map.put("dateEnd", getDateEnd());
		map.put("api", getApi());
		map.put("sulphur", getSulphur());
		map.put("acidity", getAcidity());
		map.put("freezingPoint", getFreezingPoint());
		map.put("flashPoint", getFlashPoint());
		map.put("viscosity", getViscosity());
		map.put("carbonResidue", getCarbonResidue());
		map.put("nickel", getNickel());
		map.put("vanadium", getVanadium());
		map.put("yield", getYield());
		map.put("originName", getOriginName());
		map.put("originArea", getOriginArea());
		map.put("catagoryName", getCatagoryName());
		map.put("simpleDate", getSimpleDate());
		map.put("crudeNameE", getCrudeNameE());
		map.put("crudeNameC", getCrudeNameC());
		map.put("crudeId", getCrudeId());
		
		return map;
	}
	
	public String getCrudeId() {
		return crudeId;
	}

	public void setCrudeId(String crudeId) {
		this.crudeId = crudeId;
	}

	public String getSimpleDate() {
		return simpleDate;
	}

	public void setSimpleDate(String simpleDate) {
		this.simpleDate = simpleDate;
	}

	public String getOilApi() {
		return oilApi;
	}

	public void setOilApi(String oilApi) {
		this.oilApi = oilApi;
	}

	public String getOilSulphur() {
		return oilSulphur;
	}

	public void setOilSulphur(String oilSulphur) {
		this.oilSulphur = oilSulphur;
	}

	public String getOilAcidity() {
		return oilAcidity;
	}

	public void setOilAcidity(String oilAcidity) {
		this.oilAcidity = oilAcidity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOilName() {
		return oilName;
	}

	public void setOilName(String oilName) {
		this.oilName = oilName;
	}

	public String getOilPlace() {
		return oilPlace;
	}

	public void setOilPlace(String oilPlace) {
		this.oilPlace = oilPlace;
	}

	public String getOilArea() {
		return oilArea;
	}

	public void setOilArea(String oilArea) {
		this.oilArea = oilArea;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getSulphur() {
		return sulphur;
	}

	public void setSulphur(String sulphur) {
		this.sulphur = sulphur;
	}

	public String getAcidity() {
		return acidity;
	}

	public void setAcidity(String acidity) {
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

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getOriginArea() {
		return originArea;
	}

	public void setOriginArea(String originArea) {
		this.originArea = originArea;
	}

	public String getCatagoryName() {
		return catagoryName;
	}

	public void setCatagoryName(String catagoryName) {
		this.catagoryName = catagoryName;
	}

	public String getCrudeNameE() {
		return crudeNameE;
	}

	public void setCrudeNameE(String crudeNameE) {
		this.crudeNameE = crudeNameE;
	}

	public String getCrudeNameC() {
		return crudeNameC;
	}

	public void setCrudeNameC(String crudeNameC) {
		this.crudeNameC = crudeNameC;
	}

	public BigDecimal getOilAcidityMin() {
		return oilAcidityMin;
	}

	public void setOilAcidityMin(BigDecimal oilAcidityMin) {
		this.oilAcidityMin = oilAcidityMin;
	}

	public BigDecimal getOilAcidityMax() {
		return oilAcidityMax;
	}

	public void setOilAcidityMax(BigDecimal oilAcidityMax) {
		this.oilAcidityMax = oilAcidityMax;
	}

	public BigDecimal getOilSulphurMin() {
		return oilSulphurMin;
	}

	public void setOilSulphurMin(BigDecimal oilSulphurMin) {
		this.oilSulphurMin = oilSulphurMin;
	}

	public BigDecimal getOilSulphurMax() {
		return oilSulphurMax;
	}

	public void setOilSulphurMax(BigDecimal oilSulphurMax) {
		this.oilSulphurMax = oilSulphurMax;
	}

	public BigDecimal getOilApiMin() {
		return oilApiMin;
	}

	public void setOilApiMin(BigDecimal oilApiMin) {
		this.oilApiMin = oilApiMin;
	}

	public BigDecimal getOilApiMax() {
		return oilApiMax;
	}

	public void setOilApiMax(BigDecimal oilApiMax) {
		this.oilApiMax = oilApiMax;
	}
	
	
}
