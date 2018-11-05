package com.sinochem.crude.trade.transaction.service.impl;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.blockchain.enums.AppointStatusEnums;
import com.sinochem.crude.trade.common.enums.TradeTermEnum;
import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.transaction.enums.*;
import com.sinochem.crude.trade.transaction.model.vo.ProductSpecificationVO;
import com.sinochem.crude.trade.transaction.model.vo.ShipBerthVO;
import com.sinochem.crude.trade.transaction.model.vo.ShipPortVO;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * DictionaryService的实现，后期若做字典可更换实现类
 * 该实现类线程安全
 * @author Yichen Zhao
 * date: 20180308
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    private HashMap<String, DictionaryVO> biddingSheetStatusMap;
    private HashMap<String, DictionaryVO> contractSheetStatusMap;
    private HashMap<String, DictionaryVO> currencyMap;
    private HashMap<String, DictionaryVO> exportTypeMap;
    private HashMap<String, DictionaryVO> paymentTermMap;
    private HashMap<String, DictionaryVO> priceRegionMap;
    private HashMap<String, DictionaryVO> priceSourceMap;
    private HashMap<String, DictionaryVO> pricingBenchmarkMap;
    private HashMap<String, DictionaryVO> priceRegionMap2;
    private HashMap<String, DictionaryVO> priceSourceMap2;
    private HashMap<String, DictionaryVO> pricingBenchmarkMap2;
    private HashMap<String, DictionaryVO> pricingPeriodMap;
    private HashMap<String, DictionaryVO> pricingUnitMap;
    private HashMap<String, DictionaryVO> inspetionMap;
    private HashMap<String, DictionaryVO> quantityUnitMap;
    private HashMap<String, DictionaryVO> productCategoryMap;
    private HashMap<String, DictionaryVO> productVarietyMap;
    private HashMap<String, ProductSpecificationVO> productSpecificationMap;
    private HashMap<String, DictionaryVO> saleSheetStatusMap;
    private HashMap<String, DictionaryVO> demandSheetStatusMap;
    private HashMap<String, DictionaryVO> sheetFlagMap;
    private HashMap<String, DictionaryVO> saleTypeMap;
    private HashMap<String, DictionaryVO> demandTypeMap;
    private HashMap<String, DictionaryVO> productSourceMap;
    private HashMap<String, DictionaryVO> settlementQuantityMap;
    private HashMap<String, DictionaryVO> shipPortTypeMap;
    private HashMap<String, DictionaryVO> transportModeMap;
    private HashMap<String, DictionaryVO> tradeTermMap;
    private HashMap<String, DictionaryVO> saleSheetQueryOrderMap;
    private HashMap<String, DictionaryVO> demandSheetQueryOrderMap;
    private HashMap<String, DictionaryVO> countryMap;
    private HashMap<String, DictionaryVO> loadingPortCountryMap;
    private HashMap<String, DictionaryVO> loadingPortMap;
    private HashMap<String, DictionaryVO> dischargePortCountryMap;
    private HashMap<String, DictionaryVO> dischargePortMap;
    private HashMap<String, ShipPortVO> shipPortMap;
    private HashMap<String, ShipBerthVO> shipBerthMap;
    private HashMap<String, DictionaryVO> demurrageRateMap;
    private HashMap<String, DictionaryVO> appointStatusMap;

    @Override
    public HashMap<String, DictionaryVO> biddingSheetStatusMap() {
        if (this.biddingSheetStatusMap != null) {
            return (HashMap<String, DictionaryVO>) this.biddingSheetStatusMap.clone();
        }

        HashMap<String, DictionaryVO> biddingSheetStatusMap = new LinkedHashMap<>();
        for (BiddingSheetStatusEnum biddingSheetStatusEnum : BiddingSheetStatusEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    biddingSheetStatusEnum.getCode(),
                    biddingSheetStatusEnum.getZhName(),
                    biddingSheetStatusEnum.getEnName()
            );

            biddingSheetStatusMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.biddingSheetStatusMap = biddingSheetStatusMap;
        return (HashMap<String, DictionaryVO>) this.biddingSheetStatusMap.clone();
    }

    @Override
    public DictionaryVO getBiddingSheetStatus(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return biddingSheetStatusMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> contractSheetStatusMap() {
        if (this.contractSheetStatusMap != null) {
            return (HashMap<String, DictionaryVO>) this.contractSheetStatusMap.clone();
        }

        HashMap<String, DictionaryVO> contractSheetMap = new LinkedHashMap<>();
        for (ContractSheetStatusEnum contractSheetStatusEnum : ContractSheetStatusEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    contractSheetStatusEnum.getCode(),
                    contractSheetStatusEnum.getZhName(),
                    contractSheetStatusEnum.getEnName()
            );

            contractSheetMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.contractSheetStatusMap = contractSheetMap;
        return (HashMap<String, DictionaryVO>) this.contractSheetStatusMap.clone();
    }

    @Override
    public DictionaryVO getContractSheetStatus(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return contractSheetStatusMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> currencyMap() {
        if (this.currencyMap != null) {
            return (HashMap<String, DictionaryVO>) this.currencyMap.clone();
        }

        HashMap<String, DictionaryVO> currencyMap = new LinkedHashMap<>();
        for (CurrencyEnum currencyEnum : CurrencyEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    currencyEnum.getCode(),
                    currencyEnum.getZhName(),
                    currencyEnum.getEnName()
            );

            currencyMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.currencyMap = currencyMap;
        return (HashMap<String, DictionaryVO>) this.currencyMap.clone();
    }

    @Override
    public DictionaryVO getCurrency(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return currencyMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> exportTypeMap() {
        if (this.exportTypeMap != null) {
            return (HashMap<String, DictionaryVO>) this.exportTypeMap.clone();
        }

        HashMap<String, DictionaryVO> exportTypeMap = new LinkedHashMap<>();
        for (ExportTypeEnum exportTypeEnum : ExportTypeEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    exportTypeEnum.getCode(),
                    exportTypeEnum.getZhName(),
                    exportTypeEnum.getEnName()
            );

            exportTypeMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.exportTypeMap = exportTypeMap;
        return (HashMap<String, DictionaryVO>) this.exportTypeMap.clone();
    }

    @Override
    public DictionaryVO getExportType(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return exportTypeMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> paymentTermMap() {
        if (this.paymentTermMap != null) {
            return (HashMap<String, DictionaryVO>) this.paymentTermMap.clone();
        }

        HashMap<String, DictionaryVO> paymentTermMap = new LinkedHashMap<>();
        for (PaymentTermEnum paymentTermEnum : PaymentTermEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    paymentTermEnum.getCode(),
                    paymentTermEnum.getZhName(),
                    paymentTermEnum.getEnName()
            );

            paymentTermMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.paymentTermMap = paymentTermMap;
        return (HashMap<String, DictionaryVO>) this.paymentTermMap.clone();
    }

    @Override
    public DictionaryVO getPaymentTerm(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return paymentTermMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> priceRegionMap() {
        if (this.priceRegionMap != null) {
            return (HashMap<String, DictionaryVO>) this.priceRegionMap.clone();
        }

        HashMap<String, DictionaryVO> priceRegionMap = new LinkedHashMap<>();
        for (PriceRegionEnum priceRegionEnum : PriceRegionEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    priceRegionEnum.getCode(),
                    priceRegionEnum.getZhName(),
                    priceRegionEnum.getEnName()
            );

            priceRegionMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.priceRegionMap = priceRegionMap;
        return (HashMap<String, DictionaryVO>) this.priceRegionMap.clone();
    }

    @Override
    public DictionaryVO getPriceRegion(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return priceRegionMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> priceSourceMap() {
        if (this.priceSourceMap != null) {
            return (HashMap<String, DictionaryVO>) this.priceSourceMap.clone();
        }

        HashMap<String, DictionaryVO> priceSourceMap = new LinkedHashMap<>();
        for (PriceSourceEnum priceSourceEnum : PriceSourceEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    priceSourceEnum.getCode(),
                    priceSourceEnum.getZhName(),
                    priceSourceEnum.getEnName()
            );

            priceSourceMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.priceSourceMap = priceSourceMap;
        return (HashMap<String, DictionaryVO>) this.priceSourceMap.clone();
    }

    @Override
    public DictionaryVO getPriceSource(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return priceSourceMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> pricingBenchmarkMap() {
        if (this.pricingBenchmarkMap != null) {
            return (HashMap<String, DictionaryVO>) this.pricingBenchmarkMap.clone();
        }

        HashMap<String, DictionaryVO> pricingBenchmarkMap = new LinkedHashMap<>();
        for (PricingBenchmarkEnum pricingBenchmarkEnum : PricingBenchmarkEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    pricingBenchmarkEnum.getCode(),
                    pricingBenchmarkEnum.getZhName(),
                    pricingBenchmarkEnum.getEnName()
            );

            pricingBenchmarkMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.pricingBenchmarkMap = pricingBenchmarkMap;
        return (HashMap<String, DictionaryVO>) this.pricingBenchmarkMap.clone();
    }

    @Override
    public DictionaryVO getPricingBenchmark(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return pricingBenchmarkMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> pricingPeriodMap() {
        if (this.pricingPeriodMap != null) {
            return (HashMap<String, DictionaryVO>) this.pricingPeriodMap.clone();
        }

        HashMap<String, DictionaryVO> pricingPeriodMap = new LinkedHashMap<>();
        for (PricingPeriodEnum pricingPeriodEnum : PricingPeriodEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    pricingPeriodEnum.getCode(),
                    pricingPeriodEnum.getZhName(),
                    pricingPeriodEnum.getEnName()
            );

            pricingPeriodMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.pricingPeriodMap = pricingPeriodMap;
        return (HashMap<String, DictionaryVO>) this.pricingPeriodMap.clone();
    }

    @Override
    public DictionaryVO getPricingPeriod(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return pricingPeriodMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> quantityUnitMap() {
        if (this.quantityUnitMap != null) {
            return (HashMap<String, DictionaryVO>) this.quantityUnitMap.clone();
        }

        HashMap<String, DictionaryVO> quantityUnitMap = new LinkedHashMap<>();
        for (UnitEnum unitEnum : UnitEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    unitEnum.getCode(),
                    unitEnum.getZhName(),
                    unitEnum.getEnName()
            );

            quantityUnitMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.quantityUnitMap = quantityUnitMap;
        return (HashMap<String, DictionaryVO>) this.quantityUnitMap.clone();
    }

    @Override
    public DictionaryVO getQuantityUnit(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return quantityUnitMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> pricingUnitMap() {
        if (this.pricingUnitMap != null) {
            return (HashMap<String, DictionaryVO>) this.pricingUnitMap.clone();
        }

        HashMap<String, DictionaryVO> pricingUnitMap = new LinkedHashMap<>();
        for (PricingUnitEnum pricingUnitEnum : PricingUnitEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    pricingUnitEnum.getCode(),
                    pricingUnitEnum.getZhName(),
                    pricingUnitEnum.getEnName()
            );

            pricingUnitMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.pricingUnitMap = pricingUnitMap;
        return (HashMap<String, DictionaryVO>) this.pricingUnitMap.clone();
    }

    @Override
    public DictionaryVO getPricingUnit(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return pricingUnitMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> productCategoryMap() {
        if (this.productCategoryMap != null) {
            return (HashMap<String, DictionaryVO>) this.productCategoryMap.clone();
        }

        HashMap<String, DictionaryVO> productCategoryMap = new LinkedHashMap<>();
        for (ProductCategoryEnum productCategoryEnum : ProductCategoryEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    productCategoryEnum.getCode(),
                    productCategoryEnum.getZhName(),
                    productCategoryEnum.getEnName()
            );

            productCategoryMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.productCategoryMap = productCategoryMap;
        return (HashMap<String, DictionaryVO>) this.productCategoryMap.clone();
    }

    @Override
    public DictionaryVO getProductCategory(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return productCategoryMap().get(code);
    }

    @Override
    public HashMap<String, ProductSpecificationVO> productSpecificationMap() {
        if (this.productSpecificationMap != null) {
            return (HashMap<String, ProductSpecificationVO>) this.productSpecificationMap.clone();
        }

        HashMap<String, ProductSpecificationVO> productSpecificationMap = new LinkedHashMap<>();
        for (ProductSpecificationEnum productSpecificationEnum : ProductSpecificationEnum.values()) {
            ProductSpecificationVO productSpecificationVO = new ProductSpecificationVO(
                    productSpecificationEnum.getCode(),
                    productSpecificationEnum.getZhName(),
                    productSpecificationEnum.getEnName(),
                    productSpecificationEnum.getProductCategoryCode()
            );

            productSpecificationMap.put(productSpecificationVO.getCode(), productSpecificationVO);
        }

        this.productSpecificationMap = productSpecificationMap;
        return (HashMap<String, ProductSpecificationVO>) this.productSpecificationMap.clone();
    }

    @Override
    public ProductSpecificationVO getProductSpecification(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return productSpecificationMap().get(code);
    }

    @Override
    public HashMap<String, ProductSpecificationVO> getProductSpecificationMapByCategoryCode(String categoryCode) {
    	  if (StringUtil.isEmpty(categoryCode)) {
              return null;
          }

    	  HashMap<String, ProductSpecificationVO> productSpecificationMap = this.productSpecificationMap();
    	  Iterator<Entry<String, ProductSpecificationVO>> iterator = productSpecificationMap.entrySet().iterator();
    	  while (iterator.hasNext()) {
    		  ProductSpecificationVO specificationVO = (ProductSpecificationVO) iterator.next().getValue();

              if (!categoryCode.equals(specificationVO.getProductCategoryCode())) {
                  iterator.remove();
              }
          }
    	
          return productSpecificationMap;
    }

    @Override
    public HashMap<String, DictionaryVO> productVarietyMap() {
        if (this.productVarietyMap != null) {
            return (HashMap<String, DictionaryVO>) this.productVarietyMap.clone();
        }

        HashMap<String, DictionaryVO> productVarietyMap = new LinkedHashMap<>();
        for (ProductVarietyEnum productVarietyEnum : ProductVarietyEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    productVarietyEnum.getCode(),
                    productVarietyEnum.getZhName(),
                    productVarietyEnum.getEnName()
            );

            productVarietyMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.productVarietyMap = productVarietyMap;
        return (HashMap<String, DictionaryVO>) this.productVarietyMap.clone();
    }

    @Override
    public DictionaryVO getProductVariety(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return productVarietyMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> saleSheetStatusMap() {
        if (this.saleSheetStatusMap != null) {
            return (HashMap<String, DictionaryVO>) this.saleSheetStatusMap.clone();
        }

        HashMap<String, DictionaryVO> saleSheetStatusMap = new LinkedHashMap<>();
        for (SaleSheetStatusEnum saleSheetStatusEnum : SaleSheetStatusEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    saleSheetStatusEnum.getCode(),
                    saleSheetStatusEnum.getZhName(),
                    saleSheetStatusEnum.getEnName()
            );

            saleSheetStatusMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.saleSheetStatusMap = saleSheetStatusMap;
        return (HashMap<String, DictionaryVO>) this.saleSheetStatusMap.clone();
    }

    @Override
    public DictionaryVO getSaleSheetStatus(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return saleSheetStatusMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> demandSheetStatusMap() {
        if (this.demandSheetStatusMap != null) {
            return (HashMap<String, DictionaryVO>) this.demandSheetStatusMap.clone();
        }

        HashMap<String, DictionaryVO> demandSheetStatusMap = new LinkedHashMap<>();
        for (DemandSheetStatusEnum demandSheetStatusEnum : DemandSheetStatusEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    demandSheetStatusEnum.getCode(),
                    demandSheetStatusEnum.getZhName(),
                    demandSheetStatusEnum.getEnName()
            );

            demandSheetStatusMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.demandSheetStatusMap = demandSheetStatusMap;
        return (HashMap<String, DictionaryVO>) this.demandSheetStatusMap.clone();
    }

    @Override
    public DictionaryVO getDemandSheetStatus(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return demandSheetStatusMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> saleTypeMap() {
        if (this.saleTypeMap != null) {
            return (HashMap<String, DictionaryVO>) this.saleTypeMap.clone();
        }

        HashMap<String, DictionaryVO> saleTypeMap = new LinkedHashMap<>();
        for (SaleTypeEnum saleTypeEnum : SaleTypeEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    saleTypeEnum.getCode(),
                    saleTypeEnum.getZhName(),
                    saleTypeEnum.getEnName()
            );

            saleTypeMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.saleTypeMap = saleTypeMap;
        return (HashMap<String, DictionaryVO>) this.saleTypeMap.clone();
    }


    @Override
    public DictionaryVO getSaleType(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return saleTypeMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> sheetFlagMap() {
        if (this.sheetFlagMap != null) {
            return (HashMap<String, DictionaryVO>) this.sheetFlagMap.clone();
        }

        HashMap<String, DictionaryVO> sheetFlagMap = new LinkedHashMap<>();
        for (SheetFlagEnum sheetFlagEnum : SheetFlagEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    sheetFlagEnum.getCode(),
                    sheetFlagEnum.getZhName(),
                    sheetFlagEnum.getEnName()
            );

            sheetFlagMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.sheetFlagMap = sheetFlagMap;
        return (HashMap<String, DictionaryVO>) this.sheetFlagMap.clone();
    }

    @Override
    public DictionaryVO getSheetFlag(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return sheetFlagMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> demandTypeMap() {
        if (this.demandTypeMap != null) {
            return (HashMap<String, DictionaryVO>) this.demandTypeMap.clone();
        }

        HashMap<String, DictionaryVO> demandTypeMap = new LinkedHashMap<>();
        for (DemandTypeEnum demandTypeEnum : DemandTypeEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    demandTypeEnum.getCode(),
                    demandTypeEnum.getZhName(),
                    demandTypeEnum.getEnName()
            );

            demandTypeMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.demandTypeMap = demandTypeMap;
        return (HashMap<String, DictionaryVO>) this.demandTypeMap.clone();
    }

    @Override
    public DictionaryVO getDemandType(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return demandTypeMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> productSourceMap() {
        if (this.productSourceMap != null) {
            return (HashMap<String, DictionaryVO>) this.productSourceMap.clone();
        }

        HashMap<String, DictionaryVO> productSourceMap = new LinkedHashMap<>();
        for (ProductSourceEnum productSourceEnum : ProductSourceEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    productSourceEnum.getCode(),
                    productSourceEnum.getZhName(),
                    productSourceEnum.getEnName()
            );

            productSourceMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.productSourceMap = productSourceMap;
        return (HashMap<String, DictionaryVO>) this.productSourceMap.clone();
    }

    @Override
    public DictionaryVO getProductSource(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return productSourceMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> settlementQuantityMap() {
        if (this.settlementQuantityMap != null) {
            return (HashMap<String, DictionaryVO>) this.settlementQuantityMap.clone();
        }

        HashMap<String, DictionaryVO> settlementQuantityMap = new LinkedHashMap<>();
        for (SettlementQuantityEnum settlementQuantityEnum : SettlementQuantityEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    settlementQuantityEnum.getCode(),
                    settlementQuantityEnum.getZhName(),
                    settlementQuantityEnum.getEnName()
            );

            settlementQuantityMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.settlementQuantityMap = settlementQuantityMap;
        return (HashMap<String, DictionaryVO>) this.settlementQuantityMap.clone();
    }

    @Override
    public DictionaryVO getSettlementQuantity(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return settlementQuantityMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> shipPortTypeMap() {
        if (this.shipPortTypeMap != null) {
            return (HashMap<String, DictionaryVO>) this.shipPortTypeMap.clone();
        }

        HashMap<String, DictionaryVO> shipPortTypeMap = new LinkedHashMap<>();
        for (ShipPortTypeEnum shipPortTypeEnum : ShipPortTypeEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    shipPortTypeEnum.getCode(),
                    shipPortTypeEnum.getZhName(),
                    shipPortTypeEnum.getEnName()
            );

            shipPortTypeMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.shipPortTypeMap = shipPortTypeMap;
        return (HashMap<String, DictionaryVO>) this.shipPortTypeMap.clone();
    }

    @Override
    public DictionaryVO getShipPortType(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return shipPortTypeMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> transportModeMap() {
        if (this.transportModeMap != null) {
            return (HashMap<String, DictionaryVO>) this.transportModeMap.clone();
        }

        HashMap<String, DictionaryVO> transportModeMap = new LinkedHashMap<>();
        for (TransportModeEnum transportModeEnum : TransportModeEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    transportModeEnum.getCode(),
                    transportModeEnum.getZhName(),
                    transportModeEnum.getEnName()
            );

            transportModeMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.transportModeMap = transportModeMap;
        return (HashMap<String, DictionaryVO>) this.transportModeMap.clone();
    }

    @Override
    public DictionaryVO getTransportMode(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return transportModeMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> tradeTermMap() {
        if (this.tradeTermMap != null) {
            return (HashMap<String, DictionaryVO>) this.tradeTermMap.clone();
        }

        HashMap<String, DictionaryVO> tradeTermMap = new LinkedHashMap<>();
        for (TradeTermEnum tradeTermEnum : TradeTermEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    tradeTermEnum.getCode(),
                    tradeTermEnum.getZhName(),
                    tradeTermEnum.getEnName()
            );

            tradeTermMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.tradeTermMap = tradeTermMap;
        return (HashMap<String, DictionaryVO>) this.tradeTermMap.clone();
    }

    @Override
    public DictionaryVO getTradeTerm(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return this.tradeTermMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> saleSheetQueryOrderMap() {
        if (this.saleSheetQueryOrderMap != null) {
            return (HashMap<String, DictionaryVO>) this.saleSheetQueryOrderMap.clone();
        }

        HashMap<String, DictionaryVO> saleSheetQueryOrderMap = new LinkedHashMap<>();
        for (SaleSheetQueryOrderEnum saleSheetQueryOrderEnum : SaleSheetQueryOrderEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    saleSheetQueryOrderEnum.getCode(),
                    saleSheetQueryOrderEnum.getZhName(),
                    saleSheetQueryOrderEnum.getEnName()
            );

            saleSheetQueryOrderMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.saleSheetQueryOrderMap = saleSheetQueryOrderMap;
        return (HashMap<String, DictionaryVO>) this.saleSheetQueryOrderMap.clone();
    }

    @Override
    public String getSaleSheetQueryOrder(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        for (SaleSheetQueryOrderEnum saleSheetQueryOrderEnum : SaleSheetQueryOrderEnum.values()) {
            if (saleSheetQueryOrderEnum.getCode().equals(code)) {
                return saleSheetQueryOrderEnum.getColumn();
            }
        }

        return null;
    }

    @Override
    public HashMap<String, DictionaryVO> demandSheetQueryOrderMap() {
        if (this.demandSheetQueryOrderMap != null) {
            return (HashMap<String, DictionaryVO>) this.demandSheetQueryOrderMap.clone();
        }

        HashMap<String, DictionaryVO> demandSheetQueryOrderMap = new LinkedHashMap<>();
        for (DemandSheetQueryOrderEnum demandSheetQueryOrderEnum : DemandSheetQueryOrderEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    demandSheetQueryOrderEnum.getCode(),
                    demandSheetQueryOrderEnum.getZhName(),
                    demandSheetQueryOrderEnum.getEnName()
            );

            demandSheetQueryOrderMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.demandSheetQueryOrderMap = demandSheetQueryOrderMap;
        return (HashMap<String, DictionaryVO>) this.demandSheetQueryOrderMap.clone();
    }

    @Override
    public String getDemandSheetQueryOrder(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        for (DemandSheetQueryOrderEnum demandSheetQueryOrderEnum : DemandSheetQueryOrderEnum.values()) {
            if (demandSheetQueryOrderEnum.getCode().equals(code)) {
                return demandSheetQueryOrderEnum.getColumn();
            }
        }

        return null;
    }

    @Override
    public HashMap<String, ShipPortVO> shipPortMap() {
        if (this.shipPortMap != null) {
            return (HashMap<String, ShipPortVO>) this.shipPortMap.clone();
        }

        HashMap<String, ShipPortVO> shipPortMap = new LinkedHashMap<>();
        for (ShipPortEnum shipPortEnum : ShipPortEnum.values()) {
            ShipPortVO shipPortVO = new ShipPortVO();

            shipPortVO.setCode(shipPortEnum.getCode());
            shipPortVO.setZhName(shipPortEnum.getZhName());
            shipPortVO.setEnName(shipPortEnum.getEnName());

            String shipPortTypeCode = shipPortEnum.getShipPortTypeCode();
            if (!StringUtil.isEmpty(shipPortTypeCode)) {
                shipPortVO.setShipPortTypeVO(this.getShipPortType(shipPortTypeCode));
            }

            String countryCode = shipPortEnum.getCountryCode();
            if (!StringUtil.isEmpty(countryCode)) {
                shipPortVO.setCountryVO(this.getCountry(countryCode));
            }

            shipPortMap.put(shipPortVO.getCode(), shipPortVO);
        }

        this.shipPortMap = shipPortMap;
        return (HashMap<String, ShipPortVO>) this.shipPortMap.clone();
    }

    @Override
    public ShipPortVO getShipPort(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return shipPortMap().get(code);
    }

    @Override
    public HashMap<String, ShipPortVO> getShipPortMapByCountry(String countryCode) {
        if (StringUtil.isEmpty(countryCode)) {
            return null;
        }

        HashMap<String, ShipPortVO> shipPortMap = this.shipPortMap();

        Iterator<Map.Entry<String, ShipPortVO>> iterator = shipPortMap.entrySet().iterator();
        while(iterator.hasNext()) {
            ShipPortVO shipPortVO = iterator.next().getValue();

            if (!countryCode.equals(shipPortVO.getCountryVO().getCode())) {
                iterator.remove();
            }
        }

        return shipPortMap;
    }

    @Override
    public HashMap<String, ShipBerthVO> shipBerthMap() {
        if (this.shipBerthMap != null) {
            return (HashMap<String, ShipBerthVO>) this.shipBerthMap.clone();
        }

        HashMap<String, ShipBerthVO> shipBerthMap = new LinkedHashMap<>();
        for (ShipBerthEnum shipBerthEnum : ShipBerthEnum.values()) {
            ShipBerthVO shipBerthVO = new ShipBerthVO();

            shipBerthVO.setCode(shipBerthEnum.getCode());

            String shipPortCode = shipBerthEnum.getShipPortCode();
            if (!StringUtil.isEmpty(shipPortCode)) {
                shipBerthVO.setShipPortVO(this.getShipPort(shipPortCode));
            }

            shipBerthVO.setZhName(shipBerthEnum.getZhName());
            shipBerthVO.setEnName(shipBerthEnum.getEnName());
            shipBerthVO.setBerthTonnage(shipBerthEnum.getBerthTonnage());
            shipBerthVO.setBerthDraft(shipBerthEnum.getBerthDraft());
            shipBerthVO.setVesselType(shipBerthEnum.getVesselType());
            shipBerthVO.setRemark(shipBerthEnum.getRemark());

            shipBerthMap.put(shipBerthVO.getCode(), shipBerthVO);
        }

        this.shipBerthMap = shipBerthMap;
        return (HashMap<String, ShipBerthVO>) this.shipBerthMap.clone();
    }

    @Override
    public ShipBerthVO getShipBerth(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return shipBerthMap().get(code);
    }

    @Override
    public HashMap<String, ShipBerthVO> getShipBerthMapByShipPort(String shipPortCode) {
        if (StringUtil.isEmpty(shipPortCode)) {
            return null;
        }

        HashMap<String, ShipBerthVO> shipBerthMap = this.shipBerthMap();

        Iterator<Map.Entry<String, ShipBerthVO>> iterator = shipBerthMap.entrySet().iterator();
        while (iterator.hasNext()) {
            ShipBerthVO shipBerthVO = iterator.next().getValue();

            if (!shipPortCode.equals(shipBerthVO.getShipPortVO().getCode())) {
                iterator.remove();
            }
        }

        return shipBerthMap;
    }

    @Override
    public HashMap<String, DictionaryVO> countryMap() {
        if (this.countryMap != null) {
            return (HashMap<String, DictionaryVO>) this.countryMap.clone();
        }

        HashMap<String, DictionaryVO> countryMap = new LinkedHashMap<>();
        for (CountryEnum countryEnum : CountryEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    countryEnum.getCode(),
                    countryEnum.getZhName(),
                    countryEnum.getEnName()
            );

            countryMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.countryMap = countryMap;
        return (HashMap<String, DictionaryVO>) this.countryMap.clone();
    }

    @Override
    public HashMap<String, DictionaryVO> loadingPortCountryMap() {
        if (this.loadingPortCountryMap != null) {
            return (HashMap<String, DictionaryVO>) this.loadingPortCountryMap.clone();
        }

        HashMap<String, DictionaryVO> loadingPortCountryMap = new LinkedHashMap<>();
        for (CountryEnum countryEnum : CountryEnum.values()) {
            /*if (countryEnum.getShipPortTypeCode().equals(ShipPortTypeEnum.LOADING_PORT.getCode())) {
                DictionaryVO dictionaryVO = new DictionaryVO(
                        countryEnum.getCode(),
                        countryEnum.getZhName(),
                        countryEnum.getEnName()
                );

                loadingPortCountryMap.put(dictionaryVO.getCode(), dictionaryVO);
            }*/
            DictionaryVO dictionaryVO = new DictionaryVO(
                    countryEnum.getCode(),
                    countryEnum.getZhName(),
                    countryEnum.getEnName()
            );
            loadingPortCountryMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.loadingPortCountryMap = loadingPortCountryMap;
        return (HashMap<String, DictionaryVO>) this.loadingPortCountryMap.clone();
    }

    @Override
    public HashMap<String, DictionaryVO> dischargePortCountryMap() {
        if (this.dischargePortCountryMap != null) {
            return (HashMap<String, DictionaryVO>) this.dischargePortCountryMap.clone();
        }

        HashMap<String, DictionaryVO> dischargePortCountryMap = new LinkedHashMap<>();
        for (CountryEnum countryEnum : CountryEnum.values()) {
            /*if (countryEnum.getShipPortTypeCode().equals(ShipPortTypeEnum.DISCHARGE_PORT.getCode())) {
                DictionaryVO dictionaryVO = new DictionaryVO(
                        countryEnum.getCode(),
                        countryEnum.getZhName(),
                        countryEnum.getEnName()
                );

                dischargePortCountryMap.put(dictionaryVO.getCode(), dictionaryVO);

            }*/
            DictionaryVO dictionaryVO = new DictionaryVO(
                    countryEnum.getCode(),
                    countryEnum.getZhName(),
                    countryEnum.getEnName()
            );
            dischargePortCountryMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.dischargePortCountryMap = dischargePortCountryMap;
        return (HashMap<String, DictionaryVO>) this.dischargePortCountryMap.clone();
    }

    @Override
    public DictionaryVO getCountry(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return countryMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> priceRegionMap2() {
        if (this.priceRegionMap2 != null) {
            return (HashMap<String, DictionaryVO>) this.priceRegionMap2.clone();
        }

        HashMap<String, DictionaryVO> priceRegionMap = new LinkedHashMap<>();
        for (PriceRegionEnum priceRegionEnum : PriceRegionEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    priceRegionEnum.getCode(),
                    priceRegionEnum.getZhName(),
                    priceRegionEnum.getEnName()
            );

            priceRegionMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.priceRegionMap2 = priceRegionMap;
        return (HashMap<String, DictionaryVO>) this.priceRegionMap2.clone();
    }

    @Override
    public DictionaryVO getPriceRegion2(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return priceRegionMap2().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> priceSourceMap2() {
        if (this.priceSourceMap2 != null) {
            return (HashMap<String, DictionaryVO>) this.priceSourceMap2.clone();
        }

        HashMap<String, DictionaryVO> priceSourceMap = new LinkedHashMap<>();
        for (PriceSourceEnum priceSourceEnum : PriceSourceEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    priceSourceEnum.getCode(),
                    priceSourceEnum.getZhName(),
                    priceSourceEnum.getEnName()
            );

            priceSourceMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.priceSourceMap2 = priceSourceMap;
        return (HashMap<String, DictionaryVO>) this.priceSourceMap2.clone();
    }

    @Override
    public DictionaryVO getPriceSource2(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return priceSourceMap2().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> pricingBenchmarkMap2() {
        if (this.pricingBenchmarkMap2 != null) {
            return (HashMap<String, DictionaryVO>) this.pricingBenchmarkMap2.clone();
        }

        HashMap<String, DictionaryVO> pricingBenchmarkMap = new LinkedHashMap<>();
        for (PricingBenchmarkEnum pricingBenchmarkEnum : PricingBenchmarkEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    pricingBenchmarkEnum.getCode(),
                    pricingBenchmarkEnum.getZhName(),
                    pricingBenchmarkEnum.getEnName()
            );

            pricingBenchmarkMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.pricingBenchmarkMap2 = pricingBenchmarkMap;
        return (HashMap<String, DictionaryVO>) this.pricingBenchmarkMap2.clone();
    }

    @Override
    public DictionaryVO getPricingBenchmark2(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return pricingBenchmarkMap2().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> inspetionMap() {
        if (this.inspetionMap != null) {
            return (HashMap<String, DictionaryVO>) this.inspetionMap.clone();
        }

        HashMap<String, DictionaryVO> inspetionMap = new LinkedHashMap<>();
        for (InspetionEnum inspetionEnum : InspetionEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    inspetionEnum.getCode(),
                    inspetionEnum.getZhName(),
                    inspetionEnum.getEnName()
            );

            inspetionMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.inspetionMap = inspetionMap;
        return (HashMap<String, DictionaryVO>) this.inspetionMap.clone();
    }

    @Override
    public DictionaryVO getInspetion(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return inspetionMap().get(code);
    }
    @Override
    public HashMap<String, DictionaryVO> demurrageRateMap() {
        if (this.demurrageRateMap != null) {
            return (HashMap<String, DictionaryVO>) this.demurrageRateMap.clone();
        }

        HashMap<String, DictionaryVO> demurrageRateMap = new LinkedHashMap<>();
        for (DemurrageRateEnum demurrageRateEnum : DemurrageRateEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    demurrageRateEnum.getCode(),
                    demurrageRateEnum.getZhName(),
                    demurrageRateEnum.getEnName()
            );

            demurrageRateMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.demurrageRateMap = demurrageRateMap;
        return (HashMap<String, DictionaryVO>) this.demurrageRateMap.clone();
    }

    @Override
    public DictionaryVO getDemurrageRate(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }

        return demurrageRateMap().get(code);
    }

    @Override
    public HashMap<String, DictionaryVO> loadingPortMap() {
        if (this.loadingPortMap != null) {
            return (HashMap<String, DictionaryVO>) this.loadingPortMap.clone();
        }

        HashMap<String, DictionaryVO> loadingPortMap = new LinkedHashMap<>();
        for (ShipPortEnum shipPortEnum : ShipPortEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    shipPortEnum.getCode(),
                    shipPortEnum.getZhName(),
                    shipPortEnum.getEnName()
            );

            loadingPortMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.loadingPortMap = loadingPortMap;
        return (HashMap<String, DictionaryVO>) this.loadingPortMap.clone();
    }

    @Override
    public HashMap<String, DictionaryVO> dischargePortMap() {
        if (this.dischargePortMap != null) {
            return (HashMap<String, DictionaryVO>) this.dischargePortMap.clone();
        }

        HashMap<String, DictionaryVO> dischargePortMap = new LinkedHashMap<>();
        for (ShipPortEnum shipPortEnum : ShipPortEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    shipPortEnum.getCode(),
                    shipPortEnum.getZhName(),
                    shipPortEnum.getEnName()
            );

            dischargePortMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.dischargePortMap = dischargePortMap;
        return (HashMap<String, DictionaryVO>) this.dischargePortMap.clone();
    }


    @Override
    public HashMap<String, DictionaryVO> appointStatusMap() {
        if (this.appointStatusMap != null) {
            return (HashMap<String, DictionaryVO>) this.appointStatusMap.clone();
        }

        HashMap<String, DictionaryVO> appointStatusMap = new LinkedHashMap<>();
        for (AppointStatusEnums appointStatusEnums : AppointStatusEnums.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    appointStatusEnums.getCode(),
                    appointStatusEnums.getZhName(),
                    appointStatusEnums.getEnName()
            );

            appointStatusMap.put(dictionaryVO.getCode(), dictionaryVO);
        }

        this.appointStatusMap = appointStatusMap;
        return (HashMap<String, DictionaryVO>) this.appointStatusMap.clone();
    }
}
