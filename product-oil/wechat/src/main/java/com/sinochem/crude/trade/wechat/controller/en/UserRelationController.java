package com.sinochem.crude.trade.wechat.controller.en;

import java.beans.Transient;
import java.util.*;

import com.eyeieye.melody.web.url.URLBroker;
import com.google.gson.Gson;
import com.sinochem.crude.trade.member.enums.MemberRoles;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.service.PersonService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.remote.vo.PersonVo;
import com.sinochem.crude.trade.wechat.constant.CollectionUtils;
import com.sinochem.crude.trade.wechat.constant.WechatConstant;
import com.sinochem.crude.trade.wechat.domain.WXEnterpriseVo;
import com.sinochem.crude.trade.wechat.domain.WechatLogs;
import com.sinochem.crude.trade.wechat.helper.HttpHelper;
import com.sinochem.crude.trade.wechat.helper.StringHelper;
import com.sinochem.crude.trade.wechat.model.vo.UserInfoVO;
import com.sinochem.crude.trade.wechat.service.WechatLogService;
import com.sinochem.crude.trade.wechat.service.impl.WechatLogServiceImpl;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import com.sinochem.it.b2b.common.result.ResultDatas;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sinochem.crude.trade.wechat.constant.UrlMapping;
import com.sinochem.crude.trade.wechat.domain.UserRelation;
import com.sinochem.crude.trade.wechat.model.vo.UserRelationVO;
import com.sinochem.crude.trade.wechat.service.UserRelationService;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.member.access.WithoutAccess;

import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息接口
 */
@WithoutAccess
@Controller
public class UserRelationController {

//    public static HttpConnectionManager httpClientManager = ContextLoader.getCurrentWebApplicationContext().getBean(HttpConnectionManager.class);
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserRelationService userRelationService;
    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    PersonService personService;
    @Autowired
    WechatLogService wechatLogService;
    @Autowired
    URLBroker systemServer;
    public static final Log log = LogFactory.getLog(UserRelationController.class);


