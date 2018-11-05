package com.sinochem.crude.trade.transaction.service;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.domain.po.ProductInfo;
import com.sinochem.it.b2b.common.exception.BizException;

/**
 * 商品信息的Service
 * @author Yichen Zhao
 * date: 20180228
 */
public interface ProductInfoService {

    /**
     * 新增对象，返回新增对象的ID
     */
    Long saveProductInfo(CurrentUser currentUser, ProductInfo productInfo) throws BizException;

    /**
     * 复制原有对象，根据新对象属性更新后重新插入，返回新增对象的ID
     */
    Long saveAdditionalProductInfo(CurrentUser currentUser, Long originalId, ProductInfo productInfo) throws BizException;

    /**
     * 根据主键进行物理删除，慎用
     */
    @Deprecated
    void deleteProductInfoById(CurrentUser currentUser, Long id) throws BizException;

    /**
     * 根据主键修改对象
     */
    @Deprecated
    void updateProductInfo(CurrentUser currentUser, ProductInfo productInfo) throws BizException;

    /**
     * 根据主键修改对象，更新有值的字段
     */
    @Deprecated
    void updateProductInfoSelective(CurrentUser currentUser, ProductInfo productInfo) throws BizException;

    /**
     * 根据主键查询对象
     */
    ProductInfo getProductInfoById(CurrentUser currentUser, Long id) throws BizException;

    /**
     * 根据uuid查询对象
     */
    ProductInfo getProductInfoByUuid(CurrentUser currentUser, String uuid) throws BizException;

    //**************************以下方法由开发者补充*********************************/

    /**
     * 根据主键进行逻辑删除
     */
    void deleteProductInfoByIdLogical(CurrentUser currentUser, Long id) throws BizException;
}
