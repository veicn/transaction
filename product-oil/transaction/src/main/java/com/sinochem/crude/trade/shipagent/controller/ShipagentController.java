package com.sinochem.crude.trade.shipagent.controller;

//import com.sinochem.it.b2b.common.result.ResultDatas;

import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/blockchain/shipagent/declaration")
public class ShipagentController {
    private final Logger LOGGER = LoggerFactory.getLogger(ShipagentController.class);

    @WithoutAccess
    @RequestMapping(value = "/testApi.json")
    @ResponseBody
    public String testApi(){

        return "shipagent...";
    }
}
