package com.sinochem.crude.trade.member.controller;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.service.EnterpriseService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinochem.crude.trade.member.domain.EnterpriseCrude;
import com.sinochem.crude.trade.member.service.EnterpriseCrudeService;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 企业扩展信息
 */
@Controller
public class EnterpriseCrudeController {

    private final static Logger logger  = LoggerFactory.getLogger(EnterpriseCrudeController.class);

    @Autowired
    private EnterpriseCrudeService enterpriseCrudeService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private URLBroker appServerBroker;

    
    //刪除
    @RequestMapping(UrlMapping.DELETEENTERPRISECRUDE)
    public String  delete(Long id){
        try{
            enterpriseCrudeService.deleteEnterprise(id);
        }catch (BizException e){
            logger.error(e.getMessage(),e);
        }
    	return "redirect:"+appServerBroker+"crudeList.htm";
    }

    /**
     * 查询企业扩展信息 get请求
     * @param currentUser
     * @param modelMap
     */
    @RequestMapping(value = UrlMapping.CENTER_ENTERPRISECRUDE_GET, method = RequestMethod.GET)
    public void enterpriseCrude(CurrentUser currentUser, ModelMap modelMap){
        EnterpriseCrude enterpriseCrude = enterpriseCrudeService.selectByMemberId(currentUser.getEpMemberId());
        if (enterpriseCrude == null) {
            enterpriseCrude = new EnterpriseCrude();
        }
        modelMap.put("enterpriseCrude", enterpriseCrude);
    }
    /**
     * 保存企业扩展信息 post请求
     * @param currentUser
     * @param modelMap
     */
    @RequestMapping(value = UrlMapping.CENTER_ENTERPRISECRUDE_POST, method = RequestMethod.POST)
    public String enterpriseCrude(EnterpriseCrude enterpriseCrude, BindingResult bindingResult,
                                  CurrentUser currentUser, ModelMap modelMap){
        modelMap.put("enterpriseCrude", enterpriseCrude);
        if(bindingResult.hasErrors()) {
            return null;
        }
        try {
            if (null == enterpriseCrude.getEnterpriseId() && null != currentUser.getEpMemberId()) {
                //冗余企业id
                Enterprise enterprise = enterpriseService.enterpriseByMemberId(currentUser.getEpMemberId());
                if (enterprise != null) {
                    enterpriseCrude.setEnterpriseId(enterprise.getId());
                }
            }
            enterpriseCrude.setMemberId(currentUser.getEpMemberId());
            enterpriseCrudeService.saveOrUpdate(enterpriseCrude);
        } catch (BizException e) {
            logger.error("企业补充信息保存失败！", e);
            modelMap.put("errorMessage", VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO16));
            return null;
        }
        return "redirect:"+appServerBroker+"/center/my.htm";
    }
}
