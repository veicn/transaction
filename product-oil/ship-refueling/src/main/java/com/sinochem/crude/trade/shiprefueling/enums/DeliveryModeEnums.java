package com.sinochem.crude.trade.shiprefueling.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 提货方式
 * @author wdh
 *
 */
public enum DeliveryModeEnums {


	AGREEMENT_ONE("1","配送", ""),
	AGREEMENT_TWO("2","自提", "");

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

	private DeliveryModeEnums(String code, String zhName, String enName) {
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
    public static DeliveryModeEnums getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (DeliveryModeEnums deliveryModeEnums : DeliveryModeEnums.values()) {
            if (code.equals(deliveryModeEnums.getCode())) {
                return deliveryModeEnums;
            }
        }

        return null;
    }
    /**
     * 转map
     */
    public static Map<String,String> toMap() {
        Map<String ,String > map = new HashMap<String ,String >();

        for (DeliveryModeEnums deliveryModeEnums : DeliveryModeEnums.values()) {
            map.put(deliveryModeEnums.getCode(),deliveryModeEnums.getZhName());
        }

        return map;
    }
}
