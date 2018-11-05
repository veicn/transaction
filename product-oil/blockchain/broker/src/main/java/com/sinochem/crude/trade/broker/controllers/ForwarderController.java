package com.sinochem.crude.trade.broker.controllers;

import com.sinochem.crude.trade.broker.common.ThreadContextHolder;
import com.sinochem.crude.trade.broker.domain.ResultData;
import com.sinochem.crude.trade.broker.domain.ResultDataPages;
import com.sinochem.crude.trade.broker.domain.TBrokerAppoint;
import com.sinochem.crude.trade.broker.domain.TShipagentUser;
import com.sinochem.crude.trade.broker.domain.VO.TBrokerAppointQueryVO;
import com.sinochem.crude.trade.broker.feign.HttpFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/6 15:30
 * @Version: [v1.0]
 */
@RestController
@RequestMapping("/forwarder")
public class ForwarderController {
    @Autowired
    HttpFeignClient httpFeignClient;
    @Autowired
    ThreadContextHolder threadContextHolder;

    @PostMapping(value = "/list.json", produces = "application/json")
    public ResultDataPages forwarderlist(@RequestBody TBrokerAppointQueryVO tBrokerAppoint) {
        if(tBrokerAppoint==null||tBrokerAppoint.getEnterpriseId()==null) {
            TShipagentUser user=threadContextHolder.getCurrentUser();
            if(user!=null) {
                tBrokerAppoint.setEnterpriseId(user.getEpMemberId());
            }
        }
        return httpFeignClient.forwarderlist(tBrokerAppoint);
    }

    @GetMapping(value = "/detail.json", produces = "application/json")
    public ResultData forwarderdetail(String uuid) {
        return httpFeignClient.forwarderdetail(uuid);
    }
}
