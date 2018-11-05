package com.sinochem.crude.trade.transaction.dao;

import com.sinochem.crude.trade.transaction.domain.po.BiddingSheet;
import com.sinochem.crude.trade.transaction.model.query.BiddingSheetQuery;

import java.util.List;

public interface BiddingSheetMapper {

    /**
     * 新增对象
     */
    int insert(BiddingSheet biddingSheet);

    /**
     * 根据主键物理删除, 慎用
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据主键修改对象
     */
    int updateByPrimaryKey(BiddingSheet biddingSheet);

    /**
     * 根据主键修改对象，更新有值的字段
     */
    int updateByPrimaryKeySelective(BiddingSheet biddingSheet);

    /**
     * 根据主键查询对象
     */
    BiddingSheet selectByPrimaryKey(Long id);

    /**
     * 根据uuid查询对象
     */
    BiddingSheet selectByUuid(String uuid);

    //**************************以下方法由开发者补充*********************************/

    /**
     * 根据条件查询报价单
     */
    List<BiddingSheet> getBiddingSheetList(BiddingSheetQuery biddingSheetQuery);

    /**
     * 查询泉化能看到的报价单
     */
    List<BiddingSheet> getQuanzhouBiddingList(BiddingSheetQuery biddingSheetQuery);

    /**
     *一个销售资源下的所有报价
     */
    List<BiddingSheet> selectBySaleSheetId(Long saleSheetId);

    /**
     *一个采購资源下的所有报价
     */
    List<BiddingSheet> selectByDemandSheetId(Long demandSheetId);

    /**
     *一个销售资源下的所有报价
     */
    List<BiddingSheet> selectHistoryBySaleSheetId(BiddingSheetQuery biddingSheetQuery);

    /**
     *采购资源下的所有报价
     */
    List<BiddingSheet> selectHistoryByDemandSheetId(BiddingSheetQuery biddingSheetQuery);

    /**
     *一个销售资源下当前用户的所有报价
     */
    List<BiddingSheet> selectBySaleSheetIdAndCurrentUser(BiddingSheetQuery biddingSheetQuery);
    /**
     * 当前用户报价历史
     */
    List<BiddingSheet> selectHistoryBidding(BiddingSheetQuery biddingSheetQuery);


    /**
     *一个caig的所有报价
     */
    List<BiddingSheet> selectByDemandId(Long demandId);

    /**
     * 当前用户报价历史
     */
    List<BiddingSheet> selectHistoryDemandBidding(BiddingSheetQuery biddingSheetQuery);

    /**
     *一个销售资源下当前用户的所有报价
     */
    List<BiddingSheet> selectBySaleSheetIdForMe(BiddingSheetQuery biddingSheetQuery);


    /**
     *一个销售资源下当前用户的报价
     */
    BiddingSheet getBiddingSheetByUser(BiddingSheetQuery biddingSheetQuery);


    /**
     *一个采购资源下当前用户的所有报价
     */
    List<BiddingSheet> selectByDemandSheetIdForMe(BiddingSheetQuery biddingSheetQuery);


    /**
     *一个采购下当前用户的报价
     */
    BiddingSheet getBiddingDemandSheetByUser(BiddingSheetQuery biddingSheetQuery);


}