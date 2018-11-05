package com.sinochem.crude.trade.wechat.controller.en;

import com.eyeieye.melody.web.url.URLBroker;
import com.google.gson.Gson;
import com.sinochem.crude.trade.wechat.domain.*;
//import com.sinochem.crude.trade.wechat.helper.Sha1Helper;
import com.sinochem.crude.trade.wechat.helper.StringHelper;
import com.sinochem.crude.trade.wechat.util.WeChatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.HtmlUtils;
import com.sinochem.crude.trade.wechat.constant.HttpGetUtil;
import com.sinochem.crude.trade.wechat.constant.UrlMapping;
import com.sinochem.crude.trade.wechat.constant.WechatConstant;
import com.sinochem.crude.trade.wechat.helper.HttpHelper;
import com.sinochem.crude.trade.wechat.service.AccessTokenService;
import com.sinochem.crude.trade.wechat.service.UserRelationService;
import com.sinochem.crude.trade.wechat.service.WechatLogService;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;

import org.apache.commons.codec.digest.DigestUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * author sunjianbo
 * date 2018/3/10 16:48
 * 微信开发平台接口
 */
@WithoutAccess
@Controller
public class WechatOpenApi {

    @Value("${wechat.appid}")
    String appid;
    @Value("${wechat.appsecret}")
    String appsecret;

    //    public static HttpConnectionManager httpClientManager = ContextLoader.getCurrentWebApplicationContext().getBean(HttpConnectionManager.class);
    //Log log = LogFactory.getLog(WechatOpenApi.class);
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserRelationService userRelationService;

    @Autowired
    WechatLogService wechatLogService;

    @Autowired
    URLBroker wechatServer;

    @Autowired
    private AccessTokenService accessTokenService;

    /**
     * 验证消息的确来自微信服务器(在服务器地址(URL)
     * 会调用)
     * 规则描述
     * 1. 将token、timestamp、nonce三个参数进行字典序排序
     * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
     * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     */
    @SuppressWarnings("deprecation")
    @ResponseBody
    @RequestMapping(value = UrlMapping.VERIFY_REQUEST, method = RequestMethod.GET)
    public String verifyRequest(String signature, String timestamp, String nonce, String echostr) {

        log.info("进入微信认证接口");
        List<String> list = new ArrayList<String>();
        list.add(nonce);
        list.add(timestamp);
        list.add(WechatConstant.TOKEN);
        Collections.sort(list);
        String shaHex = DigestUtils.shaHex(list.get(0) + list.get(1) + list.get(2));
        if (shaHex.equals(signature)) {
            return echostr;
        }
        return "verify fail";
    }

    Gson gson = new Gson();
    /**
     * 前端调用微信js需要进行加密
     *
     * @param timestamp
     * @param nonce
     * @param url
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getsha1.json", method = RequestMethod.GET)
    public ResultDatas<WXJSConfigResponse> getsha1(String url) {
        log.info("进入微信JS加密接口");
        String timestamp= String.valueOf(System.currentTimeMillis());
        String nonce=WechatConstant.TOKEN;
        WXJSConfigResponse response=new WXJSConfigResponse();
        response.setNonceStr(nonce);
        response.setTimestamp(timestamp);
        response.setAppId(appid);
        url = HtmlUtils.htmlUnescape(url);
        ResultDatas<WXJSConfigResponse> resultDatas = new ResultDatas<>();
        String jstoken = accessTokenService.getJSToken(false);
        if (!StringHelper.strisnull(jstoken)) {
            Map<String, String> shamap = new TreeMap<>(
                    new Comparator<String>() {
                        public int compare(String obj1, String obj2) {
                            // 升序排序
                            return obj1.compareTo(obj2);
                        }
                    });
            shamap.put("jsapi_ticket", jstoken);
            shamap.put("noncestr", nonce);
            shamap.put("timestamp", timestamp);
            shamap.put("url", url);
            String str = "";
            int i = 0;
            Set<String> keySet = shamap.keySet();
            Iterator<String> iter = keySet.iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                if (i > 0) {
                    str += "&";
                }
                i++;
                str += key + "=" + shamap.get(key);
            }
            String shaHex = DigestUtils.shaHex(str);
            log.info("sha加密前：" + str+"加密后："+shaHex);
            response.setSignature(shaHex);
            resultDatas.setDatas(response);
        } else {
            resultDatas.setFail("tick获取失败");
        }
        return resultDatas;
    }


    /**
     * 接收并处理微信客户端发送的请求
     */
    @SuppressWarnings("deprecation")
    @RequestMapping(value = UrlMapping.VERIFY_REQUEST, method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        log.info("======微信发送消息post请求收到=====");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/xml;charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            Map<String, String> map = WeChatUtil.parseXml(request);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            String event = map.get("Event");
            String message = "SUCCESS";
            if ("text".equals(msgType)) {   // 对文本消息进行处理
//                message =WeChatUtil.sendTextMsg(map,"你发送的消息是：" + content);
                log.info("公众号收到消息:" + content);
                //   message =WeChatUtil.sendTextMsg(map,"We have received your comments." );
                log.info(message);
            } else if ("event".equals(msgType.toLowerCase())) {
                if ("subscribe".equals(event.toLowerCase())) {
                    log.info(toUserName + ":绑定");
                    // message = WeChatUtil.sendTextMsg(map, "welcome sinochem");
                    //message="welcome sinochem";
                } else if ("unsubscribe".equals(event.toLowerCase())) {
                    log.info(toUserName + ":解绑");
                    //  message = WeChatUtil.sendTextMsg(map, "unsubscribe success");
                    // message="unsubscribe success";
                }
            }
            out.print(message);         // 将回应发送给微信服务器
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }


