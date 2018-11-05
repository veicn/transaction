package com.sinochem.crude.trade.member.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.util.ArrayUtil;
import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.EnterpriseContact;
import com.sinochem.crude.trade.member.domain.Person;
import com.sinochem.crude.trade.member.service.EnterpriseService;
import com.sinochem.crude.trade.member.service.MemberCredentialsService;
import com.sinochem.crude.trade.member.service.PersonService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 成品油controller
 * zhaoyulong
 */
@Controller
public class productOilController {
    @Autowired
    URLBroker systemServer;
    @Autowired
    URLBroker memberServer;
    @Autowired
    private MemberCredentialsService memberCredentialsService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private PersonService personService;
    @Autowired
    private URLBroker messageServer;

   // private URLBroker listedServer;

    @Value("${make.an.inquiry}")
    private String makeaninquiry;
    @Value("${make.an.buyersOrder}")
    private String buyersOrder;
    @Value("${make.an.purchasingInfor}")
    private String purchasingInfor;
    @Value("${make.an.salesInformation}")
    private String salesInformation;
    @Value("${make.an.sellerOrder}")
    private String sellerOrder;
    @Value("${make.an.theQuotation}")
    private String theQuotation;

    @RequestMapping("om/ifroms/make_an_inquiry.htm")
    public void findmakeaninquiry(ModelMap modelMap){

        modelMap.put("makeaninquiry",makeaninquiry);
    }
    @RequestMapping("om/ifroms/buyersOrder.htm")
    public void findbuyersOrder(ModelMap modelMap){

        modelMap.put("buyersOrder",buyersOrder);
    }
    @RequestMapping("om/ifroms/purchasingInfor.htm")
    public void findpurchasingInfor(ModelMap modelMap){

        modelMap.put("purchasingInfor",purchasingInfor);
    }
    @RequestMapping("om/ifroms/salesInformation.htm")
    public void findSalesInformation(ModelMap modelMap){

        modelMap.put("salesInformation",salesInformation);
    }
    @RequestMapping("om/ifroms/sellerOrder.htm")
    public void findsellerOrder(ModelMap modelMap){

        modelMap.put("sellerOrder",sellerOrder);
    }
    @RequestMapping("om/ifroms/theQuotation.htm")
    public void findmatheQuotation(ModelMap modelMap){

        modelMap.put("theQuotation",theQuotation);
    }

    @Autowired
    private HttpConnectionManager httpConnectionManager;
    @Value("${main.site.domain}")
    private String domain;

    @RequestMapping("product/center/tologinVm.htm")
    @WithoutAccess
    public ModelAndView toProductMyVm(HttpServletResponse response, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        Cookie cookie = new Cookie("_theme","product");
        cookie.setDomain(domain);
        cookie.setPath("/");
        response.addCookie(cookie);
        Cookie cookie1 = new Cookie("_l_","en_US");
        cookie1.setDomain(domain);
        cookie1.setPath("/");
        response.addCookie(cookie1);
        //modelAndView.setViewName("redirect:product/center/toMember");
        return modelAndView;
    }

    @RequestMapping("product/center/productToMember.htm")
    public ModelAndView toLoaginMyVm(HttpServletResponse response, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Cookie cookie1 = new Cookie("_l_","en_US");
        cookie1.setDomain(domain);
        cookie1.setPath("/");
        response.addCookie(cookie1);
        Cookie cookie = new Cookie("_theme","product");
       // cookie.setDomain(".mycrudeoil.com");
       cookie.setDomain(domain);
        cookie.setPath("/");
        response.addCookie(cookie);
        //modelAndView.setViewName("redirect:product/center/toMember");
        return modelAndView;
    }
    @RequestMapping("product/logout.htm")
    public  ModelAndView outLog(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();

        //清楚session用户记录
        request.getSession().setAttribute("_user",null);

        mav.setViewName("redirect:product/product/register.htm");
        return mav;
    }

