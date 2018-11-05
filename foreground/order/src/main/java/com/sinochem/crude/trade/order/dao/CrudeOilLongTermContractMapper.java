package com.sinochem.crude.trade.order.dao;




import com.sinochem.crude.trade.order.domain.CrudeOilLongTermContract;
import com.sinochem.crude.trade.order.model.query.LongContractQuery;

import java.util.List;

public interface CrudeOilLongTermContractMapper {
    /**
     * 删除
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 插入
     */
    Long insert(CrudeOilLongTermContract record);

    /**
     * 非空查询
     */
    Long insertSelective(CrudeOilLongTermContract record);

    /**
     * 主键查询
     */
    CrudeOilLongTermContract selectByPrimaryKey(Long id);

    /**
     * 非空字段更新
     */
    void updateByPrimaryKeySelective(CrudeOilLongTermContract record);

    /**
     * 根据主键更新
     */
    void updateByPrimaryKey(CrudeOilLongTermContract record);

    /**
     * 查询所有对象list
     * @param
     * @return
     */
    public List<CrudeOilLongTermContract> queryByLongContract(LongContractQuery query);

}