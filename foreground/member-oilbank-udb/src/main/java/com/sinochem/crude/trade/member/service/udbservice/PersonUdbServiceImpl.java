package com.sinochem.crude.trade.member.service.udbservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sinochem.crude.trade.member.contact.UdbUrlMapping;
import com.sinochem.crude.trade.member.dao.MemberMapper;
import com.sinochem.crude.trade.member.domain.Member;
import com.sinochem.crude.trade.member.domain.Person;
import com.sinochem.crude.trade.member.helper.HttpClientHelper;
import com.sinochem.crude.trade.member.model.udbvo.PersonUdbVo;
import com.sinochem.crude.trade.member.util.UdbResult;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Summer on 2018-07-27.
 */
@Component
public class PersonUdbServiceImpl {
    @Value("${UDB.webSite}")
    private String webSite;

    @Autowired
    private HttpClientHelper httpClientHelper;
    @Autowired
    private MemberMapper memberMapper;

    public UdbResult<PersonUdbVo>  addUdb(Person person)throws BizException{
        //1.获得url
        String url = UdbUrlMapping.PERSON;
        //2.获得实体
        //查询member表获得accountUid
        Long memberId = person.getMemberId();
        if(null==memberId ) throw  new BizException("memberId不能为空");
        Member member = memberMapper.selectByPrimaryKey(memberId);
        if(null == member.getUdbUUid() || null == member) throw  new BizException("udbUUid不能为空");

        PersonUdbVo personUdbVo = new PersonUdbVo();
        personUdbVo.setAccountUid(member.getUdbUUid().toString());
        personUdbVo.transferPersonVoToPersonUdbVo(person);
        personUdbVo.setWebSite(webSite);

        //3.调用HttpClientHelper
        String result = httpClientHelper.httpPost(personUdbVo,url);
        UdbResult<PersonUdbVo> udbResult = (UdbResult<PersonUdbVo>)JSON.parseObject(result, new TypeReference<UdbResult<PersonUdbVo>>(){});

        return udbResult;
    }


    public UdbResult<PersonUdbVo> selectUdb(Person person) throws BizException{
        //1.获得url
        String url = UdbUrlMapping.PERSON;
        //2.获得实体
        //2.1 获得udbuuid
        if(null == person.getMemberId()) throw new BizException("memberId为空，无法查到udbUUid");
        Member member = memberMapper.selectByPrimaryKey(person.getMemberId());
        if(null == member.getUdbUUid()) throw new BizException("udbUUid不能为空");
        PersonUdbVo personUdbVo = new PersonUdbVo();
        personUdbVo.setWebSite(webSite);
        personUdbVo.setAccountUid(member.getUdbUUid());

        //3.调用httpclientHelper
        String result = httpClientHelper.httpGet(personUdbVo,url);
        UdbResult<PersonUdbVo> udbResult = (UdbResult<PersonUdbVo>)JSON.parseObject(result,new TypeReference<UdbResult<PersonUdbVo>>(){});
        return udbResult;
    }

    public UdbResult<PersonUdbVo> selectUdb(String  udbUUid) throws BizException{
        //1.获得url
        String url = UdbUrlMapping.PERSON;
        //2.获得实体
        //2.1 获得udbuuid
        if(null == udbUUid) throw  new BizException("udbUUid不能为空");

        PersonUdbVo personUdbVo = new PersonUdbVo();
        personUdbVo.setWebSite(webSite);
        personUdbVo.setAccountUid(udbUUid);

        //3.调用httpclientHelper
        String result = httpClientHelper.httpGet(personUdbVo,url);
        UdbResult<PersonUdbVo> udbResult = (UdbResult<PersonUdbVo>)JSON.parseObject(result,new TypeReference<UdbResult<PersonUdbVo>>(){});
        return udbResult;
    }

    public UdbResult<PersonUdbVo> updateUdb(Person person)throws BizException{
        //1.获得URL
        String url = UdbUrlMapping.PERSON;
        //2.获得实体
        PersonUdbVo personUdbVo = new PersonUdbVo();
        if(null == person.getMemberId()) throw new BizException("memberId为空，无法查到udbUUid");
        Member member = memberMapper.selectByPrimaryKey(person.getMemberId());
        if(null == member.getUdbUUid()) throw new BizException("udbUUid不能为空");
        personUdbVo.setAccountUid(member.getUdbUUid());
        personUdbVo.setWebSite(webSite);
        personUdbVo.transferPersonVoToPersonUdbVo(person);
        //3.调用httpClientHelper
        String result = httpClientHelper.httpPut(personUdbVo,url);
        UdbResult<PersonUdbVo> udbResult = (UdbResult<PersonUdbVo>)JSON.parseObject(result,new TypeReference<UdbResult<PersonUdbVo>>(){});
        return udbResult;
    }
}
