package com.sinochem.crude.trade.listed.enums;

public enum ShipmentPortEnum {
	ShipmentPort1(1, "BOT",""),
	ShipmentPort2(2, "Mina Al Fahal",""),
	ShipmentPort3(3, "Ras Tanura",""),
	ShipmentPort4(4, "Mina Al Ahmadi",""),
	ShipmentPort5(5, "Kharg Island",""),
	ShipmentPort6(6, "Jebel Dhanna",""),
	ShipmentPort7(7, "Zirku Island",""),
	ShipmentPort8(8, "Halul Island",""),
	ShipmentPort9(9, "Kozmino",""),
	ShipmentPort10(10, "Prigorodnoye",""),
	ShipmentPort11(11, "STS YOSU",""),
	ShipmentPort12(12, "Liberdade FSO",""),
	ShipmentPort13(13, "Long Strait Point",""),
	ShipmentPort14(14, "Dampier Withnell Bay",""),
	ShipmentPort15(15, "Bach Ho Platform",""),
	ShipmentPort16(16, "Su TU Den Terminal",""),
	ShipmentPort17(17, "Armada TGT1 FPSO",""),
	ShipmentPort18(18, "Dumai",""),
	ShipmentPort19(19, "Kikeh FPSO",""),
	ShipmentPort20(20, "Terengganu",""),
	ShipmentPort21(21, "Seria",""),
	ShipmentPort22(22, "Malongo",""),
	ShipmentPort23(23, "Girassol",""),
	ShipmentPort24(24, "PSVM",""),
	ShipmentPort25(25, "Kizomba",""),
	ShipmentPort26(26, "Plutonio",""),
	ShipmentPort27(27, "Pazflor",""),
	ShipmentPort28(28, "Dalia",""),
	ShipmentPort29(29, "Qua Iboe",""),
	ShipmentPort30(30, "Bonny",""),
	ShipmentPort31(31, "BONGA",""),
	ShipmentPort32(32, "DJENO",""),
	ShipmentPort33(33, "Jubilee",""),
	ShipmentPort34(34, "Oguendjo",""),
	ShipmentPort35(35, "Kole",""),
	ShipmentPort36(36, "Zafiro",""),
	ShipmentPort37(37, "Ceiba",""),
	ShipmentPort38(38, "Covenas",""),
	ShipmentPort39(39, "Esmeraldas",""),
	ShipmentPort40(40, "Caleta Cordova",""),
	ShipmentPort41(41, "Peregrino",""),
	ShipmentPort42(42, "Cayo Arcos",""),
	ShipmentPort43(43, "Jose","");

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
	
	private ShipmentPortEnum(Integer code, String zhName, String enName) {
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
