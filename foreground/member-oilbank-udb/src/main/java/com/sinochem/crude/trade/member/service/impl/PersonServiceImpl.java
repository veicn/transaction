package com.sinochem.crude.trade.member.service.impl;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.eyeieye.melody.util.uuid.UUIDGenerator;
import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.http.MessageHttpUtil;
import com.sinochem.crude.trade.info.remote.InfoService;
import com.sinochem.crude.trade.info.remote.MemMemberVo;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UdbCodeConstant;
import com.sinochem.crude.trade.member.dao.MemberMapper;
import com.sinochem.crude.trade.member.dao.PersonLogMapper;
import com.sinochem.crude.trade.member.dao.PersonMapper;
import com.sinochem.crude.trade.member.domain.Member;
import com.sinochem.crude.trade.member.domain.Person;
import com.sinochem.crude.trade.member.domain.PersonLog;
import com.sinochem.crude.trade.member.domain.query.EnterpriseQuery;
import com.sinochem.crude.trade.member.domain.query.PersonQuery;
import com.sinochem.crude.trade.member.domain.query.RolesQuery;
import com.sinochem.crude.trade.member.model.udbvo.PersonUdbVo;
import com.sinochem.crude.trade.member.service.EnterpriseService;
import com.sinochem.crude.trade.member.service.PersonService;
import com.sinochem.crude.trade.member.service.udbservice.PersonUdbServiceImpl;
import com.sinochem.crude.trade.member.util.UdbResult;
import com.sinochem.it.b2b.common.CommonUtils;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import com.sinochem.it.b2b.common.page.PageUtils;
import com.sinochem.it.b2b.common.utils.VisitorLocale;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private static final Log log = LogFactory.getLog(PersonServiceImpl.class);

    @Autowired
    private PersonLogMapper personLogMapper;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private UUIDGenerator uuidGenerator;
    @Autowired
    private HttpConnectionManager httpConnectionManager;
    @Autowired
    private URLBroker systemServer;
    @Autowired
    private InfoService infoService;
    @Autowired
    private URLBroker docServer;
    @Autowired
    private PersonUdbServiceImpl personUdbService;


    @Override
    public void add(Person person) throws BizException {
        UdbResult<PersonUdbVo> result = personUdbService.addUdb(person);
        if (!UdbCodeConstant.SUCCESS.equals(result.getCode())){
            throw new BizException(result.getMessage());
        }
        personMapper.insert(person);
        PersonLog personLog = new PersonLog();
        BeanUtils.copyProperties(person, personLog);
        personLog.setId(null);
        personLog.setPersonId(person.getId());
        personLogMapper.insert(personLog);
    }

    @Override
    public void update(Person person) throws BizException{


        //1.UDb查询person，
        UdbResult<PersonUdbVo> result = personUdbService.selectUdb(person);


        //2.udb插入
        if(null == result.getResponse() ){
            UdbResult<PersonUdbVo> udbPerson = personUdbService.addUdb(person);
            if(!UdbCodeConstant.SUCCESS.equals(udbPerson.getCode())){
                throw new BizException(udbPerson.getMessage());
            }
            //4.调用本地更新函数根据情况插入或更新本地person
            updateLocal(person);

        }else{
            //3.udb更新
            UdbResult<PersonUdbVo> udbUpdate = personUdbService.updateUdb(person);
            if(!UdbCodeConstant.SUCCESS.equals(udbUpdate.getCode())){
                throw new BizException(udbUpdate.getMessage());
            }
            //4.调用本地更新函数根据情况插入或更新本地person
            updateLocal(person);
        }

        //同步推送资讯
        synMemmberInterface(person.getMemberId());
//        PersonLog personLog = new PersonLog();
//        BeanUtils.copyProperties(person,personLog);
//        personLog.setId(null);
//        personLog.setPersonId(person.getId());
//        personLogMapper.insert(personLog);
//        personMapper.updateByPrimaryKey(person);
    }

    private void updateLocal(Person person ){
        Person person1 = null;

        if(person.getId() != null){
            person1 = personMapper.getPersonById(person.getId());
        }
        if(person1 == null && person.getMemberId() != null){
            person1 = personMapper.getByMemberId(person.getMemberId());
        }

        Date time = new Date();
        if (person1 == null) {
            person.setCreateTime(time);
            person.setCreateUser(person.getMemberId().toString());
            person.setUpdateUser(person.getMemberId().toString());
            person.setUpdateTime(time);
            personMapper.insert(person);
        } else {
            person.setId(person1.getId());
            person.setUpdateUser(person.getMemberId().toString());
            person.setUpdateTime(time);
            personMapper.updateByPrimaryKeySelective(person);
        }

    }
    @Override
    public Person getInfoByMemberId(Long memberId) {
        return personMapper.getByMemberId(memberId);
    }

    @Override
    public List<Person> selectByPrimary(PersonQuery personQuery) {
//        return personMapper.selectByPrimary(person);
        List<Person> list = personMapper.selectByPrimary(personQuery);
        return list;
    }

    @Override
    public List<Person> selectByPrimary(PersonQuery query, PageInfo pageInfo) {
        PageUtils.page(pageInfo);
        return personMapper.selectByPrimary(query);
    }

    @Override
    public String insertPerson(PersonLog record) {
        // TODO Auto-generated method stub
        personLogMapper.insert(record);
        return null;
    }

    @Override
    public void deletePerson(Long id) {
        // TODO Auto-generated method stub
        personLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Person toUpdate(Long id) {
        // TODO Auto-generated method stub
        Person person = personMapper.selectByPrimaryKey(id);
        return person;
    }


    public void updatePerson(PersonLog person) {
        // TODO Auto-generated method stub
        personLogMapper.updateByPrimaryKey(person);
    }

    //前台个人详情查询
    @Override
    public Person personDetail(Long memberId) {
        // TODO Auto-generated method stub
        Person person = personMapper.personDetail(memberId);
        return person;
    }

    @Override
    public void fill(Person person, Long memberId) throws BizException {
        if (person.getId() == null) {
            person.setMemberId(memberId);
        }
        //验证人员信息
        validPerson(person);
        UdbResult<PersonUdbVo> personUdb = null;
        //需要判断当前会员是否已经有人员信息
        Person p = personMapper.getByMemberId(memberId);
        if (p == null || p.getId() == null) {
            person.setCode(uuidGenerator.gain());
            personMapper.insert(person);

            //调用udbperson的插入
            personUdb = personUdbService.addUdb(person);
            if (!UdbCodeConstant.SUCCESS.equals(personUdb.getCode())){
                throw new BizException(personUdb.getMessage());
            }
        } else {//已有人员信息，修改现有人员信息
            person.setId(p.getId());
            personMapper.updateByPrimaryKeySelective(person);

            //调用udbperson的更新
            personUdb= personUdbService.updateUdb(person);
            if (!UdbCodeConstant.SUCCESS.equals(personUdb.getCode())){
                throw new BizException(personUdb.getMessage());
            }
        }
        updateLog(person);

        //同步推送资讯
        synMemmberInterface(memberId);
        /*Member member =  memberMapper.selectByPrimaryKey(memberId);
        if (member != null) {
            member.setHeadUrl(person.getHeadImg());
            member.setType("0");
            memberMapper.updateByPrimaryKey(member);
        }*/
    }

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Member getMemberById(Long memberId) {
        return memberMapper.selectByPrimaryKey(memberId);
    }

    @Override
    public Person selectByMemberId(Long id) {
        return personMapper.selectByMemberId(id);
    }

    @Override
    public List<PersonQuery> selectQueryByPrimary(PersonQuery personQuery, PageInfo pageInfo) {
        PageUtils.page(pageInfo);
        return personMapper.selectQueryByPrimary(personQuery);
    }

    @Override
    public List<RolesQuery> getRoles() {
        return personMapper.getRoles();
    }


    private void updateLog(Person person) {
        PersonLog personLog = new PersonLog();
        personLog.setId(null);
        personLog.setPersonId(person.getId());
        BeanUtils.copyProperties(person, personLog);
        personLogMapper.insert(personLog);
    }

    /**
     * 验证人员信息
     *
     * @param person
     */
    private void validPerson(Person person) throws BizException {
        PersonQuery query = new PersonQuery();
        if (null != person.getId()) {
            query.setIdNot(person.getId());
        }
        if (StringUtils.isNotEmpty(person.getCardNo())) {
            query.setCardType(person.getCardType());
            query.setCardNo(person.getCardNo());
            int count = personMapper.count(query);
            if (count > 0) {
                throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO3));
            }
        }
    }


    public List<Member> selectMembersByCredential(String code) throws BizException {
        if (StringUtils.isBlank(code)) throw new BizException("企业资质code不能为空！");
        return this.memberMapper.selectMembersByCredential(code);
    }

    public List<EnterpriseQuery> queryMList(Long memberId) {
        return this.personMapper.queryMList(memberId);
    }



    public List<Member> selectMembersByRole(String role) throws BizException {
        if (StringUtils.isBlank(role)) throw new BizException("角色code不能为空！");
        return this.memberMapper.selectMembersByRole(role);
    }

    @Override
    public List<Member> selectByPrimary(Member member) throws BizException {
        return this.memberMapper.selectByPrimary(member);
    }


    public void synMemmberInterface(Long memberId){
        log.info("个人信息推送资讯：" + memberId);
        try{
            PersonQuery personQuery = personMapper.getByMemberId(memberId);
            MemMemberVo vo = new MemMemberVo();
            vo.setLoginName(personQuery.getUserName());
            vo.setMemberName(personQuery.getName());
            vo.setMemberNameShort(personQuery.getName());
            vo.setMemberId(memberId.toString());
            vo.setNickName(personQuery.getUserName());
            vo.setPersonalNote(personQuery.getMemo());
            if(StringUtils.isNotEmpty(personQuery.getHeadImg())){
                vo.setImgurl(docServer.get("/img/"+personQuery.getHeadImg() +".img").toString());
            }
            infoService.synMemmberInterface(vo);
        }catch (Exception e){
            e.printStackTrace();
            log.error("数据推送异常：" + e.getMessage());
        }
    }

    @Override
    public Person selectUdbPerson(Long memberId) throws BizException{
//        //1.根据memberId获得udbUUid
//        String udbUUid = memberMapper.selectUdbUUidByMemberId(memberId);
//        if(null == udbUUid || "".equals(udbUUid)) throw new BizException("udbUUid为空！");
//        //2.根据udbUUid调用udbperson
//        UdbResult<PersonUdbVo> udbPerson = personUdbService.selectUdb(udbUUid);
//        if(udbPerson.getResponse() == null) return null;
//        //3.转成person
//        PersonUdbVo personUdbVo = udbPerson.getResponse();
//        Person person = new Person();
//        person.setAddressDetail(personUdbVo.getAddressDetail());
//        person.setAreaCode(personUdbVo.getAreaCode());
//        person.setBirth(new Date(personUdbVo.getBirth()));
//        person.setCardNo(personUdbVo.getCardNo());
//        person.setCardType(personUdbVo.getCardType());
//        person.setCityCode(personUdbVo.getCityCode());
//        person.setCode(personUdbVo.get);


        return null;
    }
}
