package com.sinochem.crude.trade.listed.model.vo;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.enums.*;
import com.sinochem.crude.trade.listed.helper.DictUtils;
import com.sinochem.crude.trade.listed.model.result.DemandJoinResult;
import com.sinochem.it.b2b.common.utils.DateUtil;
import com.sinochem.it.b2b.common.utils.VisitorLocale;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * DemandResult的VO，目前该VO暂不能通用，需要比对字段
 * @author Yichen Zhao
 * date: 20180110
 */
public class DemandJoinVO {

    /**
     * id
     */
    private String id;

    private String uuid;

    private String crudeOilOptions;

    /**
     * 桶(万桶)
     */
    private String num;
    
    /**
     * 桶
     */
    private String numBBL;

    /**
     * 溢短装
     */
    private String numfloat;
    
    private String tradeItem;

    private String valuationBase;

    private Integer valuationProidType;

    private String valuationPeriodTypeContent;
    
    /**
     * 计量方式
     */
    private String measureMode;

    private String shipmentPort;

    private String shipmentStartTime;

    private String shipmentEndTime;

    private String dischargePort;

    private String dischargeStartTime;

    private String dischargeEndTime;

    private String purchaseType;

    private String agio;

    private String payItem;

    private String productOilKind;

    private String productOilSpecs;

    private String valuationPeriodStart;

    private String valuationPeriodEnd;

    private String region;

    private String stopBidTime;

    /**
     * 是否逾期  true 逾期 false 未逾期
     */
    private boolean overdue;

    private String tenderOutTime;

    private String pubDate;

    /**
     * 创建人
     */
    private Long creater;
    
    /**
     * 需求发布类型，1-按油种，2-按性质
     */
    private Integer publishType;

    /**
     * 发布范围（0-公开发布， 1-定向发布）
     */
    private Integer specified;

    /**
     * 需求发布方企业名称
     */
    private Long bEpMemberId;
    private String bEnterpriseName;
    
    /**
     * 销售发布方企业名称
     */
    private Long pEpMemberId;
    private String pEnterpriseName;

    /**
     * 合约种类
     */
    private String contractKind;
    
    /**
     * 计价公式
     */
    private String valuationFormula;
    
    /**
     * 信用条款
     */
    private String authItem;
    
    /**
     * 交易类型（投标、询价）
     */
    private String dealType;
    
    /**
     * 允许装卸货时间
     */
    private String loadAndDischargePermittedTimespan;
    
