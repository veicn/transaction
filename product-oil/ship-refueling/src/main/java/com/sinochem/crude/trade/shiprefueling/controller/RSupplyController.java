package com.sinochem.crude.trade.shiprefueling.controller;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.constants.Constants;
import com.sinochem.crude.trade.shiprefueling.controller.common.BeanConvertUtils;
import com.sinochem.crude.trade.shiprefueling.controller.common.ResultDatas;
import com.sinochem.crude.trade.shiprefueling.controller.common.SimplePageInfo;
import com.sinochem.crude.trade.shiprefueling.domain.po.Gory;
import com.sinochem.crude.trade.shiprefueling.domain.po.RSupply;
import com.sinochem.crude.trade.shiprefueling.enums.ExceptionEnum;
import com.sinochem.crude.trade.shiprefueling.model.vo.RSupplyVO;
import com.sinochem.crude.trade.shiprefueling.service.GoryService;
import com.sinochem.crude.trade.shiprefueling.service.RSupplyService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 船供订单控制器
 *
 * @author niuwk
 */
@Controller
@RequestMapping("/ordersupply")
@WithoutAccess
public class RSupplyController {

    @Autowired
    private RSupplyService rSupplyService;
    @Autowired
    private GoryService goryService;

    public static final Log log = LogFactory.getLog(RSupplyController.class);

    @RequestMapping(value = "/buylist.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas<List<Map<String, Object>>> getBuyOrderList(@RequestBody RSupplyVO vo, CurrentUser user) throws BizException {
        ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();

        this.checkSupplyBuyAuth(vo, user);

        try {
            SimplePageInfo pageInfo = vo.getPageInfo();
            Map<String, Object> map = BeanConvertUtils.beanToMap(vo);
            res = rSupplyService.querySupplyRecords(map, pageInfo);
        } catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());

        }
        return res;
    }

    /**
     * 分页列表(按交易对手展示)
     */
    @RequestMapping(value = "/buylistbycompany.json", method = POST)
    @ResponseBody
    public ResultDatas getBuyOrderCountByCompany(@RequestBody RSupplyVO vo, CurrentUser user) throws BizException {
        ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
        this.checkSupplyBuyAuth(vo, user);

        try {
            SimplePageInfo pageInfo = vo.getPageInfo();
            Map<String, Object> map = BeanConvertUtils.beanToMap(vo);
            res = rSupplyService.querySellSupplyGroupByCompany(map, pageInfo);
        } catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    /**
     * 查询要展开的订单
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/buyordersbycompany.json", method = POST)
    @ResponseBody
    public ResultDatas getBuyOrderListByCompany(@RequestBody RSupplyVO vo, CurrentUser user) throws BizException {
        ResultDatas res = new ResultDatas<>();
        // 展开的是卖家的订单
        if(vo == null || vo.getSellCompanyId() == null) {
            return res;
        }
        if(StringUtil.isBlank(vo.getSysTag())) {
            vo.setAliveFlag(Constants.SAVE_FLAG);
        }
        try {
            Map<String, Object> map = BeanConvertUtils.beanToMap(vo);
            List<Map<String, Object>> orderList = rSupplyService.queryOrderListtByCompany(map);
            res.setDatas(orderList);
            res.setTotal(orderList == null ? 0 : (long) orderList.size());
        } catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    @RequestMapping(value = "/selllist.json", method = RequestMethod.POST)
    @ResponseBody
    public ResultDatas<List<Map<String, Object>>> getSellOrderList(@RequestBody RSupplyVO vo, CurrentUser user) throws BizException {
        ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();

        this.checkSupplySellAuth(vo, user);

        try {
            SimplePageInfo pageInfo = vo.getPageInfo();
            Map<String, Object> map = BeanConvertUtils.beanToMap(vo);
            res = rSupplyService.querySupplyRecords(map, pageInfo);
        } catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());

        }
        return res;
    }

    /**
     * 分页列表(按交易对手展示)
     */
    @RequestMapping(value = "/selllistbycompany.json", method = POST)
    @ResponseBody
    public ResultDatas getSellOrderCountByCompany(@RequestBody RSupplyVO vo, CurrentUser user) throws BizException {
        ResultDatas<List<Map<String, Object>>> res = new ResultDatas<>();
        this.checkSupplySellAuth(vo, user);

        try {
            SimplePageInfo pageInfo = vo.getPageInfo();
            Map<String, Object> map = BeanConvertUtils.beanToMap(vo);
            res = rSupplyService.queryBuySupplyGroupByCompany(map, pageInfo);
        } catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    /**
     * 查询要展开的订单
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/sellordersbycompany.json", method = POST)
    @ResponseBody
    public ResultDatas getSellOrderListByCompany(@RequestBody RSupplyVO vo, CurrentUser user) throws BizException {
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
            List<Map<String, Object>> orderList = rSupplyService.queryOrderListtByCompany(map);
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
    public void checkSupplyBuyAuth(RSupplyVO vo, CurrentUser user) throws BizException {
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
    public void checkSupplySellAuth(RSupplyVO vo, CurrentUser user) throws BizException {
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
    public ResultDatas deleteOrder(@RequestBody RSupplyVO vo) {
        ResultDatas res = new ResultDatas<>();
        try {
            rSupplyService.updateRecordsAliveFlag(vo.getOrderId(), Constants.ALIEVE_FLAG_NO);
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
    public ResultDatas uploadSubmit(@RequestBody Map<String, Object> map) throws BizException {
        ResultDatas<Object> res = new ResultDatas<>();
        if(map == null || map.isEmpty() || map.get("orderId") == null) {
            throw new BizException("请求参数不合法");
        }

        try {
            RSupply supply = rSupplyService.findByPrimaryKey(NumberUtils.parseNumber(map.get("orderId").toString(), Long.class));
            supply.setStatus("2");
            rSupplyService.updateRecordById(supply);
        }catch (Exception e) {
            res.setStatus(Constants.EXCEPTION_STATUS);
            res.setMessage(e.getMessage());
        }
        return res;
    }
}
