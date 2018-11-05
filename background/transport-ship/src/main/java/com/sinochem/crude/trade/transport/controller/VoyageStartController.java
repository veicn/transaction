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

import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.URLMapping;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.domain.VoyageStart;
import com.sinochem.crude.trade.transport.model.VoyageStartVO;
import com.sinochem.crude.trade.transport.model.res.VoyageStartRes;
import com.sinochem.crude.trade.transport.service.VoyageStartService;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * @ClassName: VoyageStartController
 * @Description: 船舶航次开始信息
 * @author gyy
 */
@Controller
public class VoyageStartController  {
	@Autowired
	private VoyageStartService voyageStartService;
	
	/*@Autowired
	private AgencyService agencyService;*/
	 
	Log log = LogFactory.getLog(VoyageStartController.class);
	
	/**
	 * 船航次开始信息维护
	 * 
	 * @param vo
	 * @return
	 */
	@RolesAccess({Constants.SHIP_OWNER,Constants.SHIP_BROKER,Constants.SHIP_EXECUTOR})
	@ResponseBody
	@RequestMapping(value=URLMapping.SAVE_VOYAGE_START, method = RequestMethod.POST  )
	public Result saveVoyageStart(@RequestBody VoyageStartVO vo,CurrentUser user) {
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
			//查询是否已经维护航次开始信息
			VoyageStart start =  new VoyageStart();
			start.setShipPactUuid(vo.getShipPactUuid());
			List<VoyageStart> list = voyageStartService.queryByEntitys(start);
			if (list == null || list.isEmpty()){
				//新增
				voyageStartService.saveVoyageStart(vo,user);
			} else {
				//更新
				voyageStartService.upateVoyageStart(vo,user);
			}
			
		} catch (BizException e) {
			log.error("saveVoyageStart error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("saveVoyageStart error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
	/**
	 * 查询船航次开始信息
	 * 
	 * @param vo
	 * @return
	 */
	@RolesAccess({Constants.ADMIN,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.SHIP_BROKER,Constants.SHIP_OWNER})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_VOYAGE_START_DETAIL, method = RequestMethod.POST  )
	public ResultDatas<Object> findVoyageStartDetail(@RequestBody VoyageStartVO vo,CurrentUser user) {
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
			VoyageStartRes voyageStart = voyageStartService.findVoyageStartDetail(vo.getShipPactUuid());
			res.setDatas(voyageStart);
		} catch (BizException e) {
			log.error("findVoyageStartDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("findVoyageStartDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	/**
	 * 查询船航次开始油种信息
	 * 
	 * @param vo
	 * @return
	 */
	@RolesAccess({Constants.ADMIN,Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.SHIP_BROKER,
		Constants.SHIP_OWNER,Constants.DEPA_PORT,Constants.ARRIV_PORT})
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_OIL_LIST, method = RequestMethod.POST  )
	public ResultDatas<Object> findOilList(@RequestBody VoyageStartVO vo) {
		ResultDatas<Object> res = new ResultDatas<>();
		try {
			//校验必填
			if (StringUtils.isNullOrEmpty(vo.getShipPactUuid())){
				throw new TransportException(TransportException.TYPE.ITSH038);
			}
			List<Map<String,Object>> voyageStart = voyageStartService.findOilList(vo.getShipPactUuid());
			res.setDatas(voyageStart);
		} catch (BizException e) {
			log.error("findOilList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
		} catch (Exception e ) {
			log.error("findOilList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		return res;
	}
	
}
