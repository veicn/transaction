package com.sinochem.crude.trade.productOil.utils;

import com.sinochem.crude.trade.productOil.constant.InfoConstant;
import com.sinochem.crude.trade.productOil.model.TCmsDomesticProductPrice;
import com.sinochem.crude.trade.productOil.service.NewsPricesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TableUtil {



    public NewsPricesService newsPricesService;

    private static TableUtil tableUtil ;

    private TableUtil(){

    }

    public void setNewsPricesService(NewsPricesService newsPricesService) {
        this.newsPricesService = newsPricesService;
    }

    /**
     * 获取实例对象
     * @return
     */
    public static synchronized TableUtil getInstance(){
        if(tableUtil==null){
            tableUtil = new TableUtil();
        }
        return tableUtil;
    }

    /**
     * 汽油：92#（国四/国五/调和/95#分省价格/柴油国内市场估价，三者表格样式相同
     * 表格样式一
     * 2018年3月22日
     */
    public String createTable1(int day,String lagVer,String productName) {

        //获取价格列表
        List<TCmsDomesticProductPrice> productPriceList = newsPricesService.queryProductPrice(productName,lagVer,day);
        //无数据时返回空
        if(productPriceList==null || productPriceList.size()<=0) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        if("PRICES3".equals(InfoConstant.channelType.get(productName))) {

            buffer.append("<div>"+productName+"</div><br>\r\n");
        }/*else {

			buffer.append("<div>"+productName+"</div>\r\n");
		}*/

        buffer.append(InfoConstant.EN_first1);
        for (TCmsDomesticProductPrice tCmsDomesticProductPrice : productPriceList) {

            String areaName = tCmsDomesticProductPrice.getAreaName()==null?"":tCmsDomesticProductPrice.getAreaName();

            BigDecimal priceChangeB = tCmsDomesticProductPrice.getPriceChange();
            String priceChange = "";
            if(priceChangeB!=null) {

                priceChangeB = priceChangeB.setScale(2,BigDecimal.ROUND_HALF_UP);
                priceChange = priceChangeB.toString();
            }

            BigDecimal todayB = tCmsDomesticProductPrice.getPrice();
            String today = "";
            if(todayB!=null) {

                todayB = todayB.setScale(2,BigDecimal.ROUND_HALF_UP);
                today = todayB.toString();
            }

            BigDecimal previousB = tCmsDomesticProductPrice.getPreHighPrice();
            String previous = "";
            if(previousB!=null) {

                previousB = previousB.setScale(2,BigDecimal.ROUND_HALF_UP);
                previous = previousB.toString();
            }

            Date priceDate = tCmsDomesticProductPrice.getPriceDate();
            String dateString = "";
            if(priceDate!=null) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                dateString = formatter.format(priceDate);
            }


            buffer.append("<tr>\r\n" +
                    "        <td>"+areaName+"</td>\r\n" +
                    "        <td>"+today+"</td>\r\n" +
                    "        <td>"+previous+"</td>\r\n" +
                    "        <td>"+priceChange+"</td>\r\n" +
                    "        <td>"+dateString+"</td>\r\n" +
                    "    </tr>");
        }
        buffer.append("</table>");
        if(!"PRICES3".equals(InfoConstant.channelType.get(productName))) {

            buffer.append(InfoConstant.later);
        }
        return buffer.toString();
    }

    /**
     * 汽油：汽油95#国内市场批发报价/0#柴油东北、华北、西北报价/0#柴油华中、华东/0#柴油华南、西南，表格样式相同
     * 表格样式二
     * 2018年3月22日
     */
    public String createTable2(int day,String lagVer,String... productNames) {

        //获取价格列表
        List<TCmsDomesticProductPrice> productPriceList = new ArrayList<>();
        for (String productName: productNames) {

            List<TCmsDomesticProductPrice> tempList = newsPricesService.queryProductPrice(productName,lagVer,day);
            productPriceList.addAll(tempList);
        }

        //无数据时返回空
        if(productPriceList==null || productPriceList.size()<=0) {
            return null;
        }

        StringBuffer buffer = new StringBuffer();

        //判断是否为prices7
        String channelCode = InfoConstant.channelType.get(productNames[0]);
    	/*if(channelCode.equals("PRICES7")) {

    		buffer.append("<div>0# Gasoil Wholesale Prices of China</div>\r\n");
    	}else {

    		buffer.append("<div>"+productNames[0]+"</div>\r\n");
    	}*/

        buffer.append(InfoConstant.EN_first2);
        for (TCmsDomesticProductPrice tCmsDomesticProductPrice : productPriceList) {

            String priceSource = tCmsDomesticProductPrice.getPriceSource()==null?"":tCmsDomesticProductPrice.getPriceSource();
            String areaName = tCmsDomesticProductPrice.getAreaName()==null?"":tCmsDomesticProductPrice.getAreaName();
            String grade = tCmsDomesticProductPrice.getExtend1()==null?"":tCmsDomesticProductPrice.getExtend1();;

            BigDecimal priceChangeB = tCmsDomesticProductPrice.getPriceChange();
            String priceChange = "";
            if(priceChangeB!=null) {

                priceChangeB = priceChangeB.setScale(2,BigDecimal.ROUND_HALF_UP);
                priceChange = priceChangeB.toString();
            }

            BigDecimal todayB = tCmsDomesticProductPrice.getPrice();
            String today = "";
            if(todayB!=null) {

                todayB = todayB.setScale(2,BigDecimal.ROUND_HALF_UP);
                today = todayB.toString();
            }

            BigDecimal previousB = tCmsDomesticProductPrice.getPreHighPrice();
            String previous = "";
            if(previousB!=null) {

                previousB = previousB.setScale(2,BigDecimal.ROUND_HALF_UP);
                previous = previousB.toString();
            }

            Date priceDate = tCmsDomesticProductPrice.getPriceDate();
            String dateString = "";
            if(priceDate!=null) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                dateString = formatter.format(priceDate);
            }

            buffer.append("<tr>\r\n" +
                    "        <td>"+priceSource+"</td>\r\n" +
                    "        <td>"+areaName+"</td>\r\n" +
                    "        <td>"+grade+"</td>\r\n" +
                    "        <td>"+today+"</td>\r\n" +
                    "        <td>"+previous+"</td>\r\n" +
                    "        <td>"+priceChange+"</td>\r\n" +
                    "        <td>"+dateString+"</td>\r\n" +
                    "    </tr>");
        }

        buffer.append("</table>");
        buffer.append(InfoConstant.later);
        return buffer.toString();
    }


    /**
     * 汽油：山东地炼汽油市场报价/其他地炼汽油市场报价/山东地炼市场柴油1,2/其他地炼市场柴油，表格样式相同
     * 表格样式三
     * 2018年3月22日
     */
    public String createTable3(int day,String lagVer,String productName) {

        //获取价格列表
        List<TCmsDomesticProductPrice> productPriceList =  newsPricesService.queryProductPrice(productName,lagVer,day);

        //无数据时返回空
        if(productPriceList==null || productPriceList.size()<=0) {
            return null;
        }

        StringBuffer buffer = new StringBuffer();
    	/*buffer.append("<div>"+productName+"</div>\r\n");*/
        buffer.append(InfoConstant.EN_first3);
        for (TCmsDomesticProductPrice tCmsDomesticProductPrice : productPriceList) {

            String priceSource = tCmsDomesticProductPrice.getPriceSource()==null?"":tCmsDomesticProductPrice.getPriceSource();
            String areaName = tCmsDomesticProductPrice.getAreaName()==null?"":tCmsDomesticProductPrice.getAreaName();
            String grade = tCmsDomesticProductPrice.getExtend1()==null?"":tCmsDomesticProductPrice.getExtend1();;

            BigDecimal priceChangeB = tCmsDomesticProductPrice.getPriceChange();
            String priceChange = "";
            if(priceChangeB!=null) {

                priceChangeB = priceChangeB.setScale(2,BigDecimal.ROUND_HALF_UP);
                priceChange = priceChangeB.toString();
            }

            BigDecimal todayB = tCmsDomesticProductPrice.getPrice();
            String today = "";
            if(todayB!=null) {

                todayB = todayB.setScale(2,BigDecimal.ROUND_HALF_UP);
                today = todayB.toString();
            }

            BigDecimal previousB = tCmsDomesticProductPrice.getPreHighPrice();
            String previous = "";
            if(previousB!=null) {

                previousB = previousB.setScale(2,BigDecimal.ROUND_HALF_UP);
                previous = previousB.toString();
            }

            Date priceDate = tCmsDomesticProductPrice.getPriceDate();
            String dateString = "";
            if(priceDate!=null) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                dateString = formatter.format(priceDate);
            }

            buffer.append("<tr>\r\n" +
                    "        <td>"+priceSource+"</td>\r\n" +
                    "        <td>"+grade+"</td>\r\n" +
                    "        <td>"+today+"</td>\r\n" +
                    "        <td>"+previous+"</td>\r\n" +
                    "        <td>"+priceChange+"</td>\r\n" +
                    "        <td>"+dateString+"</td>\r\n" +
                    "    </tr>");
        }

        buffer.append("</table>");
        buffer.append(InfoConstant.later);

        return buffer.toString();
    }


    /**
     * 国际市场_汽油现货
     * 表格样式四
     * 2018年3月22日
     */
    public String createTable4(int day,String lagVer,String productName) {

        //获取价格列表
        List<TCmsDomesticProductPrice> productPriceList = newsPricesService.queryProductPrice(productName,lagVer,day);

        //无数据时返回空
        if(productPriceList==null || productPriceList.size()<=0) {
            return null;
        }

        StringBuffer buffer = new StringBuffer();
    	/*buffer.append("<div>"+productName+"</div>\r\n");*/
        buffer.append(InfoConstant.EN_first4);
        for (TCmsDomesticProductPrice tCmsDomesticProductPrice : productPriceList) {

            String name = tCmsDomesticProductPrice.getExtend1()==null?"":tCmsDomesticProductPrice.getExtend1();
//    		String term = "";
//    		String deliver = "";

            BigDecimal priceB = tCmsDomesticProductPrice.getPrice();
            String price = "";
            if(priceB!=null) {

                priceB = priceB.setScale(2,BigDecimal.ROUND_HALF_UP);
                price = priceB.toString();
            }

            String currency = tCmsDomesticProductPrice.getPriceUnit()==null?"":tCmsDomesticProductPrice.getPriceUnit();
            String transportation = tCmsDomesticProductPrice.getExtend4()==null?"":tCmsDomesticProductPrice.getExtend4();

            Date priceDate = tCmsDomesticProductPrice.getPriceDate();
            String dateString = "";
            if(priceDate!=null) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                dateString = formatter.format(priceDate);
            }

            buffer.append("<tr>\r\n" +
                    "        <td>"+name+"</td>\r\n" +
//    				"        <td>"+term+"</td>\r\n" +
//    				"        <td>"+deliver+"</td>\r\n" +
                    "        <td>"+price+"</td>\r\n" +
                    "        <td>"+currency+"</td>\r\n" +
                    "        <td>"+transportation+"</td>\r\n" +
                    "        <td>"+dateString+"</td>\r\n" +
                    "    </tr>");
        }

        buffer.append("</table>");
        buffer.append(InfoConstant.later);
        return buffer.toString();
    }



    /**
     * 国际市场_汽油现货
     * 表格样式五
     * 2018年3月22日
     */
    public String createTable5(int day,String lagVer,String productName) {

        //获取价格列表
        List<TCmsDomesticProductPrice> productPriceList = newsPricesService.queryProductPrice(productName,lagVer,day);

        //无数据时返回空
        if(productPriceList==null || productPriceList.size()<=0) {
            return null;
        }

        StringBuffer buffer = new StringBuffer();
    	/*buffer.append("<div>"+productName+"</div>\r\n");*/

        buffer.append(InfoConstant.EN_first5);
        for (TCmsDomesticProductPrice tCmsDomesticProductPrice : productPriceList) {

            String name = tCmsDomesticProductPrice.getExtend1()==null?"":tCmsDomesticProductPrice.getExtend1();
//    		String deliver = "";

            BigDecimal priceB = tCmsDomesticProductPrice.getPrice();
            String price = "";
            if(priceB!=null) {

                priceB = priceB.setScale(2,BigDecimal.ROUND_HALF_UP);
                price = priceB.toString();
            }

            String currency = tCmsDomesticProductPrice.getPriceUnit()==null?"":tCmsDomesticProductPrice.getPriceUnit();
            String transportation = tCmsDomesticProductPrice.getExtend4()==null?"":tCmsDomesticProductPrice.getExtend4();

            Date priceDate = tCmsDomesticProductPrice.getPriceDate();
            String dateString = "";
            if(priceDate!=null) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                dateString = formatter.format(priceDate);
            }
            String location = tCmsDomesticProductPrice.getExtend3()==null?"":tCmsDomesticProductPrice.getExtend3();


            buffer.append("<tr>\r\n" +
                    "        <td>"+name+"</td>\r\n" +
//    				"        <td>"+deliver+"</td>\r\n" +
                    "        <td>"+price+"</td>\r\n" +
                    "        <td>"+currency+"</td>\r\n" +
//    				"        <td>"+term+"</td>\r\n" +
                    "        <td>"+location+"</td>\r\n" +
                    "        <td>"+transportation+"</td>\r\n" +
                    "        <td>"+dateString+"</td>\r\n" +
                    "    </tr>");
        }

        buffer.append("</table>");
        buffer.append(InfoConstant.later);
        return buffer.toString();
    }

    /**
     * 获取时间
     * @param day
     * @return
     * 2018年3月24日
     */
    @SuppressWarnings("static-access")
    public String getTime(int day) {

        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE,day);
        date=calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);

        return dateString;
    }

}
