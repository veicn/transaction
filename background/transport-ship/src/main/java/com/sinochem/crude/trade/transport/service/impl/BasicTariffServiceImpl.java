package com.sinochem.crude.trade.transport.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.dao.BasicTariffMapper;
import com.sinochem.crude.trade.transport.dao.DealPointsMapper;
import com.sinochem.crude.trade.transport.domain.BasicTariff;
import com.sinochem.crude.trade.transport.domain.DealPoints;
import com.sinochem.crude.trade.transport.model.DealPointsVO;
import com.sinochem.crude.trade.transport.query.BasicTariffQuery;
import com.sinochem.crude.trade.transport.service.BasicTariffService;

@Service
public class BasicTariffServiceImpl implements BasicTariffService {
	private static Log log = LogFactory.getLog(BasicTariffServiceImpl.class);
	
	@Autowired
	private BasicTariffMapper _BasicTariffMapper;
	
	@Autowired
	private DealPointsMapper _DealPointsMapper;
	
	public BasicTariffMapper getMapper(){
		return _BasicTariffMapper;
	} 
	
	/**
	 * 根据对象-查询对象列表
	 */
	public List<BasicTariff> queryByEntitys(BasicTariff basictariff){
		return  _BasicTariffMapper.queryByEntitys(basictariff);
	}
	
	/**
	 * 根据主键-查询对象
	 */
	public BasicTariff findByPrimaryKey(Long basicTariffId){
		return  _BasicTariffMapper.findByPrimaryKey(basicTariffId);	
	}
	/**
	 * 根据UUID-查询对象
	 */
	public BasicTariff findByUuid(String uuid){
		return  _BasicTariffMapper.findByUuid(uuid);	
	} 
	
	/**
	 * 根据主键-修改对象
	 */
	public void updateRecord(BasicTariff basictariff) {
		 _BasicTariffMapper.updateRecord(basictariff);
	}
	
	/**
	 * 根据主键-逻辑删除对象（ AliveFlag修改为0）
	 */
	public void deleteRecordByKey(Long basicTariffId  , Long deleteUser) {
		 _BasicTariffMapper.deleteRecordByKey(basicTariffId , deleteUser);
	}
	
	/**
	 * 新增
	 */
	public void insertRecord(BasicTariff basictariff){
		 _BasicTariffMapper.insertRecord(basictariff);
	}
	
