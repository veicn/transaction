package com.sinochem.crude.trade.transport.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.URLMapping;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.domain.Shipment;
import com.sinochem.crude.trade.transport.model.ShipmentVO;
import com.sinochem.crude.trade.transport.model.res.ShipmentDetail;
import com.sinochem.crude.trade.transport.service.ShipmentService;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * @ClassName: ShipmentController
 * @Description: 货物装港信息
 * @author gyy
 */
@Controller
public class ShipmentController  {
	@Autowired
	private ShipmentService shipmentService;
	 
	Log log = LogFactory.getLog(ShipmentController.class);
	
	/**
	 * 货物装港信息维护
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR})
	@ResponseBody
	@RequestMapping(value=URLMapping.SAVE_SHIPMENT, method = RequestMethod.POST  )
	public Result saveShipment(@RequestBody ShipmentVO vo,CurrentUser user) {
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
			if (StringUtils.isNullOrEmpty(vo.getWaybillUuid())){
				throw new TransportException(TransportException.TYPE.ITSH006);
			}
			if (vo.getList() == null){
				throw new TransportException(TransportException.TYPE.ITSH047);
			}
			Shipment shipment = new Shipment();
			shipment.setWaybillUuid(vo.getWaybillUuid());
			List<Shipment> list = shipmentService.queryByEntitys(shipment);
			if (list != null && !list.isEmpty()){
				//修改
				shipmentService.updateShipment(vo,user);
			} else {
				//新增
				shipmentService.saveShipment(vo,user);
			}
		} catch (BizException e) {
			log.error("saveShipment error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("saveShipment error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}

	/**
	 * 查询货物装港信息
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.ADMIN,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.SHIP_OWNER,Constants.SHIP_BROKER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_SHIPMENT_DETAIL, method = RequestMethod.POST  )
	public ResultDatas<Object> findShipmentDeatil(@RequestBody ShipmentVO vo,CurrentUser user) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			//验证登录信息
			if (user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			//运单uuid校验
			if (StringUtils.isNullOrEmpty(vo.getWaybillUuid())){
				throw new TransportException(TransportException.TYPE.ITSH006);
			}
			List<ShipmentDetail>  port= shipmentService.findShipmentDeatil(vo);
			res.setDatas(port);
		} catch (BizException e) {
			log.error("findShipmentDeatil error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("findShipmentDeatil error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
}
