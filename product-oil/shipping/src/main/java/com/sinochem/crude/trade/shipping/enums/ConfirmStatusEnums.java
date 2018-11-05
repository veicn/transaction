package com.sinochem.crude.trade.shipping.enums;

/**
 * 租船确认单状态Enum
 * @author zhaoyulong
 *
 */
public enum ConfirmStatusEnums {
	
	CONFIRM_CONFIRMED("10","待确认", "To Be Confirmed"),
	CONFIRM_HAVE("20","待开始", "To Be Begins"),
	CONFIRM_VOYAGE("30","航次开始", "Voyage Begins"),
	CONFIRM_LOADINGPORT("40","已装港", "Loaded"),
	CONFIRM_TRANSIT("50","在途中", "In Transit"),
	CONFIRM_DISCHARGE("60","已卸港", "Discharged"),
	CONFIRM_END("70","航次结束", "Voyage Finish"),
	CONFIRM_CANCEL("80","已中止", "Suspended");
	
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
	
	
	private ConfirmStatusEnums(String code, String zhName, String enName) {
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