    /**
     * 获取公司信息
     */
    @RequestMapping(value = UrlMapping.ENTERPRISE, method = RequestMethod.GET)
    public Result enterprise(long memberId, @RequestParam(value = "openId", required = false)  String openId) {
        ResultDatas<EnterpriseVo> resultDatas = new ResultDatas<EnterpriseVo>();
        try {
            WechatLogs wechatLogs = new WechatLogs();
            wechatLogs.setServicename("enterprise.json");
            wechatLogs.setDesc("memberId:" + memberId);
            wechatLogs.setType(1);
            wechatLogService.addlog(wechatLogs);
            EnterpriseVo enterpriseVo = enterpriseService.getByMemberId(memberId);
            if (enterpriseVo != null) {
                Gson gson=new Gson();
                String jsonstr=gson.toJson(enterpriseVo);
                WXEnterpriseVo wxEnterpriseVo=gson.fromJson(jsonstr,WXEnterpriseVo.class);
                if(wxEnterpriseVo!=null) {
                    if(!StringHelper.strisnull(openId))
                    {
                        UserRelation userRelation=userRelationService.findByOpenid(openId);
                        if(userRelation!=null) {
                            wxEnterpriseVo.setUsername(userRelation.getUserName());
                        }
                    }
                    resultDatas.setDatas(wxEnterpriseVo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取公司信息error:" + e);
        }
        return resultDatas;

    }

    /**
     * 获取个人信息
     */
    @RequestMapping(value = UrlMapping.PERSON, method = RequestMethod.GET)
    public Result getperson(long memberId) {
        ResultDatas<PersonVo> resultDatas = new ResultDatas<PersonVo>();
        try {
            WechatLogs wechatLogs = new WechatLogs();
            wechatLogs.setServicename("person.json");
            wechatLogs.setDesc("memberId:" + memberId);
            wechatLogs.setType(1);
            wechatLogService.addlog(wechatLogs);
            PersonVo personVo = personService.getByMemberId(memberId);
            if (personVo != null) {
                resultDatas.setDatas(personVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取个人信息error:" + e);
        }
        return resultDatas;

    }

    /**
     * 解绑
     */
    @ResponseBody
    @RequestMapping(value = UrlMapping.UN_BINDING, method = RequestMethod.POST)
    public Result unbinding(@RequestBody UserRelationVO vo) {
        Result result = new Result();
        try {
            //校验
            if (vo == null) {
                throw new Exception("用户信息为空");
            }
            if (vo.getOpenid() == null) {
                throw new Exception("微信openid为空");
            }
            UserRelation userRelation = new UserRelation();
            userRelation.setOpenid(vo.getOpenid());
            int num= userRelationService.deleteRecordByOpenid(userRelation);
//            if(num<=0)
//            {
//                result.setFail("解绑失败");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("解绑出错:" + e);
        }
        return result;
    }


    /**
     * 登陆绑定接口
     *
     * @param response
     * @return
     */
//    @Transient
//    @ResponseBody
    @RequestMapping(value = UrlMapping.LOGIN, method = RequestMethod.POST)
    public ResultDatas<UserRelation> login(@RequestBody UserInfoVO userInfoVO, HttpServletResponse response) {
       return userRelationService.MemberLogin(userInfoVO);
//        ResultDatas<UserRelation> resultDatas = new ResultDatas<UserRelation>();
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        try {
//            //log
//            WechatLogs wechatLogs = new WechatLogs();
//            wechatLogs.setServicename("login.json");
//            Gson gson=new Gson();
//            String arvstr="登录参数"+gson.toJson(userInfoVO);
//            logger.info(arvstr);
//            wechatLogs.setDesc(arvstr);
//            wechatLogs.setType(1);
//            wechatLogService.addlog(wechatLogs);
//            if(StringHelper.strisnull(userInfoVO.getOpenid()))
//            {
//                resultDatas.setFail("openid is null");
//                return resultDatas;
//            }
//            String url=systemServer.get("/login.json").toString();
//            HttpHelper httpHelper=new HttpHelper();
//            Map<String, String> bobymap = new HashMap<>();
//            bobymap.put("userName", userInfoVO.getUserName());
//            bobymap.put("password", userInfoVO.getPassword() );
//            ResultDatas datas =httpHelper.PostService(url,null,bobymap,ResultDatas.class);
//            logger.info("登录app返回："+gson.toJson(datas));
//            int status = datas.getStatus();
//            if (status == 0) {
//                Map map = (Map) datas.getDatas();
//                int memberId = (Integer) map.get("memberId");
//                int epMemberId = (Integer) map.get("epMemberId");
//                List<String> roles = (List) map.get("roles");
//                String roleStr = CollectionUtils.listToString(roles);
//                UserRelation userRelation = new UserRelation();
//                userRelation.setUuid(UUID.randomUUID().toString());
//                userRelation.setMemberId(Long.parseLong(memberId + ""));
//                userRelation.setEpMemberId(Long.parseLong(epMemberId + ""));
//                userRelation.setRole(roleStr);
//                userRelation.setOpenid(userInfoVO.getOpenid());
//                userRelation.setAliveFlag("1");
//                userRelation.setVersion("1");
//                userRelation.setCreateDate(new Date());
//                userRelation.setLangVer("zh-EN");
//                userRelation.setUserName(userInfoVO.getUserName());
//                userRelation.setPassword(userInfoVO.getPassword());
//                //先解绑
//                userRelationService.deleteRecordByOpenid(userRelation);
//                userRelationService.insertRecord(userRelation);
//                resultDatas.setDatas(userRelation);
//            } else {
//
//                resultDatas.setFail(datas.getMessage());
//            }
//
//        } catch (Exception e) {
//            resultDatas.setFail("登陆失败");
//        }
//        //log
//        WechatLogs wechatLogs = new WechatLogs();
//        wechatLogs.setServicename("login.json");
//        Gson gson=new Gson();
//        wechatLogs.setDesc("返回参数:"+ gson.toJson(resultDatas));
//        wechatLogs.setType(2);
//        wechatLogService.addlog(wechatLogs);
//        return resultDatas;
    }

    /**
     * 通过openid拿到该用户的token 给接口使用
     * token 5分钟失效  redis缓存 openid token time 每次请求 更新time 设置自动过期时4分钟
     * @param openId
     * @return
     */
    @RequestMapping(value = "getmembertoken.json", method = RequestMethod.GET)
    public ResultDatas<String> gettoken(String openId)
    {

        String token=userRelationService.GetMemberToken(openId);
        ResultDatas<String> resultDatas=new ResultDatas<String>();
        resultDatas.setDatas(token);
        return resultDatas;
    }

//    /**
//     * 登陆绑定接口
//     *
//     * @param response
//     * @return
//     */
//    @Transient
//    @ResponseBody
//    @RequestMapping(value = UrlMapping.LOGIN, method = RequestMethod.POST)
//    public ResultDatas<UserRelation> login(@RequestBody UserInfoVO userInfoVO, HttpServletResponse response) {
//        ResultDatas<UserRelation> resultDatas = new ResultDatas<UserRelation>();
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        try {
//            //log
//            WechatLogs wechatLogs = new WechatLogs();
//            wechatLogs.setServicename("login.json");
//            Gson gson=new Gson();
//            String arvstr="登录参数"+gson.toJson(userInfoVO);
//            logger.info(arvstr);
//            wechatLogs.setDesc(arvstr);
//            wechatLogs.setType(1);
//            wechatLogService.addlog(wechatLogs);
//            if(StringHelper.strisnull(userInfoVO.getOpenid()))
//            {
//                resultDatas.setFail("openid is null");
//                return resultDatas;
//            }
//            if(userInfoVO.getUserName().equals("666"))
//            {
//              UserRelation userRelation=  userRelationService.findByOpenid(userInfoVO.getOpenid());
//              if(userRelation!=null)
//              {
//                  resultDatas.setDatas(userRelation);
//                  return  resultDatas;
//              }
//            }
//            //userInfoVO.setUserName(new String(userInfoVO.getUserName().getBytes(), WechatConstant.UTF8));
//            CloseableHttpClient httpClient = httpClientManager.getHttpClient();
//            HttpClientContext context = HttpClientContext.create();
//           // StringBuffer url = new StringBuffer(UrlMapping.LOGIN_URL);
//            String url=systemServer.get("/login.json").toString();
//            String sendContent = "{ \"userName\":\"" + userInfoVO.getUserName() + "\", \"password\":\"" + userInfoVO.getPassword() + "\"}";
//            StringEntity param = new StringEntity(sendContent);
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
//            httpPost.setEntity(param);
//            CloseableHttpResponse result = httpClient.execute(httpPost, context);
//            String entityStr = EntityUtils.toString(result.getEntity());
//            ResultDatas datas = new ObjectMapper().readValue(entityStr, ResultDatas.class);
//            logger.info("登录app返回："+gson.toJson(datas));
//            int status = datas.getStatus();
//            if (status == 0) {
//                Map map = (Map) datas.getDatas();
//                int memberId = (Integer) map.get("memberId");
//                int epMemberId = (Integer) map.get("epMemberId");
//                List<String> roles = (List) map.get("roles");
//                String roleStr = CollectionUtils.listToString(roles);
//                UserRelation userRelation = new UserRelation();
//                userRelation.setUuid(UUID.randomUUID().toString());
//                userRelation.setMemberId(Long.parseLong(memberId + ""));
//                userRelation.setEpMemberId(Long.parseLong(epMemberId + ""));
//                userRelation.setRole(roleStr);
//                userRelation.setOpenid(userInfoVO.getOpenid());
//                userRelation.setAliveFlag("1");
//                userRelation.setVersion("1");
//                userRelation.setCreateDate(new Date());
//                userRelation.setLangVer("zh-EN");
//               //先解绑
//                userRelationService.deleteRecordByOpenid(userRelation);
//
//                userRelationService.insertRecord(userRelation);
//                resultDatas.setDatas(userRelation);
//            } else {
//                resultDatas.setFail("登陆失败");
//            }
//
//        } catch (Exception e) {
//            resultDatas.setFail("登陆失败");
//        }
//        //log
//        WechatLogs wechatLogs = new WechatLogs();
//        wechatLogs.setServicename("login.json");
//        Gson gson=new Gson();
//        wechatLogs.setDesc("返回参数:"+ gson.toJson(resultDatas));
//        wechatLogs.setType(2);
//        wechatLogService.addlog(wechatLogs);
//        return resultDatas;
//    }
}