    /**
     * 将DemandJoinResult转换为VO
     */
    public static DemandJoinVO convertToVO(DemandJoinResult demandJoinResult) {
        if (demandJoinResult == null) {
            return null;
        }

        DemandJoinVO demandJoinVO = new DemandJoinVO();

        Long id = demandJoinResult.getId();
        if (id != null) {
            demandJoinVO.setId(id.toString());
        }

        demandJoinVO.setUuid(demandJoinResult.getUuid());
        demandJoinVO.setCrudeOilOptions(demandJoinResult.getCrudeOilOptions());

        DecimalFormat df = new DecimalFormat("#0");
       
        Long num = demandJoinResult.getNum();
        if (num != null) {
            demandJoinVO.setNum(num.toString());
            double numBBL = num / (Constant.DEMAND_NUM_AMPLIFY_SCALE);
            demandJoinVO.setNumBBL(df.format(numBBL));
        }

        Integer tradeItem = demandJoinResult.getTradeItem();
        if (tradeItem != null) {
            Map<Object, String> tradeItemMap = DictUtils.getTradeItemMap();
            demandJoinVO.setTradeItem(tradeItemMap.get(tradeItem));
        }
        demandJoinVO.setStopBidTime(DateUtil.formatDate(demandJoinResult.getStopBidTime()));

        String valuationBase = demandJoinResult.getValuationBase();
        if (!StringUtil.isEmpty(valuationBase)) {
            Map<Object, String> valuationBaseMap = DictUtils.getValuationBaseMap();
            demandJoinVO.setValuationBase(valuationBaseMap.get(valuationBase));
        }

        Integer valuationPeriodType = demandJoinResult.getValuationProidType();
        if (valuationPeriodType != null) {
            Map<Object, String> valuationPeriodTypeMap = DictUtils.getValuationProidTypeMap();
            demandJoinVO.setValuationProidType(demandJoinResult.getValuationProidType());
            demandJoinVO.setValuationPeriodTypeContent(valuationPeriodTypeMap.get(valuationPeriodType));
        }

        demandJoinVO.setShipmentPort(demandJoinResult.getShipmentPort());

        Date shipmentStartTime = demandJoinResult.getShipmentStartTime();
        if (shipmentStartTime != null) {
            demandJoinVO.setShipmentStartTime(DateUtil.formatDate(shipmentStartTime));
        }

        Date shipmentEndTime = demandJoinResult.getShipmentEndTime();
        if (shipmentEndTime != null) {
            demandJoinVO.setShipmentEndTime(DateUtil.formatDate(shipmentEndTime));
        }

        Map dischargePortMap = DictUtils.getUnLoadPortMap();
        String dischargePort = demandJoinResult.getDischargePort();
        String[][] dischargePortDictionary = (String[][]) dischargePortMap.get(dischargePort);

        if (dischargePortDictionary != null) {
            demandJoinVO.setDischargePort(VisitorLocale.getByCurrentLanguage(dischargePortDictionary));
        } else {
            demandJoinVO.setDischargePort(dischargePort);
        }

        Date dischargeStartTime = demandJoinResult.getDischargeStartTime();
        if (dischargeStartTime != null) {
            demandJoinVO.setDischargeStartTime(DateUtil.formatDate(dischargeStartTime));
        }

        Date dischargeEndTime = demandJoinResult.getDischargeEndTime();
        if (dischargeEndTime != null) {
            demandJoinVO.setDischargeEndTime(DateUtil.formatDate(dischargeEndTime));
        }

        Integer purchaseType = demandJoinResult.getPurchaseType();
        if (purchaseType != null) {
            PurchaseType purchaseTypeEnum = PurchaseType.getPurchaseTypeByCode(purchaseType);
            if (purchaseTypeEnum != null) {
                demandJoinVO.setPurchaseType(purchaseTypeEnum.getName());
            }
        }

        Long agio = demandJoinResult.getAgio();
        if (agio != null) {
        	DecimalFormat df2 = new DecimalFormat("#0.000");
            demandJoinVO.setAgio(df2.format(agio / 1000.0));
        }

        String payItem = demandJoinResult.getPayItem();
        if (payItem != null) {
            demandJoinVO.setPayItem(payItem);
        }

        Integer productOilKind = demandJoinResult.getProductOilKind();
        if (productOilKind != null) {
            String  ProductName= DictUtils.getProductOilKind().get(productOilKind);
            if (ProductName != null) {
                demandJoinVO.setProductOilKind(ProductName);
            }
        }

        Integer productOilSpecs = demandJoinResult.getProductOilSpecs();
        if (productOilSpecs != null) {
            String  productOilSpecsEnum = DictUtils.getProductOilSpecs().get(productOilSpecs);
            if (productOilSpecsEnum != null) {
                demandJoinVO.setProductOilSpecs(productOilSpecsEnum);
            }
        }

        Date valuationPeriodStart = demandJoinResult.getValuationProidStart();
        if (valuationPeriodStart != null) {
            demandJoinVO.setValuationPeriodStart(DateUtil.formatDate(valuationPeriodStart));
        }

        Date valuationPeriodEnd = demandJoinResult.getValuationProidEnd();
        if (valuationPeriodEnd != null) {
            demandJoinVO.setValuationPeriodEnd(DateUtil.formatDate(valuationPeriodEnd));
        }

        Integer region = demandJoinResult.getRegion();
        if (region != null) {
            String  productOilRegion = DictUtils.getProductOilRegion().get(region);
            if (productOilRegion != null) {
                demandJoinVO.setRegion(productOilRegion);
            }
        }

        Date tenderOutTime = demandJoinResult.getTenderOutTime();
        if (tenderOutTime != null) {
            demandJoinVO.setTenderOutTime(DateUtil.formatDate(tenderOutTime));
        }

        Date pubDate = demandJoinResult.getPubDate();
        if (pubDate != null) {
            demandJoinVO.setPubDate(DateUtil.formatDate(pubDate));
        }
        
        Integer publishType = demandJoinResult.getPublishType();
        if (publishType != null) {
            demandJoinVO.setPublishType(publishType);
        }

        demandJoinVO.setCreater(demandJoinResult.getCreater());
        demandJoinVO.setSpecified(demandJoinResult.getSpecified());
        demandJoinVO.setbEpMemberId(demandJoinResult.getBeMemberId());
        demandJoinVO.setbEnterpriseName(demandJoinResult.getbEnterpriseName());
        demandJoinVO.setpEpMemberId(demandJoinResult.getPeMemberId());
        demandJoinVO.setpEnterpriseName(demandJoinResult.getpEnterpriseName());
        
        String contractKind = demandJoinResult.getContractKind();
        if (!StringUtil.isEmpty(contractKind)) {
            demandJoinVO.setContractKind(contractKind);
        }
        
        demandJoinVO.setValuationFormula(demandJoinResult.getValuationFormula());
        
        Integer authItem = demandJoinResult.getAuthItem();
        if (authItem != null) {
            Map<Object, String> creditItem = DictUtils.getCreditItem();
            demandJoinVO.setAuthItem(creditItem.get(authItem));
        }
        
        demandJoinVO.setDealType(demandJoinResult.getDealType());
        
        Integer measureMode = demandJoinResult.getMeasureMode();
        if (measureMode != null) {
            Map<Object, String> measureModeMap = DictUtils.getMeasureModeMap();
            demandJoinVO.setMeasureMode(measureModeMap.get(measureMode));
        }
        
        demandJoinVO.setNumfloat("+/- " + demandJoinResult.getNumfloat() + " %");
        
        demandJoinVO.setLoadAndDischargePermittedTimespan(demandJoinResult.getLoadAndDischargePermittedTimespan());
        
        return demandJoinVO;
    }

