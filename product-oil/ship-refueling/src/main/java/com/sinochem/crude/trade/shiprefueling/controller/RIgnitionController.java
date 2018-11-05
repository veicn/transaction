package com.sinochem.crude.trade.shiprefueling.controller;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.controller.common.BeanConvertUtils;
import com.sinochem.crude.trade.shiprefueling.controller.common.ResultDatas;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.domain.po.Chments;
import com.sinochem.crude.trade.shiprefueling.domain.po.Gory;
import com.sinochem.crude.trade.shiprefueling.domain.po.RIgnition;
import com.sinochem.crude.trade.shiprefueling.enums.BusinessType;
import com.sinochem.crude.trade.shiprefueling.enums.ExceptionEnum;
import com.sinochem.crude.trade.shiprefueling.model.vo.ChmentsVO;
import com.sinochem.crude.trade.shiprefueling.model.vo.RIgnitionVO;
import com.sinochem.crude.trade.shiprefueling.service.GoryService;
import com.sinochem.crude.trade.shiprefueling.service.RIgnitionService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.lang.reflect.Array;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 船燃订单控制器 （原料油）
 *
 * @author niuwk
 */
@Controller
@WithoutAccess
@RequestMapping("/orderignition")
public class RIgnitionController {

    @Autowired
    private RIgnitionService rIgnitionService;
    @Autowired
    private GoryService goryService;

    public static final Log logger = LogFactory.getLog(RIgnitionController.class);


