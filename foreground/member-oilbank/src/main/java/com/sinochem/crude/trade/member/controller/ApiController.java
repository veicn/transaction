package com.sinochem.crude.trade.member.controller;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.Person;
import com.sinochem.crude.trade.member.model.vo.MemberInfoVO;
import com.sinochem.crude.trade.member.service.EnterpriseService;
import com.sinochem.crude.trade.member.service.MemberInfoService;
import com.sinochem.crude.trade.member.service.MemberTagsService;
import com.sinochem.crude.trade.member.service.PersonService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.ApiSafeAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 移动端接口
 * Created by bbt on 2018/1/3.
 */
@Controller
public class ApiController {


    final static Logger logger  = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private PersonService personService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private MemberTagsService memberTagsService;

    /**
     * 注册用户
     * @return
     */
    @WithoutAccess
    @RequestMapping(value = UrlMapping.API_FILL, method = POST)
    @ResponseBody
    public ResultDatas<Person> register(@RequestBody Person person) {
        ResultDatas result = new ResultDatas<>();
        logger.info("用户信息填充:" + person.getName());
        try {
            //注册
            personService.fill(person,person.getMemberId());

            result.setDatas(person);
        } catch (BizException e) {//捕捉到业务异常

            result.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO12));
            logger.error("注册接口调用失败",e);
        }
        return result;
    }
    /**
     * 修改手机号
     * @return
     */
    @ApiSafeAccess
    @RequestMapping(value = "access")
    @RolesAccess("ship_owner")
    @ResponseBody
    public Result get( CurrentUser user ) {

        ResultDatas<CurrentUser> result = new ResultDatas<>();
        result.setDatas(user);
        return result;
    }

    /**
     * 修改手机号
     * @return
     */
    @ApiSafeAccess
    @RequestMapping(value = "noaccess")
    @RolesAccess("admin")
    @ResponseBody
    public Result noaccess( CurrentUser user ) {

        ResultDatas<CurrentUser> result = new ResultDatas<>();
        result.setDatas(user);
        return result;
    }
    /**
     * 获取个人信息
     *
     * @return
     *
     */
    @ApiSafeAccess
    @RequestMapping(UrlMapping.API_PERSON)
    @ResponseBody
    public ResultDatas<Person> person(CurrentUser currentUser) {
        Long memberId = currentUser.getMemberId();
        ResultDatas<Person> result = new ResultDatas<Person>();
        Person person = personService.getInfoByMemberId(memberId);
        if (person != null) {
            result.setDatas(person);
        } else {
            MemberInfoVO memberInfoVO = memberInfoService.memberInfo(memberId);
            person = new Person();
            person.setEmail(memberInfoVO.getEmail());
            person.setMobile(memberInfoVO.getMobile());
            result.setDatas(person);
        }
        return result;
    }

    /**
     * 获取企业信息
     *
     * @return
     */
    @ApiSafeAccess
    @RequestMapping(UrlMapping.API_ENTERPRISE)
    @ResponseBody
    public ResultDatas<Enterprise> enterprise(CurrentUser currentUser) {
        Long memberId = currentUser.getEpMemberId();
        logger.info("企业信息查询调用:"+memberId);
        ResultDatas<Enterprise> result = new ResultDatas<Enterprise>();
        if (memberId != null) {
            Enterprise enterprise = enterpriseService.getByMemberId(memberId);
            if (enterprise != null) {
                result.setDatas(enterprise);
            } else {
                result.setDatas(new Enterprise());
                logger.error("企业信息查询接口调用失败，企业信息未找到");
            }
        } else {
            result.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO13));
            logger.error("企业信息查询接口调用失败，企业信息未找到");
        }
        return result;
    }

    /**
     * 修改个人资料
     * @param form
     * @return
     */
    @ApiSafeAccess
    @RequestMapping(value = UrlMapping.API_CHANGEINFO)
    @ResponseBody
    public Result changeInfo(@RequestBody Person form, CurrentUser user) throws BizException{
        logger.info("修改资料码接口调用：:" + form.getHeadImg());
        Result result = new Result();
        Integer check = memberTagsService.checkEnterprise(user.getMemberId());
        if(check>0){
            result.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO38));
            logger.error(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO38));
            return result;
        }
        try {
            Long memberId = user.getMemberId();
            form.setUpdateUser(memberId.toString());
            form.setMemberId(memberId);
            personService.update(form);
        } catch (BizException e) {
            result.setFail(e.getMessage());
            logger.error("接口调用失败",e);
        }
        return result;
    }
    /**
     * 获取个人资料
     * @return
     */
    @ApiSafeAccess
    @RequestMapping(value = "api/userinfo.json")
    @ResponseBody
    public ResultDatas<Map> userInfo(CurrentUser user) {

        ResultDatas<Map> result = new ResultDatas<>();
        Map<String,Object> datas = new HashMap<>();
        datas.put("epMemberId",user.getEpMemberId());
        datas.put("memberId",user.getMemberId());
        if(user.getEpMemberId() != null){
            Enterprise enterprise = enterpriseService.getByMemberId(user.getEpMemberId());
            datas.put("logo",enterprise.getEpLogo());
            datas.put("enterpriseName",enterprise.getEpType() == null || enterprise.getEpType() ==0
                    ? enterprise.getName()
                    : enterprise.getAbbEnglishName());
            if(user.getEpMemberId() == user.getMemberId()){
                //企业账号
                datas.put("type" , 1);
            }else{
                Person person =personService.getInfoByMemberId(user.getMemberId());
                datas.put("logo",person.getHeadImg());
                datas.put("name",person.getName());
                //个人账号
                datas.put("type" , 0);
            }

        }else{
            Enterprise enterprise = enterpriseService.getByMemberId(user.getMemberId());
            if (enterprise != null) {
                datas.put("logo",enterprise.getEpLogo());
                datas.put("enterpriseName",enterprise.getEpType() == null || enterprise.getEpType() ==0
                        ? enterprise.getName()
                        : enterprise.getAbbEnglishName());
                datas.put("epMemberId",enterprise.getMemberId());
                datas.put("type" , 1);
            }else{
                Person person = personService.getInfoByMemberId(user.getMemberId());
                if(person != null){
                    datas.put("logo",person.getHeadImg());
                    datas.put("name",person.getName());
                    datas.put("type" , 0);
                }
            }
        }


        result.setDatas(datas);
        return result;
    }

    /**
     * 获取登录资料
     * @return
     */
    @ApiSafeAccess
    @RequestMapping(value = UrlMapping.API_CURRENTUSER)
    @ResponseBody
    public ResultDatas currentUser(CurrentUser user) {
        ResultDatas result = new ResultDatas();
        result.setDatas(user);
        return result;
    }

}