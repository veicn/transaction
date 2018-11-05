package com.sinochem.crude.trade.transport.controller;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.HttpUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transport.commons.constants.Constants;
import com.sinochem.crude.trade.transport.commons.exceptions.TransportException;
import com.sinochem.crude.trade.transport.domain.ProductoilShip;
import com.sinochem.crude.trade.transport.model.AccessoryVO;
import com.sinochem.crude.trade.transport.model.ProductoilShipVO;
import com.sinochem.crude.trade.transport.remote.SysShipVO;
import com.sinochem.crude.trade.transport.service.MessagePushService;
import com.sinochem.crude.trade.transport.service.ProductoilShipService;
import com.sinochem.crude.trade.transport.service.SysShipService;
import com.sinochem.it.b2b.member.access.WithoutAccess;

/**
 * @ClassName: SysShipController
 * @Description: 船舶附件信息维护
 * @author wangn
 * @date 2017-11-11 16:22:30
 * @version V1.0
 */
@Controller
public class TestController  {
	/*@Autowired
	private ShipmentService shipmentService;
	
	@Autowired
	private DisburdenService disburdenService;
	 
	@Autowired
	private PalletService palletService;*/
	
	@Autowired
	private ProductoilShipService productoilShipService;
	
	@Autowired
	private MessagePushService messagePushService;
	@Autowired
	private SysShipService sysShipService;
	
	Log log = LogFactory.getLog(TestController.class);
	
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/test.json", method = RequestMethod.POST )
	public ResultDatas<Object> saveAccessory(@RequestBody AccessoryVO vo, CurrentUser user) {

		ResultDatas<Object> res = new ResultDatas<>();
		SysShipVO ship = new SysShipVO();
		try {
			ship.setName("Abtao43");
			ship.setImo("925481243");
			ship.setMmsi("72501992033");
			ship.setUseType("Oil Products Tanker");
			ship.setType("MR");
			ship.setCompleteDate("2004");
			ship.setHull("Double Hull");
			ship.setLoadQuantity(new BigDecimal(46683));
			ship.setDraft(new BigDecimal(12));
			ship.setLength(new BigDecimal(182));
			ship.setWide(new BigDecimal(32));
			ship.setCapacity(new BigDecimal(52943));
			
			//messagePushService.testPush();
			/*List<Map<String,Object>> list = productoilShipService.findSysShipNameList();
			List<SysShipNameAndId> list2 = BeanConvertUtils.mapToBeanInList(list, SysShipNameAndId.class);*/
			 /*ProductoilShip sysShip = productoilShipService.findByPrimaryKey(1L);
				if (sysShip != null){
					BeanUtils.copyProperties(sysShip, ship);
				}
			*/
			
			productoilShipService.insertProductoilShip(ship);
			res.setDatas(ship);
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
	 * 批量更新数据库mmsi码
	 * @param vo
	 * @return
	 * @exception
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value="/testProductoilmmsi.json", method = RequestMethod.POST )
	public ResultDatas<Map<String, Object>> test(@RequestBody ProductoilShipVO vo, CurrentUser user) {
		
		ResultDatas<Map<String, Object>> res = new ResultDatas<>();
		
		try {
			vo.setAliveFlag("1");
			List<ProductoilShip> list = productoilShipService.queryByEntitys(vo);
			Integer all = list.size();
			Integer succ = 0;
			Integer fal = 0;
			for (ProductoilShip sysShip : list) {
				try {
					// imo
					String imo = sysShip.getImo();
					
					// 船舶imo校验
					if (StringUtils.isNullOrEmpty(imo)) {
						throw new TransportException(TransportException.TYPE.ITSH202);
					}
					
					// 当前年月日
					Date currentTime = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
					String dateString = formatter.format(currentTime);
					
					// md5(09fd2450680248cd933ac162cbfbddf9+当前年月日)得到key
					String srcKey= "09fd2450680248cd933ac162cbfbddf9" + dateString;
					
					MessageDigest md5 = MessageDigest.getInstance("MD5");  
					md5.update((srcKey).getBytes("UTF-8"));  
					byte b[] = md5.digest();  
					
					int i;  
					StringBuffer buf = new StringBuffer("");  
					// md5小写32位
					for (int offset=0; offset<b.length; offset++) {
						i = b[offset];  
						if (i<0) {
							i+=256;  
						}
						if (i<16) {
							buf.append("0");  
						}
						buf.append(Integer.toHexString(i));  
					}
					
					String key = buf.toString();
					String url = "http://shipapi.chinaports.com/shipDataApiForShagang/getEsSearch?str=" + imo +"&key=" + key;
					
					// 调用接口
					String mmsiJson = HttpUtils.sendGet(url, "");
					
					if (!StringUtil.isBlank(mmsiJson)) {
						@SuppressWarnings("unchecked")
						Map<String, Object> mmsiObj = JSONObject.parseObject(mmsiJson, Map.class);
						Object object = mmsiObj.get("data");
						if (object != null){
							@SuppressWarnings("unchecked")
							List<Map<String,Object>> str = (List<Map<String,Object>>)object;
							Map<String, Object> map = str.get(0);
							if (map.get("mmsi") != null){
								
								String str2 = (String)map.get("mmsi");
								sysShip.setUpdateDate(DateTimeUtils.currentDate());
								sysShip.setMmsi(str2);
								productoilShipService.updateRecord(sysShip);
								log.info("#######imo=="+imo+",mmsi=="+str2);
								succ+=1;
							}
						}
					}
				} catch (Exception e) {
					fal+=1;
					e.printStackTrace();
				} 
				
			}
			log.info("共"+all+",成功"+succ+",失败"+fal);
			
		} catch (BizException e) {
			log.error("findSysShipStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getCode());
			
		} catch (Exception e) {
			log.error("findSysShipStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_CODE);
		}
		
		return res;
	}
	
}
