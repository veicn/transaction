package com.sinochem.crude.trade.order.enums;

public enum CrudeOilDischargePortEnum {
	CrudeOilDischargePort1(1, "北仑港区算山1号码",""),
	CrudeOilDischargePort2(2, "北仑港区算山2号码头",""),
	CrudeOilDischargePort3(3, "大榭港区实华1号码头",""),
	CrudeOilDischargePort4(4, "大榭港区实华3号码头",""),
	CrudeOilDischargePort5(5, "大榭中油燃料油码头",""),
	CrudeOilDischargePort6(6, "东马港区华德原油一期码头",""),
	CrudeOilDischargePort7(7, "东马港区华德原油二期码头",""),
	CrudeOilDischargePort8(8, "中海油马鞭洲原油码头",""),
	CrudeOilDischargePort9(9, "中海壳牌马鞭洲原油码头",""),
	CrudeOilDischargePort10(10, "黄岛港区原油二期码头",""),
	CrudeOilDischargePort11(11, "黄岛港区原油三期码头",""),
	CrudeOilDischargePort12(12, "黄岛港区益佳油码头",""),
	CrudeOilDischargePort13(13, "青岛港董家口港区原油码头",""),
	CrudeOilDischargePort14(14, "新港1号码头",""),
	CrudeOilDischargePort15(15, "鲇鱼湾港区0号码头",""),
	CrudeOilDischargePort16(16, "鲇鱼湾港区22号码头",""),
	CrudeOilDischargePort17(17, "大连港长兴岛港区原油码头",""),
	CrudeOilDischargePort18(18, "定海港区中化岙山1号码头",""),
	CrudeOilDischargePort19(19, "定海港区中化岙山5号码头",""),
	CrudeOilDischargePort20(20, "岑港港区册子原油一期码头",""),
	CrudeOilDischargePort21(21, "舟山港黄泽山油品码头",""),
	CrudeOilDischargePort22(22, "舟山港外钓岛光汇油品码头",""),
	CrudeOilDischargePort23(23, "南疆港区南疆码头",""),
	CrudeOilDischargePort24(24, "南疆港区原油码头30号码头",""),
	CrudeOilDischargePort25(25, "肖厝港区福建联合石化原油码头",""),
	CrudeOilDischargePort26(26, "斗尾港区青兰山原油码头",""),
	CrudeOilDischargePort27(27, "霞山港区200号原油码头",""),
	CrudeOilDischargePort28(28, "霞山港区209号原油码头",""),
	CrudeOilDischargePort29(29, "岚山港区原油一期码头",""),
	CrudeOilDischargePort30(30, "岚山港区10万吨级原油码头",""),
	CrudeOilDischargePort31(31, "日照港日仪管道配套原油码头",""),
	CrudeOilDischargePort32(32, "仙人岛港区原油码头",""),
	CrudeOilDischargePort33(33, "锦州港区301号码头",""),
	CrudeOilDischargePort34(34, "曹妃甸港区原油码头",""),
	CrudeOilDischargePort35(35, "茂名港原油码头",""),
	CrudeOilDischargePort36(36, "大揽坪港区油码头",""),
	CrudeOilDischargePort37(37, "神头港区原油码头",""),
	CrudeOilDischargePort38(38, "揭阳港揭阳石化配套原油码",""),
	CrudeOilDischargePort39(39, "惠州港东马港区华瀛油品码头",""),
	CrudeOilDischargePort40(40, "湛江港东海岛港区中科炼化原油码头",""),
	CrudeOilDischargePort41(41, "洋浦港神头港区国投孚宝油品码头","");

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
	
	private CrudeOilDischargePortEnum(Integer code, String zhName, String enName) {
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
