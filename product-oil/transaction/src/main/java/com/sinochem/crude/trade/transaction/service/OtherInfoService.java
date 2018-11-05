package com.sinochem.crude.trade.transaction.service;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.domain.po.OtherInfo;
import com.sinochem.it.b2b.common.exception.BizException;

/**
 * 其它信息的Service
 * @author Yichen Zhao
 * date: 20180228
 */
public interface OtherInfoService {

    /**
     * 新增对象，返回新增对象的ID
     */
    Long saveOtherInfo(CurrentUser currentUser, OtherInfo otherInfo) throws BizException;

    /**
     * 复制原有对象，根据新对象属性更新后重新插入，返回新增对象的ID
     */
    Long saveAdditionalOtherInfo(CurrentUser currentUser, Long originalId, OtherInfo otherInfo) throws BizException;

    /**
     * 根据主键进行物理删除，慎用
     */
    @Deprecated
    void deleteOtherInfoById(CurrentUser currentUser, Long id) throws BizException;

    /**
     * 根据主键修改对象
     */
    @Deprecated
    void updateOtherInfo(CurrentUser currentUser, OtherInfo otherInfo) throws BizException;

    /**
     * 根据主键修改对象，更新有值的字段
     */
    @Deprecated
    void updateOtherInfoSelective(CurrentUser currentUser, OtherInfo otherInfo) throws BizException;

    /**
     * 根据主键查询对象
     */
    OtherInfo getOtherInfoById(CurrentUser currentUser, Long id) throws BizException;

    /**
     * 根据uuid查询对象
     */
    OtherInfo getOtherInfoByUuid(CurrentUser currentUser, String uuid) throws BizException;

    //**************************以下方法由开发者补充*********************************/

    /**
     * 根据主键进行逻辑删除
     */
    void deleteOtherInfoByIdLogical(CurrentUser currentUser, Long id) throws BizException;
}
