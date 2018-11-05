package com.sinochem.crude.trade.order.enums;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.order.constart.MsgConstart;

/**
 * 双签类型
 * Created by zj on 2018/2/28
 */
public enum EnumDoubleSignType {

    CONFIRM_CONTRACT("01", MsgConstart.ORDER0027),
    UPDATE_CONTRACT("02", MsgConstart.ORDER0032),
    CANCEL_CONTRACT("03", MsgConstart.ORDER0033)
    ;

    private String code;
    private String desc;
    private String[][] descs;

    EnumDoubleSignType(String code, String[][] desc) {
        this.code = code;
        this.descs = descs;
    }

    public static EnumDoubleSignType getDoubleSignTypeByCode(String code) {
        for (EnumDoubleSignType doubleSignType : EnumDoubleSignType.values()) {
            if (doubleSignType.getCode().equals(code)) {
                return doubleSignType;
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
