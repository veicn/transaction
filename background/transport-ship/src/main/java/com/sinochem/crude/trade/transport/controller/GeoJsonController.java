package com.sinochem.crude.trade.transport.controller;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import jodd.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.util.JSONPObject;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.Point;
import org.geojson.domain.AllShipInfo;
import org.geojson.domain.AllShipnfoResult;
import org.geojson.domain.CurrentData;
import org.geojson.domain.CurrentDataResult;
import org.geojson.domain.CurrentInfo;
import org.geojson.domain.ExpectedData;
import org.geojson.domain.ExpectedDataResult;
import org.geojson.domain.ExpectedInfo;
import org.geojson.domain.HistoryData;
import org.geojson.domain.HistoryDataResult;
import org.geojson.domain.HistoryPortEnData;
import org.geojson.domain.HistoryPortEnInfo;
import org.geojson.domain.HistoryPortEnResult;
import org.geojson.domain.HistoryPortResult;
import org.geojson.domain.LocationData;
import org.geojson.domain.LocationDataResult;
import org.geojson.domain.MyWayPoint;
import org.geojson.domain.OppositeInfo;
import org.geojson.domain.OppositeInfoResult;
import org.geojson.domain.PlanCurrentDataData;
import org.geojson.domain.PlanCurrentDataResult;
import org.geojson.domain.PlanData;
import org.geojson.domain.PlanDataData;
import org.geojson.domain.PlanDataResult;
import org.geojson.domain.ProductOilWayPointInfoResult;
import org.geojson.domain.ShipInfo;
import org.geojson.domain.ShipInfoResult;
import org.geojson.domain.WayPointInfo;
import org.geojson.domain.WayPointInfoResult;
import org.geojson.httpclient.Arith;
import org.geojson.httpclient.HttpUtils;
import org.geojson.model.CurrentVO;
import org.geojson.model.ExpectedVO;
import org.geojson.model.HistoryPortVO;
import org.geojson.model.LocationVO;
import org.geojson.model.OppositeVO;
import org.geojson.model.PlanCurrentVO;
import org.geojson.model.PlanVO;
import org.geojson.model.Route;
import org.geojson.model.ShipVO;
import org.geojson.url.DataCenterEarthUrlServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.URLMapping;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.domain.MapPort;
import com.sinochem.crude.trade.transport.service.MapPortService;
import com.sinochem.crude.trade.transport.service.SysShipService;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;

/**
 * 谷歌地图相关接口
 * 以下所有接口返回GEOJSON格式数据
 * 如果需要返回JSONP格式数据，访问的时候需要带上callbackparam这个参数
 * 
 * VO对象有些乱，源于对方接口不断修改，调整。。。
 * 
 * @author niuwk
 *
 */
@Controller
public class GeoJsonController  {
	
	Log log = LogFactory.getLog(GeoJsonController.class);
	
	
	@Autowired
	private DataCenterEarthUrlServer dataCenterEarthUrlServer;
	
	@Autowired
	private SysShipService sysShipService;//船舶服务接口
	
	@Autowired
	private MapPortService papPortService;//K可视化港口接口
	
	// private Map<String,String> countryMap = new HashMap<String,String>();  
     
      /* @PostConstruct  
       public void init(){  
    		System.out.println("QQQQQQQQQQQQQQQQQQ:"+ Locale.getDefault());
    		Locale locale1 = new Locale("en", "US");
    		Locale.setDefault(locale1);
    		System.out.println(Locale.getDefault());
    		Locale[] locales = java.util.Locale.getAvailableLocales();
    		for (Locale locale : locales) {
    			if (!locale.getDisplayCountry().isEmpty() && !locale.getCountry().isEmpty()) {
    				System.out.println(locale.getDisplayCountry());
    				System.out.println(locale.getCountry().toLowerCase());
    				countryMap.put(locale.getDisplayCountry(), locale.getCountry().toLowerCase());
    			}			
    		} 
       } */ 
	
	 
	
	 /**
	  * 根据IMO、MMSI、船名、查船舶信息
	  * @param keyword 关键字
	  * @param user
	  * @return
	  */
	   @WithoutAccess
		//@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.INSPECTION,Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.MEMBER_APTITUDE_7,Constants.CHARTERER})
		@ResponseBody
		@RequestMapping(value=URLMapping.FIND_SYS_SHIP_BY_KEYWORD)
		public ResultDatas<Object> findSysShipByKeyword(@RequestBody ShipVO ship,CurrentUser users) {
			ResultDatas<Object> res = new ResultDatas<>();
			try {
				//验证登录信息
				/*if (user == null){
					throw new TransportException(TransportException.TYPE.ITSH001);
				}*/
				if (StringUtils.isBlank(ship.getKeyword())){
					throw new TransportException(TransportException.TYPE.ITSH701);
				}
				List<Map<String,Object>>  list = sysShipService.findSysShipByImoOrMmsiOrName(ship.getKeyword());
				res.setDatas(list);
			} catch (BizException e) {
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setCode(e.getCode());
				res.setMessage(e.getMessage());
			}  catch (Exception e ) {
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setCode(Constants.EXCEPTION_CODE);
				res.setMessage(Constants.EXCEPTION_CODE);
			}
			return res;
		}
	 
	
	
