package com.sinochem.crude.trade.listed.enums;

import java.util.ArrayList;
import java.util.List;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.model.vo.KeyValueVO;

/**
 * DataQuery中的oilNumer所对应的最小值和最大值
 * @author Yichen Zhao
 * date: 20180109
 */
public enum CrudeOilQuantityType {

    //BELOW_FIVE_HANDRED_THOUSAND("1", "50万桶以下", 0L, 500000000L),
    //FIVE_TO_TEN_HANDRED_THOUSAND("2", "50万-100万桶", 500000000L, 1000000000L),
    //TEN_TO_TWENTY_HANDRED_THOUSAND("3", "100万-200万桶", 1000000000L, 2000000000L),
    //ABOVE_TWENTY_HANDRED_THOUSAND("4", "200万桶以上", 2000000000L, Long.MAX_VALUE);
	
    BELOW_FIVE_HANDRED_THOUSAND("1", Constant.LISTED_0141, 0L, 500000000L),
    FIVE_TO_TEN_HANDRED_THOUSAND("2", Constant.LISTED_0142, 500000000L, 1000000000L),
    TEN_TO_TWENTY_HANDRED_THOUSAND("3", Constant.LISTED_0143, 1000000000L, 2000000000L),
    ABOVE_TWENTY_HANDRED_THOUSAND("4", Constant.LISTED_0144, 2000000000L, Long.MAX_VALUE);

    String code;
    String[][] names;
    String name;
    Long min;
    Long max;

    CrudeOilQuantityType(String code, String[][] names, Long min, Long max) {
        this.code = code;
        this.names = names;
        this.min = min;
        this.max = max;
    }

    public static List<KeyValueVO> convertToVOList() {
        List<KeyValueVO> keyValueVOList = new ArrayList<>();

        for (CrudeOilQuantityType crudeOilQuantityType : CrudeOilQuantityType.values()) {
            KeyValueVO keyValueVO = new KeyValueVO();

            keyValueVO.setKey(crudeOilQuantityType.getCode());
            keyValueVO.setValue(crudeOilQuantityType.getName());

            keyValueVOList.add(keyValueVO);
        }

        return keyValueVOList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }
}
