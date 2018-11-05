package com.sinochem.crude.trade.goods.remote;

import com.sinochem.crude.trade.goods.domain.BodyProperties;

/**
 * BodyProperties的VO，用于成品油和原油的VO转换
 * @author Yichen Zhao
 * date: 20180129
 */
public class BodyPropertiesVO {

    /**
     * 物性id
     */
    private Long id;

    /**
     * 指标项（值*100）
     */
    private long indicator1Min;
    private String indicator1MinAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator1Max;
    private String indicator1MaxAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator2Min;
    private String indicator2MinAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator2Max;
    private String indicator2MaxAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator3Min;
    private String indicator3MinAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator3Max;
    private String indicator3MaxAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator4Min;
    private String indicator4MinAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator4Max;
    private String indicator4MaxAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator5Min;
    private String indiactor5MinAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator5Max;
    private String indicator5MaxAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator6Min;
    private String indiactor6MinAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator6Max;
    private String indicator6MaxAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator7Min;
    private String indicator7MinAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator7Max;
    private String indiactor7MaxAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator8Min;
    private String indicator8MinAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator8Max;
    private String indicator8MaxAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator9Min;
    private String indicator9MinAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator9Max;
    private String indicator9MaxAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator10Min;
    private String indicator10MinAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator10Max;
    private String indicator10MaxAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator11Min;
    private String indicator11MinAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator11Max;
    private String indicator11MaxAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator12Min;
    private String indicator12MinAsString;

    /**
     * 指标项（值*100）
     */
    private long indicator12Max;
    private String indicator12MaxAsString;

