package com.sinochem.crude.trade.order.model.form;

import com.sinochem.crude.trade.order.domain.CrudeOilResource;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

public class CrudeOilResourceFrom extends CrudeOilResource {

    /**
     * 油品产地名称（仅用于回显）
     */
    private String originName;

    private final BigDecimal indicatorMultiple = new BigDecimal("100");

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public void setIndicator1MinDecimal(String indicator1MinDecimal) {
        setIndicator1Min(getLongValue(indicator1MinDecimal));
    }

    public void setIndicator1MaxDecimal(String indicator1MaxDecimal) {
        setIndicator1Max(getLongValue(indicator1MaxDecimal));
    }

    public void setIndicator2MinDecimal(String indicator2MinDecimal) {
        setIndicator2Min(getLongValue(indicator2MinDecimal));
    }

    public void setIndicator2MaxDecimal(String indicator2MaxDecimal) {
        setIndicator2Max(getLongValue(indicator2MaxDecimal));
    }

    public void setIndicator3MinDecimal(String indicator3MinDecimal) {
        setIndicator3Min(getLongValue(indicator3MinDecimal));
    }

    public void setIndicator3MaxDecimal(String indicator3MaxDecimal) {
        setIndicator3Max(getLongValue(indicator3MaxDecimal));
    }

    public void setIndicator4MinDecimal(String indicator4MinDecimal) {
        setIndicator4Min(getLongValue(indicator4MinDecimal));
    }

    public void setIndicator4MaxDecimal(String indicator4MaxDecimal) {
        setIndicator4Max(getLongValue(indicator4MaxDecimal));
    }

    public void setIndicator5MinDecimal(String indicator5MinDecimal) {
        setIndicator5Min(getLongValue(indicator5MinDecimal));
    }

    public void setIndicator5MaxDecimal(String indicator5MaxDecimal) {
        setIndicator5Max(getLongValue(indicator5MaxDecimal));
    }

    public void setIndicator6MinDecimal(String indicator6MinDecimal) {
        setIndicator6Min(getLongValue(indicator6MinDecimal));
    }

    public void setIndicator6MaxDecimal(String indicator6MaxDecimal) {
        setIndicator6Max(getLongValue(indicator6MaxDecimal));
    }

    public void setIndicator7MinDecimal(String indicator7MinDecimal) {
        setIndicator7Min(getLongValue(indicator7MinDecimal));
    }

    public void setIndicator7MaxDecimal(String indicator7MaxDecimal) {
        setIndicator7Max(getLongValue(indicator7MaxDecimal));
    }

    public void setIndicator8MinDecimal(String indicator8MinDecimal) {
        setIndicator8Min(getLongValue(indicator8MinDecimal));
    }

    public void setIndicator8MaxDecimal(String indicator8MaxDecimal) {
        setIndicator8Max(getLongValue(indicator8MaxDecimal));
    }

    public void setIndicator9MinDecimal(String indicator9MinDecimal) {
        setIndicator9Min(getLongValue(indicator9MinDecimal));
    }

    public void setIndicator9MaxDecimal(String indicator9MaxDecimal) {
        setIndicator9Max(getLongValue(indicator9MaxDecimal));
    }

    public void setIndicator10MinDecimal(String indicator10MinDecimal) {
        setIndicator10Min(getLongValue(indicator10MinDecimal));
    }

    public void setIndicator10MaxDecimal(String indicator10MaxDecimal) {
        setIndicator10Max(getLongValue(indicator10MaxDecimal));
    }

    public void setIndicator11MinDecimal(String indicator11MinDecimal) {
        setIndicator11Min(getLongValue(indicator11MinDecimal));
    }

    public void setIndicator11MaxDecimal(String indicator11MaxDecimal) {
        setIndicator11Max(getLongValue(indicator11MaxDecimal));
    }

    public void setIndicator12MinDecimal(String indicator12MinDecimal) {
        setIndicator12Min(getLongValue(indicator12MinDecimal));
    }

    public void setIndicator12MaxDecimal(String indicator12MaxDecimal) {
        setIndicator12Max(getLongValue(indicator12MaxDecimal));
    }

    /**
     * 搞个方法，一改都改了
     * @param indicator
     * @return
     */
    private long getLongValue(String indicator){
        BigDecimal dValue = StringUtils.isEmpty(indicator) ? BigDecimal.ZERO : new BigDecimal(indicator);
        return dValue.multiply(indicatorMultiple).longValue();
    }
}