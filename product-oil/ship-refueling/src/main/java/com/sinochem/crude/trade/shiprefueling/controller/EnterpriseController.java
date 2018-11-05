package com.sinochem.crude.trade.shiprefueling.controller;

import com.google.common.collect.Lists;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseDetailVO;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shiprefueling.controller.common.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author songhaiqiang
 */
@Controller
@RequestMapping(value = "/ep")
@WithoutAccess
public class EnterpriseController {


    @Autowired
    private EnterpriseService enterpriseService;

    @RequestMapping(value = "/list.json" , method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody
    public ResultDatas<List<Map<String , Object>>> getAllEp(){
        ResultDatas res = new ResultDatas();
        List<EnterpriseDetailVO> list  = Lists.newArrayList();
        try{
            list  = enterpriseService.getAllEnterprises();
        }catch (Exception e){
            e.printStackTrace();
        }
        res.setDatas(list);
        return res;
    }

    @RequestMapping(value = "/getCurrentEnterprise.json" , method = {RequestMethod.POST , RequestMethod.GET})
    public ResultDatas<Map<String , Object>> getCurrentEnterPrise(CurrentUser user){
        ResultDatas res = new ResultDatas();
        EnterpriseVo vo = null;
        if(user.getEpMemberId() != null){
            vo = enterpriseService.getByMemberId(user.getEpMemberId());
        }
        res.setDatas(vo);
        return res;
    }
}
