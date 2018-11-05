package com.sinochem.crude.trade.wechat.controller.en;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinochem.crude.trade.wechat.constant.WechatConstant;

import com.sinochem.crude.trade.wechat.dao.WechatSendmsgLogsMapper;
import com.sinochem.crude.trade.wechat.domain.*;
import com.sinochem.crude.trade.wechat.helper.HttpHelper;
import com.sinochem.crude.trade.wechat.service.WechatSendmsgLogService;
import com.sinochem.it.b2b.common.result.ResultDatas;
import org.apache.commons.logging.Log;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;

import com.sinochem.crude.trade.wechat.constant.AccessTokenUtil;
import com.sinochem.crude.trade.wechat.constant.UrlMapping;
import com.sinochem.crude.trade.wechat.service.AccessTokenService;
import com.sinochem.crude.trade.wechat.service.UserRelationService;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.member.access.WithoutAccess;

@WithoutAccess
@Controller
public class NotificationController {

//    public static final Log log = LogFactory.getLog(NotificationController.class);
    private Logger logger = LoggerFactory.getLogger(getClass());
//    public static HttpConnectionManager httpClientManager = ContextLoader.getCurrentWebApplicationContext().getBean(HttpConnectionManager.class);

//    Gson gson=new Gson();
    @Autowired
    private UserRelationService userRelationService;

    @Autowired
    private AccessTokenService accessTokenService;
 @Autowired
 private WechatSendmsgLogService wechatSendmsgLogService;



    @RequestMapping(value = "wxsendmsglogs", method = RequestMethod.GET)
    public ResultDatas<List<WechatSendmsgLogs>> getWXSendMsglogs(@RequestParam(value = "start", required = false, defaultValue = "0") int start, @RequestParam(value = "end", required = false, defaultValue = "10") int end) {
        List<WechatSendmsgLogs> list = wechatSendmsgLogService.selectall(start, end);
        ResultDatas<List<WechatSendmsgLogs>> resultDatas = new ResultDatas<List<WechatSendmsgLogs>>();
        resultDatas.setDatas(list);
        return resultDatas;
    }

    /**
     * 消息推送接口
     *
     * @param
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = UrlMapping.NOTIFICATION, method = RequestMethod.POST)
    public ResultDatas<List<WechatSendmsgLogs>> notification(@RequestBody SendMessage sendMessage) {
        ResultDatas<List<WechatSendmsgLogs>> res = new ResultDatas<List<WechatSendmsgLogs>>();
        List<WechatSendmsgLogs> logslist=new ArrayList<WechatSendmsgLogs>();
        try {
            logger.info(sendMessage.getMemberId()+"微信发送消息:"+sendMessage.getContent());
            //根据MemberId获取openid
            UserRelation userRelation = new UserRelation();
            userRelation.setMemberId(sendMessage.getMemberId());
            List<UserRelation> list = userRelationService.queryByEntitys(userRelation);
            for (UserRelation userRelation2 : list) {
                //调用微信API客服发消息
                WechatSendmsgLogs  wechatSendmsgLogs=new WechatSendmsgLogs();
                 wechatSendmsgLogs.setContent(sendMessage.getContent());
                  wechatSendmsgLogs.setOpenid(userRelation2.getOpenid());
                  wechatSendmsgLogs.setMemberid(sendMessage.getMemberId());
                  wechatSendmsgLogs.setReceivetime(new Date());
                logslist.add(wechatSendmsgLogs);

            }

            for(WechatSendmsgLogs item:logslist)
            {
                if(sendwxmessage(item,false))
                {
                    sendwxmessage(item,true);
                }
                wechatSendmsgLogService.insertSelective(item);

            }
            res.setDatas(logslist);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用消息推送接口时错误:" + e);
        }
        return res;
    }
/*
是否是token导致失败重发
利用客服接口-发消息
 */
    private boolean sendwxmessage(WechatSendmsgLogs wechatSendmsgLogs,boolean regain) {
        try {
            //openid==null or content==null 不发送
            if(StringHelper.isNullOrEmptyString(wechatSendmsgLogs.getOpenid())||StringHelper.isNullOrEmptyString(wechatSendmsgLogs.getContent()))
                return  false;
         //   String accessToken = accessTokenService.getToken(regain);
            Map<String, String> urlmap = new HashMap<>();
            urlmap.put("access_token", accessTokenService.getToken(regain));
            Map<String, String> bobymap = new HashMap<>();
            bobymap.put("touser", wechatSendmsgLogs.getOpenid());
            bobymap.put("msgtype", "text");
            WxSendText wxsendtext=new WxSendText();
//            wxsendtext.setContent(new String(wechatSendmsgLogs.getContent().getBytes(), WechatConstant.ISO));
            wxsendtext.setContent(wechatSendmsgLogs.getContent());
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();//解决单引号转码问题
            bobymap.put("text", gson.toJson(wxsendtext));
            HttpHelper httpHelper = new HttpHelper();
            WxError wxresponse = httpHelper.PostService(UrlMapping.SEND_MSG, urlmap, bobymap, WxError.class);
                if(wxresponse!=null) {
                    wechatSendmsgLogs.setFailcode(wxresponse.getErrcode().toString());
                    wechatSendmsgLogs.setFailmsg(wxresponse.getErrmsg());
                    wechatSendmsgLogs.setSendtime(new Date());
                    //40001 如果code 重新发送一次
                    if (!StringHelper.isNullOrEmptyString(wxresponse.getErrcode().toString()) && (wxresponse.getErrcode().toString().equals("42001") || wxresponse.getErrcode().toString().equals("40001"))) {
                        return true;
                    }
                    if(wxresponse.getErrcode().equals("0"))
                    {
                        wechatSendmsgLogs.setStatus(1);

                    }
                    else
                    {
                        int failnum=wechatSendmsgLogs.getFailnum()==null?1:wechatSendmsgLogs.getFailnum()+1;
                        wechatSendmsgLogs.setFailnum(failnum);
                    }
                }
        } catch (Exception e) {
            logger.error("调用消息推送接口时错误1",e);
            return false;
            // e.printStackTrace();

        }
        return false;
    }


    private  class WxSendText{
        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        private  String content;
    }



}