    /**
     * 微信入口
     * 用户同意授权 获取code 并跳转到回调地址
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "authorize.json")
    public void authorize(HttpServletRequest request, HttpServletResponse response) {
        try {
            log.info("-------------begin authorize.json-----------");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setContentType("text/html");
            response.setCharacterEncoding(WechatConstant.UTF8);
            request.setCharacterEncoding(WechatConstant.UTF8);
            //处理授权回调
            String redirect_uri = wechatServer.get(UrlMapping.CALL_BACK).toString();
//           String redirect_uri = URLEncoder.encode(UrlMapping.CALL_BACK_URL, "UTF-8");
            //简单获取openid的话参数response_type与scope与state参数固定写死即可
            StringBuffer url = new StringBuffer(UrlMapping.WECHAT_AUTHORIZE);
            url.append("?redirect_uri=" + redirect_uri);
            url.append("&appid=" + appid + "&response_type=code&scope=" + WechatConstant.SNSAPI_USERINFO + "&state=1#wechat_redirect");
//            url.append("&appid=" + WechatConstant.APP_ID + "&response_type=code&scope=" + WechatConstant.SNSAPI_USERINFO + "&state=1#wechat_redirect");
            log.info("----------------回调地址" + url + "------------");
            response.sendRedirect(url.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 微信回调地址
     *
     * @param request
     * @param response
     * @return openid和accesstoken
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = UrlMapping.CALL_BACK)
    public ResultDatas<UserRelation> callback(HttpServletRequest request, HttpServletResponse response) {
        ResultDatas<UserRelation> resultDatas = new ResultDatas<>();
        Map<String, String> userMap = new HashMap<>();
        try {
            response.setContentType("text/html");
            request.setCharacterEncoding(WechatConstant.UTF8);
            response.setCharacterEncoding(WechatConstant.UTF8);
            String code = request.getParameter("code");
            log.info("---------code=" + code + "-------------");
            Map<String, String> params = new HashMap<>();
            params.put("appid", appid);
            params.put("secret", appsecret);
            params.put("grant_type", "authorization_code");
            params.put("code", code);
            String authStr = HttpGetUtil.httpRequestToString(UrlMapping.WECHAT_ACCESS_TOKEN, params);
            WechatLogs wechatLogs = new WechatLogs();
            wechatLogs.setServicename("callback.json");
//            Gson gson = new Gson();
            wechatLogs.setDesc("微信回掉参数:" + gson.toJson(params) + "微信回掉返回:" + authStr);
            wechatLogs.setType(1);
            wechatLogService.addlog(wechatLogs);
            ObjectMapper objectMapper = new ObjectMapper();
            userMap = objectMapper.readValue(authStr, Map.class);
            log.info("-----------userMap=" + userMap + "----------");
            if (userMap.get("errcode") != null) {
                resultDatas.setFail("调用微信失败，" + userMap);
                log.error("调用微信失败，" + userMap);
                return resultDatas;
            }
        } catch (Exception e) {
            resultDatas.setFail("出现错误，" + e.getMessage());
            return resultDatas;
        }
        return getUserInfoByOpenid(userMap);

    }

//    //根据openid获取用户昵称和头像
//    private ResultDatas<UserRelation> getUserInfoByOpenid(Map<String, String> userMap) {
//        ResultDatas<UserRelation> resultDatas = new ResultDatas<>();
//        log.info("-------------begin getUserInfo-----------");
//        UserInfo userInfo = null;
//        UserRelation userRelation = null;
//        String openid = userMap.get("openid");
//        userRelation = new UserRelation();
//        UserRelation existUser = userRelationService.findByOpenid(openid);
//        existUser.setOpenid(openid);
//        log.info("--------------existUser="+existUser+"-------------------");
//        if(existUser!= null){
//            userRelation.setRole(existUser.getRole());
//            userRelation.setEpMemberId(existUser.getEpMemberId());
//            userRelation.setMemberId(existUser.getMemberId());
//        }
//
//        try {
//            CloseableHttpClient httpClient = httpClientManager.getHttpClient();
//            StringBuffer url = new StringBuffer(UrlMapping.WECHAT_USER_INFO);
//            url.append("?access_token=" + userMap.get("access_token"));
//            url.append("&openid=" + openid );
////            url.append("&openid=" + HtmlUtils.htmlUnescape(openid) );
//            url.append("&lang=zh_CN");
//            HttpGet httpGet = new HttpGet(url.toString());
//            CloseableHttpResponse response2 = httpClient.execute(httpGet);
//
//
//            HttpEntity entity = response2.getEntity();
//            if (entity != null) {
//                userInfo = new ObjectMapper().readValue(EntityUtils.toString(entity), UserInfo.class);
//                if(userInfo==null)
//                 log.info("userinfo is null");
//                if(userInfo.getNickname()!=null) {
//                    log.info("userinfo nickname" + userInfo.getNickname());
//                    userInfo.setNickname(new String(userInfo.getNickname().getBytes(WechatConstant.ISO), WechatConstant.UTF8));
//                }
//                //log
//                WechatLogs wechatLogs = new WechatLogs();
//                wechatLogs.setServicename("获取微信用户信息");
//                Gson gson=new Gson();
//                wechatLogs.setDesc("微信参数:"+url+"微信返回:" + gson.toJson(userInfo));
//                wechatLogs.setType(1);
//                wechatLogService.addlog(wechatLogs);
//
//
//                userRelation.setHeadimgurl(userInfo.getHeadimgurl());
//                userRelation.setOpenid(userInfo.getOpenid());
//                userRelation.setNickname(userInfo.getNickname());
//
//            }
//            log.info("-------------userRelation=" + userRelation.getNickname() + "----------------------");
//            resultDatas.setDatas(userRelation);
//        } catch (Exception e) {
//            resultDatas.setFail("调用微信失败:"+e.getMessage());
//          //  e.printStackTrace();
//        }
//
//        //log
//        WechatLogs wechatLogs = new WechatLogs();
//        wechatLogs.setServicename("回调接口返回");
//        Gson gson=new Gson();
//        wechatLogs.setDesc("返回:" + gson.toJson(resultDatas));
//        wechatLogs.setType(1);
//        wechatLogService.addlog(wechatLogs);
//        return resultDatas;
//    }

    /**
     * 刷新微信token
     *
     * @return
     */
    @RequestMapping(value = "genewwxtoken.json")
    public ResultDatas<String> getwxtoken() {

        String token = accessTokenService.getToken(true);
        ResultDatas<String> resultDatas = new ResultDatas<>();
        resultDatas.setDatas(token);
        return resultDatas;
    }


