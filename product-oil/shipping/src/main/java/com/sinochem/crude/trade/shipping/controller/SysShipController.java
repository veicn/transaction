package com.sinochem.crude.trade.shipping.controller;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.common.utils.HttpUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.constant.UrlMapping;
import com.sinochem.crude.trade.shipping.domain.SysShip;
import com.sinochem.crude.trade.shipping.enums.VesselSizeEnums;
import com.sinochem.crude.trade.shipping.exceptions.TransportException;
import com.sinochem.crude.trade.shipping.model.query.SysShipQuery;
import com.sinochem.crude.trade.shipping.model.vo.SysShipVO;
import com.sinochem.crude.trade.shipping.service.ShipDictionaryService;
import com.sinochem.crude.trade.shipping.service.SysShipService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.common.result.ResultDatas;

@Controller
@RequestMapping(UrlMapping.PRODUCT_BACK_SYSSHIP)
public class SysShipController  {

	@Autowired
	private ShipDictionaryService shipDictionaryService;
	
	@Autowired
	private SysShipService sysShipService;
	
	public static final Log log = LogFactory.getLog(SysShipController.class);
	
	@InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
		
	/**
	 * 查询平台船舶信息翻页列表
	 * @param vo
	 * @return
	 * @exception
	 */
	@RequestMapping(value=UrlMapping.INDEX)
	public void SysShipQuery(SysShipQuery query, PageInfo pageInfo, ModelMap model){
		//setPageSize 为 8
		pageInfo.setPageSize(8);
		PageInfoResult<SysShip> pageInfoResult =
				sysShipService.queryByParamList(query, pageInfo);		
		List<SysShip> list = pageInfoResult.getList();
 
        List<SysShipVO> voList = new ArrayList<>();
        
        for(SysShip rec : list){
        	
        	voList.add(new SysShipVO(rec));
        	
        }
        pageInfoResult.setList(voList);
        model.addAttribute("datas", pageInfoResult);
	}
	
	/**
	 * 新增平台船舶信息查询 vesselSizeList
	 * @param user
	 * @param modelMap
	 */
	@RequestMapping(value=UrlMapping.SAVE_SYS_SHIP)
	public void saveSysShip(CurrentUser user,ModelMap modelMap){
        Object[] vesselSizeList = shipDictionaryService.VesselSizeMap().values().toArray();
        modelMap.put("vesselSizeList", vesselSizeList);
        
	}
	
	/**
	 * 新增平台船舶信息
	 * @param vo
	 * @return
	 * @exception
	 */
	@ResponseBody
	//@RequestMapping(value=UrlMapping.SAVE_SYS_SHIP, method = RequestMethod.POST)
	@RequestMapping(value=UrlMapping.UPDATE_SAVE_SYS_SHIP, method = RequestMethod.POST)
	public Result saveSysShipEdit(/*@RequestBody*/ SysShipVO vo, CurrentUser user,ModelMap modelMap) {

		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 船舶代码 IMO校验
			if (StringUtils.isNullOrEmpty(vo.getImo())) {
				throw new TransportException(TransportException.TYPE.ITSH202);
			}
			
			// 船名校验
			if (StringUtils.isNullOrEmpty(vo.getVesselName())) {
				throw new TransportException(TransportException.TYPE.ITSH203);
			}
			
			String uuid = KeyGenUtils.newUuid();
			vo.setUuid(uuid);
			// 新增平台船舶信息
			sysShipService.saveSysShip(vo, user);
			
			res.setMessage("newSysShip success  ");
			
		} catch (BizException e) {
			log.error("saveSysShip error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("saveSysShip error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 修改平台船舶信息 回显
	 * @param vo
	 * @return
	 * @exception
	 */
	//@ResponseBody
	@RequestMapping(value=UrlMapping.EDIT_SYS_SHIP)  
	public void editSysShip(SysShipVO vo, CurrentUser user,ModelMap modelMap) {
		// 修改平台船舶信息
		if(StringUtils.isNullOrEmpty(vo.getUuid())){
			//
		}else{
			// 查询平台船舶信息详细
			SysShip sysShip = new SysShip();
			SysShipVO shipVO = new SysShipVO();
			int code = 0;
			try {
				sysShip = sysShipService.findSysShipDetail(vo);
				
				BigDecimal a = sysShip.getVesselSize();
				if(a != null){
					code = a.intValue();
					
				}
				
				shipVO.convertToVO(sysShip);
			} catch (Exception e) {
				e.printStackTrace();
			}
			VesselSizeEnums name = null;
			switch (code) {
			case 1:
				 name = VesselSizeEnums.ULCC;
				break;
			case 2:
				 name = VesselSizeEnums.VLCC;
				break;
			case 3:
				 name = VesselSizeEnums.SUEZMAX;
				break;
			case 4:
				 name = VesselSizeEnums.AFRAMEAX;
				break;
			case 5:
				 name = VesselSizeEnums.PANAMAX;
				break;
			case 6:
				name = VesselSizeEnums.HANDYSIZE;
				break;
			}
			
			//String name = VesselSizeEnums.get
			
			modelMap.put("code", code);
			
			modelMap.put("name", name);
			
			modelMap.put("shipVO", shipVO);
			//
	        Object[] vesselSizeList = shipDictionaryService.VesselSizeMap().values().toArray();
	        modelMap.put("vesselSizeList", vesselSizeList);
			
		}
		
	}
	private VesselSizeEnums VesselSizeEnums(String string, String string2, String string3) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 编辑更新
	 * @param vo
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.UPDATE_SYS_SHIP, method = RequestMethod.POST)
	public Result updateSysShip(/*@RequestBody */SysShipVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 船舶uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH200);
			}
			
