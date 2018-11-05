package com.sinochem.crude.trade.member.controller;

import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.MemberCredentials;
import com.sinochem.crude.trade.member.enums.EnumEpType;
import com.sinochem.crude.trade.member.model.EnterpriseCredentialsEnum;
import com.sinochem.crude.trade.member.service.EnterpriseService;
import com.sinochem.crude.trade.member.service.MemberCredentialsService;
import com.sinochem.crude.trade.member.service.SualificationService;
import com.sinochem.crude.trade.member.service.udbservice.MemberCredentialsUdbService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.user.User;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RolesAccess("admin")
public class OmSualificationController {

    private static final Logger logger = LoggerFactory.getLogger(OmSualificationController.class);

    @Autowired
    SualificationService sualificationService;
    @Autowired
    MemberCredentialsService memberCredentialsService;

    @Autowired
    private MemberCredentialsUdbService memberCredentialsUdbService;
    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    URLBroker systemServer;
    @Autowired
    HttpConnectionManager httpConnectionManager;

    @Autowired
    DataSourceTransactionManager transactionManager;


    //表示审核未通过
    private final Integer UNAUDITED = 0;
    //表示审核通过
    private final Integer AUDITED = 1;

    @RightAccess(110)
    @RequestMapping(UrlMapping.OM_SUALIFICATION_LIST)
    public void sualificationList(String name, ModelMap modelMap ,Integer audit, PageInfo pageInfo){
        modelMap.put("credentials",memberCredentialsService.getCredentials());
        modelMap.put("page",memberCredentialsService.selectCredentails(name,audit,pageInfo));
        modelMap.put("name",name);
    }

    @RightAccess(111)
    @RequestMapping(UrlMapping.OM_SUALIFICATION_AUDIT)
    @ResponseBody
    public String audit(Long id,  HttpServletRequest request){
        DefaultTransactionDefinition transDefinition = new DefaultTransactionDefinition();
        TransactionStatus transStatus = transactionManager.getTransaction(transDefinition);
        CloseableHttpResponse response = null;
        try {
            transDefinition.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);
            MemberCredentials enCredential = memberCredentialsService.queryEnterpriseCredential(id,String.valueOf(EnterpriseCredentialsEnum.ENTERPRISES.getCode()));
            MemberCredentials noEnCredential = memberCredentialsService.getCredentialsById(id);
            String enCode = String.valueOf(EnterpriseCredentialsEnum.ENTERPRISES.getCode());

            //审核的资质不是企业
            if(!enCode.equals(noEnCredential.getCredentialsCode())){
                //如果企业资质没有，或者企业资质没有通过审核
                if(enCredential == null || !AUDITED.equals(enCredential.getAudit())){
                    //当没有企业资质时，不允许审核其他资质
                    transactionManager.commit(transStatus);
                    return "error";
                }
            }




            // 如果审核通过，则需要修改udb的企业类型
            //如果审核的是企业资质，那么把企业的相关数据插入到udb中
            if(enCode.equals(noEnCredential.getCredentialsCode())) {
                memberCredentialsUdbService.udbInsertCredentials(noEnCredential);
            }else{
                memberCredentialsUdbService.updateEnterpriseCredential(noEnCredential);
            }
            MemberCredentials credentials = memberCredentialsService.setAudited(id);
            String[] roles = memberCredentialsService.getRolesByCode(credentials.getCredentialsCode(), true);
            if (roles == null) {
                //TODO 这里不做提交，区分两个平台之间的资质
                transactionManager.commit(transStatus);
                return "success";
            }
            HttpPost httpPost = new HttpPost(systemServer.get("/om/role/add.json").toString());
            CloseableHttpClient client = httpConnectionManager.getHttpClient();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for (String role : roles) {
                params.add(new BasicNameValuePair("roles", role));
            }
            params.add(new BasicNameValuePair("id", String.valueOf(credentials.getMemberId())));

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
                return "success";
            }else{
                transactionManager.rollback(transStatus);
                return com.sinochem.it.b2b.common.utils.VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO18);
            }
        } catch (BizException e) {
            e.printStackTrace();
            transactionManager.rollback(transStatus);
            return e.getMessage();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            transactionManager.rollback(transStatus);
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            transactionManager.rollback(transStatus);
            return e.getMessage();
        } catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback(transStatus);
            return e.getMessage();
        } finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RightAccess(112)
    @RequestMapping(UrlMapping.OM_SUALIFICATION_AUDITSHOW)
    public void auditShow(ModelMap modelMap,Long type,Long id,Long memberId){
        if(type==1) modelMap.put("enterprises",sualificationService.getCrudePotrerillosInfo(memberId));
        else if(type==2) modelMap.put("enterprises",sualificationService.getCrudeTradingCompanyInfo(memberId));
        else if(type==3) modelMap.put("enterprises",sualificationService.getCrudeProviderInfo(memberId));
        else if(type==4) modelMap.put("enterprises",sualificationService.getCrudeCustomerInfo(memberId));
        else if(type==5) modelMap.put("enterprises",sualificationService.getCrudeShipAgencyInfo(memberId));
        else if(type==99) {
            Enterprise e = sualificationService.getEnterprisesInfo(memberId);
            modelMap.put("enterprises",e);
        }

//        modelMap.put("memberCredentials",memberCredentialsService.getCredentials(memberId,type));
        modelMap.put("memberCredentials",memberCredentialsService.getCredentialsById(id));
        modelMap.put("type",type);
        modelMap.put("id",id);
    }

    @RequestMapping(UrlMapping.OM_SUALIFICATION_ADDENTERPRISESAUDIT)
    @ResponseBody
    public Long addEnterprisesAudit(Long id,CurrentUser user){
        MemberCredentials memberCredentials = new MemberCredentials();
        memberCredentials.setAudit(UNAUDITED);
        memberCredentials.setMemberId(id);
        memberCredentials.setCredentialsCode(String.valueOf(EnterpriseCredentialsEnum.ENTERPRISES.getCode()));
        memberCredentialsService.save(memberCredentials);
        Enterprise enterprises = new Enterprise();
        enterprises.setMemberId(id);
        enterprises.setCreateUser(user.getMemberId().toString());
        enterprises.setUpdateUser(user.getMemberId().toString());
        enterprises.setCreateTime(new Date());
        enterprises.setUpdateTime(new Date());
        try{
            enterpriseService.insertEnterprise(enterprises);
        }catch (BizException e){
            logger.error(e.getMessage(),e);
        }
        return enterprises.getId();
    }

    @RequestMapping(UrlMapping.OM_SUALIFICATION_AUDIT_REJECTED)
    @ResponseBody
    public String auditRejected(CurrentUser currentUser, Long id, HttpServletRequest request,String reason){

        try {
            memberCredentialsService.auditRejected(id,reason,currentUser.getMemberId());
        } catch (BizException e) {
            return e.getMessage();
        }
        return "success";
    }
}
