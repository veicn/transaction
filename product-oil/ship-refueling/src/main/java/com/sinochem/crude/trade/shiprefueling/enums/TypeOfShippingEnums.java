package com.sinochem.crude.trade.shiprefueling.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 船加油运输方式
 *
 * @author zhaoyulong
 *
 */
public enum TypeOfShippingEnums implements IEnums{


    TypeOfShipping_ONE("1","油轮运输", ""),
    TypeOfShipping_TWO("2","油罐车运输", ""),
    TypeOfShipping_THREE("3","驳船送供", ""),
    TypeOfShipping_FOR("4","面议", "");

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

	private TypeOfShippingEnums(String code, String zhName, String enName) {
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
    public static TypeOfShippingEnums getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (TypeOfShippingEnums typeOfShippingEnums : TypeOfShippingEnums.values()) {
            if (code.equals(typeOfShippingEnums.getCode())) {
                return typeOfShippingEnums;
            }
        }

        return null;
    }

    //ADD_ 转map_wdh_20180801_Start
    /**
     * 转map
     */
    public static Map<String,String> toMap() {
        Map<String ,String > map = new HashMap<String ,String >();

        for (TypeOfShippingEnums typeOfShippingEnums : TypeOfShippingEnums.values()) {
            map.put(typeOfShippingEnums.getCode(),typeOfShippingEnums.getZhName());
        }

        return map;
    }
    //ADD_ 转map_wdh_20180801_End
}