    /**
     * 买家船燃订单分页列表
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/buylist.json", method = POST)
    @ResponseBody
    public ResultDatas<List<Map<String, Object>>> getBuyOrderList(
            @RequestBody RIgnitionVO vo, CurrentUser user) throws BizException {
        this.checkIgnitionBuyAuth(vo, user);
        ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
        try {
            SimplePageInfo pageInfo = vo.getPageInfo();
            Map<String, Object> map = BeanConvertUtils.beanToMap(vo);
            res = rIgnitionService.queryIgnitionRecords(map, pageInfo);
        } catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());

        }
        return res;
    }

    /**
     * 买家船燃订分页列表(按交易对手展示)
     */
    @RequestMapping(value = "/buylistbycompany.json", method = POST)
    @ResponseBody
    public ResultDatas getBuyOrderCountByCompany(@RequestBody RIgnitionVO vo, CurrentUser user) throws BizException {

        this.checkIgnitionBuyAuth(vo, user);

        ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
        try {
            SimplePageInfo pageInfo = vo.getPageInfo();
            Map<String, Object> map = BeanConvertUtils.beanToMap(vo);
            res = rIgnitionService.querySellIgnitionGroupByCompany(map, pageInfo);
        } catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    /**
     * 买家查询要展开的订单
     * @param vo
     * @return
     */
    @RequestMapping(value = "/buyordersbycompany.json", method = POST)
    @ResponseBody
    public ResultDatas getBuyOrderListByCompany(@RequestBody RIgnitionVO vo, CurrentUser user) throws BizException {

        ResultDatas res = new ResultDatas<>();

        //展开的是卖家的订单
        if(vo == null || vo.getSellCompanyId() == null) {
            return res;
        }
        if(StringUtil.isBlank(vo.getSysTag())) {
            vo.setAliveFlag(Constants.SAVE_FLAG);
        }
        try {
            Map<String, Object> map = null;
            map = BeanConvertUtils.beanToMap(vo);
            List<Map<String, Object>> orderList = rIgnitionService.queryOrderListtByCompany(map);
            res.setTotal(orderList == null ? 0 : (long) orderList.size());
            res.setDatas(orderList);
        } catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    /**
     * 卖家船燃订单分页列表
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/selllist.json", method = POST)
    @ResponseBody
    public ResultDatas<List<Map<String, Object>>> getSellOrderList(
            @RequestBody RIgnitionVO vo, CurrentUser user) throws BizException {


        this.checkIgnitionSellAuth(vo, user);
        ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
        try {
            SimplePageInfo pageInfo = vo.getPageInfo();
            Map<String, Object> map = BeanConvertUtils.beanToMap(vo);
            res = rIgnitionService.queryIgnitionRecords(map, pageInfo);
        } catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());

        }
        return res;
    }

    /**
     * 卖家船燃订分页列表(按交易对手展示)
     */
    @RequestMapping(value = "/selllistbycompany.json", method = POST)
    @ResponseBody
    public ResultDatas getSellOrderCountByCompany(@RequestBody RIgnitionVO vo, CurrentUser user) throws BizException {

        this.checkIgnitionSellAuth(vo, user);

        ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
        try {
            SimplePageInfo pageInfo = vo.getPageInfo();
            Map<String, Object> map = BeanConvertUtils.beanToMap(vo);
            res = rIgnitionService.queryBuyIgnitionGroupByCompany(map, pageInfo);
        } catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    /**
     * 卖家查询要展开的订单
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/sellordersbycompany.json", method = POST)
    @ResponseBody
    public ResultDatas getSellOrderListByCompany(@RequestBody RIgnitionVO vo, CurrentUser user) throws BizException {

        ResultDatas res = new ResultDatas<>();
        // 展开的是买家的订单
        if(vo == null || vo.getBuyCompanyId() == null) {
            return res;
        }

        if(StringUtil.isBlank(vo.getSysTag())) {
            vo.setAliveFlag(Constants.SAVE_FLAG);
        }

        try {
            Map<String, Object> map = BeanConvertUtils.beanToMap(vo);
            List<Map<String, Object>> orderList = rIgnitionService.queryOrderListtByCompany(map);
            res.setTotal(orderList == null ? 0 : (long) orderList.size());
            res.setDatas(orderList);
        } catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    /**
     * 买家权限校验
     * @param vo
     * @param user
     * @throws BizException
     */
    public void checkIgnitionBuyAuth(RIgnitionVO vo, CurrentUser user) throws BizException {
        if (null == user) {
            //没有权限
            BizException bizException = new BizException();
            bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
            throw bizException;
        } else {

            if(StringUtil.isBlank(vo.getSysTag())) {
                vo.setAliveFlag(Constants.SAVE_FLAG);
                vo.setBuyCompanyId(user.getEpMemberId());
                //vo.setBuyCompanyId(20001L);
            }
        }
    }

    /**
     * 卖家权限校验
     * @param vo
     * @param user
     * @throws BizException
     */
    public void checkIgnitionSellAuth(RIgnitionVO vo, CurrentUser user) throws BizException {
        if (null == user) {
            //没有权限
            BizException bizException = new BizException();
            bizException.setCode(ExceptionEnum.NO_AUTHORIZATION.getCode());
            throw bizException;
        } else {
            if(StringUtil.isBlank(vo.getSysTag())) {
                vo.setAliveFlag(Constants.SAVE_FLAG);
                vo.setSellCompanyId(user.getEpMemberId());
                //vo.setSellCompanyId(20001L);
            }
        }
    }

    /**
     * 删除订单
     * @param vo
     * @return
     */
    @RequestMapping(value = "delete.json", method = POST)
    @ResponseBody
    public ResultDatas deleteOrder(@RequestBody RIgnitionVO vo) {
        ResultDatas res = new ResultDatas<>();
        try {
            rIgnitionService.updateRecordsAliveFlag(vo.getOrderId(), Constants.ALIEVE_FLAG_NO);
            goryService.updateAliveFlagByOrderId(vo.getOrderId(), Constants.DELE_FLAG);
        } catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    /**
     * 文件上传提交
     */
    @RequestMapping(value = "uploadsubmit.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas uploadSubmit(CurrentUser user , @RequestBody Map<String, Object> map) throws BizException {
        ResultDatas<Object> res = new ResultDatas<>();
        final String orderIdParam = "orderId";
        if(map == null || map.isEmpty() || map.get(orderIdParam) == null) {
            throw new BizException("请求参数不合法");
        }

        List<Map<String , String>> contractList = (List<Map<String , String>>)map.get("contractList");
        List<Map<String , String>> invoiceList = (List<Map<String , String>>)map.get("invoiceList");
        List<Map<String , String>> otherList = (List<Map<String,String>>) map.get("otherList");
        final String type = BusinessType.CRUDE_OIL.getCode().equalsIgnoreCase((String)map.get("oilType"))?
				BusinessType.CRUDE_OIL.getCode():BusinessType.IGNITION.getCode();
        try {
            // 附件剩余数量
            int resultNum = 0;
            // 业务单据标识
            long orderId = NumberUtils.parseNumber(map.get("orderId").toString(), Long.class);

			// 查询订单信息
			RIgnition ignition = rIgnitionService.findByPrimaryKey(orderId);
			if(ignition == null){
				throw new BizException("操作失败,订单不存在");
			}

			//合同验证
			if(CollectionUtils.isEmpty(contractList)){
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setMessage("合同不能为空");
				return res;
			}

            // 删除/上传合同
            resultNum = rIgnitionService.uploadOrderFile(contractList , type , Constants.FILE_AGREEMENT_TYPE , orderId , user);

			if(resultNum < 1){
				res.setStatus(Constants.EXCEPTION_STATUS);
				res.setMessage("上传合同失败!");
				return res;
			}

            if(logger.isErrorEnabled()){
                if(resultNum < contractList.size()){
                    final  String  message = String.format(Locale.getDefault() , "%d个[合同文件]上传失败!" , (invoiceList.size() - resultNum));
                    logger.error(message);
                }
            }

            // 删除/上传发票
            resultNum = rIgnitionService.uploadOrderFile(invoiceList , type , Constants.FILE_INVOICE_TYPE , orderId , user);
            if(logger.isErrorEnabled()){
                if(resultNum < invoiceList.size()){
                    final  String  message = String.format(Locale.getDefault() , "%d个[发票文件]上传失败!" , (invoiceList.size() - resultNum));
                    logger.error(message);
                }
            }

            // 删除/上传其他
            resultNum = rIgnitionService.uploadOrderFile(otherList , type, Constants.FILE_OTHER_TYPE , orderId , user);
            if(logger.isErrorEnabled()){
                if(resultNum < invoiceList.size()){
                    final  String  message = String.format(Locale.getDefault() , "%d个[其他文件]上传失败!" , (invoiceList.size() - resultNum));
                    logger.error(message);
                }
            }

			// 改变订单状态为已完成
            ignition.setStatus("2");
            rIgnitionService.updateRecordById(ignition);
        }catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }
        return res;
    }



}
