package com.sinochem.crude.trade.transport.controller;

import java.util.HashMap;
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
import com.sinochem.crude.trade.transport.domain.Intention;
import com.sinochem.crude.trade.transport.domain.Pallet;
import com.sinochem.crude.trade.transport.model.IntentionVO;
import com.sinochem.crude.trade.transport.query.IntentionQuery;
import com.sinochem.crude.trade.transport.service.IntentionService;
import com.sinochem.crude.trade.transport.service.PalletService;
import com.sinochem.it.b2b.member.access.RolesAccess;


@Controller
public class IntentionController  {
	@Autowired
	private IntentionService intentionService;
	@Autowired
	private PalletService palletService;
	 
	
	Log log = LogFactory.getLog(IntentionController.class);
	
	
	/**
	 * 新增询盘信息
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.SAVE_INTENTION, method = RequestMethod.POST )
	public Result saveIntention(@RequestBody IntentionVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			//验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//船盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getShipPlateUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH012);
			}
			//货盘uuid校验
			if(StringUtils.isNullOrEmpty(vo.getPalletUuid())){
				throw new TransportException(TransportException.TYPE.ITSH016);
			}
			
			//新增询盘信息
			intentionService.saveIntention(BeanConvertUtils.beanToBean(vo, Intention.class),user);
			res.setMessage(Constants.message_011);
			
		} catch (BizException e) {
			log.error("saveIntention error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("saveIntention error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
	
	
	/**
	 * 重复询盘校验
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.CHECK_AGAIN_INTENTION, method = RequestMethod.POST )
	public Result checkAgainIntention(@RequestBody IntentionVO vo, CurrentUser user) {
		
		ResultDatas<List<Map<String, Object>>> result=new ResultDatas<>();
		
		try {
			//验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//船盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getShipPlateUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH012);
			}
			//货盘uuid校验
			if(StringUtils.isNullOrEmpty(vo.getPalletUuid())){
				throw new TransportException(TransportException.TYPE.ITSH016);
			}
			
			//重复询盘校验
			Map<String, Object> map=new HashMap<>();
			map.put("palletUuid", vo.getPalletUuid());
			map.put("shipPlateUuid", vo.getShipPlateUuid());
			map.put("status", Constants.INTENTION_STATUS_4);	//状态不是“已关闭”
			List<Map<String, Object>> list = intentionService.findPageListStatusEqualsNo(map);

			result.setDatas(list);
			
		} catch (BizException e) {
			log.error("checkAgainIntention error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(e.getCode());
			result.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("checkAgainIntention error", e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setCode(Constants.EXCEPTION_CODE);
			result.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return result;
	}
	
	/**
	 * 查看询盘详细信息
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_INTENTION_DETAIL, method = RequestMethod.POST )
	public ResultDatas<Intention> findIntentionDetail(@RequestBody IntentionVO vo,CurrentUser user) {
		
		ResultDatas<Intention> res = new ResultDatas<>();
		try {
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//询盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH404);
			}
			Intention intention=intentionService.findIntentionDetail(BeanConvertUtils.beanToBean(vo, Intention.class),user);
			
			res.setDatas(intention);
		} catch (BizException e) {
			log.error("findIntentionDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("findIntentionDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	
	/**
	 * 修改询盘状态，终止操作（1已询盘2已还盘3已确认租船5已关闭）
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.UPDATE_INTENTION_STATUS, method = RequestMethod.POST )
	public Result updateIntentionStatus(@RequestBody IntentionVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			//验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//询盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH404);
			}
			
			//修改询盘状态
			intentionService.updateIntentionStatus(BeanConvertUtils.beanToBean(vo, Intention.class),user);
			res.setMessage(Constants.message_012);
		
		} catch (BizException e) {
			log.error("updateIntentionStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("updateIntentionStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
	
	/**
	 * 修改询盘信息（提交还盘）
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.UPDATE_INTENTION, method = RequestMethod.POST )
	public Result updateIntention(@RequestBody IntentionVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			//验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//询盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH404);
			}
			
			//修改询盘信息
			intentionService.updateIntention(BeanConvertUtils.beanToBean(vo, Intention.class),user);
			
			res.setMessage(Constants.message_013);
		} catch (BizException e) {
			log.error("updateIntention error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e) {
			log.error("updateIntention error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}

	
	
	/**
	 * 查询询盘信息翻页列表（船东/船东经纪人）
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER})
	@RequestMapping(value=URLMapping.SHIPOWNER_INTENTION_LIST)
	public void shipOwnerIntentionList(CurrentUser user, ModelMap modelMap, IntentionQuery query) {
		
		
		//验证登录信息
		if (user == null) {
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		
		//每页10条
		query.setPageSize(10);
		
		Page<Map<String,Object>> IntentionList= intentionService.getIntentionPageList(query, user);
		
		//总记录数设定
		query.setTotalItem(IntentionList.getTotal());

		modelMap.put("user", user);
		modelMap.put("datas", IntentionList);
		modelMap.put("query", query);
		modelMap.put("intentionCode", query.getIntentionCode());
	}
	
	
	/**
	 * 查询询盘信息翻页列表（货主）
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@RequestMapping(value=URLMapping.CARGOOWNER_INTENTION_LIST)
	public void cargoOwnerIntentionList(CurrentUser user, ModelMap modelMap, IntentionQuery query) {
		
		//验证登录信息
		if (user == null) {
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		
		//每页10条
		query.setPageSize(10);
		
		Page<Map<String, Object>> IntentionList= intentionService.findIntentionPageList(query, user);
		
		//总记录数设定
		query.setTotalItem(IntentionList.getTotal());

		modelMap.put("user", user);
		modelMap.put("datas", IntentionList);
		modelMap.put("query", query);
		modelMap.put("intentionCode", query.getIntentionCode());
	}
	
	
	/**
	 * 查询询盘信息翻页列表（代理）
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_TRADER})
	@RequestMapping(value=URLMapping.SHIPAGENT_INTENTION_LIST)
	public void shipAgentIntentionList(CurrentUser user, ModelMap modelMap, IntentionQuery query) {
		
		//验证登录信息
		if (user == null) {
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		
		//每页10条
		query.setPageSize(10);
		
		Page<Map<String, Object>> IntentionList= intentionService.findIntentionPageList(query, user);
		
		//总记录数设定
		query.setTotalItem(IntentionList.getTotal());
		
		modelMap.put("user", user);
		modelMap.put("datas", IntentionList);
		modelMap.put("query", query);
		modelMap.put("intentionCode", query.getIntentionCode());
	}
	
	
	/**
	 * 询盘列表（船东，船盘列表-询盘信息）
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER})
	@RequestMapping(value=URLMapping.FIND_INTENTION_LIST)
	public void findIntentionList(CurrentUser user, ModelMap modelMap, IntentionQuery query) {
		
		//验证登录信息
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		//船盘uuid校验
		if (StringUtils.isNullOrEmpty(query.getShipPlateUuid())) {
			throw new TransportException(TransportException.TYPE.ITSH012);
		}
		
		//设置每页10条数据
		query.setPageSize(10);
		
		SimplePageInfo pageInfo = query.getPageInfo();
		
		Page<Map<String,Object>> page = intentionService.findIntentionList(user,query,pageInfo);
		
		query.setTotalItem(page.getTotal());
		
		//标题取船名
		if(page!=null && page.size()>0){
			Map<String, Object> map = page.get(0);
			String shipName=(String)map.get("shipName");
			modelMap.put("shipName",shipName+" - ");
		}
		
		//设定返回数据
		modelMap.put("user", user);
		modelMap.put("datas", page);
		modelMap.put("query", query);
	}
	
	
	/**
	 * 询盘列表（二船东，船盘列表-洽谈列表）
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_TRADER})
	@RequestMapping(value=URLMapping.TALK_INTENTION_LIST)
	public void talkIntentionList(CurrentUser user, ModelMap modelMap, IntentionQuery query) {
		
		//验证登录信息
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		//船盘uuid校验
		if (StringUtils.isNullOrEmpty(query.getShipPlateUuid())) {
			throw new TransportException(TransportException.TYPE.ITSH012);
		}
		
		//设置每页10条数据
		query.setPageSize(10);
		
		SimplePageInfo pageInfo = query.getPageInfo();
		
		Page<Map<String,Object>> page = intentionService.findIntentionList(user,query,pageInfo);
		
		query.setTotalItem(page.getTotal());
		
		//设定返回数据
		modelMap.put("user", user);
		modelMap.put("datas", page);
		modelMap.put("query", query);
	}
	
	
	/**
	 * 询盘列表（货主，租船需求管理-询盘信息）
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@RequestMapping(value=URLMapping.FIND_INTENTION_LISTS)
	public void findIntentionLists(CurrentUser user, ModelMap modelMap, IntentionQuery query) {
		
		//验证登录信息
		if (user == null){
			throw new TransportException(TransportException.TYPE.ITSH001);
		}
		if (user.getEpMemberId() == null){
			throw new TransportException(TransportException.TYPE.ITSH535);
		}
		//货盘uuid校验
		if (StringUtils.isNullOrEmpty(query.getPalletUuid())) {
			throw new TransportException(TransportException.TYPE.ITSH016);
		}
		
		//设置每页10条数据
		query.setPageSize(10);
		
		SimplePageInfo pageInfo = query.getPageInfo();
		
		//查询询盘信息
		Page<Map<String,Object>> page = intentionService.findIntentionLists(user,query,pageInfo);
		//查询货盘租船编号
		Pallet pallet = palletService.findByUuid(query.getPalletUuid());
		
		query.setTotalItem(page.getTotal());
		
		//设定返回数据
		modelMap.put("user", user);
		modelMap.put("datas", page);
		modelMap.put("query", query);
		modelMap.put("palletCode", pallet.getPalletCode());
	}
	
	
	/**
	 * 询盘详细信息，租船确认单
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_TRADER,
		Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_CHARTER_SHIP_CONFIRM, method = RequestMethod.POST )
	public ResultDatas<Map<String, List<Map<String, Object>>>> findShipConfirm(@RequestBody IntentionVO vo,CurrentUser user) {
		
		ResultDatas<Map<String, List<Map<String, Object>>>> res = new ResultDatas<>();
		try {
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//船盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getShipPlateUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH012);
			}
			
			//根据船盘uuid，查询询盘、报盘
			Map<String, List<Map<String, Object>>> list=intentionService.findCharterShipConfirm(BeanConvertUtils.beanToBean(vo, Intention.class),user);
			res.setDatas(list);
			
		} catch (BizException e) {
			log.error("findShipConfirm error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("findShipConfirm error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
	
	
	/**
	 * 修改询盘状态（确认成交）
	 * @param vo
	 * @return
	 * @exception
	 */
	@RolesAccess({Constants.SALES_TRADER,Constants.BUY_TRADER,Constants.TRADE_EXECUTOR,Constants.CHARTERER})
	@ResponseBody
	@RequestMapping(value=URLMapping.UPDATE_CONFIRM_TRANSACTION, method = RequestMethod.POST )
	public Result updateConfirmTransaction(@RequestBody IntentionVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			//验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//询盘uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH404);
			}
			//船盘uuid校验
			if(StringUtils.isNullOrEmpty(vo.getShipPlateUuid())){
				throw new TransportException(TransportException.TYPE.ITSH012);
			}
			//货盘uuid校验
			if(StringUtils.isNullOrEmpty(vo.getPalletUuid())){
				throw new TransportException(TransportException.TYPE.ITSH016);
			}
			
			intentionService.updateConfirmTransaction(BeanConvertUtils.beanToBean(vo, Intention.class),user);
			res.setMessage(Constants.message_014);
		
		} catch (BizException e) {
			log.error("updateIntentionStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		
		} catch (Exception e) {
			log.error("updateIntentionStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	
}
