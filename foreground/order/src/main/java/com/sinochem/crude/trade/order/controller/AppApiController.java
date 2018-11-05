package com.sinochem.crude.trade.order.controller;

import com.sinochem.crude.trade.order.domain.ContractShipBerth;
import com.sinochem.crude.trade.order.model.vo.ContractShipBerthVO;
import com.sinochem.crude.trade.order.service.ContractShipBerthService;
import com.sinochem.crude.trade.order.util.DictUtils;
import com.sinochem.it.b2b.common.result.ResultDatas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppApiController {
    Logger logger = LoggerFactory.getLogger(AppApiController.class);
    @Autowired
    private ContractShipBerthService contractShipBerthService;

    /**
     * 获取根据订单ID获取泊位信息
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/app/queryShipBerthList.json")
    @ResponseBody
    public ResultDatas queryShipBerthList(Long orderId){
        logger.info("APP获取订单泊位信息：" + orderId);
        ResultDatas resultDatas = new ResultDatas();
        List<ContractShipBerthVO> voList = new ArrayList<>();
        if(orderId != null){
            List<ContractShipBerth> shipBerths = contractShipBerthService.queryBycontractId(orderId);
            if(shipBerths != null && shipBerths.size() > 0){
                for(ContractShipBerth shipBerth : shipBerths){
                    ContractShipBerthVO shipBerthVO = new ContractShipBerthVO();
                    shipBerthVO.setId(shipBerth.getId());
                    shipBerthVO.setBerthName(shipBerth.getBerthName());
                    if(shipBerth.getBerthGrade() != null){
                        shipBerthVO.setBerthGrade(shipBerth.getBerthGrade() + "万吨");
                    }
                    if(shipBerth.getShipType() != null){
                        shipBerthVO.setShipType(DictUtils.getValue("shipTypes",shipBerth.getShipType().toString()).toString());
                    }
                    shipBerthVO.setBerthDesc(shipBerth.getBerthDesc());
                    shipBerthVO.setDraftLimitation(shipBerth.getDraftLimitation());
                    shipBerthVO.setContractId(shipBerth.getContractId());
                    voList.add(shipBerthVO);
                }
            }
        }
        logger.info("APP获取订单泊位信息结束 size：" + voList.size());
        resultDatas.setDatas(voList);
        return resultDatas;
    }
}
