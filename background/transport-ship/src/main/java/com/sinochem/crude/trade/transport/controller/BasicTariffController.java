package com.sinochem.crude.trade.transport.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.URLMapping;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.domain.BasicTariff;
import com.sinochem.crude.trade.transport.model.BasicTariffVO;
import com.sinochem.crude.trade.transport.model.ShipPactVO;
import com.sinochem.crude.trade.transport.query.BasicTariffQuery;
import com.sinochem.crude.trade.transport.service.BasicTariffService;
import com.sinochem.crude.trade.transport.service.ShipPactService;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;



/**
 * @ClassName: Controller
 * @Description: 基础运价信息维护
 * @author Wh
 * @date 2017年11月22日
 * @version V1.0
 */
@Controller
public class BasicTariffController  {
	@Autowired
	private BasicTariffService basicTariffService;
	
	@Autowired
	private ShipPactService shipPactService;
	 
	Log log = LogFactory.getLog(BasicTariffController.class);
	
	//初始化转换时间格式控件
	@InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	/**
	 * 基础运价_翻页列表
	 * @param 
	 * @param 
	 * @return
	 */
	@RightAccess(2036)
	@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
	@RequestMapping(value = URLMapping.BASICTARIFF_GET_PAGE_LIST)
	public void getBasicTariffList(CurrentUser user, ModelMap modelMap,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain, BasicTariffQuery query){
		
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		//List<Map<String, Object>> basicList= basicTariffService.getBasicTariffPageList(query);
		Page<Map<String, Object>> basicList= basicTariffService.getBasicTariffPageLists(query);
		
		//查询值集
		ShipPactVO shipPact = new ShipPactVO();
		//查询 年份15;
		shipPact.setValueSetTypes("15;");
		if (StringUtils.isNullOrEmpty(shipPact.getValueSetType()) && StringUtils.isNullOrEmpty(shipPact.getValueSetTypes())){
			throw new TransportException(TransportException.TYPE.ITSH089);
		}
		List<Map<String,Object>> valueList = shipPactService.queryValueSet(shipPact);
		List<Object> yearList = new ArrayList<>();
		//遍历赋值
		for (Map<String, Object> map2 : valueList) {
			Set<String> keySet = map2.keySet();
			for (String string : keySet) {
				if(string == "type"){
					if(map2.get(string).equals("15")){
						yearList.add(map2.get("value"));						
					}
				}
			}
		}
		//查询装卸港
		Map<String, Object> map = basicTariffService.findAllPortList();
		query.setTotalItem(basicList.getTotal());
		//装港  卸港 年份下拉框
		modelMap.put("loadList", map.get("loadPortList"));
		modelMap.put("unloadList", map.get("unloadPortList"));
		modelMap.put("yearList", yearList);
		modelMap.put("loadPort1", query.getLoadPort1());
		modelMap.put("loadPort2", query.getLoadPort2());
		modelMap.put("loadPort3", query.getLoadPort3());
		modelMap.put("unloadPort1", query.getUnloadPort1());
		modelMap.put("unloadPort2", query.getUnloadPort2());
		modelMap.put("unloadPort3", query.getUnloadPort3());
		modelMap.put("user", user);
		modelMap.put("query", query);
		modelMap.put("datas", basicList);
		
	}
	
