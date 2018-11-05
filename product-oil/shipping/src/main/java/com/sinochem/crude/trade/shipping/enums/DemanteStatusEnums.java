package com.sinochem.crude.trade.shipping.enums;

/**
 * 租船需求状态
 * @author zhaoyulong
 *
 */
public enum DemanteStatusEnums {
	
	
	CHARTER_GENERATED("10","待处理", "Pengding"),
	CHARTER_CONFIRMED("20","租船协议待确认", "To Be Confirmed"),
	CHARTER_AGREEMENTCONFIRME("30","租船协议已确认", "Confirmed"),
	CHARTER_FOR("40","进行中", "Underway"),
	CHARTER_COMPLETE("50","已完成", "Completed");
	
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
	
	private DemanteStatusEnums(String code, String zhName, String enName) {
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
	
	
}
