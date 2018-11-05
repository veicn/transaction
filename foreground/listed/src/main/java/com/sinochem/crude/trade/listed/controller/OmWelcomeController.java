package com.sinochem.crude.trade.listed.controller;

import com.sinochem.crude.trade.listed.constant.UrlMapping;
import com.sinochem.crude.trade.member.user.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * om控制层
 *
 */

@Controller
public class OmWelcomeController {

    @RequestMapping(value = UrlMapping.OM_WELCOME)
    public void welcome(CurrentUser user, ModelMap modelMap){
        modelMap.put("user",user);
    }
}