    /**getters and setters*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCrudeOilOptions() {
        return crudeOilOptions;
    }

    public void setCrudeOilOptions(String crudeOilOptions) {
        //油种名称转换逻辑
        crudeOilOptions =
                crudeOilOptions
                .replace("API度", VisitorLocale.getByCurrentLanguage(Constant.LISTED_0038))
                .replace("含硫量",VisitorLocale.getByCurrentLanguage(Constant.LISTED_0035));
        this.crudeOilOptions = crudeOilOptions;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
    
    public String getNumBBL() {
		return numBBL;
	}

	public void setNumBBL(String numBBL) {
		this.numBBL = numBBL;
	}

	public String getNumfloat() {
		return numfloat;
	}

	public void setNumfloat(String numfloat) {
		this.numfloat = numfloat;
	}

	public String getValuationBase() {
        return valuationBase;
    }

    public void setValuationBase(String valuationBase) {
        this.valuationBase = valuationBase;
    }

    public String getShipmentPort() {
        return shipmentPort;
    }

    public void setShipmentPort(String shipmentPort) {
        this.shipmentPort = shipmentPort;
    }

    public String getShipmentStartTime() {
        return shipmentStartTime;
    }

    public void setShipmentStartTime(String shipmentStartTime) {
        this.shipmentStartTime = shipmentStartTime;
    }

    public String getShipmentEndTime() {
        return shipmentEndTime;
    }

    public void setShipmentEndTime(String shipmentEndTime) {
        this.shipmentEndTime = shipmentEndTime;
    }

    public String getDischargeStartTime() {
        return dischargeStartTime;
    }

    public void setDischargeStartTime(String dischargeStartTime) {
        this.dischargeStartTime = dischargeStartTime;
    }

    public String getDischargeEndTime() {
        return dischargeEndTime;
    }

    public void setDischargeEndTime(String dischargeEndTime) {
        this.dischargeEndTime = dischargeEndTime;
    }
    
	public Integer getValuationProidType() {
		return valuationProidType;
	}

	public void setValuationProidType(Integer valuationProidType) {
		this.valuationProidType = valuationProidType;
	}

	public String getValuationPeriodTypeContent() {
		return valuationPeriodTypeContent;
	}

	public void setValuationPeriodTypeContent(String valuationPeriodTypeContent) {
		this.valuationPeriodTypeContent = valuationPeriodTypeContent;
	}

	public String getMeasureMode() {
		return measureMode;
	}

	public void setMeasureMode(String measureMode) {
		this.measureMode = measureMode;
	}

	public String getTradeItem() {
        return tradeItem;
    }

    public void setTradeItem(String tradeItem) {
        this.tradeItem = tradeItem;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getAgio() {
        return agio;
    }

    public void setAgio(String agio) {
        this.agio = agio;
    }

    public String getPayItem() {
        return payItem;
    }

    public void setPayItem(String payItem) {
        this.payItem = payItem;
    }

    public String getProductOilKind() {
        return productOilKind;
    }

    public void setProductOilKind(String productOilKind) {
        this.productOilKind = productOilKind;
    }

    public String getProductOilSpecs() {
        return productOilSpecs;
    }

    public void setProductOilSpecs(String productOilSpecs) {
        this.productOilSpecs = productOilSpecs;
    }

    public String getValuationPeriodStart() {
        return valuationPeriodStart;
    }

    public void setValuationPeriodStart(String valuationPeriodStart) {
        this.valuationPeriodStart = valuationPeriodStart;
    }

    public String getValuationPeriodEnd() {
        return valuationPeriodEnd;
    }

    public void setValuationPeriodEnd(String valuationPeriodEnd) {
        this.valuationPeriodEnd = valuationPeriodEnd;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStopBidTime() {
        return stopBidTime;
    }

    public void setStopBidTime(String stopBidTime) {
        this.stopBidTime = stopBidTime;
    }

    public String getTenderOutTime() {
        return tenderOutTime;
    }

    public void setTenderOutTime(String tenderOutTime) {
        this.tenderOutTime = tenderOutTime;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
    
    public Integer getPublishType() {
		return publishType;
	}

	public void setPublishType(Integer publishType) {
		this.publishType = publishType;
	}

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Integer getSpecified() {
        return specified;
    }

    public void setSpecified(Integer specified) {
        this.specified = specified;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

    public String getbEnterpriseName() {
        return bEnterpriseName;
    }

    public void setbEnterpriseName(String bEnterpriseName) {
        this.bEnterpriseName = bEnterpriseName;
    }
    
    public String getpEnterpriseName() {
        return pEnterpriseName;
    }

    public void setpEnterpriseName(String pEnterpriseName) {
        this.pEnterpriseName = pEnterpriseName;
    }

    public String getDischargePort() {
        return dischargePort;
    }

    public void setDischargePort(String dischargePort) {
        this.dischargePort = dischargePort;
    }

	public String getContractKind() {
		return contractKind;
	}

	public void setContractKind(String contractKind) {
		this.contractKind = contractKind;
	}

	public String getValuationFormula() {
		return valuationFormula;
	}

	public void setValuationFormula(String valuationFormula) {
		this.valuationFormula = valuationFormula;
	}

	public String getAuthItem() {
		return authItem;
	}

	public void setAuthItem(String authItem) {
		this.authItem = authItem;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getLoadAndDischargePermittedTimespan() {
		return loadAndDischargePermittedTimespan;
	}

	public void setLoadAndDischargePermittedTimespan(
			String loadAndDischargePermittedTimespan) {
		this.loadAndDischargePermittedTimespan = loadAndDischargePermittedTimespan;
	}

    public Long getbEpMemberId() {
        return bEpMemberId;
    }

    public void setbEpMemberId(Long bEpMemberId) {
        this.bEpMemberId = bEpMemberId;
    }

    public Long getpEpMemberId() {
        return pEpMemberId;
    }

    public void setpEpMemberId(Long pEpMemberId) {
        this.pEpMemberId = pEpMemberId;
    }
}
