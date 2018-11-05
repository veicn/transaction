package com.sinochem.crude.trade.transaction.model.vo;

import com.sinochem.crude.trade.common.base.BaseDomainVO;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.*;

/**
 * 组合各部分信息的销售单的VO
 * @author Yichen Zhao
 * date: 20180303
 */
public class SaleSheetCombineVO extends BaseDomainVO<SaleSheetCombine> {

    /**
     * 销售单信息
     */
    private SaleSheetVO saleSheetVO;

    /**
     * 商品信息
     */
    private ProductInfoVO productInfoVO;

    /**
     * 计价信息
     */
    private PricingInfoVO pricingInfoVO;

    /**
     * 运输信息
     */
    private TransportInfoVO transportInfoVO;

    /**
     * 卖家信息
     */
    private StakeHolderInfoVO salerInfoVO;

    /**
     * 其它信息
     */
    private OtherInfoVO otherInfoVO;

    public SaleSheetCombineVO() {

    }

    public SaleSheetCombineVO(SaleSheetCombine saleSheetCombine) {
        super(saleSheetCombine);
    }

    @Override
    protected void convertToVO(SaleSheetCombine domain) {
        SaleSheet saleSheet = domain.getSaleSheet();
        if (saleSheet != null) {
            this.saleSheetVO = new SaleSheetVO(saleSheet);
        }

        ProductInfo productInfo = domain.getProductInfo();
        if (productInfo != null) {
            this.productInfoVO = new ProductInfoVO(productInfo);
        }

        PricingInfo pricingInfo = domain.getPricingInfo();
        if (pricingInfo != null) {
            this.pricingInfoVO = new PricingInfoVO(pricingInfo);
        }

        TransportInfo transportInfo = domain.getTransportInfo();
        if (transportInfo != null) {
            this.transportInfoVO = new TransportInfoVO(transportInfo);
        }

        StakeholderInfo salerInfo = domain.getSalerInfo();
        if (salerInfo != null) {
            this.salerInfoVO = new StakeHolderInfoVO(salerInfo);
        }

        OtherInfo otherInfo = domain.getOtherInfo();
        if (otherInfo != null) {
            this.otherInfoVO = new OtherInfoVO(otherInfo);
        }
    }

    @Override
    protected SaleSheetCombine convertToDomain() {
        SaleSheetCombine saleSheetCombine = new SaleSheetCombine();

        if (this.saleSheetVO != null) {
            saleSheetCombine.setSaleSheet(this.saleSheetVO.getDomain());
        }

        if (this.productInfoVO != null) {
            saleSheetCombine.setProductInfo(this.productInfoVO.getDomain());
        }

        if (this.pricingInfoVO != null) {
            saleSheetCombine.setPricingInfo(this.pricingInfoVO.getDomain());
        }

        if (this.transportInfoVO != null) {
            saleSheetCombine.setTransportInfo(this.transportInfoVO.getDomain());
        }

        if (this.salerInfoVO != null) {
            saleSheetCombine.setSalerInfo(this.salerInfoVO.getDomain());
        }

        if (this.otherInfoVO != null) {
            saleSheetCombine.setOtherInfo(this.otherInfoVO.getDomain());
        }

        return saleSheetCombine;
    }

    /** getters and setters */
    public SaleSheetVO getSaleSheetVO() {
        return saleSheetVO;
    }

    public void setSaleSheetVO(SaleSheetVO saleSheetVO) {
        this.saleSheetVO = saleSheetVO;
    }

    public ProductInfoVO getProductInfoVO() {
        return productInfoVO;
    }

    public void setProductInfoVO(ProductInfoVO productInfoVO) {
        this.productInfoVO = productInfoVO;
    }

    public PricingInfoVO getPricingInfoVO() {
        return pricingInfoVO;
    }

    public void setPricingInfoVO(PricingInfoVO pricingInfoVO) {
        this.pricingInfoVO = pricingInfoVO;
    }

    public TransportInfoVO getTransportInfoVO() {
        return transportInfoVO;
    }

    public void setTransportInfoVO(TransportInfoVO transportInfoVO) {
        this.transportInfoVO = transportInfoVO;
    }

    public StakeHolderInfoVO getSalerInfoVO() {
        return salerInfoVO;
    }

    public void setSalerInfoVO(StakeHolderInfoVO salerInfoVO) {
        this.salerInfoVO = salerInfoVO;
    }

    public OtherInfoVO getOtherInfoVO() {
        return otherInfoVO;
    }

    public void setOtherInfoVO(OtherInfoVO otherInfoVO) {
        this.otherInfoVO = otherInfoVO;
    }
}
