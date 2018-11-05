package com.sinochem.crude.trade.order.model.vo;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.sinochem.crude.trade.order.domain.CrudeOilLongTermContractPlan;
import com.sinochem.crude.trade.order.domain.CrudeOilResource;

/**
 * CrudeOilLongTernContractPlan的VO
 * @author Yichen Zhao
 * date: 20180129
 */
public class CrudeOilLongTermContractPlanVO {

    /**
     * PK
     */
    private Long id;

    /**
     * 对应的长约id
     */
    private Long contractId;

    /**
     * 年月
     */
    private Integer date;
    /*2018年3月27日14:13:47 由于数据库中date字段是int类型，js-年月格式 与 jquery.validate.js中 dateISO 默认校验格式不一致 才新增此 dateStr字段做转换 */
    private Integer dateEnd;
    
    public Integer getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Integer dateEnd) {
		this.dateEnd = dateEnd;
	}

	private String dateStr;//开始年月
    /**
     * 结束年月
     */
    private String dateStr2;
    /**
     * 计价公式
     */
    private String valuationFormula;
    
    
    public String getValuationFormula() {
		return valuationFormula;
	}

	public void setValuationFormula(String valuationFormula) {
		this.valuationFormula = valuationFormula;
	}

	public String getDateStr2() {
		return dateStr2;
	}

	public void setDateStr2(String dateStr2) {
		this.dateStr2 = dateStr2;
	}

	public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    /**
     * 数量，等于num*rate
     */
    private BigDecimal num;
    private String numAsString;

    /**
     * 数量偏差,按照百分比
     */
    private Integer numFloat;
    private String numFloatAsString;

    /**
     * 汇率
     */
    private Long rate;
    private String rateAsString;

    /**
     * 计量数量
     */
    private Long meternum;
    private String meternumAsString;

    /**
     * 原油
     */
    private CrudeOilResourceVO crudeOilResource;

    /**
     * 原油id
     */
    private Long crudeOilId;

    public static void convertToCrudeOilLongTernContractPlanVO(
            CrudeOilLongTermContractPlan crudeOilLongTermContractPlan,
            CrudeOilLongTermContractPlanVO crudeOilLongTermContractPlanVO) {
        if (crudeOilLongTermContractPlan == null ||crudeOilLongTermContractPlanVO == null) {
            return;
        }

        crudeOilLongTermContractPlanVO.setId(crudeOilLongTermContractPlan.getId());
        crudeOilLongTermContractPlanVO.setContractId(crudeOilLongTermContractPlan.getContractId());
        crudeOilLongTermContractPlanVO.setDate(crudeOilLongTermContractPlan.getDate());
        /*2018年3月27日14:13:47 由于数据库中date字段是int类型，js-年月格式 与 jquery.validate.js中 dateISO 默认校验格式不一致 才新增此 dateStr字段做转换 */
        Integer date = crudeOilLongTermContractPlan.getDate();//开始年月
        Integer dateEnd = crudeOilLongTermContractPlan.getDateEnd();//结束年月
        
        crudeOilLongTermContractPlanVO.setDateStr(date.toString().substring(0,4) + "-" + date.toString().substring(4,6));
        crudeOilLongTermContractPlanVO.setDateStr2(dateEnd.toString().substring(0,4) + "-" + dateEnd.toString().substring(4,6));
        
        Long num = crudeOilLongTermContractPlan.getNum();
        if (num != null) {
            crudeOilLongTermContractPlanVO.setNum(new BigDecimal(num).divide(new BigDecimal(1000)));
            crudeOilLongTermContractPlanVO.setNumAsString(num.toString());
        }

        Integer numFloat = crudeOilLongTermContractPlan.getNumFloat();
        if (numFloat != null) {
            crudeOilLongTermContractPlanVO.setNumFloat(numFloat);
            crudeOilLongTermContractPlanVO.setNumFloatAsString(numFloat.toString());
        }

        Long rate = crudeOilLongTermContractPlan.getRate();
        if (rate != null) {
            crudeOilLongTermContractPlanVO.setRate(rate);
            crudeOilLongTermContractPlanVO.setRateAsString(rate.toString());
        }

        Long meternum = crudeOilLongTermContractPlan.getMeternum();
        if (meternum != null) {
            crudeOilLongTermContractPlanVO.setMeternum(meternum);
            crudeOilLongTermContractPlanVO.setMeternumAsString(meternum.toString());
        }

        CrudeOilResource crudeOilResource = crudeOilLongTermContractPlan.getCrudeOilResource();
        if (crudeOilResource != null) {
            CrudeOilResourceVO crudeOilResourceVO = new CrudeOilResourceVO();
            CrudeOilResourceVO.convertToCrudeOilResourceVO(crudeOilResource, crudeOilResourceVO);
            crudeOilLongTermContractPlanVO.setCrudeOilResource(crudeOilResourceVO);
        }

        crudeOilLongTermContractPlanVO.setCrudeOilId(crudeOilLongTermContractPlan.getCrudeOilId());
        crudeOilLongTermContractPlanVO.setValuationFormula(crudeOilLongTermContractPlan.getValuationFormula());
    }

    public static void convertToCrudeOilLongTermContractPlan(
            CrudeOilLongTermContractPlanVO crudeOilLongTermContractPlanVO,
            CrudeOilLongTermContractPlan crudeOilLongTermContractPlan) {
        if (crudeOilLongTermContractPlanVO == null || crudeOilLongTermContractPlan == null) {
            return;
        }

        crudeOilLongTermContractPlan.setId(crudeOilLongTermContractPlanVO.getId());
        crudeOilLongTermContractPlan.setContractId(crudeOilLongTermContractPlanVO.getContractId());
        //crudeOilLongTermContractPlan.setDate(crudeOilLongTermContractPlanVO.getDate());
        /*2018年3月27日14:13:47 由于数据库中date字段是int类型，js-年月格式 与 jquery.validate.js中 dateISO 默认校验格式不一致 才新增此 dateStr字段做转换 */
        if(StringUtils.isNotBlank(crudeOilLongTermContractPlanVO.getDateStr())){
            crudeOilLongTermContractPlan.setDate(Integer.valueOf(crudeOilLongTermContractPlanVO.getDateStr().replace("-","")));
        }
        if(StringUtils.isNotBlank(crudeOilLongTermContractPlanVO.getDateStr2())){
        	crudeOilLongTermContractPlan.setDateEnd(Integer.valueOf(crudeOilLongTermContractPlanVO.getDateStr2().replace("-","")));
        }
        crudeOilLongTermContractPlan.setNum(crudeOilLongTermContractPlanVO.getNum().multiply(new BigDecimal(1000)).longValue());
        crudeOilLongTermContractPlan.setNumFloat(crudeOilLongTermContractPlanVO.getNumFloat());
        crudeOilLongTermContractPlan.setRate(crudeOilLongTermContractPlanVO.getRate());
        crudeOilLongTermContractPlan.setMeternum(crudeOilLongTermContractPlanVO.getMeternum());

        CrudeOilResourceVO crudeOilResourceVO = crudeOilLongTermContractPlanVO.getCrudeOilResource();
        if (crudeOilLongTermContractPlanVO != null) {
            CrudeOilResource crudeOilResource = new CrudeOilResource();
            CrudeOilResourceVO.convertToCrudeOilResource(crudeOilResourceVO, crudeOilResource);
            crudeOilLongTermContractPlan.setCrudeOilResource(crudeOilResource);
        }
        crudeOilLongTermContractPlan.setValuationFormula(crudeOilLongTermContractPlanVO.getValuationFormula());
        crudeOilLongTermContractPlan.setCrudeOilId(crudeOilLongTermContractPlanVO.getCrudeOilId());
    }

    /** getters and setters */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public Integer getNumFloat() {
        return numFloat;
    }

    public void setNumFloat(Integer numFloat) {
        this.numFloat = numFloat;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public Long getMeternum() {
        return meternum;
    }

    public void setMeternum(Long meternum) {
        this.meternum = meternum;
    }

    public CrudeOilResourceVO getCrudeOilResource() {
        return crudeOilResource;
    }

    public void setCrudeOilResource(CrudeOilResourceVO crudeOilResource) {
        this.crudeOilResource = crudeOilResource;
    }

    public Long getCrudeOilId() {
        return crudeOilId;
    }

    public void setCrudeOilId(Long crudeOilId) {
        this.crudeOilId = crudeOilId;
    }

    public String getNumAsString() {
        return numAsString;
    }

    public void setNumAsString(String numAsString) {
        this.numAsString = numAsString;
    }

    public String getNumFloatAsString() {
        return numFloatAsString;
    }

    public void setNumFloatAsString(String numFloatAsString) {
        this.numFloatAsString = numFloatAsString;
    }

    public String getRateAsString() {
        return rateAsString;
    }

    public void setRateAsString(String rateAsString) {
        this.rateAsString = rateAsString;
    }

    public String getMeternumAsString() {
        return meternumAsString;
    }

    public void setMeternumAsString(String meternumAsString) {
        this.meternumAsString = meternumAsString;
    }
}
