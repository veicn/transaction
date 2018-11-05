package com.sinochem.crude.trade.info.query;

import java.math.BigDecimal;

import com.sinochem.crude.trade.common.SimplePageInfo;

/**
 * 
 * @ClassName: QualityVo
 * @Description: 前台资讯查询对象
 *
 */
public class QualityVo extends SimplePageInfo{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 原油名称
	 */
	private String oilName;
	
	/**
	 * 原油产地
	 */
	private String oilPlace;
	
	/**
	 * 原油区域
	 */
	private String oilArea;
	
	/**
	 * API
	 */
	private String oilApi;
	
	/**
	 * 酸值
	 */
	private String oiloilSulphur;
	
	/**
	 * 含硫量
	 */
	private String oilAcidity;
	
	/**
	 * 日期-始
	 */
	private String dateStart;
	
	/**
	 * 日期-止
	 */
	private String dateEnd;

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

	public String getOilApi() {
		return oilApi;
	}

	public void setOilApi(String oilApi) {
		this.oilApi = oilApi;
	}

	public String getOiloilSulphur() {
		return oiloilSulphur;
	}

	public void setOiloilSulphur(String oiloilSulphur) {
		this.oiloilSulphur = oiloilSulphur;
	}

	public String getOilAcidity() {
		return oilAcidity;
	}

	public void setOilAcidity(String oilAcidity) {
		this.oilAcidity = oilAcidity;
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
	
	private BigDecimal oilApiMin;
	private BigDecimal oilApiMax;
    private BigDecimal oilSulphurMin;
    private BigDecimal oilSulphurMax;
    private BigDecimal oilAcidityMin;
    private BigDecimal oilAcidityMax;

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

	
	
}