	/*
	 * 根据主键删除数据 
	public void deleteRecordByKey(Long basicTariffId){
		 _BasicTariffMapper.deleteRecordByKey(basicTariffId);
	}
	*/
	/**
	 * 根据条件-查询全部
	 */
	public List<Map<String, Object>> queryRecords(Map<String, Object> map){
		return _BasicTariffMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-分页查询
	 */
	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount()); 
		return (Page<Map<String, Object>>) _BasicTariffMapper.queryRecords(map);
	}
	
	/**
	 * 根据条件-查询记录条数
	 */
	public int countRecords(Map<String, Object> map){
		return _BasicTariffMapper.countRecords(map);
	}
	
	/**
	 * 根据条件-批量逻辑删除 (AliveFlag修改为0)
	 */
	public void deleteRecords(Map<String, Object> map){
		_BasicTariffMapper.deleteRecords(map);
	}
	
	/**
	 * 根据条件-批量修改数据
	 */
	public void updateRecords(Map<String, Object> map) {
		_BasicTariffMapper.updateRecords(map);
	}

	//**************************以下方法为开发者补充*********************************/
	
	//翻页列表
	@Override
	public List<Map<String, Object>> getBasicTariffPageList(BasicTariffQuery query) {
		PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
		// 单位名称
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Map<String, Object>> list = null;
		//精确查询，与显示列表分离
		if(StringUtils.isNullOrEmpty(query.getLoadPort1()) && StringUtils.isNullOrEmpty(query.getLoadPort2()) && StringUtils.isNullOrEmpty(query.getLoadPort3())&& StringUtils.isNullOrEmpty(query.getUnloadPort1())&& StringUtils.isNullOrEmpty(query.getUnloadPort2())&& StringUtils.isNullOrEmpty(query.getUnloadPort3())&& StringUtils.isNullOrEmpty(query.getYear())){
			map.put("year", query.getYear());
			map.put("loadPort1", query.getLoadPort1());
			map.put("loadPort2", query.getLoadPort2());
			map.put("loadPort3", query.getLoadPort3());
			map.put("unloadPort1", query.getUnloadPort1());
			map.put("unloadPort2", query.getUnloadPort2());
			map.put("unloadPort3", query.getUnloadPort3());
			
			list = _BasicTariffMapper.queryRecords4(map);			
		}else{
			map.put("loadPort1", query.getLoadPort1());
			if(query.getLoadPort1() == null){
				map.put("loadPort1", "");
			}
			map.put("loadPort2", query.getLoadPort2());
			if(query.getLoadPort2() == null){
				map.put("loadPort2", "");
			}
			map.put("loadPort3", query.getLoadPort3());
			if(query.getLoadPort3() == null){
				map.put("loadPort3", "");
			}
			map.put("unloadPort1", query.getUnloadPort1());
			if(query.getUnloadPort1() == null){
				map.put("unloadPort1", "");
			}
			map.put("unloadPort2", query.getUnloadPort2());
			if(query.getUnloadPort2() == null){
				map.put("unloadPort2", "");
			}
			map.put("unloadPort3", query.getUnloadPort3());
			if(query.getUnloadPort3() == null){
				map.put("unloadPort3", "");
			}
			map.put("year", query.getYear());
			if(query.getYear() == null){
				map.put("year", "");
			}
			
			//特殊处理
			if("KIZOMBA A TERMINAL".equals(query.getLoadPort1())||"KIZOMBA B TERMINAL".equals(query.getLoadPort1())||"KIZOMBA C-MONDO TERMINAL".equals(query.getLoadPort1())){
				map.put("loadPort1","KIZOMBA");
			}
			if("KIZOMBA A TERMINAL".equals(query.getLoadPort2())||"KIZOMBA B TERMINAL".equals(query.getLoadPort2())||"KIZOMBA C-MONDO TERMINAL".equals(query.getLoadPort2())){
				map.put("loadPort2","KIZOMBA");
			}
			if("KIZOMBA A TERMINAL".equals(query.getLoadPort3())||"KIZOMBA B TERMINAL".equals(query.getLoadPort3())||"KIZOMBA C-MONDO TERMINAL".equals(query.getLoadPort3())){
				map.put("loadPort3","KIZOMBA");
			}
			
			list = _BasicTariffMapper.queryRecords2(map);
		}
		
		return list;
	}
	
	@Override
	public Page<Map<String, Object>> getBasicTariffPageLists(
			BasicTariffQuery query) {
		PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(query.getPageSize());
		Map<String, Object> map = new HashMap<>();
		map.put("year", query.getYear());
		map.put("loadPort1", query.getLoadPort1());
		map.put("loadPort2", query.getLoadPort2());
		map.put("loadPort3", query.getLoadPort3());
		map.put("unloadPort1", query.getUnloadPort1());
		map.put("unloadPort2", query.getUnloadPort2());
		map.put("unloadPort3", query.getUnloadPort3());
		Page<Map<String,Object>> page = this.queryRecords(map, pageInfo);
		for (Map<String, Object> map2 : page) {
			Object loadPort1 = map2.get("loadPort1");
			Object loadPort2 = map2.get("loadPort2");
			Object loadPort3 = map2.get("loadPort3");
			Object unloadPort1 = map2.get("unloadPort1");
			Object unloadPort2 = map2.get("unloadPort2");
			Object unloadPort3 = map2.get("unloadPort3");
			String load ="";
			String unload = "";
			if (loadPort1 != null){
				String str = (String)loadPort1;
				if (!StringUtils.isNullOrEmpty(str)){
					load = load +str +"-";
				}
			}
			if (loadPort2 != null){
				String str = (String)loadPort2;
				if (!StringUtils.isNullOrEmpty(str)){
					load = load +str +"-";
				}
			}
			if (loadPort3 != null){
				String str = (String)loadPort3;
				if (!StringUtils.isNullOrEmpty(str)){
					load = load +str +"-";
				}
			}
			if (unloadPort1 != null){
				String str = (String)unloadPort1;
				if (!StringUtils.isNullOrEmpty(str)){
					unload = unload +str +"-";
				}
			}
			if (unloadPort2 != null){
				String str = (String)unloadPort2;
				if (!StringUtils.isNullOrEmpty(str)){
					unload = unload +str +"-";
				}
			}
			if (unloadPort3 != null){
				String str = (String)unloadPort3;
				if (!StringUtils.isNullOrEmpty(str)){
					unload = unload +str +"-";
				}
			}
			if (load.length()>0){
				load=load.substring(0,load.length()-1);
			}
			if (unload.length()>0){
				unload=unload.substring(0,unload.length()-1);
			}
			map2.put("load", load);
			map2.put("unload", unload);
		}
		return page;
	}
	

	//新增方法
	@Override
	public void saveBasicTariff(BasicTariff bt, CurrentUser user) {
		bt.setUuid(KeyGenUtils.newUuid());
		bt.setLangVer(Constants.LANG_VER);
		bt.setAliveFlag(Constants.ALIEVE_FLAG_YES);
		bt.setCreateDate(DateTimeUtils.currentDate());
		bt.setCreateUser(user.getMemberId());
		bt.setVersion("1");
		_BasicTariffMapper.insertRecord(bt);
	}
	
	//根据uuid查询对象
	@Override
	public void getBasicTariffByUuid(BasicTariff bt) {
		_BasicTariffMapper.findByUuid(bt.getUuid());
	}

	//根据uuid逻辑删除对象
	@Override
	public void deleteBasicTariffByUuid(BasicTariff bt, CurrentUser user) {
		bt.setUpdateUser(user.getMemberId());
		_BasicTariffMapper.deleteRecordsByUuid(bt);
	}

	//根据uuid修改对象
	@Override
	public void updateBasicTariffByUuid(BasicTariff bt, CurrentUser user) {
		bt.setUpdateUser(user.getMemberId());
		bt.setVersion((Integer.parseInt(bt.getVersion())+1)+"");
		_BasicTariffMapper.updateRecordByUuid(bt);
	}

	//检查是否存在
	@Override
	public BasicTariff checkBt(BasicTariff basicTariff) {
		Map<String, String> btmap = new HashMap<>();
		btmap.put("loadPort1", basicTariff.getLoadPort1());
		btmap.put("unloadPort1", basicTariff.getUnloadPort1());
		btmap.put("year", basicTariff.getYear()+"");
		if(StringUtils.isNullOrEmpty(basicTariff.getLoadPort2())){
			btmap.put("loadPoet2", "");
		}else{
			btmap.put("loadPoet2", basicTariff.getLoadPort2());
		}
		
		if(StringUtils.isNullOrEmpty(basicTariff.getLoadPort3())){
			btmap.put("loadPoet3", "");
		}else{
			btmap.put("loadPoet3", basicTariff.getLoadPort3());
		}
		
		if(StringUtils.isNullOrEmpty(basicTariff.getUnloadPort2())){
			btmap.put("unloadPoet2", "");
		}else{
			btmap.put("unloadPoet2", basicTariff.getUnloadPort2());
		}
		
		if(StringUtils.isNullOrEmpty(basicTariff.getUnloadPort3())){
			btmap.put("unloadPoet3", "");
		}else{
			btmap.put("unloadPoet3", basicTariff.getUnloadPort3());
		}
		return _BasicTariffMapper.checkBt(btmap);
	}

	@Override
	public List<BasicTariff> findBasicTariffPageList(BasicTariff vo) {
		// 页码设定
		//PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
		// 查询成交点数信息
		if(vo.getLoadPort2() == null){
			vo.setLoadPort2("");
		}
		if(vo.getLoadPort3() == null){
			vo.setLoadPort3("");
		}
		if(vo.getUnloadPort2() == null){
			vo.setUnloadPort2("");
		} 
		if(vo.getUnloadPort3() == null){
			vo.setUnloadPort3("");
		}
		List<BasicTariff> btList = this.queryByEntitys2(vo);
		return btList;
	}
	
	
	public List<BasicTariff> queryByEntitys2(BasicTariff basictariff){
		return  _BasicTariffMapper.queryByEntitys2(basictariff);
	}

	//运费小工具
	@Override
	public Map<String, Object> fieightTools(BasicTariffQuery query) {
		String result = "";
		Map<String, Object> map2 = new HashMap<>();
		//根据条件查出基础运价and地区
		BasicTariff bt = null;
		try{
			String wsStr = "";
			String priceStr = "";
			String wsNameStr = "";
			Integer num =0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			if(query.getReferenceDate() != null){
				 query.setYear(sdf.format(query.getReferenceDate()));
			}
			List<Map<String, Object>> basicTariffPageList = this.getBasicTariffPageList(query);
			if(basicTariffPageList.size() > 0){
				 Map<String, Object> map = basicTariffPageList.get(0);
				 bt = new BasicTariff();
				 bt.setLoadPort1((String)map.get("loadPort1"));
				 bt.setLoadPort2((String)map.get("loadPort2"));
				 bt.setLoadPort3((String)map.get("loadPort3"));
				 bt.setUnloadPort1((String)map.get("unloadPort1"));
				 bt.setUnloadPort2((String)map.get("unloadPort2"));
				 bt.setUnloadPort3((String)map.get("unloadPort3"));
				 bt.setRegion((String)map.get("region"));
				 bt.setPrice((BigDecimal)map.get("price"));
				 
				 if(query.getReferenceDate() != null){
					 bt.setYear(Integer.parseInt(sdf.format(query.getReferenceDate())));
				 }
			}
			//根据条件和地区查出WS点
			DealPointsVO dealPoints = new DealPointsVO();
			dealPoints.setDate(query.getReferenceDate());
			dealPoints.setType(query.getShipType());
			if(bt != null){
				priceStr = String.valueOf(bt.getPrice());
				if (bt.getRegion() != null){
					wsNameStr = bt.getRegion();
				}
				dealPoints.setRegion(bt.getRegion());			
				DealPoints dp = _DealPointsMapper.findDpByTdr(dealPoints);
				//进行运算（(单桶运费=WS/100*基础运价*260000)/2000000）
				if (dp != null ){
					wsStr = String.valueOf(dp.getPrice());
					BigDecimal ws = dp.getPrice();
					BigDecimal fieight = bt.getPrice();
					BigDecimal cs = new BigDecimal(100);
					BigDecimal end = new BigDecimal(2000000);
					BigDecimal dq = null;
					if(bt.getRegion().equals("西非")){
						dq = new BigDecimal(260000);
						num=26;
					}else{
						dq = new BigDecimal(270000);
						num=27;
					}
					BigDecimal dtyf = (ws.divide(cs).multiply(fieight).multiply(dq)).divide(end);
					DecimalFormat df1 = new DecimalFormat("0.00");  
					String str = df1.format(dtyf);  
					result = str+"";
					map2.put("region",bt.getRegion());
					map2.put("ws",ws);
					map2.put("singleFare",result);
					map2.put("regionNum",num);
				}
			}
			log.info("############预估运费查询：基础运价="+priceStr+"，基础运价地区="+wsNameStr+"，地区计费吨="+num+"万，ws点数="+wsStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return map2;
	}

	////查询现有所有港口
	@Override
	public Map<String, Object> findAllPortList() {
		Map<String, Object> map = new HashMap<>();
		//装港集合
		List<String> loadPortList = _BasicTariffMapper.findLoadList();
		map.put("loadPortList",loadPortList);
		//卸港集合
		List<String> unloadPortList = _BasicTariffMapper.findUnLoadList();
		map.put("unloadPortList",unloadPortList);
		return map;
	}

	
	
}
