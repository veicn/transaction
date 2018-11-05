package com.sinochem.crude.trade.info.controller;

import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.RemoteFileUtils;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OssPolicyController {

    @RequestMapping(value = "/oss/policy.json")
    @ResponseBody
    public Map<String, String> policy(String bucket, String dir) throws BizException {
        return RemoteFileUtils.getPolicy(bucket, dir);
    }
}
