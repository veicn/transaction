package com.sinochem.crude.trade.transaction.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.domain.BiddingSheetCombine;
import com.sinochem.crude.trade.transaction.domain.ContractSheetCombine;
import com.sinochem.crude.trade.transaction.domain.SimplePageInfo;
import com.sinochem.crude.trade.transaction.domain.po.BiddingSheet;
import com.sinochem.crude.trade.transaction.domain.po.ContractSheet;
import com.sinochem.crude.trade.transaction.domain.po.DemandSheet;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetQueryVO;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;

import java.util.List;

/**
 * 合约（订单）的Service
 * @author Yichen Zhao
 * date: 20180228
 */
public interface ContractSheetService {

    /**
     * 新增对象，返回新增对象的ID
     */
    @Deprecated
    Long saveContractSheet(CurrentUser currentUser, ContractSheet contractSheet) throws BizException;

    /**
     * 根据主键进行物理删除，慎用
     */
    @Deprecated
    void deleteContractSheetById(CurrentUser currentUser, Long id) throws BizException;

    /**
     * 根据主键修改对象
     */
    @Deprecated
    void updateContractSheet(CurrentUser currentUser, ContractSheet contractSheet) throws BizException;

    /**
     * 根据主键修改对象，更新有值的字段
     */
    @Deprecated
    void updateContractSheetSelective(CurrentUser currentUser, ContractSheet contractSheet) throws BizException;

    /**
     * 根据主键查询对象
     */
    ContractSheet getContractSheetById(CurrentUser currentUser, Long id) throws BizException;

    /**
     * 根据uuid查询对象
     */
    ContractSheet getContractSheetByUuid(CurrentUser currentUser, String uuid) throws BizException;

    //**************************以下方法由开发者补充*********************************/

    /**
     * 根据条件查询合约单（订单）
     */
    PageInfoResult getContractSheetList(CurrentUser currentUser, ContractSheetQueryVO contractSheetQueryVO, PageInfo pageInfo)
            throws BizException;
    Page<ContractSheet> getOMContractSheetList(ContractSheetQueryVO contractSheetQueryVO, SimplePageInfo simplePageInfo);
    /**
     * 根据条件查询合约单（订单）
     */
    List<ContractSheet> getContractSheetListByeEpmemberid(long epMemeberid,String keywords);


    /**
     * 修改投标单，组合各类信息
     */
    void updateContractSheetCombineSaveHistory(CurrentUser currentUser, ContractSheetCombine contractSheetCombine) throws BizException;

    /**
     * 根据主键进行逻辑删除
     */
    void deleteContractSheetByIdLogical(CurrentUser currentUser, Long id) throws BizException;

    /**
     * 生成合约单
     */
    ContractSheet generateContractSheet(CurrentUser currentUser, SaleSheet saleSheet, BiddingSheet biddingSheet) throws BizException;

    /**
     * 改变合约单状态
     */
    void changeContractSheetStatus(CurrentUser currentUser, ContractSheet contractSheet, String targetStatusCode) throws BizException;

    /**
     * 保存合约单历史
     */
    void saveContractSheetHistory(CurrentUser currentUser, Long contractSheetId) throws BizException;

    /**
     * 组合合约单
     */
    ContractSheetCombine getContractSheetCombine(CurrentUser currentUser, ContractSheet contractSheet) throws BizException;

    /**
     * 根据采购单和报价单 生成合约单 武刚鹏 -2018年5月21日14:34:28
     * @param currentUser
     * @param demandSheet
     * @param biddingSheet
     * @throws BizException
     */
    void generateContractSheet(CurrentUser currentUser, DemandSheet demandSheet, BiddingSheet biddingSheet) throws BizException;

    /**
     * 根据serial_number查询对象
     */
    ContractSheet selectBySerialNumber(CurrentUser currentUser, String serialNumber) throws BizException;

    /**
     * 委托上链
     * @param currentUser
     * @param contractuuid
     * @param appointType
     * @param appointEnterpriseId
     * @param appointEnterpriseName
     * @return
     * @throws BizException
     */
    void contractAppiont(CurrentUser currentUser, String contractuuid, String appointType,
                                       String appointEnterpriseId,String appointEnterpriseName) throws BizException;

}
