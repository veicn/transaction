package com.sinochem.crude.trade.shiprefueling.controller;

import com.sinochem.crude.trade.shiprefueling.domain.po.Chments;
import com.sinochem.crude.trade.shiprefueling.service.ChmentsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by z1761 on 2018/5/25.
 */
@Controller
public class TestController {
    @Autowired
    private ChmentsService chmentsService;

    public static final Log log = LogFactory.getLog(ChmentsController.class);

    @RequestMapping("text.htm")
    public void test(){
        System.out.println(1);
        Chments byPrimaryKey = chmentsService.findByPrimaryKey(Long.parseLong("1"));
        System.out.println(byPrimaryKey);
        System.out.println(byPrimaryKey);
        System.out.println(byPrimaryKey);
        System.out.println(byPrimaryKey);
        System.out.println(byPrimaryKey);
        System.out.println(byPrimaryKey);
    }
}
