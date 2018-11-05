package com.sinochem.crude.trade.wechat.controller.en;

import com.sinochem.crude.trade.wechat.domain.WechatLogs;
import com.sinochem.crude.trade.wechat.service.WechatLogService;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/4/28 16:39
 * @Version: [v1.0]
 */
@WithoutAccess
@Controller
public class WechatLogsController {

    @Autowired
    private WechatLogService wechatLogService;

    @RequestMapping(value = "wxgetlogs", method = RequestMethod.GET)
    public ResultDatas<List<WechatLogs>> getlogs(@RequestParam(value = "start", required = false, defaultValue = "0") int start, @RequestParam(value = "end", required = false, defaultValue = "10") int end) {
        List<WechatLogs> list = wechatLogService.selectall(start, end);
        ResultDatas<List<WechatLogs>> resultDatas = new ResultDatas<List<WechatLogs>>();
        resultDatas.setDatas(list);
        return resultDatas;
    }
}
