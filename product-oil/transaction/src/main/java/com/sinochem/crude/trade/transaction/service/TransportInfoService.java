package com.sinochem.crude.trade.transaction.service;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.domain.po.TransportInfo;
import com.sinochem.it.b2b.common.exception.BizException;

/**
 * 运输信息的Service
 * @author Yichen Zhao
 * date: 20180228
 */
public interface TransportInfoService {

    /**
     * 新增对象，返回新增对象的ID
     */
    Long saveTransportInfo(CurrentUser currentUser, TransportInfo transportInfo) throws BizException;

    /**
     * 复制原有对象，根据新对象属性更新后重新插入，返回新增对象的ID
     */
    Long saveAdditionalTransportInfo(CurrentUser currentUser, Long originalId, TransportInfo transportInfo) throws BizException;

    /**
     * 根据主键进行逻辑删除
     */
    @Deprecated
    void deleteTransportInfoById(CurrentUser currentUser, Long id) throws BizException;

    /**
     * 根据主键修改对象
     */
    @Deprecated
    void updateTransportInfo(CurrentUser currentUser, TransportInfo transportInfo) throws BizException;

    /**
     * 根据主键修改对象，更新有值的字段
     */
    @Deprecated
    void updateTransportInfoSelective(CurrentUser currentUser, TransportInfo transportInfo) throws BizException;

    /**
     * 根据主键查询对象
     */
    TransportInfo getTransportInfoById(CurrentUser currentUser, Long id) throws BizException;

    /**
     * 根据uuid查询对象
     */
    TransportInfo getTransportInfoByUuid(CurrentUser currentUser, String uuid) throws BizException;

    //**************************以下方法由开发者补充*********************************/

    /**
     * 根据主键进行逻辑删除
     */
    void deleteTransportInfoByIdLogical(CurrentUser currentUser, Long id) throws BizException;

}
