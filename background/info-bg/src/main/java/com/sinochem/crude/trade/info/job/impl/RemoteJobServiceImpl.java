package com.sinochem.crude.trade.info.job.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.constants.Constants.PRICEMODE;
import com.sinochem.crude.trade.info.domain.Agio;
import com.sinochem.crude.trade.info.domain.ChannelSub;
import com.sinochem.crude.trade.info.domain.CrudeBasePrice;
import com.sinochem.crude.trade.info.domain.FuturesPrice;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.domain.InfoContent;
import com.sinochem.crude.trade.info.domain.Oil;
import com.sinochem.crude.trade.info.domain.PriceIndex;
import com.sinochem.crude.trade.info.domain.PriceIndexTemplate;
import com.sinochem.crude.trade.info.domain.ShipPoint;
import com.sinochem.crude.trade.info.job.RemoteJobService;
import com.sinochem.crude.trade.info.service.AgioService;
import com.sinochem.crude.trade.info.service.ChannelSubService;
import com.sinochem.crude.trade.info.service.CrudeBasePriceService;
import com.sinochem.crude.trade.info.service.FuturesPriceService;
import com.sinochem.crude.trade.info.service.InfoContentService;
import com.sinochem.crude.trade.info.service.InfoService;
import com.sinochem.crude.trade.info.service.OilService;
import com.sinochem.crude.trade.info.service.PriceIndexService;
import com.sinochem.crude.trade.info.service.PriceIndexTemplateService;
import com.sinochem.crude.trade.info.service.ShipPointService;
import com.sinochem.crude.trade.info.util.DBUtil;
import com.sinochem.crude.trade.info.util.Html2Text;
import com.sinochem.crude.trade.transport.remote.IDealPointsService;
import com.sinochem.crude.trade.transport.remote.IDealPointsVO;

/**
 * 远程数据
 * 
 * @author x
 */
@Service
public class RemoteJobServiceImpl implements RemoteJobService {

	@Autowired
	private DBUtil dBUtil;
	@Autowired
	private InfoService infoService; // 资讯
	@Autowired
	private InfoContentService infoContentService; //
	@Autowired
	private ChannelSubService channelSubService;
	@Autowired
	private ShipPointService shipPointService; // 航运
	@Autowired
	private FuturesPriceService futuresPriceService; // 指数
	@Autowired
	private CrudeBasePriceService crudeBasePriceService; // 官价
	@Autowired
	private AgioService agioService; // 贴水
	@Autowired
	private OilService oilService;
	@Autowired
	private PriceIndexService priceIndexService;
	@Autowired 
	PriceIndexTemplateService priceIndexTemplateService;
	@Autowired
	IDealPointsService iDealPointsService;

