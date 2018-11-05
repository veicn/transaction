package com.sinochem.crude.trade.member.remote.impl;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.member.dao.PersonMapper;
import com.sinochem.crude.trade.member.domain.Person;
import com.sinochem.crude.trade.member.domain.query.PersonQuery;
import com.sinochem.crude.trade.member.remote.service.PersonService;
import com.sinochem.crude.trade.member.remote.vo.PersonVo;
import com.sinochem.it.b2b.common.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RemotePersonServiceImpl implements PersonService{

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private URLBroker docServer;
    @Override
    public PersonVo getByMemberId(Long memberId) {
        PersonQuery person  = personMapper.getByMemberId(memberId);
        PersonVo personVo = new PersonVo();
        if(person != null){
            personVo.setUserName(person.getUserName());
            personVo.setName(person.getName());
            personVo.setEmail(person.getEmail());
            personVo.setMobile(person.getMobile());
            personVo.setCardType(person.getCardType());
            personVo.setCardNo(person.getCardNo());
            personVo.setMemberId(person.getMemberId());
            personVo.setMemo(person.getMemo());
            if(StringUtils.isNotEmpty(person.getHeadImg())){
                personVo.setHeadImg(docServer.get("/img/"+person.getHeadImg() +".img").toString());
            }
        }
        return personVo;
    }

    @Override
    public List<PersonVo> getByEpMemberIdAndRoles(Long epMemberId, String roleCode) {
        PersonQuery personQuery  = new PersonQuery();
        personQuery.setMemberId(epMemberId);
        personQuery.setRoleCode(roleCode);
        List<PersonVo> personList = new ArrayList<>();
        List<PersonQuery> persons  = personMapper.queryMListByEpMemberRoles(personQuery);
        if(persons != null && persons.size() > 0){
            for(PersonQuery person : persons){
                PersonVo personVo = new PersonVo();
                personVo.setUserName(person.getUserName());
                personVo.setName(person.getName());
                personVo.setEmail(person.getEmail());
                personVo.setMobile(person.getMobile());
                personVo.setCardType(person.getCardType());
                personVo.setCardNo(person.getCardNo());
                personVo.setMemberId(person.getMemberId());
                personVo.setMemo(person.getMemo());
                personList.add(personVo);
            }
        }
        return personList;
    }
}
