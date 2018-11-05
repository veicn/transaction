package com.sinochem.crude.trade.listed.model.form;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;
import com.sinochem.crude.trade.listed.domain.CrudeOil;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;

/**
 * 接收油表
 */
public class CrudeOilForm {

    private static final int AMPLIFY_SCALE = 100; //数据库中存储的是整型

    /**
     * id
     */
    private Long id;
    /**
     *油种表id
     */
    private Long crudeOilId;

    /**
     * 区域
     */
    private Long area;
    
    /**
     * 字符串地区
     */
    private String originName;
    
    /**
     * 字符串地区
     */
    private String originNameE;
    
    /**
     * 名称（英文名）
     */
    @Length(max=255)
    private String name;

    /**
     * 中文名
     */
    @Length(max=255)
    private String cnName;

    /**
     * 产地
     */
    private String origin;

    /**
     * 物性表id
     */
    private Long propertyId;

    /**
     * 版本发布日期
     */
    private String version;

    /**
     * 序号
     */
    private String order;

    /**
     * 指标1最小值
     */
    private String indicator1Min;

    /**
     * 指标1最大值
     */
    private String indicator1Max;

    /**
     * 指标2最小值
     */
    private String indicator2Min;

    /**
     * 指标2最大值
     */
    private String indicator2Max;

    /**
     * 指标3最小值
     */
    private String indicator3Min;

    /**
     * 指标3最大值
     */
    private String indicator3Max;

    /**
     * 指标4最小值
     */
    private String indicator4Min;

    /**
     * 指标4最大值
     */
    private String indicator4Max;

    /**
     * 指标5最小值
     */
    private String indicator5Min;

    /**
     * 指标5最大值
     */
    private String indicator5Max;

    /**
     * 指标6最小值
     */
    private String indicator6Min;

    /**
     * 指标6最大值
     */
    private String indicator6Max;

    /**
     * 指标7最小值
     */
    private String indicator7Min;

    /**
     * 指标7最大值
     */
    private String indicator7Max;

    /**
     * 指标8最小值
     */
    private String indicator8Min;

    /**
     * 指标8最大值
     */
    private String indicator8Max;

    /**
     * 指标9最小值
     */
    private String indicator9Min;

    /**
     * 指标9最大值
     */
    private String indicator9Max;

    /**
     * 指标10最小值
     */
    private String indicator10Min;

    /**
     * 指标10最大值
     */
    private String indicator10Max;

    /**
     * 指标11最小值
     */
    private String indicator11Min;

    /**
     * 指标11最大值
     */
    private String indicator11Max;

    /**
     * 指标12最小值
     */
    private String indicator12Min;

    /**
     * 指标12最大值
     */
    private String indicator12Max;

    /**
     * 备注
     */
    @Length(max=255)
    private String desc;

