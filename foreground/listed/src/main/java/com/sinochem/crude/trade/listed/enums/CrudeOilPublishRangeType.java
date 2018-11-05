package com.sinochem.crude.trade.listed.enums;

import java.util.ArrayList;
import java.util.List;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.model.vo.KeyValueVO;

/**
 * Created by sijliu on 18/01/2018.
 */
public enum CrudeOilPublishRangeType {

    //ALL_PUBLISH(0, "公开发布"),
    //SPECIFY_PUBLISH(1, "定向发布");
	
    ALL_PUBLISH(0, Constant.LISTED_0138),
    SPECIFY_PUBLISH(1, Constant.LISTED_0139);

    Integer code;
    String[][] names;
    String name;

    CrudeOilPublishRangeType(Integer code, String[][] names) {
        this.code = code;
        this.names = names;
    }

    public static CrudeOilPublishRangeType getCrudeOilPublishRangeTypeByCode(Integer code) {
        for (CrudeOilPublishRangeType publishRangeType : CrudeOilPublishRangeType.values()) {
            if (publishRangeType.getCode().equals(code)) {
                return publishRangeType;
            }
        }
        return null;
    }

    public static List<KeyValueVO> convertToVOList() {
        List<KeyValueVO> keyValueVOList = new ArrayList<>();

        for(CrudeOilPublishRangeType publishRangeType : CrudeOilPublishRangeType.values()) {
            KeyValueVO keyValueVO = new KeyValueVO();

            keyValueVO.setKey(publishRangeType.getCode().toString());
            keyValueVO.setValue(publishRangeType.getName());

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
