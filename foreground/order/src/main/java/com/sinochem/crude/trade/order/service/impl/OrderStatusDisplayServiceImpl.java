package com.sinochem.crude.trade.order.service.impl;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.order.dao.OrderBizTypeMapper;
import com.sinochem.crude.trade.order.dao.OrderStatusMapper;
import com.sinochem.crude.trade.order.service.OrderStatusDisplayService;
import com.sinochem.crude.trade.order.model.vo.OrderStatusVO;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.order.status.dao.OrderStatusItemDao;
import com.sinochem.it.b2b.order.status.domain.OrderStatus;
import com.sinochem.it.b2b.order.status.domain.OrderStatusItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderStatusDisplayServiceImpl implements OrderStatusDisplayService {

    @Autowired
    OrderStatusMapper orderStatusMapper;
    @Autowired
    OrderStatusItemDao orderStatusItemDao;

    @Autowired
    OrderBizTypeMapper orderBizTypeMapper;

    @Override
    public List<OrderStatusVO> getStatusList(Long orderId) throws BizException {
        List<OrderStatusItem> orderStatusItemList = orderStatusItemDao.getAllItems();
        List<OrderStatus> orderStatusList = orderStatusMapper.findAllByOrderId(orderId);
        List<OrderStatusVO> orderStatusRtnList = new ArrayList<OrderStatusVO>();

        for(OrderStatusItem item : orderStatusItemList){
            for(OrderStatus orderStatus : orderStatusList){
                if(item.getCode().equals(orderStatus.getItemCode())){
                    String describe =  VisitorLocale.getByCurrentLanguage(new String[][]{new String[]{"zh",item.getDescribe()},new String[]{"en",item.getDescribeEn()}});
                    String valueDesc = null;
                    for (OrderStatusItem.Entity entity : item.getEntities()){
                        if(entity.getValue() == orderStatus.getValue()){
                            valueDesc = VisitorLocale.getByCurrentLanguage(new String[][]{new String[]{"zh",entity.getDescribe()},new String[]{"en",entity.getDescribeEn()}});
                            break;
                        }
                    }
                    orderStatusRtnList.add(new OrderStatusVO(orderStatus,describe,valueDesc));
                }
            }
        }

        return orderStatusRtnList;

    }
}
