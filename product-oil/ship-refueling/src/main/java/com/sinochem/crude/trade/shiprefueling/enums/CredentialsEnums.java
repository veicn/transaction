package com.sinochem.crude.trade.shiprefueling.enums;

/**
 * @author WGP
 * @descriptioncrude-trade
 * @date 2018/6/7
 **/
public enum CredentialsEnums {

    /**
     * 企业资质
     * 1-- 炼厂 2-- 贸易商 3-- 商检 4-- 船东 5-- 船经纪
     * 6-- 船代 7-- 转租船东 8-- 银行 9-- 期货经纪商 10-- 原油供应商
     *
     * */
    CREDENTIALS_ONE("1","炼厂", ""),
    CREDENTIALS_TWO("2","贸易商", ""),
    CREDENTIALS_THREE("3","商检", ""),
    CREDENTIALS_FOUR("4","船东", ""),
    CREDENTIALS_FIVE("5","船经纪", ""),
    CREDENTIALS_SIX("6","船代", ""),
    CREDENTIALS_SEVEN("7","转租船东", ""),
    CREDENTIALS_EIGHT("8","银行", ""),
    CREDENTIALS_NINE("9","期货经纪商", ""),
    CREDENTIALS_TEN("10","原油供应商", ""),
    CREDENTIALS_ELEVEN("11","平台邀请观察员", ""),
    CREDENTIALS_TWELVE("12","租家", ""),
    CREDENTIALS_THIRTEEN("13","平台租船代理", ""),
    CREDENTIALS_FOURTEEN("60","船燃炼厂", ""),
    CREDENTIALS_FIFTEEN("61","船燃调油商", ""),
    CREDENTIALS_SIXTEEN("62","船燃贸易商", ""),
    CREDENTIALS_SEVENTEEN("63","中间商", ""),
    CREDENTIALS_EIGHTEEN("64","供油商", ""),
    CREDENTIALS_NINETEEN("65","船公司", ""),
    CREDENTIALS_TWENTY("66","物流公司", ""),
    CREDENTIALS_TWENTY_ONE("67","油品仓储", ""),
    CREDENTIALS_TWENTY_TWO("68","港口码头", ""),
    CREDENTIALS_TWENTY_THREE("69","交易中心", ""),
    CREDENTIALS_TWENTY_FOUR("70","检验检测", ""),
    CREDENTIALS_TWENTY_FIVE("71","设备供应商", ""),
    CREDENTIALS_TWENTY_SIX("72","船用油添加剂供应商", ""),
    CREDENTIALS_TWENTY_SEVEN("73","政府机关", ""),
    CREDENTIALS_TWENTY_EIGHT("74","金融机构", ""),
    CREDENTIALS_NINETY_EIGHT("98","企业资质", ""),
    CREDENTIALS_NINETY_NINE("99","平台OM角色", "");



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

    private CredentialsEnums(String code, String zhName, String enName) {
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
