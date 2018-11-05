package com.sinochem.crude.trade.blockchain.enums;

import java.util.HashMap;
import java.util.Map;

public enum HttpKeyEnums{
    SHIPAGENT("shipagent","670ae10a2c87dcd1be94c68602463d0d", ""),
    BROKER("broker","e3c9c373c82d6d5bd4a4aa6a8269bbe6", ""),
    INSPECTOR("inspector","8e96c1fb87ac069c2a39f1ed61b10428", ""),
    CUSTOMS("customs","ad846cd10b74d0d11443fcf0be2de2eb", "");

    private String code;
    private String name;
    private String desc;

	private HttpKeyEnums(String code, String Name, String desc) {
		this.code = code;
        this.name = Name;
        this.desc = desc;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public static HttpKeyEnums getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (HttpKeyEnums httpKeyEnums : HttpKeyEnums.values()) {
            if (code.equals(httpKeyEnums.getCode())) {
                return httpKeyEnums;
            }
        }

        return null;
    }

}
