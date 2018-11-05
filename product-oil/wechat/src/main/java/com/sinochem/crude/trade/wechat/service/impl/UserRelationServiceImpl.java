package com.sinochem.crude.trade.wechat.service.impl;

import java.util.*;

import com.eyeieye.melody.web.url.URLBroker;
import com.google.gson.Gson;
import com.sinochem.crude.trade.wechat.constant.CollectionUtils;
import com.sinochem.crude.trade.wechat.domain.WechatLogs;
import com.sinochem.crude.trade.wechat.helper.StringHelper;
import com.sinochem.crude.trade.wechat.model.vo.UserInfoVO;
import com.sinochem.crude.trade.wechat.service.WechatLogService;
import com.sinochem.crude.trade.wechat.util.RedisUtil;
import com.sinochem.it.b2b.common.result.ResultDatas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.crude.trade.wechat.domain.UserRelation;
import com.sinochem.crude.trade.wechat.dao.UserRelationMapper;
import com.sinochem.crude.trade.wechat.service.UserRelationService;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRelationServiceImpl implements UserRelationService {
    @Autowired
    private UserRelationMapper userRelationMapper;
    @Autowired
    WechatLogService wechatLogService;
    @Autowired
    URLBroker systemServer;
    @Autowired
    RedisUtil redisUtil;
    Gson gson = new Gson();
    private Logger logger = LoggerFactory.getLogger(getClass());

    public UserRelationMapper getMapper() {
        return userRelationMapper;
    }

    /**
     * 通过openid 查询member token
     * @param openid
     * @return
     */
    @Override
    public String GetMemberToken(String openid){
        logger.info("通过openid查询token："+openid);
      String redistoken= redisUtil.getMemberToken(openid);//查询redis缓存的token

       if(!StringHelper.strisnull(redistoken))
       {
           logger.info("通过openid查询token,redis返回token:"+redistoken);
           return redistoken;
       }
        UserInfoVO userInfoVO=new UserInfoVO();
        UserRelation userRelation= userRelationMapper.findByOpenid(openid);
        if(userRelation!=null){
            userInfoVO.setUserName(userRelation.getUserName());
            userInfoVO.setPassword(userRelation.getPassword());
            String token= GetMemberToken(userInfoVO);
            if(!StringHelper.strisnull(token)){

                redisUtil.setMemberToken(openid,token);
            }
            return token;
        }
       return null;
    }

    /**
     * 获取member的token
     *
     * @param userInfoVO
     */
    private String GetMemberToken(UserInfoVO userInfoVO) {
        String token ="";
        try {
            String url = "/token.htm?client_secret=zaq1xsw2cde3vfr4bgt5nhy6mju8&grant_type=password&redirect_uri=&code=0okm9ijn8uhb7ygv6tfc5rdu&client_id=0okm9ijn8uhb7ygv6tfc5rdu&response_type=code&password=" + userInfoVO.getPassword() + "&username=" + userInfoVO.getUserName();
            url = systemServer.get(url).toString();
            logger.info("member获取tokenur：" + url);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
            String jsonstr = "";
            HttpEntity<String> formEntity = new HttpEntity<String>(jsonstr, headers);
            RestTemplate restTemplate = new RestTemplate();
            String restr = restTemplate.postForObject(url, formEntity, String.class);
            logger.info("member获取token返回：" + restr);
//            memberuserres resdata = gson.fromJson(restr, memberuserres.class);
            Map<String, String> map = gson.fromJson(restr, HashMap.class);
            token = map.get("access_token");

        }
        catch (Exception e)
        {
            logger.error("member 获取token：",e);
           // e.printStackTrace();
        }
        return token;
    }

    private memberuserres GetCurrentUser(String token) {
        memberuserres resdata=null;
        try {
            String url = "/currentUser.json?oauth_token=" + token;
            url = systemServer.get(url).toString();
            logger.info("member获取用户信息url：" + url);
            HttpHeaders requestHeaders = new HttpHeaders();
            RestTemplate template = new RestTemplate();
            HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
            ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, requestEntity, String.class);
            String restr = response.getBody();
            logger.info("member 返回：" + restr);
            resdata = gson.fromJson(restr, memberuserres.class);
        }
        catch (Exception e){
            logger.error("member 获取用户信息：",e);
          //  e.printStackTrace();
        }

        return resdata;
//		int memberId = (Integer) map.get("memberId");
//		int epMemberId = (Integer) map.get("epMemberId");
//		List<String> roles = (List) map.get("roles");

    }
    private class memberuserres{
        private memberuser user;
        private int memberId;

        public memberuser getUser() {
            return user;
        }

        public void setUser(memberuser user) {
            this.user = user;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }
    }
    private class memberuser
    {
        private int memberId;
        private int pMemberId;
        private List<String> roles;

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public int getpMemberId() {
            return pMemberId;
        }

        public void setpMemberId(int pMemberId) {
            this.pMemberId = pMemberId;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }
    }


    @Override
    public ResultDatas<UserRelation> MemberLogin(UserInfoVO userInfoVO) {
        ResultDatas<UserRelation> resultDatas = new ResultDatas<UserRelation>();
        //log
        WechatLogs wechatLogs = new WechatLogs();
        wechatLogs.setServicename("login.json");
        Gson gson = new Gson();
        String arvstr = "登录参数" + gson.toJson(userInfoVO);
        logger.info(arvstr);
        wechatLogs.setDesc(arvstr);
        wechatLogs.setType(1);
        wechatLogService.addlog(wechatLogs);
        if (StringHelper.strisnull(userInfoVO.getOpenid())) {
            resultDatas.setFail("openid is null");
            return resultDatas;
        }
        String token = GetMemberToken(userInfoVO);
        if (StringHelper.strisnull(token)) {
            resultDatas.setFail("token is null");
            return resultDatas;
        }
        memberuserres user = GetCurrentUser(token);
        if (user==null||user.getUser()==null) {
            resultDatas.setFail("user is null");
            return resultDatas;
        }

        int memberId = user.getUser().getMemberId();
        int epMemberId = user.getUser().getpMemberId();
        if(epMemberId==0)//特殊处理 企业账号没有epmemberid，就是memberid
        {
            epMemberId=memberId;
        }

        List<String> roles =user.getUser().getRoles();

        String roleStr = CollectionUtils.listToString(roles);
        UserRelation userRelation = new UserRelation();
        userRelation.setUuid(UUID.randomUUID().toString());
        userRelation.setMemberId(Long.parseLong(memberId + ""));
        userRelation.setEpMemberId(Long.parseLong(epMemberId + ""));
        userRelation.setRole(roleStr);
        userRelation.setOpenid(userInfoVO.getOpenid());
        userRelation.setAliveFlag("1");
        userRelation.setVersion("1");
        userRelation.setCreateDate(new Date());
        userRelation.setLangVer("zh-EN");
        userRelation.setUserName(userInfoVO.getUserName());
        userRelation.setPassword(userInfoVO.getPassword());
        //先解绑
        deleteRecordByOpenid(userRelation);
        insertRecord(userRelation);
        resultDatas.setDatas(userRelation);
        //log
        WechatLogs wechatLogs2 = new WechatLogs();
        wechatLogs2.setServicename("login.json");
        wechatLogs2.setDesc("返回参数:" + gson.toJson(resultDatas));
        wechatLogs2.setType(2);
        wechatLogService.addlog(wechatLogs2);
        return resultDatas;
    }


    /**
     * 新增
     */
    @Override
    public int insertRecord(UserRelation userrelation) {
        return userRelationMapper.insertRecord(userrelation);
    }

    /**
     * 根据主键物理删除, 慎用！！！
     */
    @Override
    public int deleteById(Long wechatUserId) throws BizException {
        if (wechatUserId == null) {
            throw new BizException("id 为空，不能修改 ");
        }
        return userRelationMapper.deleteById(wechatUserId);
    }

    /**
     * 根据条件-物理删除对象执行delete语句, 慎用！！！
     */
    @Override
    public int deleteRecords(UserRelation record) {
        return userRelationMapper.deleteRecords(record);
    }

    /**
     * 根据主键-修改对象
     */
    @Override
    public int updateRecordById(UserRelation userRelation) throws BizException {
        if (userRelation.getWechatUserId() == null) {
            throw new BizException("wechatUserId 为空，不能修改 ");
        }
        return userRelationMapper.updateRecordById(userRelation);
    }

    /**
     * 根据uuid-修改对象
     */
    @Override
    public int updateRecordByUuid(UserRelation userRelation) throws BizException {
        if (userRelation.getUuid() == null) {
            throw new BizException("uuid为空，不能修改 ");
        }
        return userRelationMapper.updateRecordByUuid(userRelation);
    }

    /**
     * 根据条件-批量修改数据(危险慎用)
     */
    @Override
    public int updateRecords(Map<String, Object> map) {
        return userRelationMapper.updateRecords(map);
    }

    /**
     * 根据条件-批量修改数据(危险慎用)
     */
    @Override
    public int updateRecords(UserRelation userRelation) {
        return userRelationMapper.updateRecords(userRelation.toMap());
    }


    /**
     * 根据主键-查询对象
     */
    @Override
    public UserRelation findByPrimaryKey(Long wechatUserId) {
        return userRelationMapper.findByPrimaryKey(wechatUserId);
    }

    /**
     * 根据uuid查询对象
     */
    @Override
    public UserRelation findByUuid(String uuid) {
        return userRelationMapper.findByUuid(uuid);
    }

    /**
     * 根据对象-查询对象列表
     */
    @Override
    public List<UserRelation> queryByEntitys(UserRelation userRelation) {
        return userRelationMapper.queryByEntitys(userRelation);
    }

    /**
     * 根据条件-查询全部
     */
    @Override
    public List<Map<String, Object>> queryRecords(Map<String, Object> map) {
        return userRelationMapper.queryRecords(map);
    }

    /**
     * 根据条件-分页查询
     */
//	@Override
//	public Page<Map<String, Object>> queryRecords(Map<String, Object> map, SimplePageInfo pageInfo){
//		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getCount());
//		return (Page<Map<String, Object>>) userRelationMapper.queryRecords(map);
//	}

    /**
     * 根据条件-查询记录条数
     */
    @Override
    public int countRecords(Map<String, Object> map) {
        return userRelationMapper.countRecords(map);
    }

    //**************************以下方法为开发者补充*********************************/

    /**
     * 根据uuid查询对象
     */
    @Override
    public UserRelation findByOpenid(String openid) {
        return userRelationMapper.findByOpenid(openid);
    }

    /**
     * 根据openid逻辑删除
     */
    @Override
    public int deleteRecordByOpenid(UserRelation userRelation) {
        return userRelationMapper.deleteRecordByOpenid(userRelation);
    }
}
