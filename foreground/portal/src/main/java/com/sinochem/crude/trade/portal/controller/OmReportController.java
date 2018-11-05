package com.sinochem.crude.trade.portal.controller;

import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 报表控制层
 */
@Controller
@RolesAccess("admin")
public class OmReportController {

    @Value("${report.home.path}")
    private String reportHomePath;

    @Value("${report.info.path}")
    private String reportInfoPath;

    @Value("${report.member.path}")
    private String reportMemberPath;

    @Value("${report.order.path}")
    private String reportOrderPath;

    @Value("${report.order.execut.path}")
    private String reportOrderExecutPath;

    @Value("${report.ship.path}")
    private String reportShipPath;

    /**
     * 首页
     */
    @RightAccess(201)
    @RequestMapping("/om/welcome")
    public void reportHome(ModelMap modelMap) {
        modelMap.put("reportHomePath",reportHomePath);
    }

    /**
     * 用户
     */
    @RightAccess(202)
    @RequestMapping("/om/report/report_member")
    public void reportMember(ModelMap modelMap) {
        modelMap.put("reportMemberPath",reportMemberPath);
    }

    /**
     * 交易
     */
    @RightAccess(203)
    @RequestMapping("/om/report/report_order")
    public void reportOrder(ModelMap modelMap) {
        modelMap.put("reportOrderPath",reportOrderPath);

    }

    /**
     * 订单执行
     */
    @RightAccess(204)
    @RequestMapping("/om/report/report_order_execute")
    public void reportOrderExecte(ModelMap modelMap) {
        modelMap.put("reportOrderExecutPath",reportOrderExecutPath);

    }

    /**
     * 船务
     */
    @RightAccess(205)
    @RequestMapping("/om/report/report_ship")
    public void reportShip(ModelMap modelMap) {
        modelMap.put("reportShipPath",reportShipPath);
    }

    /**
     * 资讯
     */
    @RightAccess(206)
    @RequestMapping("/om/report/report_info")
    public void reportInfo(ModelMap modelMap) {
        modelMap.put("reportInfoPath",reportInfoPath);
    }
}