    /**
     * 个人信息补充页面
     *
     * @param user
     * @param modelMap
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = "product/center/member/personFill", method = RequestMethod.GET)
    public void personFill(CurrentUser user, ModelMap modelMap) {
        if (user != null) {
            modelMap.put("user", user);
            Person person = personService.getInfoByMemberId(user.getMemberId());
            modelMap.put("item", person == null ? new Person() : person);
        } else {
            modelMap.put("item", new Person());
        }
    }

    /**
     * 企业信息补充页面
     *
     * @param user
     * @param modelMap
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = "product/register/enterprise", method = RequestMethod.GET)
    public void enterpriseFill(CurrentUser user, ModelMap modelMap) {
        if (user != null) {
            modelMap.put("user", user);
            Enterprise enterprise = enterpriseService.getByMemberId(user.getMemberId());
            if (enterprise == null){
                enterprise = new Enterprise();
            }
            modelMap.put("enterprise", enterprise);
        } else {
            modelMap.put("enterprise", new Enterprise());
        }
        modelMap.put("credentials", memberCredentialsService.getCredentials(true));
        modelMap.put("contact", new EnterpriseContact());
    }


    @RequestMapping("product/center/info")
    public String info(CurrentUser user) {
        if ( ArrayUtil.contains(user.getRoles() ,"enterprise") ){
            return "redirect:" + memberServer.get("product/center/member/enterpriseFill.htm");
        } else {
            return "redirect:" + memberServer.get("product/center/member/personFill.htm");
        }
    }

    @RequestMapping("product/center/my")
    public String enterpersiceDetail(CurrentUser user, ModelMap modelMap) {
        if(user == null || user.getMemberId() == null){
            return "redirect:" + systemServer.get("login.htm");
        }
        else if(user.getEpMemberId() == null) {
            return "redirect:" + systemServer + "/center/safety.htm";
        }

        return null;
    }

    /**
     * 查询消息列表
     * @return
     */
    @RequestMapping("/mosken/msgProduct.htm")
    public  Object selectMssageProduct(CurrentUser user,ModelMap map){
        if (null == user) {

        }
        String strues = "removeHtmlTag=true";
        String st = "/message/countUnreadMsg/"+user.getMemberId()+".json";
        String str="toId="+user.getMemberId()+"&removeHtmlTag=true";

        try {
            //查询未读信息
            String s = messageServer.get(st).toString();
            JSONObject jsonObject = this.httpGet(strues, s);
            String s1 = jsonObject.get("status")==null ? "1":jsonObject.get("status").toString();
            if (s1.equals("0")) {
                String s2 = jsonObject.get("datas") == null ? "1" : jsonObject.get("datas").toString();
                map.put("total",s2);
            }
            String url = messageServer.get("/message/webMsgList.json").toString();
            JSONObject jsonObject1 = this.httpGet(str, url);
            String status = jsonObject1.get("status") == null ? "1" : jsonObject1.get("status").toString();

            if(status.equals("0")){

                if(null != jsonObject1.get("datas")){
                    Object datas = jsonObject1.get("datas");
                    JSONArray objects = JSONArray.parseArray(datas.toString());
                    map.put("msgList",objects);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 查询详情消息
     * @return
     */
    @RequestMapping("/mosken/MsgDetail.htm")
    public Object selectMsageDetail(String msgId, ModelMap map){

        String str = "/message/msgDetail/"+msgId+".json";
        if (StringUtil.isNotBlank(msgId)) {
            String s = "removeHtmlTag=false";
            String url = messageServer.get(str).toString();

            try {
                JSONObject jsonObject1 = this.httpGet(s, url);
                String s1 = jsonObject1.get("status") == null ? "1" : jsonObject1.get("status").toString();
                if (s1.equals("0")) {
                    map.put("msgObject",jsonObject1.get("datas"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 查询列表
     * @return
     */
    @RequestMapping("/mosken/msgList.htm")
    public Object queryList(CurrentUser user,ModelMap map){

        String str="toId="+user.getMemberId()+"&removeHtmlTag=false";
        String url = messageServer.get("/message/webMsgList.json").toString();
        JSONObject jsonObject1 = null;
        try {
            jsonObject1 = this.httpGet(str, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String status = jsonObject1.get("status") == null ? "1" : jsonObject1.get("status").toString();
        if(status.equals("0")){
            if(null != jsonObject1.get("datas")){
                Object datas = jsonObject1.get("datas");
                JSONArray objects = JSONArray.parseArray(datas.toString());
                map.put("msgList",objects);
            }
        }
        return map;
    }



    /**
     * post请求
     */
    private JSONObject httpPost(JSONObject paramJson, String url) throws Exception {
        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient closeableHttpClient = httpConnectionManager.getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-type","application/json; charset=utf-8");
        httpPost.setEntity(new StringEntity(paramJson.toJSONString(), CharsetUtils.get("UTF-8")));

        try {
            httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();

            if (httpEntity == null) {
                throw new RuntimeException("网络异常");
            }

            String responseString = EntityUtils.toString(httpEntity, "UTF-8");
            EntityUtils.consume(httpEntity);

            return JSONObject.parseObject(responseString);
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpResponse != null) {
                httpResponse.close();
            }
        }
    }

    /**
     * get请求，参数拼接成url的字符串
     */
    private JSONObject httpGet(String paramString, String url) throws Exception {
        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient closeableHttpClient = httpConnectionManager.getHttpClient();

        if (!StringUtil.isEmpty(paramString)) {
            if (paramString.startsWith("?")) {
                url += paramString;
            } else {
                url = url + "?" + paramString;
            }
        }

        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Content-type","application/json; charset=utf-8");

        try {
            httpResponse = closeableHttpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();

            if (httpEntity == null) {
                throw new RuntimeException("网络异常");
            }

            String responseString = EntityUtils.toString(httpEntity, "UTF-8");
            EntityUtils.consume(httpEntity);
            JSONObject  jsonObject = JSONObject.parseObject(responseString);
            return jsonObject;
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpResponse != null) {
                httpResponse.close();
            }

        }

    }
}
