package com.sinochem.crude.trade.transaction.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.domain.BiddingSheetCombine;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.BiddingSheet;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.model.query.BiddingSheetQuery;
import com.sinochem.crude.trade.transaction.model.vo.BiddingSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.BiddingSheetQueryVO;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;

import java.util.List;

/**
 * 报价单的Service
 * @author Yichen Zhao
 * date: 20180228
 */
public interface BiddingSheetService {

    /**
     * 新增对象，返回新增对象的ID
     */
    @Deprecated
    Long saveBiddingSheet(CurrentUser currentUser, BiddingSheet biddingSheet) throws BizException;

    /**
     * 根据主键进行删除，慎用
     */
    @Deprecated
    void deleteBiddingSheetById(CurrentUser currentUser, Long id) throws BizException;

    /**
     * 根据主键修改对象
     */
    @Deprecated
    void updateBiddingSheet(CurrentUser currentUser, BiddingSheet biddingSheet) throws BizException;

    /**
     * 根据主键修改对象, 更新有值的字段
     */
    @Deprecated
    void updateBiddingSheetSelective(CurrentUser currentUser, BiddingSheet biddingSheet) throws BizException;

    /**
     * 根据主键查询对象
     */
    BiddingSheet getBiddingSheetById(CurrentUser currentUser, Long id) throws BizException;

    /**
     * 根据uuid查询对象
     */
    BiddingSheet getBiddingSheetByUuid(CurrentUser currentUser, String uuid) throws BizException;

    //**************************以下方法由开发者补充*********************************/

    /**
     * 根据主键逻辑删除
     */
    void deleteBiddingSheetByIdLogical(CurrentUser currentUser, Long id) throws BizException;

    /**
     * 修改投标单，组合各类信息
     */
    void updateBiddingSheetCombineSaveHistory(CurrentUser currentUser, BiddingSheetCombine biddingSheetCombine) throws BizException;

    /**
     * 根据条件查询报价单, 这个的List是List<BiddingSheet>，需要自行转换为List<BiddingSheetCombine>
     */
    PageInfoResult getBiddingSheetList(CurrentUser currentUser, BiddingSheetQueryVO biddingSheetQueryVO, PageInfo pageInfo)
            throws BizException;

    /**
     * 发布投标单，重新报价
     */
    Long postBiddingSheet(CurrentUser currentUser, BiddingSheetCombine biddingSheetCombine) throws BizException;

    /**
     * 变更报价单状态
     */
    void changeBiddingSheetStatus(CurrentUser currentUser, BiddingSheet biddingSheet, String targetStatusCode) throws BizException;

    /**
     * 保存投标单历史
     */
    void saveBiddingSheetHistory(CurrentUser currentUser, Long biddingSheetId) throws BizException;

    /**
     * 查询需求单下所有报价单
     */
    List<BiddingSheet> selectBySaleSheetId(Long saleSheetId);

    /**
     * 查询采购单下所有报价单
     */
    List<BiddingSheet> selectByDemandSheetId(Long demandSheetId);

    /**
     * 查询需求单下所有报价单
     */
    List<BiddingSheet> selectHistoryBySaleSheetId(CurrentUser currentUser, BiddingSheetQuery biddingSheetQuery);

    /**
     * 查询需求单下当前用户所有报价单
     */
    List<BiddingSheet> selectBySaleSheetIdAndCurrentUser(CurrentUser currentUser,Long saleSheetId);

    /**
     * 查询采购单下当前用户所有报价单
     */
    List<BiddingSheet> selectHistoryByDemandSheetId(CurrentUser currentUser, BiddingSheetQuery biddingSheetQuery);

    /**
     * 组合报价单
     */
    BiddingSheetCombine getBiddingSheetCombine(CurrentUser currentUser, BiddingSheet biddingSheet) throws BizException;

    /**
     * 查询当前用户对某个单据的报价历史
     */
    List<BiddingSheet> selectHistoryBidding(CurrentUser currentUser, BiddingSheetQuery biddingSheetQuery) throws BizException;


    /**
     * 查询需采购单下的所有报价信息
     */
    List<BiddingSheet> selectByDemendId(Long demendId);

    /**
     * 根据报价单uuid查询报价单的组合信息 武刚鹏
     * @param biddingUuid
     * @return
     */
    BiddingSheetCombineVO findBiddingSheetCombineVO(CurrentUser user,String biddingUuid)throws BizException ;

    /**
     * 查询某个报价公司对某个采购单的报价历史
     * @param currentUser
     * @param biddingSheetQuery
     * @return
     * @throws BizException
     */
    List<BiddingSheet> selectHistoryDemandBidding(CurrentUser currentUser, BiddingSheetQuery biddingSheetQuery) throws BizException;

    /**
     * 查询销售单下当前用户所有报价单
     */
    List<BiddingSheet> selectBySaleSheetIdForMe(CurrentUser currentUser,Long saleSheetId);


    /**
     * 根据uuid查询对象
     */
    BiddingSheet getBiddingSheetByUser(CurrentUser currentUser, String uuid) throws BizException;

    /**
     * 查询需求单下当前用户所有报价单
     */
    List<BiddingSheet> selectByDemandSheetIdForMe(CurrentUser currentUser,Long saleSheetId);


    /**
     * 发布投标单，重新报价
     */
    Long postBiddingDemandSheet(CurrentUser currentUser, BiddingSheetCombine biddingSheetCombine) throws BizException;

    /**
     * 根据uuid查询采购对象
     */
    BiddingSheet getBiddingDemandSheetByUser(CurrentUser currentUser, String uuid) throws BizException;

}
