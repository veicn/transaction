package com.sinochem.crude.trade.shiprefueling.controller;

import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.constants.UrlMapping;
import com.sinochem.crude.trade.shiprefueling.controller.common.*;
import com.sinochem.crude.trade.shiprefueling.domain.po.Chments;
import com.sinochem.crude.trade.shiprefueling.domain.po.Info;
import com.sinochem.crude.trade.shiprefueling.domain.po.IryQuotation;
import com.sinochem.crude.trade.shiprefueling.enums.ExceptionEnum;
import com.sinochem.crude.trade.shiprefueling.helper.ExceptionHelper;
import com.sinochem.crude.trade.shiprefueling.model.query.InfoQuery;
import com.sinochem.crude.trade.shiprefueling.model.query.IryQuotationQuery;
import com.sinochem.crude.trade.shiprefueling.model.vo.ChmentsVO;
import com.sinochem.crude.trade.shiprefueling.model.vo.InfoVO;
import com.sinochem.crude.trade.shiprefueling.model.vo.IryQuotationVO;
import com.sinochem.crude.trade.shiprefueling.service.ChmentsService;
import com.sinochem.crude.trade.shiprefueling.service.InfoService;
import com.sinochem.crude.trade.shiprefueling.service.BuyShipDictionaryService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.WithoutAccess;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;

import java.util.*;


@Controller
@WithoutAccess
@RequestMapping(UrlMapping.WAB_INFO)
public class InfoController  {

	@Autowired
	private InfoService infoService;

	@Autowired
	private BuyShipDictionaryService shipDictionaryService;

	@Autowired
    private ExceptionHelper exceptionHelper;

	@Autowired
    private ChmentsService chmentsService;

    public static final Log log = LogFactory.getLog(InfoController.class);

