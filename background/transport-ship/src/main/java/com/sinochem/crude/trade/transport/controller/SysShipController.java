package com.sinochem.crude.trade.transport.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jetty.util.StringUtil;
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

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.HttpUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.URLMapping;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.domain.SysShip;
import com.sinochem.crude.trade.transport.model.ShipPactVO;
import com.sinochem.crude.trade.transport.model.SysShipVO;
import com.sinochem.crude.trade.transport.query.SysShipQuery;
import com.sinochem.crude.trade.transport.service.ShipPactService;
import com.sinochem.crude.trade.transport.service.SysShipService;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;

/**
 * @ClassName: SysShipController
 * @Description: 平台船舶信息维护
 * @author wangn
 * @date 2017-11-11 16:22:30
 * @version V1.0
 */
@Controller
public class SysShipController {
	@Autowired
	private SysShipService sysShipService;
	
	@Autowired
	private ShipPactService shipPactService;

	Log log = LogFactory.getLog(SysShipController.class);

	@InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	/**
	 * 新增平台船舶信息
	 * @param vo
	 * @return
	 * @exception
	 */
	@RightAccess(2002)
	@RolesAccess({Constants.ADMIN,Constants.SHIP_TRADER,Constants.SHIP_BROKER,Constants.SHIP_OWNER})
	@ResponseBody
	@RequestMapping(value=URLMapping.SAVE_SYS_SHIP, method = RequestMethod.POST )
	public Result saveSysShip(@RequestBody SysShipVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 船舶代码 IMO校验
			if (StringUtils.isNullOrEmpty(vo.getImo())) {
				throw new TransportException(TransportException.TYPE.ITSH202);
			}
			
			// 船名校验
			if (StringUtils.isNullOrEmpty(vo.getName())) {
				throw new TransportException(TransportException.TYPE.ITSH203);
			}
			
			// 建造年份校验
			if (null == vo.getCompleteDate()) {
				throw new TransportException(TransportException.TYPE.ITSH208);
			}
			// 船舶保存类型校验
			if(StringUtils.isNullOrEmpty(vo.getSaveType())){
				throw new TransportException(TransportException.TYPE.ITSH401);
			}
			
			// 新增平台船舶信息
			sysShipService.saveSysShip(vo, user);
			
			res.setMessage(Constants.message_020);
			
		} catch (BizException e) {
			log.error("saveSysShip error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("saveSysShip error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
	/**
	 * 修改平台船舶信息
	 * @param vo
	 * @return
	 * @exception
	 */
	@RightAccess(2009)
	@RolesAccess({Constants.ADMIN})
	@ResponseBody
	@RequestMapping(value=URLMapping.UPDATE_SYS_SHIP, method = RequestMethod.POST )
	public Result updateSysShip(@RequestBody SysShipVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 船舶uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH200);
			}
			
			// 船舶代码 IMO校验
			if (StringUtils.isNullOrEmpty(vo.getImo())) {
				throw new TransportException(TransportException.TYPE.ITSH202);
			}
			
			// 船名校验
			if (StringUtils.isNullOrEmpty(vo.getName())) {
				throw new TransportException(TransportException.TYPE.ITSH203);
			}
			
			// 建造年份校验
			if (null == vo.getCompleteDate()) {
				throw new TransportException(TransportException.TYPE.ITSH208);
			}
			
			// 修改平台船舶信息
			sysShipService.updateSysShip(vo, user);

			res.setMessage(Constants.message_021);
		
		} catch (BizException e) {
			log.error("updateSysShip error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("updateSysShip error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}

	/**
	 * 修改平台船舶状态（1待审核2未启用3已启用4已作废）
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.ADMIN})
	@ResponseBody
	@RequestMapping(value=URLMapping.UPDATE_SYS_SHIP_STATUS, method = RequestMethod.POST )
	public Result updateSysShipStatus(@RequestBody SysShipVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 船舶uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH200);
			}
			
			// 修改平台船舶状态
			sysShipService.updateSysShipStatus(BeanConvertUtils.beanToBean(vo, SysShip.class));
			res.setMessage(Constants.message_022);
		
		} catch (BizException e) {
			log.error("updateSysShipStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		
		} catch (Exception e) {
			log.error("updateSysShip error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}

	/**
	 * 删除平台船舶信息
	 * @param vo
	 * @return
	 * @exception
	 */
	@RightAccess(2011)
	@RolesAccess({Constants.ADMIN,Constants.SHIP_OWNER,Constants.SHIP_BROKER})
	@ResponseBody
	@RequestMapping(value=URLMapping.DEL_SYS_SHIP, method = RequestMethod.POST )
	public Result delSysShip(@RequestBody SysShipVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 船舶uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH200);
			}
			