    public static void convertToBodyPropertiesVO(
            BodyProperties bodyProperties, BodyPropertiesVO bodyPropertiesVO) {
        if (bodyProperties == null || bodyPropertiesVO == null) {
            return;
        }

        bodyPropertiesVO.setId(bodyProperties.getId());

        Long indicator1Min = bodyProperties.getIndicator1Min();
        if (indicator1Min != null) {
            bodyPropertiesVO.setIndicator1Min(indicator1Min);
            bodyPropertiesVO.setIndicator1MinAsString(indicator1Min.toString());
        }

        Long indicactor1Max = bodyProperties.getIndicator1Max();
        if (indicactor1Max != null) {
            bodyPropertiesVO.setIndicator1Max(indicactor1Max);
            bodyPropertiesVO.setIndicator1MaxAsString(indicactor1Max.toString());
        }

        Long indicator2Min = bodyProperties.getIndicator2Min();
        if (indicator2Min != null) {
            bodyPropertiesVO.setIndicator2Min(indicator2Min);
            bodyPropertiesVO.setIndicator2MinAsString(indicator2Min.toString());
        }

        Long indicator2Max = bodyProperties.getIndicator2Max();
        if (indicator2Max != null) {
            bodyPropertiesVO.setIndicator2Max(indicator2Max);
            bodyPropertiesVO.setIndicator2MaxAsString(indicator2Max.toString());
        }

        Long indicator3Min = bodyProperties.getIndicator3Min();
        if (indicator3Min != null) {
            bodyPropertiesVO.setIndicator3Min(indicator3Min);
            bodyPropertiesVO.setIndicator3MinAsString(indicator3Min.toString());
        }

        Long indicator3Max = bodyProperties.getIndicator3Max();
        if (indicator3Max != null) {
            bodyPropertiesVO.setIndicator3Max(indicator3Max);
            bodyPropertiesVO.setIndicator3MaxAsString(indicator3Max.toString());
        }

        Long indicator4Min = bodyProperties.getIndicator4Min();
        if (indicator4Min != null) {
            bodyPropertiesVO.setIndicator4Min(indicator4Min);
            bodyPropertiesVO.setIndicator4MinAsString(indicator4Min.toString());
        }

        Long indicator4Max = bodyProperties.getIndicator4Max();
        if (indicator4Max != null) {
            bodyPropertiesVO.setIndicator4Max(indicator4Max);
            bodyPropertiesVO.setIndicator4MaxAsString(indicator4Max.toString());
        }

        Long indicator5Min = bodyProperties.getIndicator5Min();
        if (indicator5Min != null) {
            bodyPropertiesVO.setIndicator5Min(indicator5Min);
            bodyPropertiesVO.setIndiactor5MinAsString(indicator5Min.toString());
        }

        Long indicator5Max = bodyProperties.getIndicator5Max();
        if (indicator5Max != null) {
            bodyPropertiesVO.setIndicator5Max(indicator5Max);
            bodyPropertiesVO.setIndicator5MaxAsString(indicator5Max.toString());
        }

        Long indicator6Min = bodyProperties.getIndicator6Min();
        if (indicator6Min != null) {
            bodyPropertiesVO.setIndicator6Min(indicator6Min);
            bodyPropertiesVO.setIndiactor6MinAsString(indicator6Min.toString());
        }

        Long indicator6Max = bodyProperties.getIndicator6Max();
        if (indicator6Max != null) {
            bodyPropertiesVO.setIndicator6Max(indicator6Max);
            bodyPropertiesVO.setIndicator6MaxAsString(indicator6Max.toString());
        }

        Long indicator7Min = bodyProperties.getIndicator7Min();
        if (indicator7Min != null) {
            bodyPropertiesVO.setIndicator7Min(indicator7Min);
            bodyPropertiesVO.setIndicator7MinAsString(indicator7Min.toString());
        }

        Long indicator7Max = bodyProperties.getIndicator7Max();
        if (indicator7Max != null) {
            bodyPropertiesVO.setIndicator7Max(indicator7Max);
            bodyPropertiesVO.setIndiactor7MaxAsString(indicator7Max.toString());
        }

        Long indicator8Min = bodyProperties.getIndicator8Min();
        if (indicator8Min != null) {
            bodyPropertiesVO.setIndicator8Min(indicator8Min);
            bodyPropertiesVO.setIndicator8MinAsString(indicator8Min.toString());
        }

        Long indicator8Max = bodyProperties.getIndicator8Max();
        if (indicator8Max != null) {
            bodyPropertiesVO.setIndicator8Max(indicator8Max);
            bodyPropertiesVO.setIndicator8MaxAsString(indicator8Max.toString());
        }

        Long indicator9Min = bodyProperties.getIndicator9Min();
        if (indicator9Min != null) {
            bodyPropertiesVO.setIndicator9Min(indicator9Min);
            bodyPropertiesVO.setIndicator9MinAsString(indicator9Min.toString());
        }

        Long indicator9Max = bodyProperties.getIndicator9Max();
        if (indicator9Max != null) {
            bodyPropertiesVO.setIndicator9Max(indicator9Max);
            bodyPropertiesVO.setIndicator9MaxAsString(indicator9Max.toString());
        }

        Long indicator10Min = bodyProperties.getIndicator10Min();
        if (indicator10Min != null) {
            bodyPropertiesVO.setIndicator10Min(indicator10Min);
            bodyPropertiesVO.setIndicator10MinAsString(indicator10Min.toString());
        }

        Long indicator10Max = bodyProperties.getIndicator10Max();
        if (indicator10Max != null) {
            bodyPropertiesVO.setIndicator10Max(indicator10Max);
            bodyPropertiesVO.setIndicator10MaxAsString(indicator10Max.toString());
        }

        Long indicator11Min = bodyProperties.getIndicator11Min();
        if (indicator11Min != null) {
            bodyPropertiesVO.setIndicator11Min(indicator11Min);
            bodyPropertiesVO.setIndicator11MinAsString(indicator11Min.toString());
        }

        Long indicator11Max = bodyProperties.getIndicator11Max();
        if (indicator11Max != null) {
            bodyPropertiesVO.setIndicator11Max(indicator11Max);
            bodyPropertiesVO.setIndicator11MaxAsString(indicator11Max.toString());
        }

        Long indicator12Min = bodyProperties.getIndicator12Min();
        if (indicator12Min != null) {
            bodyPropertiesVO.setIndicator12Min(indicator12Min);
            bodyPropertiesVO.setIndicator12MinAsString(indicator12Min.toString());
        }

        Long indicator12Max = bodyProperties.getIndicator12Max();
        if (indicactor1Max != null) {
            bodyPropertiesVO.setIndicator12Max(indicator12Max);
            bodyPropertiesVO.setIndicator12MaxAsString(indicator12Max.toString());
        }
    }

