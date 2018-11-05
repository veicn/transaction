package com.sinochem.crude.trade.info.service.impl;

import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eyeieye.melody.util.DateUtil;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.domain.ExternalInteractive;
import com.sinochem.crude.trade.info.model.ExternalIn;
import com.sinochem.crude.trade.info.model.ExternalOut;
import com.sinochem.crude.trade.info.remote.InfoInterfaceVo;
import com.sinochem.crude.trade.info.remote.InfoService;
import com.sinochem.crude.trade.info.service.AgioInterfaceService;
import com.sinochem.crude.trade.info.service.BasePriceInterfaceService;
import com.sinochem.crude.trade.info.service.ExternalInteractiveService;
import com.sinochem.crude.trade.info.service.ExternalService;
import com.sinochem.crude.trade.info.service.FuturesPriceInterfaceService;
import com.sinochem.crude.trade.info.service.InfoInterfaceService;
import com.sinochem.crude.trade.info.service.ShipPointInterfaceService;

@Service
public class ExternalServiceImpl implements ExternalService{
	
	private static final Log log = LogFactory.getLog(ExternalServiceImpl.class);
	
	private static final String SYS_CODE = "crudeoil.sinochemoil.com";
	
	private static final String SAVE_INFO = "saveInfor";
	
	private static final String SAVE_AGIO = "saveAgio";
	
	private static final String SAVE_BASE_PRICE = "saveBasePrice";
	
	private static final String SAVE_FUTURES_PRICE = "saveFuturesPrice";
	
	private static final String SAVE_SHIP_POINT = "saveShipPoint";	
	
	
	@Autowired
	private ExternalInteractiveService externalInteractiveService;
	
	/*@Autowired
	private FractionContentInterfaceService fractionContentInterfaceService;*/
	
	/*@Autowired
	private QualityInterfaceService qualityInterfaceService;*/
	
	@Autowired
	private AgioInterfaceService agioInterfaceService;
	
	@Autowired
	private BasePriceInterfaceService basePriceInterfaceService;
	
	/*@Autowired
	private DistillateFactorInterfaceService distillateFactorInterfaceService;*/

	/*@Autowired
	private FractionPriceInterfaceService fractionPriceInterfaceService;*/
	
	@Autowired
	private FuturesPriceInterfaceService futuresPriceInterfaceService;
	
	@Autowired
	private InfoInterfaceService infoInterfaceService;
		
	@Autowired
	private ShipPointInterfaceService shipPointInterfaceService;
	
	@Autowired
	private InfoService infoService;
		
	
	
	public ExternalOut process(ExternalIn in) throws Exception{
		ExternalOut out = new ExternalOut();
		out.setSysCode(in.getSysCode());
		//插入交互记录
		ExternalInteractive ei = new ExternalInteractive();
		ei.setSysCode(in.getSysCode());
		ei.setMethod(in.getMethod());
		ei.setFormat(in.getFormat());
		ei.setCharset(in.getCharset());
		ei.setTimeStamp(DateUtil.strToDate(in.getTimestamp(), "yyyy-MM-dd HH:mm:ss"));
		String bizContent = new ObjectMapper().writeValueAsString(in.getBizContent());
		if(bizContent.length()>2000)
			bizContent = bizContent.substring(2000);
		ei.setBizContent(bizContent);
		ei.setReceiveDate(new Date());
		ei.setAliveFlag(Constants.ALIVE_FLAG_1);
		externalInteractiveService.insertRecord(ei);
		
		out.setCode(Constants.CODE_10);
		out.setMsg(Constants.MSG_10);			
		
		try {
			
			//业务处理
			if(!SYS_CODE.equals(in.getSysCode())){
				out.setSubCode(Constants.SUB_CODE_20);
				out.setSubMsg(Constants.SUB_MSG_20 + ",sysCode不正确");
				return out;
			}
			
			switch(ei.getMethod()){
			case SAVE_INFO:
				//infoInterfaceService.processInfo(ei);				
				InfoInterfaceVo vo = new InfoInterfaceVo();
				BeanUtils.copyProperties(ei, vo);
				//infoService.saveInfoInterface(vo);
				break;
						
			/*case SAVE_CRUDE_QUALITY:
				qualityInterfaceService.processQuality(ei);
				break;
			
			case SAVE_FRACTION_CONTENT:
				fractionContentInterfaceService.processFractionContent(ei);
				break;
				
			case SAVE_DISTILLATE_FACTOR:
				distillateFactorInterfaceService.processDistillateFactor(ei);
				break;
				
			case SAVE_FRACTION_PRICE:
				fractionPriceInterfaceService.processFractionPrice(ei);
				break;*/
				
			case SAVE_AGIO:
				//agioInterfaceService.processAigo(ei);
				break;
				
			case SAVE_BASE_PRICE:
				//basePriceInterfaceService.processBasePrice(ei);
				break;
				
			case SAVE_FUTURES_PRICE:
				//futuresPriceInterfaceService.processFuturesPrice(ei);
				break;
				
			case SAVE_SHIP_POINT:
				//shipPointInterfaceService.processShipPoint(ei);
				break;
				
			default: 
				throw new Exception("method不正确");			
			}
		} catch (Exception e) {
			//更新交互记录-业务处理失败
			ei.setReturnMsg(Constants.MSG_10);
			ei.setReturnSubMsg(Constants.SUB_MSG_20);
			ei.setReturnDate(new Date());
			externalInteractiveService.updateRecordById(ei);
			
			out.setSubCode(Constants.SUB_CODE_20);
			out.setSubMsg(Constants.SUB_MSG_20 + "," + e.getMessage());
			out.setBizContent(new HashMap<String, Object>());
			log.error("", e);
			
			return out;
		}
		
		//更新交互记录-业务处理成功
		ei.setReturnMsg(Constants.MSG_10);
		ei.setReturnSubMsg(Constants.SUB_MSG_10);
		ei.setReturnDate(new Date());
		externalInteractiveService.updateRecordById(ei);
		
		out.setSubCode(Constants.SUB_CODE_10);
		out.setSubMsg(Constants.SUB_MSG_10);
		out.setBizContent(new HashMap<String, Object>());
		return out;
	}



	/**
	 * 
	 * @throws Exception
	 */
	public void useAll() throws Exception{
		
		agioInterfaceService.useAll("admin");
		
		basePriceInterfaceService.useAll("admin");
		
		futuresPriceInterfaceService.useAll("admin");
		
		shipPointInterfaceService.useAll("admin");
	}
	
	

}
