package com.sinochem.crude.trade.order.remote.impl;

import com.sinochem.crude.trade.order.remote.OrderStatusService;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.order.status.dao.OrderStatusDao;
import com.sinochem.it.b2b.order.status.dao.OrderStatusItemDao;
import com.sinochem.it.b2b.order.status.domain.OrderStatus;
import com.sinochem.it.b2b.order.status.domain.OrderStatusDetail;
import com.sinochem.it.b2b.order.status.domain.OrderStatusItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("remoteOrderStatusServiceImpl")
public class OrderStatusServiceImpl implements OrderStatusService {
    @Autowired
    com.sinochem.it.b2b.order.status.service.OrderStatusService orderStatusService;
    @Autowired
    OrderStatusDao orderStatusDao;

    @Autowired
    OrderStatusItemDao orderStatusItemDao;

    @Override
    public void setOrderStatus(Long orderId, String itemCode, int status, String detail, Long operId) throws BizException {
        orderStatusService.setOrderStatus(orderId,itemCode,status,detail,operId);
    }

    @Override
    public void addDetail(Long order, String detail, Long operId) throws BizException {
        orderStatusService.addDetail(order,detail,operId);
    }

    @Override
    public List<StatusVO> getCurrentStatus(Long orderId) {
        List<OrderStatus> orderStatuses = orderStatusDao.getAllByOrderId(orderId);

        List<StatusVO> statusVOList = new ArrayList<>();
        //根据配置排序
        for (OrderStatusItem item : orderStatusItemDao.getAllItems()){
            for(OrderStatus orderStatus : orderStatuses){
                if(item.getCode().equals(orderStatus.getItemCode())) {
                    StatusVO statusVO = new StatusVO();
                    statusVO.setItem(orderStatus.getItemCode());
                    statusVO.setValue(orderStatus.getValue());
                    statusVOList.add(statusVO);
                }
            }

        }
        return statusVOList;
    }

    @Override
    public List<StatusDetailVO> getStatusDetail(Long orderId , String code) throws BizException {
        List<OrderStatusDetail> orderStatuses = null;
            orderStatuses = orderStatusService.getDetails(orderId,code);

        List<StatusDetailVO> orderStatusVoList = new ArrayList<>();
        for(OrderStatusDetail detail : orderStatuses){
            StatusDetailVO vo = new StatusDetailVO() ;
            vo.setCreateDate(detail.getCreateDate());
            vo.setCreater(detail.getCreater());
            vo.setItemCode(detail.getItemCode());
            vo.setValue(detail.getValue());
            vo.setDesc(detail.getDesc());
            orderStatusVoList.add(vo);
        }
        return orderStatusVoList;
    }

    @Override
    public int getCurrentStatus(Long orderId, String item) {
        if(orderId == null || item==null) return -1;
        OrderStatus details = orderStatusDao.getByOrderId(orderId,item);
        return details == null ? -1 : details.getValue();
    }
}