	private Log log = LogFactory.getLog(getClass());

	
	/**
	 * 远程资讯
	 */
	@Override
	public void saveInfos(Integer days) {
		Map<Integer, Object> conditionMap = new HashMap<>();
		conditionMap.put(1, days==null?7:days);
		List<Map<String, Object>> list = getRemoteData(infoSql(), conditionMap);
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		for (Map<String, Object> item : list) {

			String infoGrabId = item.get("infoGrabId").toString();

			Info info = new Info();
			info.setInfoGrabId(infoGrabId);
			// 如已存在不处理
			if (!CollectionUtils.isEmpty(infoService.queryByEntitys(info)))
				continue;

			String title = (String) item.get("title");
			info.setTitle(title);
			info.setAuthor((String) item.get("author"));
			info.setOrigin((String) item.get("origin"));
			info.setOriginUrl((String) item.get("originUrl"));
			info.setReleaseDate((Date) item.get("releaseDate"));

			String channelName = (String) item.get("channelName");
			String channelCode = "";
			String channelCodeEn = "";
			
			if ("油价与相关指数走势".equals(channelName)) {// channelName = "油价与相关指数";
				
				channelCode = "YYYXGZS";
			} else if ("航运市场信息".equals(channelName)) {
				// channelName = "最新成交点数";
				channelCode = "ZXCJDS";
				if (StringUtils.endsWith(title, "航运市场情况")) {
					channelCodeEn = "ZXCJDS_EN";
				}
			} else {
				// channelName = "实货成交";
				channelCode = "SHCJ";
			}

			//Long channelId = channelSubService.queryByName(channelName);
			ChannelSub cs = channelSubService.findByName(channelCode);
			if (cs != null) {
				info.setChannelId(cs.getId());
			}

			Map<Integer, Object> param = new HashMap<>();
			param.put(1, Integer.valueOf(infoGrabId));
			log.info("类型" + Integer.valueOf(infoGrabId).getClass().getName());
			List<Map<String, Object>> content = getRemoteData(infoTxtSql(), param);
			if (CollectionUtils.isEmpty(content))
				return;
			String txtHtml = (String) content.get(0).get("txtHtml");

			info.setStatus("30"); // 已发布
			info.setAliveFlag(Constants.ALIEVE_FLAG);
			info.setUuid(KeyGenUtils.newUuid());
			info.setArticleType("20"); // 普通文章
			info.setCreateDate(new Date());
			info.setStick("0");
			info.setExtend10("zh");
			infoService.insertRecord(info);

			InfoContent infoContent = new InfoContent();
			infoContent.setInfoId(info.getId());
			infoContent.setUuid(KeyGenUtils.newUuid());
			infoContent.setAliveFlag(Constants.ALIEVE_FLAG);
			infoContent.setCreateUser("1");
			infoContent.setCreateDate(new Date());
			//js路径替换
			String old = "<script src='../r/echarts.min.js'>";
			String newStr = "<script src='../../info/js/echarts.js'></script>";
			infoContent.setTexHtml(txtHtml.replace(old, newStr));
			//去除html标签后的文本
			infoContent.setTex(Html2Text.getContent(infoContent.getTexHtml()));
			infoContentService.insertRecord(infoContent);
			
			if (!StringUtils.equals(channelCodeEn, "")) {// 最新成交点数 复制一份英文
				cs = channelSubService.findByName(channelCodeEn);
				if (cs != null) {
					info.setChannelId(cs.getId());
				}
				
				String titleDateStr = StringUtils.replace(title, "航运市场情况", "");
				Date titleDate = DateTimeUtils.toDate(titleDateStr, "yy年M月d日");
				StringBuilder titleSb = new StringBuilder("Market Report ");
				titleSb.append(DateTimeUtils.toDateString(titleDate, "yyyy.MM.dd"));
				info.setId(null);
				info.setUuid(KeyGenUtils.newUuid());
				info.setTitle(titleSb.toString());
				info.setExtend10("en");
				info.setAuthor("Crude Oil Company");
				infoService.insertRecord(info);
				
				infoContent.setId(null);
				infoContent.setInfoId(info.getId());
				infoContent.setUuid(KeyGenUtils.newUuid());
				infoContentService.insertRecord(infoContent);
			}
		}
	}

	/**
	 * 远程航运
	 */
	@Override
	public void saveShipPoints(Integer days) {
		Map<Integer, Object> conditionMap = new HashMap<>();
		conditionMap.put(1, days==null?7:days);
		List<Map<String, Object>> list = getRemoteData(shipPointSql(), conditionMap);
		if (CollectionUtils.isEmpty(list)) {
			throw new RuntimeException(" 远程船务 数据为空");
		}
		for (Map<String, Object> item : list) {
			Date releaseDate = (Date)item.get("releaseDate");
			String dileveryRegion = (String)item.get("dileveryRegion");

			ShipPoint shipPoint = new ShipPoint();
			shipPoint.setReleaseDate(releaseDate);
			shipPoint.setDiliveryRegion(dileveryRegion);
			if(isRepeat(shipPoint))
				continue;
			
			shipPoint.setUuid(KeyGenUtils.newUuid());
			shipPoint.setAliveFlag(Constants.ALIEVE_FLAG);
			shipPoint.setPoint(BigDecimal.valueOf(Double.valueOf((String)item.get("point"))));
			shipPoint.setShipType((String)item.get("shipType"));
			shipPoint.setDischargePort((String)item.get("dischargePort"));
			shipPoint.setDispatchPort((String)item.get("dispatchPort"));
			shipPoint.setRemarks((String)item.get("remarks"));
			shipPoint.setBucketFreight(BigDecimal.valueOf(Double.valueOf((String)item.get("bucketFreight"))));
			try {
				shipPointService.insertShipPoint(shipPoint);
				//把航运点数抛给船务
				try {
					IDealPointsVO dp = new IDealPointsVO();
					dp.setAliveFlag(Constants.ALIEVE_FLAG);
					dp.setName("航运市场情况");
					dp.setDate(releaseDate); //发布日期 - 成交日期
					dp.setRegion(dileveryRegion); // 发货区域名称 - 地区
					dp.setPrice(shipPoint.getPoint()); // 航运点数  - 价格
					dp.setType(shipPoint.getShipType()); //船型 - 船型
					dp.setRefRoute(shipPoint.getDispatchPort() + " - " + shipPoint.getDischargePort()); //参考 路线
					dp.setRemark(shipPoint.getRemarks()); // 备注 - 备注
					dp.setSingleFare(shipPoint.getBucketFreight());//预估单桶运费 - 预估单桶运费
					iDealPointsService.saveDealPoints(dp);
				}catch(Exception e) {
					log.error("对接船务 插入失败 ");
					log.error("",e);
				}
			} catch (Exception e) {
				log.error("远程船务 插入失败 " + shipPoint);
				log.error("", e);
			}
		}
	}