    public static CrudeOil convertToDomain(CrudeOilForm crudeOilForm) {
        if (crudeOilForm == null) {
            return null;
        }

        CrudeOil crudeOil = new CrudeOil();

        String origin = crudeOilForm.getOrigin();
        
        if (StringUtils.isNotBlank(origin)) {
        	crudeOil.setOrigin(Long.parseLong(origin));
        }
        
        crudeOil.setPropertyId(crudeOilForm.getPropertyId());
        crudeOil.setArea(crudeOilForm.getArea());
        crudeOil.setCnName(crudeOilForm.getCnName());
        crudeOil.setName(crudeOilForm.getName());
        crudeOil.setDesc(crudeOilForm.getDesc());

        String indicator1MinString = crudeOilForm.getIndicator1Min();
        if (!StringUtil.isEmpty(indicator1MinString)) {
            Double indicator1Min = Double.valueOf(indicator1MinString) * AMPLIFY_SCALE;
            crudeOil.setIndicator1Min(indicator1Min.longValue());
        }

        String indicator1MaxSting = crudeOilForm.getIndicator1Max();
        if (!StringUtil.isEmpty(indicator1MaxSting)) {
            Double indicator1Max = Double.valueOf(indicator1MaxSting) * AMPLIFY_SCALE;
            crudeOil.setIndicator1Max(indicator1Max.longValue());
        }

        String indicator2MinString = crudeOilForm.getIndicator2Min();
        if (!StringUtil.isEmpty(indicator2MinString)) {
            Double indicator2Min = Double.valueOf(indicator2MinString) * AMPLIFY_SCALE;
            crudeOil.setIndicator2Min(indicator2Min.longValue());
        }

        String indicator2MaxString = crudeOilForm.getIndicator2Max();
        if (!StringUtil.isEmpty(indicator2MaxString)) {
            Double indicator2Max = Double.valueOf(indicator2MaxString) * AMPLIFY_SCALE;
            crudeOil.setIndicator2Max(indicator2Max.longValue());
        }

        String indicator3MinString = crudeOilForm.getIndicator3Min();
        if (!StringUtil.isEmpty(indicator3MinString)) {
            Double indicator3Min = Double.valueOf(indicator3MinString) * AMPLIFY_SCALE;
            crudeOil.setIndicator3Min(indicator3Min.longValue());
        }

        String indicator3MaxString = crudeOilForm.getIndicator3Max();
        if (!StringUtil.isEmpty(indicator3MaxString)) {
            Double indicator3Max = Double.valueOf(indicator3MaxString) * AMPLIFY_SCALE;
            crudeOil.setIndicator3Max(indicator3Max.longValue());
        }

        String indicator4MinString = crudeOilForm.getIndicator4Min();
        if (!StringUtil.isEmpty(indicator4MinString)) {
            Double indicator4Min = Double.valueOf(indicator4MinString) * AMPLIFY_SCALE;
            crudeOil.setIndicator4Min(indicator4Min.longValue());
        }

        String indicator4MaxString = crudeOilForm.getIndicator4Max();
        if (!StringUtil.isEmpty(indicator4MaxString)) {
            Double indicator4Max = Double.valueOf(indicator4MaxString) * AMPLIFY_SCALE;
            crudeOil.setIndicator4Max(indicator4Max.longValue());
        }

        String indicator5MinString = crudeOilForm.getIndicator5Min();
        if (!StringUtil.isEmpty(indicator5MinString)) {
            Double indicator5Min = Double.valueOf(indicator5MinString) * AMPLIFY_SCALE;
            crudeOil.setIndicator5Min(indicator5Min.longValue());
        }

        String indicator5MaxString = crudeOilForm.getIndicator5Max();
        if (!StringUtil.isEmpty(indicator5MaxString)) {
            Double indicator5Max = Double.valueOf(indicator5MaxString) * AMPLIFY_SCALE;
            crudeOil.setIndicator5Max(indicator5Max.longValue());
        }

        String indicator6MinString = crudeOilForm.getIndicator6Min();
        if (!StringUtil.isEmpty(indicator6MinString)) {
            Double indicator6Min = Double.valueOf(indicator6MinString) * AMPLIFY_SCALE;
            crudeOil.setIndicator6Min(indicator6Min.longValue());
        }

        String indicator6MaxString = crudeOilForm.getIndicator6Max();
        if (!StringUtil.isEmpty(indicator6MaxString)) {
            Double indicator6Max = Double.valueOf(indicator6MaxString) * AMPLIFY_SCALE;
            crudeOil.setIndicator6Max(indicator6Max.longValue());
        }

        String indicator7MinString = crudeOilForm.getIndicator7Min();
        if (!StringUtil.isEmpty(indicator7MinString)) {
            Double indicator7Min = Double.valueOf(indicator7MinString) * AMPLIFY_SCALE;
            crudeOil.setIndicator7Min(indicator7Min.longValue());
        }

        String indicator7MaxString = crudeOilForm.getIndicator7Max();
        if (!StringUtil.isEmpty(indicator7MaxString)) {
            Double indicator7Max = Double.valueOf(indicator7MaxString) * AMPLIFY_SCALE;
            crudeOil.setIndicator7Max(indicator7Max.longValue());
        }

        String indicator8MinString = crudeOilForm.getIndicator8Min();
        if (!StringUtil.isEmpty(indicator8MinString)) {
            Double indicator8Min = Double.valueOf(indicator8MinString) * AMPLIFY_SCALE;
            crudeOil.setIndicator8Min(indicator8Min.longValue());
        }

        String indicator8MaxString = crudeOilForm.getIndicator8Max();
        if (!StringUtil.isEmpty(indicator8MaxString)) {
            Double indicator8Max = Double.valueOf(indicator8MaxString) * AMPLIFY_SCALE;
            crudeOil.setIndicator8Max(indicator8Max.longValue());
        }

        String indicator9MinString = crudeOilForm.getIndicator9Min();
        if (!StringUtil.isEmpty(indicator9MinString)) {
            Double indicator9Min = Double.valueOf(indicator9MinString) * AMPLIFY_SCALE;
            crudeOil.setIndicator9Min(indicator9Min.longValue());
        }

        String indicator9MaxString = crudeOilForm.getIndicator9Max();
        if (!StringUtil.isEmpty(indicator9MaxString)) {
            Double indicator9Max = Double.valueOf(indicator9MaxString) * AMPLIFY_SCALE;
            crudeOil.setIndicator9Max(indicator9Max.longValue());
        }

        String indicator10MinString = crudeOilForm.getIndicator10Min();
        if (!StringUtil.isEmpty(indicator10MinString)) {
            Double indicator10Min = Double.valueOf(indicator10MinString) * AMPLIFY_SCALE;
            crudeOil.setIndicator10Min(indicator10Min.longValue());
        }

        String indicator10MaxString = crudeOilForm.getIndicator10Max();
        if (!StringUtil.isEmpty(indicator10MaxString)) {
            Double indicator10Max = Double.valueOf(indicator10MaxString) * AMPLIFY_SCALE;
            crudeOil.setIndicator10Max(indicator10Max.longValue());
        }

        String indicator11MinString = crudeOilForm.getIndicator11Min();
        if (!StringUtil.isEmpty(indicator11MinString)) {
            Double indicator11Min = Double.valueOf(indicator11MinString) * AMPLIFY_SCALE;
            crudeOil.setIndicator11Min(indicator11Min.longValue());
        }

        String indicator11MaxString = crudeOilForm.getIndicator11Max();
        if (!StringUtil.isEmpty(indicator11MaxString)) {
            Double indicator11Max = Double.valueOf(indicator11MaxString) * AMPLIFY_SCALE;
            crudeOil.setIndicator11Max(indicator11Max.longValue());
        }

        String indicator12MinString = crudeOilForm.getIndicator12Min();
        if (!StringUtil.isEmpty(indicator12MinString)) {
            Double indicator12Min = Double.valueOf(indicator12MinString) * AMPLIFY_SCALE;
            crudeOil.setIndicator12Min(indicator12Min.longValue());
        }

        String indicator12MaxString = crudeOilForm.getIndicator12Max();
        if (!StringUtil.isEmpty(indicator12MaxString)) {
            Double indicator12Max = Double.valueOf(indicator12MaxString) * AMPLIFY_SCALE;
            crudeOil.setIndicator12Max(indicator12Max.longValue());
        }

        return crudeOil;
    }

