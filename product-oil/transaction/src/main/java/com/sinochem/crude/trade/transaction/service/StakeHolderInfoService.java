package com.sinochem.crude.trade.transaction.service;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.domain.po.StakeholderInfo;
import com.sinochem.it.b2b.common.exception.BizException;

/**
 * 干系人（买方，买方）的Service
 * @author Yichen Zhao
 * date: 20180228
 */
public interface StakeHolderInfoService {

    /**
     * 新增对象，返回新增对象的ID
     */
    Long saveStakeholderInfo(CurrentUser currentUser, StakeholderInfo stakeholderInfo) throws BizException;

    /**
     * 根据主键进行物理删除，慎用
     */
    @Deprecated
    void deleteStakeholderInfoById(CurrentUser currentUser, Long id) throws BizException;

    /**
     * 根据主键修改对象
     */
    @Deprecated
    void updateStakeholderInfo(CurrentUser currentUser, StakeholderInfo stakeholderInfo) throws BizException;

    /**
     * 根据主键修改对象，更新有值的字段
     */
    @Deprecated
    void updateStakeholderInfoSelective(CurrentUser currentUser, StakeholderInfo stakeholderInfo) throws BizException;

    /**
     * 根据主键查询对象
     */
    StakeholderInfo getStakeholderInfoById(CurrentUser currentUser, Long id) throws BizException;

    /**
     * 根据uuid查询对象
     */
    StakeholderInfo getStakeholderInfoByUuid(CurrentUser currentUser, String uuid) throws BizException;

    //**************************以下方法由开发者补充*********************************/

    /**
     * 根据主键进行逻辑删除
     */
    void deleteStakeholderInfoByIdLogical(CurrentUser currentUser, Long id) throws BizException;
}
