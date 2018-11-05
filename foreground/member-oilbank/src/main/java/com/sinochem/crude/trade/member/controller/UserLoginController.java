package com.sinochem.crude.trade.member.controller;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.result.ResultDatas;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by z1761 on 2018/6/9.
 */
@Controller
public class UserLoginController {

    @RequestMapping("userLogin.json")
    @ResponseBody
    public ResultDatas loginUser(CurrentUser user){
        ResultDatas resultDatas = new ResultDatas();
        resultDatas.setDatas(user);

        return resultDatas;
    }
}
