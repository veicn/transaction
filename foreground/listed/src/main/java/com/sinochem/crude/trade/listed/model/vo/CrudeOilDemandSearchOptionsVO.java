package com.sinochem.crude.trade.listed.model.vo;

import com.sinochem.crude.trade.listed.enums.CrudeOilDemandSearchOptionType;

import java.io.Serializable;
import java.util.List;

/**
 * 用于展示原油搜索条件
 * @author Yichen Zhao
 * date: 20180111
 */
public class CrudeOilDemandSearchOptionsVO implements Serializable {

    private static final long serialVersionUID = -6169229002769140555L;

    private CrudeOilDemandSearchOptionType searchOptionType;

    private List<KeyValueVO> searchOptionList;

    public CrudeOilDemandSearchOptionsVO() {}

    public CrudeOilDemandSearchOptionsVO(CrudeOilDemandSearchOptionType searchOptionType, List<KeyValueVO> searchOptionList) {
        this.searchOptionType = searchOptionType;
        this.searchOptionList = searchOptionList;
    }

    /** getters and setters*/
    public CrudeOilDemandSearchOptionType getSearchOptionType() {
        return searchOptionType;
    }

    public void setSearchOptionType(CrudeOilDemandSearchOptionType searchOptionType) {
        this.searchOptionType = searchOptionType;
    }

    public List<KeyValueVO> getSearchOptionList() {
        return searchOptionList;
    }

    public void setSearchOptionList(List<KeyValueVO> searchOptionList) {
        this.searchOptionList = searchOptionList;
    }
}
