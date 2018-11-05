package com.sinochem.crude.trade.news.job;

import java.math.BigDecimal; 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinochem.crude.trade.news.constant.InfoConstant;
import com.sinochem.crude.trade.news.dao.TCmsChannelSubMapper;
import com.sinochem.crude.trade.news.dao.TCmsDomesticProductPriceMapper;
import com.sinochem.crude.trade.news.dao.TCmsInfoContentMapper;
import com.sinochem.crude.trade.news.dao.TCmsInfoMapper;
import com.sinochem.crude.trade.news.dao.TSysCodeSetMapper;
import com.sinochem.crude.trade.news.model.TCmsChannelSub;
import com.sinochem.crude.trade.news.model.TCmsDomesticProductPrice;
import com.sinochem.crude.trade.news.model.TCmsInfo;
import com.sinochem.crude.trade.news.model.TCmsInfoContent;
import com.sinochem.crude.trade.news.model.TSysCodeSet;
import com.sinochem.crude.trade.news.model.query.InfoQuery;
import com.sinochem.crude.trade.news.model.query.ProductCodeQuery;
import com.sinochem.crude.trade.news.model.query.ProductInfoQuery;
import com.sinochem.crude.trade.news.utils.DateTimeUtils;
import com.sinochem.crude.trade.news.utils.KeyGenUtils;

/**
 * 定时生成产品价格的咨询信息
 * @author 10907
 *
 */
@Component
public class InfoTask {
	
	@Autowired
	private TSysCodeSetMapper sysCodeSetMapper;
	
	@Autowired
	private TCmsDomesticProductPriceMapper productPriceMapper;
	
	@Autowired
	private TCmsChannelSubMapper channelSubMapper;
	
	@Autowired
	private TCmsInfoMapper infoMapper;
	
	@Autowired
	private TCmsInfoContentMapper infoContentMapper;
	
    /**
     * 今天
     */
    private int today = 0;
    