	/**
	 * 远程指数
	 */
	@Override
	public void saveFutures(Integer days) {
		Map<Integer, Object> conditionMap = new HashMap<>();
		conditionMap.put(1, days==null?7:days);
		List<Map<String, Object>> list = getRemoteData(futuresPriceSql(), conditionMap);
		if (CollectionUtils.isEmpty(list)) {
			throw new RuntimeException(" 远程指数 数据为空");
		}
		for (Map<String, Object> item : list) {
			String crudeNameC = (String)item.get("crudeNameC");
			String crudeNameE = (String)item.get("crudeNameE");
			BigDecimal pricing = new BigDecimal((item.get("pricing").toString()));
			String isPricBase = (String)item.get("isPricBase");
			Date pricingDate = (Date)item.get("pricingDate");
			String note = (String)item.get("note");
		
			Oil oil = oilService.findByName(crudeNameE, crudeNameC);
			if(oil==null){
				oil = new Oil();
				oil.setUuid(UUID.randomUUID().toString().replace("-", ""));
				oil.setCrudeNameC(crudeNameC);
				oil.setCrudeNameE(crudeNameE);
				oil.setPriceBaseFlag(isPricBase);
				oil.setAliveFlag(Constants.ALIEVE_FLAG);
				oilService.insertRecord(oil);
			}
			
			FuturesPrice futurePrice = null;
			try {
				futurePrice = new FuturesPrice();
				futurePrice.setCrudeId(oil.getId());			
				futurePrice.setPricingDate(pricingDate);
				if(!isRepeat(futurePrice)){
					futurePrice.setNote(note);
					
					futurePrice.setPricing(pricing);
					futurePrice.setExtend1(crudeNameC);
					futurePrice.setExtend2(crudeNameE);
					futurePrice.setExtend3(isPricBase);
					futurePrice.setAliveFlag(Constants.ALIEVE_FLAG);
					futuresPriceService.insertFuturesPrice(futurePrice);
				}
			} catch (Exception e) {
				log.error("远程指数 插入失败 " + futurePrice);
				log.error("", e);
			}
			/*if ("1".equals(isPricBase)) {
				// isPricBase为1的数据，需要进指数表
				try {
					PriceIndex priceIndex = new PriceIndex();
					
					PriceIndexTemplate ptl =  priceIndexTemplateService.findBycrudeNameC(crudeNameC);
					if(ptl == null) {
						continue;
					}
					//设置模板id、指数值、指数日期
					priceIndex.setIndexTemplateId(ptl.getId());
					priceIndex.setSmeiValue(pricing);
					priceIndex.setIndexDate(pricingDate);
					
					//找到最近一天的记录
					PriceIndex piLast = priceIndexService.findLastUpdate(ptl.getId());
					
					//找到上一天的记录,计算涨跌、涨跌幅
					Map<String, Object> piPreMap = new HashMap<>();
					piPreMap.put("indexTemplateId", ptl.getId());
					piPreMap.put("indexDate", DateTimeUtils.toDateString(pricingDate, "yyyy-MM-dd"));
					PriceIndex piPre = priceIndexService.findPreRecordByTempId(piPreMap);
					
					
					if(piLast!=null){
						if (pricingDate.after(piLast.getIndexDate())) {// 最新指数的后一天，需要新增
							// do nothing
						} else if (pricingDate.before(piLast.getIndexDate())) {// 最新指数的前几天
							continue;
						} else {// 最新指数的同一天，需要更新
							priceIndex.setUuid(piLast.getUuid());
						}
						 
					}
					if (piPre!=null) {
						BigDecimal riseFall =  priceIndex.getSmeiValue().subtract(piPre.getSmeiValue());
						priceIndex.setRiseFall(riseFall);
						
						if(piPre.getSmeiValue().compareTo(BigDecimal.ZERO)!=0){
							BigDecimal riseFallRate =  riseFall.divide(piPre.getSmeiValue(), 4);
							priceIndex.setRiseFallRate(riseFallRate);
						}
					}
					priceIndexService.saveOrUpdatePriceIndex(priceIndex, null);
				} catch (BizException e) {
					log.error("", e);
				}
			}*/
		}
		log.info("---->完结");
	}

