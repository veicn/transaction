package com.sinochem.crude.trade.web.controller;

import com.sinochem.crude.trade.common.enums.TradeTermEnum;
import com.sinochem.crude.trade.common.utils.SequenceManager;
import com.sinochem.crude.trade.common.utils.SequenceUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.domain.BiddingSheetCombine;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.*;
import com.sinochem.crude.trade.transaction.enums.*;
import com.sinochem.crude.trade.transaction.model.vo.ShipPortVO;
import com.sinochem.crude.trade.transaction.service.BiddingSheetService;
import com.sinochem.crude.trade.transaction.service.ContractSheetService;
import com.sinochem.crude.trade.transaction.service.DictionaryService;
import com.sinochem.crude.trade.transaction.service.SaleSheetService;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

/**
 * 仅用于生成数据
 * @author Yichen Zhao
 * date: 20180312
 */
@Controller
@WithoutAccess
public class DataGeneratorController {

    @Autowired
    private SaleSheetService saleSheetService;

    @Autowired
    private BiddingSheetService biddingSheetService;

    @Autowired
    private ContractSheetService contractSheetService;

    @Autowired
    private DictionaryService dictionaryService;

    private final int SALE_SHEET_QUANTITY = 120; //报价单数量
    private final int BIDDING_SHEET_QUANTITY_PER_SALE_SHEET = 5; //每个销售需求单会生成0到5个报价单

    private final int QUANTITY = 10000;
    private final String TOLERANCE = "5";
    private final int PREMIUMS_AND_DISCOUNTS = 10;
    private final int LAYTIME = 6;
    private final String TELEPHONE = "18612345678";
    private final String EMAIL = "test@qq.com";
    private final String FAX = "123456";
    private final String BUYER_NAME = "买家";
    private final String SALER_NAME = "卖家";

    private final String PLACE_HOLDER = "test";

