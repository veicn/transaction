package com.sinochem.crude.trade.goods.enums;

public enum ProductOilIndicator {

	FLASHPOINT(1, "闪点", false),
	WATERandSEDIMENT(2, "水和沉淀物", false),
	WATERCONTENT(3, "水含量", false),
	VISCOSITY(4, "@40℃,mm2/s粘度", false),
	CORROSIONandCOPPER(5, "3h@50℃铜腐", false),
	SEDIMENTbyEXTRACTION(6, "%mass残渣", false),
	CLOUDPOINT(7, "℃冷滤点", false),
	POURPOINT(8, "℃倾点", false),
	ASH(9, "%mass灰分", false),
	SodiumandPotassium (10, "(Na+K)钠+钾", false);
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

	ProductOilIndicator(int index, String name, boolean gains) {
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
