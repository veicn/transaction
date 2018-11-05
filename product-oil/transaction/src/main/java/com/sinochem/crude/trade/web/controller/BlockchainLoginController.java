package com.sinochem.crude.trade.web.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinochem.crude.trade.blockchain.domain.TDataPartner;
import com.sinochem.crude.trade.blockchain.model.LoginResult;
import com.sinochem.crude.trade.blockchain.service.TDataPartnerService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import com.sinochem.it.b2b.member.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by z1761 on 2018/6/9.
 */
@Controller
public class BlockchainLoginController {

    @Autowired
    TDataPartnerService tDataPartnerService;

    @WithoutAccess
    @RequestMapping("/getLoginUserJsonp")
    public void getLoginUserJsonp(HttpServletRequest request, HttpServletResponse response, CurrentUser user){
        PrintWriter pw = null;
        try {
            LoginResult loginResult = new LoginResult();
            pw = response.getWriter();
            if(null!=user){
                System.out.print("#############user.getEpMemberId(): "+user.getEpMemberId());
                String url = request.getParameter("url");

                TDataPartner tDataPartner = new TDataPartner();
                tDataPartner.setEnterpriseId(user.getEpMemberId());
                tDataPartner.setWebDomain(url);
                tDataPartner = tDataPartnerService.findByQuery(tDataPartner);
                if(tDataPartner!=null) {
                    loginResult.setStatus("1");
                    loginResult.setUser(user);
                    loginResult.settDataPartner(tDataPartner);
                }else{
                    loginResult.setStatus("0");
                }

            }else{
                loginResult.setStatus("0");
            }
            Gson gson = new GsonBuilder().serializeNulls().create();
            String cnt = gson.toJson(loginResult);
            //pw.write("success" + "(" + cnt + ")");
            pw.write(cnt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        pw.close();
    }


    @WithoutAccess
    @RequestMapping("/getLoginUser")
    public void getLoginUser(HttpServletRequest request, HttpServletResponse response, CurrentUser user){
        PrintWriter pw = null;
        try {
            LoginResult loginResult = new LoginResult();
            pw = response.getWriter();
            if(null!=user){
                System.out.print("#############user.getEpMemberId(): "+user.getEpMemberId());
                String url = request.getParameter("url");

                TDataPartner tDataPartner = new TDataPartner();
                tDataPartner.setEnterpriseId(user.getEpMemberId());
                tDataPartner.setWebDomain(url);
                tDataPartner = tDataPartnerService.findByQuery(tDataPartner);
                if(tDataPartner!=null) {
                    loginResult.setStatus("1");
                    loginResult.setUser(user);
                    loginResult.settDataPartner(tDataPartner);
                }else{
                    loginResult.setStatus("0");
                }

            }else{
                loginResult.setStatus("0");
            }
            Gson gson = new GsonBuilder().serializeNulls().create();
            String cnt = gson.toJson(loginResult);
            //pw.write("success" + "(" + cnt + ")");
            pw.write(cnt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        pw.close();
    }


    @WithoutAccess
    @RequestMapping("/getLoginData")
    public void getLoginData(HttpServletRequest request, HttpServletResponse response){
        PrintWriter pw = null;
        try {
            LoginResult loginResult = new LoginResult();
            String url = request.getParameter("url");
            pw = response.getWriter();
            TDataPartner tDataPartner = new TDataPartner();
            tDataPartner.setWebDomain(url);
            tDataPartner = tDataPartnerService.findByQuery(tDataPartner);
            if(tDataPartner!=null) {
                loginResult.setStatus("1");
                loginResult.settDataPartner(tDataPartner);
            }else{
                loginResult.setStatus("0");
            }
            Gson gson = new GsonBuilder().serializeNulls().create();
            String cnt = gson.toJson(loginResult);
            pw.write("success" + "(" + cnt + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }

        pw.close();
    }


    @WithoutAccess
    @RequestMapping("/blockchain/getLoginDataByUrl")
    @ResponseBody
    public TDataPartner getLoginDataByUrl(@RequestBody String url){
        TDataPartner tDataPartner = new TDataPartner();
        try {

            tDataPartner.setWebDomain(url);
            tDataPartner = tDataPartnerService.findByQuery(tDataPartner);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tDataPartner;
    }



    @WithoutAccess
    @RequestMapping("/blockchain/getWebDemainByUserId")
    @ResponseBody
    public String getWebDemainByUserId(@RequestBody String userId){
        TDataPartner tDataPartner = new TDataPartner();
        tDataPartner.setEnterpriseId(Long.valueOf(userId));
        tDataPartner = tDataPartnerService.findByQuery(tDataPartner);
        if(tDataPartner!=null) {

            return tDataPartner.getWebDomain();
        }
        return null;
    }



}
