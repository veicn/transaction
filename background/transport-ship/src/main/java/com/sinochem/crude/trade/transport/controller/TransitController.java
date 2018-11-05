package com.sinochem.crude.trade.transport.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.sinochem.crude.trade.transport.domain.Transit;
import com.sinochem.crude.trade.transport.model.TransitVO;
import com.sinochem.crude.trade.transport.model.res.TransitRes;
import com.sinochem.crude.trade.transport.query.PactQuery;
import com.sinochem.crude.trade.transport.service.CommonService;
import com.sinochem.crude.trade.transport.service.TransitService;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;

/**
 * @ClassName: TransitController
 * @Description: 船舶在途信息
 * @author gyy
 */
@Controller
public class TransitController  {
	@Autowired
	private TransitService transitService;
	@Autowired
	private CommonService commonService;
	 
	Log log = LogFactory.getLog(TransitController.class);
	
	/**
	 * 船舶在途信息维护
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR})
	@ResponseBody
	@RequestMapping(value=URLMapping.SAVE_TRANSIT, method = RequestMethod.POST  )
	public Result saveTransit(@RequestBody TransitVO vo,CurrentUser user) {
		Result res = new Result();
		try {
			
			//验证登录信息
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//校验必填
			if (StringUtils.isNullOrEmpty(vo.getShipPactUuid())){
				throw new TransportException(TransportException.TYPE.ITSH038);
			} 
			if (StringUtils.isNullOrEmpty(vo.getUuid())){
				transitService.saveTransit(vo,user);
			} else {
				transitService.updateTransit(vo,user);
			}
			
		} catch (BizException e) {
			log.error("saveTransit error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("saveTransit error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	
	/**
	 * 查询船舶在途信息详情
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.ADMIN,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.SHIP_BROKER,Constants.SHIP_OWNER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_TRANSIT_DETAIL, method = RequestMethod.POST  )
	public ResultDatas<Object> findTransitDetail(@RequestBody TransitVO vo,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			
			//验证登录信息
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//校验必填
			if (StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH074);
			}
			TransitRes transit = transitService.findTransitDetail(vo.getUuid());
			res.setDatas(transit);
		} catch (BizException e) {
			log.error("findTransitDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("findTransitDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	/**
	 * 删除船舶在途信息详情
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.ADMIN,Constants.SHIP_EXECUTOR,Constants.SHIP_BROKER,Constants.SHIP_OWNER})
	@ResponseBody
	@RequestMapping(value=URLMapping.DELETE_TRANSIT_BY_UUID, method = RequestMethod.POST  )
	public Result deleteTransitByUuid(@RequestBody TransitVO vo,CurrentUser user) {
		Result res = new Result();
		try {
			
			//验证登录信息
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//校验必填
			if (StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH074);
			}
			transitService.deleteTransitByUuid(vo.getUuid(),user);
		} catch (BizException e) {
			log.error("deleteTransitByUuid error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("deleteTransitByUuid error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	/**
	 * 查询船舶在途港口
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.ADMIN,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.SHIP_BROKER,Constants.SHIP_OWNER})
	@ResponseBody
	@RequestMapping(value=URLMapping.UNLOAD_LIST, method = RequestMethod.POST  )
	public ResultDatas<Object> unloadList(@RequestBody TransitVO vo,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			
			//验证登录信息
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//校验必填
			if (StringUtils.isNullOrEmpty(vo.getShipPactUuid())){
				throw new TransportException(TransportException.TYPE.ITSH038);
			}
			List<Map<String,Object>> transit = transitService.unloadList(vo.getShipPactUuid());
			res.setDatas(transit);
		} catch (BizException e) {
			log.error("findTransitDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("findTransitDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	/**
	 * 查询船舶在途信息列表
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.ADMIN,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.SHIP_BROKER,Constants.SHIP_OWNER})
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.QUERY_TRANSIT_LIST, method = RequestMethod.POST  )
	public ResultDatas<Object> queryTransitList(@RequestBody TransitVO vo,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			
			//验证登录信息
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//校验必填
			if (StringUtils.isNullOrEmpty(vo.getShipPactUuid())){
				throw new TransportException(TransportException.TYPE.ITSH038);
			}
			Page<Map<String,Object>> page = transitService.queryTransitList(vo.getShipPactUuid(),vo.getPageInfo());
			
			res.setDatas(page);
			res.setPageCount(page.getPages());
			res.setPageNum(page.getPageNum());
			res.setPageSize(page.getPageSize());
		} catch (BizException e) {
			log.error("queryTransitList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("queryTransitList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	/**
	 * 查询船舶在途信息列表
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.ADMIN,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.SHIP_BROKER,Constants.SHIP_OWNER})
	@RequestMapping(value=URLMapping.SHIP_TRANSIT_LIST_HTM )
	public void queryTransitListHtm(CurrentUser user,ModelMap modelMap,  PactQuery query) {
			
			//验证登录信息
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//校验必填
			if (StringUtils.isNullOrEmpty(query.getShipPactUuid())){
				throw new TransportException(TransportException.TYPE.ITSH038);
			}
			
			query.setPageSize(10);
			SimplePageInfo pageInfo =query.getPageInfo();
			Page<Map<String,Object>> pages = transitService.queryTransitList(query.getShipPactUuid(),pageInfo);
			
			List<Transit> list = BeanConvertUtils.mapToBeanInList(pages, Transit.class);
			List<Transit> lists = new ArrayList<>(); 
			for (Transit transit : list) {
				String unload ="";
				String eta = "";
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date dateNow = transit.getDateNow();
					transit.setLangVer(format.format(dateNow));
					transit.setVersion(transit.getTimeNow());
					String unloadEta = transit.getUnloadEtaCode();
					if (!StringUtils.isNullOrEmpty(unloadEta)){
						String[] split = unloadEta.split(";");
						for (String str : split) {
							String[] split2 = str.split("/");
							if (split2.length>=2){
								String name = commonService.findNameByCodeAndLang("2", split2[0]);
								unload=unload+name+"/";
								String[] split3 = split2[1].split("\\ ");
								eta=eta+split3[0]+"/";
							}
						}
						if (!StringUtils.isNullOrEmpty(unload)){
							unload=unload.substring(0,unload.length()-1);
						}
						if (!StringUtils.isNullOrEmpty(unload)){
							eta=eta.substring(0,eta.length()-1);
						}
					}
					transit.setUnloadEta(unload);
					transit.setExt1(eta);
					lists.add(transit);
			}
			query.setTotalItem(pages.getTotal());
			modelMap.put("user", user);
			modelMap.put("datas", lists);
			modelMap.put("query", query);
			
			
	}
	/**
	 * 查询船舶在途信息列表
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.ADMIN,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.SHIP_BROKER,Constants.SHIP_OWNER})
	@RequestMapping(value=URLMapping.SHIP_TRANSIT_LIST_HTM_NEW )
	public void queryTransitListHtmNew(CurrentUser user,ModelMap modelMap,  PactQuery query) {
		
		//验证登录信息
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		//校验必填
		if (StringUtils.isNullOrEmpty(query.getShipPactUuid())){
			throw new TransportException(TransportException.TYPE.ITSH038);
		}
		
		query.setPageSize(10);
		SimplePageInfo pageInfo =query.getPageInfo();
		Page<Map<String,Object>> pages = transitService.queryTransitList(query.getShipPactUuid(),pageInfo);
		
		List<Transit> list = BeanConvertUtils.mapToBeanInList(pages, Transit.class);
		List<Transit> lists = new ArrayList<>(); 
		for (Transit transit : list) {
			String unload ="";
			String eta = "";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dateNow = transit.getDateNow();
			transit.setLangVer(format.format(dateNow));
			transit.setVersion(transit.getTimeNow());
			String unloadEta = transit.getUnloadEtaCode();
			if (!StringUtils.isNullOrEmpty(unloadEta)){
				String[] split = unloadEta.split(";");
				for (String str : split) {
					String[] split2 = str.split("/");
					if (split2.length>=2){
						String name = commonService.findNameByCodeAndLang("2", split2[0]);
						unload=unload+name+"/";
						String[] split3 = split2[1].split("\\ ");
						eta=eta+split3[0]+"/";
					}
				}
				if (!StringUtils.isNullOrEmpty(unload)){
					unload=unload.substring(0,unload.length()-1);
				}
				if (!StringUtils.isNullOrEmpty(unload)){
					eta=eta.substring(0,eta.length()-1);
				}
			}
			transit.setUnloadEta(unload);
			transit.setExt1(eta);
			lists.add(transit);
		}
		query.setTotalItem(pages.getTotal());
		modelMap.put("user", user);
		modelMap.put("datas", lists);
		modelMap.put("query", query);
		
		
	}
	
	
	/**
	 * 查询物流跟踪查看更多在途信息列表
	 * @param vo
	 * @param user
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.QUERY_MORE_TRANSIT_LIST, method = RequestMethod.POST  )
	public ResultDatas<Object> queryMoreTransitList(@RequestBody TransitVO vo,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			
			//验证登录信息
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//校验必填
			if (StringUtils.isNullOrEmpty(vo.getShipPactUuid())){
				throw new TransportException(TransportException.TYPE.ITSH038);
			}
			Page<Map<String,Object>> page = transitService.queryMoreTransitList(vo.getShipPactUuid(),vo.getPageInfo());
			
			//List<Transit> list = BeanConvertUtils.mapToBeanInList(page, Transit.class);
			res.setDatas(page);
			res.setPageCount(page.getPages());
			res.setPageNum(page.getPageNum());
			res.setPageSize(page.getPageSize());
			res.setTotal(page.getTotal());
		} catch (BizException e) {
			log.error("queryMoreTransitList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("queryMoreTransitList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	
}
