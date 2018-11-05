package com.sinochem.crude.trade.order.enums;

public enum MeasureModeEnum {
	MeasureModeEnum1(1, "提单量","B/L Quantity"),
	MeasureModeEnum2(2, "船检量","Ship Arrial Figure"),
	MeasureModeEnum3(3, "VEF船检量","Ship Arrial Figure (VEF Applied)"),
	MeasureModeEnum4(4, "船检量-ROB","Discharged Qty (ROB exclusive)"),
	MeasureModeEnum5(5, "VEF船检量-ROB","Discharged Qty (VEF applied with ROB exclusive)"),
	MeasureModeEnum6(6, "罐收量","Shoretank Received Qty"),
	MeasureModeEnum7(7, "罐出量","Shoretank Export Qty");

	/**
     * 编号
     */
    private Integer code;

    /**
     * 中文
     */
    private String zhName;

    /**
     * 英文
     */
    private String enName;	
	
	private MeasureModeEnum(Integer code, String zhName, String enName) {
		this.code = code;
        this.zhName = zhName;
        this.enName = enName;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
    public String getZhName() {
        return zhName;
    }

    public String getEnName() {
        return enName;
    }
}
