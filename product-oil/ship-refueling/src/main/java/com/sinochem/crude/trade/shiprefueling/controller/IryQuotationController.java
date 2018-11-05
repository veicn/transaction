package com.sinochem.crude.trade.shiprefueling.controller;

import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.Page;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.constants.UrlMapping;
import com.sinochem.crude.trade.shiprefueling.controller.common.BeanConvertUtils;
import com.sinochem.crude.trade.shiprefueling.controller.common.KeyGenUtils;
import com.sinochem.crude.trade.shiprefueling.controller.common.ResultDatas;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.domain.po.HaseInfo;
import com.sinochem.crude.trade.shiprefueling.domain.po.Info;
import com.sinochem.crude.trade.shiprefueling.domain.po.IryQuotation;
import com.sinochem.crude.trade.shiprefueling.enums.ExceptionEnum;
import com.sinochem.crude.trade.shiprefueling.helper.ExceptionHelper;
import com.sinochem.crude.trade.shiprefueling.model.vo.IryQuotationVO;
import com.sinochem.crude.trade.shiprefueling.service.HaseInfoService;
import com.sinochem.crude.trade.shiprefueling.service.InfoService;
import com.sinochem.crude.trade.shiprefueling.service.IryQuotationService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.WithoutAccess;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 询价报价控制器
 *
 * @author niuwk
 *
 */
@Controller
@WithoutAccess
public class IryQuotationController {

    @Autowired
    private IryQuotationService iryQuotationService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private InfoService infoService;

    @Autowired
    private HaseInfoService haseInfoService;

    @Autowired
    private ExceptionHelper exceptionHelper;

    public static final Log log = LogFactory.getLog(IryQuotationController.class);

	/**
	 * 添加询价报价信息
	 * @param vo
	 * @return
	 */
    @RequestMapping(value = UrlMapping.INQUIRY_QUOTATION_INSERT, method = POST)
    @ResponseBody
    public ResultDatas<Object> insertIryQuotation(@RequestBody IryQuotationVO vo,CurrentUser user) {
        ResultDatas<Object> res = new ResultDatas<Object>();
        try {
            //报价/询价信息
        	vo.setUuid(KeyGenUtils.newUuid());
        	vo.setCreateDate(new Date());
        	vo.setUpdateDate(new Date());
            vo.setAliveFlag(IryQuotationVO.ALIVE_FLAG_NORMAL);
            vo.setStatus(IryQuotationVO.STATUS_NOT_CONNECTED);
            //创建人标识
            vo.setCreateUser(user.getMemberId());
            //创建人账号
            vo.setCreateUserName(user.getName());

            //查询并设置报价/询价企业信息
            EnterpriseVo epSale = enterpriseService.getByMemberId(user.getEpMemberId());
            vo.setCompanyId(epSale.getMemberId());
            vo.setCompanyName(epSale.getName());

            //查询发布企业信息
            if(vo == null || vo.getNeedId() == null || vo.getNeedId().longValue() < 1){
                throw new BizException("采购信息不能为空");
            }
            //采购
            if(Constants.QUOTATION_TYPE_OFFER.equals(vo.getType())){
                HaseInfo haseInfo = haseInfoService.findByPrimaryKey(vo.getNeedId());
                //查询发布采购/销售企业信息
                vo.setEpMemberId(haseInfo.getEpMemberId());
                vo.setEpMemberName(haseInfo.getEpMemberName());
            }else{
                //销售
                Info info = infoService.findByPrimaryKey(vo.getNeedId());
                //查询发布采购/销售企业信息
                vo.setEpMemberId(info.getEpMemberId());
                vo.setEpMemberName(info.getEpMemberName());
                //add by fengzk 自己不能询价自己发布的产品
                if(info.getEpMemberId().equals(epSale.getMemberId()))
                {
                    res.setStatus(Constants.ENQUIRY_STATUS);
                    res.setMessage(Constants.ENQUIRY_MESSAGE);
                    return  res;
                }
            }

        	int i = iryQuotationService.insertIryQuotationRecord(vo);
        	if(i==0){
        		res.setStatus(Constants.EXCEPTION_STATUS);
                res.setMessage(Constants.SAVE_FALSE);
        	}
        } catch (Exception e) {
        	res.setStatus(Constants.EXCEPTION_STATUS);
			res.setMessage(e.getMessage());
            e.printStackTrace();
        }
            return res;
    }