	/**
	 * 下拉框港口列表接口
	 * @param user
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/api/ports.json")
	public ResultDatas<Object>  mapPorts(CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try{
			List<MapPort>  ports = papPortService.findMapPorts();
			res.setDatas(ports);
		}catch (Exception e ) {
			e.printStackTrace();
		}
		return res;
		
	}
	
	/**
	 * 下拉框外国港口列表接口
	 * @param user
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/api/foreign/ports.json")
	public ResultDatas<Object>  mapForeignPorts(@RequestBody ShipVO ship,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("keyword", ship.getKeyword());
			List<MapPort>  ports = papPortService.findMapForeignPorts(map);
			res.setDatas(ports);
		}catch (Exception e ) {
			e.printStackTrace();
		}
		return res;
		
	}
	
	/**
	 * 
	 * App 访问船舶信息接口
	 * @param ship
	 * @param user
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/api/appjson/ship.json")
	public ResultDatas<Object> shipApp(/*@RequestBody*/ ShipVO ship,CurrentUser users) {
		ResultDatas<Object> res = new ResultDatas<>();
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		String result =null;
		try {
			//验证登录信息
			/*if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}*/
			if (StringUtils.isNotBlank(ship.getImo())){
				url+="/sinochem/api/vessel?imo="+ship.getImo();
				long begin = System.currentTimeMillis(); 
				result=HttpUtils.sendGetUTF8(url, "");
				long end = System.currentTimeMillis() - begin; 
				log.info("url:/api/appjson/ship.json  used :" +end +"milliseconds");
			}
			ShipInfoResult shipInfoResult = JSON.parseObject(result, new TypeReference<ShipInfoResult>() {});
			res.setDatas(shipInfoResult);
		} catch (BizException e) {
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		}  catch (Exception e ) {
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
		
	}
	
	

	/**
	 * 船舶信息接口
	 * @return
	 */
	@WithoutAccess
	//@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.INSPECTION,Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.MEMBER_APTITUDE_7,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value="/api/geojson/vessel.json")
	public Object ship2(@RequestBody ShipVO ship,CurrentUser users) {
		//验证登录信息
		/*if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}*/
		
		//ObjectMapper mapper = new ObjectMapper();
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		FeatureCollection fc = new FeatureCollection();
		fc.setType("FeatureCollection");
		String result =null;
		
		try{
			if (StringUtils.isNotBlank(ship.getImo())){
				url+="/sinochem/api/vessel?imo="+ship.getImo();
				long begin = System.currentTimeMillis(); 
				result=HttpUtils.sendGetUTF8(url, "");
				long end = System.currentTimeMillis() - begin; 
				log.info("url:/api/geojson/vessel.json  used :" +end +"milliseconds");
			}
			
			ShipInfoResult shipInfoResult = JSON.parseObject(result, new TypeReference<ShipInfoResult>() {});
			List<ShipInfo> shipInfoList = shipInfoResult.getData();
			for(ShipInfo shipInfo:shipInfoList){
				Feature feature = new Feature();
				feature.setType("Feature");
				feature.setId(shipInfo.getVesselId());
				Point point = new Point();
				point.setType("Point");
				String lat =shipInfo.getLat();//纬度
				String lng =shipInfo.getLon();//经度
				Double latd = 1000.0;
				Double lngd = 1000.0;
				Map<String,Object> properties = new HashMap<String,Object>();
				Map<String,Object> shipMessageProperties = new HashMap<String,Object>();
				if(StringUtils.isNotBlank(lat)){
					latd = Double.parseDouble(lat);
					properties.put("lat", latd);//纬度
				}
				if(StringUtils.isNotBlank(lng)){
					lngd = Double.parseDouble(lng);
					properties.put("lng", lngd);//经度
				}
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
				
				point.setCoordinates(new double[]{lngd,latd,111.16});//海拔目前暂无
				feature.setGeometry(point);
				shipMessageProperties.put("Beam", shipInfo.getBeam()+"m");//宽
				shipMessageProperties.put("Cubic", shipInfo.getCubic()+"m³");//立方
				shipMessageProperties.put("MMSI", shipInfo.getMmsi());//MMSI
				shipMessageProperties.put("shipSpeed", shipInfo.getSog());//船速
				shipMessageProperties.put("IMO", shipInfo.getImo());//IMO
				shipMessageProperties.put("callSign", shipInfo.getCallSign());//呼号
				shipMessageProperties.put("destination", shipInfo.getDest());//目的地
				shipMessageProperties.put("destinationEn", "");//目的地
				shipMessageProperties.put("ETA", "");
				if(StringUtils.isNotBlank(shipInfo.getEta())){
					Date dateETA =new Date(Long.parseLong(shipInfo.getEta()));
					shipMessageProperties.put("ETA", sdf.format(dateETA));//ETA
				}
				
				shipMessageProperties.put("shipWaterline", shipInfo.getDraught());//吃水
				shipMessageProperties.put("shipFullWaterline", shipInfo.getDraft());//满载吃水
				shipMessageProperties.put("newTime", "");
				if(StringUtils.isNotBlank(shipInfo.getReceivedTime())){
					Date dateLast =new Date(Long.parseLong(shipInfo.getReceivedTime()));
					shipMessageProperties.put("newTime", sdf.format(dateLast));//接收时间
				}
				//全长
				shipMessageProperties.put("SDWT", shipInfo.getSdwt());//夏季载重量
				shipMessageProperties.put("shipName", shipInfo.getVesselName());//船名
				shipMessageProperties.put("shipType", shipInfo.getVesselSizeCn());//船型
				//shipMessageProperties.put("shipTypeEn", shipInfo.getVesselTypeEn());//船型
				shipMessageProperties.put("LOA", shipInfo.getLoa()+"m");//全长
				shipMessageProperties.put("HullType", shipInfo.getHullType());//船体结构——船壳类型
				shipMessageProperties.put("shipCondition", shipInfo.getStatus_cn());//状态
				shipMessageProperties.put("shipConditionEn", shipInfo.getStatus_en());//状态
				shipMessageProperties.put("Built", shipInfo.getBuild());//建造时间
				shipMessageProperties.put("shipInitial", "");//始发港
				shipMessageProperties.put("shipInitialEn", "");//始发港
				shipMessageProperties.put("nowSeaArea", shipInfo.getSeaAreaCn());//当前海区
				shipMessageProperties.put("nowSeaAreaEn", shipInfo.getSeaAreaEn());//当前海区
				
				shipMessageProperties.put("country", "");//国家
				if(StringUtils.isNotBlank(shipInfo.getFlag().getCountry())){
					shipMessageProperties.put("country", "flag-icon-"+shipInfo.getFlag().getCountry().toLowerCase());//国家
				}
				shipMessageProperties.put("displayCountryCn", shipInfo.getFlag().getDisplayCountryCn());//国家
				shipMessageProperties.put("displayCountryEn", shipInfo.getFlag().getDisplayCountryEn());//国家
				
				properties.put("shipMessage", shipMessageProperties);
				properties.put("path", "M-6 10L0 -10L6 10z");
				properties.put("fillColor", "#00ea46");
				properties.put("fillOpacity", 1);
				properties.put("scale", 1);
				properties.put("strokeColor", "#000000");
				properties.put("strokeWeight", 1);
				
				if(StringUtils.isNotBlank(shipInfo.getHog())){
					double	hdg = Double.parseDouble(shipInfo.getHog());
					properties.put("rotation", hdg);
				}
				feature.setProperties(properties);
				fc.add(feature);
				
			}
			
			
			
 		}catch (Exception e ) {
			e.printStackTrace();
		}
		Object o =null;
		if(StringUtils.isBlank(ship.getCallbackparam()))  o = fc;
		else o=new JSONPObject(ship.getCallbackparam(), fc);
		return o;
		
	}
	
	
	/**
	 * 船舶轨迹接口
	 * @return
	 */
	@WithoutAccess
	//@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.INSPECTION,Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.MEMBER_APTITUDE_7,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value="/api/geojson/waypoint.json")
	public List<MyWayPoint> waypoint(@RequestBody ShipVO ship,CurrentUser user) {
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		List<MyWayPoint> mydata= new ArrayList<MyWayPoint>();
		//ObjectMapper mapper = new ObjectMapper();
		String result =null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		String sdate=ship.getStartTime();
		String edate=ship.getEndTime();
		Calendar c = Calendar.getInstance();  
		Calendar c2 = Calendar.getInstance();  
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		try {
			c.setTime(sdf.parse(sdate));
			c2.setTime(sdf.parse(edate));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}  
		
		long start=c.getTimeInMillis();
		long end=c2.getTimeInMillis();
		
		url+="/sinochem/api/waypoint/"+ship.getImo()+"?&startTime="+start+"&endTime="+end;
		try{
			long begin = System.currentTimeMillis(); 
			result=HttpUtils.sendGetUTF8(url, "");
			long endtime = System.currentTimeMillis() - begin; 
			log.info("url:/api/geojson/waypoint.json  used :" +endtime +"milliseconds");
 		}catch (Exception e ) {
			e.printStackTrace();
		}
		WayPointInfoResult wayPointInfoResult = JSON.parseObject(result, new TypeReference<WayPointInfoResult>() {});
		List<WayPointInfo> data = wayPointInfoResult.getData();
		
		MyWayPoint pwp = null;
		for(WayPointInfo p:data){
			pwp =new MyWayPoint();
			pwp.setLat(Double.parseDouble(p.getLat()));
			pwp.setLng(Double.parseDouble(p.getLon()));
			pwp.setCog(Double.parseDouble(p.getCog()));
			
			if(StringUtils.isNotBlank(p.getReceivedTime())){
				Date dateETA =new Date(Long.parseLong(p.getReceivedTime()));
				pwp.setText(sdf2.format(dateETA));
			}
			
			mydata.add(pwp);
		}
		return mydata;
		
	}
	
	
	/**
	 * 船舶轨迹接口
	 * @return
	 */
	@WithoutAccess
	//@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.INSPECTION,Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.MEMBER_APTITUDE_7,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value="/api/geojson/waypointnew.json")
	public List<MyWayPoint> waypoint2(@RequestBody ShipVO ship,CurrentUser user) {
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		List<MyWayPoint> mydata= new ArrayList<MyWayPoint>();
		String result =null;
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		url+="/sinochem/api/waypoint/"+ship.getImo()+"?&startTime="+ship.getStartTime()+"&endTime="+ship.getEndTime();
		try{
			long begin = System.currentTimeMillis(); 
			result=HttpUtils.sendGetUTF8(url, "");
			long endtime = System.currentTimeMillis() - begin; 
			log.info("url:/api/geojson/waypointnew.json  used :" +endtime +"milliseconds");
 		}catch (Exception e ) {
			e.printStackTrace();
		}
		WayPointInfoResult wayPointInfoResult = JSON.parseObject(result, new TypeReference<WayPointInfoResult>() {});
		List<WayPointInfo> data = wayPointInfoResult.getData();
		
		MyWayPoint pwp = null;
		for(WayPointInfo p:data){
			pwp =new MyWayPoint();
			pwp.setLat(Double.parseDouble(p.getLat()));
			pwp.setLng(Double.parseDouble(p.getLon()));
			pwp.setCog(Double.parseDouble(p.getCog()));
			
			if(StringUtils.isNotBlank(p.getReceivedTime())){
				Date dateETA =new Date(Long.parseLong(p.getReceivedTime()));
				pwp.setText(sdf2.format(dateETA));
			}
			
			mydata.add(pwp);
		}
		return mydata;
		
	}
	
	/**
	 * 船舶轨迹接口********************************************（成品油专用）
	 * @return
	 */
	@WithoutAccess
	//@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.INSPECTION,Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.MEMBER_APTITUDE_7,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value="/api/geojson/imowaypoint.json")
	public  ResultDatas<Object> imowaypoint(@RequestBody ShipVO ship,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		List<MyWayPoint> mydata= new ArrayList<MyWayPoint>();
		String result =null;
	
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		
		url+="/sinochem/api/vessel/waypoint/"+ship.getImo();
		try{
			long begin = System.currentTimeMillis(); 
			result=HttpUtils.sendGetUTF8(url, "");
			long endtime = System.currentTimeMillis() - begin; 
			log.info("url:/api/geojson/imowaypoint.json  used :" +endtime +"milliseconds");
 		}catch (Exception e ) {
			e.printStackTrace();
		}
		ProductOilWayPointInfoResult wayPointInfoResult = JSON.parseObject(result, new TypeReference<ProductOilWayPointInfoResult>() {});
		List<WayPointInfo> data = wayPointInfoResult.getData().getWaypoint();
		
		MyWayPoint pwp = null;
		for(WayPointInfo p:data){
			pwp =new MyWayPoint();
			pwp.setLat(Double.parseDouble(p.getLat()));
			pwp.setLng(Double.parseDouble(p.getLon()));
			pwp.setCog(Double.parseDouble(p.getCog()));
			
			if(StringUtils.isNotBlank(p.getReceivedTime())){
				Date dateETA =new Date(Long.parseLong(p.getReceivedTime()));
				pwp.setText(sdf.format(dateETA));
			}
			
			mydata.add(pwp);
		}
		res.setDatas(mydata);
		return res;
		
	}
	
	
	
	
	/**
	 * 港口查询 
	 * @return
	 */
	@WithoutAccess
	//@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.INSPECTION,Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.MEMBER_APTITUDE_7,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value="/api/appjson/location.json")
	public ResultDatas<Object>  locationApp(@RequestBody LocationVO location,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		//List<MyWayPoint> mydata= new ArrayList<MyWayPoint>();
		String result =null;
		url+="/sinochem/api/ship/location";
		location.setPageIndex(location.getPageIndex()-1);
		String json=JSON.toJSONString(location);
		try{
			long begin = System.currentTimeMillis(); 
			result=HttpUtils.sendHttpPost(url, json);
			long end = System.currentTimeMillis() - begin; 
			log.info("url:/api/appjson/location.json  used :" +end +"milliseconds");
			LocationDataResult locationDataResult = JSON.parseObject(result, new TypeReference<LocationDataResult>() {});
			LocationData data=locationDataResult.getData();
			res.setDatas(data);
			res.setPageNum(data.getPageIndex()+1);
			res.setPageSize(data.getPageSize());
			res.setPageCount(data.getPageTotal());
			res.setTotal(Long.parseLong(String.valueOf(data.getRecordTotal())));
 		}catch (Exception e ) {
			e.printStackTrace();
		}
		return res;
		
	}
		
	
	/**
	 * 靠港查询 
	 * @return
	 */
	@WithoutAccess
	//@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.INSPECTION,Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.MEMBER_APTITUDE_7,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value="/api/appjson/history.json")
	public ResultDatas<Object>  historyApp(@RequestBody ShipVO ship,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		String result =null;
		ship.setPageIndex(ship.getPageIndex()-1);
		url+="/sinochem/api/dock/history?imo="+ship.getImo()+"&startTime="+ship.getStartTime()+"&endTime="+ship.getEndTime()+"&pageSize="+ship.getPageSize()+"&pageIndex="+ship.getPageIndex();
		try{
			long begin = System.currentTimeMillis(); 
			result=HttpUtils.sendGetUTF8(url, "");
			long end = System.currentTimeMillis() - begin; 
			log.info("url:/api/appjson/history.json  used :" +end +"milliseconds");
			HistoryDataResult historyDataResult = JSON.parseObject(result, new TypeReference<HistoryDataResult>() {});
			HistoryData data=historyDataResult.getData();
			res.setDatas(historyDataResult.getData().getData());
			res.setPageNum(data.getPageIndex()+1);
			res.setPageSize(data.getPageSize());
			res.setPageCount(data.getPageTotal());
			res.setTotal(Long.parseLong(String.valueOf(data.getRecordTotal())));
		}catch (Exception e ) {
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	/**
	 * 所有船舶坐标信息 (页面循环生成渲染版)
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/api/geojson/all.json")
	public ResultDatas<Object>  allship(CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		String result =null;
		url+="/sinochem/api/ship/all";
		try{
			long begin = System.currentTimeMillis(); 
			result=HttpUtils.sendGetUTF8(url, "");
			long end = System.currentTimeMillis() - begin; 
			log.info("url:/api/geojson/all.json  used :" +end +"milliseconds");
			AllShipnfoResult allShipnfoResult = JSON.parseObject(result, new TypeReference<AllShipnfoResult>() {});
			res.setDatas(allShipnfoResult.getData());
		}catch (Exception e ) {
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	
	/**
	 * 所有船舶坐标信息 (后台循环生成渲染版)
	 * @return
	 */
	@WithoutAccess
	//@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.INSPECTION,Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.MEMBER_APTITUDE_7,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value="/api/geojson/allback.json")
	public Object  allshipback(/*@RequestBody*/ ShipVO ship,CurrentUser user) {
		FeatureCollection fc = new FeatureCollection();
		fc.setType("FeatureCollection");
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		String result =null;
		url+="/sinochem/api/ship/all";
		try{
			long begin = System.currentTimeMillis(); 
			result=HttpUtils.sendHttpPost(url, "{}");
			long end = System.currentTimeMillis() - begin; 
			log.info("url:/api/geojson/all.json  used :" +end +"milliseconds");
			AllShipnfoResult allShipnfoResult = JSON.parseObject(result, new TypeReference<AllShipnfoResult>() {});
			for(AllShipInfo shipInfo:allShipnfoResult.getData()){
				Feature feature = new Feature();
				feature.setType("Feature");
				feature.setId(shipInfo.getImo());
				Point point = new Point();
				point.setType("Point");
				double lat =shipInfo.getLat();//纬度
				double lng =shipInfo.getLon();//经度
				Map<String,Object> properties = new HashMap<String,Object>();
				Map<String,Object> shipMessageProperties = new HashMap<String,Object>();
				properties.put("lat", lat);//纬度
				properties.put("lng", lng);//经度
				point.setCoordinates(new double[]{lng,lat,111.16});
				feature.setGeometry(point);
				shipMessageProperties.put("MMSI", shipInfo.getMmsi());//MMSI
				shipMessageProperties.put("IMO", shipInfo.getImo());//IMO
				shipMessageProperties.put("shipName", shipInfo.getShipName());//IMO
				properties.put("shipMessage", shipMessageProperties);
				properties.put("path", "M-6 10L0 -10L6 10z");
				properties.put("fillColor", "#00ea46");
				properties.put("fillOpacity", 1);
				properties.put("scale", 1.2);
				properties.put("strokeColor", "#000000");
				properties.put("strokeWeight", 1);
				double rotation =shipInfo.getHeading();
				properties.put("rotation", rotation);
				feature.setProperties(properties);
				fc.add(feature);
			}
		}catch (Exception e ) {
			e.printStackTrace();
		}
		
		Object o =null;
		if(StringUtils.isBlank(ship.getCallbackparam()))  o = fc;
		else o=new JSONPObject(ship.getCallbackparam(), fc);
		return o;
	}

	
	/**
	 * 历史航线
	 * @param ship
	 * @param user
	 * @return
	 */
	@WithoutAccess
	//@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.INSPECTION,Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.MEMBER_APTITUDE_7,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value="/api/geojson/historyport.json")
	public ResultDatas<Object>  HistoryPortList(@RequestBody HistoryPortVO historyPort,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		String result =null;
		url+="/sinochem/api/history/port/route";
		String json=JSON.toJSONString(historyPort);
		try{
			long begin = System.currentTimeMillis(); 
			result=HttpUtils.sendHttpPost(url, json);
			long end = System.currentTimeMillis() - begin; 
			log.info("url:/api/geojson/historyport.json  used :" +end +" milliseconds");
			HistoryPortResult historyPortResult = JSON.parseObject(result, new TypeReference<HistoryPortResult>() {});
			//List<HistoryPortInfo> data=historyPortResult.getData();
			res.setDatas(historyPortResult);
 		}catch (Exception e ) {
			e.printStackTrace();
		}
		return res;
		
	}
	
	/**
	 * 历史靠港
	 * @param ship
	 * @param user
	 * @return
	 */
	@WithoutAccess
	//@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.INSPECTION,Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.MEMBER_APTITUDE_7,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value="/api/geojson/current.json")
	public ResultDatas<Object>  current(@RequestBody CurrentVO current,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		String result =null;
		url+="/sinochem/api/history/port/current";
		String json=JSON.toJSONString(current);
		try{
			long begin = System.currentTimeMillis(); 
			result=HttpUtils.sendHttpPost(url, json);
			long end = System.currentTimeMillis() - begin; 
			log.info("url:/api/geojson/current.json  used :" +end +" milliseconds");
			HistoryPortEnResult historyPortEnResult = JSON.parseObject(result, new TypeReference<HistoryPortEnResult>() {});
			HistoryPortEnData data=historyPortEnResult.getData();
			Double total = 0.0;
			if(data.getData()!=null){
				for(HistoryPortEnInfo info:data.getData()){
					total+=info.getSdwt();
				}
			}
			res.setPageSize(total.intValue());
			res.setDatas(data);
			res.setTotal(Long.parseLong(String.valueOf(data.getRecordTotal())));
		}catch (Exception e ) {
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	
	/**
	 * 预计到港
	 * @param expected
	 * @param user
	 * @return
	 */
	@WithoutAccess
	//@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.INSPECTION,Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.MEMBER_APTITUDE_7,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value="/api/geojson/expected.json")
	public ResultDatas<Object>  expectedList(@RequestBody ExpectedVO expected,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		String result =null;
		url+="/sinochem/api/history/port/expected";
		if(expected.getNaviStatus().equals("1,2")){
			expected.setNaviStatus("");
		}
		String json=JSON.toJSONString(expected);
		try{
			long begin = System.currentTimeMillis(); 
			result=HttpUtils.sendHttpPost(url, json);
			long end = System.currentTimeMillis() - begin; 
			log.info("url:/api/geojson/expected.json  used :" +end +" milliseconds");
			ExpectedDataResult expectedDataResult = JSON.parseObject(result, new TypeReference<ExpectedDataResult>() {});
			ExpectedData data=expectedDataResult.getData();
			Double total = 0.0;
			if(data.getData()!=null){
				for(ExpectedInfo info:data.getData()){
					total+=info.getSdwt();
				}
			}
			res.setPageSize(total.intValue());
			res.setDatas(data);
			res.setTotal(Long.parseLong(String.valueOf(data.getRecordTotal())));
 		}catch (Exception e ) {
			e.printStackTrace();
		}
		return res;
		
	}
	
	
	/**
	 * 航速航线
	 * @param expected
	 * @param user
	 * @return
	 */
	@WithoutAccess
	//@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.INSPECTION,Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.MEMBER_APTITUDE_7,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value="/api/geojson/plan.json")
	public ResultDatas<Object>  plan(@RequestBody PlanVO plan,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		String result =null;
		url+="/sinochem/api/plan";
		 List<Route> routeList = null;
		 routeList=plan.getRoute();
		
		
		if(StringUtils.isNotBlank(plan.getFromPortStartTime())&&StringUtils.isNotBlank(plan.getTimeZone())){
			String timeStr = plan.getFromPortStartTime(); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"+plan.getTimeZone())); // 设置港口时区  
			Date d = null;
			try {
				d = sdf.parse(timeStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			List<Route> route=plan.getRoute();
			if(route.size()>0){
				route.get(0).setStartTime(String.valueOf(d.getTime()));
			}
		}
		
		
		
		String json=JSON.toJSONString(plan);
		try{
			long begin = System.currentTimeMillis(); 
			result=HttpUtils.sendHttpPost(url, json);
			long end = System.currentTimeMillis() - begin; 
			log.info("url:/api/geojson/plan.json  used :" +end +" milliseconds");
			PlanDataResult planDataResult = JSON.parseObject(result, new TypeReference<PlanDataResult>() {});
			PlanDataData data=planDataResult.getData();
			List<PlanData> planData = data.getData();
			TimeZone tz;
			
			if(routeList!=null){
				for(int i=0;i<routeList.size();i++){
					//myDataTest(i,Double.parseDouble(plan.getSpeed()),0,planData,routeList,plan.getSpeed());
					myDataTest(i,planData,routeList,plan.getSpeed());
					
				}
			 }
			
			for(PlanData planDataTemp : planData){
				/*Date dateStart = new Date(Long.parseLong(planDataTemp.getStartTime()));
				Date dateEnd = new Date(Long.parseLong(planDataTemp.getEndTime()));*/
				Date dateStart = new Date(planDataTemp.getStartTime());
				Date dateEnd = new Date(planDataTemp.getEndTime());
				// 获取默认的DateFormat，用于格式化Date
				// DateFormat df = DateFormat.getInstance();
				 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				 // 设置时区为tz
				 tz = TimeZone.getTimeZone("GMT"+planDataTemp.getToNameZone());
				 df.setTimeZone(tz);
				 // 获取格式化后的字符串
				 String dateStartStr = df.format(dateStart);
				 String dateEndStr = df.format(dateEnd);
				planDataTemp.setDateStartStr(dateStartStr);
				planDataTemp.setDateEndStr(dateEndStr);
			}
			res.setDatas(data);
 		}catch (Exception e ) {
			e.printStackTrace();
		}
		return res;
		
	}
	
	/**
	 * 当前在港
	 * @param current
	 * @param user
	 * @return
	 */
	@WithoutAccess
	//@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.INSPECTION,Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.MEMBER_APTITUDE_7,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value="/api/geojson/now.json")
	public ResultDatas<Object>  current2(@RequestBody CurrentVO current,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		String result =null;
		url +="/sinochem/api/ship/location";
		
		String json=JSON.toJSONString(current);
		try{
			long begin = System.currentTimeMillis(); 
			result=HttpUtils.sendHttpPost(url, json);
			long end = System.currentTimeMillis() - begin; 
			log.info("url:/api/geojson/now.json  used :" +end +" milliseconds");
			CurrentDataResult currentDataResult = JSON.parseObject(result, new TypeReference<CurrentDataResult>() {});
			CurrentData data=currentDataResult.getData();
			Double total = 0.0;
			if(data.getData()!=null){
				for(CurrentInfo info:data.getData()){
					total+=info.getSdwt();
				}
			}
			res.setPageSize(total.intValue());
			res.setDatas(data);
			res.setTotal(Long.parseLong(String.valueOf(data.getRecordTotal())));
		}catch (Exception e ) {
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	/**
	 * 港口关联接口
	 * @param current
	 * @param user
	 * @return
	 */
	@WithoutAccess
	//@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.INSPECTION,Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.MEMBER_APTITUDE_7,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value="/api/geojson/opposite.json")
	public ResultDatas<Object>  opposite(@RequestBody OppositeVO oppositeVO,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		String result =null;
		try{
			String name = oppositeVO.getPortName();
			name=URLEncoder.encode(name,"utf-8").replace("+","%20");
			oppositeVO.setPortName(name);
			url+="/sinochem/api/port/opposite/"+oppositeVO.getPortName()+"?type="+oppositeVO.getType();
			long begin = System.currentTimeMillis(); 
			result=HttpUtils.sendGetUTF8(url, "");
			long end = System.currentTimeMillis() - begin; 
			log.info("url:/api/geojson/opposite.json  used :" +end +" milliseconds");
			OppositeInfoResult oppositeInfoResult = JSON.parseObject(result, new TypeReference<OppositeInfoResult>() {});
			List<OppositeInfo> data=oppositeInfoResult.getData();
			res.setDatas(data);
		}catch (Exception e ) {
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	
	/**
	 * 航速航线-实时
	 * @param expected
	 * @param user
	 * @return
	 */
	@WithoutAccess
	//@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.INSPECTION,Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.MEMBER_APTITUDE_7,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value="/api/geojson/planCurrent.json")
	public ResultDatas<Object>  planCurrent(@RequestBody PlanCurrentVO plan,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort();
		String result =null;
		url+="/sinochem/api/plan/current";
		List<Route> routeList = null;
		 
		routeList=plan.getRoute();
		String json=JSON.toJSONString(plan);
		try{
			long begin = System.currentTimeMillis(); 
			result=HttpUtils.sendHttpPost(url, json);
			long end = System.currentTimeMillis() - begin; 
			log.info("url:/api/geojson/planCurrent.json  used :" +end +" milliseconds");
			PlanCurrentDataResult planDataResult = JSON.parseObject(result, new TypeReference<PlanCurrentDataResult>() {});
			PlanCurrentDataData data=planDataResult.getData();
			List<PlanData> planData = data.getData();
			if(routeList!=null){
				for(int i=0;i<routeList.size();i++){
					myDataTest(i,planData,routeList,plan.getSpeed());
				}
			 }
			TimeZone tz;
			for(PlanData planDataTemp : planData){
				/*Date dateStart = new Date(Long.parseLong(planDataTemp.getStartTime()));
				Date dateEnd = new Date(Long.parseLong(planDataTemp.getEndTime()));*/
				Date dateStart = new Date(planDataTemp.getStartTime());
				Date dateEnd = new Date(planDataTemp.getEndTime());
				// 获取默认的DateFormat，用于格式化Date
				// DateFormat df = DateFormat.getInstance();
				 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				 // 设置时区为tz
				 tz = TimeZone.getTimeZone("GMT"+planDataTemp.getToNameZone());
				 df.setTimeZone(tz);
				 // 获取格式化后的字符串
				 String dateStartStr = df.format(dateStart);
				 String dateEndStr = df.format(dateEnd);
				planDataTemp.setDateStartStr(dateStartStr);
				planDataTemp.setDateEndStr(dateEndStr);
			}
			res.setDatas(data);
 		}catch (Exception e ) {
			e.printStackTrace();
		}
		return res;
		
	}
	
    /**
     * 
     * @param i
     * @param r
     * @param planData
     * @param routeList
     */
	void myDataTest(int i,/*double time1,double time2,*/List<PlanData> planData,List<Route> routeList,String sp){
		Route r=routeList.get(i);
		PlanData planTemp=planData.get(i);
		PlanData planTemp2=null;
			if(sp.equals(r.getSpeed())){
				
			}else{
				double	consuming= 0;
				if(StringUtil.isBlank(r.getSpeed())){
					consuming=Double.valueOf(planTemp.getDistance())/Double.valueOf(sp);
					planTemp.setSpeed(sp);
					
				}else{
					consuming=Double.valueOf(planTemp.getDistance())/Double.valueOf(r.getSpeed());
					planTemp.setSpeed(r.getSpeed());
				}
				
				planTemp.setConsumingTime(String.valueOf(consuming));
				double time1 = consuming * 60 * 60 * 1000;
				double	newEnd = Arith.add(time1,Double.valueOf(planTemp.getStartTime()));
				BigDecimal bd = new BigDecimal(Math.ceil(newEnd));
				String str = bd.toPlainString();
				planTemp.setEndTime(Long.parseLong(str));
				
				
			}
			
		if(StringUtil.isNotBlank(r.getStayTime())){
				double	time2 = Double.valueOf(r.getStayTime()) * 60 * 60 * 1000;
				double	newEndTime = Arith.add(time2,Double.valueOf(planTemp.getEndTime()));
				BigDecimal bd = new BigDecimal(Math.ceil(newEndTime));
				String str = bd.toPlainString();
				//planTemp.setEndTime(Long.parseLong(String.valueOf(str)));
				planTemp.setStayTime(r.getStayTime());
				
				if(i+1<planData.size()){
					planTemp2 = planData.get(i+1);
					planTemp2.setStartTime(Long.parseLong(String.valueOf(str)));
				}
				
		}
		
		
		
	}
}
