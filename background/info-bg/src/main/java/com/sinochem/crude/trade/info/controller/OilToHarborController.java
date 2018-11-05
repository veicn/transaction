package com.sinochem.crude.trade.info.controller;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
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
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.info.domain.OilToHarbor;
import com.sinochem.crude.trade.info.model.OilToHarborVO;
import com.sinochem.crude.trade.info.query.HarborQuery;
import com.sinochem.crude.trade.info.service.OilToHarborService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.operation.vo.OilToHarborQuery;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;


@Controller
public class OilToHarborController  {

	@Autowired
	private OilToHarborService oilToHarborService;
	
	public static final Log log = LogFactory.getLog(OilToHarborController.class);
	/**
	 * 列表
	 * @return
	 */
	@RightAccess(1178)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value="om/harbor/oilToHarbor.htm")
	public void oilToHarbor(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "apage", required = false) Integer pageAgain, ModelMap model, OilToHarborQuery query){
		if (pageAgain != null) {
			query.setCurrentPage(pageAgain);
		} else {
			query.setCurrentPage(page);
		}
		SimplePageInfo pageInfo = new SimplePageInfo();
		pageInfo.setPageNum(query.getCurrentPage());
		pageInfo.setPageSize(query.getPageSize());
		Page<Map<String, Object>> pageList = oilToHarborService.queryRecords(BeanConvertUtils.beanToMap(query),
				pageInfo);
		query.setTotalItem(pageList.getTotal());
		model.put("datas", pageList.getResult());
		model.put("query", query);
	}
	/**
	 * 新增或更新
	 * @return
	 */
	@RightAccess(1180)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value="/harbor/saveOrUpdate.json",method=RequestMethod.POST)
	public Result saveOrUpdate(@RequestBody OilToHarborVO vo,CurrentUser user) {
		Result res = new Result();
		res.setMessage("新增或修改成功");
		try {
			OilToHarbor oil = new OilToHarbor();
			BeanUtils.copyProperties(vo, oil);
			return oilToHarborService.saveOrUpdate(oil,user);
		}catch (BizException e) {
			log.error("新增或修改失败", e);
			res.setFail(e.getMessage());
		}
		return res;
	}
	
	/**
	 * 删除
	 */
	@RightAccess(1184)
	@RolesAccess({"admin","info_oper"})
	@ResponseBody
	@RequestMapping(value="/harbor/deleteHarbor.json",method = RequestMethod.GET)
	public Result deleteHarbor(@RequestParam(value="id") Long id) throws BizException{
		
		
		Result result = new Result();
		
		try {
			result.setStatus(Result.SUCCESS);
			oilToHarborService.deleteById(id);
		} catch (Exception e) {
			result.setStatus(Result.EEROR);
			result.setMessage("删除失败");
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
	@RightAccess(1182)
	@RolesAccess({"admin","info_oper"})
	@RequestMapping(value = "/harbor/importExcel.json", method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas<Void> importExcel(CurrentUser user, HttpServletRequest request) {
		ResultDatas<Void> res = new ResultDatas<Void>();
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multiRequest.getFile("file");
		try {
			String fileName = file.getOriginalFilename();
			if(!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")){
				res.setMessage("文件格式不正确，请选择excel");
				res.setStatus(Result.EEROR);
				return res;
			}

			InputStream inputStream = file.getInputStream();
			res.setMessage("上传成功");
			res = oilToHarborService.importExcel(inputStream,user,res);
			
		} catch (Exception e) {
			res.setMessage(e.getMessage());
			log.error(e);
		}
		
		return res;
	}
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value = "/harbor/queryHarbor.json",method=RequestMethod.POST)
	public ResultDatas<List<Map<String, Object>>> queryHarbor(
			@RequestBody HarborQuery harborQuery) throws BizException{
		ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
		SimplePageInfo pageInfo = harborQuery;
		Map<String, Object> param = BeanConvertUtils.beanToMap(harborQuery);
		Page<Map<String, Object>> pageList = oilToHarborService.queryRecords(param, pageInfo);
		res.setDatas(pageList);
		res.setStatus(Result.SUCCESS);
		res.setMessage("成功");
		res.setPageNum(pageList.getPageNum());
		res.setPageSize(pageList.getPageSize());
		res.setPageCount(pageList.getPages());
		res.setTotal(pageList.getTotal());
		return res;
	}
}
