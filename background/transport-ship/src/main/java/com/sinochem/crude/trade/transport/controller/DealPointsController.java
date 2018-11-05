package com.sinochem.crude.trade.transport.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.sinochem.crude.trade.transport.domain.DealPoints;
import com.sinochem.crude.trade.transport.model.DealPointsVO;
import com.sinochem.crude.trade.transport.model.res.DealPointsRes;
import com.sinochem.crude.trade.transport.query.DealPointsQuery;
import com.sinochem.crude.trade.transport.service.DealPointsService;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;


/**
 * @ClassName: Controller
 * @Description: 成交点数信息维护
 * @author Wh
 * @date 2017年11月23日
 * @version V1.0
 */
@Controller
public class DealPointsController  {
	@Autowired
	private DealPointsService dealPointsService;
	 
	Log log = LogFactory.getLog(DealPointsController.class);
	
	
	//初始化转换时间格式控件
		@InitBinder
	    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(false);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	    }
		
		/**
		 * 成交点数_翻页列表
		 * @param 
		 * @param 
		 * @return
		 */
		@RightAccess(2045)
		@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
		@RequestMapping(value = URLMapping.DEALPOINTS_GET_PAGE_LIST)
		public void getDealPointsList(CurrentUser user, ModelMap modelMap,
				@RequestParam(value = "page", required = false, defaultValue = "1") int page,
				@RequestParam(value = "apage", required = false) Integer pageAgain, DealPointsQuery query){
			// 地区名称
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("region", query.getRegion());
			
			// 总记录数
			int total = dealPointsService.countRecords(map);
			
			//总记录数设定
			query.setTotalItem(Long.valueOf(total));
			
			if (pageAgain != null) {
				query.setCurrentPage(pageAgain);
			} else {
				query.setCurrentPage(page);
			}
			
			List<Map<String, Object>> basicList= dealPointsService.getDealPointsPageList(query);
			modelMap.put("region", query.getRegion());
			modelMap.put("user", user);
			modelMap.put("datas", basicList);
			modelMap.put("query", query);
			
		}
		
		/**
			 * 新增
			 * @param DealPointsRes
			 * @param user
			 * @return res
			 */
		@RightAccess(2047)
		@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
		@ResponseBody
		@RequestMapping(value=URLMapping.DEALPOINTS_SAVEDP, method = RequestMethod.POST  )
		public Result saveDealPoints(@RequestBody DealPointsVO dp, CurrentUser user){
			Result res = new Result();
			try {
				//检查用户
				if(user == null){
					throw new TransportException(TransportException.TYPE.ITSH001);
				}
				//检查dp对象
				if(dp == null){
					throw new TransportException(TransportException.TYPE.ITSH319);
				}
				//检查地区名
				if(StringUtils.isNullOrEmpty(dp.getRegion())){
					throw new TransportException(TransportException.TYPE.ITSH321);
				}
				//检查船型
				if(StringUtils.isNullOrEmpty(dp.getType())){
					throw new TransportException(TransportException.TYPE.ITSH322);
				}
				//检查价格
				if(dp.getPrice() == null){
					throw new TransportException(TransportException.TYPE.ITSH323);
				}
				//检查名称
				if(StringUtils.isNullOrEmpty(dp.getName())){
					throw new TransportException(TransportException.TYPE.ITSH324);
				}
				//检查预估单桶运费
				if(dp.getSingleFare() == null){
					throw new TransportException(TransportException.TYPE.ITSH326);
				}
				//预估参考路线
				if(StringUtils.isNullOrEmpty(dp.getRefRoute())){
					throw new TransportException(TransportException.TYPE.ITSH327);
				}
				//检查日期
				if(dp.getDate() == null){
					throw new TransportException(TransportException.TYPE.ITSH325);
				}
				//查重
				DealPoints dealPoints = dealPointsService.checkDp(dp);
				if(dealPoints == null){
					dealPointsService.saveDealPoints(BeanConvertUtils.beanToBean(dp, DealPoints.class), user);					
				}else{
					res.setMessage(Constants.message_004);
					res.setStatus(999);
				}
			} catch (BizException e) {
				log.error("saveDealPoints error", e);
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setCode(e.getCode());
				res.setMessage(e.getMessage());
			} catch (Exception e) {
				log.error("saveDealPoints error", e);
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setCode(Constants.EXCEPTION_CODE);
				res.setMessage(Constants.EXCEPTION_MESSAGE);
			}
			return res;
		}
		
		/**
		 * 逻辑删除
		 * @param DealPointsRes
		 * @param user
		 * @return res
		 */
		@RightAccess(2050)
		@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
		@ResponseBody
		@RequestMapping(value=URLMapping.DEALPOINTS_DELETEDP, method = RequestMethod.POST  )
		public Result deleteDealPoints(@RequestBody DealPointsVO dp, CurrentUser user){
			Result res = new Result();
			try {
				//检查用户
				if(user == null){
					throw new TransportException(TransportException.TYPE.ITSH001);
				}
				//检查dp对象
				if(dp == null){
					throw new TransportException(TransportException.TYPE.ITSH319);
				}
				//检查uuid
				if(StringUtils.isNullOrEmpty(dp.getUuid())){
					throw new TransportException(TransportException.TYPE.ITSH320);
				}
				//检查是否已被删除
				DealPoints dealPoints = dealPointsService.findByUuid(dp.getUuid());
				if(dealPoints != null){
					dealPointsService.deleteDealPointsByUuid(BeanConvertUtils.beanToBean(dp, DealPoints.class), user);					
				}else{
					res.setMessage(Constants.message_003);
					res.setStatus(999);
				}
			} catch (BizException e) {
				log.error("deleteDealPoints error", e);
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setCode(e.getCode());
				res.setMessage(e.getMessage());
			} catch (Exception e) {
				log.error("deleteDealPoints error", e);
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setCode(Constants.EXCEPTION_CODE);
				res.setMessage(Constants.EXCEPTION_MESSAGE);
			}
			return res;
		}
		
		/**
		 * 修改
		 * @param DealPointsRes
		 * @param user
		 * @return res
		 */
		@RightAccess(2049)
		@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
		@ResponseBody
		@RequestMapping(value=URLMapping.DEALPOINTS_UPDATEDP, method = RequestMethod.POST  )
		public Result updateDealPoints(@RequestBody DealPointsVO dp, CurrentUser user){
			Result res = new Result();
			try {
				//检查用户
				if(user == null){
					throw new TransportException(TransportException.TYPE.ITSH001);
				}
				//检查dp对象
				if(dp == null){
					throw new TransportException(TransportException.TYPE.ITSH319);
				}
				//检查uuid
				if(StringUtils.isNullOrEmpty(dp.getUuid())){
					throw new TransportException(TransportException.TYPE.ITSH320);
				}
				//检查地区名
				if(StringUtils.isNullOrEmpty(dp.getRegion())){
					throw new TransportException(TransportException.TYPE.ITSH321);
				}
				//检查船型
				if(StringUtils.isNullOrEmpty(dp.getType())){
					throw new TransportException(TransportException.TYPE.ITSH322);
				}
				//检查价格
				if(dp.getPrice() == null){
					throw new TransportException(TransportException.TYPE.ITSH323);
				}
				//检查名称
				if(StringUtils.isNullOrEmpty(dp.getName())){
					throw new TransportException(TransportException.TYPE.ITSH324);
				}
				//检查日期
				if(dp.getDate() == null){
					throw new TransportException(TransportException.TYPE.ITSH325);
				}
				//预估参考路线
				if(StringUtils.isNullOrEmpty(dp.getRefRoute())){
					throw new TransportException(TransportException.TYPE.ITSH327);
				}
				//检查日期
				if(dp.getDate() == null){
					throw new TransportException(TransportException.TYPE.ITSH325);
				}
				//乐观锁控制
				DealPoints dealPoints = dealPointsService.findByUuid(dp.getUuid());
				if(dp.getVersion().equals(dealPoints.getVersion())){
					//查重
					DealPoints dealPoints2 = dealPointsService.checkDp(dp);
					if(dealPoints2 == null){
						dealPointsService.updateDealPointsByUuid(BeanConvertUtils.beanToBean(dp, DealPoints.class), user);
					}else{
						res.setMessage(Constants.message_004);
						res.setStatus(999);
					}
				}else{
					res.setMessage(Constants.message_010);
					res.setStatus(999);
				}
			} catch (BizException e) {
				log.error("updateDealPoints error", e);
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setCode(e.getCode());
				res.setMessage(e.getMessage());
			} catch (Exception e) {
				log.error("updateDealPoints error", e);
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setCode(Constants.EXCEPTION_CODE);
				res.setMessage(Constants.EXCEPTION_MESSAGE);
			}
			return res;
		}
		
		/**
		 * 查找对象
		 * @param DealPointsRes
		 * @return bt
		 */
		@RightAccess(2051)
		@RolesAccess({Constants.ADMIN, Constants.ENTER_MASTER})
		@ResponseBody
		@RequestMapping(value=URLMapping.DEALPOINTS_FINDDP, method = RequestMethod.POST  )
		public ResultDatas<DealPointsVO> getDealPoints(@RequestBody DealPointsVO dp){
			ResultDatas<DealPointsVO> res = new ResultDatas<>();
			try {
				
				//检查dp对象
				if(dp == null){
					throw new TransportException(TransportException.TYPE.ITSH319);
				}
				//检查uuid
				if(StringUtils.isNullOrEmpty(dp.getUuid())){
					throw new TransportException(TransportException.TYPE.ITSH320);
				}
				DealPoints basicTariff = dealPointsService.findByUuid(dp.getUuid());
				res.setDatas(BeanConvertUtils.beanToBean(basicTariff, DealPointsVO.class));
			} catch (BizException e) {
				log.error("getDealPoints error", e);
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setCode(e.getCode());
				res.setMessage(e.getMessage());
			} catch (Exception e) {
				log.error("getDealPoints error", e);
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setCode(Constants.EXCEPTION_CODE);
				res.setMessage(Constants.EXCEPTION_MESSAGE);
			}
			return res;
		}
		
	/********************************以下方法是给前台的接口**********************************************/
	
	/**
	 * 获取最新的成交点数(交易模块使用)
	 */
	@WithoutAccess	
	@ResponseBody
	@RequestMapping(value=URLMapping.QUERY_DEALPOINTS_TODAY, method = RequestMethod.POST)
	public ResultDatas<List<DealPointsRes>> queryTodayDealPointsList(){
		ResultDatas<List<DealPointsRes>> res = new ResultDatas<>();
		try {
			List<DealPoints> list = dealPointsService.getTodayDealPoints(null);
			if(list != null ){
				res.setDatas(BeanConvertUtils.beanToBeanInList(list, DealPointsRes.class));				
			}
		} catch (BizException e) {
			log.error("queryTodayDealPointsList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("queryTodayDealPointsList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 获取最新的成交点数
	 */
	@ResponseBody
	@RolesAccess({Constants.ADMIN,Constants.INSPECTION,Constants.DEPA_PORT,Constants.ARRIV_PORT,Constants.SALES_TRADER,Constants.INSPECTION,Constants.BUY_TRADER,
		Constants.SHIP_EXECUTOR,Constants.SHIP_TRADER,Constants.SHIP_BROKER,Constants.SHIP_OWNER,Constants.TRADE_EXECUTOR,Constants.ENTER_MASTER,Constants.CHARTERER})
	@RequestMapping(value=URLMapping.DEALPOINTS_GET_TODAY, method = RequestMethod.POST  )
	public ResultDatas<List<DealPointsVO>> getTodayDealPoints(CurrentUser user){
		ResultDatas<List<DealPointsVO>> res = new ResultDatas<>();
		try {
			
			// 验证登录信息
			if (user == null) {
				throw new TransportException(TransportException.TYPE.ITSH001);
			}
			if (user.getEpMemberId() == null){
				throw new TransportException(TransportException.TYPE.ITSH535);
			}
			
			List<DealPoints> list = dealPointsService.getNowDealPoints();
			for (DealPoints dealPoints : list) {
				DealPoints dealp = dealPointsService.getDealPointsByRecode(dealPoints);
					if(dealp != null){
						dealPoints.setExt1(dealPoints.getPrice().subtract(dealp.getPrice()).toString());
					}
			}
			if(list != null ){
				res.setDatas(BeanConvertUtils.beanToBeanInList(list, DealPointsVO.class));				
			}
		} catch (BizException e) {
			log.error("getNowDealPoints error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("getNowDealPoints error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 根据地区获取线性图所需数据
	 */
	@ResponseBody
	@RequestMapping(value=URLMapping.DEALPOINTS_GET_LINEDATAS, method = RequestMethod.POST  )
	public ResultDatas<List<Map<String, Object>>> getLineDatas(@RequestBody DealPointsVO dealPointsVO){
		ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
		try {
			long times = new Date().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String ext1 = sdf.format(times);
			dealPointsVO.setExt1(ext1);
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd");
			List<DealPoints> list = dealPointsService.getThisYearDealPoints(BeanConvertUtils.beanToBean(dealPointsVO, DealPoints.class));
			
			//返回给前台的集合
			List<Map<String, Object>> listRes = new ArrayList<>();
			
			for (DealPoints dp : list) {
				Map<String, Object> option = new HashMap<>();
				//横坐标
				option.put("cross", sdf2.format(dp.getDate()));
				//纵坐标
				option.put("ordinate", dp.getPrice());
				listRes.add(option);
			}
			
			if(listRes != null ){
				res.setDatas(listRes);				
			}
		} catch (BizException e) {
			log.error("getLineDatas error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("getLineDatas error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
		
		
	/********************************以下方法是对外提供的接口*****************************************************/
		
	/**
	 * 根据条件 查询集合
		 * @param dealPointsvo
		 * @return dealPointList
	 */
/*	@ResponseBody
<<<<<<< .working
	@RequestMapping(value=URLMapping.DEALPOINTS_FINDALL, method = RequestMethod.POST)	
=======
	@RequestMapping(value=URLMapping.DEALPOINTS_FINDALL, method = RequestMethod.POST  )	
>>>>>>> .merge-right.r13738
	public ResultDatas<Page<Map<String, Object>>> queryDealPoints(@RequestBody DealPointsVO dealPointsVO, SimplePageInfo pageInfo){
		ResultDatas<Page<Map<String, Object>>> res = new ResultDatas<>();
		try {
			//检查dealPointsVO
			if(dealPointsVO == null){
				throw new TransportException(TransportException.TYPE.ITSH301);
			}else{
				Page<DealPoints> list = dealPointsService.findDealPointsPageList(BeanConvertUtils.beanToBean(dealPointsVO, DealPoints.class), pageInfo);
				Page<Map<String, Object>> list2 = new Page<>();
				//存放所需数据
				for (DealPoints dp : list) {
					Map<String, Object> map = new HashMap<>();
					map.put("region", dp.getRegion());
					map.put("refRoute", dp.getRefRoute());
					map.put("singleFare", dp.getSingleFare());
					map.put("type", dp.getType());
					map.put("price", dp.getPrice());
					map.put("name", dp.getName());
					map.put("date", dp.getDate());
					map.put("remark", dp.getRemark());
					list2.add(map);
				}
				
				res.setDatas(list2);
				res.setPageCount(list.getPages());
				res.setPageNum(list.getPageNum());
				res.setPageSize(list.getPageSize());
			}			
		} catch (BizException e) {
			log.error("findDealPointsList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("findDealPointsList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}*/
	
	/**
	 * 根据条件 查询集合
		 * @param dealPointsvo
		 * @return dealPointList
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.DEALPOINTS_FINDALL, method = RequestMethod.POST  )	
	public ResultDatas<List<Map<String, Object>>> queryDealPoints(@RequestBody DealPointsVO vo){
		ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//检查dealPointsVO
			if(vo == null){
				throw new TransportException(TransportException.TYPE.ITSH301);
			}
			//条件查询
			Map<String, String> map = new HashMap<>();
			if(vo.getDate() != null){
				map.put("date", sdf.format(vo.getDate()));
			}
			if(vo.getDateStart() != null){
				map.put("dateStart", sdf.format(vo.getDateStart()));
			}
			if(vo.getDateEnd() != null){
				map.put("dateEnd", sdf.format(vo.getDateEnd()));
			}
			List<Map<String, Object>> list = dealPointsService.findDealPointsPageListByRecords(map);
			res.setDatas(list);
			/*if(dealPointsVO.getDate() == null){
				List<DealPoints> list = dealPointsService.findDealPointsPageList();
				List<Map<String, Object>> list2 = new ArrayList<>();
				//存放所需数据
				for (DealPoints dp : list) {
						Map<String, Object> map = new HashMap<>();
						map.put("region", dp.getRegion());
						map.put("refRoute", dp.getRefRoute());
						map.put("singleFare", dp.getSingleFare());
						map.put("type", dp.getType());
						map.put("price", dp.getPrice());
						String ddd = sdf.format(dp.getDate());
						map.put("pricingDate", ddd);
						map.put("remark", dp.getRemark());
						list2.add(map);
				}
				res.setDatas(list2);
			}
			else{
				List<DealPoints> list = dealPointsService.findDealPointsPageList();
				List<Map<String, Object>> list2 = new ArrayList<>();
				//存放所需数据
				String str2 = sdf.format(dealPointsVO.getDate());
				for (DealPoints dp : list) {
					String str1 = sdf.format(dp.getDate());
					if(str1.equals(str2)){
						Map<String, Object> map = new HashMap<>();
						map.put("region", dp.getRegion());
						map.put("refRoute", dp.getRefRoute());
						map.put("singleFare", dp.getSingleFare());
						map.put("type", dp.getType());
						map.put("price", dp.getPrice());
						String ddd = sdf.format(dp.getDate());
						map.put("pricingDate", ddd);
						map.put("remark", dp.getRemark());
						list2.add(map);
					}
				}
				res.setDatas(list2);
			}	*/		
		} catch (BizException e) {
			log.error("findDealPointsList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("findDealPointsList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	/**
	 * 新增
	 * @param DealPointsRes
	 * @param user
	 * @return res
	 */
	@ResponseBody
	@RequestMapping(value=URLMapping.DEALPOINTS_SAVEDPZX, method = RequestMethod.POST  )
	public Result saveDealPoints(@RequestBody DealPointsVO dp){
		Result res = new Result();
		try {
			//检查dp对象
			if(dp == null){
				throw new TransportException(TransportException.TYPE.ITSH319);
			}
			//检查地区名
			if(StringUtils.isNullOrEmpty(dp.getRegion())){
				throw new TransportException(TransportException.TYPE.ITSH321);
			}
			//检查船型
			if(StringUtils.isNullOrEmpty(dp.getType())){
				throw new TransportException(TransportException.TYPE.ITSH322);
			}
			//检查价格
			if(dp.getPrice() == null){
				throw new TransportException(TransportException.TYPE.ITSH323);
			}
			//检查名称
			if(StringUtils.isNullOrEmpty(dp.getName())){
				throw new TransportException(TransportException.TYPE.ITSH324);
			}
			//检查预估单桶运费
			if(dp.getSingleFare() == null){
				throw new TransportException(TransportException.TYPE.ITSH326);
			}
			//预估参考路线
			if(StringUtils.isNullOrEmpty(dp.getRefRoute())){
				throw new TransportException(TransportException.TYPE.ITSH327);
			}
			//检查日期
			if(dp.getDate() == null){
				throw new TransportException(TransportException.TYPE.ITSH325);
			}
			//查重
			DealPoints dealPoints = dealPointsService.checkDp(dp);
			if(dealPoints == null){
				dealPointsService.saveDealPoints2(BeanConvertUtils.beanToBean(dp, DealPoints.class));					
			}else{
				res.setMessage(Constants.message_004);
				res.setStatus(999);
			}
		} catch (BizException e) {
			log.error("saveDealPoints error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("saveDealPoints error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
	
	
	/**
	 * 参考时间集合
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.DEALPOINTS_FINDDATE, method = RequestMethod.POST  )	
	public ResultDatas<List<String>> queryDealPointsDates(@RequestBody DealPointsVO dealPointsVO){
		ResultDatas<List<String>> res = new ResultDatas<>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<Date> list = dealPointsService.findAllDates();
			List<String> list2 = new ArrayList<>();
			for (Date date : list) {
				list2.add(sdf.format(date));
			}
			res.setDatas(list2);
		} catch (BizException e) {
			log.error("findDealPointsList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(e.getCode());
			res.setMessage(e.getMessage());
		} catch (Exception e) {
			log.error("findDealPointsList error", e);
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setCode(Constants.EXCEPTION_CODE);
			res.setMessage(Constants.EXCEPTION_MESSAGE);
		}
		return res;
	}
}