    /**
     * 昨天
     */
    private int yesterday = -1;
	
	
	
//    @Scheduled(cron = "0 0/5 * * * ?")
    public void updateTodyInfoTable() {
    	
    	//产生prices1
    	String htmlText_prices1 = createTable5(today,InfoConstant.langVel_EN, InfoConstant.gasoline_international);
    	createInfo(today,InfoConstant.gasoline_international, htmlText_prices1, InfoConstant.langVel_EN);
    	
    	//产生prices2
    	String htmlText_prices2 = createTable4(today,InfoConstant.langVel_EN, InfoConstant.diesel_international);
    	createInfo(today,InfoConstant.diesel_international, htmlText_prices2, InfoConstant.langVel_EN);

    	//产生prices3
    	StringBuffer htmlText_prices3 = new StringBuffer();
    	String htmlText_prices3_92 = createTable1(today,InfoConstant.langVel_EN, InfoConstant.gasoline92_provinces);
    	if(htmlText_prices3_92 != null){

    		htmlText_prices3.append(htmlText_prices3_92);
		}
    	String htmlText_prices3_95 = createTable1(today,InfoConstant.langVel_EN, InfoConstant.gasoline95_provinces);
    	if(htmlText_prices3_95 != null){

    		htmlText_prices3.append(htmlText_prices3_95);
		}
		if(htmlText_prices3.toString().length()>0){

    		htmlText_prices3.append(InfoConstant.later2);
    		createInfo(today,InfoConstant.gasoline92_provinces, htmlText_prices3.toString(), InfoConstant.langVel_EN);
		}


    	//产生prices4
    	String htmlText_prices4 = createTable2(today,InfoConstant.langVel_EN, InfoConstant.gasoline95);
    	createInfo(today,InfoConstant.gasoline95, htmlText_prices4, InfoConstant.langVel_EN);
    	
    	//产生prices5
    	String htmlText_prices5 = createTable3(today,InfoConstant.langVel_EN, InfoConstant.shandong_refine_gasoline);
    	createInfo(today,InfoConstant.shandong_refine_gasoline, htmlText_prices5, InfoConstant.langVel_EN);
    	
    	//产生prices6
    	String htmlText_prices6 = createTable3(today,InfoConstant.langVel_EN, InfoConstant.shandong_refine_diesel_1_2);
    	createInfo(today,InfoConstant.shandong_refine_diesel_1_2, htmlText_prices6, InfoConstant.langVel_EN);
    	
    	//产生prices7
    	String htmlText_prices7 = createTable2(today,InfoConstant.langVel_EN, InfoConstant.diesel_northEast_northChina_northWest,InfoConstant.diesel_southChina_southWest,InfoConstant.diesle_southChina_southWest);
    	createInfo(today,InfoConstant.diesel_northEast_northChina_northWest, htmlText_prices7, InfoConstant.langVel_EN);
    	
    	//产生prices8
    	String htmlText_prices8 = createTable1(today,InfoConstant.langVel_EN, InfoConstant.diesel_domestic);
    	createInfo(today,InfoConstant.diesel_domestic, htmlText_prices8, InfoConstant.langVel_EN);
    	
    }
    
    
//    @Scheduled(cron = "0 0/30 * * * ?")
    public void updateYesterdayInfoTable() {
    	
    	//产生prices1
    	String htmlText_prices1 = createTable5(yesterday,InfoConstant.langVel_EN, InfoConstant.gasoline_international);
    	createInfo(yesterday,InfoConstant.gasoline_international, htmlText_prices1, InfoConstant.langVel_EN);
    	
    	//产生prices2
    	String htmlText_prices2 = createTable4(yesterday,InfoConstant.langVel_EN, InfoConstant.diesel_international);
    	createInfo(yesterday,InfoConstant.diesel_international, htmlText_prices2, InfoConstant.langVel_EN);

		//产生prices3
		StringBuffer htmlText_prices3 = new StringBuffer();
		String htmlText_prices3_92 = createTable1(yesterday,InfoConstant.langVel_EN, InfoConstant.gasoline92_provinces);
		if(htmlText_prices3_92 != null){

			htmlText_prices3.append(htmlText_prices3_92);
		}
		String htmlText_prices3_95 = createTable1(yesterday,InfoConstant.langVel_EN, InfoConstant.gasoline95_provinces);
		if(htmlText_prices3_95 != null){

			htmlText_prices3.append(htmlText_prices3_95);
		}
		if(htmlText_prices3.toString().length()>0){

			htmlText_prices3.append(InfoConstant.later2);
			createInfo(yesterday,InfoConstant.gasoline92_provinces, htmlText_prices3.toString(), InfoConstant.langVel_EN);
		}
    	
    	createInfo(yesterday,InfoConstant.gasoline92_provinces, htmlText_prices3.toString(), InfoConstant.langVel_EN);
    	
    	//产生prices4
    	String htmlText_prices4 = createTable2(yesterday,InfoConstant.langVel_EN, InfoConstant.gasoline95);
    	createInfo(yesterday,InfoConstant.gasoline95, htmlText_prices4, InfoConstant.langVel_EN);
    	
    	//产生prices5
    	String htmlText_prices5 = createTable3(yesterday,InfoConstant.langVel_EN, InfoConstant.shandong_refine_gasoline);
    	createInfo(yesterday,InfoConstant.shandong_refine_gasoline, htmlText_prices5, InfoConstant.langVel_EN);
    	
    	//产生prices6
    	String htmlText_prices6 = createTable3(yesterday,InfoConstant.langVel_EN, InfoConstant.shandong_refine_diesel_1_2);
    	createInfo(yesterday,InfoConstant.shandong_refine_diesel_1_2, htmlText_prices6, InfoConstant.langVel_EN);
    	
    	//产生prices7
    	String htmlText_prices7 = createTable2(yesterday,InfoConstant.langVel_EN, InfoConstant.diesel_northEast_northChina_northWest,InfoConstant.diesel_southChina_southWest,InfoConstant.diesle_southChina_southWest);
    	createInfo(yesterday,InfoConstant.diesel_northEast_northChina_northWest, htmlText_prices7, InfoConstant.langVel_EN);
    	
    	//产生prices8
    	String htmlText_prices8 = createTable1(yesterday,InfoConstant.langVel_EN, InfoConstant.diesel_domestic);
    	createInfo(yesterday,InfoConstant.diesel_domestic, htmlText_prices8, InfoConstant.langVel_EN);
    	
    }
    
    
    
    
    /**
     * 汽油：92#（国四/国五/调和/95#分省价格/柴油国内市场估价，三者表格样式相同
     * 表格样式一
     * 2018年3月22日
     */
    public String createTable1(int day,String lagVer,String productName) {
    	
    	//获取价格列表
    	List<TCmsDomesticProductPrice> productPriceList = queryProductPrice(productName,lagVer,day);
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
			
    		List<TCmsDomesticProductPrice> tempList = queryProductPrice(productName,lagVer,day);
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
    	List<TCmsDomesticProductPrice> productPriceList =  queryProductPrice(productName,lagVer,day);
    	
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
    	List<TCmsDomesticProductPrice> productPriceList = queryProductPrice(productName,lagVer,day);
    	
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
    	List<TCmsDomesticProductPrice> productPriceList = queryProductPrice(productName,lagVer,day);
    	
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
     * 创建咨询信息
     * @param lagVel
     * @param day
     * @param htmlText
     * 2018年3月22日
     */
    public void createInfo(int day,String productName,String htmlText,String lagVel) {
    	
    	String channelCode = InfoConstant.channelType.get(productName);
    	//无PRICES时不存储数据
    	if(channelCode==null || "".equals(channelCode)) {
    		return ;
    	}
    	
    	//无数据时不存储
    	if(htmlText==null || "".equals(htmlText)) {
    		return ;
    	}
    	
    	String title = getTime(day)+"  ";
    	if(channelCode.equals("PRICES7")) {
    		productName = "0# Gasoil Wholesale Prices of China";
    	}else if(channelCode.equals("PRICES3")) {
    		productName = "Prices of gasoline #92 and #95";
    	}
    	title = title+productName;
    	
    	//查询频道信息
    	TCmsChannelSub channelSub = channelSubMapper.selectByChannelCode(channelCode);
    	
    	//查询是否存在
    	InfoQuery infoQuery = new InfoQuery();
    	infoQuery.setChannelId(channelSub.getId());
    	infoQuery.setExtend10(InfoConstant.langVel_EN);
    	infoQuery.setTitle(title);
    	TCmsInfo mysqlInfo = infoMapper.selectByChannelTitle(infoQuery);
    	if(mysqlInfo!=null) {
    		
    		TCmsInfoContent mysqlInfoContent =  infoContentMapper.selectByInfoId(mysqlInfo.getId());
    		if(mysqlInfoContent!=null) {
    			mysqlInfoContent.setTexHtml(htmlText);
    			infoContentMapper.updateByPrimaryKeySelective(mysqlInfoContent);
    		}
    	}else {
    		//创建咨询
    		TCmsInfo info = new TCmsInfo();

    		info.setStick("0");
    		info.setUuid(KeyGenUtils.newUuid());
    		info.setStatus("30");
    		info.setAuthor("金凯讯");
    		info.setIsShowDisclaimer("1");// 免责申明
    		
    		info.setCreateDate(DateTimeUtils.currentDate());
    		info.setReleaseDate(DateTimeUtils.currentDate());
    		info.setValidBegin(DateTimeUtils.currentDate());

    		info.setChannelId(channelSub.getId());
    		info.setTitle(title);
    		
    		info.setExtend10(lagVel);
    		info.setIsShowOrigin("0");
    		info.setAliveFlag("1");
    		
    		info.setBrowseCount(0);
    		info.setCommentCount(0);
    		info.setShareCount(0);
    		info.setCollectionCount(0);
    		info.setTrampleCount(0);
    		info.setFabulousCount(0);
    		
    		infoMapper.insertSelective(info);
    		
    		//创建咨询内容
    		TCmsInfoContent infoContent = new TCmsInfoContent();
    		infoContent.setUuid(KeyGenUtils.newUuid());
    		infoContent.setInfoId(info.getId());
    		infoContent.setAliveFlag("1");
    		infoContent.setCreateDate(DateTimeUtils.currentDate());
    		infoContent.setTexHtml(htmlText);
    		
    		infoContentMapper.insertSelective(infoContent);
    	}
    }
    
    /**
     * 获取商品价格列表
     * @param productName
     * @return
     * 2018年3月22日
     */
    public List<TCmsDomesticProductPrice> queryProductPrice(String productName,String lagVer,int day){
    	

    	//查询PRODUCT_CODE
    	ProductCodeQuery codeQuery = new ProductCodeQuery();
    	codeQuery.setItemName(productName);
    	codeQuery.setLangVer(lagVer);
    	
    	List<TSysCodeSet> sysCodeSetList = sysCodeSetMapper.selectByItemName(codeQuery);
    	if(sysCodeSetList==null) {
    		return null;
    	}
    	List<TCmsDomesticProductPrice> productPriceList = new ArrayList<>();
    	for (TSysCodeSet sysCodeSet : sysCodeSetList) {
			
    		ProductInfoQuery infoQuery = new ProductInfoQuery();
    		infoQuery.setPriceDate(getTime(day));
    		infoQuery.setProductCode(sysCodeSet.getItemCode());
    		infoQuery.setProductName(productName);
    		infoQuery.setExtend10(lagVer);
    		List<TCmsDomesticProductPrice> tempList = productPriceMapper.selectByProductCode(infoQuery);
    		productPriceList.addAll(tempList);
		}
    
    	return productPriceList;
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
    
    
    
    public static void main(String[] args) {
		
	    BigDecimal a= new BigDecimal("123.02");
	    System.out.print(a.toString());
	}
    
    
    
}