	/**
	 *  后台销售列表分页查询
	 * <p>
	 *   此接口为后端销售列表分页查询，目前前端也有调用。
	 *
	 * </p>
	 *
	 * @param infoQuery
	 * epMemberName      选填   公司名称
	 * oilVarieties      选填   油品规格
	 * oilClassification 选填   油品类型
	 * locationProvince  选填   地区省
	 * locationCity   	 选填   地区市
	 * releaseBeginDate  选填   开始日期
	 * releaseEndDate    选填   结束日期
	 * deliveryWay       选填   //TODO:运输方式
	 * status			 必填   1：已发布  2：已确认 3：已下架
	 *
	 * <br/>
	 * SimplePageInfo pageInfo : 分页参数信息
	 * pageNum:     选填     页码      默认   1
	 * pageSize:    选填     每页数量   默认  10
	 *
	 * <p>
	 * status字符串这里传递的格式为状态加英文格式的","隔开.Example：
	 * <ul>
	 * <li>"" :查询全部
	 * <li>"1,2" : "已发布"，"已确认",
	 * <li>"1,3"："已发布","已下架",
	 * <li>"2,3"："已确认"，"已下架"
	 * </ul>
	 *</p>
	 * @param user  当前用户信息
	 * @return
	 */
	@RequestMapping(value = "selectALLOrder.json" , method = RequestMethod.POST)
    @ResponseBody
	public ResultDatas<List<Map<String,Object>>> selectALLOrder(HttpServletRequest req, CurrentUser user, @RequestBody InfoQuery infoQuery){
        ResultDatas res = new ResultDatas();
        BizException bizException = new BizException();
        try {
            if (null == user) {
                //没有权限
                bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
                throw  bizException;
            }

            // 根据当前用户查询当前用户所拥有的集合
            infoQuery.setAliveFlag(null);
            Page<Map<String, Object>> page = infoService.queryByUserId(infoQuery,infoQuery.getPageInfo());
            res.setDatas(page);
            res.setPageNum(page.getPageNum());
            res.setPageSize(page.getPageSize());
            res.setTotal(page.getTotal());
            res.setPageCount(page.getPages());
        }catch (BizException e) {
            int code = e.getCode();
            res.setStatus(code);
            res.setMessage(exceptionHelper.getBizExceptionByCode(code).getZhName());
            log.error("selectALLOrder",e);
        }catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
	        res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
            log.error("selectALLOrder",e);
        }
        return res;
    }

	/**
	 *  销售单详细信息查询。
	 * <p>
	 *     个人中心和后台均在调用
	 *     </br>
	 *
	 *     此接口返回销售信息infoVO 以及销售厂家信息enterprise
	 * </p>
	 *
	 * @param infoParamter
	 *
	 * uuid   必填   销售单UUID
	 *
	 * @return  ResultDatas
	 */
    @RequestMapping(value = "detailsInfo.json")
    @ResponseBody
    public ResultDatas detailsInfo(CurrentUser user, @RequestBody InfoVO infoParamter){
        ResultDatas res = new ResultDatas();
        Map<String , Object> result = Maps.newHashMap();
        BizException bizException = new BizException();
        try {
            if (null == user) {
                bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
                throw  bizException;
            }
            if (StringUtil.isBlank(infoParamter.getUuid())) {
                throw new BizException("uuid 不能为空!");
            }
            result = infoService.getInfoEpDetail(infoParamter.getUuid());
            res.setDatas(result);
        }catch (BizException e) {
            int code = e.getCode();
            res.setStatus(code);
            res.setMessage(exceptionHelper.getBizExceptionByCode(code).getZhName());
            log.error("detailsInfo",e);
        }catch (Exception e){
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
            log.error("detailsInfo",e);
        }
        return res;
    }


    /**
     * 销售详情修改/新增
	 *<p>
	 *
	 *   当UUID为不存在时新增数据，UUID存在时更新当前数据。
	 *
	 *</p>
	 * @param vo
	 * uuid					选填    String   UUID
	 * oilClassification	必填		String	油品分类
	 * oilVarieties			必填		String	品种
	 * deliveryWay			必填		String	交货方式(1:送供 2:自提)
	 * unitPrice			必填		String	单价
	 * locationProvince		必填		String	省份
	 * locationCity			必填		String	区
	 * detailedDescription	必填		String	详细描述
	 * infoTitle			必填		String	信息标题
	 * infoType				必填		String	信息类型(1:船燃销售 2:船舶加油（内贸）3:船舶加油（保税）9:原料油)
	 * releasedDate			必填		String	发布日期（"2018-09-12"）
	 * chmentsVOList		必填		List<ChmentsVO>	附件列表信息
	 *
	 * {@link ChmentsVO} 参数信息
	 * attachmentId         选填     long    附件标识ID
	 * url					选填     String  附件上传URL
	 *
	 * 对于附件信息的新增/修改逻辑:
	 * <ul>
	 * <li>如果id，path都不为空或都为空则不处理
	 * <li>如果id为空path不为空则新增
	 * <li>如果id不为空path为空则删除
	 * </ul>
     */
    @RequestMapping(value = "updateOrSaveInfo.json",method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas updateOrSaveInfo(CurrentUser user, @RequestBody InfoVO vo ){
        log.info(JSONObject.toJSONString(vo));
        ResultDatas res = new ResultDatas();
        BizException bizException = new BizException();
            try {
                if (null == user||null==vo) {
                    bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
                    throw bizException;
                }
                if(vo.getEpMemberId() == null || StringUtil.isBlank(vo.getEpMemberName())){
                    res.setStatus(Constants.EXCEPTION_STATUS);
                    res.setMessage("公司名称不合法");
                    return res;
                }
                if (null != vo) {
                        Info info = vo.voToInfo();
                    int code;
                    if (StringUtil.isNotBlank(vo.getUuid())) {
                        info.setUpdateUser(user.getMemberId());
                        code = infoService.updateRecordByUuid(info);
                        chmentsService.updateProductChments(vo.getChmentsVOList() , info.getInfoId() , user);
                    } else {
                         code = infoService.insertRecord(info , vo.getChmentsVOList() , user);
                    }

                    if (code>0) {
                        //成功
                        res.setStatus(Constants.SAVE_SUCCESS);
                    }
                }
            } catch (BizException e) {
                int code = e.getCode();
                res.setStatus(code);
                res.setMessage(exceptionHelper.getBizExceptionByCode(code).getZhName());
                log.error("updateInfoByuuid",e);
            } catch (Exception e) {
                res.setStatus(Constants.EXCEPTION_STATUS);
                res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
                log.error("updateInfoByuuid",e);
            }
        return res;
    }

    /**
     * 修改销售单状态
     * @param user
     * @param vo
     * @return
     */
    @RequestMapping(value="updateByuuidStatus.json",method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas updateByuuidStatus(CurrentUser user,InfoVO vo){
        ResultDatas res = new ResultDatas();
        BizException bizException = new BizException();
        try {

            if (null == user||null == vo||StringUtil.isBlank(vo.getUuid())){
                bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
                throw bizException;
            }
            Info info = vo.voToInfo();
            int code = infoService.updateByUuidStatus(info);

            if (code>0) {
                res.setStatus(Constants.SAVE_SUCCESS);
            }
        }catch (BizException e) {
            int code = e.getCode();
            res.setStatus(code);
            res.setMessage(exceptionHelper.getBizExceptionByCode(code).getZhName());
            log.error("updateByuuidStatus",e);
        }catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
            log.error("updateByuuidStatus",e);
        }
        return res;
    }


    @RequestMapping(value = "delete.json" , method = {RequestMethod.POST})
    @ResponseBody
    public ResultDatas deleteRecordByUuid(CurrentUser user , @RequestBody Info info){
        ResultDatas res = new ResultDatas();
        BizException bizException = new BizException();
        try{
            if(user == null || org.jsoup.helper.StringUtil.isBlank(info.getUuid())){
                bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
                throw bizException;
            }

            //逻辑删除销售详情和相关询价信息
            infoService.deleteInfoAndIqByUUid(info.getUuid());
        }catch (Exception e){
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }
        return res;
    }




    /**
     * 根据uuid删除销售订单
     * @param user
     * @param uuid
     * @return
     */
    @RequestMapping(value = "deleteByuuid.json",method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas deleteByuuid(CurrentUser user,String uuid){
        ResultDatas res = new ResultDatas();
        BizException bizException = new BizException();
        try {

            if (null == user||StringUtil.isBlank(uuid)){
                bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
                throw bizException;
            }
            int code = infoService.deleteByuuid(uuid);

            if (code>0) {
                res.setStatus(Constants.SAVE_SUCCESS);
            }
        }catch (BizException e) {
            int code = e.getCode();
            res.setStatus(code);
            res.setMessage(exceptionHelper.getBizExceptionByCode(code).getZhName());
            log.error("deleteByuuid",e);
        }catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
            log.error("deleteByuuid",e);
        }
        return res;
    }

	/**
	 * 根据销售单uuid 查询询价列表
	 * @param user
	 * @param iryQuotationQuery
	 * @return
	 */
    @RequestMapping(value = "selectBySaleInquiryAll")
    @ResponseBody
    public ResultDatas<List<Map<String,Object>>> selectBySaleInquiryAll(CurrentUser user,@RequestBody IryQuotationQuery iryQuotationQuery){
        ResultDatas res = new ResultDatas();
        BizException bizException = new BizException();
        JSONObject jsonObject = new JSONObject();
        try {

            if (null == user||null == iryQuotationQuery||StringUtil.isBlank(iryQuotationQuery.getUuid())){
                bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
                throw bizException;
            }
            //int code = infoService.deleteByuuid(uuid);
            //查询订单id
           // Info byUuid = infoService.findByUuid(uuid);
            //调用查询询价列表list  TODO
            iryQuotationQuery.setNeedUuid(iryQuotationQuery.getUuid());
            //获取pageinfo
            SimplePageInfo pageInfo = iryQuotationQuery.getPageInfo();

            Page<Map<String, Object>> page = infoService.findByidInquiry(iryQuotationQuery, pageInfo);
            jsonObject.put("page",page);
            res.setDatas(page);
            res.setPageNum(page.getPageNum());
            res.setPageSize(page.getPageSize());
            res.setTotal(page.getTotal());
            res.setPageCount(page.getPages());
        }catch (BizException e) {
            int code = e.getCode();
            res.setStatus(code);
            res.setMessage(exceptionHelper.getBizExceptionByCode(code).getZhName());
            log.error("selectBySaleInquiryAll",e);
        }catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
            log.error("selectBySaleInquiryAll",e);
        }
        return res;

    }

    /**
     * 询价表修改询价信息
     * @param user
     * @param vo
     * @return
     */
    @RequestMapping(value = "updateInquiryStatus.json",method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas updateInquiryStatus(CurrentUser user,IryQuotationVO vo) {
        ResultDatas res = new ResultDatas();
        BizException bizException = new BizException();
        try {

            if (null == user||null == vo||StringUtil.isBlank(vo.getUuid())){
                bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
                throw bizException;
            }
            //int code = infoService.deleteByuuid(uuid);
            switch (vo.getStatus()){
                case Constants.IRYQUOTATION_10:
                    vo.setStatus(Constants.IRYQUOTATION_10);break;
                case Constants.IRYQUOTATION_20:
                    vo.setStatus(Constants.IRYQUOTATION_20);break;
                case Constants.IRYQUOTATION_30:
                    vo.setStatus(Constants.IRYQUOTATION_30);break;
                case  Constants.IRYQUOTATION_40:
                    vo.setStatus(Constants.IRYQUOTATION_40);break;
                default:
                    bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
                    throw bizException;
            }
            IryQuotation iryQuotation = new IryQuotation();
            iryQuotation.setStatus(vo.getStatus());
            iryQuotation.setUuid(vo.getUuid());

           int code = infoService.updateByuuidInquiry(iryQuotation);
            if (code>0) {
                res.setStatus(Constants.SAVE_SUCCESS);
            }
        }catch (BizException e) {
            int code = e.getCode();
            res.setStatus(code);
            res.setMessage(exceptionHelper.getBizExceptionByCode(code).getZhName());
            log.error("deleteByuuid",e);
        }catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
            log.error("deleteByuuid",e);
        }
        return res;




    }

	/**
	 *  前台分页查询
	 *<p>
	 *   分页查询，默认按发布时间倒序排列。<br/>
	 *   此接口在两个地方调用:
	 *	</ul>
	 *  <li>前台个人中心销售列表查询
	 *  <li>油品大厅销售列表查询
	 *  <ul>
	 * </p>
	 *
	 * @param infoQuery
	 * epMemberName      选填   公司名称
	 * oilVarieties      选填   油品规格
	 * oilClassification 选填   油品类型
	 * locationProvince  选填   地区省
	 * releaseBeginDate  选填   开始日期
	 * releaseEndDate    选填   结束日期
	 * locationCity   	 选填   地区市
	 * transportWay      选填   运输方式
	 * personal          必填   1：个人中心   0：油品大厅
	 * status			 必填   1：已发布  2：已确认 3：已下架
	 *
	 * <br/>
	 * SimplePageInfo pageInfo : 分页参数信息
	 * pageNum:     选填     页码      默认   1
	 * pageSize:    选填     每页数量   默认  10
	 *
	 * @param user  当前用户信息
	 *
	 *
	 * <p>
	 *  status填写规则:
	 *  <ul>
	 *  <li>"":查询所有状态
	 *  <li>"1,2":查询已发布,已确认
	 *  <li>"1,3"：查询已发布,已下架
	 *  <li>"2,3"：查询已确认，已下架
	 *  <li>"3":查询已下架
	 *  </ul>
	 * </p>
	 *
	 * @return ResultDatas
	 */
    @RequestMapping(value = "selectSellInfoList.json",method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas selectSellInfoList(@RequestBody InfoQuery ifoQuery , CurrentUser user){
        ResultDatas result = new ResultDatas();
        result.setStatus(Constants.EXCEPTION_STATUS);
        result.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
        try{
            if(ifoQuery == null || ifoQuery.getPageInfo() == null){
                BizException bizException = new BizException();
                bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
                throw bizException;
            }
            if(user == null){
                BizException bizException = new BizException();
                bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
                throw bizException;
            }
            ifoQuery.setAliveFlag(Constants.ALIEVE_FLAG_YES);
            if(Constants.PERSONAL_OK.equals(ifoQuery.getPersonal()))ifoQuery.setEpMemberId(user.getEpMemberId());
            Page<Map<String,Object>> map  =  infoService.queryByUserId(ifoQuery,ifoQuery.getPageInfo());
            result.setDatas(map);
            result.setTotal(map.getTotal());
            result.setPageSize(map.getPageSize());
            result.setPageNum(map.getPageNum());
            result.setStatus(Constants.SAVE_SUCCESS);
            result.setMessage(Constants.SUCCESS_MESSAGE);
        } catch (Exception e){
            log.error("分页查询销售单列表失败",e);
        }finally {
            return result;
        }
    }


    /**
     * 第三方查询  查询销售信息列表-分页查询
     * @param pageInfo
     * @return
     */
    @RequestMapping(value = "api/sellInfoList.json",method = RequestMethod.POST)
    @ResponseBody
    public Map<String , Object> sellInfoList(@RequestParam(required = false , value = "releaseBeginDate") String releaseBeginDate,
                                    @RequestParam(required = false , value = "releaseEndDate")String releaseEndDate, CurrentUser user){
        Map<String , Object> response = Maps.newHashMap();
		response.put("status" , Constants.SAVE_SUCCESS);
		response.put("message",Constants.SUCCESS_MESSAGE);
        try{
            if(user == null){
                BizException bizException = new BizException();
                bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
                throw bizException;
            }
            InfoQuery infoQuery = new InfoQuery();
            infoQuery.setReleaseBeginDate(releaseBeginDate);
            infoQuery.setReleaseEndDate(releaseEndDate);
            SimplePageInfo pageInfo = new SimplePageInfo();
            pageInfo.setPageSize(Integer.MAX_VALUE);
            Page<Map<String,Object>> map  =  infoService.queryByUserId(infoQuery,pageInfo);
            if(map == null){
                map = new Page();
            }
            response.put("datas" , map);
            response.put("count", map.size());
        } catch (Exception e){
            log.error("分页查询销售单列表失败",e);
			response.put("status" , Constants.EXCEPTION_CODE);
			response.put("message",Constants.EXCEPTION_MESSAGE);
			return response;
        }
            return response;
    }

    /**
     * 查询最新的采购信息
     * @param size
     * @return
     */
    @RequestMapping(value = "selectNewSellInfoList/{size}",method = RequestMethod.GET)
    @ResponseBody
    public ResultDatas selectNewSellInfoList(@PathVariable("size")int size){

        ResultDatas result = new ResultDatas();
        result.setStatus(Constants.EXCEPTION_STATUS);
        result.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
        try{
            List<InfoVO> infoVOList = infoService.selectNewSellInfoList(size);
            result.setDatas(infoVOList);
            result.setStatus(Constants.SAVE_SUCCESS);
            result.setMessage(Constants.SUCCESS_MESSAGE);
        } catch (Exception e){
            log.error("查询最新的销售记录失败",e);
        }finally {
            return result;
        }
    }

    /**
     * 查询销售的详细信息
     * @param uuid
     * @return
     */
    @RequestMapping(value = "selectSellInfoDetail/{uuid}",method = RequestMethod.GET)
    @ResponseBody
    public ResultDatas selectSellInfoDetail(@PathVariable("uuid")String uuid){

        ResultDatas result = new ResultDatas();
        result.setStatus(Constants.EXCEPTION_STATUS);
        result.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
        try{
            Info info = infoService.findByUuid(uuid);
            InfoVO infoVO = new InfoVO();
            infoVO = infoVO.toInfovo(info);
            result.setDatas(infoVO);
            result.setStatus(Constants.SAVE_SUCCESS);
            result.setMessage(Constants.SUCCESS_MESSAGE);
        } catch (Exception e){
            log.error("查询销售的详细信息",e);
        }finally {
            return result;
        }
    }

    /**
     * 销售下拉框接口信息
     * @param user
     * @return
     */
    @RequestMapping("selectInfoEnumList")
    @ResponseBody
    public ResultDatas selectInfoEnumList(CurrentUser user){
        ResultDatas res = new ResultDatas();
        BizException bizException = new BizException();
        HashMap<String, Object> map = new HashMap<>();
        try {

            if (null == user){
                bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
                throw bizException;
            }

            Collection<DictionaryVO> deliveryMode = shipDictionaryService.deliveryModeStatusMap().values();
            Collection<DictionaryVO> oilType = shipDictionaryService.oilTypeEnumsMap().values();
            Collection<DictionaryVO> typeOfShipping = shipDictionaryService.typeOfShippingEnumsMap().values();

            //int code = infoService.deleteByuuid(uuid);
            map.put("typeOfShipping",typeOfShipping);
            map.put("deliveryMode",deliveryMode);
            map.put("oilType",oilType);
            res.setDatas(map);
        }catch (BizException e) {
            int code = e.getCode();
            res.setStatus(code);
            res.setMessage(exceptionHelper.getBizExceptionByCode(code).getZhName());
            log.error("deleteByuuid",e);
        }catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
            log.error("deleteByuuid",e);
        }
        return res;
    }

    /**
     * 根据油品类查询油类型下的信息
     * @param user
     * @param oiltypeCode
     * @return
     */
    @RequestMapping("selectOilCodeEnumList")
    @ResponseBody
    public ResultDatas selectOilCodeEnumList(CurrentUser user,String oiltypeCode){
        ResultDatas res = new ResultDatas();
        BizException bizException = new BizException();
        HashMap<String, Object> map = new HashMap<>();
        try {

            if (null == user){
                bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
                throw bizException;
            }
            switch (oiltypeCode){
                case Constants.OILTYPE_1:
                    Collection<DictionaryVO> values = shipDictionaryService.fuelOilEnumsMap().values();
                    map.put("oiltypeList",values);break;
                case Constants.OILTYPE_2:
                    Collection<DictionaryVO> values1 = shipDictionaryService.dieselOilEnumsMap().values();
                    map.put("oiltypeList",values1);break;
                case Constants.OILTYPE_3:
                    Collection<DictionaryVO> values2 = shipDictionaryService.engineOilEnumsMap().values();
                    map.put("oiltypeList",values2);break;
            }
//            Collection<DictionaryVO> deliveryMode = shipDictionaryService.deliveryModeStatusMap().values();
//            Collection<DictionaryVO> oilType = shipDictionaryService.oilTypeEnumsMap().values();
            //int code = infoService.deleteByuuid(uuid);

            res.setDatas(map);
        }catch (BizException e) {
            int code = e.getCode();
            res.setStatus(code);
            res.setMessage(exceptionHelper.getBizExceptionByCode(code).getZhName());
            log.error("deleteByuuid",e);
        }catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
            log.error("deleteByuuid",e);
        }
        return res;
    }

    /**
     * 销售详细页面会员id查询
     * @param memberid
     */
    @RequestMapping("selectByMemberId")
    @ResponseBody
    public void selectByMemberId(Long memberid){

        //（1）期不要删
       /* DubboConfig dubboConfig = new DubboConfig();
        boolean allEnterpriseRoles = dubboConfig.getAllEnterpriseRoles(memberid, Constants.SELLER_CODE);
        //true 查询上游下拉数据  本期作废，暂时不用；
        if (allEnterpriseRoles) {

        }*/

    }



    /**
     * 前台——根据条件查询销售信息列表
     * @param infoVO
     * @return
     */
    @RequestMapping(value = "selectSellInfoListByVo.json",method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas selectSellInfoListByVo(@ModelAttribute("infoVo") InfoVO infoVO,
                                              @ModelAttribute("pageInfo")SimplePageInfo pageInfo,
                                              @RequestParam(value = "epMemberName" , required = false)String epMemberName){

        ResultDatas result = new ResultDatas();
        result.setStatus(Constants.EXCEPTION_STATUS);
        result.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
        try{
            Info info = infoVO.voToInfo();
            List<Info>  list = infoService.queryByEntitys(info);
            List<InfoVO>  voList = null;
            if(list != null && list.size()>0){
                voList = new ArrayList<>(list.size());
                for (Info infoOfList:list) {
                    InfoVO vo = new InfoVO();
                    vo = vo.toInfovo(infoOfList);
                    voList.add(vo);
                }
            }
            result.setDatas(voList);
            result.setStatus(Constants.SAVE_SUCCESS);
            result.setMessage(Constants.SUCCESS_MESSAGE);
        } catch (Exception e){
            log.error("根据条件查询销售信息列表异常",e);
        }finally {
            return result;
        }
    }

    //ADD_ 船油销售订单查询首页展示_wdh_20180731_Start
    /**
     * 查询销售列表
     */
    @RequestMapping(value = "selectOrderList.json" , method = RequestMethod.GET)
//    @ResponseBody
    public ResultDatas<List<Map<String,Object>>> selectOrderList(HttpServletRequest req, CurrentUser user/*, @RequestBody InfoQuery InfoQuery*/){

        ResultDatas res = new ResultDatas();
        BizException bizException = new BizException();
        try {
            if (null == user) {
                //没有权限
                bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
                throw  bizException;
            }

            SimplePageInfo pageInfo = new SimplePageInfo();

            //查询枚举
            Collection<DictionaryVO> values = shipDictionaryService.deliveryModeStatusMap().values();

            //根据当前用户查询当前用户所拥有的集合
            Page<Map<String,Object>> pageMap = new Page<Map<String,Object>>();
            //9为从燃料油 不是则为船油
            pageMap = infoService.queryList(pageInfo);

            res.setDatas(pageMap);
            res.setMessage("船油");

        }catch (BizException e) {
            int code = e.getCode();
            res.setStatus(code);
            res.setMessage(exceptionHelper.getBizExceptionByCode(code).getZhName());
            log.error("selectOrderList",e);
        }catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
            log.error("selectOrderList",e);
        }
        return res;
    }
    //ADD_ 船油销售订单查询首页展示_wdh_20180731_End



    @RequestMapping(value = "/downGoodsShelves.json" , method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas DownGoodsShelves(@RequestBody Info info){
        ResultDatas res = new ResultDatas();
        info.setStatus(Info.STATUS_DELETED);
        int count = infoService.updateByUuidStatus(info);
        if(count < 1){
            res.setStatus(Result.EEROR);
            res.setMessage(Constants.MODIFY_FALSE);
            return res;
        }
        res.setStatus(Result.SUCCESS);
        res.setMessage(Constants.MODIFY_TRU);
        return res;
    }

}
