package com.sinochem.crude.trade.transaction.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.BiddingSheet;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetWXVO;
import com.sinochem.crude.trade.transaction.model.vo.SaleSheetQueryVO;
import com.sinochem.crude.trade.transaction.model.vo.SaleSheetVO;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.result.ResultDatas;

/**
 * 销售单的Service
 * @author Yichen Zhao
 * date: 20180228
 */
public interface SaleSheetService {

    /**
     * 新增对象，返回新增对象的ID
     */
    @Deprecated
    Long saveSaleSheet(CurrentUser currentUser, SaleSheet saleSheet) throws BizException;

    /**
     * 根据主键进行物理删除，慎用
     */
    @Deprecated
    void deleteSaleSheetById(CurrentUser currentUser, Long id) throws BizException;

    /**
     * 根据主键修改对象
     */
    @Deprecated
    void updateSaleSheet(CurrentUser currentUser, SaleSheet saleSheet) throws BizException;

    /**
     * 根据主键修改对象，更新有值的字段
     */
    @Deprecated
    void updateSaleSheetSelective(CurrentUser currentUser, SaleSheet saleSheet) throws BizException;

    /**
     * 根据主键查询对象
     */
    SaleSheet getSaleSheetById(CurrentUser currentUser, Long id) throws BizException;

    /**
     * 根据uuid查询对象
     */
    SaleSheet getSaleSheetByUuid(CurrentUser currentUser, String uuid) throws BizException;

    //**************************以下方法由开发者补充*********************************/

    /**
     * 根据uuid进行物理删除，慎用
     */
    void deleteSaleSheetByIdLogical(CurrentUser currentUser, String uuid) throws BizException;

    /**
     * 发布/保存销售需求
     */
    Long postSaleSheet(CurrentUser currentUser, SaleSheetCombine saleSheetCombine) throws BizException;

    /**
     * 修改销售单，组合各类信息
     */
    void updateSaleSheetCombineSaveHistory(CurrentUser currentUser, SaleSheetCombine saleSheetCombine) throws BizException;

    /**
     * 根据条件查询销售需求单，这个的List是List<SaleSheet>，需要自行转换为List<SaleSheetCombine>
     */
    PageInfoResult getSaleSheetList(CurrentUser currentUser, SaleSheetQueryVO saleSheetQueryVO, PageInfo pageInfo) throws BizException;

    /**
     * 根据条件查询销售需求单，并受权限控制（比如定向企业），这个的List是List<SaleSheet>，需要自行转换为List<SaleSheetCombine>
     */
    PageInfoResult getVisibleSaleSheetList(CurrentUser currentUser, SaleSheetQueryVO saleSheetQueryVO, PageInfo pageInfo) throws BizException;

    /**
     * 变更销售单状态
     */
    void changeSaleSheetStatus(CurrentUser currentUser, SaleSheet saleSheet, String targetStatusCode) throws BizException;

    /**
     * 确认中标
     */
    void confirmBidding(CurrentUser currentUser, SaleSheet salesSheet, BiddingSheet biddingSheet) throws BizException;

    /**
     * 保存销售需求单历史
     */
    void saveSaleSheetHistory(CurrentUser currentUser, Long saleSheetId) throws BizException;

    /**
     * 组合销售单
     */
    SaleSheetCombine getSaleSheetCombine(CurrentUser currentUser, SaleSheet saleSheet) throws BizException;

    /**
     * 流标
     */
    void cancelBiddingSheet(CurrentUser currentUser, SaleSheet saleSheet) throws BizException;

    /**
     * 资源列表商品 上架 、下架
     */
    void productShelfOrOffShelf(CurrentUser currentUser, String uuid, String code) throws BizException;


/*    public ResultDatas<ContractSheetWXVO> getContractSheetListByeuuid(String uuid) throws BizException ;

    public ContractSheetWXVO getContractSheetListByuuid(String uuid) throws BizException ;*/

}