    @RequestMapping(UrlMapping.DATA_GENERATOR)
    public void dataGenerator() {
        try {
            Random random = new Random();

            for (int i = 0; i < SALE_SHEET_QUANTITY; i++) {
                SaleSheetCombine saleSheetCombine = new SaleSheetCombine();
                CurrentUser saleSheetUser = new CurrentUser();
                CurrentUser biddingSheetUser = new CurrentUser();

            /* SaleSheet */
                SaleSheet saleSheet = new SaleSheet();
                saleSheet.setSerialNumber(String.valueOf(i));
                saleSheet.setUuid(String.valueOf(i));
                saleSheet.setSaleSheetStatusCode(SaleSheetStatusEnum.RELEASED.getCode());
                saleSheet.setSaleTypeCode(SaleTypeEnum.PUBLIC.getCode());
                saleSheetCombine.setSaleSheet(saleSheet);

            /* ProductInfo */
                ProductInfo productInfo = new ProductInfo();
                productInfo.setDeadline(new Date());
                productInfo.setBidOpeningDate(new Date());

                int productCategoryCount = ProductCategoryEnum.values().length;
                productInfo.setProductCategoryCode(ProductCategoryEnum.values()[random.nextInt(productCategoryCount)].getCode());

                int productVarietyCount = ProductVarietyEnum.values().length;
                productInfo.setProductVarietyCode(ProductVarietyEnum.values()[random.nextInt(productVarietyCount)].getCode());

                int productSpecificationCount = ProductSpecificationEnum.values().length;
                productInfo.setProductSpecificationCode(ProductSpecificationEnum.values()[random.nextInt(productSpecificationCount)].getCode());

                int exportTypeCount = ExportTypeEnum.values().length;
                productInfo.setExportTypeCode(ExportTypeEnum.values()[random.nextInt(exportTypeCount)].getCode());

                int tradeTermCount = TradeTermEnum.values().length;
                productInfo.setTradeTermCode(TradeTermEnum.values()[random.nextInt(tradeTermCount)].getCode());

                productInfo.setQuantity(new BigDecimal(QUANTITY));
                productInfo.setTolerance(TOLERANCE);
                saleSheetCombine.setProductInfo(productInfo);

            /* PricingInfo */
                PricingInfo pricingInfo = new PricingInfo();

                int currencyCount = CurrencyEnum.values().length;
                pricingInfo.setCurrencyCode(CurrencyEnum.values()[random.nextInt(currencyCount)].getCode());

                int pricingBenchmarkCount = PricingBenchmarkEnum.values().length;
                pricingInfo.setPricingBenchmarkCode(PricingBenchmarkEnum.values()[random.nextInt(pricingBenchmarkCount)].getCode());

                int pricingUnitCount = PricingUnitEnum.values().length;
                pricingInfo.setPricingUnitCode(PricingUnitEnum.values()[random.nextInt(pricingUnitCount)].getCode());

                int priceSourceCount = PriceSourceEnum.values().length;
                pricingInfo.setPriceSourceCode(PriceSourceEnum.values()[random.nextInt(priceSourceCount)].getCode());

                int priceRegionCount = PriceRegionEnum.values().length;
                pricingInfo.setPriceRegionCode(PriceRegionEnum.values()[random.nextInt(priceRegionCount)].getCode());

                int settlementQuantityCount = SettlementQuantityEnum.values().length;
                pricingInfo.setSettlementQuantity(SettlementQuantityEnum.values()[random.nextInt(settlementQuantityCount)].getCode());

                int pricingPeriodCount = PricingPeriodEnum.values().length;
                pricingInfo.setPricingPeriod(PricingPeriodEnum.values()[random.nextInt(pricingPeriodCount)].getCode());

                int paymentTermCount = PaymentTermEnum.values().length;
                pricingInfo.setPaymentTerm(PaymentTermEnum.values()[random.nextInt(paymentTermCount)].getCode());

                pricingInfo.setPremiumsAndDiscounts(new BigDecimal(PREMIUMS_AND_DISCOUNTS));

                saleSheetCombine.setPricingInfo(pricingInfo);

            /* TransportInfo */
                TransportInfo transportInfo = new TransportInfo();

                int transportModeCount = TransportModeEnum.values().length;
                transportInfo.setTransportModeCode(TransportModeEnum.values()[random.nextInt(transportModeCount)].getCode());

                HashMap<String, ShipPortVO> shipPortMap = dictionaryService.shipPortMap();
                int shipPortCount = shipPortMap.size();
                transportInfo.setLoadingPort(((ShipPortVO)shipPortMap.values().toArray()[random.nextInt(shipPortCount)]).getCode());

                transportInfo.setLaytime(new BigDecimal(LAYTIME));
                transportInfo.setLaycanStartDate(new Date());
                transportInfo.setLaycanEndDate(new Date());

                transportInfo.setDischargePort(((ShipPortVO)shipPortMap.values().toArray()[random.nextInt(shipPortCount)]).getCode());

                saleSheetCombine.setTransportInfo(transportInfo);

            /* SalerInfo */
                StakeholderInfo salerInfo = new StakeholderInfo();

                salerInfo.setAddress(PLACE_HOLDER);
                salerInfo.setTraderName(SALER_NAME);
                salerInfo.setEmail(EMAIL);
                salerInfo.setTelephone(TELEPHONE);
                salerInfo.setFax(FAX);

                saleSheetCombine.setSalerInfo(salerInfo);

            /* OtherInfo */
                OtherInfo otherInfo = new OtherInfo();
                otherInfo.setOtherTerm(PLACE_HOLDER);

                saleSheetCombine.setOtherInfo(otherInfo);

                Long saleSheetId = saleSheetService.postSaleSheet(saleSheetUser, saleSheetCombine);

                Long[] biddingSheetIdArray = new Long[BIDDING_SHEET_QUANTITY_PER_SALE_SHEET];
                for (int j = 0; j < BIDDING_SHEET_QUANTITY_PER_SALE_SHEET; j++) {
                    BiddingSheetCombine biddingSheetCombine = new BiddingSheetCombine();

                    BiddingSheet biddingSheet = new BiddingSheet();
                    biddingSheet.setSaleSheetId(saleSheetId);
                    biddingSheet.setSaleSheetUuid(saleSheet.getUuid());
                    biddingSheet.setBiddingSheetStatusCode(BiddingSheetStatusEnum.RELEASED.getCode());
                    biddingSheetCombine.setBiddingSheet(biddingSheet);

                    productInfo.setUuid(UUID.randomUUID().toString());
                    biddingSheetCombine.setProductInfo(productInfo);

                    pricingInfo.setUuid(UUID.randomUUID().toString());
                    biddingSheetCombine.setPricingInfo(pricingInfo);

                    transportInfo.setUuid(UUID.randomUUID().toString());
                    biddingSheetCombine.setTransportInfo(transportInfo);

                    StakeholderInfo buyerInfo = new StakeholderInfo();

                    buyerInfo.setAddress(PLACE_HOLDER);
                    buyerInfo.setTraderName(BUYER_NAME);
                    buyerInfo.setEmail(EMAIL);
                    buyerInfo.setTelephone(TELEPHONE);
                    buyerInfo.setFax(FAX);

                    biddingSheetCombine.setBuyerInfo(buyerInfo);

                    otherInfo.setUuid(UUID.randomUUID().toString());
                    biddingSheetCombine.setOtherInfo(otherInfo);

                    Long biddingSheetId = biddingSheetService.postBiddingSheet(biddingSheetUser, biddingSheetCombine);
                    biddingSheetIdArray[j] = biddingSheetId;
                }

                if (random.nextInt(2) == 1) {
                    Long wonBiddingSheetId = biddingSheetIdArray[random.nextInt(BIDDING_SHEET_QUANTITY_PER_SALE_SHEET)];

                    saleSheetService.confirmBidding(saleSheetUser,
                            saleSheetService.getSaleSheetById(saleSheetUser, saleSheetId),
                            biddingSheetService.getBiddingSheetById(biddingSheetUser, wonBiddingSheetId));
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
