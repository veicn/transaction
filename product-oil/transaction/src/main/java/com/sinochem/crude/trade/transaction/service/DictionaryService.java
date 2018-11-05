package com.sinochem.crude.trade.transaction.service;

import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.transaction.model.vo.ProductSpecificationVO;
import com.sinochem.crude.trade.transaction.model.vo.ShipBerthVO;
import com.sinochem.crude.trade.transaction.model.vo.ShipPortVO;

import java.util.HashMap;

/**
 * 字典的Service，一期先使用Enum，之后可使用值集系统
 * @author DimitriZhao
 * date: 20180301
 */
public interface DictionaryService {

    HashMap<String, DictionaryVO> biddingSheetStatusMap();
    DictionaryVO getBiddingSheetStatus(String code);

    HashMap<String, DictionaryVO> contractSheetStatusMap();
    DictionaryVO getContractSheetStatus(String code);

    HashMap<String, DictionaryVO> currencyMap();
    DictionaryVO getCurrency(String code);

    HashMap<String, DictionaryVO> exportTypeMap();
    DictionaryVO getExportType(String code);

    HashMap<String, DictionaryVO> paymentTermMap();
    DictionaryVO getPaymentTerm(String code);

    HashMap<String, DictionaryVO> priceRegionMap();

    DictionaryVO getPriceRegion(String code);
    HashMap<String, DictionaryVO> priceSourceMap();

    DictionaryVO getPriceSource(String code);
    HashMap<String, DictionaryVO> priceRegionMap2();
    DictionaryVO getPriceRegion2(String code);

    HashMap<String, DictionaryVO> priceSourceMap2();
    DictionaryVO getPriceSource2(String code);

    HashMap<String, DictionaryVO> pricingBenchmarkMap2();
    DictionaryVO getPricingBenchmark2(String code);

    HashMap<String, DictionaryVO> pricingBenchmarkMap();
    DictionaryVO getPricingBenchmark(String code);

    HashMap<String, DictionaryVO> pricingPeriodMap();
    DictionaryVO getPricingPeriod(String code);

    HashMap<String, DictionaryVO> pricingUnitMap();
    DictionaryVO getPricingUnit(String code);

    HashMap<String, DictionaryVO> quantityUnitMap();
    DictionaryVO getQuantityUnit(String code);

    HashMap<String, DictionaryVO> productCategoryMap();
    DictionaryVO getProductCategory(String code);

    HashMap<String, ProductSpecificationVO> productSpecificationMap();
    ProductSpecificationVO getProductSpecification(String code);
    HashMap<String, ProductSpecificationVO> getProductSpecificationMapByCategoryCode(String categoryCode);

    HashMap<String, DictionaryVO> productVarietyMap();
    DictionaryVO getProductVariety(String code);

    HashMap<String, DictionaryVO> saleSheetStatusMap();
    DictionaryVO getSaleSheetStatus(String code);

    HashMap<String, DictionaryVO> demandSheetStatusMap();
    DictionaryVO getDemandSheetStatus(String code);

    HashMap<String, DictionaryVO> saleTypeMap();
    DictionaryVO getSaleType(String code);

    HashMap<String, DictionaryVO> sheetFlagMap();
    DictionaryVO getSheetFlag(String code);

    HashMap<String, DictionaryVO> demandTypeMap();
    DictionaryVO getDemandType(String code);

    HashMap<String, DictionaryVO> productSourceMap();
    DictionaryVO getProductSource(String code);

    HashMap<String, DictionaryVO> settlementQuantityMap();
    DictionaryVO getSettlementQuantity(String code);

    HashMap<String, DictionaryVO> demurrageRateMap();
    DictionaryVO getDemurrageRate(String code);

    HashMap<String, DictionaryVO> inspetionMap();
    DictionaryVO getInspetion(String code);

    HashMap<String, DictionaryVO> shipPortTypeMap();
    DictionaryVO getShipPortType(String code);

    HashMap<String, DictionaryVO> transportModeMap();
    DictionaryVO getTransportMode(String code);

    HashMap<String, DictionaryVO> tradeTermMap();
    DictionaryVO getTradeTerm(String code);

    HashMap<String, DictionaryVO> saleSheetQueryOrderMap();
    String getSaleSheetQueryOrder(String code);

    HashMap<String, DictionaryVO> demandSheetQueryOrderMap();
    String getDemandSheetQueryOrder(String code);

    HashMap<String, ShipPortVO> shipPortMap();
    ShipPortVO getShipPort(String code);
    HashMap<String, ShipPortVO> getShipPortMapByCountry(String countryCode);

    HashMap<String, ShipBerthVO> shipBerthMap();
    ShipBerthVO getShipBerth(String code);
    HashMap<String, ShipBerthVO> getShipBerthMapByShipPort(String shipPortCode);

    HashMap<String, DictionaryVO> loadingPortMap();
    HashMap<String, DictionaryVO> dischargePortMap();

    HashMap<String, DictionaryVO> countryMap();
    HashMap<String, DictionaryVO> loadingPortCountryMap();
    HashMap<String, DictionaryVO> dischargePortCountryMap();
    DictionaryVO getCountry(String code);

    HashMap<String, DictionaryVO> appointStatusMap();

}