			// 用户Id
			Long memberId = user.getMemberId();
			
			// 删除平台船舶信息
			sysShipService.delSysShip(BeanConvertUtils.beanToBean(vo, SysShip.class), memberId);
			res.setMessage(Constants.message_023);
			
		} catch (BizException e) {
			log.error("delSysShip error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
			
		} catch (Exception e) {
			log.error("delSysShip error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}

	/**
	 * 查询平台船舶信息详细
	 * @param vo
	 * @return
	 * @exception
	 */
	@RightAccess(2008)
	@RolesAccess({Constants.ADMIN,Constants.SHIP_OWNER,Constants.SHIP_BROKER,})
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_SYS_SHIP_DETAIL, method = RequestMethod.POST )
	public ResultDatas<SysShip> findSysShipDetail(@RequestBody SysShipVO vo, CurrentUser user) {

		ResultDatas<SysShip> res = new ResultDatas<>();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 船舶uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH200);
			}
			
			// 查询平台船舶信息详细
			SysShip sysShip= sysShipService.findSysShipDetail(BeanConvertUtils.beanToBean(vo, SysShip.class));
			
			// 设定返回数据
			res.setDatas(sysShip);
			
		} catch (BizException e) {
			log.error("findSysShipDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
			
		} catch (Exception e) {
			log.error("findSysShipDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
	/**
	 * 查询平台船舶名称下拉列表
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.ADMIN,Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_SYS_SHIP_LIST, method = RequestMethod.POST )
	public ResultDatas<List<SysShip>> findSysShipList( @RequestBody SysShipVO vo, CurrentUser user) {

		ResultDatas<List<SysShip>> res = new ResultDatas<>();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 查询状态为 "有效" 的船舶
			vo.setStatus(Constants.SYS_SHIP_STATUS_2);
			// 查询平台船舶信息列表
			List<SysShip> sysShipList= sysShipService.findSysShipList(BeanConvertUtils.beanToBean(vo, SysShip.class));
			
			// 设定返回数据
			res.setDatas(sysShipList);
			
		} catch (BizException e) {
			log.error("findSysShipList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
			
		} catch (Exception e) {
			log.error("findSysShipList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}

	/**
	 * 根据Imo、船名、船型查询平台船舶信息翻页列表(前台接口)
	 * @param vo
	 * @return
	 * @exception
	 */
//	@RolesAccess({Constants.ADMIN,Constants.INSPECTION,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.SALES_TRADER,Constants.INSPECTION,Constants.BUY_TRADER,
//		Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.SHIP_BROKER,Constants.SHIP_OWNER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_SYS_SHIP_BY_IMO_NAME, method = RequestMethod.POST )
	public ResultDatas<List<Map<String, Object>>> findSysShipPageList(@RequestBody SysShipVO vo) {

		ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
		
		try {
			SimplePageInfo pageInfo = vo;
			// 查询平台船舶信息翻页列表
			Page<Map<String, Object>> page= sysShipService.findSysShipPageList(BeanConvertUtils.beanToBean(vo, SysShip.class), pageInfo);
			
			// 设定返回数据
			res.setTotal(page.getTotal());
			res.setPageSize(vo.getPageSize());
			res.setPageNum(vo.getPageNum());
			res.setDatas(page.getResult());
			res.setStatus(0);
			
		} catch (BizException e) {
			log.error("findSysShipPageList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
			
		} catch (Exception e) {
			log.error("findSysShipPageList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
	/**
	 * 查询平台船舶信息翻页列表
	 * @param vo
	 * @return
	 * @exception
	 */
	@RightAccess(2001)
	@RolesAccess({Constants.ADMIN})
	@RequestMapping(value=URLMapping.GET_SYS_SHIP_PAGE_LIST)
	public void getSysShipPageList(CurrentUser user, ModelMap modelMap,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain, SysShipQuery query) {
		
		// 船舶名称
		Map<String, Object> map = new HashMap<String, Object>();
		String searchName = query.getName();
		map.put("name", searchName);
		
		// 总记录数
		int total = sysShipService.countRecords(map);
		
		//总记录数设定
		query.setTotalItem(Long.valueOf(total));
		
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		List<Map<String, Object>> sysShipList= sysShipService.getSysShipPageList(query);

		modelMap.put("user", user);
		modelMap.put("datas", sysShipList);
		modelMap.put("query", query);
		modelMap.put("searchName", searchName);
	}
	
	/**
	 * 根据uuid、查询船舶状态
	 * @param vo
	 * @return
	 * @exception
	 */
	@RightAccess(2010)
	@RolesAccess({Constants.ADMIN,Constants.SHIP_OWNER,Constants.SHIP_BROKER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_SYS_SHIP_STATUS, method = RequestMethod.POST )
	public ResultDatas<List<Map<String,Object>>> findSysShipStatus(@RequestBody SysShipVO vo, CurrentUser user) {

		ResultDatas<List<Map<String,Object>>> res = new ResultDatas<>();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 船舶uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH200);
			}
			
			// 查询平台船舶信息
			List<Map<String,Object>> sysShipStatus= sysShipService.findSysShipStatus(BeanConvertUtils.beanToBean(vo, SysShip.class));
			
			// 设定返回数据
			res.setDatas(sysShipStatus);
			
		} catch (BizException e) {
			log.error("findSysShipStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
			
		} catch (Exception e) {
			log.error("findSysShipStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
	/**
	 * 根据imo、查询船舶MMSI列表
	 * @param vo
	 * @return
	 * @exception
	 */
	@RightAccess(2004)
	@RolesAccess({Constants.ADMIN,Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_SYS_SHIP_MMSI, method = RequestMethod.POST )
	public ResultDatas<Map<String, Object>> findSysShipMmsi(@RequestBody SysShipVO vo, CurrentUser user) {

		ResultDatas<Map<String, Object>> res = new ResultDatas<>();
		
		try {
			// imo
			String imo = vo.getImo();
			
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}

			// 船舶imo校验
			if (StringUtils.isNullOrEmpty(imo)) {
				throw new TransportException(TransportException.TYPE.ITSH202);
			}
			
			// 当前年月日
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String dateString = formatter.format(currentTime);
			
			// md5(09fd2450680248cd933ac162cbfbddf9+当前年月日)得到key
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
    			// 设定返回数据
    			res.setDatas(mmsiObj);
    		} else {
    			// 设定返回数据
    			res.setDatas(null);
    		}
			
		} catch (BizException e) {
			log.error("findSysShipStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
			
		} catch (Exception e) {
			log.error("findSysShipStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
	/**
	 * 查询值集后台
	 * @param vo
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/sysShip/queryValueSet.json", method = RequestMethod.POST )
	public ResultDatas<Object> queryValueSet(@RequestBody ShipPactVO vo) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			if (StringUtils.isNullOrEmpty(vo.getValueSetType()) && StringUtils.isNullOrEmpty(vo.getValueSetTypes())) {
				throw new TransportException(TransportException.TYPE.ITSH089);
			}
			List<Map<String,Object>> map = shipPactService.queryValueSet(vo);
			res.setDatas(map);
		} catch (BizException e) {
			log.error("queryValueSet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("queryValueSet error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/**
	 * 船舶审核 （checkFlag：1审核通过 2审核驳回）
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.ADMIN})
	@ResponseBody
	@RequestMapping(value=URLMapping.CHECK_SHIP, method = RequestMethod.POST )
	public Result checkShip(@RequestBody SysShipVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			// 船舶uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH200);
			}
			// 船舶审核标识校验
			if (StringUtils.isNullOrEmpty(vo.getCheckFlag())) {
				throw new TransportException(TransportException.TYPE.ITSH402);
			}
			
			// 修改平台船舶状态
			sysShipService.checkShip(vo,user);
		
		} catch (BizException e) {
			log.error("checkShip error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		
		} catch (Exception e) {
			log.error("checkShip error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
	/**
	 * 批量更新数据库mmsi码
	 * @param vo
	 * @return
	 * @exception
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/testmmsi.json", method = RequestMethod.POST )
	public ResultDatas<Map<String, Object>> test(@RequestBody SysShipVO vo, CurrentUser user) {
		
		ResultDatas<Map<String, Object>> res = new ResultDatas<>();
		
		try {
			vo.setAliveFlag("1");
			List<SysShip> list = sysShipService.queryByEntitys(vo);
			Integer all = list.size();
			Integer succ = 0;
			Integer fal = 0;
			for (SysShip sysShip : list) {
				try {
					// imo
					String imo = sysShip.getImo();
					
					// 船舶imo校验
					if (StringUtils.isNullOrEmpty(imo)) {
						throw new TransportException(TransportException.TYPE.ITSH202);
					}
					
					// 当前年月日
					Date currentTime = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
					String dateString = formatter.format(currentTime);
					
					// md5(09fd2450680248cd933ac162cbfbddf9+当前年月日)得到key
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
								
								String str2 = (String)map.get("mmsi");
								sysShip.setUpdateDate(DateTimeUtils.currentDate());
								sysShip.setMmsi(str2);
								sysShipService.updateRecord(sysShip);
								log.info("#######imo=="+imo+",mmsi=="+str2);
								succ+=1;
							}
						}
					}
				} catch (Exception e) {
					fal+=1;
					e.printStackTrace();
				} 
				
			}
			log.info("共"+all+",成功"+succ+",失败"+fal);
			
		} catch (BizException e) {
			log.error("findSysShipStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
			
		} catch (Exception e) {
			log.error("findSysShipStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
	
	//------------------------------------------------------------
	/**
	 * 查询船舶信息翻页列表（船东/经纪人）
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER})
	@RequestMapping(value=URLMapping.SHIPOWNER_SYSSHIP_PAGE_LIST)
	public void shipOwnerSysShipPageList(CurrentUser user, ModelMap modelMap, SysShipQuery query) {
		
		// 验证登录信息
		if (user == null) {
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
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
		
		query.setPageSize(10);
		
		Page<Map<String,Object>> page = sysShipService.shipOwnerSysShipPageList(query,user);
		
		//总记录数设定
		query.setTotalItem(page.getTotal());

		modelMap.put("user", user);
		modelMap.put("datas", page);
		modelMap.put("query", query);
		modelMap.put("searchName", query.getName());
		modelMap.put("searchStatus", query.getStatus());
	}
	
	
	
	/**
	 * 查询船舶名称下拉列表(gyy)
	 * @param vo
	 * @return
	 * @exception
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_SYS_SHIP_NAME_LIST, method = RequestMethod.POST )
	public ResultDatas<List<Map<String,Object>>> findSysShipNameList( @RequestBody SysShipVO vo, CurrentUser user) {
		ResultDatas<List<Map<String,Object>>> res = new ResultDatas<>();
		try {
			
			// 查询平台船舶信息列表
			List<SysShip> sysShipList= sysShipService.findSysShipList(BeanConvertUtils.beanToBean(vo, SysShip.class));
			List<Map<String,Object>> strList = new ArrayList<>();
			for (SysShip sysShip : sysShipList) {
				Map<String,Object> map = new HashMap<>();
				if (!StringUtils.isNullOrEmpty(sysShip.getName())){
					map.put("name", sysShip.getName());
					map.put("uuid", sysShip.getUuid());
					strList.add(map);
				}
			}
			// 设定返回数据
			res.setDatas(strList);
			
		} catch (BizException e) {
			log.error("findSysShipNameList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
			
		} catch (Exception e) {
			log.error("findSysShipNameList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}

}