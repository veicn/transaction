package com.sinochem.crude.trade.shipping.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipping.constant.Constants;
import com.sinochem.crude.trade.shipping.constant.UrlMapping;
import com.sinochem.crude.trade.shipping.domain.VoyageStart;
import com.sinochem.crude.trade.shipping.model.vo.VoyageStartVO;
import com.sinochem.crude.trade.shipping.service.VoyageStartService;
import com.sinochem.it.b2b.common.result.Result;

/**
 * zhaoyulong
 *
 * @author 航程跟踪
 */
@Controller
@RequestMapping(UrlMapping.PRODUCT_BACK_VOYAGESTART)
// 开发时可以先把这个加上，这个是表示不做权限控制
public class VoyageStartController {

    public static final Log log = LogFactory
            .getLog(VoyageStartController.class);
    @Autowired
    private VoyageStartService voyageStartService;


    /**
     * 配载计划保存 -武刚鹏-2018年3月19日19:30:14
     * @param vsVO
     * @param currentUser
     * @return
     */
    @RequestMapping(value = "insertStowagePlan.json", method = RequestMethod.POST)
    @ResponseBody
    public Result insertStowagePlan(@RequestBody VoyageStartVO vsVO, CurrentUser currentUser) {
        Result result = new Result();
        try {
            VoyageStart voyageStart = new VoyageStart();
            voyageStart = vsVO.voToDomain();
            Integer code;
            if(StringUtil.isEmpty(voyageStart.getUuid())){
                 code = voyageStartService.insertRecordByCurrentUser(voyageStart,currentUser);
            }else{
                code = voyageStartService.updateRecordByUuid(voyageStart,currentUser);
            }
            if (code > 0) {
                result.setMessage(Constants.SAVE_TRU);
            } else {
                result.setMessage(Constants.SAVE_FALSE);
            }
        } catch (Exception e) {
            log.error("getConfirmationSheet error", e);
            result.setStatus(Constants.EXCEPTION_STATUS);
            result.setMessage(Constants.EXCEPTION_MESSAGE);
        } finally {
            return result;
        }

    }

}
