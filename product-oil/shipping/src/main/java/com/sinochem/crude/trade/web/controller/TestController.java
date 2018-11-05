package com.sinochem.crude.trade.web.controller;

import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
@RequestMapping("test")
public class TestController {

    @RequestMapping("test")
    @WithoutAccess
    public void test(ModelMap modelMap, Locale locale) {
        modelMap.put("message", locale.getDisplayCountry());
    }

    @RequestMapping(value = "restTest.json")
    @ResponseBody
    @WithoutAccess
    public ResultDatas test(HttpServletRequest request) {
        ResultDatas resultDatas = new ResultDatas();
        resultDatas.setDatas(request.getLocale().getDisplayCountry());
        return resultDatas;
    }
}
