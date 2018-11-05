package com.sinochem.crude.trade.info.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eyeieye.melody.web.locale.VisitorLocale;
import com.github.pagehelper.Page;
import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.info.domain.ColColumn;
import com.sinochem.crude.trade.info.domain.ColSubscribe;
import com.sinochem.crude.trade.info.domain.Collection;
import com.sinochem.crude.trade.info.domain.Fabulous;
import com.sinochem.crude.trade.info.domain.Info;
import com.sinochem.crude.trade.info.model.ColColumnVO;
import com.sinochem.crude.trade.info.model.ColSubscribeVO;
import com.sinochem.crude.trade.info.model.ColumnDetailVO;
import com.sinochem.crude.trade.info.model.ColumnVO;
import com.sinochem.crude.trade.info.model.FabulousVO;
import com.sinochem.crude.trade.info.query.ColColumnQuery;
import com.sinochem.crude.trade.info.result.ColColumnRest;
import com.sinochem.crude.trade.info.service.ColColumnService;
import com.sinochem.crude.trade.info.service.InfoService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;

@Controller
public class ColColumnController {
	@Autowired
	private ColColumnService colColumnService;
	
	@Autowired
	private InfoService infoService;
	
	Log log = LogFactory.getLog(AuditInfoController.class);
	
	private static final String STATUS1 = "1";
	private static final String STATUS0 = "0";
	private static final String ERROR_CODE = "Invalid Access";
	private static final String MAP_KEY = "status";
	
	/**
	 * 新增栏目文章
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = URLMapping.COLCLUMNINFO_ADD, method = RequestMethod.POST)
	public Result addColumnInfo(@RequestBody ColumnVO vo,CurrentUser user){
		Result result = new Result();
		if(StringUtils.isBlank(vo.getBizId())){
			result.setFail("专栏文章ID不为空");
			return result;
		}
		return colColumnService.addColumnInfo(vo,user);
	}
	
	/**
	 * 文章详情/预览栏目文章
	 * @param infoUuid
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value = URLMapping.COLCLUMNINFO_QUERY)
	public ResultDatas<ColumnVO> infoDetail(@RequestParam String infoUuid,CurrentUser user){
		ResultDatas<ColumnVO> result = new ResultDatas<>();
		try {
			if(StringUtils.isBlank(infoUuid)){
				result.setFail("文章uuid不能为空！");
				return result;
			}
			Map<String, Object> map = colColumnService.getInfoByUuid(infoUuid);
			
			if(null == map){
				result.setFail("文章不存在");
				return result;
			}else{
				ColumnVO columnVO = BeanConvertUtils.mapToBean(map, ColumnVO.class);
				if(user == null){
					columnVO.setSubStatus(STATUS0);
					columnVO.setFabStatus(STATUS0);
					columnVO.setColStatus(STATUS0);
				}else{
					ColSubscribeVO colSubscribeVO = new ColSubscribeVO();
					colSubscribeVO.setColumnId(columnVO.getId());
					colSubscribeVO.setSubscribeUser(user.getMemberId().toString());
					List<ColSubscribe> subList = colColumnService.findSubscribeByColumnId(colSubscribeVO);
					if(subList.size() != 1){
						columnVO.setSubStatus(STATUS0);
					}else{
						columnVO.setSubStatus(STATUS1);
					}
					
					/*查询是否点赞*/
					FabulousVO fabulousVO = new FabulousVO();
					fabulousVO.setObjId(Long.valueOf(columnVO.getInfoId()));
					fabulousVO.setFabulousUserId(user.getMemberId().toString());
					List<Fabulous> fabList = colColumnService.findFabulousByInfoId(fabulousVO);
					if(fabList.size() != 1){
						columnVO.setFabStatus(STATUS0);
					}else{
						columnVO.setFabStatus(STATUS1);
					}
					