    //根据openid获取用户昵称和头像
    private ResultDatas<UserRelation> getUserInfoByOpenid(Map<String, String> userMap) {
        ResultDatas<UserRelation> resultDatas = new ResultDatas<>();
        log.info("-------------begin getUserInfo-----------");
        UserInfo userInfo = null;
        UserRelation userRelation = null;
        String openid = userMap.get("openid");
        log.info("微信的openid:" + openid);
        userRelation = new UserRelation();
        UserRelation existUser = userRelationService.findByOpenid(openid);
//        existUser.setOpenid(openid);
        log.info("--------------existUser=" + existUser + "-------------------");
        if (existUser != null) {
            userRelation.setRole(existUser.getRole());
            userRelation.setEpMemberId(existUser.getEpMemberId());
            userRelation.setMemberId(existUser.getMemberId());
        }
        userInfo = getuserinfo(openid).getDatas();
        userRelation.setHeadimgurl(userInfo.getHeadimgurl());
        userRelation.setOpenid(userInfo.getOpenid());
        userRelation.setNickname(userInfo.getNickname());
        resultDatas.setDatas(userRelation);
        //log
        WechatLogs wechatLogs = new WechatLogs();
        wechatLogs.setServicename("回调接口返回");
//        Gson gson = new Gson();
        wechatLogs.setDesc("返回:" + gson.toJson(resultDatas));
        wechatLogs.setType(1);
        wechatLogService.addlog(wechatLogs);

        return resultDatas;
    }

