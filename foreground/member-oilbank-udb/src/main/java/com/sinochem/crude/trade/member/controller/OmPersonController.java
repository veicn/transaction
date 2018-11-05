package com.sinochem.crude.trade.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.query.PersonQuery;
import com.sinochem.crude.trade.member.service.EnterpriseService;
import com.sinochem.it.b2b.common.CommonUtils;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.common.utils.VisitorLocale;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinochem.crude.trade.member.domain.Person;
import com.sinochem.crude.trade.member.domain.PersonLog;
import com.sinochem.crude.trade.member.service.PersonService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by xyuser on 2017/11/11.
 */
@Controller
@RolesAccess("admin")
public class OmPersonController {

    @Autowired
    PersonService personService;
    @Autowired
    private URLBroker appServerBroker;
    @Autowired
    EnterpriseService enterpriseService;




    //人员列表
    @RightAccess(121)
    @RequestMapping(UrlMapping.OM_PERSON_LIST)
    public void list(PageInfo pageInfo, PersonQuery personQuery, ModelMap modelMap){
        modelMap.put("roles",personService.getRoles());
        modelMap.put("person",personQuery);
        modelMap.put("page", new PageInfoResult(personService.selectQueryByPrimary(personQuery, pageInfo)));
    }

   //人员添加
   @RightAccess(122)
    @RequestMapping(UrlMapping.OM_PERSON_TOADD)
    public  String toAdd(Person person) throws BizException{
    	personService.add(person);
		return "redirect:"+appServerBroker+"list.htm";
    	
    }
    
    //刪除
    @RequestMapping(UrlMapping.OM_PERSON_DELETEPERSON)
    public String  delete(Long id){
    	personService.deletePerson(id);
    	return "redirect:"+appServerBroker+"list.htm";
    }
    
    //修改回显
    @RightAccess(123)
    @RequestMapping(UrlMapping.OM_PERSON_TOUPDATE)
    public String toupdate(Long id,Long memberId,Long add,ModelMap modelMap){
        Person person = new Person();
        if(id != null){
            person = personService.toUpdate(id);
        }
        if(id == null && memberId != null){
            person = personService.selectByMemberId(memberId);
        }

        //查询udbperson

        if(person==null){
            person = new Person();
            person.setMemberId(memberId);
        }
        modelMap.put("person",person);
        modelMap.put("add",add);
		return "om/person/update";
    }
    
    //修改
    @RightAccess(124)
    @RequestMapping(UrlMapping.OM_PERSON_UPDATE)
    public String update(PersonQuery person) throws BizException{
    	personService.update(person);
		return "redirect:list.htm";
    	
    }

    //修改
    @RightAccess(129)
    @RequestMapping(value = UrlMapping.OM_CHECK_MEMBER, method = POST)
    @ResponseBody
    public ResultDatas<Integer> checkMember(Long id){
        ResultDatas<Integer> result = new ResultDatas<Integer>();
        Enterprise enterprise = enterpriseService.getByMemberId(id);
        if(enterprise==null){
            result.setDatas(0);
        }else{
            result.setDatas(1);
        }
        return result;
    }

}
