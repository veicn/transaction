package com.sinochem.crude.trade.member.service;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.Member;
import com.sinochem.crude.trade.member.domain.Person;
import com.sinochem.crude.trade.member.domain.PersonLog;
import com.sinochem.crude.trade.member.domain.query.EnterpriseQuery;
import com.sinochem.crude.trade.member.domain.query.PersonQuery;
import com.sinochem.crude.trade.member.domain.query.RolesQuery;
import com.sinochem.it.b2b.common.exception.BizException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface PersonService {

    /**
     * 添加个人
     */
    public void add(Person person) throws BizException;

    /**
     * 修改个人信息
     */
    public void update(Person person) throws BizException;

    /**
     * 通过member获得个人信息
     *
     * @param memberId
     * @return
     */
    Person getInfoByMemberId(Long memberId);

    List<Person> selectByPrimary(PersonQuery personQuery);

    List<Person> selectByPrimary(PersonQuery personQuery, PageInfo pageInfo);

    public String insertPerson(PersonLog person);

    public void deletePerson(Long id);

    public Person toUpdate(Long id);

    public void updatePerson(PersonLog person);

    //前台个人详情查询
    public Person personDetail(Long memberId);

    void fill(Person person, Long memberId) throws BizException;

    public Member getMemberById(Long memberId);

    Person selectByMemberId(Long id);

    List<PersonQuery> selectQueryByPrimary(PersonQuery personQuery, PageInfo pageInfo);

    List<RolesQuery> getRoles();


    List<Member> selectMembersByCredential(String code) throws BizException;

    List<Member> selectMembersByRole(String role) throws BizException;

    List<Member> selectByPrimary(Member member) throws BizException;

	List<EnterpriseQuery> queryMList(Long memberId);

	//查询udbperson，返回person
	Person selectUdbPerson(Long memberId)throws BizException;

}