    /** getters and setters */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCrudeOilId() {
        return crudeOilId;
    }

    public void setCrudeOilId(Long crudeOilId) {
        this.crudeOilId = crudeOilId;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getIndicator1Min() {
        return indicator1Min;
    }

    public void setIndicator1Min(String indicator1Min) {
        this.indicator1Min = indicator1Min;
    }

    public String getIndicator1Max() {
        return indicator1Max;
    }

    public void setIndicator1Max(String indicator1Max) {
        this.indicator1Max = indicator1Max;
    }

    public String getIndicator2Min() {
        return indicator2Min;
    }

    public void setIndicator2Min(String indicator2Min) {
        this.indicator2Min = indicator2Min;
    }

    public String getIndicator2Max() {
        return indicator2Max;
    }

    public void setIndicator2Max(String indicator2Max) {
        this.indicator2Max = indicator2Max;
    }

    public String getIndicator3Min() {
        return indicator3Min;
    }

    public void setIndicator3Min(String indicator3Min) {
        this.indicator3Min = indicator3Min;
    }

    public String getIndicator3Max() {
        return indicator3Max;
    }

    public void setIndicator3Max(String indicator3Max) {
        this.indicator3Max = indicator3Max;
    }

    public String getIndicator4Min() {
        return indicator4Min;
    }

    public void setIndicator4Min(String indicator4Min) {
        this.indicator4Min = indicator4Min;
    }

    public String getIndicator4Max() {
        return indicator4Max;
    }

    public void setIndicator4Max(String indicator4Max) {
        this.indicator4Max = indicator4Max;
    }

    public String getIndicator5Min() {
        return indicator5Min;
    }

    public void setIndicator5Min(String indicator5Min) {
        this.indicator5Min = indicator5Min;
    }

    public String getIndicator5Max() {
        return indicator5Max;
    }

    public void setIndicator5Max(String indicator5Max) {
        this.indicator5Max = indicator5Max;
    }

    public String getIndicator6Min() {
        return indicator6Min;
    }

    public void setIndicator6Min(String indicator6Min) {
        this.indicator6Min = indicator6Min;
    }

    public String getIndicator6Max() {
        return indicator6Max;
    }

    public void setIndicator6Max(String indicator6Max) {
        this.indicator6Max = indicator6Max;
    }

    public String getIndicator7Min() {
        return indicator7Min;
    }

    public void setIndicator7Min(String indicator7Min) {
        this.indicator7Min = indicator7Min;
    }

    public String getIndicator7Max() {
        return indicator7Max;
    }

    public void setIndicator7Max(String indicator7Max) {
        this.indicator7Max = indicator7Max;
    }

    public String getIndicator8Min() {
        return indicator8Min;
    }

    public void setIndicator8Min(String indicator8Min) {
        this.indicator8Min = indicator8Min;
    }

    public String getIndicator8Max() {
        return indicator8Max;
    }

    public void setIndicator8Max(String indicator8Max) {
        this.indicator8Max = indicator8Max;
    }

    public String getIndicator9Min() {
        return indicator9Min;
    }

    public void setIndicator9Min(String indicator9Min) {
        this.indicator9Min = indicator9Min;
    }

    public String getIndicator9Max() {
        return indicator9Max;
    }

    public void setIndicator9Max(String indicator9Max) {
        this.indicator9Max = indicator9Max;
    }

    public String getIndicator10Min() {
        return indicator10Min;
    }

    public void setIndicator10Min(String indicator10Min) {
        this.indicator10Min = indicator10Min;
    }

    public String getIndicator10Max() {
        return indicator10Max;
    }

    public void setIndicator10Max(String indicator10Max) {
        this.indicator10Max = indicator10Max;
    }

    public String getIndicator11Min() {
        return indicator11Min;
    }

    public void setIndicator11Min(String indicator11Min) {
        this.indicator11Min = indicator11Min;
    }

    public String getIndicator11Max() {
        return indicator11Max;
    }

    public void setIndicator11Max(String indicator11Max) {
        this.indicator11Max = indicator11Max;
    }

    public String getIndicator12Min() {
        return indicator12Min;
    }

    public void setIndicator12Min(String indicator12Min) {
        this.indicator12Min = indicator12Min;
    }

    public String getIndicator12Max() {
        return indicator12Max;
    }

    public void setIndicator12Max(String indicator12Max) {
        this.indicator12Max = indicator12Max;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getOriginNameE() {
		return originNameE;
	}

	public void setOriginNameE(String originNameE) {
		this.originNameE = originNameE;
	}

	public static CrudeOilForm convertVoToForm(CrudeOilInfoVO info) {
        CrudeOilForm cof = new CrudeOilForm();
        if(info != null){
        	cof.setPropertyId(info.getId());
            cof.setDesc(info.getDesc());
            cof.setCnName(info.getCrudeNameC());
            cof.setName(info.getCrudeNameE());
            cof.setArea(info.getOriginAreaId());
            cof.setCrudeOilId(info.getCatagoryId());
            cof.setOrigin(info.getOriginId().toString());
            cof.setIndicator1Min(info.getApi().toString());
            cof.setIndicator2Min(info.getSulphur().toString());
            cof.setIndicator3Min(info.getAcidity().toString());
            cof.setIndicator4Min(info.getCarbonResidue());
            cof.setIndicator5Min(info.getNickel());
            cof.setIndicator6Min(info.getVanadium());
            cof.setIndicator7Min(info.getYield());
            
            cof.setOriginName(info.getOriginNameC());
            cof.setOriginNameE(info.getOriginNameE());
        }
        return cof;
    }
}