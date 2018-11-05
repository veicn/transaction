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
import com.sinochem.crude.trade.transport.domain.LoadPort;
import com.sinochem.crude.trade.transport.model.LoadPortVO;
import com.sinochem.crude.trade.transport.model.res.LoadPortRes;
import com.sinochem.crude.trade.transport.service.LoadPortService;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * @ClassName: LoadPortController
 * @Description: 船舶装港信息
 * @author gyy
 */
@Controller
public class LoadPortController  {
	@Autowired
	private LoadPortService loadPortService;
	 
	Log log = LogFactory.getLog(LoadPortController.class);
	
	/**
	 * 船舶装港信息维护
	 * 
	 * @param vo
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.DEPA_PORT,Constants.ARRIV_PORT})
	@ResponseBody
	@RequestMapping(value=URLMapping.SAVE_LOAD_PORT, method = RequestMethod.POST  )
	public Result saveLoadPort(@RequestBody LoadPortVO vo,CurrentUser user) {
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
			if (vo.getList() == null || vo.getList().isEmpty()){
				throw new TransportException(TransportException.TYPE.ITSH047);
			}
			
			LoadPort port = new LoadPort();
			port.setShipPactUuid(vo.getShipPactUuid());
			List<LoadPort> list = loadPortService.queryByEntitys(port);
			if ( list == null || list.isEmpty()){
				//新增
				loadPortService.saveLoadPort(vo,user);
			} else {
				//修改
				loadPortService.updateLoadPort(vo,user);
			}
			
		} catch (BizException e) {
			log.error("saveLoadPort error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("saveLoadPort error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	/**
	 * 查询船舶装港信息
	 * 
	 * @param vo
	 * @return
	 */
	@RolesAccess({Constants.ADMIN,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.SHIP_BROKER,Constants.SHIP_OWNER,Constants.DEPA_PORT,Constants.ARRIV_PORT})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_LOAD_PORT_DEATIL, method = RequestMethod.POST  )
	public ResultDatas<Object> findLoadPortDeatil(@RequestBody LoadPortVO vo,CurrentUser user) {
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
			if (StringUtils.isNullOrEmpty(vo.getShipPactUuid())){
				throw new TransportException(TransportException.TYPE.ITSH038);
			}
			LoadPortRes  port= loadPortService.findLoadPortDeatil(vo.getShipPactUuid());
			res.setDatas(port);
		} catch (BizException e) {
			log.error("findLoadPortDeatil error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("findLoadPortDeatil error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
}
