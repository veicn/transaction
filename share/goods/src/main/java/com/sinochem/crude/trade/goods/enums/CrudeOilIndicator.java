package com.sinochem.crude.trade.goods.enums;

/**
 * 原油指标，这个项目可以写死
 * 
 * @author Leo
 *
 */
public enum CrudeOilIndicator {

	API(1, "API度", true),
	SULFIDE(2, "硫含量/m%", true),
	ACIDVALUE(3,"酸值/(mgKOH/g)",true),
	CARBONRESIDUE(4,"残炭/%",true),
	NICKEL(5,"镍/ppm",true),VANADIUM(6,"钒/ppm",true),
	YIELD(7,"质量收率/%",true),
	YIELD520(8,"质量收率/%",false),
	NICKEL520(9,"镍/ppm",false),
	VANADIUM520(10,"钒/ppm",false),
	CARBONRESIDUE520(11,"残炭/%",false),
	SULFIDE520(12, "硫含量/ m%", false);

	/**
	 * 索引
	 */
	private int index;

	/**
	 * 对应的
	 */
	private String name;

	/**
	 * 走势图
	 */
	private boolean gains;

	CrudeOilIndicator(int index, String name, boolean gains) {
		this.index = index;
		this.name = name;
		this.gains = gains;
	}

	public int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public boolean isGains() {
		return gains;
	}

}
