package com.sinochem.crude.trade.transport.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.sinochem.crude.trade.transport.domain.Unit;
import com.sinochem.crude.trade.transport.model.ShipPactVO;
import com.sinochem.crude.trade.transport.model.UnitVO;
import com.sinochem.crude.trade.transport.query.UnitQuery;
import com.sinochem.crude.trade.transport.service.ShipPactService;
import com.sinochem.crude.trade.transport.service.UnitRateService;
import com.sinochem.crude.trade.transport.service.UnitService;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;


/**
 * @ClassName: UnitController
 * @Description:单位换算信息维护 
 * @author Wh
 * @date 2017年11月20日
 * @version V1.0
 */

@Controller
public class UnitController  {
	@Autowired
	private UnitService unitService;
	@Autowired
	private UnitRateService unitRateService;
	
	@Autowired
	private ShipPactService shipPactService;
	 
	Log log = LogFactory.getLog(UnitController.class);
	
	@InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	/**
	 * 单位换算_查询分页列表信息
	 */
	@RightAccess(2022)
	@WithoutAccess
	@RequestMapping(value=URLMapping.UNIT_GET_UNIT_PAGE_LIST)
	public void unitList(CurrentUser user, ModelMap modelMap,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain, UnitQuery query){
		
		// 类型名称
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", query.getType());
		
		// 总记录数
		int total = unitService.countRecords(map);
		
		//总记录数设定
		query.setTotalItem(Long.valueOf(total));
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		//查询值集
		ShipPactVO shipPact = new ShipPactVO();
		//查询14单位集
		shipPact.setValueSetTypes("14;");
		if (StringUtils.isNullOrEmpty(shipPact.getValueSetType()) && StringUtils.isNullOrEmpty(shipPact.getValueSetTypes())){
			throw new TransportException(TransportException.TYPE.ITSH089);
		}
		List<Map<String,Object>> valueList = shipPactService.queryValueSet(shipPact);
		List<Object> types = new ArrayList<>();
		//遍历赋值
		
		for (Map<String, Object> ty : valueList) {
			Set<String> keySet = ty.keySet();
			for (String string : keySet) {
				if(string == "type"){
					if(ty.get(string).equals("14")){
						types.add(ty.get("value"));							
					}
				}
			}
		}
		
		List<Map<String, Object>> unitList= unitService.getUnitPageList(query);
		 
		modelMap.put("user", user);
		modelMap.put("types", types);
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
	@RequestMapping(value=URLMapping.UNIT_FINDALL, method = RequestMethod.POST  )
	public ResultDatas<List<UnitVO>> findAllUnit(@RequestBody UnitVO unitVO){
		ResultDatas<List<UnitVO>> res = new ResultDatas<>();
		try {
			//检查unitVO
			if(unitVO == null){
				throw new TransportException(TransportException.TYPE.ITSH301);
			}else{
				//根据类型查询所对应的所有单位
				List<Unit> queryByEntitys = unitService.queryByEntitys(BeanConvertUtils.beanToBean(unitVO, Unit.class));
				List<UnitVO> unitList = BeanConvertUtils.beanToBeanInList(queryByEntitys, UnitVO.class);			
				res.setDatas(unitList);
			}			
		} catch (BizException e) {
			log.error("findUnitList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("findUnitList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 单位换算信息维护_查询对象
	 */
	@RightAccess(2028)
	@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
	@ResponseBody
	@RequestMapping(value=URLMapping.UNIT_FINDUNIT, method = RequestMethod.POST  )
	public ResultDatas<UnitVO> findUnit(@RequestBody UnitVO unitVO){
		ResultDatas<UnitVO> res = new ResultDatas<>();
		try {
			
			//检查unitVO
			if(unitVO == null){
				throw new TransportException(TransportException.TYPE.ITSH301);
			}
			//检查uuid
			if(StringUtils.isNullOrEmpty(unitVO.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH303);
			}
			res.setDatas(BeanConvertUtils.beanToBean(unitService.findByUuid(unitVO.getUuid()), UnitVO.class));
		} catch (BizException e) {
			log.error("findUnit error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("findUnit error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 单位换算信息维护__新增对象
	 */
	@RightAccess(2024)
	@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
	@ResponseBody
	@RequestMapping(value=URLMapping.UNIT_SAVEUNIT, method = RequestMethod.POST  )
	public Result saveUnit(@RequestBody UnitVO unitVO, CurrentUser user){
		Result res = new Result();
		try {
			//检查用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			//检查unitVO
			if(unitVO == null){
				throw new TransportException(TransportException.TYPE.ITSH301);
			}
			//检查单位名称
			if(StringUtils.isNullOrEmpty(unitVO.getChName())){
				throw new TransportException(TransportException.TYPE.ITSH306);
			}
			//检查单位类型
			if(StringUtils.isNullOrEmpty(unitVO.getType())){
				throw new TransportException(TransportException.TYPE.ITSH305);
			}
			//检查单位是否为参考
			if(StringUtils.isNullOrEmpty(unitVO.getIsDefaultUnit())){
				throw new TransportException(TransportException.TYPE.ITSH307);
			}
			//修改默认参考单位
			if(unitVO.getIsDefaultUnit().equals("1")){
				unitService.updateDefaultUnit(BeanConvertUtils.beanToBean(unitVO, Unit.class));
			}
			//检查是否已经存在
			Unit unit = unitService.findByName(unitVO.getChName());
			if(unit == null){
				unitService.insertRecord2(BeanConvertUtils.beanToBean(unitVO, Unit.class), user.getMemberId());				
			}else{
				res.setMessage(Constants.message_027);
				res.setStatus(999);
			}
		} catch (BizException e) {
			log.error("saveUnit error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("saveUnit error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 单位换算信息维护__逻辑删除对象
	 */
	@RightAccess(2025)
	@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
	@ResponseBody
	@RequestMapping(value=URLMapping.UNIT_DELETEUNIT, method = RequestMethod.POST  )
	public Result delUnit(@RequestBody UnitVO unitVO, CurrentUser user){
		Result res = new Result();
		try {
			//检查用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			//检查unitVO
			if(unitVO == null){
				throw new TransportException(TransportException.TYPE.ITSH301);
			}
			//检查uuid
			if(StringUtils.isNullOrEmpty(unitVO.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH303);
			}
			//检查是否重复操作
			Unit unit = unitService.findByUuid(unitVO.getUuid());
			if(unit != null){
				//检查如果已经存在单位换算的比率关系 禁止直接删除
				String chName = unit.getChName();
				int ur = unitRateService.getUnitRateByName(chName);
				if(ur > 0){
					res.setMessage(Constants.message_028);
					res.setStatus(999);
				}else{
					unitService.deleteRecordByUuid(unitVO.getUuid(), user.getMemberId());
				}
			}else{
				res.setMessage(Constants.message_003);
				res.setStatus(999);
			}
		} catch (BizException e) {
			log.error("deleteUnit error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("deleteUnit error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 单位换算信息维护__修改
	 */
	@RightAccess(2027)
	@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
	@ResponseBody
	@RequestMapping(value=URLMapping.UNIT_UPDATEUNIT, method = RequestMethod.POST  )
	public Result updateUnit(@RequestBody UnitVO unitVO, CurrentUser user){
		Result res = new Result();
		try {
			
			//检查用户
			if(user == null){
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			//检查unitVO
			if(unitVO == null){
				throw new TransportException(TransportException.TYPE.ITSH301);
			}
			//检查uuid
			if(StringUtils.isNullOrEmpty(unitVO.getUuid())){
				throw new TransportException(TransportException.TYPE.ITSH303);
			}
			//检查单位名称
			if(StringUtils.isNullOrEmpty(unitVO.getChName())){
				throw new TransportException(TransportException.TYPE.ITSH306);
			}
			//检查单位类型
			if(StringUtils.isNullOrEmpty(unitVO.getType())){
				throw new TransportException(TransportException.TYPE.ITSH305);
			}
			//检查单位是否为参考
			if(StringUtils.isNullOrEmpty(unitVO.getIsDefaultUnit())){
				throw new TransportException(TransportException.TYPE.ITSH307);
			}
			//修改默认参考单位
			Unit unit = unitService.findByUuid(unitVO.getUuid());
			if(unitVO.getVersion().equals(unit.getVersion())){
				//检查是否已经存在
				Unit unit2 = unitService.findByName(unitVO.getChName());
				if(unit2 == null || unit.getChName().equals(unitVO.getChName())){
					if(unitVO.getIsDefaultUnit().equals("1")){
						unitService.updateDefaultUnit(BeanConvertUtils.beanToBean(unitVO, Unit.class));
					}
					unitService.updateRecordByUuid(BeanConvertUtils.beanToBean(unitVO, Unit.class), user.getMemberId());				
				}else{
					res.setMessage(Constants.message_004);
					res.setStatus(999);
				}
			}else{
				res.setMessage(Constants.message_029);
				res.setStatus(999);
			}
		} catch (BizException e) {
			log.error("updateUnit error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("updateUnit error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
}