	/**
	 * 远程官价
	 */
	@Override
	public void saveCrudeBasePrices(Integer days) {
		Map<Integer, Object> conditionMap = new HashMap<>();
		conditionMap.put(1, days==null?7:days);
		List<Map<String, Object>> list = getRemoteData(crudeBasePriceSql(), conditionMap);
		if (CollectionUtils.isEmpty(list)) {
			throw new RuntimeException(" 远程官价 数据为空");
		}
		for (Map<String, Object> item : list) {
			String crudeNameC = (String) item.get("crudeNameC");
			String crudeNameE = (String) item.get("crudeNameE");
			String isPricBase = (String) item.get("isPricBase");
			String priceMode = (String) item.get("priceMode");
			String note = (String) item.get("note");
			Date pricingDate = (Date) item.get("pricingDate");

			CrudeBasePrice crudeBasePrice = new CrudeBasePrice();
			Oil oil = oilService.findByName(crudeNameE, crudeNameC);
			if(oil==null){
				oil = new Oil();
				oil.setUuid(UUID.randomUUID().toString().replace("-", ""));
				oil.setCrudeNameC(crudeNameC);
				oil.setCrudeNameE(crudeNameE);
				oil.setPriceBaseFlag(isPricBase);
				oilService.insertRecord(oil);
				crudeBasePrice.setCrudeId(oil.getId());
			}else
				crudeBasePrice.setCrudeId(oil.getId());
			crudeBasePrice.setPricingDate(pricingDate);
			
			if(isRepeat(crudeBasePrice))
				continue;

			crudeBasePrice.setPricing(item.get("pricing")==null?BigDecimal.ZERO:BigDecimal.valueOf((Double)item.get("pricing")));
			crudeBasePrice.setExtend1(crudeNameC); // crudeNameC
			crudeBasePrice.setExtend2(crudeNameE); // crudeNameE
			crudeBasePrice.setExtend3(this.getExtend3Name(priceMode)); //priceMode
			crudeBasePrice.setExtend4(isPricBase); //isPricBase
			crudeBasePrice.setNote(note); // 备注
			try {
				crudeBasePriceService.insertBasePrice(crudeBasePrice);
			} catch (Exception e) {
				log.error("远程官价 插入失败 " + crudeBasePrice);
				log.error("", e);
			}

		}
	}

	/**
	 * 远程贴水
	 */
	@Override
	public void saveAgios(Integer days) {
		Map<Integer, Object> conditionMap = new HashMap<>();
		conditionMap.put(1, days==null?7:days);
		List<Map<String, Object>> list = getRemoteData(agioSql(), conditionMap);
		if (CollectionUtils.isEmpty(list)) {
			throw new RuntimeException(" 远程贴水 数据为空");
		}
		log.info("取到的数据条数 --->" + list.size());
		int discard = 0;
		int valid = 0;
		for (Map<String, Object> item : list) {
			String crudeNameC = (String) item.get("crudeNameC");
			String crudeNameE = (String) item.get("crudeNameE");
			Date pricingDate = (Date) item.get("pricingDate");
			Agio agio = new Agio();
			Oil oil = oilService.findByName(crudeNameE, crudeNameC);
			if(oil==null){
				oil = new Oil();
				oil.setUuid(KeyGenUtils.newUuid());
				oil.setCrudeNameC(crudeNameC);
				oil.setCrudeNameE(crudeNameE);
				oilService.insertRecord(oil);
				agio.setCrudeId(oil.getId());
			}else
				agio.setCrudeId(oil.getId());
			agio.setPricingDate(pricingDate);
			
			if(isRepeat(agio)){
				discard++;
				continue;
			}
			// 置为最新
			agio.setLoadingMonth((String) item.get("loadingMonth"));
			agio.setChange1((String) item.get("change1"));
			agio.setPricing(BigDecimal.valueOf((Double) item.get("pricing")));
			agio.setExtend1(crudeNameC);  //中文名
			agio.setExtend2(crudeNameE);  //英文名
			agio.setExtend3(getExtend3Name((String) item.get("extend3"))); //油价基准
			agio.setExtend4((String) item.get("note")); // 备注
			agio.setExtend5(item.get("extend5")==null?"":item.get("extend5").toString());	//效益
			agio.setCreatePerson("1");
			agio.setCreateDate(DateTimeUtils.currentDate());
			agio.setUuid(KeyGenUtils.newUuid());
			agio.setTransportClause((String) item.get("transportClause"));
			try {
				agioService.insertAgio(agio);
				valid++;
			} catch (Exception e) {
				log.error("远程贴水 插入失败 " + agio);
				log.error("", e);
			}
		}
		log.info("丢弃数据条数  --->" + discard);
		log.info("新增数据条数  --->" + valid);
	}

