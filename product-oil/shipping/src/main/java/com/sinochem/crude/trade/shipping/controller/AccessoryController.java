package com.sinochem.crude.trade.shipping.controller;

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
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.constant.UrlMapping;
import com.sinochem.crude.trade.shipping.domain.Accessory;
import com.sinochem.crude.trade.shipping.exceptions.TransportException;
import com.sinochem.crude.trade.shipping.model.vo.AccessoryVO;
import com.sinochem.crude.trade.shipping.model.vo.SysShipVO;
import com.sinochem.crude.trade.shipping.service.AccessoryService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.common.result.ResultDatas;


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
	 * 删除船舶附件
	 * @param vo
	 * @return
	 * @exception
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.DEL_ACCESSORY, method = RequestMethod.POST)
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
			AccessoryVO acVO = new AccessoryVO();
			
			acVO.setAccessoryId(vo.getFileInfoList().get(0).getAccessoryId());
			// 删除平台船舶信息
			accessoryService.delAccessory(acVO);
			res.setMessage("船舶附件信息删除成功");
			
		} catch (BizException e) {
			log.error("delAccessory error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
			
		} catch (Exception e) {
			log.error("delAccessory error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
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
	@RequestMapping(value=UrlMapping.FIND_ACCESSORY_LIST, method = RequestMethod.POST)
	public ResultDatas<List<Accessory>> findAccessoryList(@RequestBody AccessoryVO vo, CurrentUser user) {

		ResultDatas<List<Accessory>> res = new ResultDatas<>();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 查询附件信息列表
			List<Accessory> accessoryList = accessoryService.findAccessory(vo);
			
			// 设定返回数据
			res.setDatas(accessoryList);
			
		} catch (BizException e) {
			log.error("findAccessoryList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
			
		} catch (Exception e) {
			log.error("findAccessoryList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
}
