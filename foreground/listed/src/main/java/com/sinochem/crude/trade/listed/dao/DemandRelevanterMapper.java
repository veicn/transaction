package com.sinochem.crude.trade.listed.dao;

import com.sinochem.crude.trade.listed.domain.DemandRelevanter;

import java.util.List;

public interface DemandRelevanterMapper {

    /**
     * 按主键删除
     * @param id
     *
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 插入
     * @param record
     * @return
     */
    Long insert(DemandRelevanter record);

    /**
     * 插入
     * @param record
     * @return
     */
    Long insertSelective(DemandRelevanter record);

    /**
     * 按主键查询
     * @param id
     * @return
     */
    DemandRelevanter selectByPrimaryKey(Long id);

    /**
     * 按主键更新
     * @param record
     *
     */
    void updateByPrimaryKeySelective(DemandRelevanter record);

    /**
     * 按主键更新
     * @param record
     *
     */
    void updateByPrimaryKey(DemandRelevanter record);
    
    /**
     * 按需求查询
     * @param demandId
     * @return
     */
    public List<DemandRelevanter> getDemandRelevantersByDemandId(Long demandId);


    /**
     * 查询买家联系人
     * @param demandId
     * @param i
     * @return
     */
    DemandRelevanter getDemandRelevanterByDemandIdAndTypeOne(Long demandId,  String i);

    /**
     * 查询代理商联系人
     * @param demandId
     * @param j
     * @return
     */
    DemandRelevanter getDemandRelevanterByDemandIdAndTypeTwo(Long demandId,  String j);

    /**
     * 根据需求id和类型查询联系人信息
     * @param demandId
     * @param type
     * @return
     */
    DemandRelevanter getDemandRelevantersByDemandIdAndType(Long demandId,  String type);

    /**
     * 删除需求的联系人
     * @param demandId
     */
    void deleteByDemandId(Long demandId);
}