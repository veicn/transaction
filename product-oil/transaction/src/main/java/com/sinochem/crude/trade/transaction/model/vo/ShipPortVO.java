package com.sinochem.crude.trade.transaction.model.vo;

import com.sinochem.crude.trade.common.model.vo.DictionaryVO;

/**
 * 港口的VO
 * @author Yichen Zhao
 * date: 20180301
 */
public class ShipPortVO {

    /**
     * 代码
     */
    private String code;

    /**
     * 装港
     */
    private String zhName;

    /**
     * 卸港
     */
    private String enName;

    /**
     * 港口类型
     */
    private DictionaryVO shipPortTypeVO;

    /**
     * 国家
     */
    private DictionaryVO countryVO;

    /** getters and setters */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public DictionaryVO getShipPortTypeVO() {
        return shipPortTypeVO;
    }

    public void setShipPortTypeVO(DictionaryVO shipPortTypeVO) {
        this.shipPortTypeVO = shipPortTypeVO;
    }

    public DictionaryVO getCountryVO() {
        return countryVO;
    }

    public void setCountryVO(DictionaryVO countryVO) {
        this.countryVO = countryVO;
    }
}
