package com.sinochem.crude.trade.transport.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
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
import com.sinochem.crude.trade.transport.domain.UnitRate;
import com.sinochem.crude.trade.transport.model.UnitRateVO;
import com.sinochem.crude.trade.transport.query.UnitRateQuery;
import com.sinochem.crude.trade.transport.service.UnitRateService;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;


@Controller
public class UnitRateController  {
	@Autowired
	private UnitRateService unitRateService;
	 
	Log log = LogFactory.getLog(UnitRateController.class);	

	@InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	/**
	 * 单位换算比率_查询分页列表信息
	 */
	@RightAccess(2029)
	@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
	@RequestMapping(value=URLMapping.UNIT_GET_UNITRATE_PAGE_LIST)
	public void unitRateList(CurrentUser user, ModelMap modelMap,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain, UnitRateQuery query){
		
		// 单位名称
		Map<String, Object> map = new HashMap<String, Object>();
		if(query.getOneName() == null){
			query.setOneName("");
		}
		if(query.getTwoName() == null){
			query.setTwoName("");
		}
		map.put("oneName", query.getOneName());
		map.put("twoName", query.getTwoName());
		
		// 总记录数
		int total = unitRateService.countRecords(map);
		
		//总记录数设定
		query.setTotalItem(Long.valueOf(total));
		
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		List<Map<String, Object>> unitList= unitRateService.getUnitPageList(query);

		modelMap.put("serchNameOne", query.getOneName());
		modelMap.put("serchNameTwo", query.getTwoName());
		modelMap.put("user", user);
		modelMap.put("datas", unitList);
		modelMap.put("query", query);
		
	}
	
