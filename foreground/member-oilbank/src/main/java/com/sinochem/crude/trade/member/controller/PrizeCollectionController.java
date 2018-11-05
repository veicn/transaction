package com.sinochem.crude.trade.member.controller;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.Person;
import com.sinochem.crude.trade.member.domain.PrizeCollection;
import com.sinochem.crude.trade.member.model.PrizeCollectionVO;
import com.sinochem.crude.trade.member.model.vo.MemberInfoVO;
import com.sinochem.crude.trade.member.service.MemberInfoService;
import com.sinochem.crude.trade.member.service.PersonService;
import com.sinochem.crude.trade.member.service.PrizeCollectionService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.member.access.ApiSafeAccess;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Made By WangTing
 * 本类用使用有奖征集的controller层
 *
 */

@Controller
public class PrizeCollectionController {

    Logger logger = LoggerFactory.getLogger(PrizeCollectionController.class);

    @Autowired
    private PrizeCollectionService prizeCollectionService;
    @Autowired
    private PersonService personService;
    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private URLBroker portalServer;

    /**
     * 查询所有列表
     * @param modelMap
     * @return
     */
    @RightAccess(119)
    @RequestMapping(UrlMapping.OM_PRIZE_LIST)
    public String  selectAll(CurrentUser user,String contactUser,ModelMap modelMap) throws BizException {
        if(user == null || user.getMemberId() == null){
            return "/welcome";
        }
        try {
            if(StringUtil.isNotBlank(contactUser)){
                List<PrizeCollectionVO> list = prizeCollectionService.selectByContactUser(contactUser);
                modelMap.put("list",list);
                modelMap.put("contactUser",contactUser);
            }else{
                List<PrizeCollectionVO> list = prizeCollectionService.selectAll();
                modelMap.put("list",list);
            }
        } catch (Exception e) {
            logger.error("获取列表异常：" , e);
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO7));
        }
        return "om/prize/list";

    }

    /**
     *  根据 id删除
     * @param user
     * @param id
     * @return
     * @throws BizException
     */
    @RightAccess(120)
    @RequestMapping(value=UrlMapping.OM_PRIZE_DELETE)
    @ResponseBody
    public Result delete(CurrentUser user,Long id) throws BizException {
        Result result = new Result();
        try {
            if(user == null || user.getMemberId() == null){
                result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO8));
                result.setStatus(Result.ERROR);
                return result;
            }
            prizeCollectionService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("删除数据异常" );
            result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO19));
            result.setStatus(Result.ERROR);
            return result;
        }
        result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO20));
        result.setStatus(Result.SUCCESS);
        return result;
    }

    @RequestMapping(UrlMapping.TRADEHALL_RC_PRIZE)
    public String collection(CurrentUser user, ModelMap modelMap) throws BizException {
        modelMap.put("user", user);
        return "/tradeHall/rc_prize";
    }

//    有奖征集页面的保存方法
    @ApiSafeAccess
    @RequestMapping(value = UrlMapping.PRIZE_SAVE)
    @ResponseBody
    public Result save(CurrentUser user, @RequestBody PrizeCollection prizeCollection){
        Result result = new Result();
        try {
            if(user == null || user.getMemberId() == null){
                result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO8));
                result.setStatus(Result.ERROR);
                return result;
            }
            Person person = personService.getInfoByMemberId(user.getMemberId());
            if(person == null){
                MemberInfoVO vo = memberInfoService.memberInfo(user.getEpMemberId());
                prizeCollection.setContactUser(user.getName());
                prizeCollection.setContactTelephone(vo.getMobile());
                prizeCollection.setContactEmail(vo.getEmail());
            }else{
                prizeCollection.setContactUser(person.getName());
                prizeCollection.setContactTelephone(person.getMobile());
                prizeCollection.setContactEmail(person.getEmail());
            }
            prizeCollection.setCreateUser(user.getMemberId());
            prizeCollection.setCreateTime(new Date());
            prizeCollectionService.add(prizeCollection);
        } catch (Exception e) {
            logger.error("保存数据异常" );
            result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO10));
            result.setStatus(Result.ERROR);
            return result;
        }
        result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO21));
        result.setStatus(Result.SUCCESS);
        return result;
    }
}
