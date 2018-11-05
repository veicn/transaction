package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TDataPartner;
import java.util.*;

public interface TDataPartnerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TDataPartner record);

    int insertSelective(TDataPartner record);

    TDataPartner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TDataPartner record);

    int updateByPrimaryKey(TDataPartner record);

    List<TDataPartner> selectAllList();

    List<TDataPartner> selectListByRole(String role);

    TDataPartner findByQuery(TDataPartner tDataPartner);
}