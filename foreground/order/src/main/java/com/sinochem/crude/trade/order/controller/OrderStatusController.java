package com.sinochem.crude.trade.order.controller;

import com.eyeieye.melody.util.uuid.UUIDGenerator;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.order.constart.MsgConstart;
import com.sinochem.crude.trade.order.service.CrudeContractService;
import com.sinochem.crude.trade.order.service.OrderStatusDisplayService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.order.status.service.OrderStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("status")
@RolesAccess("trade_oper")
public class OrderStatusController {
    private final Logger LOGGER = LoggerFactory.getLogger(OrderStatusController.class);
    @Autowired
    UUIDGenerator uuidGenerator;
    @Autowired
    OrderStatusDisplayService orderStatusDisplayService;
    @Autowired
    OrderStatusService orderStatusService;
    @Autowired
    CrudeContractService crudeContractService;

    @RequestMapping("list")
    public String status(Long orderId, ModelMap modelMap) {

        try {
            modelMap.put("order", crudeContractService.queryContractInfo(orderId));

            modelMap.put("statusList", orderStatusDisplayService.getStatusList(orderId));

            modelMap.put("detailList", orderStatusService.getDetails(orderId, null));

            modelMap.put("status", orderStatusService.checkOrderStatus(orderId));
        } catch (BizException e) {
            LOGGER.error("{}",orderId);
            LOGGER.error("",e);
            return "error";
        }
        return null;
    }

    @RequestMapping("change")
    @ResponseBody
    public String change(Long orderId, String itemCode, String status) {
        try {
            orderStatusService.setOrderStatus(orderId, itemCode, Integer.parseInt(status), VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0007), 99999999L);
        } catch (BizException e) {
            LOGGER.error("订单状态修改失败{}",orderId,itemCode,status);
            LOGGER.error("",e);
        }
        return VisitorLocale.getByCurrentLanguage(MsgConstart.ORDER0006);
    }

}
