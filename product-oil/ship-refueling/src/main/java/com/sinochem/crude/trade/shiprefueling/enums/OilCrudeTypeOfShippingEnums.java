package com.sinochem.crude.trade.shiprefueling.enums;

/**
 * 原料油运输方式
 * @author songhaiqiang
 *
 */
public enum  OilCrudeTypeOfShippingEnums implements IEnums{


	TypeOfShipping_ONE("1","船运", ""),
	TypeOfShipping_TWO("2","汽运", ""),
	TypeOfShipping_THREE("3","火车运输", ""),
	TypeOfShipping_FOUR("4","其他", "");

	OilCrudeTypeOfShippingEnums(String code , String zhName , String enName){
		this.code = code;
		this.zhName = zhName;
		this.enName = enName;
	}

	/**
	 * 代码
	 */
	private String code;

	/**
	 * 中文名称
	 */
	private String zhName;

	/**
	 * 英文名称
	 */
	private String enName;


    public String getCode() {
        return code;
    }

	public void setCode(String code) {
		this.code = code;
	}

	public String getZhName() {
		return zhName;
	}

	public void setZhName(String zhName) {
		this.zhName = zhName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

    /**
     * 根据代码获取
     */
    public static OilCrudeTypeOfShippingEnums getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (OilCrudeTypeOfShippingEnums typeOfShippingEnums : OilCrudeTypeOfShippingEnums.values()) {
            if (code.equals(typeOfShippingEnums.getCode())) {
                return typeOfShippingEnums;
            }
        }

        return null;
    }
}
