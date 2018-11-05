package com.sinochem.crude.trade.order.enums;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.order.constart.MsgConstart;

/**
 * 订单类型
 * Created by bbt on 2017/12/13.
 */
public enum EnumContractType {

    NormalContract("D", MsgConstart.ORDER0034),
    LongTermContract("L", MsgConstart.ORDER0035)
    ;

    private String code;
    private String desc;
    private String[][] descs;

    EnumContractType(String code, String[][] descs) {
        this.code = code;
        this.descs = descs;
    }

    public static EnumContractType getContractTypeByCode(String code) {
        for (EnumContractType contractType : EnumContractType.values()) {
            if (contractType.getCode().equals(code)) {
                return contractType;
            }
        }

        return null;
    }

    public String[][] getDescs() {
        return descs;
    }

    public void setDescs(String[][] descs) {
        this.descs = descs;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return VisitorLocale.getByCurrentLanguage(descs);
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