	/**
	 * 单位换算_条件查询列表
	 * @param UnitRate_type
	 * @return List<UnitRate>
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.UNITRATE_FINDALL, method = RequestMethod.POST  )
	public ResultDatas<List<UnitRateVO>> findAllUnitRate(@RequestBody UnitRateVO unitRateVO){
		ResultDatas<List<UnitRateVO>> res = new ResultDatas<>();
		try {
			//检查unitRateRateVO
			if(unitRateVO == null){
				//如果为空查询  所有类型返回
				throw new TransportException(TransportException.TYPE.ITSH302);
			}else{
				//根据类型查询所对应的所有单位
				List<UnitRate> queryByEntitys = unitRateService.queryByEntitys(BeanConvertUtils.beanToBean(unitRateVO, UnitRate.class));
				List<UnitRateVO> unitRateList = BeanConvertUtils.beanToBeanInList(queryByEntitys, UnitRateVO.class);			
				res.setDatas(unitRateList);
			}			
		} catch (BizException e) {
			log.error("findUnitRateList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("findUnitRateList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 单位换算信息维护_查询对象
	 * @param uuid
	 * @return UnitRate对象
	 */
	@RightAccess(2035)
	@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
	@ResponseBody
	@RequestMapping(value=URLMapping.UNITRATE_FINDUNITRATE, method = RequestMethod.POST  )
	public ResultDatas<UnitRateVO> findUnitRate(@RequestBody UnitRateVO unitRateVO){
		ResultDatas<UnitRateVO> res = new ResultDatas<>();
		try {
			
			//检查unitRateRateVO
			if(unitRateVO == null){
				throw new TransportException(TransportException.TYPE.ITSH302);
			}
			//检查uuid
			if(StringUtils.isNullOrEmpty(unitRateVO.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH304);
			}
			UnitRate unitRate = unitRateService.findByUuid(unitRateVO.getUuid());
			res.setDatas(BeanConvertUtils.beanToBean(unitRate, UnitRateVO.class));
		} catch (BizException e) {
			log.error("findUnitRate error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("findUnitRate error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 单位换算信息维护__新增对象
	 * @param UnitRate对象
	 */
	@RightAccess(2031)
	@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
	@ResponseBody
	@RequestMapping(value=URLMapping.UNITRATE_SAVEUNITRATE, method = RequestMethod.POST  )
	public Result saveUnitRate(@RequestBody UnitRateVO unitRateVO, CurrentUser user){
		Result res = new Result();
		try {
			//检查用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			//检查unitRateRateVO
			if(unitRateVO == null){
				throw new TransportException(TransportException.TYPE.ITSH302);
			}
			//检查参考单位名称
			if(StringUtils.isNullOrEmpty(unitRateVO.getOneName())){
				throw new TransportException(TransportException.TYPE.ITSH308);
			}
			//检查对比单位类型
			if(StringUtils.isNullOrEmpty(unitRateVO.getTwoName())){
				throw new TransportException(TransportException.TYPE.ITSH309);
			}
			//检查底数
			if(StringUtils.isNullOrEmpty(unitRateVO.getTwoName())){
				throw new TransportException(TransportException.TYPE.ITSH310);
			}
			//检查指数
			if(StringUtils.isNullOrEmpty(unitRateVO.getTwoName())){
				throw new TransportException(TransportException.TYPE.ITSH311);
			}
			//检查是否已经存在此种比例
			UnitRate unitRate = unitRateService.getRateInfo(BeanConvertUtils.beanToBean(unitRateVO, UnitRate.class));
			if(unitRate == null){
				unitRateService.insertRecord2(BeanConvertUtils.beanToBean(unitRateVO, UnitRate.class), user.getMemberId());				
			}else{
				res.setMessage(Constants.message_030);
				res.setStatus(999);
			}
		} catch (BizException e) {
			log.error("savaUnitRate error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("savaUnitRate error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 单位换算信息维护__逻辑删除对象
	 * @param uuid
	 */
	@RightAccess(2034)
	@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
	@ResponseBody
	@RequestMapping(value=URLMapping.UNITRATE_DELETEUNITRATE, method = RequestMethod.POST  )
	public Result delUnitRate(@RequestBody UnitRateVO unitRateVO, CurrentUser user){
		Result res = new Result();
		try {
			//检查用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			//检查unitRateVO
			if(unitRateVO == null){
				throw new TransportException(TransportException.TYPE.ITSH302);
			}
			//检查uuid
			if(StringUtils.isNullOrEmpty(unitRateVO.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH304);
			}
			//检查被删除的对象是否已经被删除
			UnitRate unitRate = unitRateService.findByUuid(unitRateVO.getUuid());
			if(unitRate != null){
				unitRateService.deleteRecordByUuid(unitRateVO.getUuid(), user.getMemberId());				
			}else{
				res.setMessage(Constants.message_003);
				res.setStatus(999);
			}
		} catch (BizException e) {
			log.error("deleteUnitRate error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("deleteUnitRate error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 单位换算信息维护__修改
	 * @param unitRate对象
	 */
	@RightAccess(2033)
	@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
	@ResponseBody
	@RequestMapping(value=URLMapping.UNITRATE_UPDATEUNITRATE, method = RequestMethod.POST  )
	public Result updateUnitRate(@RequestBody UnitRateVO unitRateVO, CurrentUser user){
		Result res = new Result();
		try {
			//检查用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			//检查unitRateRateVO
			if(unitRateVO == null){
				throw new TransportException(TransportException.TYPE.ITSH302);
			}
			//检查uuid
			if(StringUtils.isNullOrEmpty(unitRateVO.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH304);
			}
			//检查参考单位名称
			if(StringUtils.isNullOrEmpty(unitRateVO.getOneName())){
				throw new TransportException(TransportException.TYPE.ITSH308);
			}
			//检查对比单位类型
			if(StringUtils.isNullOrEmpty(unitRateVO.getTwoName())){
				throw new TransportException(TransportException.TYPE.ITSH309);
			}
			//检查底数
			if(StringUtils.isNullOrEmpty(unitRateVO.getTwoName())){
				throw new TransportException(TransportException.TYPE.ITSH310);
			}
			//检查指数
			if(StringUtils.isNullOrEmpty(unitRateVO.getTwoName())){
				throw new TransportException(TransportException.TYPE.ITSH311);
			}
			//乐观锁控制修改
			UnitRate unitRate = unitRateService.findByUuid(unitRateVO.getUuid());
			if(unitRateVO.getVersion().equals(unitRate.getVersion())){
				//检查是否已经存在此种比例
				UnitRate unitRate2 = unitRateService.getRateInfo(BeanConvertUtils.beanToBean(unitRateVO, UnitRate.class));
				if(unitRate2 == null || unitRateVO.getOneName().equals(unitRate.getOneName()) && unitRateVO.getTwoName().equals(unitRate.getTwoName())){
					unitRateService.updateRecordByUuid(BeanConvertUtils.beanToBean(unitRateVO, UnitRate.class), user.getMemberId());				
				}else{
					res.setMessage(Constants.message_030);
					res.setStatus(999);
				}
			}else{
				res.setMessage(Constants.message_031);
				res.setStatus(999);
			}
		} catch (BizException e) {
			log.error("updateUnitRate error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("updateUnitRate error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/*****************************前台功能***********************************/
	
	/**
		 * 通过类型，参考单位，对比单位，查找出比率信息
		 * @param UnitRateVO
		 * @return UnitRateVO
		 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.GET_UNITRATE_BY_RECORD, method = RequestMethod.POST  )
	public ResultDatas<Double> getRateInfo(@RequestBody UnitRateVO unitRateVO){
		ResultDatas<Double> res = new ResultDatas<>();
		try {
			//检查unitRateRateVO
			if(unitRateVO == null){
				throw new TransportException(TransportException.TYPE.ITSH302);
			}
			//检查参考单位名称
			if(StringUtils.isNullOrEmpty(unitRateVO.getOneName())){
				throw new TransportException(TransportException.TYPE.ITSH308);
			}
			//检查对比单位类型
			if(StringUtils.isNullOrEmpty(unitRateVO.getTwoName())){
				throw new TransportException(TransportException.TYPE.ITSH309);
			}
			UnitRate rateInfo = unitRateService.getRateInfo(BeanConvertUtils.beanToBean(unitRateVO, UnitRate.class));
			if(rateInfo != null){
				Double baseName = rateInfo.getBaseName().doubleValue();
				Double doubleValue = rateInfo.getPower().doubleValue();
				Double rate = (baseName*Math.pow(10,doubleValue));
				res.setDatas(rate);				
			}else{
				res.setMessage(Constants.message_032);
			}
		} catch (BizException e) {
			log.error("getRateInfo error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("getRateInfo error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
}
