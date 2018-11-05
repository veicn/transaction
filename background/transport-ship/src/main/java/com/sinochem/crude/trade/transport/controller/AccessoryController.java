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
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.URLMapping;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.domain.Accessory;
import com.sinochem.crude.trade.transport.model.AccessoryVO;
import com.sinochem.crude.trade.transport.model.SysShipVO;
import com.sinochem.crude.trade.transport.service.AccessoryService;

/**
 * @ClassName: SysShipController
 * @Description: 船舶附件信息维护
 * @author wangn
 * @date 2017-11-11 16:22:30
 * @version V1.0
 */
@Controller
public class AccessoryController  {
	@Autowired
	private AccessoryService accessoryService;
	 
	Log log = LogFactory.getLog(AccessoryController.class);
	
	/**
	 * 新增船舶附件信息
	 * @param vo
	 * @return
	 * @exception
	 */
	@ResponseBody
	@RequestMapping(value=URLMapping.SAVE_ACCESSORY, method = RequestMethod.POST )
	public Result saveAccessory(@RequestBody AccessoryVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 船舶ID校验
			if (vo.getId() == null) {
				throw new TransportException(TransportException.TYPE.ITSH217);
			}
			
			// 新增附件信息
			accessoryService.saveAccessory(BeanConvertUtils.beanToBean(vo, Accessory.class), user);
			
			res.setMessage(Constants.message_001);
			
		} catch (BizException e) {
			log.error("saveAccessory error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("saveAccessory error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 删除船舶附件
	 * @param vo
	 * @return
	 * @exception
	 */
	@ResponseBody
	@RequestMapping(value=URLMapping.DEL_ACCESSORY, method = RequestMethod.POST)
	public Result delAccessory(@RequestBody SysShipVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 附件uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH218);
			}
			
			// 用户Id
			Long memberId = user.getMemberId();
			
			// 删除平台船舶信息
			accessoryService.delAccessory(BeanConvertUtils.beanToBean(vo, Accessory.class), memberId);
			res.setMessage(Constants.message_002);
			
		} catch (BizException e) {
			log.error("delAccessory error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
			
		} catch (Exception e) {
			log.error("delAccessory error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 查询附件信息列表
	 * @param vo
	 * @return
	 * @exception
	 */
	@ResponseBody
	@RequestMapping(value=URLMapping.FIND_ACCESSORY_LIST, method = RequestMethod.POST)
	public ResultDatas<List<Accessory>> findAccessoryList(@RequestBody AccessoryVO vo, CurrentUser user) {

		ResultDatas<List<Accessory>> res = new ResultDatas<>();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 查询附件信息列表
			List<Accessory> accessoryList = accessoryService.findAccessory(BeanConvertUtils.beanToBean(vo, Accessory.class));
			
			// 设定返回数据
			res.setDatas(accessoryList);
			
		} catch (BizException e) {
			log.error("findAccessoryList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
			
		} catch (Exception e) {
			log.error("findAccessoryList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
}
