package com.sinochem.crude.trade.xxl;

import com.sinochem.crude.trade.productOil.constant.InfoConstant;
import com.sinochem.crude.trade.productOil.service.NewsPricesService;
import com.sinochem.crude.trade.productOil.utils.TableUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  成品油生成price咨詢信息
 */
@Component
@JobHander("productOilPriceJob")
public class ProductOilPriceHandler extends IJobHandler {

    @Autowired
    public NewsPricesService newsPricesService;

    private static final Log log = LogFactory.getLog(ProductOilPriceHandler.class);
    /**
     * 今天
     */
    private int today = 0;

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {

        log.error("productOilPriceJob  start ---->");
        TableUtil tableUtil = TableUtil.getInstance();
        tableUtil.setNewsPricesService(newsPricesService);
        //产生prices1
        String htmlText_prices1 = tableUtil.createTable5(today, InfoConstant.langVel_EN, InfoConstant.gasoline_international);
        newsPricesService.createInfo(today,InfoConstant.gasoline_international, htmlText_prices1, InfoConstant.langVel_EN);

        //产生prices2
        String htmlText_prices2 = tableUtil.createTable4(today,InfoConstant.langVel_EN, InfoConstant.diesel_international);
        newsPricesService.createInfo(today,InfoConstant.diesel_international, htmlText_prices2, InfoConstant.langVel_EN);

        //产生prices3
        StringBuffer htmlText_prices3 = new StringBuffer();
        String htmlText_prices3_92 = tableUtil.createTable1(today,InfoConstant.langVel_EN, InfoConstant.gasoline92_provinces);
        if(htmlText_prices3_92 != null){

            htmlText_prices3.append(htmlText_prices3_92);
        }
        String htmlText_prices3_95 = tableUtil.createTable1(today,InfoConstant.langVel_EN, InfoConstant.gasoline95_provinces);
        if(htmlText_prices3_95 != null){

            htmlText_prices3.append(htmlText_prices3_95);
        }
        if(htmlText_prices3.toString().length()>0){

            htmlText_prices3.append(InfoConstant.later2);
            newsPricesService.createInfo(today,InfoConstant.gasoline92_provinces, htmlText_prices3.toString(), InfoConstant.langVel_EN);
        }

        //产生prices4
        String htmlText_prices4 = tableUtil.createTable2(today,InfoConstant.langVel_EN, InfoConstant.gasoline95);
        newsPricesService.createInfo(today,InfoConstant.gasoline95, htmlText_prices4, InfoConstant.langVel_EN);

        //产生prices5
        String htmlText_prices5 = tableUtil.createTable3(today,InfoConstant.langVel_EN, InfoConstant.shandong_refine_gasoline);
        newsPricesService.createInfo(today,InfoConstant.shandong_refine_gasoline, htmlText_prices5, InfoConstant.langVel_EN);

        //产生prices6
        String htmlText_prices6 = tableUtil.createTable3(today,InfoConstant.langVel_EN, InfoConstant.shandong_refine_diesel_1_2);
        newsPricesService.createInfo(today,InfoConstant.shandong_refine_diesel_1_2, htmlText_prices6, InfoConstant.langVel_EN);

        //产生prices7
        String htmlText_prices7 = tableUtil.createTable2(today,InfoConstant.langVel_EN, InfoConstant.diesel_northEast_northChina_northWest,InfoConstant.diesel_southChina_southWest,InfoConstant.diesle_southChina_southWest);
        newsPricesService.createInfo(today,InfoConstant.diesel_northEast_northChina_northWest, htmlText_prices7, InfoConstant.langVel_EN);

        //产生prices8
        String htmlText_prices8 = tableUtil.createTable1(today,InfoConstant.langVel_EN, InfoConstant.diesel_domestic);
        newsPricesService.createInfo(today,InfoConstant.diesel_domestic, htmlText_prices8, InfoConstant.langVel_EN);

        log.error("productOilPriceJob  end ---->");
        return ReturnT.SUCCESS;
    }
}
