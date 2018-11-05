package com.sinochem.crude.trade.order.model.form;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.order.domain.*;
import com.sinochem.crude.trade.order.domain.CrudeOilLongTermContractPlan;
import com.sinochem.crude.trade.order.model.vo.*;
import org.apache.commons.lang.StringUtils;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*订单form模型*/

/**
 * modified by Yichen Zhao on 20180124
 */
public class ContractForm extends ContractVO{

	/*原油信息*/
    private CrudeOilResourceVO crudeOilResource;

    /*成品油信息*/
    private ProductOilResourceVO productOilResource;

    //泊位信息
    private List<ContractShipBerthVO> contractShipBerthList = new ArrayList<>();

    //船务信息
    @Valid
    private ContractShipVO contractShip = new ContractShipVO();

    /*买家信息*/
    @Valid
    private ContractRelevanterVO buyerRelevanter = new ContractRelevanterVO();

    /*卖家信息*/
    @Valid
    private ContractRelevanterVO supplierRelevanter = new ContractRelevanterVO();

    /*中间商信息*/
    private ContractRelevanterVO traderRelevanter = new ContractRelevanterVO();

    //长协plans
    private List<CrudeOilLongTermContractPlanVO> plans = new ArrayList();

	/**
     * 供应商和贸易商信息
     */
    private List<ContractRelevanterVO> contractRelevanters = new ArrayList();

    /**
     * 前端保存更新接收数量
     */
    private String numStr;
    /**
     * 前端保存更新接收贴水
     */
    private String agioStr;


	/**
     *  转换为contract
     */
    public static void convertToContract(ContractForm contractForm, Contract contract){
        if (contractForm == null) {
            return;
        }

        contract.setId(contractForm.getId());

        Long agio = contractForm.getAgio();
        if (agio != null) {
            contract.setAgio(agio);
        }

        /*这里会在重构时改掉*/
        String agioStr = contractForm.getAgioStr();
        if (!StringUtil.isEmpty(agioStr)) {
            contract.setAgio(convertToLong(agioStr));
        }

        contract.setAuthItem(contractForm.getAuthItem());
        contract.setBiddingId(contractForm.getBiddingId());
        contract.setBusinessCheckOrg(contractForm.getBusinessCheckOrg());

        Boolean buySign = contractForm.getBuySign();
        if (buySign != null) {
            contract.setBuySign(buySign.booleanValue());
        }

        Boolean cancelFlag = contractForm.getCancelFlag();
        if (cancelFlag != null) {
            contract.setCancelFlag(cancelFlag.booleanValue());
        }

        contract.setContractType(contractForm.getContractType());
        contract.setCreater(contractForm.getCreater());
        contract.setCreateTime(contractForm.getCreateTime());
        contract.setDoubleSign(contractForm.getDoubleSign());
        contract.setExecutor(contractForm.getExecutor());
        contract.setExportConfFile(contractForm.getExportConfFile());
        contract.setMeterNum(contractForm.getMeterNum());
        contract.setMeterUnit(contractForm.getMeterUnit());
        contract.setNum(contractForm.getNum());
        
        contract.setFilePath(contractForm.getFilePath());
        contract.setOriginalName(contractForm.getOriginalName());
        contract.setIsSubmit(contractForm.getIsSubmit());
        
        /*这里会在重构时改掉*/
        String numStr = contractForm.getNumStr();
        if (!StringUtil.isEmpty(numStr)) {
            contract.setNum(convertToLong(numStr));
        }

        contract.setNumFloat(contractForm.getNumFloat());
        contract.setOrderNo(contractForm.getOrderNo());
        contract.setOrderStatus(contractForm.getOrderStatus());
        contract.setOtherItem(contractForm.getOtherItem());
        contract.setPayItem(contractForm.getPayItem());
        contract.setPayItemJSON(contractForm.getPayItemJSON());

        contract.setPayTime(contractForm.getPayTime());
        contract.setPayType(contractForm.getPayType());
        contract.setPrice(contractForm.getPrice());
        contract.setPurchaseMode(contractForm.getPurchaseMode());
        contract.setPurchaseType(contractForm.getPurchaseType());
        contract.setRate(contractForm.getRate());
        contract.setReferenceId(contractForm.getReferenceId());
        contract.setRemark(contractForm.getRemark());

        Boolean sellerSign = contractForm.getSellerSign();
        if (sellerSign != null) {
            contract.setSellerSign(sellerSign);
        }

        contract.setStopBidTime(contractForm.getStopBidTime());
        contract.setTenderOutTime(contractForm.getTenderOutTime());
        contract.setToken(contractForm.getToken());
        contract.setTradeItem(contractForm.getTradeItem());

        contract.setUnit(contractForm.getUnit());
        contract.setUnitPrice(contractForm.getUnitPrice());
        contract.setUpdater(contractForm.getUpdater());
        contract.setUpdateTime(contractForm.getUpdateTime());
        contract.setUuid(contractForm.getUuid());
        contract.setValuationBase(contractForm.getValuationBase());
        contract.setValuationFormula(contractForm.getValuationFormula());
        contract.setValuationProidStart(contractForm.getValuationProidStart());
        contract.setValuationProidEnd(contractForm.getValuationProidEnd());
        contract.setValuationProidType(contractForm.getValuationProidType());
        contract.setVersion(contractForm.getVersion());
        contract.setValuationFormulaJson(contractForm.getValuationFormulaJson());
        contract.setInspectionFeeSharingType(contractForm.getInspectionFeeSharingType());

        ContractShipVO contractShipVO = contractForm.getContractShip();
        if (contractShipVO != null) {
            ContractShip contractShip = new ContractShip();
            ContractShipVO.convertToContractShip(contractShipVO, contractShip);

            List<ContractShipBerthVO> contractShipBerthVOList = contractForm.getContractShipBerthList();
            if (contractShipBerthVOList != null && contractShipBerthVOList.size() != 0) {
                List<ContractShipBerth> contractShipBerthList = new ArrayList<>();

                for (ContractShipBerthVO contractShipBerthVO : contractShipBerthVOList) {
                    ContractShipBerth contractShipBerth = new ContractShipBerth();
                    ContractShipBerthVO.convertToContractShipBerth(contractShipBerthVO, contractShipBerth);
                    contractShipBerthList.add(contractShipBerth);
                }

                contractShip.setContractShipBerths(contractShipBerthList);
            }

            contract.setContractShip(contractShip);
        }

        ContractRelevanterVO buyerRelevanterVO = contractForm.getBuyerRelevanter();
        if (buyerRelevanterVO != null) {
            ContractBuyer buyerRelevanter = new ContractBuyer();
            ContractRelevanterVO.convertToContractRelevanter(buyerRelevanterVO, buyerRelevanter);
            contract.setContractBuyer(buyerRelevanter);
        }

        ContractRelevanterVO supplierRelevanterVO = contractForm.getSupplierRelevanter();
        if (supplierRelevanterVO != null) {
            ContractSupplier supplierRelevanter = new ContractSupplier();
            ContractRelevanterVO.convertToContractRelevanter(supplierRelevanterVO, supplierRelevanter);
            contract.setContractSupplier(supplierRelevanter);
        }

        ContractRelevanterVO traderRelevanterVO = contractForm.getTraderRelevanter();
        if (traderRelevanterVO != null) {
            ContractTrader traderRelevanter = new ContractTrader();
            ContractRelevanterVO.convertToContractRelevanter(traderRelevanterVO, traderRelevanter);
            contract.setContractTrader(traderRelevanter);
        }
    }