			// 船舶代码 IMO校验
			if (StringUtils.isNullOrEmpty(vo.getImo())) {
				throw new TransportException(TransportException.TYPE.ITSH202);
			}
			
			// 船名校验
			if (StringUtils.isNullOrEmpty(vo.getVesselName())) {
				throw new TransportException(TransportException.TYPE.ITSH203);
			}
			
			// 修改平台船舶信息
			sysShipService.updateSysShip(vo, user);
			res.setMessage("updateSysShip success!");
			
		
		} catch (BizException e) {
			log.error("updateSysShip error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
		
		} catch (Exception e) {
			log.error("updateSysShip error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 删除平台船舶信息
	 * @param vo
	 * @return
	 * @exception
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.DEL_SYS_SHIP)
	public Result delSysShip(/*@RequestBody*/ SysShipVO vo, CurrentUser user) {

		Result res = new Result();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 船舶uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH200);
			}
			
		
			// 删除平台船舶信息
			//sysShipService.delSysShip(vo);
			SysShip sysShip = new SysShip();
			sysShip = sysShipService.findByUuid(vo.getUuid());
			long code = sysShipService.deleteById(sysShip.getSysShipId());
			if(code > 0){
				res.setMessage(Constants.DELE_TRU);
			}
			
			//res.setMessage("平台船舶信息删除成功");
			
		} catch (BizException e) {
			log.error("delSysShip error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
			
		} catch (Exception e) {
			log.error("delSysShip error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}
	
	/**
	 * 查询平台船舶信息详细
	 * @param vo
	 * @return
	 * @exception
	 */
	/*@ResponseBody
	@RequestMapping(value=UrlMapping.FIND_SYS_SHIP_DETAIL, method = RequestMethod.POST)
	public ResultDatas<SysShip> findSysShipDetail(@RequestBody SysShipVO vo, CurrentUser user) {

		ResultDatas<SysShip> res = new ResultDatas<>();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 船舶uuid校验
			if (StringUtils.isNullOrEmpty(vo.getUuid())) {
				throw new TransportException(TransportException.TYPE.ITSH200);
			}
			
			// 查询平台船舶信息详细
			SysShip sysShip= sysShipService.findSysShipDetail(vo);
			
			// 设定返回数据
			res.setDatas(sysShip);
			
		} catch (BizException e) {
			log.error("findSysShipDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
			
		} catch (Exception e) {
			log.error("findSysShipDetail error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}*/
	
	//查看
	@RequestMapping(value=UrlMapping.FIND_SYS_SHIP_DETAIL)
	public void findSysShipDetail(SysShipVO vo,ModelMap model, CurrentUser user) {
			//uuid="4395ab7f-393e-4b12-89fd-a7a890a55260";
			if(StringUtils.isNullOrEmpty(vo.getUuid())){
				//
			}else{
				// 查询平台船舶信息详细
				SysShip sysShip = new SysShip();
				SysShipVO shipVO = new SysShipVO();
				try {
					sysShip = sysShipService.findSysShipDetail(vo);
					shipVO.convertToVO(sysShip);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				model.put("shipVO", shipVO);
			}
		}
	
	/**
	 * 查询平台船舶名称下拉列表
	 * @param vo
	 * @return
	 * @exception
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.FIND_SYS_SHIP_LIST, method = RequestMethod.POST)
	public ResultDatas<List<SysShip>> findSysShipList( @RequestBody SysShipVO vo, CurrentUser user) {

		ResultDatas<List<SysShip>> res = new ResultDatas<>();
		
		try {
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			
			// 查询平台船舶信息列表
			List<SysShip> sysShipList= sysShipService.findSysShipList(vo);
			
			// 设定返回数据
			res.setDatas(sysShipList);
			
		} catch (BizException e) {
			log.error("findSysShipList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
			
		} catch (Exception e) {
			log.error("findSysShipList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}

	/**
	 * 根据imo、查询船舶MMSI列表
	 * @param vo
	 * @return
	 * @exception
	 */
	@ResponseBody
	@RequestMapping(value=UrlMapping.FIND_SYS_SHIP_MMSI, method = RequestMethod.POST)
	public ResultDatas<Map<String, Object>> findSysShipMmsi(@RequestBody SysShipVO vo, CurrentUser user) {

		ResultDatas<Map<String, Object>> res = new ResultDatas<>();
		
		try {
			// imo
			String imo = vo.getImo();
			
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}

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
    		
    		if (!StringUtils.isNullOrEmpty(mmsiJson)) {
    			@SuppressWarnings("unchecked")
    			Map<String, Object> mmsiObj = JSONObject.parseObject(mmsiJson, Map.class);
    			// 设定返回数据
    			res.setDatas(mmsiObj);
    		} else {
    			// 设定返回数据
    			res.setDatas(null);
    		}
			
		} catch (BizException e) {
			log.error("findSysShipStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
			
		} catch (Exception e) {
			log.error("findSysShipStatus error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		
		return res;
	}

	/**
	 * json接口 根据船舶ID查询船舶信息（主要是IMO）
	 * @param shipVO
	 * @return
	 */
	@RequestMapping(value = "findShipVoByShipId.json",method = RequestMethod.POST)
	@ResponseBody
	public SysShipVO findShipVoByShipId(@RequestBody SysShipVO shipVO){

		try{
			shipVO =  sysShipService.findShipVoByShipIdRemote(shipVO);
		}catch (Exception e){
			log.error("根据船舶ID查询imo失败",e);
		}finally {
			return shipVO;
		}
	}
}
