package com.sinochem.crude.trade.web.controller;

import com.sinochem.crude.trade.common.constant.HttpRequestAttributes;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;
import com.sinochem.crude.trade.transaction.model.vo.TestVO;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 示例的Controller
 * @author Yichen Zhao
 * date: 20180307
 */
@Controller
@RequestMapping(UrlMapping.EXAMPLE)
@WithoutAccess //开发时可以先把这个加上，这个是表示不做权限控制
public class ExampleController {

    @RequestMapping(UrlMapping.INDEX)
    public void index(CurrentUser currentUser, TestVO testVO) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/" + UrlMapping.EXAMPLE + "/" + UrlMapping.EXAMPLE + ".htm");
    }

    @RequestMapping(UrlMapping.EXAMPLE1)
    public void example1(CurrentUser currentUser, ModelMap modelMap) {
        modelMap.put("message", "success"); //这里，“success”是魔法值，是不允许在正式代码中出现的
    }

    @RequestMapping(UrlMapping.EXAMPLE2)
    public ModelAndView example2(CurrentUser currentUser, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();

        if (((List<?>) request.getAttribute(HttpRequestAttributes.MEDIA_TYPES)).contains(MediaType.APPLICATION_JSON)) {
            ResultDatas resultDatas = new ResultDatas();
            resultDatas.setDatas("success");

            mav.addAllObjects((Map<String, ?>) resultDatas.toMap());
            mav.setView(new MappingJacksonJsonView());
        } else {
            mav.addObject("message", "success");
        }

        return mav;
    }

    @RequestMapping(UrlMapping.EXAMPLE3)
    public ModelAndView example3(CurrentUser currentUser, TestVO testVO) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("test", "test");

        return mav;
    }
}