	/**
	 * 远程数据
	 */
	private List<Map<String, Object>> getRemoteData(String sql, Map<Integer, Object> conditionMap) {
		log.info("拉取 - 远程数据");
		return dBUtil.queryAll(sql.toString(), conditionMap);
	}

	/**
	 * 判断拉取的远程数据是否重复
	 */
	public boolean isRepeat(Object obj) {
		String cla = obj.getClass().getName();
		switch(cla) {
			case "com.sinochem.crude.trade.info.domain.Agio":
				return !CollectionUtils.isEmpty(agioService.queryByEntitys((Agio) obj));
			case "com.sinochem.crude.trade.info.domain.CrudeBasePrice":
				return !CollectionUtils.isEmpty(crudeBasePriceService.queryByEntitys((CrudeBasePrice) obj));
			case "com.sinochem.crude.trade.info.domain.Info":
				return !CollectionUtils.isEmpty(infoService.queryByEntitys((Info) obj));
			case "com.sinochem.crude.trade.info.domain.ShipPoint":
				return !CollectionUtils.isEmpty(shipPointService.queryByEntitys((ShipPoint) obj));
			case "com.sinochem.crude.trade.info.domain.FuturesPrice":
				return !CollectionUtils.isEmpty(futuresPriceService.queryByEntitys((FuturesPrice) obj));
			case "com.sinochem.crude.trade.info.domain.PriceIndex":
				return !CollectionUtils.isEmpty(priceIndexService.queryByEntitys((PriceIndex) obj));
		}
		return false;
	}

