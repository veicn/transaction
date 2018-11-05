package com.sinochem.crude.trade.transport.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.sinochem.crude.trade.transport.domain.Tide;
import com.sinochem.crude.trade.transport.model.TideVO;
import com.sinochem.crude.trade.transport.query.TideQuery;
import com.sinochem.crude.trade.transport.service.TideService;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;

/**
 * @ClassName: TideController
 * @Description: 潮汐信息维护
 * @author wangn
 * @date 2017-11-11 16:22:30
 * @version V1.0
 */
@Controller
public class TideController {
	@Autowired
	private TideService tideService;
	
	Log log = LogFactory.getLog(TideController.class);
	
	@InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	/**
	 * 新增潮汐信息
	 * @param vo
	 * @return
	 * @exception
	 */
	@RightAccess(2054)
	@RolesAccess({Constants.ADMIN})
	@ResponseBody
	@RequestMapping(value=URLMapping.SAVE_TIDE, method = RequestMethod.POST )
	public Result saveTide(@RequestBody TideVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 港口code校验
			if (StringUtils.isNullOrEmpty(vo.getPortCode())) {
				throw new TransportException(TransportException.TYPE.ITSH222);
			}
			
			// 港口名称
			if (StringUtils.isNullOrEmpty(vo.getPortName())) {
				throw new TransportException(TransportException.TYPE.ITSH223);
			}
			
			// 日期
			if (null == vo.getDate()) {
				throw new TransportException(TransportException.TYPE.ITSH227);
			}
			
			// 新增潮汐信息
			tideService.saveTide(BeanConvertUtils.beanToBean(vo, Tide.class), vo.getUuid(), user.getEpMemberId());
			
			res.setMessage(Constants.message_024);
			
		} catch (BizException e) {
			log.error("saveTide error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("saveTide error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 修改潮汐信息
	 * @param vo
	 * @return
	 * @exception
	 */
	@RightAccess(2056)
	@RolesAccess({Constants.ADMIN})
	@ResponseBody
	@RequestMapping(value=URLMapping.UPDATE_TIDE, method = RequestMethod.POST )
	public Result updateTide(@RequestBody TideVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 潮汐uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH220);
			}
			
			// 港口code校验
			if (StringUtils.isNullOrEmpty(vo.getPortCode())) {
				throw new TransportException(TransportException.TYPE.ITSH222);
			}
			
			// 港口名称
			if (StringUtils.isNullOrEmpty(vo.getPortName())) {
				throw new TransportException(TransportException.TYPE.ITSH223);
			}
			
			// 日期
			if (null == vo.getDate()) {
				throw new TransportException(TransportException.TYPE.ITSH227);
			}
			
			// 修改潮汐信息
			tideService.updateTide(BeanConvertUtils.beanToBean(vo, Tide.class), user.getEpMemberId());
			res.setMessage(Constants.message_025);
		
		} catch (BizException e) {
			log.error("updateTide error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		
		} catch (Exception e ) {
			log.error("updateTide error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}

	/**
	 * 删除潮汐信息
	 * @param vo
	 * @return
	 * @exception
	 */
	@RightAccess(2058)
	@RolesAccess({Constants.ADMIN})
	@ResponseBody
	@RequestMapping(value=URLMapping.DEL_TIDE, method = RequestMethod.POST )
	public Result delTide(@RequestBody TideVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 潮汐uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH220);
			}
			
			// 用户Id
			Long memberId = user.getMemberId();
			
			// 删除潮汐信息
			tideService.delTide(BeanConvertUtils.beanToBean(vo, Tide.class), memberId);
			res.setMessage(Constants.message_026);
			
		} catch (BizException e) {
			log.error("delTide error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
			
		} catch (Exception e ) {
			log.error("delTide error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}

	/**
	 * 查询潮汐信息详细
	 * @param vo
	 * @return
	 * @exception
	 */
	@RightAccess(2057)
	@RolesAccess({Constants.ADMIN,Constants.ENTER_MASTER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_TIDE_DETAIL, method = RequestMethod.POST )
	public ResultDatas<Tide> findTideDetail(@RequestBody TideVO vo, CurrentUser user) {

		ResultDatas<Tide> res = new ResultDatas<>();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 潮汐uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH220);
			}
			
			// 查询潮汐信息详细
			Tide tide= tideService.findTideDetail(BeanConvertUtils.beanToBean(vo, Tide.class));
			
			// 设定返回数据
			res.setDatas(tide);
			
		} catch (BizException e) {
			log.error("findTideDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
			
		} catch (Exception e ) {
			log.error("findTideDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 查询潮汐信息列表
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.ADMIN,Constants.ENTER_MASTER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_TIDE_LIST, method = RequestMethod.POST )
	public ResultDatas<List<Tide>> findTideList(@RequestBody  TideVO vo, CurrentUser user) {

		ResultDatas<List<Tide>> res = new ResultDatas<>();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 查询潮汐信息列表
			List<Tide> tideList= tideService.findTideList(BeanConvertUtils.beanToBean(vo, Tide.class));
			
			// 设定返回数据
			res.setDatas(tideList);
			
		} catch (BizException e) {
			log.error("findTideList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
			
		} catch (Exception e ) {
			log.error("findTideList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 查询潮汐信息翻页列表
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.ADMIN,Constants.ENTER_MASTER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_TIDE_PAGE_LIST, method = RequestMethod.POST )
	public ResultDatas<Page<Tide>> findTidePageList(@RequestBody TideVO vo, SimplePageInfo pageInfo) {

		ResultDatas<Page<Tide>> res = new ResultDatas<>();
		
		try {
			// 潮汐uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH200);
			}
			
			// 查询潮汐信息翻页列表
			Page<Tide> tideList= tideService.findTidePageList(BeanConvertUtils.beanToBean(vo, Tide.class), pageInfo);
			
			// 设定返回数据
			res.setDatas(tideList);
			
		} catch (BizException e) {
			log.error("findTidePageList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
			
		} catch (Exception e ) {
			log.error("findTidePageList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 查询潮汐信息翻页列表
	 * @param vo
	 * @return
	 * @exception
	 */
	@RightAccess(2052)
	@RolesAccess({Constants.ADMIN,Constants.ENTER_MASTER})
	@RequestMapping(value=URLMapping.GET_TIDE_PAGE_LIST)
	public void getTidePageList(CurrentUser user, ModelMap modelMap,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain, TideQuery query) {
		
		// 潮汐名称
		Map<String, Object> map = new HashMap<String, Object>();
		String stateSearch = query.getStateSearch();
		String countrySearch = query.getCountrySearch();
		String provinceSearch = query.getProvinceSearch();
		String portSearch = query.getPortSearch();
		String dateSearch = query.getDateSearch();
		
		map.put("portCode", portSearch);
		map.put("date", dateSearch);
		
		// 总记录数
		int total = tideService.countRecords(map);
		
		// 总记录数设定
		query.setTotalItem(Long.valueOf(total));
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		
		List<Map<String, Object>> tideList= tideService.getTidePageList(query);

		modelMap.put("user", user);
		modelMap.put("datas", tideList);
		modelMap.put("query", query);
		modelMap.put("stateSearch", stateSearch);
		modelMap.put("countrySearch", countrySearch);
		modelMap.put("provinceSearch", provinceSearch);
		modelMap.put("portSearch", portSearch);
		modelMap.put("dateSearch", dateSearch);
	}
	
	/**
	 * 根据港口、日期查询潮汐信息(前台接口)
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.ADMIN,Constants.INSPECTION,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.SALES_TRADER,Constants.INSPECTION,Constants.BUY_TRADER,
		Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.SHIP_BROKER,Constants.SHIP_OWNER,Constants.TRADE_EXECUTOR,Constants.ENTER_MASTER,Constants.CHARTERER})
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_TIDE_LIST_BY_PORT_DATE, method = RequestMethod.POST )
	public ResultDatas<List<Map<String, Object>>> findTideListByPortDate(@RequestBody TideVO vo) {

		ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
		
		try {
			// 港口code或港口名称校验
			if (StringUtils.isNullOrEmpty(vo.getPortCode()) && StringUtils.isNullOrEmpty(vo.getPortName())) {
				throw new TransportException(TransportException.TYPE.ITSH222);
			}
			
			// 日期
			if (null == vo.getDate()) {
				throw new TransportException(TransportException.TYPE.ITSH227);
			}
			
			// 查询潮汐信息列表
			List<Map<String, Object>> tideList= tideService.findTideListByPortDate(BeanConvertUtils.beanToBean(vo, Tide.class));
			
			// 设定返回数据
			res.setDatas(tideList);
			
		} catch (BizException e) {
			log.error("findTideList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
			
		} catch (Exception e ) {
			log.error("findTideList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}

}