    public static void convertToBodyProperties(
            BodyPropertiesVO bodyPropertiesVO, BodyProperties bodyProperties) {
        if (bodyPropertiesVO == null || bodyProperties == null) {
            return;
        }

        bodyProperties.setId(bodyPropertiesVO.getId());
        bodyProperties.setIndicator1Min(bodyPropertiesVO.getIndicator1Min());
        bodyProperties.setIndicator1Max(bodyPropertiesVO.getIndicator1Max());
        bodyProperties.setIndicator2Min(bodyPropertiesVO.getIndicator2Min());
        bodyProperties.setIndicator2Max(bodyPropertiesVO.getIndicator2Max());
        bodyProperties.setIndicator3Min(bodyPropertiesVO.getIndicator3Min());
        bodyProperties.setIndicator3Max(bodyPropertiesVO.getIndicator3Max());
        bodyProperties.setIndicator4Min(bodyPropertiesVO.getIndicator4Min());
        bodyProperties.setIndicator4Max(bodyPropertiesVO.getIndicator4Max());
        bodyProperties.setIndicator5Min(bodyPropertiesVO.getIndicator5Min());
        bodyProperties.setIndicator5Max(bodyPropertiesVO.getIndicator5Max());
        bodyProperties.setIndicator6Min(bodyPropertiesVO.getIndicator6Min());
        bodyProperties.setIndicator6Max(bodyPropertiesVO.getIndicator6Max());
        bodyProperties.setIndicator7Min(bodyPropertiesVO.getIndicator7Min());
        bodyProperties.setIndicator7Max(bodyPropertiesVO.getIndicator7Max());
        bodyProperties.setIndicator8Min(bodyPropertiesVO.getIndicator8Min());
        bodyProperties.setIndicator8Max(bodyPropertiesVO.getIndicator8Max());
        bodyProperties.setIndicator9Min(bodyPropertiesVO.getIndicator9Min());
        bodyProperties.setIndicator9Max(bodyPropertiesVO.getIndicator9Max());
        bodyProperties.setIndicator10Min(bodyPropertiesVO.getIndicator10Min());
        bodyProperties.setIndicator10Max(bodyPropertiesVO.getIndicator10Max());
        bodyProperties.setIndicator11Min(bodyPropertiesVO.getIndicator11Min());
        bodyProperties.setIndicator11Max(bodyPropertiesVO.getIndicator11Max());
        bodyProperties.setIndicator12Min(bodyPropertiesVO.getIndicator12Min());
        bodyProperties.setIndicator12Max(bodyPropertiesVO.getIndicator12Max());
    }

    /** getters and setters */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIndicator1Min() {
        return indicator1Min;
    }

    public void setIndicator1Min(long indicator1Min) {
        this.indicator1Min = indicator1Min;
    }

    public long getIndicator1Max() {
        return indicator1Max;
    }

    public void setIndicator1Max(long indicator1Max) {
        this.indicator1Max = indicator1Max;
    }

    public long getIndicator2Min() {
        return indicator2Min;
    }

    public void setIndicator2Min(long indicator2Min) {
        this.indicator2Min = indicator2Min;
    }

    public long getIndicator2Max() {
        return indicator2Max;
    }

    public void setIndicator2Max(long indicator2Max) {
        this.indicator2Max = indicator2Max;
    }

    public long getIndicator3Min() {
        return indicator3Min;
    }

    public void setIndicator3Min(long indicator3Min) {
        this.indicator3Min = indicator3Min;
    }

    public long getIndicator3Max() {
        return indicator3Max;
    }

    public void setIndicator3Max(long indicator3Max) {
        this.indicator3Max = indicator3Max;
    }

    public long getIndicator4Min() {
        return indicator4Min;
    }

    public void setIndicator4Min(long indicator4Min) {
        this.indicator4Min = indicator4Min;
    }

    public long getIndicator4Max() {
        return indicator4Max;
    }

    public void setIndicator4Max(long indicator4Max) {
        this.indicator4Max = indicator4Max;
    }

