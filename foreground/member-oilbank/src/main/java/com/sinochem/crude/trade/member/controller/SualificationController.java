package com.sinochem.crude.trade.member.controller;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.controller.vo.SualificationVo;
import com.sinochem.crude.trade.member.domain.*;
import com.sinochem.crude.trade.member.service.EnterpriseCrudeService;
import com.sinochem.crude.trade.member.service.EnterpriseService;
import com.sinochem.crude.trade.member.service.MemberCredentialsService;
import com.sinochem.crude.trade.member.service.SualificationService;
import com.sinochem.crude.trade.member.user.CurrentUser;

import java.io.IOException;
import java.util.*;

import com.sinochem.it.b2b.common.CommonUtils;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import com.sinochem.it.b2b.member.user.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
@RolesAccess({"enter_admin","enter_master","enterprise"})
public class SualificationController {

    @Autowired
    SualificationService sualificationService;
    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    URLBroker appServerBroker;
    
    @Autowired
    EnterpriseCrudeService enterpriseCrudeService;
    @Autowired
    MemberCredentialsService memberCredentialsService;
    @Autowired
    DataSourceTransactionManager transactionManager;
    @Autowired
    HttpConnectionManager httpConnectionManager;
    @Autowired
    URLBroker systemServer;
    /**
     * 开通炼厂资质
     * @param currentUser
     * @param modelMap
     */
    @RequestMapping(UrlMapping.CENTER_POTRERILLOSINFO)
    public void getPotrerillosInfo(CurrentUser currentUser, ModelMap modelMap){
        CrudePotrerillosInfo crudeProviderInfo = sualificationService.getCrudePotrerillosInfo(currentUser.getEpMemberId());

        modelMap.put("info",crudeProviderInfo==null ? new CrudePotrerillosInfo() : crudeProviderInfo);
    }
    /**
     * 开通贸易公司资质
     * @param currentUser
     * @param modelMap
     */
    @RequestMapping(UrlMapping.CENTER_TRADINGCOMPANYINFO)
    public void getTradingCompanyInfo(CurrentUser currentUser, ModelMap modelMap){
        CrudeTradingCompanyInfo crudeTradingCompanyInfo =sualificationService.getCrudeTradingCompanyInfo(currentUser.getEpMemberId());
        modelMap.put("info",crudeTradingCompanyInfo==null ? new CrudeTradingCompanyInfo() : crudeTradingCompanyInfo);
    }
    /**
     * 开通供应商资质
     * @param currentUser
     * @param modelMap
     */
    @RequestMapping(UrlMapping.CENTER_PROVIDERINFO)
    public void getProviderInfo(CurrentUser currentUser, ModelMap modelMap){
        CrudeProviderInfo crudeProviderInfo = sualificationService.getCrudeProviderInfo(currentUser.getEpMemberId());

        modelMap.put("info",crudeProviderInfo==null ? new CrudeProviderInfo() : crudeProviderInfo);
    }
    /**
     * 开通客户资质
     * @param currentUser
     * @param modelMap
     */
    @RequestMapping(UrlMapping.CENTER_CUSTOMERINFO)
    public void getCustomerInfo(CurrentUser currentUser, ModelMap modelMap){
        CrudeCustomerInfo crudeCustomerInfo = sualificationService.getCrudeCustomerInfo(currentUser.getEpMemberId());

        modelMap.put("info",crudeCustomerInfo==null ? new CrudeCustomerInfo() : crudeCustomerInfo);
    }

    @RequestMapping(UrlMapping.CENTER_SUALIFICATIONLIST)
    //@RolesAccess(CommonUtils.GUEST_ROLE_NAME)
    public void sualificationList(CurrentUser currentUser, ModelMap modelMap){
        modelMap.put("credentials",memberCredentialsService.getCredentials(true));
        modelMap.put("sualification",new SualificationVo( sualificationService.getRedentials(currentUser.getEpMemberId()) ));
        Enterprise enterprise = enterpriseService.enterpriseByMemberId(currentUser.getMemberId());
        if(enterprise != null){ //只有企业账户可以申请修改资质  张戬  bug号 4348
            modelMap.put("flag",true);
        }
    }
    @RequestMapping(value = UrlMapping.CENTER_POTRERILLOSINFO_SAVE,method = RequestMethod.POST)
    public String add(CrudePotrerillosInfo info, BindingResult result, CurrentUser user , ModelMap modelMap){
        if(result.hasErrors()) return null;

        sualificationService.addCrudePotrerillosInfo(info,user.getEpMemberId());
        return "redirect:"+appServerBroker+"/loginEx.htm";
    }
    @RequestMapping(value = UrlMapping.CENTER_TRADINGCOMPANYINFO_SAVE,method = RequestMethod.POST)
    public String add(CrudeTradingCompanyInfo info, BindingResult result, CurrentUser user , ModelMap modelMap){
        if(result.hasErrors()) return null;

        sualificationService.addCrudeTradingCompanyInfo(info,user.getEpMemberId());
        return "redirect:"+appServerBroker+"/loginEx.htm";
    }
    @RequestMapping(value = UrlMapping.CENTER_PROVIDERINFO_SAVE,method = RequestMethod.POST)
    public String add(CrudeProviderInfo info, BindingResult result, CurrentUser user , ModelMap modelMap){
        if(result.hasErrors()) return null;

        sualificationService.addCrudeProviderInfo(info,user.getEpMemberId());
        return "redirect:"+appServerBroker+"/loginEx.htm";
    }
    @RequestMapping(value = UrlMapping.CENTER_CUSTOMERINFO_SAVE,method = RequestMethod.POST)
    public String add(CrudeCustomerInfo info, BindingResult result, CurrentUser user , ModelMap modelMap){
        if(result.hasErrors()) return null;

        sualificationService.addCrudeCustomerInfo(info,user.getEpMemberId());
        return "redirect:"+appServerBroker+"/loginEx.htm";
    }

