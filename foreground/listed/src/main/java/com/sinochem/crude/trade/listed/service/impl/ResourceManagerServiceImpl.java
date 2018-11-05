package com.sinochem.crude.trade.listed.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.sinochem.crude.trade.listed.constant.Constant;
import com.sinochem.crude.trade.listed.dao.*;
import com.sinochem.crude.trade.listed.domain.*;
import com.sinochem.crude.trade.listed.enums.DealType;
import com.sinochem.crude.trade.listed.enums.DemandStatus;
import com.sinochem.crude.trade.listed.enums.DemandType;
import com.sinochem.crude.trade.listed.enums.SerialNumberBizType;
import com.sinochem.crude.trade.listed.domain.Demand;
import com.sinochem.crude.trade.listed.service.DemandMessageService;
import com.sinochem.crude.trade.listed.service.ResourceManagerService;
import com.sinochem.crude.trade.listed.helper.SerialNumberUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sijliu on 20/11/2017.
 */
@Service
@Transactional
public class ResourceManagerServiceImpl implements ResourceManagerService {

    @Autowired
    private DemandMapper demandMapper;

    @Autowired
    private DemandRelevanterMapper demandRelevanterMapper;

    @Autowired
    private DemandShipBerthMapper demandShipBerthMapper;

    @Autowired
    private DemandImagesMapper demandImagesMapper;

    @Autowired
    private DemandShipMapper demandShipMapper;



    @Autowired
    private DemandMessageService demandMessageService;


    @Override
    public void batchUpdateStatus(String ids, int status) throws BizException{
        if (StringUtils.isNotEmpty(ids)) {
            String[] idArr = ids.split(",");
            Demand demand = null;
            for (String idStr : idArr) {
                demand = new Demand();
                demand.setId(Long.valueOf(idStr));
                demand.setStatus(status);
                if (status == DemandStatus.DEMAND_STATUS_2.getCode()) {
                    demand.setPubDate(new Date());
                }
                demandMapper.updateByPrimaryKeySelective(demand);
                if (status == DemandStatus.DEMAND_STATUS_2.getCode()) {
                    //发送消息
                }
            }
            for (String idStr : idArr) {
                if (status == DemandStatus.DEMAND_STATUS_2.getCode()) {
                    //发送消息
                    demandMessageService.demandDirectionalRelease(Long.valueOf(idStr));
                }
            }
        }

    }

    @Override
    public Demand getDemandByKey(Long demandId) {
        return demandMapper.selectByPrimaryKey(demandId);
    }

    @Override
    public String batchUpdateStatused(String ids, int status) throws BizException {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotEmpty(ids)) {
            String[] idArr = ids.split(",");
            for (String idStr : idArr) {
                Demand demand = demandMapper.selectByPrimaryKey(Long.valueOf(idStr));
                if(demand != null && demand.getStatus() == status){
                    sb.append(demand.getUuid()).append(",");
                }
            }
        }
        return sb.toString();
    }


}