    public long getIndicator5Min() {
        return indicator5Min;
    }

    public void setIndicator5Min(long indicator5Min) {
        this.indicator5Min = indicator5Min;
    }

    public long getIndicator5Max() {
        return indicator5Max;
    }

    public void setIndicator5Max(long indicator5Max) {
        this.indicator5Max = indicator5Max;
    }

    public long getIndicator6Min() {
        return indicator6Min;
    }

    public void setIndicator6Min(long indicator6Min) {
        this.indicator6Min = indicator6Min;
    }

    public long getIndicator6Max() {
        return indicator6Max;
    }

    public void setIndicator6Max(long indicator6Max) {
        this.indicator6Max = indicator6Max;
    }

    public long getIndicator7Min() {
        return indicator7Min;
    }

    public void setIndicator7Min(long indicator7Min) {
        this.indicator7Min = indicator7Min;
    }

    public long getIndicator7Max() {
        return indicator7Max;
    }

    public void setIndicator7Max(long indicator7Max) {
        this.indicator7Max = indicator7Max;
    }

    public long getIndicator8Min() {
        return indicator8Min;
    }

    public void setIndicator8Min(long indicator8Min) {
        this.indicator8Min = indicator8Min;
    }

    public long getIndicator8Max() {
        return indicator8Max;
    }

    public void setIndicator8Max(long indicator8Max) {
        this.indicator8Max = indicator8Max;
    }

    public long getIndicator9Min() {
        return indicator9Min;
    }

    public void setIndicator9Min(long indicator9Min) {
        this.indicator9Min = indicator9Min;
    }

    public long getIndicator9Max() {
        return indicator9Max;
    }

    public void setIndicator9Max(long indicator9Max) {
        this.indicator9Max = indicator9Max;
    }

    public long getIndicator10Min() {
        return indicator10Min;
    }

    public void setIndicator10Min(long indicator10Min) {
        this.indicator10Min = indicator10Min;
    }

    public long getIndicator10Max() {
        return indicator10Max;
    }

    public void setIndicator10Max(long indicator10Max) {
        this.indicator10Max = indicator10Max;
    }

    public long getIndicator11Min() {
        return indicator11Min;
    }

    public void setIndicator11Min(long indicator11Min) {
        this.indicator11Min = indicator11Min;
    }

    public long getIndicator11Max() {
        return indicator11Max;
    }

    public void setIndicator11Max(long indicator11Max) {
        this.indicator11Max = indicator11Max;
    }

    public long getIndicator12Min() {
        return indicator12Min;
    }

    public void setIndicator12Min(long indicator12Min) {
        this.indicator12Min = indicator12Min;
    }

    public long getIndicator12Max() {
        return indicator12Max;
    }

    public void setIndicator12Max(long indicator12Max) {
        this.indicator12Max = indicator12Max;
    }

    public String getIndicator1MinAsString() {
        return indicator1MinAsString;
    }

    public void setIndicator1MinAsString(String indicator1MinAsString) {
        this.indicator1MinAsString = indicator1MinAsString;
    }

    public String getIndicator1MaxAsString() {
        return indicator1MaxAsString;
    }

    public void setIndicator1MaxAsString(String indicator1MaxAsString) {
        this.indicator1MaxAsString = indicator1MaxAsString;
    }

    public String getIndicator2MinAsString() {
        return indicator2MinAsString;
    }

    public void setIndicator2MinAsString(String indicator2MinAsString) {
        this.indicator2MinAsString = indicator2MinAsString;
    }

    public String getIndicator2MaxAsString() {
        return indicator2MaxAsString;
    }

    public void setIndicator2MaxAsString(String indicator2MaxAsString) {
        this.indicator2MaxAsString = indicator2MaxAsString;
    }

    public String getIndicator3MinAsString() {
        return indicator3MinAsString;
    }

    public void setIndicator3MinAsString(String indicator3MinAsString) {
        this.indicator3MinAsString = indicator3MinAsString;
    }

    public String getIndicator3MaxAsString() {
        return indicator3MaxAsString;
    }

    public void setIndicator3MaxAsString(String indicator3MaxAsString) {
        this.indicator3MaxAsString = indicator3MaxAsString;
    }

