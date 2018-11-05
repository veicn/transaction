package com.sinochem.crude.trade.info.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.common.SimplePageInfo;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.info.model.CrudeVO;
import com.sinochem.crude.trade.info.query.CrudeQuery;
import com.sinochem.crude.trade.info.service.OilService;
import com.sinochem.crude.trade.info.service.QualityService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;

/**
 * admin - 系统管理员
 * info_oper - 内容维护人员
 */
@Controller
public class CrudeController  {

	@Autowired
	private QualityService qualityService;
	@Autowired
	private OilService oilService;
	
	Log log = LogFactory.getLog(CrudeController.class);
	
	/**
	 * 原油品质查询
	 */
	@RightAccess(1106)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = "/om/crudeOil/crudeOil.htm")
	public void selectListCrudeOil(ModelMap model, CrudeQuery query,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain)throws BizException{
		
		Result result = new Result();
		
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}	
		// 查询条件

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oilName", query.getOilName());
		map.put("oilPlace", query.getOilPlace());
		map.put("oilArea", query.getOilArea());
		/*map.put("oilApi", query.getOilApi());*/
		/*map.put("oilSulphur", query.getOilSulphur());*/
		/*map.put("oilAcidity", query.getOilAcidity());*/
		map.put("dateStart", query.getDateStart());
		map.put("dateEnd", query.getDateEnd());
		map.put("oilAcidityMin", query.getDateEnd());
		map.put("oilAcidityMax", query.getDateEnd());
		map.put("oilSulphurMin", query.getDateEnd());
		map.put("oilSulphurMax", query.getDateEnd());
		map.put("oilApiMin", query.getDateEnd());
		map.put("oilApiMax", query.getDateEnd());

		// 分页条件
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageSize(query.getPageSize());
		pageInfo.setPageNum(query.getCurrentPage());
		try {
			// 查询
			Page<Map<String, Object>> datas = qualityService.crudeRecords(map, pageInfo);
			query.setTotalItem(datas.getTotal());
			model.put("crudes", datas.getResult());
			model.put("query", query);
			
		} catch (Exception e) {
			result.setStatus(4);
			result.setMessage("查询失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 原油品质新增查询
	 */

	@RequestMapping(value="/om/crudeOilAdd/crudeOilAdd.htm")
	public void addListCrudeOil(ModelMap model, CrudeQuery query,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain)throws BizException{
		
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		
		Result result = new Result();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("crudeNameE", query.getCrudeNameE());
		map.put("crudeNameC", query.getCrudeNameC());
		// 分页
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageSize(query.getPageSize());
		pageInfo.setPageNum(query.getCurrentPage());
		
		try {
			Page<Map<String, Object>> datas = oilService.addQueryRecords(map, pageInfo);
			query.setTotalItem(datas.getTotal());
			model.put("crudeAdds", datas.getResult());
			model.put("query", query);
			result.setTotal(datas.getTotal());
			result.setPageCount(datas.getPages());
		} catch (Exception e) {
			result.setStatus(9);
			result.setMessage("新增查询失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 原油增加
	 */
	@RequestMapping(value="/om/crudeOilInsert/crudeOilInsert.htm")
	public void insertCrudeOilDemo(CrudeQuery query) throws BizException{
		
		
		
	}
	
	
	/**
	 * 原油增加
	 */
	@RightAccess(1107)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value="/crudeOilInsert/crudeOilInsertAdd.json",method = RequestMethod.POST)
	public Result insertCrudeOil(@RequestBody CrudeVO vo,CurrentUser user) throws BizException{
		
		Result result = new Result();
		
		if("".equals(vo.getUuid())){
			try {
				vo.setCreatePerson(user.getName());
				vo.setModifyPerson(user.getName());
				qualityService.crudeInsert(vo);
			} catch (Exception e) {
				result.setStatus(9);
				result.setMessage("新增失败");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				qualityService.crudeUpdate(vo);
			} catch (Exception e) {
				result.setStatus(4);
				result.setMessage("编辑失败");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	/**
	 * 原油品质删除
	 */
	@RightAccess(1110)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value="/crudeOil/crudeOilDelete.json",method = RequestMethod.GET)
	public Result deleteCrudeOil(CrudeQuery query) throws BizException{
		
		Result result = new Result();
		
		try {
			qualityService.crudeDelete(query.getUuid());	
		} catch (Exception e) {
			result.setStatus(9);
			result.setMessage("删除失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}	
	
	/**
	 * 原油修改
	 */
	@ResponseBody
	@RequestMapping(value="/crudeOil/crudeOilUpdate.json",method = RequestMethod.POST)
	public Result updateCrudeOil(@RequestBody CrudeVO vo) throws BizException{
		
		Result result = new Result();
		
		try {
			qualityService.crudeUpdate(vo);
		} catch (Exception e) {
			result.setStatus(4);
			result.setMessage("编辑失败");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	/**
	 * 批量导入
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/om/crudeOil/crudeOilImport.json", method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<Void> crudeOilImport(CurrentUser user, HttpServletRequest request) {
		ResultDatas<Void> res = new ResultDatas<Void>();
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multiRequest.getFile("file");
		try {
			String fileName = file.getOriginalFilename();
			if(!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")){
				res.setMessage("导入失败");
			}
			InputStream inputStream = file.getInputStream();
			res = qualityService.crudeOilImport(inputStream,user);
			
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			log.error(e);
		}
		
		return res;
	}
	/**
     * 导出excel 文件
     * @param response
     */
    @RequestMapping(value = "/om/crudeOil/exportList.htm")
    public void exportList(HttpServletResponse response){
    	qualityService.exportList(response);
 	}
	
}