	/**
	 * 远程贴水
	 * @return
	 */
	private String agioSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(" t1.CRUDE_NAME_C as crudeNameC,");
		sql.append(" t1.CRUDE_NAME_E as crudeNameE,");
		sql.append(" t1.PRIC_MODE as extend3,");
		sql.append(" t.AGIO as pricing,");
		sql.append(" t.PRICING_DATE as pricingDate,");
		sql.append(" t.NOTE as note,");
		sql.append(" t.LOADING_MONTH as loadingMonth,");
		sql.append(" t.transport_clause as transportClause,");
		sql.append(" t.change as change1,");
		sql.append(" t.xiaoyi as extend5");
		sql.append(" FROM");
		sql.append(" om_stock_agio_his t left join sys_crude t1 on  t1.ID");
		sql.append(" = t.CRUDE_ID");
		sql.append(" WHERE");
		sql.append(" t.PRICING_DATE is not null and t.AGIO is not null");
		sql.append(" and t.PRICING_DATE > date_sub(NOW(), INTERVAL ? DAY)");
		sql.append(" order by t.PRICING_DATE asc");
		return sql.toString();
	}

	/**
	 * 远程官价
	 * 
	 * @return
	 */
	private String crudeBasePriceSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(" b.CRUDE_NAME_C as crudeNameC,");
		sql.append(" b.CRUDE_NAME_E as crudeNameE,");
		sql.append(" b.IS_PRIC_BASIS as isPricBase,");
		sql.append(" b.PRIC_MODE as priceMode,");
		sql.append(" a.PRICING as pricing,");
		sql.append(" a.PRICING_DATE as pricingDate,");
		sql.append(" a.NOTE as note");
		sql.append(" FROM");
		sql.append(" om_pricing_basis_his a,");
		sql.append(" sys_crude b");
		sql.append(" WHERE");
		sql.append(" a.CRUDE_ID = b.ID");
		sql.append(" and a.PRICING is not null");
		sql.append(" and a.PRICING_DATE is not null");
		sql.append(" AND a.PRICING_DATE > date_sub(NOW(), INTERVAL ? DAY)");
		sql.append(" ORDER BY a.PRICING_DATE asc");
		return sql.toString();
	}

	/**
	 * 远程指数
	 * 
	 * @return
	 */
	private String futuresPriceSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(" a.CRUDE_NAME_C AS crudeNameC,");
		sql.append(" a.CRUDE_NAME_E AS crudeNameE,");
		sql.append(" a.IS_PRIC_BASIS AS isPricBase,");
		sql.append(" t.PRICING AS pricing,");
		sql.append(" t.PRICING_DATE AS pricingDate,");
		sql.append(" t.NOTE AS note");
		sql.append(" FROM");
		sql.append(" om_futures_price_his t,");
		sql.append(" sys_crude a");
		sql.append(" WHERE");
		sql.append(" t.CRUDE_ID = a.ID");
		sql.append(" AND t.PRICING is not null");
		sql.append(" AND t.PRICING_DATE is not null");
		sql.append(" AND t.PRICING_DATE > date_sub(NOW(), INTERVAL ? DAY)");
		sql.append(" order by t.PRICING_DATE asc"); // isPricBase为1的数据，需要进指数表
		return sql.toString();
	}

	/**
	 * 远程航运
	 * 
	 * @return
	 */
	private String shipPointSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(" t.release_date as releaseDate,");
		sql.append(" t.ws as point,");
		sql.append(" t.frt_b as bucketFreight,");
		sql.append("  t.remarks as remarks,");
		sql.append(" 'VLCC' as shipType,");
		sql.append(" a.NAME_E as dispatchPort,");
		sql.append(" b.NAME_E as dischargePort,");
		sql.append(" c.NAME_C as dileveryRegion");
		sql.append(" FROM");
		sql.append(" gm_ws_his t,");
		sql.append(" sys_port a,");
		sql.append(" sys_port b,");
		sql.append(" sys_origin_area c");
		sql.append(" WHERE");
		sql.append(" t.port_shipmert_id = a.ID");
		sql.append(" AND t.port_discharge_id = b.id");
		sql.append(" and t.origin_area_id = c.ID");
		sql.append(" and t.release_date is not null");
		sql.append(" and t.ws is not null");
		sql.append(" and t.release_date > date_sub(NOW(), INTERVAL ? DAY)");
		sql.append(" ORDER BY"); // RELEASE_DATE + DILIVERY_REGION, 为逻辑主键，如果这2项一致说明是同一条数据；
		sql.append(" t.release_date asc");
		return sql.toString();
	}

	/**
	 * 远程资讯
	 * 
	 * @return
	 */
	private String infoSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(" t.content_id AS infoGrabId,");
		sql.append(" t2.channel_name AS channelName,");
		sql.append(" t.title AS title,");
		sql.append(" t.author AS author,");
		sql.append(" t.origin AS origin,");
		sql.append(" t.origin_url AS originUrl,");
		sql.append(" t.release_date AS releaseDate");
		sql.append(" FROM");
		sql.append(" jc_content_ext t,");
		sql.append(" jc_content_channel t1,");
		sql.append(" jc_channel_ext t2,");
		sql.append(" jc_content_txt t3");
		sql.append(" WHERE");
		sql.append(" t.content_id = t1.content_id");
		sql.append(" AND t1.channel_id = t2.channel_id");
		sql.append(" AND t3.content_id = t.content_id");
		sql.append(" AND t2.channel_name IN (");
		sql.append(" '油价与相关指数走势', "); // 油价与相关指数
		sql.append(" '航运市场信息',");// 最新成交点数
		sql.append(" '实货市场信息',");// 原油实货成交信息
		sql.append(" '实货动态', ");
		sql.append(" '原油市场' )");
		sql.append(" AND t.release_date > date_sub(NOW(), INTERVAL ? DAY)");
		sql.append(" ORDER BY t.content_id asc");
		return sql.toString();
	}

	/**
	 * 远程资讯
	 * 
	 * @return
	 */
	private String infoTxtSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT");
		sql.append(" t.content_id AS infoGrabId,");
		sql.append(" t.txt AS txtHtml");
		sql.append(" FROM");
		sql.append(" jc_content_txt t");
		sql.append(" WHERE");
		sql.append(" t.content_id=?");
		return sql.toString();
	}
	
	private String getExtend3Name(String priceType) {
		for(PRICEMODE p : Constants.PRICEMODE.values()) {
			if(p.getPriceType().equals(priceType)) {
				return p.getName();
			}
		}
		return "";
	}
}
