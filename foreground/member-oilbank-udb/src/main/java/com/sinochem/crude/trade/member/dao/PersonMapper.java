package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.Person;
import com.sinochem.crude.trade.member.domain.query.EnterpriseQuery;
import com.sinochem.crude.trade.member.domain.query.PersonQuery;
import com.sinochem.crude.trade.member.domain.query.RolesQuery;
import com.sinochem.crude.trade.member.remote.vo.PersonVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person person);

	List<Person> selectByPrimary(PersonQuery query);

	//前台个人详情查询
		Person personDetail(Long memberId);

    PersonQuery getByMemberId(Long memberId);

    Person getPersonById(Long id);

    List<Person> selectByNameOrEmail(@Param("keyword") String keyword);

    int count(PersonQuery query);

    Person selectByMemberId(Long id);

    List<PersonQuery> selectQueryByPrimary(PersonQuery personQuery);

    List<RolesQuery> getRoles();

    List<EnterpriseQuery> queryMList(@Param("mMemberId")Long memberId);

    List<PersonQuery> queryMListByEpMemberRoles(PersonQuery personQuery);
}