    /**
     * 数量？？
     * @param b1
     * @return
     */
    private static Long convertToLong(BigDecimal b1){
        return b1 == null?null:b1.multiply(new BigDecimal(1000)).longValue();
    }

    /**
     * 数量？？
     * @param b1
     * @return
     */
    private static Long convertToLong(String b1){
        return StringUtils.isBlank(b1)?null:new BigDecimal(b1.trim()).multiply(new BigDecimal(1000)).longValue();
    }

    /**
     * 贴水？？
     * @param b1
     * @return
     */
    private static Long convertToDiscount(String b1) {
        return StringUtils.isBlank(b1)?null:new BigDecimal(b1.trim()).multiply(new BigDecimal(100000)).longValue();
    }

    public CrudeOilResourceVO getCrudeOilResource() {
        return crudeOilResource;
    }

    public void setCrudeOilResource(CrudeOilResourceVO crudeOilResource) {
        this.crudeOilResource = crudeOilResource;
    }

    public ProductOilResourceVO getProductOilResource() {
        return productOilResource;
    }

    public void setProductOilResource(ProductOilResourceVO productOilResource) {
        this.productOilResource = productOilResource;
    }

    public List<ContractShipBerthVO> getContractShipBerthList() {
        return contractShipBerthList;
    }

    public void setContractShipBerthList(List<ContractShipBerthVO> contractShipBerthList) {
        this.contractShipBerthList = contractShipBerthList;
    }

    public ContractShipVO getContractShip() {
        return contractShip;
    }

    public void setContractShip(ContractShipVO contractShip) {
        this.contractShip = contractShip;
    }

    public ContractRelevanterVO getBuyerRelevanter() {
        return buyerRelevanter;
    }

    public void setBuyerRelevanter(ContractRelevanterVO buyerRelevanter) {
        this.buyerRelevanter = buyerRelevanter;
    }

    public ContractRelevanterVO getSupplierRelevanter() {
        return supplierRelevanter;
    }

    public void setSupplierRelevanter(ContractRelevanterVO supplierRelevanter) {
        this.supplierRelevanter = supplierRelevanter;
    }

    public ContractRelevanterVO getTraderRelevanter() {
        return traderRelevanter;
    }

    public void setTraderRelevanter(ContractRelevanterVO traderRelevanter) {
        this.traderRelevanter = traderRelevanter;
    }

    public List<CrudeOilLongTermContractPlanVO> getPlans() {
        return plans;
    }

    public void setPlans(List<CrudeOilLongTermContractPlanVO> plans) {
        this.plans = plans;
    }

    public List<ContractRelevanterVO> getContractRelevanters() {
        return contractRelevanters;
    }

    public void setContractRelevanters(List<ContractRelevanterVO> contractRelevanters) {
        this.contractRelevanters = contractRelevanters;
    }

    public String getNumStr() {
        return numStr;
    }

    public String getAgioStr() {
        return agioStr;
    }

    public void setNumStr(String numStr) {
        this.numStr = numStr;
        this.setNum(convertToLong(numStr));
    }

    public void setAgioStr(String agioStr) {
        this.agioStr = agioStr;
        this.setAgio(convertToLong(agioStr));
    }
}
