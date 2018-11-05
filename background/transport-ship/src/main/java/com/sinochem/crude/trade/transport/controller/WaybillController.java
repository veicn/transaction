package com.sinochem.crude.trade.transport.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.URLMapping;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.domain.Waybill;
import com.sinochem.crude.trade.transport.model.WaybillVO;
import com.sinochem.crude.trade.transport.service.WaybillService;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * @ClassName: WaybillController
 * @Description: 运单信息
 * @author gyy
 */
@Controller
public class WaybillController  {
	@Autowired
	private WaybillService waybillService;
	 
	Log log = LogFactory.getLog(WaybillController.class);
	
	/**
	 * 生成运单
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.SAVE_WAYBILL, method = RequestMethod.POST  )
	public Result saveWaybill(@RequestBody WaybillVO vo,CurrentUser user) {
		Result res = new Result();
		try {
			
			//验证登录信息
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			if (StringUtils.isNullOrEmpty(vo.getShipPactUuid())){
				throw new TransportException(TransportException.TYPE.ITSH038);
			} 
			if (StringUtils.isNullOrEmpty(vo.getAgreementUuid())){
				throw new TransportException(TransportException.TYPE.ITSH026);
			}
			
			waybillService.saveWaybill(vo,user);
			
		} catch (BizException e) {
			log.error("saveWaybill error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("saveWaybill error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	
	/**
	 * 生成运单多个
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.SAVE_WAYBILL_MANY, method = RequestMethod.POST  )
	public Result saveWaybillMany(@RequestBody WaybillVO vo,CurrentUser user) {
		Result res = new Result();
		try {
			
			//验证登录信息
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			if (StringUtils.isNullOrEmpty(vo.getShipPactUuid())){
				throw new TransportException(TransportException.TYPE.ITSH038);
			} 
			if (vo.getAgreementUuids()== null || vo.getAgreementUuids().isEmpty()){
				throw new TransportException(TransportException.TYPE.ITSH026);
			}
			
			waybillService.saveWaybillMany(vo,user);
			
		} catch (BizException e) {
			log.error("saveWaybill error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("saveWaybill error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/**
	 * 查询运单列表
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,
		Constants.ARRIV_PORT,Constants.DEPA_PORT})
	@ResponseBody
	@RequestMapping(value=URLMapping.QUERY_WAYBILL_LIST, method = RequestMethod.POST  )
	public ResultDatas<Object> queryWaybillList(@RequestBody WaybillVO vo,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			
			//验证登录信息
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			if (StringUtils.isNullOrEmpty(vo.getShipPactUuid())){
				throw new TransportException(TransportException.TYPE.ITSH038);
			}
			Page<Map<String,Object>> page = waybillService.queryWaybillList(vo,vo.getPageInfo());
			List<Waybill> list = BeanConvertUtils.mapToBeanInList(page, Waybill.class);
			res.setDatas(list);
			res.setPageNum(page.getPageNum());
			res.setPageSize(page.getPageSize());
			res.setPageCount(page.getPages());
		} catch (BizException e) {
			log.error("queryWaybillList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("queryWaybillList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	/**
	 * 运单取消匹配
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER})
	@ResponseBody
	@RequestMapping(value=URLMapping.CANCEL_WAYBILL, method = RequestMethod.POST  )
	public Result cancelWaybill(@RequestBody WaybillVO vo,CurrentUser user) {
		Result res = new Result();
		try {
			
			//验证登录信息
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			if (StringUtils.isNullOrEmpty(vo.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH006);
			}
			waybillService.cancelWaybill(vo.getUuid(),user);
		} catch (BizException e) {
			log.error("cancelWaybill error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("cancelWaybill error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
}