    public String getIndicator4MinAsString() {
        return indicator4MinAsString;
    }

    public void setIndicator4MinAsString(String indicator4MinAsString) {
        this.indicator4MinAsString = indicator4MinAsString;
    }

    public String getIndicator4MaxAsString() {
        return indicator4MaxAsString;
    }

    public void setIndicator4MaxAsString(String indicator4MaxAsString) {
        this.indicator4MaxAsString = indicator4MaxAsString;
    }

    public String getIndiactor5MinAsString() {
        return indiactor5MinAsString;
    }

    public void setIndiactor5MinAsString(String indiactor5MinAsString) {
        this.indiactor5MinAsString = indiactor5MinAsString;
    }

    public String getIndicator5MaxAsString() {
        return indicator5MaxAsString;
    }

    public void setIndicator5MaxAsString(String indicator5MaxAsString) {
        this.indicator5MaxAsString = indicator5MaxAsString;
    }

    public String getIndiactor6MinAsString() {
        return indiactor6MinAsString;
    }

    public void setIndiactor6MinAsString(String indiactor6MinAsString) {
        this.indiactor6MinAsString = indiactor6MinAsString;
    }

    public String getIndicator6MaxAsString() {
        return indicator6MaxAsString;
    }

    public void setIndicator6MaxAsString(String indicator6MaxAsString) {
        this.indicator6MaxAsString = indicator6MaxAsString;
    }

    public String getIndicator7MinAsString() {
        return indicator7MinAsString;
    }

    public void setIndicator7MinAsString(String indicator7MinAsString) {
        this.indicator7MinAsString = indicator7MinAsString;
    }

    public String getIndiactor7MaxAsString() {
        return indiactor7MaxAsString;
    }

    public void setIndiactor7MaxAsString(String indiactor7MaxAsString) {
        this.indiactor7MaxAsString = indiactor7MaxAsString;
    }

    public String getIndicator8MinAsString() {
        return indicator8MinAsString;
    }

    public void setIndicator8MinAsString(String indicator8MinAsString) {
        this.indicator8MinAsString = indicator8MinAsString;
    }

    public String getIndicator8MaxAsString() {
        return indicator8MaxAsString;
    }

    public void setIndicator8MaxAsString(String indicator8MaxAsString) {
        this.indicator8MaxAsString = indicator8MaxAsString;
    }

    public String getIndicator9MinAsString() {
        return indicator9MinAsString;
    }

    public void setIndicator9MinAsString(String indicator9MinAsString) {
        this.indicator9MinAsString = indicator9MinAsString;
    }

    public String getIndicator9MaxAsString() {
        return indicator9MaxAsString;
    }

    public void setIndicator9MaxAsString(String indicator9MaxAsString) {
        this.indicator9MaxAsString = indicator9MaxAsString;
    }

    public String getIndicator10MinAsString() {
        return indicator10MinAsString;
    }

    public void setIndicator10MinAsString(String indicator10MinAsString) {
        this.indicator10MinAsString = indicator10MinAsString;
    }

    public String getIndicator10MaxAsString() {
        return indicator10MaxAsString;
    }

    public void setIndicator10MaxAsString(String indicator10MaxAsString) {
        this.indicator10MaxAsString = indicator10MaxAsString;
    }

    public String getIndicator11MinAsString() {
        return indicator11MinAsString;
    }

    public void setIndicator11MinAsString(String indicator11MinAsString) {
        this.indicator11MinAsString = indicator11MinAsString;
    }

    public String getIndicator11MaxAsString() {
        return indicator11MaxAsString;
    }

    public void setIndicator11MaxAsString(String indicator11MaxAsString) {
        this.indicator11MaxAsString = indicator11MaxAsString;
    }

    public String getIndicator12MinAsString() {
        return indicator12MinAsString;
    }

    public void setIndicator12MinAsString(String indicator12MinAsString) {
        this.indicator12MinAsString = indicator12MinAsString;
    }

    public String getIndicator12MaxAsString() {
        return indicator12MaxAsString;
    }

    public void setIndicator12MaxAsString(String indicator12MaxAsString) {
        this.indicator12MaxAsString = indicator12MaxAsString;
    }
}
