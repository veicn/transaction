package com.sinochem.crude.trade.transport.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import com.eyeieye.melody.web.locale.VisitorLocale;
import com.github.pagehelper.Page;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.URLMapping;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.domain.Pallet;
import com.sinochem.crude.trade.transport.domain.ShipPlate;
import com.sinochem.crude.trade.transport.model.ShipPlateVO;
import com.sinochem.crude.trade.transport.query.SysShipQuery;
import com.sinochem.crude.trade.transport.query.Trader;
import com.sinochem.crude.trade.transport.service.CommonService;
import com.sinochem.crude.trade.transport.service.IntentionService;
import com.sinochem.crude.trade.transport.service.PalletService;
import com.sinochem.crude.trade.transport.service.ShipPlateService;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * @ClassName: ShipPlateController
 * @Description: 船盘信息维护
 * @author wangn
 * @date 2017-11-11 16:22:30
 * @version V1.0
 */
@Controller
public class ShipPlateController  {
	@Autowired
	private ShipPlateService shipPlateService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private PalletService palletService;
	@Autowired
	private IntentionService intentionService;
	@Autowired
	private CommonService commonService;
	
	 
	Log log = LogFactory.getLog(ShipPlateController.class);
	
	@InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormatDate = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatDate.setLenient(false);
        SimpleDateFormat dateFormatDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormatDateTime.setLenient(false);
        binder.registerCustomEditor(Date.class, "completeDate", new CustomDateEditor(dateFormatDate, false));
        binder.registerCustomEditor(Date.class, "eta", new CustomDateEditor(dateFormatDateTime, false));
    }

	/**
	 * 新增船盘信息
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.SAVE_SHIP_PLATE, method = RequestMethod.POST )
	public Result saveShipPlate(@RequestBody ShipPlateVO vo, CurrentUser user) {
		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			// 船名校验
			if (StringUtils.isNullOrEmpty(vo.getName())) {
				throw new TransportException(TransportException.TYPE.ITSH203);
			}
			//船舶uuid校验
			if (StringUtils.isNullOrEmpty(vo.getSysShipUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH200);
			}
			
			// 新增船盘信息
			shipPlateService.saveShipPlate(BeanConvertUtils.beanToBean(vo, ShipPlate.class), user);
			res.setMessage(Constants.message_015);
			
		} catch (BizException e) {
			log.error("saveShipPlate error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("saveShipPlate error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
	/**
	 * 修改船盘信息
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.UPDATE_SHIP_PLATE, method = RequestMethod.POST )
	public Result updateShipPlate(@RequestBody ShipPlateVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			// 船盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH012);
			}
			
			// 修改船盘信息
			shipPlateService.updateShipPlate(vo,user.getEpMemberId(),user.getMemberId());
			res.setMessage(Constants.message_016);
			
		} catch (BizException e) {
			log.error("updateShipPlate error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("updateShipPlate error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}

	
	/**
	 * 修改船盘状态（1已发布2洽谈中3已确认租船）(作废)
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.UPDATE_SHIP_PLATE_STATUS, method = RequestMethod.POST )
	public Result updateShipPlateStatus(@RequestBody ShipPlateVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			// 船盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH012);
			}
			
			vo.setSaveType("1");	//保存后船盘状态为：已发布
			
			// 修改船盘状态
			shipPlateService.updateShipPlateStatus(BeanConvertUtils.beanToBean(vo, ShipPlate.class),vo.getSaveType(),user);
			
			if ("1".equals(vo.getSaveType())){
				res.setMessage(Constants.message_018);
			} else if ("4".equals(vo.getSaveType())){
				res.setMessage(Constants.message_017);
			}
		
		} catch (BizException e) {
			log.error("updateShipPlateStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		
		} catch (Exception e) {
			log.error("updateShipPlateStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	

	/**
	 * 撤销操作，逻辑删除船盘信息
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.DEL_SHIP_PLATE, method = RequestMethod.POST )
	public Result delShipPlate(@RequestBody ShipPlateVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			// 船盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH012);
			}
			
			// 删除船盘信息
			shipPlateService.delShipPlate(BeanConvertUtils.beanToBean(vo, ShipPlate.class), user);
			res.setMessage(Constants.message_019);
			
		} catch (BizException e) {
			log.error("delShipPlate error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("delShipPlate error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
	
	/**
	 * 撤销操作，逻辑删除船盘信息（om平台）
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.ADMIN})
	@ResponseBody
	@RequestMapping(value=URLMapping.DEL_SHIP_PLATE_OM, method = RequestMethod.POST )
	public Result delShipPlateOM(@RequestBody ShipPlateVO vo, CurrentUser user) {
		
		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			// 船盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH012);
			}
			
			// 删除船盘信息
			shipPlateService.delShipPlateOM(BeanConvertUtils.beanToBean(vo, ShipPlate.class), user);
			res.setMessage(Constants.message_019);
			
		} catch (BizException e) {
			log.error("delShipPlateOM error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("delShipPlateOM error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
	
	/**
	 * 批量删除船盘（om平台）
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.ADMIN})
	@ResponseBody
	@RequestMapping(value=URLMapping.BATCH_DEL_SHIPPLATE_OM, method = RequestMethod.POST )
	public Result batchDelShipPlateOM(@RequestBody ShipPlateVO vo, CurrentUser user) {
		
		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			// 船盘uuids校验
			if (StringUtils.isNullOrEmpty(vo.getUuidStr())) {
				throw new TransportException(TransportException.TYPE.ITSH012);
			}
			
			String uuidStr = vo.getUuidStr();
			String[] uuids = uuidStr.split(",");
			
			// 批量删除船盘信息
			if(uuids!=null && uuids.length>0){
				for (String uuid : uuids) {
					shipPlateService.batchDelShipPlateOM(uuid, user);
				}
			}
			
			res.setMessage(Constants.message_019);
			
		} catch (BizException e) {
			log.error("delShipPlate error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("delShipPlate error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	

	/**
	 * 查询船盘信息详细
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_SHIP_PLATE_DETAIL, method = RequestMethod.POST )
	public ResultDatas<ShipPlate> findShipPlateDetail(@RequestBody ShipPlateVO vo, CurrentUser user) {

		ResultDatas<ShipPlate> res = new ResultDatas<>();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			// 船盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH012);
			}
			
			// 查询船盘信息详细
			ShipPlate shipPlate= shipPlateService.findShipPlateDetail(BeanConvertUtils.beanToBean(vo, ShipPlate.class));
			
			// 设定返回数据
			res.setDatas(shipPlate);
			
		} catch (BizException e) {
			log.error("findShipPlateDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("findShipPlateDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
	
	/**
	 * 查询船盘信息详细（om平台）
	 * @param vo
	 * @return
	 * @exception
	 */
	@RightAccess(2016)
	@RolesAccess({Constants.ADMIN})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_SHIP_PLATE_DETAIL_OM, method = RequestMethod.POST )
	public ResultDatas<ShipPlate> findShipPlateDetailOM(@RequestBody ShipPlateVO vo, CurrentUser user) {

		ResultDatas<ShipPlate> res = new ResultDatas<>();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			// 船盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH012);
			}
			
			// 查询船盘信息详细
			ShipPlate shipPlate= shipPlateService.findShipPlateDetail(BeanConvertUtils.beanToBean(vo, ShipPlate.class));
			
			// 设定返回数据
			res.setDatas(shipPlate);
			
		} catch (BizException e) {
			log.error("findShipPlateDetailOM error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("findShipPlateDetailOM error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
	
	/**
	 * 查询船盘信息列表(前台接口，船盘滚动信息)
	 * @param vo
	 * @return
	 * @exception
	 */
	@ResponseBody
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@RequestMapping(value=URLMapping.FIND_SHIP_PLATE_LIST, method = RequestMethod.POST )
	public ResultDatas<List<Map<String,Object>>> findShipPlateList(ShipPlateVO vo, CurrentUser user) {

		ResultDatas<List<Map<String,Object>>> res = new ResultDatas<>();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			// 查询船盘信息列表
			List<Map<String,Object>> shipPlateList= shipPlateService.findShipPlateList(BeanConvertUtils.beanToBean(vo, ShipPlate.class));
			
			// 设定返回数据
			res.setDatas(shipPlateList);
			
		} catch (BizException e) {
			log.error("findShipPlateList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
			
		} catch (Exception e) {
			log.error("findShipPlateList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}

	
	/**
	 * 查询船盘信息详细(前台接口,返回结果包括船盘、船舶、附件信息)
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_SHIP_PLATE_DETAIL_FORGROUND, method = RequestMethod.POST )
	public ResultDatas<Object> findShipPlateDetailForground(@RequestBody ShipPlateVO vo, CurrentUser user) {

		ResultDatas<Object> res = new ResultDatas<>();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			// 船盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH012);
			}
			
			Map<String, Object> map = shipPlateService.findShipPlateDetailForground(vo, user);
			
			// 设定返回数据
			res.setDatas(map);
			
		} catch (BizException e) {
			log.error("findShipPlateDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
			
		} catch (Exception e) {
			log.error("findShipPlateDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}

	
	/**
	 * 查询船盘信息翻页列表(作废)
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_SHIP_PLATE_PAGE_LIST, method = RequestMethod.POST )
	public ResultDatas<Page<ShipPlate>> findShipPlatePageList(@RequestBody ShipPlateVO vo, SimplePageInfo pageInfo) {

		ResultDatas<Page<ShipPlate>> res = new ResultDatas<>();
		
		try {
			// 船盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH012);
			}
			
			// 查询船盘信息翻页列表
			Page<ShipPlate> shipPlateList= shipPlateService.findShipPlatePageList(BeanConvertUtils.beanToBean(vo, ShipPlate.class), pageInfo);
			
			// 设定返回数据
			res.setDatas(shipPlateList);
			
		} catch (BizException e) {
			log.error("findShipPlatePageList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
			
		} catch (Exception e) {
			log.error("findShipPlatePageList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
	
	/**
	 * 查询平台船盘信息翻页列表（船东/经纪人）
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER})
	@RequestMapping(value=URLMapping.GET_SHIP_PLATE_PAGE_LIST)
	public void getSysShipPageList(CurrentUser user, ModelMap modelMap, SysShipQuery query) {
		
		// 验证登录信息
		if (user == null) {
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		
		//状态校验
		if(StringUtils.isNullOrEmpty(query.getStatus())){
			query.setStatus("1");	//首次加载，默认访问“已发布”状态列表
		}
		
		query.setRelType("1");	//作为“船东/经纪人”，身份查询
		
		//处理中文船名乱码
		if(!StringUtils.isNullOrEmpty(query.getName())){
			String name = query.getName();
			try {
				String nameStr=new String(name.getBytes("iso8859-1"),"UTF-8");
				query.setName(nameStr.trim());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		// 每页10条
		query.setPageSize(10);
		// 指定状态的当前页数据
		Page<Map<String,Object>> page= shipPlateService.getShipPlatePageList(query, user);
		
		
		//查询不同状态的总条数
		Map<String, Object> map=new HashMap<>();
		map.put("epMemberId", user.getEpMemberId());
		map.put("relType", "1");	//船盘发布类型（1船东/船东经纪人2转租船东）
		
		map.put("status", "1");
		int publishedCount = shipPlateService.countRecordsAll(map);
		map.put("status", "2");
		int talkingCount = shipPlateService.countRecordsAll(map);
		map.put("status", "3");
		int confirmShipCount = shipPlateService.countRecordsAll(map);
		
		
		// 指定状态下的总记录数设定
		query.setTotalItem(page.getTotal());

		modelMap.put("user", user);
		modelMap.put("datas", page);
		modelMap.put("query", query);
		modelMap.put("searchName", query.getName());
		modelMap.put("searchStatus", query.getStatus());
		modelMap.put("publishedCount", publishedCount);
		modelMap.put("talkingCount", talkingCount);
		modelMap.put("confirmShipCount", confirmShipCount);
	}
	
	
	/**
	 * 查询平台船盘信息翻页列表（转租船东）
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_TRADER})
	@RequestMapping(value=URLMapping.GET_AGENT_SHIPPLATE_PAGE_LIST)
	public void getAgentSysShipPageList(CurrentUser user, ModelMap modelMap, SysShipQuery query) {
		
		// 验证登录信息
		if (user == null) {
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		
		//状态校验
		if(StringUtils.isNullOrEmpty(query.getStatus())){
			query.setStatus("1");	//首次加载，默认访问“已发布”状态列表
		}
		
		query.setRelType("2");	//作为“转租船东”，身份查询
		
		//处理中文船名乱码
		if(!StringUtils.isNullOrEmpty(query.getName())){
			String name = query.getName();
			try {
				String nameStr=new String(name.getBytes("iso8859-1"),"UTF-8");
				query.setName(nameStr.trim());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		// 每页10条
		query.setPageSize(10);
		// 指定状态的当前页数据
		Page<Map<String,Object>> page= shipPlateService.getShipPlatePageList(query, user);
		
		
		//查询不同状态的总条数
		Map<String, Object> map=new HashMap<>();
		map.put("epMemberId", user.getEpMemberId());
		map.put("relType", "2");	//船盘发布类型（1船东/船东经纪人2转租船东）
		
		map.put("status", "1");
		int publishedCount = shipPlateService.countRecordsAll(map);
		map.put("status", "2");
		int talkingCount = shipPlateService.countRecordsAll(map);
		map.put("status", "3");
		int confirmShipCount = shipPlateService.countRecordsAll(map);
		
		
		// 指定状态下的总记录数设定
		query.setTotalItem(page.getTotal());

		modelMap.put("user", user);
		modelMap.put("datas", page);
		modelMap.put("query", query);
		modelMap.put("searchName", query.getName());
		modelMap.put("searchStatus", query.getStatus());
		modelMap.put("publishedCount", publishedCount);
		modelMap.put("talkingCount", talkingCount);
		modelMap.put("confirmShipCount", confirmShipCount);
	}
	
	
	/**
	 * 查询船盘信息翻页列表（om平台）
	 * @param vo
	 * @return
	 * @exception
	 */
	@RightAccess(2012)
	@RolesAccess({Constants.ADMIN})
	@RequestMapping(value=URLMapping.GET_OM_SHIPPLATE_PAGE_LIST)
	public void omSysShipPageList(CurrentUser user, ModelMap modelMap,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain, SysShipQuery query) {
		
		// 验证登录信息
		if (user == null) {
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		
		//处理中文船名乱码
		if(!StringUtils.isNullOrEmpty(query.getName())){
			String name = query.getName();
			try {
				String nameStr=new String(name.getBytes("iso8859-1"),"UTF-8");
				query.setName(nameStr.trim());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if(!StringUtils.isNullOrEmpty(query.getBrokerName())){
			String brokerName = query.getBrokerName();
			try {
				String brokerNameStr=new String(brokerName.getBytes("iso8859-1"),"UTF-8");
				query.setBrokerName(brokerNameStr.trim());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if(!StringUtils.isNullOrEmpty(query.getEpMembername())){
			String epMembername = query.getEpMembername();
			try {
				String epMembernameStr=new String(epMembername.getBytes("iso8859-1"),"UTF-8");
				query.setEpMembername(epMembernameStr.trim());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if(!StringUtils.isNullOrEmpty(query.getShipOwner())){
			String shipOwner = query.getShipOwner();
			try {
				String shipOwnerStr=new String(shipOwner.getBytes("iso8859-1"),"UTF-8");
				query.setShipOwner(shipOwnerStr.trim());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		
		Page<Map<String,Object>> pageData= shipPlateService.getShipPlatePageListOM(query, user);
		
		query.setTotalItem(pageData.getTotal());
		
		modelMap.put("user", user);
		modelMap.put("datas", pageData);
		modelMap.put("query", query);
		
		modelMap.put("searchAgeBeg", query.getAgeBeg());
		modelMap.put("searchAgeEnd", query.getAgeEnd());
		modelMap.put("searchPublishBeg", query.getPublishBeg());
		modelMap.put("searchPublishEnd", query.getPublishEnd());
		modelMap.put("searchOpenBeg", query.getOpenBeg());
		modelMap.put("searchOpenEnd", query.getOpenEnd());
		modelMap.put("searchETABeg", query.getETABeg());
		modelMap.put("searchETAEnd", query.getETAEnd());
	}
	
	
	/**
	 * 船盘列表_发送报盘（转租船东）
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_TRADER})
	@RequestMapping(value=URLMapping.SEND_CLAUSE_SHIPPLATE_LIST)
	public void sendClauseShipPlateList(CurrentUser user, ModelMap modelMap, SysShipQuery query) {
		
		// 验证登录信息
		if (user == null) {
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		
		//每页10条
		query.setPageSize(10);
		
		Page<Map<String,Object>> page= shipPlateService.sendClauseShipPlateList(query, user);
		
		// 总记录数设定
		query.setTotalItem(page.getTotal());
		
		modelMap.put("user", user);
		modelMap.put("datas", page);
		modelMap.put("query", query);
	}
	
	
	/**
	 * 查询代理船盘信息翻页列表
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.MOBILEQUERY_SHIPPLATE_LIST ,method = RequestMethod.POST)
	public ResultDatas<Object> queryAgencyShipPletePageList(@RequestBody ShipPlateVO vo,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		
		try {
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			SimplePageInfo pageInfo = vo.getPageInfo();
			
			Page<ShipPlate> page = shipPlateService.findShipPlatePageList(vo,pageInfo);
			List<ShipPlate> spList = BeanConvertUtils.beanToBeanInList(page, ShipPlate.class);
			
			//根据语言环境设置epMemberName、shipOwner、broker
			if ("en".equals(lang)){
				if(spList!=null && !spList.isEmpty()){
					for (ShipPlate plate : spList) {
						//epMemberName
						if(!StringUtils.isNullOrEmpty(plate.getEpMemberNameEn())){
							plate.setEpMemberName(plate.getEpMemberNameEn());
						}else if(plate.getEpMemberId()!=null){
							plate.setEpMemberName(commonService.findNameByEpMemberId(plate.getEpMemberId()));
						}
						//shipOwner
						if(!StringUtils.isNullOrEmpty(plate.getShipOwnerEn())){
							plate.setShipOwner(plate.getShipOwnerEn());
						}else if(plate.getShipOwnerId()!=null){
							plate.setShipOwner(commonService.findNameByEpMemberId(plate.getShipOwnerId()));
						}
						//broker
						if(!StringUtils.isNullOrEmpty(plate.getBrokerNameEn())){
							plate.setBrokerName(plate.getBrokerNameEn());
						}else if(plate.getBrokerId()!=null){
							plate.setBrokerName(commonService.findNameByEpMemberId(plate.getBrokerId()));
						}
					}
				}
			}
			
			res.setDatas(spList);
			res.setPageNum(page.getPageNum());
			res.setPageCount(page.getPages());
			res.setPageSize(page.getPageSize());
			
		} catch (BizException e) {
			log.error("queryAgencyShipPletePageList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("queryAgencyShipPletePageList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	
	/**
	 * 查询船盘翻页列表（前台接口）
	 * @param vo
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.QUERY_SHIPPLATE_LIST, method = RequestMethod.POST  )
	public ResultDatas<Object> queryShipPlateList(@RequestBody ShipPlateVO vo,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		
		try {
			
			//验证登录信息
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			SimplePageInfo pageInfo = vo.getPageInfo();
			Page<Map<String,Object>> page = shipPlateService.queryShipPlateList(BeanConvertUtils.beanToMap(vo),pageInfo);
			List<ShipPlate> spList = BeanConvertUtils.mapToBeanInList(page, ShipPlate.class);
			
			//根据语言环境设置epMemberName、shipOwner、broker
			if ("en".equals(lang)){
				if(spList!=null && spList.size()>0){
					for (ShipPlate plate : spList) {
						//epMemberName
						if(!StringUtils.isNullOrEmpty(plate.getEpMemberNameEn())){
							plate.setEpMemberName(plate.getEpMemberNameEn());
						}else if(plate.getEpMemberId()!=null){
							plate.setEpMemberName(commonService.findNameByEpMemberId(plate.getEpMemberId()));
						}
						//shipOwner
						if(!StringUtils.isNullOrEmpty(plate.getShipOwnerEn())){
							plate.setShipOwner(plate.getShipOwnerEn());
						}else if(plate.getShipOwnerId()!=null){
							plate.setShipOwner(commonService.findNameByEpMemberId(plate.getShipOwnerId()));
						}
						//broker
						if(!StringUtils.isNullOrEmpty(plate.getBrokerNameEn())){
							plate.setBrokerName(plate.getBrokerNameEn());
						}else if(plate.getBrokerId()!=null){
							plate.setBrokerName(commonService.findNameByEpMemberId(plate.getBrokerId()));
						}
					}
				}
			}
			
			// 设定返回数据
			res.setTotal(page.getTotal());
			res.setDatas(spList);
			res.setPageNum(page.getPageNum());
			res.setPageCount(page.getPages());
			res.setPageSize(page.getPageSize());
		} catch (BizException e) {
			log.error("queryShipPactList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("queryShipPactList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	
	/**
	 * 查询全部船盘信息列表(前台接口：查看更多)
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.QUERY_ALL_SHIPPLATE_LIST, method = RequestMethod.POST )
	public ResultDatas<List<Map<String,Object>>> queryShipPlatePageList(@RequestBody ShipPlateVO vo, CurrentUser user) {
		ResultDatas<List<Map<String,Object>>> res = new ResultDatas<>();
		//获取当前语言环境
		Locale current = VisitorLocale.getCurrent();
		String lang = current.getLanguage();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			
			SimplePageInfo pageInfo = vo.getPageInfo();
			
			// 查询船盘信息列表
			Page<Map<String,Object>> page = shipPlateService.queryShipPlatePageList(BeanConvertUtils.beanToBean(vo, ShipPlate.class),pageInfo);
			List<Map<String,Object>> list =page;
			
			
			//根据语言环境设置epMemberName、shipOwner、broker
			if ("en".equals(lang)){
				if(list!=null && !list.isEmpty()){
					for (Map<String,Object> map : list) {
						//epMemberName
						Object obj1=map.get("epMemberNameEn");
						Object obj2=map.get("epMemberId");
						if(obj1!=null){
							map.put("epMemberName",(String)obj1);
							map.put("ext1",(String)obj1);
						}else if(obj2!=null){
							map.put("epMemberName",commonService.findNameByEpMemberId((Long)obj2));
							map.put("ext1",commonService.findNameByEpMemberId((Long)obj2));
						}
						//shipOwner
						Object obj3=map.get("shipOwnerEn");
						Object obj4=map.get("shipOwnerId");
						if(obj3!=null){
							map.put("shipOwner",(String)obj3);
						}else if(obj4!=null){
							map.put("shipOwner",commonService.findNameByEpMemberId((Long)obj4));
						}
						//broker
						Object obj5=map.get("brokerNameEn");
						Object obj6=map.get("brokerId");
						if(obj5!=null){
							map.put("brokerName",(String)obj5);
						}else if(obj6!=null){
							map.put("brokerName",commonService.findNameByEpMemberId((Long)obj6));
						}
					}
				}
			}else{
				if(list!=null && !list.isEmpty()){
					for (Map<String,Object> map : list) {
						map.put("ext1",map.get("epMemberName"));
					}
				}
			}
			
			// 设定返回数据
			res.setTotal(page.getTotal());
			res.setDatas(list);
			res.setPageNum(page.getPageNum());
			res.setPageCount(page.getPages());
			res.setPageSize(page.getPageSize());
			
		} catch (BizException e) {
			log.error("queryShipPlatePageList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
			
		} catch (Exception e) {
			log.error("queryShipPlatePageList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
	
	/*******************************************3/31*******************************/
	/**
	 * 船盘推荐
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@RequestMapping(value=URLMapping.RECOMMEND_SHIPPLATE)
	public void recommendShipPlateShipOwner(CurrentUser user, ModelMap modelMap,SysShipQuery query) {
		// 验证登录信息
		if (user == null) {
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		if(StringUtils.isNullOrEmpty(query.getPalletUuid())){
			throw new TransportException(TransportException.TYPE.ITSH016);
		}
		Pallet pallet = palletService.findByUuid(query.getPalletUuid());
		if(pallet == null ){ 
			throw new TransportException(TransportException.TYPE.ITSH017);
		}
		//先选船时
		if(pallet.getPlateSel()!=null&&"1".equals(pallet.getPlateSel())&&!StringUtils.isNullOrEmpty(pallet.getShipPlateUuid())){
			ShipPlate shipPlate = shipPlateService.findByUuid(pallet.getShipPlateUuid());
			
			if(shipPlate==null){
				throw new TransportException(TransportException.TYPE.ITSH013);
			}
			//推荐选择船盘
			Map<String, Object> choice =  BeanConvertUtils.beanToMap(shipPlate);
			modelMap.put("choice", choice);
			//提示有选定船盘
			modelMap.put("haveChoice","1");
		}else{
			modelMap.put("haveChoice","0");
		}
		String traderSel = pallet.getTraderSel();
		//是否指定转租船东
		if(traderSel!=null&&"1".equals(traderSel)){
			modelMap.put("traderSel",traderSel);
		}else{
			Map<String, Object> param=new HashMap<>();
			param.put("palletUuid", pallet.getUuid());
			List<Map<String, Object>> intentions =  intentionService.queryRecords(param);
			if(intentions!=null && !intentions.isEmpty()){
				modelMap.put("traderSel","1");
			}else{
				modelMap.put("traderSel","0");
			}
		}
		//排序条件
		Map<String,Object> map = new HashMap<>();
		map.put("shipType", pallet.getShipType());
		map.put("shipAgeBeg", pallet.getShipAgeBeg());
		map.put("shipAgeEnd", pallet.getShipAgeEnd());
		//推荐船盘
		List<Map<String,Object>> recommend= shipPlateService.recommendShipPlate(query,map);
		modelMap.put("datas", recommend);
		modelMap.put("user", user);
		modelMap.put("query", query);
	}
	
	
	/**
	 * 更多船盘
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@RequestMapping(value=URLMapping.MORESHIPPLATE)
	public void moreShipplateShipper(CurrentUser user, ModelMap modelMap,SysShipQuery query) {
		if (user == null) {
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		if(StringUtils.isNullOrEmpty(query.getPalletUuid())){
			throw new TransportException(TransportException.TYPE.ITSH016);
		}
		Pallet pallet = palletService.findByUuid(query.getPalletUuid());
		if(pallet == null ){
			throw new TransportException(TransportException.TYPE.ITSH017);
		}
		Page<Map<String, Object>> ShipPlates= shipPlateService.moreShipplate(query);
		String searchName = query.getName();
		modelMap.put("searchName", searchName);
		modelMap.put("user", user);
		modelMap.put("datas", ShipPlates);
		modelMap.put("query", query);
	}
	/**
	 * 新增船盘权限
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_TRADER})
	@RequestMapping(value="/shipPlate/shipAgentEditShipPlate.htm")
	public void shipAgentEditShipPlate(CurrentUser user, ModelMap modelMap, SysShipQuery query) {
		
		// 验证登录信息
		if (user == null) {
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}

		modelMap.put("user", user);
		modelMap.put("query", query);
	}
	
	
	
	/**
	 * 查询承运商列表（4船东,5船经纪人,7转租船东）
	 * @param vo
	 * @param user
	 * @return
	 */
	@RightAccess(2014)
	@ResponseBody
	@RolesAccess({Constants.ADMIN})
	@RequestMapping(value=URLMapping.QUERY_TRANDERNAME, method = RequestMethod.POST  )
	public ResultDatas<Object> queryWaybillListForBack(@RequestBody Trader trader) {
		ResultDatas<Object> res = new ResultDatas<>();
		
		/*会员资质类型 1炼厂,2贸易商,3商检,4船东,5船经纪人,6船代,7转租船东*/
		List<String> typeList=new ArrayList<>();
		typeList.add("4");
		typeList.add("5");
		typeList.add("7");
		
		try {
			List<Map<String,Object>> list = new ArrayList<>();
			try {
				//多种资质查询
				for (String type : typeList) {
					List<EnterpriseVo> list2 = enterpriseService.selectByCredentials(type);
					if (list2 != null && !list2.isEmpty()){
						for (EnterpriseVo enterpriseVo : list2) {
							if (enterpriseVo.getMemberId() != null){
								Map<String,Object> map = new HashMap<>();
								map.put("traderId",enterpriseVo.getMemberId());
								if(!StringUtils.isNullOrEmpty(enterpriseVo.getEnglishName())){
									map.put("traderName", enterpriseVo.getEnglishName());
								}else{
									map.put("traderName",enterpriseVo.getFullName());
								}
								map.put("type",type);
								list.add(map);
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("根据资质查询会员接口异常 ",e);
			}
			res.setDatas(list);
		} catch (BizException e) {
			log.error("根据资质查询会员接口异常", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("根据资质查询会员接口异常", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
}