					/*查询是否收藏*/
					Collection collection = new Collection();
					collection.setInformationId(Long.valueOf(columnVO.getInfoId()));
					collection.setCollectionUserId(user.getMemberId().toString());
					List<Collection> collList = colColumnService.findCollectionByInfoId(collection);
					if(collList.size() != 1){
						columnVO.setColStatus(STATUS0);
					}else{
						columnVO.setColStatus(STATUS1);
					}
					
				}
				
				
				Integer i = colColumnService.updateInfo(columnVO);
				if(i > 0){
					result.setStatus(Result.SUCCESS);
					result.setMessage("成功");
					result.setDatas(columnVO);
				}else{
					result.setFail("查看失败");
				}
				return result;
			}
		} catch (Exception e) {
			result.setStatus(Result.EEROR);
			result.setMessage("失败");
			return result;
		}
		
	}
	
	/**
	 * 保存预览文章接口
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = URLMapping.COLCLUMNINFO_SAVE, method = RequestMethod.POST)
	public Result saveColumnInfo(@RequestBody ColumnVO vo,CurrentUser user){
		Result result = new Result();
		if(StringUtils.isBlank(vo.getBizId())){
			result.setFail("专栏文章ID不为空");
			return result;
		}
		return colColumnService.saveColumnInfo(vo,user);
	}
	
	/**
	 * 热门文章列表
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value = URLMapping.COLCLUMNINFO_HOT, method = RequestMethod.GET)
	public ResultDatas<List<ColumnVO>> hotInfoList(){
		ResultDatas<List<ColumnVO>> result = new ResultDatas<>();
		try {
			List<Map<String, Object>> hotInfoList = colColumnService.hotInfoList();
			result.setDatas(BeanConvertUtils.mapToBeanInList(hotInfoList, ColumnVO.class));
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			result.setStatus(Result.EEROR);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 
	 * 专栏申请
	 * @param colColumnVO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value=URLMapping.COLCLUMN_APPLY,method = RequestMethod.POST)
	public ResultDatas<Map<String,Object>>  applyColumn(@RequestBody ColColumnVO colColumnVO,CurrentUser user){
		ResultDatas<Map<String,Object>> result=new ResultDatas<>();
		Locale current = VisitorLocale.getCurrent();
		try {
			result.setStatus(Result.SUCCESS);
			result.setMessage("专栏成功提交申请");
			if(user==null){
				result.setStatus(Result.EEROR);
				result.setCode(ERROR_CODE);
				result.setMessage("请登录");
				return result;
			}
			colColumnVO.setAuthor(user.getMemberId()+"");
			colColumnVO.setStatus("10");
			if(StringUtils.isEmpty(current.getLanguage())){
				colColumnVO.setLangVer("zh");
			}else{
				colColumnVO.setLangVer(current.getLanguage());
			}
			ColColumn col=new ColColumn();
			col.setAuthor(user.getMemberId()+"");
			List<ColColumn> queryByEntitys = colColumnService.queryByEntitys(col);
			if(StringUtils.isEmpty(colColumnVO.getId())){
				if(CollectionUtils.isNotEmpty(queryByEntitys)){
					result.setCode(queryByEntitys.get(0).getId());
					result.setStatus(Result.EEROR);
					result.setMessage("已申请过专栏");
					return result;
				}
				colColumnService.insertRecord(colColumnVO,user);
			}else{
				if(CollectionUtils.isNotEmpty(queryByEntitys)){
					if(StringUtils.equals("20", queryByEntitys.get(0).getStatus())){
						colColumnVO.setStatus("20");
					}
				}
				colColumnVO.setUpdateDate(DateTimeUtils.currentDate());
				colColumnVO.setUpdateUser(user.getName());
				colColumnService.updateRecord(colColumnVO);
			}
			result.setCode(colColumnVO.getId());
			return result;
		} catch (Exception e) {
			result.setFail("申请失败");
			return result;
		}
		
	}
	/**
	 * 
	 * 专栏预览保存
	 * @param colColumnVO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value=URLMapping.COLCLUMN_PREVIEW,method = RequestMethod.POST)
	public ResultDatas<Map<String,Object>>  savePreviewColumn(@RequestBody ColColumnVO colColumnVO,CurrentUser user){
		ResultDatas<Map<String,Object>> result=new ResultDatas<>();
		Locale current = VisitorLocale.getCurrent();
		try {
			result.setStatus(Result.SUCCESS);
			result.setMessage("专栏成功提交申请");
			if(user==null){
				result.setStatus(Result.EEROR);
				result.setCode(ERROR_CODE);
				result.setMessage("请登录");
				return result;
			}
			colColumnVO.setAuthor(user.getMemberId()+"");
			colColumnVO.setStatus("09");
			if(StringUtils.isEmpty(current.getLanguage())){
				colColumnVO.setLangVer("zh");
			}else{
				colColumnVO.setLangVer(current.getLanguage());
			}
			if(StringUtils.isEmpty(colColumnVO.getId())){
				ColColumn col=new ColColumn();
				col.setAuthor(user.getMemberId()+"");
				if(CollectionUtils.isNotEmpty(colColumnService.queryByEntitys(col))){
					result.setStatus(Result.EEROR);
					result.setMessage("已申请过专栏");
					return result;
				}
				colColumnService.insertRecord(colColumnVO,user);
			}else{
				colColumnVO.setUpdateDate(DateTimeUtils.currentDate());
				colColumnVO.setUpdateUser(user.getName());
				colColumnService.updateRecord(colColumnVO);
			}
			result.setCode(colColumnVO.getId());
			return result;
		} catch (Exception e) {
			result.setStatus(Result.EEROR);
			result.setFail("预览失败");
			return result;
		}
		
	}
	/**
	 * 
	 * 专栏详情、预览
	 * @param colColumnVO
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.COLCLUMN_QUERY,method = RequestMethod.POST)
	public ResultDatas<ColumnDetailVO> previewColumn(@RequestBody ColColumnQuery query,CurrentUser user){
		ResultDatas<ColumnDetailVO> result = new ResultDatas<>();
		try {
			if(StringUtils.isEmpty(query.getId())){
				result.setStatus(Result.EEROR);
				result.setMessage("专栏id为空");
			}else{
				Map<String, Object> map=new HashMap<>();
				map.put("id", query.getId());
				if(user!=null){
					map.put("userId", user.getMemberId());
				}
				ColumnDetailVO data = colColumnService.findById(map);
				result.setDatas(data);
				result.setStatus(Result.SUCCESS);
			}
			return result;
		} catch (Exception e) {
			result.setMessage("查询失败"+e);
			result.setStatus(Result.EEROR);
			return result;
		}
	}
	/**
	 * 
	 * 我的专栏
	 * @param colColumnVO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value=URLMapping.COLCLUMN_MY)
	public ResultDatas<ColumnDetailVO> myColumn(CurrentUser user){
		ResultDatas<ColumnDetailVO> result = new ResultDatas<>();
		try {
			if(user == null){
				result.setStatus(Result.EEROR);
				result.setCode(ERROR_CODE);
				result.setMessage("请登录");
			}else{
				ColColumn col=new ColColumn();
				col.setAuthor(user.getMemberId()+"");
				ColumnDetailVO data = colColumnService.findById(BeanConvertUtils.beanToMap(col));
				if(data!=null){
					result.setDatas(data);
					result.setMessage("查询成功");
					result.setStatus(Result.SUCCESS);
				}else{
					result.setStatus(Result.SUCCESS);
					result.setMessage("无专栏");
				}
			}
			return result;
		} catch (Exception e) {
			result.setMessage("查询失败"+e);
			result.setStatus(Result.EEROR);
			return result;
		}
	}
	/**
	 * 
	 * 行业分析专栏接口
	 * @param query
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.COLCLUMN_LIST,method = RequestMethod.POST)
	public ResultDatas<List<Map<String, Object>>> columnList(@RequestBody ColColumnQuery query,CurrentUser user){
		ResultDatas<List<Map<String, Object>>> result=new ResultDatas<>();
		try {
			ColColumn col=new ColColumn();
			col.setStatus("20");
			if(user!=null){
				col.setExt1(user.getMemberId()+"");
			}
			SimplePageInfo pageInfo = new SimplePageInfo();
			pageInfo.setPageNum(query.getPageNum());
			pageInfo.setPageSize(query.getPageSize());
			Page<Map<String, Object>> pageList =colColumnService.queryColumnList(BeanConvertUtils.beanToMap(col),pageInfo);
			for (int i = 0; i < pageList.getResult().size(); i++) {
				String id = (String)pageList.getResult().get(i).get("id");
				Info info=new Info();
				info.setBizId(id);
				info.setArticleType("10");
				info.setStatus("30");
				List<Map<String,Object>> datas=infoService.findByBizId(info);
				pageList.getResult().get(i).put("infoList", datas);
			}
			result.setDatas(pageList);
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
			result.setPageNum(pageList.getPageNum());
			result.setPageSize(pageList.getPageSize());
			result.setPageCount(pageList.getPages());
			result.setTotal(pageList.getTotal());
			return result;
		} catch (Exception e) {
			result.setMessage("查询失败"+e);
			result.setStatus(Result.EEROR);
			return result;
		}
	}
	/**
	 * 
	 * 文章列表接口
	 * @param query
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value=URLMapping.COLCLUMN_INFO_LIST,method = RequestMethod.POST)
	public ResultDatas<List<Map<String, Object>>> infoList(@RequestBody ColColumnQuery query){
		ResultDatas<List<Map<String, Object>>> result=new ResultDatas<>();
		try {
			if(StringUtils.isNotEmpty(query.getId())){
				Info info=new Info();
				info.setBizId(query.getId());
				info.setArticleType("10");
				info.setStatus("30");
				SimplePageInfo pageInfo = new SimplePageInfo();
				pageInfo.setPageNum(query.getPageNum());
				pageInfo.setPageSize(query.getPageSize());
				Page<Map<String, Object>> pageList =infoService.queryInfoListByBizId(BeanConvertUtils.beanToMap(info),pageInfo);
				result.setDatas(pageList);
				result.setStatus(Result.SUCCESS);
				result.setMessage("成功");
				result.setPageNum(pageList.getPageNum());
				result.setPageSize(pageList.getPageSize());
				result.setPageCount(pageList.getPages());
				result.setTotal(pageList.getTotal());
			}else{
				result.setMessage("专栏id为空");
				result.setStatus(Result.EEROR);
			}
			return result;
		} catch (Exception e) {
			result.setMessage("查询失败"+e);
			result.setStatus(Result.EEROR);
			return result;
		}
	}
	
	/**
	 * 页面启动
	 */
	@RequestMapping(value = "/om/columnManagement/columnManagement.htm")
	public void init(CurrentUser user, ModelMap modelMap) {
		int countToAudit;//待审核 10
		int countApproved;//审核通过 20
		int countAuditFailed;//审核未通过 01
		Map<String, Object> mapToAudit = new HashMap<>();
		mapToAudit.put(MAP_KEY, "10");
		countToAudit = colColumnService.countRecords(mapToAudit);
		modelMap.addAttribute("countToAudit", countToAudit);
		
		Map<String, Object> mapApproved = new HashMap<>();
		mapApproved.put(MAP_KEY, "20");
		countApproved = colColumnService.countRecords(mapApproved);
		modelMap.addAttribute("countApproved", countApproved);
		
		Map<String, Object> mapAuditFailed = new HashMap<>();
		mapAuditFailed.put(MAP_KEY, "01");
		countAuditFailed = colColumnService.countRecords(mapAuditFailed);
		modelMap.addAttribute("countAuditFailed", countAuditFailed);
	}
	
	//列表数据
	@RightAccess(1011)
	@RequestMapping(value = URLMapping.COLUMNMANAGEMENT_LIST,method = RequestMethod.POST)
	@ResponseBody
	@WithoutAccess
	public ResultDatas<List<ColColumnRest>> queryColumnManagementList(@RequestBody ColColumnVO query) {

		ResultDatas<List<ColColumnRest>> result = new ResultDatas<>();
		// return false if no condition info   ColColumnRest
		//query.set
		if (null == query) {
			result.setFail("");
			return result;
		}
		//待审核
		if(query.getStatus() == null){
			query.setStatus("10");
		}

		SimplePageInfo pageInfo = new SimplePageInfo();
		if(query.getPageNum() == null){
			pageInfo.setPageNum(1);
		}else{
			pageInfo.setPageNum(query.getPageNum());
		}
		if(query.getPageSize() == null){
			pageInfo.setPageSize(10);
		}else{
			pageInfo.setPageSize(query.getPageSize());
		}
		
		ColColumn queryEntity = new ColColumn();
		queryEntity.setStatus(query.getStatus());
		queryEntity.setColumnClassifyName(query.getColumnClassifyName());
		try {
			Page<Map<String, Object>> datas = colColumnService.queryRecords(BeanConvertUtils.beanToMap(query), pageInfo);

			result.setPageNum(datas.getPageNum());
			result.setPageSize(datas.getPageSize());
			result.setPageCount(datas.getPages());
			result.setTotal(datas.getTotal());

			result.setDatas(BeanConvertUtils.mapToBeanInList(datas,ColColumnRest.class));
			return result;
		} catch (Exception e) {
			result.setFail(e.toString());
			return result;
		}
		
	}
	
	
	/**
	 * OM专栏 查看页面启动
	 */
	@RequestMapping(value = "/om/columnManagement/columnDetail.htm")
	public void initCheck(CurrentUser user, ModelMap modelMap){
		// Do nothing because of X and Y.

	}
	
	//查看
	@RequestMapping(value = URLMapping.CHECK_COLUMN)
	@ResponseBody
	public ResultDatas<ColColumnRest> checkColumn(@RequestBody ColColumnVO query){
		ResultDatas<ColColumnRest> result = new ResultDatas<>();
		// return false if no condition info  
		if (null == query) {
			result.setFail("");
			return result;
		}
		return result;
	}
	
	/**
	 * OM专栏审核页面启动
	 */
	@RequestMapping(value = "/om/columnManagement/reviewColumn.htm")
	public void initReview(CurrentUser user, ModelMap modelMap){
		//Do nothing because of X and Y
	}
	
	//审核   
	@RightAccess(1012)
	@RequestMapping(value = URLMapping.REVIEW_COLUMN)
	@ResponseBody
	public ResultDatas<ColColumnRest> reviewColumn(@RequestBody ColColumnVO query) {

		ResultDatas<ColColumnRest> result = new ResultDatas<>();
		// return false if no condition info  
		//query.set
		if (null == query) {
			result.setFail("");
			return result;
		}
		//根据ID查询 
		String id = query.getId();
		ColColumn column = colColumnService.findByPrimaryKey(id);
		
		ColColumnRest colColumnRest = new ColColumnRest();
		
		colColumnRest.setColumnClassifyName(column.getColumnClassifyName());
		colColumnRest.setColumnTitle(column.getColumnTitle());
		colColumnRest.setId(column.getId());
		colColumnRest.setAuthorName(column.getAuthorName());
		colColumnRest.setColumnBrief(column.getColumnBrief());
		colColumnRest.setStatus(column.getStatus());
		colColumnRest.setArticleCount(column.getArticleCount());
		colColumnRest.setSubscribeCount(column.getSubscribeCount());
		colColumnRest.setColumnClassify(column.getColumnClassify());
		colColumnRest.setColumnCover(column.getColumnCover());
		colColumnRest.setExamineNoDesc(column.getExamineNoDesc());
		result.setDatas(colColumnRest);
		return result;
	}
	
	//审核通过 20  审核不通过01
	@RequestMapping(value = URLMapping.UPDATE_COLUMN)
	@ResponseBody
	@WithoutAccess
	public Result updateColumn(@RequestBody ColColumnVO query) {
		Result result = new Result();
		if (null == query) {
			result.setFail("参数不能为空");
			return result;
		}
		
		try {
			colColumnService.updateRecord(query);
		} catch (Exception e) {
			result.setFail("审核失败");
		}
		return result;
	}
	
	/**
	 * 文章删除
	 */
	/*@RightAccess(1028)*/
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value = "/column/delInfo.json")
	public Result delInfo(@RequestParam(value = "uuid") String uuid,CurrentUser user) {
		Result res = new Result();

		// 判断uuid是否为空
		if (StringUtils.isBlank(uuid)) {
			res.setStatus(Result.EEROR);
			res.setMessage("uuid为空，不能进行删除操作");
			return res;
		}
		Info info = infoService.findByUuid(uuid);
		if(info == null){
			res.setFail("文章为空");
			return res;
		}
		info.setUpdateUser(user.getName());
		try {
			if (infoService.delete(info)) {
				res.setStatus(Result.SUCCESS);
				res.setMessage("已成功删除");
			} else {
				res.setStatus(Result.EEROR);
				res.setMessage("删除失败");
			}
		} catch (Exception e) {
			log.error(e);
			res.setStatus(Result.EEROR);
			res.setMessage(e.getMessage());
		}
		return res;
	}
	
}
