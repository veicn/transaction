package com.sinochem.crude.trade.broker.controller;

import com.github.pagehelper.Page;
import com.google.gson.Gson;
import com.sinochem.crude.trade.blockchain.domain.TBrokerAppoint;
import com.sinochem.crude.trade.broker.model.vo.ForwarderListVO;
import com.sinochem.crude.trade.broker.model.vo.TBrokerAppointQueryVO;
import com.sinochem.crude.trade.broker.service.impl.ForwarderService;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetWXVO;
import com.sinochem.crude.trade.transaction.model.vo.ResultDatas;
import com.sinochem.crude.trade.web.controller.MyContractDetailController;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.hibernate.validator.internal.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/9/6 15:33
 * @Version: [v1.0]
 */
@Controller
@RequestMapping("/blockchain/broker/forwarder")
public class ForwarderController {
    private final Logger logger = LoggerFactory.getLogger(ForwarderController.class);
    @Autowired
    private ForwarderService forwarderService;
    @Autowired
    private MyContractDetailController myContractDetailController;

    Gson gson = new Gson();

    /**
     * 货代委托查询列表
     *
     * @param tBrokerAppoint
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = "/list.json")
    @ResponseBody
    public ResultDatas GetforwarderList(@RequestBody TBrokerAppointQueryVO tBrokerAppoint) {
        ResultDatas resultDatas = new ResultDatas();
        try {
            List<ForwarderListVO> resultlist = new ArrayList<ForwarderListVO>();
            ForwarderListVO vo = null;
            Page<TBrokerAppoint> list = forwarderService.getTBrokerAppointPage(tBrokerAppoint);
            for (int i = 0; i < list.size(); i++) {
                String uuid = list.get(i).getDealUuid();
                if (!StringHelper.isNullOrEmptyString(uuid)) {
                    com.sinochem.it.b2b.common.result.ResultDatas<ContractSheetWXVO> contdata = myContractDetailController.getContractSheetListByeEpmemberid(uuid);
                    if (0 == contdata.getStatus()) {
                        vo = new ForwarderListVO();
                        vo.setNomineeCompany(list.get(i).getAppointEnterpriseName());
                        if (contdata.getDatas() != null) {
                            vo.setCategory(contdata.getDatas().getProductCategory());
                            vo.setSpecification(contdata.getDatas().getProductSpecification());
                            vo.setQuantity(contdata.getDatas().getQuantity() + " " + contdata.getDatas().getPricingUnit());
                            vo.setDealNO(contdata.getDatas().getSerialNumber());
                            vo.setDealUuid(contdata.getDatas().getUuid());
                            vo.setLaycan(com.sinochem.crude.trade.transaction.utils.DateUtil.ENDateCH(contdata.getDatas().getLaycanStartDateAsString()) + " - " + com.sinochem.crude.trade.transaction.utils.DateUtil.ENDateCH(contdata.getDatas().getLaycanEndDateAsString()));
                        }
                        if ("1".equals(list.get(i).getStatus())) {
                            vo.setForwarderStatus("未完成");
                        } else if ("2".equals(list.get(i).getStatus())) {
                            vo.setForwarderStatus("已完成");
                        } else {
                            vo.setForwarderStatus(list.get(i).getStatus());
                        }
                        resultlist.add(vo);
                    } else {
                        logger.info("货代委托查询订单信息接口失败:" + gson.toJson(contdata));
                    }
                }
            }
            resultDatas.setPageNum(list.getPageNum());
            resultDatas.setPageSize(list.getPageSize());
            resultDatas.setTotal(list.getTotal());
            resultDatas.setPageCount(list.getPages());
            resultDatas.setDatas(resultlist);
        } catch (Exception e) {
            logger.error("货代委托查询列表失败", e);
            resultDatas.setFail("货代委托查询列表失败");
            resultDatas.setDatas(e.getMessage());
        }
        return resultDatas;
    }


    /**
     * 货代委托查询详情
     *
     * @param
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = "/detail.json")
    public ResultDatas GetforwarderDetail(String uuid) {
        ResultDatas resultDatas = new ResultDatas();
        try {
            if (!StringHelper.isNullOrEmptyString(uuid)) {
                com.sinochem.it.b2b.common.result.ResultDatas<ContractSheetWXVO> contdata = myContractDetailController.getContractSheetListByeuuideEN(uuid);
                if (0 == contdata.getStatus()) {
                    resultDatas.setDatas(contdata.getDatas());
                } else {
                    logger.info("货代委托查询订单信息接口失败:" + gson.toJson(contdata));
                }
            } else {
                resultDatas.setFail("uuid不可以为空");
            }
        } catch (Exception e) {
            logger.error("货代委托查询失败", e);
            resultDatas.setFail("货代委托查询失败");
            resultDatas.setDatas(e.getMessage());
        }
        return resultDatas;
    }
}