    /**
     * 获取目前关注公众号的全量openid
     *
     * @return
     */
    @RequestMapping(value = "getusers.json")
    public ResultDatas<WxGetUser> getusers() {
        ResultDatas<WxGetUser> resultDatas = new ResultDatas<>();
        Map<String, String> map = new HashMap<>();
        map.put("access_token", accessTokenService.getToken(false));
        HttpHelper httpHelper = new HttpHelper();
        WxGetUser wu = httpHelper.GetWXService(UrlMapping.WECHAT_USER_OPENID, map, WxGetUser.class);
        if (wu.valid()) {
            resultDatas.setDatas(wu);
        } else {
            resultDatas.setFail(wu.getErrmsg());
        }
        return resultDatas;
    }

    /**
     * 获取用户基本信息（包括UnionID机制）
     *
     * @return
     */
    @RequestMapping(value = "getuserinfo.json")
    public ResultDatas<UserInfo> getuserinfo(String openid) {
        ResultDatas<UserInfo> resultDatas = new ResultDatas<>();

        UserInfo userInfo = GetUserInfofun(openid, false);

        if (userInfo != null && userInfo.getErrcode() != null && (userInfo.getErrcode().equals("42001") || userInfo.getErrcode().equals("40001"))) {
            userInfo = GetUserInfofun(openid, true);
        }
        resultDatas.setDatas(userInfo);
        return resultDatas;
    }

    /**
     * 获取用户基本信息（包括UnionID机制）
     *
     * @return
     */
    private UserInfo GetUserInfofun(String openid, boolean regain) {
        Map<String, String> map = new HashMap<>();
        map.put("access_token", accessTokenService.getToken(regain));
        map.put("openid", openid);
        map.put("lang", "en");
        HttpHelper httpHelper = new HttpHelper();
        String wu = httpHelper.GetWXServiceMap(UrlMapping.WECHAT_USER_INFO, map);
//        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(wu, UserInfo.class);
        try {
            if (userInfo != null && (!StringHelper.strisnull(userInfo.getNickname()))) {
                userInfo.setNickname(new String(userInfo.getNickname().getBytes(WechatConstant.ISO), WechatConstant.UTF8));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return userInfo;
    }

    /**
     * 设置微信用户备注名
     *
     * @return
     */
    @RequestMapping(value = "updateremark.json", method = RequestMethod.POST)
    public ResultDatas<WxError> updateremark(String openid, String remark) {
        ResultDatas<WxError> resultDatas = new ResultDatas<>();
        Map<String, String> urlmap = new HashMap<>();
        urlmap.put("access_token", accessTokenService.getToken(false));

        Map<String, String> bobymap = new HashMap<>();
        bobymap.put("openid", openid);
        bobymap.put("remark", remark);

        HttpHelper httpHelper = new HttpHelper();
        WxError wu = httpHelper.PostService(UrlMapping.WECHAT_UPDATEREMARK, urlmap, bobymap, WxGetUser.class);

        resultDatas.setDatas(wu);

        return resultDatas;
    }
}
