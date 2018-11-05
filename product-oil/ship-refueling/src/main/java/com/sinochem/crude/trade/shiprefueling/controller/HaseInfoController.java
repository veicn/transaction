package com.sinochem.crude.trade.shiprefueling.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.constants.UrlMapping;
import com.sinochem.crude.trade.shiprefueling.controller.common.*;
import com.sinochem.crude.trade.shiprefueling.domain.po.HaseInfo;
import com.sinochem.crude.trade.shiprefueling.enums.ExceptionEnum;
import com.sinochem.crude.trade.shiprefueling.helper.ExceptionHelper;
import com.sinochem.crude.trade.shiprefueling.model.query.HaseInfoQuery;
import com.sinochem.crude.trade.shiprefueling.model.vo.HaseInfoVO;
import com.sinochem.crude.trade.shiprefueling.service.ChmentsService;
import com.sinochem.crude.trade.shiprefueling.service.HaseInfoService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * 采购信息控制前
 * 
 * @author niuwk
 *
 */
@Controller
@WithoutAccess
public class HaseInfoController  {

	@Autowired
	private HaseInfoService haseInfoService;
	@Autowired
	private ChmentsService chmentsService;
	public static final Log log = LogFactory.getLog(HaseInfoController.class);

	@Autowired
	private ExceptionHelper exceptionHelper;
	/**
	 * 添加采购信息
	 * @param haseInfo
	 * @return
	 */
    @RequestMapping(value = UrlMapping.HASEINFO_INSERT, method = POST)
    @ResponseBody
    public ResultDatas<Object> insertHaseInfo(@RequestBody HaseInfoVO haseInfo,CurrentUser user) {
        ResultDatas<Object> res = new ResultDatas<Object>();
		BizException bizException = new BizException();
        try {
        	if(user == null){
				bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
				throw bizException;
			}
			if(haseInfo.getEpMemberId() == null || com.eyeieye.melody.util.StringUtil.isBlank(haseInfo.getEpMemberName())){
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setMessage("公司名称不合法");
				return res;
			}
        	haseInfo.setCreateUser(user.getMemberId());
        	int i = haseInfoService.insertHaseInfoRecord(haseInfo);
        	if(i==0){
        		res.setStatus(Constants.EXCEPTION_STATUS);
        	}
        } catch (Exception e) {
        	res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());

        }
        return res;
    }
    
	/**
	 * 修改采购信息
	 * @param haseInfo
	 * @return
	 */
    @RequestMapping(value = UrlMapping.HASEINFO_MODIFY, method = POST)
    @ResponseBody
    public ResultDatas<Object> modifyHaseInfo(@RequestBody HaseInfoVO haseInfo,CurrentUser user) {
        ResultDatas<Object> res = new ResultDatas<Object>();
        try {
        	haseInfo.setUpdateUser(user.getEpMemberId());
        	int i = haseInfoService.updateHaseInfoRecordByUuid(haseInfo);
        	if(i==0){
        		res.setStatus(Constants.EXCEPTION_STATUS);
        	}
        } catch (Exception e) {
        	res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
            
        }
        return res;
    }
    
	/**
	 * 删除采购信息
	 * @param haseInfo
	 * @return
	 */
    @RequestMapping(value = UrlMapping.HASEINFO_DELETE, method = POST)
    @ResponseBody
    public ResultDatas<Object> deleteHaseInfo(@RequestBody HaseInfoVO haseInfo,CurrentUser user) {
        ResultDatas<Object> res = new ResultDatas<Object>();
		BizException bizException = new BizException();
        try {
        	if(user == null || StringUtil.isBlank(haseInfo.getUuid())){
				bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
				throw bizException;
			}

        	int i = haseInfoService.deleteRecordByUuid(haseInfo.getUuid());
        	if(i==0){
        		res.setMessage(Constants.DELE_FALSE);
        		res.setStatus(Constants.EXCEPTION_STATUS);
        	}
        } catch (Exception e) {
        	res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
            
        }
        return res;
    }
    
	/**
	 * 查询采购信息
	 * @param haseInfo
	 * @return
	 */
    @RequestMapping(value = UrlMapping.HASEINFO_GET, method = POST)
    @ResponseBody
    public ResultDatas<Object> getHaseInfo(@RequestBody HaseInfoVO haseInfo,CurrentUser user) {
        ResultDatas<Object> res = new ResultDatas<Object>();
        HaseInfo info = null;
        try {
        	 info = haseInfoService.findByUuid(haseInfo.getUuid());
        	 res.setDatas(info);
        } catch (Exception e) {
        	res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
            
        }
        return res;
    }


	/**
	 * <p>
	 *   分页查询，默认按发布时间倒序排列。<p/>
	 *
	 * @param haseInfo
	 * epMemberName			选填		String		采购公司名称
	 * oilVarieties			选填		String		规格
	 * issueTimeStart		选填		String		搜索开始时间
	 * issueTimeEnd			选填		String		搜索结束时间
	 * oilClassification	选填		String		油品类型
	 * personal          	必填   	String  	是否个人中心  1：个人中心   0：油品大厅
	 * status			 	必填   	String  	采购状态  1：已发布  2：已确认 3：已下架
	 * <p>
	 *     personal 如果personal="1" 则搜索条件加上当前用户的epMemberId设置，如果不为"1"查询全部用户的信息。
	 *
	 *     status：设置当前要查询采购信息的状态,设置条件如下:
	 *     <ul>
	 *     <li>""或null:查询所有状态
	 *     <li>"1,2":查询已发布,已确认
	 *     <li>"1,3"：查询已发布,已下架
	 *     <li>"2,3"：查询已确认，已下架
	 *     </ul>
	 * </p>
	 *
	 * @param user
	 *
	 * @return
	 */
	@RequestMapping(value = UrlMapping.HASEINFO_LIST, method = POST)
    @ResponseBody
    public ResultDatas<List<Map<String,Object>>> getHaseInfoList(@RequestBody HaseInfoVO haseInfo,CurrentUser user) {
    	ResultDatas<List<Map<String,Object>>> res=new ResultDatas<>();
        try {
        	SimplePageInfo pageInfo = haseInfo.getPageInfo();

			if(Constants.PERSONAL_OK.equals(haseInfo.getPersonal())){
				haseInfo.setEpMemberId(user.getEpMemberId());
			}
        	Page<Map<String, Object>> page = haseInfoService.queryHaseInfoList(haseInfo,pageInfo);
        	res.setDatas(page);
 			res.setPageNum(page.getPageNum());
 			res.setPageSize(page.getPageSize());
 			res.setTotal(page.getTotal());
 			res.setPageCount(page.getPages());
        } catch (Exception e) {
        	res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
            
        }
        return res;
    }




	//region 前台  查询采购信息列表-分页查询-武刚鹏-2018年5月28日17:43:19
	/**
	 * 前台  查询采购信息列表-分页查询-武刚鹏-2018年5月28日17:43:19
	 * @param haseInfoQuery
	 * @return
	 */
	@RequestMapping(value = "selectHaseInfoList.json",method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas selectHaseInfoList(@RequestBody HaseInfoQuery haseInfoQuery){

		ResultDatas result = new ResultDatas();
		result.setStatus(Constants.EXCEPTION_STATUS);
		result.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
		try{
			if(haseInfoQuery == null){
				BizException bizException = new BizException();
				bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
				throw bizException;
			}
			PageInfo<HaseInfoVO> pageInfo  =  haseInfoService.queryFrontHaseInfoList(haseInfoQuery);
			result.setPageNum(pageInfo.getPageNum());
			result.setPageSize(pageInfo.getPageSize());
			result.setTotal(pageInfo.getTotal());
			result.setPageCount(pageInfo.getPages());
			result.setDatas(pageInfo.getList());
			result.setStatus(Constants.SAVE_SUCCESS);
			result.setMessage(Constants.SUCCESS_MESSAGE);
		} catch (Exception e){
			log.error("分页查询采购单列表失败",e);
		}
        return result;
	}
	//endregion


	//region 前台——查询最新的采购信息-武刚鹏-2018年5月28日17:43:19
	/**
	 * 前台——查询最新的采购信息-武刚鹏-2018年5月28日17:43:19
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "selectNewHaseInfoList.json",method = RequestMethod.GET)
	@ResponseBody
	public ResultDatas selectNewHaseInfoList(int size){

		ResultDatas result = new ResultDatas();
		result.setStatus(Constants.EXCEPTION_STATUS);
		result.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
		try{
			List<HaseInfoVO> haseInfoVOList = haseInfoService.selectNewHashInfoList(size);
			result.setDatas(haseInfoVOList);
			result.setStatus(Constants.SAVE_SUCCESS);
			result.setMessage(Constants.SUCCESS_MESSAGE);
		} catch (Exception e){
			log.error("查询最新的采购记录失败",e);
		}finally {
			return result;
		}
	}
	//endregion


	//region 前台——查询单个采购的详细信息-武刚鹏-2018年5月28日17:43:19
	/**
	 * 前台——查询单个采购的详细信息-武刚鹏-2018年5月28日17:43:19
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value = "selectHaseInfo.json",method = RequestMethod.GET)
	@ResponseBody
	public ResultDatas selectHaseInfo(String uuid){
		ResultDatas result = new ResultDatas();
		try{
			HaseInfoVO haseInfoVO = haseInfoService.findVoByUuid(uuid);
			result.setDatas(haseInfoVO);
			result.setStatus(Constants.SAVE_SUCCESS);
			result.setMessage(Constants.SUCCESS_MESSAGE);
		} catch (Exception e){
			log.error("查询采购的详细信息",e);
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
			return result;
		}
		return result;
	}
	//endregion

	@RequestMapping(value = "selectMetaHaseInfo.json",method = RequestMethod.GET)
	@ResponseBody
	public ResultDatas selectMetaHasInfo(String uuid){
		ResultDatas result = new ResultDatas();
		if(StringUtil.isBlank(uuid)){
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.NULL_PARAMETER).getZhName());
		}
		try{
			HaseInfoVO haseInfoVO = haseInfoService.findMetaVoByUuid(uuid);
			result.setDatas(haseInfoVO);
			result.setStatus(Constants.SAVE_SUCCESS);
			result.setMessage(Constants.EXCEPTION_MESSAGE);
		}catch (Exception e){
			result.setStatus(Constants.EXCEPTION_STATUS);
			result.setMessage(Constants.EXCEPTION_MESSAGE);
			return result;
		}
		return result;
	}


	/**
	 * 前台——根据条件查询采购信息列表
	 * @param haseInfoVO
	 * @return
	 */
	@RequestMapping(value = "selectSellHaseInfoListByVo.json",method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas selectSellHaseInfoListByVo(@RequestBody HaseInfoVO haseInfoVO){
		ResultDatas result = new ResultDatas();
		result.setStatus(Constants.EXCEPTION_STATUS);
		result.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
		try{
			HaseInfo haseInfo = new HaseInfo();
			BeanUtils.copyProperties(haseInfoVO,haseInfo);
			List<HaseInfo>  list = haseInfoService.queryByEntitys(haseInfo);
			List<HaseInfoVO>  voList = null;
			if(list != null && list.size()>0){
				voList = new ArrayList<>(list.size());
				for (HaseInfo haseInfoOfList:list) {
					HaseInfoVO vo = new HaseInfoVO();
					vo.domainToVo(haseInfoOfList);
					voList.add(haseInfoVO);
				}
			}
			result.setDatas(voList);
			result.setStatus(Constants.SAVE_SUCCESS);
			result.setMessage(Constants.SUCCESS_MESSAGE);
		} catch (Exception e){
			log.error("根据条件查询采购信息列表异常",e);
		}finally {
			return result;
		}
	}


	@RequestMapping(value = "/selectSellHaseInfoStatusList.json" , method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas statusList(){
		ResultDatas res = new ResultDatas();
		Map<String , String> statusMap = Maps.newHashMap();
		statusMap.put(Constants.QUOTATION_STATUS_NOT_CONNECTED , "未联系");
		statusMap.put(Constants.QUOTATION_STATUS_CONNECTED , "已联系");
		statusMap.put(Constants.QUOTATION_STATUS_DONE , "已成交");
		statusMap.put(Constants.QUOTATION_STATUS_VALID , "有效");
		statusMap.put(Constants.QUOTATION_STATUS_INVALID , "无效");
		res.setDatas(statusMap);
		return res;
	}


//	updateRecordByUuid

	@RequestMapping(value = "/haseinfo/downGoodsShelves.json" , method = RequestMethod.POST)
	@ResponseBody
	public ResultDatas DownGoodsShelves(@RequestBody HaseInfo info){
		ResultDatas res = new ResultDatas();
		try{
			info.setStatus(HaseInfo.STATUS_DELETED);
			int count = haseInfoService.updateRecordByUuid(info);
			if(count < 1){
				res.setStatus(Result.EEROR);
				res.setMessage(Constants.MODIFY_FALSE);
				return res;
			}
		}catch (BizException e){
			res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
			return res;
		}
		res.setStatus(Result.SUCCESS);
		res.setMessage(Constants.MODIFY_TRU);
		return res;
	}
}
