package com.sinochem.crude.trade.order.enums;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.order.constart.MsgConstart;

/**
 * 订单操作类型
 * Created by zj on 2018/2/28
 */
public enum EnumOperationType {

    QR_CONTRACT("QR", MsgConstart.ORDER1001),
    JJ_CONTRACT("JJ", MsgConstart.ORDER1002),
    CH_CONTRACT("CH", MsgConstart.ORDER1003),
    SQQX_CONTRACT("SQQX", MsgConstart.ORDER1004),
    CHQX_CONTRACT("CHQX", MsgConstart.ORDER1005),
    TYQX_CONTRACT("TYQX", MsgConstart.ORDER1006),
    JJQX_CONTRACT("JJQX", MsgConstart.ORDER1007),
    ;

    private String code;
    private String desc;
    private String[][] descs;

    EnumOperationType(String code, String[][] descs) {
        this.code = code;
        this.descs = descs;
    }

    public static EnumOperationType getDoubleSignTypeByCode(String code) {
        for (EnumOperationType doubleSignType : EnumOperationType.values()) {
            if (doubleSignType.getCode().equals(code)) {
                return doubleSignType;
            }
        }

        return null;
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
