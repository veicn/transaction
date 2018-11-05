package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.Person;
import com.sinochem.crude.trade.member.domain.PersonLog;

import java.util.List;

public interface PersonLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PersonLog record);

    int insertSelective(PersonLog record);

    PersonLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PersonLog record);

    int updateByPrimaryKey(PersonLog record);
    
    List<Person> selectByPrimary();

  
}