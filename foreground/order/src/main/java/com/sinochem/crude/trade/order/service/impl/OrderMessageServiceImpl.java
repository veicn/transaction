package com.sinochem.crude.trade.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.order.domain.ContractRelevanter;
import com.sinochem.crude.trade.order.service.ContractRelevanterService;
import com.sinochem.crude.trade.order.service.OrderMessageService;
import com.sinochem.it.b2b.common.CommonUtils;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.order.status.dao.OrderStatusItemDao;
import com.sinochem.it.b2b.order.status.domain.OrderStatus;
import com.sinochem.it.b2b.order.status.domain.OrderStatusItem;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;

@Service
public class OrderMessageServiceImpl  implements OrderMessageService{
    @Autowired
    private ContractRelevanterService relevanterService;
    @Autowired
    private URLBroker messageServer;
    @Autowired
    private OrderStatusItemDao orderStatusItemDao;

    private static final Logger logger = LoggerFactory.getLogger(OrderMessageServiceImpl.class);


    @Override
    public String sendOrderChangeMessage(Long orderId, String message) {
        try {
            ContractRelevanter relevanter = relevanterService.query(orderId, "T");
            if(relevanter!=null) sendOrderChangeMessageTo(relevanter.getEMemberId(), message);
            relevanter = relevanterService.query(orderId, "S");
            if(relevanter!=null) sendOrderChangeMessageTo(relevanter.getEMemberId(), message);
            relevanter = relevanterService.query(orderId, "B");
            if(relevanter!=null) sendOrderChangeMessageTo(relevanter.getEMemberId(), message);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String sendOrderChangeMessage(OrderStatus orderStatus) {

        String itemCode = orderStatus.getItemCode();
        OrderStatusItem.Entity entity = orderStatusItemDao.getEntity(itemCode,orderStatus.getValue());
        OrderStatusItem item = orderStatusItemDao.getItem(itemCode);

        return sendOrderChangeMessage(orderStatus.getOrderId(),"" +item.getDescribe()+"状态发生变化:"+entity.getDescribe());
    }

    class Message{

        private String toId;

        private String fromId;

        private int level;

        private String content ;

        private Long createUser;

        private String effectTime;

        public String getToId() {
            return toId;
        }

        public void setToId(String toId) {
            this.toId = toId;
        }

        public String getFromId() {
            return fromId;
        }

        public void setFromId(String fromId) {
            this.fromId = fromId;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Long getCreateUser() {
            return createUser;
        }

        public void setCreateUser(Long createUser) {
            this.createUser = createUser;
        }

        public String getEffectTime() {
            return effectTime;
        }

        public void setEffectTime(String effectTime) {
            this.effectTime = effectTime;
        }
    }


    public String sendOrderChangeMessageTo(Long targetId , String message){
        CloseableHttpClient httpClient = null;//connectionManager.getHttpClient();
        // 创建POST请求
        HttpPost httpPost = new HttpPost();
        httpPost.setURI(URI.create(messageServer.get("message/push.json").toString()));
        Message msg = new Message();
        msg.setFromId(""+CommonUtils.PLANTFORM_MEMBER_ID);
        msg.setToId(""+targetId);
        msg.setContent(message);
        msg.setEffectTime("2018-01-01");
        msg.setCreateUser(CommonUtils.PLANTFORM_MEMBER_ID);
        httpPost.setHeader("Content-Type","application/json;charset=utf-8");
        try {
            ByteArrayEntity entity = new ByteArrayEntity(JSONObject.toJSONString(msg).getBytes("utf-8"));
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(httpPost, HttpClientContext.create());
            if(!(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)){
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                response.getEntity().writeTo(outputStream);
               logger.error( outputStream.toString() );
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("发送消息时发生错误",e);
        } catch (ClientProtocolException e) {
            logger.error("发送消息时发生错误",e);
        } catch (IOException e) {
            logger.error("发送消息时发生错误",e);
        } catch (Exception e) {
            logger.error("发送消息时发生错误",e);
        }
        return "OK";
    }
}
