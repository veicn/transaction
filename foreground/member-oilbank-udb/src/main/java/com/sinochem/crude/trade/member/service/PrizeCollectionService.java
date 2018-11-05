package com.sinochem.crude.trade.member.service;

import com.sinochem.crude.trade.member.domain.PrizeCollection;
import com.sinochem.crude.trade.member.model.PrizeCollectionVO;

import java.util.List;


/**
 * @made by WangTing
 * 用以实现有奖征集控制的service
 *
 */


public interface PrizeCollectionService {

    /**
     * 添加
     */
    public void add(PrizeCollection prizeCollection) ;

    /**
     * 查询所有有奖征集信息列表
     */
    public List<PrizeCollectionVO> selectAll();


    public List<PrizeCollectionVO> selectByContactUser(String contactUser);

    /**
     * 根据 id删除
     * @param id
     */
    public void deleteByPrimaryKey(Long id);


}
