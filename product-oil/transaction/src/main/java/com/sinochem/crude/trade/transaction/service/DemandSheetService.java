package com.sinochem.crude.trade.transaction.service;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.domain.DemandSheetCombine;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.BiddingSheet;
import com.sinochem.crude.trade.transaction.domain.po.DemandSheet;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.model.vo.DemandSheetQueryVO;
import com.sinochem.crude.trade.transaction.model.vo.DemandSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.DemandSheetVO;
import com.sinochem.crude.trade.transaction.model.vo.SaleSheetQueryVO;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;

/**
 *
 *
 * 采购单的Service
 * @author gyf
 * date: 20180228
 */
public interface DemandSheetService {


    /**
     * 发布/保存采购需求
     */
    Long postDemandSheet(CurrentUser currentUser, DemandSheetCombine demandSheetCombine) throws BizException;

    /**
     * 保存采购需求单历史
     */
    void saveDemandSheetHistory(CurrentUser currentUser, Long demandSheetId) throws BizException;

    /**
     * 变更采购单状态
     */
    void changeDemandSheetStatus(CurrentUser currentUser, DemandSheet demandSheet, String targetStatusCode) throws BizException;

    /**
     * 组合采购单
     */
    DemandSheetCombine getDemandSheetCombine(CurrentUser currentUser, DemandSheet demandSheet) throws BizException;

    /**
     * 根据主键查询对象
     */
    DemandSheet getDemandSheetById(CurrentUser currentUser, Long id) throws BizException;

    /**
     * 根据uuid查询对象
     */
    DemandSheet getDemandSheetByUuid(CurrentUser currentUser, String uuid) throws BizException;

    /**
     * 修改采购单，组合各类信息
     */
    void updateDemandSheetCombineSaveHistory(CurrentUser currentUser, DemandSheetCombine demandSheetCombine) throws BizException;

    /**
     * 根据uuid获取采购基本信息 -武刚鹏 -2018年5月19日14:00:50
     * @param uuid 采购单uuid
     * @return
     */
    DemandSheetCombineVO findDemandDetails(CurrentUser user, String uuid)throws BizException;


    /**
     * 接受报价 武刚鹏 -2018年5月21日14:00:42
     * @param user
     * @param biddingUuid
     * @return
     * @throws BizException
     */
    Boolean acceptBidding(CurrentUser user,String biddingUuid)throws BizException;

    /**
     * 关闭采购单的发布 -武刚鹏 -2018年5月21日14:41:51
     * @param user
     * @param demandUuid
     */
    void cancelDemand(CurrentUser user, String demandUuid)throws BizException;
    /**
     * 获取采购列表
     */
    PageInfoResult getDemandSheetList(CurrentUser currentUser, DemandSheetQueryVO demandSheetQueryVO, PageInfo pageInfo) throws BizException;

    /**
     * 采購列表商品 上架 、下架
     */
    void demandShelfOrOffShelf(CurrentUser currentUser, String uuid, String code) throws BizException;

    /**
     * 根据uuid进行物理删除，慎用
     */
    void deleteDemandSheetByIdLogical(CurrentUser currentUser, String uuid) throws BizException;


    /**
     * 根据条件查询销售需求单，并受权限控制（比如定向企业），这个的List是List<SaleSheet>，需要自行转换为List<SaleSheetCombine>
     */
    PageInfoResult getVisibleDemandSheetList(CurrentUser currentUser, DemandSheetQueryVO demandSheetQueryVO, PageInfo pageInfo) throws BizException;


}