    /**
     * 查询报价/询价详情
     * @param inquiryQuotationId
     * @return
     */
    @RequestMapping(value = "quote/selectByuuid.json" , method = POST)
    @ResponseBody
    public ResultDatas<IryQuotation> findByPrimaryKey(@RequestBody IryQuotation quotation){
        ResultDatas<IryQuotation> res = new ResultDatas<IryQuotation>();
        try{
            IryQuotation iryQuotation = iryQuotationService.findByUuid(quotation.getUuid());
            res.setDatas(iryQuotation);
        }catch (Exception e){
            log.error(e);
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }
        return res;
    }



    /**
     * 分页列表
     * @param vo
     * @return
     */
    @RequestMapping(value = UrlMapping.INQUIRY_QUOTATION_LIST, method = POST)
    @ResponseBody
    public ResultDatas<List<Map<String,Object>>> getIryQuotationList(@RequestBody IryQuotationVO vo,CurrentUser user) {
    	ResultDatas<List<Map<String,Object>>> res=new ResultDatas<>();
        try {
        	SimplePageInfo pageInfo = vo.getPageInfo();
        	Map<String, Object> map = null;
        	map = BeanConvertUtils.beanToMap(vo);
        	Page<Map<String, Object>> page = iryQuotationService.queryIryQuotationList(map,pageInfo);
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

    
    /**
     * 根据采购UUID查询询价报价信息列表
     * @param vo
     * @return
     */
    @RequestMapping(value = UrlMapping.INQUIRY_QUOTATION_LIST_BY_NEEDUUID, method = POST)
    @ResponseBody
    public ResultDatas<List<Map<String,Object>>> getListByNeedID(@RequestBody IryQuotationVO vo,CurrentUser user) {
    	ResultDatas<List<Map<String,Object>>> res=new ResultDatas<>();
        try {
        	SimplePageInfo pageInfo = vo.getPageInfo();
        	Map<String, Object> map = null;
        	map = BeanConvertUtils.beanToMap(vo);
        	Page<Map<String, Object>> page = iryQuotationService.queryListByNeedUuid(map,pageInfo);
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
    
    
    /**
     * 更新询价报价状态
     * @param vo
     * @return
     */
    @RequestMapping(value = UrlMapping.INQUIRY_QUOTATION_STATUS, method = POST)
    @ResponseBody
    public ResultDatas<Object> setStatusByUuid(@RequestBody IryQuotation iryQuotation,CurrentUser user) {
    	 ResultDatas<Object> res = new ResultDatas<Object>();
         try {


         	int i = iryQuotationService.updateRecordByUuid(iryQuotation);

         	if(i==0){
        		res.setStatus(Constants.EXCEPTION_STATUS);
        		return res;
        	}
			 //如果状态是已成交，则更改销售/采购信息
			 if(IryQuotation.STATUS_DONE.equals(iryQuotation.getStatus())){
				iryQuotation = iryQuotationService.findByUuid(iryQuotation.getUuid());
         		if(iryQuotation == null){
         			throw new BizException("更新失败，数据异常!");
				}
				if(IryQuotation.TYPE_SALE.equals(iryQuotation.getType())){
					// 销售
					Info info = new Info();
					info.setStatus(Info.STATUS_CONFIRMED);
					info.setUuid(iryQuotation.getNeedUuid());
					infoService.updateRecordByUuid(info);
				}else{
					// 采购
					HaseInfo haseInfo = new HaseInfo();
					haseInfo.setStatus(HaseInfo.STATUS_CONFIRMED);
					haseInfo.setUuid(iryQuotation.getNeedUuid());
					haseInfoService.updateRecordByUuid(haseInfo);
				}
			 }
         } catch (Exception e) {
         	res.setStatus(Constants.EXCEPTION_STATUS);
 			res.setMessage(e.getMessage());
         }
         return res;
    }


    /**
     *前台 ——询价生成询价记录 -武刚鹏-2018年5月28日17:43:19
     * @param inquiryVo
     * @return
     */
   // @PostMapping("inquiry")
    @ResponseBody
    public ResultDatas inquiry(@RequestBody IryQuotationVO inquiryVo) {
        ResultDatas result = new ResultDatas();
        result.setStatus(Constants.EXCEPTION_STATUS);
        result.setDatas(false);
        result.setMessage(exceptionHelper.convertToExceptionVO(ExceptionEnum.RUNTIME_ERROR).getZhName());
        try {
            if(inquiryVo == null && inquiryVo.getUpdateUser()==null && inquiryVo.getEpMemberId() == null){
                throw new BizException("没有用户信息");
            }
            IryQuotation inquiryDomain = new IryQuotation();
            BeanUtils.copyProperties(inquiryVo,inquiryDomain);
            iryQuotationService.insertRecord(inquiryDomain);
            result.setDatas(true);
            result.setStatus(Constants.SAVE_SUCCESS);
            result.setMessage(Constants.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("查询销售的详细信息", e);
        } finally {
            return result;
        }
    }

}
