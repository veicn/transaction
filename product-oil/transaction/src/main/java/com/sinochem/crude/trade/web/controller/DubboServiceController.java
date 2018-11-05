package com.sinochem.crude.trade.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinochem.crude.trade.common.model.vo.ResultDatasVO;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseDetailVO;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.UrlMapping;

/**
 * Dubbo服务提供的返回JSON
 */
@Controller
public class DubboServiceController {

    @Autowired
    private EnterpriseService enterpriseService;

    @RequestMapping(UrlMapping.GET_ALL_ENTERPRISE_LIST)
    @ResponseBody
    public ResultDatasVO getAllEnterpriseList(CurrentUser currentUser) {

        ResultDatasVO resultDatasVO = new ResultDatasVO();
        try {
            List<EnterpriseDetailVO> enterpriseDetailVOList = enterpriseService.getAllEnterprises();
            List<EnterpriseDetailVO> enterpriseDetailVOList2 = new ArrayList<>();
            for (EnterpriseDetailVO enterpriseDetailVO : enterpriseDetailVOList) {
                Long id = enterpriseDetailVO.getMemberId();
                if(!currentUser.getEpMemberId().equals(id)){
                    EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(id);
                    enterpriseDetailVO.setName(enterpriseVo.getEnglishName());
                    enterpriseDetailVOList2.add(enterpriseDetailVO);
                }
            }

            resultDatasVO.setDatas(enterpriseDetailVOList2);
        } catch (Exception e) {
            resultDatasVO.setFail("获取数据失败");
        }

        return resultDatasVO;
    }
}
