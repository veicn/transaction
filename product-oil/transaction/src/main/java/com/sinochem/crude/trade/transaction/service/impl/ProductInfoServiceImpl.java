package com.sinochem.crude.trade.transaction.service.impl;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.constant.Mark;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.dao.ProductInfoMapper;
import com.sinochem.crude.trade.transaction.domain.po.ProductInfo;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.domain.po.TransportInfo;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.helper.IdentificationHelper;
import com.sinochem.crude.trade.transaction.service.ProductInfoService;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductInfoServiceImpl implements ProductInfoService{

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private IdentificationHelper identificationHelper;


    @Override
    public Long saveProductInfo(CurrentUser currentUser, ProductInfo productInfo) throws BizException {

        if (productInfo == null) {
            return null;
        }

        productInfo.setUuid(identificationHelper.generateUuid());
        productInfoMapper.insert(productInfo);

        return productInfo.getId();
    }

    @Override
    public Long saveAdditionalProductInfo(CurrentUser currentUser, Long originalId, ProductInfo productInfo) throws BizException {

        if(productInfo != null){
            productInfo.setUuid(identificationHelper.generateUuid());
        }
        if (originalId == null) {
            return saveProductInfo(currentUser, productInfo);
        }

        ProductInfo originalProductInfo = getProductInfoById(currentUser, originalId);
        if (originalProductInfo == null) {
            return saveProductInfo(currentUser, productInfo);
        }

        originalProductInfo.setUuid(identificationHelper.generateUuid());
        Long id = saveProductInfo(currentUser, originalProductInfo);
        productInfo.setId(id);
        productInfoMapper.updateByPrimaryKeySelective(productInfo);

        return id;
    }

    @Override
    public void deleteProductInfoById(CurrentUser currentUser, Long id) throws BizException {

    }

    @Override
    public void updateProductInfo(CurrentUser currentUser, ProductInfo productInfo) throws BizException {

    }

    @Override
    public void updateProductInfoSelective(CurrentUser currentUser, ProductInfo productInfo) throws BizException {

    }

    @Override
    public ProductInfo getProductInfoById(CurrentUser currentUser, Long id) throws BizException {

        if (id == null) {
            return null;
        }

        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(id);
        return productInfo;
    }

    @Override
    public ProductInfo getProductInfoByUuid(CurrentUser currentUser, String uuid) throws BizException {

        if (StringUtil.isEmpty(uuid)) {
            return null;
        }

        ProductInfo productInfo = productInfoMapper.selectByUuid(uuid);
        return productInfo;
    }

    @Override
    public void deleteProductInfoByIdLogical(CurrentUser currentUser, Long id) throws BizException {
        BizException bizException = new BizException();

        if (currentUser == null || id == null) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode()); //使用ExceptionEnum来枚举异常的code
            throw bizException;
        }

        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(id);
        if (productInfo == null) {
            bizException.setCode(ExceptionEnum.NO_DATA.getCode());
            throw bizException;
        }

        ProductInfo productInfoForUpdate = new ProductInfo();
        productInfoForUpdate.setId(id);
        productInfoForUpdate.setAliveFlag(Mark.DELETED);
        productInfoMapper.updateByPrimaryKeySelective(productInfoForUpdate);
    }
}
