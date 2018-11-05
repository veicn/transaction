package com.sinochem.crude.trade.shiprefueling.enums;

/**
 * Oilvarieties 规格
 * @author zhaoyulong
 *
 */
public enum FuelOilEnums {


    FUELOIL_ONE("1001","180#", "180#"),
    FUELOIL_TWO("1002","120#", "120#"),
    FUELOIL_THR("9999","其他", "other");

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

	private FuelOilEnums(String code, String zhName, String enName) {
		this.code = code;
        this.zhName = zhName;
        this.enName = enName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
    public String getZhName() {
        return zhName;
    }

    public String getEnName() {
        return enName;
    }

    /**
     * 根据代码获取
     */
    public static FuelOilEnums getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (FuelOilEnums fuelOilEnums : FuelOilEnums.values()) {
            if (code.equals(fuelOilEnums.getCode())) {
                return fuelOilEnums;
            }
        }

        return null;
    }
}
