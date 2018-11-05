package com.sinochem.crude.trade.shiprefueling.enums;

/**
 * 油品类别
 * @author zhaoyulong
 *
 */
public enum OilTypeEnums {


	OILTYPE_ONE("1001","燃料油", ""),
	OILTYPE_TWO("2001","柴油", ""),
    OILTYPE_THREE("3001","机油", ""),
    OILTYPE_FOUR("9999","其他", "");

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

	private OilTypeEnums(String code, String zhName, String enName) {
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
    public static OilTypeEnums getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (OilTypeEnums oilTypeEnums : OilTypeEnums.values()) {
            if (code.equals(oilTypeEnums.getCode())) {
                return oilTypeEnums;
            }
        }

        return null;
    }
	
}