    /**
     * 开通船舶资质
     * @param currentUser
     * @param modelMap
     */
    @RequestMapping(UrlMapping.CENTER_SHIPAGENCYINFO)
    public void getCrudeShipAgencyInfo(CurrentUser currentUser, ModelMap modelMap){
        CrudeShipAgencyInfo crudeCustomerInfo = sualificationService.getCrudeShipAgencyInfo(currentUser.getEpMemberId());

        modelMap.put("info",crudeCustomerInfo==null ? new CrudeShipAgencyInfo() : crudeCustomerInfo);
    }
    @RequestMapping(value = UrlMapping.CENTER_SHIPAGENCYINFO_SAVE,method = RequestMethod.POST)
    public String add(CrudeShipAgencyInfo info, BindingResult result, CurrentUser user , ModelMap modelMap){
        if(result.hasErrors()) return null;

        sualificationService.addCrudeShipAgencyInfo(info,user.getEpMemberId());
        return "redirect:"+appServerBroker+"/loginEx.htm";
    }


    /**
     * 直接开通资质,不需要录入资质材料,直接使用企业资料申请
     * @param user
     * @param t
     * @return
     * @since  1.2
     */
    @ResponseBody
    @RequestMapping(UrlMapping.CENTER_REQUEST_SUALIFICATION)
    public Result requestSualification(CurrentUser user , String code){
        Result result = new Result();

        try {
            sualificationService.requestSualification(user.getEpMemberId(),code);
        } catch (BizException e) {
            result.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO17));
        }
        return result;
    }

    /**
     * 删除资质
     * @param user
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(UrlMapping.CENTER_CREDENTIALS_DELETE)
    public Result credentialsDelete(CurrentUser user , String code, HttpServletRequest request){
        DefaultTransactionDefinition transDefinition = new DefaultTransactionDefinition();
        TransactionStatus transStatus = transactionManager.getTransaction(transDefinition);
        Result result = new Result();
        CloseableHttpResponse response = null;
        try {
            sualificationService.credentialsDelete(user.getEpMemberId(),code,user.getMemberId());
            //删除权限 (这里要判断其它已有资质是否也拥有该权限)
            //循环判断要删除的资质列表
            String[] roles = memberCredentialsService.getRolesByCode(code, true);
            if (roles == null) {
                return result;
            }
            Set<String> roleSet = new HashSet<String>();
            CollectionUtils.addAll(roleSet,roles);
            //获取所有的资质
            List<String> credentialCodes = memberCredentialsService.getByMember(user.getEpMemberId());
            if(credentialCodes != null && credentialCodes.size() > 0){
                for(String credentialCode : credentialCodes){
                    if(!code.equals(credentialCode)){
                        String[] credentialRoles = memberCredentialsService.getRolesByCode(credentialCode, true);
                        if(credentialRoles != null && credentialRoles.length > 0){
                            roleSet.removeAll(Arrays.asList(credentialRoles));
                        }
                    }
                }
            }
            HttpPost httpPost = new HttpPost(systemServer.get("/om/role/remove.json").toString());//TODO 该接口暂无
            CloseableHttpClient client = httpConnectionManager.getHttpClient();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for (String role : roleSet) {
                params.add(new BasicNameValuePair("roles", role));
            }
            params.add(new BasicNameValuePair("id", String.valueOf(user.getEpMemberId())));

            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            StringBuilder sb = new StringBuilder();
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
                sb.append(cookie.getName() + "=" + cookie.getValue() + ";");
            }
            httpPost.setHeader("Cookie",sb.toString());
            response = client.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                transactionManager.commit(transStatus);
                return result;
            }else{
                transactionManager.rollback(transStatus);
                result.setFail(com.sinochem.it.b2b.common.utils.VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO18),Result.EEROR);
            }
        } catch (BizException e) {
            transactionManager.rollback(transStatus);
            result.setFail(e.getMessage(),Result.EEROR);
        } catch (ClientProtocolException e) {
            transactionManager.rollback(transStatus);
            result.setFail(e.getMessage(),Result.EEROR);
        } catch (IOException e) {
            transactionManager.rollback(transStatus);
            result.setFail(e.getMessage(),Result.EEROR);
        } finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