	/**
		 * 新增
		 * @param BasicTariff
		 * @param user
		 * @return res
		 */
	@RightAccess(2038)
	@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
	@ResponseBody
	@RequestMapping(value=URLMapping.BASICTARIFF_SAVEBT, method = RequestMethod.POST  )
	public Result saveBasicTariff(@RequestBody BasicTariffVO bt, CurrentUser user){
		Result res = new Result();
		try {
			//检查用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			//检查bt对象
			if(bt == null){
				throw new TransportException(TransportException.TYPE.ITSH312);
			}
			//检查第一装港港口
			if(StringUtils.isNullOrEmpty(bt.getLoadPort1())){
				throw new TransportException(TransportException.TYPE.ITSH313);
			}
			//检查第一卸港港口
			if(StringUtils.isNullOrEmpty(bt.getUnloadPort1())){
				throw new TransportException(TransportException.TYPE.ITSH314);
			}
			//检查基础运价
			if(bt.getPrice() == null){
				throw new TransportException(TransportException.TYPE.ITSH315);
			}
			//检查运价年份
			if(bt.getYear() == null){
				throw new TransportException(TransportException.TYPE.ITSH316);
			}
			//检查里程
			if(bt.getMileage() == null){
				throw new TransportException(TransportException.TYPE.ITSH317);
			}
			//检查地区
			if(bt.getRegion() == null){
				throw new TransportException(TransportException.TYPE.ITSH330);
			}
			//检查是否有此数据
			BasicTariff basicTariff = basicTariffService.checkBt(BeanConvertUtils.beanToBean(bt, BasicTariff.class) );
			if(basicTariff == null){
				basicTariffService.saveBasicTariff(BeanConvertUtils.beanToBean(bt, BasicTariff.class), user);				
			}else{
				res.setMessage(Constants.message_004);
				res.setStatus(999);
			}
		} catch (BizException e) {
			log.error("saveBasicTariff error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("saveBasicTariff error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 逻辑删除
	 * @param BasicTariff
	 * @param user
	 * @return res
	 */
	@RightAccess(2044)
	@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
	@ResponseBody
	@RequestMapping(value=URLMapping.BASICTARIFF_DELETEBT, method = RequestMethod.POST  )
	public Result deleteBasicTariff(@RequestBody BasicTariffVO bt, CurrentUser user){
		Result res = new Result();
		try {
			//检查用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			//检查bt对象
			if(bt == null){
				throw new TransportException(TransportException.TYPE.ITSH312);
			}
			//检查uuid
			if(StringUtils.isNullOrEmpty(bt.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH318);
			}
			//检查是否存在
			BasicTariff basicTariff = basicTariffService.findByUuid(bt.getUuid());
			if(basicTariff != null){
				basicTariffService.deleteBasicTariffByUuid(BeanConvertUtils.beanToBean(bt, BasicTariff.class), user);				
			}else{
				res.setMessage(Constants.message_005);
				res.setStatus(999);
			}
		} catch (BizException e) {
			log.error("deleteBasicTariff error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("deleteBasicTariff error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 修改
	 * @param BasicTariff
	 * @param user
	 * @return res
	 */
	@RightAccess(2042)
	@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
	@ResponseBody
	@RequestMapping(value=URLMapping.BASICTARIFF_UPDATEBT, method = RequestMethod.POST  )
	public Result updateBasicTariff(@RequestBody BasicTariffVO bt, CurrentUser user){
		Result res = new Result();
		try {
			//检查用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			//检查bt对象
			if(bt == null){
				throw new TransportException(TransportException.TYPE.ITSH312);
			}
			//检查uuid
			if(StringUtils.isNullOrEmpty(bt.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH318);
			}
			//检查第一装港港口
			if(StringUtils.isNullOrEmpty(bt.getLoadPort1())){
				throw new TransportException(TransportException.TYPE.ITSH313);
			}
			//检查第一卸港港口
			if(StringUtils.isNullOrEmpty(bt.getUnloadPort1())){
				throw new TransportException(TransportException.TYPE.ITSH314);
			}
			//检查基础运价
			if(bt.getPrice() == null){
				throw new TransportException(TransportException.TYPE.ITSH315);
			}
			//检查运价年份
			if(bt.getYear() == null){
				throw new TransportException(TransportException.TYPE.ITSH316);
			}
			//检查里程
			if(bt.getMileage() == null){
				throw new TransportException(TransportException.TYPE.ITSH317);
			}
			//检查地区
			if(bt.getRegion() == null){
				throw new TransportException(TransportException.TYPE.ITSH330);
			}
			//乐观锁控制
			BasicTariff basicTariff = basicTariffService.findByUuid(bt.getUuid());
			if(bt.getVersion().equals(basicTariff.getVersion())){
				BasicTariff basicTariff2 = basicTariffService.checkBt(BeanConvertUtils.beanToBean(bt, BasicTariff.class) );
				if(basicTariff2!= null && basicTariff2.getUuid().equals(basicTariff.getUuid())){
					basicTariffService.updateBasicTariffByUuid(BeanConvertUtils.beanToBean(bt, BasicTariff.class), user);				
				}else{
					res.setMessage(Constants.message_004);
					res.setStatus(999);
				}
			}else{
				res.setMessage(Constants.message_005);
				res.setStatus(999);
			}
		} catch (BizException e) {
			log.error("updateBasicTariff error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("updateBasicTariff error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	
	/**
	 * 查找对象
	 * @param BasicTariff
	 * @return bt
	 */
	@RightAccess(2043)
	@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
	@ResponseBody
	@RequestMapping(value=URLMapping.BASICTARIFF_FINDBT, method = RequestMethod.POST  )
	public ResultDatas<BasicTariffVO> getBasicTariff(@RequestBody BasicTariffVO bt){
		ResultDatas<BasicTariffVO> res = new ResultDatas<>();
		try {
			
			//检查bt对象
			if(bt == null){
				throw new TransportException(TransportException.TYPE.ITSH312);
			}
			//检查uuid
			if(StringUtils.isNullOrEmpty(bt.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH318);
			}
			BasicTariff basicTariff = basicTariffService.findByUuid(bt.getUuid());
			res.setDatas(BeanConvertUtils.beanToBean(basicTariff, BasicTariffVO.class));
		} catch (BizException e) {
			log.error("getBasicTariff error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		} catch (Exception e) {
			log.error("getBasicTariff error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	/***************************以下方法对外提供*****************************************/
/*	@ResponseBody
<<<<<<< .working
	@RequestMapping(value=URLMapping.BASICTARIFF_FINDALL, method = RequestMethod.POST)	
=======
	@RequestMapping(value=URLMapping.BASICTARIFF_FINDALL, method = RequestMethod.POST  )	
>>>>>>> .merge-right.r13738
	public ResultDatas<Page<Map<String, Object>>> queryBasicTariffs(@RequestBody BasicTariffVO vo, SimplePageInfo pageInfo){
		ResultDatas<Page<Map<String, Object>>> res = new ResultDatas<>();
		try {
			//检查BasicTariffVO
			if(vo == null){
				throw new TransportException(TransportException.TYPE.ITSH312);
			}else{
				Page<BasicTariff> list = basicTariffService.findBasicTariffPageList(BeanConvertUtils.beanToBean(vo, BasicTariff.class), pageInfo);
				Page<Map<String, Object>> list2 = new Page<>();
				//存放所需数据
				for (BasicTariff basicTariff : list) {
					Map<String, Object> map = new HashMap<>();
					map.put("loadPort1", basicTariff.getLoadPort1());
					map.put("loadPort2", basicTariff.getLoadPort2());
					map.put("loadPort3", basicTariff.getLoadPort3());
					map.put("unloadPort1", basicTariff.getUnloadPort1());
					map.put("unloadPort2", basicTariff.getUnloadPort2());
					map.put("unloadPort3", basicTariff.getUnloadPort3());
					map.put("year", basicTariff.getYear());
					map.put("price", basicTariff.getPrice());
					map.put("mileage", basicTariff.getMileage());
					map.put("remark", basicTariff.getRemark());
					map.put("remark2", basicTariff.getRemark2());
					list2.add(map);
				}
				res.setPageCount(list.getPages());
				res.setPageNum(list.getPageNum());
				res.setPageSize(list.getPageSize());
				res.setDatas(list2);
			}			
		} catch (BizException e) {
			log.error("findBasicTariffList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("findBasicTariffList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}*/
	
	/**
	 * 根据年份获取所有数据
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.BASICTARIFF_FINDALL, method = RequestMethod.POST  )	
	public ResultDatas<List<Map<String, Object>>> queryBasicTariffs(@RequestBody BasicTariffVO vo, SimplePageInfo pageInfo){
		ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
		try {
			//检查BasicTariffVO
			if(vo == null){
				throw new TransportException(TransportException.TYPE.ITSH312);
			}else{
				List<BasicTariff> list = basicTariffService.findBasicTariffPageList(BeanConvertUtils.beanToBean(vo, BasicTariff.class));
				List<Map<String, Object>> list2 = new ArrayList<>();
				//存放所需数据
				for (BasicTariff basicTariff : list) {
					Map<String, Object> map = new HashMap<>();
					map.put("loadPort1", basicTariff.getLoadPort1());
					map.put("unloadPort1", basicTariff.getUnloadPort1());
					map.put("year", basicTariff.getYear());
					map.put("price", basicTariff.getPrice());
					map.put("mileage", basicTariff.getMileage());
					map.put("remark", basicTariff.getRemark());
					map.put("remark2", basicTariff.getRemark2());
					list2.add(map);
				}
				res.setDatas(list2);
			}			
		} catch (BizException e) {
			log.error("findBasicTariffList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("findBasicTariffList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	
	/**
	 * 前台调用接口提供
		 * @param BasicTariffQuery
		 * @return BasicTariffVO
	 */
//	@RolesAccess({Constants.ADMIN,Constants.INSPECTION,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.SALES_TRADER,Constants.INSPECTION,Constants.BUY_TRADER,
//		Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.SHIP_BROKER,Constants.SHIP_OWNER,Constants.TRADE_EXECUTOR,Constants.ENTER_MASTER,Constants.CHARTERER})
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.BASICTARIFF_FINDBT_BY_PORT, method = RequestMethod.POST  )
	public ResultDatas<BasicTariffVO> findBasicTariffByPort(@RequestBody BasicTariffQuery query){
		ResultDatas<BasicTariffVO> res = new ResultDatas<>();
		BasicTariffVO bt = null;
		try {
			List<Map<String, Object>> basicList= basicTariffService.getBasicTariffPageList(query);
			if(basicList.size() > 0 ){
				bt = BeanConvertUtils.beanToBean(basicList.get(0), BasicTariffVO.class);
			}
			if(bt != null){
				res.setDatas(bt);
			}
		} catch (BizException e) {
			log.error("findBasicTariffByError error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("findBasicTariffByError error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 查询现有所有港口
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.FINDALL_PORT_LIST, method = RequestMethod.POST  )
	public ResultDatas<Map<String, Object>> findAllPortList(@RequestBody BasicTariffQuery query,CurrentUser user){
		ResultDatas<Map<String, Object>> res = new ResultDatas<>();
		try {
			Map<String, Object> map = basicTariffService.findAllPortList();
			res.setDatas(map);
		} catch (BizException e) {
			log.error("findAllPortList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("findAllPortList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 运费小工具前台接口
	 */
//	@RolesAccess({Constants.ADMIN,Constants.INSPECTION,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.SALES_TRADER,Constants.INSPECTION,Constants.BUY_TRADER,
//		Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.SHIP_BROKER,Constants.SHIP_OWNER,Constants.TRADE_EXECUTOR,Constants.ENTER_MASTER,Constants.CHARTERER})
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.BASICTARIFF_TOOLS, method = RequestMethod.POST  )
	public ResultDatas<Map<String, Object>> freightTools(@RequestBody BasicTariffQuery query,CurrentUser user){
		ResultDatas<Map<String, Object>> res = new ResultDatas<>();
		try {
			Map<String, Object> result = basicTariffService.fieightTools(query);
			res.setDatas(result);
		} catch (BizException e) {
			log.error("freightTools error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("freightTools error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
}
