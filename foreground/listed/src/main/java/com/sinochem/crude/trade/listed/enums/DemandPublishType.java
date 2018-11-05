package com.sinochem.crude.trade.listed.enums;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.model.vo.KeyValueVO;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

/**
 * 需求单发布类型（按油种，性质）
 * @author Yichen Zhao
 * date: 20180117
 */
public enum DemandPublishType {

    /**
     * 按油种
     */
    //BASE_ON_CRUDE_OIL_OPTION(1, "按油种采购"),
    BASE_ON_CRUDE_OIL_OPTION(1, Constant.LISTED_0147),
    
    /**
     * 按性质
     */
    //BASE_ON_PROPERTY(2, "按性质采购");
    BASE_ON_PROPERTY(2, Constant.LISTED_0148);

    Integer code;
    String[][] names;
    String name;

    DemandPublishType(Integer code, String[][] names) {
        this.code = code;
        this.names = names;
    }

    public static DemandPublishType getDemandPublishTypeByCode(Integer code) {
        for (DemandPublishType demandPublishType : DemandPublishType.values()) {
            if (demandPublishType.getCode().equals(code)) {
                return demandPublishType;
            }
        }

        return null;
    }

    public static List<KeyValueVO> convertToVOList() {
        List<KeyValueVO> keyValueVOList = new ArrayList<>();

        for (DemandPublishType demandPublishType : DemandPublishType.values()) {
            KeyValueVO keyValueVO = new KeyValueVO();

            keyValueVO.setKey(demandPublishType.getCode().toString());
            keyValueVO.setValue(demandPublishType.getName());

            keyValueVOList.add(keyValueVO);
        }

        return keyValueVOList;
    }

    /** getters and setters */
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String[][] getNames() {
        return names;
    }

    public void setNames(String[][] names) {
        this.names = names;
    }

    public String getName() {
    	return VisitorLocale.getByCurrentLanguage(names);
    }

    public void setName(String name) {
        this.name = name;
    }
}
