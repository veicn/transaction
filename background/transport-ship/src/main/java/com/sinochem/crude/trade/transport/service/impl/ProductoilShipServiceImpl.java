package com.sinochem.crude.trade.transport.service.impl;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.util.StringUtil;
import org.geojson.url.DataCenterEarthUrlServer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.HttpUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.dao.ProductoilShipMapper;
import com.sinochem.crude.trade.transport.domain.ProductoilShip;
import com.sinochem.crude.trade.transport.remote.SysShipVO;
import com.sinochem.crude.trade.transport.service.ProductoilShipService;

@Service
public class ProductoilShipServiceImpl implements ProductoilShipService {
	@Autowired
	private ProductoilShipMapper _ProductoilShipMapper;
	
	@Autowired
	private URLBroker dataCenterServer;
	
	@Autowired
	private DataCenterEarthUrlServer dataCenterEarthUrlServer;
	
	public ProductoilShipMapper getMapper(){
		return _ProductoilShipMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<ProductoilShip> queryByEntitys(ProductoilShip productoilship){
		return  _ProductoilShipMapper.queryByEntitys(productoilship);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public ProductoilShip findByPrimaryKey(Long sysShipId){
		return  _ProductoilShipMapper.findByPrimaryKey(sysShipId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public ProductoilShip findByUuid(String uuid){
		return  _ProductoilShipMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(ProductoilShip productoilship) {
		 _ProductoilShipMapper.updateRecord(productoilship);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long sysShipId  , Long deleteUser) {
		 _ProductoilShipMapper.deleteRecordByKey(sysShipId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(ProductoilShip productoilship){
		 _ProductoilShipMapper.insertRecord(productoilship);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long sysShipId){
		 _ProductoilShipMapper.deleteRecordByKey(sysShipId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _ProductoilShipMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _ProductoilShipMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _ProductoilShipMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_ProductoilShipMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_ProductoilShipMapper.updateRecords(map);
	}

	
	//**************************以下方法为开发者补充*********************************/
	@Override
	public List<Map<String, Object>> findSysShipNameList() {
		return _ProductoilShipMapper.findSysShipNameList();
	}

	@Override
	@Transactional
	public void insertProductoilShip(SysShipVO vo) throws Exception {
		ProductoilShip productoilShip = new ProductoilShip();
		productoilShip.setImo(vo.getImo());
		productoilShip.setName(vo.getName());
		List<ProductoilShip> list = this.queryByEntitys(productoilShip);
		if (list == null || list.isEmpty()) {
			//新增
			ProductoilShip ship = new ProductoilShip();
			BeanUtils.copyProperties(vo, ship);
			if (StringUtils.isNullOrEmpty(vo.getMmsi())) {
				ship.setMmsi(findMmsiByImo(ship.getImo()));
			}
			ship.setStatus("2");
			ship.setPublisherType("3");
			ship.setUuid(KeyGenUtils.newUuid());
			ship.setCreateDate(DateTimeUtils.currentDate());
			ship.setUpdateDate(DateTimeUtils.currentDate());
			ship.setLangVer(Constants.LANG_VER);
			ship.setAliveFlag(Constants.ALIEVE_FLAG_YES);
			ship.setUpdateUser(999L);
			ship.setCreateUser(999L);
			ship.setEpMemberId(999L);
			this.insertRecord(ship);
			
			//同步大数据船舶信息
			insertBigData(ship);
		}
	}

	private void insertBigData(ProductoilShip ship) throws Exception {
		//String url = dataCenterServer.get("/sinochem/api/vessel").toString();
		String url = dataCenterEarthUrlServer.getHost() + ":" +dataCenterEarthUrlServer.getPort()+"/sinochem/api/vessel";
		Map<String,Object> map = new HashMap<>();
		map.put("vesselName", ship.getName());
		map.put("type", "2");
		map.put("beam", ship.getWide());
		map.put("built", ship.getCompleteDate());
		map.put("cubic", ship.getCapacity());
		map.put("draft", ship.getDraft());
		map.put("flag", "");
		map.put("hullType", ship.getHull());
		map.put("imo", ship.getImo());
		map.put("loa", ship.getLength());
		map.put("mmsi", ship.getMmsi());
		map.put("sdwt", ship.getLoadQuantity());
		map.put("vesselSizeEn", ship.getType());
		map.put("vesselSizeEn", ship.getType());
		map.put("vesselTypeEn", ship.getUseType());
		map.put("vesselTypeEn", ship.getUseType());
		
		String json=JSON.toJSONString(map);
		String res = org.geojson.httpclient.HttpUtils.sendHttpPost(url, json);
		JSONObject jsons = JSONObject.parseObject(res);
		String mess = "";
		if (jsons != null && jsons.get("messageCn") != null) {
			mess =(String) jsons.get("messageCn");
		} else if(jsons != null){
			mess =(String) jsons.get("messageEn");
		}
		if (jsons != null && jsons.get("code") == "0") {
			throw new TransportException((String)jsons.get("code"),mess);
		}
		
		
	}
	
	private String findMmsiByImo (String imo) throws Exception {
		String mmsi = "";
		// 当前年月日
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(currentTime);
		
		String srcKey= "09fd2450680248cd933ac162cbfbddf9" + dateString;
		
		MessageDigest md5 = MessageDigest.getInstance("MD5");  
		md5.update((srcKey).getBytes("UTF-8"));  
		byte b[] = md5.digest();  
		
		int i;  
		StringBuffer buf = new StringBuffer("");  
		// md5小写32位
		for (int offset=0; offset<b.length; offset++) {
			i = b[offset];  
			if (i<0) {
				i+=256;  
			}
			if (i<16) {
				buf.append("0");  
			}
			buf.append(Integer.toHexString(i));  
		}
		String key = buf.toString();
		String url = "http://shipapi.chinaports.com/shipDataApiForShagang/getEsSearch?str=" + imo +"&key=" + key;
		// 调用接口
		String mmsiJson = HttpUtils.sendGet(url, "");
		if (!StringUtil.isBlank(mmsiJson)) {
			@SuppressWarnings("unchecked")
			Map<String, Object> mmsiObj = JSONObject.parseObject(mmsiJson, Map.class);
			Object object = mmsiObj.get("data");
			if (object != null){
				@SuppressWarnings("unchecked")
				List<Map<String,Object>> str = (List<Map<String,Object>>)object;
				Map<String, Object> map = str.get(0);
				if (map.get("mmsi") != null){
					mmsi = (String)map.get("mmsi");
				}
			}
		}
		return mmsi;
	}
}
