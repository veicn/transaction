package com.sinochem.crude.trade.listed.model.vo;

import com.sinochem.crude.trade.goods.remote.CrudeOilInfoVO;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 原油产品信息VO
 *
 * @author
 */
public class CrudeOilInfoShowVO implements Serializable {

    private static final long serialVersionUID = 5800174074974902769L;

    /**
     * t_crude_oil.ID自增主键
     */
    private Long id;

    /**
     * t_crude_oil.CRUDE_NAME_E原油英文名
     */
    private String crudeNameE;

	/**
	 * t_crude_oil.CRUDE_NAME_E_UO原油英文名首字母大写
	 */
	private String crudeNameEUp;
    
    /**
     * t_crude_oil.CRUDE_NAME_C中文
     */
    private String crudeNameC;

    /**
     * t_crude_oil.ORIGIN_ID产地id
     */
    private Long originId;

    /**
     * t_crude_origin.ORIGIN_NAME_E产地英文名
     */
    private String originNameE;

    /**
     * t_crude_origin.ORIGIN_NAME_C产地中文名
     */
    private String originNameC;

    /**
     * t_crude_quality.API度
     */
    private String api;

    /**
     * t_crude_quality.SULPHUR硫含量,单位%wt，质量百分比
     */
    private String sulphur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrudeNameE() {
        return crudeNameE;
    }

    public void setCrudeNameE(String crudeNameE) {
        this.crudeNameE = crudeNameE;
    }

    public String getCrudeNameEUp() {
		return crudeNameEUp;
	}

	public void setCrudeNameEUp(String crudeNameEUp) {
		this.crudeNameEUp = crudeNameEUp;
	}

	public String getCrudeNameC() {
        return crudeNameC;
    }

    public void setCrudeNameC(String crudeNameC) {
        this.crudeNameC = crudeNameC;
    }

    public String getOriginNameE() {
        return originNameE;
    }

    public void setOriginNameE(String originNameE) {
        this.originNameE = originNameE;
    }

    public String getOriginNameC() {
        return originNameC;
    }

    public void setOriginNameC(String originNameC) {
        this.originNameC = originNameC;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getSulphur() {
        return sulphur;
    }

    public void setSulphur(String sulphur) {
        this.sulphur = sulphur;
    }

    public Long getOriginId() {
        return originId;
    }

    public void setOriginId(Long originId) {
        this.originId = originId;
    }

    public static CrudeOilInfoShowVO convertToShowVO(CrudeOilInfoVO vo){
        CrudeOilInfoShowVO cos = new CrudeOilInfoShowVO();
        if(vo != null){
            cos.setId(vo.getId());
            cos.setCrudeNameC(vo.getCrudeNameC());
            cos.setCrudeNameEUp(vo.getCrudeNameEUp());
            cos.setCrudeNameE(vo.getCrudeNameE());
            cos.setOriginId(vo.getOriginId());
            cos.setOriginNameC(vo.getOriginNameC());
            cos.setOriginNameE(vo.getOriginNameE());
            DecimalFormat one = new DecimalFormat("######0.0");
            //DecimalFormat two = new DecimalFormat("######0.00");
            BigDecimal b = new BigDecimal(vo.getApi().stripTrailingZeros().toPlainString());
            double doubleValue = b.doubleValue();
            if(doubleValue-(int)doubleValue<1e-10){
                cos.setApi(one.format(doubleValue)+"");
            }else{
                cos.setApi(doubleValue+"");
            }
            BigDecimal bd = new BigDecimal(vo.getSulphur().stripTrailingZeros().toPlainString());
            double sul = bd.doubleValue();

            if(sul-(int)sul<1e-10){
                cos.setSulphur(one.format(sul)+"");
            }else{
                cos.setSulphur(sul+"");
            }
        }
        return cos;
    }
}

