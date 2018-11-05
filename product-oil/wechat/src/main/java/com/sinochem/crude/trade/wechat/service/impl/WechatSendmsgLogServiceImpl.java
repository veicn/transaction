package com.sinochem.crude.trade.wechat.service.impl;

import com.google.gson.Gson;
import com.sinochem.crude.trade.wechat.constant.UrlMapping;
import com.sinochem.crude.trade.wechat.constant.WechatConstant;
import com.sinochem.crude.trade.wechat.dao.WechatSendmsgLogsMapper;
import com.sinochem.crude.trade.wechat.domain.WechatSendmsgLogs;
import com.sinochem.crude.trade.wechat.helper.ForkJoinSendmsg;
import com.sinochem.crude.trade.wechat.service.AccessTokenService;
import com.sinochem.crude.trade.wechat.service.WechatSendmsgLogService;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.hibernate.validator.internal.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import java.util.Date;
import java.util.List;

@Service
public class WechatSendmsgLogServiceImpl implements WechatSendmsgLogService {

    @Autowired
    private WechatSendmsgLogsMapper wechatSendmsgLogsMapper;

    @Override
    public List<WechatSendmsgLogs> selectall(int start, int end) {
        return wechatSendmsgLogsMapper.selectall(start,end);
    }
    @Autowired
    private AccessTokenService accessTokenService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public int insertSelective(WechatSendmsgLogs record) {
        int num=0;
        try {
            num = wechatSendmsgLogsMapper.insertSelective(record);
        }
        catch (Exception e)
        {
            logger.error("WechatSendmsgLogs插入",e);
        }
        return num;
    }
    @Override
    public List<WechatSendmsgLogs> ForkJoinSendMsgList(List<WechatSendmsgLogs> logslist,HttpConnectionManager httpClientManager) {
//        for (WechatSendmsgLogs item : logslist) {
//            if (sendwxmessage(item,httpClientManager)) {
//                sendwxmessage(item,httpClientManager);
//            }
//            insertSelective(item);Join
//        }
        ForkJoinSendmsg forkJoinSendmsg=new ForkJoinSendmsg(logslist,httpClientManager);
        logslist=forkJoinSendmsg.WXSendmsg();
        return logslist;
    }

    @Override
    public List<WechatSendmsgLogs> SendMsgList(List<WechatSendmsgLogs> logslist,HttpConnectionManager httpClientManager) {
        for (WechatSendmsgLogs item : logslist) {
            if (sendwxmessage(item,httpClientManager)) {
                sendwxmessage(item,httpClientManager);
            }

            insertSelective(item);  //forjoin 去掉 在外层实现
        }

        return logslist;
    }

    /*
    是否是token导致失败重发
     */
    private boolean sendwxmessage(WechatSendmsgLogs wechatSendmsgLogs,HttpConnectionManager httpClientManager) {
        try {

            //openid==null or content==null 不发送
            if (StringHelper.isNullOrEmptyString(wechatSendmsgLogs.getOpenid()) || StringHelper.isNullOrEmptyString(wechatSendmsgLogs.getContent()))
                return false;


            String accessToken = accessTokenService.getToken(false);
            CloseableHttpClient httpClient = httpClientManager.getHttpClient();
            StringBuffer url = new StringBuffer(UrlMapping.SEND_MSG);
            url.append("?access_token=" + accessToken);
            HttpPost httpPost = new HttpPost(url.toString());
            String sendContent = "{ \"touser\":\"" + wechatSendmsgLogs.getOpenid() + "\", \"msgtype\":\"text\", \"text\": { \"content\":\"" + new String(wechatSendmsgLogs.getContent().getBytes(), WechatConstant.ISO) + "\" }}";
            StringEntity entity = new StringEntity(sendContent);
            httpPost.setEntity(entity);
            wechatSendmsgLogs.setSendtime(new Date());//发送时间
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity rsentity = response.getEntity();
            String result = EntityUtils.toString(rsentity, "UTF-8");
            if (!StringHelper.isNullOrEmptyString(result)) {
                logger.info("微信推送消息返回:" + result);

                Gson gson = new Gson();
                WXResponse wxresponse = gson.fromJson(result, WXResponse.class);
                if (wxresponse != null) {
                    wechatSendmsgLogs.setFailcode(wxresponse.getErrcode());
                    wechatSendmsgLogs.setFailmsg(wxresponse.getErrmsg());
                    int failnum = wechatSendmsgLogs.getFailnum() == null ? 0 : wechatSendmsgLogs.getFailnum() + 1;
                    wechatSendmsgLogs.setFailnum(failnum);
                    //40001 如果code 重新发送一次
                    if (!StringHelper.isNullOrEmptyString(wxresponse.getErrcode()) && (wxresponse.getErrcode().equals("42001") || wxresponse.getErrcode().equals("40001"))) {
                        return true;
                    }
                    if(wxresponse.getErrcode().equals("0"))
                    {
                        wechatSendmsgLogs.setStatus(1);
                    }
                }
            }
        } catch (Exception e) {
            return false;
            // e.printStackTrace();
            // log.error("调用消息推送接口时错误:" + e);
        }
        return false;
    }

    private class WXResponse {
        public String getErrcode() {
            return errcode;
        }

        public void setErrcode(String errcode) {
            this.errcode = errcode;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }

        private String errcode;
        private String errmsg;
    }


}
