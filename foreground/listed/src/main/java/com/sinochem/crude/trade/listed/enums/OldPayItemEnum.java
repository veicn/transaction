package com.sinochem.crude.trade.listed.enums;

public enum OldPayItemEnum {

	OldPayItemEnum1(1, "After B/L 30 Days, B/L=0",""),
	OldPayItemEnum2(2, "After B/L 60 Days, B/L=0",""),
	OldPayItemEnum3(3, "After NOR 15 Days, NOR=0",""),
	OldPayItemEnum4(4, "After NOR 30 Days, NOR=0",""),
	OldPayItemEnum5(5, "From B/L 30 Days, B/L=1",""),
	OldPayItemEnum6(6, "From B/L 60 Days, B/L=1",""),
	OldPayItemEnum7(7, "From NOR 15 Days, NOR=1",""),
	OldPayItemEnum8(8, "From NOR 30 Days, NOR=1",""),
	OldPayItemEnum9(9, "After B/L 90 Days, B/L=0",""),
	OldPayItemEnum10(10, "From B/L 90 Days, B/L=1",""),
	OldPayItemEnum11(11, "After NOR 60 Days, NOR=0",""),
	OldPayItemEnum12(12, "After NOR 90 Days, NOR=0",""),
	OldPayItemEnum13(13, "From NOR 60 Days, NOR=1",""),
	OldPayItemEnum14(14, "From NOR 90 Days, NOR=1","");

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
	
	private OldPayItemEnum(Integer code, String zhName, String enName) {
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
