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
import com.sinochem.crude.trade.transport.domain.UnloadPort;
import com.sinochem.crude.trade.transport.model.UnloadPortVO;
import com.sinochem.crude.trade.transport.model.res.UnloadPortRes;
import com.sinochem.crude.trade.transport.service.UnloadPortService;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * @ClassName: UnloadPortController
 * @Description: 船舶卸港信息
 * @author gyy
 */
@Controller
public class UnloadPortController  {
	
	@Autowired
	private UnloadPortService unloadPortService;
	 
	Log log = LogFactory.getLog(UnloadPortController.class);
	
	/**
	 * 船舶卸港信息维护
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR,Constants.DEPA_PORT,Constants.ARRIV_PORT})
	@ResponseBody
	@RequestMapping(value=URLMapping.SAVE_UNLOAD_PORT, method = RequestMethod.POST  )
	public Result saveUnloadPort(@RequestBody UnloadPortVO vo,CurrentUser user) {
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
				throw new TransportException(TransportException.TYPE.ITSH085);
			}
			UnloadPort port = new UnloadPort();
			port.setShipPactUuid(vo.getShipPactUuid());
			List<UnloadPort> list = unloadPortService.queryByEntitys(port);
			if (list == null || list.isEmpty()){
				//新增
				unloadPortService.saveUnloadPort(vo,user);
			} else {
				//修改
				unloadPortService.updateUnloadPort(vo,user);
			}
			
		} catch (BizException e) {
			log.error("saveUnloadPort error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("saveUnloadPort error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 查询船舶卸港信息
	 * @param vo
	 * @param user
	 * @return
	 */
	@RolesAccess({Constants.ADMIN,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.SHIP_BROKER,Constants.SHIP_OWNER,Constants.DEPA_PORT,Constants.ARRIV_PORT})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_UNLOAD_PORT_DETAIL, method = RequestMethod.POST  )
	public ResultDatas<Object> findUnloadPortDeatil(@RequestBody UnloadPortVO vo,CurrentUser user) {
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
			UnloadPortRes  port= unloadPortService.findUnloadPortDeatil(vo.getShipPactUuid());
			res.setDatas(port);
			
		} catch (BizException e) {
			log.error("findUnloadPortDeatil error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e ) {
			log.error("findUnloadPortDeatil error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
}
